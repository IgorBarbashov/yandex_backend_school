package com.example.spring_boot_rest_api.adresses;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> list() {
        return addressRepository.findAll();
    }

    public void delete(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
