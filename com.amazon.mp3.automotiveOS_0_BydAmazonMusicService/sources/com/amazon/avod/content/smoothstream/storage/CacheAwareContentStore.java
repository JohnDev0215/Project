package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.MediaComponentContentStore;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class CacheAwareContentStore extends AbstractDecoratingContentStore {
    private static final ContentSessionType CACHE_SESSION_TYPE_OVERRIDE = ContentSessionType.CONTENT_CACHE;
    private final MediaComponentContentStore mFileBackedStore;
    private final ManifestContentStore mManifestContentStore;
    private final MediaComponentContentStore mMemoryBackedStore;
    private final boolean shouldUseManifestContentStore;

    public CacheAwareContentStore(@Nonnull MediaComponentContentStore mediaComponentContentStore, @Nonnull MediaComponentContentStore mediaComponentContentStore2, @Nonnull ManifestContentStore manifestContentStore) {
        this(mediaComponentContentStore, mediaComponentContentStore2, manifestContentStore, SmoothStreamingPlaybackConfig.INSTANCE.shouldUseManifestContentStore());
    }

    CacheAwareContentStore(@Nonnull MediaComponentContentStore mediaComponentContentStore, @Nonnull MediaComponentContentStore mediaComponentContentStore2, @Nonnull ManifestContentStore manifestContentStore, boolean z) {
        this.mMemoryBackedStore = (MediaComponentContentStore) Preconditions.checkNotNull(mediaComponentContentStore, "memoryBackedStore");
        this.mFileBackedStore = (MediaComponentContentStore) Preconditions.checkNotNull(mediaComponentContentStore2, "fileBackedStore");
        this.mManifestContentStore = (ManifestContentStore) Preconditions.checkNotNull(manifestContentStore, "manifestStore");
        this.shouldUseManifestContentStore = z;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore
    protected SmoothStreamingContentStore getDelegate(ContentSessionType contentSessionType) {
        return this.mMemoryBackedStore;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean isManifestAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        return (this.shouldUseManifestContentStore && this.mManifestContentStore.isManifestAvailable(contentSessionType, file, playableContent, str)) || this.mFileBackedStore.isManifestAvailable(CACHE_SESSION_TYPE_OVERRIDE, file, playableContent, str) || this.mMemoryBackedStore.isManifestAvailable(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public ByteBuffer loadManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) throws ContentException {
        if (this.shouldUseManifestContentStore && this.mManifestContentStore.isManifestAvailable(contentSessionType, file, playableContent, str)) {
            return this.mManifestContentStore.loadManifest(contentSessionType, file, playableContent, str);
        }
        MediaComponentContentStore mediaComponentContentStore = this.mFileBackedStore;
        ContentSessionType contentSessionType2 = CACHE_SESSION_TYPE_OVERRIDE;
        if (mediaComponentContentStore.isManifestAvailable(contentSessionType2, file, playableContent, str)) {
            return this.mFileBackedStore.loadManifest(contentSessionType2, file, playableContent, str);
        }
        return this.mMemoryBackedStore.loadManifest(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public void storeManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, InputStream inputStream, int i, boolean z) throws ContentException {
        if (this.shouldUseManifestContentStore) {
            this.mManifestContentStore.storeManifest(contentSessionType, file, playableContent, str, inputStream, i, z);
        } else {
            super.storeManifest(contentSessionType, file, playableContent, str, inputStream, i, z);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        if (this.shouldUseManifestContentStore) {
            return this.mManifestContentStore.supportsCompressedManifests(contentSessionType);
        }
        return super.supportsCompressedManifests(contentSessionType);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        PlayableContent content = contentSessionContext.getContent();
        String encodeId = contentSessionContext.getAudioVideoUrls().getEncodeId();
        File storagePath = contentSessionContext.getStoragePath();
        return this.mMemoryBackedStore.isFragmentAvailable(contentSessionContext.getSessionType(), storagePath, content, encodeId, smoothStreamingURI) || this.mFileBackedStore.isFragmentAvailable(CACHE_SESSION_TYPE_OVERRIDE, storagePath, content, encodeId, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isAnyFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        PlayableContent content = contentSessionContext.getContent();
        String encodeId = contentSessionContext.getAudioVideoUrls().getEncodeId();
        File storagePath = contentSessionContext.getStoragePath();
        return this.mMemoryBackedStore.isAnyFragmentAvailable(contentSessionContext.getSessionType(), storagePath, content, encodeId, smoothStreamingURI) || this.mFileBackedStore.isAnyFragmentAvailable(CACHE_SESSION_TYPE_OVERRIDE, storagePath, content, encodeId, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        PlayableContent content = contentSessionContext.getContent();
        String encodeId = contentSessionContext.getAudioVideoUrls().getEncodeId();
        File storagePath = contentSessionContext.getStoragePath();
        MediaComponentContentStore mediaComponentContentStore = this.mFileBackedStore;
        ContentSessionType contentSessionType = CACHE_SESSION_TYPE_OVERRIDE;
        int bitrate = mediaComponentContentStore.isAnyFragmentAvailable(contentSessionType, storagePath, content, encodeId, smoothStreamingURI) ? this.mFileBackedStore.getBestQualityAvailable(contentSessionType, storagePath, content, encodeId, smoothStreamingURI).getQualityLevel().getBitrate() : Integer.MIN_VALUE;
        ContentSessionType sessionType = contentSessionContext.getSessionType();
        int bitrate2 = this.mMemoryBackedStore.isAnyFragmentAvailable(sessionType, storagePath, content, encodeId, smoothStreamingURI) ? this.mMemoryBackedStore.getBestQualityAvailable(sessionType, storagePath, content, encodeId, smoothStreamingURI).getQualityLevel().getBitrate() : Integer.MIN_VALUE;
        if (bitrate < 0 && bitrate2 < 0) {
            throw new ContentException(ContentException.ContentError.DISK_ERROR, "does not contain requested fragment", null, URLUtils.nullWhenMalformed(smoothStreamingURI.getAbsoluteUrl(getManifestUrl(contentSessionContext))));
        }
        return smoothStreamingURI.getSmoothStreamingUriForQuality(smoothStreamingURI.getStream().getQualityLevelLessThanEqual(smoothStreamingURI.getChunkIndex(), Math.max(bitrate, bitrate2)));
    }

    @Nullable
    private String getManifestUrl(ContentSessionContext contentSessionContext) {
        ContentUrlSelector contentUrlSelector = contentSessionContext.getContentUrlSelector();
        if (contentUrlSelector == null) {
            return null;
        }
        return contentUrlSelector.getCurrentContentUrl().getUrl();
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public ByteBuffer loadFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        PlayableContent content = contentSessionContext.getContent();
        String encodeId = contentSessionContext.getAudioVideoUrls().getEncodeId();
        File storagePath = contentSessionContext.getStoragePath();
        if (this.mMemoryBackedStore.isFragmentAvailable(contentSessionContext.getSessionType(), storagePath, content, encodeId, smoothStreamingURI)) {
            return this.mMemoryBackedStore.loadFragment(contentSessionContext.getSessionType(), storagePath, content, encodeId, smoothStreamingURI);
        }
        return this.mFileBackedStore.loadFragment(CACHE_SESSION_TYPE_OVERRIDE, storagePath, content, encodeId, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        PlayableContent content = contentSessionContext.getContent();
        String encodeId = contentSessionContext.getAudioVideoUrls().getEncodeId();
        File storagePath = contentSessionContext.getStoragePath();
        MediaComponentContentStore mediaComponentContentStore = this.mFileBackedStore;
        ContentSessionType contentSessionType = CACHE_SESSION_TYPE_OVERRIDE;
        if (mediaComponentContentStore.isAnyFragmentAvailable(contentSessionType, storagePath, content, encodeId, smoothStreamingURI)) {
            this.mFileBackedStore.releaseFragmentReferences(contentSessionType, storagePath, content, encodeId, smoothStreamingURI);
        }
        this.mMemoryBackedStore.releaseFragment(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        PlayableContent content = contentSessionContext.getContent();
        String encodeId = contentSessionContext.getAudioVideoUrls().getEncodeId();
        File storagePath = contentSessionContext.getStoragePath();
        MediaComponentContentStore mediaComponentContentStore = this.mFileBackedStore;
        ContentSessionType contentSessionType = CACHE_SESSION_TYPE_OVERRIDE;
        if (mediaComponentContentStore.isAnyFragmentAvailable(contentSessionType, storagePath, content, encodeId, smoothStreamingURI)) {
            this.mFileBackedStore.deleteFragmentAtAllQualities(contentSessionType, storagePath, content, encodeId, smoothStreamingURI);
        }
        this.mMemoryBackedStore.deleteFragmentAtAllQualities(contentSessionContext.getSessionType(), storagePath, content, encodeId, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        this.mFileBackedStore.releaseContent(CACHE_SESSION_TYPE_OVERRIDE, file, playableContent, str);
        this.mMemoryBackedStore.releaseContent(contentSessionType, file, playableContent, str);
        if (this.shouldUseManifestContentStore) {
            this.mManifestContentStore.releaseContent(contentSessionType, file, playableContent, str);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        if (this.shouldUseManifestContentStore) {
            this.mManifestContentStore.releaseContent(contentSessionType, file, playableContent, str);
        }
        super.releaseContent(contentSessionType, file, playableContent, str);
    }
}
