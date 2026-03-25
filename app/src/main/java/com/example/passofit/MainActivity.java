package com.example.passofit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView txtPassos, txtCalorias, txtMeta;
    private Button btnMeta, btnHistorico, btnSalvarDia;

    private SensorManager sensorManager;
    private Sensor acelerometro;

    private int passos = 0;
    private int meta = 8000;
    private double magnitudeAnterior = 0;

    // Detecção de passos
    private static final double THRESHOLD = 12.0;

    private SharedPreferences prefs;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Associação de componentes (Views)
        txtPassos = findViewById(R.id.txtPassos);
        txtCalorias = findViewById(R.id.txtCalorias);
        txtMeta = findViewById(R.id.txtMeta);
        btnMeta = findViewById(R.id.btnMeta);
        btnHistorico = findViewById(R.id.btnHistorico);
        btnSalvarDia = findViewById(R.id.btnSalvarDia);

        dbHelper = new DatabaseHelper(this);

        // Uso de SharedPreferences para persistência chave-valor
        prefs = getSharedPreferences("PassoFitPrefs", MODE_PRIVATE);
        passos = prefs.getInt("passos_hoje", 0);
        meta = prefs.getInt("meta_diaria", 8000);

        atualizarInterface();

        // Configuração do Sensor (Acelerômetro)
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        // Navegação para a tela de Meta
        btnMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MetaActivity.class);
                startActivity(intent);
            }
        });

        // Navegação para a tela de Histórico
        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
                startActivity(intent);
            }
        });

        // Botão para salvar o dia no SQLite e zerar contador
        btnSalvarDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataHoje = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                int calorias = (int) (passos * 0.04);

                // Salva no banco de dados local
                dbHelper.addHistorico(dataHoje, passos, calorias);

                // Zera os passos para o próximo dia
                passos = 0;
                salvarPassos();
                atualizarInterface();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Atualiza a meta caso tenha sido alterada na MetaActivity
        meta = prefs.getInt("meta_diaria", 8000);
        atualizarInterface();

        // Registra o listener do sensor
        if (acelerometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Desregistra o sensor para economizar bateria
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
        salvarPassos();
    }

    // Lógica principal do Acelerômetro para contar passos
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Calcula a magnitude do vetor de aceleração
            double magnitude = Math.sqrt(x * x + y * y + z * z);
            double delta = magnitude - magnitudeAnterior;
            magnitudeAnterior = magnitude;

            // Se a variação for maior que o limiar, consideramos um passo
            if (magnitude > THRESHOLD && delta > 2.0) {
                passos++;
                atualizarInterface();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void atualizarInterface() {
        txtPassos.setText(String.valueOf(passos));
        txtMeta.setText("/ " + meta + " passos");

        // Cálculo simples: ~0.04 kcal por passo
        int calorias = (int) (passos * 0.04);
        txtCalorias.setText(calorias + " kcal");
    }

    private void salvarPassos() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("passos_hoje", passos);
        editor.apply();
    }
}