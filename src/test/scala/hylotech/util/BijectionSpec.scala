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

import hylotech.util.Bijection._
import org.specs.Specification

class BijectionSpec extends Specification {
  "a bijection" should {
    "be implicitly applicable" in {
      val str: String = 123.as[String]
      str must be equalTo("123")
      val num: Int = "123".as[Int]
      num must be equalTo(123)
    }
  }
}
