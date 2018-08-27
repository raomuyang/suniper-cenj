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

    private static final Map<String, Predicate> KV;

    Predicate(String symbol) {
        this.symbol = symbol;
    }

    final static Set<String> allNames;

    static {
        allNames = new HashSet<>();
        KV = new HashMap<>();
        for (Predicate predicate : values()) {
            allNames.add(predicate.name());
            KV.put(predicate.name().toLowerCase(), predicate);
        }
    }

    public static Predicate get(String name) {
        return Optional.ofNullable(name).map(n -> KV.get(n.toLowerCase())).orElse(null);
    }

    public static boolean isPrefix(String word) {
        return prefixes.contains(word);
    }

}
