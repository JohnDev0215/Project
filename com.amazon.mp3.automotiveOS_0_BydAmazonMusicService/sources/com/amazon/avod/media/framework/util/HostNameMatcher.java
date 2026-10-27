package com.amazon.avod.media.framework.util;

import com.google.common.base.Preconditions;
import java.net.MalformedURLException;
import java.net.URL;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class HostNameMatcher {
    private final Iterable<String> mDomains;

    public HostNameMatcher(@Nonnull Iterable<String> iterable) {
        this.mDomains = (Iterable) Preconditions.checkNotNull(iterable);
    }

    public boolean matches(@Nonnull String str) throws MalformedURLException {
        return matches(new URL((String) Preconditions.checkNotNull(str)));
    }

    public boolean matches(@Nonnull URL url) {
        Preconditions.checkNotNull(url);
        String host = url.getHost();
        for (String str : this.mDomains) {
            if (host.endsWith("." + str) || host.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
