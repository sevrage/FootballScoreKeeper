package com.example.android.footballscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEAM_A = "A";
    private static final String TEAM_B = "B";
    private static final String GOAL = "GOAL";
    private static final String CORNER = "CORNER";
    private static final String FOUL = "FOUL";
    private static final String PENALTY = "PENALTY";
    private static final String OFFSIDE = "OFFSIDE";
    //used for toast text color
    private static final String TEAM_A_COLOR = "#1A237E";
    private static final String TEAM_B_COLOR = "#B71C1C";

    private int goalsTeamA = 0;
    private int cornersTeamA = 0;
    private int foulsTeamA = 0;
    private int penaltiesTeamA = 0;
    private int offsidesTeamA = 0;

    private int goalsTeamB = 0;
    private int cornersTeamB = 0;
    private int foulsTeamB = 0;
    private int penaltiesTeamB = 0;
    private int offsidesTeamB = 0;

    private TextView goalsViewA;
    private TextView cornersViewA;
    private TextView foulsViewA;
    private TextView penaltiesViewA;
    private TextView offsidesViewA;
    private TextView matchResultViewA;

    private TextView goalsViewB;
    private TextView cornersViewB;
    private TextView foulsViewB;
    private TextView penaltiesViewB;
    private TextView offsidesViewB;
    private TextView matchResultViewB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //disable keyboard popup on rotate
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        goalsViewA = findViewById(R.id.team_a_goals);
        cornersViewA = findViewById(R.id.team_a_corners);
        foulsViewA = findViewById(R.id.team_a_fouls);
        penaltiesViewA = findViewById(R.id.team_a_penalties);
        offsidesViewA = findViewById(R.id.team_a_offsides);
        matchResultViewA = findViewById(R.id.team_a_result);

        goalsViewB = findViewById(R.id.team_b_goals);
        cornersViewB = findViewById(R.id.team_b_corners);
        foulsViewB = findViewById(R.id.team_b_fouls);
        penaltiesViewB = findViewById(R.id.team_b_penalties);
        offsidesViewB = findViewById(R.id.team_b_offsides);
        matchResultViewB = findViewById(R.id.team_b_result);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt(GOAL.concat(TEAM_A), goalsTeamA);
        savedInstanceState.putInt(CORNER.concat(TEAM_A), cornersTeamA);
        savedInstanceState.putInt(FOUL.concat(TEAM_A), foulsTeamA);
        savedInstanceState.putInt(PENALTY.concat(TEAM_A), penaltiesTeamA);
        savedInstanceState.putInt(OFFSIDE.concat(TEAM_A), offsidesTeamA);

        savedInstanceState.putInt(GOAL.concat(TEAM_B), goalsTeamB);
        savedInstanceState.putInt(CORNER.concat(TEAM_B), cornersTeamB);
        savedInstanceState.putInt(FOUL.concat(TEAM_B), foulsTeamB);
        savedInstanceState.putInt(PENALTY.concat(TEAM_B), penaltiesTeamB);
        savedInstanceState.putInt(OFFSIDE.concat(TEAM_B), offsidesTeamB);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        goalsTeamA = savedInstanceState.getInt(GOAL.concat(TEAM_A));
        cornersTeamA = savedInstanceState.getInt(CORNER.concat(TEAM_A));
        foulsTeamA = savedInstanceState.getInt(FOUL.concat(TEAM_A));
        penaltiesTeamA = savedInstanceState.getInt(PENALTY.concat(TEAM_A));
        offsidesTeamA = savedInstanceState.getInt(OFFSIDE.concat(TEAM_A));

        goalsTeamB = savedInstanceState.getInt(GOAL.concat(TEAM_B));
        cornersTeamB = savedInstanceState.getInt(CORNER.concat(TEAM_B));
        foulsTeamB = savedInstanceState.getInt(FOUL.concat(TEAM_B));
        penaltiesTeamB = savedInstanceState.getInt(PENALTY.concat(TEAM_B));
        offsidesTeamB = savedInstanceState.getInt(OFFSIDE.concat(TEAM_B));

        displayStats();
    }

    //update all scores in respective views
    private void displayStats() {
        displayScoreForTeamX(TEAM_A, GOAL, false);
        displayScoreForTeamX(TEAM_A, CORNER, false);
        displayScoreForTeamX(TEAM_A, FOUL, false);
        displayScoreForTeamX(TEAM_A, PENALTY, false);
        displayScoreForTeamX(TEAM_A, OFFSIDE, false);

        displayScoreForTeamX(TEAM_B, GOAL,false);
        displayScoreForTeamX(TEAM_B, CORNER,false);
        displayScoreForTeamX(TEAM_B, FOUL,false);
        displayScoreForTeamX(TEAM_B, PENALTY,false);
        displayScoreForTeamX(TEAM_B, OFFSIDE,false);

        updateMatchState();
    }

    //reset all
    public void resetStats(View view) {
        goalsTeamA = 0;
        cornersTeamA = 0;
        foulsTeamA = 0;
        penaltiesTeamA = 0;
        offsidesTeamA = 0;

        goalsTeamB = 0;
        cornersTeamB = 0;
        foulsTeamB = 0;
        penaltiesTeamB = 0;
        offsidesTeamB = 0;

        displayStats();

        SingleToast.show(this, " ".concat(" ").concat(String.valueOf(getText(R.string.scores_reset))).concat(" "), "#E91E63");
    }

    //+1 goal
    public void goalForTeamX(View view) {
        switch (view.getTag().toString()) {
            case TEAM_A:
                goalsTeamA += 1;
                displayScoreForTeamX(TEAM_A, GOAL, true);
                break;
            case TEAM_B:
                goalsTeamB += 1;
                displayScoreForTeamX(TEAM_B, GOAL, true);
                break;
        }
        updateMatchState();
    }

    //+1 corner
    public void cornerForTeamX(View view) {
        switch (view.getTag().toString()) {
            case TEAM_A:
                cornersTeamA += 1;
                displayScoreForTeamX(TEAM_A, CORNER, true);
                break;
            case TEAM_B:
                cornersTeamB += 1;
                displayScoreForTeamX(TEAM_B, CORNER, true);
                break;
        }
    }

    //+1 foul
    public void foulForTeamX(View view) {
        switch (view.getTag().toString()) {
            case TEAM_A:
                foulsTeamA += 1;
                displayScoreForTeamX(TEAM_A, FOUL, true);
                break;
            case TEAM_B:
                foulsTeamB += 1;
                displayScoreForTeamX(TEAM_B, FOUL, true);
                break;
        }
    }

    //+1 penalty
    public void penaltyForTeamX(View view) {
        switch (view.getTag().toString()) {
            case TEAM_A:
                penaltiesTeamA += 1;
                displayScoreForTeamX(TEAM_A, PENALTY, true);
                break;
            case TEAM_B:
                penaltiesTeamB += 1;
                displayScoreForTeamX(TEAM_B, PENALTY, true);
                break;
        }
    }

    //+1 offside
    public void offsideForTeamX(View view) {
        switch (view.getTag().toString()) {
            case TEAM_A:
                offsidesTeamA += 1;
                displayScoreForTeamX(TEAM_A, OFFSIDE, true);
                break;
            case TEAM_B:
                offsidesTeamB += 1;
                displayScoreForTeamX(TEAM_B, OFFSIDE, true);
                break;
        }
    }

    //update match result for each team (draw/loss/win)
    private void updateMatchState() {
        if (goalsTeamA == goalsTeamB) {
            matchResultViewA.setText(String.valueOf(getText(R.string.draw)));
            matchResultViewB.setText(String.valueOf(getText(R.string.draw)));
        } else if (goalsTeamA > goalsTeamB) {
            matchResultViewA.setText(String.valueOf(getText(R.string.win)));
            matchResultViewB.setText(String.valueOf(getText(R.string.loss)));
        } else {
            matchResultViewA.setText(String.valueOf(getText(R.string.loss)));
            matchResultViewB.setText(String.valueOf(getText(R.string.win)));
        }
    }

    //update score views
    private void displayScoreForTeamX(String team, String scoreType, boolean showToast) {
        switch (team) {
            case TEAM_A:
                switch (scoreType) {
                    case GOAL:
                        goalsViewA.setText(String.valueOf(goalsTeamA));
                        break;

                    case CORNER:
                        cornersViewA.setText(String.valueOf(cornersTeamA));
                        break;

                    case FOUL:
                        foulsViewA.setText(String.valueOf(foulsTeamA));
                        break;

                    case PENALTY:
                        penaltiesViewA.setText(String.valueOf(penaltiesTeamA));
                        break;

                    case OFFSIDE:
                        offsidesViewA.setText(String.valueOf(offsidesTeamA));
                        break;
                }
                break;

            case TEAM_B:
                switch (scoreType) {
                    case GOAL:
                        goalsViewB.setText(String.valueOf(goalsTeamB));
                        break;

                    case CORNER:
                        cornersViewB.setText(String.valueOf(cornersTeamB));
                        break;

                    case FOUL:
                        foulsViewB.setText(String.valueOf(foulsTeamB));
                        break;

                    case PENALTY:
                        penaltiesViewB.setText(String.valueOf(penaltiesTeamB));
                        break;

                    case OFFSIDE:
                        offsidesViewB.setText(String.valueOf(offsidesTeamB));
                        break;
                }
                break;
        }

        if (showToast){
            showToastMessage(team, scoreType);
        }
    }

    //show toast message regarding the user button clicked
    private void showToastMessage(String team, String scoreType) {
        switch (team) {
            case TEAM_A:
                EditText nameInputA = findViewById(R.id.name_team_a);
                String teamNameA = " " + nameInputA.getText().toString();
                //user did not input a team name therefore use text hint used in the input view
                if (teamNameA.length() == 1) {
                    teamNameA = " " + getText(R.string.team_a);
                }

                switch (scoreType) {
                    case GOAL:
                        SingleToast.show(this, teamNameA.concat(" ").concat(String.valueOf(getText(R.string.goal_toast))).concat(" "), TEAM_A_COLOR);
                        break;

                    case CORNER:
                        SingleToast.show(this, teamNameA.concat(" ").concat(String.valueOf(getText(R.string.corner_toast))).concat(" "), TEAM_A_COLOR);
                        break;

                    case FOUL:
                        SingleToast.show(this, teamNameA.concat(" ").concat(String.valueOf(getText(R.string.foul_toast))).concat(" "), TEAM_A_COLOR);
                        break;

                    case PENALTY:
                        SingleToast.show(this, teamNameA.concat(" ").concat(String.valueOf(getText(R.string.penalty_toast))).concat(" "), TEAM_A_COLOR);
                        break;

                    case OFFSIDE:
                        SingleToast.show(this, teamNameA.concat(" ").concat(String.valueOf(getText(R.string.offside_toast))).concat(" "), TEAM_A_COLOR);
                        break;
                }
                break;

            case TEAM_B:
                EditText nameInputB = findViewById(R.id.name_team_b);
                String teamNameB = " " + nameInputB.getText().toString();
                if (teamNameB.length() == 1) {
                    teamNameB = " " + getText(R.string.team_b);
                }

                switch (scoreType) {
                    case GOAL:
                        SingleToast.show(this, teamNameB.concat(" ").concat(String.valueOf(getText(R.string.goal_toast))).concat(" "), TEAM_B_COLOR);
                        break;

                    case CORNER:
                        SingleToast.show(this, teamNameB.concat(" ").concat(String.valueOf(getText(R.string.corner_toast))).concat(" "), TEAM_B_COLOR);
                        break;

                    case FOUL:
                        SingleToast.show(this, teamNameB.concat(" ").concat(String.valueOf(getText(R.string.foul_toast))).concat(" "), TEAM_B_COLOR);
                        break;

                    case PENALTY:
                        SingleToast.show(this, teamNameB.concat(" ").concat(String.valueOf(getText(R.string.penalty_toast))).concat(" "), TEAM_B_COLOR);
                        break;

                    case OFFSIDE:
                        SingleToast.show(this, teamNameB.concat(" ").concat(String.valueOf(getText(R.string.offside_toast))).concat(" "), TEAM_B_COLOR);
                        break;
                }
                break;
        }
    }


}

