package u1;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: LazyHeaders.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, List<i>> f9594c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile Map<String, String> f9595d;

    /* JADX INFO: compiled from: LazyHeaders.java */
    public static final class a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static final String f9596d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final Map<String, List<i>> f9597e;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f9598a = true;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Map<String, List<i>> f9599b = f9597e;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f9600c = true;

        static {
            String strB = b();
            f9596d = strB;
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(strB)) {
                map.put("User-Agent", Collections.singletonList(new b(strB)));
            }
            f9597e = Collections.unmodifiableMap(map);
        }

        static String b() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i7 = 0; i7 < length; i7++) {
                char cCharAt = property.charAt(i7);
                if ((cCharAt > 31 || cCharAt == '\t') && cCharAt < 127) {
                    sb.append(cCharAt);
                } else {
                    sb.append('?');
                }
            }
            return sb.toString();
        }

        public j a() {
            this.f9598a = true;
            return new j(this.f9599b);
        }
    }

    /* JADX INFO: compiled from: LazyHeaders.java */
    static final class b implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f9601a;

        b(String str) {
            this.f9601a = str;
        }

        @Override // u1.i
        public String a() {
            return this.f9601a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.f9601a.equals(((b) obj).f9601a);
            }
            return false;
        }

        public int hashCode() {
            return this.f9601a.hashCode();
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.f9601a + "'}";
        }
    }

    j(Map<String, List<i>> map) {
        this.f9594c = Collections.unmodifiableMap(map);
    }

    private String b(List<i> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            String strA = list.get(i7).a();
            if (!TextUtils.isEmpty(strA)) {
                sb.append(strA);
                if (i7 != list.size() - 1) {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    private Map<String, String> c() {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<i>> entry : this.f9594c.entrySet()) {
            String strB = b(entry.getValue());
            if (!TextUtils.isEmpty(strB)) {
                map.put(entry.getKey(), strB);
            }
        }
        return map;
    }

    @Override // u1.h
    public Map<String, String> a() {
        if (this.f9595d == null) {
            synchronized (this) {
                if (this.f9595d == null) {
                    this.f9595d = Collections.unmodifiableMap(c());
                }
            }
        }
        return this.f9595d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f9594c.equals(((j) obj).f9594c);
        }
        return false;
    }

    public int hashCode() {
        return this.f9594c.hashCode();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.f9594c + '}';
    }
}
