package com.amazon.music.deeplink;

import android.net.Uri;

/* JADX INFO: loaded from: classes4.dex */
public class UnknownDeeplinkException extends Exception {
    private Uri uri;

    public UnknownDeeplinkException(Uri uri) {
        super("Cannot find matching Uri parser. Uri:" + uri);
        this.uri = uri;
    }

    public Uri getUri() {
        return this.uri;
    }
}
