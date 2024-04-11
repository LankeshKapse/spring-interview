package org.lucky.springinterview.hibernate.collection.listvsbag;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="auther_tbl")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Auther {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
//    @OrderColumn  // not useful for collection only on work on list
    @JoinTable(name = "auther_book_ref_table", joinColumns = @JoinColumn(name = "auther_id"), inverseJoinColumns = @JoinColumn(name="book_id"))
    private Collection<Book> bookList;


    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "auther_id1_ref_table")
    private List<Integer> idList;

    @JoinTable(name = "auther_id2_ref_table")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> idList2;
}
