package com.example.cinemaapp.repository;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Repository {
    static List<Reservation> reservationList = new ArrayList<>();
    public static List<Film> favoriteList = new ArrayList<>();
    private static HashMap<String, HashMap<Time, List<Boolean>>> program;
    static List<Boolean> cinemaPlaces;

    public static List<Film> getHardcodedList() {
        return Arrays.asList(
                new Film("Black Adam", "Action", "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods - and imprisoned just as quickly - Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world. ", "7.1", R.drawable.blackadam),
                new Film("Smile", "Horror", "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter starts experiencing frightening occurrences that she can't explain. Rose must confront her troubling past in order to survive and escape her horrifying new reality. ", "6.9", R.drawable.smile),
                new Film("Ticket To Paradise", "Comedy", "A divorced couple teams up and travels to Bali to stop their daughter from making the same mistake they think they made 25 years ago. ", "6.3", R.drawable.tiktopas),
                new Film("Avatar", "Adventure", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home. ", "7.8", R.drawable.avatar),
                new Film("Strange World", "Animation", "The legendary Clades are a family of explorers whose differences threaten to topple their latest and most crucial mission. ", "Not yet", R.drawable.strworld),
                new Film("Black Panther: Wakanda Forever", "Action", "The nation of Wakanda is pitted against intervening world powers as they mourn the loss of their king T'Challa. ", "Not yet", R.drawable.blackp),
                new Film("Avatar: The Way of Water", "Adventure", "Jake Sully lives with his newfound family formed on the planet of Pandora. Once a familiar threat returns to finish what was previously started, Jake must work with Neytiri and the army of the Na'vi race to protect their planet. ", "Not yet", R.drawable.avatar2)
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void addReservation(Reservation reservation) {

        if (!searchReservation(reservation)) {

            reservationList.add(reservation);
            markReservedPlaces(reservation.getPlaces());
        }
    }

    private static void markReservedPlaces(List<Integer> places) {
        for (int i : places) {
            cinemaPlaces.set(i, false);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static boolean searchReservation(Reservation reservation) {

        for (Reservation r : reservationList) {

            if (r.equals(reservation))
                return true;
        }
        return false;
    }

    public static void addToFavorites(Film film) {

        if (!searchInFavorites(film)) {

            favoriteList.add(film);
        }

    }

    public static boolean searchInFavorites(Film film) {

        for (Film f : favoriteList) {

            if (f.equals(film))
                return true;
        }
        return false;
    }

    public static void deleteFromFavorites(Film film) {

        for (Film f : favoriteList) {

            if (f.equals(film)) {
                favoriteList.remove(f);
                return;
            }
        }
    }

    public static HashMap<String, HashMap<Time, List<Boolean>>> getHardcodedProgram() {
        if (program == null) {
            program = new HashMap<>();
            List<Film> films = getHardcodedList();
            List<List<Time>> posibilities = Arrays.asList(
                    Arrays.asList(new Time(9, 0, 0), new Time(10, 30, 0), new Time(11, 30, 0)),
                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0)),
                    Arrays.asList(new Time(11, 0, 0), new Time(14, 30, 0), new Time(18, 0, 0)),
                    Arrays.asList(new Time(12, 30, 0), new Time(16, 0, 0), new Time(19, 30, 0)),
                    Arrays.asList(new Time(10, 0, 0), new Time(13, 30, 0), new Time(17, 0, 0))

            );
            int i = 0;
            for (Film f : films) {
                List<Time> thisFilmTimes = posibilities.get(i % 5);
                i++;
                HashMap<Time, List<Boolean>> thisFilmProgram = new HashMap<>();
                for (Time t : thisFilmTimes) {
                    thisFilmProgram.put(t, Arrays.asList(true, true, true, true, true, true, true, true, true));
                }
                program.put(f.getTitle(), thisFilmProgram);
            }
        }
        return program;
    }

    public static List<Boolean> getCinemaPlaces(String filmTitle, String time) {
        //transform time from string to Time obj
        Time timeObj = null;
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("kk:mm");
        try {
            timeObj = new Time(formatter.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        cinemaPlaces = Objects.requireNonNull(getHardcodedProgram().get(filmTitle)).get(timeObj);
        return cinemaPlaces;
    }

    public static List<Reservation> getReservationList() {
        return reservationList;
    }
}

