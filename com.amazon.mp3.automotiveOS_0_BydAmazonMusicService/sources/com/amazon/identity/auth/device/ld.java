package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.kcpsdk.common.HttpVerb;
import com.amazon.identity.kcpsdk.common.WebProtocol;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ld {
    public static final String m = "com.amazon.identity.auth.device.ld";
    public String d;
    public String e;
    public String f;
    public String g;
    public boolean j;
    public String k;
    public String l;
    public String c = "http";
    public HttpVerb h = HttpVerb.HttpVerbGet;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, String> f423a = new HashMap();
    public final List<String> b = new ArrayList();
    public byte[] i = new byte[0];

    public static boolean a(String str) {
        HttpVerb httpVerb = HttpVerb.HttpVerbConnect;
        new HashMap();
        new ArrayList();
        if (str == null || str.equals("")) {
            return false;
        }
        try {
            URI uri = new URI(str);
            uri.getScheme();
            uri.getHost();
            int port = uri.getPort();
            if (port != -1) {
                Integer.toString(port);
            }
            String rawPath = uri.getRawPath();
            if (rawPath != null && !"".equals(rawPath)) {
                rawPath.startsWith("/");
            }
            uri.getRawQuery();
            return true;
        } catch (URISyntaxException e) {
            Log.e(ga.a(m), "tryToParseUrl: URL is malformed: " + e.getMessage());
            return false;
        }
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        String str = this.c;
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("://");
        String str2 = this.d;
        sb.append(str2 != null ? str2 : "");
        if (this.e != null) {
            sb.append(":" + this.e);
        }
        sb.append(a());
        String string = sb.toString();
        this.l = string;
        return string;
    }

    public void c(String str) {
        if (!str.equals("") && !str.startsWith("/")) {
            str = "/" + str;
        }
        try {
            this.f = new URI("http", "www.amazon.com", str, null).getRawPath();
        } catch (URISyntaxException e) {
            Log.e(ga.a(m), "setPath: Could not set path because of URISyntaxException: " + e.getMessage());
            throw new IllegalArgumentException(e);
        }
    }

    public final boolean d(String str) {
        if (str != null && !str.equals("")) {
            try {
                URI uri = new URI(str);
                this.c = uri.getScheme();
                this.d = uri.getHost();
                int port = uri.getPort();
                if (port != -1) {
                    this.e = Integer.toString(port);
                } else {
                    this.e = null;
                }
                String rawPath = uri.getRawPath();
                this.f = rawPath;
                if (rawPath != null && !"".equals(rawPath) && !this.f.startsWith("/")) {
                    this.f = "/" + this.f;
                }
                this.g = uri.getRawQuery();
                return true;
            } catch (URISyntaxException e) {
                Log.e(ga.a(m), "tryToParseUrl: URL is malformed: " + e.getMessage());
            }
        }
        return false;
    }

    public void b(String str, String str2) {
        if (str == null || "".equals(str)) {
            Log.w(ga.a(m), "setHeader: failed because the given header name was null or empty.");
            return;
        }
        if (str2 == null) {
            this.b.remove(str);
            this.f423a.remove(str.toLowerCase(Locale.US));
        } else {
            String strReplace = str2.replace("\n", "\n ");
            this.b.add(str);
            this.f423a.put(str.toLowerCase(Locale.US), strReplace);
        }
    }

    public void b(String str) {
        try {
            this.i = str.getBytes(ParserUtil.UTF_8);
        } catch (UnsupportedEncodingException e) {
            Log.e(ga.a(m), "setBody: UnsupportedEncodingException error: " + e.getMessage());
        }
    }

    public String a() {
        String str = this.f;
        String str2 = "";
        if (str == null) {
            str = "";
        }
        if (this.g != null) {
            str2 = "?" + this.g;
        }
        String str3 = str + str2;
        this.k = str3;
        return str3;
    }

    public String a(int i) {
        if (i >= 0 && i < this.b.size()) {
            return this.b.get(i);
        }
        Log.e(ga.a(m), "getHeader: index is out of range");
        return null;
    }

    public void a(WebProtocol webProtocol) {
        if (webProtocol != null) {
            this.c = webProtocol.getValue();
        } else {
            this.c = null;
        }
    }

    public void a(String str, String str2) {
        if (str != null && !"".equals(str) && str2 != null) {
            if (this.g != null) {
                this.g += "&";
            } else {
                this.g = "";
            }
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(this.g);
                sb.append(String.format("%s=%s", URLEncoder.encode(str, ParserUtil.UTF_8), URLEncoder.encode(str2, ParserUtil.UTF_8)));
                this.g = sb.toString();
                return;
            } catch (UnsupportedEncodingException e) {
                Log.e(ga.a(m), "addQueryParameter: Could not add query parameter because of UnsupportedEncodingException: " + e.getMessage());
                return;
            }
        }
        Log.e(ga.a(m), "addQueryParameter: could not add query parameter because the supplied arguments are invalid (null or empty name or null value).");
    }
}
