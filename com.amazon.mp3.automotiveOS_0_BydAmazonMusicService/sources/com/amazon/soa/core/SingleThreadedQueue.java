package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.soa.core.MethodEntry;
import com.amazonaws.util.DateUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
class SingleThreadedQueue extends MethodQueue implements OnMethodExecutedListener {
    final SimpleDateFormat dateFormat;
    private final Logger logger;
    private final Queue<MethodEntry> methodQueue;
    private final MethodsRegistry methodsRegistry;
    private final String name;
    private final OwnerRegistry ownerRegistry;
    private final Object queueLock;

    SingleThreadedQueue(final String name, final OwnerRegistry ownerRegistry, final MethodsRegistry methodsRegistry) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
        this.dateFormat = simpleDateFormat;
        this.logger = LoggerFactory.getLogger("SingleThreadedQueue");
        this.name = name;
        this.queueLock = new Object();
        this.ownerRegistry = ownerRegistry;
        this.methodsRegistry = methodsRegistry;
        this.methodQueue = new ConcurrentLinkedQueue();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override // com.amazon.soa.core.MethodQueue
    public void addMethod(final String ownerId, final Method method) {
        MethodEntry methodEntryPeek;
        MethodEntry methodEntry = new MethodEntry(ownerId, method, this.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
        synchronized (this.queueLock) {
            this.methodQueue.add(methodEntry);
            try {
                this.logger.debug(Mappers.mapper().writeValueAsString(this.methodQueue));
            } catch (IOException e) {
                this.logger.error("Error in methodQueue serialization", (Throwable) e);
            }
            methodEntryPeek = this.methodQueue.size() == 1 ? this.methodQueue.peek() : null;
        }
        if (methodEntryPeek != null) {
            executeMethod(methodEntryPeek);
        }
    }

    private void executeMethod(final MethodEntry entry) {
        if (entry != null) {
            String str = entry.ownerId;
            if (this.ownerRegistry.canProcessMethod(str, entry.method) || this.methodsRegistry.isGlobalMethod(entry.method)) {
                entry.setStatus(MethodEntry.MethodStatus.PROCESSING);
                MethodCallback methodCallbackLookupCallback = this.methodsRegistry.lookupCallback(entry.method.getClass().getCanonicalName());
                if (methodCallbackLookupCallback == null) {
                    onMethodExecuted(entry.method);
                    return;
                } else {
                    methodCallbackLookupCallback.executeMethod(entry.method, str, this);
                    return;
                }
            }
            onMethodExecuted(entry.method);
        }
    }

    @Override // com.amazon.soa.core.OnMethodExecutedListener
    public void onMethodExecuted(final Method method) {
        MethodEntry methodEntryPeek;
        synchronized (this.queueLock) {
            this.methodQueue.poll();
            methodEntryPeek = !this.methodQueue.isEmpty() ? this.methodQueue.peek() : null;
        }
        if (methodEntryPeek != null) {
            executeMethod(methodEntryPeek);
        }
    }
}
