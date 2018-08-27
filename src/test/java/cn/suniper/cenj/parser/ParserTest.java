package cn.suniper.cenj.parser;

import cn.suniper.cenj.Parser;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public class ParserTest {
    @Test
    public void splitCamelWords() throws Exception {
        String str = "testSplitCamelWords123";
        List<String> words = Parser.splitCamelWords(str);
        assertEquals(4, words.size());
        assertTrue(words.containsAll(Arrays.asList("test", "Split", "Camel", "Words123")));
    }

}