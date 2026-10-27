package org.apache.http.params;

import org.apache.http.config.MessageConstraints;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public final class HttpParamConfig {
    private HttpParamConfig() {
    }

    public static MessageConstraints getMessageConstraints(HttpParams httpParams) {
        return MessageConstraints.custom().setMaxHeaderCount(httpParams.getIntParameter("http.connection.max-header-count", -1)).setMaxLineLength(httpParams.getIntParameter("http.connection.max-line-length", -1)).build();
    }
}
