package com.example.cinemaapp.presenter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.repository.Repository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class MakeReservationPresenter {
    private final MainView view;
    private final String time;
    private final Film film;
    private List<Integer> listPlaces;

    public MakeReservationPresenter(MainView view, Film film, String time) {
        this.view = view;
        this.film = film;
        this.time = time;
    }

    public void setListPlaces(List<Integer> listPlaces) {
        this.listPlaces = listPlaces;
    }

    public void updatePoster() {
        view.setPoster(film.getImagePath());
    }

    public void updateTitle() {
        view.setMovieTitle(film.getTitle());
    }

    public void updateStartTime() {
        view.setStartTime(time);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void createReservation() {
        Time timeObj = null;
        @SuppressLint("SimpleDateFormat") DateFormat formatter= new SimpleDateFormat("kk:mm");
        try {
             timeObj = new Time(formatter.parse(time).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Reservation reservation = new Reservation(film, timeObj, listPlaces);
        Repository.addReservation(reservation);
    }

    public interface MainView {
        void setPoster(int moviePosterID);
        void setMovieTitle(String title);
        void setStartTime(String time);
        void saveReservation();
    }
}
