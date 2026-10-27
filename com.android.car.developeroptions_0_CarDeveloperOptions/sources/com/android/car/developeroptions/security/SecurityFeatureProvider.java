package com.android.car.developeroptions.security;

import android.content.Context;
import com.android.internal.widget.LockPatternUtils;

/* JADX INFO: loaded from: classes.dex */
public interface SecurityFeatureProvider {
    LockPatternUtils getLockPatternUtils(Context context);
}
