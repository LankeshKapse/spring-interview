package org.lucky.springinterview.hibernate.loading;



import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "person_tbl")
@Getter
@Setter
@ToString
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "person_id", nullable = false)
    private Long person_id;

    @Nonnull
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_address_id")
    private Address address;

}
