package org.lucky.springinterview.hibernate.collection.maps;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Entity
@Table(name="order_tbl")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection
    private Map<Integer,String> mapCollection;
}
