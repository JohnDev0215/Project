package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.UserManager;

/* JADX INFO: renamed from: iL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0936iL extends AbstractC1047kL {
    public final Context c;
    public final BroadcastReceiver d = new C0880hL(this);

    public AbstractC0936iL(Context context) {
        this.c = context;
    }

    @Override // defpackage.AbstractC1047kL
    public void b() {
        Bundle bundle;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        System.currentTimeMillis();
        String packageName = this.c.getPackageName();
        UserManager userManager = ((AbstractC0991jL) this).e;
        if (userManager == null) {
            bundle = new Bundle();
        } else {
            try {
                bundle = userManager.getApplicationRestrictions(packageName);
            } catch (SecurityException unused) {
                bundle = new Bundle();
            }
        }
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        a(bundle);
    }
}
