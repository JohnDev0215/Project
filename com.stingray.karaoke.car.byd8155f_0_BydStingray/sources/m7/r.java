package m7;

import java.io.Serializable;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class r implements f, Serializable {
    public InterfaceC2772a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f19268m;

    @Override // m7.f
    public final Object getValue() {
        if (this.f19268m == p.f19266a) {
            InterfaceC2772a interfaceC2772a = this.l;
            A7.m.c(interfaceC2772a);
            this.f19268m = interfaceC2772a.a();
            this.l = null;
        }
        return this.f19268m;
    }

    public final String toString() {
        return this.f19268m != p.f19266a ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}
