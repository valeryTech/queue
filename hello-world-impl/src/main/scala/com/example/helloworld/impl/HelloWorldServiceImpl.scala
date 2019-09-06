package com.example.helloworld.impl

import akka.NotUsed
import com.example.helloworld.api.HelloWorldService
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.concurrent.Future

/**
  * Implementation of the HelloWorldService.
  */
class HelloWorldServiceImpl extends HelloWorldService {

  override def hello(id: String): ServiceCall[NotUsed, String] = ServiceCall { _ =>
    Future.successful(id.toString)
  }

}
