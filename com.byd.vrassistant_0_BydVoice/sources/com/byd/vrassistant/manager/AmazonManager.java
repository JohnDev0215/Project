package com.byd.vrassistant.manager;

import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.media.result.SearchMusicResult;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.struct.MusicInfo;
import cerence.ark.assistant.controller.struct.MusicSearchCriteriaInfo;
import com.byd.amazon.music.IMusicItemsCallBack;
import com.byd.amazon.music.IPlayStateCallBack;
import com.byd.amazon.music.IServiceConnectStateCallBack;
import com.byd.amazon.music.VoiceAidlControl;
import com.byd.amazon.music.bean.MusicItem;
import com.byd.amazon.music.bean.MusicItemResult;
import com.byd.amazon.music.bean.Result;
import com.byd.amazon.music.param.PlayMode;
import com.byd.amazon.music.param.PlayState;
import com.byd.amazon.music.param.VodType;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.customizedimpl.CustomSystemDomain;
import com.byd.vrassistant.eventbus.AudioEvent;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class AmazonManager {
    private static final String TAG = "NEU_AmazonManager";
    private static final int TimeOut = 4;
    private static int mErrorCode = -1;
    private static volatile AmazonManager mInstance;
    public static int mResponseCode;
    private PlayState amazonPlayState;
    public static final PlayState STATE_PLAYING = PlayState.STATE_PLAYING;
    public static final PlayState STATE_PAUSED = PlayState.STATE_PAUSED;
    public static final PlayState STATE_STOPPED = PlayState.STATE_STOPPED;
    private CountDownLatch latch = null;
    private boolean isAmazonRunning = false;
    private boolean isAmazonRunningSaved = false;
    public int amazonCountDown = 0;
    private boolean isConnected = false;
    private List<MusicInfo> lstRet = new ArrayList();
    private boolean blSearchSuccess = false;

    public PlayState getAmazonPlayState() {
        return this.amazonPlayState;
    }

    public static int getResponseCode() {
        return mResponseCode;
    }

    public static int getErrorCode() {
        return mErrorCode;
    }

    public static AmazonManager get() {
        if (mInstance == null) {
            synchronized (AmazonManager.class) {
                if (mInstance == null) {
                    mInstance = new AmazonManager();
                }
            }
        }
        return mInstance;
    }

    private AmazonManager() {
    }

    public boolean isConnected() {
        Log.e(TAG, "AmazonManager isConnected:" + this.isConnected);
        return this.isConnected;
    }

    public boolean isLogin() {
        RLog.e(TAG, "AmazonManager isLogin");
        return VoiceAidlControl.getInstance().isLogIn().getBoolean();
    }

    public boolean isAmazonRunning() {
        if (!this.isAmazonRunningSaved) {
            saveCurrentRunningState();
        }
        this.isAmazonRunningSaved = false;
        return this.isAmazonRunning;
    }

    public synchronized void init(final CountDownLatch countDownLatch) {
        Log.i(TAG, "AmazonManager init");
        VoiceAidlControl.getInstance().init(BydVoiceApplication.getContext(), new IServiceConnectStateCallBack() { // from class: com.byd.vrassistant.manager.AmazonManager.1
            @Override // com.byd.amazon.music.IServiceConnectStateCallBack
            public void onConnect() {
                RLog.e(AmazonManager.TAG, "AmazonManager onConnect onSuccess");
                AmazonManager.this.amazonCountDown = 0;
                AmazonManager.this.isConnected = true;
                CountDownLatch countDownLatch2 = countDownLatch;
                if (countDownLatch2 != null) {
                    countDownLatch2.countDown();
                }
            }

            @Override // com.byd.amazon.music.IServiceConnectStateCallBack
            public void disConnect() {
                RLog.e(AmazonManager.TAG, "AmazonManager disConnect");
                AmazonManager.this.isConnected = false;
                CountDownLatch countDownLatch2 = countDownLatch;
                if (countDownLatch2 != null) {
                    countDownLatch2.countDown();
                }
                AmazonManager.this.amazonCountDown++;
                EventBus.getDefault().postSticky(new AudioEvent("", 5));
            }
        });
    }

    public void openAmazon() {
        Log.e(TAG, "AmazonManager openAmazon:");
        SystemSettingManager.get().openAppByPackage("com.android.car.media");
    }

    public PlayState getState() {
        PlayState state = VoiceAidlControl.getInstance().getPlayState().getState();
        Log.e(TAG, "amazon getState:" + state);
        return state;
    }

    public Result addFavorite() {
        Result favorite = VoiceAidlControl.getInstance().setFavorite();
        Log.e(TAG, "AmazonManager addFavorite ret: " + favorite);
        return favorite;
    }

    public Result removeFavorite() {
        Result resultUnSetFavorite = VoiceAidlControl.getInstance().unSetFavorite();
        Log.e(TAG, "AmazonManager removeFavorite ret: " + resultUnSetFavorite);
        return resultUnSetFavorite;
    }

    public Result play() {
        Result resultPlay = VoiceAidlControl.getInstance().play();
        Log.e(TAG, "AmazonManager play ret: " + resultPlay);
        return resultPlay;
    }

    public Result playById(String str) {
        mResponseCode = -1;
        RLog.i(TAG, "playById  mediaId: " + str);
        return VoiceAidlControl.getInstance().playById(str, new IPlayStateCallBack.Stub() { // from class: com.byd.vrassistant.manager.AmazonManager.2
            @Override // com.byd.amazon.music.IPlayStateCallBack
            public void onState(PlayState playState) throws RemoteException {
                RLog.e(AmazonManager.TAG, "AmazonManager PlayState :" + playState);
                AmazonManager.this.amazonPlayState = playState;
                if (PlayState.STATE_ERROR == playState) {
                    AmazonManager.mResponseCode = -1;
                } else {
                    AmazonManager.mResponseCode = 0;
                }
                if (AmazonManager.this.latch == null || AmazonManager.this.latch.getCount() <= 0) {
                    return;
                }
                AmazonManager.this.latch.countDown();
            }
        });
    }

    public void startWait() {
        RLog.i(TAG, "enter startWait: ");
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.latch = countDownLatch;
            countDownLatch.await(4L, TimeUnit.SECONDS);
            if (this.latch.getCount() > 0) {
                this.latch.countDown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MusicItemResult getCurMusicInfo() {
        RLog.i(TAG, "AmazonManager getCurMusicInfo");
        return VoiceAidlControl.getInstance().getNowPlayingSong();
    }

    public Result pause() {
        RLog.i(TAG, "AmazonManager pause");
        return VoiceAidlControl.getInstance().pause();
    }

    public Result stop() {
        RLog.i(TAG, "AmazonManager stop");
        return VoiceAidlControl.getInstance().stop();
    }

    public Result resume() {
        Result resultPlay = VoiceAidlControl.getInstance().play();
        Log.e(TAG, "AmazonManager resume ret: " + resultPlay);
        return resultPlay;
    }

    public Result replay() {
        Result resultReplay = VoiceAidlControl.getInstance().replay();
        Log.e(TAG, "AmazonManager replay ret: " + resultReplay);
        return resultReplay;
    }

    public Result skipToNext() {
        Result resultPlayNext = VoiceAidlControl.getInstance().playNext();
        Log.e(TAG, "AmazonManager skipToNext ret: " + resultPlayNext);
        return resultPlayNext;
    }

    public Result skipToPrevious() {
        Result resultPlayPre = VoiceAidlControl.getInstance().playPre();
        Log.e(TAG, "AmazonManager skipToPrevious ret: " + resultPlayPre);
        return resultPlayPre;
    }

    public Result exitMusic() {
        RLog.i(TAG, "AmazonManager exitMusic");
        return VoiceAidlControl.getInstance().exitMusic();
    }

    public Result openPlayListPage() {
        RLog.i(TAG, "AmazonManager openPlayListPage");
        return VoiceAidlControl.getInstance().openPlayListPage();
    }

    public Result closePlayListPage() {
        RLog.i(TAG, "AmazonManager closePlayListPage");
        return VoiceAidlControl.getInstance().closePlayListPage();
    }

    public Result playByFavorite() {
        RLog.i(TAG, "AmazonManager playByFavorite");
        return VoiceAidlControl.getInstance().playByFavorite();
    }

    public boolean saveCurrentRunningState() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.android.car.media");
        arrayList.add(LocalMediaManager.AMAZON_PACKAGE_NAME);
        PlayState state = getState();
        if (BydVoiceApplication.isApplicationForeground(arrayList) || state == STATE_PLAYING) {
            this.isAmazonRunning = true;
        } else {
            this.isAmazonRunning = false;
        }
        String str = TAG;
        Log.e(str, "Amazon isAmazonRunningSaved : " + this.isAmazonRunningSaved);
        this.isAmazonRunningSaved = true;
        Log.e(str, "Amazon saveCurrentRunningState : " + this.isAmazonRunning);
        return this.isAmazonRunning;
    }

    public void saveCurrentPlayingState() {
        PlayState state = VoiceAidlControl.getInstance().getPlayState().getState();
        String str = TAG;
        Log.e(str, "saveCurrentPlayingState:" + state);
        this.amazonPlayState = state;
        Log.i(str, "saveCurrentPlayingState Amazon mediaState: " + this.amazonPlayState);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void searchMusicInfo(final MusicSearchCriteriaInfo musicSearchCriteriaInfo, final SearchMusicResult searchMusicResult, final IResponseSender<SearchMusicResult> iResponseSender, final CustomSystemDomain.TestLogCat testLogCat) {
        String str = TAG;
        Log.i(str, "searchAmazonMusicInfo");
        if (!musicSearchCriteriaInfo.getMusicType().equals("UNSPECIFIED")) {
            VodType vodType = VodType.SONG_NAME;
            String musicType = musicSearchCriteriaInfo.getMusicType();
            musicType.hashCode();
            byte b = -1;
            switch (musicType.hashCode()) {
                case -1632865838:
                    if (musicType.equals("PLAYLIST")) {
                        b = 0;
                    }
                    break;
                case 2551061:
                    if (musicType.equals("SONG")) {
                        b = 1;
                    }
                    break;
                case 62359119:
                    if (musicType.equals("ALBUM")) {
                        b = 2;
                    }
                    break;
            }
            String artist = "";
            switch (b) {
                case 0:
                    MusicInfo musicInfo = new MusicInfo();
                    musicInfo.setId("1");
                    musicInfo.setAlbum("1");
                    musicInfo.setSongName("1");
                    musicInfo.setArtist("1");
                    List<MusicInfo> list = this.lstRet;
                    if (list == null) {
                        this.lstRet = new ArrayList();
                    } else {
                        list.clear();
                    }
                    this.lstRet.add(musicInfo);
                    List<MusicInfo> list2 = this.lstRet;
                    if (list2 != null && list2.size() > 0) {
                        Log.e(str, "searchAmazonMusic PLAYLIST size:" + this.lstRet.size());
                        searchMusicResult.setMusicList(this.lstRet);
                        searchMusicResult.setMusicType(musicSearchCriteriaInfo.getMusicType());
                        if (this.lstRet.size() > 1) {
                            searchMusicResult.setNeedDisambig(true);
                        }
                        searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                        testLogCat.setResponse("OK");
                        iResponseSender.send(new Response("OK", searchMusicResult));
                        return;
                    }
                    Log.e(str, "searchAmazonMusic PLAYLIST size:0");
                    iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                    testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                    return;
                case 1:
                    vodType = VodType.SONG_NAME;
                    if (!musicSearchCriteriaInfo.getSongName().isEmpty()) {
                        artist = musicSearchCriteriaInfo.getSongName();
                        vodType = VodType.SONG_NAME;
                    }
                    if (!musicSearchCriteriaInfo.getArtist().isEmpty()) {
                        artist = musicSearchCriteriaInfo.getArtist();
                        vodType = VodType.SINGER;
                    }
                    break;
                case 2:
                    vodType = VodType.ALBUM;
                    if (!musicSearchCriteriaInfo.getAlbum().isEmpty()) {
                        artist = musicSearchCriteriaInfo.getAlbum();
                    }
                    break;
            }
            Log.i(str, "searchAmazonMusic searchText: " + artist);
            if (!TextUtils.isEmpty(artist)) {
                try {
                    VoiceAidlControl.getInstance().playByVod(artist, vodType, new IMusicItemsCallBack.Stub() { // from class: com.byd.vrassistant.manager.AmazonManager.3
                        @Override // com.byd.amazon.music.IMusicItemsCallBack
                        public void onResult(String str2, List<MusicItem> list3) throws RemoteException {
                            Log.i(AmazonManager.TAG, "AmazonManager search------------- ");
                            if (AmazonManager.this.lstRet != null) {
                                AmazonManager.this.lstRet.clear();
                            } else {
                                AmazonManager.this.lstRet = new ArrayList();
                            }
                            if (list3 != null && list3.size() > 0) {
                                Log.i(AmazonManager.TAG, "AmazonManager search size: " + list3.size());
                                for (MusicItem musicItem : list3) {
                                    if (musicItem != null) {
                                        MusicInfo musicInfo2 = new MusicInfo();
                                        musicInfo2.setId(musicItem.getMediaId());
                                        musicInfo2.setAlbum(musicItem.getAlum());
                                        musicInfo2.setSongName(musicItem.getTitle());
                                        musicInfo2.setArtist(musicItem.getSinger());
                                        musicInfo2.setGenre(musicItem.getSongClass());
                                        AmazonManager.this.lstRet.add(musicInfo2);
                                        Log.i(AmazonManager.TAG, "AmazonManager result:  mediaId = " + musicItem.getMediaId());
                                    }
                                }
                            }
                            if (AmazonManager.this.lstRet == null || AmazonManager.this.lstRet.size() <= 0) {
                                Log.e(AmazonManager.TAG, "searchAmazonMusic size:0");
                                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                                testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                                return;
                            }
                            Log.e(AmazonManager.TAG, "searchAmazonMusic size:" + AmazonManager.this.lstRet.size());
                            searchMusicResult.setMusicList(AmazonManager.this.lstRet);
                            searchMusicResult.setMusicType(musicSearchCriteriaInfo.getMusicType());
                            if (AmazonManager.this.lstRet.size() > 1) {
                                searchMusicResult.setNeedDisambig(true);
                            }
                            searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                            testLogCat.setResponse("OK");
                            iResponseSender.send(new Response("OK", searchMusicResult));
                        }

                        @Override // com.byd.amazon.music.IMusicItemsCallBack
                        public void onError(String str2, String str3) throws RemoteException {
                            Log.i(AmazonManager.TAG, "searchAmazonMusic search error,query=" + str2 + ",   errorMessage: " + str3);
                            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                            testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                        }
                    });
                    return;
                } catch (Exception unused) {
                    Log.e(TAG, "searchAmazonMusic Exception");
                    return;
                }
            } else {
                Log.e(str, "searchAmazonMusic MEDIA_NOT_FOUND");
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                return;
            }
        }
        Log.e(str, "searchAmazonMusic MEDIA_NOT_FOUND");
        iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
        testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
    }

    public Result setPlayMode(PlayMode playMode) {
        RLog.i(TAG, "AmazonManager setPlayMode");
        return VoiceAidlControl.getInstance().setPlayMode(playMode);
    }

    public PlayMode getCurPlayMode() {
        RLog.i(TAG, "AmazonManager getCurPlayMode");
        return VoiceAidlControl.getInstance().getCurPlayMode().getPlayMode();
    }

    public boolean isPlayListPageOpen() {
        RLog.i(TAG, "AmazonManager isPlayListPageOpen");
        return VoiceAidlControl.getInstance().isPlayListPageOpen().getBoolean();
    }

    public void uninit() {
        this.isConnected = false;
        VoiceAidlControl.getInstance().unInit();
    }
}
