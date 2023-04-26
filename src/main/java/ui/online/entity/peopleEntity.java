package ui.online.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="online")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class peopleEntity {
    @Id
    private Integer id;
    @Column(name = "name", nullable = true)
    private String name;
    @Column(name = "security", nullable = true)
    private String security;
    @Column(name = "hours", nullable = true)
    private Double hours;
}

