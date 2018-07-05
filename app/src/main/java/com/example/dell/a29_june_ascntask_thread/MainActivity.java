package com.example.dell.a29_june_ascntask_thread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv ;
    ProgressBar pb;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.text);
        pb = (ProgressBar)findViewById(R.id.progress);
        iv = (ImageView) findViewById(R.id.imgview);
    }




    public void alivebut(View v){
        Toast.makeText(MainActivity.this,"I am alive",Toast.LENGTH_SHORT).show();

    }
    public void limage(View v)
    {
        new LoadImage().execute(R.drawable.android);
    }

    class LoadImage extends AsyncTask<Integer,Integer,Bitmap>{

        int i;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tv.setText("Manish is best in the world");
            pb.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Integer... integers) {

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),integers[0]);
            for( i=0;i<10;i++)
            {
                try{
                    Thread.sleep(500);
                    publishProgress(i*10);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }



            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(i<10)
            pb.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            iv.setImageBitmap(bitmap);
        }

    }
}
