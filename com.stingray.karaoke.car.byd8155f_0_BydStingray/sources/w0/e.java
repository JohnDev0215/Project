package w0;

import A7.o;
import android.view.KeyEvent;
import g0.n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class e extends n implements d {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2782k f23168y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public o f23169z;

    @Override // w0.d
    public final boolean P(KeyEvent keyEvent) {
        InterfaceC2782k interfaceC2782k = this.f23168y;
        if (interfaceC2782k != null) {
            return ((Boolean) interfaceC2782k.b(new b(keyEvent))).booleanValue();
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [A7.o, z7.k] */
    @Override // w0.d
    public final boolean l(KeyEvent keyEvent) {
        ?? r12 = this.f23169z;
        if (r12 != 0) {
            return ((Boolean) r12.b(new b(keyEvent))).booleanValue();
        }
        return false;
    }
}
