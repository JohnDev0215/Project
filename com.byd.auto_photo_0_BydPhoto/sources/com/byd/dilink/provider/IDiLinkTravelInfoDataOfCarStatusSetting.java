package com.byd.dilink.provider;

import android.content.ContentResolver;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkTravelInfoDataOfCarStatusSetting {
    int getInt(ContentResolver contentResolver, String str, int i);

    String getString(ContentResolver contentResolver, String str);

    Uri getUriFor(String str);

    boolean putInt(ContentResolver contentResolver, String str, int i);

    boolean putString(ContentResolver contentResolver, String str, String str2);
}
