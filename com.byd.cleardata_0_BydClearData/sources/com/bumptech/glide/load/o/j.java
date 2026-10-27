package com.bumptech.glide.load.o;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class j implements h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, List<i>> f2233c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile Map<String, String> f2234d;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final String f2235a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final Map<String, List<i>> f2236b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2237c = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Map<String, List<i>> f2238d = f2236b;
        private boolean e = true;

        static {
            String strB = b();
            f2235a = strB;
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(strB)) {
                map.put("User-Agent", Collections.singletonList(new b(strB)));
            }
            f2236b = Collections.unmodifiableMap(map);
        }

        static String b() {
            String property = System.getProperty("http.agent");
            if (TextUtils.isEmpty(property)) {
                return property;
            }
            int length = property.length();
            StringBuilder sb = new StringBuilder(property.length());
            for (int i = 0; i < length; i++) {
                char cCharAt = property.charAt(i);
                if ((cCharAt <= 31 && cCharAt != '\t') || cCharAt >= 127) {
                    cCharAt = '?';
                }
                sb.append(cCharAt);
            }
            return sb.toString();
        }

        public j a() {
            this.f2237c = true;
            return new j(this.f2238d);
        }
    }

    static final class b implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f2239a;

        b(String str) {
            this.f2239a = str;
        }

        @Override // com.bumptech.glide.load.o.i
        public String a() {
            return this.f2239a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.f2239a.equals(((b) obj).f2239a);
            }
            return false;
        }

        public int hashCode() {
            return this.f2239a.hashCode();
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.f2239a + "'}";
        }
    }

    j(Map<String, List<i>> map) {
        this.f2233c = Collections.unmodifiableMap(map);
    }

    private String b(List<i> list) {
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String strA = list.get(i).a();
            if (!TextUtils.isEmpty(strA)) {
                sb.append(strA);
                if (i != list.size() - 1) {
                    sb.append(',');
                }
            }
        }
        return sb.toString();
    }

    private Map<String, String> c() {
        HashMap map = new HashMap();
        for (Map.Entry<String, List<i>> entry : this.f2233c.entrySet()) {
            String strB = b(entry.getValue());
            if (!TextUtils.isEmpty(strB)) {
                map.put(entry.getKey(), strB);
            }
        }
        return map;
    }

    @Override // com.bumptech.glide.load.o.h
    public Map<String, String> a() {
        if (this.f2234d == null) {
            synchronized (this) {
                if (this.f2234d == null) {
                    this.f2234d = Collections.unmodifiableMap(c());
                }
            }
        }
        return this.f2234d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f2233c.equals(((j) obj).f2233c);
        }
        return false;
    }

    public int hashCode() {
        return this.f2233c.hashCode();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.f2233c + '}';
    }
}
