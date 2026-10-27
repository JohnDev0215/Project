package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.dash.quality.audioselection.AudioFormatAndPreferredTrackIdBasedStreamAndQualitySelector;
import com.amazon.avod.content.dash.quality.audioselection.AudioFormatBasedStreamAndQualitySelector;
import com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualityPair;
import com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualitySelector;
import com.amazon.avod.content.dash.quality.audioselection.AudioTrackIdBasedStreamAndQualitySelector;
import com.amazon.avod.content.dash.quality.audioselection.FixedAudioStreamAndQualitySelector;
import com.amazon.avod.content.dash.quality.audioselection.NetworkHistoryAudioStreamAndQualitySelector;
import com.amazon.avod.content.dash.quality.heuristic.Heuristics;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamTypeComparator;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.content.smoothstream.storage.singlefile.ByteRange;
import com.amazon.avod.content.smoothstream.storage.singlefile.JsonDownloadContentStatus;
import com.amazon.avod.content.smoothstream.storage.singlefile.MediaFileStatus;
import com.amazon.avod.content.smoothstream.storage.singlefile.SingleFileStoreUtils;
import com.amazon.avod.content.urlvending.AudioTrackUtils;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.framework.network.NetworkHistoryManager;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.media.playback.support.DeviceCapabilityDetector;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class StreamSelector {
    private static final StreamTypeComparator STREAM_TYPE_COMPARATOR = new StreamTypeComparator();
    private final DeviceCapabilityDetector mDeviceCapabilityDetector;
    private final FileBackedContentStore mFilebackedContentStore;
    private final MediaQualityConfig mQualityConfig;
    private final SmoothStreamingPlaybackConfig mSmoothStreamingPlaybackConfig;

    public StreamSelector(@Nonnull FileBackedContentStore fileBackedContentStore, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull DeviceCapabilityDetector deviceCapabilityDetector) {
        this(fileBackedContentStore, smoothStreamingPlaybackConfig, deviceCapabilityDetector, MediaQualityConfig.INSTANCE);
    }

    StreamSelector(@Nonnull FileBackedContentStore fileBackedContentStore, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull DeviceCapabilityDetector deviceCapabilityDetector, @Nonnull MediaQualityConfig mediaQualityConfig) {
        this.mFilebackedContentStore = (FileBackedContentStore) Preconditions.checkNotNull(fileBackedContentStore, "fileBackedContentStore");
        this.mSmoothStreamingPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
        this.mDeviceCapabilityDetector = (DeviceCapabilityDetector) Preconditions.checkNotNull(deviceCapabilityDetector, "deviceCapabilityDetector");
        this.mQualityConfig = (MediaQualityConfig) Preconditions.checkNotNull(mediaQualityConfig, "mediaQualityConfig");
    }

    public StreamSelections selectStreams(boolean z, @Nonnull Manifest manifest, @Nonnull ContentSessionType contentSessionType, @Nonnull AudioFormat audioFormat, @Nullable String str, @Nonnull List<String> list, @Nonnull List<String> list2, @Nullable String str2, @Nonnull NetworkHistoryManager networkHistoryManager, @Nullable Heuristics heuristics, @Nonnull StartAudioBitrateSelectionMode startAudioBitrateSelectionMode, @Nullable File file, @Nullable String str3, @Nonnull PlayableContent playableContent, @Nonnull MediaQuality mediaQuality) throws ContentException {
        Preconditions.checkNotNull(manifest, "manifest");
        Preconditions.checkNotNull(contentSessionType, "contentSessionType");
        Preconditions.checkNotNull(audioFormat, "audioFormat");
        Preconditions.checkNotNull(list, "audioTrackIds");
        Preconditions.checkNotNull(list2, "preferredAudioTrackIds");
        Preconditions.checkNotNull(networkHistoryManager, "networkHistoryManager");
        Preconditions.checkNotNull(startAudioBitrateSelectionMode, "startAudioBitrateSelectionMode");
        Preconditions.checkNotNull(playableContent, "playableContent");
        Preconditions.checkNotNull(mediaQuality, "mediaQuality");
        int audioBitrateCap = this.mQualityConfig.getAudioBitrateCap(mediaQuality);
        int secondaryAudioBitrateCap = this.mQualityConfig.getSecondaryAudioBitrateCap(mediaQuality);
        boolean secondaryAudioTrackQualityConfigEnabled = this.mQualityConfig.getSecondaryAudioTrackQualityConfigEnabled();
        String strConvertAudioFormatToFourCC = convertAudioFormatToFourCC(audioFormat);
        String selectionParamString = getSelectionParamString(audioBitrateCap, startAudioBitrateSelectionMode, contentSessionType, mediaQuality, manifest, str);
        Locale locale = Locale.US;
        DLog.logf(String.format(locale, "Selecting streams with following parameters: %s", selectionParamString));
        ImmutableList.Builder builder = new ImmutableList.Builder();
        if (str2 != null) {
            builder.add(str2);
        }
        builder.addAll((Iterable) list2);
        ImmutableList immutableListBuild = builder.build();
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionType.ordinal()]) {
            case 1:
            case 2:
            case 3:
                if (!z) {
                    return selectStreamsWithAudioStreamAndQualitySelectorSelector(manifest, audioFormat, str, immutableListBuild, new AudioFormatAndPreferredTrackIdBasedStreamAndQualitySelector(audioBitrateCap, strConvertAudioFormatToFourCC, immutableListBuild), selectionParamString, contentSessionType);
                }
                int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$smoothstream$StartAudioBitrateSelectionMode[startAudioBitrateSelectionMode.ordinal()];
                if (i == 1) {
                    if (heuristics == null || heuristics.getHeuristicsPlaybackConfig() == null) {
                        throw new UnsupportedOperationException("Should not use NETWORK_HISTORY_BASED without miyagi configurations");
                    }
                    return selectStreamsWithAudioStreamAndQualitySelectorSelector(manifest, audioFormat, str, immutableListBuild, new NetworkHistoryAudioStreamAndQualitySelector(networkHistoryManager, heuristics, audioBitrateCap), selectionParamString, contentSessionType);
                }
                if (i == 2) {
                    return selectStreamsWithAudioStreamAndQualitySelectorSelector(manifest, audioFormat, str, immutableListBuild, new AudioFormatAndPreferredTrackIdBasedStreamAndQualitySelector(audioBitrateCap, strConvertAudioFormatToFourCC, immutableListBuild), selectionParamString, contentSessionType);
                }
                if (i == 3) {
                    return selectStreamsWithAudioStreamAndQualitySelectorSelector(manifest, audioFormat, str, immutableListBuild, new FixedAudioStreamAndQualitySelector(this.mSmoothStreamingPlaybackConfig.getFixedStaticAudioBitrate()), selectionParamString, contentSessionType);
                }
                throw new IllegalArgumentException("Please add more option to switch statement above");
            case 4:
            case 5:
                Preconditions.checkNotNull(file, String.format(locale, "storagePath should not be null for contentSessionType %s", contentSessionType));
                if (this.mSmoothStreamingPlaybackConfig.enableFragmentBasedAudioStreamSelectionForProgressivePlayback()) {
                    return selectStreamsBasedOnDownloadedAudioFragments(manifest, contentSessionType, audioFormat, str, list, immutableListBuild, playableContent, file, str3, mediaQuality, selectionParamString);
                }
                return selectStreamsWithAudioStreamAndQualitySelectorSelector(manifest, audioFormat, str, immutableListBuild, new AudioTrackIdBasedStreamAndQualitySelector(list, str2, audioBitrateCap, secondaryAudioBitrateCap, strConvertAudioFormatToFourCC, secondaryAudioTrackQualityConfigEnabled), selectionParamString, contentSessionType);
            case 6:
                return selectStreamsWithAudioStreamAndQualitySelectorSelector(manifest, audioFormat, str, immutableListBuild, new AudioFormatAndPreferredTrackIdBasedStreamAndQualitySelector(audioBitrateCap, strConvertAudioFormatToFourCC, immutableListBuild), selectionParamString, contentSessionType);
            default:
                throw new IllegalArgumentException(String.format(locale, "Invalid contentSessionType: %s", contentSessionType));
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.StreamSelector$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$StartAudioBitrateSelectionMode;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.LIVE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.DOWNLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr2 = new int[StartAudioBitrateSelectionMode.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$StartAudioBitrateSelectionMode = iArr2;
            try {
                iArr2[StartAudioBitrateSelectionMode.NETWORK_HISTORY_BASED.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$StartAudioBitrateSelectionMode[StartAudioBitrateSelectionMode.AUDIO_FORMAT_BASED.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$StartAudioBitrateSelectionMode[StartAudioBitrateSelectionMode.FIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private String getSelectionParamString(int i, StartAudioBitrateSelectionMode startAudioBitrateSelectionMode, ContentSessionType contentSessionType, MediaQuality mediaQuality, Manifest manifest, String str) {
        StringBuilder sb = new StringBuilder();
        Joiner joinerOn = Joiner.on(";");
        sb.append(String.format(Locale.US, "Audio bitrate cap %d, %s, %s, mediaQuality %s, audioLanguage %s, ", Integer.valueOf(i), startAudioBitrateSelectionMode, contentSessionType, mediaQuality, str));
        sb.append("Manifest[");
        for (StreamIndex streamIndex : manifest.getStreams()) {
            sb.append(String.format(Locale.US, "%s-%s", streamIndex.getType(), streamIndex.getFourCC()));
            sb.append("{");
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            for (QualityLevel qualityLevel : streamIndex.getSortedQualityLevels(0)) {
                arrayListNewArrayList.add(Integer.valueOf(qualityLevel.getBitrate()));
            }
            sb.append(joinerOn.join(arrayListNewArrayList));
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    @Nonnull
    private StreamSelections selectStreamsBasedOnDownloadedAudioFragments(@Nonnull Manifest manifest, @Nonnull ContentSessionType contentSessionType, @Nonnull AudioFormat audioFormat, @Nullable String str, @Nonnull List<String> list, @Nonnull List<String> list2, @Nonnull PlayableContent playableContent, @Nonnull File file, @Nullable String str2, @Nonnull MediaQuality mediaQuality, @Nonnull String str3) throws ContentException {
        AudioStreamAndQualitySelector audioFormatBasedStreamAndQualitySelector;
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        try {
            JsonDownloadContentStatus jsonDownloadContentStatusFromFileOrNew = JsonDownloadContentStatus.fromFileOrNew(SingleFileStoreUtils.buildDownloadStatusFilePath(file));
            ArrayList<StreamIndex> arrayListNewArrayList = Lists.newArrayList(manifest.getStreams());
            Collections.sort(arrayListNewArrayList, STREAM_TYPE_COMPARATOR);
            for (StreamIndex streamIndex : arrayListNewArrayList) {
                if (streamIndex.isAudio()) {
                    QualityLevel[] sortedQualityLevels = streamIndex.getSortedQualityLevels(z ? 1 : 0);
                    int length = sortedQualityLevels.length;
                    int i = z ? 1 : 0;
                    while (i < length) {
                        QualityLevel qualityLevel = sortedQualityLevels[i];
                        if (this.mFilebackedContentStore.isFragmentAvailable(contentSessionType, file, playableContent, str2, new SmoothStreamingURI(streamIndex, qualityLevel, z ? 1 : 0, z))) {
                            arrayList.add(new AudioStreamAndQualityPair(streamIndex, (AudioQualityLevel) qualityLevel));
                        }
                        SmoothStreamingURI smoothStreamingURI = new SmoothStreamingURI(streamIndex, qualityLevel, streamIndex.getNumChunks() - 1, z);
                        if (smoothStreamingURI.getDownloadHeaders().containsKey("Range")) {
                            MediaFileStatus mediaFileStatus = jsonDownloadContentStatusFromFileOrNew.getMediaFileStatus(SingleFileStoreUtils.getFileNameFromSSU(smoothStreamingURI));
                            long end = ByteRange.fromSmoothStreamingURI(smoothStreamingURI).getEnd() + 1;
                            if (mediaFileStatus != null && mediaFileStatus.getBitrate() == smoothStreamingURI.getQualityLevel().getBitrate() && mediaFileStatus.getFileSizeBytes() == end) {
                                arrayList.add(new AudioStreamAndQualityPair(streamIndex, (AudioQualityLevel) qualityLevel));
                            }
                        }
                        i++;
                        z = false;
                    }
                }
            }
            if (arrayList.size() > 0) {
                return new StreamSelections(manifest.getVideoStream(), getAudioStreamAndQualityPairForAudioTrackId(list2, arrayList), arrayList, manifest.getSubtitleStream(), str3, manifest.getImageStream(), contentSessionType);
            }
            DLog.warnf("Cannot find any downloaded audio fragment for audioLang:%s, fallback to audio format based selection", str);
            int audioBitrateCap = this.mQualityConfig.getAudioBitrateCap(mediaQuality);
            int secondaryAudioBitrateCap = this.mQualityConfig.getSecondaryAudioBitrateCap(mediaQuality);
            boolean secondaryAudioTrackQualityConfigEnabled = this.mQualityConfig.getSecondaryAudioTrackQualityConfigEnabled();
            String strConvertAudioFormatToFourCC = convertAudioFormatToFourCC(audioFormat);
            if (list.isEmpty()) {
                audioFormatBasedStreamAndQualitySelector = new AudioFormatBasedStreamAndQualitySelector(audioBitrateCap, strConvertAudioFormatToFourCC);
            } else {
                audioFormatBasedStreamAndQualitySelector = new AudioTrackIdBasedStreamAndQualitySelector(list, list2.isEmpty() ? null : list2.get(0), audioBitrateCap, secondaryAudioBitrateCap, strConvertAudioFormatToFourCC, secondaryAudioTrackQualityConfigEnabled);
            }
            return selectStreamsWithAudioStreamAndQualitySelectorSelector(manifest, audioFormat, str, list2, audioFormatBasedStreamAndQualitySelector, str3, contentSessionType);
        } catch (IOException e) {
            throw new ContentException(ContentException.ContentError.DISK_ERROR, String.format(Locale.US, "Cannot load download status file because %s", e.getMessage()));
        }
    }

    @Nonnull
    private StreamSelections selectStreamsWithAudioStreamAndQualitySelectorSelector(@Nonnull Manifest manifest, @Nonnull AudioFormat audioFormat, @Nullable String str, @Nonnull List<String> list, @Nonnull AudioStreamAndQualitySelector audioStreamAndQualitySelector, @Nonnull String str2, @Nonnull ContentSessionType contentSessionType) throws ContentException {
        Preconditions.checkNotNull(manifest, "manifest");
        Preconditions.checkNotNull(list, "primaryAudioTrackIds");
        Preconditions.checkNotNull(contentSessionType, "contentSessionType");
        List<StreamIndex> supportedAudioStreams = getSupportedAudioStreams(manifest, audioFormat, str);
        if (supportedAudioStreams.size() == 0 || supportedAudioStreams.get(0).getSortedQualityLevels(0).length == 0) {
            throw new ContentException(ContentException.ContentError.UNKNOWN_AUDIO_TRACK, "No supported audio representation found from manifest");
        }
        ArrayList arrayListNewArrayList = Lists.newArrayList(supportedAudioStreams);
        Collections.sort(arrayListNewArrayList, STREAM_TYPE_COMPARATOR);
        List<AudioStreamAndQualityPair> listSelect = audioStreamAndQualitySelector.select(arrayListNewArrayList);
        return new StreamSelections(manifest.getVideoStream(), getAudioStreamAndQualityPairForAudioTrackId(list, listSelect), listSelect, manifest.getSubtitleStream(), str2, manifest.getImageStream(), contentSessionType);
    }

    @Nonnull
    private AudioStreamAndQualityPair getAudioStreamAndQualityPairForAudioTrackId(@Nonnull List<String> list, @Nonnull List<AudioStreamAndQualityPair> list2) {
        Preconditions.checkNotNull(list, "primaryAudioTrackIds");
        Preconditions.checkNotNull(list2, "audioStreams");
        Preconditions.checkState(list2.size() > 0, "audioStreams cannot be empty.");
        AudioStreamAndQualityPair audioStreamAndQualityPair = null;
        if (!list.isEmpty()) {
            HashMap map = new HashMap();
            for (AudioStreamAndQualityPair audioStreamAndQualityPair2 : list2) {
                String audioTrackId = audioStreamAndQualityPair2.getAudioStream().getAudioTrackId();
                if (audioTrackId != null && !audioTrackId.isEmpty()) {
                    if (!map.containsKey(audioTrackId)) {
                        map.put(audioTrackId, new ArrayList());
                    }
                    ((List) map.get(audioTrackId)).add(audioStreamAndQualityPair2);
                }
            }
            Iterator<String> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (map.containsKey(next) && !((List) map.get(next)).isEmpty()) {
                    audioStreamAndQualityPair = (AudioStreamAndQualityPair) ((List) map.get(next)).get(0);
                    break;
                }
            }
        }
        return audioStreamAndQualityPair == null ? list2.get(0) : audioStreamAndQualityPair;
    }

    @Nonnull
    public List<StreamIndex> getSupportedAudioStreams(@Nonnull Manifest manifest, @Nonnull AudioFormat audioFormat, @Nullable String str) {
        Preconditions.checkNotNull(manifest, "manifest");
        Preconditions.checkNotNull(audioFormat, "audioFormat");
        List<StreamIndex> supportedAudioStreams = manifest.getSupportedAudioStreams(str, this.mDeviceCapabilityDetector.getSupportedAudioCodecs());
        ImmutableSet<AudioFormat> lessThanOrEqualFormats = AudioFormat.getLessThanOrEqualFormats(audioFormat);
        ImmutableList.Builder builder = ImmutableList.builder();
        for (StreamIndex streamIndex : supportedAudioStreams) {
            if (lessThanOrEqualFormats.contains(streamIndex.getAudioFormat())) {
                builder.add(streamIndex);
            }
        }
        return builder.build();
    }

    public Set<String> getManifestAudioLanguages(Manifest manifest, AudioFormat audioFormat) throws ContentException {
        String strConvertAudioFormatToFourCC = convertAudioFormatToFourCC(audioFormat);
        Set<String> availableAudioLanguages = manifest.getAvailableAudioLanguages(strConvertAudioFormatToFourCC);
        if (!availableAudioLanguages.isEmpty()) {
            return availableAudioLanguages;
        }
        String audioStream = this.mSmoothStreamingPlaybackConfig.getAudioStream();
        DLog.logf("Requested audio format %s does not exist in the manifest. Attempting to use default: %s", strConvertAudioFormatToFourCC, audioStream);
        return manifest.getAvailableAudioLanguages(audioStream);
    }

    public Set<String> getManifestAudioLanguagesAcrossAudioFormats(Manifest manifest) throws ContentException {
        return manifest.getAvailableAudioLanguagesAcrossAudioStreams();
    }

    @Nonnull
    private static String convertAudioFormatToFourCC(AudioFormat audioFormat) throws ContentException {
        return AudioTrackUtils.convertAudioFormatToFourCC(audioFormat);
    }
}
