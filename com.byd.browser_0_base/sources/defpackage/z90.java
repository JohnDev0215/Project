package defpackage;

import android.net.Uri;
import android.os.Bundle;
import defpackage.aa0;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: DigitalGoodsImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class z90 implements t90 {
    public final v90 e;
    public final a f;

    /* JADX INFO: compiled from: DigitalGoodsImpl.java */
    public interface a {
    }

    public z90(v90 v90Var, ke0 ke0Var) {
        this.e = v90Var;
        this.f = ke0Var;
    }

    @Override // defpackage.t90
    public final void B(String[] strArr, aa0.i iVar) {
        GURL gurlG = ((RenderFrameHost) ((ke0) this.f).f).g();
        if (gurlG != null) {
            Uri uri = Uri.parse(gurlG.j());
            v90 v90Var = this.e;
            v90Var.getClass();
            Bundle bundle = new Bundle();
            bundle.putStringArray("getDetails.itemIds", strArr);
            v90Var.a(uri, "getDetails", bundle, new qs0(iVar), new androidx.appcompat.widget.a(16, iVar), new wv(21, iVar));
        }
    }

    @Override // defpackage.t90
    public final void L(aa0.m mVar) {
        GURL gurlG = ((RenderFrameHost) ((ke0) this.f).f).g();
        if (gurlG != null) {
            Uri uri = Uri.parse(gurlG.j());
            v90 v90Var = this.e;
            v90Var.getClass();
            v90Var.a(uri, "listPurchaseHistory", new Bundle(), new v81(mVar), new wv(22, mVar), new aw(12, mVar));
        }
    }

    @Override // defpackage.t90
    public final void d0(aa0.q qVar) {
        GURL gurlG = ((RenderFrameHost) ((ke0) this.f).f).g();
        if (gurlG != null) {
            Uri uri = Uri.parse(gurlG.j());
            v90 v90Var = this.e;
            v90Var.getClass();
            v90Var.a(uri, "listPurchases", new Bundle(), new x81(qVar), new aw(10, qVar), new wq0(16, qVar));
        }
    }

    @Override // defpackage.t90
    public final void n0(String str, aa0.e eVar) {
        GURL gurlG = ((RenderFrameHost) ((ke0) this.f).f).g();
        if (gurlG != null) {
            Uri uri = Uri.parse(gurlG.j());
            v90 v90Var = this.e;
            v90Var.getClass();
            Bundle bundle = new Bundle();
            bundle.putString("consume.purchaseToken", str);
            ux uxVar = new ux(eVar);
            aw awVar = new aw(11, eVar);
            v90Var.a(uri, "consume", bundle, uxVar, new u90(v90Var, str, eVar, uri, new wq0(17, eVar), awVar, 0), awVar);
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
