package example

sealed trait LightSignal
case class Stop(count: Int) extends LightSignal
case class Go(count: Int) extends LightSignal

sealed trait TrafficDevice
case class PedestrianTrafficLight(
    signal: LightSignal,
    maxStopCount: Int,
    maxGoCount: Int
) extends TrafficDevice {
  def nextCount(): PedestrianTrafficLight =
    signal match {
      case Go(count) =>
        val nextLight = if (count > 0) Go(count - 1) else Stop(maxStopCount)
        PedestrianTrafficLight(nextLight, maxStopCount, maxGoCount)
      case Stop(count) =>
        val nextLight = if (count > 0) Stop(count - 1) else Go(maxGoCount)
        PedestrianTrafficLight(nextLight, maxStopCount, maxGoCount)
    }
}

object Hello extends App {
  val printSignalCount = (trafficLight: PedestrianTrafficLight) => {
    println(trafficLight.signal match {
      case Go(count)   => s"Go for $count"
      case Stop(count) => s"Stop for $count"
    })
  }

  val trafficLight = PedestrianTrafficLight(Stop(1), 1, 2)
  val trafficLight2 = trafficLight.nextCount()
  val trafficLight3 = trafficLight2.nextCount()

  printSignalCount(trafficLight)
  printSignalCount(trafficLight2)
  printSignalCount(trafficLight3)

  val otherTrafficLight = PedestrianTrafficLight(Stop(1), 1, 2)
  println(trafficLight == otherTrafficLight)
}
