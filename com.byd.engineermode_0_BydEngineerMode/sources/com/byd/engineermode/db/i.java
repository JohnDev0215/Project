package com.byd.engineermode.db;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.l;
import com.byd.engineermode.bean.FunctionBean;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FunctionDao_Impl.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RoomDatabase f2312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final androidx.room.c<FunctionBean> f2313b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final androidx.room.b<FunctionBean> f2314c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final androidx.room.b<FunctionBean> f2315d;

    /* JADX INFO: compiled from: FunctionDao_Impl.java */
    class a extends androidx.room.c<FunctionBean> {
        a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "INSERT OR ABORT INTO `FunctionBean` (`id`,`name`,`state`) VALUES (nullif(?, 0),?,?)";
        }

        @Override // androidx.room.c
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, FunctionBean functionBean) {
            fVar.g(1, functionBean.f());
            if (functionBean.g() == null) {
                fVar.m(2);
            } else {
                fVar.f(2, functionBean.g());
            }
            fVar.g(3, com.byd.engineermode.db.a.a(functionBean.h()));
        }
    }

    /* JADX INFO: compiled from: FunctionDao_Impl.java */
    class b extends androidx.room.b<FunctionBean> {
        b(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "DELETE FROM `FunctionBean` WHERE `id` = ?";
        }

        @Override // androidx.room.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, FunctionBean functionBean) {
            fVar.g(1, functionBean.f());
        }
    }

    /* JADX INFO: compiled from: FunctionDao_Impl.java */
    class c extends androidx.room.b<FunctionBean> {
        c(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.o
        public String d() {
            return "UPDATE OR ABORT `FunctionBean` SET `id` = ?,`name` = ?,`state` = ? WHERE `id` = ?";
        }

        @Override // androidx.room.b
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b.m.a.f fVar, FunctionBean functionBean) {
            fVar.g(1, functionBean.f());
            if (functionBean.g() == null) {
                fVar.m(2);
            } else {
                fVar.f(2, functionBean.g());
            }
            fVar.g(3, com.byd.engineermode.db.a.a(functionBean.h()));
            fVar.g(4, functionBean.f());
        }
    }

    public i(RoomDatabase roomDatabase) {
        this.f2312a = roomDatabase;
        this.f2313b = new a(roomDatabase);
        this.f2314c = new b(roomDatabase);
        this.f2315d = new c(roomDatabase);
    }

    @Override // com.byd.engineermode.db.h
    public long a(FunctionBean functionBean) {
        this.f2312a.b();
        this.f2312a.c();
        try {
            long jH = this.f2313b.h(functionBean);
            this.f2312a.r();
            return jH;
        } finally {
            this.f2312a.g();
        }
    }

    @Override // com.byd.engineermode.db.h
    public void b(FunctionBean functionBean) {
        this.f2312a.b();
        this.f2312a.c();
        try {
            this.f2315d.h(functionBean);
            this.f2312a.r();
        } finally {
            this.f2312a.g();
        }
    }

    @Override // com.byd.engineermode.db.h
    public List<FunctionBean> c() {
        l lVarV = l.v("SELECT * FROM functionbean", 0);
        this.f2312a.b();
        Cursor cursorB = androidx.room.r.c.b(this.f2312a, lVarV, false, null);
        try {
            int iC = androidx.room.r.b.c(cursorB, "id");
            int iC2 = androidx.room.r.b.c(cursorB, "name");
            int iC3 = androidx.room.r.b.c(cursorB, "state");
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                FunctionBean functionBean = new FunctionBean(cursorB.getString(iC2));
                functionBean.i(cursorB.getLong(iC));
                functionBean.j(com.byd.engineermode.db.a.b(cursorB.getInt(iC3)));
                arrayList.add(functionBean);
            }
            return arrayList;
        } finally {
            cursorB.close();
            lVarV.y();
        }
    }
}
