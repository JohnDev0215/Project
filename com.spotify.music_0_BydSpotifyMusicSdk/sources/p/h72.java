package p;

import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class h72 {
    public static final /* synthetic */ int a = 0;

    static {
        vc1.d("Schedulers");
    }

    public static void a(my2 my2Var, zb0 zb0Var, ArrayList arrayList) {
        if (arrayList.size() > 0) {
            zb0Var.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                my2Var.i(((ky2) it.next()).a, jCurrentTimeMillis);
            }
        }
    }

    public static void b(sw swVar, WorkDatabase workDatabase, List list) {
        if (list == null || list.size() == 0) {
            return;
        }
        my2 my2VarV = workDatabase.v();
        workDatabase.c();
        try {
            ArrayList arrayListC = my2VarV.c();
            a(my2VarV, swVar.c, arrayListC);
            ArrayList arrayListB = my2VarV.b(swVar.j);
            a(my2VarV, swVar.c, arrayListB);
            arrayListB.addAll(arrayListC);
            ArrayList arrayListA = my2VarV.a();
            workDatabase.o();
            workDatabase.k();
            if (arrayListB.size() > 0) {
                ky2[] ky2VarArr = (ky2[]) arrayListB.toArray(new ky2[arrayListB.size()]);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    b72 b72Var = (b72) it.next();
                    if (b72Var.d()) {
                        b72Var.e(ky2VarArr);
                    }
                }
            }
            if (arrayListA.size() > 0) {
                ky2[] ky2VarArr2 = (ky2[]) arrayListA.toArray(new ky2[arrayListA.size()]);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    b72 b72Var2 = (b72) it2.next();
                    if (!b72Var2.d()) {
                        b72Var2.e(ky2VarArr2);
                    }
                }
            }
        } catch (Throwable th) {
            workDatabase.k();
            throw th;
        }
    }
}
