import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class HorseTest {
 @Test
    public void     tesExceptionIntConstructorWhenFirstArgumentIsNull(){
     assertThrows(IllegalArgumentException.class,()->{new Horse(null,1,1);});
 }

 @Test
    public void testExceptionMessageInConstructorWhenFirstArgumentIsNull() {
     String expected = "Name cannot be null.";
     try {
         Horse horse = new Horse(null, 1, 1);
     } catch (IllegalArgumentException ex) {
         assertEquals(expected, ex.getMessage());
     }
 }

@ParameterizedTest
  @ValueSource(strings = {"","   ","\t","\n"})
  public void testExceptionInConstructorWhenFirstArgumentIsBlank (String argument){
         assertThrows(IllegalArgumentException.class,()->{new Horse(argument,1,1);});
     }

  @ParameterizedTest
  @ValueSource(strings = {"","   ","\t","\n"})
  public void testExceptionMessageInConstructorWhenFirstArgumentIsBlank(String argument){
     String expected = "Name cannot be blank.";
      try {
          Horse horse = new Horse(argument, 1, 1);
      } catch (IllegalArgumentException ex) {
          assertEquals(expected, ex.getMessage());
      }
  }

  @Test
  public void testExceptionInConstructorWhenSecondArgumentIsNegative(){
     assertThrows(IllegalArgumentException.class,()->{new Horse("Igogo",-7,8);});
  }

  @Test
    public void testExceptionMessageInConstuctorWhenSecondArgumentIsNegative(){
      String expected = "Speed cannot be negative.";;
      try {
          Horse horse = new Horse("Pegas", -1, 1);
      } catch (IllegalArgumentException ex) {
          assertEquals(expected, ex.getMessage());
      }
  }

  @Test
    public void testExceptionInConstructorWhenThirdArgumentIsNegative(){
     assertThrows(IllegalArgumentException.class,()->{new Horse("Pegas",2,-9);});
  }

  @Test
  public void testExceptionMessageInConstructorWhenThirdArgumentIsNegative() {
      String expected = "Distance cannot be negative.";
      try {
          Horse horse = new Horse("Pegas", 1, -1);
      } catch (IllegalArgumentException ex) {
          assertEquals(expected, ex.getMessage());
      }
  }

  @Test
    public void testGetName(){
     String expected = "Igogo";
     Horse horse = new Horse(expected,1,2);
     assertEquals(expected,horse.getName());
  }

  @Test
    public void testGetSpeed(){
     double expected = 1.4;
     Horse horse = new Horse ("Igogo",expected,10);
     assertEquals(expected,horse.getSpeed());
  }

  @Test
    public void testGetDistance(){
      double expected = 7.3;
      Horse horse = new Horse ("Igogo",8,expected);
      assertEquals(expected,horse.getDistance());
  }
  @Test
    public void testGetDistanceDefaultValue(){
      Horse horse = new Horse ("Igogo",8);
      assertEquals(0,horse.getDistance());
  }

  @Test
    public void testMoveCallsGetRandomDouble(){
      try(MockedStatic <Horse> mockHorse = Mockito.mockStatic(Horse.class)){

          new Horse("Igogo",1,1).move();
          mockHorse.verify(()-> Horse.getRandomDouble(0.2,0.9));
      }
  }

  @ParameterizedTest
    @ValueSource(doubles = {1.1, 0.1,0.11, 0.5,11,1})
    public void testMoveChangesDistance(double random){
      try(MockedStatic <Horse> mock = Mockito.mockStatic(Horse.class)) {
          Horse horse = new Horse("Igogo",8,100);
          mock.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
          horse.move();
          assertEquals(100+8*random, horse.getDistance());

      }
      }
  }




