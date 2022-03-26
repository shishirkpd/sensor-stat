# Sensor Statistics Task
### This application take the directory as input and read all the csv file and process them
#### Create a command line program that calculates statistics from humidity sensor data.



### How to run the application 
```` 
Create the fat jar by below command
 1. sbt assembly
````
```
Execute the jar
 1.java -jar sensor-stats-assembly-0.1.0-SNAPSHOT.jar
```
```
enter the dir to read file
eg csvFolder
```

### Alternate option to run 
```
Run MainApp.scala
```

## Outputs
```
 Num of processed files: 2
 Num of processed measurements: 7
 Num of failed measurements: 2
 sensor-id,min,avg,max
 s2,78,82,88
 s3,NaN,NaN,NaN
 s1,10,54,98
```
