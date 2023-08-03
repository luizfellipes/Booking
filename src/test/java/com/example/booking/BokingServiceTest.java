package com.example.booking;

import com.example.booking.Models.BookingModel;
import com.example.booking.repository.BookingRepository;
import com.example.booking.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class BokingServiceTest {

    @TestConfiguration
    static class BookingServiceTestConfiguration{
        @Bean
        public BookingService bookingService(){
            return new BookingService();
        }
    }

    @Autowired
    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void bookingTestServiceDaysCalculator(){
        String name = "luiz";
        int days = bookingService.daysCalculatorWithDatabase(name);
        Assertions.assertEquals(days, 10);
    }


    @Before
    public void setup(){
        LocalDate checkIn = LocalDate.parse("2023-08-03");
        LocalDate checkOut = LocalDate.parse("2023-08-13");
        BookingModel bookingModel = new BookingModel("1", "luiz", checkIn, checkOut, 2);
        Mockito.when(bookingRepository.findByReserveName(bookingModel.getReserveName())).thenReturn(Optional.of(bookingModel));
    }


}
