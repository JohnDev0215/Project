package f3;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import com.byd.carsettings.db.model.EnergyBean;
import d1.l;
import d1.o;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: EnergyConBeanDao_Impl.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements f3.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final RoomDatabase f4935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d1.c<EnergyBean> f4936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o f4937c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final o f4938d;

    /* JADX INFO: compiled from: EnergyConBeanDao_Impl.java */
    public class a extends d1.c<EnergyBean> {
        public a(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "INSERT OR ABORT INTO `EnergyCon` (`id`,`type`,`mile`,`avgConsume`,`time`,`string0`,`string1`,`int0`,`int1`,`double0`,`double1`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
        }

        @Override // d1.c
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(h1.f fVar, EnergyBean energyBean) {
            fVar.U(1, energyBean.i());
            fVar.U(2, energyBean.p());
            fVar.U(3, energyBean.l());
            fVar.A(4, energyBean.f());
            fVar.U(5, energyBean.o());
            if (energyBean.m() == null) {
                fVar.z(6);
            } else {
                fVar.l(6, energyBean.m());
            }
            if (energyBean.n() == null) {
                fVar.z(7);
            } else {
                fVar.l(7, energyBean.n());
            }
            fVar.U(8, energyBean.j());
            fVar.U(9, energyBean.k());
            fVar.A(10, energyBean.g());
            fVar.A(11, energyBean.h());
        }
    }

    /* JADX INFO: renamed from: f3.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: EnergyConBeanDao_Impl.java */
    public class C0040b extends o {
        public C0040b(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "DELETE FROM EnergyCon";
        }
    }

    /* JADX INFO: compiled from: EnergyConBeanDao_Impl.java */
    public class c extends o {
        public c(RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // d1.o
        public String d() {
            return "DELETE FROM EnergyCon WHERE type = ? and time <= ?";
        }
    }

    public b(RoomDatabase roomDatabase) {
        this.f4935a = roomDatabase;
        this.f4936b = new a(roomDatabase);
        this.f4937c = new C0040b(roomDatabase);
        this.f4938d = new c(roomDatabase);
    }

    @Override // f3.a
    public int a() {
        this.f4935a.b();
        h1.f fVarA = this.f4937c.a();
        this.f4935a.c();
        try {
            int iS = fVarA.s();
            this.f4935a.r();
            return iS;
        } finally {
            this.f4935a.g();
            this.f4937c.f(fVarA);
        }
    }

    @Override // f3.a
    public List<EnergyBean> b(int i8, int i9) {
        l lVarI = l.i("SELECT * FROM EnergyCon WHERE type = ? ORDER BY time DESC LIMIT ?", 2);
        lVarI.U(1, i8);
        lVarI.U(2, i9);
        this.f4935a.b();
        Cursor cursorB = f1.c.b(this.f4935a, lVarI, false, null);
        try {
            int iB = f1.b.b(cursorB, "id");
            int iB2 = f1.b.b(cursorB, "type");
            int iB3 = f1.b.b(cursorB, "mile");
            int iB4 = f1.b.b(cursorB, "avgConsume");
            int iB5 = f1.b.b(cursorB, "time");
            int iB6 = f1.b.b(cursorB, "string0");
            int iB7 = f1.b.b(cursorB, "string1");
            int iB8 = f1.b.b(cursorB, "int0");
            int iB9 = f1.b.b(cursorB, "int1");
            int iB10 = f1.b.b(cursorB, "double0");
            int iB11 = f1.b.b(cursorB, "double1");
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                EnergyBean energyBean = new EnergyBean();
                ArrayList arrayList2 = arrayList;
                energyBean.t(cursorB.getLong(iB));
                energyBean.A(cursorB.getInt(iB2));
                energyBean.w(cursorB.getInt(iB3));
                energyBean.q(cursorB.getDouble(iB4));
                energyBean.z(cursorB.getLong(iB5));
                energyBean.x(cursorB.getString(iB6));
                energyBean.y(cursorB.getString(iB7));
                energyBean.u(cursorB.getInt(iB8));
                energyBean.v(cursorB.getInt(iB9));
                energyBean.r(cursorB.getDouble(iB10));
                iB11 = iB11;
                int i10 = iB;
                energyBean.s(cursorB.getDouble(iB11));
                arrayList2.add(energyBean);
                arrayList = arrayList2;
                iB = i10;
            }
            return arrayList;
        } finally {
            cursorB.close();
            lVarI.n();
        }
    }

    @Override // f3.a
    public void c(EnergyBean energyBean) {
        this.f4935a.b();
        this.f4935a.c();
        try {
            this.f4936b.h(energyBean);
            this.f4935a.r();
        } finally {
            this.f4935a.g();
        }
    }

    @Override // f3.a
    public void d(int i8, long j8) {
        this.f4935a.b();
        h1.f fVarA = this.f4938d.a();
        fVarA.U(1, i8);
        fVarA.U(2, j8);
        this.f4935a.c();
        try {
            fVarA.s();
            this.f4935a.r();
        } finally {
            this.f4935a.g();
            this.f4938d.f(fVarA);
        }
    }

    @Override // f3.a
    public List<EnergyBean> e(int i8, int i9) {
        l lVarI = l.i("SELECT * FROM EnergyCon WHERE type = ? ORDER BY time ASC LIMIT ?", 2);
        lVarI.U(1, i8);
        lVarI.U(2, i9);
        this.f4935a.b();
        Cursor cursorB = f1.c.b(this.f4935a, lVarI, false, null);
        try {
            int iB = f1.b.b(cursorB, "id");
            int iB2 = f1.b.b(cursorB, "type");
            int iB3 = f1.b.b(cursorB, "mile");
            int iB4 = f1.b.b(cursorB, "avgConsume");
            int iB5 = f1.b.b(cursorB, "time");
            int iB6 = f1.b.b(cursorB, "string0");
            int iB7 = f1.b.b(cursorB, "string1");
            int iB8 = f1.b.b(cursorB, "int0");
            int iB9 = f1.b.b(cursorB, "int1");
            int iB10 = f1.b.b(cursorB, "double0");
            int iB11 = f1.b.b(cursorB, "double1");
            ArrayList arrayList = new ArrayList(cursorB.getCount());
            while (cursorB.moveToNext()) {
                EnergyBean energyBean = new EnergyBean();
                ArrayList arrayList2 = arrayList;
                energyBean.t(cursorB.getLong(iB));
                energyBean.A(cursorB.getInt(iB2));
                energyBean.w(cursorB.getInt(iB3));
                energyBean.q(cursorB.getDouble(iB4));
                energyBean.z(cursorB.getLong(iB5));
                energyBean.x(cursorB.getString(iB6));
                energyBean.y(cursorB.getString(iB7));
                energyBean.u(cursorB.getInt(iB8));
                energyBean.v(cursorB.getInt(iB9));
                energyBean.r(cursorB.getDouble(iB10));
                iB11 = iB11;
                int i10 = iB;
                energyBean.s(cursorB.getDouble(iB11));
                arrayList2.add(energyBean);
                arrayList = arrayList2;
                iB = i10;
            }
            return arrayList;
        } finally {
            cursorB.close();
            lVarI.n();
        }
    }

    @Override // f3.a
    public int f(int i8) {
        l lVarI = l.i("SELECT COUNT(*) FROM EnergyCon WHERE type = ?", 1);
        lVarI.U(1, i8);
        this.f4935a.b();
        Cursor cursorB = f1.c.b(this.f4935a, lVarI, false, null);
        try {
            return cursorB.moveToFirst() ? cursorB.getInt(0) : 0;
        } finally {
            cursorB.close();
            lVarI.n();
        }
    }
}
