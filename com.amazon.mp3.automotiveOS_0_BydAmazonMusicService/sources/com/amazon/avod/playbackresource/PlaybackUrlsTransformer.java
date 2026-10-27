package com.amazon.avod.playbackresource;

import android.os.Environment;
import androidx.annotation.VisibleForTesting;
import com.amazon.atv.cuepoint.CuepointPlaylist;
import com.amazon.atvplaybackresource.PlaybackSettings;
import com.amazon.atvplaybackresource.ResponseTitleRendition;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.AudioVideoUrlsBuilder;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContiguityType;
import com.amazon.avod.content.urlvending.CuepointPlaylistTransformer;
import com.amazon.avod.content.urlvending.FailoverInfo;
import com.amazon.avod.content.urlvending.FailoverManager;
import com.amazon.avod.content.urlvending.FailoverMode;
import com.amazon.avod.content.urlvending.FailoverType;
import com.amazon.avod.content.urlvending.FragmentRepresentation;
import com.amazon.avod.content.urlvending.ReturnedTitleRendition;
import com.amazon.avod.content.urlvending.SubtitleRepresentation;
import com.amazon.avod.http.HttpRequestConfig;
import com.amazon.avod.playbackresourcev2.LivePlaybackResourcesSessionResponse;
import com.amazon.avod.playbackresourcev2.LivePlaybackUrls;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.DLog;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.urlvending.AudioTrackMetadata;
import com.amazon.urlvending.Failover;
import com.amazon.urlvending.ManifestUrl;
import com.amazon.urlvending.PlaybackUrlSet;
import com.amazon.urlvending.PlaybackUrls;
import com.amazon.urlvending.Urls;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.io.Closer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackUrlsTransformer {
    private final ExecutorService mExecutorService;
    private final HttpRequestConfig mHttpRequestConfig;
    private final PlaybackSettingsTransformer mPlaybackSettingsTransformer;
    private final ReturnedTitleRenditionTransformer mReturnedTitleRenditionTransformer;

    public PlaybackUrlsTransformer() {
        this(new ReturnedTitleRenditionTransformer(), new PlaybackSettingsTransformer(), HttpRequestConfig.getInstance(), ExecutorBuilder.newBuilderFor(PlaybackUrlsTransformer.class, new String[0]).withFixedThreadPoolSize(1).build(), new CuepointPlaylistTransformer());
    }

    @VisibleForTesting
    PlaybackUrlsTransformer(@Nonnull ReturnedTitleRenditionTransformer returnedTitleRenditionTransformer, @Nonnull PlaybackSettingsTransformer playbackSettingsTransformer, @Nonnull HttpRequestConfig httpRequestConfig, @Nonnull ExecutorService executorService, @Nonnull CuepointPlaylistTransformer cuepointPlaylistTransformer) {
        this.mReturnedTitleRenditionTransformer = (ReturnedTitleRenditionTransformer) Preconditions.checkNotNull(returnedTitleRenditionTransformer, "returnedTitleRenditionTransformer");
        this.mPlaybackSettingsTransformer = (PlaybackSettingsTransformer) Preconditions.checkNotNull(playbackSettingsTransformer, "playbackSettingsTransformer");
        this.mHttpRequestConfig = (HttpRequestConfig) Preconditions.checkNotNull(httpRequestConfig, "httpRequestConfig");
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
    }

    @Nullable
    public AudioVideoUrls transform(@Nonnull Optional<PlaybackUrls> optional, @Nonnull Optional<PlaybackSettings> optional2, @Nonnull Optional<ResponseTitleRendition> optional3, @Nonnull Optional<CuepointPlaylist> optional4) {
        try {
            PlaybackUrls playbackUrls = optional.get();
            String strOr = playbackUrls.cacheKey.or("");
            String strOr2 = playbackUrls.auxCacheKey.or("");
            String str = playbackUrls.defaultUrlSetId.get();
            ImmutableMap<String, PlaybackUrlSet> immutableMap = playbackUrls.urlSets.get();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            HashSet hashSetNewHashSet = Sets.newHashSet();
            processUrlSets(immutableMap.values(), map, map2, hashSetNewHashSet);
            ReturnedTitleRendition returnedTitleRenditionTransform = this.mReturnedTitleRenditionTransformer.transform(optional3);
            ImmutableList<AudioTrackMetadata> immutableListOf = playbackUrls.audioTracks.isPresent() ? playbackUrls.audioTracks.get() : ImmutableList.of();
            ImmutableList<com.amazon.avod.content.urlvending.AudioTrackMetadata> list = FluentIterable.from(immutableListOf).transform(new WireTypeToAudioTrackMetadata(playbackUrls.defaultAudioTrackId.or(""), immutableListOf.size())).filter(Predicates.notNull()).toList();
            if (this.mHttpRequestConfig.isRequestResponseLoggingEnabled()) {
                this.mExecutorService.execute(new PlaybackUrlsLogger(returnedTitleRenditionTransform.getTitleId(), playbackUrls));
            }
            String strConstructEncodeId = constructEncodeId(returnedTitleRenditionTransform.getContentId(), strOr, strOr2);
            FailoverManager failoverManager = new FailoverManager(str, map2, map, hashSetNewHashSet);
            ImmutableList<ContentUrl> orderedContentUrlListForSeamlessFailovers = failoverManager.getOrderedContentUrlListForSeamlessFailovers();
            Map<String, String> mapTransform = this.mPlaybackSettingsTransformer.transform(optional2.orNull());
            if (orderedContentUrlListForSeamlessFailovers.isEmpty()) {
                return null;
            }
            return new AudioVideoUrlsBuilder().setContentUrls(orderedContentUrlListForSeamlessFailovers).setAudioTrackMetadataList(list).setEncodeId(strConstructEncodeId).setPlaybackSettingsOverride(mapTransform).setFailoverManager(failoverManager).setReturnedTitleRendition(returnedTitleRenditionTransform).setCuepointPlaylistInfo(CuepointPlaylistTransformer.transform(optional4)).setAuxCacheKey(strOr2).build();
        } catch (IllegalStateException e) {
            DLog.exceptionf(e, "Unable to transform PlaybackUrls response to AudioVideoUrls", new Object[0]);
            return null;
        }
    }

    @Nullable
    public AudioVideoUrls transform(@Nonnull Optional<LivePlaybackUrls> optional, @Nonnull Optional<com.amazon.avod.playbackresourcev2.PlaybackSettings> optional2, @Nonnull Optional<LivePlaybackResourcesSessionResponse> optional3) {
        try {
            LivePlaybackUrls livePlaybackUrls = optional.get();
            String strOr = livePlaybackUrls.getCacheKey().or("");
            String str = livePlaybackUrls.getDefaultUrlSetId().get();
            ImmutableList<PlaybackUrlSet> immutableList = livePlaybackUrls.getUrlSets().get();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            HashSet hashSetNewHashSet = Sets.newHashSet();
            processUrlSets(immutableList.asList(), map, map2, hashSetNewHashSet);
            ImmutableList immutableListOf = ImmutableList.of();
            ImmutableList<com.amazon.avod.content.urlvending.AudioTrackMetadata> list = FluentIterable.from(immutableListOf).transform(new WireTypeToAudioTrackMetadata("", immutableListOf.size())).filter(Predicates.notNull()).toList();
            String strConstructEncodeId = constructEncodeId(null, strOr, "");
            FailoverManager failoverManager = new FailoverManager(str, map2, map, hashSetNewHashSet);
            ImmutableList<ContentUrl> orderedContentUrlListForSeamlessFailovers = failoverManager.getOrderedContentUrlListForSeamlessFailovers();
            Map<String, String> mapTransform = this.mPlaybackSettingsTransformer.transform(optional2.orNull());
            String str2 = (String) ((Optional) optional3.transform(new Function() { // from class: com.amazon.avod.playbackresource.PlaybackUrlsTransformer$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return ((LivePlaybackResourcesSessionResponse) obj).getToken();
                }
            }).or(Optional.absent())).orNull();
            if (orderedContentUrlListForSeamlessFailovers.isEmpty()) {
                return null;
            }
            return new AudioVideoUrlsBuilder().setContentUrls(orderedContentUrlListForSeamlessFailovers).setAudioTrackMetadataList(list).setEncodeId(strConstructEncodeId).setPlaybackSettingsOverride(mapTransform).setFailoverManager(failoverManager).setCuepointPlaylistInfo(CuepointPlaylistTransformer.transform((Optional<CuepointPlaylist>) Optional.absent())).setAuxCacheKey("").setSessionToken(str2).build();
        } catch (IllegalStateException e) {
            DLog.exceptionf(e, "Unable to transform PlaybackUrls response to AudioVideoUrls", new Object[0]);
            return null;
        }
    }

    private void processUrlSets(@Nonnull ImmutableCollection<PlaybackUrlSet> immutableCollection, @Nonnull Map<String, Map<FailoverType, FailoverInfo>> map, @Nonnull Map<String, ContentUrl> map2, @Nonnull Set<String> set) {
        long time;
        Preconditions.checkNotNull(immutableCollection, "playbackUrlSets");
        Preconditions.checkNotNull(map, "urlSetIdToFailoverInfoMap");
        Preconditions.checkNotNull(map2, "urlSetIdToContentUrlMap");
        Preconditions.checkNotNull(set, "allAvailableOrigins");
        UnmodifiableIterator<PlaybackUrlSet> it = immutableCollection.iterator();
        while (it.hasNext()) {
            PlaybackUrlSet next = it.next();
            ImmutableMap<String, Failover> immutableMap = next.failover.get();
            HashMap map3 = new HashMap();
            UnmodifiableIterator<Map.Entry<String, Failover>> it2 = immutableMap.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<String, Failover> next2 = it2.next();
                String key = next2.getKey();
                Failover value = next2.getValue();
                map3.put(FailoverType.forValue(key), new FailoverInfo(value.urlSetId.get(), FailoverMode.fromModeString(value.mode.get())));
            }
            String str = next.urlSetId.get();
            Urls urls = next.urls.get();
            ManifestUrl manifestUrl = urls.manifest.get();
            String strOr = manifestUrl.cdn.or("");
            String strOr2 = manifestUrl.drm.or("");
            String strOr3 = manifestUrl.streamingTechnology.or("");
            String str2 = manifestUrl.url.get();
            String strOrNull = urls.adsTrackingEndpoint.orNull();
            long jLongValue = manifestUrl.duration.or(0L).longValue();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String strOr4 = manifestUrl.startTime.or("");
            if (strOr4.isEmpty()) {
                time = -1;
            } else {
                try {
                    time = simpleDateFormat.parse(strOr4).getTime();
                } catch (ParseException unused) {
                    DLog.warnf("Cannot get time from string %s. Using -1 to indicate no start time", strOr4);
                    time = -1;
                }
            }
            String strOr5 = manifestUrl.contiguityType.or("");
            String strOr6 = manifestUrl.fragmentRepresentation.or("");
            String strOr7 = manifestUrl.subtitleRepresentation.or("");
            UnmodifiableIterator<PlaybackUrlSet> unmodifiableIterator = it;
            String strOr8 = manifestUrl.livePlaybackStreamId.or("");
            ContentUrl contentUrl = new ContentUrl(str2, str, strOr, strOr2, strOr3, strOrNull, ContiguityType.forValue(strOr5), FragmentRepresentation.forValue(strOr6), SubtitleRepresentation.forValue(strOr7), strOr8, jLongValue, time, manifestUrl.origin.or(""));
            if (strOr8 != null) {
                set.add(strOr8);
            }
            map2.put(str, contentUrl);
            map.put(str, map3);
            it = unmodifiableIterator;
        }
    }

    private String constructEncodeId(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        String str4;
        if (str == null && str2 == null) {
            return null;
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[3];
        String str5 = "";
        if (str == null) {
            str = "";
        }
        objArr[0] = str;
        if (str2 == null) {
            str4 = "";
        } else {
            str4 = Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str2;
        }
        objArr[1] = str4;
        if (str3 != null) {
            str5 = Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str3;
        }
        objArr[2] = str5;
        return String.format(locale, "%s%s%s", objArr);
    }

    private static class PlaybackUrlsLogger implements Runnable {
        private final PlaybackUrls mPlaybackUrls;
        private final String mTitleId;

        PlaybackUrlsLogger(@Nonnull String str, @Nonnull PlaybackUrls playbackUrls) {
            this.mTitleId = (String) Preconditions.checkNotNull(str, "titleId");
            this.mPlaybackUrls = (PlaybackUrls) Preconditions.checkNotNull(playbackUrls, "playbackUrls");
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [com.google.common.io.Closer] */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.google.common.io.Closer] */
        /* JADX WARN: Type inference failed for: r1v3, types: [java.io.IOException, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r1v4 */
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            ?? e = Closer.create();
            try {
                try {
                    try {
                        ((FileOutputStream) e.register(new FileOutputStream(new File(Environment.getExternalStorageDirectory().getAbsolutePath(), this.mTitleId + "_playbackUrls.txt")))).write(this.mPlaybackUrls.toString().getBytes(ParserUtil.UTF_8));
                        e.close();
                    } catch (IOException e2) {
                        DLog.errorf("Fail to log playbackUrlSet to local storage. Error: %s", e2.getMessage());
                        e.close();
                    }
                } catch (IOException e3) {
                    e = e3;
                    DLog.logf("Could not close instance of FileOutputStream: %s", e);
                }
            } catch (Throwable th) {
                try {
                    e.close();
                } catch (IOException e4) {
                    DLog.logf("Could not close instance of FileOutputStream: %s", e4);
                }
                throw th;
            }
        }
    }

    private static class WireTypeToAudioTrackMetadata implements Function<AudioTrackMetadata, com.amazon.avod.content.urlvending.AudioTrackMetadata> {
        private final int mAudioTrackMetadataListLength;
        private final String mDefaultAudioTrackId;

        WireTypeToAudioTrackMetadata(@Nonnull String str, int i) {
            this.mDefaultAudioTrackId = str;
            this.mAudioTrackMetadataListLength = i;
        }

        @Override // com.google.common.base.Function
        @Nullable
        public com.amazon.avod.content.urlvending.AudioTrackMetadata apply(@Nonnull AudioTrackMetadata audioTrackMetadata) {
            Preconditions.checkNotNull(audioTrackMetadata, "item from ImmutableList should not be null");
            return new com.amazon.avod.content.urlvending.AudioTrackMetadata(audioTrackMetadata.audioTrackId.or(""), audioTrackMetadata.trackGroupId.or(""), audioTrackMetadata.displayName.or(""), audioTrackMetadata.languageCode.or(""), isDefaultAudioTrack(audioTrackMetadata.audioTrackId.or("")), audioTrackMetadata.audioSubtype.or(""), audioTrackMetadata.index.or(""));
        }

        private boolean isDefaultAudioTrack(String str) {
            if (this.mAudioTrackMetadataListLength == 1) {
                return true;
            }
            if (this.mDefaultAudioTrackId.isEmpty() || str.isEmpty()) {
                return false;
            }
            return str.equals(this.mDefaultAudioTrackId);
        }
    }
}
