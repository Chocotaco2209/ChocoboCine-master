package com.example.cinemaapp.presenter;

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter {

    List<Film> films = Repository.getHardcodedList();


    public List<Film> filterByGenre(String genre){
        if (genre.equals("All"))
            return films;

        List<Film> filteredfilms = new ArrayList<>();

        for (Film f : films){

            if(f.getGenre().equals(genre)){

                filteredfilms.add(f);
            }
        }

        return filteredfilms;
    }
 }
