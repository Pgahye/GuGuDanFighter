package com.jx372.gugudanfighter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jx372.gugudanfighter.test.TimerTaskActivity;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {


    private Timer timer= new Timer();
    private int count = 0;
    private int select;

    final int[] Button_Ids={
      R.id.button_0_0,R.id.button_0_1,R.id.button_0_2,R.id.button_1_0,R.id.button_1_1,R.id.button_1_2, R.id.button_2_0,R.id.button_2_1, R.id.button_2_2
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        timer.schedule(new GameActivity.PlayGameTimerTask(), 1000, 1000);

        newgame();





    }



    public void newgame(){

        System.out.println("새로운 게임입니다. ");

       select = ramdomize(0,8);

       System.out.println("정답:" + select);


        int left = ramdomize(1,9);
        int right = ramdomize(1,9);
        ((TextView)findViewById(R.id.textViewLeftOperand)).setText(left + "");
        ((TextView)findViewById(R.id.textViewRightOperand)).setText(right + "");
        ((TextView)findViewById(R.id.textViewScore)).setText(count+"");



        int a=0;
        int b=0;
        int check=0;
        int [][]num = new int[9][3];



        for(int i=0; i<9; i++){

            if(i==select){

                a=left;
                b=right;

            }else{
                a=ramdomize(1,9);
                b=ramdomize(1,9);
            }


            while(true){

                for(int j=0; j<9; j++){

                    if(num[j][2]==(a*b)){
                        a=ramdomize(1,9);
                        b=ramdomize(1,9);
                        break;
                    }
                    else{
                        if(j==8){
                            check=1;
                            break;
                        }


                    }
                }
                if(check==1){
                    check=0;
                    break;
                }

            }


            num[i][0]=a;
            num[i][1]=b;
            num[i][2]=a*b;


        }
        for(int i=0; i<9; i++){
            System.out.print(i +"번째");
            System.out.print(num[i][0] +" ");
            System.out.print(num[i][1]+" ");
            System.out.println(num[i][2]);

        }
        ((TextView)findViewById(R.id.button_0_0)).setText(num[0][2] + "");
        ((TextView)findViewById(R.id.button_0_1)).setText(num[1][2] + "");
        ((TextView)findViewById(R.id.button_0_2)).setText(num[2][2] + "");
        ((TextView)findViewById(R.id.button_1_0)).setText(num[3][2] + "");
        ((TextView)findViewById(R.id.button_1_1)).setText(num[4][2] + "");
        ((TextView)findViewById(R.id.button_1_2)).setText(num[5][2] + "");
        ((TextView)findViewById(R.id.button_2_0)).setText(num[6][2] + "");
        ((TextView)findViewById(R.id.button_2_1)).setText(num[7][2] + "");
        ((TextView)findViewById(R.id.button_2_2)).setText(num[8][2] + "");



        findViewById( Button_Ids[select]).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int textButton = ((Button) view).getId();




               // System.out.println("select" + Button_Ids[select]);

                if(textButton ==Button_Ids[select] ){


                 count++;

                  TextView textView = (TextView) findViewById(R.id.textViewScore);


                  textView.setText(count+"");

                newgame();



                }

                //count++;

                //TextView textView = (TextView) findViewById(R.id.textViewScore);


                // textView.setText(count+"");

                //newgame();


            }}


        );



    }




    public int ramdomize(int form, int to){


        return (int)(Math.random() * to) + form;
    }

    private class PlayGameTimerTask extends TimerTask {
        private int seconds;

        @Override
        public void run() {
            ++seconds;

            if(seconds >= 30){
                timer.cancel();
                Intent intent =new Intent(  GameActivity.this, GameOverActivity.class  );

                intent.putExtra("data-count", count);

                startActivity( intent );

                finish(); //startactivity c추가?


                return;
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView)findViewById(R.id.textView4)).setText((30-seconds)+"초");
                }
            });

            //System.out.println(seconds);

        }
    }
}
// 새로운 activity 띄우는게 아님 , 숫자가 중복되서는 안되고 터무니없는 숫자도 안됨 hashset a, b 발생 곱하기 값으로 비교 (동질성)