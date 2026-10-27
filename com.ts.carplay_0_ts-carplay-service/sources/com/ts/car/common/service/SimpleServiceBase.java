package com.ts.car.common.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserManager;
import android.util.Log;
import com.ts.car.common.listener.LocalListenerHelper;
import com.ts.car.core.ServiceProvider;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import ts.car.service.IPlatformService;
import ts.car.service.IServiceConnectionListener;
import ts.car.service.core.ICoreSystemService;
import ts.car.service.core.IServiceStateListener;

/* JADX INFO: loaded from: classes.dex */
public abstract class SimpleServiceBase implements IPlatformService {
    private static final int MAX_RETRY_BINDSERVICE = 100;
    private static final int MESSAGE_CORE_GET_SERVICE = 4;
    private static final int MESSAGE_LISTENER_ADDED = 3;
    private static final int MESSAGE_RETRY_BIND_SERVICE = 6;
    private static final int MESSAGE_SERVICE_AUTO_RESTART = 5;
    private static final int MESSAGE_SERVICE_CONNECTED = 1;
    private static final int MESSAGE_SERVICE_DISCONNECTED = 2;
    private static final int RANDOM_RETRY_INTERVAL = 500;
    private static final int RETRY_BINDSERVICE_INTERVAL = 1000;
    private static final String TAG = SimpleServiceBase.class.getSimpleName();
    private int mBindCount;
    private String mComponentName;
    protected Context mContext;
    private boolean mCoreEmbedded;
    private ICoreSystemService mCoreServer;
    private IServiceConnectionListener mCoreServiceConnectionListener;
    protected HelperHandler mHandler;
    protected boolean mIsBinding;
    private LocalListenerHelper<IServiceConnectionListener> mListenerHelper;
    protected boolean mManualDisconnected;
    private IBinder mRemoteService;
    private ServiceDeathListener mServiceDeathListener;
    private String mServiceName;
    protected HandlerThread mThread;
    protected ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.ts.car.common.service.SimpleServiceBase.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (SimpleServiceBase.this.mHandler.hasMessages(6)) {
                SimpleServiceBase.this.mHandler.removeMessages(6);
            }
            synchronized (SimpleServiceBase.this) {
                SimpleServiceBase.this.mRemoteService = iBinder;
                SimpleServiceBase.this.mIsBinding = false;
            }
            try {
                SimpleServiceBase.this.mRemoteService.linkToDeath(SimpleServiceBase.this.mServiceDeathListener, 0);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
            SimpleServiceBase.this.mHandler.sendEmptyMessage(1);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            synchronized (SimpleServiceBase.this) {
                try {
                    try {
                    } finally {
                    }
                } catch (NoSuchElementException ex) {
                    Log.w(SimpleServiceBase.TAG, "unlinkToDeath error, remote error.", ex);
                }
                if (SimpleServiceBase.this.mRemoteService == null) {
                    SimpleServiceBase.this.mRemoteService = null;
                    SimpleServiceBase.this.mIsBinding = false;
                } else {
                    SimpleServiceBase.this.mRemoteService.unlinkToDeath(SimpleServiceBase.this.mServiceDeathListener, 0);
                    SimpleServiceBase.this.mRemoteService = null;
                    SimpleServiceBase.this.mIsBinding = false;
                }
            }
            SimpleServiceBase.this.mHandler.sendEmptyMessage(2);
        }
    };
    private IServiceStateListener mServiceStateListener = new IServiceStateListener() { // from class: com.ts.car.common.service.SimpleServiceBase.2
        @Override // ts.car.service.core.IServiceStateListener
        public void onServiceStarted(String serviceName) {
            if (SimpleServiceBase.this.mHandler.hasMessages(6)) {
                SimpleServiceBase.this.mHandler.removeMessages(6);
            }
            synchronized (SimpleServiceBase.this) {
                if (SimpleServiceBase.this.mManualDisconnected) {
                    return;
                }
                if (serviceName != null && serviceName.equals(SimpleServiceBase.this.mServiceName) && SimpleServiceBase.this.mRemoteService == null) {
                    SimpleServiceBase.this.mIsBinding = true;
                    SimpleServiceBase.this.mHandler.sendEmptyMessage(4);
                }
            }
        }

        @Override // ts.car.service.core.IServiceStateListener
        public void onServiceStopped(String serviceName) {
            if (SimpleServiceBase.this.mHandler.hasMessages(6)) {
                SimpleServiceBase.this.mHandler.removeMessages(6);
            }
            synchronized (SimpleServiceBase.this) {
                if (serviceName != null) {
                    if (serviceName.equals(SimpleServiceBase.this.mServiceName)) {
                        SimpleServiceBase.this.mRemoteService = null;
                        SimpleServiceBase.this.mHandler.sendEmptyMessage(2);
                    }
                }
            }
        }
    };

    protected abstract void onServiceConnected(IBinder iBinder);

    protected abstract void onServiceDied();

    public SimpleServiceBase(Context context, String componentName, String serviceName, IBinder binder) {
        this.mContext = context.getApplicationContext();
        this.mComponentName = componentName;
        this.mServiceName = serviceName;
        this.mRemoteService = binder;
        Log.d(TAG, "SimpleServiceBase init: mComponentName=" + this.mComponentName + ", mServiceName=" + this.mServiceName + ", mRemoteService=" + this.mRemoteService);
        this.mListenerHelper = new LocalListenerHelper<>();
        this.mCoreEmbedded = false;
        this.mServiceDeathListener = new ServiceDeathListener();
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.mThread = handlerThread;
        handlerThread.start();
        this.mHandler = new HelperHandler(this.mThread.getLooper());
        this.mCoreServiceConnectionListener = new CoreServiceConnectionListener();
        if (serviceName != null && !serviceName.isEmpty()) {
            this.mCoreServer = (ICoreSystemService) ServiceProvider.get(ICoreSystemService.class, context);
        }
        ICoreSystemService iCoreSystemService = this.mCoreServer;
        if (iCoreSystemService == null) {
            Log.d(TAG, "SimpleServiceBase: mCoreServer is null");
            this.mHandler.post(new $$Lambda$0zn1uzYlauhYhWU5MM7Edafyqys(this));
            return;
        }
        boolean zIsServiceSupport = iCoreSystemService.isServiceSupport(serviceName);
        this.mCoreEmbedded = zIsServiceSupport;
        if (zIsServiceSupport) {
            this.mCoreServer.registerServiceStateListener(this.mServiceStateListener);
            this.mCoreServer.registerConnectionListener(this.mCoreServiceConnectionListener);
        } else if (this.mRemoteService == null) {
            this.mHandler.post(new $$Lambda$0zn1uzYlauhYhWU5MM7Edafyqys(this));
        }
    }

    public boolean bindService() {
        boolean bindSuccess;
        synchronized (this) {
            if (this.mRemoteService != null) {
                return true;
            }
            if (this.mIsBinding) {
                return false;
            }
            this.mIsBinding = true;
            this.mManualDisconnected = false;
            if (!this.mCoreEmbedded) {
                Intent intent = new Intent();
                intent.setComponent(ComponentName.unflattenFromString(this.mComponentName));
                UserManager userManager = (UserManager) this.mContext.getSystemService("user");
                boolean isUserUnlocked = userManager.isUserUnlocked();
                boolean permissionCheckPass = true;
                try {
                    bindSuccess = bindServiceInternal(intent, this.mServiceConnection);
                } catch (SecurityException ex) {
                    permissionCheckPass = false;
                    Log.w(TAG, "bindService got permission about error.", ex);
                    bindSuccess = false;
                } catch (Throwable ex2) {
                    Log.w(TAG, "bindService error.", ex2);
                    bindSuccess = false;
                }
                if (!bindSuccess) {
                    this.mIsBinding = false;
                    Log.w(TAG, "bindService failed, current app:" + this.mContext.getPackageName() + ", mServiceName:" + this.mServiceName + ", mComponentName:" + this.mComponentName);
                    if (this.mBindCount < 100) {
                        this.mBindCount++;
                        Log.w(TAG, "bindService: retry bind service:" + this.mComponentName);
                        if (this.mHandler.hasMessages(6)) {
                            this.mHandler.removeMessages(6);
                        }
                        this.mHandler.sendEmptyMessageDelayed(6, 1000L);
                        return false;
                    }
                    if (permissionCheckPass && isUserUnlocked) {
                        Log.w(TAG, "bindService failed, current app:" + this.mContext.getPackageName() + ", mServiceName:" + this.mServiceName + ", mComponentName:" + this.mComponentName + ", target package/component may not exit, or package/component is disabled or hided");
                        return false;
                    }
                    this.mHandler.postDelayed(new $$Lambda$0zn1uzYlauhYhWU5MM7Edafyqys(this), 500L);
                }
                return bindSuccess;
            }
            this.mCoreServer.registerServiceStateListener(this.mServiceStateListener);
            this.mCoreServer.registerConnectionListener(this.mCoreServiceConnectionListener);
            return true;
        }
    }

    protected boolean bindServiceInternal(Intent intent, ServiceConnection serviceConnection) {
        return this.mContext.bindService(intent, serviceConnection, 1);
    }

    public boolean unbindService() {
        synchronized (this) {
            if (this.mRemoteService == null) {
                return true;
            }
            if (this.mIsBinding) {
                return false;
            }
            this.mManualDisconnected = true;
            if (!this.mCoreEmbedded) {
                this.mRemoteService.unlinkToDeath(this.mServiceDeathListener, 0);
                this.mContext.unbindService(this.mServiceConnection);
                this.mRemoteService = null;
                return true;
            }
            this.mCoreServer.unregisterServiceStateListener(this.mServiceStateListener);
            this.mRemoteService.unlinkToDeath(this.mServiceDeathListener, 0);
            this.mHandler.sendEmptyMessage(2);
            return false;
        }
    }

    @Override // ts.car.service.IPlatformService
    public boolean isServiceConnected() {
        return this.mRemoteService != null;
    }

    @Override // ts.car.service.IPlatformService
    public boolean registerConnectionListener(IServiceConnectionListener listener) {
        boolean res = this.mListenerHelper.addListener(listener);
        if (listener != null && isServiceConnected()) {
            Message message = this.mHandler.obtainMessage(3);
            message.obj = listener;
            message.sendToTarget();
        }
        return res;
    }

    @Override // ts.car.service.IPlatformService
    public boolean unregisterConnectionListener(IServiceConnectionListener listener) {
        return this.mListenerHelper.removeListener(listener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    class HelperHandler extends Handler {
        public HelperHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            IBinder service;
            IBinder service2;
            switch (msg.what) {
                case 1:
                    Log.d(SimpleServiceBase.TAG, "MESSAGE_SERVICE_CONNECTED: mRemoteService=" + SimpleServiceBase.this.mRemoteService);
                    synchronized (SimpleServiceBase.this) {
                        service = SimpleServiceBase.this.mRemoteService;
                        break;
                    }
                    if (service == null) {
                        return;
                    }
                    SimpleServiceBase.this.onServiceConnected(service);
                    SimpleServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.car.common.service.-$$Lambda$SimpleServiceBase$HelperHandler$huqVJUzAr_Wm0Z9Y51XhTrh-evo
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            this.f$0.lambda$handleMessage$0$SimpleServiceBase$HelperHandler((IServiceConnectionListener) obj);
                        }
                    });
                    return;
                case 2:
                    SimpleServiceBase.this.onServiceDied();
                    SimpleServiceBase.this.mListenerHelper.notifyListener(new Consumer() { // from class: com.ts.car.common.service.-$$Lambda$SimpleServiceBase$HelperHandler$5X7v-ztCsDAjKNFSe1eJ_IqFKOM
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            this.f$0.lambda$handleMessage$1$SimpleServiceBase$HelperHandler((IServiceConnectionListener) obj);
                        }
                    });
                    if (!SimpleServiceBase.this.mManualDisconnected) {
                        int randTime = (int) (Math.random() * 500.0d);
                        SimpleServiceBase.this.mHandler.sendEmptyMessageDelayed(5, randTime);
                        return;
                    }
                    return;
                case 3:
                    IServiceConnectionListener listener = (IServiceConnectionListener) msg.obj;
                    Log.d(SimpleServiceBase.TAG, "MESSAGE_LISTENER_ADDED: mRemoteService=" + SimpleServiceBase.this.mRemoteService);
                    synchronized (SimpleServiceBase.this) {
                        service2 = SimpleServiceBase.this.mRemoteService;
                        break;
                    }
                    if (service2 != null && SimpleServiceBase.this.mListenerHelper.containsListener(listener)) {
                        listener.onServiceConnected(SimpleServiceBase.this);
                        return;
                    }
                    return;
                case 4:
                    Log.d(SimpleServiceBase.TAG, "MESSAGE_CORE_GET_SERVICE: !!!");
                    synchronized (SimpleServiceBase.this) {
                        if (SimpleServiceBase.this.mIsBinding) {
                            IBinder service3 = SimpleServiceBase.this.mCoreServer.getService(SimpleServiceBase.this.mServiceName);
                            synchronized (SimpleServiceBase.this) {
                                SimpleServiceBase.this.mIsBinding = false;
                                if (service3 == null) {
                                    Log.w(SimpleServiceBase.TAG, "getService null from CoreSystemServer, mServiceName:" + SimpleServiceBase.this.mServiceName);
                                    return;
                                }
                                SimpleServiceBase.this.mRemoteService = service3;
                                try {
                                    SimpleServiceBase.this.mRemoteService.linkToDeath(SimpleServiceBase.this.mServiceDeathListener, 0);
                                    break;
                                } catch (RemoteException ex) {
                                    ex.printStackTrace();
                                }
                                SimpleServiceBase.this.mHandler.sendEmptyMessage(1);
                                return;
                            }
                        }
                        return;
                    }
                case 5:
                    if (SimpleServiceBase.this.mIsBinding || SimpleServiceBase.this.mManualDisconnected) {
                        return;
                    }
                    SimpleServiceBase.this.bindService();
                    return;
                case 6:
                    if (SimpleServiceBase.this.mServiceName == null || SimpleServiceBase.this.mServiceName.isEmpty()) {
                        Log.w(SimpleServiceBase.TAG, "retryBindService mServiceName is null");
                    }
                    Log.w(SimpleServiceBase.TAG, "retryBindService try to get service:" + SimpleServiceBase.this.mServiceName);
                    SimpleServiceBase simpleServiceBase = SimpleServiceBase.this;
                    simpleServiceBase.mCoreServer = (ICoreSystemService) ServiceProvider.get(ICoreSystemService.class, simpleServiceBase.mContext);
                    if (SimpleServiceBase.this.mCoreServer == null) {
                        Log.w(SimpleServiceBase.TAG, "mCoreServer still null, retry bindService");
                        SimpleServiceBase.this.bindService();
                        return;
                    }
                    IBinder service4 = SimpleServiceBase.this.mCoreServer.getService(SimpleServiceBase.this.mServiceName);
                    if (service4 == null) {
                        Log.w(SimpleServiceBase.TAG, "getService null from CoreSystemServer, mServiceName:" + SimpleServiceBase.this.mServiceName);
                        SimpleServiceBase.this.bindService();
                        return;
                    }
                    SimpleServiceBase.this.mRemoteService = service4;
                    try {
                        SimpleServiceBase.this.mRemoteService.linkToDeath(SimpleServiceBase.this.mServiceDeathListener, 0);
                        break;
                    } catch (RemoteException ex2) {
                        ex2.printStackTrace();
                    }
                    SimpleServiceBase.this.mHandler.sendEmptyMessage(1);
                    return;
                default:
                    Log.w(SimpleServiceBase.TAG, "unrecognized handler what:" + msg.what);
                    return;
            }
        }

        public /* synthetic */ void lambda$handleMessage$0$SimpleServiceBase$HelperHandler(IServiceConnectionListener listener) {
            listener.onServiceConnected(SimpleServiceBase.this);
        }

        public /* synthetic */ void lambda$handleMessage$1$SimpleServiceBase$HelperHandler(IServiceConnectionListener listener) {
            listener.onServiceDisconnected(SimpleServiceBase.this);
        }
    }

    class ServiceDeathListener implements IBinder.DeathRecipient {
        ServiceDeathListener() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            synchronized (SimpleServiceBase.this) {
                if (SimpleServiceBase.this.mRemoteService != null) {
                    SimpleServiceBase.this.mRemoteService.unlinkToDeath(this, 0);
                    SimpleServiceBase.this.mRemoteService = null;
                }
                SimpleServiceBase.this.mIsBinding = false;
            }
            SimpleServiceBase.this.mHandler.sendEmptyMessage(2);
        }
    }

    class CoreServiceConnectionListener implements IServiceConnectionListener {
        CoreServiceConnectionListener() {
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceConnected(IPlatformService service) {
            if (SimpleServiceBase.this.mHandler.hasMessages(6)) {
                SimpleServiceBase.this.mHandler.removeMessages(6);
            }
            synchronized (SimpleServiceBase.this) {
                SimpleServiceBase.this.mIsBinding = true;
            }
            SimpleServiceBase.this.mHandler.sendEmptyMessage(4);
        }

        @Override // ts.car.service.IServiceConnectionListener
        public void onServiceDisconnected(IPlatformService service) {
            synchronized (SimpleServiceBase.this) {
                SimpleServiceBase.this.mIsBinding = false;
            }
        }
    }
}
