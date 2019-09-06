package com.example.helloworld.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object QueueService {
}

/**
  * The Queue service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the QueueService.
  */
trait QueueService extends Service {

  /**
    * Example: curl http://localhost:9000/api/number
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