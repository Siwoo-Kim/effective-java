package com.siwoo.datastructure

object Functions extends App {

  def greet(name: String, age: Int) = "Hi, my name is %s and I am %d years old".format(name, age)

  def factorial(n: Int): Int = {
    if (n == 1) 1
    else n * factorial(n-1)
  }

  println(factorial(5))

  def fibonacci(n: Int): Int = {
    if (n == 1 || n == 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }
  println(fibonacci(8))

  //
  def isPrime(n: Int): Boolean = {
    def recur(div: Int): Boolean = {
      if (div <= 1) true
      else n % div != 0 && recur(div-1);
    }
    recur(n / 2)
  }

  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
