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
