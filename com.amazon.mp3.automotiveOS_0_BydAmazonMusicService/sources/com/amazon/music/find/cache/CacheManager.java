package com.amazon.music.find.cache;

import android.net.Uri;
import com.amazon.music.find.cache.Cache;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CacheManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JR\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u0006\"\b\b\u0000\u0010\b*\u00020\u0001\"\b\b\u0001\u0010\t*\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\"\b\u0002\u0010\f\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rJR\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u0006\"\b\b\u0000\u0010\b*\u00020\u0001\"\b\b\u0001\u0010\t*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00052\"\b\u0002\u0010\f\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\rJ>\u0010\u0011\u001a\u00020\u000f\"\b\b\u0000\u0010\b*\u00020\u0001\"\b\b\u0001\u0010\t*\u00020\u00012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014H\u0002J.\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u0006\"\b\b\u0000\u0010\b*\u00020\u0001\"\b\b\u0001\u0010\t*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005R\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/music/find/cache/CacheManager;", "", "()V", "mCaches", "", "", "Lcom/amazon/music/find/cache/Cache;", "buildCache", "Key", "Value", "uri", "Landroid/net/Uri;", "configurator", "Lkotlin/Function1;", "Lcom/amazon/music/find/cache/Cache$Builder;", "", "cacheName", "configureBuilder", "builder", "queryParams", "", "getCache", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CacheManager {
    public static final CacheManager INSTANCE = new CacheManager();
    private static final Map<String, Cache<?, ?>> mCaches = new LinkedHashMap();

    private final <Key, Value> void configureBuilder(Cache.Builder<? super Key, Value> builder, Set<String> queryParams) {
    }

    private CacheManager() {
    }

    public static /* synthetic */ Cache buildCache$default(CacheManager cacheManager, Uri uri, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        return cacheManager.buildCache(uri, function1);
    }

    public final <Key, Value> Cache<Key, Value> buildCache(Uri uri, Function1<? super Cache.Builder<? super Key, Value>, Unit> configurator) throws CacheException {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Cache.Builder<? super Key, Value> builder = new Cache.Builder<>();
        String host = uri.getHost();
        if (host != null) {
            Map<String, Cache<?, ?>> map = mCaches;
            if (!map.containsKey(host)) {
                Set<String> queryParameterNames = uri.getQueryParameterNames();
                Intrinsics.checkNotNullExpressionValue(queryParameterNames, "uri.queryParameterNames");
                configureBuilder(builder, queryParameterNames);
                if (configurator != null) {
                    configurator.invoke(builder);
                }
                Cache<? super Key, Value> cacheBuild = builder.build();
                map.put(host, cacheBuild);
                return cacheBuild;
            }
        }
        throw new CacheException("Cache " + ((Object) host) + " already exists");
    }

    public static /* synthetic */ Cache buildCache$default(CacheManager cacheManager, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = null;
        }
        return cacheManager.buildCache(str, function1);
    }

    public final <Key, Value> Cache<Key, Value> buildCache(String cacheName, Function1<? super Cache.Builder<? super Key, Value>, Unit> configurator) {
        Intrinsics.checkNotNullParameter(cacheName, "cacheName");
        Uri uri = Uri.parse(Intrinsics.stringPlus("cache://", cacheName));
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"cache://$cacheName\")");
        return buildCache(uri, configurator);
    }

    public final <Key, Value> Cache<Key, Value> getCache(String cacheName) throws CacheException {
        Intrinsics.checkNotNullParameter(cacheName, "cacheName");
        Object obj = mCaches.get(cacheName);
        Cache<Key, Value> cache = obj instanceof Cache ? (Cache) obj : null;
        if (cache != null) {
            return cache;
        }
        throw new CacheException("Cache " + cacheName + " not found");
    }
}
