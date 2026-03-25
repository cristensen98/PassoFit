package com.example.passofit;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    private ListView listViewHistorico;
    private Button btnVoltarHistorico;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        listViewHistorico = findViewById(R.id.listViewHistorico);
        btnVoltarHistorico = findViewById(R.id.btnVoltarHistorico);
        dbHelper = new DatabaseHelper(this);

        // Busca os dados do SQLite
        List<String> dados = dbHelper.getAllHistorico();

        // Configura o ArrayAdapter para preencher a ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                // Layout padrão do Android para itens de lista
                android.R.layout.simple_list_item_1,
                dados
        );

        listViewHistorico.setAdapter(adapter);

        btnVoltarHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}