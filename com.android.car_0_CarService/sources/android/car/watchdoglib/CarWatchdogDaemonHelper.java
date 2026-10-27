package android.car.watchdoglib;

import android.automotive.watchdog.ICarWatchdog;
import android.automotive.watchdog.ICarWatchdogClient;
import android.automotive.watchdog.ICarWatchdogMonitor;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.util.Log;
import com.android.internal.util.function.pooled.PooledLambda;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public final class CarWatchdogDaemonHelper {
    private static final int CAR_WATCHDOG_DAEMON_BIND_MAX_RETRY = 3;
    private static final long CAR_WATCHDOG_DAEMON_BIND_RETRY_INTERVAL_MS = 500;
    private static final long CAR_WATCHDOG_DAEMON_FIND_MARGINAL_TIME_MS = 300;
    private static final String CAR_WATCHDOG_DAEMON_INTERFACE = "android.automotive.watchdog.ICarWatchdog/default";
    private static final String TAG = CarWatchdogDaemonHelper.class.getSimpleName();
    private ICarWatchdog mCarWatchdogDaemon;
    private boolean mConnectionInProgress;
    private final CopyOnWriteArrayList<OnConnectionChangeListener> mConnectionListeners;
    private final IBinder.DeathRecipient mDeathRecipient;
    private final Handler mHandler;
    private final Object mLock;
    private final String mTag;

    /* JADX INFO: Access modifiers changed from: private */
    interface Invokable {
        void invoke(ICarWatchdog iCarWatchdog) throws RemoteException;
    }

    public interface OnConnectionChangeListener {
        void onConnectionChange(boolean z);
    }

    /* JADX INFO: renamed from: android.car.watchdoglib.CarWatchdogDaemonHelper$1, reason: invalid class name */
    class AnonymousClass1 implements IBinder.DeathRecipient {
        AnonymousClass1() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.w(CarWatchdogDaemonHelper.this.mTag, "Car watchdog daemon died: reconnecting");
            CarWatchdogDaemonHelper.this.unlinkToDeath();
            synchronized (CarWatchdogDaemonHelper.this.mLock) {
                CarWatchdogDaemonHelper.this.mCarWatchdogDaemon = null;
            }
            for (OnConnectionChangeListener listener : CarWatchdogDaemonHelper.this.mConnectionListeners) {
                listener.onConnectionChange(false);
            }
            CarWatchdogDaemonHelper.this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$1$dQe2XeIf0fk4FEo9B1-bqcZTp3g
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((CarWatchdogDaemonHelper) obj).connectToDaemon(((Integer) obj2).intValue());
                }
            }, CarWatchdogDaemonHelper.this, 3), CarWatchdogDaemonHelper.CAR_WATCHDOG_DAEMON_BIND_RETRY_INTERVAL_MS);
        }
    }

    public CarWatchdogDaemonHelper() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mConnectionListeners = new CopyOnWriteArrayList<>();
        this.mLock = new Object();
        this.mDeathRecipient = new AnonymousClass1();
        this.mTag = TAG;
    }

    public CarWatchdogDaemonHelper(String requestor) {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mConnectionListeners = new CopyOnWriteArrayList<>();
        this.mLock = new Object();
        this.mDeathRecipient = new AnonymousClass1();
        this.mTag = TAG + "[" + requestor + "]";
    }

    public void connect() {
        synchronized (this.mLock) {
            if (this.mCarWatchdogDaemon == null && !this.mConnectionInProgress) {
                this.mConnectionInProgress = true;
                connectToDaemon(3);
            }
        }
    }

    public void disconnect() {
        unlinkToDeath();
        synchronized (this.mLock) {
            this.mCarWatchdogDaemon = null;
        }
    }

    public void addOnConnectionChangeListener(OnConnectionChangeListener listener) {
        Objects.requireNonNull(listener, "Listener cannot be null");
        this.mConnectionListeners.add(listener);
    }

    public void removeOnConnectionChangeListener(OnConnectionChangeListener listener) {
        Objects.requireNonNull(listener, "Listener cannot be null");
        this.mConnectionListeners.remove(listener);
    }

    public void registerClient(final ICarWatchdogClient client, final int timeout) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$fH2RLa72Wwqv76JxPZZxsilDQlM
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.registerClient(client, timeout);
            }
        });
    }

    public void unregisterClient(final ICarWatchdogClient client) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$kYwuja7m5F8ofMA9kMnM4vhSWNY
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.unregisterClient(client);
            }
        });
    }

    public void registerMediator(final ICarWatchdogClient mediator) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$ECY8kaXl5RSrqcjk92QECbqrHok
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.registerMediator(mediator);
            }
        });
    }

    public void unregisterMediator(final ICarWatchdogClient mediator) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$eLiOK0KsMpzGskQMQ14AUHMfPmg
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.unregisterMediator(mediator);
            }
        });
    }

    public void registerMonitor(final ICarWatchdogMonitor monitor) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$RtleZPAiJypbEI2qtuzP7brGCso
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.registerMonitor(monitor);
            }
        });
    }

    public void unregisterMonitor(final ICarWatchdogMonitor monitor) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$FiyN422j25yvMGRKbGaZh50oqrI
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.unregisterMonitor(monitor);
            }
        });
    }

    public void tellClientAlive(final ICarWatchdogClient client, final int sessionId) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$4xUlatZgUmVyhcBcJ2Uqou_KOo8
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.tellClientAlive(client, sessionId);
            }
        });
    }

    public void tellMediatorAlive(final ICarWatchdogClient mediator, final int[] clientsNotResponding, final int sessionId) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$xN2E4aEnSJ_UnruYvNWbEW8FvIA
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.tellMediatorAlive(mediator, clientsNotResponding, sessionId);
            }
        });
    }

    public void tellDumpFinished(final ICarWatchdogMonitor monitor, final int pid) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$HroOMYaTkzm01JZm6xZVbKhKsXA
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.tellDumpFinished(monitor, pid);
            }
        });
    }

    public void notifySystemStateChange(final int type, final int arg1, final int arg2) throws RemoteException {
        invokeDaemonMethod(new Invokable() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$bxAQginoNAqieINzuL7UrxJAM-k
            @Override // android.car.watchdoglib.CarWatchdogDaemonHelper.Invokable
            public final void invoke(ICarWatchdog iCarWatchdog) throws RemoteException {
                iCarWatchdog.notifySystemStateChange(type, arg1, arg2);
            }
        });
    }

    private void invokeDaemonMethod(Invokable r) throws RemoteException {
        ICarWatchdog daemon;
        synchronized (this.mLock) {
            if (this.mCarWatchdogDaemon == null) {
                throw new IllegalStateException("Car watchdog daemon is not connected");
            }
            daemon = this.mCarWatchdogDaemon;
        }
        r.invoke(daemon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectToDaemon(int retryCount) {
        if (retryCount <= 0) {
            synchronized (this.mLock) {
                this.mConnectionInProgress = false;
            }
            Log.e(this.mTag, "Cannot reconnect to car watchdog daemon after retrying 3 times");
            return;
        }
        if (makeBinderConnection()) {
            Log.i(this.mTag, "Connected to car watchdog daemon");
        } else {
            this.mHandler.sendMessageDelayed(PooledLambda.obtainMessage(new BiConsumer() { // from class: android.car.watchdoglib.-$$Lambda$CarWatchdogDaemonHelper$EIwulEYSZQ0rWYDuwE8Ozat1YtQ
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((CarWatchdogDaemonHelper) obj).connectToDaemon(((Integer) obj2).intValue());
                }
            }, this, Integer.valueOf(retryCount - 1)), CAR_WATCHDOG_DAEMON_BIND_RETRY_INTERVAL_MS);
        }
    }

    private boolean makeBinderConnection() {
        long currentTimeMs = SystemClock.uptimeMillis();
        IBinder binder = ServiceManager.getService(CAR_WATCHDOG_DAEMON_INTERFACE);
        if (binder == null) {
            Log.w(this.mTag, "Getting car watchdog daemon binder failed");
            return false;
        }
        long elapsedTimeMs = SystemClock.uptimeMillis() - currentTimeMs;
        if (elapsedTimeMs > CAR_WATCHDOG_DAEMON_FIND_MARGINAL_TIME_MS) {
            Log.wtf(this.mTag, "Finding car watchdog daemon took too long(" + elapsedTimeMs + "ms)");
        }
        ICarWatchdog daemon = ICarWatchdog.Stub.asInterface(binder);
        if (daemon == null) {
            Log.w(this.mTag, "Getting car watchdog daemon interface failed");
            return false;
        }
        synchronized (this.mLock) {
            this.mCarWatchdogDaemon = daemon;
            this.mConnectionInProgress = false;
        }
        linkToDeath();
        for (OnConnectionChangeListener listener : this.mConnectionListeners) {
            listener.onConnectionChange(true);
        }
        return true;
    }

    private void linkToDeath() {
        synchronized (this.mLock) {
            if (this.mCarWatchdogDaemon == null) {
                return;
            }
            IBinder binder = this.mCarWatchdogDaemon.asBinder();
            if (binder == null) {
                Log.w(this.mTag, "Linking to binder death recipient skipped");
                return;
            }
            try {
                binder.linkToDeath(this.mDeathRecipient, 0);
            } catch (RemoteException e) {
                Log.w(this.mTag, "Linking to binder death recipient failed: " + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unlinkToDeath() {
        synchronized (this.mLock) {
            if (this.mCarWatchdogDaemon == null) {
                return;
            }
            IBinder binder = this.mCarWatchdogDaemon.asBinder();
            if (binder == null) {
                Log.w(this.mTag, "Unlinking from binder death recipient skipped");
            } else {
                binder.unlinkToDeath(this.mDeathRecipient, 0);
            }
        }
    }
}
