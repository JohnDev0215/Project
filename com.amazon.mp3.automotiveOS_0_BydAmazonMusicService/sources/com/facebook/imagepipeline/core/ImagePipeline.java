package com.facebook.imagepipeline.core;

import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes3.dex */
@ThreadSafe
public class ImagePipeline {
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;

    @Nullable
    private final CallerContextVerifier mCallerContextVerifier;
    private final ImagePipelineConfigInterface mConfig;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter = new AtomicLong();
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final Supplier<Boolean> mLazyDataSource;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final RequestListener2 mRequestListener2;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    private final Supplier<Boolean> mSuppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> requestListeners, Set<RequestListener2> requestListener2s, Supplier<Boolean> isPrefetchEnabledSupplier, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache mainBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> suppressBitmapPrefetchingSupplier, Supplier<Boolean> lazyDataSource, @Nullable CallerContextVerifier callerContextVerifier, ImagePipelineConfigInterface config) {
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(requestListeners);
        this.mRequestListener2 = new ForwardingRequestListener2(requestListener2s);
        this.mIsPrefetchEnabledSupplier = isPrefetchEnabledSupplier;
        this.mBitmapMemoryCache = bitmapMemoryCache;
        this.mEncodedMemoryCache = encodedMemoryCache;
        this.mMainBufferedDiskCache = mainBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
        this.mSuppressBitmapPrefetchingSupplier = suppressBitmapPrefetchingSupplier;
        this.mLazyDataSource = lazyDataSource;
        this.mCallerContextVerifier = callerContextVerifier;
        this.mConfig = config;
    }

    public String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }

    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, Object callerContext) {
        return fetchDecodedImage(imageRequest, callerContext, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext) {
        return fetchDecodedImage(imageRequest, callerContext, ImageRequest.RequestLevel.FULL_FETCH);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit) {
        return fetchDecodedImage(imageRequest, callerContext, lowestPermittedRequestLevelOnSubmit, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, @Nullable RequestListener requestListener) {
        return fetchDecodedImage(imageRequest, callerContext, lowestPermittedRequestLevelOnSubmit, requestListener, null);
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, @Nullable RequestListener requestListener, @Nullable String uiComponentId) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, lowestPermittedRequestLevelOnSubmit, callerContext, requestListener, uiComponentId);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext) {
        return prefetchToDiskCache(imageRequest, callerContext, Priority.MEDIUM);
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, Priority priority) {
        return prefetchToDiskCache(imageRequest, callerContext, priority, null);
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, Priority priority, @Nullable RequestListener requestListener) {
        if (!this.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, priority, requestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public void clearMemoryCaches() {
        Predicate<CacheKey> predicate = new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.5
            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey key) {
                return true;
            }
        };
        this.mBitmapMemoryCache.removeAll(predicate);
        this.mEncodedMemoryCache.removeAll(predicate);
    }

    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }

    public void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public boolean isInBitmapMemoryCache(final Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mBitmapMemoryCache.contains(predicateForUri(uri));
    }

    public MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        return this.mBitmapMemoryCache;
    }

    public boolean isInBitmapMemoryCache(final ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<CloseableImage> closeableReference = this.mBitmapMemoryCache.get(this.mCacheKeyFactory.getBitmapCacheKey(imageRequest, null));
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public boolean isInDiskCacheSync(final Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public boolean isInDiskCacheSync(final Uri uri, final ImageRequest.CacheChoice cacheChoice) {
        return isInDiskCacheSync(ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build());
    }

    public boolean isInDiskCacheSync(final ImageRequest imageRequest) {
        CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        int i = AnonymousClass9.$SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[imageRequest.getCacheChoice().ordinal()];
        if (i == 1) {
            return this.mMainBufferedDiskCache.diskCheckSync(encodedCacheKey);
        }
        if (i != 2) {
            return false;
        }
        return this.mSmallImageBufferedDiskCache.diskCheckSync(encodedCacheKey);
    }

    /* JADX INFO: renamed from: com.facebook.imagepipeline.core.ImagePipeline$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice;

        static {
            int[] iArr = new int[ImageRequest.CacheChoice.values().length];
            $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice = iArr;
            try {
                iArr[ImageRequest.CacheChoice.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$imagepipeline$request$ImageRequest$CacheChoice[ImageRequest.CacheChoice.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, @Nullable RequestListener requestListener, @Nullable String uiComponentId) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        }
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(callerContext, false);
        }
        try {
            try {
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producerSequence, new SettableProducerContext(imageRequest, generateUniqueFutureId(), uiComponentId, internalRequestListener, callerContext, ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit), false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.mConfig), internalRequestListener);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceCreate;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e);
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return dataSourceImmediateFailedDataSource;
            }
        } catch (Throwable th) {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
            throw th;
        }
    }

    private DataSource<Void> submitPrefetchRequest(Producer<Void> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, Priority priority, @Nullable RequestListener requestListener) {
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.mRequestListener2);
        CallerContextVerifier callerContextVerifier = this.mCallerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(callerContext, true);
        }
        try {
            return ProducerToDataSourceAdapter.create(producerSequence, new SettableProducerContext(imageRequest, generateUniqueFutureId(), internalRequestListener, callerContext, ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit), true, false, priority, this.mConfig), internalRequestListener);
        } catch (Exception e) {
            return DataSources.immediateFailedDataSource(e);
        }
    }

    public RequestListener getRequestListenerForRequest(ImageRequest imageRequest, @Nullable RequestListener requestListener) {
        if (requestListener == null) {
            if (imageRequest.getRequestListener() == null) {
                return this.mRequestListener;
            }
            return new ForwardingRequestListener(this.mRequestListener, imageRequest.getRequestListener());
        }
        if (imageRequest.getRequestListener() == null) {
            return new ForwardingRequestListener(this.mRequestListener, requestListener);
        }
        return new ForwardingRequestListener(this.mRequestListener, requestListener, imageRequest.getRequestListener());
    }

    private Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate<CacheKey>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.8
            @Override // com.facebook.common.internal.Predicate
            public boolean apply(CacheKey key) {
                return key.containsUri(uri);
            }
        };
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }
}
