package com.aleyyu.library.util.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperService {

    private final ModelMapper modelMapper;

    public ModelMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ModelMapper forResponse() {
        //Her şeyi maplemek zornda değilsin. örn veritabanından 50 kolon geldi sen 10 tanesini maplicen
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
        return this.modelMapper;
    }

    public ModelMapper forRequest() {
        //Standartta isim benzerlikleri maplenecek ve requestte verdiğimiz bütün alanlar maplenmiş olacak.
        //Bir request geliyorsa gelenler olduğu gibi veritabanına aktarılmalı
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
        return this.modelMapper;
    }
}
