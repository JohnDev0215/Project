package com.byd.vrassistant.conversation;

import android.app.Instrumentation;
import android.content.Context;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.util.Log;
import com.byd.common.bean.WuwInfoBean;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.customizedimpl.CustomRadioDomain;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class RadioSdk {
    public int Dab1State;
    private final String TAG;
    MediaControl.Callback callback;
    public int currentMode;
    public int currentPlayMode;
    public int currentPlayState;
    public int dabsupport;
    private Context mContext;

    public void dabstateCountdown() {
    }

    private RadioSdk() {
        this.TAG = "NEU_Radio RadioSdk";
        this.mContext = BydVoiceApplication.getContext();
        this.Dab1State = 0;
        this.currentMode = -1;
        this.dabsupport = -1;
        this.currentPlayState = -1;
        this.currentPlayMode = -1;
        this.callback = new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.1
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                RLog.i("NEU_Radio RadioSdk", "MediaControl Callback mode==" + i + ",action==" + i2 + WuwInfoBean.ReturnKey + result.getString(MediaControl.PARAM_SUCCESS));
            }
        };
    }

    private static class RadioSdkHolder {
        private static final RadioSdk radio = new RadioSdk();

        private RadioSdkHolder() {
        }
    }

    public static RadioSdk getInstance(Context context) {
        return RadioSdkHolder.radio;
    }

    public int openradio(final CountDownLatch countDownLatch, boolean z) {
        RLog.i("NEU_Radio RadioSdk", "start openradio");
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (z) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 0, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.2
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "openradio success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        RLog.i("NEU_Radio RadioSdk", "openradio fail==" + result.getString(MediaControl.PARAM_REASON));
                        CustomRadioDomain.RadioResult = -1;
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "openradio ERROR e ==" + e.toString());
            return -1;
        }
    }

    public int openradioband(final CountDownLatch countDownLatch, boolean z, int i) {
        RLog.i("NEU_Radio RadioSdk", "start openradioband");
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (z) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, i, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.3
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "openradioband success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        RLog.i("NEU_Radio RadioSdk", "openradioband fail==" + result.getString(MediaControl.PARAM_REASON));
                        CustomRadioDomain.RadioResult = -1;
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "openradioband ERROR e ==" + e.toString());
            return -1;
        }
    }

    public int getMediaMode() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        int i = -1;
        try {
            getMediaModeCallBack(countDownLatch);
            countDownLatch.await(4L, TimeUnit.SECONDS);
            int i2 = this.currentMode;
            if (i2 == 11 || i2 == 12) {
                i2 = 0;
            }
            i = i2;
            RLog.i("NEU_Radio RadioSdk", "start getMediaMode ret==" + i);
            return i;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return i;
        }
    }

    public void getDabSupport(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "20230728 ACTION_GET_MEDIA_STATUS  start dabsupport = " + this.dabsupport);
        MediaControl.controlMedia(BydVoiceApplication.getContext(), -1, 72, null, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.4
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                if (result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE)) {
                    RLog.i("NEU_Radio RadioSdk", "getDabSupport type array = " + Arrays.toString(result.getResult().getIntArray(MediaControl.PARAM_SUPPORT_MODE_LIST)));
                    int[] intArray = result.getResult().getIntArray(MediaControl.PARAM_SUPPORT_MODE_LIST);
                    RLog.i("NEU_Radio RadioSdk", "getDabSupport type array = " + intArray.toString());
                    if (intArray[3] > 0) {
                        RadioSdk.this.dabsupport = 2;
                    } else {
                        RadioSdk.this.dabsupport = -1;
                    }
                } else {
                    RLog.i("NEU_Radio RadioSdk", "getDabSupport fail ret = " + result.getResult().getString(MediaControl.PARAM_REASON));
                    RadioSdk.this.dabsupport = -1;
                }
                countDownLatch.countDown();
            }
        });
        RLog.i("NEU_Radio RadioSdk", "getDabSupport  dabsupport = " + this.dabsupport);
    }

    public void getMediaModeCallBack(final CountDownLatch countDownLatch) {
        MediaControl.controlMedia(BydVoiceApplication.getContext(), -1, 72, null, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.5
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                if (result.getResult().getString(MediaControl.PARAM_SUCCESS).equals(MediaControl.TRUE)) {
                    RadioSdk.this.currentMode = result.getResult().getInt(MediaControl.PARAM_CURRENT_MODE);
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_SUPPORT_MODE current type = " + RadioSdk.this.currentMode);
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_SUPPORT_MODE type array = " + Arrays.toString(result.getResult().getIntArray(MediaControl.PARAM_SUPPORT_MODE_LIST)));
                } else {
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS fail ret = " + result.getResult().getString(MediaControl.PARAM_REASON));
                    RadioSdk.this.currentMode = -1;
                }
                countDownLatch.countDown();
            }
        });
        RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS  currentMode = " + this.currentMode);
    }

    public String getPacketAudio() {
        String currentAudioPackage = LocalMediaManager.get().getCurrentAudioPackage();
        RLog.i("NEU_Radio RadioSdk", "PLATFORM_8155 getPacketAudio  getCurrentAudioFocusPackageName==" + currentAudioPackage);
        return currentAudioPackage;
    }

    public String getLatestPacketAudio() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String lastMediaPackage = BYDAutoAudioDevice.getInstance(this.mContext).getLastMediaPackage();
        RLog.i("NEU_Radio RadioSdk", "getLatestPacketAudio  packageName=" + lastMediaPackage);
        return lastMediaPackage;
    }

    public int getMediaPlayMode() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            getCallBackPlayMode(countDownLatch);
            countDownLatch.await(4L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = this.currentPlayMode;
        if (i == 0) {
            i = 5;
        }
        int i2 = i != 1 ? i : 1;
        if (i2 == 2) {
            i2 = 0;
        }
        RLog.i("NEU_Radio RadioSdk", "getMediaPlayMode ==" + i2);
        return i2;
    }

    public void getCallBackPlayMode(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "getCallBackPlayStatus  start currentPlayMode = " + this.currentPlayMode);
        this.currentPlayMode = -1;
        MediaControl.getInstance(BydVoiceApplication.getContext()).controlMedia(-1, 70, null, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.6
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                String string = result.getResult().getString(MediaControl.PARAM_SUCCESS);
                RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS currentPlayMode result =" + string);
                if (string.equals(MediaControl.TRUE)) {
                    RadioSdk.this.currentPlayMode = result.getResult().getInt("pattern");
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS currentPlayMode mode = " + result.getResult().getInt(MediaControl.PARAM_MEDIA_MODE));
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS currentPlayMode type =" + result.getResult().getInt(MediaControl.PARAM_MEDIA_TYPE));
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS currentPlayMode PARAM_MEDIA_PLAY_STATE =" + result.getResult().getInt(MediaControl.PARAM_MEDIA_PLAY_STATE));
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS currentPlayMode play state =" + RadioSdk.this.currentPlayState);
                } else {
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS currentPlayMode fail ret =" + result.getResult().getString(MediaControl.PARAM_REASON));
                    RadioSdk.this.currentPlayMode = -1;
                }
                countDownLatch.countDown();
            }
        });
    }

    public int muteRadio(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start muteRadio");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, -1, 20, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.7
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "muteRadio success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        RLog.i("NEU_Radio RadioSdk", "muteRadio fail2==" + string);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 0;
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "muteRadio error e==" + e.toString());
            return -1;
        }
    }

    public int getMediaPlayType() {
        int i;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            RLog.i("NEU_Radio RadioSdk", "start getMediaPlayType ret1==-1");
            getMediaModeCallBack(countDownLatch);
            countDownLatch.await(4L, TimeUnit.SECONDS);
            i = this.currentMode;
            try {
                RLog.i("NEU_Radio RadioSdk", "start getMediaPlayType ret==" + i);
            } catch (InterruptedException e) {
                e = e;
                e.printStackTrace();
            }
        } catch (InterruptedException e2) {
            e = e2;
            i = -1;
        }
        int i2 = i != 11 ? i == 12 ? 0 : -1 : 1;
        RLog.i("NEU_Radio RadioSdk", "getMediaPlayType ==" + i2);
        return i2;
    }

    public int getPlayStatus() {
        int mediaPlaySate = LocalMediaManager.get().getMediaPlaySate();
        RLog.i("NEU_Radio RadioSdk", "getPlayStatus ==" + mediaPlaySate);
        return mediaPlaySate;
    }

    public int getPlayStatusForManager() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            getCallBackPlayStatus(countDownLatch);
            countDownLatch.await(4L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = this.currentPlayState;
        if (i == 1) {
            i = 0;
        } else if (i == 2) {
            i = 2;
        }
        RLog.i("NEU_Radio RadioSdk", "getPlayStatusForManager getPlayStatus ==" + i);
        return i;
    }

    public void getCallBackPlayStatus(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "getCallBackPlayStatus  start currentPlayState = " + this.currentPlayState);
        MediaControl.getInstance(BydVoiceApplication.getContext()).controlMedia(-1, 70, null, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.8
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                String string = result.getResult().getString(MediaControl.PARAM_SUCCESS);
                RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS get result =" + string);
                if (string.equals(MediaControl.TRUE)) {
                    RadioSdk.this.currentPlayState = result.getResult().getInt(MediaControl.PARAM_MEDIA_PLAY_STATE);
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS mode = " + result.getResult().getInt(MediaControl.PARAM_MEDIA_MODE));
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS type =" + result.getResult().getInt(MediaControl.PARAM_MEDIA_TYPE));
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS PARAM_PATTERN =" + result.getResult().getInt("pattern"));
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS play state =" + RadioSdk.this.currentPlayState);
                } else {
                    RLog.i("NEU_Radio RadioSdk", "ACTION_GET_MEDIA_STATUS fail ret =" + result.getResult().getString(MediaControl.PARAM_REASON));
                    RadioSdk.this.currentPlayState = -1;
                }
                countDownLatch.countDown();
            }
        });
    }

    public int playFrequency(int i, int i2, final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start playFrequency mhz==" + i + ",band===" + i2);
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam("radiofreq", i);
            int i3 = i2 == 1 ? 11 : i2 == 0 ? 12 : 0;
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, i3, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.9
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i4, int i5, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "playFrequency success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        RLog.i("NEU_Radio RadioSdk", "playFrequency fail reason==" + string);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "playFrequency duplicated_action==");
                        } else if (MediaControl.REASON_UNKNOWN_PARAM.equals(string)) {
                            CustomRadioDomain.RadioResult = 2;
                            RLog.i("NEU_Radio RadioSdk", "playFrequency unknown_param==");
                        } else if (MediaControl.REASON_UNSUPPORTED_FREQUENCY.equals(string)) {
                            CustomRadioDomain.RadioResult = 3;
                            RLog.i("NEU_Radio RadioSdk", "playFrequency REASON_UNSUPPORTED_FREQUENCY==");
                        } else if (MediaControl.REASON_OUT_OF_RANGE_FREQUENCY.equals(string)) {
                            CustomRadioDomain.RadioResult = 2;
                            RLog.i("NEU_Radio RadioSdk", "playFrequency REASON_OUT_OF_RANGE_FREQUENCY==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "start playFrequency error==" + e.toString());
            return -1;
        }
    }

    public void getRadioStationInfo() {
        MediaControl.getInstance(BydVoiceApplication.getContext()).controlMedia(-1, 68, null, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.10
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                String string = result.getResult().getString(MediaControl.PARAM_SUCCESS);
                RLog.e("NEU_Radio RadioSdk", "get result =" + string);
                if (string.equals(MediaControl.TRUE)) {
                    RLog.e("NEU_Radio RadioSdk", "freq = " + result.getResult().getInt("radiofreq"));
                } else {
                    RLog.e("NEU_Radio RadioSdk", "fail ret = " + result.getResult().getString(MediaControl.PARAM_REASON));
                }
            }
        });
    }

    public int SearchFrequency(String str, final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start SearchFrequency mhz==" + str);
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam("radiofreq", str);
            param.setParam(MediaControl.PARAM_AUTO_PLAY, 1);
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 0, 1, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.11
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "SearchFrequency success");
                        RLog.i("NEU_Radio RadioSdk", "SearchFrequency success mediaId==" + result.getString(MediaControl.PARAM_MEDIA_ID));
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "SearchFrequency duplicated_action==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_Radio RadioSdk", "SearchFrequency fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "start playFrequency error==" + e.toString());
            return -1;
        }
    }

    public int SearchFrequencyTest(String str, final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start SearchFrequencyTest mhz==" + str);
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam("radiofreq", str);
            param.setParam(MediaControl.PARAM_AUTO_PLAY, 1);
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 0, 1, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.12
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "SearchFrequencyTest success");
                        String string = result.getString(MediaControl.PARAM_MEDIA_ID);
                        RLog.i("NEU_Radio RadioSdk", "SearchFrequencyTest success mediaId==" + string);
                        MediaControl.Param param2 = new MediaControl.Param();
                        param2.setParam(MediaControl.PARAM_MEDIA_ID, string);
                        MediaControl.controlMedia(RadioSdk.this.mContext, 0, 26, param2, null);
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string2 = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string2)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "SearchFrequencyTest duplicated_action==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_Radio RadioSdk", "SearchFrequencyTest fail==" + string2);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "start playFrequency error==" + e.toString());
            return -1;
        }
    }

    public int playFavoriteFre(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start playFavoriteFre ");
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 0, 25, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.13
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "playFavoriteFre success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "playFavoriteFre duplicated_action==");
                        } else if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                            CustomRadioDomain.RadioResult = 2;
                            RLog.i("NEU_Radio RadioSdk", "playFavoriteFre not_found==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_Radio RadioSdk", "playFavoriteFre fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "start playFavoriteFre error==" + e.toString());
            return -1;
        }
    }

    public int changeBand(int i, final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start changeBand band==" + i);
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            int i2 = 11;
            if (i != 1 && i == 0) {
                i2 = 12;
            }
            MediaControl.controlMedia(this.mContext, i2, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.14
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i3, int i4, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "changeBand success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        RLog.i("NEU_Radio RadioSdk", "changeBand fail reason==" + string);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "changeBand duplicated_action==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "changeBand error ==" + e.toString());
            return -1;
        }
    }

    public int Controlfavorite(int i, final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start Controlfavorite maction==" + i);
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, -1, i, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.15
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        CustomRadioDomain.RadioTitle = "";
                        String string = result.getString("radioname");
                        String string2 = result.getString("radiordstitle");
                        Log.d("NEU_Radio RadioSdk", "Controlfavorite radioname radioname==" + string);
                        Log.d("NEU_Radio RadioSdk", "Controlfavorite radioname radiordstitle==" + string2);
                        if (string != null && !string.equals("")) {
                            CustomRadioDomain.RadioTitle = string;
                        } else if (string2 != null && !string2.equals("")) {
                            CustomRadioDomain.RadioTitle = string2;
                        }
                        RLog.i("NEU_Radio RadioSdk", "Controlfavorite ACTION_ADD_FAVORITE success");
                    } else {
                        String string3 = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_ALREADY_COLLECTED.equals(string3)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "Controlfavorite ACTION_ADD_FAVORITE REASON_ALREADY_COLLECTED==");
                        } else if (MediaControl.REASON_NOT_COLLECTED.equals(string3)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "Controlfavorite ACTION_ADD_FAVORITE REASON_NOT_COLLECTED==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                            RLog.i("NEU_Radio RadioSdk", "Controlfavorite ACTION_ADD_FAVORITE fail==" + string3);
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "Controlfavorite error e==" + e.toString());
            e.printStackTrace();
            return -1;
        }
    }

    public void playRadio() {
        RLog.i("NEU_Radio RadioSdk", "start playRadio ==");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.RadioSdk.16
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(126);
            }
        }).start();
    }

    public void pauseRadio() {
        RLog.i("NEU_Radio RadioSdk", "start pauseRadio ==");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.RadioSdk.17
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(127);
            }
        }).start();
    }

    public int playPreFre(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start playPreFre ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, -1, 21, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.18
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "playPreFre success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        RLog.i("NEU_Radio RadioSdk", "playPreFre fail==" + string);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 0;
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "playPreFre error==" + e.toString());
            return -1;
        }
    }

    public int playNextFre(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start playNextFre ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfRadioShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, -1, 22, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.19
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "playNextFre success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        RLog.i("NEU_Radio RadioSdk", "playNextFre fail==" + string);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 0;
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "playNextFre error==" + e.toString());
            return -1;
        }
    }

    public int Search_orStopSearch(int i, final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start Search_orStopSearch action==" + i);
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (16 == i) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 0, i, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.20
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "Search_orStopSearch success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "Search_orStopSearch duplicated_action==");
                        } else if (MediaControl.REASON_UNSUPPORTED_OPERATION.equals(string)) {
                            CustomRadioDomain.RadioResult = 2;
                            RLog.i("NEU_Radio RadioSdk", "Search_orStopSearch UNSUPPORTED==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_Radio RadioSdk", "Search_orStopSearch fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", " Search_orStopSearch error ==" + e.toString());
            return -1;
        }
    }

    public int openStationList(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Radio RadioSdk", "start openStationList ");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_ALL_LIST);
            VoiceAssistantManager.get().setShowAfterExit(false);
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            MediaControl.controlMedia(this.mContext, 0, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.21
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        RLog.i("NEU_Radio RadioSdk", "openStationList success");
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        CustomRadioDomain.RadioResult = -1;
                        RLog.i("NEU_Radio RadioSdk", "openStationList fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", " openStationList error ==" + e.toString());
            return -1;
        }
    }

    public int openFavoriteStationList(final CountDownLatch countDownLatch, Boolean bool) {
        RLog.i("NEU_Radio RadioSdk", "start openFavoriteStationList ");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_FAVORITES);
            if (bool.booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 0, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.22
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Radio RadioSdk", "openFavoriteStationList success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Radio RadioSdk", "openFavoriteStationList duplicated_action==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_Radio RadioSdk", "openFavoriteStationList fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", "openFavoriteStationList error==" + e.toString());
            return -1;
        }
    }

    public void closeStationList() {
        RLog.i("NEU_Radio RadioSdk", "start closeStationList ");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.RadioSdk.23
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(3);
            }
        }).start();
    }

    public int checkradiomode(final CountDownLatch countDownLatch, double d) {
        RLog.i("NEU_Radio RadioSdk", "start checkradiomode ");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam("radiofreq", String.valueOf(d));
            MediaControl.controlMedia(this.mContext, -1, 74, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.RadioSdk.24
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        CustomRadioDomain.RadioMode = result.getInt("MediaMode", -1);
                        RLog.i("NEU_Radio RadioSdk", "checkradiomode success RadioMode==" + CustomRadioDomain.RadioMode);
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        CustomRadioDomain.RadioResult = -1;
                        RLog.i("NEU_Radio RadioSdk", "openStationList fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Radio RadioSdk", " checkradiomode error ==" + e.toString());
            return -1;
        }
    }
}
