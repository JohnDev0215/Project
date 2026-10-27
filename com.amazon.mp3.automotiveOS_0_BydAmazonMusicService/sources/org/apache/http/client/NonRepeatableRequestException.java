package org.apache.http.client;

import org.apache.http.ProtocolException;

/* JADX INFO: loaded from: classes7.dex */
public class NonRepeatableRequestException extends ProtocolException {
    public NonRepeatableRequestException() {
    }

    public NonRepeatableRequestException(String str) {
        super(str);
    }

    public NonRepeatableRequestException(String str, Throwable th) {
        super(str, th);
    }
}
