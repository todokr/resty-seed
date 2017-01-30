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
      create table testTable (
        id serial not null primary key,
        name varchar(64),
        created_at timestamp not null
      )
    """.execute.apply()
    Seq("Alice", "Bob", "Chris") foreach { name =>
      sql"insert into testTable (name, created_at) values (${name}, current_timestamp)".update.apply()
    }
    val entities: List[Map[String, Any]] = {
      sql"select * from testTable".map(_.toMap).list.apply()
    }
    logger.debug(entities.flatten.toString)


    // Register Application Controllers
    Resty.register(new JobController())
  }

}
