package org.lucky.springinterview.hibernate.inhertance.singletable;

import jakarta.persistence.*;

@Entity
@Table(name = "Project_tbl")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_gen")
    @SequenceGenerator(name = "project_gen", sequenceName = "project_seq")
    @Column(name = "project_id", nullable = false)
    private Long id;

    @Column(name="project_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
