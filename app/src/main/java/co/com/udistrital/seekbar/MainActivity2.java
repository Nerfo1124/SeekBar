package co.com.udistrital.seekbar;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity{

    private ProgressBar progress;
    private int progressBarStatus = 0;
    private Handler progressBarbHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ProgressBar", "Inicio de segunda Actividad.");
        setContentView(R.layout.activity_main_2);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        progress.setMax(100);
        progress.setProgress(100);


        new Thread(new Runnable() {
            public void run() {
                Log.d("ProgressBar", "Inicio del metodo run()");
                int i = 1;
                while (progressBarStatus < 100) {
                    progressBarStatus = i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    progressBarbHandler.post(new Runnable() {
                        public void run() {
                            Log.d("ProgressBar", "Aumentando en " + progressBarStatus + " el progress");
                            progress.setProgress(progressBarStatus);
                            progress.setSecondaryProgress(progressBarStatus);
                        }
                    });
                }
            }
        }).start();
    }
}
