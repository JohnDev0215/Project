package b.a.h;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.NonNull;
import com.byd.util.w;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SQLiteDatabase f494a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f495b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f496c = new a(this);

    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<k> f497a;

        public a(k kVar) {
            super(Looper.getMainLooper());
            this.f497a = new WeakReference<>(kVar);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            k kVar;
            WeakReference<k> weakReference = this.f497a;
            if (weakReference == null || (kVar = weakReference.get()) == null) {
                return;
            }
            kVar.h();
        }
    }

    private static class b extends SQLiteOpenHelper {
        public b(Context context) {
            super(context, "thumb_db", (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table tb_thumb (path text not null,name text not null,width integer ,height integer ,crop integer,resize integer)");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f498a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f499b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f500c;
        private int d;
        private boolean e;
        private boolean f;

        public c(String str, String str2, int i, int i2, boolean z, boolean z2) {
            this.f498a = str;
            this.f499b = str2;
            this.f500c = i;
            this.d = i2;
            this.e = z;
            this.f = z2;
        }

        public int a() {
            return this.d;
        }

        public String b() {
            return this.f499b;
        }

        public String c() {
            return this.f498a;
        }

        public int d() {
            return this.f500c;
        }

        public boolean e() {
            return this.e;
        }

        public boolean f() {
            return this.f;
        }
    }

    public k(Context context) {
        this.f495b = context;
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        b bVar = null;
        try {
            b bVar2 = new b(this.f495b.getApplicationContext());
            try {
                this.f494a = bVar2.getWritableDatabase();
            } catch (Exception unused) {
                bVar = bVar2;
                if (this.f494a == null || bVar == null) {
                    if (bVar != null) {
                        try {
                            bVar.close();
                        } catch (Exception unused2) {
                        }
                    }
                    this.f496c.sendEmptyMessageDelayed(1, 300L);
                }
            }
        } catch (Exception unused3) {
        }
    }

    public int b(String str, String str2, int i, int i2, boolean z, boolean z2) {
        int iInsert;
        StringBuilder sb;
        String str3;
        if (this.f494a == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", str);
        contentValues.put("name", str2);
        contentValues.put("width", Integer.valueOf(i));
        contentValues.put("height", Integer.valueOf(i2));
        contentValues.put("crop", Integer.valueOf(z ? 1 : 0));
        contentValues.put("resize", Integer.valueOf(z2 ? 1 : 0));
        if (c(str, i, i2)) {
            iInsert = this.f494a.update("tb_thumb", contentValues, "path=? and width=? and height = ?", new String[]{str, String.valueOf(i), String.valueOf(i2)});
            sb = new StringBuilder();
            str3 = "update thumb ";
        } else {
            iInsert = (int) this.f494a.insert("tb_thumb", null, contentValues);
            sb = new StringBuilder();
            str3 = "add thumb ";
        }
        sb.append(str3);
        sb.append(str);
        sb.append(" result : ");
        sb.append(iInsert);
        w.c(sb.toString());
        return iInsert;
    }

    public boolean c(String str, int i, int i2) {
        Cursor cursorQuery = this.f494a.query("tb_thumb", null, "path=? and width=? and height=?", new String[]{str, String.valueOf(i), String.valueOf(i2)}, null, null, null);
        if (cursorQuery == null) {
            return false;
        }
        boolean zMoveToNext = cursorQuery.moveToNext();
        cursorQuery.close();
        return zMoveToNext;
    }

    public void d(String str) {
        w.c("delete thumb record: " + str + " result : " + this.f494a.delete("tb_thumb", "path=?", new String[]{str}));
    }

    public String e(String str) {
        Cursor cursorQuery = this.f494a.query("tb_thumb", new String[]{"name"}, "path=?", new String[]{str}, null, null, null);
        if (cursorQuery != null) {
            string = cursorQuery.moveToNext() ? cursorQuery.getString(0) : null;
            cursorQuery.close();
        }
        return string;
    }

    public List<c> f(String str) {
        Cursor cursorQuery = this.f494a.query("tb_thumb", new String[]{"name", "width", "height", "crop", "resize"}, "path=?", new String[]{str}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
        while (cursorQuery.moveToNext()) {
            arrayList.add(new c(str, cursorQuery.getString(0), cursorQuery.getInt(1), cursorQuery.getInt(2), cursorQuery.getInt(3) == 1, cursorQuery.getInt(4) == 1));
        }
        cursorQuery.close();
        return arrayList;
    }

    public List<String> g(String str) {
        Cursor cursorQuery = this.f494a.query("tb_thumb", new String[]{"name"}, "path=?", new String[]{str}, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
        while (cursorQuery.moveToNext()) {
            arrayList.add(cursorQuery.getString(0));
        }
        cursorQuery.close();
        return arrayList;
    }

    public List<String> i(String str) {
        Cursor cursorQuery = this.f494a.query("tb_thumb", new String[]{"path", "name"}, null, null, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(0);
            if (string.startsWith(str)) {
                linkedList.add(cursorQuery.getString(1));
                d(string);
            }
        }
        cursorQuery.close();
        return linkedList;
    }

    public void j(c cVar, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", str);
        contentValues.put("name", str2);
        w.c("rename thumb " + cVar.c() + " " + str + " result : " + this.f494a.update("tb_thumb", contentValues, "path=? and name=?", new String[]{cVar.c(), cVar.b()}));
    }
}
