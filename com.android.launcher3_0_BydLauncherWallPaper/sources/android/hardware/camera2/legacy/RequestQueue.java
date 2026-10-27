package android.hardware.camera2.legacy;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.utils.SubmitInfo;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RequestQueue {
    public static final long INVALID_FRAME = -1;
    private static final String TAG = "RequestQueue";
    private final List<Long> mJpegSurfaceIds;
    private BurstHolder mRepeatingRequest = null;
    private final ArrayDeque<BurstHolder> mRequestQueue = new ArrayDeque<>();
    private long mCurrentFrameNumber = 0;
    private long mCurrentRepeatingFrameNumber = -1;
    private int mCurrentRequestId = 0;

    public final class RequestQueueEntry {
        private final BurstHolder mBurstHolder;
        private final Long mFrameNumber;
        private final boolean mQueueEmpty;

        public BurstHolder getBurstHolder() {
            return this.mBurstHolder;
        }

        public Long getFrameNumber() {
            return this.mFrameNumber;
        }

        public boolean isQueueEmpty() {
            return this.mQueueEmpty;
        }

        public RequestQueueEntry(BurstHolder burstHolder, Long l, boolean z) {
            this.mBurstHolder = burstHolder;
            this.mFrameNumber = l;
            this.mQueueEmpty = z;
        }
    }

    public RequestQueue(List<Long> list) {
        this.mJpegSurfaceIds = list;
    }

    public synchronized RequestQueueEntry getNext() {
        BurstHolder burstHolder;
        BurstHolder burstHolderPoll = this.mRequestQueue.poll();
        boolean z = burstHolderPoll != null && this.mRequestQueue.size() == 0;
        if (burstHolderPoll == null && (burstHolder = this.mRepeatingRequest) != null) {
            this.mCurrentRepeatingFrameNumber = this.mCurrentFrameNumber + ((long) burstHolder.getNumberOfRequests());
            burstHolderPoll = burstHolder;
        }
        if (burstHolderPoll == null) {
            return null;
        }
        RequestQueueEntry requestQueueEntry = new RequestQueueEntry(burstHolderPoll, Long.valueOf(this.mCurrentFrameNumber), z);
        this.mCurrentFrameNumber += (long) burstHolderPoll.getNumberOfRequests();
        return requestQueueEntry;
    }

    public synchronized long stopRepeating(int i) {
        long j;
        BurstHolder burstHolder = this.mRepeatingRequest;
        j = -1;
        if (burstHolder != null && burstHolder.getRequestId() == i) {
            this.mRepeatingRequest = null;
            long j2 = this.mCurrentRepeatingFrameNumber;
            long j3 = j2 == -1 ? -1L : j2 - 1;
            this.mCurrentRepeatingFrameNumber = -1L;
            Log.i(TAG, "Repeating capture request cancelled.");
            j = j3;
        } else {
            Log.e(TAG, "cancel failed: no repeating request exists for request id: " + i);
        }
        return j;
    }

    public synchronized long stopRepeating() {
        BurstHolder burstHolder = this.mRepeatingRequest;
        if (burstHolder == null) {
            Log.e(TAG, "cancel failed: no repeating request exists.");
            return -1L;
        }
        return stopRepeating(burstHolder.getRequestId());
    }

    public synchronized SubmitInfo submit(CaptureRequest[] captureRequestArr, boolean z) {
        int i;
        long jCalculateLastFrame;
        i = this.mCurrentRequestId;
        this.mCurrentRequestId = i + 1;
        BurstHolder burstHolder = new BurstHolder(i, z, captureRequestArr, this.mJpegSurfaceIds);
        if (burstHolder.isRepeating()) {
            Log.i(TAG, "Repeating capture request set.");
            if (this.mRepeatingRequest != null) {
                long j = this.mCurrentRepeatingFrameNumber;
                if (j == -1) {
                    jCalculateLastFrame = -1;
                    this.mCurrentRepeatingFrameNumber = -1L;
                    this.mRepeatingRequest = burstHolder;
                } else {
                    jCalculateLastFrame = j - 1;
                    this.mCurrentRepeatingFrameNumber = -1L;
                    this.mRepeatingRequest = burstHolder;
                }
            } else {
                jCalculateLastFrame = -1;
                this.mCurrentRepeatingFrameNumber = -1L;
                this.mRepeatingRequest = burstHolder;
            }
        }
        this.mRequestQueue.offer(burstHolder);
        jCalculateLastFrame = calculateLastFrame(burstHolder.getRequestId());
        return new SubmitInfo(i, jCalculateLastFrame);
    }

    private long calculateLastFrame(int i) {
        long numberOfRequests = this.mCurrentFrameNumber;
        for (BurstHolder burstHolder : this.mRequestQueue) {
            numberOfRequests += (long) burstHolder.getNumberOfRequests();
            if (burstHolder.getRequestId() == i) {
                return numberOfRequests - 1;
            }
        }
        throw new IllegalStateException("At least one request must be in the queue to calculate frame number");
    }
}
