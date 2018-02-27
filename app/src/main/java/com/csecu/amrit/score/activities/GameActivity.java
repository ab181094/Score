package com.csecu.amrit.score.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.csecu.amrit.score.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView tvScore, tvTotal, tvOvers, tvTarget, tvCommentary;
    Button btHit, btSecond;
    int total = 0, wicket = 0, totalOvers = 10, overs = 0, ball = 0, target = 0;
    Boolean second = false;
    String[] oneArray, twoArray, threeArray, fourArray, outArray, dotArray, sixArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linkAll();

        Resources resources = getResources();
        oneArray = resources.getStringArray(R.array.one);
        twoArray = resources.getStringArray(R.array.two);
        threeArray = resources.getStringArray(R.array.three);
        fourArray = resources.getStringArray(R.array.four);
        outArray = resources.getStringArray(R.array.out);
        dotArray = resources.getStringArray(R.array.dot);
        sixArray = resources.getStringArray(R.array.six);
    }

    private void linkAll() {
        tvScore = findViewById(R.id.tv_score);
        tvTotal = findViewById(R.id.tv_total);
        tvOvers = findViewById(R.id.tv_overs);
        tvTarget = findViewById(R.id.tv_target);
        tvCommentary = findViewById(R.id.tv_commentary);

        btHit = findViewById(R.id.bt_hit);
        btSecond = findViewById(R.id.bt_second);
    }

    public void onHit(View view) {
        if (wicket < 10 && overs < totalOvers) {
            if (second) {
                if (total <= target) {
                    Random random = new Random();
                    int run = random.nextInt(7);
                    if (run == 5) {
                        tvScore.setText("Out");
                        wicket++;
                        tvTotal.setText("Total: " + total + "/" + wicket);
                    } else {
                        total = total + run;
                        tvScore.setText(String.valueOf(run));
                        tvTotal.setText("Total: " + total + "/" + wicket);
                    }
                    ball++;
                    if (ball == 6) {
                        overs++;
                        ball = 0;
                    }
                    tvOvers.setText("Overs: " + overs + "." + ball);
                    getCommentary(run);
                } else {
                    tvScore.setText("Second player wins");
                    tvCommentary.setText(null);
                }
            } else {
                Random random = new Random();
                int run = random.nextInt(7);
                if (run == 5) {
                    tvScore.setText("Out");
                    wicket++;
                    tvTotal.setText("Total: " + total + "/" + wicket);
                } else {
                    total = total + run;
                    tvScore.setText(String.valueOf(run));
                    tvTotal.setText("Total: " + total + "/" + wicket);
                }
                ball++;
                if (ball == 6) {
                    overs++;
                    ball = 0;
                }
                tvOvers.setText("Overs: " + overs + "." + ball);
                getCommentary(run);
            }
        } else if (wicket == 10) {
            if (second) {
                if (total < target) {
                    tvScore.setText("First player wins");
                } else if (total == target) {
                    tvScore.setText("Match draw");
                } else {
                    tvScore.setText("Second player wins");
                }
                tvCommentary.setText(null);
            } else {
                tvScore.setText("All Out");
                tvCommentary.setText(null);
                btHit.setVisibility(View.INVISIBLE);
                btSecond.setVisibility(View.VISIBLE);
            }
        } else if (overs == totalOvers) {
            if (second) {
                if (total < target) {
                    tvScore.setText("First player wins");
                } else if (total == target) {
                    tvScore.setText("Match draw");
                } else {
                    tvScore.setText("Second player wins");
                }
                tvCommentary.setText(null);
            } else {
                tvScore.setText("Innings over");
                tvCommentary.setText(null);
                btHit.setVisibility(View.INVISIBLE);
                btSecond.setVisibility(View.VISIBLE);
            }
        }
    }

    private void getCommentary(int run) {
        String commentary = null;
        int index = 0, bound = 0;
        Random random = new Random();

        switch (run) {
            case 0:
                try {
                    bound = dotArray.length;
                    index = random.nextInt(bound);
                    commentary = dotArray[index];
                } catch (Exception e) {
                    tvCommentary.setText("Exception " + bound);
                }
                break;
            case 1:
                try {
                    bound = oneArray.length;
                    index = random.nextInt(bound);
                    commentary = oneArray[index];
                } catch (Exception e) {
                    tvCommentary.setText("Exception " + bound);
                }
                break;
            case 2:
                try {
                    bound = twoArray.length;
                    index = random.nextInt(bound);
                    commentary = twoArray[index];
                } catch (Exception e) {
                    tvCommentary.setText("Exception " + bound);
                }
                break;
            case 3:
                try {
                    bound = threeArray.length;
                    index = random.nextInt(bound);
                    commentary = threeArray[index];
                } catch (Exception e) {
                    tvCommentary.setText("Exception " + bound);
                }
                break;
            case 4:
                try {
                    bound = fourArray.length;
                    index = random.nextInt(bound);
                    commentary = fourArray[index];
                } catch (Exception e) {
                    tvCommentary.setText("Exception " + bound);
                }
                break;
            case 5:
                try {
                    bound = outArray.length;
                    index = random.nextInt(bound);
                    commentary = outArray[index];
                } catch (Exception e) {
                    tvCommentary.setText("Exception " + bound);
                }
                break;
            case 6:
                try {
                    bound = sixArray.length;
                    index = random.nextInt(bound);
                    commentary = sixArray[index];
                } catch (Exception e) {
                    tvCommentary.setText("Exception " + bound);
                }
                break;
        }
        if (commentary != null) {
            tvCommentary.setText(commentary);
        }
    }

    public void onSecond(View view) {
        btSecond.setVisibility(View.INVISIBLE);
        btHit.setVisibility(View.VISIBLE);
        target = total;
        tvTarget.setText("Target: " + target);
        tvTarget.setVisibility(View.VISIBLE);
        total = 0;
        wicket = 0;
        totalOvers = 10;
        overs = 0;
        ball = 0;
        second = true;
    }
}
