package com.amazon.avod.media.playback.pipeline;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public class VideoFrameReleaseTimeHelper {
    private long mAdjustedLastFrameTimeNs;
    private long mFrameCount;
    private boolean mHaveSync;
    private long mLastFramePresentationTimeUs;
    private long mPendingAdjustedFrameTimeNs;
    private long mSyncFramePresentationTimeNs;
    private long mSyncUnadjustedReleaseTimeNs;
    private final long mVsyncDurationNs;
    private final long mVsyncOffsetNs;
    private final VSyncSampler mVsyncSampler;

    public VideoFrameReleaseTimeHelper(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        this.mVsyncSampler = VSyncSampler.getInstance();
        long nanos = (long) (TimeUnit.SECONDS.toNanos(1L) / ((double) getDefaultDisplayRefreshRate(context)));
        this.mVsyncDurationNs = nanos;
        this.mVsyncOffsetNs = (nanos * 80) / 100;
    }

    public void enable() {
        this.mHaveSync = false;
        this.mVsyncSampler.addObserver();
    }

    public void disable() {
        this.mVsyncSampler.removeObserver();
    }

    public long adjustReleaseTime(long j, long j2) {
        long j3;
        long j4;
        long j5 = 1000 * j;
        if (this.mHaveSync) {
            if (j != this.mLastFramePresentationTimeUs) {
                this.mFrameCount++;
                this.mAdjustedLastFrameTimeNs = this.mPendingAdjustedFrameTimeNs;
            }
            long j6 = this.mFrameCount;
            if (j6 >= 6) {
                j4 = this.mAdjustedLastFrameTimeNs + ((j5 - this.mSyncFramePresentationTimeNs) / j6);
                if (isDriftTooLarge(j4, j2)) {
                    this.mHaveSync = false;
                } else {
                    j3 = (this.mSyncUnadjustedReleaseTimeNs + j4) - this.mSyncFramePresentationTimeNs;
                }
            } else if (isDriftTooLarge(j5, j2)) {
                this.mHaveSync = false;
            }
            j3 = j2;
            j4 = j5;
        } else {
            j3 = j2;
            j4 = j5;
        }
        if (!this.mHaveSync) {
            this.mSyncFramePresentationTimeNs = j5;
            this.mSyncUnadjustedReleaseTimeNs = j2;
            this.mFrameCount = 0L;
            this.mHaveSync = true;
        }
        this.mLastFramePresentationTimeUs = j;
        this.mPendingAdjustedFrameTimeNs = j4;
        return this.mVsyncSampler.mSampledVsyncTimeNs == 0 ? j3 : closestVsync(j3, this.mVsyncSampler.mSampledVsyncTimeNs, this.mVsyncDurationNs) - this.mVsyncOffsetNs;
    }

    private boolean isDriftTooLarge(long j, long j2) {
        return Math.abs((j2 - this.mSyncUnadjustedReleaseTimeNs) - (j - this.mSyncFramePresentationTimeNs)) > 20000000;
    }

    private static long closestVsync(long j, long j2, long j3) {
        long j4;
        long j5 = j2 + (((j - j2) / j3) * j3);
        if (j <= j5) {
            j4 = j5 - j3;
        } else {
            j5 = j3 + j5;
            j4 = j5;
        }
        return j5 - j < j - j4 ? j5 : j4;
    }

    private static float getDefaultDisplayRefreshRate(@Nonnull Context context) {
        float refreshRate = ((DisplayManager) context.getSystemService("display")).getDisplay(0).getRefreshRate();
        if (refreshRate <= 0.0f) {
            return 60.0f;
        }
        if (refreshRate <= 240.0f) {
            return refreshRate;
        }
        return 240.0f;
    }

    private static final class VSyncSampler implements Choreographer.FrameCallback, Handler.Callback {
        private static final VSyncSampler INSTANCE = new VSyncSampler();
        private Choreographer mChoreographer;
        private final HandlerThread mChoreographerOwnerThread;
        private final Handler mHandler;
        public volatile long mSampledVsyncTimeNs;

        public static VSyncSampler getInstance() {
            return INSTANCE;
        }

        private VSyncSampler() {
            HandlerThread handlerThread = new HandlerThread("ChoreographerOwner:Handler");
            this.mChoreographerOwnerThread = handlerThread;
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper(), this);
            this.mHandler = handler;
            handler.sendEmptyMessage(0);
        }

        public void addObserver() {
            this.mHandler.sendEmptyMessage(1);
        }

        public void removeObserver() {
            this.mHandler.sendEmptyMessage(2);
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            this.mSampledVsyncTimeNs = j;
            this.mChoreographer.postFrameCallbackDelayed(this, 500L);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                createChoreographerInstanceInternal();
                return true;
            }
            if (i == 1) {
                addObserverInternal();
                return true;
            }
            if (i != 2) {
                return false;
            }
            removeObserverInternal();
            return true;
        }

        private void createChoreographerInstanceInternal() {
            this.mChoreographer = Choreographer.getInstance();
        }

        private void addObserverInternal() {
            this.mChoreographer.postFrameCallback(this);
        }

        private void removeObserverInternal() {
            this.mChoreographer.removeFrameCallback(this);
        }
    }
}
