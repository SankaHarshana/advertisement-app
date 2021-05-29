package com.example.advertisementapp.service;

import com.example.advertisementapp.Exception.AdvertisementException;
import com.example.advertisementapp.dto.AdvertisementDto;

import java.util.List;

public interface AdvertisementService {

    public AdvertisementDto createAdd(AdvertisementDto advertisementDto) throws AdvertisementException;

    public List<AdvertisementDto> getList() throws AdvertisementException;

    public AdvertisementDto updateAdd(long id, AdvertisementDto advertisementDto) throws AdvertisementException;

    public AdvertisementDto getAdd(long id) throws AdvertisementException;

    public Boolean deleteAdd(long id) throws AdvertisementException;
}
