package lab1.task3.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lab1.task3.abstractions.INamed;
import lab1.task3.enums.IdeaContent;
import lab1.task3.enums.Material;
import lab1.task3.enums.State;
import lab1.task3.implementations.idea.Idea;
import lab1.task3.implementations.idea.TargetedIdea;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

  Robot notZeroSchemas;
  Robot zeroSchemas;

  @BeforeEach
  void setupEach() {
    notZeroSchemas = new Robot("Rob", 1);
    zeroSchemas = new Robot("Rob", 0);
  }
  @Test
  @DisplayName("Robot enters COLD_CONTEMPT state when watching target named 'She'")
  void testWatchShe() {
    assertEquals(
      notZeroSchemas.watch((INamed) () -> "She"),
      State.COLD_CONTEMPT);
    assertEquals(
      zeroSchemas.watch((INamed) () -> "She"),
      State.COLD_CONTEMPT);
  }

  @Test
  @DisplayName("Robot state does not change when watching target not named 'She'")
  void testWatchNotShe() {
    assertEquals(
      notZeroSchemas.getCurrentState(),
      notZeroSchemas.watch((INamed) () -> "Not She"));
    assertEquals(
      zeroSchemas.getCurrentState(),
      zeroSchemas.watch((INamed) () -> "Not She"));
  }


  @Test
  @DisplayName("Robot does nothing when it has zero schemas")
  void testUseSchemasZeroSchemas() {
    assertEquals(
      zeroSchemas.getCurrentState(),
      zeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))));
  }

  @Test
  @DisplayName("Robot does not activate schemas if not in COLD_CONTEMPT state")
  void testUseSchemasNotShe() {
    assertEquals(
      notZeroSchemas.getCurrentState(),
      notZeroSchemas.watch((INamed) () -> "Not She"));
    assertEquals(
      notZeroSchemas.getCurrentState(),
      notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))));
  }

  @Test
  @DisplayName("Robot ignores ideas that are not PHYSICAL_ABUSE")
  void  testUseSchemasNotPhysicalAbuse() {
    assertEquals(notZeroSchemas.watch((INamed) () -> "She"), State.COLD_CONTEMPT);
    assertEquals(
      notZeroSchemas.getCurrentState(),
      notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.HAPPINESS, new Door(Material.MIXED))));
  }

  @Test
  @DisplayName("Robot processes non-targeted PHYSICAL_ABUSE idea and transitions to schema's state")
  void testUseSchemasPhysicalAbuseNonTargeted() {
    assertEquals(notZeroSchemas.watch((INamed) () -> "She"), State.COLD_CONTEMPT);
    assertEquals(notZeroSchemas.useSchemas(new Idea(IdeaContent.PHYSICAL_ABUSE)), State.DOUBT);
  }

  @Test
  @DisplayName("Robot performs full cognitive process and ends in DESPAIR for targeted PHYSICAL_ABUSE")
  void testUseSchemasPhysicalAbuseTargeted() {
    assertEquals(notZeroSchemas.watch((INamed) () -> "She"), State.COLD_CONTEMPT);
    assertEquals(
      notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))),
      State.DESPAIR);
  }

  @Test
  @DisplayName("Robot performs full cognitive process and ends in DESPAIR for targeted PHYSICAL_ABUSE")
  void testTurnDespaired() {
    assertEquals(notZeroSchemas.watch((INamed) () -> "She"), State.COLD_CONTEMPT);
    assertEquals(
      notZeroSchemas.useSchemas(new TargetedIdea(IdeaContent.PHYSICAL_ABUSE, new Door(Material.MIXED))),
      State.DESPAIR);
    assertTrue(notZeroSchemas.turn());
  }

  @Test
  @DisplayName("Robot performs full cognitive process and ends in DESPAIR for targeted PHYSICAL_ABUSE")
  void testTurnUndespaired() {

    assertEquals(notZeroSchemas.watch((INamed) () -> "She"), State.COLD_CONTEMPT);
    assertEquals(notZeroSchemas.useSchemas(new Idea(IdeaContent.PHYSICAL_ABUSE)), State.DOUBT);
    assertFalse(notZeroSchemas.turn());
  }

  @Test
  void testMaterial() {
    assertEquals(notZeroSchemas.getMaterial(), Material.MIXED);
  }


}
