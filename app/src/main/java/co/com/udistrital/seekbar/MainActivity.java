package co.com.udistrital.seekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SeekBar seekBar;
    private TextView cantidad;
    private TextView accion;
    private Button mas;
    private Button menos;
    private EditText texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (EditText)findViewById(R.id.txtFuente);
        seekBar = (SeekBar) findViewById(R.id.barra);
        Log.d("SeekBar", "Tamaño de letra: " + texto.getTextSize());
        seekBar.setProgress((int) texto.getTextSize());
        cantidad = (TextView) findViewById(R.id.cantidad);
        cantidad.setText("Tamaño de la Fuente: " + texto.getTextSize() + "%");
        accion = (TextView) findViewById(R.id.accion);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                //la Seekbar siempre empieza en cero, si queremos que el valor mínimo sea otro podemos modificarlo
                cantidad.setText("Tamaño de la Fuente: " + progress + "%");
                texto.setTextSize(TypedValue.COMPLEX_UNIT_PX, progress);
            }

            /**
             * El usuario inicia la interacción con la Seekbar.
             */
            @Override
            public void onStartTrackingTouch(SeekBar bar){}

            /**
             * El usuario finaliza la interacción con la Seekbar.
             */
            @Override
            public void onStopTrackingTouch(SeekBar bar){}
        });

        mas = (Button) findViewById(R.id.mas);
        mas.setOnClickListener((View.OnClickListener) this);

        menos = (Button) findViewById(R.id.menos);
        menos.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick (View v){
        int progreso;
        int newProgreso;
        if(v.getId() == R.id.mas){
            progreso = seekBar.getProgress();
            if(progreso<100){
                newProgreso = progreso + 1;
                seekBar.setProgress(newProgreso);
                cantidad.setText("Tamaño de la Fuente: " + newProgreso + "%");
                texto.setTextSize(TypedValue.COMPLEX_UNIT_PX, newProgreso);
            }
        }

        if(v.getId() == R.id.menos) {
            progreso = seekBar.getProgress();
            if(progreso>0){
                newProgreso = progreso - 1;
                seekBar.setProgress(newProgreso);
                cantidad.setText("Tamaño de la Fuente: " + newProgreso + "%");
                texto.setTextSize(TypedValue.COMPLEX_UNIT_PX, newProgreso);
            }
        }
    }
}
