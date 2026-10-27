package android.location;

import android.location.AbstractListenerManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.RemoteException;
import android.util.ArrayMap;
import com.android.internal.util.Preconditions;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
abstract class AbstractListenerManager<TRequest, TListener> {
    private TRequest mMergedRequest;
    private final Object mLock = new Object();
    private volatile ArrayMap<Object, Registration<TRequest, TListener>> mListeners = new ArrayMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    protected TListener convertKey(Object obj) {
        return obj;
    }

    protected abstract boolean registerService(TRequest trequest) throws RemoteException;

    protected abstract void unregisterService() throws RemoteException;

    AbstractListenerManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Registration<TRequest, TListener> {
        private final Executor mExecutor;
        private volatile TListener mListener;
        private TRequest mRequest;

        private Registration(TRequest trequest, Executor executor, TListener tlistener) {
            Preconditions.checkArgument(tlistener != null, "invalid null listener/callback");
            Preconditions.checkArgument(executor != null, "invalid null executor");
            this.mExecutor = executor;
            this.mListener = tlistener;
            this.mRequest = trequest;
        }

        public TRequest getRequest() {
            return this.mRequest;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unregister() {
            this.mRequest = null;
            this.mListener = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void execute(Consumer<TListener> consumer) {
            this.mExecutor.execute(PooledLambda.obtainRunnable(new BiConsumer() { // from class: android.location.-$$Lambda$AbstractListenerManager$Registration$olaX9ADjAJ8lXCb8AOH5b_5tcrQ
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((AbstractListenerManager.Registration) obj).accept((Consumer) obj2);
                }
            }, this, consumer).recycleOnUse());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void accept(Consumer<TListener> consumer) {
            TListener tlistener = this.mListener;
            if (tlistener == null) {
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                consumer.accept(tlistener);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public boolean addListener(TListener tlistener, Handler handler) throws RemoteException {
        return addInternal((Object) null, tlistener, handler);
    }

    public boolean addListener(TListener tlistener, Executor executor) throws RemoteException {
        return addInternal((Object) null, tlistener, executor);
    }

    public boolean addListener(TRequest trequest, TListener tlistener, Handler handler) throws RemoteException {
        return addInternal(trequest, tlistener, handler);
    }

    public boolean addListener(TRequest trequest, TListener tlistener, Executor executor) throws RemoteException {
        return addInternal(trequest, tlistener, executor);
    }

    protected final boolean addInternal(TRequest trequest, Object obj, Handler handler) throws RemoteException {
        return addInternal(trequest, obj, new HandlerExecutor(handler));
    }

    protected final boolean addInternal(TRequest trequest, Object obj, Executor executor) throws RemoteException {
        Preconditions.checkArgument(obj != null, "invalid null listener/callback");
        return addInternal(obj, new Registration<>(trequest, executor, convertKey(obj)));
    }

    private boolean addInternal(Object obj, Registration<TRequest, TListener> registration) throws RemoteException {
        Preconditions.checkNotNull(registration);
        synchronized (this.mLock) {
            boolean zIsEmpty = this.mListeners.isEmpty();
            ArrayMap<Object, Registration<TRequest, TListener>> arrayMap = new ArrayMap<>(this.mListeners.size() + 1);
            arrayMap.putAll(this.mListeners);
            Registration<TRequest, TListener> registrationPut = arrayMap.put(obj, registration);
            this.mListeners = arrayMap;
            if (registrationPut != null) {
                registrationPut.unregister();
            }
            TRequest trequestMergeRequests = mergeRequests();
            if (zIsEmpty || !Objects.equals(trequestMergeRequests, this.mMergedRequest)) {
                this.mMergedRequest = trequestMergeRequests;
                if (!zIsEmpty) {
                    unregisterService();
                }
                registerService(this.mMergedRequest);
            }
        }
        return true;
    }

    public void removeListener(Object obj) throws RemoteException {
        synchronized (this.mLock) {
            ArrayMap<Object, Registration<TRequest, TListener>> arrayMap = new ArrayMap<>(this.mListeners);
            Registration<TRequest, TListener> registrationRemove = arrayMap.remove(obj);
            this.mListeners = arrayMap;
            if (registrationRemove == null) {
                return;
            }
            registrationRemove.unregister();
            boolean zIsEmpty = this.mListeners.isEmpty();
            TRequest trequestMergeRequests = zIsEmpty ? null : mergeRequests();
            boolean z = (zIsEmpty || Objects.equals(trequestMergeRequests, this.mMergedRequest)) ? false : true;
            if (zIsEmpty || z) {
                unregisterService();
                this.mMergedRequest = trequestMergeRequests;
                if (z) {
                    registerService(trequestMergeRequests);
                }
            }
        }
    }

    protected TRequest merge(List<TRequest> list) {
        Iterator<TRequest> it = list.iterator();
        while (it.hasNext()) {
            Preconditions.checkArgument(it.next() == null, "merge() has to be overridden for non-null requests.");
        }
        return null;
    }

    protected void execute(Consumer<TListener> consumer) {
        Iterator<Registration<TRequest, TListener>> it = this.mListeners.values().iterator();
        while (it.hasNext()) {
            it.next().execute(consumer);
        }
    }

    private TRequest mergeRequests() {
        Preconditions.checkState(Thread.holdsLock(this.mLock));
        if (this.mListeners.isEmpty()) {
            return null;
        }
        if (this.mListeners.size() == 1) {
            return this.mListeners.valueAt(0).getRequest();
        }
        ArrayList arrayList = new ArrayList(this.mListeners.size());
        for (int i = 0; i < this.mListeners.size(); i++) {
            arrayList.add(this.mListeners.valueAt(i).getRequest());
        }
        return merge(arrayList);
    }
}
