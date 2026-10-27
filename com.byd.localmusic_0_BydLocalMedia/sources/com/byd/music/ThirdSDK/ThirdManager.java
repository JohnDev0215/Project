package com.byd.music.ThirdSDK;

import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.OnLineMusic;
import com.byd.music.ThirdSDK.AndroidAuto.AndroidAutoManager;
import com.byd.music.ThirdSDK.Carplay.CarplayManager;
import com.byd.music.ThirdSDK.Spotify.SpotifyManager;
import com.byd.music.ThirdSDK.amazon.AmazonManager;
import com.byd.music.ThirdSDK.fce.CommonFceManager;
import com.byd.music.ThirdSDK.fce.FceUtil;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.vivid.sdk.spotify.Metadata;
import java.util.Arrays;
import java.util.HashMap;
import ts.car.service.androidauto.data.MediaMetadata;
import ts.car.service.carplay.data.NowPlayingInfo;

/* JADX INFO: loaded from: classes5.dex */
public class ThirdManager {
    private static final String TAG = "ThirdManager";
    private static volatile ThirdManager instance;
    public boolean FceAudialsEnable;
    public boolean FceAudibleEnable;
    public boolean FceBloomBergEnable;
    public boolean FceDeezerEnable;
    public HashMap<Integer, String> FceEngineMap;
    public boolean FceFLOEnable;
    public HashMap<String, Integer> FceFocusMap;
    public boolean FceIHeartRadioEnable;
    public boolean FceLibbyEnable;
    public boolean FceMelonEnable;
    public boolean FcePocketcastsEnable;
    public boolean FceRadiolineEnable;
    public boolean FceTidalEnable;
    public boolean FceTuneinEnable;
    private MediaMetadata aaMusic;
    private boolean androidAutoEnable;
    private boolean carplayEnable;
    private NowPlayingInfo cpMusic;
    private CommonFceManager fceManager;
    public MutableLiveData<Boolean> isAndroidAutoConnected;
    public MutableLiveData<Boolean> isCarplayConnected;
    private Metadata spotifyMusic;
    private boolean spotifyEnable = SPUtils.getInstance().getBoolean("spotify_enable");
    private boolean amazonEnable = SPUtils.getInstance().getBoolean("amazon_enable");
    private boolean floEnable = SPUtils.getInstance().getBoolean("flo_enable");

    public Metadata getSpotifyMusic() {
        return this.spotifyMusic;
    }

    public void setSpotifyMusic(Metadata metadata) {
        Metadata metadata2;
        if (metadata == null || (metadata2 = this.spotifyMusic) == null || TextUtils.isEmpty(metadata2.getCoverUrl()) || !TextUtils.equals(metadata.getTitle(), this.spotifyMusic.getTitle()) || !TextUtils.equals(metadata.getSubTitle(), this.spotifyMusic.getSubTitle()) || metadata.getDuration() != this.spotifyMusic.getDuration()) {
            this.spotifyMusic = metadata;
            if (OnLineMusic.musicEngine == 103) {
                CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
            }
        }
    }

    public MediaMetadata getAaMusic() {
        return this.aaMusic;
    }

    public void setAaMusic(MediaMetadata mediaMetadata) {
        if (mediaMetadata != null && this.aaMusic != null && TextUtils.equals(mediaMetadata.getSong(), this.aaMusic.getSong()) && TextUtils.equals(mediaMetadata.getArtist(), this.aaMusic.getArtist()) && mediaMetadata.getDurationSeconds() == this.aaMusic.getDurationSeconds() && Arrays.equals(mediaMetadata.getAlbumArt(), this.aaMusic.getAlbumArt())) {
            return;
        }
        this.aaMusic = mediaMetadata;
        if (OnLineMusic.musicEngine == 101) {
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        }
    }

    public NowPlayingInfo getCpMusic() {
        return this.cpMusic;
    }

    public void setCpMusic(NowPlayingInfo nowPlayingInfo) {
        if (nowPlayingInfo != null && this.cpMusic != null && nowPlayingInfo.getArtworkData() == null && TextUtils.equals(nowPlayingInfo.getMediaItemTitle(), this.cpMusic.getMediaItemTitle()) && TextUtils.equals(nowPlayingInfo.getMediaItemArtist(), this.cpMusic.getMediaItemArtist()) && nowPlayingInfo.getMediaItemPlaybackDurationMs() == this.cpMusic.getMediaItemPlaybackDurationMs()) {
            return;
        }
        if (nowPlayingInfo != null && this.cpMusic != null && nowPlayingInfo.getArtworkData() == null) {
            nowPlayingInfo.setArtworkSize(1);
            nowPlayingInfo.setArtworkData(new byte[1]);
            nowPlayingInfo.setArtworkPath(this.cpMusic.getArtworkPath());
        }
        this.cpMusic = nowPlayingInfo;
        if (OnLineMusic.musicEngine == 102) {
            CurrentMediaInfoUtils.getInstance().updateCurrentMusicInfo();
        }
    }

    public static ThirdManager getInstance() {
        if (instance == null) {
            synchronized (ThirdManager.class) {
                if (instance == null) {
                    instance = new ThirdManager();
                }
            }
        }
        return instance;
    }

    private ThirdManager() {
        LogUtil.e(TAG, "ThirdManager init enter...");
        this.isCarplayConnected = new MutableLiveData<>(false);
        this.isAndroidAutoConnected = new MutableLiveData<>(false);
        this.carplayEnable = Utils.getApp().getPackageManager().getLaunchIntentForPackage(CarplayManager.CarplayPKG) != null;
        this.androidAutoEnable = Utils.getApp().getPackageManager().getLaunchIntentForPackage(AndroidAutoManager.AndroidAutoPKG) != null;
        updateFceGroup();
        instance = this;
        LogUtil.e(TAG, "ThirdManager init finish with carplayEnable = " + this.carplayEnable + ", androidAutoEnable = " + this.androidAutoEnable);
    }

    public void updateFceGroup() {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<Integer, String> map2 = new HashMap<>();
        boolean zIsPKGInstall = FceUtil.isPKGInstall(FceUtil.FLOPKG);
        this.FceFLOEnable = zIsPKGInstall;
        if (zIsPKGInstall) {
            map2.put(105, FceUtil.FLOPKG);
            map.put(FceUtil.FLOAudioFocusPKG, 105);
        }
        boolean zIsPKGInstall2 = FceUtil.isPKGInstall(FceUtil.AudialsPKG);
        this.FceAudialsEnable = zIsPKGInstall2;
        if (zIsPKGInstall2) {
            map2.put(106, FceUtil.AudialsPKG);
            map.put(FceUtil.AudialsAudioFocusPKG, 106);
        }
        boolean zIsPKGInstall3 = FceUtil.isPKGInstall(FceUtil.AudiblePKG);
        this.FceAudibleEnable = zIsPKGInstall3;
        if (zIsPKGInstall3) {
            map2.put(107, FceUtil.AudiblePKG);
            map.put(FceUtil.AudibleAudioFocusPKG, 107);
        }
        boolean zIsPKGInstall4 = FceUtil.isPKGInstall(FceUtil.BloomBergPKG);
        this.FceBloomBergEnable = zIsPKGInstall4;
        if (zIsPKGInstall4) {
            map2.put(108, FceUtil.BloomBergPKG);
            map.put(FceUtil.BloomBergAudioFocusPKG, 108);
        }
        boolean zIsPKGInstall5 = FceUtil.isPKGInstall(FceUtil.DeezerPKG);
        this.FceDeezerEnable = zIsPKGInstall5;
        if (zIsPKGInstall5) {
            map2.put(109, FceUtil.DeezerPKG);
            map.put(FceUtil.DeezerAudioFocusPKG, 109);
        }
        boolean zIsPKGInstall6 = FceUtil.isPKGInstall(FceUtil.IheartradioPKG);
        this.FceIHeartRadioEnable = zIsPKGInstall6;
        if (zIsPKGInstall6) {
            map2.put(110, FceUtil.IheartradioPKG);
            map.put(FceUtil.IheartradioAudioFocusPKG, 110);
        }
        boolean zIsPKGInstall7 = FceUtil.isPKGInstall(FceUtil.LibbyPKG);
        this.FceLibbyEnable = zIsPKGInstall7;
        if (zIsPKGInstall7) {
            map2.put(111, FceUtil.LibbyPKG);
            map.put(FceUtil.LibbyAudioFocusPKG, 111);
        }
        boolean zIsPKGInstall8 = FceUtil.isPKGInstall(FceUtil.MelonPKG);
        this.FceMelonEnable = zIsPKGInstall8;
        if (zIsPKGInstall8) {
            map2.put(112, FceUtil.MelonPKG);
            map.put(FceUtil.MelonAudioFocusPKG, 112);
        }
        boolean zIsPKGInstall9 = FceUtil.isPKGInstall(FceUtil.PocketcastsPKG);
        this.FcePocketcastsEnable = zIsPKGInstall9;
        if (zIsPKGInstall9) {
            map2.put(113, FceUtil.PocketcastsPKG);
            map.put(FceUtil.PocketcastsAudioFocusPKG, 113);
        }
        boolean zIsPKGInstall10 = FceUtil.isPKGInstall(FceUtil.RadiolinePKG);
        this.FceRadiolineEnable = zIsPKGInstall10;
        if (zIsPKGInstall10) {
            map2.put(114, FceUtil.RadiolinePKG);
            map.put(FceUtil.RadiolineAudioFocusPKG, 114);
        }
        boolean zIsPKGInstall11 = FceUtil.isPKGInstall(FceUtil.TidalPKG);
        this.FceTidalEnable = zIsPKGInstall11;
        if (zIsPKGInstall11) {
            map2.put(115, FceUtil.TidalPKG);
            map.put(FceUtil.TidalAudioFocusPKG, 115);
        }
        boolean zIsPKGInstall12 = FceUtil.isPKGInstall(FceUtil.TuneinPKG);
        this.FceTuneinEnable = zIsPKGInstall12;
        if (zIsPKGInstall12) {
            map2.put(116, FceUtil.TuneinPKG);
            map.put(FceUtil.TuneinAudioFocusPKG, 116);
        }
        if (map.size() != 0) {
            this.FceFocusMap = map;
            this.FceEngineMap = map2;
        } else {
            this.FceFocusMap = null;
            this.FceEngineMap = null;
        }
    }

    public void setSpotifyEnable(boolean z) {
        this.spotifyEnable = z;
        SPUtils.getInstance().put("spotify_enable", z);
    }

    public void setAmazonEnable(boolean z) {
        this.amazonEnable = z;
        SPUtils.getInstance().put("amazon_enable", z);
    }

    public void setFloEnable(boolean z) {
        this.floEnable = z;
        SPUtils.getInstance().put("flo_enable", z);
    }

    public void init() {
        LogUtil.d(TAG, "method init enter");
        if (this.spotifyEnable) {
            SpotifyManager.init();
        }
        if (this.carplayEnable) {
            CarplayManager.init();
        }
        if (this.androidAutoEnable) {
            AndroidAutoManager.init();
        }
    }

    public static boolean isSupportType(int i) {
        switch (i) {
            case 101:
                return getInstance().androidAutoEnable;
            case 102:
                return getInstance().carplayEnable;
            case 103:
                return getInstance().spotifyEnable;
            case 104:
                return getInstance().amazonEnable;
            case 105:
                return getInstance().FceFLOEnable;
            case 106:
                return getInstance().FceAudialsEnable;
            case 107:
                return getInstance().FceAudibleEnable;
            case 108:
                return getInstance().FceBloomBergEnable;
            case 109:
                return getInstance().FceDeezerEnable;
            case 110:
                return getInstance().FceIHeartRadioEnable;
            case 111:
                return getInstance().FceLibbyEnable;
            case 112:
                return getInstance().FceMelonEnable;
            case 113:
                return getInstance().FcePocketcastsEnable;
            case 114:
                return getInstance().FceRadiolineEnable;
            case 115:
                return getInstance().FceTidalEnable;
            case 116:
                return getInstance().FceTuneinEnable;
            default:
                return false;
        }
    }

    public static boolean isCarplayConnected() {
        try {
            return instance.isCarplayConnected.getValue().booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static boolean isAndroidAutoConnected() {
        try {
            LogUtil.d("isAndroidAutoConnected ret = " + instance.isAndroidAutoConnected.getValue());
            return instance.isAndroidAutoConnected.getValue().booleanValue();
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public static void setCarplayConnectState(boolean z) {
        LogUtil.d(TAG, "setCarplayConnectState = " + z);
        instance.isCarplayConnected.postValue(Boolean.valueOf(z));
        if (!z && OnLineMusic.musicEngine == 102) {
            CurrentMediaInfoUtils.getInstance().setPlayingState(false);
            getInstance().setCpMusic(null);
            OnLineMusic.setDefaultEngine();
        } else if (z && OnLineMusic.musicEngine != 102 && CarplayManager.CarplayAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
            OnLineMusic.setMusicEngine(102);
        }
    }

    public static void setAndroidAutoConnectState(boolean z) {
        LogUtil.d(TAG, "setAndroidAutoConnectState = " + z);
        instance.isAndroidAutoConnected.postValue(Boolean.valueOf(z));
        if (!z && OnLineMusic.musicEngine == 101) {
            CurrentMediaInfoUtils.getInstance().setPlayingState(false);
            getInstance().setAaMusic(null);
            OnLineMusic.setDefaultEngine();
        } else {
            if (!z || OnLineMusic.musicEngine == 101) {
                return;
            }
            if (OnLineMusic.musicEngine == 6) {
                OnLineMusic.setMusicEngine(101);
            } else if (AndroidAutoManager.AndroidAutoAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage)) {
                OnLineMusic.setMusicEngine(101);
            }
        }
    }

    public static String playNext() {
        int i = OnLineMusic.musicEngine;
        LogUtil.d(TAG, "play next enter with engine = " + i);
        switch (i) {
            case 101:
                AndroidAutoManager.playNext();
                break;
            case 102:
                CarplayManager.playNext();
                break;
            case 103:
                SpotifyManager.playNext();
                break;
            case 104:
                AmazonManager.playNext();
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
                CommonFceManager fceManager = instance.getFceManager();
                if (fceManager != null) {
                    fceManager.playNext();
                }
                break;
        }
        return "";
    }

    public static String playPre() {
        switch (OnLineMusic.musicEngine) {
            case 101:
                AndroidAutoManager.playPre();
                break;
            case 102:
                CarplayManager.playPre();
                break;
            case 103:
                SpotifyManager.playPre();
                break;
            case 104:
                AmazonManager.playPre();
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
                CommonFceManager fceManager = instance.getFceManager();
                if (fceManager != null) {
                    fceManager.playPrevious();
                }
                break;
        }
        return "";
    }

    public static String playOrStop(boolean z) {
        if (z && DeviceInfoUtils.isInCall()) {
            LogUtil.d(TAG, "play isInCall and return musicEngine = " + OnLineMusic.musicEngine);
            return MediaControl.REASON_AUDIO_OUT_OF_FOCUS;
        }
        switch (OnLineMusic.musicEngine) {
            case 101:
                AndroidAutoManager.playOrStop(z);
                return "";
            case 102:
                CarplayManager.playOrStop(z);
                return "";
            case 103:
                SpotifyManager.playOrStop(z);
                return "";
            case 104:
                AmazonManager.playOrStop(z);
                return "";
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
                CommonFceManager fceManager = instance.getFceManager();
                if (fceManager == null) {
                    return "";
                }
                fceManager.playOrStop(z);
                return "";
            default:
                return "";
        }
    }

    public CommonFceManager getFceManager() {
        return instance.fceManager;
    }

    public void setFceManager(CommonFceManager commonFceManager) {
        instance.fceManager = commonFceManager;
    }
}
