package io.objectbox.reactive;

import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class DataPublisherUtils {
    public static <T> void removeObserverFromCopyOnWriteSet(Set<DataObserver<T>> set, DataObserver<T> dataObserver) {
        if (set != null) {
            for (DataObserver<T> dataObserver2 : set) {
                if (dataObserver2.equals(dataObserver)) {
                    set.remove(dataObserver2);
                } else if (dataObserver2 instanceof DelegatingObserver) {
                    DataObserver<T> observerDelegate = dataObserver2;
                    while (observerDelegate instanceof DelegatingObserver) {
                        observerDelegate = ((DelegatingObserver) observerDelegate).getObserverDelegate();
                    }
                    if (observerDelegate == null || observerDelegate.equals(dataObserver)) {
                        set.remove(dataObserver2);
                    }
                }
            }
        }
    }
}
