package com.amazon.mp3.store.html5.bridge;

import android.text.TextUtils;
import com.amazon.mp3.environment.Environment;

/* JADX INFO: loaded from: classes4.dex */
public enum CollectionType {
    ALBUM("album"),
    PLAYLIST(Environment.PLAYLIST_PATH);

    String mStringValue;

    CollectionType(String strValue) {
        this.mStringValue = strValue;
    }

    public static CollectionType getCollectionType(String refineTypeStr) {
        if (TextUtils.isEmpty(refineTypeStr)) {
            return null;
        }
        for (CollectionType collectionType : values()) {
            if (collectionType.mStringValue.equals(refineTypeStr)) {
                return collectionType;
            }
        }
        return null;
    }
}
