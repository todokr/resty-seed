package io.github.todokr.resty_seed

import javax.servlet.annotation.WebListener
import javax.servlet.{ServletContextEvent, ServletContextListener}

import com.github.takezoe.resty.Resty
import io.github.todokr.resty_seed.controller.JobController
import org.slf4j.LoggerFactory
import scalikejdbc._

@WebListener
class InitializeListener extends ServletContextListener {

  private val logger = LoggerFactory.getLogger(classOf[InitializeListener])

  override def contextDestroyed(sce: ServletContextEvent): Unit = {
  }

  override def contextInitialized(sce: ServletContextEvent): Unit = {
    // Initialize DB connection
    Class.forName("org.h2.Driver")
    ConnectionPool.singleton("jdbc:h2:mem:resty_seed", "user", "pass")
    logger.info("DB Connection initialized")

    // SQL test (must be deleted on prod)
    implicit val session = AutoSession
    sql"""
      create table job (
        id serial not null primary key,
        siteCode varchar(64),
        crawlConfig varchar
      )
    """.execute.apply()
    sql"""
         insert into job (siteCode, crawlConfig)
         values ('news-picks', '{"hoge": "fuga"}')
       """.execute().apply()
    val entities: List[Map[String, Any]] = {
      sql"select * from job".map(_.toMap).list.apply()
    }
    logger.info(entities.flatten.toString)


    // Register Application Controllers
    Resty.register(new JobController())
  }

}
