package com.android.car.developeroptions.homepage.contextualcards;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class CardDatabaseHelper extends SQLiteOpenHelper {
    static CardDatabaseHelper sCardDatabaseHelper;

    public CardDatabaseHelper(Context context) {
        super(context, "homepage_cards.db", (SQLiteDatabase.CursorFactory) null, 5);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE cards(name TEXT NOT NULL PRIMARY KEY, type INTEGER NOT NULL, score DOUBLE NOT NULL, slice_uri TEXT, category INTEGER DEFAULT 0, localized_to_locale TEXT, package_name TEXT NOT NULL, app_version INTEGER NOT NULL, title_res_name TEXT, title_text TEXT, summary_res_name TEXT, summary_text TEXT, icon_res_name TEXT, icon_res_id INTEGER DEFAULT 0, card_action INTEGER, expire_time_ms INTEGER, support_half_width INTEGER DEFAULT 0, card_dismissed INTEGER DEFAULT 0 );");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < i2) {
            Log.d("CardDatabaseHelper", "Reconstructing DB from " + i + " to " + i2);
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cards");
            onCreate(sQLiteDatabase);
        }
    }

    public static synchronized CardDatabaseHelper getInstance(Context context) {
        if (sCardDatabaseHelper == null) {
            sCardDatabaseHelper = new CardDatabaseHelper(context.getApplicationContext());
        }
        return sCardDatabaseHelper;
    }

    Cursor getContextualCards() {
        return getReadableDatabase().query("cards", null, "card_dismissed=0", null, null, null, "score DESC");
    }

    public int markContextualCardAsDismissed(Context context, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("card_dismissed", (Integer) 1);
        int iUpdate = writableDatabase.update("cards", contentValues, "name=?", new String[]{str});
        writableDatabase.close();
        context.getContentResolver().notifyChange(CardContentProvider.DELETE_CARD_URI, null);
        return iUpdate;
    }
}
