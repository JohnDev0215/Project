package com.ts.androidauto.app;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.androidauto.app.manager.AndroidAutoRemoteUiManager;
import com.ts.androidauto.app.nativevr.NativeVrAdapter;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.lib.settings.vradapter.ILocalVrSettingsService;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoRemoteUiService extends Service {
    private static final String ACTION_AA_AUDIO_MUTE = "com.ts.androidauto.audio.mute";
    private static final String ACTION_VOICE_REQUEST_RELEASE = "android.broadcast.voice.request.release";
    private static final String ANDROIDAUTO_PROJECTIONSERVICE = "com.ts.androidauto.projectionservice";
    private static final String ANDROIDAUTO_SERVICE = "com.ts.androidauto.action.AndroidAutoService";
    private static final String KEY_VOICE_IDENTITY = "who";
    private static final int MSG_BIND_SERVICE = 100;
    private static final int MSG_RETRY_BIND_SERVICE = 101;
    private static final int RETRY_MAX = 10;
    private static final int SHOW_MUTE_TOAST = 1001;
    private static final String TAG = "AAP" + AndroidAutoRemoteUiService.class.getSimpleName();
    private static final int TIME_BIND_SERVICE = 8000;
    private static final int TIME_RETRY_BIND_SERVICE = 2000;
    private static final String VOICE_IDENTITY_AA = "androidauto";
    private ILocalVrSettingsService iLocalVrSettingsService;
    private AndroidAutoRemoteUiManager mAndroidAutoRemoteUiManager;
    private NativeVrAdapter mNativeVrAdapter;
    private RetryHandler mRetryHandler;
    private Context mContext = null;
    private String mToastMessage = AndroidAutoConst.DEFAULT_SCREEN_NAME;
    private boolean isConnect = false;
    private int mConnectCount = 0;
    ServiceConnection connection = new ServiceConnection() { // from class: com.ts.androidauto.app.AndroidAutoRemoteUiService.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            AndroidAutoRemoteUiService.this.iLocalVrSettingsService = ILocalVrSettingsService.Stub.asInterface(service);
            AndroidAutoRemoteUiService.this.mNativeVrAdapter.initNativeVrAdapter(AndroidAutoRemoteUiService.this.iLocalVrSettingsService);
            LogUtil.info(AndroidAutoRemoteUiService.TAG, "AIDL======connect : ");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            AndroidAutoRemoteUiService.this.iLocalVrSettingsService = null;
            AndroidAutoRemoteUiService.this.isConnect = false;
            LogUtil.info(AndroidAutoRemoteUiService.TAG, "AIDL======disconnect");
        }
    };
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.ts.androidauto.app.AndroidAutoRemoteUiService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.debug(AndroidAutoRemoteUiService.TAG, "onReceive --- intent: " + intent);
            if (AndroidAutoRemoteUiService.ACTION_AA_AUDIO_MUTE.equals(intent.getAction())) {
                AndroidAutoRemoteUiService.this.mRetryHandler.sendEmptyMessage(1001);
                AndroidAutoRemoteUiService androidAutoRemoteUiService = AndroidAutoRemoteUiService.this;
                androidAutoRemoteUiService.mToastMessage = androidAutoRemoteUiService.getResources().getString(R.string.mute_message);
                LogUtil.debug(AndroidAutoRemoteUiService.TAG, "onReceive mToastMessage:" + AndroidAutoRemoteUiService.this.mToastMessage);
            }
        }
    };

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        startAndroidAutoService();
        this.mContext = this;
        if (this.mRetryHandler == null) {
            this.mRetryHandler = new RetryHandler(this.mContext.getMainLooper());
        }
        this.mRetryHandler.sendEmptyMessageDelayed(100, 8000L);
        init(this.mContext);
        IntentFilter onMuteIntentFilter = new IntentFilter(ACTION_AA_AUDIO_MUTE);
        this.mContext.registerReceiver(this.mBroadcastReceiver, onMuteIntentFilter);
    }

    private void startAndroidAutoService() {
        Intent intentAndroidAutoService = new Intent();
        intentAndroidAutoService.setAction(ANDROIDAUTO_SERVICE);
        intentAndroidAutoService.setPackage(ANDROIDAUTO_PROJECTIONSERVICE);
        startService(intentAndroidAutoService);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.info(TAG, "onStartCommand start" + intent);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public void onDestroy() {
        uninit();
        super.onDestroy();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.info(TAG, "onBind start.");
        return null;
    }

    private void init(Context context) {
        LogUtil.info(TAG, "init start context:" + context);
        AndroidAutoRemoteUiManager androidAutoRemoteUiManager = AndroidAutoRemoteUiManager.getInstance();
        this.mAndroidAutoRemoteUiManager = androidAutoRemoteUiManager;
        androidAutoRemoteUiManager.init(context);
        sendVrBroadcastReceiver(ACTION_VOICE_REQUEST_RELEASE);
        LogUtil.info(TAG, "init end.");
    }

    private void uninit() {
        this.mNativeVrAdapter.uninit();
        this.mAndroidAutoRemoteUiManager.uninit();
    }

    private final class RetryHandler extends Handler {
        private RetryHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i == 100) {
                AndroidAutoRemoteUiService.this.bindServiceInitNativeVr();
                return;
            }
            if (i == 101) {
                AndroidAutoRemoteUiService.this.reBindService();
            } else if (i == 1001) {
                Toast toast = Toast.makeText(AndroidAutoRemoteUiService.this.mContext, AndroidAutoRemoteUiService.this.mToastMessage, 0);
                toast.setGravity(17, 0, 0);
                toast.show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindServiceInitNativeVr() {
        LogUtil.error(TAG, "bindServiceInitNativeVr");
        bindServiceByAidl();
        if (this.mNativeVrAdapter == null) {
            this.mNativeVrAdapter = new NativeVrAdapter(this.mContext);
        }
    }

    private void bindServiceByAidl() {
        Intent intent = new Intent();
        this.mConnectCount++;
        intent.setComponent(new ComponentName("com.byd.vrassistant", "com.byd.vrassistant.service.LocalVRSettingService"));
        boolean zBindService = bindService(intent, this.connection, 1);
        this.isConnect = zBindService;
        if (!zBindService) {
            if (this.mRetryHandler.hasMessages(101)) {
                this.mRetryHandler.removeMessages(101);
            }
            this.mRetryHandler.sendEmptyMessageDelayed(101, 2000L);
        }
        LogUtil.info(TAG, "bindService======isConnect " + this.isConnect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reBindService() {
        LogUtil.debug(TAG, "reBindService: " + this.isConnect + "ConnectCount:" + this.mConnectCount);
        if (!this.isConnect && this.mConnectCount != 10) {
            LogUtil.info(TAG, "AIDL disconnect, AIDL======reconnect");
            bindServiceByAidl();
        }
    }

    private void sendVrBroadcastReceiver(String action) {
        if (this.mContext != null) {
            Intent intent = new Intent(action);
            intent.putExtra(KEY_VOICE_IDENTITY, VOICE_IDENTITY_AA);
            this.mContext.sendBroadcast(intent);
            LogUtil.debug(TAG, "sendVrBroadcastReceiver() action = " + action);
        }
    }
}
