package ru.servplus.queue.impl

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceCall}
import ru.servplus.server.api.queue.generate.{Check, `QueueApi`}

import scala.concurrent.Future

/**
  * Implementation of the HelloWorldService.
  */
class QueueImpl extends `QueueApi` {

  override def getPosition(): ServiceCall[Check, Done] = ???

  override def descriptor: Descriptor = super.descriptor
}
