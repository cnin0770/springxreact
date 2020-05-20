package chn.spring.base.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PileRepo extends JpaRepository<Pile, Long> {

    List<Pile> findByCart(Cart cart);
    Pile findByItemAndCart(Item item, Cart cart);
}
