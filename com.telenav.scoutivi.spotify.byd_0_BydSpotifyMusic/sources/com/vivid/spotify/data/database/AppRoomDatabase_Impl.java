package com.vivid.spotify.data.database;

import a1.a;
import b1.c;
import c4.b;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import y0.d;
import y0.f;
import y0.h;

/* JADX INFO: loaded from: classes.dex */
public final class AppRoomDatabase_Impl extends AppRoomDatabase {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private volatile b f5253i;

    class a extends h.a {
        a(int i7) {
            super(i7);
        }

        @Override // y0.h.a
        public void a(b1.b bVar) {
            bVar.o("CREATE TABLE IF NOT EXISTS `search_keywords` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `keyword` TEXT, `remark` TEXT, `user` TEXT, `update_time` INTEGER NOT NULL, `create_time` INTEGER NOT NULL)");
            bVar.o("CREATE UNIQUE INDEX `index_search_keywords_keyword` ON `search_keywords` (`keyword`)");
            bVar.o("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
            bVar.o("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e9ce2bb16cb2133b929809cd1b9ebd72\")");
        }

        @Override // y0.h.a
        public void b(b1.b bVar) {
            bVar.o("DROP TABLE IF EXISTS `search_keywords`");
        }

        @Override // y0.h.a
        protected void c(b1.b bVar) {
            if (((f) AppRoomDatabase_Impl.this).f9926g != null) {
                int size = ((f) AppRoomDatabase_Impl.this).f9926g.size();
                for (int i7 = 0; i7 < size; i7++) {
                    ((f.b) ((f) AppRoomDatabase_Impl.this).f9926g.get(i7)).a(bVar);
                }
            }
        }

        @Override // y0.h.a
        public void d(b1.b bVar) {
            ((f) AppRoomDatabase_Impl.this).f9920a = bVar;
            AppRoomDatabase_Impl.this.l(bVar);
            if (((f) AppRoomDatabase_Impl.this).f9926g != null) {
                int size = ((f) AppRoomDatabase_Impl.this).f9926g.size();
                for (int i7 = 0; i7 < size; i7++) {
                    ((f.b) ((f) AppRoomDatabase_Impl.this).f9926g.get(i7)).b(bVar);
                }
            }
        }

        @Override // y0.h.a
        protected void e(b1.b bVar) {
            HashMap map = new HashMap(6);
            map.put("id", new a.C0000a("id", "INTEGER", true, 1));
            map.put("keyword", new a.C0000a("keyword", "TEXT", false, 0));
            map.put("remark", new a.C0000a("remark", "TEXT", false, 0));
            map.put("user", new a.C0000a("user", "TEXT", false, 0));
            map.put("update_time", new a.C0000a("update_time", "INTEGER", true, 0));
            map.put("create_time", new a.C0000a("create_time", "INTEGER", true, 0));
            HashSet hashSet = new HashSet(0);
            HashSet hashSet2 = new HashSet(1);
            hashSet2.add(new a.d("index_search_keywords_keyword", true, Arrays.asList("keyword")));
            a1.a aVar = new a1.a("search_keywords", map, hashSet, hashSet2);
            a1.a aVarA = a1.a.a(bVar, "search_keywords");
            if (aVar.equals(aVarA)) {
                return;
            }
            throw new IllegalStateException("Migration didn't properly handle search_keywords(com.vivid.spotify.data.searchKeywords.SearchKeyword).\n Expected:\n" + aVar + "\n Found:\n" + aVarA);
        }
    }

    @Override // y0.f
    protected d d() {
        return new d(this, "search_keywords");
    }

    @Override // y0.f
    protected c e(y0.a aVar) {
        return aVar.f9887a.a(c.b.a(aVar.f9888b).c(aVar.f9889c).b(new h(aVar, new a(1), "e9ce2bb16cb2133b929809cd1b9ebd72", "d7fbf66d2302dad87165ed718fbbcafc")).a());
    }

    @Override // com.vivid.spotify.data.database.AppRoomDatabase
    public b r() {
        b bVar;
        if (this.f5253i != null) {
            return this.f5253i;
        }
        synchronized (this) {
            if (this.f5253i == null) {
                this.f5253i = new c4.c(this);
            }
            bVar = this.f5253i;
        }
        return bVar;
    }
}
