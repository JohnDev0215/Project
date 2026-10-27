package P7;

import A7.A;
import A7.B;
import A7.s;
import H7.w;
import Q7.InterfaceC0652e;
import T7.C0792k;
import T7.z;
import java.util.Collection;
import n7.AbstractC1985B;
import o8.C2057b;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g implements S7.c {
    public static final e d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ w[] f5429e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C2058c f5430f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C2061f f5431g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final C2057b f5432h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f5433a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2782k f5434b;
    public final E8.i c;

    static {
        B b10 = A.f443a;
        f5429e = new w[]{b10.h(new s(b10.b(g.class), "cloneable", "getCloneable()Lorg/jetbrains/kotlin/descriptors/impl/ClassDescriptorImpl;"))};
        d = new e();
        f5430f = N7.o.f4875j;
        C2060e c2060e = N7.n.c;
        C2061f c2061fF = c2060e.f();
        A7.m.e(c2061fF, "cloneable.shortName()");
        f5431g = c2061fF;
        f5432h = C2057b.j(c2060e.g());
    }

    public g(E8.l lVar, z zVar) {
        f fVar = f.f5428m;
        this.f5433a = zVar;
        this.f5434b = fVar;
        this.c = new E8.i(lVar, new B8.A(this, 19, lVar));
    }

    @Override // S7.c
    public final Collection a(C2058c c2058c) {
        A7.m.f(c2058c, "packageFqName");
        return c2058c.equals(f5430f) ? AbstractC1985B.t((C0792k) C7.a.N(this.c, f5429e[0])) : n7.w.l;
    }

    @Override // S7.c
    public final boolean b(C2058c c2058c, C2061f c2061f) {
        A7.m.f(c2058c, "packageFqName");
        A7.m.f(c2061f, "name");
        return c2061f.equals(f5431g) && c2058c.equals(f5430f);
    }

    @Override // S7.c
    public final InterfaceC0652e c(C2057b c2057b) {
        A7.m.f(c2057b, "classId");
        if (c2057b.equals(f5432h)) {
            return (C0792k) C7.a.N(this.c, f5429e[0]);
        }
        return null;
    }
}
