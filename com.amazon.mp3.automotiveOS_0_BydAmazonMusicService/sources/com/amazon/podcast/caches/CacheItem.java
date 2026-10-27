package com.amazon.podcast.caches;

import SOACacheInterface.v1_0.WriteCacheElement;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.soa.core.Engine;

/* JADX INFO: loaded from: classes5.dex */
public class CacheItem<T extends WriteCacheElement> {
    private MutableLiveData<T> elementLiveData;
    private Engine engine;
    private String ownerId;

    public CacheItem(final Engine engine, final String ownerId) {
        this.engine = engine;
        this.ownerId = ownerId;
        engine.registerOwner(ownerId);
        this.elementLiveData = new MutableLiveData<>();
    }

    public LiveData<T> read() {
        return this.elementLiveData;
    }

    public void write(final T element) {
        this.elementLiveData.postValue(element);
    }

    public void invalidate() {
        T value = this.elementLiveData.getValue();
        if (value == null) {
            return;
        }
        this.engine.handleMethods(this.ownerId, value.getOnInvalidated());
    }

    public final void clear() {
        this.elementLiveData.postValue(null);
    }
}
