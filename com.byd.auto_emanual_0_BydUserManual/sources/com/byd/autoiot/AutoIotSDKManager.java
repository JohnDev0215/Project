package com.byd.autoiot;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import com.byd.autoiot.service.AutoiotProfile;
import com.byd.autoiot.service.IAutoiotEventListener;
import com.byd.autoiot.service.IAutoiotInterface;
import com.byd.autoiot.utils.BydAutoIotSdkLog;
import com.byd.autoiot.utils.HexUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class AutoIotSDKManager {
    private static final String TAG = "AutoIotSDKManager";
    private static AutoIotSDKManager instance;
    private Consumer<Boolean> afterBind;
    private Context mContext;
    private final Handler mHandler;
    private IBinder mIBinder;
    private final Runnable mRunnable;
    private IAutoiotInterface mService;
    private int mConnectCount = 0;
    private volatile boolean isConnected = false;
    private final List<AutoIotEventListener> mAutoIotEventListenerList = new ArrayList();
    private final IBinder.DeathRecipient mDeathRecipient = new AnonymousClass1();
    private final ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.byd.autoiot.AutoIotSDKManager.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onServiceConnected");
            AutoIotSDKManager.this.onConnected(iBinder);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onServiceDisconnected");
            AutoIotSDKManager.this.onDisconnected();
        }
    };
    private final IAutoiotEventListener.Stub mIAutoIotEventListener = new IAutoiotEventListener.Stub() { // from class: com.byd.autoiot.AutoIotSDKManager.3
        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onNewDeviceDiscovered(List<String> list) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onNewDeviceDiscovered " + list);
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onNewDeviceDiscovered mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onNewDeviceDiscovered(list);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onNewDeviceDiscovered mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceLeave(String str) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onDeviceLeave " + str);
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceLeave mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onDeviceLeave(str);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceLeave mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceDeleted(String str) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onDeviceDeleted " + str);
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceDeleted mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onDeviceDeleted(str);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceDeleted mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceConnected(String str) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onDeviceConnected " + str);
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceConnected mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onDeviceConnected(str);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceConnected mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceDisconnected(String str) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onDeviceDisconnected " + str);
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceDisconnected mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onDeviceDisconnected(str);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceDisconnected mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceChanged(String str, String str2, String str3) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onDeviceChanged " + str + "," + str2 + "," + str3);
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceChanged mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onDeviceChanged(str, str2, str3);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceChanged mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onReadResult(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onReadResult " + str + "," + str2 + "," + str3 + "," + HexUtil.byte2Hex((byte[]) Optional.ofNullable(autoiotProfile).map($$Lambda$wxNS_l2z2a1B3tbU95AwvR6zHXM.INSTANCE).orElse(null)));
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onReadResult mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onReadResult(str, str2, str3, autoiotProfile);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onReadResult mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onWriteResult(String str, String str2, String str3, boolean z) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onWriteResult " + str + "," + str2 + "," + str3 + "," + z);
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onWriteResult mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onWriteResult(str, str2, str3, z);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onWriteResult mAutoIotEventListenerList size = 0");
        }

        @Override // com.byd.autoiot.service.IAutoiotEventListener
        public void onDeviceChangedWithData(String str, String str2, String str3, AutoiotProfile autoiotProfile) throws RemoteException {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "onDeviceChangedWithData " + str + "," + str2 + "," + str3 + "," + HexUtil.byte2Hex((byte[]) Optional.ofNullable(autoiotProfile).map($$Lambda$wxNS_l2z2a1B3tbU95AwvR6zHXM.INSTANCE).orElse(null)));
            if (!AutoIotSDKManager.this.mAutoIotEventListenerList.isEmpty()) {
                for (AutoIotEventListener autoIotEventListener : AutoIotSDKManager.this.mAutoIotEventListenerList) {
                    if (autoIotEventListener == null) {
                        BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceChangedWithData mAutoIotEventListener == null");
                    } else {
                        autoIotEventListener.onDeviceChangedWithData(str, str2, str3, autoiotProfile);
                    }
                }
                return;
            }
            BydAutoIotSdkLog.e(AutoIotSDKManager.TAG, "onDeviceChangedWithData mAutoIotEventListenerList size = 0");
        }
    };

    /* JADX INFO: renamed from: com.byd.autoiot.AutoIotSDKManager$1, reason: invalid class name */
    class AnonymousClass1 implements IBinder.DeathRecipient {
        AnonymousClass1() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "binderDied");
            try {
                AutoIotSDKManager.this.mService.asBinder().unlinkToDeath(AutoIotSDKManager.this.mDeathRecipient, 0);
            } catch (Exception e) {
                BydAutoIotSdkLog.i(AutoIotSDKManager.TAG, "binder unlink fail", e);
            }
            AutoIotSDKManager.this.isConnected = false;
            if (AutoIotSDKManager.this.mService == null || AutoIotSDKManager.this.mContext == null) {
                return;
            }
            AutoIotSDKManager.this.mHandler.postDelayed(new Runnable() { // from class: com.byd.autoiot.-$$Lambda$AutoIotSDKManager$1$c3VvF_sYLhshmkJpKiiPeurhTDQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$binderDied$0$AutoIotSDKManager$1();
                }
            }, 3000L);
        }

        public /* synthetic */ void lambda$binderDied$0$AutoIotSDKManager$1() {
            AutoIotSDKManager.this.bindService();
        }
    }

    public static synchronized AutoIotSDKManager getInstance() {
        if (instance == null) {
            instance = new AutoIotSDKManager();
        }
        return instance;
    }

    private AutoIotSDKManager() {
        BydAutoIotSdkLog.i(TAG, "AutoIotSDKManager");
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mRunnable = new Runnable() { // from class: com.byd.autoiot.-$$Lambda$AutoIotSDKManager$QgeJwAt19Afz3jlGHrjZwgvTHD0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$AutoIotSDKManager();
            }
        };
    }

    public /* synthetic */ void lambda$new$0$AutoIotSDKManager() {
        if (this.isConnected) {
            return;
        }
        BydAutoIotSdkLog.i(TAG, "reconnect mConnectCount=" + this.mConnectCount);
        bindService();
        this.mConnectCount++;
    }

    public boolean isAvailable() {
        return (this.mContext == null || this.mService == null || !this.isConnected) ? false : true;
    }

    public void init(Context context, Consumer<Boolean> consumer) {
        BydAutoIotSdkLog.i(TAG, "init context");
        this.mContext = context.getApplicationContext();
        this.afterBind = consumer;
        bindService();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindService() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "bindService isConnected =" + this.isConnected);
        if (this.isConnected) {
            return;
        }
        if (this.mContext == null) {
            BydAutoIotSdkLog.e(str, "bindService mContext == null");
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setPackage("com.byd.autoiot.service");
            intent.setAction("autoiot.service");
            BydAutoIotSdkLog.i(str, "bindService state=" + this.mContext.bindService(intent, this.serviceConnection, 1));
            this.mHandler.removeCallbacks(this.mRunnable);
            this.mHandler.postDelayed(this.mRunnable, 5000L);
        } catch (Exception e) {
            BydAutoIotSdkLog.e(TAG, "bindService: Exception" + e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onConnected(IBinder iBinder) {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "onConnected");
        try {
            this.mIBinder = iBinder;
            this.isConnected = true;
            this.mService = null;
            getService();
            if (this.mService != null) {
                iBinder.linkToDeath(this.mDeathRecipient, 0);
            }
            try {
                if (isAvailable()) {
                    this.mService.registerListener(this.mIAutoIotEventListener);
                } else {
                    BydAutoIotSdkLog.e(str, "Sdk service register listener fail, isAvailable = " + isAvailable() + "  getResult = " + getResult());
                }
            } catch (RemoteException e) {
                BydAutoIotSdkLog.e(TAG, "Sdk service register listener fail, isAvailable = " + isAvailable(), e);
            }
            Consumer<Boolean> consumer = this.afterBind;
            if (consumer != null) {
                consumer.accept(true);
            }
        } catch (Exception e2) {
            BydAutoIotSdkLog.e(TAG, "onConnected: Exception" + e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void onDisconnected() {
        BydAutoIotSdkLog.i(TAG, "onDisconnected");
        this.isConnected = false;
        this.mConnectCount = 0;
    }

    private void getService() {
        if (this.mService == null) {
            this.mService = IAutoiotInterface.Stub.asInterface(this.mIBinder);
        }
        BydAutoIotSdkLog.i(TAG, "getService service=" + this.mService);
    }

    private int getResult() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "getResult");
        if (this.mContext == null) {
            BydAutoIotSdkLog.e(str, "REQUEST_FAILED_NEED_INIT");
            return -1;
        }
        if (!this.isConnected) {
            BydAutoIotSdkLog.e(str, "REQUEST_FAILED_NOT_CONNECTED");
            this.mConnectCount = 0;
            bindService();
            return -2;
        }
        getService();
        if (this.mService != null) {
            return 200;
        }
        BydAutoIotSdkLog.e(str, "REQUEST_FAILED_SERVICE_ERROR");
        return -3;
    }

    public void unbindService() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "unbindService");
        Context context = this.mContext;
        if (context == null) {
            BydAutoIotSdkLog.e(str, "unbindService this.mContext == null");
            return;
        }
        context.unbindService(this.serviceConnection);
        this.mContext = null;
        this.mService = null;
        this.mConnectCount = 0;
        this.isConnected = false;
    }

    public boolean startScan() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "startScan");
        try {
            if (isAvailable()) {
                return this.mService.startScan();
            }
            BydAutoIotSdkLog.e(str, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean stopScan() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "stopScan");
        try {
            if (isAvailable()) {
                return this.mService.stopScan();
            }
            BydAutoIotSdkLog.e(str, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readBasicInformation() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "readBasicInformation");
        try {
            if (isAvailable()) {
                return this.mService.readBasicInformation();
            }
            BydAutoIotSdkLog.e(str, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean connectTo(String str) {
        String str2 = TAG;
        BydAutoIotSdkLog.i(str2, "connectTo " + str);
        try {
            if (isAvailable()) {
                return this.mService.connectTo(str);
            }
            BydAutoIotSdkLog.e(str2, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotWrite(AutoiotProfile autoiotProfile) {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "autoiotWrite " + HexUtil.byte2Hex((byte[]) Optional.ofNullable(autoiotProfile).map($$Lambda$wxNS_l2z2a1B3tbU95AwvR6zHXM.INSTANCE).orElse(null)));
        try {
            if (isAvailable()) {
                return this.mService.autoiotWrite(autoiotProfile);
            }
            BydAutoIotSdkLog.e(str, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotWriteDev(AutoiotProfile autoiotProfile, String str) {
        String str2 = TAG;
        BydAutoIotSdkLog.i(str2, "autoiotWriteDev " + str + "," + HexUtil.byte2Hex((byte[]) Optional.ofNullable(autoiotProfile).map($$Lambda$wxNS_l2z2a1B3tbU95AwvR6zHXM.INSTANCE).orElse(null)));
        try {
            if (isAvailable()) {
                return this.mService.autoiotWriteDev(autoiotProfile, str);
            }
            BydAutoIotSdkLog.e(str2, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotWriteSrv(AutoiotProfile autoiotProfile, String str, String str2) {
        String str3 = TAG;
        BydAutoIotSdkLog.i(str3, "autoiotWriteSrv " + str + "," + str2 + "," + HexUtil.byte2Hex((byte[]) Optional.ofNullable(autoiotProfile).map($$Lambda$wxNS_l2z2a1B3tbU95AwvR6zHXM.INSTANCE).orElse(null)));
        try {
            if (isAvailable()) {
                return this.mService.autoiotWriteSrv(autoiotProfile, str, str2);
            }
            BydAutoIotSdkLog.e(str3, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotWriteChr(AutoiotProfile autoiotProfile, String str, String str2, String str3) {
        String str4 = TAG;
        BydAutoIotSdkLog.i(str4, "autoiotWriteChr " + str + "," + str2 + "," + str3 + "," + HexUtil.byte2Hex((byte[]) Optional.ofNullable(autoiotProfile).map($$Lambda$wxNS_l2z2a1B3tbU95AwvR6zHXM.INSTANCE).orElse(null)));
        try {
            if (isAvailable()) {
                return this.mService.autoiotWriteChr(autoiotProfile, str, str2, str3);
            }
            BydAutoIotSdkLog.e(str4, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotRead() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "autoiotRead");
        try {
            if (isAvailable()) {
                return this.mService.autoiotRead();
            }
            BydAutoIotSdkLog.e(str, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotReadDev(String str) {
        String str2 = TAG;
        BydAutoIotSdkLog.i(str2, "autoiotReadDev " + str);
        try {
            if (isAvailable()) {
                return this.mService.autoiotReadDev(str);
            }
            BydAutoIotSdkLog.e(str2, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotReadSrv(String str, String str2) {
        String str3 = TAG;
        BydAutoIotSdkLog.i(str3, "autoiotReadSrv " + str + "," + str2);
        try {
            if (isAvailable()) {
                return this.mService.autoiotReadSrv(str, str2);
            }
            BydAutoIotSdkLog.e(str3, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotReadChr(String str, String str2, String str3) {
        String str4 = TAG;
        BydAutoIotSdkLog.i(str4, "autoiotReadChr " + str + "," + str2 + "," + str3);
        try {
            if (isAvailable()) {
                return this.mService.autoiotReadChr(str, str2, str3);
            }
            BydAutoIotSdkLog.i(str4, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean autoiotConnected(String str) {
        String str2 = TAG;
        BydAutoIotSdkLog.i(str2, "autoiotConnected " + str);
        try {
            if (isAvailable()) {
                boolean zAutoiotConnected = this.mService.autoiotConnected(str);
                BydAutoIotSdkLog.i(str2, "autoiotConnected res: " + str + "," + zAutoiotConnected);
                return zAutoiotConnected;
            }
            BydAutoIotSdkLog.e(str2, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDevice(String str) {
        String str2 = TAG;
        BydAutoIotSdkLog.i(str2, "deleteDevice " + str);
        try {
            if (isAvailable()) {
                return this.mService.deleteDevice(str);
            }
            BydAutoIotSdkLog.e(str2, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return false;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void registerListener(AutoIotEventListener autoIotEventListener) {
        BydAutoIotSdkLog.i(TAG, "registerListener " + autoIotEventListener);
        if (this.mAutoIotEventListenerList.contains(autoIotEventListener)) {
            return;
        }
        this.mAutoIotEventListenerList.add(autoIotEventListener);
    }

    public void unregisterListener(AutoIotEventListener autoIotEventListener) {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "unregisterListener " + autoIotEventListener);
        try {
            if (isAvailable()) {
                this.mAutoIotEventListenerList.remove(autoIotEventListener);
                if (this.mAutoIotEventListenerList.isEmpty()) {
                    this.mService.unregisterListener(this.mIAutoIotEventListener);
                }
            } else {
                BydAutoIotSdkLog.e(str, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getGatewayInfo(String str) {
        String str2 = TAG;
        BydAutoIotSdkLog.i(str2, "getGatewayInfo " + str);
        try {
            if (isAvailable()) {
                return this.mService.getGatewayInfo(str);
            }
            BydAutoIotSdkLog.e(str2, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getMTU(String str) {
        String str2 = TAG;
        BydAutoIotSdkLog.i(str2, "getMTU " + str);
        try {
            if (isAvailable()) {
                return this.mService.getMTU(str);
            }
            BydAutoIotSdkLog.e(str2, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return -1;
        } catch (RemoteException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<String> getDeviceList() {
        String str = TAG;
        BydAutoIotSdkLog.i(str, "getDeviceList ");
        try {
            if (isAvailable()) {
                return this.mService.getDeviceList();
            }
            BydAutoIotSdkLog.e(str, "isAvailable = " + isAvailable() + "  getResult = " + getResult());
            return null;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
