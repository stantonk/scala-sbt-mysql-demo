import java.sql.DriverManager
import java.util.Date


// here's your DAO :-)
case class Message(id: Long, message: String, created_time: Date)

object MessagesDao {

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
      connection.close()
    }
  }

  def getMessages(limit: Int) = {
    val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "")

    try {
      val statement = connection.prepareStatement("""select * from messages limit ?""")
      statement.setLong(1, limit)
      val rs = statement.executeQuery()
      var messages = Map[Long,Message]()
      while (rs.next())
        messages += (rs.getLong(1) -> new Message(rs.getLong(1), rs.getString(2), rs.getDate(3)))
      messages
    } finally {
      connection.close()
    }
  }

}
