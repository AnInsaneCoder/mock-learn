package top.insanecoder.test;

import junit.framework.Assert;
import org.junit.Test;
import top.insanecoder.entity.UserDao;
import top.insanecoder.mock.TestDouble;

/**
 * Author: shaohang.zsh
 * Date: 2016/7/21
 */
public class MockTest {

    @Test
    public void testMock() {
        UserDao mock = TestDouble.mock(UserDao.class, new TestDouble.MockMethodInterceptor());
        System.out.println(mock.getAge(1, "zsh"));
        Assert.assertEquals(1, mock.getAge(1, "zsh"));
    }

    @Test
    public void testStub() {

        UserDao stub = TestDouble.mock(UserDao.class, new TestDouble.StubMethodInterceptor());
        TestDouble.when(stub.getAge(1, "zsh")).thenReturn(5);
        TestDouble.when(stub.getAge(2, "zsh")).thenReturn(100);
        TestDouble.when(stub.getAge(1, "zsy")).thenReturn(19);
        TestDouble.when(stub.getAge(2, "zsy")).thenReturn(1000);
        TestDouble.when(stub.getAge(3, "lhhl")).thenReturn(9);

        TestDouble.when(stub.getName(1)).thenReturn("zsh");
        TestDouble.when(stub.getName(2)).thenReturn("zsy");
        TestDouble.when(stub.getName(3)).thenReturn("lh");
        TestDouble.when(stub.getName(4)).thenReturn("hl");
        TestDouble.when(stub.getName(5)).thenReturn("lhhl");

        System.out.println(stub.getAge(1, "zsh"));
        System.out.println(stub.getAge(2, "zsh"));
        System.out.println(stub.getAge(1, "zsy"));
        System.out.println(stub.getAge(2, "zsy"));
        System.out.println(stub.getAge(3, "lhhl"));

        System.out.println(stub.getName(1));
        System.out.println(stub.getName(2));
        System.out.println(stub.getName(3));
        System.out.println(stub.getName(4));
        System.out.println(stub.getName(5));

        Assert.assertEquals(5, stub.getAge(1, "zsh"));
        Assert.assertEquals(100, stub.getAge(2, "zsh"));
        Assert.assertEquals(19, stub.getAge(1, "zsy"));
        Assert.assertEquals(1000, stub.getAge(2, "zsy"));
        Assert.assertEquals(9, stub.getAge(3, "lhhl"));

        Assert.assertEquals("zsh", stub.getName(1));
        Assert.assertEquals("zsy", stub.getName(2));
        Assert.assertEquals("lh", stub.getName(3));
        Assert.assertEquals("hl", stub.getName(4));
        Assert.assertEquals("lhhl", stub.getName(5));
    }

    @Test
    public void testSpy() {

        // UserDao is an interface, so NoSuchMethodError exception is thrown
        UserDao spy = TestDouble.mock(UserDao.class, new TestDouble.SpyMethodInterceptor());
        spy.getAge(1, "zsh");
    }
}
