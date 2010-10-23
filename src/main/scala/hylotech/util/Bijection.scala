/*
 *  Copyright 2010 nuttycom.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package hylotech.util

sealed trait Unapply[A, B] {
  def unapply(b: B): A
}

trait Bijection[A, B] extends Function1[A, B] with Unapply[A, B] 

sealed class Biject[A](a: A) {
  def as[B](implicit f: Either[Bijection[A, B], Bijection[B, A]]): B = f.fold(_ apply a, _ unapply a)
}

trait Bijections {
  implicit def biject[A](a: A): Biject[A] = new Biject(a)
  implicit def forwardEither[A, B](implicit a: Bijection[A,B]): Either[Bijection[A,B], Bijection[B,A]] = Left(a)
  implicit def reverseEither[A, B](implicit b: Bijection[B,A]): Either[Bijection[A,B], Bijection[B,A]] = Right(b)
}

object Bijection extends Bijections {
  implicit object i2s extends Bijection[Int, String] {
    override def apply(a: Int): String = a.toString
    override def unapply(b: String): Int = b.toInt
  }

  implicit object l2s extends Bijection[Long, String] {
    override def apply(a: Long): String = a.toString
    override def unapply(b: String): Long = b.toLong
  }
}