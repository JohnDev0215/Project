package m7;

import java.io.Serializable;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class n implements f, Serializable {
    public InterfaceC2772a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public volatile Object f19262m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f19263n;

    public n(InterfaceC2772a interfaceC2772a) {
        A7.m.f(interfaceC2772a, "initializer");
        this.l = interfaceC2772a;
        this.f19262m = p.f19266a;
        this.f19263n = this;
    }

    @Override // m7.f
    public final Object getValue() {
        Object objA;
        Object obj = this.f19262m;
        p pVar = p.f19266a;
        if (obj != pVar) {
            return obj;
        }
        synchronized (this.f19263n) {
            objA = this.f19262m;
            if (objA == pVar) {
                InterfaceC2772a interfaceC2772a = this.l;
                A7.m.c(interfaceC2772a);
                objA = interfaceC2772a.a();
                this.f19262m = objA;
                this.l = null;
            }
        }
        return objA;
    }

    public final String toString() {
        return this.f19262m != p.f19266a ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
