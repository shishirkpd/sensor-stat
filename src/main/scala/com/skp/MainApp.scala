package com.skp

import akka.actor.typed.ActorSystem
import com.skp.actors.SensorMasterActor
import com.skp.commands.StartFileProcessing

import scala.io.StdIn.readLine

object MainApp extends App {
  println(s"enter the directory path .")
  val dirName = readLine()

  val sensorMasterActor = ActorSystem(SensorMasterActor(), "SensorMaster")

  sensorMasterActor ! StartFileProcessing(dirName)
}
