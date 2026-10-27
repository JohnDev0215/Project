package com.android.systemui;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface SysUiServiceProvider {
    <T> T getComponent(Class<T> interfaceType);

    static <T> T getComponent(Context context, Class<T> cls) {
        return (T) ((SysUiServiceProvider) context.getApplicationContext()).getComponent(cls);
    }
}
