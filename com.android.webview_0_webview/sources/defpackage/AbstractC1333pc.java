package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;

/* JADX INFO: renamed from: pc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1333pc extends AbstractC1388qc {
    public static Resources e(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
