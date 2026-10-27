package defpackage;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import defpackage.sf3;

/* JADX INFO: compiled from: DigitalGoodsAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class v90 {
    public final sf3 a;

    /* JADX INFO: compiled from: DigitalGoodsAdapter.java */
    public class a implements sf3.a {
        public final /* synthetic */ String e;
        public final /* synthetic */ Bundle f;
        public final /* synthetic */ vd1 g;
        public final /* synthetic */ Runnable h;
        public final /* synthetic */ Runnable i;

        public a(String str, Bundle bundle, vd1 vd1Var, Runnable runnable, Runnable runnable2) {
            this.e = str;
            this.f = bundle;
            this.g = vd1Var;
            this.h = runnable;
            this.i = runnable2;
        }

        @Override // sf3.a
        public final void c() {
            this.i.run();
        }

        @Override // sf3.a
        public final void f(ft1 ft1Var, vf3 vf3Var) {
            Bundle bundleA;
            Runnable runnable = this.h;
            try {
                bundleA = vf3Var.a(this.e, this.f, this.g);
            } catch (Exception unused) {
                Log.w("cr_DigitalGoods", "Exception communicating with client.");
                runnable.run();
                bundleA = null;
            }
            boolean z = false;
            if (bundleA != null && bundleA.getBoolean("success", false)) {
                z = true;
            }
            if (z) {
                return;
            }
            runnable.run();
        }
    }

    public v90(sf3 sf3Var) {
        this.a = sf3Var;
    }

    public final void a(Uri uri, String str, Bundle bundle, vd1 vd1Var, Runnable runnable, Runnable runnable2) {
        this.a.a(uri, new a(str, bundle, vd1Var, runnable, runnable2));
    }
}
