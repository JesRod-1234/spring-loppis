package se.iths.springloppis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.service.ItemService;

import java.util.Optional;

@RestController // Då kommer den att returnera Json åt oss. Tillgång till de annotationerna. Post, Put och get osv.
@RequestMapping("items") // Path
public class ItemController {

    private final ItemService itemService; // Här pratar vi med servicen

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping                           // @RequestBody visar att det kommer komma in Json ody i vårat Http anrop.
    public ResponseEntity<ItemEntity> createItem(@RequestBody ItemEntity item) { // I java enterprice har vi något som heter Responce, men i här kallar vi de ResponseEntity
        ItemEntity createdItem = itemService.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
        // Här returnerar vi vårat skapade item och våran http status. Istället för 200 ok som innan.
        // Det här är 201.
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<ItemEntity>> findItemById(@PathVariable Long id) {
        Optional<ItemEntity> foundItem = itemService.findItemById(id);
        return new ResponseEntity<>(foundItem, HttpStatus.OK);
        // Returnerar vårat foundItem och våran http status. 201
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204. Allt gick bra men du får inte tillbaka något.
    }

    @GetMapping
    public ResponseEntity<Iterable<ItemEntity>> findAllItems() {
        Iterable<ItemEntity> allItems = itemService.findAllItems();
        return new ResponseEntity<>(allItems, HttpStatus.OK);
    }


}
