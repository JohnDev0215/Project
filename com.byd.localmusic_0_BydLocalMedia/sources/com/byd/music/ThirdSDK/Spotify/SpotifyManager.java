package com.byd.music.ThirdSDK.Spotify;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.blankj.utilcode.util.Utils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.OnLineMusic;
import com.byd.music.ThirdSDK.Spotify.SpotifyManager;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.StringSubUtil;
import com.telenav.scoutivi.spotify.sdk.byd.dx.SpotifyBYDDXMediaManager;
import com.vivid.sdk.music.RequestCallBack;
import com.vivid.sdk.music.ResponseCode;
import com.vivid.sdk.spotify.MediaControllerCallBack;
import com.vivid.sdk.spotify.Metadata;
import com.vivid.sdk.spotify.RepeatMode;
import com.vivid.sdk.spotify.ShuffleMode;

/* JADX INFO: loaded from: classes5.dex */
public class SpotifyManager {
    public static final String SpotifyAudioFocusPKG = "com.spotify.music";
    public static final String SpotifyPKG = "com.telenav.scoutivi.spotify.byd";
    public static final String SpotifySERVICE = "com.vivid.carcontroller.byd.dx.RestoreStateService";
    public static final String StopPlayButPlayMemoryEnable = "com.spotify.music_StopPlayButPlayMemoryEnable";
    private static final String TAG = "SpotifyManager";
    public static volatile boolean TAG_SPOTIFY_ONACTIVE = false;
    private static boolean isInit;
    public static int playState;

    /* JADX INFO: renamed from: com.byd.music.ThirdSDK.Spotify.SpotifyManager$1, reason: invalid class name */
    class AnonymousClass1 implements RequestCallBack {
        AnonymousClass1() {
        }

        @Override // com.vivid.sdk.music.RequestCallBack
        public void onSuccess() {
            LogUtil.d(SpotifyManager.TAG, "SpotifyBYDDXMediaManager init onSuccess");
            boolean unused = SpotifyManager.isInit = true;
            SpotifyManager.playState = SpotifyBYDDXMediaManager.getInstance().getPlayState().getCode();
            if (SpotifyManager.isSpotifyOnActive()) {
                CurrentMediaInfoUtils.getInstance().setPlayingState(SpotifyManager.playState == 3);
            }
            SpotifyBYDDXMediaManager.getInstance().addMediaControllerCallback(new MediaControllerCallBack() { // from class: com.byd.music.ThirdSDK.Spotify.SpotifyManager.1.1
                @Override // com.vivid.sdk.spotify.MediaControllerCallBack
                public void onPlaybackStateChanged(Bundle bundle) {
                    LogUtil.d(SpotifyManager.TAG, "onPlaybackStateChanged state = " + bundle);
                    if (bundle == null || !(bundle.get("KEY_PLAY_STATE") instanceof Integer)) {
                        return;
                    }
                    SpotifyManager.playState = ((Integer) bundle.get("KEY_PLAY_STATE")).intValue();
                    if (SpotifyManager.playState == 3) {
                        SpotifyManager.TAG_SPOTIFY_ONACTIVE = true;
                    }
                    LogUtil.d(SpotifyManager.TAG, "onPlaybackStateChanged KEY_PLAY_STATE Integer = " + bundle.get("KEY_PLAY_STATE"));
                    if (SpotifyManager.SpotifyAudioFocusPKG.equals(OnLineMusic.mediaFocusPackage) && ((Integer) bundle.get("KEY_PLAY_STATE")).intValue() == 3) {
                        OnLineMusic.setMusicEngine(103);
                    }
                    if (SpotifyManager.isSpotifyOnActive()) {
                        CurrentMediaInfoUtils.getInstance().setPlayingState(((Integer) bundle.get("KEY_PLAY_STATE")).intValue() == 3);
                    }
                }

                @Override // com.vivid.sdk.spotify.MediaControllerCallBack
                public void onMetadataChanged(Metadata metadata) {
                    if (SpotifyManager.isSpotifyOnActive() && metadata != null) {
                        LogUtil.d(SpotifyManager.TAG, "onMetadataChanged getTitle " + StringSubUtil.subString(metadata.getTitle()));
                        LogUtil.d(SpotifyManager.TAG, "onMetadataChanged getCoverUrl " + StringSubUtil.subString(metadata.getCoverUrl()));
                    }
                    ThirdManager.getInstance().setSpotifyMusic(metadata);
                }

                @Override // com.vivid.sdk.spotify.MediaControllerCallBack
                public void onPositionChanged(long j) {
                    LogUtil.d(SpotifyManager.TAG, "onPositionChanged position = " + j + ", playState = " + SpotifyManager.playState);
                    if (!CurrentMediaInfoUtils.getInstance().getPlayingState() && SpotifyManager.playState == 3 && SpotifyManager.isSpotifyOnActive()) {
                        CurrentMediaInfoUtils.getInstance().setPlayingState(true);
                    }
                    if (!SpotifyManager.isSpotifyOnActive() || ThirdManager.getInstance().getSpotifyMusic() == null) {
                        return;
                    }
                    CurrentMediaInfoUtils.getInstance().updateProgressAndNotify(j, ThirdManager.getInstance().getSpotifyMusic().getDuration(), ThirdManager.getInstance().getSpotifyMusic().getDuration());
                }
            });
        }

        @Override // com.vivid.sdk.music.RequestCallBack
        public void onError(ResponseCode responseCode) {
            LogUtil.e(SpotifyManager.TAG, "init onError with code = " + responseCode);
            boolean unused = SpotifyManager.isInit = false;
            SpotifyManager.TAG_SPOTIFY_ONACTIVE = false;
            if (OnLineMusic.musicEngine == 103) {
                CurrentMediaInfoUtils.getInstance().setPlayingState(false);
            }
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.ThirdSDK.Spotify.-$$Lambda$SpotifyManager$1$0fr1EI8BxXZgxwbfk6R6N799POA
                @Override // java.lang.Runnable
                public final void run() {
                    SpotifyManager.AnonymousClass1.lambda$onError$0();
                }
            }, 1000L);
        }

        static /* synthetic */ void lambda$onError$0() {
            if (!SpotifyManager.isInit) {
                LogUtil.e(SpotifyManager.TAG, "init reconnect runnable running...");
                SpotifyManager.init();
            } else {
                LogUtil.e(SpotifyManager.TAG, "init isInit = true, reconnect runnable removed");
            }
        }
    }

    public static void init() {
        SpotifyBYDDXMediaManager.getInstance().init(Utils.getApp(), new AnonymousClass1());
    }

    public static boolean isSpotifyOnActive() {
        return 103 == OnLineMusic.musicEngine;
    }

    public static String setPlayPattern(int i) {
        if (!isInit) {
            init();
            return MediaControl.REASON_UNSUPPORTED_OPERATION;
        }
        if (!isSpotifyOnActive()) {
            return "";
        }
        if (i == 0) {
            SpotifyBYDDXMediaManager.getInstance().setRepeatMode(RepeatMode.REPEAT_MODE_ALL, null);
            return "";
        }
        if (i == 1) {
            SpotifyBYDDXMediaManager.getInstance().setShuffleMode(ShuffleMode.SHUFFLE_MODE_ALL, null);
            return "";
        }
        if (i != 2) {
            return "";
        }
        SpotifyBYDDXMediaManager.getInstance().setRepeatMode(RepeatMode.REPEAT_MODE_ONE, null);
        return "";
    }

    public static void playNext() {
        if (!isInit) {
            init();
        } else if (isSpotifyOnActive()) {
            LogUtil.d(TAG, "go to skipToNext");
            SpotifyBYDDXMediaManager.getInstance().skipToNext(new RequestCallBack() { // from class: com.byd.music.ThirdSDK.Spotify.SpotifyManager.2
                @Override // com.vivid.sdk.music.RequestCallBack
                public void onSuccess() {
                    LogUtil.d(SpotifyManager.TAG, "skipToNext onSuccess");
                }

                @Override // com.vivid.sdk.music.RequestCallBack
                public void onError(ResponseCode responseCode) {
                    LogUtil.d(SpotifyManager.TAG, "skipToNext onError with responseCode = " + responseCode);
                }
            });
        }
    }

    public static void playPre() {
        if (!isInit) {
            init();
        } else if (isSpotifyOnActive()) {
            LogUtil.d(TAG, "go to skipToPrevious");
            SpotifyBYDDXMediaManager.getInstance().skipToPrevious(new RequestCallBack() { // from class: com.byd.music.ThirdSDK.Spotify.SpotifyManager.3
                @Override // com.vivid.sdk.music.RequestCallBack
                public void onSuccess() {
                    LogUtil.d(SpotifyManager.TAG, "skipToPrevious onSuccess");
                }

                @Override // com.vivid.sdk.music.RequestCallBack
                public void onError(ResponseCode responseCode) {
                    LogUtil.d(SpotifyManager.TAG, "skipToPrevious onError with responseCode = " + responseCode);
                }
            });
        }
    }

    public static void playOrStop(boolean z) {
        if (!isInit) {
            init();
        } else if (z) {
            LogUtil.d(TAG, "go to play");
            SpotifyBYDDXMediaManager.getInstance().play(new RequestCallBack() { // from class: com.byd.music.ThirdSDK.Spotify.SpotifyManager.4
                @Override // com.vivid.sdk.music.RequestCallBack
                public void onSuccess() {
                    LogUtil.d(SpotifyManager.TAG, "play onSuccess");
                }

                @Override // com.vivid.sdk.music.RequestCallBack
                public void onError(ResponseCode responseCode) {
                    LogUtil.d(SpotifyManager.TAG, "play onError with responseCode = " + responseCode);
                }
            });
        } else {
            LogUtil.d(TAG, "go to pause");
            SpotifyBYDDXMediaManager.getInstance().pause(new RequestCallBack() { // from class: com.byd.music.ThirdSDK.Spotify.SpotifyManager.5
                @Override // com.vivid.sdk.music.RequestCallBack
                public void onSuccess() {
                    LogUtil.d(SpotifyManager.TAG, "pause onSuccess");
                }

                @Override // com.vivid.sdk.music.RequestCallBack
                public void onError(ResponseCode responseCode) {
                    LogUtil.d(SpotifyManager.TAG, "pause onError with responseCode = " + responseCode);
                }
            });
        }
    }

    public static boolean isIsInit() {
        return isInit;
    }
}
