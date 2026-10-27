package p;

import android.database.Cursor;
import com.google.common.base.Optional;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ef implements qk0 {
    public final kk0 a;
    public final wg1 b;
    public final wc1 c;
    public final nk0 d;
    public final ri0 e;

    public ef(kk0 kk0Var, wg1 wg1Var, gj0 gj0Var, wc1 wc1Var, ri0 ri0Var) {
        this.a = kk0Var;
        this.b = wg1Var;
        this.d = gj0Var;
        this.c = wc1Var;
        this.e = ri0Var;
    }

    @Override // p.qk0
    public final void a(LinkedHashSet linkedHashSet, ArrayList arrayList) {
        this.a.n(linkedHashSet);
    }

    @Override // p.qk0
    public final l13 get() throws Throwable {
        m52 m52Var;
        Boolean boolValueOf;
        Optional optionalA = ((te) this.e).a();
        boolean z = optionalA.d() && !((String) optionalA.c()).isEmpty();
        ok0 ok0Var = ok0.f;
        if (!z) {
            return ok0Var;
        }
        String str = (String) optionalA.c();
        kk0 kk0Var = this.a;
        kk0Var.getClass();
        String strA = si0.a(str);
        m52 m52VarI = m52.i(3, "SELECT * FROM Events WHERE CASE WHEN authenticated IS NULL THEN 1 ELSE authenticated END = 1 AND  CASE WHEN owner IS NULL THEN ? ELSE owner END IS ? ORDER BY id ASC LIMIT ?");
        if (strA == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, strA);
        }
        if (strA == null) {
            m52VarI.K(2);
        } else {
            m52VarI.x(2, strA);
        }
        m52VarI.h0(3, 20);
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
                ok0 ok0Var2 = ok0Var;
                fi0Var.a = cursorO.getLong(iR);
                Integer numValueOf = cursorO.isNull(iR2) ? null : Integer.valueOf(cursorO.getInt(iR2));
                if (numValueOf == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                }
                fi0Var.b = boolValueOf;
                if (cursorO.isNull(iR3)) {
                    fi0Var.c = null;
                } else {
                    fi0Var.c = cursorO.getString(iR3);
                }
                if (cursorO.isNull(iR4)) {
                    fi0Var.d = null;
                } else {
                    fi0Var.d = cursorO.getBlob(iR4);
                }
                m52Var = m52VarI;
                try {
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
                    ok0Var = ok0Var2;
                    m52VarI = m52Var;
                } catch (Throwable th) {
                    th = th;
                    cursorO.close();
                    m52Var.q();
                    throw th;
                }
            }
            ok0 ok0Var3 = ok0Var;
            cursorO.close();
            m52VarI.q();
            if (arrayList.size() <= 0) {
                return ok0Var3;
            }
            ((u11) this.c).v(arrayList.size() + " Authenticated events to be sent to server: " + new tz2(" ", 1).a(com.google.common.collect.a.a(arrayList).e(new lj(3))));
            com.google.common.collect.a aVarA = com.google.common.collect.a.a(arrayList);
            wg1 wg1Var = this.b;
            Objects.requireNonNull(wg1Var);
            return new pk0(this.d, aVarA.e(new df(wg1Var, 0)).c());
        } catch (Throwable th2) {
            th = th2;
            m52Var = m52VarI;
        }
    }
}
