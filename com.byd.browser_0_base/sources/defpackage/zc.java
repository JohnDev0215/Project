package defpackage;

import defpackage.bi1;

/* JADX INFO: compiled from: BaseButtonDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zc implements bi1.a {
    public final /* synthetic */ ad e;

    public zc(ad adVar) {
        this.e = adVar;
    }

    @Override // bi1.a
    public final void d() {
        ad adVar = this.e;
        bk bkVar = adVar.e;
        bkVar.b = true;
        adVar.e(bkVar.a);
    }

    @Override // bi1.a
    public final void e() {
        ad adVar = this.e;
        bk bkVar = adVar.e;
        bkVar.b = false;
        adVar.e(bkVar.a);
    }
}
