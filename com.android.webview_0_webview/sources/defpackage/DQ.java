package defpackage;

import android.app.Activity;
import android.os.RemoteException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class DQ extends SR {
    public final VR a;

    public DQ(VR vr) {
        this.a = vr;
    }

    public final Activity c() {
        try {
            return (Activity) BinderC0998jS.d(((TR) this.a).c(), Activity.class);
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }
}
