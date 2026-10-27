package com.android.inputmethod.dictionarypack;

import android.content.ContentValues;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadRecord {
    public final ContentValues mAttributes;
    public final String mClientId;

    public DownloadRecord(final String clientId, final ContentValues attributes) {
        this.mClientId = clientId;
        this.mAttributes = attributes;
    }

    public boolean isMetadata() {
        return this.mAttributes == null;
    }
}
