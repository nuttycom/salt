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

package hylotech


package object util {
  def using[A, B](resource: A)(f: A => B)(implicit close: Close[A]): B = {
    try {
      f(resource)
    } finally {
      close.close(resource)
    }
  }

  def tryo[A](f: => A): Option[A] = try { Some(f) } catch { case ex => None }

  object implicits extends Implicits
}

package util {
  trait Implicits {
    implicit def IOClose[T <: java.io.Closeable]: Close[T] = new Close[T] {
      def close(a: T) = a.close
    }

    implicit def KT[A](a: A): KT[A] = new KT(a)
  }

  trait Close[A] {
    def close(a: A): Unit
  }

  case class KT[A](a: A) {
    def ->-(f: A => Any): A = { f(a); a }
    def ->*[B](f: A => B): B = f(a)
  }
}

