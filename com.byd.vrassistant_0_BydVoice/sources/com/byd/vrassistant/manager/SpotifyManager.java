package com.byd.vrassistant.manager;

import android.content.Context;
import android.util.Log;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.eventbus.AudioEvent;
import com.byd.vrassistant.utils.RLog;
import com.vivid.sdk.music.RequestCallBack;
import com.vivid.sdk.music.ResponseCode;
import com.vivid.sdk.spotify.PlayState;
import com.vivid.sdk.spotify.RepeatMode;
import com.vivid.sdk.spotify.ShuffleMode;
import com.vivid.sdk.spotify.SpotifyMediaManager;
import com.vivid.spotify.SearchParam;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class SpotifyManager {
    public static final String SPOTIFY_APK_PACKAGE_NAME = "com.telenav.scoutivi.spotify.byd";
    private static final String TAG = "NEU_SpotifyManager";
    private static volatile SpotifyManager mInstance;
    private int spotifyPlayState;
    public static ResponseCode mResponseCode = ResponseCode.UNKNOW_ERROR;
    public static ResponseCode mResponseRepeatModeCode = ResponseCode.UNKNOW_ERROR;
    public static final int STATE_PLAYING = PlayState.STATE_PLAYING.getCode();
    public static final int STATE_PAUSED = PlayState.STATE_PAUSED.getCode();
    public static final int STATE_STOPPED = PlayState.STATE_STOPPED.getCode();
    private Context context = BydVoiceApplication.getContext();
    private boolean isSpotifyRunning = false;
    private boolean isSpotifyRunningSaved = false;
    public int spotifyCountDown = 0;
    private boolean isConnected = false;

    public int getSpotifyPlayState() {
        return this.spotifyPlayState;
    }

    public static ResponseCode getResponseCode() {
        Log.d(TAG, "spotify getResponseCode==" + mResponseCode);
        return mResponseCode;
    }

    public static ResponseCode getResponseRepeatModeCode() {
        return mResponseRepeatModeCode;
    }

    public static SpotifyManager get() {
        if (mInstance == null) {
            synchronized (SpotifyManager.class) {
                if (mInstance == null) {
                    mInstance = new SpotifyManager();
                }
            }
        }
        return mInstance;
    }

    private SpotifyManager() {
    }

    public boolean isSpotifyRunning() {
        if (!this.isSpotifyRunningSaved) {
            saveCurrentRunningState();
        }
        this.isSpotifyRunningSaved = false;
        return this.isSpotifyRunning;
    }

    public boolean isConnected() {
        RLog.e(TAG, "SpotifyMediaManager isConnected:" + this.isConnected);
        return this.isConnected;
    }

    public void isLogin(final CountDownLatch countDownLatch) {
        RLog.e(TAG, "SpotifyMediaManager isLogin cur response:" + mResponseCode.name());
        SpotifyMediaManager.getInstance().isLogin(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.1
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RLog.e(SpotifyManager.TAG, "isLogin onSuccess:");
                SpotifyManager.mResponseCode = ResponseCode.OK;
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                RLog.e(SpotifyManager.TAG, "isLogin onError:" + responseCode.name());
                SpotifyManager.mResponseCode = responseCode;
                countDownLatch.countDown();
            }
        });
    }

    public synchronized void init(final CountDownLatch countDownLatch) {
        RLog.e(TAG, "spotify init:");
        SpotifyMediaManager.getInstance().init(this.context, new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.2
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RLog.e(SpotifyManager.TAG, "SpotifyMediaManager onSuccess");
                SpotifyManager.this.spotifyCountDown = 0;
                SpotifyManager.this.isConnected = true;
                SpotifyManager.mResponseCode = ResponseCode.OK;
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                RLog.e(SpotifyManager.TAG, "SpotifyMediaManager onError:" + responseCode.name());
                SpotifyManager.this.isConnected = false;
                countDownLatch.countDown();
                SpotifyManager.mResponseCode = responseCode;
                SpotifyManager.this.spotifyCountDown++;
                EventBus.getDefault().postSticky(new AudioEvent("", 2));
            }
        });
    }

    public void uninit() {
        this.isConnected = false;
        SpotifyMediaManager.getInstance().destroy();
    }

    public void openSpotify() {
        RLog.e(TAG, "spotify openSpotify:");
        MediaControl.Param param = new MediaControl.Param();
        VoiceAssistantManager.get().setShowAfterExit(false);
        param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
        MediaControl.controlMedia(this.context, 103, 0, param, null);
    }

    public boolean saveCurrentRunningState() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(LocalMediaManager.SPOTIFY_PACKAGE_NAME);
        arrayList.add(SPOTIFY_APK_PACKAGE_NAME);
        int state = get().getState();
        if (BydVoiceApplication.isApplicationForeground(arrayList) || state == STATE_PLAYING) {
            this.isSpotifyRunning = true;
        } else {
            this.isSpotifyRunning = false;
        }
        String str = TAG;
        RLog.e(str, "Spotify isSpotifyRunningSaved : " + this.isSpotifyRunningSaved);
        this.isSpotifyRunningSaved = true;
        RLog.e(str, "Spotify saveCurrentRunningState:" + this.isSpotifyRunning);
        return this.isSpotifyRunning;
    }

    public void saveCurrentPlayingState() {
        int code = SpotifyMediaManager.getInstance().getPlayState().getCode();
        String str = TAG;
        RLog.e(str, "saveCurrentPlayingState:" + code);
        if (code != PlayState.STATE_PLAYING.getCode() && code != PlayState.STATE_STOPPED.getCode()) {
            PlayState.STATE_PAUSED.getCode();
        }
        this.spotifyPlayState = code;
        RLog.i(str, "saveCurrentPlayingState spotify mediaState: " + this.spotifyPlayState);
    }

    public void play() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().play(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.3
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RLog.e(SpotifyManager.TAG, "Spotify play onSuccess:" + SpotifyMediaManager.getInstance().getPlayState().name());
                SpotifyManager.mResponseCode = ResponseCode.OK;
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                RLog.e(SpotifyManager.TAG, "Spotify play onError:" + responseCode.name());
                SpotifyManager.mResponseCode = responseCode;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().stop(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.4
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                RLog.e(SpotifyManager.TAG, "Spotify stop onSuccess:" + SpotifyMediaManager.getInstance().getPlayState().name());
                SpotifyManager.mResponseCode = ResponseCode.OK;
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                countDownLatch.countDown();
                RLog.e(SpotifyManager.TAG, "Spotify stop onError:" + responseCode.name());
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void restart() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().stop(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.5
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                Log.e(SpotifyManager.TAG, "Spotify stop onSuccess:" + SpotifyMediaManager.getInstance().getPlayState().name());
                SpotifyManager.this.resume();
                SpotifyManager.mResponseCode = ResponseCode.OK;
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                Log.e(SpotifyManager.TAG, "Spotify stop onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().pause(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.6
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify pause onSuccess:" + SpotifyMediaManager.getInstance().getPlayState().name());
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify pause onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().resume(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.7
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify resume onSuccess:" + SpotifyMediaManager.getInstance().getPlayState().name());
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify resume onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void replay() {
        Log.d(TAG, "Spotify replay start");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().replay(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.8
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                Log.e(SpotifyManager.TAG, "Spotify replay onSuccess:" + SpotifyMediaManager.getInstance().getPlayState().name());
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                Log.e(SpotifyManager.TAG, "Spotify replay onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getState() {
        int code = SpotifyMediaManager.getInstance().getPlayState().getCode();
        RLog.e(TAG, "Spotify getState:" + code);
        if (code != PlayState.STATE_PLAYING.getCode() && code != PlayState.STATE_STOPPED.getCode()) {
            PlayState.STATE_PAUSED.getCode();
        }
        return code;
    }

    public boolean addFavorite() {
        final boolean[] zArr = {false};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().addFavorite(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.9
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify addFavorite onSuccess:");
                zArr[0] = true;
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify addFavorite onError:" + responseCode.name());
                zArr[0] = false;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zArr[0];
    }

    public boolean removeFavorite() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = {false};
        SpotifyMediaManager.getInstance().removeFavorite(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.10
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify removeFavorite onSuccess:");
                zArr[0] = true;
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify removeFavorite onError:" + responseCode.name());
                zArr[0] = false;
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zArr[0];
    }

    public void setRepeatMode(RepeatMode repeatMode) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().setRepeatMode(repeatMode, new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.11
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                SpotifyManager.mResponseRepeatModeCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify setRepeatMode onSuccess:");
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                SpotifyManager.mResponseRepeatModeCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify setRepeatMode onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setShuffleMode(ShuffleMode shuffleMode) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().setShuffleMode(shuffleMode, new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.12
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify setShuffleMode onSuccess:");
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify setShuffleMode onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void skipToNext() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().skipToNext(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.13
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify skipToNext onSuccess:");
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify skipToNext onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void skipToPrevious() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().skipToPrevious(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.14
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify skipToPrevious onSuccess:");
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify skipToPrevious onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openPlayListPage() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().openPlayList(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.15
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify openPlayList onSuccess:");
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify openPlayList onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void closePlayListPage() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().closePlayList(new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.16
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                RLog.e(SpotifyManager.TAG, "Spotify closePlayList onSuccess:");
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                RLog.e(SpotifyManager.TAG, "Spotify closePlayList onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void playForSearch(String str, String str2, String str3, String str4) {
        SearchParam searchParamBuild;
        String str5 = str + " " + str2 + " " + str3 + " " + str4;
        RLog.d(TAG, "Spotify playForSearch query:" + str5);
        if (str != null && !str.isEmpty()) {
            SearchParam.Builder builderBuildSearch = SearchParam.Builder.buildSearch(str);
            if (str2 != null && !str2.isEmpty()) {
                builderBuildSearch.setArtist(str2);
            }
            if (str3 != null && !str3.isEmpty()) {
                builderBuildSearch.setAlbum(str3);
            }
            if (str4 != null && !str4.isEmpty()) {
                builderBuildSearch.setGenre(str4);
            }
            searchParamBuild = builderBuildSearch.build();
        } else if (str2 != null && !str2.isEmpty()) {
            SearchParam.Builder builderBuildArtistSearch = SearchParam.Builder.buildArtistSearch(str2);
            if (str3 != null && !str3.isEmpty()) {
                builderBuildArtistSearch.setAlbum(str3);
            }
            if (str4 != null && !str4.isEmpty()) {
                builderBuildArtistSearch.setGenre(str4);
            }
            searchParamBuild = builderBuildArtistSearch.build();
        } else if (str3 != null && !str3.isEmpty()) {
            SearchParam.Builder builderBuildAlbumSearch = SearchParam.Builder.buildAlbumSearch(str3);
            if (str4 != null && !str4.isEmpty()) {
                builderBuildAlbumSearch.setGenre(str4);
            }
            searchParamBuild = builderBuildAlbumSearch.build();
        } else if (str4 != null && !str4.isEmpty()) {
            searchParamBuild = SearchParam.Builder.buildGenreSearch(str4).build();
        } else {
            searchParamBuild = SearchParam.Builder.buildEmptyParam().build();
        }
        mResponseCode = ResponseCode.UNKNOW_ERROR;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyMediaManager.getInstance().playFromSearch(str5, searchParamBuild, new RequestCallBack() { // from class: com.byd.vrassistant.manager.SpotifyManager.17
            @Override // com.vivid.sdk.music.RequestCallBack
            public void onSuccess() {
                SpotifyManager.mResponseCode = ResponseCode.OK;
                Log.e(SpotifyManager.TAG, "Spotify playForSearch onSuccess:");
                countDownLatch.countDown();
            }

            @Override // com.vivid.sdk.music.RequestCallBack
            public void onError(ResponseCode responseCode) {
                SpotifyManager.mResponseCode = responseCode;
                Log.e(SpotifyManager.TAG, "Spotify playForSearch onError:" + responseCode.name());
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(15L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
