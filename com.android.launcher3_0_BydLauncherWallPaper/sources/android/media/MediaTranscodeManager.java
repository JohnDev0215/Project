package android.media;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes2.dex */
public final class MediaTranscodeManager {
    private static final int EVENT_JOB_FINISHED = 3;
    private static final int EVENT_JOB_PROGRESSED = 2;
    private static final int EVENT_JOB_STARTED = 1;
    private static final long ID_INVALID = -1;
    private static final String TAG = "MediaTranscodeManager";
    private static MediaTranscodeManager sMediaTranscodeManager;
    private final Context mContext;
    private final ConcurrentMap<Long, TranscodingJob> mPendingTranscodingJobs = new ConcurrentHashMap();

    @Retention(RetentionPolicy.SOURCE)
    public @interface Event {
    }

    @FunctionalInterface
    public interface OnTranscodingFinishedListener {
        void onTranscodingFinished(TranscodingJob transcodingJob);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public native void native_cancelTranscodingRequest(long j);

    private native boolean native_enqueueTranscodingRequest(long j, TranscodingRequest transcodingRequest, Context context);

    private static native void native_init();

    private native long native_requestUniqueJobID();

    public static final class TranscodingRequest {
        private MediaFormat mDstFormat;
        private Uri mDstUri;
        private Uri mSrcUri;

        private TranscodingRequest(Builder builder) {
            this.mSrcUri = builder.mSrcUri;
            this.mDstUri = builder.mDstUri;
            this.mDstFormat = builder.mDstFormat;
        }

        public static class Builder {
            private MediaFormat mDstFormat;
            private Uri mDstUri;
            private Uri mSrcUri;

            public Builder setSourceUri(Uri uri) {
                this.mSrcUri = uri;
                return this;
            }

            public Builder setDestinationUri(Uri uri) {
                this.mDstUri = uri;
                return this;
            }

            public Builder setDestinationFormat(MediaFormat mediaFormat) {
                this.mDstFormat = mediaFormat;
                return this;
            }

            public TranscodingRequest build() {
                return new TranscodingRequest(this);
            }
        }
    }

    public static final class TranscodingJob {
        public static final int RESULT_CANCELED = 4;
        public static final int RESULT_ERROR = 3;
        public static final int RESULT_NONE = 1;
        public static final int RESULT_SUCCESS = 2;
        public static final int STATUS_FINISHED = 3;
        public static final int STATUS_PENDING = 1;
        public static final int STATUS_RUNNING = 2;
        private final Executor mExecutor;
        private long mID;
        private final OnTranscodingFinishedListener mListener;
        private float mProgress;
        private Executor mProgressChangedExecutor;
        private OnProgressChangedListener mProgressChangedListener;
        private int mResult;
        private int mStatus;
        private final ReentrantLock mStatusChangeLock;

        @FunctionalInterface
        public interface OnProgressChangedListener {
            void onProgressChanged(float f);
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface Result {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface Status {
        }

        private TranscodingJob(long j, Executor executor, OnTranscodingFinishedListener onTranscodingFinishedListener) {
            this.mStatusChangeLock = new ReentrantLock();
            this.mProgress = 0.0f;
            this.mStatus = 1;
            this.mResult = 1;
            this.mID = j;
            this.mExecutor = executor;
            this.mListener = onTranscodingFinishedListener;
        }

        public void setOnProgressChangedListener(Executor executor, OnProgressChangedListener onProgressChangedListener) {
            this.mProgressChangedExecutor = executor;
            this.mProgressChangedListener = onProgressChangedListener;
        }

        public void cancel() {
            setJobFinished(4);
            MediaTranscodeManager.sMediaTranscodeManager.native_cancelTranscodingRequest(this.mID);
        }

        public float getProgress() {
            return this.mProgress;
        }

        public int getStatus() {
            return this.mStatus;
        }

        public int getResult() {
            return this.mResult;
        }

        private void setJobStarted() {
            this.mStatus = 2;
        }

        private void setJobProgress(float f) {
            this.mProgress = f;
            final OnProgressChangedListener onProgressChangedListener = this.mProgressChangedListener;
            if (onProgressChangedListener != null) {
                this.mProgressChangedExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaTranscodeManager$TranscodingJob$SVMXjkw4lhyRg1RzIU1R0W8DHCU
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$setJobProgress$0$MediaTranscodeManager$TranscodingJob(onProgressChangedListener);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$setJobProgress$0$MediaTranscodeManager$TranscodingJob(OnProgressChangedListener onProgressChangedListener) {
            onProgressChangedListener.onProgressChanged(this.mProgress);
        }

        private void setJobFinished(int i) {
            boolean z;
            try {
                this.mStatusChangeLock.lock();
                if (this.mStatus != 3) {
                    this.mStatus = 3;
                    this.mResult = i;
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    this.mExecutor.execute(new Runnable() { // from class: android.media.-$$Lambda$MediaTranscodeManager$TranscodingJob$_kVXik3gPOn8lnRgRSGdM1aR0CM
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$setJobFinished$1$MediaTranscodeManager$TranscodingJob();
                        }
                    });
                }
            } finally {
                this.mStatusChangeLock.unlock();
            }
        }

        public /* synthetic */ void lambda$setJobFinished$1$MediaTranscodeManager$TranscodingJob() {
            this.mListener.onTranscodingFinished(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processJobEvent(int i, int i2) {
            if (i == 1) {
                setJobStarted();
                return;
            }
            if (i == 2) {
                setJobProgress(i2 / 100.0f);
            } else if (i == 3) {
                setJobFinished(i2);
            } else {
                Log.e(MediaTranscodeManager.TAG, "Unsupported event: " + i);
            }
        }
    }

    private MediaTranscodeManager(Context context) {
        this.mContext = context;
    }

    private void postEventFromNative(int i, long j, int i2) {
        Log.d(TAG, String.format("postEventFromNative. Event %d, ID %d, arg %d", Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(i2)));
        TranscodingJob transcodingJob = this.mPendingTranscodingJobs.get(Long.valueOf(j));
        if (transcodingJob != null) {
            transcodingJob.processJobEvent(i, i2);
        } else {
            Log.e(TAG, "No matching transcode job found for id " + j);
        }
    }

    public static MediaTranscodeManager getInstance(Context context) {
        MediaTranscodeManager mediaTranscodeManager;
        Preconditions.checkNotNull(context);
        synchronized (MediaTranscodeManager.class) {
            if (sMediaTranscodeManager == null) {
                sMediaTranscodeManager = new MediaTranscodeManager(context.getApplicationContext());
            }
            mediaTranscodeManager = sMediaTranscodeManager;
        }
        return mediaTranscodeManager;
    }

    public TranscodingJob enqueueTranscodingRequest(TranscodingRequest transcodingRequest, Executor executor, OnTranscodingFinishedListener onTranscodingFinishedListener) {
        Log.i(TAG, "enqueueTranscodingRequest called.");
        Preconditions.checkNotNull(transcodingRequest);
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(onTranscodingFinishedListener);
        long jNative_requestUniqueJobID = native_requestUniqueJobID();
        if (jNative_requestUniqueJobID == -1) {
            return null;
        }
        TranscodingJob transcodingJob = new TranscodingJob(jNative_requestUniqueJobID, executor, onTranscodingFinishedListener);
        this.mPendingTranscodingJobs.put(Long.valueOf(jNative_requestUniqueJobID), transcodingJob);
        if (native_enqueueTranscodingRequest(jNative_requestUniqueJobID, transcodingRequest, this.mContext)) {
            return transcodingJob;
        }
        this.mPendingTranscodingJobs.remove(Long.valueOf(jNative_requestUniqueJobID));
        return null;
    }

    static {
        System.loadLibrary("media_jni");
        native_init();
    }
}
