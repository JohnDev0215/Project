package com.byd.vrassistant.manager;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.struct.MusicInfo;
import com.byd.minikaraoke.api.MicrophoneService;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.customizedimpl.CustomSystemDomain;
import com.byd.vrassistant.utils.RLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.compress.utils.CharsetNames;

/* JADX INFO: loaded from: classes2.dex */
public class KaraokeManager {
    private static final String TAG = "NEU_KaraokeManager";
    private static volatile KaraokeManager mInstance;
    private boolean blConnected = false;
    private MicrophoneService mMicrophoneService;

    public boolean isBlConnected() {
        return this.blConnected;
    }

    public static KaraokeManager get() {
        if (mInstance == null) {
            synchronized (KaraokeManager.class) {
                if (mInstance == null) {
                    mInstance = new KaraokeManager();
                }
            }
        }
        return mInstance;
    }

    private KaraokeManager() {
    }

    public synchronized void init() {
        try {
            MicrophoneService microphoneService = MicrophoneService.getInstance(BydVoiceApplication.getContext());
            this.mMicrophoneService = microphoneService;
            if (microphoneService != null) {
                if (!this.blConnected) {
                    this.blConnected = microphoneService.connect();
                    RLog.i(TAG, "MicrophoneService connect result = " + this.blConnected);
                }
                if (!this.blConnected) {
                    RLog.i(TAG, "MicrophoneService connect fail!");
                }
            }
        } catch (Exception unused) {
            RLog.e(TAG, "init Exception!");
        }
    }

    public void resume() {
        karaokeIntent("stingraykaraoke://command/play");
        RLog.i(TAG, "KaraokeManager resume ");
    }

    public void pause() {
        RLog.i(TAG, "KaraokeManager pause");
        karaokeIntent("stingraykaraoke://command/pause");
    }

    public void skipToNext() {
        karaokeIntent("stingraykaraoke://command/skip");
        RLog.i(TAG, "KaraokeManager skipToNext");
    }

    public void skipToPrevious() {
        karaokeIntent("stingraykaraoke://command/previous");
        RLog.i(TAG, "KaraokeManager skipToPrevious");
    }

    public void setPlayModeRepeatOne() {
        RLog.i(TAG, "KaraokeManager setPlayModeRepeatOne");
        karaokeIntent("stingraykaraoke://command/loop_one");
    }

    public void setPlayModeListLoop() {
        RLog.i(TAG, "KaraokeManager setPlayModeListLoop");
        karaokeIntent("stingraykaraoke://command/loop_all");
    }

    public void setPlayModeShuffle() {
        RLog.i(TAG, "KaraokeManager setPlayModeShuffle");
        karaokeIntent("stingraykaraoke://command/loop_off");
    }

    public void searchMusicInfo(MusicInfo musicInfo, IResponseSender<BaseResult> iResponseSender, CustomSystemDomain.TestLogCat testLogCat) {
        Response response;
        String str = TAG;
        RLog.i(str, "searchMusicInfo");
        if (!TextUtils.isEmpty(musicInfo.getSongName()) || !TextUtils.isEmpty(musicInfo.getAlbum()) || !TextUtils.isEmpty(musicInfo.getArtist()) || !TextUtils.isEmpty(musicInfo.getGenre())) {
            String songName = musicInfo.getSongName();
            String album = musicInfo.getAlbum();
            String artist = musicInfo.getArtist();
            String genre = musicInfo.getGenre();
            if (!TextUtils.isEmpty(songName) && !TextUtils.isEmpty(artist)) {
                songName = artist + " " + songName;
            } else if (TextUtils.isEmpty(songName)) {
                if (!TextUtils.isEmpty(artist)) {
                    songName = artist;
                } else if (TextUtils.isEmpty(genre)) {
                    songName = !TextUtils.isEmpty(album) ? album : "";
                } else {
                    songName = genre;
                }
            }
            RLog.i(str, "searchKaraokeMusic searchText: " + songName);
            try {
                try {
                    karaokeIntent(("stingraykaraoke://command/search_and_play?query=" + songName).replaceAll(" ", "%20"));
                    testLogCat.setResponse("OK");
                    response = new Response("OK");
                } catch (Exception unused) {
                    RLog.e(TAG, "searchKaraokeMusic Exception");
                    testLogCat.setResponse("OK");
                    response = new Response("OK");
                }
                iResponseSender.send(response);
                return;
            } catch (Throwable th) {
                testLogCat.setResponse("OK");
                iResponseSender.send(new Response("OK"));
                throw th;
            }
        }
        RLog.e(str, "searchKaraokeMusic MEDIA_NOT_FOUND");
        iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND));
        testLogCat.setResponse(ResultCodeEnum.MEDIA_NOT_FOUND);
    }

    public void showKaraokeHistory() {
        RLog.i(TAG, "KaraokeManager showKaraokeHistory");
        karaokeIntent("stingraykaraoke://command/history");
    }

    public void vocalOn() {
        RLog.i(TAG, "KaraokeManager vocalOn");
        karaokeIntent("stingraykaraoke://command/lead_vocal_on");
    }

    public void vocalOff() {
        RLog.i(TAG, "KaraokeManager vocalOff");
        karaokeIntent("stingraykaraoke://command/lead_vocal_off");
    }

    public boolean isMICConnected() {
        String str = TAG;
        RLog.i(str, "KaraokeManager isMICConnected");
        if (!this.blConnected) {
            return false;
        }
        boolean micConnectionState = this.mMicrophoneService.getMicConnectionState();
        RLog.i(str, "increaseMicVolume, result: " + micConnectionState);
        return micConnectionState;
    }

    public int getCurMICVolume() {
        String str = TAG;
        RLog.i(str, "KaraokeManager getCurMICVolume");
        if (!this.blConnected) {
            return -100;
        }
        int micVolume = this.mMicrophoneService.getMicVolume();
        RLog.i(str, "getCurMICVolume, micVolume: " + micVolume);
        return micVolume;
    }

    public boolean increaseMicVolume() {
        if (!this.blConnected) {
            return false;
        }
        boolean zIncreaseMicVolume = this.mMicrophoneService.increaseMicVolume();
        RLog.i(TAG, "increaseMicVolume, result: " + zIncreaseMicVolume);
        return zIncreaseMicVolume;
    }

    public boolean decreaseMicVolume() {
        if (!this.blConnected) {
            return false;
        }
        boolean zDecreaseMicVolume = this.mMicrophoneService.decreaseMicVolume();
        RLog.i(TAG, "decreaseMicVolume, result: " + zDecreaseMicVolume);
        return zDecreaseMicVolume;
    }

    public boolean setMicVolume(int i) {
        String str = TAG;
        RLog.i(str, "setMicVolume, volume: " + i);
        if (!this.blConnected) {
            return false;
        }
        boolean micVolume = this.mMicrophoneService.setMicVolume(i);
        RLog.i(str, "setMicVolume, result: " + micVolume);
        return micVolume;
    }

    private void karaokeIntent(String str) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            intent.setData(Uri.parse(str));
            BydVoiceApplication.getContext().startActivity(intent);
        } catch (Exception unused) {
        }
    }

    private static String encodeURL(String str) {
        String[] strArrSplit = str.split("//");
        if (strArrSplit.length <= 1) {
            return str;
        }
        try {
            strArrSplit[1] = URLEncoder.encode(strArrSplit[1], CharsetNames.UTF_8);
        } catch (UnsupportedEncodingException unused) {
        }
        return String.join("//", strArrSplit);
    }

    public void uninit() {
        this.blConnected = false;
        MicrophoneService microphoneService = this.mMicrophoneService;
        if (microphoneService != null) {
            microphoneService.disconnect();
            this.mMicrophoneService = null;
        }
    }
}
