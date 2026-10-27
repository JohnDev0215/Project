package org.apache.http.conn.scheme;

import com.amazonaws.http.HttpHeader;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpHost;
import org.apache.http.util.Args;

/* JADX INFO: loaded from: classes7.dex */
@Deprecated
public final class SchemeRegistry {
    private final ConcurrentHashMap<String, Scheme> registeredSchemes = new ConcurrentHashMap<>();

    public final Scheme getScheme(String str) {
        Scheme scheme = get(str);
        if (scheme != null) {
            return scheme;
        }
        throw new IllegalStateException("Scheme '" + str + "' not registered.");
    }

    public final Scheme getScheme(HttpHost httpHost) {
        Args.notNull(httpHost, HttpHeader.HOST);
        return getScheme(httpHost.getSchemeName());
    }

    public final Scheme get(String str) {
        Args.notNull(str, "Scheme name");
        return this.registeredSchemes.get(str);
    }

    public final Scheme register(Scheme scheme) {
        Args.notNull(scheme, "Scheme");
        return this.registeredSchemes.put(scheme.getName(), scheme);
    }
}
