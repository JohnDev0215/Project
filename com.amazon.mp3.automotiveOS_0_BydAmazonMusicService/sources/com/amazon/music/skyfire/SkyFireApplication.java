package com.amazon.music.skyfire;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import QueuesInterface.v1_0.MultiThreadedQueue;
import com.amazon.music.skyfire.core.OwnerRegistry;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.MethodEngine;
import com.amazon.music.skyfire.platform.MethodEntry;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.core.MultiThreadedQueueImpl;
import com.amazon.music.skyfire.platform.core.SingleThreadedQueueImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class SkyFireApplication implements MethodsDispatcher<Method>, OwnerRegistry {
    private final MethodEngine methodEngine = new MethodEngine();
    private String tag;

    public void registerMethods(Set<String> set, Set<String> set2, MethodCallback methodCallback) {
        this.methodEngine.registerMethods(set, set2, methodCallback);
    }

    public void deregisterMethods(Set<String> set, Set<String> set2) {
        this.methodEngine.deregisterMethods(set, set2);
    }

    @Override // com.amazon.music.skyfire.platform.MethodsDispatcher
    public void dispatchMethods(String str, List<? extends Method> list) {
        this.methodEngine.handleMethods(str, convertMethodList(str, list));
    }

    public void dispatchMethods(String str, List<? extends Method> list, String str2) {
        this.tag = str2;
        dispatchMethods(str, list);
    }

    public String getTag() {
        return this.tag;
    }

    @Override // com.amazon.music.skyfire.core.OwnerRegistry
    public void registerOwner(String str) {
        this.methodEngine.registerOwner(str);
    }

    @Override // com.amazon.music.skyfire.core.OwnerRegistry
    public void unregisterOwner(String str) {
        this.methodEngine.deregisterOwner(str);
    }

    @Override // com.amazon.music.skyfire.core.OwnerRegistry
    public boolean isOwnerAvailable(String str) {
        return this.methodEngine.isOwnerAvailable(str);
    }

    public static MethodEntry<Method> convertMethod(String str, Method method) {
        String canonicalName = method.getClass().getCanonicalName();
        if (canonicalName == null) {
            canonicalName = method.getClass().getSuperclass().getCanonicalName();
        }
        MethodEntry<Method> methodEntry = new MethodEntry<>(str, canonicalName, method);
        Queue queue = method.queue();
        if (queue instanceof MultiThreadedQueue) {
            methodEntry.setQueue(new MultiThreadedQueueImpl(queue.id()));
        } else {
            methodEntry.setQueue(new SingleThreadedQueueImpl(queue.id()));
        }
        return methodEntry;
    }

    public static List<MethodEntry<Method>> convertMethodList(String str, List<? extends Method> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<? extends Method> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(convertMethod(str, it.next()));
            }
        }
        return arrayList;
    }
}
