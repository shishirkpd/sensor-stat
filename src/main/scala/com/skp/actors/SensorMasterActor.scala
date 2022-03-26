package com.skp.actors

import akka.actor.typed.scaladsl.Behaviors
import com.skp.commands._

import java.io.{BufferedWriter, File, FileWriter}

object SensorMasterActor {
  def apply() = Behaviors.setup[SensorCommand] { context =>
    Behaviors.receiveMessage {
      case StartFileProcessing(dir) =>
        val fileReaderActor = context.spawn(FileReaderActor(context.self), "FileReaderActor")
        fileReaderActor ! ReadFile(dir)
        Behaviors.same
      case CountOfFiles(size) =>
        println(s"Num of processed files: $size")
        Behaviors.same
      case Result(res) =>
        val lines: List[String] = List("sensor-id", "min", "avg", "max").mkString(",") +: res

        writeToFIle(lines)

        lines map println

        Behaviors.stopped
    }
  }

  private def writeToFIle(lines: List[String]) = {
    val file = new File("res.csv")
    val bw = new BufferedWriter(new FileWriter(file))

    for (line <- lines) {
      bw.write(line)
      bw.write("\n")
    }
    bw.close()
  }
}
