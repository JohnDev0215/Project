package com.byd.engineermode.db;

import androidx.room.RoomDatabase;
import com.byd.engineermode.bean.ElectricBean;

/* JADX INFO: compiled from: ElectricBeanDao_Impl.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements com.byd.engineermode.db.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RoomDatabase f2297a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final androidx.room.c<ElectricBean> f2298b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final androidx.room.b<ElectricBean> f2299c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final androidx.room.b<ElectricBean> f2300d;

    /* JADX INFO: compiled from: ElectricBeanDao_Impl.java */
    class a extends androidx.room.c<ElectricBean> {
        a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "INSERT OR ABORT INTO `ElectricBean` (`id`,`name`,`type`,`shortState`,`currentState`,`connectState`,`brokenState`,`testStatus`,`supplier`,`parent_id`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
        }

        @Override // androidx.room.c
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, ElectricBean electricBean) {
            fVar.g(1, electricBean.d());
            if (electricBean.e() == null) {
                fVar.m(2);
            } else {
                fVar.f(2, electricBean.e());
            }
            fVar.g(3, com.byd.engineermode.db.a.d(electricBean.j()));
            fVar.g(4, com.byd.engineermode.db.a.a(electricBean.g()));
            fVar.g(5, com.byd.engineermode.db.a.a(electricBean.c()));
            fVar.g(6, com.byd.engineermode.db.a.a(electricBean.b()));
            fVar.g(7, com.byd.engineermode.db.a.a(electricBean.a()));
            fVar.g(8, com.byd.engineermode.db.a.a(electricBean.i()));
            if (electricBean.h() == null) {
                fVar.m(9);
            } else {
                fVar.f(9, electricBean.h());
            }
            fVar.g(10, electricBean.f());
        }
    }

    /* JADX INFO: compiled from: ElectricBeanDao_Impl.java */
    class b extends androidx.room.b<ElectricBean> {
        b(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "DELETE FROM `ElectricBean` WHERE `id` = ?";
        }

        @Override // androidx.room.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, ElectricBean electricBean) {
            fVar.g(1, electricBean.d());
        }
    }

    /* JADX INFO: renamed from: com.byd.engineermode.db.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ElectricBeanDao_Impl.java */
    class C0067c extends androidx.room.b<ElectricBean> {
        C0067c(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "UPDATE OR ABORT `ElectricBean` SET `id` = ?,`name` = ?,`type` = ?,`shortState` = ?,`currentState` = ?,`connectState` = ?,`brokenState` = ?,`testStatus` = ?,`supplier` = ?,`parent_id` = ? WHERE `id` = ?";
        }

        @Override // androidx.room.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, ElectricBean electricBean) {
            fVar.g(1, electricBean.d());
            if (electricBean.e() == null) {
                fVar.m(2);
            } else {
                fVar.f(2, electricBean.e());
            }
            fVar.g(3, com.byd.engineermode.db.a.d(electricBean.j()));
            fVar.g(4, com.byd.engineermode.db.a.a(electricBean.g()));
            fVar.g(5, com.byd.engineermode.db.a.a(electricBean.c()));
            fVar.g(6, com.byd.engineermode.db.a.a(electricBean.b()));
            fVar.g(7, com.byd.engineermode.db.a.a(electricBean.a()));
            fVar.g(8, com.byd.engineermode.db.a.a(electricBean.i()));
            if (electricBean.h() == null) {
                fVar.m(9);
            } else {
                fVar.f(9, electricBean.h());
            }
            fVar.g(10, electricBean.f());
            fVar.g(11, electricBean.d());
        }
    }

    public c(RoomDatabase roomDatabase) {
        this.f2297a = roomDatabase;
        this.f2298b = new a(roomDatabase);
        this.f2299c = new b(roomDatabase);
        this.f2300d = new C0067c(roomDatabase);
    }

    @Override // com.byd.engineermode.db.b
    public long a(ElectricBean electricBean) {
        this.f2297a.b();
        this.f2297a.c();
        try {
            long jH = this.f2298b.h(electricBean);
            this.f2297a.r();
            return jH;
        } finally {
            this.f2297a.g();
        }
    }

    @Override // com.byd.engineermode.db.b
    public void b(ElectricBean electricBean) {
        this.f2297a.b();
        this.f2297a.c();
        try {
            this.f2300d.h(electricBean);
            this.f2297a.r();
        } finally {
            this.f2297a.g();
        }
    }
}
