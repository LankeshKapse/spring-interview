package org.lucky.springinterview.hibernate.inhertance.singletable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "In_house_Project_tbl")
@Getter
@Setter
public class InHouseProject extends Project{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_gen")
    @SequenceGenerator(name = "project_gen", sequenceName = "project_seq")
    @Column(name = "big_project_id", nullable = false)
    private Long id;

    @Column(name="project_type")
    private String projectType;
}
