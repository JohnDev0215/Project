package c9;

import g1.AbstractC1274a;
import r9.InterfaceC2320j;

/* JADX INFO: loaded from: classes.dex */
public final class F extends AbstractC1274a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ y f12500e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f12501f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ byte[] f12502g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f12503h;

    public F(y yVar, int i7, byte[] bArr, int i10) {
        this.f12500e = yVar;
        this.f12501f = i7;
        this.f12502g = bArr;
        this.f12503h = i10;
    }

    @Override // g1.AbstractC1274a
    public final long B() {
        return this.f12501f;
    }

    @Override // g1.AbstractC1274a
    public final y C() {
        return this.f12500e;
    }

    @Override // g1.AbstractC1274a
    public final void f0(InterfaceC2320j interfaceC2320j) {
        interfaceC2320j.B(this.f12503h, this.f12501f, this.f12502g);
    }
}
