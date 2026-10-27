package com.android.car;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class BinderInterfaceContainer<T extends IInterface> {
    private final Map<IBinder, BinderInterface<T>> mBinders;
    private final BinderEventHandler<T> mEventHandler;
    private final Object mLock;

    public interface BinderEventHandler<T extends IInterface> {
        void onBinderDeath(BinderInterface<T> binderInterface);
    }

    public static class BinderInterface<T extends IInterface> implements IBinder.DeathRecipient {
        public final T binderInterface;
        private final BinderInterfaceContainer<T> mContainer;

        public BinderInterface(BinderInterfaceContainer<T> container, T binderInterface) {
            this.mContainer = container;
            this.binderInterface = binderInterface;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            this.binderInterface.asBinder().unlinkToDeath(this, 0);
            this.mContainer.handleBinderDeath(this);
        }
    }

    public BinderInterfaceContainer(BinderEventHandler<T> eventHandler) {
        this.mLock = new Object();
        this.mBinders = new HashMap();
        this.mEventHandler = eventHandler;
    }

    public BinderInterfaceContainer() {
        this.mLock = new Object();
        this.mBinders = new HashMap();
        this.mEventHandler = null;
    }

    public void addBinder(T binderInterface) {
        IBinder binder = binderInterface.asBinder();
        synchronized (this.mLock) {
            if (this.mBinders.get(binder) != null) {
                return;
            }
            BinderInterface<T> bInterface = new BinderInterface<>(this, binderInterface);
            try {
                binder.linkToDeath(bInterface, 0);
                this.mBinders.put(binder, bInterface);
            } catch (RemoteException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }

    public void removeBinder(T binderInterface) {
        IBinder binder = binderInterface.asBinder();
        synchronized (this.mLock) {
            BinderInterface<T> bInterface = this.mBinders.get(binder);
            if (bInterface == null) {
                return;
            }
            binder.unlinkToDeath(bInterface, 0);
            this.mBinders.remove(binder);
        }
    }

    public BinderInterface<T> getBinderInterface(T binderInterface) {
        BinderInterface<T> binderInterface2;
        IBinder binder = binderInterface.asBinder();
        synchronized (this.mLock) {
            binderInterface2 = this.mBinders.get(binder);
        }
        return binderInterface2;
    }

    public void addBinderInterface(BinderInterface<T> bInterface) {
        IBinder binder = bInterface.binderInterface.asBinder();
        synchronized (this.mLock) {
            try {
                try {
                    binder.linkToDeath(bInterface, 0);
                    this.mBinders.put(binder, bInterface);
                } catch (RemoteException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Collection<BinderInterface<T>> getInterfaces() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mBinders.values());
        }
        return arrayList;
    }

    public int size() {
        int size;
        synchronized (this.mLock) {
            size = this.mBinders.size();
        }
        return size;
    }

    public void clear() {
        synchronized (this.mLock) {
            Collection<BinderInterface<T>> interfaces = getInterfaces();
            for (BinderInterface<T> bInterface : interfaces) {
                IBinder binder = bInterface.binderInterface.asBinder();
                binder.unlinkToDeath(bInterface, 0);
            }
        }
        this.mBinders.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleBinderDeath(BinderInterface<T> bInterface) {
        BinderEventHandler<T> binderEventHandler = this.mEventHandler;
        if (binderEventHandler != null) {
            binderEventHandler.onBinderDeath(bInterface);
        }
        removeBinder(bInterface.binderInterface);
    }
}
