import sbt._

class Suits(info: ProjectInfo) extends DefaultProject(info) {
  val mavenLocal = "Local Maven Repository" at "file://"+Path.userHome+"/.m2/repository"
}

// vim: set ts=4 sw=4 et:
