package android.media.tv.tuner.filter;

import android.annotation.SystemApi;
import android.media.tv.tuner.TunerUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public class Filter implements AutoCloseable {
    public static final int STATUS_DATA_READY = 1;
    public static final int STATUS_HIGH_WATER = 4;
    public static final int STATUS_LOW_WATER = 2;
    public static final int STATUS_OVERFLOW = 8;
    public static final int SUBTYPE_AUDIO = 3;
    public static final int SUBTYPE_DOWNLOAD = 5;
    public static final int SUBTYPE_IP = 13;
    public static final int SUBTYPE_IP_PAYLOAD = 12;
    public static final int SUBTYPE_MMTP = 10;
    public static final int SUBTYPE_NTP = 11;
    public static final int SUBTYPE_PAYLOAD_THROUGH = 14;
    public static final int SUBTYPE_PCR = 8;
    public static final int SUBTYPE_PES = 2;
    public static final int SUBTYPE_PTP = 16;
    public static final int SUBTYPE_RECORD = 6;
    public static final int SUBTYPE_SECTION = 1;
    public static final int SUBTYPE_TEMI = 9;
    public static final int SUBTYPE_TLV = 15;
    public static final int SUBTYPE_TS = 7;
    public static final int SUBTYPE_UNDEFINED = 0;
    public static final int SUBTYPE_VIDEO = 4;
    private static final String TAG = "Filter";
    public static final int TYPE_ALP = 16;
    public static final int TYPE_IP = 4;
    public static final int TYPE_MMTP = 2;
    public static final int TYPE_TLV = 8;
    public static final int TYPE_TS = 1;
    public static final int TYPE_UNDEFINED = 0;
    private FilterCallback mCallback;
    private Executor mExecutor;
    private final int mId;
    private boolean mIsClosed = false;
    private final Object mLock = new Object();
    private int mMainType;
    private long mNativeContext;
    private Filter mSource;
    private boolean mStarted;
    private int mSubtype;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Subtype {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private native int nativeClose();

    private native int nativeConfigureFilter(int i, int i2, FilterConfiguration filterConfiguration);

    private native int nativeFlushFilter();

    private native int nativeGetId();

    private native int nativeRead(byte[] bArr, long j, long j2);

    private native int nativeSetDataSource(Filter filter);

    private native int nativeStartFilter();

    private native int nativeStopFilter();

    private Filter(int i) {
        this.mId = i;
    }

    private void onFilterStatus(final int i) {
        Executor executor;
        if (this.mCallback == null || (executor = this.mExecutor) == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.filter.-$$Lambda$Filter$mWQUgrz0gGC01ACF2C6D7jy0Syw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFilterStatus$0$Filter(i);
            }
        });
    }

    public /* synthetic */ void lambda$onFilterStatus$0$Filter(int i) {
        this.mCallback.onFilterStatusChanged(this, i);
    }

    private void onFilterEvent(final FilterEvent[] filterEventArr) {
        Executor executor;
        if (this.mCallback == null || (executor = this.mExecutor) == null) {
            return;
        }
        executor.execute(new Runnable() { // from class: android.media.tv.tuner.filter.-$$Lambda$Filter$1gQuuqAtdixAKwbDRNkBbr8FyR8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFilterEvent$1$Filter(filterEventArr);
            }
        });
    }

    public /* synthetic */ void lambda$onFilterEvent$1$Filter(FilterEvent[] filterEventArr) {
        this.mCallback.onFilterEvent(this, filterEventArr);
    }

    public void setType(int i, int i2) {
        this.mMainType = i;
        this.mSubtype = TunerUtils.getFilterSubtype(i, i2);
    }

    public void setCallback(FilterCallback filterCallback, Executor executor) {
        this.mCallback = filterCallback;
        this.mExecutor = executor;
    }

    public FilterCallback getCallback() {
        return this.mCallback;
    }

    public int configure(FilterConfiguration filterConfiguration) {
        int iNativeConfigureFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            Settings settings = filterConfiguration.getSettings();
            int type = settings == null ? this.mSubtype : settings.getType();
            if (this.mMainType != filterConfiguration.getType() || this.mSubtype != type) {
                throw new IllegalArgumentException("Invalid filter config. filter main type=" + this.mMainType + ", filter subtype=" + this.mSubtype + ". config main type=" + filterConfiguration.getType() + ", config subtype=" + type);
            }
            iNativeConfigureFilter = nativeConfigureFilter(filterConfiguration.getType(), type, filterConfiguration);
        }
        return iNativeConfigureFilter;
    }

    public int getId() {
        int iNativeGetId;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            iNativeGetId = nativeGetId();
        }
        return iNativeGetId;
    }

    public int setDataSource(Filter filter) {
        int iNativeSetDataSource;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            if (this.mSource != null) {
                throw new IllegalStateException("Data source is existing");
            }
            iNativeSetDataSource = nativeSetDataSource(filter);
            if (iNativeSetDataSource == 0) {
                this.mSource = filter;
            }
        }
        return iNativeSetDataSource;
    }

    public int start() {
        int iNativeStartFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            iNativeStartFilter = nativeStartFilter();
        }
        return iNativeStartFilter;
    }

    public int stop() {
        int iNativeStopFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            iNativeStopFilter = nativeStopFilter();
        }
        return iNativeStopFilter;
    }

    public int flush() {
        int iNativeFlushFilter;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            iNativeFlushFilter = nativeFlushFilter();
        }
        return iNativeFlushFilter;
    }

    public int read(byte[] bArr, long j, long j2) {
        int iNativeRead;
        synchronized (this.mLock) {
            TunerUtils.checkResourceState(TAG, this.mIsClosed);
            iNativeRead = nativeRead(bArr, j, Math.min(j2, ((long) bArr.length) - j));
        }
        return iNativeRead;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.mLock) {
            if (this.mIsClosed) {
                return;
            }
            int iNativeClose = nativeClose();
            if (iNativeClose != 0) {
                TunerUtils.throwExceptionForResult(iNativeClose, "Failed to close filter.");
            } else {
                this.mIsClosed = true;
            }
        }
    }
}
