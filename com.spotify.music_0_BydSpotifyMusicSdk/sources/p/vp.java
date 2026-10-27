package p;

import androidx.work.WorkInfo$State;
import androidx.work.impl.WorkDatabase;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public abstract class vp implements Runnable {
    public final x42 c = new x42(2);

    public static void a(wx2 wx2Var, String str) {
        vy2 vy2VarB;
        WorkDatabase workDatabase = wx2Var.i;
        my2 my2VarV = workDatabase.v();
        g90 g90VarQ = workDatabase.q();
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            WorkInfo$State workInfo$StateF = my2VarV.f(str2);
            if (workInfo$StateF != WorkInfo$State.k && workInfo$StateF != WorkInfo$State.l) {
                i52 i52Var = my2VarV.a;
                i52Var.b();
                ly2 ly2Var = my2VarV.e;
                th2 th2VarA = ly2Var.a();
                if (str2 == null) {
                    th2VarA.K(1);
                } else {
                    th2VarA.x(1, str2);
                }
                i52Var.c();
                try {
                    th2VarA.F();
                    i52Var.o();
                } finally {
                    i52Var.k();
                    ly2Var.c(th2VarA);
                }
            }
            linkedList.addAll(g90VarQ.g(str2));
        }
        qx1 qx1Var = wx2Var.l;
        synchronized (qx1Var.k) {
            vc1.c().getClass();
            qx1Var.i.add(str);
            vy2VarB = qx1Var.b(str);
        }
        qx1.d(vy2VarB, 1);
        Iterator it = wx2Var.k.iterator();
        while (it.hasNext()) {
            ((b72) it.next()).a(str);
        }
    }

    public abstract void b();

    @Override // java.lang.Runnable
    public final void run() {
        x42 x42Var = this.c;
        try {
            b();
            x42Var.f(uq1.i);
        } catch (Throwable th) {
            x42Var.f(new qq1(th));
        }
    }
}
