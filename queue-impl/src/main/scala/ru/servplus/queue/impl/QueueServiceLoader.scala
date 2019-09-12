package ru.servplus.queue.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.server._
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import play.api.libs.ws.ahc.AhcWSComponents
import com.softwaremill.macwire._
import ru.servplus.queue.api.Queue

class QueueServiceLoader
  extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new QueueServiceApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new QueueServiceApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[Queue])
}

abstract class QueueServiceApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = serverFor[Queue](wire[QueueImpl])
}
