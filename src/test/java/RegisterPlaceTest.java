import org.testng.annotations.Test;
import web_prak.java.classes.Register_place;
import web_prak.java.services.Register_placeServices;

import java.util.List;

import static org.testng.Assert.*;

public class RegisterPlaceTest {
    private Register_placeServices register_placeServices;
    private Register_place expectedRegPlace;

    @Test
    public void testCreateNewRegPlace() {
        register_placeServices = new Register_placeServices();
        expectedRegPlace = new Register_place(1, "02 10 03", "нет");
        register_placeServices.createRegisterPlace(expectedRegPlace);

        Register_place realRegPlace = register_placeServices.getRegPlaceById(expectedRegPlace.getPlace_id());
        assertEquals(expectedRegPlace, realRegPlace);

        register_placeServices.deleteRegisterPlace(expectedRegPlace);
    }

    @Test
    public void testUpdateRegPlace() {
        register_placeServices = new Register_placeServices();
        expectedRegPlace = new Register_place(1, "02 10 03", "нет");
        register_placeServices.createRegisterPlace(expectedRegPlace);

        Register_place realRegPlace = register_placeServices.getRegPlaceById((int) expectedRegPlace.getPlace_id());
        assertEquals(expectedRegPlace, realRegPlace);

        expectedRegPlace.setFree("да");
        register_placeServices.updateRegisterPlace(expectedRegPlace);
        realRegPlace = register_placeServices.getRegPlaceById((int) expectedRegPlace.getPlace_id());
        assertEquals(expectedRegPlace, realRegPlace);

        register_placeServices.deleteRegisterPlace(expectedRegPlace);
    }

    @Test
    public void testDeleteRegPlace() {
        register_placeServices = new Register_placeServices();
        expectedRegPlace = new Register_place(1, "02 10 03", "нет");
        register_placeServices.createRegisterPlace(expectedRegPlace);

        Register_place realRegPlace = register_placeServices.getRegPlaceById((int) expectedRegPlace.getPlace_id());
        assertEquals(expectedRegPlace, realRegPlace);
        register_placeServices.deleteRegisterPlace(expectedRegPlace);

        realRegPlace = register_placeServices.getRegPlaceById((int) expectedRegPlace.getPlace_id());
        assertNull(realRegPlace);
    }

    @Test
    public void testGetRegPlaceById() {
        register_placeServices = new Register_placeServices();
        expectedRegPlace = new Register_place(1, "02 10 03", "нет");
        register_placeServices.createRegisterPlace(expectedRegPlace);

        Register_place realRegPlace = register_placeServices.getRegPlaceById((int) expectedRegPlace.getPlace_id());
        assertEquals(expectedRegPlace.getPlace_id(), realRegPlace.getPlace_id());

        register_placeServices.deleteRegisterPlace(expectedRegPlace);
    }

    @Test
    public void testGetRegPlaceByFree() {
        register_placeServices = new Register_placeServices();
        List<Register_place> expectedRegPlaces = List.of(
                new Register_place(1, "02 10 03", "нет"),
                new Register_place(2, "03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(expectedRegPlaces.get(0));
        register_placeServices.createRegisterPlace(expectedRegPlaces.get(1));

        List<Register_place> realRegPlace = register_placeServices.getRegPlaceByFree("нет");
        assertEquals(expectedRegPlaces, realRegPlace);

        assertTrue(expectedRegPlaces.contains(realRegPlace.get(0)));
        assertTrue(expectedRegPlaces.contains(realRegPlace.get(1)));

        register_placeServices.deleteRegisterPlace(expectedRegPlaces.get(0));
        register_placeServices.deleteRegisterPlace(expectedRegPlaces.get(1));
    }

    @Test
    public void testGetRegPlaceByStorageLoc() {
        register_placeServices = new Register_placeServices();
        List<Register_place> expectedRegPlaces = List.of(
                new Register_place(1, "02 10 03", "нет"),
                new Register_place(2, "02 10 03", "нет")
        );
        register_placeServices.createRegisterPlace(expectedRegPlaces.get(0));
        register_placeServices.createRegisterPlace(expectedRegPlaces.get(1));

        List<Register_place> realRegPlace = register_placeServices.getRegPlaceByStorageLoc("02 10 03");
        assertEquals(expectedRegPlaces, realRegPlace);

        assertTrue(expectedRegPlaces.contains(realRegPlace.get(0)));
        assertTrue(expectedRegPlaces.contains(realRegPlace.get(1)));

        register_placeServices.deleteRegisterPlace(expectedRegPlaces.get(0));
        register_placeServices.deleteRegisterPlace(expectedRegPlaces.get(1));
    }

    @Test
    public void testGetAllRegPlace() {
        register_placeServices = new Register_placeServices();
        List<Register_place> expectedRegPlaces = List.of(
                new Register_place(1, "02 10 03", "нет"),
                new Register_place(2, "03 03 04", "нет")
        );
        register_placeServices.createRegisterPlace(expectedRegPlaces.get(0));
        register_placeServices.createRegisterPlace(expectedRegPlaces.get(1));

        List<Register_place> realRegPlace = register_placeServices.getAllRegPlace();
        assertEquals(expectedRegPlaces, realRegPlace);

        assertTrue(expectedRegPlaces.contains(realRegPlace.get(0)));
        assertTrue(expectedRegPlaces.contains(realRegPlace.get(1)));

        register_placeServices.deleteRegisterPlace(expectedRegPlaces.get(0));
        register_placeServices.deleteRegisterPlace(expectedRegPlaces.get(1));
    }
}
