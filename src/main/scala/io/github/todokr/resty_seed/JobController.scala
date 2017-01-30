package io.github.todokr.resty_seed

import com.github.takezoe.resty.Action
import com.github.takezoe.resty.sample.Message
import org.slf4j.LoggerFactory

class JobController {

  private val logger = LoggerFactory.getLogger(classOf[JobController])

  @Action(method = "GET", path = "/jobs")
  def list: Seq[JobResponse] = ???

  @Action(method = "GET", path = "/jobs/{id}")
  def show(id: Int): JobResponse = ???

  @Action(method = "POST", path = "/jobs")
  def create(): JobResponse = ???

  @Action(method = "PUT", path = "/jobs/{id}")
  def edit(id: Int): JobResponse = ???

  @Action(method = "DELETE", path = "/jobs/{id}")
  def delete(id: Int): Unit = ???

  @Action(method = "POST", path = "/jobs/execute/{id}")
  def execute(id: Int): ExecuteResponse = ???

}

case class JobResponse(id: Int, siteCode: String, crawlConfig: String)
case class ExecuteResponse(completed: Long, results: Seq[String])