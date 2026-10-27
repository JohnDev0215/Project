package com.ts.lib.settings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import com.ts.lib.settings.ISettingsAdapterService;
import com.ts.lib.settings.common.constant.Constants;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.interfaces.OnManagerConnChangedListener;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class SettingsAdapterManager {
    private static final int MSG_BIND_SERVICE = 100;
    private static final int MSG_DISCONNECT_BIND_SERVICE = 102;
    private static final int MSG_RETRY_BIND_SERVICE = 101;
    private static final String SETTING_SERVICE_CLASS_NAME = "com.ts.appservice.settings.SettingsService";
    private static final String SETTING_SERVICE_PACKAGE_NAME = "com.ts.appservice.settings";
    private static final int STATE_CONNECTED = 2;
    private static final int STATE_DISCONNECTED = 0;
    private static final int TIME_DISCONNECT_BIND_SERVICE = 3000;
    private static final int TIME_RETRY_BIND_SERVICE = 2000;
    private ISettingsAdapterService mBinder;
    private boolean mBinderResult;
    private int mConnectionState;
    private Context mContext;
    private RetryHandler mRetryHandler;
    private static final String TAG = SettingsAdapterManager.class.getSimpleName();
    private static volatile SettingsAdapterManager sInstance = null;
    private SettingAdapterDeathRecipient mSettingAdapterDeathRecipient = null;
    private List<OnManagerConnChangedListener> mServiceListeners = new CopyOnWriteArrayList();
    private HashMap<String, Boolean> mListenersMap = new HashMap<>();
    private final HashMap<String, SettingsAdapterBase> mServiceMap = new HashMap<>();
    private ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.ts.lib.settings.SettingsAdapterManager.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (SettingsAdapterManager.this) {
                SettingsAdapterManager.this.mBinder = ISettingsAdapterService.Stub.asInterface(iBinder);
                if (SettingsAdapterManager.this.mBinder == null) {
                    LogUtil.debug(SettingsAdapterManager.TAG, "onServiceConnected: iBinder is null!");
                } else {
                    LogUtil.debug(SettingsAdapterManager.TAG, "onServiceConnected: Binder is success:");
                    SettingsAdapterManager.this.mConnectionState = 2;
                    SettingsAdapterManager.this.mSettingAdapterDeathRecipient = SettingsAdapterManager.this.new SettingAdapterDeathRecipient();
                    try {
                        SettingsAdapterManager.this.mBinder.asBinder().linkToDeath(SettingsAdapterManager.this.mSettingAdapterDeathRecipient, 0);
                    } catch (RemoteException exception) {
                        exception.printStackTrace();
                    }
                    SettingsAdapterManager.this.updateServiceBinderCallback();
                }
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogUtil.debug(SettingsAdapterManager.TAG, "onServiceConnected: Unbinder is success!");
            if (SettingsAdapterManager.this.mConnectionState == 0) {
                return;
            }
            SettingsAdapterManager.this.disconnect();
        }
    };

    private final class RetryHandler extends Handler {
        private RetryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    SettingsAdapterManager.this.bindService();
                    break;
                case 101:
                case 102:
                    SettingsAdapterManager.this.reBindService();
                    break;
            }
        }
    }

    public static synchronized SettingsAdapterManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SettingsAdapterManager(context);
        }
        return sInstance;
    }

    private SettingsAdapterManager(Context context) {
        this.mContext = context.getApplicationContext();
        if (this.mRetryHandler == null) {
            this.mRetryHandler = new RetryHandler(context.getMainLooper());
        }
    }

    public Object getSettingsAdapterManager(String serviceName) {
        ISettingsAdapterService service = getICarOrThrow();
        synchronized (this.mServiceMap) {
            SettingsAdapterBase manager = this.mServiceMap.get(serviceName);
            if (manager == null) {
                try {
                    IBinder binder = service.getSettingsAdapterService(serviceName);
                    if (binder == null) {
                        LogUtil.error(TAG, "could not get binder for service:" + serviceName);
                        return null;
                    }
                    manager = createSettingsManager(serviceName, binder);
                    if (manager == null) {
                        LogUtil.error(TAG, " could not create manager for service:" + serviceName);
                        return null;
                    }
                    this.mServiceMap.put(serviceName, manager);
                } catch (RemoteException except) {
                    except.printStackTrace();
                }
            }
            return manager;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.ts.lib.settings.SettingsAdapterBase createSettingsManager(java.lang.String r3, android.os.IBinder r4) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ts.lib.settings.SettingsAdapterManager.createSettingsManager(java.lang.String, android.os.IBinder):com.ts.lib.settings.SettingsAdapterBase");
    }

    private synchronized ISettingsAdapterService getICarOrThrow() throws IllegalStateException {
        if (this.mBinder == null) {
            throw new IllegalStateException("not connected");
        }
        return this.mBinder;
    }

    public boolean isServiceBound() {
        LogUtil.info(TAG, "registerSettingCallback isServiceBinded: " + this.mConnectionState);
        return this.mConnectionState == 2;
    }

    public void connect() {
        if (!this.mBinderResult) {
            if (this.mRetryHandler.hasMessages(100)) {
                this.mRetryHandler.removeMessages(100);
            }
            this.mRetryHandler.sendEmptyMessage(100);
        }
    }

    public void setOnManagerConnChangedListener(OnManagerConnChangedListener listener) {
        LogUtil.debug(TAG, "setOnManagerConnChangedListener: " + listener);
        if (!this.mServiceListeners.contains(listener) && listener != null) {
            this.mServiceListeners.add(listener);
            this.mListenersMap.put(listener.toString(), false);
            LogUtil.debug(TAG, "setOnManagerConnChangedListener ok! listener size: " + this.mServiceListeners.size());
        }
    }

    public void removeOnManagerConnChangedListener(OnManagerConnChangedListener listener) {
        LogUtil.debug(TAG, "removeOnManagerConnChangedListener: " + listener);
        if (listener != null && this.mServiceListeners.contains(listener)) {
            this.mServiceListeners.remove(listener);
            this.mListenersMap.remove(listener.toString());
        }
    }

    protected void bindService() {
        LogUtil.error(TAG, "bindService: ");
        Intent intent = new Intent();
        intent.setAction(Constants.SETTINGS_BINDER);
        intent.setClassName(SETTING_SERVICE_PACKAGE_NAME, SETTING_SERVICE_CLASS_NAME);
        if (this.mContext != null) {
            LogUtil.debug(TAG, "BindService Connection: " + this.mServiceConnection);
            this.mBinderResult = this.mContext.bindServiceAsUser(intent, this.mServiceConnection, 1, UserHandle.getUserHandleForUid(0));
            LogUtil.error(TAG, "BindService mBinderResult: " + this.mBinderResult);
            if (!this.mBinderResult) {
                if (this.mRetryHandler.hasMessages(101)) {
                    this.mRetryHandler.removeMessages(101);
                }
                this.mRetryHandler.sendEmptyMessageDelayed(101, FileUtils.FAT_FILE_TIMESTAMP_GRANULARITY);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reBindService() {
        LogUtil.debug(TAG, "reBindService: " + this.mConnectionState);
        if (this.mConnectionState != 2) {
            bindService();
        }
    }

    public void destory() {
        LogUtil.error(TAG, "destory");
        if (sInstance == null) {
            LogUtil.error(TAG, "sInstance is null");
            return;
        }
        List<OnManagerConnChangedListener> list = this.mServiceListeners;
        if (list != null && list.size() > 0) {
            this.mServiceListeners.clear();
        }
        unBindService();
        sInstance = null;
    }

    protected void unBindService() {
        ServiceConnection serviceConnection;
        LogUtil.debug(TAG, "unBindService : " + this.mConnectionState + "// mBinderResult : " + this.mBinderResult + "// Connection : " + this.mServiceConnection);
        if (this.mConnectionState == 2 && this.mBinderResult && (serviceConnection = this.mServiceConnection) != null) {
            this.mContext.unbindService(serviceConnection);
            this.mConnectionState = 0;
            this.mBinderResult = false;
        }
        RetryHandler retryHandler = this.mRetryHandler;
        if (retryHandler != null) {
            retryHandler.removeCallbacksAndMessages(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateServiceBinderCallback() {
        LogUtil.error(TAG, "updateServiceBinderCallback");
        List<OnManagerConnChangedListener> list = this.mServiceListeners;
        if (list != null && list.size() > 0) {
            for (OnManagerConnChangedListener listener : this.mServiceListeners) {
                try {
                    if (this.mListenersMap != null) {
                        this.mListenersMap.put(listener.toString(), true);
                        listener.onBinderStateChanged(true);
                    }
                } catch (Exception except) {
                    LogUtil.error(TAG, "updateServiceBinderCallback ex ", except);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void retryBindService() {
        this.mConnectionState = 0;
        this.mBinderResult = false;
        List<OnManagerConnChangedListener> list = this.mServiceListeners;
        if (list != null && list.size() > 0) {
            for (OnManagerConnChangedListener listener : this.mServiceListeners) {
                listener.onBinderStateChanged(false);
                this.mListenersMap.put(listener.toString(), false);
            }
        }
        if (this.mRetryHandler.hasMessages(102)) {
            this.mRetryHandler.removeMessages(102);
        }
        this.mRetryHandler.sendEmptyMessageDelayed(102, 3000L);
    }

    class SettingAdapterDeathRecipient implements IBinder.DeathRecipient {
        SettingAdapterDeathRecipient() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            LogUtil.error(SettingsAdapterManager.TAG, "binderDied");
            SettingsAdapterManager.this.mBinder.asBinder().unlinkToDeath(this, 0);
            SettingsAdapterManager.this.disconnect();
            SettingsAdapterManager.this.unBindService();
            SettingsAdapterManager.this.retryBindService();
        }

        void release() {
            SettingsAdapterManager.this.mBinder.asBinder().unlinkToDeath(this, 0);
        }
    }

    private void tearDownSettingAdapterManagers() {
        synchronized (this.mServiceMap) {
            for (SettingsAdapterBase manager : this.mServiceMap.values()) {
                manager.onSettingsDisconnected();
            }
            this.mServiceMap.clear();
        }
    }

    public void disconnect() {
        synchronized (this) {
            if (this.mConnectionState == 0) {
                return;
            }
            if (this.mRetryHandler != null) {
                this.mRetryHandler.removeCallbacksAndMessages(null);
            }
            tearDownSettingAdapterManagers();
            this.mBinder = null;
            this.mConnectionState = 0;
        }
    }
}
