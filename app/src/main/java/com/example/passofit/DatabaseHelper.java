package com.example.passofit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "PassoFitDB";
        private static final String TABLE_HISTORICO = "historico";

        private static final String KEY_ID = "id";
        private static final String KEY_DATA = "data";
        private static final String KEY_PASSOS = "passos";
        private static final String KEY_CALORIAS = "calorias";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Criação da tabela de histórico
            String CREATE_TABLE = "CREATE TABLE " + TABLE_HISTORICO + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + KEY_DATA + " TEXT,"
                    + KEY_PASSOS + " INTEGER,"
                    + KEY_CALORIAS + " INTEGER" + ")";
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORICO);
            onCreate(db);
        }

        // Metodo para adicionar um dia ao histórico
        public void addHistorico(String data, int passos, int calorias) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_DATA, data);
            values.put(KEY_PASSOS, passos);
            values.put(KEY_CALORIAS, calorias);

            db.insert(TABLE_HISTORICO, null, values);
            db.close();
        }

        // Metodo para buscar todo o historico
        public List<String> getAllHistorico() {
            List<String> historicoList = new ArrayList<>();
            String selectQuery = "SELECT * FROM " + TABLE_HISTORICO + " ORDER BY " + KEY_ID + " DESC";

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    String registro = cursor.getString(1) + " - Passos: " +
                            cursor.getInt(2) + " | Kcal: " + cursor.getInt(3);
                    historicoList.add(registro);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return historicoList;
        }
    }
