package com.example.advertisementapp.service;

import com.example.advertisementapp.Exception.AdvertisementException;
import com.example.advertisementapp.dao.AdvertisementRepository;
import com.example.advertisementapp.domain.Advertisement;
import com.example.advertisementapp.dto.AdvertisementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public AdvertisementDto createAdd(AdvertisementDto advertisementDto) throws AdvertisementException {
        if(advertisementDto != null) {
            Advertisement advertisement = new Advertisement();
            advertisement.setAdvertisement(advertisementDto.getAdvertisement());
            Advertisement created = advertisementRepository.save(advertisement);
            if(created != null) {
                AdvertisementDto returnDto = new AdvertisementDto();
                returnDto.setId(advertisement.getId());
                returnDto.setAdvertisement(advertisement.getAdvertisement());
                return returnDto;
            }else throw new AdvertisementException("Something went wrong");
        }else throw new AdvertisementException("Empty object");
    }


    @Override
    public List<AdvertisementDto> getList() throws AdvertisementException {
        List<Advertisement> list = advertisementRepository.getAddList();
        if(list.size() > 0 ) {
            List<AdvertisementDto> returnList = new ArrayList<>();
            for (Advertisement a : list) {
                AdvertisementDto returnDto = new AdvertisementDto();
                returnDto.setId(a.getId());
                returnDto.setAdvertisement(a.getAdvertisement());
                returnList.add(returnDto);
            }
            return returnList;
        }else throw new AdvertisementException("not found");
    }

    @Override
    public AdvertisementDto updateAdd(long id, AdvertisementDto advertisementDto) throws AdvertisementException {
        if(advertisementDto != null) {
            Advertisement advertisement = advertisementRepository.getAdd(id);
            if(advertisement != null) {
                advertisement.setAdvertisement(advertisementDto.getAdvertisement());
                Advertisement updated = advertisementRepository.save(advertisement);
                if(updated != null) {
                    AdvertisementDto returnDto = new AdvertisementDto();
                    returnDto.setId(updated.getId());
                    returnDto.setAdvertisement(updated.getAdvertisement());
                    return returnDto;
                }else throw new AdvertisementException("Something went wrong");
            }else throw new AdvertisementException("not found");
        }else throw new AdvertisementException("Empty object");
    }

    @Override
    public AdvertisementDto getAdd(long id) throws AdvertisementException {
        Advertisement advertisement = advertisementRepository.getAdd(id);
        if(advertisement != null) {
            AdvertisementDto returnDto = new AdvertisementDto();
            returnDto.setId(advertisement.getId());
            returnDto.setAdvertisement(advertisement.getAdvertisement());
            return returnDto;
        }else throw new AdvertisementException("not found");
    }

    @Override
    public Boolean deleteAdd(long id) throws AdvertisementException {
        Advertisement advertisement = advertisementRepository.getAdd(id);
        if(advertisement != null) {
            advertisementRepository.delete(advertisement);
            return true;
        }else throw new AdvertisementException("not found");
    }
}
