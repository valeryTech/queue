package com.example.helloworld.impl

import akka.NotUsed
import com.example.helloworld.api.QueueService
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

/**
  * Implementation of the HelloWorldService.
  */
class QueueServiceImpl extends QueueService {

  override def hello(id: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    Future.successful(id.toString)
  }

}
