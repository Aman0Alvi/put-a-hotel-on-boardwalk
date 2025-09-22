package org.example;

import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }

    @Test
    void appendAndStepThroughOrderWraps() {
        CircularLinkedList<String> list = new CircularLinkedList<>();
        list.append("Go");
        list.append("Mediterranean Avenue");
        list.append("Community Chest");
        list.append("Baltic Avenue");
        list.append("Income Tax");

        assertEquals("Go", list.getCurrent());

        list.step();
        assertEquals("Mediterranean Avenue", list.getCurrent());

        list.step(); // Community Chest
        list.step(); // Baltic Avenue
        list.step(); // Income Tax
        assertEquals("Income Tax", list.getCurrent());

        for (int i = 0; i < 5; i++) list.step();
        assertEquals("Income Tax", list.getCurrent());

        list.resetToHead();
        list.move(37);
        assertEquals("Community Chest", list.getCurrent());
    }

    @Test
    void getCurrentOnEmptyThrows() {
        CircularLinkedList<Integer> empty = new CircularLinkedList<>();
        assertThrows(java.util.NoSuchElementException.class, empty::getCurrent);
    }

    @Test
    void roll2d6MovesAccordingly() {
        // Seeded RNG so the test is deterministic
        CircularLinkedList<String> list = new CircularLinkedList<>(new Random(1234));
        list.append("Go");
        list.append("A");
        list.append("B");
        list.append("C");
        list.append("D");

        list.resetToHead();
        int rolled = list.roll2d6AndMove();

        assertTrue(rolled >= 2 && rolled <= 12, "roll should be between 2 and 12 inclusive");

        String afterRoll = list.getCurrent();

        list.resetToHead();
        list.move(rolled);
        String expected = list.getCurrent();

        assertEquals(expected, afterRoll, "roll2d6AndMove should advance by the rolled amount");
    }
}
