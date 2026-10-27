package L0;

import E0.F;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class n extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final n f3915m = new n(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        j jVarQ = ((F) obj).q();
        boolean z6 = false;
        if (jVarQ != null && jVarQ.f3909m) {
            z6 = true;
        }
        return Boolean.valueOf(z6);
    }
}
