package com.example.game.controller;

import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.williammora.snackbar.Snackbar;

public class TicTacToeFragment extends Fragment {

    public static final String BUNDLE_TIC_TAC_TOE = "TicTacToe";

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
        if (savedInstanceState != null) {
            mTicTacToe = (TicTacToe) savedInstanceState.getSerializable(BUNDLE_TIC_TAC_TOE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_TIC_TAC_TOE, mTicTacToe);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        setListeners();
        initButtonsText();
        setButtonsEnable(!mTicTacToe.isGameFinished());
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
                                setButtonsEnable(false);
                                GameResult gr = mTicTacToe.getGameResult();
                                if (gr == GameResult.X_WINS)
                                    Snackbar.with(getActivity()).text("X has won!").show(getActivity());
                                else if (gr == GameResult.O_WINS)
                                    Snackbar.with(getActivity()).text("O has won!").show(getActivity());
                                else if (gr == GameResult.DRAW)
                                    Snackbar.with(getActivity()).text("Draw!!").show(getActivity());
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
                initButtonsText();
                setButtonsEnable(true);
            }
        });
    }

    private void initButtonsText() {
        Value[][] ticTacToeTable = mTicTacToe.getTable();
        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons[0].length; j++) {
                if (ticTacToeTable[i][j] == Value.E)
                    mButtons[i][j].setText("");
                else if (ticTacToeTable[i][j] == Value.X)
                    mButtons[i][j].setText("X");
                else if (ticTacToeTable[i][j] == Value.O)
                    mButtons[i][j].setText("O");
            }
        }

    }

    private void setButtonsEnable(boolean b){
        for (int i = 0; i < mButtons.length; i++) {
            for (int j = 0; j < mButtons[0].length; j++) {
                mButtons[i][j].setEnabled(b);
            }
        }




    }

}