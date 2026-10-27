package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HighFrameRateCappedQualitySelectionComponent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J%\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013H\u0002¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0016J%\u0010\u001b\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001c\u001a\u00020\n2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u0013H\u0002¢\u0006\u0002\u0010\u001dJ\u0018\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0010H\u0016J#\u0010\"\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010!\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010#J#\u0010$\u001a\u00020\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00100\u00132\u0006\u0010!\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010#J\b\u0010%\u001a\u00020\u0019H\u0016R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\bR\u000e\u0010\u000e\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateCappedQualitySelectionComponent;", "Lcom/amazon/avod/content/smoothstream/quality/StreamingBitrateSelectionComponent;", "Lcom/amazon/avod/content/smoothstream/quality/QualityCappingUpdateListener;", "qualityHolder", "Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityHolderInterface;", "(Lcom/amazon/avod/content/smoothstream/quality/HighFrameRateQualityHolderInterface;)V", "avcDeviceBitrateCap", "", "Ljava/lang/Integer;", "avcDeviceResolutionCap", "Lcom/amazon/avod/media/VideoResolution$ResolutionBand;", "contentSessionContext", "Lcom/amazon/avod/content/ContentSessionContext;", "hevcDeviceBitrateCap", "hevcDeviceResolutionCap", "bitrateCappingKernel", "Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;", "deviceBitrateCap", "sortedQualityLevels", "", "(I[Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;)Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;", "getResolutionCap", "fourCC", "", "initialize", "", "context", "resolutionCappingKernel", "deviceResolutionCap", "(Lcom/amazon/avod/media/VideoResolution$ResolutionBand;[Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;)Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;", "selectQuality", "state", "Lcom/amazon/avod/content/smoothstream/quality/StreamingBitrateSelectionState;", "currentQualityLevel", "tryBitrateCap", "([Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;)Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;", "tryResolutionCap", "update", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HighFrameRateCappedQualitySelectionComponent implements StreamingBitrateSelectionComponent, QualityCappingUpdateListener {
    private Integer avcDeviceBitrateCap;
    private VideoResolution.ResolutionBand avcDeviceResolutionCap;
    private ContentSessionContext contentSessionContext;
    private Integer hevcDeviceBitrateCap;
    private VideoResolution.ResolutionBand hevcDeviceResolutionCap;
    private final HighFrameRateQualityHolderInterface qualityHolder;

    public HighFrameRateCappedQualitySelectionComponent(HighFrameRateQualityHolderInterface qualityHolder) {
        Intrinsics.checkNotNullParameter(qualityHolder, "qualityHolder");
        this.qualityHolder = qualityHolder;
        VideoStreamType videoStreamType = VideoStreamType.H264;
        this.avcDeviceBitrateCap = qualityHolder.getBitrateCap(videoStreamType.getFourCC());
        this.avcDeviceResolutionCap = getResolutionCap(videoStreamType.getFourCC());
        VideoStreamType videoStreamType2 = VideoStreamType.H265;
        this.hevcDeviceBitrateCap = qualityHolder.getBitrateCap(videoStreamType2.getFourCC());
        this.hevcDeviceResolutionCap = getResolutionCap(videoStreamType2.getFourCC());
        DLog.logf("HFRQualityCap AVC maxBitrate " + this.avcDeviceBitrateCap + " maxResolution " + this.avcDeviceResolutionCap);
        DLog.logf("HFRQualityCap HEVC maxBitrate " + this.hevcDeviceBitrateCap + " maxResolution " + this.hevcDeviceResolutionCap);
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.contentSessionContext = context;
        this.qualityHolder.clearBitrateCappingUpdateListenersList();
        this.qualityHolder.registerBitrateCappingUpdateListener(this);
        ContentSessionContext contentSessionContext = this.contentSessionContext;
        if (contentSessionContext == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentSessionContext");
            contentSessionContext = null;
        }
        StreamIndex videoStream = contentSessionContext.getManifest().getVideoStream();
        if (videoStream == null) {
            return;
        }
        QualityLevel[] sortedQualityLevels = videoStream.getSortedQualityLevels(videoStream.getIndex());
        this.qualityHolder.clearBitrateResolutionMapping();
        Intrinsics.checkNotNullExpressionValue(sortedQualityLevels, "sortedQualityLevels");
        int i = 0;
        int length = sortedQualityLevels.length;
        while (i < length) {
            QualityLevel qualityLevel = sortedQualityLevels[i];
            i++;
            if (qualityLevel != null && (qualityLevel instanceof VideoQualityLevel)) {
                HighFrameRateQualityHolderInterface highFrameRateQualityHolderInterface = this.qualityHolder;
                VideoQualityLevel videoQualityLevel = (VideoQualityLevel) qualityLevel;
                int bitrate = videoQualityLevel.getBitrate();
                int maxWidth = videoQualityLevel.getMaxWidth();
                int maxHeight = videoQualityLevel.getMaxHeight();
                String fourCC = videoQualityLevel.getFourCC();
                Intrinsics.checkNotNullExpressionValue(fourCC, "it.fourCC");
                highFrameRateQualityHolderInterface.addBitrateResolutionMapping(bitrate, maxWidth, maxHeight, fourCC);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x006b, code lost:
    
        if (r4.qualityHolder.getShouldApplyHFRCappingNonLive() != false) goto L26;
     */
    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.avod.content.smoothstream.manifest.QualityLevel selectQuality(com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState r5, com.amazon.avod.content.smoothstream.manifest.QualityLevel r6) {
        /*
            r4 = this;
            java.lang.String r0 = "state"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "currentQualityLevel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            com.amazon.avod.content.ContentSessionContext r0 = r4.contentSessionContext
            r1 = 0
            java.lang.String r2 = "contentSessionContext"
            if (r0 != 0) goto L16
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L16:
            com.amazon.avod.content.smoothstream.manifest.Manifest r0 = r0.getManifest()
            com.amazon.avod.content.smoothstream.manifest.StreamIndex r0 = r0.getVideoStream()
            com.amazon.avod.content.smoothstream.manifest.StreamIndex r3 = r5.getStream()
            boolean r3 = r3.isVideo()
            if (r3 == 0) goto L8c
            if (r0 == 0) goto L8c
            com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface r3 = r4.qualityHolder
            float r0 = r0.getMaxFrameRate()
            boolean r0 = r3.isHighFrameRate(r0)
            if (r0 == 0) goto L8c
            com.amazon.avod.content.ContentSessionContext r0 = r4.contentSessionContext
            if (r0 == 0) goto L8c
            if (r0 != 0) goto L40
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L40:
            com.amazon.avod.media.playback.VideoSpecification r0 = r0.getSpecification()
            boolean r0 = r0.isLiveStream()
            if (r0 == 0) goto L52
            com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface r0 = r4.qualityHolder
            boolean r0 = r0.getShouldApplyHFRCappingLive()
            if (r0 != 0) goto L6d
        L52:
            com.amazon.avod.content.ContentSessionContext r0 = r4.contentSessionContext
            if (r0 != 0) goto L5a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L5b
        L5a:
            r1 = r0
        L5b:
            com.amazon.avod.media.playback.VideoSpecification r0 = r1.getSpecification()
            boolean r0 = r0.isLiveStream()
            if (r0 != 0) goto L8c
            com.amazon.avod.content.smoothstream.quality.HighFrameRateQualityHolderInterface r0 = r4.qualityHolder
            boolean r0 = r0.getShouldApplyHFRCappingNonLive()
            if (r0 == 0) goto L8c
        L6d:
            com.amazon.avod.content.smoothstream.manifest.StreamIndex r0 = r5.getStream()
            int r0 = r0.getIndex()
            com.amazon.avod.content.smoothstream.manifest.StreamIndex r5 = r5.getStream()
            com.amazon.avod.content.smoothstream.manifest.QualityLevel[] r5 = r5.getSortedQualityLevels(r0)
            java.lang.String r0 = "sortedQualityLevels"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)
            com.amazon.avod.content.smoothstream.manifest.QualityLevel r6 = r4.tryBitrateCap(r5, r6)
            com.amazon.avod.content.smoothstream.manifest.QualityLevel r5 = r4.tryResolutionCap(r5, r6)
            return r5
        L8c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.quality.HighFrameRateCappedQualitySelectionComponent.selectQuality(com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionState, com.amazon.avod.content.smoothstream.manifest.QualityLevel):com.amazon.avod.content.smoothstream.manifest.QualityLevel");
    }

    private final QualityLevel tryBitrateCap(QualityLevel[] sortedQualityLevels, QualityLevel currentQualityLevel) {
        Integer num;
        QualityLevel qualityLevelBitrateCappingKernel;
        String fourCC = currentQualityLevel.getFourCC();
        if (Intrinsics.areEqual(fourCC, VideoStreamType.H264.getFourCC())) {
            num = this.avcDeviceBitrateCap;
        } else {
            num = Intrinsics.areEqual(fourCC, VideoStreamType.H265.getFourCC()) ? this.hevcDeviceBitrateCap : null;
        }
        if (num == null || (qualityLevelBitrateCappingKernel = bitrateCappingKernel(num.intValue(), sortedQualityLevels)) == null || qualityLevelBitrateCappingKernel.getBitrate() >= currentQualityLevel.getBitrate()) {
            return currentQualityLevel;
        }
        DLog.devf("HFRQualityCap Heuristics " + currentQualityLevel + " BitrateCap " + qualityLevelBitrateCappingKernel);
        return qualityLevelBitrateCappingKernel;
    }

    private final QualityLevel tryResolutionCap(QualityLevel[] sortedQualityLevels, QualityLevel currentQualityLevel) {
        VideoResolution.ResolutionBand resolutionBand;
        QualityLevel qualityLevelResolutionCappingKernel;
        String fourCC = currentQualityLevel.getFourCC();
        if (Intrinsics.areEqual(fourCC, VideoStreamType.H264.getFourCC())) {
            resolutionBand = this.avcDeviceResolutionCap;
        } else {
            resolutionBand = Intrinsics.areEqual(fourCC, VideoStreamType.H265.getFourCC()) ? this.hevcDeviceResolutionCap : VideoResolution.ResolutionBand.UNKNOWN;
        }
        if (resolutionBand == VideoResolution.ResolutionBand.UNKNOWN || (qualityLevelResolutionCappingKernel = resolutionCappingKernel(resolutionBand, sortedQualityLevels)) == null || qualityLevelResolutionCappingKernel.getBitrate() >= currentQualityLevel.getBitrate()) {
            return currentQualityLevel;
        }
        DLog.devf("HFRQualityCap Heuristics " + currentQualityLevel + " ResolutionCap " + qualityLevelResolutionCappingKernel);
        return qualityLevelResolutionCappingKernel;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.QualityCappingUpdateListener
    public void update() {
        HighFrameRateQualityHolderInterface highFrameRateQualityHolderInterface = this.qualityHolder;
        VideoStreamType videoStreamType = VideoStreamType.H264;
        this.avcDeviceBitrateCap = highFrameRateQualityHolderInterface.getBitrateCap(videoStreamType.getFourCC());
        this.avcDeviceResolutionCap = getResolutionCap(videoStreamType.getFourCC());
        HighFrameRateQualityHolderInterface highFrameRateQualityHolderInterface2 = this.qualityHolder;
        VideoStreamType videoStreamType2 = VideoStreamType.H265;
        this.hevcDeviceBitrateCap = highFrameRateQualityHolderInterface2.getBitrateCap(videoStreamType2.getFourCC());
        this.hevcDeviceResolutionCap = getResolutionCap(videoStreamType2.getFourCC());
        DLog.logf("Modified HFRQualityCap AVC maxBitrate " + this.avcDeviceBitrateCap + " maxResolution " + this.avcDeviceResolutionCap);
        DLog.logf("Modified HFRQualityCap HEVC maxBitrate " + this.hevcDeviceBitrateCap + " maxResolution " + this.hevcDeviceResolutionCap);
    }

    private final VideoResolution.ResolutionBand getResolutionCap(String fourCC) {
        VideoResolution.ResolutionBand staticResolutionCap = this.qualityHolder.getStaticResolutionCap(fourCC);
        VideoResolution.ResolutionBand dynamicResolutionCap = this.qualityHolder.getDynamicResolutionCap(fourCC);
        return (this.qualityHolder.getShouldApplyDynamicQualityCap() && dynamicResolutionCap.satisfiesOrExceeds(staticResolutionCap.getMinWidth(), staticResolutionCap.getMinHeight())) ? dynamicResolutionCap : staticResolutionCap;
    }

    private final QualityLevel bitrateCappingKernel(int deviceBitrateCap, QualityLevel[] sortedQualityLevels) {
        int length = sortedQualityLevels.length;
        QualityLevel qualityLevel = null;
        int i = 0;
        while (i < length) {
            QualityLevel qualityLevel2 = sortedQualityLevels[i];
            i++;
            if ((qualityLevel2 instanceof VideoQualityLevel) && ((VideoQualityLevel) qualityLevel2).getBitrate() <= deviceBitrateCap) {
                qualityLevel = qualityLevel2;
            }
        }
        return qualityLevel;
    }

    private final QualityLevel resolutionCappingKernel(VideoResolution.ResolutionBand deviceResolutionCap, QualityLevel[] sortedQualityLevels) {
        int length = sortedQualityLevels.length;
        QualityLevel qualityLevel = null;
        int i = 0;
        while (i < length) {
            QualityLevel qualityLevel2 = sortedQualityLevels[i];
            i++;
            if (qualityLevel2 instanceof VideoQualityLevel) {
                VideoQualityLevel videoQualityLevel = (VideoQualityLevel) qualityLevel2;
                if (deviceResolutionCap.doesNotExceed(videoQualityLevel.getMaxWidth(), videoQualityLevel.getMaxHeight())) {
                    qualityLevel = qualityLevel2;
                }
            }
        }
        return qualityLevel;
    }
}
