package com.amazon.avod.content.smoothstream.manifest;

import androidx.core.util.Pair;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.RefreshableManifestValidator;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PeriodView {
    private final int mLatestVirtualIndex;

    @Nonnull
    private final List<Period> mPeriods;

    @Nonnull
    private final List<Period> mReversedPeriods;
    private final MultiPeriodStreamIndexSynchronizer mSynchronizer;

    public static final class Builder {
        private int currentVirtualIndex;
        private final boolean mIsPeriodViewDebugLogsEnabled;
        private final boolean mManifestFragmentContiguityValidationEnabled;
        private final List<Period> mPeriods;
        private final PeriodView mPreviousPeriodView;
        private final MultiPeriodStreamIndexSynchronizer mPreviousSynchronizer;

        public Builder() {
            this(RefreshableManifestValidator.RefreshableManifestValidatorConfig.getInstance());
        }

        Builder(@Nonnull RefreshableManifestValidator.RefreshableManifestValidatorConfig refreshableManifestValidatorConfig) {
            this.currentVirtualIndex = 0;
            this.mPeriods = Lists.newLinkedList();
            Preconditions.checkNotNull(refreshableManifestValidatorConfig, "refreshableManifestValidatorConfig");
            this.mPreviousPeriodView = null;
            this.mPreviousSynchronizer = new MultiPeriodStreamIndexSynchronizer();
            this.mIsPeriodViewDebugLogsEnabled = refreshableManifestValidatorConfig.isPeriodViewDebugLogsEnabled();
            this.mManifestFragmentContiguityValidationEnabled = refreshableManifestValidatorConfig.getManifestFragmentContiguityValidationEnabled().booleanValue();
        }

        public Builder(@Nonnull RefreshableManifestValidator.RefreshableManifestValidatorConfig refreshableManifestValidatorConfig, PeriodView periodView) {
            this.currentVirtualIndex = 0;
            this.mPeriods = Lists.newLinkedList();
            Preconditions.checkNotNull(refreshableManifestValidatorConfig, "refreshableManifestValidatorConfig");
            this.mPreviousPeriodView = periodView;
            this.mPreviousSynchronizer = periodView.getStreamIndexSynchronizer();
            this.mIsPeriodViewDebugLogsEnabled = refreshableManifestValidatorConfig.isPeriodViewDebugLogsEnabled();
            this.mManifestFragmentContiguityValidationEnabled = refreshableManifestValidatorConfig.getManifestFragmentContiguityValidationEnabled().booleanValue();
        }

        public Builder withPeriod(@Nonnull String str, int i, int i2, @Nonnull StreamIndex streamIndex) throws ContentException {
            Range range;
            Range range2;
            long j;
            Pair pairLookupManifestToVirtualIndexRanges;
            Preconditions.checkNotNull(str, "periodId");
            Preconditions.checkNotNull(streamIndex, "streamIndex");
            int index = streamIndex.getIndex();
            Range rangeClosed = Range.closed(Integer.valueOf(i), Integer.valueOf(i2));
            int i3 = i2 - i;
            PeriodView periodView = this.mPreviousPeriodView;
            boolean z = false;
            if (periodView != null && (pairLookupManifestToVirtualIndexRanges = periodView.lookupManifestToVirtualIndexRanges(str)) != null) {
                Range range3 = (Range) Preconditions.checkNotNull(pairLookupManifestToVirtualIndexRanges.first, "first");
                this.currentVirtualIndex = (((Integer) rangeClosed.lowerEndpoint()).intValue() - ((Integer) range3.lowerEndpoint()).intValue()) + ((Integer) ((Range) Preconditions.checkNotNull(pairLookupManifestToVirtualIndexRanges.second, "second")).lowerEndpoint()).intValue();
                if (this.mManifestFragmentContiguityValidationEnabled && !range3.isConnected(rangeClosed)) {
                    throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, String.format(Locale.US, "Manifest indices are not connected: [%d,%d] and [%d,%d]", range3.lowerEndpoint(), range3.upperEndpoint(), rangeClosed.lowerEndpoint(), rangeClosed.upperEndpoint()));
                }
                z = true;
            }
            Integer numValueOf = Integer.valueOf(this.currentVirtualIndex);
            int i4 = this.currentVirtualIndex + i3;
            this.currentVirtualIndex = i4;
            Range rangeClosed2 = Range.closed(numValueOf, Integer.valueOf(i4));
            this.currentVirtualIndex++;
            long chunkTimeInNanos = streamIndex.getChunkTimeInNanos(i);
            long chunkTimeInNanos2 = streamIndex.getChunkTimeInNanos(i2) + streamIndex.getChunkDurationInNanos(i2);
            if (z && this.mIsPeriodViewDebugLogsEnabled) {
                range = rangeClosed;
                range2 = rangeClosed2;
                j = chunkTimeInNanos2;
                DLog.logf("PeriodView Stream: %d :%-10s PeriodId: %-15s Virtual: %-15s Manifest: %-15s TimelineRange: %d ~ %d", Integer.valueOf(index), streamIndex.getType(), str, rangeClosed2, rangeClosed, Long.valueOf(chunkTimeInNanos), Long.valueOf(chunkTimeInNanos2));
            } else {
                range = rangeClosed;
                range2 = rangeClosed2;
                j = chunkTimeInNanos2;
            }
            TimeUnit timeUnit = TimeUnit.NANOSECONDS;
            long encodeTimeMillis = streamIndex.getEncodeTimeMillis(timeUnit.toMillis(chunkTimeInNanos));
            long encodeTimeMillis2 = streamIndex.getEncodeTimeMillis(timeUnit.toMillis(j));
            DLog.devf("PeriodView  Stream: %d :%-10s PeriodId: %-15s EncodeTimelineRange(ms): %d ~ %d", Integer.valueOf(index), streamIndex.getType(), str, Long.valueOf(encodeTimeMillis), Long.valueOf(encodeTimeMillis2));
            this.mPeriods.add(new Period(str, streamIndex, range, range2, Range.closedOpen(Long.valueOf(chunkTimeInNanos), Long.valueOf(j)), Range.closed(Long.valueOf(encodeTimeMillis), Long.valueOf(encodeTimeMillis2))));
            return this;
        }

        public PeriodView build() {
            return new PeriodView(this.mPeriods, this.mPreviousSynchronizer);
        }
    }

    public static PeriodView createEmptyPeriod() {
        return new PeriodView(Collections.emptyList(), new MultiPeriodStreamIndexSynchronizer());
    }

    protected PeriodView(List<Period> list, MultiPeriodStreamIndexSynchronizer multiPeriodStreamIndexSynchronizer) {
        list = list == null ? Collections.emptyList() : list;
        this.mPeriods = list;
        this.mReversedPeriods = Lists.reverse(list);
        this.mSynchronizer = multiPeriodStreamIndexSynchronizer;
        if (list.isEmpty()) {
            this.mLatestVirtualIndex = -1;
        } else {
            this.mLatestVirtualIndex = ((Integer) list.get(list.size() - 1).getVirtualIndexRange().upperEndpoint()).intValue();
        }
    }

    public Pair<Integer, StreamIndex> lookupManifestIndexAndStream(int i) {
        for (Period period : this.mReversedPeriods) {
            Range<Integer> virtualIndexRange = period.getVirtualIndexRange();
            if (virtualIndexRange.contains(Integer.valueOf(i))) {
                return Pair.create(Integer.valueOf(((Integer) period.getIndexRange().upperEndpoint()).intValue() - (((Integer) virtualIndexRange.upperEndpoint()).intValue() - i)), period.getStreamIndex());
            }
        }
        DLog.devf("warning! cannot find requested StreamIndex for virtualIndex " + i);
        for (Period period2 : this.mReversedPeriods) {
            DLog.devf("warning! PeriodRange StreamType:%10s periodId :%s -> %d : %d", period2.getStreamIndex().getType(), period2.getId(), period2.getVirtualIndexRange().lowerEndpoint(), period2.getVirtualIndexRange().upperEndpoint());
        }
        throw new IndexOutOfBoundsException(String.format(Locale.US, "Requested streamIndex for virtual segmentIndex: %s not found", Integer.valueOf(i)));
    }

    public String buildInvalidSegmentTimeQos(long j) {
        ListIterator<Period> listIterator = this.mPeriods.listIterator();
        if (this.mPeriods.isEmpty()) {
            return String.format(Locale.US, "Requested virtual Index for segmentTimeInNanos: %s Period List is empty!", Long.valueOf(j));
        }
        long jLongValue = ((Long) this.mPeriods.get(0).getTimeRangeInNanos().lowerEndpoint()).longValue();
        long jLongValue2 = ((Long) this.mReversedPeriods.get(0).getTimeRangeInNanos().upperEndpoint()).longValue();
        Period period = null;
        while (listIterator.hasNext()) {
            Period next = listIterator.next();
            if (period != null && j < ((Long) next.getTimeRangeInNanos().lowerEndpoint()).longValue() && j >= ((Long) period.getTimeRangeInNanos().upperEndpoint()).longValue()) {
                return String.format(Locale.US, "Requested virtual Index for segmentTimeInNanos: %s not found in timeline: %s -> %s gap range: (%s, %s) between periods: (%s, %s)", Long.valueOf(j), Long.valueOf(jLongValue), Long.valueOf(jLongValue2), period.getTimeRangeInNanos().upperEndpoint(), next.getTimeRangeInNanos().lowerEndpoint(), next.getId(), period.getId());
            }
            period = next;
        }
        return String.format(Locale.US, "Requested virtual Index for segmentTimeInNanos: %s is outside the timeline: %s -> %s ", Long.valueOf(j), Long.valueOf(jLongValue), Long.valueOf(jLongValue2));
    }

    public int lookupVirtualIndex(long j) {
        for (Period period : this.mReversedPeriods) {
            if (period.getTimeRangeInNanos().contains(Long.valueOf(j))) {
                return ((Integer) period.getVirtualIndexRange().upperEndpoint()).intValue() - (((Integer) period.getIndexRange().upperEndpoint()).intValue() - period.getStreamIndex().getChunkIndexFromNanos(j));
            }
        }
        DLog.devf("warning! cannot find virtual Index for segmentTimeInNanos " + j);
        for (Period period2 : this.mReversedPeriods) {
            DLog.devf("warning! PeriodRange StreamType:%10s periodId :%s -> %d : %d", period2.getStreamIndex().getType(), period2.getId(), period2.getTimeRangeInNanos().lowerEndpoint(), period2.getTimeRangeInNanos().upperEndpoint());
        }
        throw new IndexOutOfBoundsException(buildInvalidSegmentTimeQos(j));
    }

    public StreamIndex lookupPeriodWithTimestamp(long j) {
        for (Period period : this.mReversedPeriods) {
            if (period.getTimeRangeInNanos().contains(Long.valueOf(j))) {
                return period.getStreamIndex();
            }
        }
        DLog.devf("warning! cannot find stream index for segmentTimeInNanos " + j);
        for (Period period2 : this.mReversedPeriods) {
            DLog.devf("warning! PeriodRange StreamType:%10s periodId:%s -> %d : %d", period2.getStreamIndex().getType(), period2.getId(), period2.getTimeRangeInNanos().lowerEndpoint(), period2.getTimeRangeInNanos().upperEndpoint());
        }
        throw new IndexOutOfBoundsException(String.format(Locale.US, "Requested stream index for segmentTimeInNanos: %s is out of window", Long.valueOf(j)));
    }

    public long convertMediaTimeToEncodeTimeMillis(long j) {
        try {
            return lookupPeriodWithTimestamp(TimeUnit.MILLISECONDS.toNanos(j)).getEncodeTimeMillis(j);
        } catch (IndexOutOfBoundsException unused) {
            return -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public Pair<Range<Integer>, Range<Integer>> lookupManifestToVirtualIndexRanges(String str) {
        for (Period period : this.mReversedPeriods) {
            if (period.getId().equals(str)) {
                return new Pair<>(period.getIndexRange(), period.getVirtualIndexRange());
            }
        }
        return null;
    }

    public int getLatestVirtualIndex() {
        return this.mLatestVirtualIndex;
    }

    public MultiPeriodStreamIndexSynchronizer getStreamIndexSynchronizer() {
        return this.mSynchronizer;
    }

    @Nonnull
    public List<Period> getPeriods() {
        return this.mPeriods;
    }

    @Nullable
    public String lookupPeriodIdWithTimestamp(long j) {
        for (Period period : this.mReversedPeriods) {
            if (period.getTimeRangeInNanos().contains(Long.valueOf(j))) {
                return period.getId();
            }
        }
        DLog.devf("warning! Cannot find period for timestampNanos " + j);
        for (Period period2 : this.mReversedPeriods) {
            DLog.devf("warning! PeriodRange StreamType:%10s periodId:%s -> %d : %d", period2.getStreamIndex().getType(), period2.getId(), period2.getTimeRangeInNanos().lowerEndpoint(), period2.getTimeRangeInNanos().upperEndpoint());
        }
        return null;
    }

    public static class Period {
        private final Range<Long> mEncodeTimeRangeInMillis;
        private final String mId;
        private final Range<Integer> mIndexRange;
        private final StreamIndex mStreamIndex;
        private final Range<Long> mTimeRangeInNanos;
        private final Range<Integer> mVirtualIndexRange;

        protected Period(@Nonnull String str, @Nonnull StreamIndex streamIndex, @Nonnull Range<Integer> range, @Nonnull Range<Integer> range2, @Nonnull Range<Long> range3, @Nonnull Range<Long> range4) {
            this.mId = (String) Preconditions.checkNotNull(str, "id");
            this.mStreamIndex = (StreamIndex) Preconditions.checkNotNull(streamIndex, "streamIndex");
            this.mIndexRange = (Range) Preconditions.checkNotNull(range, "indexRange");
            this.mVirtualIndexRange = (Range) Preconditions.checkNotNull(range2, "virtualIndexRange");
            this.mTimeRangeInNanos = (Range) Preconditions.checkNotNull(range3, "timeRangeInNanos");
            this.mEncodeTimeRangeInMillis = (Range) Preconditions.checkNotNull(range4, "encodeTimeRangeInMillis");
        }

        @Nonnull
        public String getId() {
            return this.mId;
        }

        @Nonnull
        public StreamIndex getStreamIndex() {
            return this.mStreamIndex;
        }

        @Nonnull
        public Range<Integer> getIndexRange() {
            return this.mIndexRange;
        }

        @Nonnull
        public Range<Integer> getVirtualIndexRange() {
            return this.mVirtualIndexRange;
        }

        @Nonnull
        public Range<Long> getTimeRangeInNanos() {
            return this.mTimeRangeInNanos;
        }
    }
}
