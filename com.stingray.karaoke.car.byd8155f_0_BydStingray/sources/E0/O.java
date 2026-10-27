package E0;

import java.util.Map;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class O implements C0.L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f1573b;
    public final /* synthetic */ Map c;
    public final /* synthetic */ InterfaceC2782k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Q f1574e;

    public O(int i7, int i10, Map map, InterfaceC2782k interfaceC2782k, Q q4) {
        this.f1572a = i7;
        this.f1573b = i10;
        this.c = map;
        this.d = interfaceC2782k;
        this.f1574e = q4;
    }

    @Override // C0.L
    public final Map a() {
        return this.c;
    }

    @Override // C0.L
    public final void b() {
        this.d.b(this.f1574e.f1578t);
    }

    @Override // C0.L
    public final int c() {
        return this.f1573b;
    }

    @Override // C0.L
    public final InterfaceC2782k d() {
        return null;
    }

    @Override // C0.L
    public final int e() {
        return this.f1572a;
    }
}
