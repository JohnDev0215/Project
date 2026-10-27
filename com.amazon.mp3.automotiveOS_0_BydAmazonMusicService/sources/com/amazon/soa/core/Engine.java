package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class Engine {
    private final OwnerRegistry ownerRegistry = new OwnerRegistry();
    private final Map<String, MethodQueue> methodQueueLookup = new HashMap();
    private final MethodsRegistry methodsRegistry = new MethodsRegistry();

    public void registerMethods(final Set<String> methods, final Set<String> globalMethods, final MethodCallback methodCallback) {
        this.methodsRegistry.addMethods(methods, methodCallback);
        this.methodsRegistry.addGlobalMethods(globalMethods, methodCallback);
    }

    public void registerOwner(final String ownerId) {
        this.ownerRegistry.registerOwner(ownerId);
    }

    public void deregisterOwner(final String ownerId) {
        this.ownerRegistry.unregisterOwner(ownerId);
    }

    public void handleMethods(final String ownerId, final List<Method> methods) {
        if (methods == null) {
            return;
        }
        Iterator<Method> it = methods.iterator();
        while (it.hasNext()) {
            handleMethod(ownerId, it.next());
        }
    }

    public void handleMethod(final String ownerId, final Method method) {
        MethodQueue singleThreadedQueue;
        if (method == null) {
            return;
        }
        if (this.ownerRegistry.canProcessMethod(ownerId, method) || this.methodsRegistry.isGlobalMethod(method)) {
            Queue queue = method.getQueue();
            String id = queue.getId();
            if (!this.methodQueueLookup.containsKey(id)) {
                if (queue instanceof SOACoreInterface.v1_0.MultiThreadedQueue) {
                    singleThreadedQueue = new MultiThreadedQueue(id, this.ownerRegistry, this.methodsRegistry);
                } else {
                    singleThreadedQueue = new SingleThreadedQueue(id, this.ownerRegistry, this.methodsRegistry);
                }
                this.methodQueueLookup.put(id, singleThreadedQueue);
            }
            this.methodQueueLookup.get(id).addMethod(ownerId, method);
        }
    }
}
