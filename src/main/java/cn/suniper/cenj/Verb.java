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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public enum Verb {
    Find("select"),
    Select("select"),
    Query("select"),
    Delete("delete"),
    Update("update"),
    Insert("insert"),
    Count("select count");

    String symbol;

    final static Map<String, Verb> all;

    static {
        all = new HashMap<>();
        for (Verb verb : values()) {
            all.put(verb.name().toLowerCase(), verb);
        }
    }

    Verb(String symbol) {
        this.symbol = symbol;
    }

    public static Verb getVerb(String name) {
        return Optional.ofNullable(name).map(n -> all.get(n.toLowerCase())).orElse(null);
    }

    public String getSymbol() {
        return this.symbol;
    }
}
