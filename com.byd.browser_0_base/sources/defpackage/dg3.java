package defpackage;

import android.content.ComponentName;
import android.os.Bundle;
import android.support.customtabs.trusted.ITrustedWebActivityService;

/* JADX INFO: compiled from: TrustedWebActivityServiceConnection.java */
/* JADX INFO: loaded from: classes.dex */
public final class dg3 {
    public final ITrustedWebActivityService a;
    public final ComponentName b;

    public dg3(ITrustedWebActivityService iTrustedWebActivityService, ComponentName componentName) {
        this.a = iTrustedWebActivityService;
        this.b = componentName;
    }

    public static void a(Bundle bundle, String str) {
        if (!bundle.containsKey(str)) {
            throw new IllegalArgumentException("Bundle must contain ".concat(str));
        }
    }
}
