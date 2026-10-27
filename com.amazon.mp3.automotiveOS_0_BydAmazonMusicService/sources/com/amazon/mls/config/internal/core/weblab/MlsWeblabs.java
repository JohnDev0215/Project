package com.amazon.mls.config.internal.core.weblab;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class MlsWeblabs {
    private static MlsWeblabs sInstance;
    private static final List<Object> sWeblabs = Collections.emptyList();
    final SharedPreferences mSharedPreferences;

    public static synchronized MlsWeblabs getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MlsWeblabs(context);
        }
        return sInstance;
    }

    private MlsWeblabs(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.mSharedPreferences = context.getSharedPreferences("com.amazon.mls.core.weblab.MlsWeblabsSP", 0);
    }
}
