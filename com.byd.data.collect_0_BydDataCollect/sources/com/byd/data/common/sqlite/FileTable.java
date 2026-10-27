package com.byd.data.common.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class FileTable extends Table<com.byd.data.common.e.b> {

    /* JADX INFO: renamed from: h */
    public final String f3475h = "createData";

    /* JADX INFO: renamed from: i */
    public final String f3476i = "isGb";
    public final String j = "length";
    private final String k = "createData <? ";
    private final String l = "createData =? ";

    public FileTable() {
        e();
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (g()) {
            p(sQLiteDatabase);
        }
        com.byd.data.common.i.f.g(this.f3477a, "deleteExpire cast time =" + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    private String d() {
        return "INSERT OR REPLACE INTO " + this.f3478b + " (name, path, createData, isGb, length) VALUES (?, ?, ?, ?, ?)";
    }

    private void e() {
        this.f3478b = getClass().getSimpleName();
        this.f3481e.add(new n("name", "item_type_text"));
        this.f3481e.add(new n("path", "item_type_text"));
        this.f3481e.add(new n("createData", "item_type_integer"));
        this.f3481e.add(new n("isGb", "item_type_boolean"));
        this.f3481e.add(new n("length", "item_type_integer"));
    }

    private boolean g() {
        StatFs statFs = new StatFs("data_acquisition");
        statFs.getBlockSizeLong();
        long blockCountLong = statFs.getBlockCountLong();
        long availableBlocksLong = statFs.getAvailableBlocksLong();
        boolean z = (10 * availableBlocksLong) / blockCountLong <= 3;
        com.byd.data.common.i.f.g(this.f3477a, "total =" + blockCountLong + ", avail =" + availableBlocksLong + ",notEnough =" + z);
        return z;
    }

    static /* synthetic */ int h(File file, File file2) {
        return (int) (file.lastModified() - file2.lastModified());
    }

    /* JADX INFO: renamed from: i */
    public /* synthetic */ void j(StringBuilder sb, String[] strArr, SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(this.f3478b, sb.toString(), strArr);
    }

    /* JADX INFO: renamed from: k */
    public /* synthetic */ void l(File file, long j, boolean z, SQLiteDatabase sQLiteDatabase) {
        c(sQLiteDatabase);
        sQLiteDatabase.execSQL(d(), new Object[]{file.getName(), file.getPath(), Long.valueOf(j / 1000), Boolean.valueOf(z), 0L});
    }

    /* JADX INFO: renamed from: m */
    public /* synthetic */ void n(boolean z, com.byd.data.common.c.d.b bVar, SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList(10);
        String str = this.f3478b;
        String str2 = "isGb =? ";
        String[] strArr = new String[1];
        strArr[0] = z ? "1" : "0";
        Cursor cursorQuery = sQLiteDatabase.query(str, null, str2, strArr, null, null, "createData");
        int length = 2;
        if (cursorQuery == null || cursorQuery.getCount() <= 0) {
            bVar.a(arrayList, 2);
            return;
        }
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("name"));
            length += string.length() + 6;
            arrayList.add(new com.byd.data.common.e.b(cursorQuery.getLong(cursorQuery.getColumnIndex("createData")), string, cursorQuery.getString(cursorQuery.getColumnIndex("path")), z, cursorQuery.getInt(cursorQuery.getColumnIndex("length"))));
        }
        cursorQuery.close();
        bVar.a(arrayList, length);
    }

    private void p(SQLiteDatabase sQLiteDatabase) {
        String strB = com.byd.data.common.i.g.b("gb_file_name_key");
        String strB2 = com.byd.data.common.i.g.b("gb_extra_file_name_key");
        Cursor cursorQuery = sQLiteDatabase.query(this.f3478b, null, null, null, null, null, "createData", "1");
        if (cursorQuery == null || cursorQuery.getCount() <= 0) {
            com.byd.data.common.i.f.g(this.f3477a, "queryExpireDb cursor is empty");
            return;
        }
        long j = 0;
        while (cursorQuery.moveToNext()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("name"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("path"));
            long j2 = cursorQuery.getLong(cursorQuery.getColumnIndex("createData"));
            if (com.byd.data.common.i.c.c(string2)) {
                if (TextUtils.equals(string, strB)) {
                    com.byd.data.common.i.g.f("gb_file_name_key");
                } else if (TextUtils.equals(string, strB2)) {
                    com.byd.data.common.i.g.f("gb_extra_file_name_key");
                }
                com.byd.data.common.i.f.g(this.f3477a, "deleteFile=" + string2 + ",deleteCreateData=" + j2);
                j = j2;
            }
        }
        cursorQuery.close();
        if (j > 0) {
            sQLiteDatabase.delete(this.f3478b, "createData =? ", new String[]{Long.toString(j)});
        }
    }

    public void a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        File dataDir = com.byd.data.common.g.a.i().d().getDataDir();
        File file = new File(dataDir, "app_gb_file");
        if (file.exists()) {
            StatFs statFs = new StatFs("data_acquisition");
            int availableBlocksLong = (int) ((statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong()) >>> 20);
            com.byd.data.common.i.f.g(this.f3477a, "asyncDeleteExpire avail =" + availableBlocksLong + "mb");
            if (availableBlocksLong > 256) {
                return;
            }
            File file2 = new File(dataDir, "app_gb_extension_file");
            File[] fileArrListFiles = file.listFiles();
            File[] fileArrListFiles2 = file2.listFiles();
            if (fileArrListFiles == null || fileArrListFiles2 == null) {
                return;
            }
            File[] fileArr = new File[fileArrListFiles.length + fileArrListFiles2.length];
            System.arraycopy(fileArrListFiles, 0, fileArr, 0, fileArrListFiles.length);
            System.arraycopy(fileArrListFiles2, 0, fileArr, fileArrListFiles.length, fileArrListFiles2.length);
            b((List) Arrays.stream(fileArr).sorted(new Comparator() { // from class: com.byd.data.common.sqlite.f
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return FileTable.h((File) obj, (File) obj2);
                }
            }).collect(Collectors.toList()));
            com.byd.data.common.i.f.g(this.f3477a, "asyncDeleteExpire consume=" + (System.currentTimeMillis() - jCurrentTimeMillis));
        }
    }

    protected void b(List<File> list) {
        ArrayList arrayList = new ArrayList((int) Math.ceil(26.666666666666668d));
        final StringBuilder sb = new StringBuilder("name IN (");
        int size = list.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            File file = list.get(i3);
            if (file != null && file.isFile()) {
                String name = file.getName();
                int length = (int) file.length();
                boolean zDelete = file.delete();
                com.byd.data.common.i.f.h(this.f3477a, "batchDelDb deleteRet= %b, file=%s", Boolean.valueOf(zDelete), name);
                if (zDelete) {
                    arrayList.add(name);
                    i2 += length;
                    sb.append("?, ");
                    if (arrayList.size() >= 20 && i2 >= 209715200) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        sb.append("?)");
        if (arrayList.size() > 0) {
            final String[] strArr = (String[]) arrayList.toArray(new String[0]);
            o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.h
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f3508a.j(sb, strArr, (SQLiteDatabase) obj);
                }
            });
        }
        com.byd.data.common.i.f.k(this.f3477a, "batchDelDb is end size=" + size + ",delSpc=" + i2);
    }

    public void f(final boolean z, final File file, final long j) {
        if (file == null || !file.exists() || j < 0) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.e
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3500a.l(file, j, z, (SQLiteDatabase) obj);
            }
        });
        com.byd.data.common.i.f.g(this.f3477a, "insertOrUpdate end cast =" + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public void o(final boolean z, final com.byd.data.common.c.d.b bVar) {
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.g
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3505a.n(z, bVar, (SQLiteDatabase) obj);
            }
        });
    }
}
