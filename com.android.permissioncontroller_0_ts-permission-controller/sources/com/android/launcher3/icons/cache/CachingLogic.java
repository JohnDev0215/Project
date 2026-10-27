package com.android.launcher3.icons.cache;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.LocaleList;
import android.os.UserHandle;
import com.android.launcher3.icons.BitmapInfo;

/* JADX INFO: loaded from: classes7.dex */
public interface CachingLogic<T> {
    ComponentName getComponent(T t);

    CharSequence getLabel(T t);

    UserHandle getUser(T t);

    BitmapInfo loadIcon(Context context, T t);

    default CharSequence getDescription(T object, CharSequence fallback) {
        return fallback;
    }

    default String getKeywords(T object, LocaleList localeList) {
        return null;
    }

    default long getLastUpdatedTime(T object, PackageInfo info) {
        return info.lastUpdateTime;
    }

    default boolean addToMemCache() {
        return true;
    }
}
