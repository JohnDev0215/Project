package defpackage;

import android.app.Activity;
import android.content.Context;
import defpackage.di1;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.init.AsyncInitializationActivity;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: DuplicateDownloadDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class we0 implements di1.a {
    public final /* synthetic */ Callback e;
    public final /* synthetic */ bi1 f;
    public final /* synthetic */ String g;
    public final /* synthetic */ Context h;

    public we0(ze0 ze0Var, bi1 bi1Var, String str, Activity activity) {
        this.e = ze0Var;
        this.f = bi1Var;
        this.g = str;
        this.h = activity;
    }

    @Override // di1.a
    public final void b(int i) {
        zl1 zl1Var;
        if (i == 1) {
            return;
        }
        Callback callback = this.e;
        if (callback != null && i != 2) {
            callback.onResult(Boolean.FALSE);
            ye0.a(4, !this.g.isEmpty());
        }
        Context context = this.h;
        if (context instanceof AsyncInitializationActivity) {
            g3 g3Var = ((AsyncInitializationActivity) context).O;
            if (g3Var == null) {
                aj3<zl1> aj3Var = am1.a;
                zl1Var = null;
            } else {
                zl1Var = (zl1) am1.a.e(g3Var.r);
            }
            if (zl1Var != null) {
                zl1Var.destroy();
                zl1Var.f.finish();
            }
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        boolean z = i == 0;
        Callback callback = this.e;
        if (callback != null) {
            callback.onResult(Boolean.valueOf(z));
        }
        this.f.b(z ? 1 : 2, propertyModel);
        ye0.a(z ? 1 : 2, !this.g.isEmpty());
    }
}
