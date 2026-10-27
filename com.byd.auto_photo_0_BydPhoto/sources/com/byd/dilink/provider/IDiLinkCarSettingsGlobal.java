package com.byd.dilink.provider;

import android.content.ContentResolver;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkCarSettingsGlobal {
    boolean getBoolean(ContentResolver contentResolver, String str);

    int getInt(ContentResolver contentResolver, String str);

    Uri getUriFor(Uri uri, String str);

    boolean putBoolean(ContentResolver contentResolver, String str, boolean z);

    boolean putInt(ContentResolver contentResolver, String str, int i);
}
