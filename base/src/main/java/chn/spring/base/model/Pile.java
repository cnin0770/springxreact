package chn.spring.base.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pile {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Cart cart;
}
