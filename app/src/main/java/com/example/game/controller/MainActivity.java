package com.example.game.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.game.R;

public class MainActivity extends AppCompatActivity {

    Button mButtonTicTacToe;
    Button mButton4inRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }


    private void findViews() {
        mButtonTicTacToe = findViewById(R.id.button_tic_tac_toe);
        mButton4inRow = findViewById(R.id.button_4_in_row);
    }

    private void setListeners() {
        mButtonTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null && !(fragment instanceof TicTacToeFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, TicTacToeFragment.newInstance())
                            .commit();
                }
                if (fragment == null) {
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.fragment_container, TicTacToeFragment.newInstance())
                            .commit();
                }

            }
        });

        mButton4inRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
                if (fragment != null && !(fragment instanceof FourInRowFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.fragment_container, FourInRowFragment.newInstance())
                            .commit();
                }

                if (fragment == null || !(fragment instanceof FourInRowFragment)) {
                    fragmentManager
                            .beginTransaction()
                            .add(R.id.fragment_container, FourInRowFragment.newInstance())
                            .commit();
                }
            }
        });
    }


}