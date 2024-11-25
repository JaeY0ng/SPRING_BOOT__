package com.example.demo.domain.entity;

import com.example.demo.domain.dto.PersonDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Test")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    private Long id;
    private String col1;
    private String col2;

    @ElementCollection
    @CollectionTable(
                name="test_col3",
                joinColumns = @JoinColumn(
                        name = "id",
                        foreignKey = @ForeignKey(
                                    name = "FK_TEST_COL3",
                                    foreignKeyDefinition = "FOREIGN KEY(id) REFERENCES Test(id) ON DELETE CASCADE ON UPDATE CASCADE"
                        )

                )
    )
    List<String> col3;

}
