package com.byd.base.hilt.impl;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public abstract class TsCommonCarImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2442a = getClass().getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f2443b;

    public TsCommonCarImpl(Context context) {
        this.f2443b = context;
    }

    public void j() {
        this.f2443b = null;
    }
}
