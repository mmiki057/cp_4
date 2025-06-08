package pl.mikailov.exommerce.sales;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Customer name is required")
    @Column(name = "customer_name", nullable = false)
    private String customerName;
    
    @Email(message = "Valid email is required")
    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false)
    private String email;
    
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @Column(name = "phone")
    private String phone;
    
    @NotNull(message = "Reservation date and time is required")
    @Future(message = "Reservation must be in the future")
    @Column(name = "reservation_datetime", nullable = false)
    private LocalDateTime reservationDateTime;
    
    @Min(value = 1, message = "Number of guests must be at least 1")
    @Max(value = 20, message = "Maximum 20 guests allowed")
    @Column(name = "number_of_guests", nullable = false)
    private Integer numberOfGuests;
    
    @Column(name = "special_requests")
    private String specialRequests;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status = ReservationStatus.PENDING;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public Reservation() {
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public LocalDateTime getReservationDateTime() { return reservationDateTime; }
    public void setReservationDateTime(LocalDateTime reservationDateTime) { 
        this.reservationDateTime = reservationDateTime; 
    }
    
    public Integer getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(Integer numberOfGuests) { 
        this.numberOfGuests = numberOfGuests; 
    }
    
    public String getSpecialRequests() { return specialRequests; }
    public void setSpecialRequests(String specialRequests) { 
        this.specialRequests = specialRequests; 
    }
    
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

enum ReservationStatus {
    PENDING, CONFIRMED, CANCELLED
}
