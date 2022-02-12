object Main extends App {

  import scala.reflect.runtime.universe._

  case class Foo(
      foo: String
  )

  val members = weakTypeOf[Foo].typeSymbol.asType.toType.members

  val getter = members.filter(_.isMethod).find(_.toString.contains("foo")).get
  val field = members.filter(_.isTerm).find(_.toString.contains("foo")).get

  // https://github.com/softwaremill/magnolia/blob/9b3e5dbfcc5718171f294c0c5b5bb9d31835276f/core/src/main/scala/magnolia1/magnolia.scala#L192-L195
  def magnolia(left: Symbol, right: Symbol) =
    (left, right) match {
      case (t1: TermSymbol, t2: TermSymbol) if t1.name == t2.name =>
        println("terms equal")
      case (m1: MethodSymbol, m2: MethodSymbol) if m1.name == m2.name && m1.paramLists.size == m2.paramLists.size =>
        println("methods equal")
      case _ =>
        println("not equal")
    }

  magnolia(field, field) // prints "terms equal"
  magnolia(getter, getter) // also prints "terms equal", because
  // type MethodSymbol >: Null <: MethodSymbolApi with TermSymbol
}
