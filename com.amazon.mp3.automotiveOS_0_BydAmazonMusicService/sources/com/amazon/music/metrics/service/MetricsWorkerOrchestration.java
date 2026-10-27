package com.amazon.music.metrics.service;

import com.amazon.music.metrics.event.Event;
import com.amazon.music.metrics.event.EventHandler;
import com.amazon.music.metrics.service.worker.EventHandlerWorker;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsWorkerOrchestration implements EventHandlerWorker.EventHandlerWorkerListener {
    private static final String TAG = "MetricsWorkerOrchestration";
    private AtomicInteger mActiveWorkerCount = new AtomicInteger(0);
    private MetricsWorkerConductor mConductor;

    public MetricsWorkerOrchestration(MetricsWorkerConductor metricsWorkerConductor) {
        this.mConductor = metricsWorkerConductor;
    }

    public void handleEvent(Event event, EventHandler eventHandler) {
        createAndPostHandlerWorker(event, eventHandler);
    }

    private void createAndPostHandlerWorker(Event event, EventHandler eventHandler) {
        this.mConductor.execute(new EventHandlerWorker(event, eventHandler, this));
    }

    @Override // com.amazon.music.metrics.service.worker.EventHandlerWorker.EventHandlerWorkerListener
    public void onEventHandlingComplete(EventHandler eventHandler) {
        decrementCountAndCheck();
    }

    @Override // com.amazon.music.metrics.service.worker.EventHandlerWorker.EventHandlerWorkerListener
    public void onEventHandlingFailed(EventHandler eventHandler, Exception exc) {
        decrementCountAndCheck();
        LoggerFactory.getLogger(TAG).error("Event Handling failed", (Throwable) exc);
    }

    private void decrementCountAndCheck() {
        if (this.mActiveWorkerCount.decrementAndGet() == 0) {
            this.mConductor.onAllWorkersComplete();
        }
    }
}
