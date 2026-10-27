package com.byd.vrassistant.customizedimpl;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.media.AbsMediaDomain;
import cerence.ark.assistant.controller.domain.media.param.IsMediaProviderRunningParam;
import cerence.ark.assistant.controller.domain.media.param.PlayMusicParam;
import cerence.ark.assistant.controller.domain.media.param.SearchMusicParam;
import cerence.ark.assistant.controller.domain.media.result.IsMediaProviderRunningResult;
import cerence.ark.assistant.controller.domain.media.result.SearchMusicResult;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.struct.MusicInfo;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.AudioSdk;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.conversation.onlinemedia.OnlineMediaHelper;
import com.byd.vrassistant.customizedimpl.CustomSystemDomain;
import com.byd.vrassistant.manager.AmazonManager;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.SpotifyManager;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.receiver.BluetoothReceiver;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.InternetConnectionJudge;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.google.common.base.Ascii;
import com.vivid.sdk.music.ResponseCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class CustomMusicDomain extends AbsMediaDomain {
    public static String TAG = "NEU_CustomMusicDomain Ver=S.8.12.4";
    private Handler mainHandler;
    CustomSystemDomain.TestLogCat testLogCat;

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
    }

    protected CustomMusicDomain(Dhi dhi) {
        super(dhi);
        this.testLogCat = new CustomSystemDomain.TestLogCat("music");
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    protected <T extends BaseResult> void processSpotify(IResponseSender<T> iResponseSender, T t, boolean z, String str) {
        RLog.e(TAG, "processSpotify");
        if (SpotifyManager.get().isConnected()) {
            SpotifyManager.get().saveCurrentRunningState();
            if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
                RLog.e(TAG, "There is no Internet Connection");
                iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, t));
                return;
            }
            if (!SystemSettingManager.get().isSpotifySupportRegion()) {
                RLog.e(TAG, "the region can not support spotify");
                iResponseSender.send(new Response(ResultCodeEnum.REGION_UNAVAILABLE, t));
                return;
            }
            SpotifyManager.get().openSpotify();
            if (z) {
                if (SpotifyManager.get().getState() == SpotifyManager.STATE_PLAYING) {
                    this.testLogCat.setResponse("ALREADY_EXPECTED");
                    iResponseSender.send(new Response("ALREADY_EXPECTED", t));
                    return;
                }
                SpotifyManager.get().play();
                if (SpotifyManager.getResponseCode() != ResponseCode.OK) {
                    RLog.e(TAG, "processSpotify play error");
                    if (SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                        RLog.e(TAG, "processSpotify play error:NOT_LOGGED_IN");
                        iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, t));
                        return;
                    } else {
                        RLog.e(TAG, "processSpotify play error:");
                        iResponseSender.send(new Response(ResultCodeEnum.ERROR, t));
                        return;
                    }
                }
            }
        } else {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            SpotifyManager.get().init(countDownLatch);
            try {
                countDownLatch.await(6L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!SpotifyManager.get().isConnected()) {
                RLog.e(TAG, "processSpotify 1");
                iResponseSender.send(new Response(ResultCodeEnum.SPOTIFY_UNAVAILABLE, t));
                return;
            }
            RLog.e(TAG, "processSpotify 2");
            if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
                RLog.e(TAG, "There is no Internet Connection");
                iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, t));
                return;
            }
            if (!SystemSettingManager.get().isSpotifySupportRegion()) {
                RLog.e(TAG, "the region can not support spotify");
                iResponseSender.send(new Response(ResultCodeEnum.REGION_UNAVAILABLE, t));
                return;
            }
            SpotifyManager.get().openSpotify();
            if (z && SpotifyManager.get().getState() == SpotifyManager.STATE_PLAYING) {
                this.testLogCat.setResponse("ALREADY_EXPECTED");
                iResponseSender.send(new Response("ALREADY_EXPECTED", t));
                return;
            }
            SpotifyManager.get().play();
            if (SpotifyManager.getResponseCode() != ResponseCode.OK) {
                if (SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                    iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, t));
                    return;
                } else {
                    RLog.e(TAG, "processSpotify play error:");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR, t));
                    return;
                }
            }
        }
        RLog.e(TAG, "processSpotify 3 :" + str);
        iResponseSender.send(new Response(str, t));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // cerence.ark.assistant.controller.domain.media.AbsMediaDomain
    protected void searchMusic(SearchMusicParam searchMusicParam, IResponseSender<SearchMusicResult> iResponseSender) {
        String str;
        String str2;
        String str3;
        Response response;
        byte b;
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        SearchMusicResult searchMusicResult = new SearchMusicResult();
        ArrayList arrayList = new ArrayList();
        arrayList.add(LocalMediaManager.KARAOKE_PACKAGE_NAME);
        if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.KARAOKE) || BydVoiceApplication.isApplicationForeground(arrayList)) {
            searchMusicResult.setMediaProvider(MediaProviderEnum.KARAOKE);
            iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
            this.testLogCat.setResponse("UNSUPPORTED");
            return;
        }
        try {
            RLog.e(TAG, "searchMusic:" + searchMusicParam.toJsonString());
            EventBus.getDefault().post("searchMusic: " + searchMusicParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String upperCase = searchMusicParam.getSearchCriteria().getMediaProvider().toUpperCase(Locale.ROOT);
        final List<MusicInfo> usbMedia = LocalMediaManager.get().getUsbMedia();
        final List<MusicInfo> sdcardMedia = LocalMediaManager.get().getSdcardMedia();
        List<MusicInfo> localMedia = LocalMediaManager.get().getLocalMedia();
        RLog.e(TAG, "usbMusicInfos.size : " + usbMedia.size());
        RLog.e(TAG, "sdMusicInfos.size : " + sdcardMedia.size());
        RLog.e(TAG, "localMusicInfos.size : " + localMedia.size());
        BluetoothReceiver.BT_CONNECTED = PhoneHelper.getInstance().getConnectionState();
        upperCase.hashCode();
        byte b2 = -1;
        switch (upperCase.hashCode()) {
            case -1853887263:
                if (upperCase.equals(MediaProviderEnum.SDCARD)) {
                    b = 0;
                    b2 = b;
                }
                break;
            case -1809226979:
                if (upperCase.equals(MediaProviderEnum.TUNEIN)) {
                    b2 = 1;
                }
                break;
            case -1280740710:
                if (upperCase.equals(MediaProviderEnum.SPOTIFY)) {
                    b = 2;
                    b2 = b;
                }
                break;
            case -1229078518:
                if (upperCase.equals(MediaProviderEnum.AMAZON_MUSIC)) {
                    b = 3;
                    b2 = b;
                }
                break;
            case -1165764341:
                if (upperCase.equals(MediaProviderEnum.POCKET_CASTS)) {
                    b = 4;
                    b2 = b;
                }
                break;
            case -694160498:
                if (upperCase.equals("MEMORY_CARD")) {
                    b = 5;
                    b2 = b;
                }
                break;
            case -290115045:
                if (upperCase.equals(MediaProviderEnum.BLOOMBERG)) {
                    b = 6;
                    b2 = b;
                }
                break;
            case -218062396:
                if (upperCase.equals(MediaProviderEnum.KARAOKE)) {
                    b = 7;
                    b2 = b;
                }
                break;
            case 69705:
                if (upperCase.equals(MediaProviderEnum.FLO)) {
                    b = 8;
                    b2 = b;
                }
                break;
            case 84324:
                if (upperCase.equals(MediaProviderEnum.USB)) {
                    b = 9;
                    b2 = b;
                }
                break;
            case 56714978:
                if (upperCase.equals(MediaProviderEnum.AUDIBLE)) {
                    b = 10;
                    b2 = b;
                }
                break;
            case 72427900:
                if (upperCase.equals(MediaProviderEnum.LIBBY)) {
                    b = 11;
                    b2 = b;
                }
                break;
            case 72607563:
                if (upperCase.equals(MediaProviderEnum.LOCAL)) {
                    b = 12;
                    b2 = b;
                }
                break;
            case 73242259:
                if (upperCase.equals(MediaProviderEnum.MELON)) {
                    b = Ascii.CR;
                    b2 = b;
                }
                break;
            case 79817946:
                if (upperCase.equals(MediaProviderEnum.TIDAL)) {
                    b = Ascii.SO;
                    b2 = b;
                }
                break;
            case 104154238:
                if (upperCase.equals(MediaProviderEnum.IHEARTRADIO)) {
                    b = Ascii.SI;
                    b2 = b;
                }
                break;
            case 460509838:
                if (upperCase.equals("BLUETOOTH")) {
                    b = 16;
                    b2 = b;
                }
                break;
            case 915963460:
                if (upperCase.equals(MediaProviderEnum.AUDIALS_PLAY)) {
                    b = 17;
                    b2 = b;
                }
                break;
            case 1817075279:
                if (upperCase.equals(MediaProviderEnum.RADIOLINE)) {
                    b = Ascii.DC2;
                    b2 = b;
                }
                break;
            case 2012649507:
                if (upperCase.equals(MediaProviderEnum.DEEZER)) {
                    b = 19;
                    b2 = b;
                }
                break;
        }
        switch (b2) {
            case 0:
            case 5:
                RLog.e(TAG, "open SDCARD music:");
                String str4 = SystemPropertiesUtils.get("sys.byd.mSdcardUuid", "defaultvalue");
                RLog.e(TAG, "open SDCARD music usb_uuid :" + str4);
                if (str4 == null || str4.isEmpty() || str4.equals("defaultvalue") || str4.equals("null")) {
                    RLog.e(TAG, "SDCARD didn't connected yet");
                    searchMusicResult.setMediaProvider(MediaProviderEnum.SDCARD);
                    iResponseSender.send(new Response(ResultCodeEnum.MEDIA_PROVIDER_UNAVAILABLE, searchMusicResult));
                    this.testLogCat.setResponse(ResultCodeEnum.MEDIA_PROVIDER_UNAVAILABLE);
                    return;
                }
                if (sdcardMedia != null && sdcardMedia.size() > 0) {
                    RLog.e(TAG, "sdMusicInfos size:" + sdcardMedia.size());
                    searchMusicResult.setMusicList(sdcardMedia);
                    searchMusicResult.setMusicType("SONG");
                    searchMusicResult.setMediaProvider(MediaProviderEnum.SDCARD);
                    this.testLogCat.setResponse("OK");
                    iResponseSender.send(new Response("OK", searchMusicResult));
                    return;
                }
                RLog.e(TAG, "sdMusicInfos size:0");
                searchMusicResult.setMediaProvider(MediaProviderEnum.SDCARD);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                this.testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                return;
            case 1:
            case 4:
            case 6:
            case 8:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 17:
            case 18:
            case 19:
                if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), OnlineMediaHelper.get().getPackageNameFromProvider(upperCase))) {
                    searchMusicResult.setMediaProvider(upperCase);
                    searchMusicResult.setMusicType("SONG");
                    ArrayList arrayList2 = new ArrayList();
                    MusicInfo musicInfo = new MusicInfo();
                    musicInfo.setSongName(upperCase + "music");
                    musicInfo.setId("0xffffffff");
                    arrayList2.add(musicInfo);
                    searchMusicResult.setMusicList(arrayList2);
                    iResponseSender.send(new Response("OK", searchMusicResult));
                    this.testLogCat.setResponse("OK");
                    return;
                }
                RLog.e(TAG, "onlinemedia size:0");
                searchMusicResult.setMediaProvider(upperCase);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                this.testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                return;
            case 2:
                RLog.e(TAG, "open SPOTIFY music:");
                if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), SpotifyManager.SPOTIFY_APK_PACKAGE_NAME)) {
                    searchMusicResult.setMediaProvider(MediaProviderEnum.SPOTIFY);
                    searchMusicResult.setMusicType("SONG");
                    ArrayList arrayList3 = new ArrayList();
                    MusicInfo musicInfo2 = new MusicInfo();
                    musicInfo2.setSongName("spotify music");
                    musicInfo2.setId("0xffffffff");
                    arrayList3.add(musicInfo2);
                    searchMusicResult.setMusicList(arrayList3);
                    iResponseSender.send(new Response("OK", searchMusicResult));
                    this.testLogCat.setResponse("OK");
                    return;
                }
                RLog.e(TAG, "sdMusicInfos size:0");
                searchMusicResult.setMediaProvider(MediaProviderEnum.SPOTIFY);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                this.testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                return;
            case 3:
                RLog.e(TAG, "open AMAZON_MUSIC music:");
                if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.AMAZON_PACKAGE_NAME)) {
                    searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                    searchMusicResult.setMusicType("SONG");
                    ArrayList arrayList4 = new ArrayList();
                    MusicInfo musicInfo3 = new MusicInfo();
                    musicInfo3.setSongName("amazon music");
                    musicInfo3.setId("0xffffffff");
                    arrayList4.add(musicInfo3);
                    searchMusicResult.setMusicList(arrayList4);
                    iResponseSender.send(new Response("OK", searchMusicResult));
                    this.testLogCat.setResponse("OK");
                    return;
                }
                RLog.e(TAG, "amazon_musicinfos size:0");
                searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                this.testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                return;
            case 7:
                RLog.e(TAG, "open KARAOKE music:");
                if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.KARAOKE_PACKAGE_NAME)) {
                    searchMusicResult.setMediaProvider(MediaProviderEnum.KARAOKE);
                    searchMusicResult.setMusicType("SONG");
                    ArrayList arrayList5 = new ArrayList();
                    MusicInfo musicInfo4 = new MusicInfo();
                    musicInfo4.setSongName("karaoke music");
                    musicInfo4.setId("0xffffffff");
                    arrayList5.add(musicInfo4);
                    searchMusicResult.setMusicList(arrayList5);
                    iResponseSender.send(new Response("OK", searchMusicResult));
                    this.testLogCat.setResponse("OK");
                    return;
                }
                RLog.e(TAG, "karaoke_musicinfos size:0");
                searchMusicResult.setMediaProvider(MediaProviderEnum.KARAOKE);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                this.testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                return;
            case 9:
                RLog.e(TAG, "open USB music:");
                String str5 = SystemPropertiesUtils.get("sys.byd.mUsbotgUuid", "defaultvalue");
                RLog.e(TAG, "open USB music usb_uuid :" + str5);
                if (str5 == null || str5.isEmpty() || str5.equals("defaultvalue") || str5.equals("null")) {
                    RLog.e(TAG, "USB didn't connected yet");
                    searchMusicResult.setMediaProvider(MediaProviderEnum.USB);
                    iResponseSender.send(new Response(ResultCodeEnum.MEDIA_PROVIDER_UNAVAILABLE, searchMusicResult));
                    this.testLogCat.setResponse(ResultCodeEnum.MEDIA_PROVIDER_UNAVAILABLE);
                    return;
                }
                if (usbMedia != null && usbMedia.size() > 0) {
                    RLog.e(TAG, "usbMusicInfos size:" + usbMedia.size());
                    searchMusicResult.setMusicList(usbMedia);
                    searchMusicResult.setMusicType("SONG");
                    searchMusicResult.setMediaProvider(MediaProviderEnum.USB);
                    this.testLogCat.setResponse("OK");
                    iResponseSender.send(new Response("OK", searchMusicResult));
                    return;
                }
                RLog.e(TAG, "usbMusicInfos size:0");
                searchMusicResult.setMediaProvider(MediaProviderEnum.USB);
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                this.testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                return;
            case 12:
                if (localMedia != null && localMedia.stream().filter(new Predicate() { // from class: com.byd.vrassistant.customizedimpl.CustomMusicDomain$$ExternalSyntheticLambda1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return CustomMusicDomain.lambda$searchMusic$1(usbMedia, (MusicInfo) obj);
                    }
                }).filter(new Predicate() { // from class: com.byd.vrassistant.customizedimpl.CustomMusicDomain$$ExternalSyntheticLambda2
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return CustomMusicDomain.lambda$searchMusic$3(sdcardMedia, (MusicInfo) obj);
                    }
                }).count() > 0) {
                    RLog.e(TAG, "localMusicInfos size:" + localMedia.size());
                    searchMusicResult.setMusicList(localMedia);
                    searchMusicResult.setMusicType("SONG");
                    searchMusicResult.setMediaProvider(MediaProviderEnum.LOCAL);
                    this.testLogCat.setResponse("OK");
                    iResponseSender.send(new Response("OK", searchMusicResult));
                } else {
                    RLog.e(TAG, "localMusicInfos size:0");
                    searchMusicResult.setMediaProvider(MediaProviderEnum.LOCAL);
                    iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                    this.testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
                }
                RLog.e(TAG, "open LOCAL music:");
                return;
            case 16:
                RLog.e(TAG, "open BLUETOOTH music:");
                if (BluetoothReceiver.BT_CONNECTED) {
                    RLog.e(TAG, "BT_CONNECTED:" + BluetoothReceiver.BT_CONNECTED);
                    searchMusicResult.setMusicType("SONG");
                    ArrayList arrayList6 = new ArrayList();
                    MusicInfo musicInfo5 = new MusicInfo();
                    musicInfo5.setSongName("blutooth music");
                    musicInfo5.setId("0xbbbbbbbb");
                    arrayList6.add(musicInfo5);
                    searchMusicResult.setMusicList(arrayList6);
                    searchMusicResult.setMediaProvider("BLUETOOTH");
                    this.testLogCat.setResponse("OK");
                    iResponseSender.send(new Response("OK", searchMusicResult));
                    return;
                }
                RLog.e(TAG, "BT_CONNECTED:" + BluetoothReceiver.BT_CONNECTED);
                searchMusicResult.setMediaProvider("BLUETOOTH");
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_PROVIDER_UNAVAILABLE, searchMusicResult));
                this.testLogCat.setResponse(ResultCodeEnum.MEDIA_PROVIDER_UNAVAILABLE);
                return;
            default:
                String packetAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio();
                try {
                    String latestPacketAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getLatestPacketAudio();
                    str = ResultCodeEnum.MEDIA_NOT_FOUND;
                    str2 = "OK";
                    str3 = latestPacketAudio;
                } catch (Exception unused) {
                    str = ResultCodeEnum.MEDIA_NOT_FOUND;
                    str2 = "OK";
                    Log.d(TAG, "getLatestPacketAudio error");
                    str3 = "";
                }
                if (packetAudio.contains(BydVoiceApplication.getContext().getPackageName())) {
                    packetAudio = "";
                }
                ArrayList arrayList7 = new ArrayList();
                arrayList7.add(LocalMediaManager.SPOTIFY_PACKAGE_NAME);
                arrayList7.add(SpotifyManager.SPOTIFY_APK_PACKAGE_NAME);
                ArrayList arrayList8 = new ArrayList();
                arrayList8.add(LocalMediaManager.LOCALMEDIA_PACKAGE_NAME);
                ArrayList arrayList9 = new ArrayList();
                arrayList9.add("com.android.car.media");
                arrayList9.add(LocalMediaManager.AMAZON_PACKAGE_NAME);
                if (OnlineMediaHelper.get().OnlineApplicationForegroundFor() != "") {
                    searchMusicResult.setMediaProvider(OnlineMediaHelper.get().getMediaProviderFromPackagename(OnlineMediaHelper.get().OnlineApplicationForegroundFor()));
                    searchMusicResult.setMusicType("SONG");
                    iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                    this.testLogCat.setResponse("UNSUPPORTED");
                    return;
                }
                if (OnlineMediaHelper.get().OnlineGetCurrentPackage() != "") {
                    searchMusicResult.setMediaProvider(OnlineMediaHelper.get().getMediaProviderFromPackagename(OnlineMediaHelper.get().OnlineGetCurrentPackage()));
                    searchMusicResult.setMusicType("SONG");
                    iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                    this.testLogCat.setResponse("UNSUPPORTED");
                    return;
                }
                if (OnlineMediaHelper.get().OnlineGetRecentlyPackage() != "") {
                    searchMusicResult.setMediaProvider(OnlineMediaHelper.get().getMediaProviderFromPackagename(OnlineMediaHelper.get().OnlineGetRecentlyPackage()));
                    searchMusicResult.setMusicType("SONG");
                    iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                    this.testLogCat.setResponse("UNSUPPORTED");
                    return;
                }
                if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), SpotifyManager.SPOTIFY_APK_PACKAGE_NAME) && BydVoiceApplication.isApplicationForeground(arrayList7)) {
                    playSearchSpotify(searchMusicParam, iResponseSender);
                    return;
                }
                String str6 = str3;
                if (BydVoiceApplication.isApplicationForeground(arrayList8)) {
                    if (packetAudio.equals(LocalMediaManager.BLUETOOTHMEDIA_PACKAGE_NAME)) {
                        searchMusicResult.setMediaProvider("BLUETOOTH");
                        searchMusicResult.setMusicType("SONG");
                        LocalMediaManager.get().saveCurrentRunningState();
                        LocalMediaManager.get().openBlutoothMedia(new CountDownLatch(1), 255);
                        iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                        this.testLogCat.setResponse("UNSUPPORTED");
                        return;
                    }
                } else {
                    if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.AMAZON_PACKAGE_NAME) && BydVoiceApplication.isApplicationForeground(arrayList9)) {
                        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
                            RLog.e(TAG, "There is no Internet Connection");
                            searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, searchMusicResult));
                            return;
                        }
                        processAmazon();
                        if (!AmazonManager.get().isConnected()) {
                            RLog.e(TAG, "processAmazon  searchMusic");
                            searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR, searchMusicResult));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【Amazon_UNAVAILABLE】");
                            return;
                        }
                        if (!AmazonManager.get().isLogin()) {
                            RLog.e(TAG, "processAmazon searchMusic error:NOT_LOGGED_IN");
                            iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, searchMusicResult));
                            return;
                        } else {
                            if (!searchMusicParam.getSearchCriteria().getGenre().isEmpty()) {
                                searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                                this.testLogCat.setResponse("UNSUPPORTED");
                                return;
                            }
                            AmazonManager.get().searchMusicInfo(searchMusicParam.getSearchCriteria(), searchMusicResult, iResponseSender, this.testLogCat);
                            return;
                        }
                    }
                    if (packetAudio.equals(LocalMediaManager.SPOTIFY_PACKAGE_NAME) || str6.equals(LocalMediaManager.SPOTIFY_PACKAGE_NAME)) {
                        RLog.e(TAG, "spotify curPackage:" + packetAudio);
                        searchMusicResult.setMediaProvider(MediaProviderEnum.SPOTIFY);
                        searchMusicResult.setMusicType("SONG");
                        try {
                            SpotifyManager.get().openSpotify();
                            playSearchSpotify(searchMusicParam, iResponseSender);
                            response = new Response("UNSUPPORTED", searchMusicResult);
                        } catch (Exception unused2) {
                            response = new Response("UNSUPPORTED", searchMusicResult);
                        } catch (Throwable th) {
                            iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                            throw th;
                        }
                        iResponseSender.send(response);
                        this.testLogCat.setResponse("UNSUPPORTED");
                        return;
                    }
                    if (packetAudio.equals(LocalMediaManager.BLUETOOTHMEDIA_PACKAGE_NAME)) {
                        RLog.e(TAG, "blue curPackage:" + packetAudio);
                        searchMusicResult.setMediaProvider("BLUETOOTH");
                        searchMusicResult.setMusicType("SONG");
                        LocalMediaManager.get().saveCurrentRunningState();
                        LocalMediaManager.get().openBlutoothMedia(new CountDownLatch(1), 255);
                        iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                        this.testLogCat.setResponse("UNSUPPORTED");
                        return;
                    }
                    if (packetAudio.equals(LocalMediaManager.AMAZON_PACKAGE_NAME)) {
                        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
                            RLog.e(TAG, "There is no Internet Connection");
                            searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, searchMusicResult));
                            return;
                        }
                        processAmazon();
                        if (!AmazonManager.get().isConnected()) {
                            RLog.e(TAG, "processAmazon  searchMusic");
                            searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR, searchMusicResult));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【Amazon_UNAVAILABLE】");
                            return;
                        }
                        if (!AmazonManager.get().isLogin()) {
                            RLog.e(TAG, "processAmazon searchMusic error:NOT_LOGGED_IN");
                            iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, searchMusicResult));
                            return;
                        } else {
                            if (!searchMusicParam.getSearchCriteria().getGenre().isEmpty()) {
                                searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                                this.testLogCat.setResponse("UNSUPPORTED");
                                return;
                            }
                            AmazonManager.get().searchMusicInfo(searchMusicParam.getSearchCriteria(), searchMusicResult, iResponseSender, this.testLogCat);
                            return;
                        }
                    }
                    if (packetAudio.equals(LocalMediaManager.LOCALMEDIA_PACKAGE_NAME)) {
                        RLog.e(TAG, "local meida curPackage:" + packetAudio);
                    } else {
                        packetAudio.isEmpty();
                    }
                }
                if (packetAudio.isEmpty() && searchMusicParam.getSearchCriteria().getMediaProvider().equals(MediaProviderEnum.NONE) && InternetConnectionJudge.getInstance().isThereInternetConnection()) {
                    if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), "com.vivid.music.byd")) {
                        SpotifyManager.get().openSpotify();
                        playSearchSpotify(searchMusicParam, iResponseSender);
                        return;
                    }
                    if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.AMAZON_PACKAGE_NAME)) {
                        processAmazon();
                        if (!AmazonManager.get().isConnected()) {
                            RLog.e(TAG, "processAmazon  searchMusic");
                            searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR, searchMusicResult));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【Amazon_UNAVAILABLE】");
                            return;
                        }
                        if (!AmazonManager.get().isLogin()) {
                            RLog.e(TAG, "processAmazon searchMusic error:NOT_LOGGED_IN");
                            iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, searchMusicResult));
                            return;
                        } else {
                            if (!searchMusicParam.getSearchCriteria().getGenre().isEmpty()) {
                                searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                                this.testLogCat.setResponse("UNSUPPORTED");
                                return;
                            }
                            AmazonManager.get().searchMusicInfo(searchMusicParam.getSearchCriteria(), searchMusicResult, iResponseSender, this.testLogCat);
                            return;
                        }
                    }
                    if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), LocalMediaManager.AMAZON_PACKAGE_NAME)) {
                        processAmazon();
                        if (!AmazonManager.get().isConnected()) {
                            RLog.e(TAG, "processAmazon  searchMusic");
                            searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR, searchMusicResult));
                            RLog.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【Amazon_UNAVAILABLE】");
                            return;
                        }
                        if (!AmazonManager.get().isLogin()) {
                            RLog.e(TAG, "processAmazon searchMusic error:NOT_LOGGED_IN");
                            iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, searchMusicResult));
                            return;
                        } else {
                            if (!searchMusicParam.getSearchCriteria().getGenre().isEmpty()) {
                                searchMusicResult.setMediaProvider(MediaProviderEnum.AMAZON_MUSIC);
                                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                                this.testLogCat.setResponse("UNSUPPORTED");
                                return;
                            }
                            AmazonManager.get().searchMusicInfo(searchMusicParam.getSearchCriteria(), searchMusicResult, iResponseSender, this.testLogCat);
                            return;
                        }
                    }
                }
                if (!searchMusicParam.getSearchCriteria().getGenre().isEmpty()) {
                    searchMusicResult.setMediaProvider(MediaProviderEnum.LOCAL);
                    iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                    this.testLogCat.setResponse("UNSUPPORTED");
                    return;
                }
                List<MusicInfo> listSearchMusicInfo = LocalMediaManager.get().searchMusicInfo(localMedia, searchMusicParam.getSearchCriteria());
                if (listSearchMusicInfo != null && listSearchMusicInfo.size() > 0) {
                    RLog.e(TAG, "searchMusic size:" + listSearchMusicInfo.size());
                    searchMusicResult.setMusicList(listSearchMusicInfo);
                    searchMusicResult.setMusicType(searchMusicParam.getSearchCriteria().getMusicType());
                    if (listSearchMusicInfo.size() > 1) {
                        searchMusicResult.setNeedDisambig(true);
                    }
                    int localMusicType = AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType();
                    if (localMusicType == 0) {
                        searchMusicResult.setMediaProvider(MediaProviderEnum.LOCAL);
                    } else if (localMusicType == 2) {
                        searchMusicResult.setMediaProvider(MediaProviderEnum.SDCARD);
                    } else if (localMusicType == 1) {
                        searchMusicResult.setMediaProvider(MediaProviderEnum.USB);
                    } else {
                        searchMusicResult.setMediaProvider(MediaProviderEnum.LOCAL);
                    }
                    String str7 = str2;
                    this.testLogCat.setResponse(str7);
                    iResponseSender.send(new Response(str7, searchMusicResult));
                    return;
                }
                RLog.e(TAG, "searchMusic size:0");
                String str8 = str;
                iResponseSender.send(new Response(str8, searchMusicResult));
                this.testLogCat.setResponse(str8);
                return;
        }
    }

    static /* synthetic */ boolean lambda$searchMusic$1(List list, final MusicInfo musicInfo) {
        return !list.stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.customizedimpl.CustomMusicDomain$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MusicInfo) obj).getId().equals(musicInfo.getId());
            }
        });
    }

    static /* synthetic */ boolean lambda$searchMusic$3(List list, final MusicInfo musicInfo) {
        return !list.stream().anyMatch(new Predicate() { // from class: com.byd.vrassistant.customizedimpl.CustomMusicDomain$$ExternalSyntheticLambda3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((MusicInfo) obj).getId().equals(musicInfo.getId());
            }
        });
    }

    @Override // cerence.ark.assistant.controller.domain.media.AbsMediaDomain
    protected void playMusic(PlayMusicParam playMusicParam, IResponseSender<BaseResult> iResponseSender) {
        int iOpenLocalFavouriteMedia;
        int iOpenLocalFavouriteMedia2;
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        try {
            RLog.e(TAG, "playMusic:" + playMusicParam.toJsonString());
            EventBus.getDefault().post("playMusic: " + playMusicParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add("com.android.car.media");
        arrayList.add(LocalMediaManager.AMAZON_PACKAGE_NAME);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(LocalMediaManager.LOCALMEDIA_PACKAGE_NAME);
        if (BydVoiceApplication.isApplicationForeground(arrayList)) {
            List<MusicInfo> musicList = playMusicParam.getMusicList();
            if (musicList != null && musicList.size() > 0) {
                if (!playMusicParam.getSearchCriteria().getMusicType().equals("PLAYLIST")) {
                    if (!TextUtils.isEmpty(musicList.get(0).getId())) {
                        if (AmazonManager.get().playById(musicList.get(0).getId()).getValue() != 0) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
                            return;
                        }
                        AmazonManager.get().startWait();
                        int responseCode = AmazonManager.getResponseCode();
                        RLog.i(TAG, "playMusic ret : " + responseCode);
                        this.testLogCat.setResponse(responseCode != -1 ? "OK" : ResultCodeEnum.ERROR);
                        iResponseSender.send(new Response(responseCode == -1 ? ResultCodeEnum.ERROR : "OK", new BaseResult()));
                        return;
                    }
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
                    return;
                }
                int value = AmazonManager.get().playByFavorite().getValue();
                if (value == 0) {
                    this.testLogCat.setResponse("OK");
                    iResponseSender.send(new Response("OK", new BaseResult()));
                    return;
                } else if (1001 == value) {
                    this.testLogCat.setResponse(ResultCodeEnum.NOT_LOGGED_IN);
                    iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, new BaseResult()));
                    return;
                } else if (1002 == value) {
                    this.testLogCat.setResponse("UNSUPPORTED");
                    iResponseSender.send(new Response("UNSUPPORTED", new BaseResult()));
                    return;
                } else {
                    this.testLogCat.setResponse(ResultCodeEnum.ERROR);
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
                    return;
                }
            }
            this.testLogCat.setResponse(ResultCodeEnum.ERROR);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
            return;
        }
        if (BydVoiceApplication.isApplicationForeground(arrayList2)) {
            EventBus.getDefault().post("getSearchCriteria: " + playMusicParam.getSearchCriteria().getSongName());
            List<MusicInfo> musicList2 = playMusicParam.getMusicList();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (musicList2 != null && musicList2.size() > 0) {
                if (!playMusicParam.getSearchCriteria().getMusicType().equals("PLAYLIST")) {
                    iOpenLocalFavouriteMedia2 = LocalMediaManager.get().playUsbMediaBySongName(musicList2.get(0), 1, countDownLatch);
                } else {
                    iOpenLocalFavouriteMedia2 = LocalMediaManager.get().openLocalFavouriteMedia(countDownLatch);
                }
                int madieRet = iOpenLocalFavouriteMedia2;
                try {
                    countDownLatch.await(6L, TimeUnit.SECONDS);
                    madieRet = LocalMediaManager.get().getMadieRet();
                    RLog.e(TAG, "playMusic ret : " + madieRet);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (madieRet != -3) {
                    if (madieRet != -2 && madieRet != -1) {
                        if (madieRet != 0) {
                            return;
                        } else {
                            iResponseSender.send(new Response("OK", new BaseResult()));
                        }
                    }
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
                    return;
                }
                iResponseSender.send(new Response("UNSUPPORTED", new BaseResult()));
                return;
            }
            this.testLogCat.setResponse(ResultCodeEnum.ERROR);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
            return;
        }
        if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.AMAZON_MUSIC)) {
            List<MusicInfo> musicList3 = playMusicParam.getMusicList();
            if (musicList3 != null && musicList3.size() > 0) {
                if (!playMusicParam.getSearchCriteria().getMusicType().equals("PLAYLIST")) {
                    if (!TextUtils.isEmpty(musicList3.get(0).getId())) {
                        if (AmazonManager.get().playById(musicList3.get(0).getId()).getValue() != 0) {
                            iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
                            return;
                        }
                        AmazonManager.get().startWait();
                        int responseCode2 = AmazonManager.getResponseCode();
                        RLog.i(TAG, "playMusic ret : " + responseCode2);
                        this.testLogCat.setResponse(responseCode2 != -1 ? "OK" : ResultCodeEnum.ERROR);
                        iResponseSender.send(new Response(responseCode2 == -1 ? ResultCodeEnum.ERROR : "OK", new BaseResult()));
                        return;
                    }
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
                    return;
                }
                int value2 = AmazonManager.get().playByFavorite().getValue();
                if (value2 == 0) {
                    this.testLogCat.setResponse("OK");
                    iResponseSender.send(new Response("OK", new BaseResult()));
                    return;
                } else if (1001 == value2) {
                    this.testLogCat.setResponse(ResultCodeEnum.NOT_LOGGED_IN);
                    iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, new BaseResult()));
                    return;
                } else if (1002 == value2) {
                    this.testLogCat.setResponse("UNSUPPORTED");
                    iResponseSender.send(new Response("UNSUPPORTED", new BaseResult()));
                    return;
                } else {
                    this.testLogCat.setResponse(ResultCodeEnum.ERROR);
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
                    return;
                }
            }
            this.testLogCat.setResponse(ResultCodeEnum.ERROR);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
            return;
        }
        EventBus.getDefault().post("getSearchCriteria: " + playMusicParam.getSearchCriteria().getSongName());
        List<MusicInfo> musicList4 = playMusicParam.getMusicList();
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        if (musicList4 != null && musicList4.size() > 0) {
            if (!playMusicParam.getSearchCriteria().getMusicType().equals("PLAYLIST")) {
                iOpenLocalFavouriteMedia = LocalMediaManager.get().playUsbMediaBySongName(musicList4.get(0), 1, countDownLatch2);
            } else {
                iOpenLocalFavouriteMedia = LocalMediaManager.get().openLocalFavouriteMedia(countDownLatch2);
            }
            int madieRet2 = iOpenLocalFavouriteMedia;
            try {
                countDownLatch2.await(6L, TimeUnit.SECONDS);
                madieRet2 = LocalMediaManager.get().getMadieRet();
                RLog.e(TAG, "playMusic ret : " + madieRet2);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            this.testLogCat.setResponse(madieRet2 != -1 ? "OK" : ResultCodeEnum.ERROR);
            iResponseSender.send(new Response(madieRet2 == -1 ? ResultCodeEnum.ERROR : "OK", new BaseResult()));
            return;
        }
        this.testLogCat.setResponse(ResultCodeEnum.ERROR);
        iResponseSender.send(new Response(ResultCodeEnum.ERROR, new BaseResult()));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:157:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x03ba  */
    @Override // cerence.ark.assistant.controller.domain.media.AbsMediaDomain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void playMedia(cerence.ark.assistant.controller.domain.media.param.PlayMediaParam r21, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.controller.domain.media.result.PlayMediaResult> r22) {
        /*
            Method dump skipped, instruction units count: 1132
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.customizedimpl.CustomMusicDomain.playMedia(cerence.ark.assistant.controller.domain.media.param.PlayMediaParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    @Override // cerence.ark.assistant.controller.domain.media.AbsMediaDomain
    protected void isMediaProviderRunning(IsMediaProviderRunningParam isMediaProviderRunningParam, IResponseSender<IsMediaProviderRunningResult> iResponseSender) {
        try {
            this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
            RLog.e(TAG, "isMediaProviderRunning:" + isMediaProviderRunningParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String upperCase = isMediaProviderRunningParam.getMediaProvider().toUpperCase(Locale.ROOT);
        IsMediaProviderRunningResult isMediaProviderRunningResult = new IsMediaProviderRunningResult();
        if (upperCase.equals(MediaProviderEnum.SPOTIFY)) {
            RLog.e(TAG, "spotify :" + upperCase);
            if (!SpotifyManager.get().isConnected()) {
                CountDownLatch countDownLatch = new CountDownLatch(1);
                SpotifyManager.get().init(countDownLatch);
                try {
                    countDownLatch.await(6L, TimeUnit.SECONDS);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            if (!SpotifyManager.get().isConnected()) {
                isMediaProviderRunningResult.setIsRunning(false);
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, isMediaProviderRunningResult));
                this.testLogCat.setResponse(ResultCodeEnum.ERROR);
                return;
            }
            RLog.e(TAG, "processSpotify 2");
            CountDownLatch countDownLatch2 = new CountDownLatch(1);
            SpotifyManager.get().isLogin(countDownLatch2);
            try {
                countDownLatch2.await(6L, TimeUnit.SECONDS);
                if (SpotifyManager.getResponseCode() != ResponseCode.OK && SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                    isMediaProviderRunningResult.setIsRunning(false);
                    iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, isMediaProviderRunningResult));
                    this.testLogCat.setResponse(ResultCodeEnum.NOT_LOGGED_IN);
                    return;
                } else {
                    RLog.i(TAG, "isMediaProviderRunning spotify mediaState: " + SpotifyManager.get().getState());
                    isMediaProviderRunningResult.setIsRunning(SpotifyManager.get().isSpotifyRunning());
                    iResponseSender.send(new Response("OK", isMediaProviderRunningResult));
                    this.testLogCat.setResponse("OK");
                    return;
                }
            } catch (InterruptedException e3) {
                throw new RuntimeException(e3);
            }
        }
        if (upperCase.equals(MediaProviderEnum.NONE)) {
            return;
        }
        if (upperCase.equals("BLUETOOTH") || upperCase.equals(MediaProviderEnum.USB) || upperCase.equals(MediaProviderEnum.SDCARD) || upperCase.equals(MediaProviderEnum.LOCAL) || upperCase.equals("MEMORY_CARD")) {
            LocalMediaManager.get().saveCurrentRunningStateByProvider(upperCase);
        }
        isMediaProviderRunningResult.setIsRunning(LocalMediaManager.get().isMediaRunning());
        RLog.i(TAG, " result media setIsRunning : " + LocalMediaManager.get().isMediaRunning());
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK", isMediaProviderRunningResult));
    }

    protected <T extends BaseResult> void processAmazon(IResponseSender<T> iResponseSender, T t, boolean z, String str) {
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            RLog.e(TAG, "There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, t));
            return;
        }
        RLog.e(TAG, "processAmazon");
        if (AmazonManager.get().isConnected()) {
            AmazonManager.get().saveCurrentRunningState();
            AmazonManager.get().openAmazon();
            if (z) {
                if (AmazonManager.get().getState() == AmazonManager.STATE_PLAYING) {
                    this.testLogCat.setResponse("ALREADY_EXPECTED");
                    iResponseSender.send(new Response("ALREADY_EXPECTED", t));
                    return;
                } else if (!AmazonManager.get().isLogin()) {
                    RLog.e(TAG, "processAmazon play error:NOT_LOGGED_IN");
                    iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, t));
                    return;
                } else if (AmazonManager.get().play().getValue() != 0) {
                    RLog.e(TAG, "processAmazon play error");
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR, t));
                    return;
                }
            }
        } else {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            AmazonManager.get().init(countDownLatch);
            try {
                countDownLatch.await(3L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!AmazonManager.get().isConnected()) {
                RLog.e(TAG, "processAmazon 1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, t));
                return;
            }
            RLog.e(TAG, "processAmazon 2");
            AmazonManager.get().openAmazon();
            if (z && AmazonManager.get().getState() == AmazonManager.STATE_PLAYING) {
                this.testLogCat.setResponse("ALREADY_EXPECTED");
                iResponseSender.send(new Response("ALREADY_EXPECTED", t));
                return;
            } else if (!AmazonManager.get().isLogin()) {
                RLog.e(TAG, "processAmazon play error:NOT_LOGGED_IN");
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, t));
                return;
            } else if (AmazonManager.get().play().getValue() != 0) {
                RLog.e(TAG, "processAmazon play error");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, t));
                return;
            }
        }
        RLog.e(TAG, "processAmazon 3 :" + str);
        iResponseSender.send(new Response(str, t));
    }

    public void processAmazon() {
        RLog.i(TAG, "processAmazon");
        if (AmazonManager.get().isConnected()) {
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AmazonManager.get().init(countDownLatch);
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void playSearchSpotify(SearchMusicParam searchMusicParam, IResponseSender<SearchMusicResult> iResponseSender) {
        Log.d(TAG, "playSearchSpotify");
        SearchMusicResult searchMusicResult = new SearchMusicResult();
        searchMusicResult.setMediaProvider(MediaProviderEnum.SPOTIFY);
        searchMusicResult.setMusicType("SONG");
        if (!InternetConnectionJudge.getInstance().isThereInternetConnection()) {
            Log.e(TAG, "playfromSearchHelper There is no Internet Connection");
            iResponseSender.send(new Response(ResultCodeEnum.NO_INTERNET, searchMusicResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【NO_INTERNET】");
            return;
        }
        if (!SpotifyManager.get().isConnected()) {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            SpotifyManager.get().init(countDownLatch);
            try {
                countDownLatch.await(4L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (!SpotifyManager.get().isConnected()) {
            RLog.e(TAG, "playSearchSpotify  searchMusic");
            iResponseSender.send(new Response(ResultCodeEnum.SPOTIFY_UNAVAILABLE, searchMusicResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【Spotify_UNAVAILABLE】");
            return;
        }
        if (searchMusicParam.getSearchCriteria().getMusicType().equals("PLAYLIST")) {
            try {
                Log.e(TAG, "playfromSearchHelper  UNSUPPORTED   PLAYLIST");
                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                Log.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【UNSUPPORTED】");
                return;
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
        String genre = searchMusicParam.getSearchCriteria().getGenre();
        if (genre != null && !genre.isEmpty()) {
            try {
                Log.e(TAG, "playfromSearchHelper  UNSUPPORTED   Genre");
                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                Log.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【UNSUPPORTED】");
                return;
            } catch (Exception e3) {
                throw new RuntimeException(e3);
            }
        }
        SpotifyManager.get().playForSearch(searchMusicParam.getSearchCriteria().getSongName(), searchMusicParam.getSearchCriteria().getArtist(), searchMusicParam.getSearchCriteria().getAlbum(), searchMusicParam.getSearchCriteria().getGenre());
        if (SpotifyManager.getResponseCode() != ResponseCode.OK) {
            Log.e(TAG, "SpotifyManager searchMusic .getResponseCode != OK : " + SpotifyManager.getResponseCode().name());
            if (SpotifyManager.getResponseCode() == ResponseCode.USER_NOT_LOGIN) {
                iResponseSender.send(new Response(ResultCodeEnum.NOT_LOGGED_IN, searchMusicResult));
                Log.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【NOT_LOGGED_IN】");
                return;
            } else if (SpotifyManager.getResponseCode() == ResponseCode.MODE_UNSUPPORT) {
                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                Log.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【UNSUPPORTED】");
                return;
            } else if (SpotifyManager.getResponseCode() == ResponseCode.UNSUPPORT_OPERATION) {
                iResponseSender.send(new Response("UNSUPPORTED", searchMusicResult));
                Log.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【UNSUPPORTED】");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, searchMusicResult));
                Log.i(Constant.TAG_FORTEST, "request domain【CustomMusicDomain】,api【searchMusic】,result【MEDIA_NOT_FOUND】");
                return;
            }
        }
        Log.e(TAG, "Spotify searchMusic  processSpotify 2");
        iResponseSender.send(new Response(ResultCodeEnum.ALREADY_PLAYED, searchMusicResult));
        Log.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【SpotifyControlPlayList】,result【ALREADY_PLAYED】");
    }
}
