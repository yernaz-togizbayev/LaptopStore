class StoreItem (var id: Int, var name: String, var value: Int) extends Item with Logger {
    def logAction(actionName: String, name: String = name): Unit = { println(s"$name $actionName") }
}