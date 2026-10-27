package com.amazon.avod.playback.smoothstream.diagnostics;

import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class DiagnosticDataPoint extends DataPoint {
    private final long mAverageDownloadBitsPerSecond;
    private final int mBitrate;
    private final Optional<Integer> mBitrateCap;
    private final Optional<String> mBitrateCapDescription;
    private final long mDownloadBitsPerSecond;
    private final FragmentType mFragmentType;
    private final long mInMemoryBufferInNanoseconds;
    private final long mLatencyInNanos;
    private final long mNativeBufferInNanoseconds;
    private final StreamType mStreamType;

    public DiagnosticDataPoint(TimeSpan timeSpan, int i, @Nonnull Optional<Integer> optional, @Nonnull Optional<String> optional2, long j, long j2, long j3, long j4, FragmentType fragmentType, StreamType streamType, long j5) {
        super(timeSpan);
        this.mBitrate = i;
        this.mBitrateCap = (Optional) Preconditions.checkNotNull(optional, "bitrateCap");
        this.mBitrateCapDescription = (Optional) Preconditions.checkNotNull(optional2, "bitrateCapDescription");
        this.mDownloadBitsPerSecond = j;
        this.mAverageDownloadBitsPerSecond = j2;
        this.mNativeBufferInNanoseconds = j3;
        this.mInMemoryBufferInNanoseconds = j4;
        this.mFragmentType = fragmentType;
        this.mStreamType = streamType;
        this.mLatencyInNanos = j5;
    }

    public int getBitrate() {
        return this.mBitrate;
    }

    @Nonnull
    public Optional<Integer> getBitrateCap() {
        return this.mBitrateCap;
    }

    @Nonnull
    public Optional<String> getBitrateCapDescription() {
        return this.mBitrateCapDescription;
    }

    public long getDownloadRate() {
        return this.mDownloadBitsPerSecond;
    }

    public long getAverageDownloadRate() {
        return this.mAverageDownloadBitsPerSecond;
    }

    public long getNativeBufferTimeInNanoseconds() {
        return this.mNativeBufferInNanoseconds;
    }

    public long getInMemoryBufferTimeInNanoseconds() {
        return this.mInMemoryBufferInNanoseconds;
    }

    public FragmentType getFragmentType() {
        return this.mFragmentType;
    }

    public StreamType getStreamType() {
        return this.mStreamType;
    }

    public long getLatencyInNanos() {
        return this.mLatencyInNanos;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint, java.lang.Comparable
    public int compareTo(DataPoint dataPoint) {
        Preconditions.checkArgument(dataPoint instanceof DiagnosticDataPoint, "point must be instance of DiagnosticsDataPoint");
        DiagnosticDataPoint diagnosticDataPoint = (DiagnosticDataPoint) dataPoint;
        int iCompareTo = super.compareTo(dataPoint);
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int bitrate = this.mBitrate - diagnosticDataPoint.getBitrate();
        return bitrate != 0 ? bitrate : this.mFragmentType.getValue() - diagnosticDataPoint.getFragmentType().getValue();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        DiagnosticDataPoint diagnosticDataPoint = (DiagnosticDataPoint) obj;
        return Objects.equal(this.mTimeStamp, diagnosticDataPoint.mTimeStamp) && Objects.equal(Integer.valueOf(this.mBitrate), Integer.valueOf(diagnosticDataPoint.mBitrate)) && Objects.equal(this.mFragmentType, diagnosticDataPoint.mFragmentType);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DataPoint
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.mTimeStamp, Integer.valueOf(this.mBitrate), this.mFragmentType);
    }
}
