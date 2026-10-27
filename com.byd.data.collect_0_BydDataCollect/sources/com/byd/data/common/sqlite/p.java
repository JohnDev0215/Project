package com.byd.data.common.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.byd.data.common.energy.TravelBean;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: compiled from: TravelTable.java */
/* JADX INFO: loaded from: classes.dex */
public class p extends Table<TravelBean> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f3540h = "type";

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f3541i = "flag";
    public final String j = "startTime";
    public final String k = "endTime";
    public final String l = "mileage";
    public final String m = "elecCons";
    public final String n = "oilCons";
    public final String o = "string0";
    public final String p = "string1";
    public final String q = "int0";
    public final String r = "int1";
    public final String s = "float0";
    public final String t = "float1";
    public final String u = "long0";
    public final String v = "long1";

    public p() {
        h();
    }

    private void h() {
        this.f3478b = "TravelTable";
        this.f3481e.add(new n("type", "item_type_integer"));
        this.f3481e.add(new n("flag", "item_type_integer"));
        this.f3481e.add(new n("startTime", "item_type_long"));
        this.f3481e.add(new n("endTime", "item_type_long"));
        this.f3481e.add(new n("mileage", "item_type_float"));
        this.f3481e.add(new n("elecCons", "item_type_float"));
        this.f3481e.add(new n("oilCons", "item_type_float"));
        this.f3481e.add(new n("string0", "item_type_text"));
        this.f3481e.add(new n("string1", "item_type_text"));
        this.f3481e.add(new n("int0", "item_type_integer"));
        this.f3481e.add(new n("int1", "item_type_integer"));
        this.f3481e.add(new n("float0", "item_type_float"));
        this.f3481e.add(new n("float1", "item_type_float"));
        this.f3481e.add(new n("long0", "item_type_long"));
        this.f3481e.add(new n("long1", "item_type_long"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void j(ContentValues contentValues, SQLiteDatabase sQLiteDatabase) {
        long jInsert = sQLiteDatabase.insert(this.f3478b, null, contentValues);
        if (jInsert == -1) {
            com.byd.data.common.i.f.d(this.f3477a, "Failed to insert record");
            return;
        }
        com.byd.data.common.i.f.g(this.f3477a, "Inserted record with ID: " + jInsert);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void l(SQLiteDatabase sQLiteDatabase) {
        int iDelete = sQLiteDatabase.delete(this.f3478b, null, null);
        com.byd.data.common.i.f.g(this.f3477a, "Deleted " + iDelete + " records from table: " + this.f3478b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n(ContentValues contentValues, StringBuilder sb, String[] strArr, long[] jArr, SQLiteDatabase sQLiteDatabase) {
        int iUpdate = sQLiteDatabase.update(this.f3478b, contentValues, sb.toString(), strArr);
        com.byd.data.common.i.f.g(this.f3477a, "Updated " + iUpdate + " records with ids: " + jArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: o, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void p(String str, String[] strArr, long j, SQLiteDatabase sQLiteDatabase) {
        int iDelete = sQLiteDatabase.delete(this.f3478b, str, strArr);
        com.byd.data.common.i.f.g(this.f3477a, "Deleted " + iDelete + " records before " + j + " from table: " + this.f3478b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void r(long j, long j2, int i2, int i3, List list, SQLiteDatabase sQLiteDatabase) {
        String str;
        String[] strArr = {String.valueOf(!com.byd.data.common.energy.b.b() ? 1 : 0), String.valueOf(0), String.valueOf(j), String.valueOf(j2)};
        if (i2 != 0) {
            str = i3 + " , " + i2;
        } else {
            str = null;
        }
        Cursor cursorQuery = sQLiteDatabase.query(this.f3478b, null, "type = ? AND flag = ? AND endTime >= ? AND endTime < ?", strArr, null, null, "endTime DESC", str);
        try {
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0) {
                        while (cursorQuery.moveToNext()) {
                            TravelBean travelBean = new TravelBean();
                            travelBean.C(cursorQuery.getLong(cursorQuery.getColumnIndex(this.f3482f)));
                            travelBean.M(cursorQuery.getInt(cursorQuery.getColumnIndex("type")));
                            travelBean.J(cursorQuery.getLong(cursorQuery.getColumnIndex("startTime")));
                            travelBean.z(cursorQuery.getLong(cursorQuery.getColumnIndex("endTime")));
                            travelBean.H(cursorQuery.getFloat(cursorQuery.getColumnIndex("mileage")));
                            travelBean.y(cursorQuery.getFloat(cursorQuery.getColumnIndex("elecCons")));
                            travelBean.I(cursorQuery.getFloat(cursorQuery.getColumnIndex("oilCons")));
                            travelBean.K(cursorQuery.getString(cursorQuery.getColumnIndex("string0")));
                            travelBean.L(cursorQuery.getString(cursorQuery.getColumnIndex("string1")));
                            travelBean.D(cursorQuery.getInt(cursorQuery.getColumnIndex("int0")));
                            travelBean.E(cursorQuery.getInt(cursorQuery.getColumnIndex("int1")));
                            travelBean.A(cursorQuery.getFloat(cursorQuery.getColumnIndex("float0")));
                            travelBean.B(cursorQuery.getFloat(cursorQuery.getColumnIndex("float1")));
                            travelBean.F(cursorQuery.getLong(cursorQuery.getColumnIndex("long0")));
                            travelBean.G(cursorQuery.getLong(cursorQuery.getColumnIndex("long1")));
                            list.add(travelBean);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        } finally {
            cursorQuery.close();
        }
    }

    public void a(TravelBean travelBean) {
        if (travelBean == null) {
            return;
        }
        final ContentValues contentValuesE = e(travelBean);
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.l
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3521a.j(contentValuesE, (SQLiteDatabase) obj);
            }
        });
    }

    public void b() {
        try {
            com.byd.data.common.i.f.g(this.f3477a, "[EnergyCollect]---- Deleted table " + this.f3478b);
            o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.j
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f3516a.l((SQLiteDatabase) obj);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
            com.byd.data.common.i.f.d(this.f3477a, "[EnergyCollect]---- Deleted table is error " + e2.getMessage());
        }
    }

    public void c(final long[] jArr) {
        if (jArr == null) {
            return;
        }
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append(this.f3482f);
            sb.append(" IN (");
            for (int i2 = 0; i2 < jArr.length; i2++) {
                sb.append("?");
                if (i2 < jArr.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append(")");
            final String[] strArr = new String[jArr.length];
            for (int i3 = 0; i3 < jArr.length; i3++) {
                strArr[i3] = String.valueOf(jArr[i3]);
            }
            final ContentValues contentValues = new ContentValues();
            contentValues.put("flag", (Integer) 1);
            o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.i
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f3511a.n(contentValues, sb, strArr, jArr, (SQLiteDatabase) obj);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void d() {
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(jCurrentTimeMillis);
            calendar.set(5, 1);
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.add(2, -7);
            final long timeInMillis = calendar.getTimeInMillis();
            final String str = "endTime < ?";
            final String[] strArr = {String.valueOf(timeInMillis)};
            o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.k
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f3517a.p(str, strArr, timeInMillis, (SQLiteDatabase) obj);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    protected ContentValues e(TravelBean travelBean) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", Integer.valueOf(travelBean.x()));
        contentValues.put("flag", Integer.valueOf(travelBean.d()));
        contentValues.put("startTime", Long.valueOf(travelBean.u()));
        contentValues.put("endTime", Long.valueOf(travelBean.c()));
        contentValues.put("mileage", Float.valueOf(travelBean.s()));
        contentValues.put("elecCons", Float.valueOf(travelBean.a()));
        contentValues.put("oilCons", Float.valueOf(travelBean.t()));
        contentValues.put("string0", travelBean.v());
        contentValues.put("string1", travelBean.w());
        contentValues.put("int0", Integer.valueOf(travelBean.l()));
        contentValues.put("int1", Integer.valueOf(travelBean.p()));
        contentValues.put("float0", Float.valueOf(travelBean.f()));
        contentValues.put("float1", Float.valueOf(travelBean.j()));
        contentValues.put("long0", Long.valueOf(travelBean.q()));
        contentValues.put("long1", Long.valueOf(travelBean.r()));
        return contentValues;
    }

    public List<TravelBean> f(long j, long j2) {
        return g(j, j2, 0, 0);
    }

    public List<TravelBean> g(final long j, final long j2, final int i2, final int i3) {
        final ArrayList arrayList = new ArrayList();
        o.f().j(new Consumer() { // from class: com.byd.data.common.sqlite.m
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f3523a.r(j, j2, i3, i2, arrayList, (SQLiteDatabase) obj);
            }
        });
        return arrayList;
    }
}
