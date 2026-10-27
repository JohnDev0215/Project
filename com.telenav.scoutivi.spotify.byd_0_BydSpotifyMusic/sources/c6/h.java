package c6;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: Challenge.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Map<String, String> f3719a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3720b;

    public h(String scheme, Map<String, String> authParams) {
        String lowerCase;
        kotlin.jvm.internal.l.e(scheme, "scheme");
        kotlin.jvm.internal.l.e(authParams, "authParams");
        this.f3720b = scheme;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, String> entry : authParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null) {
                Locale US = Locale.US;
                kotlin.jvm.internal.l.d(US, "US");
                lowerCase = key.toLowerCase(US);
                kotlin.jvm.internal.l.d(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            } else {
                lowerCase = null;
            }
            linkedHashMap.put(lowerCase, value);
        }
        Map<String, String> mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        kotlin.jvm.internal.l.d(mapUnmodifiableMap, "unmodifiableMap<String?, String>(newAuthParams)");
        this.f3719a = mapUnmodifiableMap;
    }

    public final Charset a() {
        String str = this.f3719a.get("charset");
        if (str != null) {
            try {
                Charset charsetForName = Charset.forName(str);
                kotlin.jvm.internal.l.d(charsetForName, "Charset.forName(charset)");
                return charsetForName;
            } catch (Exception unused) {
            }
        }
        Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;
        kotlin.jvm.internal.l.d(ISO_8859_1, "ISO_8859_1");
        return ISO_8859_1;
    }

    public final String b() {
        return this.f3719a.get("realm");
    }

    public final String c() {
        return this.f3720b;
    }

    public boolean equals(Object obj) {
        if (obj instanceof h) {
            h hVar = (h) obj;
            if (kotlin.jvm.internal.l.a(hVar.f3720b, this.f3720b) && kotlin.jvm.internal.l.a(hVar.f3719a, this.f3719a)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((899 + this.f3720b.hashCode()) * 31) + this.f3719a.hashCode();
    }

    public String toString() {
        return this.f3720b + " authParams=" + this.f3719a;
    }
}
