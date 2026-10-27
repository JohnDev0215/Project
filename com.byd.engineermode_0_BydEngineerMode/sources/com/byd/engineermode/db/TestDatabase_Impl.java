package com.byd.engineermode.db;

import androidx.room.RoomDatabase;
import androidx.room.k;
import androidx.room.r.f;
import b.m.a.c;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class TestDatabase_Impl extends TestDatabase {
    private volatile h n;
    private volatile f o;
    private volatile b p;
    private volatile d q;

    class a extends k.a {
        a(int i) {
            super(i);
        }

        @Override // androidx.room.k.a
        public void a(b.m.a.b bVar) {
            bVar.e("CREATE TABLE IF NOT EXISTS `FunctionBean` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `state` INTEGER)");
            bVar.e("CREATE TABLE IF NOT EXISTS `ElectricItem` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT)");
            bVar.e("CREATE TABLE IF NOT EXISTS `ElectricBean` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `type` INTEGER, `shortState` INTEGER, `currentState` INTEGER, `connectState` INTEGER, `brokenState` INTEGER, `testStatus` INTEGER, `supplier` TEXT, `parent_id` INTEGER NOT NULL)");
            bVar.e("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            bVar.e("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '33878a2b55136a9899b8372ebc4b6cc8')");
        }

        @Override // androidx.room.k.a
        public void b(b.m.a.b bVar) {
            bVar.e("DROP TABLE IF EXISTS `FunctionBean`");
            bVar.e("DROP TABLE IF EXISTS `ElectricItem`");
            bVar.e("DROP TABLE IF EXISTS `ElectricBean`");
            if (((RoomDatabase) TestDatabase_Impl.this).h != null) {
                int size = ((RoomDatabase) TestDatabase_Impl.this).h.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.b) ((RoomDatabase) TestDatabase_Impl.this).h.get(i)).b(bVar);
                }
            }
        }

        @Override // androidx.room.k.a
        protected void c(b.m.a.b bVar) {
            if (((RoomDatabase) TestDatabase_Impl.this).h != null) {
                int size = ((RoomDatabase) TestDatabase_Impl.this).h.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.b) ((RoomDatabase) TestDatabase_Impl.this).h.get(i)).a(bVar);
                }
            }
        }

        @Override // androidx.room.k.a
        public void d(b.m.a.b bVar) {
            ((RoomDatabase) TestDatabase_Impl.this).f1307a = bVar;
            TestDatabase_Impl.this.m(bVar);
            if (((RoomDatabase) TestDatabase_Impl.this).h != null) {
                int size = ((RoomDatabase) TestDatabase_Impl.this).h.size();
                for (int i = 0; i < size; i++) {
                    ((RoomDatabase.b) ((RoomDatabase) TestDatabase_Impl.this).h.get(i)).c(bVar);
                }
            }
        }

        @Override // androidx.room.k.a
        public void e(b.m.a.b bVar) {
        }

        @Override // androidx.room.k.a
        public void f(b.m.a.b bVar) {
            androidx.room.r.c.a(bVar);
        }

        @Override // androidx.room.k.a
        protected k.b g(b.m.a.b bVar) {
            HashMap map = new HashMap(3);
            map.put("id", new f.a("id", "INTEGER", true, 1, null, 1));
            map.put("name", new f.a("name", "TEXT", false, 0, null, 1));
            map.put("state", new f.a("state", "INTEGER", false, 0, null, 1));
            androidx.room.r.f fVar = new androidx.room.r.f("FunctionBean", map, new HashSet(0), new HashSet(0));
            androidx.room.r.f fVarA = androidx.room.r.f.a(bVar, "FunctionBean");
            if (!fVar.equals(fVarA)) {
                return new k.b(false, "FunctionBean(com.byd.engineermode.bean.FunctionBean).\n Expected:\n" + fVar + "\n Found:\n" + fVarA);
            }
            HashMap map2 = new HashMap(2);
            map2.put("id", new f.a("id", "INTEGER", true, 1, null, 1));
            map2.put("name", new f.a("name", "TEXT", false, 0, null, 1));
            androidx.room.r.f fVar2 = new androidx.room.r.f("ElectricItem", map2, new HashSet(0), new HashSet(0));
            androidx.room.r.f fVarA2 = androidx.room.r.f.a(bVar, "ElectricItem");
            if (!fVar2.equals(fVarA2)) {
                return new k.b(false, "ElectricItem(com.byd.engineermode.bean.ElectricItem).\n Expected:\n" + fVar2 + "\n Found:\n" + fVarA2);
            }
            HashMap map3 = new HashMap(10);
            map3.put("id", new f.a("id", "INTEGER", true, 1, null, 1));
            map3.put("name", new f.a("name", "TEXT", false, 0, null, 1));
            map3.put("type", new f.a("type", "INTEGER", false, 0, null, 1));
            map3.put("shortState", new f.a("shortState", "INTEGER", false, 0, null, 1));
            map3.put("currentState", new f.a("currentState", "INTEGER", false, 0, null, 1));
            map3.put("connectState", new f.a("connectState", "INTEGER", false, 0, null, 1));
            map3.put("brokenState", new f.a("brokenState", "INTEGER", false, 0, null, 1));
            map3.put("testStatus", new f.a("testStatus", "INTEGER", false, 0, null, 1));
            map3.put("supplier", new f.a("supplier", "TEXT", false, 0, null, 1));
            map3.put("parent_id", new f.a("parent_id", "INTEGER", true, 0, null, 1));
            androidx.room.r.f fVar3 = new androidx.room.r.f("ElectricBean", map3, new HashSet(0), new HashSet(0));
            androidx.room.r.f fVarA3 = androidx.room.r.f.a(bVar, "ElectricBean");
            if (fVar3.equals(fVarA3)) {
                return new k.b(true, null);
            }
            return new k.b(false, "ElectricBean(com.byd.engineermode.bean.ElectricBean).\n Expected:\n" + fVar3 + "\n Found:\n" + fVarA3);
        }
    }

    @Override // androidx.room.RoomDatabase
    protected androidx.room.g e() {
        return new androidx.room.g(this, new HashMap(0), new HashMap(0), "FunctionBean", "ElectricItem", "ElectricBean");
    }

    @Override // androidx.room.RoomDatabase
    protected b.m.a.c f(androidx.room.a aVar) {
        return aVar.f1317a.a(c.b.a(aVar.f1318b).c(aVar.f1319c).b(new k(aVar, new a(1), "33878a2b55136a9899b8372ebc4b6cc8", "f93f4749b73d59d091fda366128223de")).a());
    }

    @Override // com.byd.engineermode.db.TestDatabase
    public b s() {
        b bVar;
        if (this.p != null) {
            return this.p;
        }
        synchronized (this) {
            if (this.p == null) {
                this.p = new c(this);
            }
            bVar = this.p;
        }
        return bVar;
    }

    @Override // com.byd.engineermode.db.TestDatabase
    public d t() {
        d dVar;
        if (this.q != null) {
            return this.q;
        }
        synchronized (this) {
            if (this.q == null) {
                this.q = new e(this);
            }
            dVar = this.q;
        }
        return dVar;
    }

    @Override // com.byd.engineermode.db.TestDatabase
    public f u() {
        f fVar;
        if (this.o != null) {
            return this.o;
        }
        synchronized (this) {
            if (this.o == null) {
                this.o = new g(this);
            }
            fVar = this.o;
        }
        return fVar;
    }

    @Override // com.byd.engineermode.db.TestDatabase
    public h v() {
        h hVar;
        if (this.n != null) {
            return this.n;
        }
        synchronized (this) {
            if (this.n == null) {
                this.n = new i(this);
            }
            hVar = this.n;
        }
        return hVar;
    }
}
