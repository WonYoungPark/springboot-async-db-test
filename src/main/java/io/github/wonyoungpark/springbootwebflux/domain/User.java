package io.github.wonyoungpark.springbootwebflux.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "TB_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
}
