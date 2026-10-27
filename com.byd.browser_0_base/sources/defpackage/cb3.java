package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;

/* JADX INFO: compiled from: Toast.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cb3 extends ContextWrapper {
    public cb3(Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final ApplicationInfo getApplicationInfo() {
        ApplicationInfo applicationInfo = new ApplicationInfo(super.getApplicationInfo());
        applicationInfo.flags &= -536870913;
        return applicationInfo;
    }
}
