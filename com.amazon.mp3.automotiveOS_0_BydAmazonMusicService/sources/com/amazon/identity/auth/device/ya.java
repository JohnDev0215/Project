package com.amazon.identity.auth.device;

import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ya {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f681a = "com.amazon.identity.auth.device.ya";

    /* JADX INFO: compiled from: DCP */
    public static class a extends UrlQuerySanitizer {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f682a;

        public a(String str) {
            this.f682a = str;
        }

        @Override // android.net.UrlQuerySanitizer
        public void addSanitizedEntry(String str, String str2) {
            if (str == null) {
                Log.w(ga.a(ya.f681a), "ignoring <null> named parameter to a Sanitizer", new IllegalArgumentException());
                return;
            }
            if (str2 == null) {
                Log.w(ga.a(ya.f681a), "ignoring <null> value to parameter '" + str + "' to a Sanitizer", new IllegalArgumentException());
                return;
            }
            String strTrim = str.trim();
            String strTrim2 = str2.trim();
            if (!strTrim.equals(str) || !strTrim2.equals(str2)) {
                Log.i(ga.a(ya.f681a), "Parameter or value was trimmed on the parameter " + strTrim + " (" + strTrim.equals(str) + ", " + strTrim2.equals(str2) + "). MAP trims the query parameter keys and values in the URL, however auth portal does not accept non-trimmed parameters. Please consider fixing the auth portal parameters passed to MAP.");
            }
            super.addSanitizedEntry(strTrim, strTrim2);
        }

        @Override // android.net.UrlQuerySanitizer
        public String unescape(String str) {
            if (str == null) {
                Log.w(ga.a(ya.f681a), "Unescaping <null> string");
                return null;
            }
            try {
                return URLDecoder.decode(str, this.f682a);
            } catch (UnsupportedEncodingException e) {
                Log.e(ga.a(ya.f681a), this.f682a + " is not supported. Using decoder without encoding.", e);
                return super.unescape(str);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class b {
        public static final String b = "com.amazon.identity.auth.device.ya$b";

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Uri.Builder f683a = new Uri.Builder();

        public b a(String str, String str2) {
            if (str2 != null) {
                try {
                    this.f683a.appendQueryParameter(URLEncoder.encode(str, ParserUtil.UTF_8), URLEncoder.encode(str2, ParserUtil.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    Log.wtf(ga.a(b), "UTF-8 is not supported. This should not happen according to http://developer.android.com/reference/java/nio/charset/Charset.html", e);
                }
                return this;
            }
            Log.w(ga.a(b), "Parameter '" + str + "' has <null> value, will not append parameter to query string.", new IllegalArgumentException());
            return this;
        }
    }

    public static URL a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            Log.e(ga.a(f681a), "Failed to construct URL object.", e);
            return null;
        }
    }

    public static UrlQuerySanitizer b(String str) {
        a aVar = new a(ParserUtil.UTF_8);
        aVar.setAllowUnregisteredParamaters(true);
        aVar.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
        if (str != null) {
            aVar.parseUrl(str);
        } else {
            Log.w(ga.a(f681a), "Attempted to retrive a Sanitizer for a <null> url (string).");
        }
        return aVar;
    }
}
