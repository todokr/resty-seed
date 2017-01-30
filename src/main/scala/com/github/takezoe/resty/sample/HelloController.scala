package com.github.takezoe.resty.sample

import com.github.takezoe.resty.Action
import org.slf4j.LoggerFactory

class HelloController {

  private val logger = LoggerFactory.getLogger(classOf[HelloController])

  @Action(method = "GET", path = "/hello/{name}")
  def hello(name: String): Message = {
    logger.debug(name)
    Message(name)
  }

}

case class Message(message: String)
