package p;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class qn1 implements qk0 {
    public final kk0 a;
    public final wg1 b;
    public final wc1 c;
    public final nk0 d;

    public qn1(kk0 kk0Var, wg1 wg1Var, gj0 gj0Var, wc1 wc1Var) {
        this.a = kk0Var;
        this.b = wg1Var;
        this.d = gj0Var;
        this.c = wc1Var;
    }

    @Override // p.qk0
    public final void a(LinkedHashSet linkedHashSet, ArrayList arrayList) {
        this.a.n(linkedHashSet);
    }

    @Override // p.qk0
    public final l13 get() {
        Boolean boolValueOf;
        kk0 kk0Var = this.a;
        kk0Var.getClass();
        m52 m52VarI = m52.i(2, "SELECT * FROM Events WHERE CASE WHEN authenticated IS NULL THEN 1 ELSE authenticated END = 0 ORDER BY id ASC LIMIT ? OFFSET ?");
        m52VarI.h0(1, 20);
        boolean z = false;
        m52VarI.h0(2, 0);
        ((i52) kk0Var.k).b();
        Cursor cursorO = p02.o((i52) kk0Var.k, m52VarI);
        try {
            int iR = f60.r(cursorO, "id");
            int iR2 = f60.r(cursorO, "authenticated");
            int iR3 = f60.r(cursorO, "eventName");
            int iR4 = f60.r(cursorO, "sequenceId");
            int iR5 = f60.r(cursorO, "sequenceNumber");
            int iR6 = f60.r(cursorO, "fragments");
            int iR7 = f60.r(cursorO, "owner");
            int iR8 = f60.r(cursorO, "deviceId");
            ArrayList arrayList = new ArrayList(cursorO.getCount());
            while (cursorO.moveToNext()) {
                fi0 fi0Var = new fi0();
                int i = iR3;
                fi0Var.a = cursorO.getLong(iR);
                Integer numValueOf = cursorO.isNull(iR2) ? null : Integer.valueOf(cursorO.getInt(iR2));
                if (numValueOf == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0 ? true : z);
                }
                fi0Var.b = boolValueOf;
                if (cursorO.isNull(i)) {
                    fi0Var.c = null;
                } else {
                    fi0Var.c = cursorO.getString(i);
                }
                if (cursorO.isNull(iR4)) {
                    fi0Var.d = null;
                } else {
                    fi0Var.d = cursorO.getBlob(iR4);
                }
                int i2 = iR4;
                fi0Var.e = cursorO.getLong(iR5);
                if (cursorO.isNull(iR6)) {
                    fi0Var.f = null;
                } else {
                    fi0Var.f = cursorO.getBlob(iR6);
                }
                if (cursorO.isNull(iR7)) {
                    fi0Var.g = null;
                } else {
                    fi0Var.g = cursorO.getString(iR7);
                }
                if (cursorO.isNull(iR8)) {
                    fi0Var.h = null;
                } else {
                    fi0Var.h = cursorO.getString(iR8);
                }
                arrayList.add(fi0Var);
                iR4 = i2;
                iR3 = i;
                z = false;
            }
            cursorO.close();
            m52VarI.q();
            if (arrayList.size() <= 0) {
                return ok0.f;
            }
            ((u11) this.c).v(arrayList.size() + " Non authenticated events to be sent to server: " + new tz2(" ", 1).a(com.google.common.collect.a.a(arrayList).e(new lj(7))));
            com.google.common.collect.a aVarA = com.google.common.collect.a.a(arrayList);
            wg1 wg1Var = this.b;
            Objects.requireNonNull(wg1Var);
            return new pk0(this.d, aVarA.e(new df(wg1Var, 1)).c());
        } catch (Throwable th) {
            cursorO.close();
            m52VarI.q();
            throw th;
        }
    }
}
