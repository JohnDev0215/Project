package org.apache.http;

import java.io.IOException;

/* JADX INFO: loaded from: classes7.dex */
public class NoHttpResponseException extends IOException {
    public NoHttpResponseException(String str) {
        super(HttpException.clean(str));
    }
}
