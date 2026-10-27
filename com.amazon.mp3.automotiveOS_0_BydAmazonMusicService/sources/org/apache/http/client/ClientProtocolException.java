package org.apache.http.client;

import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public class ClientProtocolException extends IOException {
    public ClientProtocolException() {
    }

    public ClientProtocolException(String str) {
        super(str);
    }

    public ClientProtocolException(Throwable th) {
        initCause(th);
    }
}
