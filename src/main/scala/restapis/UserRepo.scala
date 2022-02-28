package restapis

import spray.json.JsValue

trait UserRepo {
  def insert(name : User) : Unit
  def getAll: JsValue
  def getUser(name: String) : JsValue
  def deleteUser(name: String)
}
