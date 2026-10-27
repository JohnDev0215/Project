package defpackage;

import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: ue, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1609ue extends AbstractC0836ge {
    public final C0668dd c;

    public C1609ue(C0668dd c0668dd, C1614uj c1614uj) {
        super(4, c1614uj);
        this.c = c0668dd;
    }

    @Override // defpackage.AbstractC0218Md
    public final /* bridge */ /* synthetic */ void b(C1389qd c1389qd, boolean z) {
    }

    @Override // defpackage.AbstractC1335pe
    public final Feature[] f(C0415Xc c0415Xc) {
        if (((AbstractC0948ie) c0415Xc.g.get(this.c)) == null) {
            return null;
        }
        throw null;
    }

    @Override // defpackage.AbstractC1335pe
    public final boolean g(C0415Xc c0415Xc) {
        if (((AbstractC0948ie) c0415Xc.g.get(this.c)) == null) {
            return false;
        }
        throw null;
    }

    @Override // defpackage.AbstractC0836ge
    public final void h(C0415Xc c0415Xc) {
        if (((AbstractC0948ie) c0415Xc.g.remove(this.c)) != null) {
            throw null;
        }
        C0027Bj c0027Bj = this.b.a;
        synchronized (c0027Bj.a) {
            if (c0027Bj.c) {
                return;
            }
            c0027Bj.c = true;
            c0027Bj.b.a(c0027Bj);
        }
    }
}
