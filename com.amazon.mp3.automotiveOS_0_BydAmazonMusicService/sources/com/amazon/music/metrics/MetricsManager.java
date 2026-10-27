package com.amazon.music.metrics;

import com.amazon.music.metrics.event.Event;
import com.amazon.music.metrics.event.EventHandler;
import com.amazon.music.metrics.service.MetricsWorkerConductor;
import com.amazon.music.metrics.service.MetricsWorkerExecutor;
import com.amazon.music.metrics.service.MetricsWorkerOrchestration;
import com.amazon.music.metrics.tools.EventHandlerMap;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsManager {
    private EventHandlerMap mMap;
    private MetricsWorkerOrchestration mOrchestration;

    public MetricsManager() {
        this(new MetricsWorkerExecutor());
    }

    public MetricsManager(MetricsWorkerConductor metricsWorkerConductor) {
        this.mMap = new EventHandlerMap();
        this.mOrchestration = new MetricsWorkerOrchestration(metricsWorkerConductor);
    }

    public void handleEvent(Event event) {
        Iterator<EventHandler> it = getHandlers(event).iterator();
        while (it.hasNext()) {
            handleEvent(event, it.next());
        }
    }

    public void handleEvent(Event event, EventHandler eventHandler) {
        this.mOrchestration.handleEvent(event, eventHandler);
    }

    public void handleSignout() {
        Iterator<EventHandler> it = this.mMap.getAllHandlers().iterator();
        while (it.hasNext()) {
            it.next().handleSignout();
        }
    }

    public void registerEventHandler(EventHandler eventHandler) {
        getHandlerMap().register(eventHandler);
    }

    public void deregisterEventHandler(EventHandler eventHandler) {
        getHandlerMap().deregister(eventHandler);
    }

    private EventHandlerMap getHandlerMap() {
        return this.mMap;
    }

    private Set<EventHandler> getHandlers(Event event) {
        return getHandlerMap().getHandlers(event.getType());
    }
}
