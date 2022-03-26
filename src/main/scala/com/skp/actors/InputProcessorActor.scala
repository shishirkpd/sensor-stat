package com.skp.actors

import akka.actor.typed.scaladsl.Behaviors
import akka.actor.typed.{ActorRef, Behavior}
import com.skp.commands.{ProcessRecords, Result, SensorCommand}

import java.io.File

object InputProcessorActor {

  var mapOfValue: scala.collection.immutable.Map[String, List[String]] = Map.empty
  var numberOfMeasurementsProcessed = 0
  var numberOfMeasurementsFailed = 0

  def apply(replyTo: ActorRef[SensorCommand]): Behavior[SensorCommand] = Behaviors.setup[SensorCommand] { _ =>
    Behaviors.receiveMessage {
      case ProcessRecords(listFile) =>
        processFile(listFile)

        println(s"Num of processed measurements: $numberOfMeasurementsProcessed")
        println(s"Num of failed measurements: $numberOfMeasurementsFailed")

        val res = generateResult

        replyTo ! Result(res.toList)
        Behaviors.same
    }
  }

  private def generateResult = {
    mapOfValue.map { entry =>
      val listOfValidValues = entry._2.filter(!_.equalsIgnoreCase("NaN")).map(_.toInt)
      listOfValidValues match {
        case Nil => List(entry._1, "NaN", "NaN", "NaN").mkString(",")
        case x: List[Int] => List(entry._1, x.min, x.sum / x.size, x.max).mkString(",")
      }
    }
  }

  private def processFile(listFile: List[File]) = {
    for {
      fileName <- listFile
    } yield {
      val file = io.Source.fromFile(fileName)
      val lines = file.getLines.drop(1).toList
      lines.map { line =>
        val a = line.split(",")
        numberOfMeasurementsProcessed += 1
        if (a.contains("NaN")) numberOfMeasurementsFailed += 1
        mapOfValue.get(a(0)) match {
          case Some(value) => mapOfValue = mapOfValue + (a(0) -> (value :+ a(1)))
          case None => mapOfValue = mapOfValue + (a(0) -> List(a(1)))
        }
      }
      file.close()
    }
  }
}
