package com.example.passofit;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MetaActivity extends AppCompatActivity {

    private EditText edtNovaMeta;
    private Button btnSalvarMeta, btnVoltar;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meta);

        edtNovaMeta = findViewById(R.id.edtNovaMeta);
        btnSalvarMeta = findViewById(R.id.btnSalvarMeta);
        btnVoltar = findViewById(R.id.btnVoltarMeta);

        prefs = getSharedPreferences("PassoFitPrefs", MODE_PRIVATE);

        // Carrega a meta atual no campo de texto
        int metaAtual = prefs.getInt("meta_diaria", 8000);
        edtNovaMeta.setText(String.valueOf(metaAtual));

        btnSalvarMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String metaStr = edtNovaMeta.getText().toString();
                if (!metaStr.isEmpty()) {
                    int novaMeta = Integer.parseInt(metaStr);

                    // Salva a nova meta usando SharedPreferences
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("meta_diaria", novaMeta);
                    editor.apply();

                    Toast.makeText(MetaActivity.this, "Meta atualizada!", Toast.LENGTH_SHORT).show();
                    finish();
                    // Volta para a tela anterior
                } else {
                    Toast.makeText(MetaActivity.this, "Digite um valor válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}