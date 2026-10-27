package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.soa.core.MethodEntry;
import com.amazonaws.util.DateUtils;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
class MultiThreadedQueue extends MethodQueue implements OnMethodExecutedListener {
    final SimpleDateFormat dateFormat;
    private final Logger logger;
    private final Map<Method, MethodEntry> lookup;
    private final Queue<MethodEntry> methodQueue;
    private final MethodsRegistry methodsRegistry;
    private final OwnerRegistry ownerRegistry;
    private final String queueId;

    MultiThreadedQueue(final String name, final OwnerRegistry ownerRegistry, final MethodsRegistry methodsRegistry) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
        this.dateFormat = simpleDateFormat;
        this.logger = LoggerFactory.getLogger("MultiThreadedQueue");
        this.queueId = name;
        this.lookup = new HashMap();
        this.ownerRegistry = ownerRegistry;
        this.methodsRegistry = methodsRegistry;
        this.methodQueue = new ConcurrentLinkedQueue();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override // com.amazon.soa.core.MethodQueue
    public void addMethod(final String ownerId, final Method method) {
        MethodEntry methodEntry = new MethodEntry(ownerId, method, this.dateFormat.format(Long.valueOf(System.currentTimeMillis())));
        this.lookup.put(method, methodEntry);
        this.methodQueue.add(methodEntry);
        try {
            String strWriteValueAsString = Mappers.mapper().writeValueAsString(this.methodQueue);
            this.logger.debug("=========" + strWriteValueAsString);
        } catch (IOException e) {
            this.logger.error("Error in methodQueue serialization", (Throwable) e);
        }
        executeMethod(methodEntry);
    }

    private void executeMethod(final MethodEntry entry) {
        if (entry != null) {
            String str = entry.ownerId;
            if (this.ownerRegistry.canProcessMethod(str, entry.method)) {
                entry.setStatus(MethodEntry.MethodStatus.PROCESSING);
                MethodCallback methodCallbackLookupCallback = this.methodsRegistry.lookupCallback(entry.method.getClass().getCanonicalName());
                if (methodCallbackLookupCallback == null) {
                    this.methodQueue.remove(entry);
                } else {
                    methodCallbackLookupCallback.executeMethod(entry.method, str, this);
                }
            }
        }
    }

    @Override // com.amazon.soa.core.OnMethodExecutedListener
    public void onMethodExecuted(final Method method) {
        MethodEntry methodEntry = this.lookup.get(method);
        if (methodEntry != null) {
            this.methodQueue.remove(methodEntry);
        }
    }
}
