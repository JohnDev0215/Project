package com.byd.vrassistant.manager;

import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.media.AudioFocusInfo;
import android.net.Uri;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.text.TextUtils;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.struct.MusicInfo;
import cerence.ark.assistant.controller.struct.MusicSearchCriteriaInfo;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.conversation.AudioHelper;
import com.byd.vrassistant.conversation.AudioSdk;
import com.byd.vrassistant.conversation.RadioHelper;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper;
import com.byd.vrassistant.receiver.UsbReceiver;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.google.common.base.Ascii;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public class LocalMediaManager {
    public static final int ACTION_NONE = 255;
    public static final int ACTION_PLAY = 19;
    public static final String AMAZON_PACKAGE_NAME2 = "com.android.car.media";
    public static final String BLUETOOTHMEDIA_PACKAGE_NAME = "com.android.bluetooth";
    public static final String KARAOKE_PACKAGE_NAME = "com.stingray.karaoke.car.byd8155f";
    public static final String LOCALMEDIA_PACKAGE_NAME = "com.byd.localmusic";
    public static final int MULTIMEDIA_STATE_PLAY = 0;
    public static final String SPOTIFY_PACKAGE_NAME = "com.spotify.music";
    private static final String TAG = "NEU_LocalMediaManager";
    private static volatile LocalMediaManager mInstance;
    int madieRet;
    private int mediaPlaySate;
    private int savedStreamType;
    public static final String AMAZON_PACKAGE_NAME = "com.amazon.mp3.automotiveOS";
    public static final String[] SUPPORTED_AMAZON_APPS = {AMAZON_PACKAGE_NAME, "com.android.car.media"};
    private Context context = BydVoiceApplication.getContext();
    private boolean isUsbConnected = false;
    List<MusicInfo> localMusicList = new ArrayList();
    List<MusicInfo> usbMusicList = new ArrayList();
    List<MusicInfo> sdcardMusicList = new ArrayList();
    private boolean isMediaRunning = false;
    private String mCurrentAudioPackage = "";
    public String currentMediaProvider = MediaProviderEnum.NONE;
    public String recentMediaProvider = MediaProviderEnum.NONE;

    public boolean isUsbConnected() {
        return this.isUsbConnected;
    }

    public void setUsbConnected(boolean z) {
        this.isUsbConnected = z;
    }

    public int getMediaPlaySate() {
        return this.mediaPlaySate;
    }

    public static LocalMediaManager get() {
        if (mInstance == null) {
            synchronized (LocalMediaManager.class) {
                if (mInstance == null) {
                    mInstance = new LocalMediaManager();
                }
            }
        }
        return mInstance;
    }

    public boolean isMediaRunning() {
        return this.isMediaRunning;
    }

    public void saveCurrentRunningStateByProvider(String str) {
        boolean z = false;
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue() || RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
            this.isMediaRunning = false;
            return;
        }
        str.hashCode();
        switch (str) {
            case "SDCARD":
            case "MEMORY_CARD":
            case "USB":
            case "LOCAL":
                this.isMediaRunning = false;
                break;
            case "BLUETOOTH":
                if (AudioHelper.getInstance(this.context).IfBTMusicWorking().booleanValue() && RadioSdk.getInstance(this.context).getPlayStatus() == 0) {
                    z = true;
                }
                this.isMediaRunning = z;
                break;
        }
    }

    public void saveCurrentRunningState() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(LOCALMEDIA_PACKAGE_NAME);
        arrayList.add(BLUETOOTHMEDIA_PACKAGE_NAME);
        int playStatus = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus();
        RLog.i(TAG, "isMediaProviderRunning Local mediaState: " + playStatus);
        if (BydVoiceApplication.isApplicationForeground(arrayList) || playStatus == 0) {
            this.isMediaRunning = true;
        } else {
            this.isMediaRunning = false;
        }
    }

    public void saveCurrentPlayingState() {
        this.mediaPlaySate = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatusForManager();
        RLog.i(TAG, "saveCurrentPlayingState Local mediaState: " + this.mediaPlaySate);
    }

    private LocalMediaManager() {
    }

    public void init() {
        registerUsbReceiver();
    }

    public int getMadieRet() {
        return this.madieRet;
    }

    public void setMadieRet(int i) {
        this.madieRet = i;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public String getCurrentMediaProvider() {
        String packetAudio = RadioSdk.getInstance(this.context).getPacketAudio();
        if (TextUtils.isEmpty(packetAudio)) {
            packetAudio = "";
        }
        packetAudio.hashCode();
        byte b = -1;
        switch (packetAudio.hashCode()) {
            case -2029002628:
                if (packetAudio.equals(KARAOKE_PACKAGE_NAME)) {
                    b = 0;
                }
                break;
            case -1414906960:
                if (packetAudio.equals(OnlineMediaHelper.POCKETCASTS_SERVICE_PACKAGE_NAME)) {
                    b = 1;
                }
                break;
            case -1405221166:
                if (packetAudio.equals(OnlineMediaHelper.TUNEIN_SERVICE_PACKAGE_NAME)) {
                    b = 2;
                }
                break;
            case -1238500887:
                if (packetAudio.equals(OnlineMediaHelper.LIBBY_SERVICE_PACKAGE_NAME)) {
                    b = 3;
                }
                break;
            case 0:
                if (packetAudio.equals("")) {
                    b = 4;
                }
                break;
            case 346148845:
                if (packetAudio.equals(OnlineMediaHelper.MELON_SERVICE_PACKAGE_NAME)) {
                    b = 5;
                }
                break;
            case 611866594:
                if (packetAudio.equals(OnlineMediaHelper.AUDIALS_SERVICE_PACKAGE_NAME)) {
                    b = 6;
                }
                break;
            case 613209156:
                if (packetAudio.equals(SPOTIFY_PACKAGE_NAME)) {
                    b = 7;
                }
                break;
            case 682812825:
                if (packetAudio.equals(OnlineMediaHelper.BLOOMBERG_SERVICE_PACKAGE_NAME)) {
                    b = 8;
                }
                break;
            case 729178156:
                if (packetAudio.equals(OnlineMediaHelper.MUSICMATE_SERVICE_PACKAGE_NAME)) {
                    b = 9;
                }
                break;
            case 1022450689:
                if (packetAudio.equals(OnlineMediaHelper.TIDAL_SERVICE_PACKAGE_NAME)) {
                    b = 10;
                }
                break;
            case 1316692951:
                if (packetAudio.equals(OnlineMediaHelper.IHEARTRADIO_SERVICE_PACKAGE_NAME)) {
                    b = 11;
                }
                break;
            case 1419403383:
                if (packetAudio.equals(OnlineMediaHelper.AUDIBLE_SERVICE_PACKAGE_NAME)) {
                    b = 12;
                }
                break;
            case 1435822868:
                if (packetAudio.equals(AMAZON_PACKAGE_NAME)) {
                    b = Ascii.CR;
                }
                break;
            case 1504905431:
                if (packetAudio.equals(OnlineMediaHelper.DEEZER_SERVICE_PACKAGE_NAME)) {
                    b = Ascii.SO;
                }
                break;
            case 1817998425:
                if (packetAudio.equals(OnlineMediaHelper.RADIOLINE_SERVICE_PACKAGE_NAME)) {
                    b = Ascii.SI;
                }
                break;
            case 1919366984:
                if (packetAudio.equals(LOCALMEDIA_PACKAGE_NAME)) {
                    b = 16;
                }
                break;
            case 1948807874:
                if (packetAudio.equals(BLUETOOTHMEDIA_PACKAGE_NAME)) {
                    b = 17;
                }
                break;
        }
        switch (b) {
            case 0:
                this.currentMediaProvider = MediaProviderEnum.KARAOKE;
                break;
            case 1:
                this.currentMediaProvider = MediaProviderEnum.POCKET_CASTS;
                break;
            case 2:
                this.currentMediaProvider = MediaProviderEnum.TUNEIN;
                break;
            case 3:
                this.currentMediaProvider = MediaProviderEnum.LIBBY;
                break;
            case 4:
                boolean zIsAppInstalled = ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), KARAOKE_PACKAGE_NAME);
                ArrayList arrayList = new ArrayList();
                arrayList.add(KARAOKE_PACKAGE_NAME);
                if (zIsAppInstalled && BydVoiceApplication.isApplicationForeground(arrayList)) {
                    this.currentMediaProvider = MediaProviderEnum.KARAOKE;
                } else {
                    this.currentMediaProvider = MediaProviderEnum.NONE;
                }
                break;
            case 5:
                this.currentMediaProvider = MediaProviderEnum.MELON;
                break;
            case 6:
                this.currentMediaProvider = MediaProviderEnum.AUDIALS_PLAY;
                break;
            case 7:
                this.currentMediaProvider = MediaProviderEnum.SPOTIFY;
                break;
            case 8:
                this.currentMediaProvider = MediaProviderEnum.BLOOMBERG;
                break;
            case 9:
                this.currentMediaProvider = MediaProviderEnum.FLO;
                break;
            case 10:
                this.currentMediaProvider = MediaProviderEnum.TIDAL;
                break;
            case 11:
                this.currentMediaProvider = MediaProviderEnum.IHEARTRADIO;
                break;
            case 12:
                this.currentMediaProvider = MediaProviderEnum.AUDIBLE;
                break;
            case 13:
                this.currentMediaProvider = MediaProviderEnum.AMAZON_MUSIC;
                break;
            case 14:
                this.currentMediaProvider = MediaProviderEnum.DEEZER;
                break;
            case 15:
                this.currentMediaProvider = MediaProviderEnum.RADIOLINE;
                break;
            case 16:
                int localMusicType = AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType();
                RLog.i(TAG, "getCurrentMediaProvider mediaType==" + localMusicType);
                if (RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 8) {
                    this.currentMediaProvider = "BLUETOOTH";
                } else if (localMusicType == 0) {
                    this.currentMediaProvider = MediaProviderEnum.LOCAL;
                } else if (localMusicType == 2) {
                    this.currentMediaProvider = MediaProviderEnum.SDCARD;
                } else if (localMusicType == 1) {
                    this.currentMediaProvider = MediaProviderEnum.USB;
                } else {
                    this.currentMediaProvider = MediaProviderEnum.LOCAL;
                }
                break;
            case 17:
                this.currentMediaProvider = "BLUETOOTH";
                break;
            default:
                this.currentMediaProvider = MediaProviderEnum.NONE;
                break;
        }
        RLog.e(TAG, "getCurrentMediaProvider:" + this.currentMediaProvider + ",package:" + packetAudio);
        return this.currentMediaProvider;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public String getRecentMediaProvider() {
        String latestPacketAudio;
        try {
            latestPacketAudio = RadioSdk.getInstance(this.context).getLatestPacketAudio();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            latestPacketAudio = "";
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            latestPacketAudio = "";
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            latestPacketAudio = "";
        }
        if (TextUtils.isEmpty(latestPacketAudio)) {
            latestPacketAudio = "";
        }
        boolean zIsAppInstalled = ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), KARAOKE_PACKAGE_NAME);
        ArrayList arrayList = new ArrayList();
        arrayList.add(KARAOKE_PACKAGE_NAME);
        if (zIsAppInstalled && BydVoiceApplication.isApplicationForeground(arrayList)) {
            this.recentMediaProvider = MediaProviderEnum.KARAOKE;
            return MediaProviderEnum.KARAOKE;
        }
        latestPacketAudio.hashCode();
        byte b = -1;
        switch (latestPacketAudio.hashCode()) {
            case -2029002628:
                if (latestPacketAudio.equals(KARAOKE_PACKAGE_NAME)) {
                    b = 0;
                }
                break;
            case -1414906960:
                if (latestPacketAudio.equals(OnlineMediaHelper.POCKETCASTS_SERVICE_PACKAGE_NAME)) {
                    b = 1;
                }
                break;
            case -1405221166:
                if (latestPacketAudio.equals(OnlineMediaHelper.TUNEIN_SERVICE_PACKAGE_NAME)) {
                    b = 2;
                }
                break;
            case -1238500887:
                if (latestPacketAudio.equals(OnlineMediaHelper.LIBBY_SERVICE_PACKAGE_NAME)) {
                    b = 3;
                }
                break;
            case 0:
                if (latestPacketAudio.equals("")) {
                    b = 4;
                }
                break;
            case 346148845:
                if (latestPacketAudio.equals(OnlineMediaHelper.MELON_SERVICE_PACKAGE_NAME)) {
                    b = 5;
                }
                break;
            case 611866594:
                if (latestPacketAudio.equals(OnlineMediaHelper.AUDIALS_SERVICE_PACKAGE_NAME)) {
                    b = 6;
                }
                break;
            case 613209156:
                if (latestPacketAudio.equals(SPOTIFY_PACKAGE_NAME)) {
                    b = 7;
                }
                break;
            case 682812825:
                if (latestPacketAudio.equals(OnlineMediaHelper.BLOOMBERG_SERVICE_PACKAGE_NAME)) {
                    b = 8;
                }
                break;
            case 729178156:
                if (latestPacketAudio.equals(OnlineMediaHelper.MUSICMATE_SERVICE_PACKAGE_NAME)) {
                    b = 9;
                }
                break;
            case 1022450689:
                if (latestPacketAudio.equals(OnlineMediaHelper.TIDAL_SERVICE_PACKAGE_NAME)) {
                    b = 10;
                }
                break;
            case 1316692951:
                if (latestPacketAudio.equals(OnlineMediaHelper.IHEARTRADIO_SERVICE_PACKAGE_NAME)) {
                    b = 11;
                }
                break;
            case 1419403383:
                if (latestPacketAudio.equals(OnlineMediaHelper.AUDIBLE_SERVICE_PACKAGE_NAME)) {
                    b = 12;
                }
                break;
            case 1435822868:
                if (latestPacketAudio.equals(AMAZON_PACKAGE_NAME)) {
                    b = Ascii.CR;
                }
                break;
            case 1504905431:
                if (latestPacketAudio.equals(OnlineMediaHelper.DEEZER_SERVICE_PACKAGE_NAME)) {
                    b = Ascii.SO;
                }
                break;
            case 1817998425:
                if (latestPacketAudio.equals(OnlineMediaHelper.RADIOLINE_SERVICE_PACKAGE_NAME)) {
                    b = Ascii.SI;
                }
                break;
            case 1919366984:
                if (latestPacketAudio.equals(LOCALMEDIA_PACKAGE_NAME)) {
                    b = 16;
                }
                break;
            case 1948807874:
                if (latestPacketAudio.equals(BLUETOOTHMEDIA_PACKAGE_NAME)) {
                    b = 17;
                }
                break;
        }
        switch (b) {
            case 0:
                this.recentMediaProvider = MediaProviderEnum.KARAOKE;
                break;
            case 1:
                this.recentMediaProvider = MediaProviderEnum.POCKET_CASTS;
                break;
            case 2:
                this.recentMediaProvider = MediaProviderEnum.TUNEIN;
                break;
            case 3:
                this.recentMediaProvider = MediaProviderEnum.LIBBY;
                break;
            case 4:
                if (zIsAppInstalled && BydVoiceApplication.isApplicationForeground(arrayList)) {
                    this.recentMediaProvider = MediaProviderEnum.KARAOKE;
                } else {
                    this.recentMediaProvider = MediaProviderEnum.NONE;
                }
                break;
            case 5:
                this.recentMediaProvider = MediaProviderEnum.MELON;
                break;
            case 6:
                this.recentMediaProvider = MediaProviderEnum.AUDIALS_PLAY;
                break;
            case 7:
                this.recentMediaProvider = MediaProviderEnum.SPOTIFY;
                break;
            case 8:
                this.recentMediaProvider = MediaProviderEnum.BLOOMBERG;
                break;
            case 9:
                this.recentMediaProvider = MediaProviderEnum.FLO;
                break;
            case 10:
                this.recentMediaProvider = MediaProviderEnum.TIDAL;
                break;
            case 11:
                this.recentMediaProvider = MediaProviderEnum.IHEARTRADIO;
                break;
            case 12:
                this.recentMediaProvider = MediaProviderEnum.AUDIBLE;
                break;
            case 13:
                this.recentMediaProvider = MediaProviderEnum.AMAZON_MUSIC;
                break;
            case 14:
                this.recentMediaProvider = MediaProviderEnum.DEEZER;
                break;
            case 15:
                this.recentMediaProvider = MediaProviderEnum.RADIOLINE;
                break;
            case 16:
                int localMusicType = AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType();
                RLog.i(TAG, "getRecentMediaProvider  mediaType==" + localMusicType);
                if (RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 8) {
                    this.recentMediaProvider = "BLUETOOTH";
                } else if (localMusicType == 0) {
                    this.recentMediaProvider = MediaProviderEnum.LOCAL;
                } else if (localMusicType == 2) {
                    this.recentMediaProvider = MediaProviderEnum.SDCARD;
                } else if (localMusicType == 1) {
                    this.recentMediaProvider = MediaProviderEnum.USB;
                } else {
                    this.recentMediaProvider = MediaProviderEnum.LOCAL;
                }
                break;
            case 17:
                this.recentMediaProvider = "BLUETOOTH";
                break;
            default:
                this.recentMediaProvider = MediaProviderEnum.NONE;
                break;
        }
        RLog.e(TAG, "getRecentMediaProvider:" + this.recentMediaProvider + ",package:" + latestPacketAudio);
        return this.recentMediaProvider;
    }

    public void setCurrentMediaProvider(String str) {
        this.currentMediaProvider = str;
    }

    public void registerUsbReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
        intentFilter.addAction("android.intent.action.MEDIA_EJECT");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_STARTED");
        intentFilter.addAction("android.intent.action.MEDIA_SCANNER_FINISHED");
        intentFilter.addDataScheme("file");
        this.context.registerReceiver(new UsbReceiver(), intentFilter);
    }

    public List<MusicInfo> searchMusicInfo(List<MusicInfo> list, final MusicSearchCriteriaInfo musicSearchCriteriaInfo) {
        RLog.e(TAG, "searchMusicInfo");
        if (musicSearchCriteriaInfo.getMusicType().equals("UNSPECIFIED")) {
            return null;
        }
        return (List) list.stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LocalMediaManager$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return LocalMediaManager.lambda$searchMusicInfo$0(musicSearchCriteriaInfo, (MusicInfo) obj);
            }
        }).collect(Collectors.toList());
    }

    static /* synthetic */ boolean lambda$searchMusicInfo$0(MusicSearchCriteriaInfo musicSearchCriteriaInfo, MusicInfo musicInfo) {
        boolean zContains;
        String musicType = musicSearchCriteriaInfo.getMusicType();
        musicType.hashCode();
        switch (musicType) {
            case "SONG":
                zContains = musicSearchCriteriaInfo.getSongName().isEmpty() ? true : true & (DataUtils.stringFilter(musicInfo.getSongName()).contains(DataUtils.stringFilter(musicSearchCriteriaInfo.getSongName())) | DataUtils.stringFilter(musicInfo.getGenre()).contains(DataUtils.stringFilter(musicSearchCriteriaInfo.getSongName())));
                if (!musicSearchCriteriaInfo.getArtist().isEmpty()) {
                    zContains &= DataUtils.stringFilter(musicInfo.getArtist()).contains(DataUtils.stringFilter(musicSearchCriteriaInfo.getArtist()));
                }
                if (!musicSearchCriteriaInfo.getGenre().isEmpty()) {
                    return false;
                }
                break;
            case "ALBUM":
                if (DataUtils.stringFilter(musicInfo.getAlbum()).isEmpty()) {
                    return false;
                }
                return DataUtils.stringFilter(musicSearchCriteriaInfo.getAlbum()).contains(DataUtils.stringFilter(musicInfo.getAlbum())) | DataUtils.stringFilter(musicInfo.getAlbum()).contains(DataUtils.stringFilter(musicSearchCriteriaInfo.getAlbum()));
        }
        return zContains;
    }

    public MusicInfo searchMediaControl(MusicSearchCriteriaInfo musicSearchCriteriaInfo) {
        String str;
        MediaControl.Param param = new MediaControl.Param();
        param.setParam(MediaControl.PARAM_CUSTOM_ACTION, "com.byd.action.AUTOVOICE_SEARCH");
        param.setParam("EXTRA_ARTIST_SEARCH", "邓紫棋");
        param.setParam("EXTRA_PROGRAM_TAGS", "国语|香港");
        param.setParam("EXTRA_PROGRAM_TAGS", "国语|香港");
        param.setParam("EXTRA_KEYWORDS_SEARCH", "泡沫");
        MediaControl.controlMedia(this.context, 1, 256, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.1
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                    return;
                }
                result.getString(MediaControl.PARAM_REASON);
            }
        });
        String musicType = musicSearchCriteriaInfo.getMusicType();
        int iHashCode = musicType.hashCode();
        if (iHashCode == -1632865838) {
            str = "PLAYLIST";
        } else if (iHashCode == 2551061) {
            str = "SONG";
        } else {
            if (iHashCode != 62359119) {
                return null;
            }
            str = "ALBUM";
        }
        musicType.equals(str);
        return null;
    }

    public int playUsbMediaBySongName(final MusicInfo musicInfo, int i, final CountDownLatch countDownLatch) {
        Predicate<? super MusicInfo> predicate;
        int i2;
        try {
            RLog.e(TAG, "playUsbMediaBySongName:" + musicInfo.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.madieRet = -1;
        try {
            try {
                MediaControl.Param param = new MediaControl.Param();
                if (i == 1) {
                    predicate = new Predicate() { // from class: com.byd.vrassistant.manager.LocalMediaManager$$ExternalSyntheticLambda2
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return ((MusicInfo) obj).getSongName().equals(musicInfo.getSongName());
                        }
                    };
                } else {
                    predicate = new Predicate() { // from class: com.byd.vrassistant.manager.LocalMediaManager$$ExternalSyntheticLambda3
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return ((MusicInfo) obj).getArtist().equals(musicInfo.getArtist());
                        }
                    };
                }
                if (getSdcardMedia().stream().anyMatch(predicate)) {
                    RLog.e(TAG, "playUsbMediaBySongName SOURCE_SD");
                    i2 = 2;
                } else {
                    i2 = -1;
                }
                if (getUsbMedia().stream().anyMatch(predicate)) {
                    RLog.e(TAG, "playUsbMediaBySongName SOURCE_USB");
                    i2 = 1;
                }
                if (getLocalMedia().stream().filter(new Predicate() { // from class: com.byd.vrassistant.manager.LocalMediaManager$$ExternalSyntheticLambda4
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.f$0.m135xee0adbb1((MusicInfo) obj);
                    }
                }).filter(new Predicate() { // from class: com.byd.vrassistant.manager.LocalMediaManager$$ExternalSyntheticLambda5
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return this.f$0.m136xd8ff2833((MusicInfo) obj);
                    }
                }).anyMatch(predicate)) {
                    RLog.e(TAG, "playUsbMediaBySongName SOURCE_LOCAL");
                    i2 = 0;
                }
                param.setParam("source", i2);
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
                if (i == 1) {
                    param.setParam("fliename", musicInfo.getSongName() + ".byd");
                    param.setParam("artistname", musicInfo.getArtist());
                } else if (i == 4) {
                    param.setParam("fliename", musicInfo.getSongName() + ".byd");
                }
                MediaControl.controlMedia(this.context, 1, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.2
                    @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                    public void onControlResult(int i3, int i4, MediaControl.Result result) {
                        if (i3 == 1 && i4 == 19) {
                            if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                                RLog.e(LocalMediaManager.TAG, "playUsbMediaBySongName success");
                                LocalMediaManager.this.madieRet = 0;
                            } else {
                                RLog.e(LocalMediaManager.TAG, "playUsbMediaBySongName fail==" + result.getString(MediaControl.PARAM_REASON));
                                LocalMediaManager.this.madieRet = -1;
                            }
                            countDownLatch.countDown();
                        }
                    }
                });
                RLog.e(TAG, "playUsbMediaBySongName ret : " + this.madieRet);
                return this.madieRet;
            } catch (Exception e2) {
                this.madieRet = -1;
                RLog.e(TAG, "playUsbMediaBySongName ERROR e ==" + e2.toString());
                return this.madieRet;
            }
        } catch (Throwable unused) {
            return this.madieRet;
        }
    }

    /* JADX INFO: renamed from: lambda$playUsbMediaBySongName$4$com-byd-vrassistant-manager-LocalMediaManager, reason: not valid java name */
    /* synthetic */ boolean m135xee0adbb1(final MusicInfo musicInfo) {
        return !getUsbMedia().stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.manager.LocalMediaManager$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MusicInfo) obj).getId().equals(musicInfo.getId());
            }
        });
    }

    /* JADX INFO: renamed from: lambda$playUsbMediaBySongName$6$com-byd-vrassistant-manager-LocalMediaManager, reason: not valid java name */
    /* synthetic */ boolean m136xd8ff2833(final MusicInfo musicInfo) {
        return !getSdcardMedia().stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.manager.LocalMediaManager$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MusicInfo) obj).getId().equals(musicInfo.getId());
            }
        });
    }

    public int openLocalMedia(final CountDownLatch countDownLatch) {
        String str = TAG;
        RLog.e(str, "start openLocalMedia");
        this.madieRet = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            VoiceAssistantManager.get().setShowAfterExit(false);
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            MediaControl.controlMedia(this.context, 1, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.3
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (i == 1 && i2 == 19) {
                        if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                            RLog.e(LocalMediaManager.TAG, "openLocalMedia success");
                            LocalMediaManager.this.madieRet = 0;
                        } else {
                            String string = result.getString(MediaControl.PARAM_REASON);
                            RLog.e(LocalMediaManager.TAG, "openUsbMedia fail==" + string);
                            if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                                LocalMediaManager.this.madieRet = 2;
                                RLog.e(LocalMediaManager.TAG, "openLocalMedia not_found==");
                            } else {
                                LocalMediaManager.this.madieRet = -1;
                                RLog.e(LocalMediaManager.TAG, "openLocalMedia fail==");
                            }
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            RLog.e(str, "openLocalMedia ret : " + this.madieRet);
        } catch (Exception e) {
            this.madieRet = -1;
            RLog.e(TAG, "openLocalMedia ERROR e ==" + e.toString());
        }
        return this.madieRet;
    }

    public int openLocalMediaWithUI(CountDownLatch countDownLatch, boolean z) {
        return openLocalMediaWithUIBySource(countDownLatch, -1, z);
    }

    public int openLocalMediaWithUIEx(CountDownLatch countDownLatch, boolean z) {
        return openLocalMediaWithUIBySource(countDownLatch, 0, z);
    }

    public int openUSBMediaWithUI(CountDownLatch countDownLatch, boolean z) {
        return openLocalMediaWithUIBySource(countDownLatch, 1, z);
    }

    public int openSDCardMediaWithUI(CountDownLatch countDownLatch, boolean z) {
        return openLocalMediaWithUIBySource(countDownLatch, 2, z);
    }

    public int openLocalMediaWithUIBySource(final CountDownLatch countDownLatch, int i, boolean z) {
        String str = TAG;
        RLog.e(str, "start openLocalMediaWithUI showUI==" + z + ",source==" + i);
        this.madieRet = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (z) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            if (i != -1) {
                param.setParam("source", i);
            }
            MediaControl.controlMedia(this.context, 1, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.4
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (i2 == 1 && i3 == 19) {
                        if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                            RLog.e(LocalMediaManager.TAG, "openUsbMedia success");
                            LocalMediaManager.this.madieRet = 0;
                        } else {
                            String string = result.getString(MediaControl.PARAM_REASON);
                            RLog.e(LocalMediaManager.TAG, "openUsbMedia fail==" + string);
                            if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                                LocalMediaManager.this.madieRet = 2;
                                RLog.e(LocalMediaManager.TAG, "openUsbMedia not_found==");
                            } else if (MediaControl.REASON_UNSUPPORTED_OPERATION.equals(string)) {
                                LocalMediaManager.this.madieRet = -1;
                                RLog.e(LocalMediaManager.TAG, "openUsbMedia REASON_UNSUPPORTED_OPERATION==");
                            } else if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                                LocalMediaManager.this.madieRet = 1;
                                RLog.e(LocalMediaManager.TAG, "openUsbMedia duplicated_action==");
                            } else {
                                LocalMediaManager.this.madieRet = -1;
                                RLog.e(LocalMediaManager.TAG, "openUsbMedia fail==");
                            }
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            RLog.e(str, "openLocalMedia ret : " + this.madieRet);
        } catch (Exception e) {
            this.madieRet = -1;
            RLog.e(TAG, "openUsbMedia ERROR e ==" + e.toString());
        }
        return this.madieRet;
    }

    public int openLocalFavouriteMedia(final CountDownLatch countDownLatch) {
        String str = TAG;
        RLog.e(str, "start openLocalFavouriteMedia");
        this.madieRet = -1;
        try {
            try {
                MediaControl.Param param = new MediaControl.Param();
                if (AudioHelper.getInstance(BydVoiceApplication.getContext()).IfMusicShow().booleanValue()) {
                    VoiceAssistantManager.get().setShowAfterExit(false);
                    param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
                } else {
                    param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
                }
                int localMusicType = AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType();
                if (localMusicType == 0) {
                    param.setParam("source", 0);
                } else if (localMusicType == 1) {
                    param.setParam("source", 1);
                } else if (localMusicType == 2) {
                    param.setParam("source", 2);
                }
                MediaControl.controlMedia(this.context, 1, 25, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.5
                    @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                    public void onControlResult(int i, int i2, MediaControl.Result result) {
                        if (i == 1 && i2 == 25) {
                            if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                                RLog.e(LocalMediaManager.TAG, "openLocalFavouriteMedia success");
                                LocalMediaManager.this.madieRet = 0;
                            } else {
                                String string = result.getString(MediaControl.PARAM_REASON);
                                RLog.e(LocalMediaManager.TAG, "openLocalFavouriteMedia fail==" + string);
                                LocalMediaManager.this.madieRet = -1;
                                if (string.equals(MediaControl.REASON_DUPLICATED_ACTION)) {
                                    LocalMediaManager.this.madieRet = -2;
                                }
                            }
                            countDownLatch.countDown();
                        }
                    }
                });
                RLog.e(str, "openLocalFavouriteMedia ret : " + this.madieRet);
                return this.madieRet;
            } catch (Exception e) {
                this.madieRet = -1;
                RLog.e(TAG, "openLocalFavouriteMedia ERROR e ==" + e.toString());
                return this.madieRet;
            }
        } catch (Throwable unused) {
            return this.madieRet;
        }
    }

    public int openBlutoothMedia(final CountDownLatch countDownLatch, final int i) {
        RLog.e(TAG, "start openBlutoothMedia");
        this.madieRet = -1;
        try {
            try {
                MediaControl.Param param = new MediaControl.Param();
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
                MediaControl.controlMedia(this.context, 8, i, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.6
                    @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                    public void onControlResult(int i2, int i3, MediaControl.Result result) {
                        RLog.e(LocalMediaManager.TAG, "openBlutoothMedia mode ==" + i2);
                        RLog.e(LocalMediaManager.TAG, "openBlutoothMedia media_action ==" + i);
                        if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                            RLog.e(LocalMediaManager.TAG, "openBlutoothMedia success");
                            LocalMediaManager.this.madieRet = 0;
                        } else {
                            RLog.e(LocalMediaManager.TAG, "openBlutoothMedia fail==" + result.getString(MediaControl.PARAM_REASON));
                            LocalMediaManager.this.madieRet = -1;
                        }
                        countDownLatch.countDown();
                    }
                });
                return this.madieRet;
            } catch (Exception e) {
                this.madieRet = -1;
                RLog.e(TAG, "openBlutoothMedia ERROR e ==" + e.toString());
                return this.madieRet;
            }
        } catch (Throwable unused) {
            return this.madieRet;
        }
    }

    public int openBlutoothMediaWithUI(final CountDownLatch countDownLatch, final int i, boolean z) {
        RLog.e(TAG, "start openBlutoothMediaWithUI");
        this.madieRet = -1;
        try {
            try {
                MediaControl.Param param = new MediaControl.Param();
                if (z) {
                    VoiceAssistantManager.get().setShowAfterExit(false);
                    param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
                } else {
                    param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
                }
                MediaControl.controlMedia(this.context, 8, i, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.7
                    @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                    public void onControlResult(int i2, int i3, MediaControl.Result result) {
                        RLog.e(LocalMediaManager.TAG, "openBlutoothMediaWithUI mode ==" + i2);
                        RLog.e(LocalMediaManager.TAG, "openBlutoothMediaWithUI media_action ==" + i);
                        if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                            RLog.e(LocalMediaManager.TAG, "openBlutoothMediaWithUI success");
                            LocalMediaManager.this.madieRet = 0;
                        } else {
                            RLog.e(LocalMediaManager.TAG, "openBlutoothMediaWithUI fail==" + result.getString(MediaControl.PARAM_REASON));
                            LocalMediaManager.this.madieRet = -1;
                        }
                        countDownLatch.countDown();
                    }
                });
                return this.madieRet;
            } catch (Exception e) {
                this.madieRet = -1;
                RLog.e(TAG, "openBlutoothMediaWithUI ERROR e ==" + e.toString());
                return this.madieRet;
            }
        } catch (Throwable unused) {
            return this.madieRet;
        }
    }

    public int openLocalVideo(final CountDownLatch countDownLatch) {
        String str = TAG;
        RLog.e(str, "start openLocalVideo");
        this.madieRet = -1;
        try {
            try {
                MediaControl.Param param = new MediaControl.Param();
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
                MediaControl.controlMedia(this.context, 2, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.manager.LocalMediaManager.8
                    @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                    public void onControlResult(int i, int i2, MediaControl.Result result) {
                        if (i == 2 && i2 == 19) {
                            if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                                RLog.e(LocalMediaManager.TAG, "openLocalVideo success");
                                LocalMediaManager.this.madieRet = 0;
                            } else {
                                RLog.e(LocalMediaManager.TAG, "openLocalVideo fail==" + result.getString(MediaControl.PARAM_REASON));
                                LocalMediaManager.this.madieRet = -1;
                            }
                        }
                        countDownLatch.countDown();
                    }
                });
                RLog.e(str, "openLocalVideo ret : " + this.madieRet);
                return this.madieRet;
            } catch (Exception e) {
                this.madieRet = -1;
                RLog.e(TAG, "openLocalVideo ERROR e ==" + e.toString());
                return this.madieRet;
            }
        } catch (Throwable unused) {
            return this.madieRet;
        }
    }

    public List<MusicInfo> getLocalMedia() {
        if (this.localMusicList.size() <= 0) {
            searchLocalMedia();
        }
        RLog.e(TAG, "localMusicList size=" + this.localMusicList.size());
        return this.localMusicList;
    }

    public List<MusicInfo> getUsbMedia() {
        if (this.usbMusicList.size() <= 0) {
            searchUsbMedia();
        }
        RLog.e(TAG, "getUsbMedia size=" + this.usbMusicList.size());
        return this.usbMusicList;
    }

    public List<MusicInfo> getSdcardMedia() {
        if (this.sdcardMusicList.size() <= 0) {
            searchSdcardMedia();
        }
        RLog.e(TAG, "getSdcardMedia size=" + this.sdcardMusicList.size());
        return this.sdcardMusicList;
    }

    public synchronized List<MusicInfo> searchUsbMedia() {
        this.usbMusicList.clear();
        String uuid = "";
        List<StorageVolume> storageVolumes = ((StorageManager) this.context.getSystemService("storage")).getStorageVolumes();
        RLog.e(TAG, "searchUsbMedia mUsbotgUuid==" + SystemPropertiesUtils.get("sys.byd.mUsbotgUuid", "defaultvalue"));
        Iterator<StorageVolume> it = storageVolumes.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            StorageVolume next = it.next();
            if (next.getUuid() != null && next.getUuid().equals(SystemPropertiesUtils.get("sys.byd.mUsbotgUuid", "defaultvalue"))) {
                uuid = next.getUuid();
                break;
            }
        }
        RLog.e(TAG, "searchUsbMedia UsbUUID==" + uuid);
        if (!uuid.equals("")) {
            this.usbMusicList.addAll(searchMediaByPath(MediaStore.Audio.Media.getContentUri(uuid.toLowerCase())));
        }
        return this.usbMusicList;
    }

    public synchronized List<MusicInfo> searchSdcardMedia() {
        this.sdcardMusicList.clear();
        String uuid = "";
        List<StorageVolume> storageVolumes = ((StorageManager) this.context.getSystemService("storage")).getStorageVolumes();
        RLog.e(TAG, "searchSdcardMedia mSdcardUuid==" + SystemPropertiesUtils.get("sys.byd.mSdcardUuid", "defaultvalue"));
        Iterator<StorageVolume> it = storageVolumes.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            StorageVolume next = it.next();
            if (next.getUuid() != null && next.getUuid().equals(SystemPropertiesUtils.get("sys.byd.mSdcardUuid", "defaultvalue"))) {
                uuid = next.getUuid();
                break;
            }
        }
        RLog.e(TAG, "searchSdcardMedia SDUUID==" + uuid);
        if (!uuid.equals("")) {
            this.sdcardMusicList.addAll(searchMediaByPath(MediaStore.Audio.Media.getContentUri(uuid.toLowerCase())));
        }
        return this.sdcardMusicList;
    }

    public synchronized List<MusicInfo> searchLocalMedia() {
        this.localMusicList.clear();
        this.localMusicList.addAll(searchMediaByPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI));
        return this.localMusicList;
    }

    public static String removeExtension(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf > -1 ? str.substring(0, iLastIndexOf) : str;
    }

    public synchronized List<MusicInfo> searchMediaByPath(Uri uri) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            try {
                Cursor cursorQuery = this.context.getContentResolver().query(uri, null, null, null, "_display_name", null);
                if (cursorQuery != null) {
                    RLog.i(TAG, "searchMediaByPath cursor!=null start uripath:" + uri);
                    while (cursorQuery.moveToNext()) {
                        MusicInfo musicInfo = new MusicInfo();
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                        long j = cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_id"));
                        String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist"));
                        cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                        cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("duration"));
                        cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_size"));
                        cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("album_id"));
                        String string3 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("album"));
                        String string4 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_display_name"));
                        musicInfo.setId(j + "");
                        musicInfo.setSongName(removeExtension(string));
                        musicInfo.setGenre(string4);
                        if (TextUtils.isEmpty(string3) || string3.equals("<unknown>")) {
                            musicInfo.setAlbum("");
                        } else {
                            musicInfo.setAlbum(string3);
                        }
                        if (TextUtils.isEmpty(string2) || string2.equals("<unknown>")) {
                            musicInfo.setArtist("");
                        } else {
                            musicInfo.setArtist(string2);
                        }
                        arrayList.add(musicInfo);
                    }
                    cursorQuery.close();
                } else {
                    RLog.i(TAG, "searchMediaByPath cursor=null");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                RLog.i(TAG, e.getMessage());
                return arrayList;
            }
        } catch (Throwable unused) {
            return arrayList;
        }
        return arrayList;
    }

    public String getCurrentAudioPackage() {
        return this.mCurrentAudioPackage;
    }

    public void saveCurrentAudioPackage() {
        List<AudioFocusInfo> curAudioFocusInfo = BYDAutoAudioDevice.getInstance(BydVoiceApplication.getContext()).getCurAudioFocusInfo(0);
        if (curAudioFocusInfo != null) {
            RLog.i(TAG, "getCurrentAudioPackage audioFocus size=" + curAudioFocusInfo.size());
            if (curAudioFocusInfo.size() <= 0) {
                this.mCurrentAudioPackage = "";
            }
            for (int i = 0; i < curAudioFocusInfo.size(); i++) {
                this.mCurrentAudioPackage = curAudioFocusInfo.get(i).getPackageName();
                RLog.i(TAG, "getCurrentAudioPackage packageName=" + curAudioFocusInfo.get(i).getPackageName());
            }
            return;
        }
        this.mCurrentAudioPackage = "";
        RLog.i(TAG, "getCurrentAudioPackage packageName=null");
    }

    public void setSavedStreamType(int i) {
        this.savedStreamType = BYDAutoAudioDevice.getInstance(BydVoiceApplication.getContext()).getVolumeGroupIdForUsage(18);
        RLog.i(TAG, "getSavedStreamType =" + this.savedStreamType);
    }
}
