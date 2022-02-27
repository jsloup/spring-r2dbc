package jsloup.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table("person")
public class Person {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("age")
    private int age;
}
