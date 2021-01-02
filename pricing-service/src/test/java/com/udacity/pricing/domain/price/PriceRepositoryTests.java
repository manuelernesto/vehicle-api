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

    protected BigDecimal expected = new BigDecimal("25000.00");

    @Autowired
    PriceRepository priceRepository;

    @Before
    public void setup() {
        Price price = new Price("USD", expected, 1L);
        priceRepository.save(price);
    }

    @Test
    public void findByIdTest() {
        Price price = priceRepository.findById(1L).get();
        assertThat(price.getPrice()).isEqualTo(expected);
    }

    @Test(expected = NoSuchElementException.class)
    public void DeleteTest() {
        Long id = 2L;
        priceRepository.save(new Price("USD", new BigDecimal("35000.00"), id));
        Price retrievedPrice = priceRepository.findById(id).get();
        assertThat(retrievedPrice.getVehicleId()).isEqualTo(id);
        priceRepository.delete(retrievedPrice);
        priceRepository.findById(id).get();
    }
}
