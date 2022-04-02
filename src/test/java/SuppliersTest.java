import org.testng.annotations.Test;
import web_prak.java.classes.Suppliers;
import web_prak.java.services.SuppliersServices;

import java.util.List;

import static org.testng.Assert.*;

public class SuppliersTest {
    private SuppliersServices suppliersServices;
    private Suppliers expectedSupplier;

    @Test
    public void testCreateNewSuppliers() {
        suppliersServices = new SuppliersServices();
        expectedSupplier = new Suppliers("Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(expectedSupplier);

        Suppliers realSupplier = suppliersServices.getSuppliersById(expectedSupplier.getSupplier_id());
        assertEquals(expectedSupplier, realSupplier);

        suppliersServices.deleteSuppliers(expectedSupplier);
    }

    @Test
    public void testUpdateSupplier() {
        suppliersServices = new SuppliersServices();
        expectedSupplier = new Suppliers("Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(expectedSupplier);

        Suppliers realSupplier = suppliersServices.getSuppliersById(expectedSupplier.getSupplier_id());
        assertEquals(expectedSupplier, realSupplier);

        expectedSupplier.setAddress("Достоевского д17");
        suppliersServices.updateSuppliers(expectedSupplier);

        realSupplier = suppliersServices.getSuppliersById(expectedSupplier.getSupplier_id());
        assertEquals(expectedSupplier, realSupplier);

        suppliersServices.deleteSuppliers(expectedSupplier);
    }

    @Test
    public void testDeleteSuppliers() {
        suppliersServices = new SuppliersServices();
        expectedSupplier = new Suppliers("Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(expectedSupplier);

        Suppliers realSupplier = suppliersServices.getSuppliersById(expectedSupplier.getSupplier_id());
        assertEquals(expectedSupplier, realSupplier);
        suppliersServices.deleteSuppliers(expectedSupplier);

        realSupplier = suppliersServices.getSuppliersById(expectedSupplier.getSupplier_id());
        assertNull(realSupplier);
    }

    @Test
    public void testGetSuppliersById() {
        suppliersServices = new SuppliersServices();
        expectedSupplier = new Suppliers("Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(expectedSupplier);

        Suppliers realSupplier = suppliersServices.getSuppliersById(expectedSupplier.getSupplier_id());
        assertEquals(expectedSupplier.getSupplier_id(), realSupplier.getSupplier_id());

        suppliersServices.deleteSuppliers(expectedSupplier);
    }

    @Test
    public void testGetSuppliersByName() {
        suppliersServices = new SuppliersServices();
        List<Suppliers> expectedSuppliers = List.of(
                new Suppliers("Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp"),
                new Suppliers("Казакова Алиса", "+7 (926) 765-17-17", "улица Арбат, д1", "pbrew2@booking.com")
                );
        suppliersServices.createSuppliers(expectedSuppliers.get(0));
        suppliersServices.createSuppliers(expectedSuppliers.get(1));

        List<Suppliers> realSupplier = suppliersServices.getSuppliersByName("Казакова Алиса");
        assertEquals(expectedSuppliers, realSupplier);

        assertTrue(expectedSuppliers.contains(realSupplier.get(0)));
        assertTrue(expectedSuppliers.contains(realSupplier.get(1)));

        suppliersServices.deleteSuppliers(expectedSuppliers.get(0));
        suppliersServices.deleteSuppliers(expectedSuppliers.get(1));
    }

    @Test
    public void testGetSuppliersAll() {
        suppliersServices = new SuppliersServices();
        List<Suppliers> expectedSuppliers = List.of(
                new Suppliers("Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp"),
                new Suppliers("Казакова Алиса", "+7 (926) 765-17-17", "улица Арбат, д1", "pbrew2@booking.com")
        );
        suppliersServices.createSuppliers(expectedSuppliers.get(0));
        suppliersServices.createSuppliers(expectedSuppliers.get(1));

        List<Suppliers> realSupplier = suppliersServices.getSuppliersAll();
        assertEquals(expectedSuppliers, realSupplier);

        assertTrue(expectedSuppliers.contains(realSupplier.get(0)));
        assertTrue(expectedSuppliers.contains(realSupplier.get(1)));

        suppliersServices.deleteSuppliers(expectedSuppliers.get(0));
        suppliersServices.deleteSuppliers(expectedSuppliers.get(1));
    }

}
