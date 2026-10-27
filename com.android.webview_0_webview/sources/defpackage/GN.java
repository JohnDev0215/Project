package defpackage;

import android.app.Activity;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GN extends LN {
    public WeakReference v;

    public GN(WeakReference weakReference) {
        this.v = weakReference;
    }

    @Override // defpackage.LN
    public final boolean b(String str) {
        Activity activity = (Activity) this.v.get();
        if (activity == null) {
            return false;
        }
        return C1185mt.j(activity, str);
    }

    @Override // defpackage.LN
    public final boolean c(String[] strArr, int i) {
        Activity activity = (Activity) this.v.get();
        if (activity == null) {
            return false;
        }
        C1185mt.o(activity, strArr, i);
        return true;
    }

    @Override // defpackage.LN
    public final boolean d(String str) {
        Activity activity = (Activity) this.v.get();
        if (activity == null) {
            return false;
        }
        return C1185mt.p(activity, str);
    }
}
