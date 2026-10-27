package com.android.car.watchdog;

import android.automotive.watchdog.ICarWatchdogClient;
import android.car.hardware.power.ICarPowerStateListener;
import android.car.user.CarUserManager;
import android.car.watchdog.ICarWatchdogService;
import android.car.watchdog.ICarWatchdogServiceCallback;
import android.car.watchdoglib.CarWatchdogDaemonHelper;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.core.os.EnvironmentCompat;
import com.android.car.CarLocalServices;
import com.android.car.CarPowerManagementService;
import com.android.car.CarServiceBase;
import com.android.car.user.CarUserService;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
public final class CarWatchdogService extends ICarWatchdogService.Stub implements CarServiceBase {
    private static final int[] ALL_TIMEOUTS = {0, 1, 2};
    private static final boolean DEBUG = false;
    private static final String TAG = "CAR.WATCHDOG";
    private final Context mContext;
    private int mLastSessionId;
    private final Handler mMainHandler = new Handler(Looper.getMainLooper());
    private final CarWatchdogDaemonHelper.OnConnectionChangeListener mConnectionListener = new CarWatchdogDaemonHelper.OnConnectionChangeListener() { // from class: com.android.car.watchdog.-$$Lambda$CarWatchdogService$WQkD-eZW_KchroiSmrlxem3V8-E
        @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.OnConnectionChangeListener
        public final void onConnectionChange(boolean z) {
            this.f$0.lambda$new$0$CarWatchdogService(z);
        }
    };
    private final Object mLock = new Object();
    private final SparseArray<ArrayList<ClientInfo>> mClientMap = new SparseArray<>();
    private final SparseArray<SparseArray<ClientInfo>> mPingedClientMap = new SparseArray<>();
    private final SparseArray<Boolean> mClientCheckInProgress = new SparseArray<>();
    private final ArrayList<ClientInfo> mClientsNotResponding = new ArrayList<>();
    private final SparseBooleanArray mStoppedUser = new SparseBooleanArray();
    private final CarWatchdogDaemonHelper mCarWatchdogDaemonHelper = new CarWatchdogDaemonHelper("CAR.WATCHDOG");
    private final ICarWatchdogClientImpl mWatchdogClient = new ICarWatchdogClientImpl();

    public /* synthetic */ void lambda$new$0$CarWatchdogService(boolean connected) {
        if (connected) {
            registerToDaemon();
        }
    }

    public CarWatchdogService(Context context) {
        this.mContext = context;
    }

    @Override // com.android.car.CarServiceBase
    public void init() {
        for (int timeout : ALL_TIMEOUTS) {
            this.mClientMap.put(timeout, new ArrayList<>());
            this.mPingedClientMap.put(timeout, new SparseArray<>());
            this.mClientCheckInProgress.put(timeout, false);
        }
        subscribePowerCycleChange();
        subscribeUserStateChange();
        this.mCarWatchdogDaemonHelper.addOnConnectionChangeListener(this.mConnectionListener);
        this.mCarWatchdogDaemonHelper.connect();
    }

    @Override // com.android.car.CarServiceBase
    public void release() {
        unregisterFromDaemon();
        this.mCarWatchdogDaemonHelper.disconnect();
    }

    @Override // com.android.car.CarServiceBase
    public void dump(PrintWriter writer) {
        int count = 1;
        synchronized (this.mLock) {
            writer.println("*CarWatchdogService*");
            writer.println("Registered clients");
            int[] iArr = ALL_TIMEOUTS;
            int length = iArr.length;
            char c = 0;
            int i = 0;
            while (i < length) {
                int timeout = iArr[i];
                ArrayList<ClientInfo> clients = this.mClientMap.get(timeout);
                String timeoutStr = timeoutToString(timeout);
                int i2 = 0;
                while (i2 < clients.size()) {
                    ClientInfo clientInfo = clients.get(i2);
                    Object[] objArr = new Object[4];
                    objArr[c] = "  ";
                    objArr[1] = Integer.valueOf(count);
                    objArr[2] = timeoutStr;
                    objArr[3] = Integer.valueOf(clientInfo.pid);
                    writer.printf("%sclient #%d: timeout = %s, pid = %d\n", objArr);
                    i2++;
                    count++;
                    c = 0;
                }
                i++;
                c = 0;
            }
            writer.printf("Stopped users: ", new Object[0]);
            int size = this.mStoppedUser.size();
            if (size > 0) {
                writer.printf("%d", Integer.valueOf(this.mStoppedUser.keyAt(0)));
                for (int i3 = 1; i3 < size; i3++) {
                    writer.printf(", %d", Integer.valueOf(this.mStoppedUser.keyAt(i3)));
                }
                writer.printf("\n", new Object[0]);
            } else {
                writer.printf("none\n", new Object[0]);
            }
        }
    }

    public void registerClient(ICarWatchdogServiceCallback client, int timeout) {
        ArrayList<ClientInfo> clients = this.mClientMap.get(timeout);
        if (clients == null) {
            Log.w("CAR.WATCHDOG", "Cannot register the client: invalid timeout");
            return;
        }
        synchronized (this.mLock) {
            IBinder binder = client.asBinder();
            for (int i = 0; i < clients.size(); i++) {
                ClientInfo clientInfo = clients.get(i);
                if (binder == clientInfo.client.asBinder()) {
                    Log.w("CAR.WATCHDOG", "Cannot register the client: the client(pid:" + clientInfo.pid + ") has been already registered");
                    return;
                }
            }
            int pid = Binder.getCallingPid();
            int userId = UserHandle.getUserId(Binder.getCallingUid());
            ClientInfo clientInfo2 = new ClientInfo(client, pid, userId, timeout);
            try {
                clientInfo2.linkToDeath();
                clients.add(clientInfo2);
            } catch (RemoteException e) {
                Log.w("CAR.WATCHDOG", "Cannot register the client: linkToDeath to the client failed");
            }
        }
    }

    public void unregisterClient(ICarWatchdogServiceCallback client) {
        synchronized (this.mLock) {
            IBinder binder = client.asBinder();
            for (int timeout : ALL_TIMEOUTS) {
                ArrayList<ClientInfo> clients = this.mClientMap.get(timeout);
                for (int i = 0; i < clients.size(); i++) {
                    ClientInfo clientInfo = clients.get(i);
                    if (binder == clientInfo.client.asBinder()) {
                        clientInfo.unlinkToDeath();
                        clients.remove(i);
                        return;
                    }
                }
            }
            Log.w("CAR.WATCHDOG", "Cannot unregister the client: the client has not been registered before");
        }
    }

    public void tellClientAlive(ICarWatchdogServiceCallback client, int sessionId) {
        SparseArray<ClientInfo> pingedClients;
        ClientInfo clientInfo;
        synchronized (this.mLock) {
            for (int timeout : ALL_TIMEOUTS) {
                if (this.mClientCheckInProgress.get(timeout).booleanValue() && (clientInfo = (pingedClients = this.mPingedClientMap.get(timeout)).get(sessionId)) != null && clientInfo.client.asBinder() == client.asBinder()) {
                    pingedClients.remove(sessionId);
                    return;
                }
            }
        }
    }

    protected int getClientCount(int timeout) {
        int size;
        synchronized (this.mLock) {
            ArrayList<ClientInfo> clients = this.mClientMap.get(timeout);
            size = clients != null ? clients.size() : 0;
        }
        return size;
    }

    private void registerToDaemon() {
        try {
            this.mCarWatchdogDaemonHelper.registerMediator(this.mWatchdogClient);
        } catch (RemoteException | RuntimeException e) {
            Log.w("CAR.WATCHDOG", "Cannot register to car watchdog daemon: " + e);
        }
        UserManager userManager = UserManager.get(this.mContext);
        List<UserInfo> users = userManager.getUsers();
        try {
            for (UserInfo info : users) {
                int userState = userManager.isUserRunning(info.id) ? 0 : 1;
                this.mCarWatchdogDaemonHelper.notifySystemStateChange(1, info.id, userState);
                if (userState == 1) {
                    this.mStoppedUser.put(info.id, true);
                } else {
                    this.mStoppedUser.delete(info.id);
                }
            }
        } catch (RemoteException | RuntimeException e2) {
            Log.w("CAR.WATCHDOG", "Notifying system state change failed: " + e2);
        }
    }

    private void unregisterFromDaemon() {
        try {
            this.mCarWatchdogDaemonHelper.unregisterMediator(this.mWatchdogClient);
        } catch (RemoteException | RuntimeException e) {
            Log.w("CAR.WATCHDOG", "Cannot unregister from car watchdog daemon: " + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientDeath(ICarWatchdogServiceCallback client, int timeout) {
        synchronized (this.mLock) {
            removeClientLocked(client.asBinder(), timeout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postHealthCheckMessage(int sessionId) {
        this.mMainHandler.sendMessage(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.car.watchdog.-$$Lambda$CarWatchdogService$UMmAWPgOlcR0eA5Mlpm2zabg21s
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((CarWatchdogService) obj).doHealthCheck(((Integer) obj2).intValue());
            }
        }, this, Integer.valueOf(sessionId)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHealthCheck(int sessionId) {
        analyzeClientResponse(0);
        reportHealthCheckResult(sessionId);
        sendPingToClients(0);
        sendPingToClientsAndCheck(1);
        sendPingToClientsAndCheck(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void analyzeClientResponse(int timeout) {
        SparseArray<ClientInfo> pingedClients = this.mPingedClientMap.get(timeout);
        synchronized (this.mLock) {
            for (int i = 0; i < pingedClients.size(); i++) {
                ClientInfo clientInfo = pingedClients.valueAt(i);
                if (!this.mStoppedUser.get(clientInfo.userId)) {
                    this.mClientsNotResponding.add(clientInfo);
                    removeClientLocked(clientInfo.client.asBinder(), timeout);
                }
            }
            this.mClientCheckInProgress.setValueAt(timeout, false);
        }
    }

    private void sendPingToClients(int timeout) {
        ArrayList<ClientInfo> clientsToCheck;
        SparseArray<ClientInfo> pingedClients = this.mPingedClientMap.get(timeout);
        synchronized (this.mLock) {
            pingedClients.clear();
            clientsToCheck = new ArrayList<>(this.mClientMap.get(timeout));
            for (int i = 0; i < clientsToCheck.size(); i++) {
                ClientInfo clientInfo = clientsToCheck.get(i);
                if (!this.mStoppedUser.get(clientInfo.userId)) {
                    int sessionId = getNewSessionId();
                    clientInfo.sessionId = sessionId;
                    pingedClients.put(sessionId, clientInfo);
                }
            }
            this.mClientCheckInProgress.setValueAt(timeout, true);
        }
        for (int i2 = 0; i2 < clientsToCheck.size(); i2++) {
            ClientInfo clientInfo2 = clientsToCheck.get(i2);
            try {
                clientInfo2.client.onCheckHealthStatus(clientInfo2.sessionId, timeout);
            } catch (RemoteException e) {
                Log.w("CAR.WATCHDOG", "Sending a ping message to client(pid: " + clientInfo2.pid + ") failed: " + e);
                synchronized (this.mLock) {
                    pingedClients.remove(clientInfo2.sessionId);
                }
            }
        }
    }

    private void sendPingToClientsAndCheck(int timeout) {
        synchronized (this.mLock) {
            if (this.mClientCheckInProgress.get(timeout).booleanValue()) {
                return;
            }
            sendPingToClients(timeout);
            this.mMainHandler.sendMessageDelayed(PooledLambda.obtainMessage(new BiConsumer() { // from class: com.android.car.watchdog.-$$Lambda$CarWatchdogService$hwtla0pskai5c58CWeH0S3YTsWI
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((CarWatchdogService) obj).analyzeClientResponse(((Integer) obj2).intValue());
                }
            }, this, Integer.valueOf(timeout)), timeoutToDurationMs(timeout));
        }
    }

    private int getNewSessionId() {
        int i = this.mLastSessionId + 1;
        this.mLastSessionId = i;
        if (i <= 0) {
            this.mLastSessionId = 1;
        }
        return this.mLastSessionId;
    }

    private void removeClientLocked(IBinder clientBinder, int timeout) {
        ArrayList<ClientInfo> clients = this.mClientMap.get(timeout);
        for (int i = 0; i < clients.size(); i++) {
            ClientInfo clientInfo = clients.get(i);
            if (clientBinder == clientInfo.client.asBinder()) {
                clients.remove(i);
                return;
            }
        }
    }

    private void reportHealthCheckResult(int sessionId) {
        int[] clientsNotResponding;
        ArrayList<ClientInfo> clientsToNotify;
        synchronized (this.mLock) {
            clientsNotResponding = toIntArray(this.mClientsNotResponding);
            clientsToNotify = new ArrayList<>(this.mClientsNotResponding);
            this.mClientsNotResponding.clear();
        }
        for (int i = 0; i < clientsToNotify.size(); i++) {
            ClientInfo clientInfo = clientsToNotify.get(i);
            try {
                clientInfo.client.onPrepareProcessTermination();
            } catch (RemoteException e) {
                Log.w("CAR.WATCHDOG", "Notifying onPrepareProcessTermination to client(pid: " + clientInfo.pid + ") failed: " + e);
            }
        }
        try {
            this.mCarWatchdogDaemonHelper.tellMediatorAlive(this.mWatchdogClient, clientsNotResponding, sessionId);
        } catch (RemoteException | RuntimeException e2) {
            Log.w("CAR.WATCHDOG", "Cannot respond to car watchdog daemon (sessionId=" + sessionId + "): " + e2);
        }
    }

    private void subscribePowerCycleChange() {
        CarPowerManagementService powerService = (CarPowerManagementService) CarLocalServices.getService(CarPowerManagementService.class);
        if (powerService == null) {
            Log.w("CAR.WATCHDOG", "Cannot get CarPowerManagementService");
        } else {
            powerService.registerListener(new ICarPowerStateListener.Stub() { // from class: com.android.car.watchdog.CarWatchdogService.1
                public void onStateChanged(int state) {
                    int powerCycle;
                    if (state == 6) {
                        powerCycle = 2;
                        CarWatchdogService.this.prepareHealthCheck();
                    } else if (state == 7) {
                        powerCycle = 1;
                    } else {
                        return;
                    }
                    try {
                        CarWatchdogService.this.mCarWatchdogDaemonHelper.notifySystemStateChange(0, powerCycle, -1);
                    } catch (RemoteException | RuntimeException e) {
                        Log.w("CAR.WATCHDOG", "Notifying system state change failed: " + e);
                    }
                }
            });
        }
    }

    private void subscribeUserStateChange() {
        CarUserService userService = (CarUserService) CarLocalServices.getService(CarUserService.class);
        if (userService == null) {
            Log.w("CAR.WATCHDOG", "Cannot get CarUserService");
        } else {
            userService.addUserLifecycleListener(new CarUserManager.UserLifecycleListener() { // from class: com.android.car.watchdog.-$$Lambda$CarWatchdogService$2mXgJXoYgt9P_Tr0C_2WZDShzvY
                public final void onEvent(CarUserManager.UserLifecycleEvent userLifecycleEvent) {
                    this.f$0.lambda$subscribeUserStateChange$1$CarWatchdogService(userLifecycleEvent);
                }
            });
        }
    }

    public /* synthetic */ void lambda$subscribeUserStateChange$1$CarWatchdogService(CarUserManager.UserLifecycleEvent event) {
        int userState;
        int userId = event.getUserHandle().getIdentifier();
        synchronized (this.mLock) {
            int eventType = event.getEventType();
            if (eventType == 1) {
                this.mStoppedUser.delete(userId);
                userState = 0;
            } else {
                if (eventType != 6) {
                    return;
                }
                this.mStoppedUser.put(userId, true);
                userState = 1;
            }
            try {
                this.mCarWatchdogDaemonHelper.notifySystemStateChange(1, userId, userState);
            } catch (RemoteException | RuntimeException e) {
                Log.w("CAR.WATCHDOG", "Notifying system state change failed: " + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void prepareHealthCheck() {
        synchronized (this.mLock) {
            for (int timeout : ALL_TIMEOUTS) {
                SparseArray<ClientInfo> pingedClients = this.mPingedClientMap.get(timeout);
                pingedClients.clear();
            }
        }
    }

    private int[] toIntArray(ArrayList<ClientInfo> list) {
        int size = list.size();
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            intArray[i] = list.get(i).pid;
        }
        return intArray;
    }

    private String timeoutToString(int timeout) {
        if (timeout == 0) {
            return "critical";
        }
        if (timeout == 1) {
            return "moderate";
        }
        if (timeout == 2) {
            return "normal";
        }
        Log.w("CAR.WATCHDOG", "Unknown timeout value");
        return EnvironmentCompat.MEDIA_UNKNOWN;
    }

    private long timeoutToDurationMs(int timeout) {
        if (timeout == 0) {
            return 3000L;
        }
        if (timeout == 1) {
            return 5000L;
        }
        if (timeout == 2) {
            return 10000L;
        }
        Log.w("CAR.WATCHDOG", "Unknown timeout value");
        return 10000L;
    }

    private static final class ICarWatchdogClientImpl extends ICarWatchdogClient.Stub {
        private final WeakReference<CarWatchdogService> mService;

        private ICarWatchdogClientImpl(CarWatchdogService service) {
            this.mService = new WeakReference<>(service);
        }

        @Override // android.automotive.watchdog.ICarWatchdogClient
        public void checkIfAlive(int sessionId, int timeout) {
            CarWatchdogService service = this.mService.get();
            if (service != null) {
                service.postHealthCheckMessage(sessionId);
            } else {
                Log.w("CAR.WATCHDOG", "CarWatchdogService is not available");
            }
        }

        @Override // android.automotive.watchdog.ICarWatchdogClient
        public void prepareProcessTermination() {
            Log.w("CAR.WATCHDOG", "CarWatchdogService is about to be killed by car watchdog daemon");
        }

        @Override // android.automotive.watchdog.ICarWatchdogClient
        public int getInterfaceVersion() {
            return 2;
        }

        @Override // android.automotive.watchdog.ICarWatchdogClient
        public String getInterfaceHash() {
            return "f7adf2ef96b380c7fde3919f565eb764986bdcdd";
        }
    }

    private final class ClientInfo implements IBinder.DeathRecipient {
        public final ICarWatchdogServiceCallback client;
        public final int pid;
        public volatile int sessionId;
        public final int timeout;
        public final int userId;

        private ClientInfo(ICarWatchdogServiceCallback client, int pid, int userId, int timeout) {
            this.client = client;
            this.pid = pid;
            this.userId = userId;
            this.timeout = timeout;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.w("CAR.WATCHDOG", "Client(pid: " + this.pid + ") died");
            CarWatchdogService.this.onClientDeath(this.client, this.timeout);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void linkToDeath() throws RemoteException {
            this.client.asBinder().linkToDeath(this, 0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unlinkToDeath() {
            this.client.asBinder().unlinkToDeath(this, 0);
        }
    }
}
