package com.manin.seekbarmusic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    AppCompatButton btnplay,btnpause,btnstop=null;
    AppCompatSeekBar seekBar;
    MediaPlayer mediaPlayer;
    Thread thread;
    AppCompatTextView waktuMusic,tvtotal;
    private Boolean bolPause;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deklarasi();
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();

                }
                else {
                    mediaPlayer.start();

                    updateSeekbar();
                    btnplay.setEnabled(false);
                    setUp();
                }
            }
        });

        preparemediaPLAYER();
        
        //setUp();

        //run();

      //  thread = new Thread();

        //thread.start();

    }

    private void setUp() {

        btnplay.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                mediaPlayer.start();
                btnplay.setEnabled(false);

            }

        });



        btnpause.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                mediaPlayer.pause();
                btnplay.setEnabled(true);
               // btnpause.setEnabled(false);

            }

        });


        btnstop.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View currentView) {

                mediaPlayer.stop();

                mediaPlayer =  MediaPlayer.create(getBaseContext(), R.raw.lagunya);

            }

        });

        //seekbar
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser)

                {

                    mediaPlayer.seekTo(progress);

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


       //run();

    }


    public void run(){
        int currentPosition = 0;

        int soundTotal = mediaPlayer.getDuration();

        seekBar.setMax(soundTotal);

        while (mediaPlayer != null && currentPosition < soundTotal)

        {

            try

            {

                Thread.sleep(300);

                currentPosition = mediaPlayer.getCurrentPosition();

            } catch (InterruptedException soundException) {

                return;

            } catch (Exception otherException) {

                return;

            }

            seekBar.setProgress(currentPosition);

        }


    }

    private void deklarasi() {

        btnplay=findViewById(R.id.id_play);
        btnplay.setOnClickListener(this);
        btnpause=findViewById(R.id.id_pause);
        btnpause.setOnClickListener(this);
        btnstop=findViewById(R.id.id_stop);
        btnstop.setOnClickListener(this);
        seekBar=findViewById(R.id.id_seekBar);
        tvtotal=findViewById(R.id.id_tv_total);

        waktuMusic=findViewById(R.id.id_waktu);

        seekBar.setMax(100);


        mediaPlayer = MediaPlayer.create(this.getBaseContext(), R.raw.lagunya);



    }

    @Override
    public void onClick(View v) {

    }


    //new
    private  void updateSeekbar(){
        if (mediaPlayer.isPlaying()){
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100 ));}
        handler.postDelayed(updater, 1000);
    }


    private Runnable updater=new Runnable() {
        @Override
        public void run() {
            updateSeekbar();
            long currentDuration=mediaPlayer.getCurrentPosition();
            waktuMusic.setText(milliSecondToTimer(currentDuration));

        }
    };



    private String milliSecondToTimer(long milliSeconds){
        String timerString="";
        String secondString="";

        int hours=(int)(milliSeconds / (1000 * 60 * 60));
        int minutes=(int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds=(int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000 );

        if (hours > 0) {     timerString= hours +"0"; }

        if (seconds < 10){ secondString="0"+seconds ; }
        else { secondString="" +seconds ; }

        timerString=timerString + minutes + " " + secondString;
        return timerString;
    }



    private void preparemediaPLAYER(){
        try {
            // mediaPlayer.setDataSource(R.raw.lagunya);
            //   mediaPlayer.setDataSource("http://infinityandroid.com/music/good_times.no3"); // url of music file
            // mediaPlayer.setDataSource("https://soundcloud.com/jksonxie/on-my-way-alan-walker");

            mediaPlayer =  MediaPlayer.create(getBaseContext(), R.raw.lagunya);
            mediaPlayer.prepare();

            //total durasi gak muncul
           // tvtotal.setText(milliSecondToTimer(mediaPlayer.getDuration()));
            //tvtotal.setText(mediaPlayer.getDuration());
        }
        catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }



    private void cobaUpdateToGithub(){
        int a=1;
        int b=2;
        int c=a+b;
    }
}
