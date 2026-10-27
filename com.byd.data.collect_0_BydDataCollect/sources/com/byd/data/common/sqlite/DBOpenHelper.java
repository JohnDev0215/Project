package com.byd.data.common.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DBOpenHelper extends SQLiteOpenHelper {
    private final String TAG;
    private List<Table> tables;

    public DBOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i2, List<Table> list) {
        super(context, str, cursorFactory, i2);
        this.TAG = "DBOpenHelper";
        this.tables = new ArrayList();
        this.tables = list;
    }

    private String getTableCreateSQLString(Table<n> table) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("create table if not exists ");
        sb.append(table.f3478b);
        sb.append("(");
        sb.append(table.f3482f);
        sb.append(" integer primary key autoincrement");
        int size = table.f3481e.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append(",");
            n nVar = table.f3481e.get(i2);
            String str2 = nVar.f3530b;
            str2.hashCode();
            switch (str2) {
                case "item_type_float":
                    str = " float not null";
                    break;
                case "item_type_integer":
                    str = " integer not null";
                    break;
                case "item_type_long":
                    str = " long not null";
                    break;
                case "item_type_text":
                    str = " text not null";
                    break;
                case "item_type_boolean":
                    str = " bool not null";
                    break;
                default:
                    str = "";
                    break;
            }
            sb.append(nVar.f3529a);
            sb.append(str);
        }
        sb.append(");");
        String string = sb.toString();
        d.a.a.f.b.b.a.d("DBOpenHelper", "getTableCreateSQLString = " + string);
        return string;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        int size = this.tables.size();
        for (int i2 = 0; i2 < size; i2++) {
            sQLiteDatabase.execSQL(getTableCreateSQLString(this.tables.get(i2)));
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        int size = this.tables.size();
        com.byd.data.common.i.f.k("DBOpenHelper", "onUpgrade: " + i2 + " -> " + i3 + " tables = " + size);
        if (size <= i2) {
            return;
        }
        while (i2 < size) {
            Table table = this.tables.get(i2);
            if (table != null) {
                sQLiteDatabase.execSQL(getTableCreateSQLString(table));
            }
            i2++;
        }
    }

    public DBOpenHelper(Context context, String str, int i2, SQLiteDatabase.OpenParams openParams) {
        super(context, str, i2, openParams);
        this.TAG = "DBOpenHelper";
        this.tables = new ArrayList();
    }
}
