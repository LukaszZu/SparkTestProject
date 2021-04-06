name := "SparkTestProject"

version := "0.1"

scalaVersion := "2.12.13"

idePackagePrefix := Some("com.zz.demo")

val sparkVersion = "3.1.1"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion