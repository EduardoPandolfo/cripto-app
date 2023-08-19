package com.eduardokp.criptoapp.services;

import com.eduardokp.criptoapp.dtos.ProductDTO;
import com.eduardokp.criptoapp.dtos.SaleDTO;
import com.eduardokp.criptoapp.dtos.SaleInfoDTO;
import com.eduardokp.criptoapp.entities.ItemSale;
import com.eduardokp.criptoapp.entities.Product;
import com.eduardokp.criptoapp.entities.Sale;
import com.eduardokp.criptoapp.entities.User;
import com.eduardokp.criptoapp.repositories.ItemSaleRepository;
import com.eduardokp.criptoapp.repositories.ProductRepository;
import com.eduardokp.criptoapp.repositories.SaleRepository;
import com.eduardokp.criptoapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ItemSaleRepository itemSaleRepository;

    public List<SaleInfoDTO> findAll() {
        return saleRepository.findAll().stream().map(this::getSaleInfo).collect(Collectors.toList());
    }

    private SaleInfoDTO getSaleInfo(Sale sale) {
        SaleInfoDTO dto = new SaleInfoDTO();
        dto.setUserName(sale.getUser().getName());
        dto.setDateTime(sale.getDateTime());
        dto.setItems(getProductInfo(sale.getItens()));

        return dto;
    }

    private List<ProductDTO> getProductInfo(List<ItemSale> itens) {
        return itens.stream().map(item -> new ProductDTO(item.getProduct().getId(), item.getProduct().getDescription(), item.getQuantity())).collect(Collectors.toList());
    }

    @Transactional
    public long save(SaleDTO saveDTO) {
        Optional<User> optUser = userRepository.findById(saveDTO.getUserId());

        if(optUser.isEmpty()) {
            throw new RuntimeException("User not found!");
        }


        Sale sale = new Sale();
        sale.setDateTime(LocalDateTime.now());
        sale.setUser(optUser.get());

        sale = saleRepository.save(sale);

        List<ItemSale> itens = getItemSale(saveDTO.getItems());
        saveItemSale(sale, itens);

        return sale.getId();
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products) {
        return products.stream().map(dto -> {
                Product product = productRepository.getReferenceById(dto.getProductId());
                ItemSale itemSale = new ItemSale();
                itemSale.setProduct(product);
                itemSale.setQuantity(dto.getQuantity());

                return itemSale;
        }).collect(Collectors.toList());
    }

    private void saveItemSale(Sale sale, List<ItemSale> itens) {
        for(ItemSale itemSale : itens) {
            itemSale.setSale(sale);
            itemSaleRepository.save(itemSale);
        }
    }

    public SaleInfoDTO getById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.map(this::getSaleInfo).orElse(null);
    }
}
