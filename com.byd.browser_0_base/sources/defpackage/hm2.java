package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.ui.signin.fre.SigninFirstRunView;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: SigninFirstRunCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hm2 {
    public final km2 a;
    public j<PropertyModel, SigninFirstRunView, v62> b;

    /* JADX INFO: compiled from: SigninFirstRunCoordinator.java */
    public interface a {
    }

    public hm2(Context context, bi1 bi1Var, a aVar, v42 v42Var) {
        this.a = new km2(context, bi1Var, aVar, v42Var);
    }

    public final void a(SigninFirstRunView signinFirstRunView) {
        j<PropertyModel, SigninFirstRunView, v62> jVar = this.b;
        if (jVar != null) {
            jVar.c();
            this.b = null;
        }
        if (signinFirstRunView != null) {
            this.b = j.b(this.a.j, signinFirstRunView, new ag(19));
        }
    }
}
