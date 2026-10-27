package android.media.tv.tuner.dvr;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.media.tv.tuner.TunerUtils;
import android.media.tv.tuner.filter.Filter;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.android.internal.util.FrameworkStatsLog;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class DvrRecorder implements AutoCloseable {
    private static final String TAG = "TvTunerRecord";
    private static int sInstantId;
    private Executor mExecutor;
    private OnRecordStatusChangedListener mListener;
    private long mNativeContext;
    private int mOverflow;
    private int mSegmentId;
    private int mUserId = ActivityManager.getCurrentUser();

    private native int nativeAttachFilter(Filter filter);

    private native int nativeClose();

    private native int nativeConfigureDvr(DvrSettings dvrSettings);

    private native int nativeDetachFilter(Filter filter);

    private native int nativeFlushDvr();

    private native void nativeSetFileDescriptor(int i);

    private native int nativeStartDvr();

    private native int nativeStopDvr();

    private native long nativeWrite(long j);

    private native long nativeWrite(byte[] bArr, long j, long j2);

    private DvrRecorder() {
        this.mSegmentId = 0;
        int i = sInstantId;
        this.mSegmentId = (65535 & i) << 16;
        sInstantId = i + 1;
    }

    public void setListener(Executor executor, OnRecordStatusChangedListener onRecordStatusChangedListener) {
        this.mExecutor = executor;
        this.mListener = onRecordStatusChangedListener;
    }

    private void onRecordStatusChanged(final int i) {
        if (i == 8) {
            this.mOverflow++;
        }
        Executor executor = this.mExecutor;
        if (executor == null || this.mListener == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.dvr.-$$Lambda$DvrRecorder$IS-TlOpzEoQ86ajK23rt44rxkh0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onRecordStatusChanged$0$DvrRecorder(i);
            }
        });
    }

    public /* synthetic */ void lambda$onRecordStatusChanged$0$DvrRecorder(int i) {
        this.mListener.onRecordStatusChanged(i);
    }

    public int attachFilter(Filter filter) {
        return nativeAttachFilter(filter);
    }

    public int detachFilter(Filter filter) {
        return nativeDetachFilter(filter);
    }

    public int configure(DvrSettings dvrSettings) {
        return nativeConfigureDvr(dvrSettings);
    }

    public int start() {
        int i = this.mSegmentId;
        this.mSegmentId = (((i & 65535) + 1) & 65535) | ((-65536) & i);
        this.mOverflow = 0;
        Log.d(TAG, "Write Stats Log for Record.");
        FrameworkStatsLog.write(279, this.mUserId, 2, 1, this.mSegmentId, 0);
        return nativeStartDvr();
    }

    public int stop() {
        Log.d(TAG, "Write Stats Log for Playback.");
        FrameworkStatsLog.write(279, this.mUserId, 2, 2, this.mSegmentId, this.mOverflow);
        return nativeStopDvr();
    }

    public int flush() {
        return nativeFlushDvr();
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        int iNativeClose = nativeClose();
        if (iNativeClose != 0) {
            TunerUtils.throwExceptionForResult(iNativeClose, "failed to close DVR recorder");
        }
    }

    public void setFileDescriptor(ParcelFileDescriptor parcelFileDescriptor) {
        nativeSetFileDescriptor(parcelFileDescriptor.getFd());
    }

    public long write(long j) {
        return nativeWrite(j);
    }

    public long write(byte[] bArr, long j, long j2) {
        if (j2 + j > bArr.length) {
            throw new ArrayIndexOutOfBoundsException("Array length=" + bArr.length + ", offset=" + j + ", size=" + j2);
        }
        return nativeWrite(bArr, j, j2);
    }
}
