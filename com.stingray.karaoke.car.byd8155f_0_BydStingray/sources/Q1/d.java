package Q1;

import A7.m;
import android.content.Context;
import h6.r;
import java.util.Set;
import m7.n;
import n7.AbstractC1998m;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2785n f6111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H5.d f6112b;
    public final Context c;
    public final n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Set f6113e;

    public d(Context context, Set set, InterfaceC2785n interfaceC2785n, H5.d dVar) {
        m.f(context, "context");
        m.f(set, "keysToMigrate");
        m.f(interfaceC2785n, "shouldRunMigration");
        a aVar = new a(context, 0);
        this.f6111a = interfaceC2785n;
        this.f6112b = dVar;
        this.c = context;
        this.d = r.o(aVar);
        this.f6113e = set == e.f6114a ? null : AbstractC1998m.C0(set);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(java.lang.Object r5, s7.AbstractC2410c r6) throws java.lang.Throwable {
        /*
            r4 = this;
            boolean r0 = r6 instanceof Q1.c
            if (r0 == 0) goto L13
            r0 = r6
            Q1.c r0 = (Q1.c) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            Q1.c r0 = new Q1.c
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.f6109m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            Q1.d r4 = r0.l
            h8.AbstractC1386c.u(r6)
            goto L41
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            h8.AbstractC1386c.u(r6)
            r0.l = r4
            r0.o = r3
            z7.n r6 = r4.f6111a
            java.lang.Object r6 = r6.invoke(r5, r0)
            if (r6 != r1) goto L41
            return r1
        L41:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r5 = r6.booleanValue()
            if (r5 != 0) goto L4c
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        L4c:
            java.util.Set r5 = r4.f6113e
            m7.n r4 = r4.d
            r6 = 0
            if (r5 != 0) goto L6b
            java.lang.Object r4 = r4.getValue()
            android.content.SharedPreferences r4 = (android.content.SharedPreferences) r4
            java.util.Map r4 = r4.getAll()
            java.lang.String r5 = "sharedPrefs.all"
            A7.m.e(r4, r5)
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L69
            goto L97
        L69:
            r3 = r6
            goto L97
        L6b:
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.lang.Object r4 = r4.getValue()
            android.content.SharedPreferences r4 = (android.content.SharedPreferences) r4
            boolean r0 = r5 instanceof java.util.Collection
            if (r0 == 0) goto L81
            r0 = r5
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L81
            goto L69
        L81:
            java.util.Iterator r5 = r5.iterator()
        L85:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L69
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = r4.contains(r0)
            if (r0 == 0) goto L85
        L97:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: Q1.d.a(java.lang.Object, s7.c):java.lang.Object");
    }
}
