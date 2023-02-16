object MainApp{  
  def main(args: Array[String]): Unit = {
    val data = CSVreader("data.csv")
    val db = new Database
    for (i <- 0 to (data.length - 1))
      db.store(data.apply(i))

    println("--- SUM UP ---")
    println(db.sumUp())

    
    println("\n--- FILTERED BY ASUS ---")
    val asus = db.filterByName("ASUS", data)
    for (i <- 0 to asus.length - 1)
      println(asus.apply(i).name)
    println(asus.length)



    println("\n--- FILTERED BY Lenovo ---")
    val lenovo = db.filterByName("Lenovo", data)
    for (i <- 0 to lenovo.length - 1)
      println(lenovo.apply(i).name)
    println(lenovo.length)


    println("\n--- FILTERED BY HP ---")
    var hp = db.filterByName("HP", data)
    for (i <- 0 to hp.length - 1)
      println(hp.apply(i).name)
    println(hp.length)


    println("\n--- SORTED ITEMS ---")
    for (i <- 0 to data.length - 1)
      println(db.sortByValueDesc().apply(i).name + " " + db.sortByValueDesc().apply(i).value)    

    
    println("\n--- Higher Order ---")
    db.higherThan(300)
    println(db.higherThan(300).length)
  }

  


  
  def CSVreader(filename: String): Array[StoreItem] = {
    var laptops: Array[StoreItem] = Array.empty
    val file = io.Source.fromFile(filename)

    for (line <- file.getLines().drop(1)) {
      val columns = line.split(",").map(_.trim())
      val laptop = new StoreItem(columns(0).toInt, columns(1), columns(2).toInt)
      laptops = laptops :+ laptop
    }

    file.close
    laptops
  }
}
