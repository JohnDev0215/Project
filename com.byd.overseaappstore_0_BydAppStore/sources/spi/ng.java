package spi;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ng implements je {
    public final Map<String, List<mg>> b;
    public volatile Map<String, String> c;

    public static final class a {
        public static final Map<String, List<mg>> b;
        public Map<String, List<mg>> a = b;

        static {
            String property = System.getProperty("http.agent");
            if (!TextUtils.isEmpty(property)) {
                int length = property.length();
                StringBuilder sb = new StringBuilder(property.length());
                for (int i = 0; i < length; i++) {
                    char cCharAt = property.charAt(i);
                    if ((cCharAt <= 31 && cCharAt != '\t') || cCharAt >= 127) {
                        cCharAt = '?';
                    }
                    sb.append(cCharAt);
                }
                property = sb.toString();
            }
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(property)) {
                map.put("User-Agent", Collections.singletonList(new b(property)));
            }
            b = Collections.unmodifiableMap(map);
        }
    }

    public static final class b implements mg {

        @NonNull
        public final String a;

        public b(@NonNull String str) {
            this.a = str;
        }

        @Override // spi.mg
        public final String a() {
            return this.a;
        }

        public final boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.a.equals(((b) obj).a);
            }
            return false;
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        public final String toString() {
            StringBuilder sbK = e.k("StringHeaderFactory{value='");
            sbK.append(this.a);
            sbK.append('\'');
            sbK.append('}');
            return sbK.toString();
        }
    }

    public ng(Map<String, List<mg>> map) {
        this.b = Collections.unmodifiableMap(map);
    }

    @Override // spi.je
    public final Map<String, String> a() {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = Collections.unmodifiableMap(b());
                }
            }
        }
        return this.c;
    }

    public final Map<String, String> b() {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<mg>> entry : this.b.entrySet()) {
            List<mg> value = entry.getValue();
            StringBuilder sb = new StringBuilder();
            int size = value.size();
            for (int i = 0; i < size; i++) {
                String strA = value.get(i).a();
                if (!TextUtils.isEmpty(strA)) {
                    sb.append(strA);
                    if (i != value.size() - 1) {
                        sb.append(',');
                    }
                }
            }
            String string = sb.toString();
            if (!TextUtils.isEmpty(string)) {
                map.put(entry.getKey(), string);
            }
        }
        return map;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ng) {
            return this.b.equals(((ng) obj).b);
        }
        return false;
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        StringBuilder sbK = e.k("LazyHeaders{headers=");
        sbK.append(this.b);
        sbK.append('}');
        return sbK.toString();
    }
}
