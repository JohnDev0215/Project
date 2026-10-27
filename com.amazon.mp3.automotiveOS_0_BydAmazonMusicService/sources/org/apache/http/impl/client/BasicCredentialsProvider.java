package org.apache.http.impl.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
public class BasicCredentialsProvider implements CredentialsProvider {
    private final ConcurrentHashMap<AuthScope, Credentials> credMap = new ConcurrentHashMap<>();

    private static Credentials matchCredentials(Map<AuthScope, Credentials> map, AuthScope authScope) {
        Credentials credentials = map.get(authScope);
        if (credentials != null) {
            return credentials;
        }
        int i = -1;
        AuthScope authScope2 = null;
        for (AuthScope authScope3 : map.keySet()) {
            int iMatch = authScope.match(authScope3);
            if (iMatch > i) {
                authScope2 = authScope3;
                i = iMatch;
            }
        }
        return authScope2 != null ? map.get(authScope2) : credentials;
    }

    @Override // org.apache.http.client.CredentialsProvider
    public Credentials getCredentials(AuthScope authScope) {
        Args.notNull(authScope, "Authentication scope");
        return matchCredentials(this.credMap, authScope);
    }

    public String toString() {
        return this.credMap.toString();
    }
}
