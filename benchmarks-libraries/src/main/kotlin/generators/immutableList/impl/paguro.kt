/*
 * Copyright 2016-2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package generators.immutableList.impl

import generators.immutableList.*

object PaguroListImplementation: ListImplementation {
    override val packageName: String
            = "paguro"

    override fun type(): String
            = "org.organicdesign.fp.collections.RrbTree.ImRrbt<$listElementType>"
    override fun empty(): String
            = "org.organicdesign.fp.collections.RrbTree.empty<$listElementType>()"

    override fun getOperation(list: String, index: String): String
            = "$list.get($index)"
    override fun setOperation(list: String, index: String, newValue: String): String
            = "$list.replace($index, $newValue)"
    override fun addOperation(list: String, element: String): String
            = "$list.append($element)"
    override fun removeLastOperation(list: String): String
            = "$list.without($list.size - 1)"

    override fun iterateLastToFirst(list: String, size: String): String {
        return """
    @Benchmark
    fun lastToFirst(bh: Blackhole) {
        val iterator = $list.listIterator($size)

        while (iterator.hasPrevious()) {
            bh.consume(iterator.previous())
        }
    }"""
    }
}