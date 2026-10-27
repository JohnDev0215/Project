package com.byd.engineermode.db;

import android.content.Context;
import androidx.room.RoomDatabase;

/* JADX INFO: loaded from: classes.dex */
public abstract class TestDatabase extends RoomDatabase {
    private static TestDatabase l;
    static final androidx.room.q.a m = new a(1, 2);

    static class a extends androidx.room.q.a {
        a(int i, int i2) {
            super(i, i2);
        }

        @Override // androidx.room.q.a
        public void a(b.m.a.b bVar) {
            com.byd.engineermode.utils.a.a("Migration updateElectric");
            try {
                bVar.e("drop table ElectricBean");
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                bVar.e("drop table ElectricItem");
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
            bVar.e("CREATE TABLE IF NOT EXISTS `ElectricBean` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `type` INTEGER, `shortState` INTEGER, `currentState` INTEGER, `connectState` INTEGER, `brokenState` INTEGER, `testStatus` INTEGER, `supplier` TEXT, `parent_id` INTEGER NOT NULL)");
            bVar.e("CREATE TABLE IF NOT EXISTS `ElectricItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT)");
        }
    }

    public static synchronized TestDatabase w(Context context) {
        if (l == null) {
            l = (TestDatabase) androidx.room.i.a(context.getApplicationContext(), TestDatabase.class, "my_db").a(m).b();
        }
        return l;
    }

    public abstract b s();

    public abstract d t();

    public abstract f u();

    public abstract h v();
}
