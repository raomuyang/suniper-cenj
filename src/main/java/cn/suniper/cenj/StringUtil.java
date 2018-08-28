package cn.suniper.cenj;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public class StringUtil {

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

    /**
     * AAA to Aaa
     * aaa to Aaa
     *
     * @param str
     * @return
     */
    public static String word2Camel(String str) {
        if (str == null) return null;
        char[] array = str.toLowerCase().toCharArray();
        array[0] -= 32;
        return new String(array);
    }

}
