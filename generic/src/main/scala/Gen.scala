import scala.language.experimental.macros
import scala.reflect.macros._

object Gen {
  def derive[T]: Unit = macro gen[T]

  def gen[T: c.WeakTypeTag](c: whitebox.Context): c.Tree = {
    import c.universe._

    val clazz = weakTypeOf[T].typeSymbol.asType.toType

    val annotations = clazz.baseClasses
      .flatMap { c =>
        c.asType.toType.members
          .filter(s => s.isTerm || s.isMethod)
          .filter(_.toString.contains("foo"))
          .flatMap(_.annotations)
      }

    annotations.foreach(_.tree) // touch annotations to evaluate lazy info
    println(annotations) // prints List(<notype>, <notype>, <notype>, <notype>)
    q"()"
  }
}
