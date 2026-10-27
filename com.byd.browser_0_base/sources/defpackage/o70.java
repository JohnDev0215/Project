package defpackage;

import android.content.pm.ResolveInfo;
import defpackage.w20;

/* JADX INFO: compiled from: DefaultBrowserProviderImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class o70 implements w20.b {
    @Override // w20.b
    public final String a() {
        ResolveInfo resolveInfoE = wt1.e();
        if (resolveInfoE != null) {
            return resolveInfoE.activityInfo.packageName;
        }
        return null;
    }
}
