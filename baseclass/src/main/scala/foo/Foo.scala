package foo

import baz.description

class Base(
    @description("base foo")
    val foo: String
)
case class Foo(
    @description("foo!")
    override val foo: String
) extends Base(foo)
