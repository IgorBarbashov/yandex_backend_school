package com.example.spring_boot_rest_api.adresses;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(path = "list")
    public List<Address> list() {
        return addressService.list();
    }

    @DeleteMapping(path = "item/{addressId}")
    public void delete(@PathVariable Long addressId) {
        addressService.delete(addressId);
    }
}
