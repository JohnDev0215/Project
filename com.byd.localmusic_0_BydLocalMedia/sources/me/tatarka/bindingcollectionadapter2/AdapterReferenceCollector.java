package me.tatarka.bindingcollectionadapter2;

import androidx.databinding.ObservableList;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
class AdapterReferenceCollector {
    static final ReferenceQueue<Object> QUEUE = new ReferenceQueue<>();
    static PollReferenceThread thread;

    AdapterReferenceCollector() {
    }

    static <T, A extends BindingCollectionAdapter<T>> WeakReference<A> createRef(A a, ObservableList<T> observableList, ObservableList.OnListChangedCallback onListChangedCallback) {
        PollReferenceThread pollReferenceThread = thread;
        if (pollReferenceThread == null || !pollReferenceThread.isAlive()) {
            PollReferenceThread pollReferenceThread2 = new PollReferenceThread();
            thread = pollReferenceThread2;
            pollReferenceThread2.start();
        }
        return new AdapterRef(a, observableList, onListChangedCallback);
    }

    private static class PollReferenceThread extends Thread {
        private PollReferenceThread() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    Reference<? extends Object> referenceRemove = AdapterReferenceCollector.QUEUE.remove();
                    if (referenceRemove instanceof AdapterRef) {
                        ((AdapterRef) referenceRemove).unregister();
                    }
                } catch (InterruptedException unused) {
                    return;
                }
            }
        }
    }

    static class AdapterRef<T, A extends BindingCollectionAdapter<T>> extends WeakReference<A> {
        private final ObservableList.OnListChangedCallback callback;
        private final ObservableList<T> items;

        AdapterRef(A a, ObservableList<T> observableList, ObservableList.OnListChangedCallback onListChangedCallback) {
            super(a, AdapterReferenceCollector.QUEUE);
            this.items = observableList;
            this.callback = onListChangedCallback;
        }

        void unregister() {
            this.items.removeOnListChangedCallback(this.callback);
        }
    }
}
