package util;

import dtos.*;
import model.Inventory;
import model.Rental;
import model.Staff;
import model.Store;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DTOEntityUtil {
    public static InventoryDTO createInventoryDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setId(inventory.getInventoryId());
        Map<String, String> filmhref = new HashMap<>();
        filmhref.put("href",Hrefs.FILM.getHref()!=null?Hrefs.FILM.getHref() + "films/" + inventory.getFilmId():"");
        Map<String, String> storeHref = new HashMap<>();
        storeHref.put("href",Hrefs.STORE.getHref()!=null?Hrefs.STORE.getHref() + "stores/" + inventory.getFilmId():"");
        inventoryDTO.setFilm(filmhref);
        inventoryDTO.setStore(storeHref);
        return inventoryDTO;
    }

    public static RentalDtoGet createRentalDtoGET(Rental rental) {
        RentalDtoGet rentalDTO = new RentalDtoGet();
        rentalDTO.setRentalId(rental.getRentalId());
        rentalDTO.setRentalDate(rental.getRentalDate());
        rentalDTO.setReturnDate(rental.getReturnDate());
        Map<String, String> customerHref = new HashMap<>();
        customerHref.put("href", Hrefs.CUSTOMER.getHref()!=null?Hrefs.CUSTOMER.getHref() + "customers/" + rental.getCustomerId():"");
        rentalDTO.setCustomer(customerHref);
        Inventory inventory = rental.getInventoryByInventoryId();
        Map<String, String> storeHref = new HashMap<>();
        storeHref.put("href", Hrefs.STORE.getHref()!=null?Hrefs.STORE.getHref() + "stores/" + inventory.getStoreByStoreId().getStoreId():"");
        rentalDTO.setStore(storeHref);
        Map<String, String> filmHref = new HashMap<>();
        filmHref.put("href", Hrefs.FILM.getHref()!=null?Hrefs.FILM.getHref() + "films/" + inventory.getFilmId():"");
        rentalDTO.setFilm(filmHref);

        return rentalDTO;
    }


    public static UserDTO createUserDTO(Staff staff) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(staff.getStaffId());
        userDTO.setActive(staff.isActive());
        userDTO.setEmail(staff.getEmail());
        userDTO.setFirstName(staff.getFirstName());
        userDTO.setLastName(staff.getLastName());
        userDTO.setPassword(staff.getPassword());
        userDTO.setUsername(staff.getUsername());
        userDTO.setPicture(Arrays.toString(staff.getPicture()));
        return userDTO;
    }

    public static StoreDTO createStoreDTO(Store store) {
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setId(store.getStoreId());
        return storeDTO;
    }
}
