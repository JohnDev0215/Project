package defpackage;

import android.graphics.Bitmap;
import defpackage.ll;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: ProbabilisticCryptidRenderer.java */
/* JADX INFO: loaded from: classes.dex */
public final class m52 implements Callback<od> {
    public final /* synthetic */ ax0 e;
    public final /* synthetic */ Callback f;

    public m52(ax0 ax0Var, ll.b bVar) {
        this.e = ax0Var;
        this.f = bVar;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(od odVar) {
        od odVar2 = odVar;
        this.e.b();
        if (odVar2 == null) {
            oj2.U(1, 3, "NewTabPage.CryptidRenderResult");
            this.f.onResult(null);
        } else {
            nd ndVar = new nd(odVar2, Bitmap.Config.ARGB_8888);
            ndVar.W = 1;
            ndVar.r = true;
            this.f.onResult(ndVar);
        }
    }
}
