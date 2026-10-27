package n3;

import android.content.Context;
import android.content.ContextWrapper;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: n3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1937b extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1937b f19846n = new C1937b(1, 0);
    public static final C1937b o = new C1937b(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1937b f19847p = new C1937b(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1937b f19848q = new C1937b(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C1937b f19849r = new C1937b(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C1937b f19850s = new C1937b(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C1937b f19851t = new C1937b(1, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C1937b f19852u = new C1937b(1, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C1937b f19853v = new C1937b(1, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C1937b f19854w = new C1937b(1, 9);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f19855m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1937b(int i7, int i10) {
        super(i7);
        this.f19855m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f19855m) {
            case 0:
                Context context = (Context) obj;
                A7.m.f(context, "it");
                if (context instanceof ContextWrapper) {
                    return ((ContextWrapper) context).getBaseContext();
                }
                return null;
            case 1:
                Context context2 = (Context) obj;
                A7.m.f(context2, "it");
                if (context2 instanceof ContextWrapper) {
                    return ((ContextWrapper) context2).getBaseContext();
                }
                return null;
            case 2:
                C1934I c1934i = (C1934I) obj;
                A7.m.f(c1934i, "$this$navOptions");
                c1934i.c = true;
                return m7.q.f19267a;
            case 3:
                y yVar = (y) obj;
                A7.m.f(yVar, "destination");
                C1927B c1927b = yVar.f19914m;
                if (c1927b == null || c1927b.f19791v != yVar.f19917q) {
                    return null;
                }
                return c1927b;
            case 4:
                y yVar2 = (y) obj;
                A7.m.f(yVar2, "destination");
                C1927B c1927b2 = yVar2.f19914m;
                if (c1927b2 == null || c1927b2.f19791v != yVar2.f19917q) {
                    return null;
                }
                return c1927b2;
            case 5:
                Q q4 = (Q) obj;
                A7.m.f(q4, "$this$popUpTo");
                q4.f19845a = true;
                return m7.q.f19267a;
            case 6:
                y yVar3 = (y) obj;
                A7.m.f(yVar3, "it");
                return Integer.valueOf(yVar3.f19917q);
            case 7:
                y yVar4 = (y) obj;
                A7.m.f(yVar4, "it");
                return yVar4.f19914m;
            case 8:
                y yVar5 = (y) obj;
                A7.m.f(yVar5, "it");
                if (!(yVar5 instanceof C1927B)) {
                    return null;
                }
                C1927B c1927b3 = (C1927B) yVar5;
                return c1927b3.s(c1927b3.f19791v, c1927b3, false, null);
            default:
                C1934I c1934i2 = (C1934I) obj;
                A7.m.f(c1934i2, "$this$navOptions");
                c1934i2.f19833b = true;
                return m7.q.f19267a;
        }
    }
}
