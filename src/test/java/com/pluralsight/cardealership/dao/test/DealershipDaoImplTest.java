package com.pluralsight.cardealership.dao.test;

import com.pluralsight.cardealership.dao.DealershipDao;
import com.pluralsight.cardealership.dao.DealershipDaoImpl;
import com.pluralsight.cardealership.model.Dealership;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DealershipDaoImplTest {
    static DealershipDao dao;

    @BeforeAll
    static void setup() {
        dao = new DealershipDaoImpl("DB_URL", "DB_USR", "DB_PASS");
    }

    @Test
    void findAllDealerships() {
        List<Dealership> dealershipList = dao.findAllDealerships();
        int expectedListSize = 5;

        assertEquals(expectedListSize, dealershipList.size());
    }
}