package com.amazon.avod.smoothstream.dash;

import android.util.LongSparseArray;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.ExternalFourCCMapper;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.media.framework.config.DashConfig;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.smoothstream.DashManifestUtil;
import com.amazon.avod.util.DLog;
import com.amazonaws.util.DateUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.visualon.OSMPUtils.voMimeTypes;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class DashManifestJni {
    private final LongSparseArray<Float> mCachedQualityLevelFrameRate;
    private final DashConfig mDashConfig;
    private boolean mInitialized;
    private final PlaybackNativeLibrariesLoader mLibraryLoader;
    private final PlaybackPmetMetricReporter mPmetMetricReporter;
    private final SimpleDateFormat mRFC_3339_DATE_FORMAT;
    private final Object mSyncObject;
    private final List<String> mValidAtmosSupplementalProperties;

    private static native String getAdaptationSetAspectRatio(long j);

    private static native String getAdaptationSetAudioTrackId(long j);

    private static native String getAdaptationSetBaseUrl(long j);

    private static native boolean getAdaptationSetBitstreamSwitching(long j);

    private static native String getAdaptationSetContentType(long j);

    private static native String getAdaptationSetLanguage(long j);

    private static native String getAdaptationSetMimeType(long j);

    private static native long getAdaptationSetProtection(long j, int i);

    private static native int getAdaptationSetProtectionCount(long j);

    private static native long getAdaptationSetRepresentation(long j, int i);

    private static native int getAdaptationSetRepresentationCount(long j);

    private static native boolean getAdaptationSetSegmentAlignment(long j);

    private static native long getAdaptationSetSegmentDurations(long j);

    private static native long getAdaptationSetSegmentList(long j);

    private static native long getAdaptationSetSegmentTemplate(long j);

    private static native int getAdaptationSetStartWithSap(long j);

    private static native boolean getAdaptationSetSubsegmentAlignment(long j);

    private static native int getAdaptationSetSubsegmentStartsWithSap(long j);

    private static native String getAudioChannelConfigurationSchemeIdUri(long j);

    private static native String getAudioChannelConfigurationValue(long j);

    private static native String getEssentialPropertySchemeIdUri(long j);

    private static native String getEssentialPropertyValue(long j);

    private static native double getFragmentQualityScore(long j, int i, int i2);

    private static native long getFragmentSize(long j, int i, int i2);

    private static native String getMpdAvailabilityStartTime(long j);

    private static native String getMpdBaseUrl(long j);

    private static native boolean getMpdIsPattermTemplateManifest(long j);

    private static native long getMpdMediaPresentationDuration(long j);

    private static native long getMpdMinBufferTime(long j);

    private static native long getMpdMinimumUpdatePeriod(long j);

    private static native long getMpdPeriod(long j, int i);

    private static native int getMpdPeriodCount(long j);

    private static native String getMpdProfiles(long j);

    private static native String getMpdSchemaLocation(long j);

    private static native long getMpdSuggestedPresentationDelay(long j);

    private static native long getMpdTimeShiftBufferDepth(long j);

    private static native int getMpdType(long j);

    private static native String getNativeCodeCompileTime();

    private static native String getNativeLibraryVersion();

    private static native long getPeriodAdaptationSet(long j, int i);

    private static native int getPeriodAdaptationSetCount(long j);

    private static native int getPeriodAdaptationSetGroup(long j);

    private static native String getPeriodBaseUrl(long j);

    private static native String getPeriodId(long j);

    private static native String getPeriodPeriodAssetIdentifierSchemeIdUri(long j);

    private static native String getPeriodPeriodAssetIdentifierValue(long j);

    private static native long getPeriodSegmentList(long j);

    private static native long getPeriodSegmentTemplate(long j);

    private static native long getPeriodStart(long j);

    private static native long getPeriodSupplementalProperty(long j, int i);

    private static native int getPeriodSupplementalPropertyCount(long j);

    private static native String getProtectionDefaultKid(long j);

    private static native String getProtectionPssh(long j);

    private static native String getProtectionSchemeIdUri(long j);

    private static native String getProtectionValue(long j);

    private static native String getPssh(long j, boolean z, boolean z2);

    private static native String getRange(long j, int i, int i2);

    private static native long getRepresentationAudioChannelConfiguration(long j);

    private static native int getRepresentationAudioSamplingRate(long j);

    private static native int getRepresentationBandwidth(long j);

    private static native String getRepresentationBaseUrl(long j);

    private static native String getRepresentationCodecPrivateData(long j);

    private static native String getRepresentationCodecs(long j);

    private static native long getRepresentationEssentialProperty(long j, int i);

    private static native int getRepresentationEssentialPropertyCount(long j);

    private static native String getRepresentationFrameRate(long j);

    private static native int getRepresentationHeight(long j);

    private static native String getRepresentationId(long j);

    private static native long getRepresentationSegmentList(long j);

    private static native long getRepresentationSegmentTemplate(long j);

    private static native String getRepresentationStorageAspectRatio(long j);

    private static native long getRepresentationSupplementalProperty(long j, int i);

    private static native int getRepresentationSupplementalPropertyCount(long j);

    private static native int getRepresentationWidth(long j);

    private static native int getSegmentDuration(long j, int i);

    private static native long getSegmentDurationTimestamp(long j, int i);

    private static native int getSegmentDurationsCount(long j);

    private static native int getSegmentDurationsTimescale(long j);

    private static native int getSegmentListDuration(long j);

    private static native long getSegmentListInitRangeBegin(long j);

    private static native long getSegmentListInitRangeEnd(long j);

    private static native long getSegmentListMediaRangeBegin(long j, int i);

    private static native int getSegmentListMediaRangeCount(long j);

    private static native long getSegmentListMediaRangeEnd(long j, int i);

    private static native int getSegmentListTimescale(long j);

    private static native int getSegmentTemplateAvailabilityTimeOffset(long j);

    private static native int getSegmentTemplateDuration(long j);

    private static native String getSegmentTemplateInitialization(long j);

    private static native String getSegmentTemplateMedia(long j);

    private static native long getSegmentTemplatePresentationTimeOffset(long j);

    private static native int getSegmentTemplateStartNumber(long j);

    private static native int getSegmentTemplateTimescale(long j);

    private static native int getSegmentTimelineChunkCount(long j);

    private static native int getSegmentTimelineCount(long j);

    private static native int getSegmentTimelineDuration(long j, int i);

    private static native int getSegmentTimelineRepeat(long j, int i);

    private static native long getSegmentTimelineTime(long j, int i);

    private static native long getSegmentTimelineTimeOffset(long j, int i);

    private static native String getSupplementalPropertySchemeIdUri(long j);

    private static native String getSupplementalPropertyValue(long j);

    private static native long parse(ByteBuffer byteBuffer, int i, int i2) throws ContentException;

    private static native void release(long j);

    public long getManifestTimeScale() {
        return 10000000L;
    }

    public DashManifestJni(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this(playbackNativeLibrariesLoader, DashConfig.getInstance(), PlaybackPmetMetricReporter.getInstance());
    }

    @VisibleForTesting
    DashManifestJni(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader, @Nonnull DashConfig dashConfig, @Nonnull PlaybackPmetMetricReporter playbackPmetMetricReporter) {
        this.mInitialized = false;
        this.mSyncObject = new Object();
        this.mLibraryLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader);
        this.mCachedQualityLevelFrameRate = new LongSparseArray<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
        this.mRFC_3339_DATE_FORMAT = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        DashConfig dashConfig2 = (DashConfig) Preconditions.checkNotNull(dashConfig, "dashConfig");
        this.mDashConfig = dashConfig2;
        this.mValidAtmosSupplementalProperties = dashConfig2.getValidAtmosSupplementalProperties();
        this.mPmetMetricReporter = (PlaybackPmetMetricReporter) Preconditions.checkNotNull(playbackPmetMetricReporter, "PlaybackPmetMetricReporter");
    }

    public Manifest parse(@Nonnull ByteBuffer byteBuffer, @Nonnull DrmScheme drmScheme) throws ContentException {
        DashManifest dashManifest;
        synchronized (this.mSyncObject) {
            if (!this.mInitialized) {
                initialize();
            }
            boolean isDashManifestValidateParsedObjectGraphEnabled = this.mDashConfig.getIsDashManifestValidateParsedObjectGraphEnabled();
            ByteBuffer directByteBuffer = Mp4FragmentJni.getDirectByteBuffer(byteBuffer);
            dashManifest = new DashManifest(this, parse(directByteBuffer, directByteBuffer.capacity(), isDashManifestValidateParsedObjectGraphEnabled ? 1 : 0), drmScheme, SmoothStreamingPlaybackConfig.INSTANCE);
        }
        return dashManifest;
    }

    public int getPeriodCount(long j) {
        int mpdPeriodCount;
        synchronized (this.mSyncObject) {
            mpdPeriodCount = getMpdPeriodCount(j);
        }
        return mpdPeriodCount;
    }

    public void releaseManifest(long j) {
        synchronized (this.mSyncObject) {
            if (j != 0) {
                release(j);
            }
        }
    }

    public boolean isDynamic(long j) {
        boolean z;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            z = true;
            if (getMpdType(j) != 1) {
                z = false;
            }
        }
        return z;
    }

    public boolean isPattermTemplateManifest(long j) {
        boolean mpdIsPattermTemplateManifest;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            mpdIsPattermTemplateManifest = getMpdIsPattermTemplateManifest(j);
        }
        return mpdIsPattermTemplateManifest;
    }

    public long getPeriodHandle(long j, int i) {
        long mpdPeriod;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            mpdPeriod = getMpdPeriod(j, i);
        }
        return mpdPeriod;
    }

    public long getPeriodStartTime(long j) {
        long periodStart;
        synchronized (this.mSyncObject) {
            periodStart = getPeriodStart(j);
        }
        return periodStart;
    }

    @Nullable
    public String getPeriodIdString(long j) {
        String periodId;
        synchronized (this.mSyncObject) {
            periodId = getPeriodId(j);
        }
        return periodId;
    }

    public boolean isEncrypted(long j) {
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            for (int i = 0; i < getPeriodCount(j); i++) {
                if (getAdaptationSetProtectionCount(getAvailableStream(j, i, 0)) > 0) {
                    return true;
                }
            }
            return false;
        }
    }

    @Nullable
    public String getDefaultKeyId(long j) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            int adaptationSetProtectionCount = getAdaptationSetProtectionCount(j);
            for (int i = 0; i < adaptationSetProtectionCount; i++) {
                String protectionDefaultKid = getProtectionDefaultKid(getAdaptationSetProtection(j, i));
                if (!Strings.isNullOrEmpty(protectionDefaultKid)) {
                    return protectionDefaultKid;
                }
            }
            return null;
        }
    }

    public int getAvailableStreamCount(long j, int i) {
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            int mpdPeriodCount = getMpdPeriodCount(j);
            if (mpdPeriodCount != 0 && i < mpdPeriodCount) {
                return getPeriodAdaptationSetCount(getMpdPeriod(j, i));
            }
            return 0;
        }
    }

    public long getManifestDuration(long j) {
        long mpdMediaPresentationDuration;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            mpdMediaPresentationDuration = getMpdMediaPresentationDuration(j);
        }
        return mpdMediaPresentationDuration;
    }

    public long getTimeShiftBufferDepth(long j) {
        long mpdTimeShiftBufferDepth;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            mpdTimeShiftBufferDepth = getMpdTimeShiftBufferDepth(j);
        }
        return mpdTimeShiftBufferDepth;
    }

    @Nullable
    public String getAvailabilityStartTimeUTC(long j) {
        String mpdAvailabilityStartTime;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            mpdAvailabilityStartTime = getMpdAvailabilityStartTime(j);
        }
        return mpdAvailabilityStartTime;
    }

    public long getFrontMargin(long j) {
        long mpdSuggestedPresentationDelay;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            mpdSuggestedPresentationDelay = getMpdSuggestedPresentationDelay(j);
        }
        return mpdSuggestedPresentationDelay;
    }

    public long getMinimumUpdatePeriod(long j) {
        long mpdMinimumUpdatePeriod;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            mpdMinimumUpdatePeriod = getMpdMinimumUpdatePeriod(j);
        }
        return mpdMinimumUpdatePeriod;
    }

    public long getAvailableStream(long j, int i, int i2) {
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            if (getMpdPeriodCount(j) == 0) {
                return 0L;
            }
            return getPeriodAdaptationSet(getMpdPeriod(j, i), i2);
        }
    }

    public String getProtectionHeader(long j, @Nonnull DrmScheme drmScheme) {
        String pssh;
        synchronized (this.mSyncObject) {
            checkManifestHandle(j);
            pssh = getPssh(j, drmScheme == DrmScheme.PLAYREADY, false);
        }
        return pssh;
    }

    public StreamType getStreamIndexType(long j) {
        StreamType adaptationSetType;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            adaptationSetType = getAdaptationSetType(j);
        }
        return adaptationSetType;
    }

    public int getStreamQualityLevelCount(long j) {
        int adaptationSetRepresentationCount;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            adaptationSetRepresentationCount = getAdaptationSetRepresentationCount(j);
        }
        return adaptationSetRepresentationCount;
    }

    public long getStreamQualityLevel(long j, int i) {
        long adaptationSetRepresentation;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            adaptationSetRepresentation = getAdaptationSetRepresentation(j, i);
        }
        return adaptationSetRepresentation;
    }

    public String getStreamLanguage(long j) {
        String adaptationSetLanguage;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            adaptationSetLanguage = getAdaptationSetLanguage(j);
        }
        return adaptationSetLanguage;
    }

    public String getStreamAudioTrackId(long j) {
        String adaptationSetAudioTrackId;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            adaptationSetAudioTrackId = getAdaptationSetAudioTrackId(j);
        }
        return adaptationSetAudioTrackId;
    }

    public boolean isSegmentListBased(long j) {
        boolean z;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long adaptationSetRepresentation = getAdaptationSetRepresentation(j, 0);
            Preconditions.checkState(adaptationSetRepresentation != 0, "Invalid representation handle");
            z = 0 != getRepresentationSegmentList(adaptationSetRepresentation);
        }
        return z;
    }

    public boolean isSegmentTemplateBased(long j) {
        return !isSegmentListBased(j);
    }

    public int getStreamChunkCountFromSegmentList(long j) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long adaptationSetRepresentation = getAdaptationSetRepresentation(j, 0);
            if (adaptationSetRepresentation == 0) {
                return 0;
            }
            long representationSegmentList = getRepresentationSegmentList(adaptationSetRepresentation);
            if (representationSegmentList == 0) {
                return 0;
            }
            return getSegmentListMediaRangeCount(representationSegmentList);
        }
    }

    public int getStreamChunkCountFromSegmentTemplate(long j) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long adaptationSetRepresentation = getAdaptationSetRepresentation(j, 0);
            if (adaptationSetRepresentation == 0) {
                return 0;
            }
            long representationSegmentTemplate = getRepresentationSegmentTemplate(adaptationSetRepresentation);
            if (representationSegmentTemplate == 0) {
                return 0;
            }
            return getSegmentTimelineChunkCount(representationSegmentTemplate);
        }
    }

    public long getStreamChunkDurationFromSegmentDurations(long j, int i) {
        long segmentDuration;
        synchronized (this.mSyncObject) {
            boolean z = true;
            Preconditions.checkState(j != 0, "Invalid segment durations handle");
            int segmentDurationsTimescale = getSegmentDurationsTimescale(j);
            if (segmentDurationsTimescale <= 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "timescale");
            segmentDuration = (long) ((((double) getSegmentDuration(j, i)) / ((double) segmentDurationsTimescale)) * 1.0E7d);
        }
        return segmentDuration;
    }

    public long getStreamChunkTimeStampFromSegmentTemplate(long j, int i) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long adaptationSetRepresentation = getAdaptationSetRepresentation(j, 0);
            if (adaptationSetRepresentation == 0) {
                return 0L;
            }
            long representationSegmentTemplate = getRepresentationSegmentTemplate(adaptationSetRepresentation);
            if (representationSegmentTemplate == 0) {
                return 0L;
            }
            return (long) ((getSegmentTimelineTime(representationSegmentTemplate, i) / ((double) getSegmentTemplateTimescale(representationSegmentTemplate))) * 1.0E7d);
        }
    }

    public long getStreamChunkTimeOffsetFromSegmentTemplate(long j, int i) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long adaptationSetRepresentation = getAdaptationSetRepresentation(j, 0);
            if (adaptationSetRepresentation == 0) {
                return 0L;
            }
            long representationSegmentTemplate = getRepresentationSegmentTemplate(adaptationSetRepresentation);
            if (representationSegmentTemplate == 0) {
                return 0L;
            }
            return (long) ((getSegmentTimelineTimeOffset(representationSegmentTemplate, i) / ((double) getSegmentTemplateTimescale(representationSegmentTemplate))) * 1.0E7d);
        }
    }

    public long getSegmentDurationsHandle(long j) {
        long adaptationSetSegmentDurations;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            adaptationSetSegmentDurations = getAdaptationSetSegmentDurations(j);
        }
        return adaptationSetSegmentDurations;
    }

    public long getStreamChunkTimestamp(long j, int i) {
        long segmentDurationTimestamp;
        synchronized (this.mSyncObject) {
            boolean z = true;
            Preconditions.checkState(j != 0, "Invalid segment durations handle");
            int segmentDurationsTimescale = getSegmentDurationsTimescale(j);
            if (segmentDurationsTimescale <= 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "timescale");
            segmentDurationTimestamp = (long) ((getSegmentDurationTimestamp(j, i) / ((double) segmentDurationsTimescale)) * 1.0E7d);
        }
        return segmentDurationTimestamp;
    }

    public long getStreamChunkDurationFromSegmentTemplate(long j, int i) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long adaptationSetRepresentation = getAdaptationSetRepresentation(j, 0);
            if (adaptationSetRepresentation == 0) {
                return 0L;
            }
            long representationSegmentTemplate = getRepresentationSegmentTemplate(adaptationSetRepresentation);
            if (representationSegmentTemplate == 0) {
                return 0L;
            }
            return (long) ((((double) getSegmentTimelineDuration(representationSegmentTemplate, i)) / ((double) getSegmentTemplateTimescale(representationSegmentTemplate))) * 1.0E7d);
        }
    }

    public String getStreamChunkRange(long j, int i, int i2) {
        String range;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            range = getRange(j, i, i2);
        }
        return range;
    }

    public long getStreamChunkSize(long j, int i, int i2) {
        long fragmentSize;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            fragmentSize = getFragmentSize(j, i, i2);
        }
        return fragmentSize;
    }

    public double getChunkQualityScore(long j, int i, int i2) {
        double fragmentQualityScore;
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            fragmentQualityScore = getFragmentQualityScore(j, i, i2);
        }
        return fragmentQualityScore;
    }

    public double getStreamDisplayAspectRatio(long j) {
        return DashManifestUtil.convertAspectRatioStringToDouble(getAdaptationSetAspectRatio(j));
    }

    public int getQualityLevelBitrate(long j) {
        int representationBandwidth;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            representationBandwidth = getRepresentationBandwidth(j);
        }
        return representationBandwidth;
    }

    public String getQualityLevelFourCC(long j) {
        String strConvertCodecToFourCC;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            String representationCodecs = getRepresentationCodecs(j);
            int supplementalPropertyCount = getSupplementalPropertyCount(j);
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            for (int i = 0; i < supplementalPropertyCount; i++) {
                SupplementalProperty supplementalProperty = getSupplementalProperty(j, i);
                if (supplementalProperty != null) {
                    arrayListNewArrayList.add(supplementalProperty);
                }
            }
            strConvertCodecToFourCC = convertCodecToFourCC(representationCodecs, arrayListNewArrayList);
        }
        return strConvertCodecToFourCC;
    }

    public boolean isHdr(long j) {
        String representationCodecs;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            representationCodecs = getRepresentationCodecs(j);
        }
        return isHdr(representationCodecs);
    }

    @VisibleForTesting
    boolean isHdr(@Nullable String str) {
        if (Strings.isNullOrEmpty(str)) {
            return false;
        }
        Locale locale = Locale.US;
        String lowerCase = str.toLowerCase(locale);
        if (Strings.isNullOrEmpty(lowerCase)) {
            return false;
        }
        if (!lowerCase.startsWith(VideoStreamType.AV01.getFourCC().toLowerCase(locale))) {
            return lowerCase.startsWith("hev1.2.") || lowerCase.startsWith("hvc1.2.") || lowerCase.startsWith("dvhe");
        }
        String[] strArrSplit = lowerCase.split("[.]");
        if (strArrSplit.length != 10) {
            return false;
        }
        return "09".equals(strArrSplit[6]) && "16".equals(strArrSplit[7]);
    }

    public String getQualityLevelCodecData(long j) {
        String representationCodecPrivateData;
        synchronized (this.mSyncObject) {
            Preconditions.checkState(j != 0, "Invalid quality level handle");
            representationCodecPrivateData = getRepresentationCodecPrivateData(j);
        }
        return representationCodecPrivateData;
    }

    @Nullable
    Float getQualityLevelFrameRate(long j) {
        Float f;
        synchronized (this.mSyncObject) {
            Preconditions.checkState(j != 0, "Invalid quality level handle");
            if (this.mCachedQualityLevelFrameRate.indexOfKey(j) < 0) {
                String representationFrameRate = getRepresentationFrameRate(j);
                if (!Strings.isNullOrEmpty(representationFrameRate)) {
                    String[] strArrSplit = representationFrameRate.split("/");
                    if (strArrSplit.length > 1 && !strArrSplit[1].equals("0")) {
                        this.mCachedQualityLevelFrameRate.append(j, Float.valueOf(Float.valueOf(strArrSplit[0]).floatValue() / Float.valueOf(strArrSplit[1]).floatValue()));
                    } else {
                        this.mCachedQualityLevelFrameRate.append(j, Float.valueOf(strArrSplit[0]));
                    }
                }
            }
            f = this.mCachedQualityLevelFrameRate.get(j);
        }
        return f;
    }

    public int getQualityLevelNalUnitLengthField(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
        }
        return 0;
    }

    public int getQualityLevelMaxWidth(long j) {
        int representationWidth;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            representationWidth = getRepresentationWidth(j);
        }
        return representationWidth;
    }

    public int getQualityLevelMaxHeight(long j) {
        int representationHeight;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            representationHeight = getRepresentationHeight(j);
        }
        return representationHeight;
    }

    public int getQualityLevelAudioTag(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
        }
        return 0;
    }

    public int getQualityLevelNumChannels(long j) {
        String audioChannelConfiguration = getAudioChannelConfiguration(j);
        if (audioChannelConfiguration == null) {
            return 0;
        }
        try {
            return Integer.parseInt(audioChannelConfiguration);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    @Nullable
    public String getAudioChannelConfiguration(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            long representationAudioChannelConfiguration = getRepresentationAudioChannelConfiguration(j);
            if (representationAudioChannelConfiguration == 0) {
                return null;
            }
            return getAudioChannelConfigurationValue(representationAudioChannelConfiguration);
        }
    }

    private int getSupplementalPropertyCount(long j) {
        int representationSupplementalPropertyCount;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            representationSupplementalPropertyCount = getRepresentationSupplementalPropertyCount(j);
        }
        return representationSupplementalPropertyCount;
    }

    @Nullable
    private SupplementalProperty getSupplementalProperty(long j, int i) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long representationSupplementalProperty = getRepresentationSupplementalProperty(j, i);
            if (representationSupplementalProperty == 0) {
                return null;
            }
            String supplementalPropertySchemeIdUri = getSupplementalPropertySchemeIdUri(representationSupplementalProperty);
            String supplementalPropertyValue = getSupplementalPropertyValue(representationSupplementalProperty);
            if (supplementalPropertySchemeIdUri != null && supplementalPropertyValue != null) {
                return new SupplementalProperty(supplementalPropertySchemeIdUri, supplementalPropertyValue);
            }
            return null;
        }
    }

    @Nullable
    public EssentialProperty getEssentialProperty(long j, int i) {
        synchronized (this.mSyncObject) {
            checkStreamHandle(j);
            long representationEssentialProperty = getRepresentationEssentialProperty(j, i);
            if (representationEssentialProperty == 0) {
                return null;
            }
            String essentialPropertySchemeIdUri = getEssentialPropertySchemeIdUri(representationEssentialProperty);
            String essentialPropertyValue = getEssentialPropertyValue(representationEssentialProperty);
            if (essentialPropertySchemeIdUri != null && essentialPropertyValue != null) {
                return new EssentialProperty(essentialPropertySchemeIdUri, essentialPropertyValue);
            }
            return null;
        }
    }

    public long getPeriodEncodeTimeEpochMillis(long j) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            int supplementalPropertyCountFromPeriod = getSupplementalPropertyCountFromPeriod(j);
            for (int i = 0; i < supplementalPropertyCountFromPeriod; i++) {
                SupplementalProperty supplementalPropertyFromPeriod = getSupplementalPropertyFromPeriod(j, i);
                if (supplementalPropertyFromPeriod != null && supplementalPropertyFromPeriod.getSchemeIdUri().equalsIgnoreCase("urn:scte:dash:utc-time")) {
                    try {
                        return this.mRFC_3339_DATE_FORMAT.parse(supplementalPropertyFromPeriod.getValue()).getTime();
                    } catch (ParseException e) {
                        DLog.exceptionf(e, "Exception while parsing period encode utc time: ", new Object[0]);
                    }
                }
            }
            return -1L;
        }
    }

    private int getSupplementalPropertyCountFromPeriod(long j) {
        int periodSupplementalPropertyCount;
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            periodSupplementalPropertyCount = getPeriodSupplementalPropertyCount(j);
        }
        return periodSupplementalPropertyCount;
    }

    @Nullable
    private SupplementalProperty getSupplementalPropertyFromPeriod(long j, int i) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            long periodSupplementalProperty = getPeriodSupplementalProperty(j, i);
            if (periodSupplementalProperty == 0) {
                return null;
            }
            String supplementalPropertySchemeIdUri = getSupplementalPropertySchemeIdUri(periodSupplementalProperty);
            String supplementalPropertyValue = getSupplementalPropertyValue(periodSupplementalProperty);
            if (supplementalPropertySchemeIdUri != null && supplementalPropertyValue != null) {
                return new SupplementalProperty(supplementalPropertySchemeIdUri, supplementalPropertyValue);
            }
            return null;
        }
    }

    public long getQualityLevelTimeScale(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            if (getRepresentationSegmentList(j) == 0) {
                return 0L;
            }
            return getSegmentListTimescale(r5);
        }
    }

    public long getQualityLevelTimeScaleFromSegmentTemplate(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            if (getRepresentationSegmentTemplate(j) == 0) {
                return 0L;
            }
            return getSegmentTemplateTimescale(r5);
        }
    }

    public int getQualityLevelSampleRate(long j) {
        int representationAudioSamplingRate;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            representationAudioSamplingRate = getRepresentationAudioSamplingRate(j);
        }
        return representationAudioSamplingRate;
    }

    public int getQualityLevelBitsPerSample(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
        }
        return 0;
    }

    public String getBaseUrlFromRepresentation(long j) {
        String representationBaseUrl;
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            representationBaseUrl = getRepresentationBaseUrl(j);
        }
        return representationBaseUrl;
    }

    public String getMediaFromSegmentTemplate(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            long representationSegmentTemplate = getRepresentationSegmentTemplate(j);
            if (representationSegmentTemplate == 0) {
                return "";
            }
            return getSegmentTemplateMedia(representationSegmentTemplate);
        }
    }

    public String getQualityLevelInitRange(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            long representationSegmentList = getRepresentationSegmentList(j);
            if (representationSegmentList == 0) {
                return "";
            }
            return String.format(Locale.US, "%d-%d", Long.valueOf(getSegmentListInitRangeBegin(representationSegmentList)), Long.valueOf(getSegmentListInitRangeEnd(representationSegmentList)));
        }
    }

    public String getInitializationFromSegmentTemplate(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            long representationSegmentTemplate = getRepresentationSegmentTemplate(j);
            if (representationSegmentTemplate == 0) {
                return "";
            }
            return getSegmentTemplateInitialization(representationSegmentTemplate);
        }
    }

    public int getStartNumberFromSegmentTemplate(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            checkStreamHandle(j2);
            checkQualityLevelHandle(j3);
            long segmentTemplate = getSegmentTemplate(j, j2, j3);
            if (segmentTemplate == 0) {
                return 0;
            }
            return getSegmentTemplateStartNumber(segmentTemplate);
        }
    }

    public int getTimescaleFromSegmentTemplate(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            checkStreamHandle(j2);
            checkQualityLevelHandle(j3);
            long segmentTemplate = getSegmentTemplate(j, j2, j3);
            if (segmentTemplate == 0) {
                return 0;
            }
            return getSegmentTemplateTimescale(segmentTemplate);
        }
    }

    public int getDurationFromSegmentTemplate(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            checkStreamHandle(j2);
            checkQualityLevelHandle(j3);
            long segmentTemplate = getSegmentTemplate(j, j2, j3);
            if (segmentTemplate == 0) {
                return 0;
            }
            return getSegmentTemplateDuration(segmentTemplate);
        }
    }

    public long getPresentationTimeOffsetFromSegmentTemplate(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            checkStreamHandle(j2);
            checkQualityLevelHandle(j3);
            long segmentTemplate = getSegmentTemplate(j, j2, j3);
            if (segmentTemplate == 0) {
                return 0L;
            }
            return getSegmentTemplatePresentationTimeOffset(segmentTemplate);
        }
    }

    public int getAvailabilityTimeOffsetFromSegmentTemplate(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            checkStreamHandle(j2);
            checkQualityLevelHandle(j3);
            long segmentTemplate = getSegmentTemplate(j, j2, j3);
            if (segmentTemplate == 0) {
                return 0;
            }
            return getSegmentTemplateAvailabilityTimeOffset(segmentTemplate);
        }
    }

    public String getMediaFromSegmentTemplate(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            checkStreamHandle(j2);
            checkQualityLevelHandle(j3);
            long segmentTemplate = getSegmentTemplate(j, j2, j3);
            if (segmentTemplate == 0) {
                return "";
            }
            return getSegmentTemplateMedia(segmentTemplate);
        }
    }

    public int getQualityLevelChunkStartNumber(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            long representationSegmentTemplate = getRepresentationSegmentTemplate(j);
            if (representationSegmentTemplate == 0) {
                return 0;
            }
            return getSegmentTemplateStartNumber(representationSegmentTemplate);
        }
    }

    public int getQualityLevelInitFragmentSize(long j) {
        synchronized (this.mSyncObject) {
            checkQualityLevelHandle(j);
            long representationSegmentList = getRepresentationSegmentList(j);
            if (representationSegmentList == 0) {
                return -1;
            }
            return Ints.checkedCast((getSegmentListInitRangeEnd(representationSegmentList) - getSegmentListInitRangeBegin(representationSegmentList)) + 1);
        }
    }

    @Nullable
    String getBaseUrl(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            String adaptationSetBaseUrl = getAdaptationSetBaseUrl(j3);
            if (!Strings.isNullOrEmpty(adaptationSetBaseUrl)) {
                DLog.devf("BaseUrl from AdaptationSet %s", adaptationSetBaseUrl);
                return adaptationSetBaseUrl;
            }
            String periodBaseUrl = getPeriodBaseUrl(j2);
            if (!Strings.isNullOrEmpty(periodBaseUrl)) {
                DLog.devf("BaseUrl from Period %s", periodBaseUrl);
                return periodBaseUrl;
            }
            String mpdBaseUrl = getMpdBaseUrl(j);
            DLog.devf("BaseUrl from MPD %s", mpdBaseUrl);
            return mpdBaseUrl;
        }
    }

    @Nullable
    String getBaseUrl(String str, long j, long j2, long j3) {
        String baseUrlHelper;
        synchronized (this.mSyncObject) {
            baseUrlHelper = getBaseUrlHelper(str, getMpdBaseUrl(j), getPeriodBaseUrl(j2), getAdaptationSetBaseUrl(j3));
        }
        return baseUrlHelper;
    }

    @Nullable
    @VisibleForTesting
    String getBaseUrlHelper(String str, String str2, String str3, String str4) {
        try {
            URI uri = new URI(str);
            if (!Strings.isNullOrEmpty(str2)) {
                uri = uri.resolve(str2);
            }
            if (!Strings.isNullOrEmpty(str3)) {
                uri = uri.resolve(str3);
            }
            if (!Strings.isNullOrEmpty(str4)) {
                uri = uri.resolve(str4);
            }
            return uri.toString();
        } catch (IllegalArgumentException | URISyntaxException e) {
            DLog.errorf("Encountered exception while resolving manifest url against base urls. ManifestUrl: %s, exception message: %s", str, e.getMessage());
            this.mPmetMetricReporter.reportFragmentURLResolutionErrorMetrics(FragmentURLResolutionPmetMetrics.toReportableString(FragmentURLResolutionPmetMetrics.FRAGMENT_URL_HIERARCHICAL_PARSING_ERROR));
            return str;
        }
    }

    @Nullable
    @VisibleForTesting
    String convertCodecToFourCC(@Nonnull String str, @Nonnull List<SupplementalProperty> list) {
        Preconditions.checkNotNull(str, "codec");
        Preconditions.checkNotNull(list, "supplementalPropertyList");
        String lowerCase = str.toLowerCase(Locale.US);
        ExternalFourCCMapper externalFourCCMapper = ExternalFourCCMapper.INSTANCE;
        AudioStreamType audioStreamTypeMapAudioCodecToStreamType = externalFourCCMapper.mapAudioCodecToStreamType(lowerCase);
        if (audioStreamTypeMapAudioCodecToStreamType != null) {
            if (audioStreamTypeMapAudioCodecToStreamType == AudioStreamType.DDP) {
                for (SupplementalProperty supplementalProperty : list) {
                    String str2 = String.format(Locale.US, "%s,%s", supplementalProperty.getSchemeIdUri(), supplementalProperty.getValue());
                    DLog.devf("DD+ track supplemental property tag: %s", str2);
                    Iterator<String> it = this.mValidAtmosSupplementalProperties.iterator();
                    while (it.hasNext()) {
                        if (it.next().contains(str2)) {
                            return AudioStreamType.ATMOS.getFourCC();
                        }
                    }
                }
                return audioStreamTypeMapAudioCodecToStreamType.getFourCC();
            }
            return audioStreamTypeMapAudioCodecToStreamType.getFourCC();
        }
        VideoStreamType videoStreamTypeMapVideoCodecToStreamType = externalFourCCMapper.mapVideoCodecToStreamType(lowerCase);
        if (videoStreamTypeMapVideoCodecToStreamType != null) {
            return videoStreamTypeMapVideoCodecToStreamType.getFourCC();
        }
        return null;
    }

    private void initialize() throws ContentException {
        if (!this.mLibraryLoader.waitForInitialization()) {
            throw new ContentException(ContentException.ContentError.ERROR_LOADING_NATIVE_LIBRARIES, "Failed to load the playback native libraries!");
        }
        String nativeLibraryVersion = getNativeLibraryVersion();
        DLog.logf("%s library: version %s, compile time %s", "AIVDash", nativeLibraryVersion, getNativeCodeCompileTime());
        Preconditions.checkState(nativeLibraryVersion.equals("2.9"), "FATAL DEPLOYMENT ERROR: This AIV app is built to run with version %s of the lib%s.so library, but version %s was found on this device", "2.9", "AIVDash", nativeLibraryVersion);
        this.mInitialized = true;
    }

    private StreamType getAdaptationSetType(long j) {
        String adaptationSetMimeType = getAdaptationSetMimeType(j);
        if (adaptationSetMimeType.equalsIgnoreCase(voMimeTypes.VOVIDEO_MP4)) {
            return StreamType.VIDEO;
        }
        if (adaptationSetMimeType.equalsIgnoreCase(voMimeTypes.VOAUDIO_MP4)) {
            return StreamType.AUDIO;
        }
        if (adaptationSetMimeType.equalsIgnoreCase("application/mp4")) {
            return StreamType.SUBTITLES;
        }
        if (adaptationSetMimeType.equalsIgnoreCase("image/jpeg")) {
            return StreamType.IMAGE;
        }
        return StreamType.UNSUPPORTED;
    }

    private void checkManifestHandle(long j) {
        Preconditions.checkState(j != 0, "Need to parse the manifest first");
    }

    private void checkStreamHandle(long j) {
        Preconditions.checkState(j != 0, "Invalid stream handle");
    }

    private void checkQualityLevelHandle(long j) {
        Preconditions.checkState(j != 0, "Invalid quality level handle");
    }

    private void checkPeriodHandle(long j) {
        Preconditions.checkState(j != 0, "Invalid period handle");
    }

    public long getSegmentTemplate(long j, long j2, long j3) {
        synchronized (this.mSyncObject) {
            checkPeriodHandle(j);
            checkStreamHandle(j2);
            checkQualityLevelHandle(j3);
            long representationSegmentTemplate = getRepresentationSegmentTemplate(j3);
            if (representationSegmentTemplate != 0) {
                return representationSegmentTemplate;
            }
            long adaptationSetSegmentTemplate = getAdaptationSetSegmentTemplate(j2);
            if (adaptationSetSegmentTemplate != 0) {
                return adaptationSetSegmentTemplate;
            }
            return getPeriodSegmentTemplate(j);
        }
    }
}
