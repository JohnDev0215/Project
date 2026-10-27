package com.byd.vrassistant.customizedimpl;

import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain;
import cerence.ark.assistant.controller.domain.audiocontrol.param.ControlFavoritelistParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.ControlPlaylistParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.ControlVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.GetMediaStateBeforeConversationParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.GetRecentPlayedMediaProviderParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.GetVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.PlayControlParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetMICVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetPlayModeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetVolumeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.result.FavoriteControlResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetActiveMediaTypeBeforeConversationResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetCurrentStreamTypeResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetGlobalMuteStateResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetMICVolumeResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetMediaStateBeforeConversationResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetRecentPlayedMediaProviderResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.GetVolumeResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.IsBTConnectedResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.IsMICConnectedResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.PlayControlResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.SetPlayModeResult;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.MediaStateEnum;
import cerence.ark.assistant.controller.enumeration.MediaTypeEnum;
import cerence.ark.assistant.controller.enumeration.PlayControlTypeEnum;
import cerence.ark.assistant.controller.enumeration.PlayModeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.StreamTypeEnum;
import com.byd.amazon.music.param.PlayMode;
import com.byd.amazon.music.param.PlayState;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.AudioHelper;
import com.byd.vrassistant.conversation.AudioSdk;
import com.byd.vrassistant.conversation.DABHelper;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.conversation.RadioHelper;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper;
import com.byd.vrassistant.manager.AmazonManager;
import com.byd.vrassistant.manager.KaraokeManager;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.SpotifyManager;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.InternetConnectionJudge;
import com.byd.vrassistant.utils.RLog;
import com.vivid.sdk.music.ResponseCode;
import com.vivid.sdk.spotify.RepeatMode;
import com.vivid.sdk.spotify.ShuffleMode;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class CustomAudioControlDomain extends AbsAudioControlDomain {
    public static int AudioResult = -1;
    public static String TAG = "NEU_CustomAudioControlDomain Ver=S.8.12.4";
    public static int pre_mute_volume_bt_music = -1;
    public static int pre_mute_volume_music = -1;

    public CustomAudioControlDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void getActiveMediaTypeBeforeConversation(IResponseSender<GetActiveMediaTypeBeforeConversationResult> iResponseSender) {
        RLog.i(TAG, "getActiveMediaTypeBeforeConversation:");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getActiveMediaTypeBeforeConversation】");
        GetActiveMediaTypeBeforeConversationResult getActiveMediaTypeBeforeConversationResult = new GetActiveMediaTypeBeforeConversationResult();
        int mediaMode = RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode();
        String packetAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio();
        RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus();
        if (OnlineMediaHelper.get().OnlineGetCurrentPackage() != "" || OnlineMediaHelper.get().OnlineGetRecentlyPackage() != "" || LocalMediaManager.SPOTIFY_PACKAGE_NAME.equals(packetAudio) || LocalMediaManager.AMAZON_PACKAGE_NAME.equals(packetAudio)) {
            getActiveMediaTypeBeforeConversationResult.setMediaType("SONG");
        } else if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
            getActiveMediaTypeBeforeConversationResult.setMediaType(MediaTypeEnum.DAB);
        } else if (LocalMediaManager.LOCALMEDIA_PACKAGE_NAME.equals(packetAudio)) {
            if (mediaMode == 0) {
                getActiveMediaTypeBeforeConversationResult.setMediaType(MediaTypeEnum.RADIO);
            } else if (mediaMode == 1 || mediaMode == 8 || mediaMode == 5) {
                getActiveMediaTypeBeforeConversationResult.setMediaType("SONG");
            }
        } else if (LocalMediaManager.get().getCurrentMediaProvider().equals("BLUETOOTH") || LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || LocalMediaManager.KARAOKE_PACKAGE_NAME.equals(packetAudio)) {
            getActiveMediaTypeBeforeConversationResult.setMediaType("SONG");
        } else {
            getActiveMediaTypeBeforeConversationResult.setMediaType("UNSPECIFIED");
        }
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getActiveMediaTypeBeforeConversation】,result【OK】");
        iResponseSender.send(new Response("OK", getActiveMediaTypeBeforeConversationResult));
    }

    protected void SpotifyPlayGetPlayState(GetMediaStateBeforeConversationParam getMediaStateBeforeConversationParam, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender) {
        try {
            RLog.i(TAG, "SpotifyPlayGetPlayState: " + getMediaStateBeforeConversationParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GetMediaStateBeforeConversationResult getMediaStateBeforeConversationResult = new GetMediaStateBeforeConversationResult();
        RLog.i(TAG, "SpotifyManager: isConnected:" + SpotifyManager.get().isConnected());
        processSpotify();
        if (!SpotifyManager.get().isConnected()) {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【SPOTIFY_UNAVAILABLE】");
            iResponseSender.send(new Response(ResultCodeEnum.SPOTIFY_UNAVAILABLE, getMediaStateBeforeConversationResult));
            return;
        }
        RLog.e(TAG, "SpotifyPlayGetPlayState processSpotify 2");
        if (SpotifyManager.getResponseCode() != ResponseCode.OK && SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【NOT_LOGGED_IN】");
            iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, getMediaStateBeforeConversationResult));
            return;
        }
        int state = SpotifyManager.get().getState();
        RLog.i(TAG, "getMediaStateBeforeConversation mediaState: " + state);
        if (state == SpotifyManager.STATE_PLAYING) {
            getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PLAYING);
        } else if (state == SpotifyManager.STATE_STOPPED) {
            getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
        } else if (state == SpotifyManager.STATE_PAUSED) {
            getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PAUSED);
        }
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【OK】");
        iResponseSender.send(new Response("OK", getMediaStateBeforeConversationResult));
    }

    protected void AmazonPlayGetPlayState(GetMediaStateBeforeConversationParam getMediaStateBeforeConversationParam, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender) {
        try {
            RLog.i(TAG, "AmazonPlayGetPlayState: " + getMediaStateBeforeConversationParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GetMediaStateBeforeConversationResult getMediaStateBeforeConversationResult = new GetMediaStateBeforeConversationResult();
        RLog.i(TAG, "AmazonManager: isConnected:" + AmazonManager.get().isConnected());
        processAmazon();
        if (!AmazonManager.get().isConnected()) {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【MEDIA_PROVIDER_UNAVAILABLE】");
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_PROVIDER_UNAVAILABLE, getMediaStateBeforeConversationResult));
            return;
        }
        RLog.e(TAG, "AmazonPlayGetPlayState processSpotify 2");
        if (!AmazonManager.get().isLogin()) {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【NOT_LOGGED_IN】");
            iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, getMediaStateBeforeConversationResult));
            return;
        }
        PlayState state = AmazonManager.get().getState();
        RLog.i(TAG, "getMediaStateBeforeConversation mediaState: " + state);
        if (state == AmazonManager.STATE_PLAYING) {
            getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PLAYING);
        } else if (state == AmazonManager.STATE_STOPPED) {
            getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
        } else if (state == AmazonManager.STATE_PAUSED) {
            getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PAUSED);
        }
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【OK】");
        iResponseSender.send(new Response("OK", getMediaStateBeforeConversationResult));
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void getMediaStateBeforeConversation(GetMediaStateBeforeConversationParam getMediaStateBeforeConversationParam, IResponseSender<GetMediaStateBeforeConversationResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】");
        RLog.i(TAG, "getMediaStateBeforeConversation:");
        try {
            RLog.i(TAG, "getMediaStateBeforeConversation: " + getMediaStateBeforeConversationParam.toJsonString());
            EventBus.getDefault().post("getMediaStateBeforeConversation: " + getMediaStateBeforeConversationParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GetMediaStateBeforeConversationResult getMediaStateBeforeConversationResult = new GetMediaStateBeforeConversationResult();
        int playStatus = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus();
        RLog.i(TAG, "getMediaStateBeforeConversation Local mediaState: " + playStatus);
        if (getMediaStateBeforeConversationParam.getMediaType().equals(MediaTypeEnum.RADIO)) {
            if (!RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue()) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            } else if (playStatus == 0) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PLAYING);
            } else if (playStatus == 1) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PAUSED);
            } else if (playStatus == 2) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            }
        } else if (getMediaStateBeforeConversationParam.getMediaType().equals(MediaTypeEnum.DAB)) {
            if (!RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            } else if (playStatus == 0) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PLAYING);
            } else if (playStatus == 1) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PAUSED);
            } else if (playStatus == 2) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            }
        } else if (getMediaStateBeforeConversationParam.getMediaType().equals("SONG")) {
            if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue() || RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            } else {
                if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY)) {
                    RLog.i(TAG, "getMediaStateBeforeConversation SpotifyPlayGetPlayState getMediaType SONG");
                    SpotifyPlayGetPlayState(getMediaStateBeforeConversationParam, iResponseSender);
                    return;
                }
                if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC)) {
                    RLog.i(TAG, "getMediaStateBeforeConversation AmazonPlayGetPlayState getMediaType SONG");
                    AmazonPlayGetPlayState(getMediaStateBeforeConversationParam, iResponseSender);
                    return;
                }
                if (OnlineMediaHelper.get().OnlineGetCurrentPackage() != "") {
                    OnlineMediaHelper.get().OnlineGetPlayState(OnlineMediaHelper.get().OnlineGetCurrentPackage(), iResponseSender);
                }
                if (OnlineMediaHelper.get().OnlineGetRecentlyPackage() != "") {
                    OnlineMediaHelper.get().OnlineGetPlayState(OnlineMediaHelper.get().OnlineGetRecentlyPackage(), iResponseSender);
                }
                if (!AudioHelper.getInstance(BydVoiceApplication.getContext()).IfBTMusicWorking().booleanValue() && !AudioHelper.getInstance(BydVoiceApplication.getContext()).IfMusicWorking().booleanValue()) {
                    getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
                } else if (playStatus == 0) {
                    getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PLAYING);
                } else if (playStatus == 1) {
                    getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PAUSED);
                } else if (playStatus == 2) {
                    getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
                }
            }
        } else if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue() || RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
            if (playStatus == 0) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PLAYING);
            } else if (playStatus == 1) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PAUSED);
            } else if (playStatus == 2) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            }
        } else {
            if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY)) {
                RLog.i(TAG, "getMediaStateBeforeConversation SpotifyPlayGetPlayState getMediaType UNSPECIFIED");
                SpotifyPlayGetPlayState(getMediaStateBeforeConversationParam, iResponseSender);
                return;
            }
            if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC)) {
                RLog.i(TAG, "getMediaStateBeforeConversation AmazonPlayGetPlayState getMediaType UNSPECIFIED");
                AmazonPlayGetPlayState(getMediaStateBeforeConversationParam, iResponseSender);
                return;
            } else if (!AudioHelper.getInstance(BydVoiceApplication.getContext()).IfBTMusicWorking().booleanValue() && !AudioHelper.getInstance(BydVoiceApplication.getContext()).IfMusicWorking().booleanValue()) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            } else if (playStatus == 0) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PLAYING);
            } else if (playStatus == 1) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.PAUSED);
            } else if (playStatus == 2) {
                getMediaStateBeforeConversationResult.setMediaState(MediaStateEnum.STOPPED);
            }
        }
        if (!RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue() && (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY))) {
            SpotifyPlayGetPlayState(getMediaStateBeforeConversationParam, iResponseSender);
        } else if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC)) {
            AmazonPlayGetPlayState(getMediaStateBeforeConversationParam, iResponseSender);
        } else {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMediaStateBeforeConversation】,result【OK】");
            iResponseSender.send(new Response("OK", getMediaStateBeforeConversationResult));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void getRecentPlayedMediaProvider(GetRecentPlayedMediaProviderParam getRecentPlayedMediaProviderParam, IResponseSender<GetRecentPlayedMediaProviderResult> iResponseSender) {
        try {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getRecentPlayedMediaProvider】");
            RLog.i(TAG, "getRecentPlayedMediaProvider: " + getRecentPlayedMediaProviderParam.toJsonString());
            EventBus.getDefault().post("playMedia: " + getRecentPlayedMediaProviderParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GetRecentPlayedMediaProviderResult getRecentPlayedMediaProviderResult = new GetRecentPlayedMediaProviderResult();
        boolean zIsAppInstalled = ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.KARAOKE_PACKAGE_NAME);
        ArrayList arrayList = new ArrayList();
        arrayList.add(LocalMediaManager.KARAOKE_PACKAGE_NAME);
        if (zIsAppInstalled && BydVoiceApplication.isApplicationForeground(arrayList)) {
            getRecentPlayedMediaProviderResult.setMediaProvider(MediaProviderEnum.KARAOKE);
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getRecentPlayedMediaProvider】,result【OK】");
            iResponseSender.send(new Response("OK", getRecentPlayedMediaProviderResult));
            return;
        }
        String currentMediaProvider = LocalMediaManager.get().getCurrentMediaProvider();
        if (currentMediaProvider.equals(MediaProviderEnum.LOCAL) && (RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 0 || RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 13)) {
            currentMediaProvider = MediaProviderEnum.NONE;
        }
        if (currentMediaProvider.equals(MediaProviderEnum.NONE)) {
            currentMediaProvider = LocalMediaManager.get().getRecentMediaProvider();
        }
        getRecentPlayedMediaProviderResult.setMediaProvider(currentMediaProvider);
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getRecentPlayedMediaProvider】,result【OK】");
        iResponseSender.send(new Response("OK", getRecentPlayedMediaProviderResult));
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void getVolume(GetVolumeParam getVolumeParam, IResponseSender<GetVolumeResult> iResponseSender) {
        try {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getVolume】");
            RLog.i(TAG, "getVolume: " + getVolumeParam.toJsonString());
            EventBus.getDefault().post("getVolume: " + getVolumeParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GetVolumeResult getVolumeResult = new GetVolumeResult();
        int i = 1;
        if (!getVolumeParam.getStreamType().equals("MEDIA") && !getVolumeParam.getStreamType().equals("BLUETOOTH") && !getVolumeParam.getStreamType().equals(StreamTypeEnum.BLUETOOTH_MUSIC)) {
            if (getVolumeParam.getStreamType().equals(StreamTypeEnum.NAVIGATION)) {
                i = 12;
            } else if (getVolumeParam.getStreamType().equals(StreamTypeEnum.VOICE_ASSISTANT)) {
                i = 16;
            } else if (getVolumeParam.getStreamType().equals(StreamTypeEnum.BLUETOOTH_PHONE)) {
                i = 2;
            }
        }
        int minVolume = AudioHelper.getInstance(BydVoiceApplication.getContext()).getMinVolume(i);
        int maxVolume = AudioHelper.getInstance(BydVoiceApplication.getContext()).getMaxVolume(i);
        int currentVolume = AudioHelper.getInstance(BydVoiceApplication.getContext()).getCurrentVolume(i);
        RLog.i(TAG, "getVolume: minValue ==== " + minVolume);
        RLog.i(TAG, "getVolume: maxValue ==== " + maxVolume);
        RLog.i(TAG, "getVolume: currentValue ==== " + currentVolume);
        getVolumeResult.setMinValue(minVolume);
        getVolumeResult.setMaxValue(maxVolume);
        getVolumeResult.setCurrentValue(currentVolume);
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getVolume】,result【OK】");
        iResponseSender.send(new Response("OK", getVolumeResult));
    }

    void processSpotify() {
        if (SpotifyManager.get().isConnected()) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SpotifyManager.mResponseCode = ResponseCode.OK;
        SpotifyManager.get().init(countDownLatch);
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void processAmazon() {
        RLog.i(TAG, "processAmazon");
        if (AmazonManager.get().isConnected()) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AmazonManager.get().init(countDownLatch);
        try {
            countDownLatch.await(6L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void AmazonPlayControl(PlayControlParam playControlParam, IResponseSender<PlayControlResult> iResponseSender) {
        int value;
        try {
            RLog.i(TAG, "AmazonPlayControl: " + playControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
        RLog.i(TAG, "AmazonManager: isConnected:" + AmazonManager.get().isConnected());
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e(TAG, "There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【NO_INTERNET】");
            return;
        }
        processAmazon();
        if (!AmazonManager.get().isConnected()) {
            RLog.e(TAG, "processAmazon 1");
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【Amazon_UNAVAILABLE】");
            return;
        }
        if (PlayControlTypeEnum.PREV.equals(playControlParam.getControlType())) {
            if (!AmazonManager.get().saveCurrentRunningState()) {
                RLog.e(TAG, "!isActivityAlreadyRun");
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【MEDIA_NOT_FOUND】");
                return;
            }
            value = AmazonManager.get().skipToPrevious().getValue();
            RLog.i(TAG, "AmazonManager: skipToPrevious");
        } else if (PlayControlTypeEnum.NEXT.equals(playControlParam.getControlType())) {
            if (!AmazonManager.get().saveCurrentRunningState()) {
                RLog.e(TAG, "!isActivityAlreadyRun");
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【MEDIA_NOT_FOUND】");
                return;
            }
            value = AmazonManager.get().skipToNext().getValue();
            RLog.i(TAG, "AmazonManager: skipToNext");
        } else if (PlayControlTypeEnum.PAUSE.equals(playControlParam.getControlType())) {
            if (AmazonManager.get().getState() == AmazonManager.STATE_PLAYING) {
                value = AmazonManager.get().pause().getValue();
                RLog.i(TAG, "AmazonManager: pause");
            } else if (AmazonManager.get().getState() == AmazonManager.STATE_PAUSED) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
                return;
            } else {
                RLog.i(TAG, "AmazonManager:else pause");
                value = AmazonManager.get().pause().getValue();
            }
        } else if (!PlayControlTypeEnum.RESUME.equals(playControlParam.getControlType())) {
            value = 0;
        } else if (AmazonManager.get().getState() == AmazonManager.STATE_PLAYING) {
            RLog.i(TAG, "AmazonManager: is playing");
            iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
            return;
        } else if (AmazonManager.get().getState() == AmazonManager.STATE_PAUSED) {
            value = AmazonManager.get().resume().getValue();
            RLog.i(TAG, "AmazonManager: resume");
        } else {
            RLog.i(TAG, "AmazonManager:else resume");
            value = AmazonManager.get().resume().getValue();
        }
        if (value == 0) {
            RLog.e(TAG, "AmazonManager.getResponseCode = OK");
            iResponseSender.send(new Response("OK", playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
        } else if (value == 1001) {
            iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "2 request domain【CustomAudioControlDomain】,api【playControl】,result【NOT_LOGGED_IN】");
        } else if (value == 1002) {
            iResponseSender.send(new Response("UNSUPPORTED", playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【UNSUPPORTED】");
        } else if (value == 1009) {
            iResponseSender.send(new Response("OUT_OF_RANGE", playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OUT_OF_RANGE】");
        } else {
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【MEDIA_NOT_FOUND】");
        }
    }

    public void SpotifyPlayControl(PlayControlParam playControlParam, IResponseSender<PlayControlResult> iResponseSender) {
        try {
            RLog.i(TAG, "SpotifyPlayControl: " + playControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(MediaProviderEnum.SPOTIFY);
        RLog.i(TAG, "SpotifyManager: isConnected:" + SpotifyManager.get().isConnected());
        processSpotify();
        if (!SpotifyManager.get().isConnected()) {
            RLog.e(TAG, "processSpotify 1");
            iResponseSender.send(new Response(ResultCodeEnum.SPOTIFY_UNAVAILABLE, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【SPOTIFY_UNAVAILABLE】");
            return;
        }
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e(TAG, "There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【NO_INTERNET】");
            return;
        }
        if (PlayControlTypeEnum.PREV.equals(playControlParam.getControlType())) {
            if (!SpotifyManager.get().saveCurrentRunningState()) {
                RLog.e(TAG, "!isActivityAlreadyRun");
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【MEDIA_NOT_FOUND】");
                return;
            }
            SpotifyManager.get().skipToPrevious();
            RLog.i(TAG, "SpotifyManager: skipToPrevious");
        } else if (PlayControlTypeEnum.NEXT.equals(playControlParam.getControlType())) {
            if (!SpotifyManager.get().saveCurrentRunningState()) {
                RLog.e(TAG, "!isActivityAlreadyRun");
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【MEDIA_NOT_FOUND】");
                return;
            }
            SpotifyManager.get().skipToNext();
            RLog.i(TAG, "SpotifyManager: skipToNext");
        } else if (PlayControlTypeEnum.PAUSE.equals(playControlParam.getControlType())) {
            if (SpotifyManager.get().getState() == SpotifyManager.STATE_PLAYING) {
                SpotifyManager.get().pause();
                RLog.i(TAG, "SpotifyManager: pause");
            } else if (SpotifyManager.get().getState() == SpotifyManager.STATE_PAUSED) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
                return;
            } else {
                RLog.i(TAG, "SpotifyManager:else pause");
                SpotifyManager.get().pause();
            }
        } else if (PlayControlTypeEnum.RESUME.equals(playControlParam.getControlType())) {
            if (SpotifyManager.get().getState() == SpotifyManager.STATE_PLAYING) {
                RLog.i(TAG, "SpotifyManager: is playing");
                iResponseSender.send(new Response("ALREADY_EXPECTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
                return;
            } else if (SpotifyManager.get().getState() == SpotifyManager.STATE_PAUSED) {
                SpotifyManager.get().resume();
                RLog.i(TAG, "SpotifyManager: resume");
            } else {
                RLog.i(TAG, "SpotifyManager:else resume");
                SpotifyManager.get().resume();
            }
        }
        if (SpotifyManager.getResponseCode() != ResponseCode.OK) {
            RLog.e(TAG, "SpotifyManager.getResponseCode != OK : " + SpotifyManager.getResponseCode().name());
            if (SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "2 request domain【CustomAudioControlDomain】,api【playControl】,result【NOT_LOGGED_IN】");
                return;
            }
            if (SpotifyManager.getResponseCode() == ResponseCode.MODE_UNSUPPORT) {
                iResponseSender.send(new Response("UNSUPPORTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【UNSUPPORTED】");
                return;
            } else if (SpotifyManager.getResponseCode() == ResponseCode.NO_NEXT_SONG) {
                iResponseSender.send(new Response("OUT_OF_RANGE", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OUT_OF_RANGE】");
                return;
            } else if (SpotifyManager.getResponseCode() == ResponseCode.NO_PREV_SONG) {
                iResponseSender.send(new Response("UNSUPPORTED", playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OUT_OF_RANGE】");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, playControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【MEDIA_NOT_FOUND】");
                return;
            }
        }
        RLog.e(TAG, "SpotifyManager.getResponseCode = OK");
        iResponseSender.send(new Response("OK", playControlResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
    }

    public void KaraokePlayControl(PlayControlParam playControlParam, IResponseSender<PlayControlResult> iResponseSender) {
        try {
            RLog.i(TAG, "KaraokePlayControl: " + playControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.setMediaProvider(MediaProviderEnum.KARAOKE);
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e(TAG, "There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, playControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【NO_INTERNET】");
            return;
        }
        if (PlayControlTypeEnum.PREV.equals(playControlParam.getControlType())) {
            KaraokeManager.get().skipToPrevious();
            RLog.i(TAG, "KaraokeManager: skipToPrevious");
        } else if (PlayControlTypeEnum.NEXT.equals(playControlParam.getControlType())) {
            KaraokeManager.get().skipToNext();
            RLog.i(TAG, "KaraokeManager: skipToNext");
        } else if (PlayControlTypeEnum.PAUSE.equals(playControlParam.getControlType())) {
            KaraokeManager.get().pause();
            RLog.i(TAG, "KaraokeManager: pause");
        } else if (PlayControlTypeEnum.RESUME.equals(playControlParam.getControlType())) {
            RLog.i(TAG, "KaraokeManager:else resume");
            KaraokeManager.get().resume();
        }
        RLog.e(TAG, "KaraokeManager.getResponseCode = OK");
        iResponseSender.send(new Response("OK", playControlResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:197:0x06b6  */
    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void playControl(cerence.ark.assistant.controller.domain.audiocontrol.param.PlayControlParam r18, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.audiocontrol.result.PlayControlResult> r19) {
        /*
            Method dump skipped, instruction units count: 2558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.customizedimpl.CustomAudioControlDomain.playControl(cerence.ark.assistant.controller.domain.audiocontrol.param.PlayControlParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public void SpotifySetPlayMode(SetPlayModeParam setPlayModeParam, IResponseSender<SetPlayModeResult> iResponseSender, SetPlayModeResult setPlayModeResult) {
        try {
            RLog.i(TAG, "SpotifySetPlayMode: setPlayModeParam==" + setPlayModeParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        processSpotify();
        if (!SpotifyManager.get().isConnected()) {
            iResponseSender.send(new Response(ResultCodeEnum.SPOTIFY_UNAVAILABLE, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【SPOTIFY_UNAVAILABLE】");
            return;
        }
        ResponseCode responseCode = ResponseCode.OK;
        if ("SHUFFLE".equals(setPlayModeParam.getPlayMode())) {
            RLog.i(TAG, "SpotifySetPlayMode: setPlayModeParam==SHUFFLE");
            SpotifyManager.get().setShuffleMode(ShuffleMode.SHUFFLE_MODE_ALL);
        } else if (PlayModeEnum.SEQUENCE.equals(setPlayModeParam.getPlayMode())) {
            RLog.i(TAG, "SpotifySetPlayMode: setPlayModeParam==SEQUENCE");
            SpotifyManager.get().setRepeatMode(RepeatMode.REPEAT_MODE_NONE);
            SpotifyManager.get().setShuffleMode(ShuffleMode.SHUFFLE_MODE_NONE);
        } else if (PlayModeEnum.REPEAT_ONE.equals(setPlayModeParam.getPlayMode())) {
            RLog.i(TAG, "SpotifySetPlayMode: setPlayModeParam==REPEAT_ONE");
            SpotifyManager.get().setRepeatMode(RepeatMode.REPEAT_MODE_ONE);
        } else if (PlayModeEnum.REPEAT_ALL.equals(setPlayModeParam.getPlayMode())) {
            RLog.i(TAG, "SpotifySetPlayMode: setPlayModeParam==REPEAT_ALL");
            SpotifyManager.get().setRepeatMode(RepeatMode.REPEAT_MODE_ALL);
        }
        if (SpotifyManager.getResponseCode() != ResponseCode.OK) {
            RLog.e(TAG, "SpotifyManager.getResponseCode != OK : " + SpotifyManager.getResponseCode().name());
            if (SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【NOT_LOGGED_IN】");
                return;
            }
            if (SpotifyManager.getResponseCode() == ResponseCode.MODE_UNSUPPORT) {
                iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
                return;
            }
            if (SpotifyManager.getResponseCode() == ResponseCode.REPEAT_SET_MODE) {
                if (PlayModeEnum.SEQUENCE.equals(setPlayModeParam.getPlayMode())) {
                    if (SpotifyManager.getResponseRepeatModeCode() == ResponseCode.REPEAT_SET_MODE) {
                        iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                        return;
                    } else {
                        iResponseSender.send(new Response("OK", setPlayModeResult));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
                        return;
                    }
                }
                iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                return;
            }
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【MEDIA_NOT_FOUND】");
            return;
        }
        RLog.e(TAG, "SpotifySetPlayMode processSpotify 2");
        iResponseSender.send(new Response("OK", setPlayModeResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
    }

    public void AmazonSetPlayMode(SetPlayModeParam setPlayModeParam, IResponseSender<SetPlayModeResult> iResponseSender, SetPlayModeResult setPlayModeResult) {
        int value;
        try {
            RLog.i(TAG, "AmazonSetPlayMode: setPlayModeParam==" + setPlayModeParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        processAmazon();
        if (!AmazonManager.get().isConnected()) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【AMAZON_UNAVAILABLE】");
            return;
        }
        PlayMode curPlayMode = AmazonManager.get().getCurPlayMode();
        if ("SHUFFLE".equals(setPlayModeParam.getPlayMode())) {
            if (curPlayMode == PlayMode.SHUFFLE) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                return;
            }
            value = AmazonManager.get().setPlayMode(PlayMode.SHUFFLE).getValue();
        } else if (PlayModeEnum.REPEAT_ONE.equals(setPlayModeParam.getPlayMode())) {
            if (curPlayMode == PlayMode.SINGLE_REPEAT) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                return;
            }
            value = AmazonManager.get().setPlayMode(PlayMode.SINGLE_REPEAT).getValue();
        } else {
            if (curPlayMode == PlayMode.LIST_LOOP) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                return;
            }
            value = AmazonManager.get().setPlayMode(PlayMode.LIST_LOOP).getValue();
        }
        if (value != 0) {
            RLog.e(TAG, "AmazonManager.getResponseCode != OK : " + value);
            if (value == 1001) {
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【NOT_LOGGED_IN】");
                return;
            } else if (value == 1002) {
                iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
                return;
            } else if (value == 1012) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【MEDIA_NOT_FOUND】");
                return;
            }
        }
        RLog.e(TAG, "AmazonSetPlayMode processAmazon 2");
        iResponseSender.send(new Response("OK", setPlayModeResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
    }

    public void KaraokeSetPlayMode(SetPlayModeParam setPlayModeParam, IResponseSender<SetPlayModeResult> iResponseSender, SetPlayModeResult setPlayModeResult) {
        try {
            RLog.i(TAG, "KaraokeSetPlayMode: setPlayModeParam==" + setPlayModeParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if ("SHUFFLE".equals(setPlayModeParam.getPlayMode())) {
            KaraokeManager.get().setPlayModeShuffle();
        } else if (PlayModeEnum.REPEAT_ONE.equals(setPlayModeParam.getPlayMode())) {
            KaraokeManager.get().setPlayModeRepeatOne();
        } else {
            KaraokeManager.get().setPlayModeListLoop();
        }
        RLog.e(TAG, "KaraokeSetPlayMode  2");
        iResponseSender.send(new Response("OK", setPlayModeResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void setPlayMode(SetPlayModeParam setPlayModeParam, IResponseSender<SetPlayModeResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】");
        RLog.i(TAG, "setPlayMode: start");
        SetPlayModeResult setPlayModeResult = new SetPlayModeResult();
        setPlayModeResult.setMediaProvider(MediaProviderEnum.NONE);
        ArrayList arrayList = new ArrayList();
        arrayList.add(LocalMediaManager.SPOTIFY_PACKAGE_NAME);
        arrayList.add(SpotifyManager.SPOTIFY_APK_PACKAGE_NAME);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("com.android.car.media");
        arrayList2.add(LocalMediaManager.AMAZON_PACKAGE_NAME);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(LocalMediaManager.KARAOKE_PACKAGE_NAME);
        if (!RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue() && !RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
            if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || ((LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY) && BydVoiceApplication.isActivityAlreadyRun("com.vivid.spotify.MainActivity")) || BydVoiceApplication.isApplicationForeground(arrayList))) {
                setPlayModeResult.setMediaProvider(MediaProviderEnum.SPOTIFY);
                SpotifySetPlayMode(setPlayModeParam, iResponseSender, setPlayModeResult);
                return;
            }
            if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || BydVoiceApplication.isApplicationForeground(arrayList2)) {
                setPlayModeResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                AmazonSetPlayMode(setPlayModeParam, iResponseSender, setPlayModeResult);
                return;
            }
            if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.KARAOKE) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.KARAOKE) || BydVoiceApplication.isApplicationForeground(arrayList3)) {
                setPlayModeResult.setMediaProvider(MediaProviderEnum.KARAOKE);
                KaraokeSetPlayMode(setPlayModeParam, iResponseSender, setPlayModeResult);
                return;
            }
            if (OnlineMediaHelper.get().OnlineApplicationForegroundFor() != "") {
                OnlineMediaHelper.get().setPlayMode(OnlineMediaHelper.get().OnlineApplicationForegroundFor(), setPlayModeParam, iResponseSender, setPlayModeResult);
                return;
            }
            if (OnlineMediaHelper.get().OnlineGetCurrentPackage() != "") {
                OnlineMediaHelper.get().setPlayMode(OnlineMediaHelper.get().OnlineGetCurrentPackage(), setPlayModeParam, iResponseSender, setPlayModeResult);
                return;
            }
            if (OnlineMediaHelper.get().OnlineGetRecentlyPackage() != "") {
                OnlineMediaHelper.get().setPlayMode(OnlineMediaHelper.get().OnlineGetRecentlyPackage(), setPlayModeParam, iResponseSender, setPlayModeResult);
                return;
            }
            if (LocalMediaManager.get().getCurrentMediaProvider().equals("BLUETOOTH")) {
                RLog.i(TAG, "setPlayMode: bluetooth UNSUPPORTED ");
                setPlayModeResult.setMediaProvider("BLUETOOTH");
                iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
                return;
            }
            if (!AudioHelper.getInstance(BydVoiceApplication.getContext()).IfMusicWorking().booleanValue()) {
                RLog.i(TAG, "setPlayMode: no music  MEDIA_NOT_FOUND ");
                setPlayModeResult.setMediaProvider(MediaProviderEnum.NONE);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【MEDIA_NOT_FOUND】");
                return;
            }
            if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.NONE)) {
                RLog.i(TAG, "setPlayMode: MEDIA_NOT_FOUND");
                setPlayModeResult.setMediaProvider(MediaProviderEnum.NONE);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【MEDIA_NOT_FOUND】");
                return;
            }
            Map<Integer, Boolean> mapIfSourceHasSong = AudioHelper.getInstance(BydVoiceApplication.getContext()).IfSourceHasSong();
            RLog.i(TAG, "setPlayMode SOURCE_LOCAL==" + mapIfSourceHasSong.get(0));
            RLog.i(TAG, "setPlayMode SOURCE_USB==" + mapIfSourceHasSong.get(1));
            RLog.i(TAG, "setPlayMode SOURCE_SD==" + mapIfSourceHasSong.get(2));
            AudioHelper.getInstance(BydVoiceApplication.getContext()).setPlayModeReturnMediaNotFound(setPlayModeParam, mapIfSourceHasSong, iResponseSender, setPlayModeResult);
            return;
        }
        RLog.i(TAG, "setPlayMode: Radio UNSUPPORTED ");
        setPlayModeResult.setMediaProvider(MediaProviderEnum.NONE);
        iResponseSender.send(new Response("UNSUPPORTED", setPlayModeResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【UNSUPPORTED】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void controlPlaylist(ControlPlaylistParam controlPlaylistParam, IResponseSender<BaseResult> iResponseSender) {
        String str;
        RLog.i(TAG, "controlPlaylist ------");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】");
        String packetAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio();
        RLog.i(TAG, "controlPlaylist ------" + packetAudio);
        ArrayList arrayList = new ArrayList();
        arrayList.add(LocalMediaManager.SPOTIFY_PACKAGE_NAME);
        arrayList.add("com.vivid.music.byd");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("com.android.car.media");
        arrayList2.add(LocalMediaManager.LOCALMEDIA_PACKAGE_NAME);
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add("com.android.car.media");
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(LocalMediaManager.KARAOKE_PACKAGE_NAME);
        if (BydVoiceApplication.isApplicationForeground(arrayList4) || LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.KARAOKE) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.KARAOKE)) {
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
            return;
        }
        if (!RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue() && !RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue() && (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || ((LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY) && BydVoiceApplication.isActivityAlreadyRun("com.vivid.spotify.MainActivity")) || BydVoiceApplication.isApplicationForeground(arrayList)))) {
            SpotifyControlPlayList(controlPlaylistParam, iResponseSender);
            return;
        }
        if (OnlineMediaHelper.get().OnlineApplicationForegroundFor() != "") {
            OnlineMediaHelper.get().Controlplaylist(OnlineMediaHelper.get().OnlineApplicationForegroundFor(), controlPlaylistParam, iResponseSender);
            return;
        }
        if (OnlineMediaHelper.get().OnlineGetCurrentPackage() != "") {
            OnlineMediaHelper.get().Controlplaylist(OnlineMediaHelper.get().OnlineGetCurrentPackage(), controlPlaylistParam, iResponseSender);
            return;
        }
        if (OnlineMediaHelper.get().OnlineGetRecentlyPackage() != "") {
            OnlineMediaHelper.get().Controlplaylist(OnlineMediaHelper.get().OnlineGetRecentlyPackage(), controlPlaylistParam, iResponseSender);
            return;
        }
        if (TextUtils.isEmpty(packetAudio) && !BydVoiceApplication.isApplicationForeground(arrayList) && !BydVoiceApplication.isApplicationForeground(arrayList2) && !ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.AMAZON_PACKAGE_NAME)) {
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【MEDIA_NOT_FOUND】");
            return;
        }
        if (!BydVoiceApplication.isApplicationForeground(arrayList3) && !packetAudio.equals(LocalMediaManager.AMAZON_PACKAGE_NAME)) {
            str = "open music play list source type ==";
        } else {
            if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
                RLog.e(TAG, "There is no Internet Connection");
                iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【NO_INTERNET】");
                return;
            }
            str = "open music play list source type ==";
            if ("ON".equals(controlPlaylistParam.getAction())) {
                try {
                    if (AmazonManager.get().isPlayListPageOpen()) {
                        if (!BydVoiceApplication.isApplicationForeground(arrayList3)) {
                            AmazonManager.get().openAmazon();
                        }
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        RLog.i(TAG, "openMusicList   ALREADY_EXPECTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【ALREADY_EXPECTED】");
                        return;
                    }
                    int value = AmazonManager.get().openPlayListPage().getValue();
                    if (value == 0) {
                        iResponseSender.send(new Response("OK"));
                        RLog.i(TAG, "openMusicList   OK");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【OK】");
                    } else if (value == 1002) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(TAG, "openMusicList   UNSUPPORTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【ERROR】");
                    }
                } catch (Exception unused) {
                    RLog.i(TAG, "openMusicList 失败");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【openMusicList】,result【ERROR】");
                }
            } else {
                try {
                    if (!AmazonManager.get().isPlayListPageOpen()) {
                        iResponseSender.send(new Response("ALREADY_EXPECTED"));
                        RLog.i(TAG, "closeMusicList   ALREADY_EXPECTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【ALREADY_EXPECTED】");
                        return;
                    }
                    int value2 = AmazonManager.get().closePlayListPage().getValue();
                    if (value2 == 0) {
                        iResponseSender.send(new Response("OK"));
                        RLog.i(TAG, "closeMusicList   OK");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【OK】");
                    } else if (value2 == 1002) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(TAG, "closeMusicList   UNSUPPORTED");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
                    } else {
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【ERROR】");
                    }
                } catch (Exception unused2) {
                    RLog.i(TAG, "closeMusicList 失败");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【closeMusicList】,result【ERROR】");
                }
            }
        }
        if (packetAudio.equals(LocalMediaManager.SPOTIFY_PACKAGE_NAME)) {
            SpotifyControlPlayList(controlPlaylistParam, iResponseSender);
            return;
        }
        if (AudioHelper.getInstance(BydVoiceApplication.getContext()).IfBTMusicWorking().booleanValue()) {
            RLog.d(TAG, "bluetooth return unsupported");
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
            return;
        }
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue()) {
            if ("ON".equals(controlPlaylistParam.getAction())) {
                try {
                    RadioHelper.getInstance(BydVoiceApplication.getContext()).OpenStationListHelper(iResponseSender);
                    return;
                } catch (Exception unused3) {
                    RLog.d(TAG, "openMusicList 失败");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【ERROR】");
                    return;
                }
            }
            RLog.d(TAG, "closeMusicList return unsupported");
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
            return;
        }
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
            if ("ON".equals(controlPlaylistParam.getAction())) {
                try {
                    DABHelper.getInstance(BydVoiceApplication.getContext()).OpenDABStationListHelper(iResponseSender);
                    return;
                } catch (Exception unused4) {
                    RLog.d(TAG, "openMusicList 失败");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【ERROR】");
                    return;
                }
            }
            RLog.d(TAG, "closeMusicList return unsupported");
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
            return;
        }
        if (AudioHelper.getInstance(BydVoiceApplication.getContext()).IfMusicWorking().booleanValue()) {
            if ("ON".equals(controlPlaylistParam.getAction())) {
                try {
                    int localMusicType = AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType();
                    RLog.d(TAG, str + localMusicType);
                    if (localMusicType != 0 && localMusicType != 2 && localMusicType != 1) {
                        iResponseSender.send(new Response("UNSUPPORTED"));
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
                        return;
                    }
                    AudioHelper.getInstance(BydVoiceApplication.getContext()).OpenMusicListHelper(iResponseSender, localMusicType);
                    return;
                } catch (Exception unused5) {
                    RLog.d(TAG, "getMusicSource 失败");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【ERROR】");
                    return;
                }
            }
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
            return;
        }
        iResponseSender.send(new Response("UNSUPPORTED"));
        RLog.i(TAG, "controlPlaylist ------ UNSUPPORTED");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void controlFavoritelist(ControlFavoritelistParam controlFavoritelistParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(TAG, "controlFavoritelist ------");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlFavoritelist】");
        iResponseSender.send(new Response("UNSUPPORTED"));
        RLog.i(TAG, "controlPlaylist ------ UNSUPPORTED");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlPlaylist】,result【UNSUPPORTED】");
    }

    private void SpotifyControlPlayList(ControlPlaylistParam controlPlaylistParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            RLog.i(TAG, "SpotifyControlPlayList: controlPlaylistParam==" + controlPlaylistParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        processSpotify();
        if (!SpotifyManager.get().isConnected()) {
            iResponseSender.send(new Response(ResultCodeEnum.SPOTIFY_UNAVAILABLE));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【SPOTIFY_UNAVAILABLE】");
            return;
        }
        if ("ON".equals(controlPlaylistParam.getAction())) {
            try {
                SpotifyManager.get().openPlayListPage();
            } catch (Exception unused) {
                RLog.i(TAG, "openMusicList spotify 失败");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【openMusicList】,result【ERROR】");
                return;
            }
        } else {
            try {
                SpotifyManager.get().closePlayListPage();
            } catch (Exception unused2) {
                RLog.i(TAG, "closeMusicList spotify 失败");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【closeMusicList】,result【ERROR】");
                return;
            }
        }
        RLog.e(TAG, "SpotifyManager.getResponseCode : " + SpotifyManager.getResponseCode());
        RLog.e(TAG, "SpotifyManager.getResponseCode name : " + SpotifyManager.getResponseCode().name());
        if (SpotifyManager.getResponseCode() != ResponseCode.OK) {
            RLog.e(TAG, "SpotifyManager.getResponseCode != OK : " + SpotifyManager.getResponseCode().name());
            if (SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【SpotifyControlPlayList】,result【NOT_LOGGED_IN】");
                return;
            } else if (SpotifyManager.getResponseCode() == ResponseCode.MODE_UNSUPPORT) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【SpotifyControlPlayList】,result【UNSUPPORTED】");
                return;
            } else if (SpotifyManager.getResponseCode() == ResponseCode.UNSUPPORT_OPERATION) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【SpotifyControlPlayList】,result【UNSUPPORTED】");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【SpotifyControlPlayList】,result【MEDIA_NOT_FOUND】");
                return;
            }
        }
        RLog.e(TAG, "SpotifySetPlayMode processSpotify 2");
        iResponseSender.send(new Response("OK"));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【SpotifyControlPlayList】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void setVolume(SetVolumeParam setVolumeParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setVolume】");
            RLog.i(TAG, "setVolume: setVolumeParam==" + setVolumeParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int i = 1;
        if (!setVolumeParam.getStreamType().equals("MEDIA") && !setVolumeParam.getStreamType().equals("BLUETOOTH") && !setVolumeParam.getStreamType().equals(StreamTypeEnum.BLUETOOTH_MUSIC)) {
            if (setVolumeParam.getStreamType().equals(StreamTypeEnum.NAVIGATION)) {
                i = 12;
            } else if (setVolumeParam.getStreamType().equals(StreamTypeEnum.VOICE_ASSISTANT)) {
                i = 16;
            } else if (setVolumeParam.getStreamType().equals(StreamTypeEnum.BLUETOOTH_PHONE)) {
                i = 2;
            }
        }
        RLog.i(TAG, "setVolume stream_Type==: " + i);
        int maxVolume = AudioHelper.getInstance(BydVoiceApplication.getContext()).getMaxVolume(i);
        int minVolume = AudioHelper.getInstance(BydVoiceApplication.getContext()).getMinVolume(i);
        if (setVolumeParam.getValue() > maxVolume) {
            AudioHelper.getInstance(BydVoiceApplication.getContext()).setVolume(maxVolume, i);
            iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MAX));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setVolume】,result【ALREADY_MAX】");
        } else if (setVolumeParam.getValue() <= minVolume) {
            AudioHelper.getInstance(BydVoiceApplication.getContext()).setVolume(minVolume, i);
            iResponseSender.send(new Response(ResultCodeEnum.ALREADY_MIN));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setVolume】,result【ALREADY_MIN】");
        } else {
            AudioHelper.getInstance(BydVoiceApplication.getContext()).setVolume(setVolumeParam.getValue(), i);
            iResponseSender.send(new Response("OK"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setVolume】,result【OK】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void controlVolume(ControlVolumeParam controlVolumeParam, IResponseSender<BaseResult> iResponseSender) {
        try {
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlVolume】");
            RLog.i(TAG, "controlVolume:" + controlVolumeParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        AudioHelper.getInstance(BydVoiceApplication.getContext()).domaincontrolVolume(controlVolumeParam, iResponseSender);
    }

    public void SpotifyFavoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<FavoriteControlResult> iResponseSender) {
        try {
            RLog.i(TAG, "SpotifyFavoriteControl: favoriteControlParam==" + favoriteControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
        favoriteControlResult.setMediaProvider(MediaProviderEnum.SPOTIFY);
        processSpotify();
        if (!SpotifyManager.get().isConnected()) {
            iResponseSender.send(new Response(ResultCodeEnum.SPOTIFY_UNAVAILABLE, favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【SPOTIFY_UNAVAILABLE】");
            return;
        }
        if ("ADD".equals(favoriteControlParam.getControlType())) {
            RLog.i(TAG, "SpotifyManager: addFavorite");
            SpotifyManager.get().addFavorite();
        } else {
            RLog.i(TAG, "SpotifyManager: addFavorite");
            SpotifyManager.get().removeFavorite();
        }
        if (SpotifyManager.getResponseCode() != ResponseCode.OK) {
            RLog.e(TAG, "SpotifyManager.getResponseCode != OK : " + SpotifyManager.getResponseCode().name());
            if (SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【NOT_LOGGED_IN】");
                return;
            }
            if (SpotifyManager.getResponseCode() == ResponseCode.MODE_UNSUPPORT || SpotifyManager.getResponseCode() == ResponseCode.MEDIA_UNSUPPORT_FAVORIT) {
                iResponseSender.send(new Response("UNSUPPORTED", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【UNSUPPORTED】");
                return;
            } else if (SpotifyManager.getResponseCode() == ResponseCode.MEDIA_IS_COLLECTED || SpotifyManager.getResponseCode() == ResponseCode.MEDIA_IS_NOT_COLLECTED) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【MEDIA_NOT_FOUND】");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【MEDIA_NOT_FOUND】");
                return;
            }
        }
        RLog.e(TAG, "processSpotify 2");
        iResponseSender.send(new Response("OK", favoriteControlResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【OK】");
    }

    public void AmazonFavoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<FavoriteControlResult> iResponseSender) {
        int value;
        try {
            RLog.i(TAG, "AmazonFavoriteControl: favoriteControlParam==" + favoriteControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
        favoriteControlResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
        processAmazon();
        if (!AmazonManager.get().isConnected()) {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【AMAZON_UNAVAILABLE】");
            return;
        }
        if ("ADD".equals(favoriteControlParam.getControlType())) {
            RLog.i(TAG, "AmazonManager: addFavorite");
            value = AmazonManager.get().addFavorite().getValue();
        } else {
            RLog.i(TAG, "AmazonManager: removeFavorite");
            value = AmazonManager.get().removeFavorite().getValue();
        }
        if (value != 0) {
            RLog.e(TAG, "AmazonManager.getResponseCode != OK : " + value);
            if (value == 1001) {
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【NOT_LOGGED_IN】");
                return;
            }
            if (value == 1002) {
                iResponseSender.send(new Response("UNSUPPORTED", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【UNSUPPORTED】");
                return;
            } else if (value == 1010 || value == 1011) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ALREADY_EXPECTED】");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【MEDIA_NOT_FOUND】");
                return;
            }
        }
        RLog.e(TAG, "processAmazon 2");
        iResponseSender.send(new Response("OK", favoriteControlResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void favoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<FavoriteControlResult> iResponseSender) {
        RLog.i(TAG, "favoriteControl");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】");
        ArrayList arrayList = new ArrayList();
        arrayList.add(LocalMediaManager.SPOTIFY_PACKAGE_NAME);
        arrayList.add(SpotifyManager.SPOTIFY_APK_PACKAGE_NAME);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(LocalMediaManager.KARAOKE_PACKAGE_NAME);
        if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SPOTIFY) || ((LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY) && BydVoiceApplication.isActivityAlreadyRun("com.vivid.spotify.MainActivity")) || BydVoiceApplication.isApplicationForeground(arrayList))) {
            SpotifyFavoriteControl(favoriteControlParam, iResponseSender);
            return;
        }
        if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC)) {
            AmazonFavoriteControl(favoriteControlParam, iResponseSender);
            return;
        }
        if (BydVoiceApplication.isApplicationForeground(arrayList2) || LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.KARAOKE) || LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.KARAOKE)) {
            FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
            favoriteControlResult.setMediaProvider(MediaProviderEnum.KARAOKE);
            iResponseSender.send(new Response("UNSUPPORTED", favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【UNSUPPORTED】");
            return;
        }
        if (OnlineMediaHelper.get().OnlineApplicationForegroundFor() != "") {
            OnlineMediaHelper.get().favoriteControl(OnlineMediaHelper.get().OnlineApplicationForegroundFor(), favoriteControlParam, iResponseSender);
            return;
        }
        if (OnlineMediaHelper.get().OnlineGetCurrentPackage() != "") {
            OnlineMediaHelper.get().favoriteControl(OnlineMediaHelper.get().OnlineGetCurrentPackage(), favoriteControlParam, iResponseSender);
            return;
        }
        if (OnlineMediaHelper.get().OnlineGetRecentlyPackage() != "") {
            OnlineMediaHelper.get().favoriteControl(OnlineMediaHelper.get().OnlineGetRecentlyPackage(), favoriteControlParam, iResponseSender);
            return;
        }
        if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.NONE)) {
            FavoriteControlResult favoriteControlResult2 = new FavoriteControlResult();
            favoriteControlResult2.setMediaProvider(MediaProviderEnum.LOCAL);
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, favoriteControlResult2));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【MEDIA_NOT_FOUND】");
            return;
        }
        if (LocalMediaManager.get().getCurrentMediaProvider().equals("BLUETOOTH")) {
            FavoriteControlResult favoriteControlResult3 = new FavoriteControlResult();
            favoriteControlResult3.setMediaProvider("BLUETOOTH");
            iResponseSender.send(new Response("UNSUPPORTED", favoriteControlResult3));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【UNSUPPORTED】");
            return;
        }
        try {
            RLog.i(TAG, "favoriteControl: favoriteControlParam==" + favoriteControlParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (MediaTypeEnum.RADIO.equals(favoriteControlParam.getMediaType())) {
            if ("ADD".equals(favoriteControlParam.getControlType())) {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).ControlfavoriteForAudioHelper(64, iResponseSender);
                return;
            } else {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).ControlfavoriteForAudioHelper(65, iResponseSender);
                return;
            }
        }
        if ("SONG".equals(favoriteControlParam.getMediaType())) {
            if ("ADD".equals(favoriteControlParam.getControlType())) {
                AudioHelper.getInstance(BydVoiceApplication.getContext()).ControlfavoriteAudioHelper(64, iResponseSender);
            } else {
                AudioHelper.getInstance(BydVoiceApplication.getContext()).ControlfavoriteAudioHelper(65, iResponseSender);
            }
        }
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void isBTConnected(IResponseSender<IsBTConnectedResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【isBTConnected】");
        RLog.i(TAG, "isBTConnected BTConnected==" + PhoneHelper.getInstance().getConnectionState());
        IsBTConnectedResult isBTConnectedResult = new IsBTConnectedResult();
        isBTConnectedResult.setIsConnected(PhoneHelper.getInstance().getConnectionState());
        iResponseSender.send(new Response("OK", isBTConnectedResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【isBTConnected】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void getGlobalMuteState(IResponseSender<GetGlobalMuteStateResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getGlobalMuteState】");
        GetGlobalMuteStateResult getGlobalMuteStateResult = new GetGlobalMuteStateResult();
        if (AudioHelper.getInstance(BydVoiceApplication.getContext()).getMuteState()) {
            getGlobalMuteStateResult.setGlobalMuteState("ON");
        } else {
            getGlobalMuteStateResult.setGlobalMuteState("OFF");
        }
        iResponseSender.send(new Response("OK", getGlobalMuteStateResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getGlobalMuteState】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void getCurrentStreamType(IResponseSender<GetCurrentStreamTypeResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getCurrentStreamType】");
        GetCurrentStreamTypeResult getCurrentStreamTypeResult = new GetCurrentStreamTypeResult();
        getCurrentStreamTypeResult.setStreamType("MEDIA");
        RLog.i(TAG, "getCurrentStreamType getCurrentStreamTypeResult stream_Type final MEDIA==" + getCurrentStreamTypeResult.getStreamType());
        iResponseSender.send(new Response("OK", getCurrentStreamTypeResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getCurrentStreamType】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void isMICConnected(IResponseSender<IsMICConnectedResult> iResponseSender) {
        if (!KaraokeManager.get().isBlConnected()) {
            KaraokeManager.get().init();
        }
        IsMICConnectedResult isMICConnectedResult = new IsMICConnectedResult();
        isMICConnectedResult.setIsConnected(KaraokeManager.get().isMICConnected());
        iResponseSender.send(new Response("OK", isMICConnectedResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【isMICConnected】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void getMICVolume(IResponseSender<GetMICVolumeResult> iResponseSender) {
        if (!KaraokeManager.get().isBlConnected()) {
            KaraokeManager.get().init();
        }
        GetMICVolumeResult getMICVolumeResult = new GetMICVolumeResult();
        int curMICVolume = KaraokeManager.get().getCurMICVolume();
        if (curMICVolume != -100) {
            getMICVolumeResult.setCurrentValue(curMICVolume);
            iResponseSender.send(new Response("OK", getMICVolumeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMICVolume】,result【OK】");
        } else {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【getMICVolume】,result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.audiocontrol.AbsAudioControlDomain
    protected void setMICVolume(SetMICVolumeParam setMICVolumeParam, IResponseSender<BaseResult> iResponseSender) {
        if (!KaraokeManager.get().isBlConnected()) {
            KaraokeManager.get().init();
        }
        if (KaraokeManager.get().setMicVolume(setMICVolumeParam.getValue())) {
            iResponseSender.send(new Response("OK"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setMICVolume】,result【OK】");
        } else {
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setMICVolume】,result【ERROR】");
        }
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【activate】");
        RLog.i(TAG, "activate:");
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【deactivate】");
        RLog.i(TAG, "deactivate:");
    }
}
