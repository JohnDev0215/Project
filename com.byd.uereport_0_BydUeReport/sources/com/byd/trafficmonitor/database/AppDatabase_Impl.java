package com.byd.trafficmonitor.database;

import androidx.room.RoomDatabase;
import d1.g;
import d1.k;
import f1.c;
import f1.e;
import h1.c;
import java.util.HashMap;
import java.util.HashSet;
import v4.b;

/* JADX INFO: loaded from: classes2.dex */
public final class AppDatabase_Impl extends AppDatabase {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile b f3023l;

    public class a extends k.a {
        public a(int i8) {
            super(i8);
        }

        @Override // d1.k.a
        public void a(h1.b bVar) {
            bVar.k("CREATE TABLE IF NOT EXISTS `traffic_data` (`package_name` TEXT, `app_name` TEXT, `uid` INTEGER NOT NULL, `is_system_app` INTEGER NOT NULL, `enable_network` INTEGER NOT NULL, `mobile_rx_byte` INTEGER NOT NULL, `mobile_tx_byte` INTEGER NOT NULL, PRIMARY KEY(`uid`))");
            bVar.k("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            bVar.k("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '12aec1c4c98ad1e0426bad8fbc34fb44')");
        }

        @Override // d1.k.a
        public void b(h1.b bVar) {
            bVar.k("DROP TABLE IF EXISTS `traffic_data`");
            if (AppDatabase_Impl.this.f1742h != null) {
                int size = AppDatabase_Impl.this.f1742h.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((RoomDatabase.b) AppDatabase_Impl.this.f1742h.get(i8)).b(bVar);
                }
            }
        }

        @Override // d1.k.a
        public void c(h1.b bVar) {
            if (AppDatabase_Impl.this.f1742h != null) {
                int size = AppDatabase_Impl.this.f1742h.size();
                for (int i8 = 0; i8 < size; i8++) {
                    ((RoomDatabase.b) AppDatabase_Impl.this.f1742h.get(i8)).a(bVar);
                }
            }
        }

        @Override // d1.k.a
        public void d(h1.b bVar) {
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
        public void e(h1.b bVar) {
        }

        @Override // d1.k.a
        public void f(h1.b bVar) {
            c.a(bVar);
        }

        @Override // d1.k.a
        public k.b g(h1.b bVar) {
            HashMap map = new HashMap(7);
            map.put("package_name", new e.a("package_name", "TEXT", false, 0, null, 1));
            map.put("app_name", new e.a("app_name", "TEXT", false, 0, null, 1));
            map.put("uid", new e.a("uid", "INTEGER", true, 1, null, 1));
            map.put("is_system_app", new e.a("is_system_app", "INTEGER", true, 0, null, 1));
            map.put("enable_network", new e.a("enable_network", "INTEGER", true, 0, null, 1));
            map.put("mobile_rx_byte", new e.a("mobile_rx_byte", "INTEGER", true, 0, null, 1));
            map.put("mobile_tx_byte", new e.a("mobile_tx_byte", "INTEGER", true, 0, null, 1));
            e eVar = new e("traffic_data", map, new HashSet(0), new HashSet(0));
            e eVarA = e.a(bVar, "traffic_data");
            if (eVar.equals(eVarA)) {
                return new k.b(true, null);
            }
            return new k.b(false, "traffic_data(com.byd.trafficmonitor.model.TrafficInfo).\n Expected:\n" + eVar + "\n Found:\n" + eVarA);
        }
    }

    @Override // androidx.room.RoomDatabase
    public g e() {
        return new g(this, new HashMap(0), new HashMap(0), "traffic_data");
    }

    @Override // androidx.room.RoomDatabase
    public h1.c f(d1.a aVar) {
        return aVar.f4334a.a(c.b.a(aVar.f4335b).c(aVar.f4336c).b(new k(aVar, new a(1), "12aec1c4c98ad1e0426bad8fbc34fb44", "78899cc42fa34faaf2e62e009115b615")).a());
    }

    @Override // com.byd.trafficmonitor.database.AppDatabase
    public b s() {
        b bVar;
        if (this.f3023l != null) {
            return this.f3023l;
        }
        synchronized (this) {
            if (this.f3023l == null) {
                this.f3023l = new v4.c(this);
            }
            bVar = this.f3023l;
        }
        return bVar;
    }
}
