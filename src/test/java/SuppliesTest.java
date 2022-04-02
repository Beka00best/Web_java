import org.testng.annotations.Test;
import web_prak.java.classes.Suppliers;
import web_prak.java.classes.Supplies;
import web_prak.java.services.SuppliersServices;
import web_prak.java.services.SuppliesServices;

import java.util.List;

import static org.testng.Assert.*;

public class SuppliesTest {
    private final SuppliersServices suppliersServices = new SuppliersServices();
    private final SuppliesServices suppliesServices = new SuppliesServices();

    @Test
    public void testCreateNewSupplies() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies expectedSupplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(expectedSupplies);

        Supplies realSupply = suppliesServices.getSuppliesById(expectedSupplies.getSupply_id());
        assertEquals(expectedSupplies, realSupply);

        suppliesServices.deleteSupplies(expectedSupplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testUpdateSupplies() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies expectedSupplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(expectedSupplies);

        Supplies realSupply = suppliesServices.getSuppliesById(expectedSupplies.getSupply_id());
        assertEquals(expectedSupplies, realSupply);

        expectedSupplies.setData_supply(java.sql.Date.valueOf("2022-08-10"));
        suppliesServices.updateSupplies(expectedSupplies);
        realSupply = suppliesServices.getSuppliesById(expectedSupplies.getSupply_id());
        assertEquals(expectedSupplies, realSupply);

        suppliesServices.deleteSupplies(expectedSupplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testDeleteSupplies() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies expectedSupplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(expectedSupplies);

        Supplies realSupply = suppliesServices.getSuppliesById(expectedSupplies.getSupply_id());
        assertEquals(expectedSupplies, realSupply);

        suppliesServices.deleteSupplies(expectedSupplies);
        suppliersServices.deleteSuppliers(suppliers);

        realSupply = suppliesServices.getSuppliesById(expectedSupplies.getSupply_id());
        assertNull(realSupply);
    }

    @Test
    public void testGetSuppliesById() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);
        Supplies expectedSupplies = new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120);
        suppliesServices.createSupplies(expectedSupplies);

        Supplies realSupply = suppliesServices.getSuppliesById(expectedSupplies.getSupply_id());
        assertEquals(expectedSupplies.getSupply_id(), realSupply.getSupply_id());

        suppliesServices.deleteSupplies(expectedSupplies);
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesBySupplier() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);

        List<Supplies> expectedSupplies = List.of(
                new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120),
                new Supplies(2, suppliers, java.sql.Date.valueOf("2023-05-05"), "в процессе", 100)
        );
        suppliesServices.createSupplies(expectedSupplies.get(0));
        suppliesServices.createSupplies(expectedSupplies.get(1));

        List<Supplies> realSupply = suppliesServices.getSuppliesBySupplier(suppliers.getSupplier_id());
        assertEquals(expectedSupplies, realSupply);

        assertTrue(expectedSupplies.contains(realSupply.get(0)));
        assertTrue(expectedSupplies.contains(realSupply.get(1)));

        suppliesServices.deleteSupplies(expectedSupplies.get(0));
        suppliesServices.deleteSupplies(expectedSupplies.get(1));
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesByDate() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);

        List<Supplies> expectedSupplies = List.of(
                new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120),
                new Supplies(2, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 100)
        );
        suppliesServices.createSupplies(expectedSupplies.get(0));
        suppliesServices.createSupplies(expectedSupplies.get(1));

        List<Supplies> realSupply = suppliesServices.getSuppliesDate(java.sql.Date.valueOf("2022-01-05"));
        assertEquals(expectedSupplies, realSupply);

        assertTrue(expectedSupplies.contains(realSupply.get(0)));
        assertTrue(expectedSupplies.contains(realSupply.get(1)));

        suppliesServices.deleteSupplies(expectedSupplies.get(0));
        suppliesServices.deleteSupplies(expectedSupplies.get(1));
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesByDateAfter() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);

        List<Supplies> expectedSupplies = List.of(
                new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120),
                new Supplies(2, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 100)
        );
        suppliesServices.createSupplies(expectedSupplies.get(0));
        suppliesServices.createSupplies(expectedSupplies.get(1));

        List<Supplies> realSupply = suppliesServices.getSuppliesDateAfter(java.sql.Date.valueOf("2023-01-05"));
        assertEquals(expectedSupplies, realSupply);

        assertTrue(expectedSupplies.contains(realSupply.get(0)));
        assertTrue(expectedSupplies.contains(realSupply.get(1)));

        suppliesServices.deleteSupplies(expectedSupplies.get(0));
        suppliesServices.deleteSupplies(expectedSupplies.get(1));
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesByDateBefore() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);

        List<Supplies> expectedSupplies = List.of(
                new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120),
                new Supplies(2, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 100)
        );
        suppliesServices.createSupplies(expectedSupplies.get(0));
        suppliesServices.createSupplies(expectedSupplies.get(1));

        List<Supplies> realSupply = suppliesServices.getSuppliesDateBefore(java.sql.Date.valueOf("2021-01-05"));
        assertEquals(expectedSupplies, realSupply);

        assertTrue(expectedSupplies.contains(realSupply.get(0)));
        assertTrue(expectedSupplies.contains(realSupply.get(1)));

        suppliesServices.deleteSupplies(expectedSupplies.get(0));
        suppliesServices.deleteSupplies(expectedSupplies.get(1));
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesByStatus() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);

        List<Supplies> expectedSupplies = List.of(
                new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120),
                new Supplies(2, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 100)
        );
        suppliesServices.createSupplies(expectedSupplies.get(0));
        suppliesServices.createSupplies(expectedSupplies.get(1));

        List<Supplies> realSupply = suppliesServices.getSuppliesByStatus("в процессе");
        assertEquals(expectedSupplies, realSupply);

        assertTrue(expectedSupplies.contains(realSupply.get(0)));
        assertTrue(expectedSupplies.contains(realSupply.get(1)));

        suppliesServices.deleteSupplies(expectedSupplies.get(0));
        suppliesServices.deleteSupplies(expectedSupplies.get(1));
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesByStorePeriod() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);

        List<Supplies> expectedSupplies = List.of(
                new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120),
                new Supplies(2, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120)
        );
        suppliesServices.createSupplies(expectedSupplies.get(0));
        suppliesServices.createSupplies(expectedSupplies.get(1));

        List<Supplies> realSupply = suppliesServices.getSuppliesByStorePeriod(120);
        assertEquals(expectedSupplies, realSupply);

        assertTrue(expectedSupplies.contains(realSupply.get(0)));
        assertTrue(expectedSupplies.contains(realSupply.get(1)));

        suppliesServices.deleteSupplies(expectedSupplies.get(0));
        suppliesServices.deleteSupplies(expectedSupplies.get(1));
        suppliersServices.deleteSuppliers(suppliers);
    }

    @Test
    public void testGetSuppliesAll() {
        Suppliers suppliers = new Suppliers(1,"Казакова Алиса", "+7 (926) 059-17-93", "улица Пятницкая, д27", "wokker0@infoseek.co.jp");
        suppliersServices.createSuppliers(suppliers);

        List<Supplies> expectedSupplies = List.of(
                new Supplies(1, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 120),
                new Supplies(2, suppliers, java.sql.Date.valueOf("2022-01-05"), "в процессе", 100)
        );
        suppliesServices.createSupplies(expectedSupplies.get(0));
        suppliesServices.createSupplies(expectedSupplies.get(1));

        List<Supplies> realSupply = suppliesServices.getSuppliesAll();
        assertEquals(expectedSupplies, realSupply);

        assertTrue(expectedSupplies.contains(realSupply.get(0)));
        assertTrue(expectedSupplies.contains(realSupply.get(1)));

        suppliesServices.deleteSupplies(expectedSupplies.get(0));
        suppliesServices.deleteSupplies(expectedSupplies.get(1));
        suppliersServices.deleteSuppliers(suppliers);
    }

}
