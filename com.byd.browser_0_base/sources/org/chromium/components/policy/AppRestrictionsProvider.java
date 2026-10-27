package org.chromium.components.policy;

import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;

/* JADX INFO: loaded from: classes3.dex */
public class AppRestrictionsProvider extends AbstractAppRestrictionsProvider {
    public final UserManager e;

    public AppRestrictionsProvider(Context context) {
        super(context);
        this.e = (UserManager) context.getSystemService("user");
    }

    @Override // org.chromium.components.policy.AbstractAppRestrictionsProvider
    public final Bundle c(String str) {
        try {
            return this.e.getApplicationRestrictions(str);
        } catch (SecurityException unused) {
            return new Bundle();
        }
    }

    @Override // org.chromium.components.policy.AbstractAppRestrictionsProvider
    public final void d() {
    }
}
