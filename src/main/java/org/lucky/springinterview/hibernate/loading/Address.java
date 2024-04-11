package org.lucky.springinterview.hibernate.loading;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "address_tbl")
@Getter
@Setter
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "address_id", nullable = false)
    private Long address_id;

    private String address;

}
