object Main extends App {
  import scala.reflect.runtime.universe._

  import scala.annotation.StaticAnnotation

  class description(val text: String) extends StaticAnnotation

  class Bar {
    @description("foo!") val foo = 321
  }

  weakTypeOf[Bar].typeSymbol.asType.toType.members
    .map(x => (x, x.annotations, x.isMethod, x.name.encodedName.toString))
    .filter(_._1.toString.contains("foo"))
    .foreach(println)

  /*
    Prints

    (value foo,List(Main.description("foo!")),false,foo$u0020)
    (value foo,List(),true,foo)

    Which can be an issue, because annotation on the field inside of the class (not in the constructor) ends up not on
    the getter, but on private field, and it's named "foo$u0020". So, names on constructor parameters and in private
    fields of the class are actually different
   */
}
