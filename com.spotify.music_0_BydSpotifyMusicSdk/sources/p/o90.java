package p;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public abstract class o90 {
    public static final /* synthetic */ int a = 0;

    static {
        v03.i(vc1.d("DiagnosticsWrkr"), "tagWithPrefix(\"DiagnosticsWrkr\")");
    }

    public static final void a(ay2 ay2Var, oy2 oy2Var, ii2 ii2Var, ArrayList arrayList) {
        StringBuilder sb = new StringBuilder("\n Id \t Class Name\t Job Id\t State\t Unique Name\t Tags\t");
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ky2 ky2Var = (ky2) it.next();
            hi2 hi2VarA = ((cy2) ii2Var).a(ci1.u(ky2Var));
            Integer numValueOf = hi2VarA != null ? Integer.valueOf(hi2VarA.c) : null;
            ay2Var.getClass();
            m52 m52VarI = m52.i(1, "SELECT name FROM workname WHERE work_spec_id=?");
            String str = ky2Var.a;
            if (str == null) {
                m52VarI.K(1);
            } else {
                m52VarI.x(1, str);
            }
            ((i52) ay2Var.c).b();
            Cursor cursorO = p02.o((i52) ay2Var.c, m52VarI);
            try {
                ArrayList arrayList2 = new ArrayList(cursorO.getCount());
                while (cursorO.moveToNext()) {
                    arrayList2.add(cursorO.isNull(0) ? null : cursorO.getString(0));
                }
                cursorO.close();
                m52VarI.q();
                String strH0 = ou.h0(arrayList2, ",", null, null, null, 62);
                String strH02 = ou.h0(((f02) oy2Var).b(str), ",", null, null, null, 62);
                StringBuilder sbP = u4.p("\n", str, "\t ");
                sbP.append(ky2Var.c);
                sbP.append("\t ");
                sbP.append(numValueOf);
                sbP.append("\t ");
                sbP.append(ky2Var.b.name());
                sbP.append("\t ");
                sbP.append(strH0);
                sbP.append("\t ");
                sbP.append(strH02);
                sbP.append('\t');
                sb.append(sbP.toString());
            } catch (Throwable th) {
                cursorO.close();
                m52VarI.q();
                throw th;
            }
        }
        v03.i(sb.toString(), "StringBuilder().apply(builderAction).toString()");
    }
}
