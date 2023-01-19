package org.example.value;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

class ValueTest {
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(A.class);
    }

    @Test
    @DisplayName("Inject from @Value annotation content")
    void test1() {
        A a = context.getBean(A.class);
        assertNotNull(a);
        assertEquals("some string", a.strValue);
    }

    @Test
    @DisplayName("Existing property")
    void test2() {
        A a = context.getBean(A.class);
        assertNotNull(a);
        assertEquals("HR", a.dbName);
    }

    @Test
    @DisplayName("Missing property - use default")
    void test3() {
        A a = context.getBean(A.class);
        assertNotNull(a);
        assertEquals(80, a.httpPort);
    }

    @Test
    @DisplayName("Existing property - does not use default")
    void test4() {
        A a = context.getBean(A.class);
        assertNotNull(a);
        assertEquals("ivan", a.username);
    }

    @Test
    @DisplayName("Existing system property - does not use default")
    void test5() {
        A a = context.getBean(A.class);
        assertNotNull(a);
        assertEquals("lilic", a.usernameFromSystem);
    }

    @Test
    @DisplayName("Missing @PropertySource")
    void test6() {
        context = new AnnotationConfigApplicationContext(AWithoutSource.class);
        AWithoutSource a = context.getBean(AWithoutSource.class);
        assertNotNull(a);
        assertNotEquals("HR", a.dbName);
    }

    @Test
    @DisplayName("Multiple @PropertySources")
    void test7() {
        context = new AnnotationConfigApplicationContext(AMultipleSources.class);
        AMultipleSources a = context.getBean(AMultipleSources.class);
        assertNotNull(a);
        assertEquals(81, a.httpPort);
        assertEquals("HR", a.dbName);
    }

    @Test
    @DisplayName("Multiple @PropertySources - same property")
    void test8() {
        context = new AnnotationConfigApplicationContext(AMultipleSources.class);
        AMultipleSources a = context.getBean(AMultipleSources.class);
        assertNotNull(a);
        assertEquals("georgi", a.username);
    }

    @Test
    @DisplayName("Constructor @Value")
    void test9() {
        context = new AnnotationConfigApplicationContext(B.class);
        B b = context.getBean(B.class);
        assertNotNull(b);
        assertEquals("Silistra", b.city);
    }

    @Test
    @DisplayName("Setter @Value")
    void test10() {
        context = new AnnotationConfigApplicationContext(B.class);
        B b = context.getBean(B.class);
        assertNotNull(b);
        assertEquals("ivan", b.user);
    }

    @Test
    @DisplayName("SpEl system property")
    void test11() {
        context = new AnnotationConfigApplicationContext(C.class);
        C c = context.getBean(C.class);
        assertNotNull(c);
        assertEquals("lilic", c.username);
    }

    @Test
    @DisplayName("SpEl system property - default value")
    void test12() {
        context = new AnnotationConfigApplicationContext(C.class);
        C c = context.getBean(C.class);
        assertNotNull(c);
        assertEquals("default", c.defaultStr);
    }

    @Test
    @Disabled
    @DisplayName("SpEl get value from other bean")
    void test13() {
        context = new AnnotationConfigApplicationContext(A.class, C.class);
        C c = context.getBean(C.class);
        assertNotNull(c);
        //assertEquals(80, c.httpPort);
    }

    @Test
    @DisplayName("SpEl get list property")
    void test14() {
        context = new AnnotationConfigApplicationContext(C.class);
        C c = context.getBean(C.class);
        assertNotNull(c);
        List<String> expectedValues = List.of("A", "B", "C");
        assertTrue(expectedValues.equals(c.valuesList));
    }

    @Test
    @DisplayName("SpEl get map property")
    void test15() {
        context = new AnnotationConfigApplicationContext(C.class);
        C c = context.getBean(C.class);
        assertNotNull(c);
        Map<String, Integer> expectedValues = Map.of(
                "key1", 1, "key2", 2, "key3", 3);
        assertTrue(expectedValues.equals(c.valuesMap));
    }

    @Test
    @DisplayName("SpEl get map value")
    void test16() {
        context = new AnnotationConfigApplicationContext(C.class);
        C c = context.getBean(C.class);
        assertNotNull(c);
        assertEquals(1, c.valuesMapKey1);
    }

    @Test
    @DisplayName("SpEl get map missing value")
    void test17() {
        context = new AnnotationConfigApplicationContext(C.class);
        C c = context.getBean(C.class);
        assertNotNull(c);
        assertNull(c.unknownMapKey);
    }
}
