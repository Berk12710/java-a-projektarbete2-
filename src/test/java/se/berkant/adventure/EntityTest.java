package se.berkant.adventure;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityTest {

    @Test
    public void testPunchMethod() {
        Resident resident = new Resident();
        Burglar burglar = new Burglar();

        int initialHealth = burglar.getHealth();
        resident.punch(burglar);

        assertEquals(initialHealth - resident.getDamage(), burglar.getHealth(),
                "Burglar's health should decrease by Resident's damage after being punched.");
    }

    @Test
    public void testTakeHitMethod() {
        Resident resident = new Resident();
        int initialHealth = resident.getHealth();

        int damage = 4;
        resident.takeHit(damage);

        assertEquals(initialHealth - damage, resident.getHealth(),
                "Resident's health should decrease by the amount of damage taken.");
    }

    @Test
    public void testIsConsciousTrue() {
        Resident resident = new Resident();
        resident.takeHit(5); // Inflict some damage, but not enough to knock out

        assertTrue(resident.isConscious(), "Resident should be conscious if health is above 0.");
    }

    @Test
    public void testIsConsciousFalse() {
        Resident resident = new Resident();
        resident.takeHit(resident.getHealth()); // Inflict damage equal to resident's health

        assertFalse(resident.isConscious(), "Resident should not be conscious if health is 0 or below.");
    }

    @Test
    public void testFightScenario() {
        Resident resident = new Resident();
        Burglar burglar = new Burglar();

        // Resident attacks Burglar until one is unconscious
        while (resident.isConscious() && burglar.isConscious()) {
            resident.punch(burglar);
            if (burglar.isConscious()) {
                burglar.punch(resident);
            }
        }

        if (resident.isConscious()) {
            assertFalse(burglar.isConscious(), "Burglar should be unconscious if Resident wins.");
        } else {
            assertFalse(resident.isConscious(), "Resident should be unconscious if Burglar wins.");
        }
    }
}
