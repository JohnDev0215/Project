package p;

import android.content.Context;
import android.database.Cursor;
import androidx.work.WorkInfo$State;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class vy2 implements Runnable {
    public static final String z = vc1.d("WorkerWrapper");
    public final Context c;
    public final String e;
    public final ky2 k;
    public qb1 l;
    public final yx2 m;
    public final sw o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final zb0 f202p;
    public final gw0 q;
    public final WorkDatabase r;
    public final my2 s;
    public final g90 t;
    public final List u;
    public String v;
    public pb1 n = new mb1();
    public final androidx.work.impl.utils.futures.b w = new androidx.work.impl.utils.futures.b();
    public final androidx.work.impl.utils.futures.b x = new androidx.work.impl.utils.futures.b();
    public volatile int y = -256;

    public vy2(uy2 uy2Var) {
        this.c = (Context) uy2Var.a;
        this.m = (yx2) uy2Var.d;
        this.q = (gw0) uy2Var.c;
        ky2 ky2Var = (ky2) uy2Var.g;
        this.k = ky2Var;
        this.e = ky2Var.a;
        this.l = (qb1) uy2Var.b;
        sw swVar = (sw) uy2Var.e;
        this.o = swVar;
        this.f202p = swVar.c;
        WorkDatabase workDatabase = (WorkDatabase) uy2Var.f;
        this.r = workDatabase;
        this.s = workDatabase.v();
        this.t = workDatabase.q();
        this.u = (List) uy2Var.h;
    }

    public final void a(pb1 pb1Var) {
        boolean z2 = pb1Var instanceof ob1;
        ky2 ky2Var = this.k;
        if (!z2) {
            if (pb1Var instanceof nb1) {
                vc1.c().getClass();
                c();
                return;
            }
            vc1.c().getClass();
            if (ky2Var.c()) {
                d();
                return;
            } else {
                g();
                return;
            }
        }
        vc1.c().getClass();
        if (ky2Var.c()) {
            d();
            return;
        }
        g90 g90Var = this.t;
        String str = this.e;
        my2 my2Var = this.s;
        WorkDatabase workDatabase = this.r;
        workDatabase.c();
        try {
            my2Var.m(WorkInfo$State.k, str);
            my2Var.l(str, ((ob1) this.n).a);
            this.f202p.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis();
            for (String str2 : g90Var.g(str)) {
                if (my2Var.f(str2) == WorkInfo$State.m && g90Var.h(str2)) {
                    vc1.c().getClass();
                    my2Var.m(WorkInfo$State.c, str2);
                    my2Var.k(str2, jCurrentTimeMillis);
                }
            }
            workDatabase.o();
            workDatabase.k();
            e(false);
        } catch (Throwable th) {
            workDatabase.k();
            e(false);
            throw th;
        }
    }

    public final void b() {
        if (h()) {
            return;
        }
        this.r.c();
        try {
            WorkInfo$State workInfo$StateF = this.s.f(this.e);
            cy2 cy2VarU = this.r.u();
            String str = this.e;
            i52 i52Var = cy2VarU.a;
            i52Var.b();
            androidx.room.a aVar = cy2VarU.c;
            th2 th2VarA = aVar.a();
            if (str == null) {
                th2VarA.K(1);
            } else {
                th2VarA.x(1, str);
            }
            i52Var.c();
            try {
                th2VarA.F();
                i52Var.o();
                if (workInfo$StateF == null) {
                    e(false);
                } else if (workInfo$StateF == WorkInfo$State.e) {
                    a(this.n);
                } else if (!workInfo$StateF.a()) {
                    this.y = -512;
                    c();
                }
                this.r.o();
                this.r.k();
            } finally {
                i52Var.k();
                aVar.c(th2VarA);
            }
        } catch (Throwable th) {
            this.r.k();
            throw th;
        }
    }

    public final void c() {
        String str = this.e;
        my2 my2Var = this.s;
        WorkDatabase workDatabase = this.r;
        workDatabase.c();
        try {
            my2Var.m(WorkInfo$State.c, str);
            this.f202p.getClass();
            my2Var.k(str, System.currentTimeMillis());
            my2Var.j(this.k.v, str);
            my2Var.i(str, -1L);
            workDatabase.o();
        } finally {
            workDatabase.k();
            e(true);
        }
    }

    public final void d() {
        String str = this.e;
        my2 my2Var = this.s;
        WorkDatabase workDatabase = this.r;
        workDatabase.c();
        try {
            this.f202p.getClass();
            my2Var.k(str, System.currentTimeMillis());
            i52 i52Var = my2Var.a;
            my2Var.m(WorkInfo$State.c, str);
            i52Var.b();
            ly2 ly2Var = my2Var.j;
            th2 th2VarA = ly2Var.a();
            if (str == null) {
                th2VarA.K(1);
            } else {
                th2VarA.x(1, str);
            }
            i52Var.c();
            try {
                th2VarA.F();
                i52Var.o();
                i52Var.k();
                ly2Var.c(th2VarA);
                my2Var.j(this.k.v, str);
                i52Var.b();
                ly2 ly2Var2 = my2Var.f;
                th2 th2VarA2 = ly2Var2.a();
                if (str == null) {
                    th2VarA2.K(1);
                } else {
                    th2VarA2.x(1, str);
                }
                i52Var.c();
                try {
                    th2VarA2.F();
                    i52Var.o();
                    i52Var.k();
                    ly2Var2.c(th2VarA2);
                    my2Var.i(str, -1L);
                    workDatabase.o();
                } catch (Throwable th) {
                    i52Var.k();
                    ly2Var2.c(th2VarA2);
                    throw th;
                }
            } catch (Throwable th2) {
                i52Var.k();
                ly2Var.c(th2VarA);
                throw th2;
            }
        } finally {
            workDatabase.k();
            e(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(boolean r5) {
        /*
            r4 = this;
            androidx.work.impl.WorkDatabase r0 = r4.r
            r0.c()
            androidx.work.impl.WorkDatabase r0 = r4.r     // Catch: java.lang.Throwable -> L40
            p.my2 r0 = r0.v()     // Catch: java.lang.Throwable -> L40
            r0.getClass()     // Catch: java.lang.Throwable -> L40
            java.lang.String r1 = "SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1"
            r2 = 0
            p.m52 r1 = p.m52.i(r2, r1)     // Catch: java.lang.Throwable -> L40
            p.i52 r0 = r0.a     // Catch: java.lang.Throwable -> L40
            r0.b()     // Catch: java.lang.Throwable -> L40
            r3 = 0
            android.database.Cursor r0 = r0.m(r1, r3)     // Catch: java.lang.Throwable -> L40
            boolean r3 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L2d
            if (r3 == 0) goto L2f
            int r3 = r0.getInt(r2)     // Catch: java.lang.Throwable -> L2d
            if (r3 == 0) goto L2f
            r3 = 1
            goto L30
        L2d:
            r5 = move-exception
            goto L73
        L2f:
            r3 = r2
        L30:
            r0.close()     // Catch: java.lang.Throwable -> L40
            r1.q()     // Catch: java.lang.Throwable -> L40
            if (r3 != 0) goto L42
            android.content.Context r0 = r4.c     // Catch: java.lang.Throwable -> L40
            java.lang.Class<androidx.work.impl.background.systemalarm.RescheduleReceiver> r1 = androidx.work.impl.background.systemalarm.RescheduleReceiver.class
            p.ir1.a(r0, r1, r2)     // Catch: java.lang.Throwable -> L40
            goto L42
        L40:
            r5 = move-exception
            goto L7a
        L42:
            if (r5 == 0) goto L5f
            p.my2 r0 = r4.s     // Catch: java.lang.Throwable -> L40
            androidx.work.WorkInfo$State r1 = androidx.work.WorkInfo$State.c     // Catch: java.lang.Throwable -> L40
            java.lang.String r2 = r4.e     // Catch: java.lang.Throwable -> L40
            r0.m(r1, r2)     // Catch: java.lang.Throwable -> L40
            p.my2 r0 = r4.s     // Catch: java.lang.Throwable -> L40
            java.lang.String r1 = r4.e     // Catch: java.lang.Throwable -> L40
            int r2 = r4.y     // Catch: java.lang.Throwable -> L40
            r0.n(r2, r1)     // Catch: java.lang.Throwable -> L40
            p.my2 r0 = r4.s     // Catch: java.lang.Throwable -> L40
            java.lang.String r1 = r4.e     // Catch: java.lang.Throwable -> L40
            r2 = -1
            r0.i(r1, r2)     // Catch: java.lang.Throwable -> L40
        L5f:
            androidx.work.impl.WorkDatabase r0 = r4.r     // Catch: java.lang.Throwable -> L40
            r0.o()     // Catch: java.lang.Throwable -> L40
            androidx.work.impl.WorkDatabase r0 = r4.r
            r0.k()
            androidx.work.impl.utils.futures.b r0 = r4.w
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r0.j(r5)
            return
        L73:
            r0.close()     // Catch: java.lang.Throwable -> L40
            r1.q()     // Catch: java.lang.Throwable -> L40
            throw r5     // Catch: java.lang.Throwable -> L40
        L7a:
            androidx.work.impl.WorkDatabase r0 = r4.r
            r0.k()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p.vy2.e(boolean):void");
    }

    public final void f() {
        WorkInfo$State workInfo$StateF = this.s.f(this.e);
        if (workInfo$StateF == WorkInfo$State.e) {
            vc1.c().getClass();
            e(true);
        } else {
            vc1 vc1VarC = vc1.c();
            Objects.toString(workInfo$StateF);
            vc1VarC.getClass();
            e(false);
        }
    }

    public final void g() {
        String str = this.e;
        WorkDatabase workDatabase = this.r;
        workDatabase.c();
        try {
            LinkedList linkedList = new LinkedList();
            linkedList.add(str);
            while (true) {
                boolean zIsEmpty = linkedList.isEmpty();
                my2 my2Var = this.s;
                if (zIsEmpty) {
                    w50 w50Var = ((mb1) this.n).a;
                    my2Var.j(this.k.v, str);
                    my2Var.l(str, w50Var);
                    workDatabase.o();
                    return;
                }
                String str2 = (String) linkedList.remove();
                if (my2Var.f(str2) != WorkInfo$State.n) {
                    my2Var.m(WorkInfo$State.l, str2);
                }
                linkedList.addAll(this.t.g(str2));
            }
        } finally {
            workDatabase.k();
            e(false);
        }
    }

    public final boolean h() {
        if (this.y == -256) {
            return false;
        }
        vc1.c().getClass();
        if (this.s.f(this.e) == null) {
            e(false);
        } else {
            e(!r0.a());
        }
        return true;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z2;
        s41 s41Var;
        StringBuilder sb = new StringBuilder("Work [ id=");
        String str = this.e;
        sb.append(str);
        sb.append(", tags={ ");
        List<String> list = this.u;
        boolean z3 = true;
        for (String str2 : list) {
            if (z3) {
                z3 = false;
            } else {
                sb.append(", ");
            }
            sb.append(str2);
        }
        sb.append(" } ]");
        this.v = sb.toString();
        ky2 ky2Var = this.k;
        if (h()) {
            return;
        }
        WorkDatabase workDatabase = this.r;
        workDatabase.c();
        try {
            WorkInfo$State workInfo$State = ky2Var.b;
            WorkInfo$State workInfo$State2 = WorkInfo$State.c;
            if (workInfo$State == workInfo$State2) {
                boolean zC = ky2Var.c();
                String str3 = ky2Var.c;
                if (zC || (ky2Var.b == workInfo$State2 && ky2Var.k > 0)) {
                    this.f202p.getClass();
                    if (System.currentTimeMillis() < ky2Var.a()) {
                        vc1 vc1VarC = vc1.c();
                        String.format("Delaying execution for %s because it is being executed before schedule.", str3);
                        vc1VarC.getClass();
                        e(true);
                        workDatabase.o();
                    }
                }
                workDatabase.o();
                workDatabase.k();
                boolean zC2 = ky2Var.c();
                w50 w50VarA = ky2Var.e;
                my2 my2Var = this.s;
                sw swVar = this.o;
                String str4 = z;
                if (!zC2) {
                    swVar.e.getClass();
                    String str5 = ky2Var.d;
                    v03.j(str5, "className");
                    String str6 = t41.a;
                    try {
                        Object objNewInstance = Class.forName(str5).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                        v03.g(objNewInstance, "null cannot be cast to non-null type androidx.work.InputMerger");
                        s41Var = (s41) objNewInstance;
                    } catch (Exception e) {
                        vc1.c().b(t41.a, "Trouble instantiating ".concat(str5), e);
                        s41Var = null;
                    }
                    if (s41Var == null) {
                        vc1.c().a(str4, "Could not create Input Merger ".concat(str5));
                        g();
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(w50VarA);
                    my2Var.getClass();
                    m52 m52VarI = m52.i(1, "SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)");
                    if (str == null) {
                        m52VarI.K(1);
                    } else {
                        m52VarI.x(1, str);
                    }
                    i52 i52Var = my2Var.a;
                    i52Var.b();
                    Cursor cursorM = i52Var.m(m52VarI, null);
                    try {
                        ArrayList arrayList2 = new ArrayList(cursorM.getCount());
                        while (cursorM.moveToNext()) {
                            arrayList2.add(w50.a(cursorM.isNull(0) ? null : cursorM.getBlob(0)));
                        }
                        cursorM.close();
                        m52VarI.q();
                        arrayList.addAll(arrayList2);
                        w50VarA = s41Var.a(arrayList);
                    } catch (Throwable th) {
                        cursorM.close();
                        m52VarI.q();
                        throw th;
                    }
                }
                UUID uuidFromString = UUID.fromString(str);
                ExecutorService executorService = swVar.a;
                gw0 gw0Var = this.q;
                yx2 yx2Var = this.m;
                qx2 qx2Var = new qx2(workDatabase, gw0Var, yx2Var);
                WorkerParameters workerParameters = new WorkerParameters();
                workerParameters.a = uuidFromString;
                workerParameters.b = w50VarA;
                new HashSet(list);
                workerParameters.c = executorService;
                workerParameters.d = yx2Var;
                ty2 ty2Var = swVar.d;
                workerParameters.e = ty2Var;
                if (this.l == null) {
                    Context context = this.c;
                    ty2Var.getClass();
                    this.l = ty2.a(context, str3, workerParameters);
                }
                qb1 qb1Var = this.l;
                if (qb1Var == null) {
                    vc1.c().a(str4, "Could not create Worker " + str3);
                    g();
                    return;
                }
                if (qb1Var.l) {
                    vc1.c().a(str4, "Received an already-used Worker " + str3 + "; Worker Factory should return new instances");
                    g();
                    return;
                }
                qb1Var.l = true;
                workDatabase.c();
                try {
                    if (my2Var.f(str) == workInfo$State2) {
                        my2Var.m(WorkInfo$State.e, str);
                        i52 i52Var2 = my2Var.a;
                        i52Var2.b();
                        ly2 ly2Var = my2Var.i;
                        th2 th2VarA = ly2Var.a();
                        if (str == null) {
                            th2VarA.K(1);
                        } else {
                            th2VarA.x(1, str);
                        }
                        i52Var2.c();
                        try {
                            th2VarA.F();
                            i52Var2.o();
                            i52Var2.k();
                            ly2Var.c(th2VarA);
                            my2Var.n(-256, str);
                            z2 = true;
                        } catch (Throwable th2) {
                            i52Var2.k();
                            ly2Var.c(th2VarA);
                            throw th2;
                        }
                    } else {
                        z2 = false;
                    }
                    workDatabase.o();
                    if (!z2) {
                        f();
                        return;
                    }
                    if (h()) {
                        return;
                    }
                    ox2 ox2Var = new ox2(this.c, this.k, this.l, qx2Var, this.m);
                    yx2Var.d.execute(ox2Var);
                    androidx.work.impl.utils.futures.b bVar = ox2Var.c;
                    ua uaVar = new ua(this, 8, bVar);
                    wa waVar = new wa(1);
                    androidx.work.impl.utils.futures.b bVar2 = this.x;
                    bVar2.a(uaVar, waVar);
                    bVar.a(new yf1(this, 7, bVar), yx2Var.d);
                    bVar2.a(new yf1(this, 8, this.v), yx2Var.a);
                    return;
                } finally {
                }
            }
            f();
            workDatabase.o();
            vc1.c().getClass();
        } finally {
            workDatabase.k();
        }
    }
}
