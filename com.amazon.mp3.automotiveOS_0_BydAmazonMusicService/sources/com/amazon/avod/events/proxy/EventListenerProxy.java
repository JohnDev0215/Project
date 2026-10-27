package com.amazon.avod.events.proxy;

import android.os.Handler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class EventListenerProxy<T> implements InvocationHandler {
    static final Map<Class<?>, Object> mPrimitiveReturnValues;
    private final ConditionalRunnableQueue mConditionalQueue;
    private final Handler mHandler;
    private final EventListenerList<T> mListenerList;

    static {
        HashMap map = new HashMap();
        mPrimitiveReturnValues = map;
        map.put(Boolean.TYPE, Boolean.FALSE);
        map.put(Long.TYPE, 0);
        map.put(Integer.TYPE, 0);
        map.put(Short.TYPE, 0);
        map.put(Byte.TYPE, 0);
        map.put(Character.TYPE, '0');
        map.put(Double.TYPE, Double.valueOf(0.0d));
        map.put(Float.TYPE, Float.valueOf(0.0f));
    }

    public static <T> void addListener(T t, T t2) {
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(t);
        if (invocationHandler instanceof EventListenerProxy) {
            ((EventListenerProxy) invocationHandler).addListener(t2);
        }
    }

    public static <T> void startDispatchingEvents(T... tArr) {
        for (T t : tArr) {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(t);
            if (invocationHandler instanceof EventListenerProxy) {
                ((EventListenerProxy) invocationHandler).startDispatchingEvents();
            }
        }
    }

    public EventListenerProxy() {
        this(null);
    }

    public EventListenerProxy(Handler handler) {
        this(handler, new ConditionalRunnableQueue());
    }

    EventListenerProxy(Handler handler, ConditionalRunnableQueue conditionalRunnableQueue) {
        this.mListenerList = new EventListenerList<>();
        this.mHandler = handler;
        this.mConditionalQueue = conditionalRunnableQueue;
    }

    public void addListener(T t) {
        this.mListenerList.add(t);
    }

    public void startDispatchingEvents() {
        this.mConditionalQueue.setConditionMet();
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        final EventInvocation eventInvocation = new EventInvocation(method, objArr);
        this.mConditionalQueue.addAction(new Runnable() { // from class: com.amazon.avod.events.proxy.EventListenerProxy.1
            @Override // java.lang.Runnable
            public void run() {
                EventListenerProxy.this.mListenerList.invokeOnEach(eventInvocation, EventListenerProxy.this.mHandler);
            }
        });
        Class<?> returnType = method.getReturnType();
        Map<Class<?>, Object> map = mPrimitiveReturnValues;
        if (map.containsKey(returnType)) {
            return map.get(returnType);
        }
        return null;
    }
}
