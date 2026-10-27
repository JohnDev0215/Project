package com.amazon.music.media.playback.sequencer.impl;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.sequencer.IndexedSequencerBase;
import com.amazon.music.media.playback.sequencer.impl.SequencerDataRequestHandler;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class AsyncSequencer extends IndexedSequencerBase {
    private static final Logger logger = LoggerFactory.getLogger("AsyncSequencer");
    private final SequencerDataRequestHandler.StateRequest activeRequest;
    private final Object lock;
    private final List<Integer> otherOrder;
    private final Executor requestExecutor;
    private SequencerDataRequestHandler requestHandler;
    private boolean requestPending;
    private final Runnable requestRunnable;
    private Thread requestThread;
    private final Runnable resultRunnable;
    private SequencerDataRequestHandler.SequenceData sequenceData;
    private final SequencerDataRequestHandler.StateRequest stateRequest;

    protected AsyncSequencer(boolean z) {
        super(z);
        this.lock = new Object();
        this.stateRequest = new SequencerDataRequestHandler.StateRequest();
        this.activeRequest = new SequencerDataRequestHandler.StateRequest();
        this.otherOrder = new ArrayList();
        this.requestExecutor = ThreadUtils.createBackgroundExecutor(getClass().getSimpleName() + "-Request");
        this.requestRunnable = new Runnable() { // from class: com.amazon.music.media.playback.sequencer.impl.AsyncSequencer.1
            @Override // java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                    } catch (Exception e) {
                        AsyncSequencer.logger.error("Exception handling request", (Throwable) e);
                        StrictMode.crashIfStrict(e);
                        return;
                    }
                    synchronized (AsyncSequencer.this.lock) {
                        if (AsyncSequencer.this.stateRequest.suspend) {
                            AsyncSequencer.this.activeRequest.suspend = true;
                            AsyncSequencer.this.lock.notifyAll();
                            return;
                        }
                        AsyncSequencer.this.prepareActiveStateRequest();
                        AsyncSequencer.this.requestThread = Thread.currentThread();
                        try {
                            AsyncSequencer asyncSequencer = AsyncSequencer.this;
                            asyncSequencer.sequenceData = asyncSequencer.requestHandler.handleRequest(AsyncSequencer.this.activeRequest);
                            AsyncSequencer.this.requestThread = null;
                            synchronized (AsyncSequencer.this.lock) {
                                if (!AsyncSequencer.this.shouldAbandonResult()) {
                                    ThreadUtils.postOnMainThread(AsyncSequencer.this.resultRunnable);
                                    return;
                                }
                            }
                        } catch (Throwable th) {
                            AsyncSequencer.this.requestThread = null;
                            throw th;
                        }
                        AsyncSequencer.logger.error("Exception handling request", (Throwable) e);
                        StrictMode.crashIfStrict(e);
                        return;
                    }
                }
            }
        };
        this.resultRunnable = new Runnable() { // from class: com.amazon.music.media.playback.sequencer.impl.AsyncSequencer.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (AsyncSequencer.this.lock) {
                    AsyncSequencer.this.requestPending = false;
                    if (AsyncSequencer.this.shouldAbandonResult()) {
                        AsyncSequencer.this.request();
                        return;
                    }
                    try {
                        IndexedSequencerBase.SequencerInfo sequencerInfoProcessResult = AsyncSequencer.this.processResult();
                        AsyncSequencer.this.updateCanLoadMore(sequencerInfoProcessResult);
                        AsyncSequencer.this.setSequencerInfo(sequencerInfoProcessResult);
                        if (AsyncSequencer.this.sequenceData.error != null) {
                            AsyncSequencer asyncSequencer = AsyncSequencer.this;
                            asyncSequencer.notifyErrorFetchingMediaItems(asyncSequencer.sequenceData.error);
                        }
                    } catch (Exception e) {
                        AsyncSequencer.logger.error("Exception processing result", (Throwable) e);
                        StrictMode.crashIfStrict(e);
                    }
                    if (AsyncSequencer.this.hasMoreToProcess()) {
                        AsyncSequencer.this.request();
                    }
                    AsyncSequencer.this.sequenceData = null;
                }
            }
        };
        ((IndexedSequencerBase.SequencerInfo) this.info).setCanShuffle(true);
    }

    public void setSequencerDataRequestHandler(SequencerDataRequestHandler sequencerDataRequestHandler) {
        this.requestHandler = sequencerDataRequestHandler;
        request();
    }

    protected boolean shouldAbandonResult() {
        SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
        if (stateRequest.invalidate || stateRequest.unshuffledIndex == -2 || stateRequest.shuffledIndex == -2) {
            return true;
        }
        boolean z = stateRequest.shuffled;
        if (z == this.activeRequest.shuffled) {
            return false;
        }
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        return sequenceData == null || z != sequenceData.shuffled;
    }

    protected boolean hasUrgentRequest() {
        SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
        if (stateRequest.suspend) {
            return true;
        }
        if (stateRequest.fetchingCurrent && !this.activeRequest.fetchingCurrent) {
            return true;
        }
        int i = stateRequest.unshuffledIndex;
        if (i >= 0 && i != this.activeRequest.unshuffledIndex) {
            return true;
        }
        int i2 = stateRequest.shuffledIndex;
        if (i2 < 0 || i2 == this.activeRequest.shuffledIndex) {
            return shouldAbandonResult();
        }
        return true;
    }

    protected boolean hasMoreToProcess() {
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        if (sequenceData.partialResult) {
            return true;
        }
        SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
        if (stateRequest.suspend && !this.activeRequest.suspend) {
            return true;
        }
        int i = stateRequest.fetchingUpcomingCount;
        SequencerDataRequestHandler.StateRequest stateRequest2 = this.activeRequest;
        if (i > stateRequest2.fetchingUpcomingCount && i > sequenceData.fetchingUpcomingCount) {
            return true;
        }
        int i2 = stateRequest.fetchingPreviousCount;
        if (i2 > stateRequest2.fetchingPreviousCount && i2 > sequenceData.fetchingPreviousCount) {
            return true;
        }
        if (stateRequest.fetchingCurrent && !stateRequest2.fetchingCurrent && !sequenceData.fetchingCurrent) {
            return true;
        }
        int i3 = stateRequest.shuffledIndex;
        if (i3 >= 0 && i3 != sequenceData.shuffledIndex) {
            return true;
        }
        int i4 = stateRequest.unshuffledIndex;
        return i4 >= 0 && i4 != sequenceData.unshuffledIndex;
    }

    @Override // com.amazon.music.media.playback.sequencer.Sequencer
    public int getUnshuffledIndex(int i) {
        int i2;
        synchronized (this.lock) {
            SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
            if (i == stateRequest.shuffledIndex && (i2 = stateRequest.unshuffledIndex) >= 0) {
                return i2;
            }
            if (isShuffled()) {
                return getOtherOrderIndex(i);
            }
            return getCurrentOrderIndex(i);
        }
    }

    public int getShuffledIndex(int i) {
        int i2;
        synchronized (this.lock) {
            SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
            if (i == stateRequest.unshuffledIndex && (i2 = stateRequest.shuffledIndex) >= 0) {
                return i2;
            }
            if (isShuffled()) {
                return getCurrentOrderIndex(i);
            }
            return getOtherOrderIndex(i);
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.Sequencer
    public void setCurrentIndex(int i, ChangeReason changeReason) {
        synchronized (this.lock) {
            if (i < 0) {
                StrictMode.crashIfStrict("Illegal index: " + i);
                return;
            }
            if ((((IndexedSequencerBase.SequencerInfo) this.info).getMaximumPosition() < 0 || i <= ((IndexedSequencerBase.SequencerInfo) this.info).getMaximumPosition()) && ((IndexedSequencerBase.SequencerInfo) this.info).getCurrentPosition() != i) {
                ((IndexedSequencerBase.SequencerInfo) this.info).setCurrentPosition(i);
                int otherOrderIndex = getOtherOrderIndex(i);
                if (otherOrderIndex < 0) {
                    otherOrderIndex = -1;
                }
                SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
                if (stateRequest.shuffled) {
                    stateRequest.shuffledIndex = i;
                    stateRequest.unshuffledIndex = otherOrderIndex;
                } else {
                    stateRequest.unshuffledIndex = i;
                    stateRequest.shuffledIndex = otherOrderIndex;
                }
                stateRequest.currentItem = null;
                updateCanLoadMore((IndexedSequencerBase.SequencerInfo) this.info);
                notifySequencerInfoChanged();
            }
        }
    }

    private int getOtherOrderIndex(int i) {
        Integer num;
        int queueStartPosition = ((IndexedSequencerBase.SequencerInfo) this.info).getQueueStartPosition();
        if (i < queueStartPosition || i >= this.otherOrder.size() + queueStartPosition || (num = this.otherOrder.get(i - queueStartPosition)) == null || num.intValue() < 0) {
            return -1;
        }
        return num.intValue();
    }

    private int getCurrentOrderIndex(int i) {
        int size = this.otherOrder.size();
        for (int i2 = 0; i2 < size; i2++) {
            Integer num = this.otherOrder.get(i2);
            if (num != null && num.intValue() == i) {
                return ((IndexedSequencerBase.SequencerInfo) this.info).getQueueStartPosition() + i2;
            }
        }
        return -1;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer
    protected void onShuffledChanged(boolean z) {
        synchronized (this.lock) {
            if (!z) {
                this.stateRequest.currentItem = null;
            } else {
                SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
                if (stateRequest.currentItem == null) {
                    stateRequest.currentItem = getCurrentMediaItem();
                }
            }
            this.stateRequest.shuffled = isShuffled();
            this.stateRequest.invalidate = true;
            ((IndexedSequencerBase.SequencerInfo) this.info).getQueue().clear();
            this.otherOrder.clear();
            SequencerDataRequestHandler.StateRequest stateRequest2 = this.stateRequest;
            if (stateRequest2.shuffled) {
                stateRequest2.shuffledIndex = 0;
                if (!z) {
                    stateRequest2.unshuffledIndex = -2;
                } else if (stateRequest2.currentItem != null || stateRequest2.unshuffledIndex >= 0) {
                    ((IndexedSequencerBase.SequencerInfo) this.info).getQueue().add(this.stateRequest.currentItem);
                    ((IndexedSequencerBase.SequencerInfo) this.info).setQueueStartPosition(0);
                    this.otherOrder.add(Integer.valueOf(this.stateRequest.unshuffledIndex));
                }
            } else {
                if (!z) {
                    stateRequest2.shuffledIndex = -1;
                    stateRequest2.unshuffledIndex = 0;
                } else if (stateRequest2.unshuffledIndex < 0) {
                    stateRequest2.unshuffledIndex = -1;
                }
                if (stateRequest2.unshuffledIndex >= 0 && (stateRequest2.currentItem != null || stateRequest2.shuffledIndex >= 0)) {
                    ((IndexedSequencerBase.SequencerInfo) this.info).getQueue().add(this.stateRequest.currentItem);
                    ((IndexedSequencerBase.SequencerInfo) this.info).setQueueStartPosition(this.stateRequest.unshuffledIndex);
                    this.otherOrder.add(Integer.valueOf(this.stateRequest.shuffledIndex));
                }
            }
        }
        SequencerDataRequestHandler.StateRequest stateRequest3 = this.stateRequest;
        int i = stateRequest3.shuffled ? stateRequest3.shuffledIndex : stateRequest3.unshuffledIndex;
        if (i >= 0) {
            ((IndexedSequencerBase.SequencerInfo) this.info).setCurrentPosition(i);
        }
        updateCanLoadMore((IndexedSequencerBase.SequencerInfo) this.info);
        request();
        super.onShuffledChanged(z);
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingUpcomingMediaItems() {
        boolean z;
        synchronized (this.lock) {
            z = this.stateRequest.fetchingUpcomingCount > 0;
        }
        return z;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingPreviousMediaItems() {
        boolean z;
        synchronized (this.lock) {
            z = this.stateRequest.fetchingPreviousCount > 0;
        }
        return z;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean isFetchingCurrentMediaItem() {
        boolean z;
        synchronized (this.lock) {
            z = this.stateRequest.fetchingCurrent;
        }
        return z;
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchUpcomingMediaItems(int i) {
        synchronized (this.lock) {
            SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
            if (stateRequest.fetchingUpcomingCount < i) {
                stateRequest.fetchingUpcomingCount = i;
                request();
            }
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchPreviousMediaItems(int i) {
        synchronized (this.lock) {
            SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
            if (stateRequest.fetchingPreviousCount < i) {
                stateRequest.fetchingPreviousCount = i;
                request();
            }
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public void fetchCurrentMediaItem() {
        synchronized (this.lock) {
            if (!this.stateRequest.fetchingCurrent && getCurrentMediaItem() == null) {
                this.stateRequest.fetchingCurrent = true;
                request();
            }
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.Sequencer
    public MediaItem getCurrentMediaItem() {
        MediaItem mediaItem = this.stateRequest.currentItem;
        return mediaItem != null ? mediaItem : super.getCurrentMediaItem();
    }

    @MainThread
    public void invalidate(int i, int i2, boolean z, MediaItem mediaItem) {
        logger.debug("invalidate(" + i + ", " + i2 + ", " + z + ", " + mediaItem + ")");
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        sequencerInfoCopySequencerInfo.setMaximumPosition(-1);
        sequencerInfoCopySequencerInfo.setShuffled(z);
        int i3 = z ? i2 : i;
        if (i3 >= 0) {
            sequencerInfoCopySequencerInfo.setCurrentPosition(i3);
        }
        sequencerInfoCopySequencerInfo.setCanLoadPrevious(sequencerInfoCopySequencerInfo.getCurrentPosition() > 0);
        sequencerInfoCopySequencerInfo.setCanLoadMore(true);
        sequencerInfoCopySequencerInfo.getQueue().clear();
        this.otherOrder.clear();
        if (mediaItem != null) {
            sequencerInfoCopySequencerInfo.getQueue().add(mediaItem);
            sequencerInfoCopySequencerInfo.setQueueStartPosition(sequencerInfoCopySequencerInfo.getCurrentPosition());
            this.otherOrder.add(Integer.valueOf(Math.max(-1, z ? i : i2)));
        } else {
            sequencerInfoCopySequencerInfo.setQueueStartPosition(0);
        }
        setSequencerInfo(sequencerInfoCopySequencerInfo);
        synchronized (this.lock) {
            SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
            stateRequest.invalidate = true;
            stateRequest.unshuffledIndex = i;
            stateRequest.shuffledIndex = i2;
            stateRequest.shuffled = z;
            stateRequest.currentItem = mediaItem;
            stateRequest.fetchingCurrent = false;
            stateRequest.fetchingPreviousCount = 0;
            stateRequest.fetchingUpcomingCount = 0;
            request();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void request() {
        synchronized (this.lock) {
            if (this.requestPending) {
                return;
            }
            this.requestPending = true;
            this.requestExecutor.execute(this.requestRunnable);
        }
    }

    @WorkerThread
    protected void suspend() {
        if (ThreadUtils.isOnMainThread()) {
            StrictMode.crashIfStrict("Cannot call suspend() from the main thread!");
            throw new IllegalThreadStateException("Cannot call suspend() from the main thread!");
        }
        synchronized (this.lock) {
            if (this.activeRequest.suspend) {
                StrictMode.crashIfStrict("Already suspended!");
                return;
            }
            if (Thread.currentThread() == this.requestThread) {
                StrictMode.crashIfStrict("Cannot call suspend() from within handleRequest()");
                throw new IllegalThreadStateException("Cannot call suspend() from within handleRequest()");
            }
            this.stateRequest.suspend = true;
            request();
            while (!this.activeRequest.suspend) {
                try {
                    this.lock.wait();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            ThreadUtils.callOnMainThread(new Runnable() { // from class: com.amazon.music.media.playback.sequencer.impl.AsyncSequencer.3
                @Override // java.lang.Runnable
                public void run() {
                    AsyncSequencer.this.prepareActiveStateRequest();
                    AsyncSequencer asyncSequencer = AsyncSequencer.this;
                    asyncSequencer.sequenceData = asyncSequencer.requestHandler.onSuspend(AsyncSequencer.this.activeRequest);
                    synchronized (AsyncSequencer.this.lock) {
                        IndexedSequencerBase.SequencerInfo sequencerInfoProcessResult = AsyncSequencer.this.processResult();
                        AsyncSequencer.this.updateCanLoadMore(sequencerInfoProcessResult);
                        AsyncSequencer.this.setSequencerInfo(sequencerInfoProcessResult);
                        if (AsyncSequencer.this.sequenceData.error != null) {
                            AsyncSequencer asyncSequencer2 = AsyncSequencer.this;
                            asyncSequencer2.notifyErrorFetchingMediaItems(asyncSequencer2.sequenceData.error);
                        }
                        AsyncSequencer.this.sequenceData = null;
                    }
                }
            });
        }
    }

    @CallSuper
    protected void prepareActiveStateRequest() {
        this.activeRequest.copyFrom(this.stateRequest);
        SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
        if (stateRequest.unshuffledIndex == -2) {
            stateRequest.unshuffledIndex = -1;
        }
        if (stateRequest.shuffledIndex == -2) {
            stateRequest.shuffledIndex = -1;
        }
        stateRequest.fetchingCurrent = false;
        stateRequest.fetchingUpcomingCount = 0;
        stateRequest.fetchingPreviousCount = 0;
        stateRequest.invalidate = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resume() {
        synchronized (this.lock) {
            this.stateRequest.suspend = false;
            SequencerDataRequestHandler.StateRequest stateRequest = this.activeRequest;
            if (!stateRequest.suspend) {
                StrictMode.crashIfStrict("Not suspended!");
                return;
            }
            stateRequest.suspend = false;
            this.requestPending = false;
            request();
        }
    }

    @Override // com.amazon.music.media.playback.sequencer.IndexedSequencerBase, com.amazon.music.media.playback.sequencer.AbstractSequencer, com.amazon.music.media.playback.sequencer.Sequencer
    public boolean canFetchPreviousMediaItems() {
        return ((IndexedSequencerBase.SequencerInfo) this.info).getCurrentPosition() > 0 && ((IndexedSequencerBase.SequencerInfo) this.info).canLoadPrevious();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCanLoadMore(IndexedSequencerBase.SequencerInfo sequencerInfo) {
        int currentPosition = sequencerInfo.getCurrentPosition();
        int maximumPosition = sequencerInfo.getMaximumPosition();
        int queueStartPosition = sequencerInfo.getQueueStartPosition();
        List<MediaItem> queue = sequencerInfo.getQueue();
        boolean z = true;
        boolean z2 = maximumPosition < 0 || getQueueEnd() < maximumPosition;
        if (!z2 && currentPosition >= 0) {
            if (currentPosition < queueStartPosition) {
                z2 = true;
                break;
            }
            int size = queue.size();
            for (int i = currentPosition - queueStartPosition; i < size; i++) {
                if (queue.get(i) == null) {
                    z2 = true;
                    break;
                }
            }
        }
        sequencerInfo.setCanLoadMore(z2);
        boolean z3 = queueStartPosition > 0 || queue.isEmpty() || currentPosition > getQueueEnd();
        if (z3) {
            z = z3;
            break;
        }
        do {
            currentPosition--;
            if (currentPosition < 0) {
                z = z3;
                break;
            }
        } while (queue.get(currentPosition) != null);
        sequencerInfo.setCanLoadPrevious(z);
    }

    @CallSuper
    @MainThread
    protected IndexedSequencerBase.SequencerInfo processResult() {
        int i;
        if (StrictMode.isStrictMode()) {
            this.sequenceData.validate();
        }
        IndexedSequencerBase.SequencerInfo sequencerInfoCopySequencerInfo = copySequencerInfo();
        List<MediaItem> queue = sequencerInfoCopySequencerInfo.getQueue();
        queue.clear();
        queue.addAll(this.sequenceData.items);
        this.otherOrder.clear();
        this.otherOrder.addAll(this.sequenceData.otherOrder);
        SequencerDataRequestHandler.StateRequest stateRequest = this.stateRequest;
        if (stateRequest.shuffled) {
            int i2 = stateRequest.shuffledIndex;
        } else {
            int i3 = stateRequest.unshuffledIndex;
        }
        SequencerDataRequestHandler.StateRequest stateRequest2 = this.activeRequest;
        if (stateRequest2.shuffled) {
            int i4 = stateRequest2.shuffledIndex;
        } else {
            int i5 = stateRequest2.unshuffledIndex;
        }
        SequencerDataRequestHandler.SequenceData sequenceData = this.sequenceData;
        int i6 = sequenceData.shuffled ? sequenceData.shuffledIndex : sequenceData.unshuffledIndex;
        sequencerInfoCopySequencerInfo.setQueueStartPosition(sequenceData.startIndex);
        sequencerInfoCopySequencerInfo.setMaximumPosition(this.sequenceData.maxItems);
        sequencerInfoCopySequencerInfo.setShuffled(this.sequenceData.shuffled);
        sequencerInfoCopySequencerInfo.setMediaCollectionInfo(this.sequenceData.mediaCollectionInfo);
        SequencerDataRequestHandler.StateRequest stateRequest3 = this.stateRequest;
        int i7 = stateRequest3.shuffledIndex;
        boolean z = true;
        if ((i7 < 0 || i7 == this.activeRequest.shuffledIndex || i7 == this.sequenceData.shuffledIndex) && ((i = stateRequest3.unshuffledIndex) < 0 || i == this.activeRequest.unshuffledIndex || i == this.sequenceData.unshuffledIndex)) {
            z = false;
        }
        if (z) {
            stateRequest3.fetchingCurrent |= this.sequenceData.fetchingCurrent;
            int i8 = stateRequest3.fetchingUpcomingCount;
            SequencerDataRequestHandler.StateRequest stateRequest4 = this.activeRequest;
            int i9 = stateRequest4.fetchingUpcomingCount;
            if (i8 < i9) {
                stateRequest3.fetchingUpcomingCount = i9;
            }
            int i10 = stateRequest3.fetchingPreviousCount;
            int i11 = stateRequest4.fetchingPreviousCount;
            if (i10 < i11) {
                stateRequest3.fetchingPreviousCount = i11;
            }
        } else {
            if (i6 >= 0) {
                sequencerInfoCopySequencerInfo.setCurrentPosition(i6);
            }
            SequencerDataRequestHandler.StateRequest stateRequest5 = this.stateRequest;
            SequencerDataRequestHandler.SequenceData sequenceData2 = this.sequenceData;
            stateRequest5.shuffledIndex = sequenceData2.shuffledIndex;
            stateRequest5.unshuffledIndex = sequenceData2.unshuffledIndex;
            boolean z2 = sequenceData2.fetchingCurrent;
            if (z2 || this.activeRequest.fetchingCurrent) {
                stateRequest5.fetchingCurrent = z2;
            }
            this.stateRequest.fetchingUpcomingCount = Math.min(this.activeRequest.fetchingUpcomingCount, this.sequenceData.fetchingUpcomingCount) + Math.max(0, stateRequest5.fetchingUpcomingCount - this.activeRequest.fetchingUpcomingCount);
            this.stateRequest.fetchingPreviousCount = Math.min(this.activeRequest.fetchingPreviousCount, this.sequenceData.fetchingPreviousCount) + Math.max(0, this.stateRequest.fetchingPreviousCount - this.activeRequest.fetchingPreviousCount);
        }
        return sequencerInfoCopySequencerInfo;
    }
}
