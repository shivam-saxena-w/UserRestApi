package restapis

case class User(name: String, UId : String = java.util.UUID.randomUUID().toString)