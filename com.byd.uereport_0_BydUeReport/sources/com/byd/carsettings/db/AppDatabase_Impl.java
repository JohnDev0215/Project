package com.byd.carsettings.db;

import androidx.room.RoomDatabase;
import d1.g;
import d1.k;
import f1.c;
import f1.e;
import h1.b;
import h1.c;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class AppDatabase_Impl extends AppDatabase {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile f3.a f2681l;

    public class a extends k.a {
        public a(int i8) {
            super(i8);
        }

        @Override // d1.k.a
        public void a(b bVar) {
            bVar.k("CREATE TABLE IF NOT EXISTS `EnergyCon` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` INTEGER NOT NULL, `mile` INTEGER NOT NULL, `avgConsume` REAL NOT NULL, `time` INTEGER NOT NULL, `string0` TEXT, `string1` TEXT, `int0` INTEGER NOT NULL, `int1` INTEGER NOT NULL, `double0` REAL NOT NULL, `double1` REAL NOT NULL)");
            bVar.k("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            bVar.k("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'b18fb461fcfc01301a7776dfa9d71421')");
        }

        @Override // d1.k.a
        public void b(b bVar) {
            bVar.k("DROP TABLE IF EXISTS `EnergyCon`");
            if (AppDatabase_Impl.this.f1742h != null) {
                int size = AppDatabase_Impl.this.f1742h.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((RoomDatabase.b) AppDatabase_Impl.this.f1742h.get(i8)).b(bVar);
                }
            }
        }

        @Override // d1.k.a
        public void c(b bVar) {
            if (AppDatabase_Impl.this.f1742h != null) {
                int size = AppDatabase_Impl.this.f1742h.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((RoomDatabase.b) AppDatabase_Impl.this.f1742h.get(i8)).a(bVar);
                }
            }
        }

        @Override // d1.k.a
        public void d(b bVar) {
            AppDatabase_Impl.this.f1735a = bVar;
            AppDatabase_Impl.this.m(bVar);
            if (AppDatabase_Impl.this.f1742h != null) {
                int size = AppDatabase_Impl.this.f1742h.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((RoomDatabase.b) AppDatabase_Impl.this.f1742h.get(i8)).c(bVar);
                }
            }
        }

        @Override // d1.k.a
        public void e(b bVar) {
        }

        @Override // d1.k.a
        public void f(b bVar) {
            c.a(bVar);
        }

        @Override // d1.k.a
        public k.b g(b bVar) {
            HashMap map = new HashMap(11);
            map.put("id", new e.a("id", "INTEGER", true, 1, null, 1));
            map.put("type", new e.a("type", "INTEGER", true, 0, null, 1));
            map.put("mile", new e.a("mile", "INTEGER", true, 0, null, 1));
            map.put("avgConsume", new e.a("avgConsume", "REAL", true, 0, null, 1));
            map.put("time", new e.a("time", "INTEGER", true, 0, null, 1));
            map.put("string0", new e.a("string0", "TEXT", false, 0, null, 1));
            map.put("string1", new e.a("string1", "TEXT", false, 0, null, 1));
            map.put("int0", new e.a("int0", "INTEGER", true, 0, null, 1));
            map.put("int1", new e.a("int1", "INTEGER", true, 0, null, 1));
            map.put("double0", new e.a("double0", "REAL", true, 0, null, 1));
            map.put("double1", new e.a("double1", "REAL", true, 0, null, 1));
            e eVar = new e("EnergyCon", map, new HashSet(0), new HashSet(0));
            e eVarA = e.a(bVar, "EnergyCon");
            if (eVar.equals(eVarA)) {
                return new k.b(true, null);
            }
            return new k.b(false, "EnergyCon(com.byd.carsettings.db.model.EnergyBean).\n Expected:\n" + eVar + "\n Found:\n" + eVarA);
        }
    }

    @Override // androidx.room.RoomDatabase
    public g e() {
        return new g(this, new HashMap(0), new HashMap(0), "EnergyCon");
    }

    @Override // androidx.room.RoomDatabase
    public h1.c f(d1.a aVar) {
        return aVar.f4334a.a(c.b.a(aVar.f4335b).c(aVar.f4336c).b(new k(aVar, new a(1), "b18fb461fcfc01301a7776dfa9d71421", "51308b5e5c5379f296ffd36162af9e10")).a());
    }

    @Override // com.byd.carsettings.db.AppDatabase
    public f3.a s() {
        f3.a aVar;
        if (this.f2681l != null) {
            return this.f2681l;
        }
        synchronized (this) {
            if (this.f2681l == null) {
                this.f2681l = new f3.b(this);
            }
            aVar = this.f2681l;
        }
        return aVar;
    }
}
