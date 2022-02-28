package restapis

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import spray.json.JsValue
import akka.http.scaladsl.server.Directives._
import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

class UserService(userRepo: UserRepo){
  def insert(str: User): Unit = userRepo.insert(str)
  def getAllUsers: JsValue = userRepo.getAll
  def getUser(id: String): JsValue = userRepo.getUser(id)
  def deleteUser(name: String): Unit = userRepo.deleteUser(name)
}

object UserMethods extends App {
  implicit val system: ActorSystem = ActorSystem("system")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  val userRepo = new UserRepoImpl
  val UserService = new UserService(userRepo)

  val routes = {
      get{
        path("getAllUsers"){
          complete(UserService.getAllUsers.toString())
        }
      }~ post{
        path("addUser" / Segment){ userSeg =>
          UserService.insert(User(userSeg))
          complete("User Added")
        }
      }~ get {
        path ("getUser" / Segment) { userSeg =>
          complete(UserService.getUser(userSeg).toString())
        }
      }~ delete {
        path("deleteUser" / Segment) { userSeg =>
          UserService.deleteUser(userSeg)
          complete("user deleted")
        }
      }

  }

  val bindFuture = Http().newServerAt("127.0.0.1", 9000).bind(routes)

  StdIn.readLine()
  bindFuture.flatMap(_.unbind()).onComplete(_ => system.terminate())
}