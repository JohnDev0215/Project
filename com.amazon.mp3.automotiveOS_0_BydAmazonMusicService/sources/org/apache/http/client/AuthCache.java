package org.apache.http.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;

/* JADX INFO: loaded from: classes7.dex */
public interface AuthCache {
    AuthScheme get(HttpHost httpHost);

    void put(HttpHost httpHost, AuthScheme authScheme);

    void remove(HttpHost httpHost);
}
