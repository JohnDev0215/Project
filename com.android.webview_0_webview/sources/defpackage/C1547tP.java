package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;

/* JADX INFO: renamed from: tP, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1547tP extends ContextWrapper {
    public C1547tP(C1602uP c1602uP, Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ApplicationInfo getApplicationInfo() {
        ApplicationInfo applicationInfo = new ApplicationInfo(super.getApplicationInfo());
        applicationInfo.targetSdkVersion = 19;
        applicationInfo.flags &= -536870913;
        return applicationInfo;
    }
}
