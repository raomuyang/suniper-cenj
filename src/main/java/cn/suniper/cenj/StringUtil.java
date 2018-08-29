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
     * @param str single word
     * @return Camel formatted word
     */
    public static String word2Camel(String str) {
        if (str == null) return null;
        char[] array = str.toLowerCase().toCharArray();
        array[0] -= 32;
        return new String(array);
    }

}
