package com.skp.commands

import java.io.File

sealed trait SensorCommand

case class StartFileProcessing(dir: String) extends SensorCommand

case class ReadFile(dir: String) extends SensorCommand
case class CountOfFiles(size: Int) extends SensorCommand

case class ProcessRecords(listFile: List[File]) extends SensorCommand

case class Result(res: List[String]) extends SensorCommand