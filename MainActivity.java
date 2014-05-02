package com.example.myapplication5.app;

import java.io.IOException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;



public class MainActivity extends Activity implements OnClickListener {

    public Button bplay,bstop,bsoundPool;
    public MediaPlayer mp;
    public SoundPool sp;
    public int flujodemusica;
    public String direcciones[] = {"http://dl.dropboxusercontent.com/s/nvccrw71omnrnnp/01%20One%20More%20Time.mp3","http://dl.dropboxusercontent.com/s/jp63c09hp5z7axa/02%20Aerodynamic.mp3","http://dl.dropboxusercontent.com/s/zsunjrrvrcmq9qd/03%20Digital%20Love.mp3","http://dl.dropboxusercontent.com/s/vdneww9qhj77edf/04%20Harder%2C%20Better%2C%20Faster%2C%20Stronger.mp3","http://dl.dropboxusercontent.com/s/igxsu0ng2flqxjz/12%20Short%20Circuit.mp3"};
    public int i=0;


       public void onCreate(Bundle savedInstanceState) {


           Timer timer = new Timer();
           timer.scheduleAtFixedRate(
                   new java.util.TimerTask() {
                       @Override
                       public void run() {
                            if (mp.isPlaying()==false)
                            {
                           try {
                               play_mp();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }}
                       }
                   },
                   2000, 5000
           );
               mp=new MediaPlayer();

               mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
               super.onCreate(savedInstanceState);

               setContentView(R.layout.activity_main);

               bstop=(Button)

               findViewById(R.id.stopMp);

               bsoundPool=(Button)

               findViewById(R.id.playSp);

               bstop.setOnClickListener(this);
               bsoundPool.setOnClickListener(this);

               this.

               setVolumeControlStream(AudioManager.STREAM_MUSIC);


           }
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
            try {
                play_mp();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void play_mp() throws IOException

    {

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
    }
    private void stop_mp() throws IOException {

        //mp.stop();

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















