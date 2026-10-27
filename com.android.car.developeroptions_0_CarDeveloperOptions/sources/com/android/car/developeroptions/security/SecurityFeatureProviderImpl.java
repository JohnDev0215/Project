package com.android.car.developeroptions.security;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes.dex */
public class SecurityFeatureProviderImpl implements SecurityFeatureProvider {
    private LockPatternUtils mLockPatternUtils;

    @Override // com.android.car.developeroptions.security.SecurityFeatureProvider
    public LockPatternUtils getLockPatternUtils(Context context) {
        if (this.mLockPatternUtils == null) {
            this.mLockPatternUtils = new LockPatternUtils(context.getApplicationContext());
        }
        return this.mLockPatternUtils;
    }
}
