package com.byd.vrassistant.conversation.onlinemedia;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.audiocontrol.param.ControlPlaylistParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.PlayControlParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetPlayModeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.result.FavoriteControlResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetMediaStateBeforeConversationResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.PlayControlResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.SetPlayModeResult;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.MediaStateEnum;
import cerence.ark.assistant.controller.enumeration.PlayControlTypeEnum;
import cerence.ark.assistant.controller.enumeration.PlayModeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.manager.MediaSessionManager;
import com.byd.vrassistant.utils.InternetConnectionJudge;
import com.byd.vrassistant.utils.RLog;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class OnlineMediaHelper {
    public static final String AUDIALS_SERVICE_PACKAGE_NAME = "com.audials";
    public static final String AUDIBLE_SERVICE_PACKAGE_NAME = "com.audible.application";
    public static final String BLOOMBERG_PACKAGE_NAME = "com.fce.onlinemedia.bloomberg";
    public static final String BLOOMBERG_SERVICE_PACKAGE_NAME = "com.bloomberg.android.plus";
    public static final String DEEZER_SERVICE_PACKAGE_NAME = "deezer.android.app";
    public static final String IHEARTRADIO_SERVICE_PACKAGE_NAME = "com.clearchannel.iheartradio.controller";
    public static final String LIBBY_SERVICE_PACKAGE_NAME = "com.overdrive.mobile.android.automotive.libby";
    public static final String MELON_SERVICE_PACKAGE_NAME = "com.iloen.melon";
    public static final String MUSICMATE_SERVICE_PACKAGE_NAME = "skplanet.musicmate";
    public static final String ONLINE_MEDIASESSION_CLASS = "com.fce.onlinemedia.voice.CarMediaTransportService";
    public static final String POCKETCASTS_SERVICE_PACKAGE_NAME = "au.com.shiftyjelly.pocketcasts";
    public static final String RADIOLINE_SERVICE_PACKAGE_NAME = "com.radioline.android.radioline.auto";
    public static final String TIDAL_SERVICE_PACKAGE_NAME = "com.aspiro.tidal";
    public static final String TUNEIN_SERVICE_PACKAGE_NAME = "tunein.player";
    private final String TAG;
    private Context mContext;
    public static final String DEEZER_PACKAGE_NAME = "com.fce.onlinemedia.deezer";
    public static final String TIDAL_PACKAGE_NAME = "com.fce.onlinemedia.tidal";
    public static final String MELON_PACKAGE_NAME = "com.fce.onlinemedia.melon";
    public static final String MUSICMATE_PACKAGE_NAME = "com.fce.onlinemedia.musicmate";
    public static final String TUNEIN_PACKAGE_NAME = "com.fce.onlinemedia.tunein";
    public static final String POCKETCASTS_PACKAGE_NAME = "com.fce.onlinemedia.pocketcasts";
    public static final String RADIOLINE_PACKAGE_NAME = "com.fce.onlinemedia.radioline";
    public static final String IHEARTRADIO_PACKAGE_NAME = "com.fce.onlinemedia.iheartradio";
    public static final String AUDIALS_PACKAGE_NAME = "com.fce.onlinemedia.audials";
    public static final String AUDIBLE_PACKAGE_NAME = "com.fce.onlinemedia.audible";
    public static final String LIBBY_PACKAGE_NAME = "com.fce.onlinemedia.libby";
    public static final String[] onlineApps = {DEEZER_PACKAGE_NAME, TIDAL_PACKAGE_NAME, MELON_PACKAGE_NAME, MUSICMATE_PACKAGE_NAME, TUNEIN_PACKAGE_NAME, POCKETCASTS_PACKAGE_NAME, RADIOLINE_PACKAGE_NAME, IHEARTRADIO_PACKAGE_NAME, AUDIALS_PACKAGE_NAME, AUDIBLE_PACKAGE_NAME, LIBBY_PACKAGE_NAME};

    private OnlineMediaHelper() {
        this.TAG = "NEU_OnlineMediaHelper";
        this.mContext = BydVoiceApplication.getContext();
    }

    public void setPlayMode(final String str, SetPlayModeParam setPlayModeParam, IResponseSender<SetPlayModeResult> iResponseSender, SetPlayModeResult setPlayModeResult) {
        boolean z;
        boolean z2;
        try {
            RLog.i("NEU_OnlineMediaHelper", "setPlayMode packagename== " + str + " ,setPlayMode: " + setPlayModeParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.e("NEU_OnlineMediaHelper", "setPlayMode processOnlineMedia 1");
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionManager.get().connectToMediaService(str, countDownLatch);
                }
            });
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.d("NEU_OnlineMediaHelper", "setPlayMode processOnline reconnected");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ERROR】");
            return;
        }
        RLog.d("NEU_OnlineMediaHelper", "setPlayMode processOnline already connected");
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e("NEU_OnlineMediaHelper", "There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【NO_INTERNET】");
            return;
        }
        PlaybackStateCompat currentPlaybackState = MediaSessionManager.get().getCurrentPlaybackState(str);
        if (currentPlaybackState != null) {
            z2 = (currentPlaybackState.getActions() & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0;
            z = (currentPlaybackState.getActions() & PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE) != 0;
        } else {
            z = false;
            z2 = false;
        }
        if ("SHUFFLE".equals(setPlayModeParam.getPlayMode())) {
            RLog.i("NEU_OnlineMediaHelper", "SetPlayMode: setPlayModeParam==SHUFFLE");
            if (!z) {
                iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
                return;
            } else {
                if (MediaSessionManager.get().getCurrentShuffleMode(str) == 1 && MediaSessionManager.get().getCurrentRepeatMode(str) == 0) {
                    iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                    return;
                }
                MediaSessionManager.get().setShuffleMode(str, true);
                if (z2) {
                    MediaSessionManager.get().setRepeatMode(0, str);
                }
                iResponseSender.send(new Response("OK", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
                return;
            }
        }
        if (PlayModeEnum.SEQUENCE.equals(setPlayModeParam.getPlayMode())) {
            RLog.i("NEU_OnlineMediaHelper", "SetPlayMode: setPlayModeParam==SEQUENCE");
            if (!z2) {
                iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
                return;
            } else {
                if (MediaSessionManager.get().getCurrentRepeatMode(str) == 0 && MediaSessionManager.get().getCurrentShuffleMode(str) == 0) {
                    iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                    return;
                }
                if (z) {
                    MediaSessionManager.get().setShuffleMode(str, false);
                }
                MediaSessionManager.get().setRepeatMode(0, str);
                iResponseSender.send(new Response("OK", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
                return;
            }
        }
        if (PlayModeEnum.REPEAT_ONE.equals(setPlayModeParam.getPlayMode())) {
            RLog.i("NEU_OnlineMediaHelper", "SetPlayMode: setPlayModeParam==REPEAT_ONE");
            if (!z2) {
                iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
                return;
            } else {
                if (MediaSessionManager.get().getCurrentRepeatMode(str) == 1 && MediaSessionManager.get().getCurrentShuffleMode(str) == 0) {
                    iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                    return;
                }
                if (z) {
                    MediaSessionManager.get().setShuffleMode(str, false);
                }
                MediaSessionManager.get().setRepeatMode(1, str);
                iResponseSender.send(new Response("OK", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
                return;
            }
        }
        if (PlayModeEnum.REPEAT_ALL.equals(setPlayModeParam.getPlayMode())) {
            RLog.i("NEU_OnlineMediaHelper", "SetPlayMode: setPlayModeParam==REPEAT_ALL");
            if (!z2) {
                iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
            } else {
                if (MediaSessionManager.get().getCurrentRepeatMode(str) == 2 && MediaSessionManager.get().getCurrentShuffleMode(str) == 0) {
                    iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                    return;
                }
                if (z) {
                    MediaSessionManager.get().setShuffleMode(str, false);
                }
                MediaSessionManager.get().setRepeatMode(2, str);
                iResponseSender.send(new Response("OK", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
            }
        }
    }

    private static class OnlineMediaHelperHolder {
        private static final OnlineMediaHelper audioHelper = new OnlineMediaHelper();

        private OnlineMediaHelperHolder() {
        }
    }

    public static OnlineMediaHelper get() {
        return OnlineMediaHelperHolder.audioHelper;
    }

    public void playControl(final String str, PlayControlParam playControlParam, final IResponseSender<PlayControlResult> iResponseSender) {
        boolean z;
        try {
            RLog.i("NEU_OnlineMediaHelper", "playControl packageName== " + str + " ,playControl: " + playControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(getMediaProviderFromPackagename(str));
        RLog.i("NEU_OnlineMediaHelper", ": isConnected:" + MediaSessionManager.get().isServiceConnected(str));
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.e("NEU_OnlineMediaHelper", "processOnlineMedia 1");
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionManager.get().connectToMediaService(str, countDownLatch);
                }
            });
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        RLog.e("NEU_OnlineMediaHelper", "processOnlineMedia 14444444");
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.d("NEU_OnlineMediaHelper", "processOnline reconnected");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            return;
        }
        RLog.d("NEU_OnlineMediaHelper", "processOnline already connected");
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e("NEU_OnlineMediaHelper", "There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【NO_INTERNET】");
            return;
        }
        RLog.i("NEU_OnlineMediaHelper", "cccccccccccccccc: skipToPrevious =" + str);
        PlaybackStateCompat currentPlaybackState = MediaSessionManager.get().getCurrentPlaybackState(str);
        boolean z2 = false;
        if (currentPlaybackState != null) {
            boolean z3 = (currentPlaybackState.getActions() & 16) != 0;
            z = (currentPlaybackState.getActions() & 32) != 0;
            z2 = z3;
        } else {
            z = false;
        }
        if (PlayControlTypeEnum.PREV.equals(playControlParam.getControlType())) {
            RLog.i("NEU_OnlineMediaHelper", "onlinemedia: skipToPrevious =" + str);
            if (!z2) {
                iResponseSender.send(new Response("UNSUPPORTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【UNSUPPORTED】");
                return;
            } else {
                MediaSessionManager.get().playPrevious(str, new MediaMetadataCompatListener() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper.1
                    @Override // com.byd.vrassistant.conversation.onlinemedia.MediaMetadataCompatListener
                    public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                        iResponseSender.send(new Response("OK", playControlResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
                    }
                });
                return;
            }
        }
        if (PlayControlTypeEnum.NEXT.equals(playControlParam.getControlType())) {
            RLog.i("NEU_OnlineMediaHelper", "onlinemedia: skipToNext =" + str);
            if (!z) {
                iResponseSender.send(new Response("UNSUPPORTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【UNSUPPORTED】");
                return;
            } else {
                MediaSessionManager.get().playNext(str, new MediaMetadataCompatListener() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper.2
                    @Override // com.byd.vrassistant.conversation.onlinemedia.MediaMetadataCompatListener
                    public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
                        RLog.i("NEU_OnlineMediaHelper", "onlinemedia: skipToNext success");
                        iResponseSender.send(new Response("OK", playControlResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
                    }
                });
                return;
            }
        }
        if (PlayControlTypeEnum.PAUSE.equals(playControlParam.getControlType())) {
            RLog.i("NEU_OnlineMediaHelper", "onlinemedia: pause =" + str);
            if (!MediaStateEnum.PLAYING.equals(MediaSessionManager.get().getPlayStateInfo(str))) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
            }
            MediaSessionManager.get().pause(str, new PlaybackStateListener() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper.3
                @Override // com.byd.vrassistant.conversation.onlinemedia.PlaybackStateListener
                public void onStateChanged(PlaybackStateCompat playbackStateCompat) {
                    RLog.i("NEU_OnlineMediaHelper", "playcontrol PAUSE: onStateChanged =" + playbackStateCompat.getState());
                    if (playbackStateCompat.getState() != 3) {
                        iResponseSender.send(new Response("OK", playControlResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
                    } else {
                        RLog.i("NEU_OnlineMediaHelper", "playcontrol PAUSE: onStateChanged error ");
                    }
                }
            });
            return;
        }
        if (PlayControlTypeEnum.RESUME.equals(playControlParam.getControlType())) {
            RLog.i("NEU_OnlineMediaHelper", "onlinemedia: play =" + str);
            if (MediaStateEnum.PLAYING.equals(MediaSessionManager.get().getPlayStateInfo(str))) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
            }
            MediaSessionManager.get().play(str, new PlaybackStateListener() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper.4
                @Override // com.byd.vrassistant.conversation.onlinemedia.PlaybackStateListener
                public void onStateChanged(PlaybackStateCompat playbackStateCompat) {
                    RLog.i("NEU_OnlineMediaHelper", "playcontrol RESUME: onStateChanged =" + playbackStateCompat.getState());
                    if (playbackStateCompat.getState() == 3 || playbackStateCompat.getState() == 6) {
                        iResponseSender.send(new Response("OK", playControlResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
                    } else {
                        RLog.i("NEU_OnlineMediaHelper", "playcontrol RESUME: onStateChanged error ");
                    }
                }
            });
        }
    }

    public void OnlineGetPlayState(final String str, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender) {
        GetMediaStateBeforeConversationResult getMediaStateBeforeConversationResult = new GetMediaStateBeforeConversationResult();
        RLog.i("NEU_OnlineMediaHelper", "OnlineGetPlayState : isConnected:" + MediaSessionManager.get().isServiceConnected(str));
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.e("NEU_OnlineMediaHelper", "OnlineGetPlayState 1");
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionManager.get().connectToMediaService(str, countDownLatch);
                }
            });
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.d("NEU_OnlineMediaHelper", "processOnline reconnected");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, getMediaStateBeforeConversationResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
            return;
        }
        RLog.d("NEU_OnlineMediaHelper", "processOnline already connected");
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e("NEU_OnlineMediaHelper", "There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, getMediaStateBeforeConversationResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【NO_INTERNET】");
        } else {
            getMediaStateBeforeConversationResult.setMediaState(MediaSessionManager.get().getPlayStateInfo(str));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【OK】");
            iResponseSender.send(new Response("OK", getMediaStateBeforeConversationResult));
        }
    }

    public void favoriteControl(final String str, FavoriteControlParam favoriteControlParam, final IResponseSender<FavoriteControlResult> iResponseSender) {
        boolean z;
        try {
            RLog.i("NEU_OnlineMediaHelper", "favoriteControl: favoriteControlParam==" + favoriteControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
        favoriteControlResult.setMediaProvider(getMediaProviderFromPackagename(str));
        RLog.i("NEU_OnlineMediaHelper", ":favoriteControl isConnected:" + MediaSessionManager.get().isServiceConnected(str));
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.e("NEU_OnlineMediaHelper", "favoriteControl processOnlineMedia 1");
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionManager.get().connectToMediaService(str, countDownLatch);
                }
            });
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        RLog.e("NEU_OnlineMediaHelper", "favoriteControl processOnlineMedia 14444444");
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.d("NEU_OnlineMediaHelper", "favoriteControl processOnline reconnected");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "favoriteControl request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
            return;
        }
        RLog.d("NEU_OnlineMediaHelper", "favoriteControl processOnline already connected");
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e("NEU_OnlineMediaHelper", "favoriteControl There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【NO_INTERNET】");
            return;
        }
        MediaMetadataCompat currentMediaMeta = MediaSessionManager.get().getCurrentMediaMeta(str);
        boolean z2 = false;
        if (currentMediaMeta != null) {
            boolean z3 = currentMediaMeta.getLong(MediaSessionManager.METADATA_KEY_SUPPORT_FAVORITE) == 1;
            z = currentMediaMeta.getLong(MediaSessionManager.METADATA_KEY_FAVORITE_STATE) == 1;
            z2 = z3;
        } else {
            z = false;
        }
        RLog.d("NEU_OnlineMediaHelper", "favoriteControl: supportFavorite==" + z2 + " ,isCollect==" + z);
        if (!z2) {
            iResponseSender.send(new Response("UNSUPPORTED", favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【UNSUPPORTED】");
            return;
        }
        if ("ADD".equals(favoriteControlParam.getControlType())) {
            RLog.i("NEU_OnlineMediaHelper", "favoriteControl: addFavorite");
            if (z) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ALREADY_EXPECTED】");
                return;
            } else {
                MediaSessionManager.get().addFavorite(str, new FavoriteCollectListener() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper.5
                    @Override // com.byd.vrassistant.conversation.onlinemedia.FavoriteCollectListener
                    public void onFavoriteChanged(boolean z4) {
                        RLog.i("NEU_OnlineMediaHelper", "onFavoriteChanged: add Favorite iscollect==" + z4);
                        MediaMetadataCompat currentMediaMeta2 = MediaSessionManager.get().getCurrentMediaMeta(str);
                        boolean z5 = false;
                        if (currentMediaMeta2 != null && currentMediaMeta2.getLong(MediaSessionManager.METADATA_KEY_FAVORITE_STATE) == 1) {
                            z5 = true;
                        }
                        RLog.d("NEU_OnlineMediaHelper", "favoriteControl: isCollect==" + z5);
                        if (z5) {
                            iResponseSender.send(new Response("OK", favoriteControlResult));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【OK】");
                        } else {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
                        }
                    }
                });
                return;
            }
        }
        RLog.i("NEU_OnlineMediaHelper", "favoriteControl: remove Favorite");
        if (!z) {
            iResponseSender.send(new Response("ALREADY_EXPECTED", favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ALREADY_EXPECTED】");
        } else {
            MediaSessionManager.get().removeFavorite(str, new FavoriteCollectListener() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper.6
                @Override // com.byd.vrassistant.conversation.onlinemedia.FavoriteCollectListener
                public void onFavoriteChanged(boolean z4) {
                    RLog.i("NEU_OnlineMediaHelper", "onFavoriteChanged: add remove iscollect==" + z4);
                    MediaMetadataCompat currentMediaMeta2 = MediaSessionManager.get().getCurrentMediaMeta(str);
                    boolean z5 = false;
                    if (currentMediaMeta2 != null && currentMediaMeta2.getLong(MediaSessionManager.METADATA_KEY_FAVORITE_STATE) == 1) {
                        z5 = true;
                    }
                    RLog.d("NEU_OnlineMediaHelper", "favoriteControl: isCollect==" + z5);
                    if (!z5) {
                        iResponseSender.send(new Response("OK", favoriteControlResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【OK】");
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
                    }
                }
            });
        }
    }

    public void Controlplaylist(final String str, final ControlPlaylistParam controlPlaylistParam, final IResponseSender<BaseResult> iResponseSender) {
        try {
            RLog.i("NEU_OnlineMediaHelper", "Controlplaylist: controlPlaylistParam==" + controlPlaylistParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.e("NEU_OnlineMediaHelper", "Controlplaylist 1");
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionManager.get().connectToMediaService(str, countDownLatch);
                }
            });
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.d("NEU_OnlineMediaHelper", "Controlplaylist processOnline reconnected");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【Controlplaylist】,result【ERROR】");
            return;
        }
        RLog.d("NEU_OnlineMediaHelper", "Controlplaylist processOnline already connected");
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e("NEU_OnlineMediaHelper", "Controlplaylist There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【Controlplaylist】,result【NO_INTERNET】");
            return;
        }
        MediaMetadataCompat currentMediaMeta = MediaSessionManager.get().getCurrentMediaMeta(str);
        boolean z = false;
        if (currentMediaMeta != null) {
            z = currentMediaMeta.getLong(MediaSessionManager.METADATA_KEY_SUPPORT_SHOW_PLAY_LIST) == 1;
        }
        if (!z) {
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【Controlplaylist】,result【UNSUPPORTED】");
        } else {
            MediaSessionManager.get().requestPlayListShowStatus(str, new GetPlaylistStateListener() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper.7
                @Override // com.byd.vrassistant.conversation.onlinemedia.GetPlaylistStateListener
                public void onPlaylistState(boolean z2) {
                    if ("ON".equals(controlPlaylistParam.getAction())) {
                        if (z2) {
                            iResponseSender.send(new Response("ALREADY_EXPECTED"));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【Controlplaylist】,result【ALREADY_EXPECTED】");
                            return;
                        } else {
                            try {
                                MediaSessionManager.get().playListShowHide(true, str);
                            } catch (Exception unused) {
                                RLog.i("NEU_OnlineMediaHelper", "openMusicList spotify 失败");
                                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【openMusicList】,result【ERROR】");
                                return;
                            }
                        }
                    } else if (!z2) {
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【Controlplaylist】,result【ALREADY_EXPECTED】");
                        return;
                    } else {
                        try {
                            MediaSessionManager.get().playListShowHide(false, str);
                        } catch (Exception unused2) {
                            RLog.i("NEU_OnlineMediaHelper", "closeMusicList spotify 失败");
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【closeMusicList】,result【ERROR】");
                            return;
                        }
                    }
                    iResponseSender.send(new Response("OK"));
                }
            });
        }
    }

    public String OnlineGetCurrentPackage() {
        String packetAudio = RadioSdk.getInstance(this.mContext).getPacketAudio();
        RLog.e("NEU_OnlineMediaHelper", "OnlineGetCurrentPackage package_name=" + packetAudio);
        packetAudio.hashCode();
        switch (packetAudio) {
            case "au.com.shiftyjelly.pocketcasts":
                return POCKETCASTS_PACKAGE_NAME;
            case "tunein.player":
                return TUNEIN_PACKAGE_NAME;
            case "com.overdrive.mobile.android.automotive.libby":
                return LIBBY_PACKAGE_NAME;
            case "com.iloen.melon":
                return MELON_PACKAGE_NAME;
            case "com.audials":
                return AUDIALS_PACKAGE_NAME;
            case "com.bloomberg.android.plus":
                return BLOOMBERG_PACKAGE_NAME;
            case "skplanet.musicmate":
                return MUSICMATE_PACKAGE_NAME;
            case "com.aspiro.tidal":
                return TIDAL_PACKAGE_NAME;
            case "com.clearchannel.iheartradio.controller":
                return IHEARTRADIO_PACKAGE_NAME;
            case "com.audible.application":
                return AUDIBLE_PACKAGE_NAME;
            case "deezer.android.app":
                return DEEZER_PACKAGE_NAME;
            case "com.radioline.android.radioline.auto":
                return RADIOLINE_PACKAGE_NAME;
            default:
                return "";
        }
    }

    public String OnlineGetRecentlyPackage() {
        String latestPacketAudio;
        try {
            latestPacketAudio = RadioSdk.getInstance(this.mContext).getLatestPacketAudio();
        } catch (Exception e) {
            RLog.e("NEU_OnlineMediaHelper", "OnlineGetRecentlyPackage error=" + e.toString());
            latestPacketAudio = null;
        }
        RLog.e("NEU_OnlineMediaHelper", "OnlineGetRecentlyPackage package_name=" + latestPacketAudio);
        latestPacketAudio.hashCode();
        switch (latestPacketAudio) {
            case "au.com.shiftyjelly.pocketcasts":
                return POCKETCASTS_PACKAGE_NAME;
            case "tunein.player":
                return TUNEIN_PACKAGE_NAME;
            case "com.overdrive.mobile.android.automotive.libby":
                return LIBBY_PACKAGE_NAME;
            case "com.iloen.melon":
                return MELON_PACKAGE_NAME;
            case "com.audials":
                return AUDIALS_PACKAGE_NAME;
            case "com.bloomberg.android.plus":
                return BLOOMBERG_PACKAGE_NAME;
            case "skplanet.musicmate":
                return MUSICMATE_PACKAGE_NAME;
            case "com.aspiro.tidal":
                return TIDAL_PACKAGE_NAME;
            case "com.clearchannel.iheartradio.controller":
                return IHEARTRADIO_PACKAGE_NAME;
            case "com.audible.application":
                return AUDIBLE_PACKAGE_NAME;
            case "deezer.android.app":
                return DEEZER_PACKAGE_NAME;
            case "com.radioline.android.radioline.auto":
                return RADIOLINE_PACKAGE_NAME;
            default:
                return "";
        }
    }

    public String OnlineApplicationForegroundFor() {
        ComponentName componentName;
        ArrayList arrayList = new ArrayList();
        arrayList.add(DEEZER_PACKAGE_NAME);
        arrayList.add(TIDAL_PACKAGE_NAME);
        arrayList.add(MELON_PACKAGE_NAME);
        arrayList.add(MUSICMATE_PACKAGE_NAME);
        arrayList.add(TUNEIN_PACKAGE_NAME);
        arrayList.add(POCKETCASTS_PACKAGE_NAME);
        arrayList.add(AUDIALS_PACKAGE_NAME);
        arrayList.add(IHEARTRADIO_PACKAGE_NAME);
        arrayList.add(RADIOLINE_PACKAGE_NAME);
        arrayList.add(AUDIBLE_PACKAGE_NAME);
        arrayList.add(LIBBY_PACKAGE_NAME);
        arrayList.add(BLOOMBERG_PACKAGE_NAME);
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) BydVoiceApplication.getContext().getSystemService("activity")).getRunningTasks(1);
        new AtomicBoolean(false);
        if (runningTasks.isEmpty() || (componentName = runningTasks.get(0).topActivity) == null) {
            return "";
        }
        String packageName = componentName.getPackageName();
        RLog.e("NEU_OnlineMediaHelper", "OnlineApplicationForegroundFor topActivity.getPackageName()=" + packageName);
        if (!arrayList.contains(packageName)) {
            return "";
        }
        RLog.e("NEU_OnlineMediaHelper", "contains.getPackageName()=" + packageName);
        return packageName;
    }

    public String getMediaProviderFromPackagename(String str) {
        RLog.d("NEU_OnlineMediaHelper", "getMediaProviderFromPackagename packagename==" + str);
        str.hashCode();
        switch (str) {
            case "com.fce.onlinemedia.radioline":
                return MediaProviderEnum.RADIOLINE;
            case "com.fce.onlinemedia.pocketcasts":
                return MediaProviderEnum.POCKET_CASTS;
            case "com.fce.onlinemedia.iheartradio":
                return MediaProviderEnum.IHEARTRADIO;
            case "com.fce.onlinemedia.musicmate":
                return MediaProviderEnum.FLO;
            case "com.fce.onlinemedia.libby":
                return MediaProviderEnum.LIBBY;
            case "com.fce.onlinemedia.melon":
                return MediaProviderEnum.MELON;
            case "com.fce.onlinemedia.tidal":
                return MediaProviderEnum.TIDAL;
            case "com.fce.onlinemedia.bloomberg":
                return MediaProviderEnum.BLOOMBERG;
            case "com.fce.onlinemedia.audials":
                return MediaProviderEnum.AUDIALS_PLAY;
            case "com.fce.onlinemedia.audible":
                return MediaProviderEnum.AUDIBLE;
            case "com.fce.onlinemedia.deezer":
                return MediaProviderEnum.DEEZER;
            case "com.fce.onlinemedia.tunein":
                return MediaProviderEnum.TUNEIN;
            default:
                return MediaProviderEnum.NONE;
        }
    }

    public String getPackageNameFromProvider(String str) {
        RLog.d("NEU_OnlineMediaHelper", "getPackageNameFromProvider provider==" + str);
        str.hashCode();
        switch (str) {
            case "TUNEIN":
                return TUNEIN_PACKAGE_NAME;
            case "POCKET_CASTS":
                return POCKETCASTS_PACKAGE_NAME;
            case "BLOOMBERG":
                return BLOOMBERG_PACKAGE_NAME;
            case "FLO":
                return MUSICMATE_PACKAGE_NAME;
            case "AUDIBLE":
                return AUDIBLE_PACKAGE_NAME;
            case "LIBBY":
                return LIBBY_PACKAGE_NAME;
            case "MELON":
                return MELON_PACKAGE_NAME;
            case "TIDAL":
                return TIDAL_PACKAGE_NAME;
            case "IHEARTRADIO":
                return IHEARTRADIO_PACKAGE_NAME;
            case "AUDIALS_PLAY":
                return AUDIALS_PACKAGE_NAME;
            case "RADIOLINE":
                return RADIOLINE_PACKAGE_NAME;
            case "DEEZER":
                return DEEZER_PACKAGE_NAME;
            default:
                return "";
        }
    }

    public <T extends BaseResult> void openOnlineMusic(final String str, IResponseSender<T> iResponseSender, T t, boolean z, String str2) {
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.e("NEU_OnlineMediaHelper", "Controlplaylist 1");
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    MediaSessionManager.get().connectToMediaService(str, countDownLatch);
                }
            });
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!MediaSessionManager.get().isServiceConnected(str)) {
            RLog.d("NEU_OnlineMediaHelper", "Controlplaylist processOnline reconnected");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, t));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【Controlplaylist】,result【ERROR】");
            return;
        }
        RLog.d("NEU_OnlineMediaHelper", "Controlplaylist processOnline already connected");
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e("NEU_OnlineMediaHelper", "Controlplaylist There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, t));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【Controlplaylist】,result【NO_INTERNET】");
        } else {
            MediaSessionManager.get().openOnlineMedia(str);
            iResponseSender.send(new Response("OK", t));
        }
    }
}
