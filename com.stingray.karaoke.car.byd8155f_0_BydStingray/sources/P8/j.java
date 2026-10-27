package P8;

import java.util.Iterator;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class j implements k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5473a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5474b;
    public final m7.c c;

    /* JADX WARN: Multi-variable type inference failed */
    public j(k kVar, InterfaceC2782k interfaceC2782k) {
        A7.m.f(kVar, "sequence");
        this.f5474b = kVar;
        this.c = (A7.o) interfaceC2782k;
    }

    @Override // P8.k
    public final Iterator iterator() {
        switch (this.f5473a) {
            case 0:
                return new i(this);
            default:
                return new f(this);
        }
    }

    public j(InterfaceC2772a interfaceC2772a, InterfaceC2782k interfaceC2782k) {
        A7.m.f(interfaceC2782k, "getNextValue");
        this.f5474b = interfaceC2772a;
        this.c = interfaceC2782k;
    }
}
