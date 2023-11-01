package util;

import dtos.*;
import model.Inventory;
import model.Rental;
import model.Staff;
import model.Store;

import java.util.Arrays;

public class DTOEntityUtil {
    public static InventoryDTO createInventoryDTO(Inventory inventory) {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setId(inventory.getInventoryId());
        inventoryDTO.setFilm(Hrefs.FILM.getHref() + "films/" + inventory.getFilmId());
        inventoryDTO.setStore(Hrefs.STORE.getHref() + "stores/" + inventory.getFilmId());
        return inventoryDTO;
    }

    public static RentalDtoGet createRentalDtoGET(Rental rental) {
        RentalDtoGet rentalDTO = new RentalDtoGet();
        rentalDTO.setRentalId(rental.getRentalId());
        rentalDTO.setRentalDate(rental.getRentalDate());
        rentalDTO.setReturnDate(rental.getReturnDate());
        rentalDTO.setCustomer(Hrefs.CUSTOMER.getHref()+"customers/" + rental.getCustomerId());
        rentalDTO.setStore(Hrefs.STORE.getHref()+"stores/"+rental.getInventoryByInventoryId().getStoreByStoreId().getStoreId());
        rentalDTO.setFilm(Hrefs.FILM.getHref()+"films/"+rental.getInventoryByInventoryId().getFilmId());
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
