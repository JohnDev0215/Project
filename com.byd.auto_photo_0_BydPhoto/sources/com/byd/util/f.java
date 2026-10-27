package com.byd.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.byd.bean.AlbumItem;
import com.byd.bean.FileBean;
import com.byd.dilink.hardware.watermark.DiLinkFontUtilsConstants;
import com.byd.dilink.os.BootBusinessConstants;
import com.byd.dilink.provider.CarStatusSettingConstants;
import com.byd.dilink.provider.DiLinkBluetoothDeviceConstants;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile f f2951a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SQLiteDatabase f2952b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f2953c;
    private Handler d = new d(this);

    class a implements FilenameFilter {
        a() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(DiLinkFontUtilsConstants.FILE_PNG_SUFFIX) || str.endsWith(".jpg");
        }
    }

    class b implements FilenameFilter {
        b() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(DiLinkFontUtilsConstants.FILE_PNG_SUFFIX) || str.endsWith(".jpg");
        }
    }

    static class c extends SQLiteOpenHelper {
        public c(Context context) {
            super(context, "photo", (SQLiteDatabase.CursorFactory) null, 2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("create table collect (_id integer primary key autoincrement,name text not null,path text not null,type integer not null,thumb text,time text)");
            sQLiteDatabase.execSQL("create table album (_id integer primary key autoincrement,path text not null,time text)");
            sQLiteDatabase.execSQL("create table tb_external (_id integer primary key autoincrement,name text not null,_data text not null,source_type integer not null,file_type integer,file_date text,description text)");
            sQLiteDatabase.execSQL("create table search_kw(_id integer primary key autoincrement,kw text not null,time text)");
            w.c("photo database onCreate , version : " + sQLiteDatabase.getVersion());
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 1 && i2 == 2) {
                w.c("photo database update , old : " + i + " new : " + i2);
                sQLiteDatabase.execSQL("create table search_kw(_id integer primary key autoincrement,kw text not null,time text)");
            }
        }
    }

    private static class d extends b.a.d<f> {
        public d(f fVar) {
            super(fVar);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(f fVar, Message message) {
            if (message.what != 1) {
                return;
            }
            fVar.m();
        }
    }

    private f(Context context) {
        this.f2953c = context.getApplicationContext();
        m();
    }

    private void f(int i) {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.delete("search_kw", "_id=" + i, null);
    }

    public static f k(Context context) {
        synchronized (f.class) {
            if (f2951a == null) {
                f2951a = new f(context);
            }
        }
        return f2951a;
    }

    private int l() {
        Cursor cursorQuery;
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null || (cursorQuery = sQLiteDatabase.query("search_kw", null, null, null, null, null, null)) == null) {
            return 0;
        }
        int count = cursorQuery.getCount();
        cursorQuery.close();
        return count;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        c cVar;
        Exception e;
        try {
            cVar = new c(this.f2953c.getApplicationContext());
            try {
                this.f2952b = cVar.getWritableDatabase();
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                if (this.f2952b == null || cVar == null) {
                    if (cVar != null) {
                        try {
                            cVar.close();
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                    this.d.sendEmptyMessageDelayed(1, 2000L);
                }
            }
        } catch (Exception e4) {
            cVar = null;
            e = e4;
        }
    }

    private boolean q(String str) {
        Cursor cursorQuery;
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null || (cursorQuery = sQLiteDatabase.query("search_kw", null, "kw=?", new String[]{str}, null, null, null)) == null) {
            return false;
        }
        boolean zMoveToFirst = cursorQuery.moveToFirst();
        cursorQuery.close();
        return zMoveToFirst;
    }

    private FileBean r(String str) {
        File file = new File(str);
        File[] fileArrListFiles = file.listFiles(new a());
        if (fileArrListFiles == null) {
            w.c("collect loadBurstBean -> fs is null");
            return null;
        }
        int length = fileArrListFiles.length;
        File fileR = length > 0 ? x.r(fileArrListFiles) : null;
        w.c("collect burst child num:" + length);
        if (fileR == null) {
            return null;
        }
        long jP = n.p(this.f2953c, fileR.getPath());
        if (jP == 0) {
            jP = fileR.lastModified();
        }
        FileBean fileBean = new FileBean(file.getName(), fileR.getPath(), 3, 3, jP);
        fileBean.setNum(length);
        return fileBean;
    }

    public long b(String str) {
        if (this.f2952b == null) {
            return 0L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", str);
        contentValues.put("time", String.valueOf(System.currentTimeMillis()));
        return this.f2952b.insert("album", null, contentValues);
    }

    public long c(FileBean fileBean) {
        if (this.f2952b == null) {
            w.c("db is null, addCollect fail");
            return 0L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", fileBean.getName());
        contentValues.put(CarStatusSettingConstants.DiCareRecord.DICARE_TYPE, Integer.valueOf(fileBean.getType()));
        contentValues.put("path", fileBean.getType() == 3 ? new File(fileBean.getPath()).getParent() : fileBean.getPath());
        if (fileBean.getThumbPath() != null) {
            contentValues.put("thumb", fileBean.getThumbPath());
        }
        contentValues.put("time", String.valueOf(System.currentTimeMillis()));
        long jInsert = this.f2952b.insert("collect", null, contentValues);
        w.c("add collect : " + contentValues.toString() + " result : " + jInsert);
        return jInsert;
    }

    public void d(String str) {
        if (this.f2952b == null || TextUtils.isEmpty(str)) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", String.valueOf(System.currentTimeMillis()));
        int i = 0;
        if (q(str)) {
            this.f2952b.update("search_kw", contentValues, "kw=?", new String[]{str});
            return;
        }
        if (l() >= 30) {
            Cursor cursorQuery = this.f2952b.query("search_kw", new String[]{DiLinkBluetoothDeviceConstants._ID}, null, null, null, null, "time asc");
            if (cursorQuery != null) {
                cursorQuery.moveToFirst();
                i = cursorQuery.getInt(0);
                cursorQuery.close();
            }
            if (i > 0) {
                f(i);
            }
        }
        contentValues.put("kw", str);
        this.f2952b.insert("search_kw", null, contentValues);
    }

    public void e() {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase != null && sQLiteDatabase.delete("search_kw", null, null) > 0) {
            Cursor cursorQuery = this.f2952b.query("search_kw", null, null, null, null, null, null);
            if (cursorQuery == null) {
                this.f2952b.execSQL("update sqlite_sequence set seq=0 where name='search_kw'");
                return;
            }
            if (cursorQuery.getCount() == 0) {
                this.f2952b.execSQL("update sqlite_sequence set seq=0 where name='search_kw'");
            }
            cursorQuery.close();
        }
    }

    public void g(Context context, b.a.k.s sVar) {
        FileBean fileBean;
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null) {
            return;
        }
        Cursor cursorQuery = sQLiteDatabase.query("collect", new String[]{"name", "path", CarStatusSettingConstants.DiCareRecord.DICARE_TYPE, "thumb", "time"}, null, null, null, null, DiLinkBluetoothDeviceConstants.SORT_ORDER);
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(1);
            File file = new File(string);
            if (string.startsWith("/storage/FFFF-") || (file.exists() && file.length() != 0)) {
                String string2 = cursorQuery.getString(0);
                int i = cursorQuery.getInt(2);
                if (i == 3) {
                    fileBean = r(string);
                    if (fileBean == null) {
                    }
                } else {
                    long jP = n.p(context, string);
                    if (jP == 0) {
                        jP = file.lastModified();
                    }
                    fileBean = new FileBean(string2, string, 3, i, jP);
                }
                String string3 = cursorQuery.getString(3);
                if (string3 != null && !"".equals(string3) && !BootBusinessConstants.SubBusinessState.SUB_BUSINESS_NULL.equals(string3)) {
                    fileBean.setThumbPath(string3);
                }
                sVar.a(fileBean);
            } else {
                w.c("collect file no found : " + string);
                u(string);
            }
        }
        cursorQuery.close();
    }

    public List<String> h() {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        LinkedList linkedList = null;
        if (sQLiteDatabase == null) {
            return null;
        }
        Cursor cursorQuery = sQLiteDatabase.query("search_kw", new String[]{"kw"}, null, null, null, null, DiLinkBluetoothDeviceConstants.SORT_ORDER);
        if (cursorQuery != null) {
            if (cursorQuery.getCount() > 0) {
                LinkedList linkedList2 = new LinkedList();
                while (cursorQuery.moveToNext()) {
                    try {
                        Thread.sleep(0L);
                        linkedList2.add(cursorQuery.getString(0));
                    } catch (Exception unused) {
                        w.c("getAllKeyWords interrupt");
                    }
                }
                linkedList = linkedList2;
            }
            cursorQuery.close();
        }
        return linkedList;
    }

    public List<String> i() {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null) {
            return null;
        }
        Cursor cursorQuery = sQLiteDatabase.query("album", new String[]{"path", "time"}, null, null, null, null, DiLinkBluetoothDeviceConstants.SORT_ORDER);
        ArrayList arrayList = new ArrayList(cursorQuery.getCount());
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(0);
            if (new File(string).exists()) {
                arrayList.add(string);
            } else {
                t(string);
            }
        }
        cursorQuery.close();
        return arrayList;
    }

    public int j(AlbumItem albumItem) {
        Cursor cursorQuery;
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null || (cursorQuery = sQLiteDatabase.query("collect", new String[]{"path", CarStatusSettingConstants.DiCareRecord.DICARE_TYPE}, null, null, null, null, DiLinkBluetoothDeviceConstants.SORT_ORDER)) == null) {
            return 0;
        }
        j jVarE = j.e(this.f2953c);
        int i = 0;
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(0);
            File file = new File(string);
            if (string.startsWith("/storage/FFFF-") || (file.exists() && file.length() != 0)) {
                if (x.i(string) && jVarE.d(string) == 0) {
                    w.c("remove external collect file : " + string);
                } else {
                    if (albumItem.getCoverPath() == null) {
                        if (cursorQuery.getInt(1) != 3) {
                            albumItem.setCoverPath(string);
                            w.c("collection cover path:" + albumItem.getCoverPath());
                        } else {
                            File[] fileArrListFiles = new File(string).listFiles(new b());
                            if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                                string = x.r(fileArrListFiles).getPath();
                                albumItem.setCoverPath(string);
                            }
                            w.c("collection cover path:" + albumItem.getCoverPath());
                        }
                    }
                    i++;
                }
            }
            u(string);
        }
        if (i == 0) {
            albumItem.setCoverPath(null);
        }
        cursorQuery.close();
        return i;
    }

    public boolean n(String str) {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        boolean z = false;
        if (sQLiteDatabase == null) {
            return false;
        }
        Cursor cursorQuery = sQLiteDatabase.query("collect", new String[]{"path"}, null, null, null, null, null);
        if (cursorQuery != null) {
            while (true) {
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                if (cursorQuery.getString(0).startsWith(str)) {
                    z = true;
                    break;
                }
            }
            cursorQuery.close();
        }
        return z;
    }

    public boolean o(String str) {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null) {
            return false;
        }
        Cursor cursorQuery = sQLiteDatabase.query("collect", null, "path=?", new String[]{str}, null, null, null);
        boolean zMoveToFirst = cursorQuery.moveToFirst();
        cursorQuery.close();
        return zMoveToFirst;
    }

    public boolean p(String str) {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null) {
            return false;
        }
        Cursor cursorQuery = sQLiteDatabase.query("album", null, "path=?", new String[]{str}, null, null, null);
        boolean zMoveToFirst = cursorQuery.moveToFirst();
        cursorQuery.close();
        return zMoveToFirst;
    }

    public void s() {
        this.d.removeCallbacksAndMessages(null);
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
            this.f2952b = null;
        }
        f2951a = null;
    }

    public boolean t(String str) {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null) {
            return false;
        }
        int iDelete = sQLiteDatabase.delete("album", "path=?", new String[]{str});
        w.c("removeAlbum : " + str + " id:" + iDelete);
        return iDelete > 0;
    }

    public boolean u(String str) {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        return sQLiteDatabase != null && sQLiteDatabase.delete("collect", "path=?", new String[]{str}) > 0;
    }

    public void v(String str) {
        SQLiteDatabase sQLiteDatabase = this.f2952b;
        if (sQLiteDatabase == null) {
            return;
        }
        int iDelete = sQLiteDatabase.delete("collect", "path like '" + str + "%'", null);
        StringBuilder sb = new StringBuilder();
        sb.append("delete external collect count  : ");
        sb.append(iDelete);
        w.c(sb.toString());
    }

    public int w(String str, String str2) {
        if (this.f2952b == null) {
            return 0;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("path", str2);
        contentValues.put("time", String.valueOf(System.currentTimeMillis()));
        return this.f2952b.update("album", contentValues, "path=?", new String[]{str});
    }

    public long x(String str, String str2) {
        if (this.f2952b == null) {
            return 0L;
        }
        File file = new File(str2);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", file.getName());
        contentValues.put("path", file.getPath());
        contentValues.put("time", String.valueOf(System.currentTimeMillis()));
        return this.f2952b.update("collect", contentValues, "path=?", new String[]{str});
    }
}
