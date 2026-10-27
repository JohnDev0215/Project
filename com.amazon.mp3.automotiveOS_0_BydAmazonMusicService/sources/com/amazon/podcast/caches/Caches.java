package com.amazon.podcast.caches;

import Podcast.JumpBackInInterface.v1_0.InvalidateJumpBackInCacheMethod;
import Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElement;
import Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethod;
import SOACacheInterface.v1_0.InvalidateCacheMethod;
import SOACacheInterface.v1_0.WriteCacheElement;
import SOACacheInterface.v1_0.WriteCacheMethod;
import SOACoreInterface.v1_0.Method;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes5.dex */
public enum Caches implements MethodCallback {
    INSTANCE;

    private static final String DETAIL_TEMPLATE_FEATURED_ITEM_OWNER = "detailTemplateFeaturedItemCache";
    private static final String JUMP_BACK_IN_OWNER = "jumpBackIn";
    private static final String LATEST_TEMPLATE_FEATURED_ITEM_OWNER = "latestTemplateFeaturedItemCache";
    private static final String PODCAST_DETAIL_TEMPLATE_FEATURED_ITEM_OWNER = "podcastDetailTemplateFeaturedItemCache";
    private CacheItem<JumpBackInWriteCacheElement> jumpBackInCache;
    private CacheItem<FeaturedItemWriteCacheElement> latestTemplateV2FeaturedItemCache;
    private CacheItems<Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement, InvalidateFeaturedItemMethod> podcastDetailTemplateV2FeaturedItemCache;

    public void init(final Engine engine) {
        this.jumpBackInCache = new CacheItem<>(engine, JUMP_BACK_IN_OWNER);
        this.latestTemplateV2FeaturedItemCache = new CacheItem<>(engine, LATEST_TEMPLATE_FEATURED_ITEM_OWNER);
        this.podcastDetailTemplateV2FeaturedItemCache = new CacheItems<>(engine, PODCAST_DETAIL_TEMPLATE_FEATURED_ITEM_OWNER);
        HashSet hashSet = new HashSet();
        hashSet.add(WriteCacheMethod.class.getCanonicalName());
        hashSet.add(JumpBackInWriteCacheElement.class.getCanonicalName());
        hashSet.add(FeaturedItemWriteCacheElement.class.getCanonicalName());
        hashSet.add(Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement.class.getCanonicalName());
        hashSet.add(InvalidateCacheMethod.class.getCanonicalName());
        hashSet.add(InvalidateJumpBackInCacheMethod.class.getCanonicalName());
        hashSet.add(Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethod.class.getCanonicalName());
        hashSet.add(InvalidateFeaturedItemMethod.class.getCanonicalName());
        engine.registerMethods(Collections.emptySet(), hashSet, this);
    }

    public CacheItem jumpBackInCache() {
        return this.jumpBackInCache;
    }

    public CacheItem latestTemplateV2FeaturedItemCache() {
        return this.latestTemplateV2FeaturedItemCache;
    }

    public CacheItems podcastDetailTemplateV2FeaturedItemCache() {
        return this.podcastDetailTemplateV2FeaturedItemCache;
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof WriteCacheMethod) {
            write((WriteCacheMethod) method);
        } else if (method instanceof InvalidateCacheMethod) {
            invalidate((InvalidateCacheMethod) method);
        }
        listener.onMethodExecuted(method);
    }

    private void write(final WriteCacheMethod method) {
        WriteCacheElement element = method.getElement();
        if (element instanceof JumpBackInWriteCacheElement) {
            this.jumpBackInCache.write((JumpBackInWriteCacheElement) element);
        } else if (element instanceof FeaturedItemWriteCacheElement) {
            this.latestTemplateV2FeaturedItemCache.write((FeaturedItemWriteCacheElement) element);
        } else if (element instanceof Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement) {
            this.podcastDetailTemplateV2FeaturedItemCache.write((Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement) element);
        }
    }

    private void invalidate(final InvalidateCacheMethod method) {
        if (method instanceof InvalidateJumpBackInCacheMethod) {
            this.jumpBackInCache.invalidate();
        } else if (method instanceof Podcast.Touch.LatestTemplateInterface.v2_0.InvalidateFeaturedItemMethod) {
            this.latestTemplateV2FeaturedItemCache.invalidate();
        } else if (method instanceof InvalidateFeaturedItemMethod) {
            this.podcastDetailTemplateV2FeaturedItemCache.invalidate((InvalidateFeaturedItemMethod) method);
        }
    }

    public final void clear() {
        CacheItem<JumpBackInWriteCacheElement> cacheItem = this.jumpBackInCache;
        if (cacheItem != null) {
            cacheItem.clear();
        }
        CacheItem<FeaturedItemWriteCacheElement> cacheItem2 = this.latestTemplateV2FeaturedItemCache;
        if (cacheItem2 != null) {
            cacheItem2.clear();
        }
        CacheItems<Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement, InvalidateFeaturedItemMethod> cacheItems = this.podcastDetailTemplateV2FeaturedItemCache;
        if (cacheItems != null) {
            cacheItems.clear();
        }
    }
}
