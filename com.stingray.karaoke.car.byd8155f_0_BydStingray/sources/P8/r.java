package P8;

import F1.C0259s;
import java.util.Iterator;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class r implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f5484a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2782k f5485b;

    public r(k kVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(interfaceC2782k, "transformer");
        this.f5484a = kVar;
        this.f5485b = interfaceC2782k;
    }

    @Override // P8.k
    public final Iterator iterator() {
        return new C0259s(this);
    }
}
