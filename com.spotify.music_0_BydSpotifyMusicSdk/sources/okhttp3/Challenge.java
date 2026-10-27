package okhttp3;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import p.hf2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class Challenge {
    public final String a;
    public final Map b;

    public Challenge(String str, Map map) {
        String lowerCase;
        this.a = str;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (str2 != null) {
                Locale locale = Locale.US;
                v03.i(locale, "US");
                lowerCase = str2.toLowerCase(locale);
                v03.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            } else {
                lowerCase = null;
            }
            linkedHashMap.put(lowerCase, str3);
        }
        Map mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        v03.i(mapUnmodifiableMap, "unmodifiableMap<String?, String>(newAuthParams)");
        this.b = mapUnmodifiableMap;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Challenge) {
            Challenge challenge = (Challenge) obj;
            if (v03.b(challenge.a, this.a) && v03.b(challenge.b, this.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode() + hf2.b(this.a, 899, 31);
    }

    public final String toString() {
        return this.a + " authParams=" + this.b;
    }
}
