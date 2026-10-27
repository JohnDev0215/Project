package com.byd.vrassistant;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import cerence.ark.assistant.api.ArkAssistant;
import cerence.ark.assistant.api.dynamic.ContentType;
import cerence.ark.assistant.framework.prompter.IPrompter;
import cerence.ark.assistant.framework.prompter.PlayState;
import cerence.ark.assistant.framework.prompter.PrompterCallback;
import cerence.ark.assistant.framework.prompter.PrompterError;
import com.blankj.utilcode.util.SPUtils;
import com.byd.common.countrycode.CountryCodeUtil;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.naviserviceapi.INaviService;
import com.byd.vrassistant.bean.ScreenMode;
import com.byd.vrassistant.bean.VRAudioTrackNullBean;
import com.byd.vrassistant.bean.VRPowerStateBean;
import com.byd.vrassistant.bean.VoiceLanuageDp;
import com.byd.vrassistant.conversation.AppHelper;
import com.byd.vrassistant.conversation.NaviHelper;
import com.byd.vrassistant.conversation.ZoomCarHelper;
import com.byd.vrassistant.eventbus.AudioEvent;
import com.byd.vrassistant.eventbus.DataRecordEvent;
import com.byd.vrassistant.eventbus.FinalOperation;
import com.byd.vrassistant.eventbus.NavigationEvent;
import com.byd.vrassistant.eventbus.SystemStatusEvent;
import com.byd.vrassistant.eventbus.TtsEvent;
import com.byd.vrassistant.eventbus.VoiceSateEvent;
import com.byd.vrassistant.manager.ActiveManager;
import com.byd.vrassistant.manager.AmazonManager;
import com.byd.vrassistant.manager.KaraokeManager;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.SpotifyManager;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.USBCopyDataManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.receiver.AppStateReceiver;
import com.byd.vrassistant.receiver.AudioMicReceiver;
import com.byd.vrassistant.receiver.CallingReceiver;
import com.byd.vrassistant.receiver.ContactsReceiver;
import com.byd.vrassistant.receiver.DabStateReceiver;
import com.byd.vrassistant.receiver.FactoryCheck8155Receiver;
import com.byd.vrassistant.receiver.FactoryDataPackCheckReceiver;
import com.byd.vrassistant.receiver.HardKeyReceiverEx;
import com.byd.vrassistant.receiver.HomeListenerReceiver;
import com.byd.vrassistant.receiver.MusicObserver;
import com.byd.vrassistant.receiver.OtaMode8155Receiver;
import com.byd.vrassistant.receiver.PanoStartReceiver;
import com.byd.vrassistant.receiver.Power8155Receiver;
import com.byd.vrassistant.receiver.ScreenOffReceiver;
import com.byd.vrassistant.receiver.SettingObserver;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.NavUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemStateUtils;
import com.byd.vrassistant.utils.VoiceServiceUtils;
import com.byd.vrassistant.utils.VrToastUtils;
import com.byd.vrassistant.view.ViewManager;
import com.byd.vrassistant.view.ViewStateManger;
import com.byd.vrassistant.viewManager.ViewManagerInitCenter;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class NeuVoiceService extends Service {
    private static final String TAG = "NEU_VoiceService";
    public static String topAppPkg = "";
    AppStateReceiver appStateReceiver;
    DabStateReceiver dabStateReceiver;
    FactoryCheck8155Receiver factoryCheck8155Receiver;
    FactoryDataPackCheckReceiver factoryDataPackCheckReceiver;
    CallingReceiver mCallingReceiver;
    MusicObserver musicObserver;
    private ServiceConnection naviConnection;
    OtaMode8155Receiver otaMode8155Receiver;
    PanoStartReceiver panoStartReceiver;
    Power8155Receiver power8155Receiver;
    ScreenOffReceiver screenOffReceiver;
    SettingObserver settingObserver;
    private AudioMicReceiver mAudioMicReceiver = null;
    HomeListenerReceiver mHomeListenerReceiver = null;
    ContactsReceiver mContactsReceiver = null;
    BlockingQueue<Integer> musicBlockingQueue = new ArrayBlockingQueue(10, true);
    Thread searchListThread = new Thread(new Runnable() { // from class: com.byd.vrassistant.NeuVoiceService$$ExternalSyntheticLambda1
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.m127lambda$new$0$combydvrassistantNeuVoiceService();
        }
    });
    CommonViewModel commonViewModel = (CommonViewModel) new ViewModelProvider(BydVoiceApplication.getContext()).get(CommonViewModel.class);
    ViewManagerInitCenter viewManagerInitCenter = new ViewManagerInitCenter();

    static /* synthetic */ boolean lambda$onCreate$2(Message message) {
        return true;
    }

    static /* synthetic */ boolean lambda$onCreate$3(Message message) {
        return true;
    }

    public static class SingleToast {
        private static Toast mToast;

        public static void show(Context context, String str, int i) {
            Toast toast = mToast;
            if (toast != null) {
                toast.cancel();
            }
            Toast toastMakeText = Toast.makeText(context, str, i);
            mToast = toastMakeText;
            toastMakeText.show();
        }
    }

    /* JADX INFO: renamed from: lambda$new$0$com-byd-vrassistant-NeuVoiceService, reason: not valid java name */
    /* synthetic */ void m127lambda$new$0$combydvrassistantNeuVoiceService() {
        while (true) {
            try {
                this.musicBlockingQueue.take();
                RLog.i(TAG, "musicBlockingQueue take:" + this.musicBlockingQueue.size());
                searchMusicList();
                Thread.sleep(10000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(String str) {
        str.hashCode();
        switch (str) {
            case "android.intent.action.MEDIA_MOUNTED":
                USBCopyDataManager.get().startUSBCopyDataEx();
                break;
            case "android.intent.action.MEDIA_UNMOUNTED":
            case "android.intent.action.MEDIA_EJECT":
                USBCopyDataManager.get().endUSBCopyData();
                break;
        }
    }

    public void searchMusicList() {
        ArkAssistant.get().notifyContentChanged(ContentType.MUSIC);
        LocalMediaManager.get().searchUsbMedia();
        LocalMediaManager.get().searchSdcardMedia();
        LocalMediaManager.get().searchLocalMedia();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.ASYNC)
    public void onMessage(AudioEvent audioEvent) {
        int process = audioEvent.getProcess();
        if (process == 0) {
            RLog.e(TAG, "AudioEvent 0");
            if (this.musicBlockingQueue.offer(1)) {
                RLog.i(TAG, "收到ContentObserver媒体库改变------开始查询媒体库");
                RLog.i(TAG, "ContentObserver Array size:" + this.musicBlockingQueue.size());
                return;
            }
            return;
        }
        if (process == 1) {
            ArkAssistant.get().notifyContentChanged(ContentType.APP);
            return;
        }
        if (process == 2) {
            if (SpotifyManager.get().spotifyCountDown >= 10 || !SystemSettingManager.get().getPowerCtlStatus()) {
                return;
            }
            SpotifyManager.get().init(new CountDownLatch(1));
            return;
        }
        if (process == 3) {
            if (this.musicBlockingQueue.size() > 1) {
                this.musicBlockingQueue.clear();
                this.musicBlockingQueue.offer(1);
                return;
            }
            return;
        }
        if (process == 5 && AmazonManager.get().amazonCountDown < 5 && SystemSettingManager.get().getPowerCtlStatus()) {
            AmazonManager.get().init(null);
        }
    }

    @Subscribe(priority = 1, sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessage(SystemStatusEvent systemStatusEvent) {
        List<VoiceLanuageDp> list = null;
        switch (systemStatusEvent.getProcess()) {
            case 0:
                try {
                    RLog.i(TAG, "SystemStatusEvent : 0");
                    SpotifyManager.get().init(new CountDownLatch(1));
                    AmazonManager.get().init(null);
                    VoiceAssistantManager.get().initLanguageDatas();
                    VoiceAssistantManager.get().initVoiceAssistant();
                    KaraokeManager.get().init();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                RLog.i(TAG, "SystemStatusEvent : 1");
                KaraokeManager.get().uninit();
                VoiceAssistantManager.get().destroyVoiceAssistant();
                break;
            case 2:
                RLog.e(TAG, "SystemStatusEvent : 2");
                File file = new File(systemStatusEvent.getText() + "_bk/");
                File file2 = new File(systemStatusEvent.getText());
                RLog.e(TAG, "dst " + file + " src " + file2);
                if (file.exists()) {
                    DataUtils.deleteDir(file2);
                    file.renameTo(file2);
                    RLog.e(TAG, file + " backup restore to " + file2);
                }
                VoiceAssistantManager.get().reinit();
                break;
            case 3:
                VoiceAssistantManager.get().reinit();
                break;
            case 4:
                VoiceAssistantManager.get().setAutoBatchStart(true);
                VoiceAssistantManager.get().setAudioRecordStart(false);
                VoiceAssistantManager.get().reinit();
                break;
            case 5:
                VoiceAssistantManager.get().setAutoBatchStart(false);
                VoiceAssistantManager.get().setAudioRecordStart(false);
                VoiceAssistantManager.get().reinit();
                break;
            case 6:
                VoiceAssistantManager.get().setAudioRecordStart(true);
                VoiceAssistantManager.get().setAutoBatchStart(false);
                VoiceAssistantManager.get().reinit();
                break;
            case 7:
                VoiceAssistantManager.get().setAudioRecordStart(false);
                VoiceAssistantManager.get().setAutoBatchStart(false);
                VoiceAssistantManager.get().reinit();
                break;
            case 8:
                if (VoiceAssistantManager.get().isAutoBatchStart()) {
                    VoiceAssistantManager.get().setAsrDialogList(systemStatusEvent.getText());
                }
                break;
            case 9:
                if (systemStatusEvent.getText() != null && !systemStatusEvent.getText().isEmpty()) {
                    RLog.e(TAG, "statusEvent process =" + systemStatusEvent.getProcess());
                    try {
                        list = (List) VoiceAssistantManager.gson.fromJson(systemStatusEvent.getText(), new TypeToken<ArrayList<VoiceLanuageDp>>() { // from class: com.byd.vrassistant.NeuVoiceService.1
                        }.getType());
                    } catch (JsonSyntaxException e2) {
                        e2.printStackTrace();
                    }
                    if (list != null && list.size() > 0) {
                        list.forEach(new Consumer() { // from class: com.byd.vrassistant.NeuVoiceService$$ExternalSyntheticLambda0
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                RLog.e(NeuVoiceService.TAG, obj.toString());
                            }
                        });
                        USBCopyDataManager.get().startOTATask(list);
                        break;
                    }
                }
                break;
            case 10:
                VoiceAssistantManager.get().setAudioExportLog(true);
                VoiceAssistantManager.get().reinit();
                break;
            case 11:
                VoiceAssistantManager.get().setAudioExportLog(false);
                VoiceAssistantManager.get().reinit();
                break;
            case 12:
                VoiceAssistantManager.get().initAudioWakeupCommands();
                VoiceAssistantManager.get().initVoiceAcvtiveZone();
                VoiceAssistantManager.get().processVoiceLockZone();
                VoiceAssistantManager.get().clearAllView();
                VoiceAssistantManager.get().stopVoiceAssistant();
                VoiceAssistantManager.get().startVoiceAssistant();
                break;
            case 13:
                if (!systemStatusEvent.getText().isEmpty()) {
                    VoiceAssistantManager.get().recordDataPackaProcessEx(DataRecordEvent.OTA_BACKDEL.name(), systemStatusEvent.getText());
                }
                VoiceAssistantManager.get().initLanguageDatas();
                VoiceAssistantManager.get().setLanguage(VoiceAssistantManager.get().getLanguage());
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(TtsEvent ttsEvent) {
        IPrompter prompter = VoiceAssistantManager.get().getPrompter();
        if (prompter != null) {
            if (ttsEvent.getProcess() == 1) {
                prompter.stop();
            } else {
                prompter.play(ttsEvent.getText(), false, new PrompterCallback() { // from class: com.byd.vrassistant.NeuVoiceService.2
                    @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
                    public void onPlayStateChanged(PlayState playState) {
                        RLog.d(NeuVoiceService.TAG, "playState = " + playState.name());
                    }

                    @Override // cerence.ark.assistant.framework.prompter.PrompterCallback
                    public void onError(PrompterError prompterError) {
                        RLog.d(NeuVoiceService.TAG, "prompterError = " + prompterError.name());
                    }
                });
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessage(VRAudioTrackNullBean vRAudioTrackNullBean) {
        if (SystemSettingManager.get().getHUForbidStatus() == 8) {
            return;
        }
        if (vRAudioTrackNullBean.isStart()) {
            VoiceServiceUtils.startSendNullTrack();
        } else {
            VoiceServiceUtils.endSendNullTrack();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(FinalOperation finalOperation) {
        if (finalOperation.isToast()) {
            VrToastUtils.show(finalOperation.getAction());
            return;
        }
        if (!finalOperation.getAction().equals("PRIVACY") || SystemStateUtils.isPanoramicStart()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.byd.vrsettings");
        if (!BydVoiceApplication.isApplicationForeground(arrayList)) {
            SystemSettingManager.get().openScreenByName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity");
        }
        BydVoiceApplication.getContext().sendBroadcast(new Intent(Constant.ACTION_VOICE_SHOW_VOICEGUIDE));
        RLog.d(TAG, "onNotification: middle");
        VoiceAssistantManager.get().sendExitNotification();
        RLog.d(TAG, "onNotification: end");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(NavigationEvent navigationEvent) {
        boolean zIsReadyForBinding = navigationEvent.isReadyForBinding();
        int type = navigationEvent.getType();
        RLog.i(TAG, "onMessage: isReadyForBinding == " + zIsReadyForBinding + " , type == " + type);
        if (type == 0) {
            if (navigationEvent.isReadyForBinding()) {
                bindNaviService();
            }
        } else if (type == 1) {
            bindNaviService();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(VRPowerStateBean vRPowerStateBean) {
        if (vRPowerStateBean.isLock()) {
            VoiceAssistantManager.get().sendCancelPTTEvent();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(VoiceSateEvent voiceSateEvent) {
        RLog.e(TAG, "started:" + voiceSateEvent.getVoiceStarted());
        ViewStateManger.get().setVoiceStateEvent(voiceSateEvent);
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Locale locale = Locale.getDefault();
        if (!locale.equals(VoiceAssistantManager.get().getCountryLocale())) {
            VoiceAssistantManager.get().setCountryLocale(locale);
            ContextHolder.updateSysLocale(configuration.locale);
            String language = locale.getLanguage();
            String string = Locale.getDefault().toString();
            RLog.e(TAG, "language:" + language + ",local:" + string + ",country:" + getResources().getConfiguration().locale.getCountry());
            if (SPUtils.getInstance().getString(Constant.OTA_KEY_CUR_LANGUAGE, "").isEmpty()) {
                VoiceAssistantManager.get().setFailCountDown(0);
                RLog.e(TAG, "reinit：" + string);
                VoiceAssistantManager.get().reinit();
            }
            AppHelper.getInstance().loadAppLabel();
        }
        if (configuration.orientation == 2) {
            RLog.i(TAG, "onConfigurationChanged: horizontal");
            this.commonViewModel.setScreenMode(ScreenMode.LANDSCAPE);
        } else {
            RLog.i(TAG, "onConfigurationChanged: vertical");
            this.commonViewModel.setScreenMode(ScreenMode.PORTRAIT);
        }
        RLog.e(TAG, "onConfigurationChanged_sys_screen_mode==" + SystemSettingManager.get().getSkinType());
        int i = configuration.uiMode & 48;
        RLog.i(TAG, "onConfigurationChanged: nightMode==" + i);
        if (i == 32) {
            RLog.i(TAG, "onConfigurationChanged: Night Mode.");
            this.commonViewModel.setSkinType(2);
        } else {
            RLog.i(TAG, "onConfigurationChanged: Day Mode.");
            this.commonViewModel.setSkinType(1);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        RLog.e(TAG, "NeuVoiceService2 onCreate...  AppVersion[S.8.12.4_sop]");
        getPackageManager().setApplicationEnabledSetting("com.byd.vrsettings", 1, 1);
        getPackageManager().setApplicationEnabledSetting(BuildConfig.APPLICATION_ID, 1, 1);
        this.searchListThread.setDaemon(true);
        this.searchListThread.setName("SearchMusicList");
        this.searchListThread.start();
        ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("channel_vrapp", "NeuVoiceService", 4));
        startForeground(1, new Notification.Builder(this, "channel_vrapp").build());
        EventBus.getDefault().register(this);
        VoiceServiceUtils.init(this);
        ActiveManager.get().active();
        USBCopyDataManager.get().init();
        this.settingObserver = new SettingObserver(new Handler(new Handler.Callback() { // from class: com.byd.vrassistant.NeuVoiceService$$ExternalSyntheticLambda2
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return NeuVoiceService.lambda$onCreate$2(message);
            }
        }));
        this.musicObserver = new MusicObserver(new Handler(new Handler.Callback() { // from class: com.byd.vrassistant.NeuVoiceService$$ExternalSyntheticLambda3
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return NeuVoiceService.lambda$onCreate$3(message);
            }
        }));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(HardKeyReceiverEx.ACTION_MEDIA_VOICE_BUTTON);
        registerReceiver(new HardKeyReceiverEx(), intentFilter);
        this.screenOffReceiver = new ScreenOffReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(ScreenOffReceiver.ACTION_SCREEN_OFF);
        intentFilter2.addAction(ScreenOffReceiver.EXIT_AUDIO_SCREEN_ACTION);
        intentFilter2.addAction(ScreenOffReceiver.ACTION_POWERSCREEN_ENTER);
        registerReceiver(this.screenOffReceiver, intentFilter2);
        this.panoStartReceiver = new PanoStartReceiver();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction(Constant.ACTION_PANO_START);
        registerReceiver(this.panoStartReceiver, intentFilter3);
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction(Constant.ACTION_REQUEST_MIC);
        intentFilter4.addAction(Constant.ACTION_RELEASE_MIC);
        intentFilter4.addAction(Constant.ACTION_AA_OPEN);
        AudioMicReceiver audioMicReceiver = new AudioMicReceiver();
        this.mAudioMicReceiver = audioMicReceiver;
        registerReceiver(audioMicReceiver, intentFilter4);
        IntentFilter intentFilter5 = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        HomeListenerReceiver homeListenerReceiver = new HomeListenerReceiver();
        this.mHomeListenerReceiver = homeListenerReceiver;
        registerReceiver(homeListenerReceiver, intentFilter5);
        this.factoryDataPackCheckReceiver = new FactoryDataPackCheckReceiver();
        IntentFilter intentFilter6 = new IntentFilter();
        intentFilter6.addAction(Constant.ACTION_FACTORY_VOICE_DP_CHECK);
        registerReceiver(this.factoryDataPackCheckReceiver, intentFilter6);
        this.power8155Receiver = new Power8155Receiver();
        IntentFilter intentFilter7 = new IntentFilter();
        intentFilter7.addAction(Constant.ACTION_CAR_POWER_STATE);
        registerReceiver(this.power8155Receiver, intentFilter7);
        this.otaMode8155Receiver = new OtaMode8155Receiver();
        IntentFilter intentFilter8 = new IntentFilter();
        intentFilter8.addAction(OtaMode8155Receiver.ACTION_ENTER_OTA_MODEL);
        registerReceiver(this.otaMode8155Receiver, intentFilter8);
        this.factoryCheck8155Receiver = new FactoryCheck8155Receiver();
        IntentFilter intentFilter9 = new IntentFilter();
        intentFilter9.addAction(Constant.ACTION_FACTORY_VOICE_8155);
        registerReceiver(this.factoryCheck8155Receiver, intentFilter9);
        LocalMediaManager.get().init();
        getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.settingObserver);
        getContentResolver().registerContentObserver(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true, this.musicObserver);
        try {
            ViewManager.get().init(this.viewManagerInitCenter.getConfiguration());
            this.naviConnection = new ServiceConnection() { // from class: com.byd.vrassistant.NeuVoiceService.3
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    RLog.i(NeuVoiceService.TAG, "onServiceConnected_name==" + componentName);
                    NaviHelper.getInstance().setNaviService(INaviService.Stub.asInterface(iBinder));
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    RLog.i(NeuVoiceService.TAG, "onServiceDisconnected_name==" + componentName);
                    NaviHelper.getInstance().destroyNaviService();
                }
            };
            bindNaviService();
            VoiceAssistantManager.get().initPrivacyPolicy(false);
            this.appStateReceiver = new AppStateReceiver();
            IntentFilter intentFilter10 = new IntentFilter();
            intentFilter10.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter10.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter10.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter10.addAction("android.intent.action.MY_PACKAGE_REPLACED");
            intentFilter10.addAction("android.intent.action.INSTALL_PACKAGE");
            intentFilter10.addDataScheme(MediaControl.PARAM_PACKAGE);
            registerReceiver(this.appStateReceiver, intentFilter10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        RLog.v(TAG, "onStartCommand startId=" + i2);
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.power8155Receiver);
        USBCopyDataManager.get().uninit();
        EventBus.getDefault().unregister(this);
        VoiceAssistantManager.get().uninit();
        SystemSettingManager.get().uninit();
        SpotifyManager.get().uninit();
        AmazonManager.get().uninit();
        KaraokeManager.get().uninit();
        ActiveManager.get().forceQuit();
        ViewManager.get().unInit();
        ZoomCarHelper.getInstance().unRegisterZoomReceiver();
        unregisterReceiver(this.mAudioMicReceiver);
        unregisterReceiver(this.mHomeListenerReceiver);
        unregisterReceiver(this.mContactsReceiver);
        unregisterReceiver(this.screenOffReceiver);
        unregisterReceiver(this.panoStartReceiver);
        unregisterReceiver(this.appStateReceiver);
        unbindService(this.naviConnection);
    }

    private void registerZoomCarReceiver() {
        ZoomCarHelper.getInstance().registerZoomReceiver();
    }

    private void bindNaviService() {
        RLog.i(TAG, "bindNaviService...");
        String countryCode = CountryCodeUtil.getInstance().getCountryCode(BydVoiceApplication.getContext());
        String strCodeToPackage = NavUtil.codeToPackage(countryCode);
        RLog.i(TAG, "countryCode==" + countryCode + " , navPackageName==" + strCodeToPackage);
        Intent intent = new Intent();
        if (Constant.TELENAV_NAVIPACKAGE.equals(strCodeToPackage)) {
            intent.setComponent(new ComponentName(strCodeToPackage, Constant.TELENAV_NAVISERVICE));
            RLog.i(TAG, "bindNaviService: bindService == " + bindService(intent, this.naviConnection, 1));
            return;
        }
        if (Constant.NEU_NAVIPACKAGE.equals(strCodeToPackage)) {
            intent.setComponent(new ComponentName(strCodeToPackage, Constant.NEU_NAVISERVICE));
            RLog.i(TAG, "bindNaviService: bindService == " + bindService(intent, this.naviConnection, 1));
            return;
        }
        if (Constant.YINDUNAV_NAVIPACKAGE.equals(strCodeToPackage)) {
            intent.setComponent(new ComponentName(strCodeToPackage, Constant.YINDUNAV_NAVISERVICE));
            RLog.i(TAG, "bindNaviService: bindService == " + bindService(intent, this.naviConnection, 1));
            return;
        }
        if (Constant.ZAMP_NAVIPACKAGE.equals(strCodeToPackage)) {
            intent.setComponent(new ComponentName(strCodeToPackage, Constant.ZAMP_NAVISERVICE));
            RLog.i(TAG, "bindNaviService: bindService == " + bindService(intent, this.naviConnection, 1));
            return;
        }
        if (Constant.GAODE_NAVIPACKAGE.equals(strCodeToPackage)) {
            intent.setComponent(new ComponentName(strCodeToPackage, Constant.GAODE_NAVISERVICE));
            RLog.i(TAG, "bindNaviService: bindService == " + bindService(intent, this.naviConnection, 1));
        } else if (Constant.KOR_NAVIPACKAGE.equals(strCodeToPackage)) {
            intent.setComponent(new ComponentName(strCodeToPackage, Constant.KOR_NAVISERVICE));
            RLog.i(TAG, "bindNaviService: bindService == " + bindService(intent, this.naviConnection, 1));
        } else {
            try {
                intent.setComponent(new ComponentName(Constant.NEU_NAVIPACKAGE, Constant.NEU_NAVISERVICE));
                RLog.i(TAG, "bindNaviService: bindService NEU == " + bindService(intent, this.naviConnection, 1));
            } catch (Exception e) {
                RLog.i(TAG, "bindNaviService: bindService NEU error == " + e.getMessage());
            }
        }
    }

    private static boolean contains(int[] iArr, int i) {
        if (iArr.length == 0) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        RLog.i(TAG, "NeuVoiceService onBind ");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
