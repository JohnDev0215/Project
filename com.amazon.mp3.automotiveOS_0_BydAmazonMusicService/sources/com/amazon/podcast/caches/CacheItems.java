package com.amazon.podcast.caches;

import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.InvalidateFeaturedItemMethod;
import SOACacheInterface.v1_0.InvalidateCacheMethod;
import SOACacheInterface.v1_0.WriteCacheElement;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.soa.core.Engine;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class CacheItems<T extends WriteCacheElement, R extends InvalidateCacheMethod> {
    private static String EMPTY_STRING = "";
    private static String ownerId;
    private Map<String, MutableLiveData<T>> cacheLookup;
    private Engine engine;

    public CacheItems(final Engine engine, final String ownerId2) {
        this.engine = engine;
        ownerId = ownerId2;
        engine.registerOwner(ownerId2);
        this.cacheLookup = new HashMap();
    }

    public LiveData<T> read(final String podcastId) {
        if (this.cacheLookup.get(podcastId) == null) {
            this.cacheLookup.put(podcastId, new MutableLiveData<>());
        }
        return this.cacheLookup.get(podcastId);
    }

    public void write(final T writeElement) {
        String podcastId = EMPTY_STRING;
        if (writeElement instanceof FeaturedItemWriteCacheElement) {
            podcastId = ((FeaturedItemWriteCacheElement) writeElement).getPodcastId();
        }
        if (this.cacheLookup.get(podcastId) == null) {
            if (podcastId.isEmpty()) {
                return;
            } else {
                this.cacheLookup.put(podcastId, new MutableLiveData<>());
            }
        }
        this.cacheLookup.get(podcastId).postValue(writeElement);
    }

    public void invalidate(final R method) {
        String id = EMPTY_STRING;
        if (method instanceof InvalidateFeaturedItemMethod) {
            id = ((InvalidateFeaturedItemMethod) method).getId();
        }
        MutableLiveData<T> mutableLiveData = this.cacheLookup.get(id);
        if (mutableLiveData == null) {
            return;
        }
        this.engine.handleMethods(ownerId, mutableLiveData.getValue().getOnInvalidated());
    }

    public void clear() {
        this.cacheLookup.clear();
    }
}
