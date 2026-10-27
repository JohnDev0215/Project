package com.amazon.avod.playback;

import com.amazon.avod.metrics.pmet.MetricParameter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class BufferingAnalysis {
    private final long mAvailAudioBufferNanos;
    private final long mAvailVideoBufferNanos;
    private final BufferType mBufferType;
    private final boolean mIsContentReady;
    private final boolean mIsRendererStall;
    private final long mLastAudioContentPositionNanos;
    private final long mLastAudioPlayerPositionNanos;
    private final long mLastVideoContentPositionNanos;
    private final long mLastVideoPlayerPositionNanos;
    private final long mPlayHeadNanos;

    public enum BufferType implements MetricParameter {
        RENDERER_UNDERRUN,
        VIDEO_CONTENT_UNDERRUN,
        AUDIO_CONTENT_UNDERRUN,
        UNKNOWN;

        @Override // com.amazon.avod.metrics.pmet.MetricParameter
        @Nonnull
        public String toReportableString() {
            return name();
        }
    }

    private BufferingAnalysis(@Nonnull BufferType bufferType, boolean z, boolean z2, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.mBufferType = (BufferType) Preconditions.checkNotNull(bufferType, "bufferType");
        this.mIsRendererStall = z;
        this.mIsContentReady = z2;
        this.mPlayHeadNanos = j;
        this.mAvailVideoBufferNanos = j2;
        this.mAvailAudioBufferNanos = j3;
        this.mLastVideoPlayerPositionNanos = j4;
        this.mLastVideoContentPositionNanos = j5;
        this.mLastAudioPlayerPositionNanos = j6;
        this.mLastAudioContentPositionNanos = j7;
    }

    @Nonnull
    public BufferType getBufferType() {
        return this.mBufferType;
    }

    public String toString() {
        return "BufferingAnalysis{mBufferType=" + this.mBufferType + ", mIsRendererStall=" + this.mIsRendererStall + ", mIsContentReady=" + this.mIsContentReady + ", mPlayHeadNanos=" + this.mPlayHeadNanos + ", mAvailVideoBufferNanos=" + this.mAvailVideoBufferNanos + ", mAvailAudioBufferNanos=" + this.mAvailAudioBufferNanos + ", mLastVideoPlayerPositionNanos=" + this.mLastVideoPlayerPositionNanos + ", mLastVideoContentPositionNanos=" + this.mLastVideoContentPositionNanos + ", mLastAudioPlayerPositionNanos=" + this.mLastAudioPlayerPositionNanos + ", mLastAudioContentPositionNanos=" + this.mLastAudioContentPositionNanos + '}';
    }

    public static final class Builder {
        private boolean mIsContentReady;
        private boolean mIsRendererStall;
        private long mLastAudioContentPositionNanos;
        private long mLastAudioPlayerPositionNanos;
        private long mLastVideoContentPositionNanos;
        private long mLastVideoPlayerPositionNanos;
        private long mPlayHeadNanos;

        public Builder setPlayHeadNanos(long j) {
            this.mPlayHeadNanos = j;
            return this;
        }

        public Builder setLastVideoPlayerPositionNanos(long j) {
            this.mLastVideoPlayerPositionNanos = j;
            return this;
        }

        public Builder setLastVideoContentPositionNanos(long j) {
            this.mLastVideoContentPositionNanos = j;
            return this;
        }

        public Builder setLastAudioPlayerPositionNanos(long j) {
            this.mLastAudioPlayerPositionNanos = j;
            return this;
        }

        public Builder setLastAudioContentPositionNanos(long j) {
            this.mLastAudioContentPositionNanos = j;
            return this;
        }

        public Builder setRendererStall(boolean z) {
            this.mIsRendererStall = z;
            return this;
        }

        public Builder setContentReady(boolean z) {
            this.mIsContentReady = z;
            return this;
        }

        public BufferingAnalysis build() {
            BufferType bufferType;
            long j = this.mLastVideoContentPositionNanos;
            long j2 = this.mLastVideoPlayerPositionNanos;
            long j3 = j - j2;
            long j4 = this.mLastAudioContentPositionNanos;
            long j5 = this.mLastAudioPlayerPositionNanos;
            long j6 = j4 - j5;
            boolean z = this.mIsRendererStall;
            if (z) {
                if (this.mIsContentReady) {
                    bufferType = BufferType.RENDERER_UNDERRUN;
                } else if (j3 <= j6) {
                    bufferType = BufferType.VIDEO_CONTENT_UNDERRUN;
                } else {
                    bufferType = BufferType.AUDIO_CONTENT_UNDERRUN;
                }
            } else {
                bufferType = BufferType.UNKNOWN;
            }
            return new BufferingAnalysis(bufferType, z, this.mIsContentReady, this.mPlayHeadNanos, j3, j6, j2, j, j5, j4);
        }
    }
}
