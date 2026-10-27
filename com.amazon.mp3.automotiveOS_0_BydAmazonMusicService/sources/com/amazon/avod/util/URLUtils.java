package com.amazon.avod.util;

import android.net.Uri;
import com.amazon.bolthttp.Request;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class URLUtils {
    private URLUtils() {
    }

    @Nonnull
    public static String createEncodedURL(@Nonnull String str) throws MalformedURLException, URISyntaxException {
        Preconditions.checkNotNull(str);
        URL url = new URL(str);
        return new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), url.getRef()).toASCIIString();
    }

    @Nonnull
    public static ImmutableMap<String, String> getRequestParameters(Request<?> request) {
        Uri uri = Uri.parse(request.getUrl().toString());
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (String str : queryParameterNames) {
            builder.put(str, uri.getQueryParameter(str));
        }
        return builder.build();
    }

    @Nonnull
    public static URL createUrlOrThrow(@Nonnull String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Nullable
    public static URL nullWhenMalformed(@Nullable String str) {
        if (str == null) {
            return null;
        }
        try {
            return new URL(str);
        } catch (IllegalArgumentException | MalformedURLException unused) {
            return null;
        }
    }
}
