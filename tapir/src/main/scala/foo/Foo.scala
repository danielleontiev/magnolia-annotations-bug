package foo

import sttp.tapir.Schema.annotations.description

case class Foo(
    @description("foo!")
    foo: String
)
