package com.amazon.avod.content.smoothstream.quality;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.QualityScoreCappingStrategy;
import com.amazon.avod.content.dash.quality.heuristic.HeuristicsPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.util.DLog;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: QualityScoreBitrateSelectionComponent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u001e\u0010\u001fJ \u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J \u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016J\u001a\u0010\u0010\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\n8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lcom/amazon/avod/content/smoothstream/quality/QualityScoreBitrateSelectionComponent;", "Lcom/amazon/avod/content/smoothstream/quality/StreamingBitrateSelectionComponent;", "Lcom/amazon/avod/content/smoothstream/manifest/StreamIndex;", "stream", "Lcom/amazon/avod/content/smoothstream/manifest/QualityLevel;", "currentQualityLevel", "", "chunkIndex", "selectQualityLevelWithMaxQualityScore", "selectQualityLevelWithIdealQualityScore", "Lcom/amazon/avod/content/ContentSessionContext;", "context", "", "initialize", "Lcom/amazon/avod/content/smoothstream/quality/StreamingBitrateSelectionState;", "state", "selectQuality", "Lcom/amazon/avod/content/dash/quality/heuristic/HeuristicsPlaybackConfig;", "heuristicsPlaybackConfig", "Lcom/amazon/avod/content/dash/quality/heuristic/HeuristicsPlaybackConfig;", "getHeuristicsPlaybackConfig", "()Lcom/amazon/avod/content/dash/quality/heuristic/HeuristicsPlaybackConfig;", "Lcom/amazon/avod/content/config/QualityScoreCappingStrategy;", "qualityScoreCappingStrategy", "Lcom/amazon/avod/content/config/QualityScoreCappingStrategy;", "", "desiredMediaQualityScore", "D", "contentSessionContext", "Lcom/amazon/avod/content/ContentSessionContext;", "<init>", "(Lcom/amazon/avod/content/dash/quality/heuristic/HeuristicsPlaybackConfig;)V", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0})
public final class QualityScoreBitrateSelectionComponent implements StreamingBitrateSelectionComponent {
    private ContentSessionContext contentSessionContext;
    private final double desiredMediaQualityScore;
    private final HeuristicsPlaybackConfig heuristicsPlaybackConfig;
    private final QualityScoreCappingStrategy qualityScoreCappingStrategy;

    /* JADX INFO: compiled from: QualityScoreBitrateSelectionComponent.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[QualityScoreCappingStrategy.values().length];
            iArr[QualityScoreCappingStrategy.MAX_QUALITY_SCORE.ordinal()] = 1;
            iArr[QualityScoreCappingStrategy.IDEAL_QUALITY_SCORE.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public QualityScoreBitrateSelectionComponent(HeuristicsPlaybackConfig heuristicsPlaybackConfig) {
        this.heuristicsPlaybackConfig = heuristicsPlaybackConfig;
        QualityScoreCappingStrategy qualityScoreCappingStrategy = heuristicsPlaybackConfig == null ? null : heuristicsPlaybackConfig.getQualityScoreCappingStrategy();
        this.qualityScoreCappingStrategy = qualityScoreCappingStrategy == null ? QualityScoreCappingStrategy.NO_QUALITY_SCORE : qualityScoreCappingStrategy;
        this.desiredMediaQualityScore = heuristicsPlaybackConfig == null ? 100.0d : heuristicsPlaybackConfig.getDesiredMediaQualityScore();
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public void initialize(ContentSessionContext context) {
        if (context == null) {
            return;
        }
        this.contentSessionContext = context;
    }

    @Override // com.amazon.avod.content.smoothstream.quality.StreamingBitrateSelectionComponent
    public QualityLevel selectQuality(StreamingBitrateSelectionState state, QualityLevel currentQualityLevel) {
        Intrinsics.checkNotNullParameter(currentQualityLevel, "currentQualityLevel");
        if (state == null) {
            return currentQualityLevel;
        }
        StreamIndex stream = state.getStream();
        Intrinsics.checkNotNullExpressionValue(stream, "state.stream");
        if (!stream.isVideo()) {
            return currentQualityLevel;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this.qualityScoreCappingStrategy.ordinal()];
        if (i != 1) {
            return i != 2 ? currentQualityLevel : selectQualityLevelWithIdealQualityScore(stream, currentQualityLevel, state.getChunkIndex());
        }
        return selectQualityLevelWithMaxQualityScore(stream, currentQualityLevel, state.getChunkIndex());
    }

    private final QualityLevel selectQualityLevelWithMaxQualityScore(StreamIndex stream, QualityLevel currentQualityLevel, int chunkIndex) {
        QualityLevel[] sortedQualityLevels = stream.getSortedQualityLevels(stream.getIndex());
        Intrinsics.checkNotNullExpressionValue(sortedQualityLevels, "sortedQualityLevels");
        int length = sortedQualityLevels.length;
        double d = Double.MIN_VALUE;
        int i = 0;
        QualityLevel qualityLevel = currentQualityLevel;
        while (i < length) {
            QualityLevel qualityLevel2 = sortedQualityLevels[i];
            i++;
            if (qualityLevel2 instanceof VideoQualityLevel) {
                double chunkQualityScore = stream.getChunkQualityScore(qualityLevel2, chunkIndex);
                StringBuilder sb = new StringBuilder();
                sb.append("QualityScore Index ");
                sb.append(chunkIndex);
                sb.append(" Bitrate ");
                VideoQualityLevel videoQualityLevel = (VideoQualityLevel) qualityLevel2;
                sb.append(videoQualityLevel.getBitrate());
                sb.append(" Score ");
                sb.append(chunkQualityScore);
                sb.append(" DesiredScore ");
                sb.append(this.desiredMediaQualityScore);
                sb.append(' ');
                DLog.devf(sb.toString());
                if (chunkQualityScore > d && chunkQualityScore <= this.desiredMediaQualityScore && videoQualityLevel.getBitrate() <= currentQualityLevel.getBitrate()) {
                    qualityLevel = qualityLevel2;
                    d = chunkQualityScore;
                }
            }
        }
        DLog.devf("MaxQualityScore capping InputBitrate " + currentQualityLevel.getBitrate() + " capped " + qualityLevel.getBitrate());
        return qualityLevel;
    }

    private final QualityLevel selectQualityLevelWithIdealQualityScore(StreamIndex stream, QualityLevel currentQualityLevel, int chunkIndex) {
        StreamIndex streamIndex = stream;
        QualityLevel[] sortedQualityLevels = streamIndex.getSortedQualityLevels(stream.getIndex());
        Intrinsics.checkNotNullExpressionValue(sortedQualityLevels, "sortedQualityLevels");
        int length = sortedQualityLevels.length;
        double d = Double.MAX_VALUE;
        int i = 0;
        QualityLevel qualityLevel = currentQualityLevel;
        while (i < length) {
            QualityLevel qualityLevel2 = sortedQualityLevels[i];
            i++;
            if (qualityLevel2 instanceof VideoQualityLevel) {
                double chunkQualityScore = streamIndex.getChunkQualityScore(qualityLevel2, chunkIndex);
                double dAbs = Math.abs(this.desiredMediaQualityScore - chunkQualityScore);
                StringBuilder sb = new StringBuilder();
                sb.append("QualityScore Index ");
                sb.append(chunkIndex);
                sb.append(" Bitrate ");
                VideoQualityLevel videoQualityLevel = (VideoQualityLevel) qualityLevel2;
                sb.append(videoQualityLevel.getBitrate());
                sb.append(" Score ");
                sb.append(chunkQualityScore);
                sb.append(" DesiredScore ");
                sb.append(this.desiredMediaQualityScore);
                DLog.devf(sb.toString());
                if (dAbs < d && videoQualityLevel.getBitrate() <= currentQualityLevel.getBitrate()) {
                    streamIndex = stream;
                    qualityLevel = qualityLevel2;
                    d = dAbs;
                }
            }
            streamIndex = stream;
        }
        DLog.devf("IdealQualityScore capping InputBitrate " + currentQualityLevel.getBitrate() + " capped " + qualityLevel.getBitrate());
        return qualityLevel;
    }
}
