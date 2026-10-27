package com.byd.dilink.provider;

import android.content.ContentResolver;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkCarSettingsConfig {
    boolean getBoolean(ContentResolver contentResolver, String str);

    int getInt(ContentResolver contentResolver, String str);

    boolean putBoolean(ContentResolver contentResolver, String str, boolean z);

    boolean putInt(ContentResolver contentResolver, String str, int i);
}
