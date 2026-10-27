package com.android.launcher3.icons.cache;

import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import com.android.launcher3.icons.BitmapInfo;

/* JADX INFO: loaded from: classes4.dex */
public interface CachingLogic<T> {
    ComponentName getComponent(T object);

    CharSequence getLabel(T object);

    UserHandle getUser(T object);

    void loadIcon(Context context, T object, BitmapInfo target);
}
