package se.iths.springloppis.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.springloppis.entity.ItemEntity;
import se.iths.springloppis.repository.ItemRepository;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;
@Service // De här annotationerna får man inte missa. Allt som ska vara klassnivå måste annoteras.
public class ItemService {

//    @Autowired    // De här motsvarar Inject annotationen i Enterprice. Dock vill man helst skapa en konstruktor.
//    BeanScopesDemo beanScopesDemo;

   /* @Autowired
    BeanScopesDemo beanScopesDemo;*/

    private final ItemRepository itemRepository;

    //@Autowired // Man behöver dock ej använda den så mycket längre. Men förr var man tvungen att använda den.
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Nu börjar vi skapa crud metoder

    public ItemEntity createItem(ItemEntity item) {
        return itemRepository.save(item);
        // Save är en hibernategrej som motsvarar persist. Istället för persist, använder vi oss för save.
    }

    public Optional<ItemEntity> findItemById(Long id) {
        return Optional.ofNullable(itemRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        //Ovan är ett smidigt sätt att kasta nullcheck. Alltså om det är tomt.

        // Interfaces som implementeras åt oss i repositorys, har lite fördefinierade typer. I och med att detta är find( skulle de kunna vara null).
        // Då vill implementation ge oss Optional. För att undvika behöva göra "null" checks.
    }

    public void deleteItem(Long id) {
        ItemEntity foundItem = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        itemRepository.deleteById(foundItem.getId());
    }

    public Iterable<ItemEntity> findAllItems() {
        return itemRepository.findAll();
    }
}