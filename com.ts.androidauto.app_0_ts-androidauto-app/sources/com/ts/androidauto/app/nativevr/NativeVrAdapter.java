package com.ts.androidauto.app.nativevr;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import com.ts.androidauto.app.common.LogUtil;
import com.ts.car.core.ServiceProvider;
import com.ts.lib.settings.vradapter.ILocalVrSettingsCallback;
import com.ts.lib.settings.vradapter.ILocalVrSettingsService;
import ts.car.service.androidauto.IAndroidAutoAppListener;
import ts.car.service.androidauto.IAndroidAutoAppManager;

/* JADX INFO: loaded from: classes2.dex */
public class NativeVrAdapter {
    private static final String ACTION_VOICE_REQUEST_RELEASE = "android.broadcast.voice.request.release";
    private static final String ACTION_VOICE_REQUEST_START = "android.broadcast.voice.request.start";
    private static final String FINISH_SHOW_ACTIVITY_ACTION = "com.ts.androidauto.app.display.aapactivity.RECEIVER_FINISH_ACTION";
    private static final String KEY_SHOW = "show";
    private static final String KEY_USE_MIC = "usemic";
    private static final String KEY_VOICE_IDENTITY = "who";
    private static final int NATIVE_ASR_ACTIVE = 0;
    private static final int NATIVE_ASR_DEACTIVE = 1;
    private static final String TAG = "AAP" + NativeVrAdapter.class.getSimpleName();
    private static final String VOICE_IDENTITY_AA = "androidauto";
    private static final String VOICE_IDENTITY_BYD = "bydvoice";
    private IAndroidAutoAppManager mAndroidAutoAppManager;
    private Context mContext;
    private ILocalVrSettingsService mLocalVrSettingsService;
    private VrBroadcastReceiver mVrBroadcastReceiver = null;
    private boolean mIsNativeVrActive = false;
    private boolean mIsNativeVrInterrupt = false;
    private boolean mShowAaAfterExit = false;
    private IAndroidAutoAppListener mAndroidAutoAppListener = new IAndroidAutoAppListener() { // from class: com.ts.androidauto.app.nativevr.NativeVrAdapter.1
        public void onVideoFocusStatusChanged(int requestType) {
            LogUtil.debug(NativeVrAdapter.TAG, "onVideoFocusStatusChanged: " + requestType);
            if (requestType == 1) {
                boolean isNativeVrActive = NativeVrAdapter.this.getNativeVrState();
                if (isNativeVrActive) {
                    LogUtil.info(NativeVrAdapter.TAG, "AA projection start, stop Native Vr!");
                    NativeVrAdapter.this.stopNativeVr();
                }
            }
        }
    };
    ILocalVrSettingsCallback settingsStopCallback = new ILocalVrSettingsCallback.Stub() { // from class: com.ts.androidauto.app.nativevr.NativeVrAdapter.2
        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsCallback
        public void onLocalVrAppStateChanged(int state) throws RemoteException {
            LogUtil.debug(NativeVrAdapter.TAG, "ILocalVrSettingsCallback======settingsStopCallback");
        }
    };

    public NativeVrAdapter(Context mContext) {
        this.mAndroidAutoAppManager = null;
        LogUtil.info(TAG, "init");
        this.mContext = mContext;
        registerVrBroadcastReceiver();
        IAndroidAutoAppManager iAndroidAutoAppManager = (IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, mContext);
        this.mAndroidAutoAppManager = iAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                iAndroidAutoAppManager.registerAndroidAutoAppListener(this.mAndroidAutoAppListener);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void initNativeVrAdapter(ILocalVrSettingsService localVrSettingsService) {
        LogUtil.info(TAG, "initNativeVrAdapter mLocalVrSettingsService: " + localVrSettingsService);
        this.mLocalVrSettingsService = localVrSettingsService;
    }

    public void uninit() {
        this.mContext.unregisterReceiver(this.mVrBroadcastReceiver);
    }

    public boolean getNativeVrState() {
        boolean isNativeVrActive = false;
        try {
            if (this.mLocalVrSettingsService == null) {
                LogUtil.info(TAG, "getNativeVrState mLocalVrSettingsService is null!");
            } else {
                int state = this.mLocalVrSettingsService.getVrState();
                LogUtil.debug(TAG, "AIDL======getNativeVrState state: " + state);
                if (state == 0) {
                    isNativeVrActive = true;
                } else {
                    isNativeVrActive = false;
                }
            }
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
        LogUtil.debug(TAG, "getNativeVrState isNativeVrActive: " + isNativeVrActive);
        return isNativeVrActive;
    }

    public void stopNativeVr() {
        try {
            if (this.mLocalVrSettingsService == null) {
                LogUtil.info(TAG, "stopNativeVr iLocalVrSettingsService is null!");
            } else {
                LogUtil.info(TAG, "AIDL======stopNativeVr");
                this.mLocalVrSettingsService.doStopVr(this.settingsStopCallback);
            }
        } catch (RemoteException exception) {
            exception.printStackTrace();
        }
    }

    private class VrBroadcastReceiver extends BroadcastReceiver {
        private VrBroadcastReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x004f  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r7, android.content.Intent r8) {
            /*
                r6 = this;
                java.lang.String r0 = r8.getAction()
                java.lang.String r1 = "who"
                java.lang.String r1 = r8.getStringExtra(r1)
                java.lang.String r2 = com.ts.androidauto.app.nativevr.NativeVrAdapter.access$000()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "VrBroadcastReceiver onReceive action = "
                r3.append(r4)
                r3.append(r0)
                java.lang.String r4 = ",who = "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                com.ts.androidauto.app.common.LogUtil.info(r2, r3)
                int r2 = r0.hashCode()
                r3 = -1988150117(0xffffffff897f3c9b, float:-3.0723005E-33)
                r4 = 0
                r5 = 1
                if (r2 == r3) goto L45
                r3 = -658999808(0xffffffffd8b87600, float:-1.6225356E15)
                if (r2 == r3) goto L3b
            L3a:
                goto L4f
            L3b:
                java.lang.String r2 = "android.broadcast.voice.request.release"
                boolean r2 = r0.equals(r2)
                if (r2 == 0) goto L3a
                r2 = r5
                goto L50
            L45:
                java.lang.String r2 = "android.broadcast.voice.request.start"
                boolean r2 = r0.equals(r2)
                if (r2 == 0) goto L3a
                r2 = r4
                goto L50
            L4f:
                r2 = -1
            L50:
                java.lang.String r3 = "bydvoice"
                if (r2 == 0) goto L73
                if (r2 == r5) goto L57
                goto L83
            L57:
                boolean r2 = r3.equals(r1)
                if (r2 == 0) goto L83
                com.ts.androidauto.app.nativevr.NativeVrAdapter r2 = com.ts.androidauto.app.nativevr.NativeVrAdapter.this
                com.ts.androidauto.app.nativevr.NativeVrAdapter.access$102(r2, r4)
                com.ts.androidauto.app.nativevr.NativeVrAdapter r2 = com.ts.androidauto.app.nativevr.NativeVrAdapter.this
                java.lang.String r3 = "show"
                boolean r3 = r8.getBooleanExtra(r3, r4)
                com.ts.androidauto.app.nativevr.NativeVrAdapter.access$302(r2, r3)
                com.ts.androidauto.app.nativevr.NativeVrAdapter r2 = com.ts.androidauto.app.nativevr.NativeVrAdapter.this
                com.ts.androidauto.app.nativevr.NativeVrAdapter.access$200(r2)
                goto L83
            L73:
                boolean r2 = r3.equals(r1)
                if (r2 == 0) goto L83
                com.ts.androidauto.app.nativevr.NativeVrAdapter r2 = com.ts.androidauto.app.nativevr.NativeVrAdapter.this
                com.ts.androidauto.app.nativevr.NativeVrAdapter.access$102(r2, r5)
                com.ts.androidauto.app.nativevr.NativeVrAdapter r2 = com.ts.androidauto.app.nativevr.NativeVrAdapter.this
                com.ts.androidauto.app.nativevr.NativeVrAdapter.access$200(r2)
            L83:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.ts.androidauto.app.nativevr.NativeVrAdapter.VrBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    private void registerVrBroadcastReceiver() {
        if (this.mContext != null) {
            IntentFilter intentFilter = new IntentFilter();
            this.mVrBroadcastReceiver = new VrBroadcastReceiver();
            intentFilter.addAction(ACTION_VOICE_REQUEST_START);
            intentFilter.addAction(ACTION_VOICE_REQUEST_RELEASE);
            this.mContext.registerReceiver(this.mVrBroadcastReceiver, intentFilter);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("registerVrBroadcastReceiver mVrBroadcastReceiver: ");
            sb.append(this.mVrBroadcastReceiver != null);
            LogUtil.info(str, sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleAaProjectionByNativeVr() {
        int isAaVideoShow = 0;
        IAndroidAutoAppManager iAndroidAutoAppManager = this.mAndroidAutoAppManager;
        if (iAndroidAutoAppManager != null) {
            try {
                isAaVideoShow = iAndroidAutoAppManager.getVideoFocusStatus();
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        LogUtil.info(TAG, "handleAaProjectionByNativeVr isAaVideoShow: " + isAaVideoShow + " mIsNativeVrActive: " + this.mIsNativeVrActive + " mShowAaAfterExit: " + this.mShowAaAfterExit);
        if (this.mIsNativeVrActive && isAaVideoShow == 1) {
            LogUtil.info(TAG, "Native Vr Start, hide AA Projection!");
            Intent finishShowActivityIntent = new Intent();
            finishShowActivityIntent.setAction(FINISH_SHOW_ACTIVITY_ACTION);
            Context context = this.mContext;
            if (context != null) {
                context.sendBroadcast(finishShowActivityIntent);
            }
            this.mIsNativeVrInterrupt = true;
            return;
        }
        if (!this.mIsNativeVrActive && this.mShowAaAfterExit && isAaVideoShow == 0 && this.mIsNativeVrInterrupt) {
            LogUtil.info(TAG, "Native Vr end, Start AA Projection!");
            IAndroidAutoAppManager iAndroidAutoAppManager2 = this.mAndroidAutoAppManager;
            if (iAndroidAutoAppManager2 != null) {
                try {
                    iAndroidAutoAppManager2.requestUi(0);
                } catch (RemoteException ex2) {
                    ex2.printStackTrace();
                }
            }
            this.mIsNativeVrInterrupt = false;
        }
    }
}
