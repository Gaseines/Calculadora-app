package br.com.gsn.calculadora.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.com.gsn.calculadora.Historico.Historico;
import br.com.gsn.calculadora.R;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CALCULADORA";
    private static final String TABLE_HISTORICO = "historico";


    private static final String CREATE_TABLE_HISTORICO = "CREATE TABLE " + TABLE_HISTORICO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "conta VARCHAR(200), " +
            "resultado VARCHAR(200));";


    private static final String DROP_TABLE_HISTORICO = "DROP TABLE IF EXISTS " + TABLE_HISTORICO;


    public  DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_HISTORICO);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_HISTORICO);
        onCreate(db);

    }

    public long createHistorico (Historico h) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("conta", h.getConta());
        cv.put("resultado", h.getResultado());
        long id = db.insert(TABLE_HISTORICO, null, cv);
        db.close();
        return id;
    }
    public long deleteHistorico (Historico h) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_HISTORICO, "_id = ?", new String[]{String.valueOf(h.getId())});
        db.close();
        return id;
    }

    public void getAllHistorico(Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "conta" , "resultado"};
        Cursor data = db.query(TABLE_HISTORICO, columns, null,
                null, null, null, "conta");
        int[] to = {R.id.textViewId, R.id.textViewConta, R.id.textViewResultado};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.layout_list_view_historico, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Historico getByIdEndereco(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "conta", "resultado"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_HISTORICO, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Historico h = new Historico();
        h.setId(data.getInt(0));
        h.setConta(data.getString(1));
        h.setResultado(data.getString(2));
        data.close();
        db.close();
        return h;
    }

}
