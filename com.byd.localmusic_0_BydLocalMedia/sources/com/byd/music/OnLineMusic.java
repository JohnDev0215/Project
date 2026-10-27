package com.byd.music;

import android.app.Application;
import android.app.blob.XmlTags;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.power.AbsBYDAutoPowerListener;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.showcarmode.BYDAutoShowCarDevice;
import android.hardware.bydauto.showcarmode.IShowCarModelChangeListener;
import android.media.AudioFocusInfo;
import android.media.AudioSystem;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.TimedRemoteCaller;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.internal.content.NativeLibraryHelper;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CollectionUtils;
import com.blankj.utilcode.util.ProcessUtils;
import com.blankj.utilcode.util.SPStaticUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.ThirdSDK.Spotify.SpotifyManager;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.ThirdSDK.amazon.AmazonManager;
import com.byd.music.ThirdSDK.fce.CommonFceManager;
import com.byd.music.ThirdSDK.fce.FceUtil;
import com.byd.music.activity.main.MediaMainActivity;
import com.byd.music.api.ApiMethod;
import com.byd.music.base.BaseActivity;
import com.byd.music.beans.ApiChangeBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.AudioFocusManager;
import com.byd.music.constant.SPConstants;
import com.byd.music.crashHandle.CrashHandler;
import com.byd.music.db.BoxManager;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.bluetooth.BlueToothFragment;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.fragment.localMusic.ui.LocalMusicFragment;
import com.byd.music.fragment.localMusic.ui.LocalSDFragment;
import com.byd.music.fragment.localMusic.ui.LocalUsbFragment;
import com.byd.music.fragment.localMusic.utils.LocalMusicUtils;
import com.byd.music.fragment.radio.AMFragment;
import com.byd.music.fragment.radio.DABFragment;
import com.byd.music.fragment.radio.FMFragment;
import com.byd.music.netObserver.NetStateChangeReceiver;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.style.LayoutPreWrapper;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.OrientationUtil;
import com.byd.music.utils.ShowCarModeUtil;
import com.byd.music.utils.StringSubUtil;
import com.byd.music.utils.mediahelper.MediaButtonReceiver;
import com.byd.music.utils.mediahelper.MusicWidgetProvider;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public class OnLineMusic {
    public static final String AUTO_VOICE_PKG = "com.byd.autovoice";
    public static final String AUTO_VOICE_PKG_CLASS = "com.byd.autovoice.broadreceiver.ThirdAppReceiver";
    public static String NAVI_DEST = null;
    public static int NAVI_TYPE = 0;
    public static final String TAG = "OnLineMusic";
    public static int VENDOR_CODE;
    public static boolean isBTMusicActive;
    public static boolean isBTViewActive;
    public static boolean isMemoryPlayOpen;
    private static int localChildType;
    private static AbsBYDAutoAudioListener mFocusListener;
    private static AbsBYDAutoPowerListener mPowerListener;
    public static long popupLastDismissTime;
    public static boolean specialFocusHold;
    public static Map<String, View> preLoadMap = new HashMap();
    public static int musicEngine = SPUtils.getInstance().getInt(SPConstants.KEY_MUSIC_API, -1);
    public static int radioEngine = SPUtils.getInstance().getInt(SPConstants.KEY_RADIO_TYPE, 7);
    public static String mediaFocusPackage = "";
    private static int buildVersion = 0;
    public static int NAVI_CHILD_SOURCE = -1;
    public static volatile boolean NEED_RESTORE = false;
    private static boolean alreadyAccOn = false;
    private static String[] spotifySlogan = {"Listening is everything", "聆听大千世界", "Escutar muda tudo", "Escuchar es todo", "Escuchar lo es todo", "Écouter, ça change tout", "اسمع تعرف"};
    public static boolean bootComplete = false;
    public static boolean bootWaitForBT = false;
    public static boolean bootWaitForNormal = false;
    public static boolean bootWaitForLocal = false;

    public static void init(final Application application) {
        LogUtil.e("OnlineMusic init enter with init engine = " + musicEngine);
        initInMain(application);
        AppExecutors.getInstance().networkIO().execute(new Runnable() { // from class: com.byd.music.-$$Lambda$OnLineMusic$hpKWBCs-HLEmbiZK2-fKk6N7rcQ
            @Override // java.lang.Runnable
            public final void run() {
                OnLineMusic.initInnerInThread(application);
            }
        });
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.byd.music.-$$Lambda$OnLineMusic$cwuHq47MLKJmaGFbKxfo0aq1xz4
            @Override // android.os.MessageQueue.IdleHandler
            public final boolean queueIdle() {
                return OnLineMusic.lambda$init$1();
            }
        });
    }

    static /* synthetic */ boolean lambda$init$1() {
        registerCustomListener();
        try {
            int i = musicEngine;
            if (i == 3 || i == 4 || i == 5) {
                initPlayMusic(false);
            } else {
                switch (i) {
                    case 105:
                    case 106:
                    case 107:
                    case 108:
                    case 109:
                    case 110:
                    case 111:
                    case 112:
                    case 113:
                    case 114:
                    case 115:
                    case 116:
                        if (ThirdManager.getInstance().FceEngineMap.get(Integer.valueOf(musicEngine)) == null) {
                            setDefaultEngine();
                        }
                        if (ThirdManager.getInstance().getFceManager() == null) {
                            ThirdManager.getInstance().setFceManager(new CommonFceManager());
                        }
                        ThirdManager.getInstance().getFceManager().reset(Utils.getApp(), ThirdManager.getInstance().FceEngineMap.get(Integer.valueOf(musicEngine)), "com.fce.onlinemedia.voice.CarMediaTransportService", musicEngine);
                        break;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
            LogUtil.e("initInMain: initPlayMusic error = " + th.getMessage());
        }
        CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        return false;
    }

    public static int getBuildType() {
        if (buildVersion == 0) {
            if (AppUtils.getAppVersionName().endsWith(String.valueOf(1))) {
                buildVersion = 1;
            } else if (AppUtils.getAppVersionName().endsWith(String.valueOf(2))) {
                buildVersion = 2;
            } else {
                buildVersion = 8;
            }
        }
        return buildVersion;
    }

    public static void setLocalMusicChildType(int i) {
        if (localChildType == i) {
            return;
        }
        localChildType = i;
        CurrentMediaInfoUtils.getInstance().updateForceAndNotify();
    }

    public static void deinit() {
        LogUtil.e("OnLineMusic deinit enter...");
        BYDAutoAudioDevice.getInstance(Utils.getApp()).unregisterListener(mFocusListener);
        BYDAutoBodyworkDevice.getInstance(Utils.getApp()).unregisterListener(mPowerListener);
        MusicPlayHelper.getInstance().getMusicPlayer().releaseMediaPlayer();
    }

    private static void initInMain(Application application) {
        if (ProcessUtils.isMainProcess()) {
            System.currentTimeMillis();
            Utils.init(application);
            BoxManager.get().initAppDB(application);
            LogUtil.d(TAG, "start initInMain");
            SPStaticUtils.setDefaultSPUtils(SPUtils.getInstance("byd_music"));
            String str = SystemProperties.get("ro.product.version.soc.name", String.valueOf(0));
            if (str.startsWith("51")) {
                VENDOR_CODE = 1;
            } else if (str.startsWith("56")) {
                VENDOR_CODE = 2;
            } else {
                LogUtil.e("SystemProperties soc.name ret = %s , fatal error!", str);
                LogUtil.e("SystemProperties soc.name ret = %s , fatal error!", str);
                LogUtil.e("SystemProperties soc.name ret = %s , fatal error!", str);
            }
            try {
                mPowerListener = new AbsBYDAutoPowerListener() { // from class: com.byd.music.OnLineMusic.1
                    @Override // android.hardware.bydauto.power.AbsBYDAutoPowerListener
                    public void onPowerSTRChanged(int i) {
                        LogUtil.e("onPowerSTRChanged state = " + i);
                        if (i == 4) {
                            boolean unused = OnLineMusic.alreadyAccOn = false;
                            MusicPlayHelper.getInstance().setMemoryPlay(false);
                            RadioServiceWrapper.getInstance().setRadioSTR(true);
                            if (OnLineMusic.musicEngine == 102 || OnLineMusic.musicEngine == 101) {
                                LogUtil.e("onPowerSTRChanged acc off with CP/AA, just return.");
                                return;
                            }
                            if (CurrentMediaInfoUtils.getInstance().getPlayingState()) {
                                CurrentMediaInfoUtils.getInstance().expectPlayingStatusShouldNotBeOverride = true;
                            }
                            if (OnLineMusic.musicEngine == 103) {
                                Utils.getApp().sendBroadcast(new Intent() { // from class: com.byd.music.OnLineMusic.1.1
                                    {
                                        setAction(SpotifyManager.StopPlayButPlayMemoryEnable);
                                        setPackage(SpotifyManager.SpotifyPKG);
                                    }
                                });
                                return;
                            }
                            CurrentMediaInfoUtils.getInstance().saveCurrentProgress();
                            MusicPlayHelper.getInstance().isAccOff = true;
                            ApiMethod.playOrStop(false);
                            MusicPlayHelper.getInstance().getMusicPlayer().releaseMediaPlayer();
                            return;
                        }
                        if (i == 8) {
                            if (OnLineMusic.musicEngine == 5 || OnLineMusic.musicEngine == 4 || OnLineMusic.musicEngine == 3) {
                                MusicPlayHelper.getInstance().saveCurLocalMusicEngineProgress();
                            }
                            MusicPlayHelper.getInstance().isAccOff = true;
                            return;
                        }
                        if (i != 10) {
                            if (i == 16) {
                                CurrentMediaInfoUtils.getInstance().updateForceAndNotify();
                                return;
                            }
                            if (i != 19) {
                                return;
                            }
                            boolean unused2 = OnLineMusic.alreadyAccOn = false;
                            MusicPlayHelper.getInstance().setMemoryPlay(false);
                            RadioServiceWrapper.getInstance().setRadioSTR(true);
                            if (OnLineMusic.musicEngine == 102) {
                                LogUtil.e("onPowerSTRChanged acc off with TYPE_CARPLAY, just return.");
                                return;
                            }
                            if (CurrentMediaInfoUtils.getInstance().getPlayingState()) {
                                CurrentMediaInfoUtils.getInstance().expectPlayingStatusShouldNotBeOverride = true;
                            }
                            if (OnLineMusic.musicEngine == 103) {
                                Utils.getApp().sendBroadcast(new Intent() { // from class: com.byd.music.OnLineMusic.1.2
                                    {
                                        setAction(SpotifyManager.StopPlayButPlayMemoryEnable);
                                        setPackage(SpotifyManager.SpotifyPKG);
                                    }
                                });
                                return;
                            }
                            MusicPlayHelper.getInstance().isAccOff = true;
                            ApiMethod.playOrStop(false);
                            MusicPlayHelper.getInstance().getMusicPlayer().releaseMediaPlayer();
                            return;
                        }
                        LogUtil.e("onPowerSTRChanged IVI_PM_DISPLAY_ON, alreadyAccOn = " + OnLineMusic.alreadyAccOn);
                        if (OnLineMusic.alreadyAccOn) {
                            return;
                        }
                        boolean unused3 = OnLineMusic.alreadyAccOn = true;
                        MusicPlayHelper.getInstance().isAccOff = false;
                        RadioServiceWrapper.getInstance().setRadioSTR(false);
                        if (Settings.System.getInt(Utils.getApp().getContentResolver(), "byd_memory_playback_state", 1) > 0) {
                            LogUtil.d("acc on -> byd_memory_playback_state = true");
                            OnLineMusic.isMemoryPlayOpen = true;
                            if (ShowCarModeUtil.isShowCarModeStyle()) {
                                OnLineMusic.onShowCarMusicAutoPlay();
                                return;
                            } else {
                                AppExecutors.getInstance().mainThreadDelay().executeDelay(new AnonymousClass3(), 500L);
                                return;
                            }
                        }
                        LogUtil.d("byd_memory_playback_state = false");
                        OnLineMusic.isMemoryPlayOpen = false;
                        OnLineMusic.onShowCarMusicAutoPlay();
                    }

                    /* JADX INFO: renamed from: com.byd.music.OnLineMusic$1$3, reason: invalid class name */
                    class AnonymousClass3 implements Runnable {
                        AnonymousClass3() {
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (!OnLineMusic.isHigherFocusHolding()) {
                                OnLineMusic.memoryPlayStart();
                            } else {
                                AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.-$$Lambda$NITpMHZrWUiNVv0xLmesXltNX2I
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        this.f$0.run();
                                    }
                                }, 500L);
                            }
                        }
                    }
                };
                BYDAutoPowerDevice.getInstance(Utils.getApp()).registerListener(mPowerListener);
                LogUtil.e("BYD_MUSIC register BYDAutoPowerDevice finish.");
            } catch (Throwable th) {
                LogUtil.e("BYD_MUSIC register BYDAutoPowerDevice error " + th.getMessage());
                th.printStackTrace();
            }
            bootCompleteRunnable();
            MediaButtonReceiver mediaButtonReceiver = new MediaButtonReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_MEDIA_BUTTON);
            intentFilter.addAction(MediaButtonReceiver.ACTION_REQUEST_FOR_MEDIA_INFO);
            intentFilter.addAction(MediaButtonReceiver.ACTION_SWITCH_MEDIA_MODE);
            intentFilter.addAction(MediaButtonReceiver.ACTION_ENTER_OTA_MODEL);
            intentFilter.addAction(MediaButtonReceiver.ACTION_EXIT_OTA_MODEL);
            intentFilter.addAction(Intent.ACTION_LOCALE_CHANGED);
            intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
            intentFilter.addAction(Intent.ACTION_SHUTDOWN);
            intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
            intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
            intentFilter.addDataScheme("package");
            Utils.getApp().registerReceiver(mediaButtonReceiver, intentFilter);
            RadioServiceWrapper.getInstance().bind(Utils.getApp());
            if (CurrentMediaInfoUtils.getInstance().checkCurrentMusicInfoEntityExit()) {
                LogUtil.d("OnLineMusic initInMain curMusic has entity!");
            }
            LogUtil.e("setMediaSessionActive in Online init");
            AudioFocusManager.getInstance().setMediaSessionActive(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initInnerInThread(Application application) {
        int iIntValue;
        LogUtil.e("initInnerInThread: enter...");
        SystemProperties.set("log.tag.Glide", "ERROR");
        SystemProperties.set("log.tag.HttpUrlFetcher", "ERROR");
        SystemProperties.set("log.tag.LocalUriFetcher", "ERROR");
        ThirdManager.getInstance().init();
        if (musicEngine == 102 && !ThirdManager.isCarplayConnected()) {
            setDefaultEngine();
        }
        if (musicEngine == 101 && !ThirdManager.isAndroidAutoConnected()) {
            setDefaultEngine();
        }
        String string = SPUtils.getInstance().getString("saved_version", "");
        LogUtil.e("initInnerInThread: saved_version = " + string + ", LogUtil.COMMON_TAG = " + LogUtil.COMMON_TAG);
        if (!TextUtils.equals(string, LogUtil.COMMON_TAG)) {
            SPUtils.getInstance().put("saved_country_number", -1);
            SPUtils.getInstance().put("saved_version", LogUtil.COMMON_TAG);
            LogUtil.e("initInnerInThread: update saved_version to " + LogUtil.COMMON_TAG);
        }
        try {
            iIntValue = Integer.valueOf(SystemProperties.get("persist.vendor.byd.cfg.huc.config.area")).intValue();
            LogUtil.e("initInnerInThread: read country number from property = " + iIntValue);
        } catch (Throwable th) {
            LogUtil.e("initInnerInThread: read country number error = " + th.getMessage());
            th.printStackTrace();
            iIntValue = 81;
        }
        int i = SPUtils.getInstance().getInt("saved_country_number", -1);
        LogUtil.e("initInnerInThread: saved_country_number = " + i);
        if (i != iIntValue) {
            String strReloadCountryCode = reloadCountryCode(iIntValue);
            resetMediaInfoConfig(strReloadCountryCode);
            if (RadioServiceWrapper.getInstance().getFMEnable() || RadioServiceWrapper.getInstance().getAMEnable()) {
                resetFMAMConfig(strReloadCountryCode, iIntValue);
            }
            SPUtils.getInstance().put("saved_country_number", iIntValue);
            LogUtil.e("saved country number finish.");
        }
        preloadFrontPageLayout(application);
        NetStateChangeReceiver.registerReceiver(application);
        SPUtils.getInstance().put(SPConstants.LOCAL_MUSIC_SCAN_KEY, false);
        CrashHandler.setCrashHandler(true);
        LogUtil.e("initInnerInThread: enter...111");
        MusicPlayHelper.getInstance().isNeedPlay = CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState();
        checkBluetoothMusicActive();
        if (checkIsFirstStart()) {
            SPUtils.getInstance().put("killedByMultiTaskTime", 0L);
            LogUtil.d("bluetoothTime : killedByMultiTaskTime = ", "0, due to first start");
        } else {
            try {
                initLocalMusic(true);
            } finally {
                try {
                } finally {
                }
            }
            LogUtil.e("initInnerInThread: enter...333");
        }
        CurrentMediaInfoUtils.getInstance().deleteAlbumCache();
    }

    private static boolean checkIsFirstStart() {
        if (TextUtils.isEmpty(SystemProperties.get("sys.mediacenter.status"))) {
            LogUtil.e("first start, yes.");
            SystemProperties.set("sys.mediacenter.status", "byd_music");
            return true;
        }
        LogUtil.e("first start, no no no.");
        return false;
    }

    private static String reloadCountryCode(int i) {
        LogUtil.e("reloadCountryCode enter with countryNumber = " + i);
        try {
            InputStream inputStreamOpen = Utils.getApp().getResources().getAssets().open("countrycode.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            String str = new String(bArr);
            inputStreamOpen.close();
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject.getString("number").equals(String.valueOf(i))) {
                    return jSONObject.getString("countrycode");
                }
            }
            return "45";
        } catch (IOException e) {
            LogUtil.e("readJasonConfig error!");
            e.printStackTrace();
            return "45";
        } catch (JSONException e2) {
            LogUtil.e("readJasonConfig error!");
            e2.printStackTrace();
            return "45";
        }
    }

    private static void resetMediaInfoConfig(String str) {
        LogUtil.e("resetMediaInfoConfig enter with systemCountryCode = " + str);
        try {
            InputStream inputStreamOpen = Utils.getApp().getResources().getAssets().open("mediainfoconfig.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            String str2 = new String(bArr);
            inputStreamOpen.close();
            JSONArray jSONArray = new JSONArray(str2);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getString("countrycode").equals(str)) {
                    ThirdManager.getInstance().setSpotifyEnable(FceUtil.isPKGInstall(SpotifyManager.SpotifyPKG));
                    ThirdManager.getInstance().setAmazonEnable(FceUtil.isPKGInstall(AmazonManager.AmazonPKG));
                    boolean z = true;
                    RadioServiceWrapper.getInstance().setDABEnable(Integer.valueOf(SystemProperties.get("persist.vendor.byd.cfg.huc.dab", "1")).intValue() == 0);
                    RadioServiceWrapper.getInstance().setAMEnable(jSONObject.optBoolean(XmlTags.TAG_ACCESS_MODE));
                    RadioServiceWrapper.getInstance().setFMEnable(jSONObject.optBoolean("fm"));
                    setDefaultEngine();
                    StringBuilder sb = new StringBuilder();
                    sb.append("resetMediaInfoConfig leave with spotify enable = ");
                    sb.append(FceUtil.isPKGInstall(SpotifyManager.SpotifyPKG));
                    sb.append(", amazon enable = ");
                    sb.append(FceUtil.isPKGInstall(AmazonManager.AmazonPKG));
                    sb.append(", dab enable = ");
                    if (Integer.valueOf(SystemProperties.get("persist.vendor.byd.cfg.huc.dab", "1")).intValue() != 0) {
                        z = false;
                    }
                    sb.append(z);
                    sb.append(", am = ");
                    sb.append(jSONObject.optBoolean(XmlTags.TAG_ACCESS_MODE));
                    sb.append(", fm = ");
                    sb.append(jSONObject.optBoolean("fm"));
                    LogUtil.e(sb.toString());
                }
            }
        } catch (IOException e) {
            LogUtil.e("readJasonConfig error!");
            e.printStackTrace();
        } catch (JSONException e2) {
            LogUtil.e("readJasonConfig error!");
            e2.printStackTrace();
        }
    }

    private static void resetFMAMConfig(String str, int i) {
        LogUtil.e("resetFMAMConfig enter with systemCountryCode = " + str);
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("resetFMAMConfig with str empty, just return.");
            return;
        }
        try {
            InputStream inputStreamOpen = Utils.getApp().getResources().getAssets().open("radioFreq.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            String str2 = new String(bArr);
            inputStreamOpen.close();
            JSONArray jSONArray = new JSONArray(str2);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject.getString("countrycode").equals(str)) {
                    String string = jSONObject.getString("am-step");
                    String string2 = jSONObject.getString("fm-step");
                    String[] strArrSplit = jSONObject.getString("am-range").split(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                    String[] strArrSplit2 = jSONObject.getString("fm-range").split(NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
                    RadioServiceWrapper.getInstance();
                    RadioServiceWrapper.resetFMAMConfig(Integer.valueOf(strArrSplit2[0]).intValue(), Integer.valueOf(strArrSplit2[1]).intValue(), Integer.valueOf(string2).intValue(), Integer.valueOf(strArrSplit[0]).intValue(), Integer.valueOf(strArrSplit[1]).intValue(), Integer.valueOf(string).intValue());
                }
            }
        } catch (IOException e) {
            LogUtil.e("readJasonConfig error!");
            e.printStackTrace();
        } catch (JSONException e2) {
            LogUtil.e("readJasonConfig error!");
            e2.printStackTrace();
        }
    }

    public static void initLocalMusic(final Boolean bool) {
        LocalMusicUtils.initMusicData(bool);
        MusicPlayHelper.getInstance().setIsSDPlug(Boolean.valueOf(SPUtils.getInstance().getBoolean(SPConstants.SP_EXISTED_SD, false)));
        MusicPlayHelper.getInstance().setIsUsbPlug(Boolean.valueOf(SPUtils.getInstance().getBoolean(SPConstants.SP_EXISTED_USB, false)));
        LogUtil.e("initInnerInThread: initLocalMusic.. savePlaying=" + CurrentMediaInfoUtils.getInstance().savePlaying);
        AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.OnLineMusic.2
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder sb = new StringBuilder("initLocalMusic ");
                long jCurrentTimeMillis = System.currentTimeMillis();
                OnLineMusic.initPlayMusic(bool.booleanValue());
                if (ShowCarModeUtil.isShowCarModeStyle()) {
                    sb.append("isShowCarModeStyle auto play");
                    if (!MusicPlayHelper.getInstance().isPlaying.get()) {
                        List<LocalMusicBean> localMusicByStorageType = LocalMusicDB.getLocalMusicByStorageType(0);
                        LocalMusicUtils.sortShowCarMusicList(localMusicByStorageType);
                        MusicPlayHelper.getInstance().setLocalMusicBeanList(localMusicByStorageType);
                        MusicPlayHelper.getInstance().autoPlayShowCar();
                    }
                }
                sb.append(" costTime= ");
                sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
                sb.append(" tName= ");
                sb.append(Thread.currentThread().getName());
                LogUtil.e(OnLineMusic.TAG, sb.toString());
            }
        });
    }

    public static void initPlayMusic(boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("initInnerInThread: initPlayMusic: isInternalStorage=");
        sb.append(z);
        sb.append(" musicEngine");
        sb.append(musicEngine);
        MusicPlayHelper.getInstance().restoreCurrentMusic();
        LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp();
        if (currentMusicFromSp == null) {
            sb.append(" spMusic null");
            LogUtil.e(sb.toString());
            return;
        }
        MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(currentMusicFromSp.getStorageType()));
        int i = musicEngine;
        if (i == 5 || i == 3 || i == 4) {
            String uri = currentMusicFromSp.getUri();
            Boolean boolValueOf = Boolean.valueOf(LocalMusicUtils.checkMusicFileExist(uri));
            sb.append(" spMusicPath=");
            sb.append(StringSubUtil.subString(uri));
            sb.append(" title=");
            sb.append(StringSubUtil.subString(currentMusicFromSp.getTitle()));
            sb.append(" isExistSpMusic=");
            sb.append(boolValueOf);
            sb.append(" NEED_RESTORE:");
            sb.append(NEED_RESTORE);
            if (boolValueOf.booleanValue()) {
                boolean z2 = MusicPlayHelper.getInstance().isNeedPlay;
                sb.append(" isNeedPlay=");
                sb.append(z2);
                MusicPlayHelper.getInstance().isNeedPlay = false;
                MusicPlayHelper.getInstance().setCurrentMusic(currentMusicFromSp);
                if (MusicPlayHelper.getInstance().currentLocalMusic.get() == null) {
                    MusicPlayHelper.getInstance().updateCurrentMusicInfo();
                }
            } else {
                MusicPlayHelper.getInstance().setCurrentMusic(null);
            }
            sb.append(" initData costTime= ");
            sb.append(System.currentTimeMillis() - jCurrentTimeMillis);
            sb.append(" tName= ");
            sb.append(Thread.currentThread().getName());
            LogUtil.e(sb.toString());
            return;
        }
        LogUtil.e(sb.toString());
    }

    public static boolean isAudioFocusAvailable() {
        if (CurrentMediaInfoUtils.getInstance().currentMusic.get() == null || CurrentMediaInfoUtils.getInstance().currentMusic.get().getEntity() == null) {
            LogUtil.d(TAG, "isAudioFocusAvailable false, currentMusic or getEntity is null");
            return false;
        }
        if (TextUtils.equals(CurrentMediaInfoUtils.getInstance().currentMusic.get().getMusicName(), Utils.getApp().getString(R.string.unknown_music)) || (musicEngine == 9 && RadioServiceWrapper.getInstance().mCurrentRadio.getValue().getDAB_SID() == 0)) {
            LogUtil.d(TAG, "isAudioFocusAvailable false, getMusicName unknown");
            return false;
        }
        int i = musicEngine;
        if (i == 103) {
            if (!SpotifyManager.TAG_SPOTIFY_ONACTIVE) {
                LogUtil.d(TAG, "isAudioFocusAvailable false, TAG_SPOTIFY_ONACTIVE false");
                return false;
            }
            for (String str : spotifySlogan) {
                if (TextUtils.equals(CurrentMediaInfoUtils.getInstance().currentMusic.get().getMusicName(), str)) {
                    LogUtil.d(TAG, "isAudioFocusAvailable false, spotifySlogan equals");
                    return false;
                }
            }
            return true;
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
                if (!AudioFocusManager.getInstance().isHoldAudioFocus() && !com.byd.musiclib.BuildConfig.APPLICATION_ID.equals(mediaFocusPackage)) {
                    LogUtil.d(TAG, "isAudioFocusAvailable false, isHoldAudioFocus or focusPackage false");
                    break;
                }
                break;
            case 6:
                if (!BluetoothViewModel.TAG_BT_ONACTIVE) {
                    LogUtil.d(TAG, "isAudioFocusAvailable false, TAG_BT_ONACTIVE false");
                }
                break;
        }
        return false;
    }

    private static void registerCustomListener() {
        try {
            mFocusListener = new AbsBYDAutoAudioListener() { // from class: com.byd.music.OnLineMusic.3
                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                /* JADX WARN: Removed duplicated region for block: B:34:0x0094  */
                @Override // android.hardware.bydauto.audio.AbsBYDAutoAudioListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void onAudioFocusChanged(int r8, java.util.List<android.media.AudioFocusInfo> r9) {
                    /*
                        Method dump skipped, instruction units count: 254
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.byd.music.OnLineMusic.AnonymousClass3.onAudioFocusChanged(int, java.util.List):void");
                }
            };
            BYDAutoAudioDevice.getInstance(Utils.getApp()).registerListener(mFocusListener);
            BYDAutoAudioDevice.getInstance(Utils.getApp()).registerConnectionListener(new IServiceConnectionListener() { // from class: com.byd.music.OnLineMusic.4
                @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
                public void onServiceDisconnected(boolean z) {
                }

                @Override // android.hardware.bydauto.audio.tsimpl.IServiceConnectionListener
                public void onServiceConnected(boolean z) {
                    if (BYDAutoAudioDevice.getInstance(Utils.getApp()).getCurAudioFocusInfo(0) == null || BYDAutoAudioDevice.getInstance(Utils.getApp()).getCurAudioFocusInfo(0).size() <= 0) {
                        return;
                    }
                    for (AudioFocusInfo audioFocusInfo : BYDAutoAudioDevice.getInstance(Utils.getApp()).getCurAudioFocusInfo(0)) {
                        if (audioFocusInfo.getAttributes().getUsage() == 24 || audioFocusInfo.getAttributes().getUsage() == 33 || audioFocusInfo.getAttributes().getUsage() == 1) {
                            OnLineMusic.mediaFocusPackage = audioFocusInfo.getPackageName();
                            LogUtil.e("onServiceConnected BYDAutoAudioDevice getCurAudioFocusInfo audioFocusPackage = " + OnLineMusic.mediaFocusPackage);
                            return;
                        }
                    }
                }
            });
            LogUtil.e("BYD_MUSIC register BYDAutoAudioDevice finish.");
        } catch (Throwable th) {
            LogUtil.e("BYD_MUSIC register BYDAutoAudioDevice error " + th.getMessage());
            th.printStackTrace();
        }
        try {
            BYDAutoInstrumentDevice.getInstance(Utils.getApp()).registerListener(new AbsBYDAutoInstrumentListener() { // from class: com.byd.music.OnLineMusic.5
                @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
                public void onMusicProgressChanged(int i, int i2) {
                    super.onMusicProgressChanged(i, i2);
                    LogUtil.d("AbsBYDAutoInstrumentListener", "onMusicProgressChanged type = " + i + ", state = " + i2);
                }

                @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
                public void onMusicControlStatusChanged(int i, int i2) {
                    super.onMusicControlStatusChanged(i, i2);
                    LogUtil.d("AbsBYDAutoInstrumentListener", "onMusicControlStatusChanged type = " + i + ", state = " + i2);
                    if (i2 == 1) {
                        ApiMethod.playOrStop(true);
                    } else {
                        if (i2 != 2) {
                            return;
                        }
                        ApiMethod.playOrStop(false);
                    }
                }

                @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
                public void onMusicChannelStatusChanged(int i, int i2) {
                    super.onMusicChannelStatusChanged(i, i2);
                    LogUtil.d("AbsBYDAutoInstrumentListener", "onMusicChannelStatusChanged type = " + i + ", state = " + i2);
                    if (i2 == 1) {
                        ApiMethod.playPre();
                    } else {
                        if (i2 != 2) {
                            return;
                        }
                        ApiMethod.playNext();
                    }
                }

                @Override // android.hardware.bydauto.instrument.AbsBYDAutoInstrumentListener
                public void onMusicResourceConfigChanged(int i, int i2) {
                    super.onMusicResourceConfigChanged(i, i2);
                    LogUtil.d("AbsBYDAutoInstrumentListener", "onMusicResourceConfigChanged type = " + i + ", state = " + i2);
                    try {
                        Utils.getApp().sendBroadcast(new Intent(MusicWidgetProvider.HIDE_MEDIA_POPUP));
                    } catch (Throwable th2) {
                        LogUtil.i("AbsBYDAutoInstrumentListener", "sendBroadcast e = " + th2.getMessage());
                    }
                    if (i2 == 1 && !DeviceInfoUtils.isInCall()) {
                        OnLineMusic.dummyModeSwitchFromRCS();
                    }
                }
            });
        } catch (Throwable th2) {
            LogUtil.e("BYD_MUSIC register AbsBYDAutoInstrumentListener error " + th2.getMessage());
        }
        try {
            BYDAutoShowCarDevice.getInstance(Utils.getApp()).registerListener(new IShowCarModelChangeListener() { // from class: com.byd.music.OnLineMusic.6
                @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
                public void showCarModelChange(int i) {
                    LogUtil.e("showCarModelChange state = " + i);
                    if (i == 1 || i == 2) {
                        ShowCarModeUtil.setCarMode(true);
                    } else {
                        if (i != 3) {
                            return;
                        }
                        ShowCarModeUtil.setCarMode(false);
                    }
                }

                @Override // android.hardware.bydauto.showcarmode.IShowCarModelChangeListener
                public void showCarGearChange(int i) {
                    LogUtil.e("showCarGearChange state = " + i);
                }
            });
        } catch (Throwable th3) {
            LogUtil.e("BYD_MUSIC register AbsBYDAutoInstrumentListener error " + th3.getMessage());
        }
        AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.OnLineMusic.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    int showCarModelType = BYDAutoShowCarDevice.getInstance(Utils.getApp()).getShowCarModelType();
                    if (showCarModelType == 1 || showCarModelType == 2) {
                        ShowCarModeUtil.setCarMode(true);
                    } else if (showCarModelType == 3) {
                        ShowCarModeUtil.setCarMode(false);
                    }
                } catch (Throwable unused) {
                }
            }
        }, TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void memoryPlayStart() {
        if (CurrentMediaInfoUtils.getInstance().getPlayingState() || !CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState()) {
            return;
        }
        int i = musicEngine;
        if (i == 103) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(SpotifyManager.SpotifyPKG, SpotifyManager.SpotifySERVICE));
                Utils.getApp().startService(intent);
                LogUtil.e("init start spotify service finish.");
                return;
            } catch (Throwable th) {
                LogUtil.e("init start spotify service failed.");
                th.printStackTrace();
                return;
            }
        }
        if (i == 104) {
            Intent intent2 = new Intent();
            intent2.setPackage(AmazonManager.AmazonPKG);
            intent2.setAction(AmazonManager.AmazonMemoryPlayAction);
            Utils.getApp().sendBroadcast(intent2);
            LogUtil.e("init start amazon broadcast finish.");
            return;
        }
        LogUtil.e("acc on restore  type = " + musicEngine + " memoryPlay = " + MusicPlayHelper.getInstance().isMemoryPlay() + " bootComplete = " + bootComplete);
        int i2 = musicEngine;
        if (i2 != 5 && i2 != 3 && i2 != 4) {
            if (i2 != 6) {
                if (bootComplete) {
                    ApiMethod.playOrStop(true);
                    return;
                } else {
                    bootWaitForNormal = true;
                    return;
                }
            }
            return;
        }
        if (bootComplete) {
            if (MusicPlayHelper.getInstance().isMemoryPlay()) {
                MusicPlayHelper.getInstance().memoryPlayAccOn();
            } else {
                setNeedRestore(true);
            }
            startLocalDelayRunnable();
            return;
        }
        bootWaitForLocal = true;
    }

    public static void preloadFrontPageLayout(final Application application) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() { // from class: com.byd.music.-$$Lambda$OnLineMusic$iM_7nP12ICC6CNOXiswuI2M_28Q
            @Override // java.lang.Runnable
            public final void run() {
                OnLineMusic.lambda$preloadFrontPageLayout$2(application);
            }
        });
    }

    static /* synthetic */ void lambda$preloadFrontPageLayout$2(Application application) {
        LogUtil.e("musicEngine = " + musicEngine);
        Boolean boolIsNight = DeviceInfoUtils.isNight();
        Boolean boolValueOf = Boolean.valueOf(OrientationUtil.isPortrait());
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(application, R.style.theme_Musiclib);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(contextThemeWrapper);
        LayoutPreWrapper.setLayoutPreWrapper(contextThemeWrapper, layoutInflaterFrom);
        View viewInflate = layoutInflaterFrom.inflate(R.layout.activity_media_main, (ViewGroup) null);
        preLoadMap.put(MediaMainActivity.class.getCanonicalName(), viewInflate);
        viewInflate.setTag(R.layout.activity_media_main, boolIsNight);
        LogUtil.d("preloadView: " + MediaMainActivity.class.getCanonicalName() + " has preload");
        switch (musicEngine) {
            case 3:
                View viewInflate2 = layoutInflaterFrom.inflate(R.layout.fragment_local_sd, (ViewGroup) null);
                viewInflate2.setTag(R.layout.activity_media_main, boolIsNight);
                viewInflate2.setTag(268435456, boolValueOf);
                preLoadMap.put(LocalSDFragment.class.getCanonicalName(), viewInflate2);
                LogUtil.d("preloadView: " + LocalSDFragment.class.getCanonicalName() + " has preload");
                break;
            case 4:
                View viewInflate3 = layoutInflaterFrom.inflate(R.layout.fragment_local_usb, (ViewGroup) null);
                viewInflate3.setTag(R.layout.activity_media_main, boolIsNight);
                viewInflate3.setTag(268435456, boolValueOf);
                preLoadMap.put(LocalUsbFragment.class.getCanonicalName(), viewInflate3);
                LogUtil.d("preloadView: " + LocalUsbFragment.class.getCanonicalName() + " has preload");
                break;
            case 5:
                View viewInflate4 = layoutInflaterFrom.inflate(R.layout.fragment_local_music, (ViewGroup) null);
                viewInflate4.setTag(R.layout.activity_media_main, boolIsNight);
                viewInflate4.setTag(268435456, boolValueOf);
                preLoadMap.put(LocalMusicFragment.class.getCanonicalName(), viewInflate4);
                LogUtil.d("preloadView: " + LocalMusicFragment.class.getCanonicalName() + " has preload");
                break;
            case 6:
                View viewInflate5 = layoutInflaterFrom.inflate(R.layout.fragment_music_bluetooth, (ViewGroup) null);
                preLoadMap.put(BlueToothFragment.class.getCanonicalName(), viewInflate5);
                viewInflate5.setTag(R.layout.activity_media_main, boolIsNight);
                viewInflate5.setTag(268435456, boolValueOf);
                LogUtil.d("preloadView: " + BlueToothFragment.class.getCanonicalName() + " has preload");
                break;
            case 7:
                View viewInflate6 = layoutInflaterFrom.inflate(R.layout.fragment_fm, (ViewGroup) null);
                viewInflate6.setTag(R.layout.activity_media_main, boolIsNight);
                viewInflate6.setTag(268435456, boolValueOf);
                preLoadMap.put(FMFragment.class.getCanonicalName(), viewInflate6);
                LogUtil.d("preloadView: " + FMFragment.class.getCanonicalName() + " has preload");
                break;
            case 8:
                View viewInflate7 = layoutInflaterFrom.inflate(R.layout.fragment_am, (ViewGroup) null);
                preLoadMap.put(AMFragment.class.getCanonicalName(), viewInflate7);
                viewInflate7.setTag(R.layout.activity_media_main, boolIsNight);
                viewInflate7.setTag(268435456, boolValueOf);
                LogUtil.d("preloadView: " + AMFragment.class.getCanonicalName() + " has preload");
                break;
            case 9:
                View viewInflate8 = layoutInflaterFrom.inflate(R.layout.fragment_dab, (ViewGroup) null);
                viewInflate8.setTag(R.layout.activity_media_main, boolIsNight);
                viewInflate8.setTag(268435456, boolValueOf);
                preLoadMap.put(DABFragment.class.getCanonicalName(), viewInflate8);
                LogUtil.d("preloadView: " + DABFragment.class.getCanonicalName() + " has preload");
                break;
        }
    }

    public static int setDefaultEngine() {
        if (ThirdManager.isSupportType(103)) {
            return setMusicEngine(103);
        }
        if (ThirdManager.isSupportType(104)) {
            return setMusicEngine(104);
        }
        if (RadioServiceWrapper.getInstance().getFMEnable()) {
            return setMusicEngine(7);
        }
        if (RadioServiceWrapper.getInstance().getAMEnable()) {
            return setMusicEngine(8);
        }
        if (RadioServiceWrapper.getInstance().getDABEnable()) {
            return setMusicEngine(9);
        }
        return setMusicEngine(6);
    }

    public static int setMusicEngine(int i) {
        return setMusicEngine(i, 0);
    }

    public static int setMusicEngine(int i, int i2) {
        if (musicEngine == i) {
            LogUtil.e(" OnLineMusic setMusicEngine failed, current engine is already = " + i);
            return -1;
        }
        MusicPlayHelper.getInstance().saveCurLocalMusicEngineProgress();
        if (i != -1) {
            switch (i) {
                case 3:
                    musicEngine = 3;
                    SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 3);
                    break;
                case 4:
                    musicEngine = 4;
                    SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 4);
                    break;
                case 5:
                    musicEngine = 5;
                    SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 5);
                    break;
                case 6:
                    if (ThirdManager.isAndroidAutoConnected()) {
                        return -1;
                    }
                    musicEngine = 6;
                    SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 6);
                    AudioFocusManager.getInstance().releaseAudioFocus();
                    break;
                case 7:
                    if (!RadioServiceWrapper.getInstance().getFMEnable()) {
                        LogUtil.e(" OnLineMusic setMusicEngine failed, FM INVALID.");
                        return -1;
                    }
                    musicEngine = 7;
                    SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 7);
                    radioEngine = 7;
                    SPUtils.getInstance().put(SPConstants.KEY_RADIO_TYPE, 7);
                    break;
                    break;
                case 8:
                    if (!RadioServiceWrapper.getInstance().getAMEnable()) {
                        LogUtil.e(" OnLineMusic setMusicEngine failed, AM INVALID.");
                        return -1;
                    }
                    musicEngine = 8;
                    SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 8);
                    radioEngine = 8;
                    SPUtils.getInstance().put(SPConstants.KEY_RADIO_TYPE, 8);
                    break;
                    break;
                case 9:
                    if (!RadioServiceWrapper.getInstance().getDABEnable()) {
                        LogUtil.e(" OnLineMusic setMusicEngine failed, DAB INVALID.");
                        return -1;
                    }
                    musicEngine = 9;
                    SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 9);
                    radioEngine = 9;
                    SPUtils.getInstance().put(SPConstants.KEY_RADIO_TYPE, 9);
                    break;
                    break;
                default:
                    switch (i) {
                        case 101:
                            musicEngine = 101;
                            SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 101);
                            AudioFocusManager.getInstance().releaseAudioFocus();
                            break;
                        case 102:
                            musicEngine = 102;
                            SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 102);
                            AudioFocusManager.getInstance().releaseAudioFocus();
                            break;
                        case 103:
                            if (!ThirdManager.isSupportType(103)) {
                                LogUtil.e(" OnLineMusic setMusicEngine failed, SPOTIFY INVALID.");
                                return -1;
                            }
                            musicEngine = 103;
                            SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 103);
                            AudioFocusManager.getInstance().releaseAudioFocus();
                            break;
                            break;
                        case 104:
                            if (!ThirdManager.isSupportType(104)) {
                                LogUtil.e(" OnLineMusic setMusicEngine failed, AMAZON INVALID.");
                                return -1;
                            }
                            musicEngine = 104;
                            SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, 104);
                            AudioFocusManager.getInstance().releaseAudioFocus();
                            break;
                            break;
                        case 105:
                        case 106:
                        case 107:
                        case 108:
                        case 109:
                        case 110:
                        case 111:
                        case 112:
                        case 113:
                        case 114:
                        case 115:
                        case 116:
                            if (!ThirdManager.isSupportType(i)) {
                                LogUtil.e(" OnLineMusic setMusicEngine failed, INVALID engine");
                                return -1;
                            }
                            musicEngine = i;
                            SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, i);
                            AudioFocusManager.getInstance().releaseAudioFocus();
                            break;
                            break;
                        default:
                            LogUtil.e("OnLineMusic setMusicEngine failed, not exist target = " + i);
                            return -1;
                    }
                    break;
            }
        } else {
            musicEngine = -1;
            SPUtils.getInstance().put(SPConstants.KEY_MUSIC_API, -1);
        }
        LogUtil.e("OnLineMusic setMusicEngine succeed! type = " + i + ", extraCode = " + i2);
        EventBus.getDefault().post(new ApiChangeBean(i2));
        checkBluetoothMusicActive();
        return 1;
    }

    public static void setNeedRestore(boolean z) {
        NEED_RESTORE = z;
        LogUtil.d("setNeedRestore musicEngine:" + musicEngine + " playing" + MusicPlayHelper.getInstance().isPlaying.get() + " NEED_RESTORE=" + NEED_RESTORE);
    }

    public static boolean getNeedRestore() {
        LogUtil.d("getNeedRestore musicEngine:" + musicEngine + " playing" + MusicPlayHelper.getInstance().isPlaying.get() + " NEED_RESTORE=" + NEED_RESTORE);
        int i = musicEngine;
        if ((i != 5 && i != 3 && i != 4) || MusicPlayHelper.getInstance().isPlaying.get()) {
            setNeedRestore(false);
        }
        return NEED_RESTORE;
    }

    public static boolean checkBluetoothMusicActive() {
        boolean z = musicEngine == 6 || isBTViewActive;
        isBTMusicActive = z;
        if (z) {
            SystemProperties.set("sys.mediacenter.bluetooth.active", "1");
        } else {
            SystemProperties.set("sys.mediacenter.bluetooth.active", AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS);
        }
        LogUtil.d("checkBluetoothMusicActive expect = " + z + ", write result = " + SystemProperties.get("sys.mediacenter.bluetooth.active"));
        return z;
    }

    private static void setCustomParam() {
        int i;
        if (VENDOR_CODE == 2) {
            RadioServiceWrapper.getInstance().initPogramListUpdateRunnable();
            RadioServiceWrapper.getInstance().checkAndStartProgramListUpdateRunnable();
            if (RadioServiceWrapper.getInstance().isPlaying.get() && RadioServiceWrapper.getInstance().isRadioHalReady && ((i = musicEngine) == 7 || i == 8 || i == 9)) {
                AudioFocusManager.getInstance().audioManager.setParameters("handle_fm=1048578");
            }
        }
        try {
            RadioServiceWrapper.getInstance().setAlternativeFrequency(SPUtils.getInstance().getBoolean("af_settings_switch", false));
        } catch (Throwable th) {
            LogUtil.e(TAG, "setAlternativeFrequency error");
            th.printStackTrace();
        }
    }

    /* JADX INFO: renamed from: com.byd.music.OnLineMusic$8, reason: invalid class name */
    class AnonymousClass8 implements Runnable {
        AnonymousClass8() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OnLineMusic.isHigherFocusHolding()) {
                LogUtil.e(OnLineMusic.TAG, "boot_completed isHigherFocusHolding, retry");
                AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.-$$Lambda$8Xvovj_cIKW0a8FXyoYAiQRf6H4
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.run();
                    }
                }, 500L);
                return;
            }
            if ("1".equals(SystemProperties.get("sys.boot_completed"))) {
                LogUtil.e(OnLineMusic.TAG, "boot_completed 1");
                OnLineMusic.bootComplete = true;
                if (OnLineMusic.bootWaitForBT) {
                    LogUtil.e(OnLineMusic.TAG, "boot_completed bootWaitForBT");
                    BluetoothViewModel.playMusic();
                    return;
                }
                if (OnLineMusic.bootWaitForNormal) {
                    LogUtil.e(OnLineMusic.TAG, "boot_completed bootWaitForNormal");
                    ApiMethod.playOrStop(true);
                    return;
                } else {
                    if (OnLineMusic.bootWaitForLocal) {
                        LogUtil.e(OnLineMusic.TAG, "boot_completed bootWaitForLocal");
                        if (MusicPlayHelper.getInstance().isMemoryPlay()) {
                            MusicPlayHelper.getInstance().memoryPlayAccOn();
                        } else {
                            OnLineMusic.setNeedRestore(true);
                        }
                        OnLineMusic.startLocalDelayRunnable();
                        return;
                    }
                    return;
                }
            }
            LogUtil.e(OnLineMusic.TAG, "boot_completed not yet, retry");
            AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.-$$Lambda$8Xvovj_cIKW0a8FXyoYAiQRf6H4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.run();
                }
            }, 500L);
        }
    }

    public static void bootCompleteRunnable() {
        AppExecutors.getInstance().mainThread().execute(new AnonymousClass8());
    }

    /* JADX INFO: renamed from: com.byd.music.OnLineMusic$9, reason: invalid class name */
    class AnonymousClass9 implements Runnable {
        AnonymousClass9() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (OnLineMusic.isHigherFocusHolding()) {
                AppExecutors.getInstance().mainThreadDelay().executeDelay(new Runnable() { // from class: com.byd.music.-$$Lambda$L-L9uKKTo_3m6v-MhSNH-6jwY0s
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.run();
                    }
                }, 500L);
                return;
            }
            try {
                LogUtil.e("local source postDelay enter");
                if (CurrentMediaInfoUtils.getInstance().getPlayingState()) {
                    LogUtil.e("local source already play...");
                    return;
                }
                if (!TextUtils.isEmpty(OnLineMusic.mediaFocusPackage) && !com.byd.musiclib.BuildConfig.APPLICATION_ID.equals(OnLineMusic.mediaFocusPackage)) {
                    LogUtil.e("local source current audioFocusPackage = " + OnLineMusic.mediaFocusPackage);
                    return;
                }
                if (ActivityUtils.getTopActivity() instanceof BaseActivity) {
                    LogUtil.e("local source already show activity...");
                    return;
                }
                if (OnLineMusic.musicEngine != 5 && OnLineMusic.musicEngine != 3 && OnLineMusic.musicEngine != 4) {
                    LogUtil.e("local source no longer front...");
                    return;
                }
                LogUtil.e("local source play and needRestore= " + OnLineMusic.getNeedRestore() + " memoryPlay= " + MusicPlayHelper.getInstance().isMemoryPlay());
                if (OnLineMusic.getNeedRestore()) {
                    MusicPlayHelper.getInstance().memoryPlayAccOn();
                } else {
                    ApiMethod.playOrStop(true);
                }
            } catch (Throwable th) {
                LogUtil.e("local source postDelay error = " + th.getMessage());
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startLocalDelayRunnable() {
        AppExecutors.getInstance().mainThreadDelay().executeDelay(new AnonymousClass9(), 6000L);
    }

    public static void dummyModeSwitchFromRCS() {
        int i;
        int i2;
        int i3;
        int i4 = musicEngine;
        switch (i4) {
            case 3:
                i = 2;
                i2 = 1;
                break;
            case 4:
                i2 = 1;
                i = 1;
                break;
            case 5:
                i = 0;
                i2 = 1;
                break;
            case 6:
                i2 = 8;
                i = -1;
                break;
            case 7:
                i2 = 11;
                i = -1;
                break;
            case 8:
                i2 = 12;
                i = -1;
                break;
            case 9:
                i2 = 13;
                i = -1;
                break;
            default:
                switch (i4) {
                    case 101:
                        i2 = 101;
                        break;
                    case 102:
                        i2 = 102;
                        break;
                    case 103:
                        i2 = 103;
                        break;
                    case 104:
                        i2 = 104;
                        break;
                    case 105:
                        i2 = 105;
                        break;
                    default:
                        i2 = -1;
                        break;
                }
                i = -1;
                break;
        }
        LogUtil.d("AbsBYDAutoInstrumentListener", "onMusicResourceConfigChanged currentMode = " + i2 + "， currentType = " + i);
        if (i2 == 1) {
            if (i == 0) {
                if (MusicPlayHelper.getInstance().getIsSDPlug().get()) {
                    LogUtil.d("AbsBYDAutoInstrumentListener", "controlMedia MODE_MUSIC target SOURCE = 2");
                    MediaControl.Param param = new MediaControl.Param();
                    param.setParam("source", 2);
                    param.setParam(MediaControl.PARAM_WITH_UI, "true");
                    MediaControl.getInstance(Utils.getApp()).controlMedia(1, 19, param);
                    return;
                }
                if (MusicPlayHelper.getInstance().getIsUsbPlug().get()) {
                    LogUtil.d("AbsBYDAutoInstrumentListener", "controlMedia MODE_MUSIC target SOURCE = 1");
                    MediaControl.Param param2 = new MediaControl.Param();
                    param2.setParam("source", 1);
                    param2.setParam(MediaControl.PARAM_WITH_UI, "true");
                    MediaControl.getInstance(Utils.getApp()).controlMedia(1, 19, param2);
                    return;
                }
            } else if (i == 2 && MusicPlayHelper.getInstance().getIsUsbPlug().get()) {
                LogUtil.d("AbsBYDAutoInstrumentListener", "controlMedia MODE_MUSIC target SOURCE = 1");
                MediaControl.Param param3 = new MediaControl.Param();
                param3.setParam("source", 1);
                param3.setParam(MediaControl.PARAM_WITH_UI, "true");
                MediaControl.getInstance(Utils.getApp()).controlMedia(1, 19, param3);
                return;
            }
        }
        int[] iArr = new int[9];
        if (ThirdManager.isSupportType(103)) {
            iArr[0] = 103;
        }
        if (ThirdManager.isSupportType(104)) {
            iArr[1] = 104;
        }
        if (ThirdManager.isSupportType(105)) {
            iArr[2] = 105;
        }
        if (RadioServiceWrapper.getInstance().getFMEnable()) {
            iArr[3] = 11;
        }
        if (RadioServiceWrapper.getInstance().getAMEnable()) {
            iArr[4] = 12;
        }
        if (RadioServiceWrapper.getInstance().getDABEnable()) {
            iArr[5] = 13;
        }
        if (ThirdManager.isAndroidAutoConnected()) {
            iArr[6] = 101;
        } else if (ThirdManager.isCarplayConnected()) {
            iArr[6] = 102;
        }
        iArr[7] = ThirdManager.isAndroidAutoConnected() ? 0 : 8;
        iArr[8] = 1;
        LogUtil.d("AbsBYDAutoInstrumentListener", "onMusicResourceConfigChanged modeList = " + Arrays.toString(iArr));
        int i5 = 0;
        while (true) {
            if (i5 >= 9) {
                i3 = -1;
                i5 = -1;
            } else if (iArr[i5] == i2) {
                i3 = -1;
            } else {
                i5++;
            }
        }
        if (i5 == i3) {
            throw new RuntimeException("onMusicResourceConfigChanged error with currentIndex = -1");
        }
        LogUtil.d("AbsBYDAutoInstrumentListener", "currentIndex = " + i5);
        do {
            if (i5 == 8) {
                i5 = i3;
            }
            i5++;
        } while (iArr[i5] <= 0);
        int i6 = iArr[i5];
        LogUtil.d("AbsBYDAutoInstrumentListener", "controlMedia targetMode = " + i6);
        if (i6 == 1) {
            MediaControl.Param param4 = new MediaControl.Param();
            param4.setParam("source", 0);
            param4.setParam(MediaControl.PARAM_WITH_UI, "true");
            MediaControl.getInstance(Utils.getApp()).controlMedia(1, 19, param4);
            return;
        }
        MediaControl.Param param5 = new MediaControl.Param();
        param5.setParam(MediaControl.PARAM_WITH_UI, "true");
        MediaControl.getInstance(Utils.getApp()).controlMedia(i6, 19, param5);
    }

    public static boolean isHigherFocusHolding() {
        try {
            List<AudioFocusInfo> curAudioFocusInfo = BYDAutoAudioDevice.getInstance(Utils.getApp()).getCurAudioFocusInfo(0);
            if (CollectionUtils.isEmpty(curAudioFocusInfo)) {
                LogUtil.d(TAG, "isHigherFocusHolding focus list empty.");
                return false;
            }
            Iterator<AudioFocusInfo> it = curAudioFocusInfo.iterator();
            while (it.hasNext()) {
                String packageName = it.next().getPackageName();
                LogUtil.d(TAG, "isHigherFocusHolding once focus packageName = " + packageName);
                if ("com.byd.uereport".equals(packageName) || "com.byd.auto_camera".equals(packageName)) {
                    return true;
                }
            }
            LogUtil.d(TAG, "isHigherFocusHolding return false after try");
            return false;
        } catch (Throwable unused) {
            LogUtil.d(TAG, "isHigherFocusHolding return false after catch");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onShowCarMusicAutoPlay() {
        StringBuilder sb = new StringBuilder("onShowCarMusicAutoPlay:");
        boolean zIsShowCarModeStyle = ShowCarModeUtil.isShowCarModeStyle();
        sb.append(" isShowCarMode=");
        sb.append(zIsShowCarModeStyle);
        if (zIsShowCarModeStyle && !MusicPlayHelper.getInstance().isPlaying.get()) {
            sb.append(" and autoPlayShowCar");
            List<LocalMusicBean> localMusicByStorageType = LocalMusicDB.getLocalMusicByStorageType(0);
            LocalMusicUtils.sortShowCarMusicList(localMusicByStorageType);
            MusicPlayHelper.getInstance().setLocalMusicBeanList(localMusicByStorageType);
            MusicPlayHelper.getInstance().autoPlayShowCar();
        }
        LogUtil.d(TAG, sb.toString());
    }
}
