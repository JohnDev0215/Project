package T;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import u.C2453A;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: T.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0765s0 extends s7.i implements InterfaceC2786o {
    public List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f8339m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public List f8340n;
    public C2453A o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C2453A f8341p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public C2453A f8342q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Set f8343r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C2453A f8344s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f8345t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public /* synthetic */ T f8346u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ C0767t0 f8347v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0765s0(C0767t0 c0767t0, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.f8347v = c0767t0;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m(T.C0767t0 r23, java.util.List r24, java.util.List r25, java.util.List r26, u.C2453A r27, u.C2453A r28, u.C2453A r29, u.C2453A r30) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0765s0.m(T.t0, java.util.List, java.util.List, java.util.List, u.A, u.A, u.A, u.A):void");
    }

    public static final void p(List list, C0767t0 c0767t0) {
        list.clear();
        synchronized (c0767t0.f8352b) {
            try {
                ArrayList arrayList = c0767t0.f8358j;
                int size = arrayList.size();
                for (int i7 = 0; i7 < size; i7++) {
                    list.add((V) arrayList.get(i7));
                }
                c0767t0.f8358j.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws Throwable {
        C0765s0 c0765s0 = new C0765s0(this.f8347v, (InterfaceC2189c) obj3);
        c0765s0.f8346u = (T) obj2;
        c0765s0.invokeSuspend(m7.q.f19267a);
        return EnumC2303a.l;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0096 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0155 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0149 -> B:44:0x0150). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:67:0x01fd -> B:64:0x01f8). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 529
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: T.C0765s0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
