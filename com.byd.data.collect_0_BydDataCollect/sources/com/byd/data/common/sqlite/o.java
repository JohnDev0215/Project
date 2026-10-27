package com.byd.data.common.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.byd.data.common.energy.TravelBean;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

/* JADX INFO: compiled from: SQLiteManager.java */
/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a */
    private static o f3531a;

    /* JADX INFO: renamed from: c */
    public SQLiteDatabase f3533c;

    /* JADX INFO: renamed from: d */
    private Context f3534d;

    /* JADX INFO: renamed from: e */
    private DBOpenHelper f3535e;

    /* JADX INFO: renamed from: f */
    private String f3536f;

    /* JADX INFO: renamed from: b */
    private final String f3532b = "SQLiteManager";

    /* JADX INFO: renamed from: g */
    private final int f3537g = 4;

    /* JADX INFO: renamed from: h */
    private List<Table> f3538h = new ArrayList();

    /* JADX INFO: renamed from: i */
    private ReentrantLock f3539i = new ReentrantLock();

    private o() {
    }

    private void a() {
        SQLiteDatabase sQLiteDatabase = this.f3533c;
        if (sQLiteDatabase == null) {
            n();
            return;
        }
        sQLiteDatabase.setTransactionSuccessful();
        this.f3533c.endTransaction();
        SQLiteDatabase sQLiteDatabase2 = this.f3533c;
        if (sQLiteDatabase2 != null) {
            sQLiteDatabase2.close();
            this.f3533c = null;
        }
        n();
    }

    private void b(Consumer<SQLiteDatabase> consumer) {
        if (this.f3533c == null) {
            return;
        }
        File file = new File(this.f3533c.getPath());
        com.byd.data.common.i.f.g("SQLiteManager", "database path =" + this.f3533c.getPath());
        if (file.exists()) {
            return;
        }
        try {
            if (file.createNewFile()) {
                j(consumer);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static o f() {
        if (f3531a == null) {
            synchronized (o.class) {
                if (f3531a == null) {
                    f3531a = new o();
                }
            }
        }
        return f3531a;
    }

    private void n() {
        int holdCount;
        ReentrantLock reentrantLock = this.f3539i;
        if (reentrantLock != null && (holdCount = reentrantLock.getHoldCount()) >= 1) {
            k("unLock hold=" + holdCount);
            try {
                this.f3539i.unlock();
            } catch (IllegalMonitorStateException unused) {
                com.byd.data.common.i.f.b("SQLiteManager", "IllegalMonitorStateException crash");
            }
        }
    }

    public BmsFileTable c() {
        if (this.f3538h.isEmpty()) {
            i(this.f3534d);
        }
        return (BmsFileTable) this.f3538h.get(2);
    }

    public CloudFileTable d() {
        if (this.f3538h.isEmpty()) {
            i(this.f3534d);
        }
        Table tableG = g(CloudFileTable.class);
        if (tableG instanceof CloudFileTable) {
            return (CloudFileTable) tableG;
        }
        return null;
    }

    public FileTable e() {
        if (this.f3538h.isEmpty()) {
            i(this.f3534d);
        }
        Table tableG = g(FileTable.class);
        if (tableG instanceof FileTable) {
            return (FileTable) tableG;
        }
        return null;
    }

    public <T extends Table> T g(Class<? extends Table> cls) {
        if (this.f3538h.isEmpty()) {
            return null;
        }
        int size = this.f3538h.size();
        for (int i2 = 0; i2 < size; i2++) {
            T t = (T) this.f3538h.get(i2);
            if (t.getClass().isAssignableFrom(cls)) {
                return t;
            }
        }
        return null;
    }

    public p h() {
        if (this.f3538h.isEmpty()) {
            i(this.f3534d);
        }
        Table tableG = g(p.class);
        if (tableG instanceof p) {
            return (p) tableG;
        }
        return null;
    }

    public synchronized void i(Context context) {
        if (!this.f3538h.isEmpty()) {
            this.f3538h.clear();
        }
        l(new FileTable());
        l(new CloudFileTable());
        l(new BmsFileTable());
        m(new p());
        this.f3534d = context;
        this.f3536f = "data_pick.db";
    }

    public void j(Consumer<SQLiteDatabase> consumer) {
        ReentrantLock reentrantLock = this.f3539i;
        if (reentrantLock == null) {
            return;
        }
        try {
            try {
                if (!reentrantLock.tryLock(500L, TimeUnit.MILLISECONDS)) {
                    com.byd.data.common.i.f.k("SQLiteManager", "database tryLock time out");
                    j(consumer);
                }
                k("open tryLock success");
                DBOpenHelper dBOpenHelper = new DBOpenHelper(this.f3534d, this.f3536f, null, 4, this.f3538h);
                this.f3535e = dBOpenHelper;
                SQLiteDatabase writableDatabase = dBOpenHelper.getWritableDatabase();
                this.f3533c = writableDatabase;
                if (writableDatabase != null) {
                    writableDatabase.beginTransaction();
                    consumer.accept(this.f3533c);
                }
            } catch (SQLiteException e2) {
                e2.printStackTrace();
                b(consumer);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
        } finally {
            a();
        }
    }

    public void k(String str) {
        if (com.byd.data.common.server.b.e().o()) {
            com.byd.data.common.i.f.b("SQLiteManager", str);
        }
    }

    public void l(Table<com.byd.data.common.e.b> table) {
        this.f3538h.add(table);
    }

    public void m(Table<TravelBean> table) {
        this.f3538h.add(table);
    }
}
