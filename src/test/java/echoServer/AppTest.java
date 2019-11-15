package echoserver;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class AppTest {
    App classUnderTest;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;

    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void initialize() {
        classUnderTest = new App();
        setupStreams();
    }

    @After
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    public void testAppHasAGreeting() {
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }

    @Test
    public void testHelloWorldOutput() {
        classUnderTest.printHelloWorld();

        assertEquals("Hello World!\n", outContent.toString());
    }

    @Test
    public void testSystemInput() {
        String inputStr = "Hello!";
        InputStream input = new ByteArrayInputStream(inputStr.getBytes());
        System.setIn(input);

        assertEquals("Hello!", classUnderTest.getInput());
    }
}

