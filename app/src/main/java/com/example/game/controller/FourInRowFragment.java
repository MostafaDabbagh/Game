package com.example.game.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.game.R;

public class FourInRowFragment extends Fragment {

    public FourInRowFragment() {
        // Required empty public constructor
    }

    public static FourInRowFragment newInstance() {
        FourInRowFragment fragment = new FourInRowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_4_in_row, container, false);
    }
}