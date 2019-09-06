package com.example.helloworld.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object QueueService {
}

/**
  * The Hello World service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the HelloWorldService.
  */
trait QueueService extends Service {

  /**
    * Example: curl http://localhost:9000/api/hello/Alice
    */
  def hello(id: String): ServiceCall[NotUsed, String]


  override final def descriptor: Descriptor = {
    import Service._
    named("hello-world")
      .withCalls(
        restCall(Method.GET, "/api/hello/:id", hello _),
      )
      .withAutoAcl(true)
  }
}

/**
  * The greeting message class.
  */
case class GreetingMessage(message: String)

object GreetingMessage {
  /**
    * Format for converting greeting messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[GreetingMessage] = Json.format[GreetingMessage]
}


/**
  * The greeting message class used by the topic stream.
  * Different than [[GreetingMessage]], this message includes the name (id).
  */
case class GreetingMessageChanged(name: String, message: String)

object GreetingMessageChanged {
  /**
    * Format for converting greeting messages to and from JSON.
    *
    * This will be picked up by a Lagom implicit conversion from Play's JSON format to Lagom's message serializer.
    */
  implicit val format: Format[GreetingMessageChanged] = Json.format[GreetingMessageChanged]
}
