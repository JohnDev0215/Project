package defpackage;

import defpackage.nj2;

/* JADX INFO: compiled from: SettingsAutofillAndPaymentsObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lj2 implements Runnable {
    public final /* synthetic */ nj2.a e;
    public final /* synthetic */ qa f;

    public lj2(nj2.a aVar, qa qaVar) {
        this.e = aVar;
        this.f = qaVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.e.b(this.f);
    }
}
