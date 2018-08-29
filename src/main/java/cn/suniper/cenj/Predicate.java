/*
 * Copyright 2018 Suniper
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

package cn.suniper.cenj;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public enum Predicate {
    And("and"),
    Or("or"),
    Is("= ?"),
    By("where"),
    Equals("= ?"),
    Between("between ? and ?"),
    LessThan("< ?"),
    GreaterThan("> ?"),
    LessThanEquals("<= ?"),
    GreaterThanEquals(">= ?"),
    After("> ?"),
    Before("< ?"),
    IsNull("is null"),
    IsNotNull("is not null"),
    IsTrue("is true"),
    IsFalse("is false"),
    NotNull("is not null"),
    Like("like"),
    NotLike("not like"),
    StartingWith("like '?%'"),
    EndingWith("like '%?'"),
    Containing("like '%?%'"),
    OrderBy("order by"),
    Not("<> ?"),
    In("in (?)"),
    NotIn("not in (?)");

    String symbol;

    private static final List<String> prefixes = Arrays.asList(
            "Starting", "Ending", "Is", "Not", "Less", "Greater",
            "Order", "Before", "After", "In", "Like", "Containing",
            "Order", "And", "By", "Or"
    );

    private static final Map<String, Predicate> ALL;

    Predicate(String symbol) {
        this.symbol = symbol;
    }

    final static Set<String> allNames;

    static {
        allNames = new HashSet<>();
        ALL = new HashMap<>();
        for (Predicate predicate : values()) {
            allNames.add(predicate.name());
            ALL.put(predicate.name().toLowerCase(), predicate);
        }
    }

    public static Predicate get(String name) {
        return Optional.ofNullable(name).map(n -> ALL.get(n.toLowerCase())).orElse(null);
    }

    public static boolean isPrefix(String word) {
        return prefixes.contains(word);
    }

    public static boolean contains(String word) {
        if (word == null) return false;
        return ALL.keySet().contains(word.toLowerCase());
    }

}
