object Main extends App {
  val greeter = new Greeter("hello, ", "!")
  greeter.greet("Scala developer")
}

class Greeter(prefix: String, suffix: String) {
  def greet(name: String) {
    println(prefix + name + suffix)
  }
}
