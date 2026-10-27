package com.byd.dilink.provider;

import android.content.ContentResolver;
import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkDiCareRecordOfCarStatusSetting {
    Cursor getDiCareRecord(ContentResolver contentResolver);

    boolean insertDiCareRecord(ContentResolver contentResolver, long j, int i, int i2, int i3, String str);
}
