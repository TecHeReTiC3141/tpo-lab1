package lab1.task3.implementations;

import lab1.task3.enums.Material;
import lab1.task3.enums.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SchemaTest {
  Schema schema;

  @BeforeEach
  void setupEach() {
    schema = new Schema();
  }

  @Test
  @DisplayName("chirp() should transition state from DEFAULT to DISGUST")
  void testChirpDefault() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
  }

  @Test
  @DisplayName("chirp() should not change state when it already not DEFAULT")
  void testChirpNotDefault() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
    Assertions.assertEquals(schema.getCurrentState(), schema.chirp());
  }

  @Test
  @DisplayName("click() should transition state from DISGUST to DOUBT")
  void testClickDisgust() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
    Assertions.assertEquals(schema.click(), State.DOUBT);
  }

  @Test
  @DisplayName("click() should transition state from DISGUST to DOUBT")
  void testClickNotDisgust() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
    Assertions.assertEquals(schema.click(), State.DOUBT);
    Assertions.assertEquals(schema.getCurrentState(), schema.click());
  }

  @Test
  @DisplayName("compareMaterials() should transition state from DOUBT to FUN")
  void testCompareMaterialsDoubt() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
    Assertions.assertEquals(schema.click(), State.DOUBT);
    Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
  }

  @Test
  @DisplayName("compareMaterials() should not change state when current state is not DOUBT")
  void testCompareMaterialsNotDoubt() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
    Assertions.assertEquals(schema.click(), State.DOUBT);
    Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
    Assertions.assertEquals(schema.getCurrentState(), schema.compareMaterials(Material.METAL, Material.METAL));
  }

  @Test
  @DisplayName("checkHydrogen() should transition state from FUN to BORED")
  void testCheckHydrogenFun() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
    Assertions.assertEquals(schema.click(), State.DOUBT);
    Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
    Assertions.assertEquals(schema.checkHydrogen(), State.BORED);
  }

  @Test
  @DisplayName("checkHydrogen() should not change state when current state is not FUN")
  void testCheckHydrogenNotFun() {
    Assertions.assertEquals(schema.chirp(), State.DISGUST);
    Assertions.assertEquals(schema.click(), State.DOUBT);
    Assertions.assertEquals(schema.compareMaterials(Material.METAL, Material.METAL), State.FUN);
    Assertions.assertEquals(schema.checkHydrogen(), State.BORED);
    Assertions.assertEquals(schema.getCurrentState(), schema.checkHydrogen());
  }

  @Test
  @DisplayName("turnOff() should always reset state to DEFAULT")
  void testTurnOff() {
    System.out.println("Test Schema.turnOff()");
    Assertions.assertEquals(schema.turnOff(), State.DEFAULT);
  }


  @Test
  @DisplayName("getMaterial() should return MIXED material type")
  void testMaterial() {
    assertEquals(schema.getMaterial(), Material.MIXED);
  }

}
