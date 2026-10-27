package com.ts.car.common.service;

import android.content.Context;
import android.hidl.base.V1_0.IBase;
import android.hidl.manager.V1_0.IServiceManager;
import android.hidl.manager.V1_0.IServiceNotification;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IHwBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.ts.car.common.listener.LocalListenerHelper;
import java.util.function.Consumer;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class SimpleHalBase<T extends IBase> implements IPlatformService {
    private static final int MESSAGE_LISTENER_ADDED = 3;
    private static final int MESSAGE_SERVICE_AUTO_RESTART = 4;
    private static final int MESSAGE_SERVICE_CONNECTED = 1;
    private static final int MESSAGE_SERVICE_DISCONNECTED = 2;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final String TAG = SimpleHalBase.class.getSimpleName();
    protected Context mContext;
    private T mHalService;
    protected SimpleHalBase<T>.HelperHandler mHandler;
    private boolean mIsBinding;
    private LocalListenerHelper<IServiceConnectionListener> mListenerHelper;
    private SimpleHalBase<T>.HwSmDeathListener mManagerDeathListener;
    private boolean mManualDisconnected;
    private SimpleHalBase<T>.ServiceDeathListener mServiceDeathListener;
    private IServiceManager mServiceManager;
    private String mServiceName;
    private SimpleHalBase<T>.HwServiceNotification mServiceNotification;
    protected HandlerThread mThread;

    public abstract T getHwService() throws RemoteException;

    public abstract void onHalConnected(T t);

    public abstract void onHalDisconnected();

    public SimpleHalBase(Context context, String serviceName) {
        this.mContext = context;
        this.mServiceName = serviceName;
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mListenerHelper = new LocalListenerHelper<>();
        this.mServiceDeathListener = new ServiceDeathListener();
        this.mManagerDeathListener = new HwSmDeathListener();
        this.mServiceNotification = new HwServiceNotification();
        this.mListenerHelper = new LocalListenerHelper<>();
        SimpleHalBase<T>.HelperHandler helperHandler = new HelperHandler(this.mThread.getLooper());
        this.mHandler = helperHandler;
        helperHandler.post(new Runnable() { // from class: com.ts.car.common.service.-$$Lambda$Yf-B759qlo36li0uOlal3leLGXs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.connectHal();
            }
        });
    }

    public boolean connectHal() {
        synchronized (this) {
            if (this.mHalService != null) {
                return true;
            }
            if (this.mIsBinding) {
                return false;
            }
            this.mIsBinding = true;
            this.mManualDisconnected = false;
            if (initServiceManager() && isServiceManagerSupport()) {
                return initHalService();
            }
            return false;
        }
    }

    public boolean disconnectHal() {
        synchronized (this) {
            if (this.mHalService == null) {
                return true;
            }
            if (this.mIsBinding) {
                return false;
            }
            this.mManualDisconnected = true;
            try {
                this.mHalService.unlinkToDeath(this.mServiceDeathListener);
                this.mHalService = null;
                this.mHandler.sendEmptyMessage(2);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    private boolean initServiceManager() {
        synchronized (this) {
            if (this.mServiceManager != null) {
                return true;
            }
            try {
                IServiceManager service = IServiceManager.getService();
                this.mServiceManager = service;
                if (service == null) {
                    return false;
                }
                if (!service.linkToDeath(this.mManagerDeathListener, 0L)) {
                    Log.w(TAG, "Error on linkToDeath on IServiceManager");
                    this.mServiceManager = null;
                }
                if (this.mServiceManager == null) {
                    return false;
                }
                if (!this.mServiceManager.registerForNotifications(this.mServiceName, "", this.mServiceNotification)) {
                    this.mServiceManager = null;
                }
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            return this.mServiceManager != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initHalService() {
        T t;
        synchronized (this) {
            if (this.mHalService != null) {
                return true;
            }
            try {
                t = (T) getHwService();
                this.mHalService = t;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (t == null) {
                return false;
            }
            if (!t.linkToDeath(this.mServiceDeathListener, 0L)) {
                this.mHalService = null;
                return false;
            }
            this.mHandler.sendEmptyMessage(1);
            return this.mHalService != null;
        }
    }

    private boolean isServiceManagerSupport() {
        synchronized (this) {
            if (this.mServiceManager == null) {
                return false;
            }
            try {
                return this.mServiceManager.getTransport(this.mServiceName, "default") != 0;
            } catch (RemoteException e) {
                return false;
            }
        }
    }

    @Override // ts.car.service.IPlatformService
    public boolean isServiceConnected() {
        return this.mHalService != null;
    }

    @Override // ts.car.service.IPlatformService
    public boolean registerConnectionListener(IServiceConnectionListener listener) {
        if (!this.mListenerHelper.addListener(listener)) {
            return false;
        }
        Message msg = Message.obtain(this.mHandler, 3, listener);
        msg.sendToTarget();
        return true;
    }

    @Override // ts.car.service.IPlatformService
    public boolean unregisterConnectionListener(IServiceConnectionListener listener) {
        return this.mListenerHelper.removeListener(listener);
    }

    class ServiceDeathListener implements IHwBinder.DeathRecipient {
        ServiceDeathListener() {
        }

        public void serviceDied(long cookie) {
            synchronized (SimpleHalBase.this) {
                SimpleHalBase.this.mHalService = null;
                SimpleHalBase.this.mHandler.sendEmptyMessage(2);
            }
        }
    }

    class HwSmDeathListener implements IHwBinder.DeathRecipient {
        HwSmDeathListener() {
        }

        public void serviceDied(long cookie) {
            synchronized (SimpleHalBase.this) {
                SimpleHalBase.this.mServiceManager = null;
            }
        }
    }

    class HwServiceNotification extends IServiceNotification.Stub {
        HwServiceNotification() {
        }

        public void onRegistration(String fqName, String name, boolean preexisting) {
            synchronized (SimpleHalBase.this) {
                if (SimpleHalBase.this.mManualDisconnected) {
                    return;
                }
                SimpleHalBase.this.initHalService();
            }
        }
    }

    public class HelperHandler extends Handler {
        public HelperHandler(Looper looper) {
            super(looper);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            IBase iBase;
            IBase iBase2;
            int i = msg.what;
            if (i == 1) {
                synchronized (SimpleHalBase.this) {
                    iBase = SimpleHalBase.this.mHalService;
                }
                if (iBase == null) {
                    return;
                }
                SimpleHalBase.this.onHalConnected(iBase);
                SimpleHalBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.car.common.service.-$$Lambda$SimpleHalBase$HelperHandler$j2y1XRiT_umO-7xPUc1G-TWIXWY
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$handleMessage$0$SimpleHalBase$HelperHandler((IServiceConnectionListener) obj);
                    }
                });
                return;
            }
            if (i == 2) {
                SimpleHalBase.this.onHalDisconnected();
                SimpleHalBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.car.common.service.-$$Lambda$SimpleHalBase$HelperHandler$T8ejBKCxRKhg9aebXZNtj80-n5o
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$handleMessage$1$SimpleHalBase$HelperHandler((IServiceConnectionListener) obj);
                    }
                });
                if (!SimpleHalBase.this.mManualDisconnected) {
                    int randTime = (int) (Math.random() * 500.0d);
                    SimpleHalBase.this.mHandler.sendEmptyMessageDelayed(4, randTime);
                    return;
                }
                return;
            }
            if (i != 3) {
                if (i == 4 && !SimpleHalBase.this.mIsBinding && !SimpleHalBase.this.mManualDisconnected && !SimpleHalBase.this.connectHal()) {
                    int randTime2 = (int) (Math.random() * 500.0d);
                    SimpleHalBase.this.mHandler.sendEmptyMessageDelayed(4, randTime2);
                    return;
                }
                return;
            }
            IServiceConnectionListener listener = (IServiceConnectionListener) msg.obj;
            synchronized (SimpleHalBase.this) {
                iBase2 = SimpleHalBase.this.mHalService;
            }
            if (iBase2 != null) {
                listener.onServiceConnected(SimpleHalBase.this);
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$SimpleHalBase$HelperHandler(IServiceConnectionListener listener) {
            listener.onServiceConnected(SimpleHalBase.this);
        }

        public /* synthetic */ void lambda$handleMessage$1$SimpleHalBase$HelperHandler(IServiceConnectionListener listener) {
            listener.onServiceDisconnected(SimpleHalBase.this);
        }
    }
}
