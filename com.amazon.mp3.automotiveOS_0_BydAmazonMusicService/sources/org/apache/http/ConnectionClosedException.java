package org.apache.http;

import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public class ConnectionClosedException extends IOException {
    public ConnectionClosedException(String str) {
        super(HttpException.clean(str));
    }
}
