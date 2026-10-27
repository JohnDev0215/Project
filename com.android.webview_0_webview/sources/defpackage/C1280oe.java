package defpackage;

import android.os.Parcel;
import com.google.android.gms.common.Feature;

/* JADX INFO: renamed from: oe, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1280oe extends AbstractC1224nd {
    public final /* synthetic */ C1169md c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1280oe(C1169md c1169md, Feature[] featureArr, boolean z) {
        super(featureArr, z, null);
        this.c = c1169md;
    }

    @Override // defpackage.AbstractC1224nd
    public final void a(InterfaceC1607uc interfaceC1607uc, C1614uj c1614uj) {
        String str = ((C0204Lg) this.c.a).b;
        InterfaceC0114Gg interfaceC0114Gg = (InterfaceC0114Gg) ((C0186Kg) interfaceC1607uc).i();
        BinderC0293Qg binderC0293Qg = new BinderC0293Qg(c1614uj);
        C0150Ig c0150Ig = (C0150Ig) interfaceC0114Gg;
        Parcel parcelF = c0150Ig.f();
        parcelF.writeString(str);
        AbstractC0383Vg.c(parcelF, binderC0293Qg);
        c0150Ig.d(2, parcelF);
    }
}
