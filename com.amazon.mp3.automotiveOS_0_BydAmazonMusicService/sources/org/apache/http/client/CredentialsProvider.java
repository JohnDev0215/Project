package org.apache.http.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;

/* JADX INFO: loaded from: classes7.dex */
public interface CredentialsProvider {
    Credentials getCredentials(AuthScope authScope);
}
