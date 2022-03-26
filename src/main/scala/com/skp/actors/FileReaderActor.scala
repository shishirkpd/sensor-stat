package com.skp.actors

import akka.actor.typed.ActorRef
import akka.actor.typed.scaladsl.Behaviors
import com.skp.commands.{CountOfFiles, ProcessRecords, ReadFile, SensorCommand}

import java.io.File

object FileReaderActor {
  def apply(replyTo: ActorRef[SensorCommand]) = Behaviors.setup[SensorCommand] { context =>
    Behaviors.receiveMessage {
      case ReadFile(dir) => {
        val listOfFiles: List[File] = new java.io.File(dir).listFiles.filter(_.getName.endsWith(".csv")).toList
        replyTo ! CountOfFiles(listOfFiles.size)
        val inputProcessorActor = context.spawn(InputProcessorActor(replyTo), "inputProcessorActor")
        inputProcessorActor ! ProcessRecords(listOfFiles)
        Behaviors.same
      }
    }

  }
}
