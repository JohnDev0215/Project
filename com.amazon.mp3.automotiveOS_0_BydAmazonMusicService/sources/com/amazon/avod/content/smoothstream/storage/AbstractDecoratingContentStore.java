package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.pmet.ContentStoreType;
import com.google.common.base.Optional;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
abstract class AbstractDecoratingContentStore implements SmoothStreamingContentStore {
    protected abstract SmoothStreamingContentStore getDelegate(ContentSessionType contentSessionType);

    AbstractDecoratingContentStore() {
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void begin(PlayableContent playableContent, ContentSessionType contentSessionType, ContentManagementEventBus contentManagementEventBus, @Nullable ContentSessionContext contentSessionContext) throws ContentException {
        getDelegate(contentSessionType).begin(playableContent, contentSessionType, contentManagementEventBus, contentSessionContext);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void end(@Nonnull ContentSessionType contentSessionType, @Nonnull PlayableContent playableContent, String str) throws ContentException {
        getDelegate(contentSessionType).end(contentSessionType, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean isManifestAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        return getDelegate(contentSessionType).isManifestAvailable(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public ByteBuffer loadManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) throws ContentException {
        return getDelegate(contentSessionType).loadManifest(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public void storeManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, InputStream inputStream, int i, boolean z) throws ContentException {
        getDelegate(contentSessionType).storeManifest(contentSessionType, file, playableContent, str, inputStream, i, z);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        return getDelegate(contentSessionType).supportsCompressedManifests(contentSessionType);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return getDelegate(contentSessionContext.getSessionType()).isFragmentAvailable(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isAnyFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return getDelegate(contentSessionContext.getSessionType()).isAnyFragmentAvailable(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return getDelegate(contentSessionContext.getSessionType()).getBestQualityAvailable(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public ByteBuffer loadFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return getDelegate(contentSessionContext.getSessionType()).loadFragment(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str) throws ContentException {
        getDelegate(contentSessionContext.getSessionType()).storeFragment(contentSessionContext, smoothStreamingURI, inputStream, i, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void validateAndStoreFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str) throws ContentException {
        getDelegate(contentSessionContext.getSessionType()).validateAndStoreFragment(contentSessionContext, smoothStreamingURI, inputStream, i, fragmentValidator, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        getDelegate(contentSessionContext.getSessionType()).releaseFragment(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        getDelegate(contentSessionContext.getSessionType()).deleteErroredFragment(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public ByteBuffer loadStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex) throws ContentException {
        return getDelegate(contentSessionContext.getSessionType()).loadStatFile(contentSessionContext, streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isStatFileAvailable(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        return getDelegate(contentSessionContext.getSessionType()).isStatFileAvailable(contentSessionContext, streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        getDelegate(contentSessionContext.getSessionType()).storeStatFile(contentSessionContext, streamIndex, inputStream, i);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void deleteAllContent(ContentSessionType contentSessionType, File file) {
        getDelegate(contentSessionType).deleteAllContent(contentSessionType, file);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        getDelegate(contentSessionType).releaseContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Optional<DiskUtils.DeletionProgressListener> optional) {
        getDelegate(contentSessionType).releaseContent(contentSessionType, file, playableContent, str, optional);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        getDelegate(contentSessionType).deleteErroredContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteFragmentAtQuality(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Integer num, SmoothStreamingURI smoothStreamingURI) {
        getDelegate(contentSessionType).deleteFragmentAtQuality(contentSessionType, file, playableContent, str, num, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseBuffer(ContentSessionType contentSessionType, ByteBuffer byteBuffer) {
        getDelegate(contentSessionType).releaseBuffer(contentSessionType, byteBuffer);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ContentStoreType getContentStoreType(@Nonnull ContentSessionType contentSessionType) {
        return getDelegate(contentSessionType).getContentStoreType(contentSessionType);
    }
}
