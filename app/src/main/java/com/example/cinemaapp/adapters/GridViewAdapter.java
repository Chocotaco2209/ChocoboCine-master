package com.example.cinemaapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.example.cinemaapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private final List<Integer> listOfPlaces;
    private final List<Integer> selectedPositions;
    private final Context mContext;
    
    private List<Boolean> cinemaPlacesState; 

    public GridViewAdapter(Context mContext) {
        this.listOfPlaces = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        this.mContext = mContext;
        this.selectedPositions = new ArrayList<>();
    }

    public void setCinemaPlacesState(List<Boolean> cinemaPlacesState) {
        this.cinemaPlacesState = cinemaPlacesState;
    }

    @Override
    public int getCount() {
        return listOfPlaces.size();
    }

    @Override
    public Object getItem(int position) {
        return listOfPlaces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            button = new Button(mContext);
            button.setLayoutParams(new GridView.LayoutParams(100, 100));
            button.setPadding(8,8,8,8);
            button.setText(Integer.toString(listOfPlaces.get(position)));
            if (!cinemaPlacesState.get(position)) {
                button.setBackgroundColor(Color.LTGRAY);
                button.setEnabled(false);
            } else if (cinemaPlacesState.get(position)){
                button.setBackgroundColor(Color.rgb(53, 172, 72));
            }
            button.setTextColor(Color.BLACK);
            button.setOnClickListener(view -> {
                int pos = Integer.parseInt(((Button) view).getText().toString()) - 1;

                int selectedIndex = selectedPositions.indexOf(pos);
                if (cinemaPlacesState.get(pos) && selectedIndex > -1) {
                    selectedPositions.remove(selectedIndex);
                    ((Button) view).setBackgroundColor(Color.rgb(53, 172, 72));
                } else if (cinemaPlacesState.get(pos) && selectedIndex == -1){
                    selectedPositions.add(pos);
                    ((Button) view).setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.titleColor));
                }
            });
        } else {
          button = (Button)convertView;
        }

        return button;
    }

    public List<Integer> getSelectedPositions() {
        return selectedPositions;
    }
}
