package com.jx372.gugudanfighter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent= getIntent();
        int datacount = intent.getIntExtra("data-count", -1);

        ((TextView)findViewById(R.id.textcount)).setText("맞은갯수:"+datacount);

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
                                                         @Override
                                                         public void onClick(View view) {

                                                             startActivity( new Intent( GameOverActivity.this, GameActivity.class  ) );
                                                             finish();

                                                         }
                                                     }


        );

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
                                                          @Override
                                                          public void onClick(View view) {

                                                              startActivity( new Intent( GameOverActivity.this, MainActivity.class  ) );
                                                              finish();

                                                          }
                                                      }


        );


    }
}
