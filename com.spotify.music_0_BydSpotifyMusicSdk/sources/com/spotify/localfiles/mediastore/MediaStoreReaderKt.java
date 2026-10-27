package com.spotify.localfiles.mediastore;

import android.annotation.SuppressLint;
import android.database.Cursor;

/* JADX INFO: loaded from: classes.dex */
public final class MediaStoreReaderKt {

    @SuppressLint({"InlinedApi"})
    private static String DURATION = "duration";
    private static final int MILLISECONDS_IN_SECOND = 1000;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInt(Cursor cursor, int i) {
        return cursor.getType(i) == 1;
    }
}
