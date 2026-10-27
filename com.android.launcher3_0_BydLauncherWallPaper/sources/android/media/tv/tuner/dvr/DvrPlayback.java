package android.media.tv.tuner.dvr;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.media.tv.tuner.TunerUtils;
import android.media.tv.tuner.filter.Filter;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.internal.util.FrameworkStatsLog;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DvrPlayback implements AutoCloseable {
    public static final int PLAYBACK_STATUS_ALMOST_EMPTY = 2;
    public static final int PLAYBACK_STATUS_ALMOST_FULL = 4;
    public static final int PLAYBACK_STATUS_EMPTY = 1;
    public static final int PLAYBACK_STATUS_FULL = 8;
    private static final String TAG = "TvTunerPlayback";
    private static int sInstantId;
    private Executor mExecutor;
    private OnPlaybackStatusChangedListener mListener;
    private long mNativeContext;
    private int mSegmentId;
    private int mUnderflow;
    private int mUserId = ActivityManager.getCurrentUser();

    @Retention(RetentionPolicy.SOURCE)
    @interface PlaybackStatus {
    }

    private native int nativeAttachFilter(Filter filter);

    private native int nativeClose();

    private native int nativeConfigureDvr(DvrSettings dvrSettings);

    private native int nativeDetachFilter(Filter filter);

    private native int nativeFlushDvr();

    private native long nativeRead(long j);

    private native long nativeRead(byte[] bArr, long j, long j2);

    private native void nativeSetFileDescriptor(int i);

    private native int nativeStartDvr();

    private native int nativeStopDvr();

    public int attachFilter(Filter filter) {
        return 1;
    }

    public int detachFilter(Filter filter) {
        return 1;
    }

    private DvrPlayback() {
        this.mSegmentId = 0;
        int i = sInstantId;
        this.mSegmentId = (65535 & i) << 16;
        sInstantId = i + 1;
    }

    public void setListener(Executor executor, OnPlaybackStatusChangedListener onPlaybackStatusChangedListener) {
        this.mExecutor = executor;
        this.mListener = onPlaybackStatusChangedListener;
    }

    private void onPlaybackStatusChanged(final int i) {
        if (i == 1) {
            this.mUnderflow++;
        }
        Executor executor = this.mExecutor;
        if (executor == null || this.mListener == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.dvr.-$$Lambda$DvrPlayback$YUVgb9eZOs65hTzunOCoJNbj-Lo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onPlaybackStatusChanged$0$DvrPlayback(i);
            }
        });
    }

    public /* synthetic */ void lambda$onPlaybackStatusChanged$0$DvrPlayback(int i) {
        this.mListener.onPlaybackStatusChanged(i);
    }

    public int configure(DvrSettings dvrSettings) {
        return nativeConfigureDvr(dvrSettings);
    }

    public int start() {
        int i = this.mSegmentId;
        this.mSegmentId = (((i & 65535) + 1) & 65535) | ((-65536) & i);
        this.mUnderflow = 0;
        Log.d(TAG, "Write Stats Log for Playback.");
        FrameworkStatsLog.write(279, this.mUserId, 1, 1, this.mSegmentId, 0);
        return nativeStartDvr();
    }

    public int stop() {
        Log.d(TAG, "Write Stats Log for Playback.");
        FrameworkStatsLog.write(279, this.mUserId, 1, 2, this.mSegmentId, this.mUnderflow);
        return nativeStopDvr();
    }

    public int flush() {
        return nativeFlushDvr();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        int iNativeClose = nativeClose();
        if (iNativeClose != 0) {
            TunerUtils.throwExceptionForResult(iNativeClose, "failed to close DVR playback");
        }
    }

    public void setFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        nativeSetFileDescriptor(parcelFileDescriptor.getFd());
    }

    public long read(long j) {
        return nativeRead(j);
    }

    public long read(byte[] bArr, long j, long j2) {
        if (j2 + j > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("Array length=" + bArr.length + ", offset=" + j + ", size=" + j2);
        }
        return nativeRead(bArr, j, j2);
    }
}
