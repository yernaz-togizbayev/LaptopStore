import scala.annotation.meta.getter
class Database extends ShoppingCart {
    private var storedItems: Array[StoreItem] = Array.empty

    override def delete(id: Int): Array[StoreItem] = {
        storedItems.find(_.id == id) match {
            case Some(item) => item.logAction("deleted")
            case None => println(s"Id $id not found")
        }
        storedItems = storedItems.filterNot(_.id == id)
        storedItems
    }

    override def search(name: String): Array[StoreItem] = {
        val found = storedItems.filter(_.name == name)

        if (found.isEmpty)
            println(s"$name not found")
        else
            found.foreach(_.logAction("found"))
        found
    }

    override def sortByValueAsc(): Array[StoreItem] = {
        storedItems.sortBy(_.value)
    }

    override def sortByValueDesc(): Array[StoreItem] = {
        storedItems.sortBy(-_.value)
    }
    
    override def store(item: StoreItem): Array[StoreItem] = {
        if (storedItems.indexWhere(_.id == item.id) == -1)
            storedItems = storedItems :+ item
        item.logAction{"stored"}
        storedItems
    }

    override def sumUp(): Int = {
        storedItems.foldLeft(0)(_ + _.value)
    }

    def filterByName(name: String, items: Array[StoreItem]): Array[StoreItem] = {
        var tmp: Array[StoreItem] = Array.empty
        for (i <- 0 to items.length - 1) {
            if (items.apply(i).name.contains(name))
                tmp = tmp :+ items.apply(i)
        }
        tmp.sortBy(_.value)
    }

    def higherThan(value: Int): Array[StoreItem] = {
        var tmp = sortByValueAsc().filter(_.value > value)
        tmp.map(i => i.logAction(i.value.toString()))
        tmp
    }

    def getStoreItem(): Array[StoreItem] = {
        storedItems
    }

}