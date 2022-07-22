import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {
    @Test
    public void testExceptionInConstructorWhenArgumentIsNull(){
        assertThrows(IllegalArgumentException.class,()->new Hippodrome(null));
    }
    @Test
    public void testExceptionMessageInConstructorWhenArgumentIsNull() {
        String expected = "Horses cannot be null.";
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException ex) {
            assertEquals(expected, ex.getMessage());
        }
    }

    @Test
    public void testExceptionInConstructorWhenArgumentIsEmpty() {
            assertThrows(IllegalArgumentException.class,()->new Hippodrome(new ArrayList<>()));
        }

    @Test
    public void testExceptionMessageInConstructorWhenArgumentIsEmpty() {
        String expected = "Horses cannot be empty.";
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException ex) {
            assertEquals(expected, ex.getMessage());
        }
    }

    @Test
    public void testGetHorses(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse N"+i,i,i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    public void moveTest(){
        List <Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse:horses ){
            verify(horse).move();
        }
    }

    @Test
    public void testGetWinner(){
        Horse horse1 = new Horse("Pegas",4,8);
        Horse horse2 = new Horse("Plotva",5,18);
        Horse horse3 = new Horse("seryj",2,4);
        Horse horse4 = new Horse("Julij",7,3);
        Horse horse5 = new Horse("Poni",1,1);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1,horse2,horse3,horse4,horse5));
        assertSame(horse2,hippodrome.getWinner());
    }

    }

