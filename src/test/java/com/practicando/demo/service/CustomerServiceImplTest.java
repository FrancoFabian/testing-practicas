package com.practicando.demo.service;
import com.practicando.demo.dto.CustomerDTO;
import com.practicando.demo.entity.Customer;
import com.practicando.demo.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;


    @Test
    void createCustomer_shouldReturnSavedCustomer() {
        // Arrange
        CustomerDTO dto = CustomerDTO.builder()
                .firstName("Juan")
                .lastName("Pérez")
                .email("juan@email.com")
                .build();

        Customer saved = Customer.builder()
                .id(1L)
                .firstName("Juan")
                .lastName("Pérez")
                .email("juan@email.com")
                .build();

        when(customerRepository.save(any(Customer.class))).thenReturn(saved);

        // Act
        Customer result = customerService.createCustomer(dto);

        // Assert
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getEmail()).isEqualTo("juan@email.com");
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void findCustomerById_found() {
        Customer customer = Customer.builder()
                .id(2L)
                .firstName("Ana")
                .lastName("Ramírez")
                .email("ana@email.com")
                .build();

        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer));

        Customer result = customerService.findCustomerById(2L);

        assertThat(result.getFirstName()).isEqualTo("Ana");
    }

    @Test
    void findCustomerById_notFound_shouldThrow() {
        when(customerRepository.findById(3L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.findCustomerById(3L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Customer not found");
    }

    @Test
    void listCustomers_shouldReturnPageContent() {
        Customer c1 = Customer.builder().id(1L).firstName("A").lastName("B").email("a@a.com").build();
        Customer c2 = Customer.builder().id(2L).firstName("B").lastName("C").email("b@b.com").build();
        Page<Customer> page = new PageImpl<>(List.of(c1, c2));
        PageRequest pageable = PageRequest.of(0, 10);

        when(customerRepository.findAll(pageable)).thenReturn(page);

        List<Customer> result = customerService.listCustomers(pageable);

        assertThat(result).hasSize(2);
        verify(customerRepository).findAll(pageable);
    }
}
