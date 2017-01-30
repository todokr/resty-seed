package io.github.todokr.resty_seed.service

trait JobService {

  def createJob(info: JobRequest): Either[String, JobResponse] = ???

  case class JobRequest(siteCode: String, crawlConfig: String)
  case class JobResponse(id: Int, siteCode: String, crawlConfig: String)
  case class ExecuteResponse(completed: Long, results: Seq[String])

}
