package P8;

import java.util.Iterator;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k f5469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2782k f5470b;
    public final InterfaceC2782k c;

    public h(k kVar, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2) {
        A7.m.f(interfaceC2782k, "transformer");
        this.f5469a = kVar;
        this.f5470b = interfaceC2782k;
        this.c = interfaceC2782k2;
    }

    @Override // P8.k
    public final Iterator iterator() {
        return new f(this);
    }
}
