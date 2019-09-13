package ru.servplus.queue.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents
import ru.servplus.server.api.queue.generate.`QueueApi`

class QueueServiceLoader
  extends LagomApplicationLoader {

  override def load(context: LagomApplicationContext): LagomApplication =
    new QueueServiceApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator
    }

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new QueueServiceApplication(context) with LagomDevModeComponents

  override def describeService = Some(readDescriptor[`QueueApi`])
}

abstract class QueueServiceApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with AhcWSComponents {

  // Bind the service that this server provides
  override lazy val lagomServer: LagomServer = serverFor[`QueueApi`](wire[QueueImpl])
}
