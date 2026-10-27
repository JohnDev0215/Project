package com.amazon.mp3.auto;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
public class AutoHelper {
    public static boolean hasMusic(Context context, Uri uri) {
        if (uri == null) {
            return false;
        }
        Cursor cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() > 0) {
                    cursorQuery.close();
                    return true;
                }
            } catch (Throwable th) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return false;
    }
}
