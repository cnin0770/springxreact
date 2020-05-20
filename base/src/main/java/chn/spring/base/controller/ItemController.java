package chn.spring.base.controller;

import chn.spring.base.model.Item;
import chn.spring.base.model.ItemRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemRepo itemRepo;

    @Autowired
    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/items")
    Collection<Item> findAll() {
        return itemRepo.findAll();
    }

    @GetMapping("/item/{id}")
    ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Item> itemOption = itemRepo.findById(id);

        return itemOption.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/item")
    ResponseEntity<Item> save(@RequestBody Item item) throws URISyntaxException {
        log.info("Request to save Item: {}", item);
        Item result = itemRepo.save(item);

        return ResponseEntity.created(new URI("/api/item/" + result.getId())).body(result);
    }

    @DeleteMapping("/item/{id}")
    ResponseEntity<?> deleteById(@PathVariable Long id) {
        log.info("Request to delete Item: {}", id);
        itemRepo.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
