package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.MediaComponentContentStore;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.pmet.ContentStoreType;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class LegacyDownloadCompatibleContentStore extends AbstractDecoratingContentStore {
    private static final String ENCODE_ID_OVERRIDE = null;
    private final MediaComponentContentStore mMediaStore;

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void begin(PlayableContent playableContent, ContentSessionType contentSessionType, ContentManagementEventBus contentManagementEventBus, @Nullable ContentSessionContext contentSessionContext) throws ContentException {
        super.begin(playableContent, contentSessionType, contentManagementEventBus, contentSessionContext);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.ContentStore
    public /* bridge */ /* synthetic */ void deleteAllContent(ContentSessionType contentSessionType, File file) {
        super.deleteAllContent(contentSessionType, file);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void deleteErroredContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        super.deleteErroredContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void deleteErroredFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        super.deleteErroredFragment(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void deleteFragmentAtQuality(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Integer num, SmoothStreamingURI smoothStreamingURI) {
        super.deleteFragmentAtQuality(contentSessionType, file, playableContent, str, num, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void end(@Nonnull ContentSessionType contentSessionType, @Nonnull PlayableContent playableContent, String str) throws ContentException {
        super.end(contentSessionType, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public /* bridge */ /* synthetic */ ContentStoreType getContentStoreType(@Nonnull ContentSessionType contentSessionType) {
        return super.getContentStoreType(contentSessionType);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void releaseBuffer(ContentSessionType contentSessionType, ByteBuffer byteBuffer) {
        super.releaseBuffer(contentSessionType, byteBuffer);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.ContentStore
    public /* bridge */ /* synthetic */ void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        super.releaseContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.ContentStore
    public /* bridge */ /* synthetic */ void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Optional optional) {
        super.releaseContent(contentSessionType, file, playableContent, str, optional);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public /* bridge */ /* synthetic */ boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        return super.supportsCompressedManifests(contentSessionType);
    }

    public LegacyDownloadCompatibleContentStore(MediaComponentContentStore mediaComponentContentStore) {
        this.mMediaStore = mediaComponentContentStore;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore
    protected SmoothStreamingContentStore getDelegate(ContentSessionType contentSessionType) {
        return this.mMediaStore;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean isManifestAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        return this.mMediaStore.isManifestAvailable(contentSessionType, file, removeAudioTrackId(playableContent), ENCODE_ID_OVERRIDE);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public ByteBuffer loadManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) throws ContentException {
        return this.mMediaStore.loadManifest(contentSessionType, file, removeAudioTrackId(playableContent), ENCODE_ID_OVERRIDE);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public void storeManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, InputStream inputStream, int i, boolean z) throws ContentException {
        this.mMediaStore.storeManifest(contentSessionType, file, removeAudioTrackId(playableContent), ENCODE_ID_OVERRIDE, inputStream, i, z);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return this.mMediaStore.isFragmentAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isAnyFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return this.mMediaStore.isAnyFragmentAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return this.mMediaStore.getBestQualityAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public ByteBuffer loadFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return this.mMediaStore.loadFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str) throws ContentException {
        this.mMediaStore.storeFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, smoothStreamingURI, inputStream, i, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void validateAndStoreFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str) throws ContentException {
        this.mMediaStore.validateAndStoreFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, smoothStreamingURI, inputStream, i, fragmentValidator, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        this.mMediaStore.deleteFragmentAtAllQualities(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public ByteBuffer loadStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex) throws ContentException {
        return this.mMediaStore.loadStatFile(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isStatFileAvailable(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        return this.mMediaStore.isStatFileAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        this.mMediaStore.storeStatFile(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), ENCODE_ID_OVERRIDE, streamIndex, inputStream, i);
    }

    @Nonnull
    private PlayableContent removeAudioTrackId(@Nonnull PlayableContent playableContent) {
        Preconditions.checkNotNull(playableContent, "content");
        return new PlayableContent(playableContent.getTitleId(), playableContent.isTrailer(), Optional.absent());
    }
}
