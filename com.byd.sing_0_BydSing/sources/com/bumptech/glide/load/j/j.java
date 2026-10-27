package com.bumptech.glide.load.j;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: LazyHeaders.java */
/* JADX INFO: loaded from: classes.dex */
public final class j implements h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Map<String, List<i>> f2979c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile Map<String, String> f2980d;

    /* JADX INFO: compiled from: LazyHeaders.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final String f2981a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final Map<String, List<i>> f2982b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f2983c = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Map<String, List<i>> f2984d = f2982b;
        private boolean e = true;

        static {
            String strB = b();
            f2981a = strB;
            HashMap map = new HashMap(2);
            if (!TextUtils.isEmpty(strB)) {
                map.put("User-Agent", Collections.singletonList(new b(strB)));
            }
            f2982b = Collections.unmodifiableMap(map);
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
                if ((cCharAt > 31 || cCharAt == '\t') && cCharAt < 127) {
                    sb.append(cCharAt);
                } else {
                    sb.append('?');
                }
            }
            return sb.toString();
        }

        public j a() {
            this.f2983c = true;
            return new j(this.f2984d);
        }
    }

    /* JADX INFO: compiled from: LazyHeaders.java */
    static final class b implements i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f2985a;

        b(String str) {
            this.f2985a = str;
        }

        @Override // com.bumptech.glide.load.j.i
        public String a() {
            return this.f2985a;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                return this.f2985a.equals(((b) obj).f2985a);
            }
            return false;
        }

        public int hashCode() {
            return this.f2985a.hashCode();
        }

        public String toString() {
            return "StringHeaderFactory{value='" + this.f2985a + "'}";
        }
    }

    j(Map<String, List<i>> map) {
        this.f2979c = Collections.unmodifiableMap(map);
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
        for (Map.Entry<String, List<i>> entry : this.f2979c.entrySet()) {
            String strB = b(entry.getValue());
            if (!TextUtils.isEmpty(strB)) {
                map.put(entry.getKey(), strB);
            }
        }
        return map;
    }

    @Override // com.bumptech.glide.load.j.h
    public Map<String, String> a() {
        if (this.f2980d == null) {
            synchronized (this) {
                if (this.f2980d == null) {
                    this.f2980d = Collections.unmodifiableMap(c());
                }
            }
        }
        return this.f2980d;
    }

    public boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f2979c.equals(((j) obj).f2979c);
        }
        return false;
    }

    public int hashCode() {
        return this.f2979c.hashCode();
    }

    public String toString() {
        return "LazyHeaders{headers=" + this.f2979c + '}';
    }
}
