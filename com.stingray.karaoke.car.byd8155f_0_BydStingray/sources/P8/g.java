package P8;

import java.util.Iterator;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class g implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f5467a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5468b;
    public final InterfaceC2782k c;

    public g(k kVar, boolean z6, InterfaceC2782k interfaceC2782k) {
        this.f5467a = kVar;
        this.f5468b = z6;
        this.c = interfaceC2782k;
    }

    @Override // P8.k
    public final Iterator iterator() {
        return new f(this);
    }
}
