package com.android.car;

import android.car.IAppFocus;
import android.car.IAppFocusListener;
import android.car.IAppFocusOwnershipCallback;
import android.content.Context;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import com.android.car.BinderInterfaceContainer;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class AppFocusService extends IAppFocus.Stub implements CarServiceBase, BinderInterfaceContainer.BinderEventHandler<IAppFocusOwnershipCallback> {
    private static final boolean DBG = false;
    private static final boolean DBG_EVENT = false;
    final ClientHolder mAllChangeClients;
    final OwnershipClientHolder mAllOwnershipClients;
    private final DispatchHandler mDispatchHandler;
    private final SystemActivityMonitoringService mSystemActivityMonitoringService;
    private final Object mLock = new Object();
    private final SparseArray<OwnershipClientInfo> mFocusOwners = new SparseArray<>();
    private final Set<Integer> mActiveAppTypes = new ArraySet();
    private final List<FocusOwnershipCallback> mFocusOwnershipCallbacks = new ArrayList();
    private final BinderInterfaceContainer.BinderEventHandler<IAppFocusListener> mAllBinderEventHandler = new BinderInterfaceContainer.BinderEventHandler() { // from class: com.android.car.-$$Lambda$AppFocusService$AiDDPZgEZaDV_-UI2aNW_sgF2_U
        @Override // com.android.car.BinderInterfaceContainer.BinderEventHandler
        public final void onBinderDeath(BinderInterfaceContainer.BinderInterface binderInterface) {
            AppFocusService.lambda$new$0(binderInterface);
        }
    };
    private final HandlerThread mHandlerThread = CarServiceUtils.getHandlerThread(getClass().getSimpleName());

    public interface FocusOwnershipCallback {
        void onFocusAbandoned(int i, int i2, int i3);

        void onFocusAcquired(int i, int i2, int i3);
    }

    static /* synthetic */ void lambda$new$0(BinderInterfaceContainer.BinderInterface bInterface) {
    }

    public AppFocusService(Context context, SystemActivityMonitoringService systemActivityMonitoringService) {
        this.mDispatchHandler = new DispatchHandler(this.mHandlerThread.getLooper(), this);
        this.mSystemActivityMonitoringService = systemActivityMonitoringService;
        this.mAllChangeClients = new ClientHolder(this.mAllBinderEventHandler);
        this.mAllOwnershipClients = new OwnershipClientHolder();
    }

    public void registerFocusListener(IAppFocusListener listener, int appType) {
        synchronized (this.mLock) {
            ClientInfo info = (ClientInfo) this.mAllChangeClients.getBinderInterface(listener);
            if (info == null) {
                this.mAllChangeClients.addBinderInterface(new ClientInfo(this.mAllChangeClients, listener, Binder.getCallingUid(), Binder.getCallingPid(), appType));
            } else {
                info.addAppType(Integer.valueOf(appType));
            }
        }
    }

    public void unregisterFocusListener(IAppFocusListener listener, int appType) {
        synchronized (this.mLock) {
            ClientInfo info = (ClientInfo) this.mAllChangeClients.getBinderInterface(listener);
            if (info == null) {
                return;
            }
            info.removeAppType(Integer.valueOf(appType));
            if (info.getAppTypes().isEmpty()) {
                this.mAllChangeClients.removeBinder(listener);
            }
        }
    }

    public int[] getActiveAppTypes() {
        int[] array;
        synchronized (this.mLock) {
            array = this.mActiveAppTypes.stream().mapToInt($$Lambda$UV1wDVoVlbcxpr8zevj_aMFtUGw.INSTANCE).toArray();
        }
        return array;
    }

    public boolean isOwningFocus(IAppFocusOwnershipCallback callback, int appType) {
        OwnershipClientInfo info;
        synchronized (this.mLock) {
            info = (OwnershipClientInfo) this.mAllOwnershipClients.getBinderInterface(callback);
        }
        if (info == null) {
            return false;
        }
        return info.getOwnedAppTypes().contains(Integer.valueOf(appType));
    }

    public int requestAppFocus(IAppFocusOwnershipCallback callback, int appType) {
        synchronized (this.mLock) {
            OwnershipClientInfo info = (OwnershipClientInfo) this.mAllOwnershipClients.getBinderInterface(callback);
            if (info == null) {
                info = new OwnershipClientInfo(this.mAllOwnershipClients, callback, Binder.getCallingUid(), Binder.getCallingPid());
                this.mAllOwnershipClients.addBinderInterface(info);
            }
            Set<Integer> alreadyOwnedAppTypes = info.getOwnedAppTypes();
            if (!alreadyOwnedAppTypes.contains(Integer.valueOf(appType))) {
                OwnershipClientInfo ownerInfo = this.mFocusOwners.get(appType);
                if (ownerInfo != null && ownerInfo != info) {
                    if (this.mSystemActivityMonitoringService.isInForeground(ownerInfo.getPid(), ownerInfo.getUid()) && !this.mSystemActivityMonitoringService.isInForeground(info.getPid(), info.getUid())) {
                        Log.w(CarLog.TAG_APP_FOCUS, "Focus request failed for non-foreground app(pid=" + info.getPid() + ", uid=" + info.getUid() + ").Foreground app (pid=" + ownerInfo.getPid() + ", uid=" + ownerInfo.getUid() + ") owns it.");
                        return 0;
                    }
                    ownerInfo.removeOwnedAppType(Integer.valueOf(appType));
                    this.mDispatchHandler.requestAppFocusOwnershipLossDispatch(ownerInfo.binderInterface, appType);
                }
                updateFocusOwner(appType, info);
            }
            info.addOwnedAppType(Integer.valueOf(appType));
            this.mDispatchHandler.requestAppFocusOwnershipGrantDispatch(info.binderInterface, appType);
            this.mActiveAppTypes.add(Integer.valueOf(appType));
            for (BinderInterfaceContainer.BinderInterface<IAppFocusListener> client : this.mAllChangeClients.getInterfaces()) {
                ClientInfo clientInfo = (ClientInfo) client;
                if (clientInfo.getAppTypes().contains(Integer.valueOf(appType))) {
                    this.mDispatchHandler.requestAppFocusChangeDispatch(clientInfo.binderInterface, appType, true);
                }
            }
            return 1;
        }
    }

    public void abandonAppFocus(IAppFocusOwnershipCallback callback, int appType) {
        synchronized (this.mLock) {
            OwnershipClientInfo info = (OwnershipClientInfo) this.mAllOwnershipClients.getBinderInterface(callback);
            if (info == null) {
                return;
            }
            if (this.mActiveAppTypes.contains(Integer.valueOf(appType))) {
                Set<Integer> currentlyOwnedAppTypes = info.getOwnedAppTypes();
                if (currentlyOwnedAppTypes.contains(Integer.valueOf(appType))) {
                    if (this.mFocusOwners.contains(appType)) {
                        this.mFocusOwners.remove(appType);
                        this.mActiveAppTypes.remove(Integer.valueOf(appType));
                        info.removeOwnedAppType(Integer.valueOf(appType));
                        for (FocusOwnershipCallback ownershipCallback : this.mFocusOwnershipCallbacks) {
                            ownershipCallback.onFocusAbandoned(appType, info.mUid, info.mPid);
                        }
                        for (BinderInterfaceContainer.BinderInterface<IAppFocusListener> client : this.mAllChangeClients.getInterfaces()) {
                            ClientInfo clientInfo = (ClientInfo) client;
                            if (clientInfo.getAppTypes().contains(Integer.valueOf(appType))) {
                                this.mDispatchHandler.requestAppFocusChangeDispatch(clientInfo.binderInterface, appType, false);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
    }

    public Looper getLooper() {
        return this.mHandlerThread.getLooper();
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        synchronized (this.mLock) {
            this.mAllChangeClients.clear();
            this.mAllOwnershipClients.clear();
            this.mFocusOwners.clear();
            this.mActiveAppTypes.clear();
        }
    }

    @Override // com.android.car.BinderInterfaceContainer.BinderEventHandler
    public void onBinderDeath(BinderInterfaceContainer.BinderInterface<IAppFocusOwnershipCallback> bInterface) {
        OwnershipClientInfo info = (OwnershipClientInfo) bInterface;
        synchronized (this.mLock) {
            for (Integer appType : info.getOwnedAppTypes()) {
                abandonAppFocus(bInterface.binderInterface, appType.intValue());
            }
        }
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        writer.println("**AppFocusService**");
        synchronized (this.mLock) {
            writer.println("mActiveAppTypes:" + this.mActiveAppTypes);
            for (BinderInterfaceContainer.BinderInterface<IAppFocusOwnershipCallback> client : this.mAllOwnershipClients.getInterfaces()) {
                OwnershipClientInfo clientInfo = (OwnershipClientInfo) client;
                writer.println(clientInfo);
            }
        }
    }

    public boolean isFocusOwner(int uid, int pid, int appType) {
        synchronized (this.mLock) {
            boolean z = false;
            if (!this.mFocusOwners.contains(appType)) {
                return false;
            }
            OwnershipClientInfo clientInfo = this.mFocusOwners.get(appType);
            if (clientInfo.getUid() == uid && clientInfo.getPid() == pid) {
                z = true;
            }
            return z;
        }
    }

    public void registerContextOwnerChangedCallback(FocusOwnershipCallback callback) {
        SparseArray<OwnershipClientInfo> owners;
        synchronized (this.mLock) {
            this.mFocusOwnershipCallbacks.add(callback);
            owners = this.mFocusOwners.clone();
        }
        for (int idx = 0; idx < owners.size(); idx++) {
            int key = owners.keyAt(idx);
            OwnershipClientInfo clientInfo = owners.valueAt(idx);
            callback.onFocusAcquired(key, clientInfo.getUid(), clientInfo.getPid());
        }
    }

    public void unregisterContextOwnerChangedCallback(FocusOwnershipCallback callback) {
        synchronized (this.mLock) {
            this.mFocusOwnershipCallbacks.remove(callback);
        }
    }

    private void updateFocusOwner(final int appType, final OwnershipClientInfo owner) {
        CarServiceUtils.runOnMain(new Runnable() { // from class: com.android.car.-$$Lambda$AppFocusService$Exg-V1b2ywBToPzPFRw0drXnRNk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateFocusOwner$1$AppFocusService(appType, owner);
            }
        });
    }

    public /* synthetic */ void lambda$updateFocusOwner$1$AppFocusService(int appType, OwnershipClientInfo owner) {
        List<FocusOwnershipCallback> focusOwnershipCallbacks;
        synchronized (this.mLock) {
            this.mFocusOwners.put(appType, owner);
            focusOwnershipCallbacks = new ArrayList<>(this.mFocusOwnershipCallbacks);
        }
        for (FocusOwnershipCallback callback : focusOwnershipCallbacks) {
            callback.onFocusAcquired(appType, owner.getUid(), owner.getPid());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAppFocusOwnershipLoss(IAppFocusOwnershipCallback callback, int appType) {
        try {
            callback.onAppFocusOwnershipLost(appType);
        } catch (RemoteException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAppFocusOwnershipGrant(IAppFocusOwnershipCallback callback, int appType) {
        try {
            callback.onAppFocusOwnershipGranted(appType);
        } catch (RemoteException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchAppFocusChange(IAppFocusListener listener, int appType, boolean active) {
        try {
            listener.onAppFocusChanged(appType, active);
        } catch (RemoteException e) {
        }
    }

    static class ClientHolder extends BinderInterfaceContainer<IAppFocusListener> {
        private ClientHolder(BinderInterfaceContainer.BinderEventHandler<IAppFocusListener> holder) {
            super(holder);
        }
    }

    static class OwnershipClientHolder extends BinderInterfaceContainer<IAppFocusOwnershipCallback> {
        private OwnershipClientHolder(AppFocusService service) {
            super(service);
        }
    }

    private class ClientInfo extends BinderInterfaceContainer.BinderInterface<IAppFocusListener> {
        private final Set<Integer> mAppTypes;
        private final int mPid;
        private final int mUid;

        private ClientInfo(ClientHolder holder, IAppFocusListener binder, int uid, int pid, int appType) {
            super(holder, binder);
            ArraySet arraySet = new ArraySet();
            this.mAppTypes = arraySet;
            this.mUid = uid;
            this.mPid = pid;
            arraySet.add(Integer.valueOf(appType));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Set<Integer> getAppTypes() {
            Set<Integer> setUnmodifiableSet;
            synchronized (AppFocusService.this.mLock) {
                setUnmodifiableSet = Collections.unmodifiableSet(this.mAppTypes);
            }
            return setUnmodifiableSet;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean addAppType(Integer appType) {
            boolean zAdd;
            synchronized (AppFocusService.this.mLock) {
                zAdd = this.mAppTypes.add(appType);
            }
            return zAdd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean removeAppType(Integer appType) {
            boolean zRemove;
            synchronized (AppFocusService.this.mLock) {
                zRemove = this.mAppTypes.remove(appType);
            }
            return zRemove;
        }

        public String toString() {
            String str;
            synchronized (AppFocusService.this.mLock) {
                str = "ClientInfo{mUid=" + this.mUid + ",mPid=" + this.mPid + ",appTypes=" + this.mAppTypes + "}";
            }
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class OwnershipClientInfo extends BinderInterfaceContainer.BinderInterface<IAppFocusOwnershipCallback> {
        private final Set<Integer> mOwnedAppTypes;
        private final int mPid;
        private final int mUid;

        private OwnershipClientInfo(OwnershipClientHolder holder, IAppFocusOwnershipCallback binder, int uid, int pid) {
            super(holder, binder);
            this.mOwnedAppTypes = new ArraySet();
            this.mUid = uid;
            this.mPid = pid;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Set<Integer> getOwnedAppTypes() {
            Set<Integer> setUnmodifiableSet;
            synchronized (AppFocusService.this.mLock) {
                setUnmodifiableSet = Collections.unmodifiableSet(this.mOwnedAppTypes);
            }
            return setUnmodifiableSet;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean addOwnedAppType(Integer appType) {
            boolean zAdd;
            synchronized (AppFocusService.this.mLock) {
                zAdd = this.mOwnedAppTypes.add(appType);
            }
            return zAdd;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean removeOwnedAppType(Integer appType) {
            boolean zRemove;
            synchronized (AppFocusService.this.mLock) {
                zRemove = this.mOwnedAppTypes.remove(appType);
            }
            return zRemove;
        }

        int getUid() {
            return this.mUid;
        }

        int getPid() {
            return this.mPid;
        }

        public String toString() {
            String str;
            synchronized (AppFocusService.this.mLock) {
                str = "ClientInfo{mUid=" + this.mUid + ",mPid=" + this.mPid + ",owned=" + this.mOwnedAppTypes + "}";
            }
            return str;
        }
    }

    private static final class DispatchHandler extends Handler {
        private static final int MSG_DISPATCH_FOCUS_CHANGE = 2;
        private static final int MSG_DISPATCH_OWNERSHIP_GRANT = 1;
        private static final int MSG_DISPATCH_OWNERSHIP_LOSS = 0;
        private static final String TAG = DispatchHandler.class.getSimpleName();
        private final WeakReference<AppFocusService> mService;

        private DispatchHandler(Looper looper, AppFocusService service) {
            super(looper);
            this.mService = new WeakReference<>(service);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestAppFocusOwnershipLossDispatch(IAppFocusOwnershipCallback callback, int appType) {
            Message msg = obtainMessage(0, appType, 0, callback);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestAppFocusOwnershipGrantDispatch(IAppFocusOwnershipCallback callback, int appType) {
            Message msg = obtainMessage(1, appType, 0, callback);
            sendMessage(msg);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestAppFocusChangeDispatch(IAppFocusListener iAppFocusListener, int i, boolean z) {
            sendMessage(obtainMessage(2, i, z ? 1 : 0, iAppFocusListener));
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            AppFocusService service = this.mService.get();
            if (service == null) {
                Log.i(TAG, "handleMessage null service");
                return;
            }
            int i = msg.what;
            if (i == 0) {
                service.dispatchAppFocusOwnershipLoss((IAppFocusOwnershipCallback) msg.obj, msg.arg1);
                return;
            }
            if (i == 1) {
                service.dispatchAppFocusOwnershipGrant((IAppFocusOwnershipCallback) msg.obj, msg.arg1);
                return;
            }
            if (i == 2) {
                service.dispatchAppFocusChange((IAppFocusListener) msg.obj, msg.arg1, msg.arg2 == 1);
                return;
            }
            Log.e(CarLog.TAG_APP_FOCUS, "Can't dispatch message: " + msg);
        }
    }
}
