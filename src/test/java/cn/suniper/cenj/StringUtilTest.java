package cn.suniper.cenj;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Rao Mengnan
 *         on 2018/8/15.
 */
public class StringUtilTest {
    @Test
    public void splitCamelWords() throws Exception {
        String str = "testSplitCamelWords123";
        List<String> words = StringUtil.splitCamelWords(str);
        assertEquals(4, words.size());
        assertTrue(words.containsAll(Arrays.asList("test", "Split", "Camel", "Words123")));
    }

    @Test
    public void testWord2Camel() {
        String origin = "test";
        String except = "Test";
        assertEquals(except, StringUtil.word2Camel(origin));
    }

}