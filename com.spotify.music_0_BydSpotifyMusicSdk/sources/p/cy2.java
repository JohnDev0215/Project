package p;

import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public final class cy2 implements ii2 {
    public final i52 a;
    public final f90 b;
    public final androidx.room.a c;
    public final androidx.room.a d;

    public cy2(i52 i52Var, int i) {
        if (i != 1) {
            this.a = i52Var;
            this.b = new f90(this, i52Var, 4);
            this.c = new by2(i52Var, 0);
            this.d = new by2(i52Var, 1);
            return;
        }
        this.a = i52Var;
        this.b = new f90(this, i52Var, 2);
        this.c = new ji2(i52Var, 0);
        this.d = new ji2(i52Var, 1);
    }

    public final hi2 a(rx2 rx2Var) {
        v03.j(rx2Var, "id");
        m52 m52VarI = m52.i(2, "SELECT * FROM SystemIdInfo WHERE work_spec_id=? AND generation=?");
        String str = rx2Var.a;
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        m52VarI.h0(2, rx2Var.b);
        i52 i52Var = this.a;
        i52Var.b();
        hi2 hi2Var = null;
        String string = null;
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            int iR = f60.r(cursorM, "work_spec_id");
            int iR2 = f60.r(cursorM, "generation");
            int iR3 = f60.r(cursorM, "system_id");
            if (cursorM.moveToFirst()) {
                if (!cursorM.isNull(iR)) {
                    string = cursorM.getString(iR);
                }
                hi2Var = new hi2(string, cursorM.getInt(iR2), cursorM.getInt(iR3));
            }
            return hi2Var;
        } finally {
            cursorM.close();
            m52VarI.q();
        }
    }

    public final void b(hi2 hi2Var) {
        i52 i52Var = this.a;
        i52Var.b();
        i52Var.c();
        try {
            this.b.e(hi2Var);
            i52Var.o();
        } finally {
            i52Var.k();
        }
    }
}
