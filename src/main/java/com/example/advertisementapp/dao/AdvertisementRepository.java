package com.example.advertisementapp.dao;

import com.example.advertisementapp.domain.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    @Query(" SELECT a " +
            " FROM Advertisement a ")
    List<Advertisement> getAddList();

    @Query(" SELECT a " +
            " FROM Advertisement a " +
            " WHERE a.id = :id ")
    Advertisement getAdd(@Param("id") long id);
}
