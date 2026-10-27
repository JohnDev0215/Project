package com.byd.engineermode.db;

import androidx.room.RoomDatabase;

/* JADX INFO: compiled from: ElectricItemDao_Impl.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RoomDatabase f2305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final androidx.room.c<com.byd.engineermode.bean.b> f2306b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final androidx.room.b<com.byd.engineermode.bean.b> f2307c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final androidx.room.b<com.byd.engineermode.bean.b> f2308d;

    /* JADX INFO: compiled from: ElectricItemDao_Impl.java */
    class a extends androidx.room.c<com.byd.engineermode.bean.b> {
        a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "INSERT OR ABORT INTO `ElectricItem` (`id`,`name`) VALUES (nullif(?, 0),?)";
        }

        @Override // androidx.room.c
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, com.byd.engineermode.bean.b bVar) {
            fVar.g(1, bVar.a());
            if (bVar.b() == null) {
                fVar.m(2);
            } else {
                fVar.f(2, bVar.b());
            }
        }
    }

    /* JADX INFO: compiled from: ElectricItemDao_Impl.java */
    class b extends androidx.room.b<com.byd.engineermode.bean.b> {
        b(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "DELETE FROM `ElectricItem` WHERE `id` = ?";
        }

        @Override // androidx.room.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, com.byd.engineermode.bean.b bVar) {
            fVar.g(1, bVar.a());
        }
    }

    /* JADX INFO: compiled from: ElectricItemDao_Impl.java */
    class c extends androidx.room.b<com.byd.engineermode.bean.b> {
        c(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "UPDATE OR ABORT `ElectricItem` SET `id` = ?,`name` = ? WHERE `id` = ?";
        }

        @Override // androidx.room.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, com.byd.engineermode.bean.b bVar) {
            fVar.g(1, bVar.a());
            if (bVar.b() == null) {
                fVar.m(2);
            } else {
                fVar.f(2, bVar.b());
            }
            fVar.g(3, bVar.a());
        }
    }

    public g(RoomDatabase roomDatabase) {
        this.f2305a = roomDatabase;
        this.f2306b = new a(roomDatabase);
        this.f2307c = new b(roomDatabase);
        this.f2308d = new c(roomDatabase);
    }

    @Override // com.byd.engineermode.db.f
    public long a(com.byd.engineermode.bean.b bVar) {
        this.f2305a.b();
        this.f2305a.c();
        try {
            long jH = this.f2306b.h(bVar);
            this.f2305a.r();
            return jH;
        } finally {
            this.f2305a.g();
        }
    }
}
