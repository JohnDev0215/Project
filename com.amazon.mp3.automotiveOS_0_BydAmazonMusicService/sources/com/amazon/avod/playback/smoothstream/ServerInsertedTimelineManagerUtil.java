package com.amazon.avod.playback.smoothstream;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.ads.AdBreak;
import com.amazon.avod.media.ads.AdPlan;
import com.amazon.avod.media.ads.AdPositionType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ServerInsertedTimelineManagerUtil {
    private static long DEFAULT_START_POSITION;

    public static List<ServerInsertedTimelineVector> createTimelineVectors(@Nonnull AdPlan adPlan, long j) {
        Preconditions.checkNotNull(adPlan, "adPlan");
        List<AdBreak> breaks = adPlan.getBreaks();
        ArrayList arrayList = new ArrayList();
        if (breaks.size() == 0) {
            DLog.logf("ServerInsertedPlaybackSession: Cannot create TimelineVectors for empty adplan");
            return arrayList;
        }
        boolean z = false;
        long j2 = 0;
        for (int i = 0; i < breaks.size(); i++) {
            AdBreak adBreak = breaks.get(i);
            long totalNanoSeconds = adBreak.getStartTime().getTotalNanoSeconds();
            long totalNanoSeconds2 = adBreak.getDuration().getTotalNanoSeconds();
            AdPositionType adPositionType = adBreak.getAdPositionType();
            DLog.logf("ServerInsertedPlaybackSession: AdBreak: %s", adBreak.toString());
            if (adPositionType == AdPositionType.PRE_ROLL) {
                long j3 = DEFAULT_START_POSITION;
                TimeSpan timeSpan = TimeSpan.ZERO;
                arrayList.add(createAdContentVector(j3, totalNanoSeconds2, timeSpan.getTotalNanoSeconds(), timeSpan.getTotalNanoSeconds()));
            } else {
                long lastVectorAbsoluteEndPosition = getLastVectorAbsoluteEndPosition(arrayList);
                long jMax = Math.max(0L, lastVectorAbsoluteEndPosition - j2);
                arrayList.add(createMainContentVector(lastVectorAbsoluteEndPosition, totalNanoSeconds, jMax, jMax + (totalNanoSeconds - lastVectorAbsoluteEndPosition)));
                long lastVectorRelativeEndPosition = getLastVectorRelativeEndPosition(arrayList);
                arrayList.add(createAdContentVector(totalNanoSeconds, totalNanoSeconds + totalNanoSeconds2, lastVectorRelativeEndPosition, lastVectorRelativeEndPosition));
                if (adPositionType == AdPositionType.POST_ROLL) {
                    z = true;
                }
            }
            j2 += totalNanoSeconds2;
        }
        if (!z) {
            long lastVectorAbsoluteEndPosition2 = getLastVectorAbsoluteEndPosition(arrayList);
            long jMax2 = Math.max(0L, lastVectorAbsoluteEndPosition2 - j2);
            arrayList.add(createMainContentVectorWithClosedRelativeInterval(lastVectorAbsoluteEndPosition2, j, jMax2, jMax2 + (j - lastVectorAbsoluteEndPosition2)));
        }
        return arrayList;
    }

    private static long getLastVectorAbsoluteEndPosition(@Nonnull List<ServerInsertedTimelineVector> list) {
        int size = list.size();
        return size > 0 ? ((Long) list.get(size - 1).getVectorAbsoluteRange().upperEndpoint()).longValue() : DEFAULT_START_POSITION;
    }

    private static long getLastVectorRelativeEndPosition(@Nonnull List<ServerInsertedTimelineVector> list) {
        return ((Long) list.get(list.size() - 1).getVectorRelativeRange().upperEndpoint()).longValue();
    }

    private static ServerInsertedTimelineVector createMainContentVector(long j, long j2, long j3, long j4) {
        return new ServerInsertedTimelineVector(Range.closedOpen(Long.valueOf(j), Long.valueOf(j2)), Range.closedOpen(Long.valueOf(j3), Long.valueOf(j4)), ServerInsertedTimelineVectorType.MAIN);
    }

    private static ServerInsertedTimelineVector createMainContentVectorWithClosedRelativeInterval(long j, long j2, long j3, long j4) {
        return new ServerInsertedTimelineVector(Range.closedOpen(Long.valueOf(j), Long.valueOf(j2)), Range.closed(Long.valueOf(j3), Long.valueOf(j4)), ServerInsertedTimelineVectorType.MAIN);
    }

    private static ServerInsertedTimelineVector createAdContentVector(long j, long j2, long j3, long j4) {
        return new ServerInsertedTimelineVector(Range.closedOpen(Long.valueOf(j), Long.valueOf(j2)), Range.closed(Long.valueOf(j3), Long.valueOf(j4)), ServerInsertedTimelineVectorType.AD);
    }

    public static long getPlaybackAbsolutePositionForRelativePosition(@Nonnull List<ServerInsertedTimelineVector> list, long j, ServerInsertedTimelineVectorType serverInsertedTimelineVectorType) {
        for (ServerInsertedTimelineVector serverInsertedTimelineVector : list) {
            Range<Long> vectorRelativeRange = serverInsertedTimelineVector.getVectorRelativeRange();
            if (serverInsertedTimelineVector.getTimelineItemType() == serverInsertedTimelineVectorType && vectorRelativeRange.contains(Long.valueOf(j))) {
                return ((Long) serverInsertedTimelineVector.getVectorAbsoluteRange().lowerEndpoint()).longValue() + (j - ((Long) vectorRelativeRange.lowerEndpoint()).longValue());
            }
        }
        throw new IllegalArgumentException(String.format("getAbsolutePositionForRelativePosition: Encountered invalid playback position %s, that's not part of any relative scale", new TimeSpan(j)));
    }

    public static long getNormalisedRelativePosition(List<ServerInsertedTimelineVector> list, long j) {
        for (ServerInsertedTimelineVector serverInsertedTimelineVector : list) {
            if (serverInsertedTimelineVector.getVectorAbsoluteRange().contains(Long.valueOf(j))) {
                if (serverInsertedTimelineVector.getTimelineItemType() == ServerInsertedTimelineVectorType.AD) {
                    return ((Long) serverInsertedTimelineVector.getVectorRelativeRange().upperEndpoint()).longValue();
                }
                return ((Long) serverInsertedTimelineVector.getVectorRelativeRange().lowerEndpoint()).longValue() + (j - ((Long) serverInsertedTimelineVector.getVectorAbsoluteRange().lowerEndpoint()).longValue());
            }
        }
        throw new IllegalArgumentException(String.format("getRelativePositionGeneric: Encountered invalid playback position %s, that's not part of any absolute scale", new TimeSpan(j)));
    }

    public static boolean areInSameChapter(List<ServerInsertedTimelineVector> list, long j, long j2) {
        Iterator<ServerInsertedTimelineVector> it = list.iterator();
        while (it.hasNext()) {
            Range<Long> vectorAbsoluteRange = it.next().getVectorAbsoluteRange();
            if (vectorAbsoluteRange.contains(Long.valueOf(j)) && vectorAbsoluteRange.contains(Long.valueOf(j2))) {
                return true;
            }
        }
        return false;
    }
}
