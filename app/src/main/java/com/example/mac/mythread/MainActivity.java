package com.example.mac.mythread;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    private TextView mesg;
    private Ui handler;
    private Timer Timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Ui();
        Timer = new Timer();
        mesg = (TextView)findViewById(R.id.mesg);


    }

    public void test1(View view){
        Mythread mythread = new Mythread();
        Mythread mythread1 = new Mythread();
        mythread.start(); mythread1.start();
    }
    public void test2(View view){
        Timer.schedule(new MyTask(), 1000, 500);
    }
    public void test3(View view){

    }

    private class Mythread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                Log.i("test","i="+ i);
                //mesg.setTex("i="+ i)
                handler.sendEmptyMessage(i);

                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }

            }
        }
    }
    private class MyTask extends TimerTask{
        private int i ;

        @Override
        public void run() {
            i++;
            Log.i("test",":" + i);
        }
    }

    @Override
    public void finish() {
        super.finish();
        if (Timer != null){
            Timer.cancel();
            Timer.purge();
            Timer = null;
        }
    }

    private class Ui extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mesg.setText("i="+ msg.what);
        }
    }
}
