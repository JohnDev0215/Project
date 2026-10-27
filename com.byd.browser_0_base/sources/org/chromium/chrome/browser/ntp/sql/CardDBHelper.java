package org.chromium.chrome.browser.ntp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import defpackage.im;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ntp.NewTabPageLayout;

/* JADX INFO: loaded from: classes2.dex */
public class CardDBHelper extends SQLiteOpenHelper {
    public static CardDBHelper h;
    public static ArrayList<im> i = new ArrayList<>();
    public final SQLiteDatabase e;
    public final Context f;
    public a g;

    public interface a {
    }

    public CardDBHelper(Context context) {
        super(context, "card", (SQLiteDatabase.CursorFactory) null, 1);
        this.e = getWritableDatabase();
        this.f = context;
    }

    public static CardDBHelper q(Context context) {
        if (h == null) {
            h = new CardDBHelper(context);
        }
        return h;
    }

    public final ArrayList C() {
        ArrayList<im> arrayList = i;
        if (arrayList == null || arrayList.size() == 0) {
            if (i == null) {
                i = new ArrayList<>();
            }
            Cursor cursorQuery = this.e.query("card", null, null, null, null, null, "id");
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    im imVar = new im();
                    String string = cursorQuery.getString(cursorQuery.getColumnIndex("url"));
                    String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("tittle"));
                    imVar.e = String.valueOf(cursorQuery.getInt(cursorQuery.getColumnIndex("id")));
                    imVar.d = string2;
                    imVar.c = string;
                    i.add(imVar);
                }
                cursorQuery.close();
            }
            Collections.reverse(i);
        }
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= i.size()) {
                break;
            }
            if (i.get(i2).e.equals("-1")) {
                z = true;
                break;
            }
            i2++;
        }
        if (!z) {
            im imVar2 = new im();
            imVar2.d = this.f.getString(R$string.card_add);
            imVar2.e = "-1";
            i.add(imVar2);
        }
        return i;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table card(id INTEGER PRIMARY KEY AUTOINCREMENT,tittle VARCHAR,url VARCHAR,back VARCHAR);");
    }

    public final void t(int i2, im imVar, boolean z) {
        Log.e("CardDBHelper", "insertData: " + imVar.d);
        ContentValues contentValues = new ContentValues();
        contentValues.put("tittle", imVar.d);
        contentValues.put("url", imVar.c);
        contentValues.put("back", imVar.d);
        SQLiteDatabase sQLiteDatabase = this.e;
        if (sQLiteDatabase.insert("card", null, contentValues) > 0) {
            Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select last_insert_rowid() fromcard", null);
            int i3 = cursorRawQuery.moveToFirst() ? cursorRawQuery.getInt(0) : -1;
            cursorRawQuery.close();
            imVar.e = String.valueOf(i3);
        } else {
            imVar.e = "-1";
        }
        i.add(i2, imVar);
        a aVar = this.g;
        if (aVar != null) {
            ((NewTabPageLayout) aVar).f(z);
            return;
        }
        int i4 = R$drawable.icon_dark_public_green_cherk;
        int i5 = R$string.card_add_sucess;
        Context context = this.f;
        Toast toast = new Toast(context);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.layout_toast_clear_success, (ViewGroup) null, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.iv_toast);
        TextView textView = (TextView) viewInflate.findViewById(R$id.tv_toast);
        toast.setView(viewInflate);
        if (imageView != null) {
            imageView.setImageResource(i4);
        }
        if (textView != null) {
            textView.setText(i5);
        }
        toast.show();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
