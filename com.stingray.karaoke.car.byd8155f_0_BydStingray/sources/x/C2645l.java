package x;

import T.C0735d;
import T.C0738e0;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: x.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2645l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D0 f23552a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f23553b;
    public final long c;
    public final A7.o d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f23554e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AbstractC2652s f23555f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f23556g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f23557h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0738e0 f23558i;

    /* JADX WARN: Multi-variable type inference failed */
    public C2645l(Object obj, D0 d02, AbstractC2652s abstractC2652s, long j3, Object obj2, long j10, InterfaceC2772a interfaceC2772a) {
        this.f23552a = d02;
        this.f23553b = obj2;
        this.c = j10;
        this.d = (A7.o) interfaceC2772a;
        T.S s6 = T.S.f8224q;
        this.f23554e = C0735d.O(obj, s6);
        this.f23555f = AbstractC2631e.i(abstractC2652s);
        this.f23556g = j3;
        this.f23557h = Long.MIN_VALUE;
        this.f23558i = C0735d.O(Boolean.TRUE, s6);
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [A7.o, z7.a] */
    public final void a() {
        this.f23558i.setValue(Boolean.FALSE);
        this.d.a();
    }
}
