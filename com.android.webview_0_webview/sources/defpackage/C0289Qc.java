package defpackage;

import java.util.Arrays;

/* JADX INFO: renamed from: Qc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0289Qc {
    public final boolean a = false;
    public final int b;
    public final C0020Bc c;
    public final InterfaceC1772xc d;

    public C0289Qc(C0020Bc c0020Bc, InterfaceC1772xc interfaceC1772xc) {
        this.c = c0020Bc;
        this.d = interfaceC1772xc;
        this.b = Arrays.hashCode(new Object[]{c0020Bc, interfaceC1772xc});
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0289Qc)) {
            return false;
        }
        C0289Qc c0289Qc = (C0289Qc) obj;
        return !this.a && !c0289Qc.a && AbstractC0893hf.a(this.c, c0289Qc.c) && AbstractC0893hf.a(this.d, c0289Qc.d);
    }

    public final int hashCode() {
        return this.b;
    }
}
