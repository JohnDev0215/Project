package defpackage;

import org.chromium.device.nfc.NfcDelegate;

/* JADX INFO: compiled from: NfcProviderImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class hn1 implements gn1 {
    public final NfcDelegate e;
    public fn1 f;

    /* JADX INFO: compiled from: NfcProviderImpl.java */
    public static class a implements a21<gn1> {
        public final NfcDelegate e;

        public a(NfcDelegate nfcDelegate) {
            this.e = nfcDelegate;
        }

        @Override // defpackage.a21
        public final y11 d() {
            return new hn1(this.e);
        }
    }

    public hn1(NfcDelegate nfcDelegate) {
        this.e = nfcDelegate;
    }

    @Override // defpackage.gn1
    public final void C0(int i, f21<an1> f21Var) {
        xc2 xc2Var;
        fn1 fn1Var = this.f;
        if (fn1Var != null && (xc2Var = fn1Var.g) != null) {
            xc2Var.close();
            fn1Var.g = null;
        }
        this.f = new fn1(i, this.e, f21Var);
    }

    @Override // defpackage.gn1
    public final void E0() {
        fn1 fn1Var = this.f;
        if (fn1Var != null) {
            fn1Var.l = false;
            fn1Var.v0();
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        fn1 fn1Var = this.f;
        if (fn1Var != null) {
            xc2 xc2Var = fn1Var.g;
            if (xc2Var != null) {
                xc2Var.close();
                fn1Var.g = null;
            }
            this.f = null;
        }
    }

    @Override // defpackage.gn1
    public final void l0() {
        fn1 fn1Var = this.f;
        if (fn1Var != null) {
            fn1Var.l = true;
            fn1Var.F();
            fn1Var.Y();
            fn1Var.H();
        }
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
