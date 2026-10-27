package org.apache.http.client;

import org.apache.http.HttpResponse;

/* JADX INFO: loaded from: classes7.dex */
public interface ConnectionBackoffStrategy {
    boolean shouldBackoff(Throwable th);

    boolean shouldBackoff(HttpResponse httpResponse);
}
