package ru.servplus.queue.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.transport.Method
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object Queue {
}

/**
  * The Queue service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the QueueService.
  */
trait Queue extends Service {

  /**
    * Example: curl http://localhost:9000/api/number
    */
  def hello(id: String): ServiceCall[NotUsed, String]


  override final def descriptor: Descriptor = {
    import Service._
    named("queue-service")
      .withCalls(
        restCall(Method.GET, "/api/number/:id", hello _),
      )
      .withAutoAcl(true)
  }
}