package ru.servplus.queue.impl

import java.util.concurrent.atomic.AtomicInteger

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceCall}
import com.lightbend.lagom.scaladsl.server.ServerServiceCall
import ru.servplus.server.api.queue.generate.{Check, `QueueApi`}

import scala.concurrent.Future

/**
  * Implementation of the HelloWorldService.
  */
class QueueImpl extends `QueueApi` {

  lazy val counter = new AtomicInteger(0);

  override def getPosition(): ServiceCall[Check, String] = ServiceCall { _ =>

    // check processing

    Future.successful(counter.incrementAndGet().toString)
  }


  override def descriptor: Descriptor = super.descriptor
}
