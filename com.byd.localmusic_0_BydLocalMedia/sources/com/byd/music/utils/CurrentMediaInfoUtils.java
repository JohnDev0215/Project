package com.byd.music.utils;

import android.appwidget.AppWidgetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.os.Handler;
import android.os.Looper;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableLong;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.byd.bluetoothaidl.manage.HfpManage;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager;
import com.byd.music.ThirdSDK.Carplay.CarplayManager;
import com.byd.music.ThirdSDK.Spotify.SpotifyManager;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.ThirdSDK.amazon.AmazonManager;
import com.byd.music.ThirdSDK.fce.CommonFceManager;
import com.byd.music.api.ApiMethod;
import com.byd.music.beans.ApiChangeBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.beans.MultiMusicBean;
import com.byd.music.beans.PlayStateDBBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.constant.SPConstants;
import com.byd.music.db.PlayStateDB;
import com.byd.music.fragment.bluetooth.BluetoothMusicBean;
import com.byd.music.fragment.bluetooth.BluetoothViewModel;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.mediahelper.MusicWidgetProvider;
import com.bydux.api.common.views.skin.loader.SkinManager;
import com.ts.car.core.ServiceProvider;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.AbstractMap;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import ts.car.service.androidauto.IAndroidAutoAppManager;

/* JADX INFO: loaded from: classes5.dex */
public class CurrentMediaInfoUtils {
    public static final String ALBUM_CACHE_PATH = "/data/vendor/nfs/nfs_ota/album/";
    private static final String TAG = "CurrentMediaInfoUtils";
    private static volatile CurrentMediaInfoUtils instance;
    private long lastSyncAlbumTiming;
    private CacheMediaInfo mCacheMediaInfo;
    public PlayStateDBBean savePlaying;
    public ObservableField<MultiMusicBean> currentMusic = new ObservableField<>();
    private ObservableBoolean isPlaying = new ObservableBoolean(false);
    public boolean radioSavePlaying = SPUtils.getInstance().getBoolean(SPConstants.KEY_RADIO_SAVE_PLAYING_STATUS, true);
    public boolean expectPlayingStatusShouldNotBeOverride = false;
    public ObservableLong maxProgress = new ObservableLong(0);
    public ObservableLong curProgress = new ObservableLong(SPUtils.getInstance().getLong(SPConstants.KEY_SAVE_PROGRESS, 0));
    public ObservableLong bufProgress = new ObservableLong(0);
    public volatile AbstractMap.SimpleEntry<String, Bitmap> currentAlbum = new AbstractMap.SimpleEntry<>(null, null);
    public volatile AbstractMap.SimpleEntry<String, Bitmap> cacheAlbumForCarplay = new AbstractMap.SimpleEntry<>(null, null);
    public volatile AbstractMap.SimpleEntry<String, Bitmap> cacheAlbumForDab = new AbstractMap.SimpleEntry<>(null, null);
    public volatile String albumMD5Cache = getDefaultAlbumForInstrument();
    private int loadFailCount = 0;
    public Handler mainHandler = new Handler(Looper.getMainLooper());
    public Runnable updateWidgetRunnable = new Runnable() { // from class: com.byd.music.utils.-$$Lambda$CurrentMediaInfoUtils$QnVHDL5hL7MvIfjbwIMqPAwOsBY
        @Override // java.lang.Runnable
        public final void run() {
            MusicWidgetProvider.updateAppWidget(Utils.getApp(), AppWidgetManager.getInstance(Utils.getApp()));
        }
    };
    private Runnable notifyAlbumRunnable = new Runnable() { // from class: com.byd.music.utils.-$$Lambda$CurrentMediaInfoUtils$lNtZxvFS0qdDH_m0Wte5d4vviGI
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$1$CurrentMediaInfoUtils();
        }
    };
    public Runnable notifyDefaultAlbumUpdateRunnable = new Runnable() { // from class: com.byd.music.utils.-$$Lambda$CurrentMediaInfoUtils$uCyAKUfPPDcrA8Vaafe8dWzXHvw
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$2$CurrentMediaInfoUtils();
        }
    };
    Observable.OnPropertyChangedCallback currentMusicChangedCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.utils.CurrentMediaInfoUtils.2
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            CurrentMediaInfoUtils.this.loadFailCount = 0;
            if (CurrentMediaInfoUtils.this.currentMusic.get().getAlbum() == null) {
                LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: stage 1, getAlbum is null");
                CurrentMediaInfoUtils.this.currentMusic.get().setAlbumTAG("");
            } else if (TextUtils.isEmpty(CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG())) {
                CurrentMediaInfoUtils.this.currentMusic.get().setAlbumTAG(String.valueOf(CurrentMediaInfoUtils.this.currentMusic.get().getAlbum().hashCode()));
                LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: stage 2, setTag = " + CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG());
            }
            if (CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG() == null) {
                if ("".equals(CurrentMediaInfoUtils.this.currentAlbum.getKey())) {
                    CurrentMediaInfoUtils.this.currentAlbum.setValue(((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.img_logo_media64)).getBitmap());
                } else {
                    CurrentMediaInfoUtils.this.currentAlbum = new AbstractMap.SimpleEntry<>("", ((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.img_logo_media64)).getBitmap());
                }
                CurrentMediaInfoUtils.this.albumMD5Cache = CurrentMediaInfoUtils.getDefaultAlbumForInstrument();
            } else if (!CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG().equals(CurrentMediaInfoUtils.this.currentAlbum.getKey()) || OnLineMusic.musicEngine == 9) {
                LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: stage 3, currentMusic.get().getAlbumTAG() = " + CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG() + ", currentAlbum.getKey() = " + CurrentMediaInfoUtils.this.currentAlbum.getKey());
                CurrentMediaInfoUtils.this.currentAlbum.setValue(((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.img_logo_media64)).getBitmap());
                if (OnLineMusic.musicEngine != 101) {
                    CurrentMediaInfoUtils.this.albumMD5Cache = CurrentMediaInfoUtils.getDefaultAlbumForInstrument();
                }
                CurrentMediaInfoUtils.this.updateAlbumBitmap();
            } else {
                LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: stage 666, currentMusic.get().getAlbumTAG() = " + CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG() + ", currentAlbum.getKey() = " + CurrentMediaInfoUtils.this.currentAlbum.getKey());
            }
            int i2 = OnLineMusic.musicEngine;
            if (i2 == 3 || i2 == 4 || i2 == 5) {
                CurrentMediaInfoUtils.this.maxProgress.set(0L);
            } else {
                if (i2 == 7 || i2 == 8 || i2 == 9) {
                    CurrentMediaInfoUtils.this.isPlaying.set(RadioServiceWrapper.getInstance().isPlaying.get());
                } else {
                    switch (i2) {
                    }
                }
                CurrentMediaInfoUtils.this.maxProgress.set(0L);
            }
            if (CurrentMediaInfoUtils.this.currentMusic.get() == null || CurrentMediaInfoUtils.this.currentMusic.get().getEntity() == null) {
                LogUtil.d(CurrentMediaInfoUtils.TAG, "onPropertyChanged: current name = null because currentMusic.get = null ~~ ");
                CurrentMediaInfoUtils.this.curProgress.set(0L);
                CurrentMediaInfoUtils.this.maxProgress.set(0L);
                LogUtil.d(CurrentMediaInfoUtils.TAG, "currentMusicChangedCallback curProgress.set 0");
            } else {
                LogUtil.d(CurrentMediaInfoUtils.TAG, "onPropertyChanged: current name = " + StringSubUtil.subString(CurrentMediaInfoUtils.this.currentMusic.get().getMusicName()) + ", artist = " + StringSubUtil.subString(CurrentMediaInfoUtils.this.currentMusic.get().getArtist()));
            }
            CurrentMediaInfoUtils.this.updatePlayStateAndNotify();
        }
    };
    Observable.OnPropertyChangedCallback isPlayingChangedCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.utils.CurrentMediaInfoUtils.5
        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(Observable observable, int i) {
            LogUtil.e(CurrentMediaInfoUtils.TAG, "onPropertyChanged: isPlaying = " + CurrentMediaInfoUtils.this.isPlaying.get());
            if (CurrentMediaInfoUtils.this.isPlaying.get()) {
                CurrentMediaInfoUtils.this.savePlaying.setPlayState(true);
                PlayStateDB.insert(CurrentMediaInfoUtils.this.savePlaying);
                CurrentMediaInfoUtils.this.checkAndSetRadioSavePlaying(true);
                LogUtil.e(CurrentMediaInfoUtils.TAG, "zzl onPropertyChanged: savePlaying set true,time:" + CurrentMediaInfoUtils.this.savePlaying.getTimeStamp());
                CurrentMediaInfoUtils.this.expectPlayingStatusShouldNotBeOverride = false;
                LogUtil.e(CurrentMediaInfoUtils.TAG, "expectPlayingStatusShouldNotBeOverride = false in Utils isPlayingChangedCallback");
            } else if (!CurrentMediaInfoUtils.this.expectPlayingStatusShouldNotBeOverride) {
                CurrentMediaInfoUtils.this.savePlaying.setPlayState(false);
                PlayStateDB.insert(CurrentMediaInfoUtils.this.savePlaying);
                LogUtil.e(CurrentMediaInfoUtils.TAG, "zzl onPropertyChanged: savePlaying set false ,time:" + CurrentMediaInfoUtils.this.savePlaying.getTimeStamp());
            }
            CurrentMediaInfoUtils.this.updatePlayStateAndNotify();
        }
    };

    public static int getCurrentInstrumentMediaType(int i) {
        switch (i) {
            case 3:
                return 4;
            case 4:
                return 2;
            case 5:
                return 1;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 18;
            case 9:
                return 17;
            default:
                switch (i) {
                    case 101:
                        return 31;
                    case 102:
                        return 30;
                    case 103:
                        return 16;
                    case 104:
                        return 32;
                    case 105:
                        return 33;
                    case 106:
                        return 41;
                    case 107:
                        return 44;
                    case 108:
                        return 49;
                    case 109:
                        return 34;
                    case 110:
                        return 40;
                    case 111:
                        return 43;
                    case 112:
                        return 36;
                    case 113:
                        return 39;
                    case 114:
                        return 42;
                    case 115:
                        return 35;
                    case 116:
                        return 37;
                    default:
                        return -1;
                }
        }
    }

    public static int getCurrentRCSMediaType(int i) {
        switch (i) {
            case 3:
                return 4;
            case 4:
                return 2;
            case 5:
                return 1;
            case 6:
                return 6;
            case 7:
                return 7;
            case 8:
                return 23;
            case 9:
                return 24;
            default:
                switch (i) {
                    case 101:
                        return 17;
                    case 102:
                        return 16;
                    case 103:
                        return 18;
                    case 104:
                        return 19;
                    case 105:
                        return 22;
                    default:
                        return -1;
                }
        }
    }

    static /* synthetic */ int access$108(CurrentMediaInfoUtils currentMediaInfoUtils) {
        int i = currentMediaInfoUtils.loadFailCount;
        currentMediaInfoUtils.loadFailCount = i + 1;
        return i;
    }

    public static String getDefaultAlbumForWrite() {
        return isDarkTheme() ? "/data/vendor/nfs/nfs_ota/album/default_dark.png" : "/data/vendor/nfs/nfs_ota/album/default_light.png";
    }

    public static String getDefaultAlbumForInstrument() {
        return isDarkTheme() ? "/album/default_dark.png" : "/album/default_light.png";
    }

    public static boolean isDarkTheme() {
        return (Utils.getApp().getResources().getConfiguration().uiMode & 48) == 32;
    }

    public /* synthetic */ void lambda$new$1$CurrentMediaInfoUtils() {
        if (OnLineMusic.getBuildType() == 1) {
            return;
        }
        AppExecutors.getInstance().networkIO().execute(new AnonymousClass1());
    }

    /* JADX INFO: renamed from: com.byd.music.utils.CurrentMediaInfoUtils$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String md5;
            if (TextUtils.equals("", CurrentMediaInfoUtils.getInstance().currentAlbum.getKey())) {
                LogUtil.i(CurrentMediaInfoUtils.TAG, "notifyAlbumRunnable with album key empty, just return.");
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            CurrentMediaInfoUtils.this.lastSyncAlbumTiming = jCurrentTimeMillis;
            boolean z = true;
            Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(CurrentMediaInfoUtils.getInstance().currentAlbum.getValue(), 176, 176, true);
            if (OnLineMusic.musicEngine == 6) {
                md5 = CurrentMediaInfoUtils.getMD5(6 + ((String) CurrentMediaInfoUtils.this.currentMusic.get().getAlbum()));
            } else {
                md5 = CurrentMediaInfoUtils.getMD5(OnLineMusic.musicEngine + CurrentMediaInfoUtils.this.currentMusic.get().getMusicName() + CurrentMediaInfoUtils.this.currentMusic.get().getArtist());
            }
            File file = new File(CurrentMediaInfoUtils.ALBUM_CACHE_PATH);
            if (!file.exists()) {
                LogUtil.d(CurrentMediaInfoUtils.TAG, "notifyAlbumRunnable mkdir with dir no exists.");
                file.mkdir();
                AppExecutors.getInstance().networkIO().execute(CurrentMediaInfoUtils.this.notifyDefaultAlbumUpdateRunnable);
            }
            final File file2 = new File(CurrentMediaInfoUtils.ALBUM_CACHE_PATH + md5 + ".png");
            if (!file2.exists()) {
                LogUtil.d(CurrentMediaInfoUtils.TAG, "notifyAlbumRunnable png file no exists.");
            } else if (System.currentTimeMillis() - file2.lastModified() <= 86400000) {
                z = false;
            }
            if (z) {
                LogUtil.d(CurrentMediaInfoUtils.TAG, "notifyAlbumRunnable deleteRet = " + file2.delete());
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    bitmapCreateScaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.close();
                    LogUtil.i(CurrentMediaInfoUtils.TAG, "notifyAlbumRunnable dir fos close.");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (jCurrentTimeMillis == CurrentMediaInfoUtils.this.lastSyncAlbumTiming) {
                AppExecutors.getInstance().mainThread().execute(new Runnable() { // from class: com.byd.music.utils.-$$Lambda$CurrentMediaInfoUtils$1$FhOSq5MITTOGz-EcxFl-MpG7IWI
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$run$0$CurrentMediaInfoUtils$1(file2);
                    }
                });
            }
        }

        public /* synthetic */ void lambda$run$0$CurrentMediaInfoUtils$1(File file) {
            try {
                String str = "/album/" + file.getName();
                LogUtil.e(CurrentMediaInfoUtils.TAG, "notifyAlbumRunnable go to sync! finalPath = " + str);
                CurrentMediaInfoUtils.this.albumMD5Cache = str;
                if (!OnLineMusic.isAudioFocusAvailable()) {
                    BYDAutoInstrumentDevice.getInstance(Utils.getApp()).sendMusicInfoUrl("", "", 2, 0, "");
                } else {
                    BYDAutoInstrumentDevice.getInstance(Utils.getApp()).sendMusicInfoUrl(CurrentMediaInfoUtils.this.currentMusic.get().getEntity() == null ? WidgetHintTextUtil.getHintMusic(OnLineMusic.musicEngine) : CurrentMediaInfoUtils.this.currentMusic.get().getMusicName(), CurrentMediaInfoUtils.this.currentMusic.get().getEntity() == null ? WidgetHintTextUtil.getHintArtist(OnLineMusic.musicEngine) : CurrentMediaInfoUtils.this.currentMusic.get().getArtist(), CurrentMediaInfoUtils.this.isPlaying.get() ? 1 : 2, CurrentMediaInfoUtils.getCurrentInstrumentMediaType(OnLineMusic.musicEngine), str);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX INFO: renamed from: updateDefaultAlbum, reason: merged with bridge method [inline-methods] */
    public void lambda$new$2$CurrentMediaInfoUtils() {
        File file = new File(getDefaultAlbumForWrite());
        if (this.albumMD5Cache.contains("default_")) {
            this.albumMD5Cache = getDefaultAlbumForInstrument();
        }
        if (file.exists()) {
            if (System.currentTimeMillis() - file.lastModified() <= 86400000) {
                return;
            } else {
                file.delete();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Bitmap.createScaledBitmap(((BitmapDrawable) SkinManager.getInstance().getDrawable(R.drawable.album_default_248)).getBitmap(), 176, 176, true).compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            LogUtil.i(TAG, "ALBUM_CACHE_PATH_DEFAULT fos close.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void deleteAlbumCache() {
        try {
            File file = new File(ALBUM_CACHE_PATH);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.utils.-$$Lambda$CurrentMediaInfoUtils$aZduaCs1lCQrd3xhZEbcxVaDZPI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$deleteAlbumCache$3$CurrentMediaInfoUtils();
                }
            }, 2000L);
            if (!file.exists()) {
                file.mkdir();
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null) {
                for (File file2 : fileArrListFiles) {
                    deleteFolder(file2, jCurrentTimeMillis, 172800000L);
                }
            }
            LogUtil.i(TAG, "deleteAlbumCache finish.");
        } catch (Throwable th) {
            LogUtil.e(TAG, "deleteAlbumCache error = " + th.getMessage());
        }
    }

    public /* synthetic */ void lambda$deleteAlbumCache$3$CurrentMediaInfoUtils() {
        AppExecutors.getInstance().networkIO().execute(this.notifyDefaultAlbumUpdateRunnable);
    }

    private static void deleteFolder(File file, long j, long j2) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    deleteFolder(file2, j, j2);
                } else if (j - file2.lastModified() > j2) {
                    file2.delete();
                }
            }
        }
        file.delete();
    }

    public static CurrentMediaInfoUtils getInstance() {
        if (instance == null) {
            synchronized (CurrentMediaInfoUtils.class) {
                if (instance == null) {
                    instance = new CurrentMediaInfoUtils();
                }
            }
        }
        return instance;
    }

    private CurrentMediaInfoUtils() {
        LogUtil.e("CurrentMediaInfoUtils init enter...");
        instance = this;
        this.mCacheMediaInfo = new CacheMediaInfo(this, null);
        List<PlayStateDBBean> listQueryAll = PlayStateDB.queryAll();
        if (listQueryAll.size() == 0 || listQueryAll.get(0) == null) {
            LogUtil.e("CurrentMediaInfoUtils init has no play state db,new PlayStateDBBean");
            this.savePlaying = new PlayStateDBBean();
        } else {
            this.savePlaying = listQueryAll.get(0);
            LogUtil.e("CurrentMediaInfoUtils init has play state db PlayStateDB size:" + listQueryAll.size());
        }
        this.currentMusic.addOnPropertyChangedCallback(this.currentMusicChangedCallback);
        this.isPlaying.addOnPropertyChangedCallback(this.isPlayingChangedCallback);
        EventBus.getDefault().register(this);
        LogUtil.d(TAG, "CurrentMediaInfoUtils init savePlaying to isPlaying = " + this.savePlaying.getPlayState() + ",time：" + this.savePlaying.getTimeStamp() + ",id:" + this.savePlaying.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("CurrentMediaInfoUtils: init curProgress = ");
        sb.append(this.curProgress.get());
        LogUtil.d(TAG, sb.toString());
    }

    public void destoryCurrentMediaInfoUtils() {
        LogUtil.e("CurrentMediaInfoUtils deInit enter...");
        instance = null;
        EventBus.getDefault().unregister(this);
        this.currentMusic.removeOnPropertyChangedCallback(this.currentMusicChangedCallback);
        this.isPlaying.removeOnPropertyChangedCallback(this.isPlayingChangedCallback);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveChangeApi(ApiChangeBean apiChangeBean) {
        int i = OnLineMusic.musicEngine;
        switch (i) {
            case 3:
            case 4:
            case 5:
                Object entity = this.currentMusic.get() != null ? this.currentMusic.get().getEntity() : null;
                if (!(entity instanceof LocalMusicBean) || MusicPlayHelper.getInstance().convertStorageTypeToEngine(((LocalMusicBean) entity).getStorageType()) != OnLineMusic.musicEngine) {
                    MusicPlayHelper.getInstance().localMusicPause();
                }
                BluetoothViewModel.pauseMusic();
                RadioServiceWrapper.getInstance().setRadioPlayState(false);
                if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    CarplayManager.playOrStop(false);
                }
                if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    AndroidAutoManager.playOrStop(false);
                }
                if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    SpotifyManager.playOrStop(false);
                }
                if (AmazonManager.AmazonAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    AmazonManager.playOrStop(false);
                }
                if (ThirdManager.getInstance().getFceManager() != null) {
                    ThirdManager.getInstance().getFceManager().pause();
                }
                break;
            case 6:
                MusicPlayHelper.getInstance().localMusicPause();
                RadioServiceWrapper.getInstance().setRadioPlayState(false);
                if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    CarplayManager.playOrStop(false);
                }
                if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    AndroidAutoManager.playOrStop(false);
                }
                if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    SpotifyManager.playOrStop(false);
                }
                if (AmazonManager.AmazonAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    AmazonManager.playOrStop(false);
                }
                if (ThirdManager.getInstance().getFceManager() != null) {
                    ThirdManager.getInstance().getFceManager().pause();
                }
                break;
            case 7:
            case 8:
            case 9:
                MusicPlayHelper.getInstance().localMusicPause();
                BluetoothViewModel.pauseMusic();
                if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    CarplayManager.playOrStop(false);
                }
                if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    AndroidAutoManager.playOrStop(false);
                }
                if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    SpotifyManager.playOrStop(false);
                }
                if (AmazonManager.AmazonAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                    AmazonManager.playOrStop(false);
                }
                if (ThirdManager.getInstance().getFceManager() != null) {
                    ThirdManager.getInstance().getFceManager().pause();
                }
                break;
            default:
                switch (i) {
                    case 101:
                        try {
                            String projectionBluetoothAddress = ((IAndroidAutoAppManager) ServiceProvider.get(IAndroidAutoAppManager.class, Utils.getApp())).getProjectionBluetoothAddress();
                            LogUtil.e("case TYPE_ANDROIDAUTO, AA ADDR = " + projectionBluetoothAddress);
                            String address = HfpManage.getInstance().getA2dpConnectDevice().getAddress();
                            LogUtil.e("case TYPE_ANDROIDAUTO, BT ADDR = " + address);
                            if (!TextUtils.isEmpty(projectionBluetoothAddress) && !TextUtils.isEmpty(address) && !TextUtils.equals(projectionBluetoothAddress, address)) {
                                LogUtil.e("case TYPE_CARPLAY, pause BT ");
                                BluetoothViewModel.pauseMusic();
                            }
                        } catch (Throwable th) {
                            LogUtil.e("case TYPE_ANDROIDAUTO, do not pause with throwable = " + th.getMessage());
                        }
                        RadioServiceWrapper.getInstance().setRadioPlayState(false);
                        MusicPlayHelper.getInstance().localMusicPause();
                        if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            CarplayManager.playOrStop(false);
                        }
                        if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            SpotifyManager.playOrStop(false);
                        }
                        if (AmazonManager.AmazonAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AmazonManager.playOrStop(false);
                        }
                        if (ThirdManager.getInstance().getFceManager() != null) {
                            ThirdManager.getInstance().getFceManager().pause();
                        }
                        break;
                    case 102:
                        RadioServiceWrapper.getInstance().setRadioPlayState(false);
                        MusicPlayHelper.getInstance().localMusicPause();
                        BluetoothViewModel.pauseMusic();
                        if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            SpotifyManager.playOrStop(false);
                        }
                        if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AndroidAutoManager.playOrStop(false);
                        }
                        if (AmazonManager.AmazonAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AmazonManager.playOrStop(false);
                        }
                        if (ThirdManager.getInstance().getFceManager() != null) {
                            ThirdManager.getInstance().getFceManager().pause();
                        }
                        break;
                    case 103:
                        BluetoothViewModel.pauseMusic();
                        RadioServiceWrapper.getInstance().setRadioPlayState(false);
                        MusicPlayHelper.getInstance().localMusicPause();
                        if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            CarplayManager.playOrStop(false);
                        }
                        if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AndroidAutoManager.playOrStop(false);
                        }
                        if (AmazonManager.AmazonAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AmazonManager.playOrStop(false);
                        }
                        if (ThirdManager.getInstance().getFceManager() != null) {
                            ThirdManager.getInstance().getFceManager().pause();
                        }
                        break;
                    case 104:
                        BluetoothViewModel.pauseMusic();
                        RadioServiceWrapper.getInstance().setRadioPlayState(false);
                        MusicPlayHelper.getInstance().localMusicPause();
                        if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            CarplayManager.playOrStop(false);
                        }
                        if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            SpotifyManager.playOrStop(false);
                        }
                        if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AndroidAutoManager.playOrStop(false);
                        }
                        if (ThirdManager.getInstance().getFceManager() != null) {
                            ThirdManager.getInstance().getFceManager().pause();
                        }
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
                        BluetoothViewModel.pauseMusic();
                        RadioServiceWrapper.getInstance().setRadioPlayState(false);
                        MusicPlayHelper.getInstance().localMusicPause();
                        if (CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            CarplayManager.playOrStop(false);
                        }
                        if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            SpotifyManager.playOrStop(false);
                        }
                        if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AndroidAutoManager.playOrStop(false);
                        }
                        if (AmazonManager.AmazonAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                            AmazonManager.playOrStop(false);
                        }
                        if (ThirdManager.getInstance().getFceManager() == null) {
                            ThirdManager.getInstance().setFceManager(new CommonFceManager());
                        }
                        ThirdManager.getInstance().getFceManager().reset(Utils.getApp(), ThirdManager.getInstance().FceEngineMap.get(Integer.valueOf(OnLineMusic.musicEngine)), "com.fce.onlinemedia.voice.CarMediaTransportService", OnLineMusic.musicEngine);
                        break;
                }
                break;
        }
        setPlayingState(false);
        if (apiChangeBean.flagEnable(2)) {
            return;
        }
        updateCurrentMusicInfo();
    }

    public void notifyWidgetUpdate() {
        notifyWidgetUpdate(false);
    }

    public void notifyAlbumUpdate() {
        this.mainHandler.removeCallbacks(this.notifyAlbumRunnable);
        this.mainHandler.postDelayed(this.notifyAlbumRunnable, 200L);
    }

    public void notifyWidgetUpdate(boolean z) {
        this.mainHandler.removeCallbacks(this.updateWidgetRunnable);
        if (z) {
            if (Looper.getMainLooper().isCurrentThread()) {
                MusicWidgetProvider.updateAppWidget(Utils.getApp(), AppWidgetManager.getInstance(Utils.getApp()));
                return;
            } else {
                this.mainHandler.post(this.updateWidgetRunnable);
                return;
            }
        }
        this.mainHandler.postDelayed(this.updateWidgetRunnable, 200L);
    }

    public void cancelWidgetUpdate() {
        this.mainHandler.removeCallbacks(this.updateWidgetRunnable);
    }

    public void saveCurrentProgress() {
        String str;
        StringBuilder sb = new StringBuilder("saveCurrentProgress: ");
        sb.append(" musicEngine= ");
        sb.append(OnLineMusic.musicEngine);
        sb.append(" isAccOff= ");
        sb.append(MusicPlayHelper.getInstance().isAccOff);
        sb.append(" curProgress = ");
        sb.append(this.curProgress.get());
        int i = OnLineMusic.musicEngine;
        if (i == 3 || i == 4 || i == 5) {
            if (this.currentMusic != null && !MusicPlayHelper.getInstance().isAccOff && this.curProgress.get() > 0) {
                if (OnLineMusic.musicEngine == 5) {
                    str = SPConstants.KEY_SAVE_PROGRESS_LOCAL;
                } else if (OnLineMusic.musicEngine == 3) {
                    str = SPConstants.KEY_SAVE_PROGRESS_SD;
                } else {
                    str = OnLineMusic.musicEngine == 4 ? SPConstants.KEY_SAVE_PROGRESS_USB : "";
                }
                sb.append(" spKey=");
                sb.append(str);
                SPUtils.getInstance().put(SPConstants.KEY_SAVE_PROGRESS, this.curProgress.get());
                if (!TextUtils.isEmpty(str)) {
                    SPUtils.getInstance().put(str, this.curProgress.get());
                }
            } else {
                sb.append(" currentMusic is null = ");
                sb.append(this.currentMusic == null);
                sb.append(" ,not save progress return!");
                LogUtil.e(sb.toString());
                return;
            }
        } else {
            SPUtils.getInstance().put(SPConstants.KEY_SAVE_PROGRESS, this.curProgress.get());
        }
        int i2 = (int) SPUtils.getInstance().getLong(SPConstants.KEY_SAVE_PROGRESS, -1L);
        sb.append(" and save spProgress result = ");
        sb.append(((long) i2) == this.curProgress.get());
        LogUtil.e(TAG, sb.toString());
    }

    public void setCurrentMusicInfoAndPlay(boolean z) {
        LogUtil.e(TAG, "zzl setCurrentMusicInfoAndPlay: enter with music type = " + OnLineMusic.musicEngine + ", gotoPlay = " + z + ", currentPlaying state = " + this.isPlaying.get());
        boolean z2 = this.isPlaying.get();
        updateCurrentMusicInfo();
        if (z2 && !z) {
            ApiMethod.playOrStop(false);
        } else {
            if (z2 || !z) {
                return;
            }
            ApiMethod.playOrStop(true);
        }
    }

    public void updateCurrentMusicInfo() {
        int i = OnLineMusic.musicEngine;
        switch (i) {
            case 3:
            case 4:
            case 5:
                try {
                    this.currentMusic.set(new MultiMusicBean(MusicPlayHelper.getInstance().getCurrentPlayLocalMusic(MusicPlayHelper.getInstance().convertMusicEngineToStorageType(OnLineMusic.musicEngine))));
                } catch (Exception e) {
                    this.currentMusic.set(new MultiMusicBean(null));
                    e.printStackTrace();
                }
                break;
            case 6:
                if (!BluetoothViewModel.isA2DPConnected.get()) {
                    this.currentMusic.set(new MultiMusicBean(null));
                } else {
                    long j = BluetoothViewModel.currentTime.get();
                    long j2 = (j % 1000 > 0 ? (((int) j) / 1000) + 1 : ((int) j) / 1000) * 1000;
                    this.currentMusic.set(new MultiMusicBean(new BluetoothMusicBean(BluetoothViewModel.isA2DPConnected.get(), BluetoothViewModel.totalTime.get(), BluetoothViewModel.artist.get(), BluetoothViewModel.title.get(), BluetoothViewModel.imageUrl.get(), BluetoothViewModel.playOrPause.get())));
                    this.curProgress.set(j2);
                    LogUtil.d(TAG, "updateCurrentMusicInfo TYPE_BT CurrentMediaInfoUtils.curProgress.set " + j2);
                }
                break;
            case 7:
            case 8:
            case 9:
                try {
                    this.currentMusic.set(new MultiMusicBean(RadioServiceWrapper.getInstance().mCurrentRadio.getValue()));
                } catch (Exception e2) {
                    this.currentMusic.set(new MultiMusicBean(null));
                    e2.printStackTrace();
                }
                break;
            default:
                switch (i) {
                    case 101:
                        try {
                            this.currentMusic.set(new MultiMusicBean(ThirdManager.getInstance().getAaMusic()));
                        } catch (Exception e3) {
                            this.currentMusic.set(new MultiMusicBean(null));
                            e3.printStackTrace();
                        }
                        break;
                    case 102:
                        try {
                            this.currentMusic.set(new MultiMusicBean(ThirdManager.getInstance().getCpMusic()));
                        } catch (Exception e4) {
                            this.currentMusic.set(new MultiMusicBean(null));
                            e4.printStackTrace();
                        }
                        break;
                    case 103:
                        try {
                            this.currentMusic.set(new MultiMusicBean(ThirdManager.getInstance().getSpotifyMusic()));
                        } catch (Exception e5) {
                            this.currentMusic.set(new MultiMusicBean(null));
                            e5.printStackTrace();
                        }
                        break;
                    default:
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
                                try {
                                    ThirdManager.getInstance().getFceManager().requestAppInfo();
                                } catch (Exception e6) {
                                    LogUtil.d(TAG, "updateCurrentMusicInfo TYPE_FCE e = " + e6.getMessage());
                                    this.currentMusic.set(new MultiMusicBean(null));
                                }
                                break;
                            default:
                                this.currentMusic.set(new MultiMusicBean(null));
                                break;
                        }
                        break;
                }
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("OnLineMusic updateCurrentMusicInfo currentMusic has entity =  ");
        sb.append((this.currentMusic.get() == null || this.currentMusic.get().mEntity == null) ? false : true);
        LogUtil.d(TAG, sb.toString());
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0007. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:22:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean checkCurrentMusicInfoEntityExit() {
        /*
            r3 = this;
            int r0 = com.byd.music.OnLineMusic.musicEngine
            r1 = 1
            r2 = 0
            switch(r0) {
                case 3: goto L40;
                case 4: goto L40;
                case 5: goto L40;
                case 6: goto L39;
                case 7: goto L2c;
                case 8: goto L2c;
                case 9: goto L2c;
                default: goto L7;
            }
        L7:
            switch(r0) {
                case 101: goto L21;
                case 102: goto L16;
                case 103: goto Lb;
                default: goto La;
            }
        La:
            goto L4d
        Lb:
            com.byd.music.ThirdSDK.ThirdManager r0 = com.byd.music.ThirdSDK.ThirdManager.getInstance()
            com.vivid.sdk.spotify.Metadata r0 = r0.getSpotifyMusic()
            if (r0 == 0) goto L4b
            goto L4c
        L16:
            com.byd.music.ThirdSDK.ThirdManager r0 = com.byd.music.ThirdSDK.ThirdManager.getInstance()
            ts.car.service.carplay.data.NowPlayingInfo r0 = r0.getCpMusic()
            if (r0 == 0) goto L4b
            goto L4c
        L21:
            com.byd.music.ThirdSDK.ThirdManager r0 = com.byd.music.ThirdSDK.ThirdManager.getInstance()
            ts.car.service.androidauto.data.MediaMetadata r0 = r0.getAaMusic()
            if (r0 == 0) goto L4b
            goto L4c
        L2c:
            com.byd.music.service.RadioServiceWrapper r0 = com.byd.music.service.RadioServiceWrapper.getInstance()
            androidx.lifecycle.MutableLiveData<com.byd.music.beans.RadioDBBean> r0 = r0.mCurrentRadio
            java.lang.Object r0 = r0.getValue()
            if (r0 == 0) goto L4b
            goto L4c
        L39:
            androidx.databinding.ObservableBoolean r0 = com.byd.music.fragment.bluetooth.BluetoothViewModel.isA2DPConnected
            boolean r2 = r0.get()
            goto L4d
        L40:
            com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper r0 = com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper.getInstance()
            com.byd.music.beans.LocalMusicBean r0 = r0.getCurrentPlayLocalMusic()
            if (r0 == 0) goto L4b
            goto L4c
        L4b:
            r1 = r2
        L4c:
            r2 = r1
        L4d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "checkCurrentMusicInfoEntityExit = "
            r0.append(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "CurrentMediaInfoUtils"
            com.byd.music.utils.LogUtil.d(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.utils.CurrentMediaInfoUtils.checkCurrentMusicInfoEntityExit():boolean");
    }

    public void setPlayingState(boolean z) {
        this.isPlaying.set(z);
    }

    public boolean getPlayingState() {
        return this.isPlaying.get();
    }

    public void updateAlbumBitmap() {
        if (this.currentMusic.get() == null || this.currentMusic.get().getAlbumTAG() == null) {
            LogUtil.d(TAG, "currentMusic or album is null and return");
            return;
        }
        final String albumTAG = this.currentMusic.get().getAlbumTAG();
        if ((this.currentMusic.get().getAlbum() instanceof byte[]) && ((byte[]) this.currentMusic.get().getAlbum()).length < 4) {
            if (((byte[]) this.currentMusic.get().getAlbum()).length == 3) {
                this.currentAlbum = new AbstractMap.SimpleEntry<>(albumTAG, ImageUtils.drawable2Bitmap(SkinManager.getInstance().getDrawable(R.drawable.img_logo_media64)));
                notifyWidgetUpdate();
                return;
            } else {
                if (TextUtils.equals(this.cacheAlbumForCarplay.getKey(), albumTAG)) {
                    this.currentAlbum = new AbstractMap.SimpleEntry<>(albumTAG, this.cacheAlbumForCarplay.getValue());
                    notifyWidgetUpdate();
                    notifyAlbumUpdate();
                    return;
                }
                return;
            }
        }
        if (OnLineMusic.musicEngine == 9 && TextUtils.equals(this.cacheAlbumForDab.getKey(), albumTAG)) {
            this.currentAlbum = new AbstractMap.SimpleEntry<>(albumTAG, this.cacheAlbumForDab.getValue());
            notifyWidgetUpdate();
            notifyAlbumUpdate();
            return;
        }
        Object album = this.currentMusic.get().getAlbum();
        if (album instanceof Bitmap) {
            Bitmap bitmap = (Bitmap) album;
            LogUtil.d(TAG, "updateAlbumBitmap get bitmap succeed, height = " + bitmap.getHeight() + ", width = " + bitmap.getWidth());
            try {
                if (bitmap.getHeight() != bitmap.getWidth()) {
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(360, 360, bitmap.getConfig());
                    Canvas canvas = new Canvas(bitmapCreateBitmap);
                    canvas.drawColor(Color.GRAY);
                    float f = 360;
                    float fMin = Math.min(f / bitmap.getWidth(), f / bitmap.getHeight());
                    Paint paint = new Paint();
                    canvas.save();
                    canvas.scale(fMin, fMin);
                    canvas.drawBitmap(bitmap, ((360 - ((int) (bitmap.getWidth() * fMin))) / 2) / fMin, ((360 - ((int) (bitmap.getHeight() * fMin))) / 2) / fMin, paint);
                    canvas.restore();
                    album = bitmapCreateBitmap;
                }
            } catch (Throwable th) {
                LogUtil.d(TAG, "updateAlbumBitmap get bitmap Throwable e = " + th.getMessage());
                th.printStackTrace();
            }
        }
        Glide.with(Utils.getApp()).asBitmap().transform(new CenterCrop(), new RoundedCorners(12)).placeholder(SkinManager.getInstance().getDrawable(R.drawable.img_logo_media64)).error(SkinManager.getInstance().getDrawable(R.drawable.img_logo_media64)).dontAnimate().load(album).override(176, 176).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).listener(new RequestListener<Bitmap>() { // from class: com.byd.music.utils.CurrentMediaInfoUtils.4
            /* JADX INFO: renamed from: onResourceReady, reason: avoid collision after fix types in other method */
            public boolean onResourceReady2(Bitmap bitmap2, Object obj, Target target, DataSource dataSource, boolean z) {
                return false;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public /* bridge */ /* synthetic */ boolean onResourceReady(Bitmap bitmap2, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                return onResourceReady2(bitmap2, obj, (Target) target, dataSource, z);
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                try {
                    if (glideException.getMessage().contains("FileNotFoundException")) {
                        final String string = new StringBuilder(CurrentMediaInfoUtils.this.currentAlbum.getKey()).toString();
                        CurrentMediaInfoUtils.this.mainHandler.postDelayed(new Runnable() { // from class: com.byd.music.utils.CurrentMediaInfoUtils.4.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    LogUtil.d(CurrentMediaInfoUtils.TAG, "errorListener: onLoadFailed Runnable failedKey = " + string);
                                    LogUtil.d(CurrentMediaInfoUtils.TAG, "errorListener: onLoadFailed Runnable currentAlbum.getKey = " + CurrentMediaInfoUtils.this.currentAlbum.getKey());
                                    if (CurrentMediaInfoUtils.this.loadFailCount < 4 && TextUtils.equals(string, CurrentMediaInfoUtils.this.currentAlbum.getKey())) {
                                        CurrentMediaInfoUtils.this.updateAlbumBitmap();
                                    }
                                    CurrentMediaInfoUtils.access$108(CurrentMediaInfoUtils.this);
                                } catch (Throwable unused) {
                                }
                            }
                        }, 2000L);
                    }
                } catch (Throwable unused) {
                }
                glideException.printStackTrace();
                return false;
            }
        }).into(new CustomTarget<Bitmap>() { // from class: com.byd.music.utils.CurrentMediaInfoUtils.3
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap bitmap2, Transition<? super Bitmap> transition) {
                String str = albumTAG;
                if (str != null && str.equals(CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG())) {
                    LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onResourceReady and valid tag = " + StringSubUtil.subString(albumTAG));
                    CurrentMediaInfoUtils.this.currentAlbum = new AbstractMap.SimpleEntry<>(albumTAG, bitmap2);
                    int i = OnLineMusic.musicEngine;
                    if (i == 9) {
                        CurrentMediaInfoUtils.this.cacheAlbumForDab = new AbstractMap.SimpleEntry<>(albumTAG, bitmap2);
                    } else if (i == 102) {
                        CurrentMediaInfoUtils.this.cacheAlbumForCarplay = new AbstractMap.SimpleEntry<>(albumTAG, bitmap2);
                    }
                    CurrentMediaInfoUtils.this.notifyWidgetUpdate();
                    CurrentMediaInfoUtils.this.notifyAlbumUpdate();
                    return;
                }
                if (albumTAG == null) {
                    LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onResourceReady but null");
                } else {
                    LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onLoadCleared but inValid");
                }
            }

            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable drawable) {
                String str = albumTAG;
                if (str != null && str.equals(CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG())) {
                    LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onLoadCleared and valid tag = " + albumTAG);
                    CurrentMediaInfoUtils.this.currentAlbum = new AbstractMap.SimpleEntry<>(albumTAG, ImageUtils.drawable2Bitmap(drawable));
                    CurrentMediaInfoUtils.this.albumMD5Cache = CurrentMediaInfoUtils.getDefaultAlbumForInstrument();
                    CurrentMediaInfoUtils.this.notifyWidgetUpdate();
                    return;
                }
                if (albumTAG == null) {
                    LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onLoadCleared but null");
                } else {
                    LogUtil.v(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onLoadCleared but inValid");
                }
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable drawable) {
                if (albumTAG == null && CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG() == null) {
                    LogUtil.d(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onLoadFailed double null valid.");
                    CurrentMediaInfoUtils.this.currentAlbum = new AbstractMap.SimpleEntry<>("", ImageUtils.drawable2Bitmap(drawable));
                    CurrentMediaInfoUtils.this.albumMD5Cache = CurrentMediaInfoUtils.getDefaultAlbumForInstrument();
                    CurrentMediaInfoUtils.this.notifyWidgetUpdate();
                    return;
                }
                if (TextUtils.equals(albumTAG, CurrentMediaInfoUtils.this.currentMusic.get().getAlbumTAG())) {
                    LogUtil.d(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onLoadFailed and valid tag = " + albumTAG);
                    CurrentMediaInfoUtils.this.currentAlbum = new AbstractMap.SimpleEntry<>(albumTAG, ImageUtils.drawable2Bitmap(drawable));
                    CurrentMediaInfoUtils.this.albumMD5Cache = CurrentMediaInfoUtils.getDefaultAlbumForInstrument();
                    CurrentMediaInfoUtils.this.notifyWidgetUpdate();
                    return;
                }
                LogUtil.d(CurrentMediaInfoUtils.TAG, "updateAlbumBitmap: onLoadFailed but inValid");
            }
        });
    }

    public void checkAndSetRadioSavePlaying(boolean z) {
        int i = OnLineMusic.musicEngine;
        if (i == 7 || i == 8 || i == 9) {
            this.radioSavePlaying = z;
            SPUtils.getInstance().put(SPConstants.KEY_RADIO_SAVE_PLAYING_STATUS, z);
        }
    }

    public void updateForceAndNotify() {
        updateProgressAndNotify(this.curProgress.get(), this.bufProgress.get(), this.maxProgress.get(), true);
    }

    public void updatePlayStateAndNotify() {
        updateProgressAndNotify(this.curProgress.get(), this.bufProgress.get(), this.maxProgress.get());
    }

    public void updateProgressAndNotify(long j, long j2, long j3) {
        updateProgressAndNotify(j, j2, j3, false);
    }

    public void updateProgressAndNotify(long j, long j2, long j3, final boolean z) {
        long j4;
        synchronized (CurrentMediaInfoUtils.class) {
            LogUtil.d(TAG, "updateProgressAndNotify: enter. cusProgress = " + j + ", maxProgress = " + j3 + ", type = " + OnLineMusic.musicEngine + ", isPlaying = " + this.isPlaying.get() + " isForce = " + z);
            long j5 = 0;
            if (j3 == 0) {
                j4 = 0;
            } else {
                long j6 = j % 1000;
                int i = (int) j;
                j4 = (j6 > 0 ? (i / 1000) + 1 : i / 1000) * 1000;
            }
            if (j3 != 0) {
                long j7 = j2 % 1000;
                int i2 = (int) j2;
                j5 = (j7 > 0 ? (i2 / 1000) + 1 : i2 / 1000) * 1000;
            }
            this.curProgress.set(j4);
            this.bufProgress.set(j5);
            this.maxProgress.set(j3);
            if (OnLineMusic.musicEngine != 9 && !this.mCacheMediaInfo.isCacheUpdate(this.currentMusic.get(), false) && !z) {
                LogUtil.d(TAG, "updateProgressAndNotify: enter but don't need update, just return");
            } else {
                notifyWidgetUpdate();
                this.mainHandler.post(new Runnable() { // from class: com.byd.music.utils.-$$Lambda$CurrentMediaInfoUtils$Xh2JosGHJeyUcgGzfpliN_eTomY
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$updateProgressAndNotify$4$CurrentMediaInfoUtils(z);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: sendMusicInfoToOutSide, reason: merged with bridge method [inline-methods] */
    public void lambda$updateProgressAndNotify$4$CurrentMediaInfoUtils(boolean z) {
        int i = 1;
        boolean z2 = this.mCacheMediaInfo.isCacheMediaInfoUpdate(this.currentMusic.get(), true) || z;
        boolean z3 = this.mCacheMediaInfo.isCacheMediaProgressUpdate(this.currentMusic.get(), true) || z;
        int currentInstrumentMediaType = getCurrentInstrumentMediaType(OnLineMusic.musicEngine);
        int currentRCSMediaType = getCurrentRCSMediaType(OnLineMusic.musicEngine);
        try {
            int i2 = (int) (this.maxProgress.get() == 0 ? 0.0f : (this.curProgress.get() * 100.0f) / this.maxProgress.get());
            if (z2) {
                int i3 = OnLineMusic.musicEngine;
                if (i3 == 7 || i3 == 8 || i3 == 9) {
                    BYDAutoInstrumentDevice.getInstance(Utils.getApp()).setRadioChannel(this.currentMusic.get().getMusicName());
                } else {
                    BYDAutoInstrumentDevice.getInstance(Utils.getApp()).setMusicName(this.currentMusic.get().getMusicName());
                    BYDAutoInstrumentDevice.getInstance(Utils.getApp()).setMusicSingerName(this.currentMusic.get().getArtist());
                }
                BYDAutoInstrumentDevice.getInstance(Utils.getApp()).setMusicInfo(this.isPlaying.get() ? 1 : 2, RadioServiceWrapper.getInstance().isPlaying.get() ? 1 : 2, i2, currentRCSMediaType);
            }
            if (z3) {
                int[] iArrLongToTime = longToTime(this.mCacheMediaInfo.curProgress);
                int[] iArrLongToTime2 = longToTime(this.mCacheMediaInfo.maxProgress);
                BYDAutoInstrumentDevice.getInstance(Utils.getApp()).setPadPrsntTime(iArrLongToTime[0], iArrLongToTime[1], iArrLongToTime[2]);
                BYDAutoInstrumentDevice.getInstance(Utils.getApp()).setPadTotalTime(iArrLongToTime2[0], iArrLongToTime2[1], iArrLongToTime2[2]);
                BYDAutoInstrumentDevice.getInstance(Utils.getApp()).setMusicInfo(this.isPlaying.get() ? 1 : 2, RadioServiceWrapper.getInstance().isPlaying.get() ? 1 : 2, i2, currentRCSMediaType);
            }
            LogUtil.i(TAG, "sendMusicInfoToOutSide: currentRCSType = " + currentRCSMediaType + ", rcsProgress = " + i2 + ", curProgress = " + this.curProgress.get());
        } catch (Throwable th) {
            LogUtil.i(TAG, "sendMusicInfoToOutSide: currentRCS error = " + th.getMessage());
        }
        if (!OnLineMusic.isAudioFocusAvailable()) {
            try {
                this.mCacheMediaInfo.isCacheMediaInfoUpdate(this.currentMusic.get(), true);
                this.mCacheMediaInfo.isCacheMediaProgressUpdate(this.currentMusic.get(), true);
                BYDAutoInstrumentDevice.getInstance(Utils.getApp()).sendMusicInfoUrl("", "", 2, 0, "");
                LogUtil.i(TAG, "sendMusicInfoToOutSide with empty message.");
                return;
            } catch (Throwable unused) {
                return;
            }
        }
        if (z2) {
            try {
                int iSendMusicInfoUrl = BYDAutoInstrumentDevice.getInstance(Utils.getApp()).sendMusicInfoUrl(this.currentMusic.get().getEntity() == null ? WidgetHintTextUtil.getHintMusic(OnLineMusic.musicEngine) : this.currentMusic.get().getMusicName(), this.currentMusic.get().getEntity() == null ? WidgetHintTextUtil.getHintArtist(OnLineMusic.musicEngine) : this.currentMusic.get().getArtist(), this.isPlaying.get() ? 1 : 2, currentInstrumentMediaType, this.albumMD5Cache);
                if (iSendMusicInfoUrl != 0) {
                    this.mainHandler.postDelayed(new Runnable() { // from class: com.byd.music.utils.-$$Lambda$CurrentMediaInfoUtils$LIldVv_ZET9HAAYoap7fxq_59t8
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$sendMusicInfoToOutSide$5$CurrentMediaInfoUtils();
                        }
                    }, 1000L);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("sendMusicInfoToOutSide: current name = ");
                sb.append(StringSubUtil.subString(this.currentMusic.get().getMusicName()));
                sb.append(", isPlaying = ");
                if (!this.isPlaying.get()) {
                    i = 2;
                }
                sb.append(i);
                sb.append(", autoType = ");
                sb.append(currentInstrumentMediaType);
                sb.append(", ret = ");
                sb.append(iSendMusicInfoUrl);
                LogUtil.d(TAG, sb.toString());
            } catch (Throwable th2) {
                LogUtil.i(TAG, "sendMusicInfoToOutSide failed with error = " + th2.getMessage());
                th2.printStackTrace();
                return;
            }
        }
        if (z3) {
            BYDAutoInstrumentDevice.getInstance(Utils.getApp()).sendMusicProgress((int) this.mCacheMediaInfo.curProgress, (int) this.mCacheMediaInfo.maxProgress);
        }
    }

    public /* synthetic */ void lambda$sendMusicInfoToOutSide$5$CurrentMediaInfoUtils() {
        lambda$updateProgressAndNotify$4$CurrentMediaInfoUtils(true);
    }

    private class CacheMediaInfo {
        String albumTAG;
        long bufProgress;
        long curProgress;
        boolean isPlaying;
        long maxProgress;
        String musicArtist;
        String musicName;
        int musicType;

        private CacheMediaInfo() {
        }

        /* synthetic */ CacheMediaInfo(CurrentMediaInfoUtils currentMediaInfoUtils, AnonymousClass1 anonymousClass1) {
            this();
        }

        public boolean isCacheUpdate(MultiMusicBean multiMusicBean, boolean z) {
            if (multiMusicBean == null) {
                return false;
            }
            if (multiMusicBean.getMusicName().equals(this.musicName) && multiMusicBean.getArtist().equals(this.musicArtist) && this.isPlaying == CurrentMediaInfoUtils.getInstance().getPlayingState() && this.musicType == OnLineMusic.musicEngine && this.curProgress == CurrentMediaInfoUtils.this.curProgress.get() && this.maxProgress == CurrentMediaInfoUtils.this.maxProgress.get() && this.bufProgress == CurrentMediaInfoUtils.this.bufProgress.get() && TextUtils.equals(this.albumTAG, multiMusicBean.albumTAG)) {
                return false;
            }
            if (!z) {
                return true;
            }
            this.musicName = multiMusicBean.getMusicName();
            this.musicArtist = multiMusicBean.getArtist();
            this.isPlaying = CurrentMediaInfoUtils.this.getPlayingState();
            this.musicType = OnLineMusic.musicEngine;
            this.curProgress = CurrentMediaInfoUtils.this.curProgress.get();
            this.maxProgress = CurrentMediaInfoUtils.this.maxProgress.get();
            this.bufProgress = CurrentMediaInfoUtils.this.bufProgress.get();
            this.albumTAG = multiMusicBean.albumTAG;
            return true;
        }

        public boolean isCacheMediaInfoUpdate(MultiMusicBean multiMusicBean, boolean z) {
            if (multiMusicBean == null) {
                return false;
            }
            if (multiMusicBean.getMusicName().equals(this.musicName) && multiMusicBean.getArtist().equals(this.musicArtist) && this.isPlaying == CurrentMediaInfoUtils.getInstance().getPlayingState() && this.musicType == OnLineMusic.musicEngine && TextUtils.equals(this.albumTAG, multiMusicBean.albumTAG)) {
                return false;
            }
            if (!z) {
                return true;
            }
            this.musicName = multiMusicBean.getMusicName();
            this.musicArtist = multiMusicBean.getArtist();
            this.isPlaying = CurrentMediaInfoUtils.this.getPlayingState();
            this.musicType = OnLineMusic.musicEngine;
            this.albumTAG = multiMusicBean.albumTAG;
            return true;
        }

        public boolean isCacheMediaProgressUpdate(MultiMusicBean multiMusicBean, boolean z) {
            if (multiMusicBean == null) {
                return false;
            }
            if (this.curProgress == CurrentMediaInfoUtils.this.curProgress.get() && this.maxProgress == CurrentMediaInfoUtils.this.maxProgress.get() && this.bufProgress == CurrentMediaInfoUtils.this.bufProgress.get()) {
                return false;
            }
            if (!z) {
                return true;
            }
            this.curProgress = CurrentMediaInfoUtils.this.curProgress.get();
            this.maxProgress = CurrentMediaInfoUtils.this.maxProgress.get();
            this.bufProgress = CurrentMediaInfoUtils.this.bufProgress.get();
            return true;
        }
    }

    public static int[] longToTime(long j) {
        int i = (int) (j / 3600000);
        long j2 = j % 3600000;
        int i2 = (int) (j2 / 60000);
        long j3 = j2 % 60000;
        int i3 = (int) (j3 / 1000);
        LogUtil.d(TAG, "longToTime leave with milliseconds = " + j3 + ", hours = " + i + ", minutes = " + i2 + ", seconds = " + i3);
        return new int[]{i, i2, i3};
    }

    public static String getMD5(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance(KeyProperties.DIGEST_MD5).digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(String.format("%02x", Byte.valueOf(b)));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
