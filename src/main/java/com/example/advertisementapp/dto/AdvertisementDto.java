package com.example.advertisementapp.dto;

public class AdvertisementDto {

    private Long id;
    private String advertisement;

    public AdvertisementDto() {
    }

    public AdvertisementDto(Long id, String advertisement) {
        this.id = id;
        this.advertisement = advertisement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(String advertisement) {
        this.advertisement = advertisement;
    }
}
