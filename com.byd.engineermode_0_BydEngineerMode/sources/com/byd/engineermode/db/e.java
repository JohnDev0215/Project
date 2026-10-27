package com.byd.engineermode.db;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.l;
import com.byd.engineermode.bean.ElectricBean;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ElectricContactDao_Impl.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final RoomDatabase f2304a;

    public e(RoomDatabase roomDatabase) {
        this.f2304a = roomDatabase;
    }

    private void c(b.d.d<ArrayList<ElectricBean>> dVar) {
        b.d.d<ArrayList<ElectricBean>> dVar2 = dVar;
        if (dVar.h()) {
            return;
        }
        if (dVar.m() > 999) {
            b.d.d<ArrayList<ElectricBean>> dVar3 = new b.d.d<>(999);
            int iM = dVar.m();
            int i = 0;
            int i2 = 0;
            while (i < iM) {
                dVar3.j(dVar2.i(i), dVar2.n(i));
                i++;
                i2++;
                if (i2 == 999) {
                    c(dVar3);
                    dVar3 = new b.d.d<>(999);
                    i2 = 0;
                }
            }
            if (i2 > 0) {
                c(dVar3);
                return;
            }
            return;
        }
        StringBuilder sbB = androidx.room.r.e.b();
        sbB.append("SELECT `id`,`name`,`type`,`shortState`,`currentState`,`connectState`,`brokenState`,`testStatus`,`supplier`,`parent_id` FROM `ElectricBean` WHERE `parent_id` IN (");
        int iM2 = dVar.m();
        androidx.room.r.e.a(sbB, iM2);
        sbB.append(")");
        l lVarV = l.v(sbB.toString(), iM2 + 0);
        int i3 = 1;
        for (int i4 = 0; i4 < dVar.m(); i4++) {
            lVarV.g(i3, dVar2.i(i4));
            i3++;
        }
        Cursor cursorB = androidx.room.r.c.b(this.f2304a, lVarV, false, null);
        try {
            int iB = androidx.room.r.b.b(cursorB, "parent_id");
            int i5 = -1;
            if (iB == -1) {
                return;
            }
            int iB2 = androidx.room.r.b.b(cursorB, "id");
            int iB3 = androidx.room.r.b.b(cursorB, "name");
            int iB4 = androidx.room.r.b.b(cursorB, "type");
            int iB5 = androidx.room.r.b.b(cursorB, "shortState");
            int iB6 = androidx.room.r.b.b(cursorB, "currentState");
            int iB7 = androidx.room.r.b.b(cursorB, "connectState");
            int iB8 = androidx.room.r.b.b(cursorB, "brokenState");
            int iB9 = androidx.room.r.b.b(cursorB, "testStatus");
            int iB10 = androidx.room.r.b.b(cursorB, "supplier");
            int iB11 = androidx.room.r.b.b(cursorB, "parent_id");
            while (cursorB.moveToNext()) {
                if (cursorB.isNull(iB)) {
                    dVar2 = dVar;
                } else {
                    int i6 = iB2;
                    ArrayList<ElectricBean> arrayListE = dVar2.e(cursorB.getLong(iB));
                    if (arrayListE != null) {
                        ElectricBean electricBean = new ElectricBean(iB3 == i5 ? null : cursorB.getString(iB3));
                        iB2 = i6;
                        int i7 = -1;
                        if (iB2 != -1) {
                            electricBean.n(cursorB.getLong(iB2));
                            i7 = -1;
                        }
                        if (iB4 != i7) {
                            electricBean.s(a.c(cursorB.getInt(iB4)));
                            i7 = -1;
                        }
                        if (iB5 != i7) {
                            electricBean.p(a.b(cursorB.getInt(iB5)));
                            i7 = -1;
                        }
                        if (iB6 != i7) {
                            electricBean.m(a.b(cursorB.getInt(iB6)));
                            i7 = -1;
                        }
                        if (iB7 != i7) {
                            electricBean.l(a.b(cursorB.getInt(iB7)));
                            i7 = -1;
                        }
                        if (iB8 != i7) {
                            electricBean.k(a.b(cursorB.getInt(iB8)));
                            i7 = -1;
                        }
                        if (iB9 != i7) {
                            electricBean.r(a.b(cursorB.getInt(iB9)));
                            i7 = -1;
                        }
                        if (iB10 != i7) {
                            electricBean.q(cursorB.getString(iB10));
                            i7 = -1;
                        }
                        if (iB11 != i7) {
                            electricBean.o(cursorB.getLong(iB11));
                        }
                        arrayListE.add(electricBean);
                    } else {
                        iB2 = i6;
                    }
                    dVar2 = dVar;
                    i5 = -1;
                }
            }
        } finally {
            cursorB.close();
        }
    }

    @Override // com.byd.engineermode.db.d
    public List<com.byd.engineermode.bean.a> a() {
        com.byd.engineermode.bean.b bVar;
        l lVarV = l.v("SELECT * FROM electricItem", 0);
        this.f2304a.b();
        this.f2304a.c();
        try {
            Cursor cursorB = androidx.room.r.c.b(this.f2304a, lVarV, true, null);
            try {
                int iC = androidx.room.r.b.c(cursorB, "id");
                int iC2 = androidx.room.r.b.c(cursorB, "name");
                b.d.d<ArrayList<ElectricBean>> dVar = new b.d.d<>();
                while (cursorB.moveToNext()) {
                    if (!cursorB.isNull(iC)) {
                        long j = cursorB.getLong(iC);
                        if (dVar.e(j) == null) {
                            dVar.j(j, new ArrayList<>());
                        }
                    }
                }
                cursorB.moveToPosition(-1);
                c(dVar);
                ArrayList arrayList = new ArrayList(cursorB.getCount());
                while (cursorB.moveToNext()) {
                    if (cursorB.isNull(iC) && cursorB.isNull(iC2)) {
                        bVar = null;
                    } else {
                        bVar = new com.byd.engineermode.bean.b();
                        bVar.c(cursorB.getLong(iC));
                        bVar.d(cursorB.getString(iC2));
                    }
                    ArrayList<ElectricBean> arrayListE = !cursorB.isNull(iC) ? dVar.e(cursorB.getLong(iC)) : null;
                    if (arrayListE == null) {
                        arrayListE = new ArrayList<>();
                    }
                    com.byd.engineermode.bean.a aVar = new com.byd.engineermode.bean.a();
                    aVar.d(bVar);
                    aVar.e(arrayListE);
                    arrayList.add(aVar);
                }
                this.f2304a.r();
                return arrayList;
            } finally {
                cursorB.close();
                lVarV.y();
            }
        } finally {
            this.f2304a.g();
        }
    }
}
