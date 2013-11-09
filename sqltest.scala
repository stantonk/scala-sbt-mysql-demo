object App {

  def main(args: Array[String]) {
    val id = 2

    val msg = MessagesDao.findById(id)
    if (msg.isDefined)
      println(msg.toString)
    else
      println("No message found for id " + id)

    val messages = MessagesDao.getMessages(5)
    for ((pk, msg) <- messages)
      printf("%s = %s\n", pk, msg)
  }

}
