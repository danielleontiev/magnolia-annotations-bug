import bar.Bar
import foo.Foo
import sttp.tapir.Schema

object Main extends App {
  Bar.bar(null)

  Schema.derived[Foo]
}
