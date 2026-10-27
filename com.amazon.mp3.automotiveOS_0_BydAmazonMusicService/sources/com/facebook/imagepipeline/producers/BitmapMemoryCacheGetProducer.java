package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;

/* JADX INFO: loaded from: classes5.dex */
public class BitmapMemoryCacheGetProducer extends BitmapMemoryCacheProducer {
    @Override // com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer
    protected String getOriginSubcategory() {
        return "pipe_ui";
    }

    @Override // com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer
    protected String getProducerName() {
        return "BitmapMemoryCacheGetProducer";
    }

    @Override // com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer
    protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(final Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey, boolean isMemoryCacheEnabled) {
        return consumer;
    }

    public BitmapMemoryCacheGetProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> inputProducer) {
        super(memoryCache, cacheKeyFactory, inputProducer);
    }
}
