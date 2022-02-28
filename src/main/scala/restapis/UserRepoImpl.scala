package restapis
import scala.collection.mutable.ListBuffer

import spray.json._
import DefaultJsonProtocol._ // if you don't supply your own Protocol (see below)

class UserRepoImpl extends UserRepo {
  val UserList = ListBuffer.empty[String]
  override def insert(user: User): Unit = UserList.append( user.name )
  override def getAll: JsValue = UserList.toList.toJson
  override def getUser(name: String): JsValue = UserList.filter(_.contains(name)).toList.toJson
  override def deleteUser(name: String): Unit = UserList.remove(UserList.indexOf(name))

}
