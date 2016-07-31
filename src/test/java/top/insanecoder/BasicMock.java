package top.insanecoder;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static junit.framework.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: shaohang.zsh
 * Date: 2016/7/27
 */
public class BasicMock {

    @Test
    public void basicTest() {

        List<String> mockList = mock(ArrayList.class);

        when(mockList.get(10)).thenReturn("first mock");
        when(mockList.get(11)).thenReturn("second mock");

        assertEquals("first mock", mockList.get(10));
        assertEquals("second mock", mockList.get(11));

//        Mockito.when(mockList.get(20)).thenThrow(new RuntimeException("mock exception"));
//        mockList.get(20);

        mockList.add("once");
        mockList.add("three times");
        mockList.add("three times");
        mockList.add("three times");

        verify(mockList).add("once");
        verify(mockList, times(3)).add("three times");
    }

    @Test
    public void iterator_will_return_hello_world() {
        Iterator<String> iterator = mock(Iterator.class);
        when(iterator.next()).thenReturn("hello ").thenReturn("world!").thenReturn("zsh").thenReturn("tjy");
        String result = iterator.next() + iterator.next();
        System.out.println(result);
        assertEquals("hello world!", result);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

    @Test
    public void mockWithArguments() {
        Comparable com = mock(Comparable.class);
        when(com.compareTo("test")).thenReturn(1);
        when(com.compareTo("Test")).thenReturn(2);
        assertEquals(1, com.compareTo("test"));
        assertEquals(2, com.compareTo("Test"));
    }

    @Test
    public void mockWithAnyArguments() {
        Comparable com = mock(Comparable.class);
        when(com.compareTo(anyString())).thenReturn(-1);
        assertEquals(-1, com.compareTo("lalala"));
        assertEquals(-1, com.compareTo("hahaha"));
        assertEquals(-1, com.compareTo("shiyixia"));
    }

    @Test
    public void mockWithThrowsException() throws IOException {
        OutputStream out = mock(OutputStream.class);
        out.close();
        verify(out).close();
    }

    @Test
    public void mockWithAnswer() {
        List<List> mockList = mock(ArrayList.class);
        Answer<String> answer = new Answer<String>() {

            private String defaultAnswer = "answer";
            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] strs = invocationOnMock.getArguments();
                return defaultAnswer + " " + strs[0];
            }
        };

        when(mockList.get(anyInt())).then(answer);
        System.out.println(mockList.get(0));
        System.out.println(mockList.get(1));
        System.out.println(mockList.get(2));
        System.out.println(mockList.get(3));
        System.out.println(mockList.get(4));
    }
}
