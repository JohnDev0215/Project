package com.amazon.music.curate.skyfire.core;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import QueuesInterface.v1_0.MultiThreadedQueue;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.MethodEntry;
import com.amazon.music.skyfire.platform.core.MultiThreadedQueueImpl;
import com.amazon.music.skyfire.platform.core.SingleThreadedQueueImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class MethodEngine {
    final com.amazon.music.skyfire.platform.MethodEngine methodEngine = new com.amazon.music.skyfire.platform.MethodEngine();

    public void registerMethods(final Set<String> methods, final Set<String> globalMethods, final MethodCallback methodCallback) {
        this.methodEngine.registerMethods(methods, globalMethods, methodCallback);
    }

    public void deregisterMethods(final Set<String> methods, final Set<String> globalMethods) {
        this.methodEngine.deregisterMethods(methods, globalMethods);
    }

    public void registerOwner(final String ownerId) {
        this.methodEngine.registerOwner(ownerId);
    }

    public void deregisterOwner(final String ownerId) {
        this.methodEngine.deregisterOwner(ownerId);
    }

    public void handleMethods(final String ownerId, final List<? extends Method> methods) {
        this.methodEngine.handleMethods(ownerId, convertMethodList(ownerId, methods));
    }

    public void handleMethod(final String ownerId, final Method method) {
        this.methodEngine.handleMethod(ownerId, convertMethod(ownerId, method));
    }

    public static MethodEntry<Method> convertMethod(final String ownerId, final Method method) {
        MethodEntry<Method> methodEntry = new MethodEntry<>(ownerId, method.getClass().getCanonicalName(), method);
        Queue queue = method.queue();
        if (queue instanceof MultiThreadedQueue) {
            methodEntry.setQueue(new MultiThreadedQueueImpl(queue.id()));
        } else {
            methodEntry.setQueue(new SingleThreadedQueueImpl(queue.id()));
        }
        return methodEntry;
    }

    public static List<MethodEntry<Method>> convertMethodList(final String ownerId, final List<?> methodList) {
        ArrayList arrayList = new ArrayList();
        if (methodList != null) {
            Iterator<?> it = methodList.iterator();
            while (it.hasNext()) {
                arrayList.add(convertMethod(ownerId, (Method) it.next()));
            }
        }
        return arrayList;
    }
}
