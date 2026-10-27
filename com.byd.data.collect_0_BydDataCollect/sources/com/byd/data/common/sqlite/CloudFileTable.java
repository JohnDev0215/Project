package com.byd.data.common.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CloudFileTable extends Table<com.byd.data.common.e.b> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f3473h = "createData";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f3474i = "lastModifiedTime";
    public final String j = "isPeriod";
    public final String k = "type";
    public final String l = "uuid";
    public final String m = "length";
    private final String n = "createData =? ";

    public CloudFileTable() {
        j();
    }

    private void a(File file, File file2) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int i2 = fileInputStream.read(bArr);
                        if (i2 < 0) {
                            break;
                        } else {
                            zipOutputStream.write(bArr, 0, i2);
                        }
                    }
                    fileInputStream.close();
                    zipOutputStream.close();
                    fileOutputStream.close();
                    boolean zDelete = file.delete();
                    com.byd.data.common.i.f.g(this.f3477a, "writeZipFile fileName = " + file2.getPath() + ", delRet =" + zDelete);
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (l()) {
            e(sQLiteDatabase);
        }
        com.byd.data.common.i.f.g(this.f3477a, "deleteExpire cast time =" + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query(this.f3478b, null, "type = ?", new String[]{String.valueOf(1)}, null, null, "createData", "1");
        if (cursorQuery == null || cursorQuery.getCount() <= 0) {
            com.byd.data.common.i.f.g(this.f3477a, "queryExpireDb cursor is empty");
            return;
        }
        long j = 0;
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("path"));
            long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("createData"));
            if (com.byd.data.common.i.c.c(string)) {
                com.byd.data.common.i.f.g(this.f3477a, "deleteFile=" + string + ",deleteCreateData=" + j2);
                j = j2;
            }
        }
        cursorQuery.close();
        if (j > 0) {
            sQLiteDatabase.delete(this.f3478b, "createData =? ", new String[]{Long.toString(j)});
        }
    }

    private String g() {
        return "INSERT OR REPLACE INTO " + this.f3478b + " (name, path, createData, type, uuid, length, lastModifiedTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    private List<File> h(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        ArrayList arrayList = new ArrayList();
        if (fileArrListFiles != null) {
            for (File file : fileArrListFiles) {
                if (file.getName().endsWith(".data")) {
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }

    private void j() {
        this.f3478b = getClass().getSimpleName();
        this.f3481e.add(new n("name", "item_type_text"));
        this.f3481e.add(new n("path", "item_type_text"));
        this.f3481e.add(new n("createData", "item_type_long"));
        this.f3481e.add(new n("lastModifiedTime", "item_type_long"));
        this.f3481e.add(new n("type", "item_type_integer"));
        this.f3481e.add(new n("uuid", "item_type_text"));
        this.f3481e.add(new n("length", "item_type_integer"));
    }

    private boolean l() {
        long jE;
        try {
            jE = com.byd.data.common.i.c.e(new File(com.byd.data.common.g.a.i().h() + f()));
        } catch (Exception unused) {
            jE = 0;
        }
        boolean z = jE >= 31457280;
        com.byd.data.common.i.f.g(this.f3477a, "usedSize =" + jE + ", isFull =" + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n(int i2, int i3, List list, SQLiteDatabase sQLiteDatabase) {
        String str = "DELETE FROM " + this.f3478b + " WHERE name =?";
        while (i2 < i3) {
            File file = (File) list.get(i2);
            try {
                if (file.delete()) {
                    com.byd.data.common.i.f.b(this.f3477a, "delete file =" + file.getName());
                    sQLiteDatabase.execSQL(str, new String[]{file.getName()});
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void p(File file, long j, int i2, String str, SQLiteDatabase sQLiteDatabase) {
        c(sQLiteDatabase);
        sQLiteDatabase.execSQL(g(), new Object[]{file.getName(), file.getPath(), Long.valueOf(j), Integer.valueOf(i2), str, Long.valueOf(file.length()), Long.valueOf(System.currentTimeMillis())});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void r(File file, byte b2, SQLiteDatabase sQLiteDatabase) {
        String name = file.getName();
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strI = i(file, b2, jCurrentTimeMillis);
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", strI);
        contentValues.put("path", file.getParent() + File.separator + strI);
        contentValues.put("lastModifiedTime", Long.valueOf(jCurrentTimeMillis));
        sQLiteDatabase.update(this.f3478b, contentValues, "name =? ", new String[]{name});
        a(file, new File(file.getParent(), strI));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: s, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void t(String str, String[] strArr, com.byd.data.common.c.d.c cVar, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query(this.f3478b, null, str, strArr, null, null, "createData", x());
        if (cursorQuery == null || cursorQuery.getCount() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("name"));
            File file = new File(cursorQuery.getString(cursorQuery.getColumnIndex("path")));
            if (string.endsWith(".zip") & file.exists()) {
                arrayList.add(file);
            }
        }
        cursorQuery.close();
        if (arrayList.size() > 0) {
            cVar.a(arrayList);
        }
    }

    public void b(String str, String str2) {
        ArrayList arrayList = new ArrayList(4);
        arrayList.addAll(h(str));
        arrayList.addAll(h(str2));
        d(arrayList, 0, arrayList.size());
    }

    public void d(final List<File> list, final int i2, final int i3) {
        if (list.size() == 0 || list.size() < i2 + i3) {
            return;
        }
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3487a.n(i2, i3, list, (SQLiteDatabase) obj);
            }
        });
    }

    protected String f() {
        return "app_cloud";
    }

    protected String i(File file, byte b2, long j) {
        String name = file.getName();
        SQLiteDatabase sQLiteDatabase = o.f().f3533c;
        if (sQLiteDatabase == null) {
            return name.replace(".data", ".zip");
        }
        Cursor cursorQuery = sQLiteDatabase.query(this.f3478b, null, "name =? ", new String[]{name}, null, null, null);
        if (cursorQuery == null || !cursorQuery.moveToFirst()) {
            return name.replace(".data", ".zip");
        }
        long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("createData"));
        int i2 = cursorQuery.getInt(cursorQuery.getColumnIndex("type"));
        StringBuilder sb = new StringBuilder();
        sb.append(i2 == 1 ? "P" : "E");
        sb.append("-");
        sb.append(com.byd.data.common.g.a.i().f().i());
        sb.append("-");
        sb.append(j2);
        sb.append("-");
        sb.append(j);
        sb.append(".zip");
        return sb.toString();
    }

    public void k(final int i2, final File file, final long j, final String str) {
        if (file == null || !file.exists() || j < 0) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3491a.p(file, j, i2, str, (SQLiteDatabase) obj);
            }
        });
        com.byd.data.common.i.f.g(this.f3477a, "insertOrUpdate end cast =" + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public void u(final byte b2, final File file) {
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3484a.r(file, b2, (SQLiteDatabase) obj);
            }
        });
    }

    protected void v(final String str, final String[] strArr, final com.byd.data.common.c.d.c cVar) {
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.d
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3496a.t(str, strArr, cVar, (SQLiteDatabase) obj);
            }
        });
    }

    public void w(String str, com.byd.data.common.c.d.c cVar) {
        v("uuid =? ", new String[]{str}, cVar);
    }

    protected String x() {
        return String.valueOf(200);
    }
}
