package com.amazon.music.metrics.service.worker;

import com.amazon.music.metrics.event.Event;
import com.amazon.music.metrics.event.EventHandler;

/* JADX INFO: loaded from: classes4.dex */
public class EventHandlerWorker extends EventWorker {
    private static final String TAG = EventHandlerWorker.class.getSimpleName();
    private Event mEvent;
    private EventHandler mHandler;
    private EventHandlerWorkerListener mListener;

    public interface EventHandlerWorkerListener {
        void onEventHandlingComplete(EventHandler eventHandler);

        void onEventHandlingFailed(EventHandler eventHandler, Exception exc);
    }

    public EventHandlerWorker(Event event, EventHandler eventHandler, EventHandlerWorkerListener eventHandlerWorkerListener) {
        this.mEvent = event;
        this.mHandler = eventHandler;
        this.mListener = eventHandlerWorkerListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.mHandler.handleEvent(this.mEvent);
            this.mListener.onEventHandlingComplete(this.mHandler);
        } catch (Exception e) {
            this.mListener.onEventHandlingFailed(this.mHandler, e);
        }
    }
}
