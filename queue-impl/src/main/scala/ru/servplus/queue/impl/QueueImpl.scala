package ru.servplus.queue.impl

import akka.NotUsed
import com.lightbend.lagom.scaladsl.api.ServiceCall
import ru.servplus.queue.api.Queue

import scala.concurrent.Future

/**
  * Implementation of the HelloWorldService.
  */
class QueueImpl extends Queue {

  override def hello(id: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    Future.successful(id.toString)
  }

}
