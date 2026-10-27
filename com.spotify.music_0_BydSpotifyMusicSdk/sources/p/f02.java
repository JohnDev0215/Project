package p;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class f02 implements oy2 {
    public final i52 a;
    public final f90 b;
    public final py2 c;

    public f02(i52 i52Var, int i) {
        int i2 = 1;
        if (i != 1) {
            this.a = i52Var;
            this.b = new f90(this, i52Var, 9);
            this.c = new py2(this, i52Var, i2);
        } else {
            this.a = i52Var;
            this.b = new f90(this, i52Var, 6);
            this.c = new py2(this, i52Var, 0);
        }
    }

    public static void a(f02 f02Var, Map map) {
        v03.j(map, "report");
        for (Map.Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            int iIntValue = ((Number) entry.getValue()).intValue();
            m52 m52VarI = m52.i(1, "\n            SELECT *\n            FROM RateLimitedEvents\n            WHERE eventName = ?\n        ");
            if (str == null) {
                m52VarI.K(1);
            } else {
                m52VarI.x(1, str);
            }
            i52 i52Var = f02Var.a;
            i52Var.b();
            d02 d02Var = null;
            String string = null;
            Cursor cursorM = i52Var.m(m52VarI, null);
            try {
                int iR = f60.r(cursorM, "eventName");
                int iR2 = f60.r(cursorM, "count");
                int iR3 = f60.r(cursorM, "timestamp");
                if (cursorM.moveToFirst()) {
                    if (!cursorM.isNull(iR)) {
                        string = cursorM.getString(iR);
                    }
                    d02Var = new d02(cursorM.getInt(iR2), cursorM.getLong(iR3), string);
                }
                if (d02Var == null) {
                    d02 d02Var2 = new d02(iIntValue, TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()), str);
                    i52Var.b();
                    i52Var.c();
                    try {
                        f02Var.b.e(d02Var2);
                        i52Var.o();
                    } finally {
                        i52Var.k();
                    }
                } else if (d02Var.b != iIntValue) {
                    long seconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                    i52Var.b();
                    py2 py2Var = f02Var.c;
                    th2 th2VarA = py2Var.a();
                    th2VarA.h0(1, iIntValue);
                    th2VarA.h0(2, seconds);
                    if (str == null) {
                        th2VarA.K(3);
                    } else {
                        th2VarA.x(3, str);
                    }
                    i52Var.c();
                    try {
                        th2VarA.F();
                        i52Var.o();
                    } finally {
                        i52Var.k();
                        py2Var.c(th2VarA);
                    }
                } else {
                    continue;
                }
            } finally {
                cursorM.close();
                m52VarI.q();
            }
        }
    }

    public final ArrayList b(String str) {
        m52 m52VarI = m52.i(1, "SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?");
        if (str == null) {
            m52VarI.K(1);
        } else {
            m52VarI.x(1, str);
        }
        i52 i52Var = this.a;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            ArrayList arrayList = new ArrayList(cursorM.getCount());
            while (cursorM.moveToNext()) {
                arrayList.add(cursorM.isNull(0) ? null : cursorM.getString(0));
            }
            return arrayList;
        } finally {
            cursorM.close();
            m52VarI.q();
        }
    }
}
