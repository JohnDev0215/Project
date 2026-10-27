package com.byd.vrsettings.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import com.byd.vrdownload.i.IDownloadCallback;
import com.byd.vrdownload.model.AidlResult;
import com.byd.vrdownload.model.DownloadTask;
import com.byd.vrdownload.model.LanguageOTAResult;
import com.byd.vrdownload.model.LanguageUpdate;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.R;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.model.LanguageBean;
import com.byd.vrsettings.model.LanguageLocal;
import com.byd.vrsettings.receiver.LogToolObserver;
import com.byd.vrsettings.receiver.SettingObserver;
import com.byd.vrsettings.service.AidlServiceManager;
import com.byd.vrsettings.utils.GSON;
import com.byd.vrsettings.utils.LanguageUtil;
import com.byd.vrsettings.utils.LogUtils;
import com.byd.vrsettings.utils.ModeUtils;
import com.byd.vrsettings.utils.NetUtils;
import com.byd.vrsettings.utils.RLog;
import com.byd.vrsettings.utils.SharedPreferencesUtils;
import com.chad.library.BuildConfig;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class NotifyService extends Service {
    private static final String TAG = "NEU_NotifyService";
    private Context mContext;
    private LogToolObserver mLogToolReceiver;
    private SettingObserver settingObserver;
    private boolean showNotify = true;
    ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.byd.vrsettings.service.NotifyService.2
        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            RLog.i(NotifyService.TAG, "onAvailable");
            NotifyService.this.showNotify = true;
            AidlServiceManager.getInstance().requestLanguageList(OtaDataManager.createParemeter());
            List<DownloadTask> allTask = AidlServiceManager.getInstance().getAllTask();
            RLog.i(NotifyService.TAG, "allTasks===" + GSON.toJson(allTask));
            if (allTask == null || allTask.size() <= 0) {
                return;
            }
            for (DownloadTask downloadTask : allTask) {
                if (downloadTask.getStatus() == -1) {
                    AidlServiceManager.getInstance().resume(downloadTask.getId(), downloadTask.getLanguageUpdate());
                    NotifyManager.getInstance().cancelSingleNotify(-1, downloadTask.getLanguageUpdate().getLanguage());
                }
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            RLog.i(NotifyService.TAG, "网络断开");
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrsettings.service.NotifyService.2.1
                @Override // java.lang.Runnable
                public void run() {
                    DialogManager.get().showNetTipDialog(false);
                }
            });
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
        }
    };
    IDownloadCallback callback = new IDownloadCallback.Stub() { // from class: com.byd.vrsettings.service.NotifyService.3
        @Override // com.byd.vrdownload.i.IDownloadCallback
        public AidlResult checkSpaceQuota(long j) throws RemoteException {
            return null;
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void deleteLanguage(String str) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void installLanguage(String str) throws RemoteException {
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void getLanguageOTA(LanguageOTAResult languageOTAResult) throws RemoteException {
            LogUtils.i(NotifyService.TAG, "getLanguageOTA isSuccess===" + languageOTAResult.isSuccess());
            List<LanguageBean> listCheckOtaUpgrade = OtaDataManager.checkOtaUpgrade(languageOTAResult.getList());
            if (languageOTAResult.isSuccess()) {
                if (listCheckOtaUpgrade != null && listCheckOtaUpgrade.size() > 0) {
                    EventBus.getDefault().postSticky(new PrivacyPolicyEvent("KEY_OTA_UPGRADE", "1"));
                    if (NotifyService.this.showNotify) {
                        String str = BuildConfig.FLAVOR;
                        for (int i = 0; i < listCheckOtaUpgrade.size(); i++) {
                            if (i == listCheckOtaUpgrade.size() - 1) {
                                str = str + LanguageUtil.convertAll(NotifyService.this, listCheckOtaUpgrade.get(i).getLanguageRemote().getLanguage());
                            } else {
                                str = str + LanguageUtil.convertAll(NotifyService.this, listCheckOtaUpgrade.get(i).getLanguageRemote().getLanguage()) + ",";
                            }
                        }
                        LogUtils.i(NotifyService.TAG, "NotifyContent-->" + str);
                        NotifyManager.getInstance().saveCache(100, str).createNotify(100, str);
                        NotifyService.this.showNotify = false;
                        return;
                    }
                    return;
                }
                EventBus.getDefault().postSticky(new PrivacyPolicyEvent("KEY_OTA_UPGRADE", "0"));
            }
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onDownload(DownloadTask downloadTask) throws RemoteException {
            LogUtils.i(NotifyService.TAG, "onDownload...");
            if (downloadTask == null && downloadTask.getLanguageUpdate() == null) {
                return;
            }
            if (downloadTask.getStatus() == -2 || downloadTask.getStatus() == -1) {
                NotifyManager.getInstance().saveCache(downloadTask.getStatus(), downloadTask.getLanguageUpdate().getLanguage()).createNotify(downloadTask.getStatus(), null);
                return;
            }
            if (downloadTask.getStatus() == 6) {
                NotifyManager.getInstance().cancelSingleNotify(-2, downloadTask.getLanguageUpdate().getLanguage());
            } else if (downloadTask.getStatus() == -5) {
                NotifyManager.getInstance().saveCache(-1, downloadTask.getLanguageUpdate().getLanguage()).createNotify(-1, null);
            } else if (downloadTask.getStatus() == -3) {
                NotifyService.this.installLanguagePkg(downloadTask.getId(), downloadTask.getLanguageUpdate());
            }
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onStartDownloadResult(AidlResult aidlResult) throws RemoteException {
            LogUtils.i(NotifyService.TAG, "onStartDownloadResult: " + aidlResult.getValue());
        }

        @Override // com.byd.vrdownload.i.IDownloadCallback
        public void onDownloadProgress(DownloadTask downloadTask, long j, long j2) throws RemoteException {
            LogUtils.i(NotifyService.TAG, "getStatus====" + ((int) downloadTask.getStatus()));
        }
    };

    static /* synthetic */ boolean lambda$onCreate$1(Message message) {
        return true;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.i(TAG, "NotifyService onCreate");
        this.mContext = this;
        initLogTool();
        new Handler().postDelayed(new Runnable() { // from class: com.byd.vrsettings.service.NotifyService.1
            @Override // java.lang.Runnable
            public void run() {
                AidlServiceManager.getInstance().bindAidlServer(NotifyService.this);
            }
        }, 1500L);
        AidlServiceManager.getInstance().setCallBack(new AidlServiceManager.ServiceCallBack() { // from class: com.byd.vrsettings.service.NotifyService$$ExternalSyntheticLambda0
            @Override // com.byd.vrsettings.service.AidlServiceManager.ServiceCallBack
            public final void next(Object obj) {
                this.f$0.m59lambda$onCreate$0$combydvrsettingsserviceNotifyService((Boolean) obj);
            }
        });
        NetUtils.registNetCallBack(this.networkCallback);
        this.settingObserver = new SettingObserver(new Handler(new Handler.Callback() { // from class: com.byd.vrsettings.service.NotifyService$$ExternalSyntheticLambda1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return NotifyService.lambda$onCreate$1(message);
            }
        }));
        getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.settingObserver);
        DialogManager.get().start();
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-byd-vrsettings-service-NotifyService, reason: not valid java name */
    /* synthetic */ void m59lambda$onCreate$0$combydvrsettingsserviceNotifyService(Boolean bool) {
        if (bool.booleanValue()) {
            AidlServiceManager.getInstance().registDownLoadCallBack(this.callback, "setting_notify_service");
            AidlServiceManager.getInstance().requestLanguageList(OtaDataManager.createParemeter());
        }
    }

    private void initLogTool() {
        RLog.i(TAG, "initLogTool: ");
        this.mLogToolReceiver = new LogToolObserver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.byd.action.OpenVRSettingsLogSwitch");
        intentFilter.addAction("bydlogtool.action.savelogresult");
        registerReceiver(this.mLogToolReceiver, intentFilter);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtils.i(TAG, "NotifyService onStartCommand");
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("vrsettings", "NotifyService", 2));
        startForeground(80, new Notification.Builder(this, "vrsettings").build());
        if (intent != null) {
            String stringExtra = intent.getStringExtra("NotifyParams");
            LogUtils.i(TAG, "NotifyService onStartCommand source===" + stringExtra);
            if ("source_boot".equals(stringExtra)) {
                new Handler().postDelayed(new Runnable() { // from class: com.byd.vrsettings.service.NotifyService$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m60lambda$onStartCommand$2$combydvrsettingsserviceNotifyService();
                    }
                }, 2000L);
            } else if ("installPkg".equals(stringExtra)) {
                LanguageUpdate languageUpdate = (LanguageUpdate) intent.getParcelableExtra("languageRemote");
                int intExtra = intent.getIntExtra("taskId", -1);
                if (languageUpdate != null) {
                    installLanguagePkg(intExtra, languageUpdate);
                } else {
                    LogUtils.i("wens", "getSerializableExtra(languageBean) ==null");
                }
            } else if ("switchPkg".equals(stringExtra)) {
                OtaDataManager.switchTimeOut(intent.getStringExtra("convertName"));
            } else {
                AidlServiceManager.getInstance().requestLanguageList(OtaDataManager.createParemeter());
            }
        }
        return super.onStartCommand(intent, i, i2);
    }

    /* JADX INFO: renamed from: lambda$onStartCommand$2$com-byd-vrsettings-service-NotifyService, reason: not valid java name */
    /* synthetic */ void m60lambda$onStartCommand$2$combydvrsettingsserviceNotifyService() {
        this.showNotify = true;
        AidlServiceManager.getInstance().requestLanguageList(OtaDataManager.createParemeter());
    }

    private void startDownloadService() {
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage("com.byd.vrdownload");
        if (launchIntentForPackage != null) {
            startActivity(launchIntentForPackage);
        } else {
            OtaDataManager.showToast("please install byd download service");
        }
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        DialogManager.get().setLight(!ModeUtils.isNightMode(this));
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(TAG, "onDestroy:" + this.callback);
        unregisterReceiver(this.mLogToolReceiver);
        if (this.callback != null) {
            AidlServiceManager.getInstance().unRegistDownLoadCallBack(this.callback);
            this.callback = null;
        }
        ConnectivityManager.NetworkCallback networkCallback = this.networkCallback;
        if (networkCallback != null) {
            NetUtils.unRegistNetCallBack(networkCallback);
            this.networkCallback = null;
        }
        AidlServiceManager.getInstance().unBindService(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(PrivacyPolicyEvent privacyPolicyEvent) {
        if (privacyPolicyEvent.getType().equals("KEY_LANGUAG_INSTALLING")) {
            OtaDataManager.showToast(R.string.text_vr_assistant_suspend);
            return;
        }
        if (privacyPolicyEvent.getType().equals("SHOW_TOAST")) {
            if (TextUtils.isEmpty(privacyPolicyEvent.getValue())) {
                return;
            }
            OtaDataManager.showToast(privacyPolicyEvent.getValue());
        } else if (privacyPolicyEvent.getType().equals("KEY_VOICE_DIALOGEVENT")) {
            OtaDataManager.checkInstallMap();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installLanguagePkg(int i, LanguageUpdate languageUpdate) {
        LogUtils.i("wens", "installLanguagePkg#=,path:" + languageUpdate.getDownloadingPath());
        boolean zBooleanValue = ((Boolean) SharedPreferencesUtils.getParam(BydVoiceSettingApplication.getContext(), languageUpdate.getLanguage(), false)).booleanValue();
        if (!zBooleanValue) {
            if (!OtaDataManager.isAssistantLive(this)) {
                EventBus.getDefault().post(new PrivacyPolicyEvent("SHOW_TOAST", getString(R.string.text_install_fail)));
                return;
            }
            LanguageLocal languageLocal = new LanguageLocal();
            languageLocal.setPath(languageUpdate.getDownloadingPath());
            languageLocal.setName("data_" + languageUpdate.getLanguage());
            languageLocal.setVersion(languageUpdate.getDownloadingPkgVersion());
            LogUtils.i("wens", "installLanguagePkg#=,isInstall:" + zBooleanValue);
            int autoEnableState = OtaDataManager.getAutoEnableState(languageUpdate.getLanguage());
            LogUtils.i("wens", "installLanguagePkg#=,autoEnable state:" + autoEnableState);
            if (autoEnableState == -1 && languageUpdate != null) {
                OtaDataManager.setSettingValue(languageLocal, languageUpdate.isDifferentialUpdate() ? 5 : 3);
                return;
            } else {
                OtaDataManager.setSettingValue(languageLocal, autoEnableState);
                return;
            }
        }
        LogUtils.i("wens", "not installLanguagePkg #=");
    }
}
