package com.zz.demo

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions.{first, struct}

object FailStructWithWindow {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("demo").master("local").getOrCreate()

    import spark.implicits._

    val data = Seq(
      ("a", "b"),
      ("a", "b")
    ).toDF("a", "b").select($"*", struct("a", "b").as("ab"))

    val w = Window.partitionBy("a").orderBy("b")

    val e = data.select(first(struct("*")).over(w).as("o"))

    e.select("o.ab.*").show()

    /* should return as below, as in spark 3.0.2 and 2.4.7
    +---+---+
    |  a|  b|
    +---+---+
    |  a|  b|
    |  a|  b|
    +---+---+
     */
  }
}
