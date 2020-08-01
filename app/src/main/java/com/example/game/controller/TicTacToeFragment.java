package com.example.game.controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.game.R;
import com.example.game.enums.GameResult;
import com.example.game.enums.Value;
import com.example.game.exceptions.IndexAlreadyTakenException;
import com.example.game.model.TicTacToe;

public class TicTacToeFragment extends Fragment {

    Button[][] mButtons = new Button[3][3];
    Button mButtonReset;
    TicTacToe mTicTacToe = new TicTacToe();

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
        mButtonReset = view.findViewById(R.id.button_reset);
    }

    private void setListeners() {
        for (int i = 0; i < mButtons.length; i++) {
            final int finalI = i;
            for (int j = 0; j < mButtons[0].length; j++) {
                final int finalJ = j;

                mButtons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            mTicTacToe.makeAction(finalI, finalJ);
                            if (mTicTacToe.getTurn() == Value.X) {
                                mButtons[finalI][finalJ].setText("O");
                            } else if (mTicTacToe.getTurn() == Value.O)
                                mButtons[finalI][finalJ].setText("X");
                            if (mTicTacToe.isGameFinished()) {
                                GameResult gr = mTicTacToe.getGameResult();
                                if (gr == GameResult.X_WINS)
                                    // Snackbar
                                    Toast.makeText(getActivity(), "X has won", Toast.LENGTH_SHORT).show();
                                else if (gr == GameResult.O_WINS)
                                    // Snackbar
                                    Toast.makeText(getActivity(), "O has won", Toast.LENGTH_SHORT).show();
                                else if (gr == GameResult.DRAW)
                                    // Snackbar
                                    Toast.makeText(getActivity(), "Draw!!!", Toast.LENGTH_SHORT).show();
                            }

                        } catch (IndexAlreadyTakenException e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (IndexOutOfBoundsException e) {
                            Toast.makeText(getActivity(), "Out of bound input", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTicTacToe = new TicTacToe();
                resetButtonsText();
            }
        });
    }

    private void resetButtonsText() {
        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons[0].length; j++) {
                mButtons[i][j].setText("");
            }
        }

    }

}