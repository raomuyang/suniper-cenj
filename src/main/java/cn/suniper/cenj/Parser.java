package cn.suniper.cenj;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public class Parser {

    public static List<String> splitCamelWords(String str) {
        return Optional.ofNullable(str).map(s -> {
            List<String> words = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c)) {
                    words.add(sb.toString());
                    sb = new StringBuilder();
                }
                sb.append(c);
            }
            if (sb.length() > 0) {
                words.add(sb.toString());
            }
            return words;
        }).orElse(null);
    }

}
