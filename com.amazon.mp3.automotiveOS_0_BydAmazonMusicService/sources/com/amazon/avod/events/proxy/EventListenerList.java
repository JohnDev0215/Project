package com.amazon.avod.events.proxy;

import android.os.Handler;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class EventListenerList<T> {
    private final List<T> mListeners = Collections.synchronizedList(newArrayList());

    private List<T> newArrayList() {
        return Lists.newArrayList();
    }

    public void add(T t) {
        this.mListeners.add(t);
    }

    public void invokeOnEach(EventInvocation eventInvocation, Handler handler) {
        synchronized (this.mListeners) {
            Iterator<T> it = this.mListeners.iterator();
            while (it.hasNext()) {
                eventInvocation.invoke(it.next(), handler);
            }
        }
    }
}
