package com.android.server.telecom;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.telecom.Log;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.EventLog;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.Preconditions;
import com.android.server.telecom.TelecomSystem;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class ServiceBinder {
    private IBinder mBinder;
    protected final ComponentName mComponentName;
    private final Context mContext;
    private boolean mIsBindingAborted;
    protected final TelecomSystem.SyncRoot mLock;
    protected final String mPackageAbbreviation;
    private final String mServiceAction;
    private ServiceConnection mServiceConnection;
    private ServiceDeathRecipient mServiceDeathRecipient;
    private UserHandle mUserHandle;
    private final Set<BindCallback> mCallbacks = new ArraySet();
    private int mAssociatedCallCount = 0;
    private final Set<Listener> mListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));

    interface BindCallback {
        void onFailure();

        void onSuccess();
    }

    interface Listener<ServiceBinderClass extends ServiceBinder> {
        void onUnbind(ServiceBinderClass servicebinderclass);
    }

    protected abstract void removeServiceInterface();

    protected abstract void setServiceInterface(IBinder iBinder);

    final class Binder2 {
        Binder2() {
        }

        void bind(BindCallback bindCallback, Call call) {
            boolean zBindService;
            Log.d(ServiceBinder.this, "bind()", new Object[0]);
            ServiceBinder.this.clearAbort();
            synchronized (ServiceBinder.this.mCallbacks) {
                if (!ServiceBinder.this.mCallbacks.isEmpty()) {
                    ServiceBinder.this.mCallbacks.add(bindCallback);
                    return;
                }
                ServiceBinder.this.mCallbacks.add(bindCallback);
                if (ServiceBinder.this.mServiceConnection == null) {
                    Intent component = new Intent(ServiceBinder.this.mServiceAction).setComponent(ServiceBinder.this.mComponentName);
                    ServiceBinderConnection serviceBinderConnection = ServiceBinder.this.new ServiceBinderConnection(call);
                    Log.addEvent(call, "BIND_CS", ServiceBinder.this.mComponentName);
                    if (ServiceBinder.this.mUserHandle == null) {
                        zBindService = ServiceBinder.this.mContext.bindService(component, serviceBinderConnection, 68157441);
                    } else {
                        zBindService = ServiceBinder.this.mContext.bindServiceAsUser(component, serviceBinderConnection, 68157441, ServiceBinder.this.mUserHandle);
                    }
                    if (zBindService) {
                        return;
                    }
                    ServiceBinder.this.handleFailedConnection();
                    return;
                }
                Log.d(ServiceBinder.this, "Service is already bound.", new Object[0]);
                Preconditions.checkNotNull(ServiceBinder.this.mBinder);
                ServiceBinder.this.handleSuccessfulConnection();
            }
        }
    }

    private class ServiceDeathRecipient implements IBinder.DeathRecipient {
        private ComponentName mComponentName;

        ServiceDeathRecipient(ComponentName componentName) {
            this.mComponentName = componentName;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            try {
                synchronized (ServiceBinder.this.mLock) {
                    Log.startSession("SDR.bD", Log.getPackageAbbreviation(this.mComponentName));
                    Log.i(this, "binderDied: ConnectionService %s died.", new Object[]{this.mComponentName});
                    ServiceBinder.this.logServiceDisconnected("binderDied");
                    ServiceBinder.this.handleDisconnect();
                }
            } finally {
                Log.endSession();
            }
        }
    }

    private final class ServiceBinderConnection implements ServiceConnection {
        private Call mCall;

        ServiceBinderConnection(Call call) {
            this.mCall = call;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                Log.startSession("SBC.oSC", Log.getPackageAbbreviation(componentName));
                synchronized (ServiceBinder.this.mLock) {
                    Log.i(this, "Service bound %s", new Object[]{componentName});
                    Log.addEvent(this.mCall, "CS_BOUND", componentName);
                    if (ServiceBinder.this.mIsBindingAborted) {
                        ServiceBinder.this.clearAbort();
                        ServiceBinder.this.logServiceDisconnected("onServiceConnected");
                        ServiceBinder.this.mContext.unbindService(this);
                        ServiceBinder.this.handleFailedConnection();
                        return;
                    }
                    if (iBinder != null) {
                        ServiceBinder.this.mServiceDeathRecipient = ServiceBinder.this.new ServiceDeathRecipient(componentName);
                        try {
                            iBinder.linkToDeath(ServiceBinder.this.mServiceDeathRecipient, 0);
                            ServiceBinder.this.mServiceConnection = this;
                            ServiceBinder.this.setBinder(iBinder);
                            ServiceBinder.this.handleSuccessfulConnection();
                        } catch (RemoteException unused) {
                            Log.w(this, "onServiceConnected: %s died.", new Object[0]);
                            if (ServiceBinder.this.mServiceDeathRecipient != null) {
                                ServiceBinder.this.mServiceDeathRecipient.binderDied();
                            }
                        }
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            try {
                Log.startSession("SBC.oSD", Log.getPackageAbbreviation(componentName));
                synchronized (ServiceBinder.this.mLock) {
                    ServiceBinder.this.logServiceDisconnected("onServiceDisconnected");
                    ServiceBinder.this.handleDisconnect();
                }
            } finally {
                Log.endSession();
            }
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(ComponentName componentName) {
            try {
                Log.startSession("SBC.oNB");
                synchronized (ServiceBinder.this.mLock) {
                    Log.w(this, "Null binding %s", new Object[]{componentName});
                    Log.addEvent(this.mCall, "NULL_BINDING", componentName);
                    EventLog.writeEvent(1397638484, "211114016", -1, componentName == null ? "null" : componentName.toString());
                    ServiceBinder.this.logServiceDisconnected("onNullBinding");
                    ServiceBinder.this.mContext.unbindService(this);
                    ServiceBinder.this.clearAbort();
                    ServiceBinder.this.handleFailedConnection();
                }
            } finally {
                Log.endSession();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDisconnect() {
        this.mServiceConnection = null;
        clearAbort();
        handleServiceDisconnected();
    }

    protected ServiceBinder(String str, ComponentName componentName, Context context, TelecomSystem.SyncRoot syncRoot, UserHandle userHandle) {
        Preconditions.checkState(!TextUtils.isEmpty(str));
        Preconditions.checkNotNull(componentName);
        this.mContext = context;
        this.mLock = syncRoot;
        this.mServiceAction = str;
        this.mComponentName = componentName;
        this.mPackageAbbreviation = Log.getPackageAbbreviation(componentName);
        this.mUserHandle = userHandle;
    }

    final UserHandle getUserHandle() {
        return this.mUserHandle;
    }

    final void incrementAssociatedCallCount() {
        int i = this.mAssociatedCallCount + 1;
        this.mAssociatedCallCount = i;
        Log.v(this, "Call count increment %d, %s", new Object[]{Integer.valueOf(i), this.mComponentName.flattenToShortString()});
    }

    final void decrementAssociatedCallCount() {
        decrementAssociatedCallCount(false);
    }

    final void decrementAssociatedCallCount(boolean z) {
        int i = this.mAssociatedCallCount;
        if (i > 0) {
            int i2 = i - 1;
            this.mAssociatedCallCount = i2;
            Log.v(this, "Call count decrement %d, %s", new Object[]{Integer.valueOf(i2), this.mComponentName.flattenToShortString()});
            if (z || this.mAssociatedCallCount != 0) {
                return;
            }
            unbind();
            return;
        }
        Log.wtf(this, "%s: ignoring a request to decrement mAssociatedCallCount below zero", new Object[]{this.mComponentName.getClassName()});
    }

    final void unbind() {
        if (this.mServiceConnection == null) {
            this.mIsBindingAborted = true;
            return;
        }
        logServiceDisconnected("unbind");
        unlinkDeathRecipient();
        this.mContext.unbindService(this.mServiceConnection);
        this.mServiceConnection = null;
        setBinder(null);
    }

    public final ComponentName getComponentName() {
        return this.mComponentName;
    }

    @VisibleForTesting
    public boolean isServiceValid(String str) {
        if (this.mBinder != null) {
            return true;
        }
        Log.w(this, "%s invoked while service is unbound", new Object[]{str});
        return false;
    }

    final void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logServiceDisconnected(String str) {
        Log.i(this, "Service unbound %s, from %s.", new Object[]{this.mComponentName, str});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSuccessfulConnection() {
        ArraySet arraySet = new ArraySet();
        synchronized (this.mCallbacks) {
            arraySet.addAll(this.mCallbacks);
            this.mCallbacks.clear();
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((BindCallback) it.next()).onSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFailedConnection() {
        ArraySet arraySet = new ArraySet();
        synchronized (this.mCallbacks) {
            arraySet.addAll(this.mCallbacks);
            this.mCallbacks.clear();
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            ((BindCallback) it.next()).onFailure();
        }
    }

    private void handleServiceDisconnected() {
        unlinkDeathRecipient();
        setBinder(null);
    }

    private void unlinkDeathRecipient() {
        IBinder iBinder;
        ServiceDeathRecipient serviceDeathRecipient = this.mServiceDeathRecipient;
        if (serviceDeathRecipient != null && (iBinder = this.mBinder) != null) {
            if (!iBinder.unlinkToDeath(serviceDeathRecipient, 0)) {
                Log.i(this, "unlinkDeathRecipient: failed to unlink %s", new Object[]{this.mComponentName});
            }
            this.mServiceDeathRecipient = null;
            return;
        }
        Log.w(this, "unlinkDeathRecipient: death recipient is null.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAbort() {
        this.mIsBindingAborted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBinder(IBinder iBinder) {
        if (this.mBinder != iBinder) {
            if (iBinder == null) {
                removeServiceInterface();
                this.mBinder = null;
                Iterator<Listener> it = this.mListeners.iterator();
                while (it.hasNext()) {
                    it.next().onUnbind(this);
                }
                return;
            }
            this.mBinder = iBinder;
            setServiceInterface(iBinder);
        }
    }
}
