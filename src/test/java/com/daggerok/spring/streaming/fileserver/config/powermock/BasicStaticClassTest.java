package com.daggerok.spring.streaming.fileserver.config.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BasicStaticClassTest.class)
public class BasicStaticClassTest {
    @Test
    public void testStaticMock() throws Exception {
        PowerMockito.mockStatic(StaticClass.class);

        when(StaticClass.underscore(anyString())).thenCallRealMethod();
        when(StaticClass.child(anyString())).thenReturn("powermock is awesome!");

        String actual = StaticClass.underscore("Powermock");

        PowerMockito.verifyStatic();
        assertEquals("something wrong here...", "powermock-is-awesome!", actual);
    }

    public static class StaticClass {
        public static String underscore(String brand) {
            return child(brand.toLowerCase()).replaceAll(" ", "-");
        }

        public static String child(String who) {
            return "we are don't care, anyway it will be mocked soon, so.. " + who;
        }
    }
}
