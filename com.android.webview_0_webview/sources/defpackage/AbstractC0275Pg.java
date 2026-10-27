package defpackage;

import android.os.Parcel;

/* JADX INFO: renamed from: Pg, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0275Pg extends AbstractC1224nd {
    public C1614uj c;

    public AbstractC0275Pg(C0257Og c0257Og) {
    }

    @Override // defpackage.AbstractC1224nd
    public void a(InterfaceC1607uc interfaceC1607uc, C1614uj c1614uj) {
        this.c = c1614uj;
        InterfaceC0114Gg interfaceC0114Gg = (InterfaceC0114Gg) ((C0186Kg) interfaceC1607uc).i();
        BinderC0239Ng binderC0239Ng = new BinderC0239Ng((C0257Og) this);
        C0150Ig c0150Ig = (C0150Ig) interfaceC0114Gg;
        Parcel parcelF = c0150Ig.f();
        AbstractC0383Vg.c(parcelF, binderC0239Ng);
        c0150Ig.d(1, parcelF);
    }
}
