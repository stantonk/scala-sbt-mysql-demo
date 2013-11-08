import java.sql.{Date, DriverManager}

case class Message(id: Long, message: String, created_time: Date)

object App {

  def main(args: Array[String]) {
    val id = 2

    val msg = findById(id)
    if (msg.isDefined)
      println(msg.toString)
    else
      println("No message found for id " + id)
  }

  def findById(id: Long): Option[Message] = {
    val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")

    try {
      val statement = connection.prepareStatement("""select * from messages where id = ?""")
      statement.setLong(1, id)
      val rs = statement.executeQuery()
      if (rs.next())
        Option(new Message(rs.getLong(1), rs.getString(2), rs.getDate(3)))
      else
        Option.empty
    } finally {
      connection.close
    }
  }
}
