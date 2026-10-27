package com.amazon.identity.mobi.common.utils;

import android.text.TextUtils;
import android.util.Log;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class UrlCommonUtils {
    private static final Pattern AMAZON_HOST_PATTERN = Pattern.compile("(^|\\.)amazon\\.(ae|ca|cn|com|de|eg|es|eu|fr|in|it|nl|pl|sa|se|sg|co\\.(jp|uk)|com\\.(au|br|mx|sg|tr|co))$");
    private static final Pattern PRIME_VIDEO_HOST_PATTERN = Pattern.compile("(^|\\.)primevideo\\.com$");
    private static final Pattern SOUQ_HOST_PATTERN = Pattern.compile("(^|\\.)souq\\.com$");

    private UrlCommonUtils() {
    }

    public static URL createUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            Log.e("UrlCommonUtils", "Failed to construct URL object.", e);
            return null;
        }
    }

    public static boolean isAmazonOwnedDomain(String str) {
        try {
            String host = new URL(str).getHost();
            if (host != null) {
                return AMAZON_HOST_PATTERN.matcher(host).find() || PRIME_VIDEO_HOST_PATTERN.matcher(host).find() || SOUQ_HOST_PATTERN.matcher(host).find();
            }
            return false;
        } catch (MalformedURLException unused) {
            return false;
        }
    }
}
