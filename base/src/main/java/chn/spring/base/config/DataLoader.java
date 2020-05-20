package chn.spring.base.config;

import chn.spring.base.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Stream;

@Slf4j
@Component
public class DataLoader implements ApplicationRunner {

    private final ItemRepo itemRepo;
    private final PileRepo pileRepo;
    private final CartRepo cartRepo;

    @Autowired
    public DataLoader(ItemRepo itemRepo, PileRepo pileRepo, CartRepo cartRepo) {
        this.itemRepo = itemRepo;
        this.pileRepo = pileRepo;
        this.cartRepo = cartRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Item item1 = Item.builder().name("item1").build();
        Item item2 = Item.builder().name("item2").build();
        Stream.of(item1, item2).forEach(itemRepo::save);

        Cart cart = Cart.builder().title("cart1").date(Instant.now()).build();
        cartRepo.save(cart);

        Pile pile1 = Pile.builder().cart(cart).item(item1).build();
        Pile pile2 = Pile.builder().cart(cart).item(item2).build();
        Stream.of(pile1, pile2).forEach(pileRepo::save);

        pileRepo.findAll().forEach(pile -> log.info(pile.toString()));
    }
}
