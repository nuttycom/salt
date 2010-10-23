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

object HLists {
  sealed trait HList {
    type Head
    type Tail <: HList
  }

  final class HNil extends HList {
    type Head = Nothing
    type Tail = HNil

    def :+:[T](v : T) = HCons(v, this)
  }

  val HNil = new HNil()

  final case class HCons[H, T <: HList](head : H, tail : T) extends HList {
    type This = H :+: T
    type Head = H
    type Tail = T

    def :+:[T](v : T) = HCons(v, this)
  }

  type :+:[H, T <: HList] = HCons[H, T]
  object :+: {
    def unapply[H, T <: HList](in: HCons[H, T]): Option[(H, T)] = Some(in.head, in.tail)
  }
}