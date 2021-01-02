package com.udacity.pricing.domain.price;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceRepositoryTests {

    protected Price price;
    protected BigDecimal expectedValue = new BigDecimal(25000.00);

    @Autowired
    PriceRepository priceRepository;

    @Before
    public void setup() {
        price = new Price("USD", expectedValue, 1L);
        priceRepository.save(price);
    }

    @Test
    public void findByIdTest() {
        Price price = priceRepository.findById(1L).get();
        assertThat(price.getPrice()).isEqualTo(expectedValue);
    }

    @Test(expected = NoSuchElementException.class)
    public void DeleteTest() {
        Long newId = 2L;
        priceRepository.save(new Price("USD", new BigDecimal(50), newId));
        Price retrievedPrice = priceRepository.findById(newId).get();
        assertThat(retrievedPrice.getVehicleId()).isEqualTo(newId);
        priceRepository.delete(retrievedPrice);
        priceRepository.findById(newId).get();
    }
}
