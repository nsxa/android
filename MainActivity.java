package com.example.myapplication5.app;

import java.io.IOException;
import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.Timer;



public class MainActivity extends Activity implements OnClickListener {

    public Button bstop;
    public Button bplay;
    public MediaPlayer mp;

    public String direcciones[] = {"http://dl.dropboxusercontent.com/s/nvccrw71omnrnnp/01%20One%20More%20Time.mp3","http://dl.dropboxusercontent.com/s/jp63c09hp5z7axa/02%20Aerodynamic.mp3","http://dl.dropboxusercontent.com/s/zsunjrrvrcmq9qd/03%20Digital%20Love.mp3","http://dl.dropboxusercontent.com/s/vdneww9qhj77edf/04%20Harder%2C%20Better%2C%20Faster%2C%20Stronger.mp3","http://dl.dropboxusercontent.com/s/igxsu0ng2flqxjz/12%20Short%20Circuit.mp3"};
    public int i=0;
    public Timer timer;

       public void onCreate(Bundle savedInstanceState) {



               mp=new MediaPlayer();

               mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
               super.onCreate(savedInstanceState);

               setContentView(R.layout.activity_main);

                bstop = (Button) findViewById(R.id.stopMp);
                bplay = (Button) findViewById(R.id.playSp);

               bstop.setOnClickListener(this);
               bplay.setOnClickListener(this);

               this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

               mitimer();
           }

            //delay,tiempo5seg
           @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public void onClick(View v) {
// TODO Auto-generated method stub

        if(v.getId()==R.id.stopMp){


            try {
                stop_mp();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(v.getId()==R.id.playSp){

            mp.stop();
            mp.reset();
            timer.cancel();
            timer.purge();


            try {
                play_mp();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        }



    private void mitimer(){
        timer = new Timer();

        timer.scheduleAtFixedRate(
                new java.util.TimerTask() {

                    public MediaPlayer mp=new MediaPlayer() ;

                    public void run(){

                        if ((this.mp.isPlaying()==false)) {
                            try {
                                mp.stop();
                                mp.reset();
                                if(i>4)
                                {
                                    i=4;
                                }
                                mp.setDataSource(direcciones[i]);
                                mp.prepare();

                                mp.start();
                                i++;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }}
                    }
                },0,10000
        );
    }
    private void play_mp() throws IOException

    {
        timer.cancel();
        timer.purge();

        mp.stop();
        mp.reset();

        if(i>4)
        {
            i=4;
        }
        mp.setDataSource(direcciones[i]);
        mp.prepare();

        mp.start();
        i++;

        mitimer();
    }

    private void stop_mp() throws IOException {


        mp.stop();
        mp.reset();
        i--;
        if(i<0)
        {
            i=0;
        }
        mp.setDataSource(direcciones[i]);
        mp.prepare();

        mp.start();


    }
}















