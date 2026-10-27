package spi;

import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class ki {
    private ki() {
    }

    public static boolean a(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }
}
