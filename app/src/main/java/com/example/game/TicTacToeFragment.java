package com.example.game;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TicTacToeFragment extends Fragment {

    Button[][] mButtons = new Button[3][3];
    int mTurn = 0;
    public static TicTacToeFragment newInstance() {
        TicTacToeFragment fragment = new TicTacToeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mButtons[0][0] = view.findViewById(R.id.button_00);
        mButtons[0][1] = view.findViewById(R.id.button_01);
        mButtons[0][2] = view.findViewById(R.id.button_02);
        mButtons[1][0] = view.findViewById(R.id.button_10);
        mButtons[1][1] = view.findViewById(R.id.button_11);
        mButtons[1][2] = view.findViewById(R.id.button_12);
        mButtons[2][0] = view.findViewById(R.id.button_20);
        mButtons[2][1] = view.findViewById(R.id.button_21);
        mButtons[2][2] = view.findViewById(R.id.button_22);
    }

    private void setListeners() {
        for (int i = 0; i < mButtons.length; i++) {
            final int finalI = i;
            for (int j = 0; j < mButtons[0].length; j++) {
                final int finalJ = j;
                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            }
        }
    }





















}