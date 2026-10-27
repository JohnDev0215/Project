package p;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class t60 extends u11 {
    @Override // p.u11
    public final ProviderInfo C(ResolveInfo resolveInfo) {
        return resolveInfo.providerInfo;
    }

    @Override // p.u11
    public final List G(PackageManager packageManager, Intent intent) {
        return packageManager.queryIntentContentProviders(intent, 0);
    }
}
