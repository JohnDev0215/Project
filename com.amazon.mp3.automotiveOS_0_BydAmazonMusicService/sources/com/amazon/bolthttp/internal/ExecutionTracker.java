package com.amazon.bolthttp.internal;

import android.os.SystemClock;
import com.amazon.bolthttp.EventListener;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.Response;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class ExecutionTracker {
    private final long mStartTime = SystemClock.elapsedRealtime();

    @Nonnull
    public static ExecutionTracker start() {
        return new ExecutionTracker();
    }

    private ExecutionTracker() {
    }

    public void notifyOnCompletion(@Nonnull Response<?> response) {
        Request<?> request = ((Response) Preconditions.checkNotNull(response, "response")).getRequest();
        List<EventListener> eventListeners = request.getEventListeners();
        if (eventListeners.isEmpty()) {
            return;
        }
        EventListener.ExecutionCompleteEvent executionCompleteEvent = new EventListener.ExecutionCompleteEvent(response.hasException() ? EventListener.ExecutionCompleteEvent.State.FAILURE : EventListener.ExecutionCompleteEvent.State.SUCCESS, SystemClock.elapsedRealtime() - this.mStartTime, response.getException(), response.getDownloadStatistics());
        Iterator<EventListener> it = eventListeners.iterator();
        while (it.hasNext()) {
            it.next().onExecutionCompleteEvent(executionCompleteEvent, request);
        }
    }
}
