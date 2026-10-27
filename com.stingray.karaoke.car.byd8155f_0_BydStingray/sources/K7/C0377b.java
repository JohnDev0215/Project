package K7;

import F8.AbstractC0287w;
import Q7.InterfaceC0666t;
import W7.AbstractC0839c;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import q8.C2203g;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: K7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0377b extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0377b f3763n = new C0377b(1, 0);
    public static final C0377b o = new C0377b(1, 1);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0377b f3764p = new C0377b(1, 2);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0377b f3765q = new C0377b(1, 3);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C0377b f3766r = new C0377b(1, 4);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C0377b f3767s = new C0377b(1, 5);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0377b f3768t = new C0377b(1, 6);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C0377b f3769u = new C0377b(1, 7);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final C0377b f3770v = new C0377b(1, 8);

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final C0377b f3771w = new C0377b(1, 9);

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final C0377b f3772x = new C0377b(1, 10);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final C0377b f3773y = new C0377b(1, 11);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f3774m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0377b(int i7, int i10) {
        super(i7);
        this.f3774m = i10;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        n7.u uVar = n7.u.l;
        switch (this.f3774m) {
            case 0:
                Class cls = (Class) obj;
                A7.m.f(cls, "it");
                return io.sentry.config.a.W(AbstractC0379c.a(cls), uVar, false, uVar);
            case 1:
                A7.m.f((Class) obj, "it");
                return new ConcurrentHashMap();
            case 2:
                Class cls2 = (Class) obj;
                A7.m.f(cls2, "it");
                return io.sentry.config.a.W(AbstractC0379c.a(cls2), uVar, true, uVar);
            case 3:
                Class cls3 = (Class) obj;
                A7.m.f(cls3, "it");
                return new C0401z(cls3);
            case 4:
                Class cls4 = (Class) obj;
                A7.m.f(cls4, "it");
                return new U(cls4);
            case 5:
                Class<?> returnType = ((Method) obj).getReturnType();
                A7.m.e(returnType, "it.returnType");
                return AbstractC0839c.b(returnType);
            case 6:
                Class cls5 = (Class) obj;
                A7.m.e(cls5, "it");
                return AbstractC0839c.b(cls5);
            case 7:
                InterfaceC0666t interfaceC0666t = (InterfaceC0666t) obj;
                A7.m.f(interfaceC0666t, "descriptor");
                return C2203g.f21280e.w(interfaceC0666t) + " | " + y0.c(interfaceC0666t).i();
            case 8:
                Q7.L l = (Q7.L) obj;
                A7.m.f(l, "descriptor");
                return C2203g.f21280e.w(l) + " | " + y0.b(l).i();
            case 9:
                C2203g c2203g = x0.f3852a;
                AbstractC0287w abstractC0287wB = ((T7.Q) obj).b();
                A7.m.e(abstractC0287wB, "it.type");
                return x0.d(abstractC0287wB);
            case 10:
                C2203g c2203g2 = x0.f3852a;
                AbstractC0287w abstractC0287wB2 = ((T7.Q) obj).b();
                A7.m.e(abstractC0287wB2, "it.type");
                return x0.d(abstractC0287wB2);
            default:
                Class cls6 = (Class) obj;
                A7.m.e(cls6, "it");
                return AbstractC0839c.b(cls6);
        }
    }
}
