package defpackage;

import org.chromium.components.webxr.ArDelegateImpl;

/* JADX INFO: compiled from: XrDelegateImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public class eu3 implements du3 {
    public static final /* synthetic */ int h = 0;
    public final b8 e;
    public int f = 0;
    public final aq1<Boolean> g = new aq1<>();

    public eu3() {
        Object objNewInstance;
        try {
            objNewInstance = ArDelegateImpl.class.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            objNewInstance = null;
        }
        b8 b8Var = (b8) objNewInstance;
        this.e = b8Var;
        if (b8Var != null) {
            b8Var.b().p(new l51(24, this));
        }
    }

    @Override // defpackage.du3
    public final boolean a() {
        if (this.f == 1) {
            return this.e.a();
        }
        return false;
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.g;
    }

    @Override // defpackage.du3
    public final boolean i0() {
        return this.f == 1;
    }

    @Override // defpackage.vb
    public final int o() {
        return !a() ? 1 : 0;
    }
}
