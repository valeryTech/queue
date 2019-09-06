package com.example.helloworld.impl

import com.example.helloworld.api
import com.example.helloworld.api.HelloWorldService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}

/**
  * Implementation of the HelloWorldService.
  */
class HelloWorldServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends HelloWorldService {

  override def hello(id: String) = ServiceCall { _ =>
    // Look up the Hello World entity for the given ID.
    val ref = persistentEntityRegistry.refFor[HelloWorldEntity](id)

    // Ask the entity the Hello command.
    ref.ask(Hello(id))
  }

  override def useGreeting(id: String) = ServiceCall { request =>
    // Look up the Hello World entity for the given ID.
    val ref = persistentEntityRegistry.refFor[HelloWorldEntity](id)

    // Tell the entity to use the greeting message specified.
    ref.ask(UseGreetingMessage(request.message))
  }
}
