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
import com.google.common.collect.Maps;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class CompositeSmoothStreamingContentStore extends AbstractDecoratingContentStore {
    private final Map<ContentSessionType, SmoothStreamingContentStore> mSessionTypeStoreMap;

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
    public /* bridge */ /* synthetic */ SmoothStreamingURI getBestQualityAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return super.getBestQualityAvailable(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public /* bridge */ /* synthetic */ ContentStoreType getContentStoreType(@Nonnull ContentSessionType contentSessionType) {
        return super.getContentStoreType(contentSessionType);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ boolean isAnyFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return super.isAnyFragmentAvailable(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ boolean isFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return super.isFragmentAvailable(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public /* bridge */ /* synthetic */ boolean isManifestAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        return super.isManifestAvailable(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ boolean isStatFileAvailable(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        return super.isStatFileAvailable(contentSessionContext, streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ ByteBuffer loadFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return super.loadFragment(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public /* bridge */ /* synthetic */ ByteBuffer loadManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) throws ContentException {
        return super.loadManifest(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ ByteBuffer loadStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex) throws ContentException {
        return super.loadStatFile(contentSessionContext, streamIndex);
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

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void releaseFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        super.releaseFragment(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void storeFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str) throws ContentException {
        super.storeFragment(contentSessionContext, smoothStreamingURI, inputStream, i, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public /* bridge */ /* synthetic */ void storeManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, InputStream inputStream, int i, boolean z) throws ContentException {
        super.storeManifest(contentSessionType, file, playableContent, str, inputStream, i, z);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void storeStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        super.storeStatFile(contentSessionContext, streamIndex, inputStream, i);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public /* bridge */ /* synthetic */ boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        return super.supportsCompressedManifests(contentSessionType);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore, com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public /* bridge */ /* synthetic */ void validateAndStoreFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str) throws ContentException {
        super.validateAndStoreFragment(contentSessionContext, smoothStreamingURI, inputStream, i, fragmentValidator, str);
    }

    private static Map<ContentSessionType, SmoothStreamingContentStore> buildSessionTypeMap(MediaComponentContentStore mediaComponentContentStore, MediaComponentContentStore mediaComponentContentStore2, ManifestContentStore manifestContentStore) {
        LegacyDownloadCompatibleContentStore legacyDownloadCompatibleContentStore = new LegacyDownloadCompatibleContentStore(mediaComponentContentStore2);
        CacheAwareContentStore cacheAwareContentStore = new CacheAwareContentStore(mediaComponentContentStore, mediaComponentContentStore2, manifestContentStore);
        EnumMap enumMapNewEnumMap = Maps.newEnumMap(ContentSessionType.class);
        enumMapNewEnumMap.put(ContentSessionType.CONTENT_CACHE, mediaComponentContentStore2);
        enumMapNewEnumMap.put(ContentSessionType.STREAMING, cacheAwareContentStore);
        enumMapNewEnumMap.put(ContentSessionType.STREAMING_CONTINUOUS, cacheAwareContentStore);
        enumMapNewEnumMap.put(ContentSessionType.LIVE_CACHE, cacheAwareContentStore);
        enumMapNewEnumMap.put(ContentSessionType.DOWNLOAD, legacyDownloadCompatibleContentStore);
        enumMapNewEnumMap.put(ContentSessionType.PROGRESSIVE_PLAYBACK, legacyDownloadCompatibleContentStore);
        return enumMapNewEnumMap;
    }

    public CompositeSmoothStreamingContentStore(MemoryBackedContentStore memoryBackedContentStore, FileBackedContentStore fileBackedContentStore, DynamicManifestContentStore dynamicManifestContentStore) {
        this(buildSessionTypeMap(memoryBackedContentStore, fileBackedContentStore, dynamicManifestContentStore));
    }

    CompositeSmoothStreamingContentStore(Map<ContentSessionType, SmoothStreamingContentStore> map) {
        this.mSessionTypeStoreMap = map;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.AbstractDecoratingContentStore
    protected SmoothStreamingContentStore getDelegate(ContentSessionType contentSessionType) {
        return this.mSessionTypeStoreMap.get(contentSessionType);
    }
}
