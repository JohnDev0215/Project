package com.google.i18n.phonenumbers.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class RegexCache {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LRUCache<String, Pattern> f4145a;

    private static class LRUCache<K, V> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private LinkedHashMap<K, V> f4146a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f4147b;

        public LRUCache(int i) {
            this.f4147b = i;
            this.f4146a = new LinkedHashMap<K, V>(((i * 4) / 3) + 1, 0.75f, true) { // from class: com.google.i18n.phonenumbers.internal.RegexCache.LRUCache.1
                @Override // java.util.LinkedHashMap
                protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
                    return size() > LRUCache.this.f4147b;
                }
            };
        }

        public synchronized V b(K k) {
            return this.f4146a.get(k);
        }

        public synchronized void c(K k, V v) {
            this.f4146a.put(k, v);
        }
    }

    public RegexCache(int i) {
        this.f4145a = new LRUCache<>(i);
    }

    public Pattern a(String str) {
        Pattern patternB = this.f4145a.b(str);
        if (patternB != null) {
            return patternB;
        }
        Pattern patternCompile = Pattern.compile(str);
        this.f4145a.c(str, patternCompile);
        return patternCompile;
    }
}
