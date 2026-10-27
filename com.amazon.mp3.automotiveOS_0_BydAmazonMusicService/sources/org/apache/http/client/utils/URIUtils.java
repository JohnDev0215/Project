package org.apache.http.client.utils;

import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.fasterxml.jackson.core.JsonPointer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;
import java.util.Stack;
import org.apache.http.HttpHost;
import org.apache.http.util.Args;
import org.apache.http.util.TextUtils;

/* JADX INFO: loaded from: classes7.dex */
public class URIUtils {
    public static URI rewriteURI(URI uri, HttpHost httpHost, boolean z) throws URISyntaxException {
        Args.notNull(uri, Contexts.OpenSeeMore.uri);
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder uRIBuilder = new URIBuilder(uri);
        if (httpHost != null) {
            uRIBuilder.setScheme(httpHost.getSchemeName());
            uRIBuilder.setHost(httpHost.getHostName());
            uRIBuilder.setPort(httpHost.getPort());
        } else {
            uRIBuilder.setScheme(null);
            uRIBuilder.setHost(null);
            uRIBuilder.setPort(-1);
        }
        if (z) {
            uRIBuilder.setFragment(null);
        }
        if (TextUtils.isEmpty(uRIBuilder.getPath())) {
            uRIBuilder.setPath("/");
        }
        return uRIBuilder.build();
    }

    public static URI rewriteURI(URI uri) throws URISyntaxException {
        Args.notNull(uri, Contexts.OpenSeeMore.uri);
        if (uri.isOpaque()) {
            return uri;
        }
        URIBuilder uRIBuilder = new URIBuilder(uri);
        if (uRIBuilder.getUserInfo() != null) {
            uRIBuilder.setUserInfo(null);
        }
        if (TextUtils.isEmpty(uRIBuilder.getPath())) {
            uRIBuilder.setPath("/");
        }
        if (uRIBuilder.getHost() != null) {
            uRIBuilder.setHost(uRIBuilder.getHost().toLowerCase(Locale.ROOT));
        }
        uRIBuilder.setFragment(null);
        return uRIBuilder.build();
    }

    public static URI resolve(URI uri, URI uri2) {
        URI uriResolve;
        Args.notNull(uri, "Base URI");
        Args.notNull(uri2, "Reference URI");
        String aSCIIString = uri2.toASCIIString();
        if (aSCIIString.startsWith("?")) {
            String aSCIIString2 = uri.toASCIIString();
            int iIndexOf = aSCIIString2.indexOf(63);
            if (iIndexOf > -1) {
                aSCIIString2 = aSCIIString2.substring(0, iIndexOf);
            }
            return URI.create(aSCIIString2 + aSCIIString);
        }
        if (aSCIIString.isEmpty()) {
            String aSCIIString3 = uri.resolve(URI.create("#")).toASCIIString();
            uriResolve = URI.create(aSCIIString3.substring(0, aSCIIString3.indexOf(35)));
        } else {
            uriResolve = uri.resolve(uri2);
        }
        try {
            return normalizeSyntax(uriResolve);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    static URI normalizeSyntax(URI uri) throws URISyntaxException {
        if (uri.isOpaque() || uri.getAuthority() == null) {
            return uri;
        }
        Args.check(uri.isAbsolute(), "Base URI must be absolute");
        URIBuilder uRIBuilder = new URIBuilder(uri);
        String path = uRIBuilder.getPath();
        if (path != null && !path.equals("/")) {
            String[] strArrSplit = path.split("/");
            Stack<String> stack = new Stack();
            for (String str : strArrSplit) {
                if (!str.isEmpty() && !".".equals(str)) {
                    if ("..".equals(str)) {
                        if (!stack.isEmpty()) {
                            stack.pop();
                        }
                    } else {
                        stack.push(str);
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (String str2 : stack) {
                sb.append(JsonPointer.SEPARATOR);
                sb.append(str2);
            }
            if (path.lastIndexOf(47) == path.length() - 1) {
                sb.append(JsonPointer.SEPARATOR);
            }
            uRIBuilder.setPath(sb.toString());
        }
        if (uRIBuilder.getScheme() != null) {
            uRIBuilder.setScheme(uRIBuilder.getScheme().toLowerCase(Locale.ROOT));
        }
        if (uRIBuilder.getHost() != null) {
            uRIBuilder.setHost(uRIBuilder.getHost().toLowerCase(Locale.ROOT));
        }
        return uRIBuilder.build();
    }

    public static HttpHost extractHost(URI uri) {
        int iIndexOf;
        if (uri == null || !uri.isAbsolute()) {
            return null;
        }
        int port = uri.getPort();
        String host = uri.getHost();
        if (host == null && (host = uri.getAuthority()) != null) {
            int iIndexOf2 = host.indexOf(64);
            if (iIndexOf2 >= 0) {
                int i = iIndexOf2 + 1;
                host = host.length() > i ? host.substring(i) : null;
            }
            if (host != null && (iIndexOf = host.indexOf(58)) >= 0) {
                int i2 = iIndexOf + 1;
                int i3 = 0;
                for (int i4 = i2; i4 < host.length() && Character.isDigit(host.charAt(i4)); i4++) {
                    i3++;
                }
                if (i3 > 0) {
                    try {
                        port = Integer.parseInt(host.substring(i2, i3 + i2));
                    } catch (NumberFormatException unused) {
                    }
                }
                host = host.substring(0, iIndexOf);
            }
        }
        String scheme = uri.getScheme();
        if (TextUtils.isBlank(host)) {
            return null;
        }
        try {
            return new HttpHost(host, port, scheme);
        } catch (IllegalArgumentException unused2) {
            return null;
        }
    }

    private URIUtils() {
    }
}
