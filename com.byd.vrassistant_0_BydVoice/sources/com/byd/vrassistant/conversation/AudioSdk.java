package com.byd.vrassistant.conversation;

import android.app.Instrumentation;
import android.content.Context;
import android.media.session.MediaSessionManager;
import android.view.KeyEvent;
import com.byd.common.bean.WuwInfoBean;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.customizedimpl.CustomAudioControlDomain;
import com.byd.vrassistant.customizedimpl.CustomRadioDomain;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class AudioSdk {
    private final String TAG;
    MediaControl.Callback callback;
    public int localMusicType;
    private Context mContext;
    private int mIntCloseMusicListRet;
    private int mIntOpenMusicListRet;

    public int getIntOpenMusicListRet() {
        return this.mIntOpenMusicListRet;
    }

    public int getIntCloseMusicListRet() {
        return this.mIntCloseMusicListRet;
    }

    private AudioSdk() {
        this.TAG = "NEU_Audio AudioSdk";
        this.localMusicType = -1;
        this.mContext = BydVoiceApplication.getContext();
        this.mIntOpenMusicListRet = -1;
        this.mIntCloseMusicListRet = -1;
        this.callback = new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.1
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                RLog.i("NEU_Audio AudioSdk", "MediaControl Callback mode==" + i + ",action==" + i2 + WuwInfoBean.ReturnKey + result.getString(MediaControl.PARAM_SUCCESS));
            }
        };
    }

    private static class AudioSdkHolder {
        private static final AudioSdk audiosdk = new AudioSdk();

        private AudioSdkHolder() {
        }
    }

    public static AudioSdk getInstance(Context context) {
        return AudioSdkHolder.audiosdk;
    }

    public void playMusic() {
        RLog.i("NEU_Audio AudioSdk", "start playAudio ==");
        RLog.i("NEU_Audio AudioSdk", "start playAudio 2222==");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.AudioSdk.2
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(126);
            }
        }).start();
    }

    public void playMusic(int i) {
        RLog.d("NEU_Audio AudioSdk", "start playAudio ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, i, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.3
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.d("NEU_Audio AudioSdk", "playMediaControl success");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
                        return;
                    }
                    String string = result.getString(MediaControl.PARAM_REASON);
                    RLog.d("NEU_Audio AudioSdk", "playMediaControl fail==" + string);
                    if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
                    } else {
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
                    }
                }
            });
            RLog.d("NEU_Audio AudioSdk", "playMediaControl ret==-1");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
        } catch (Exception e) {
            RLog.d("NEU_Audio AudioSdk", "playMediaControl error e==" + e.toString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
        }
    }

    public void pauseMusic() {
        RLog.i("NEU_Audio AudioSdk", "start pauseAudio ==");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.AudioSdk.4
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(127);
            }
        }).start();
    }

    public void pauseMusic(int i) {
        RLog.d("NEU_Audio AudioSdk", "start pauseAudio ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, i, 20, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.5
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.d("NEU_Audio AudioSdk", "PauseMediaControl success");
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【OK】");
                        return;
                    }
                    String string = result.getString(MediaControl.PARAM_REASON);
                    RLog.d("NEU_Audio AudioSdk", "PauseMediaControl fail==" + string);
                    if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ALREADY_EXPECTED】");
                    } else {
                        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
                    }
                }
            });
            RLog.d("NEU_Audio AudioSdk", "PauseMediaControl ret==-1");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
        } catch (Exception e) {
            RLog.d("NEU_Audio AudioSdk", "PauseMediaControl error e==" + e.toString());
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
        }
    }

    public int playPre() {
        RLog.i("NEU_Audio AudioSdk", "start playPre ==");
        MediaSessionManager mediaSessionManager = (MediaSessionManager) this.mContext.getSystemService("media_session");
        KeyEvent keyEvent = new KeyEvent(0, 88);
        KeyEvent keyEvent2 = new KeyEvent(1, 88);
        try {
            Method declaredMethod = Class.forName("android.media.session.MediaSessionManager").getDeclaredMethod("dispatchMediaKeyEvent", KeyEvent.class);
            declaredMethod.invoke(mediaSessionManager, keyEvent);
            declaredMethod.invoke(mediaSessionManager, keyEvent2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void playPre(int i) {
        RLog.i("NEU_Audio AudioSdk", "start playPre ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, i, 21, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.6
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Audio AudioSdk", "playPreStation success");
                    } else {
                        RLog.i("NEU_Audio AudioSdk", "playPreStation fail==" + result.getString(MediaControl.PARAM_REASON));
                    }
                }
            });
            RLog.i("NEU_Audio AudioSdk", "playPreStation ret==-1");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", "playPreStation error==" + e.toString());
        }
    }

    public void playNext(int i) {
        RLog.i("NEU_Audio AudioSdk", "start playNext ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, i, 22, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.7
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Audio AudioSdk", "playNextStation success");
                    } else {
                        RLog.i("NEU_Audio AudioSdk", "playNextStation fail==" + result.getString(MediaControl.PARAM_REASON));
                    }
                }
            });
            RLog.i("NEU_Audio AudioSdk", "playNextStation ret==-1");
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playControl】,result【ERROR】");
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", "playNextStation error==" + e.toString());
        }
    }

    public int playNext() {
        RLog.i("NEU_Audio AudioSdk", "start playNext ==");
        MediaSessionManager mediaSessionManager = (MediaSessionManager) this.mContext.getSystemService("media_session");
        KeyEvent keyEvent = new KeyEvent(0, 87);
        KeyEvent keyEvent2 = new KeyEvent(1, 87);
        try {
            Method declaredMethod = Class.forName("android.media.session.MediaSessionManager").getDeclaredMethod("dispatchMediaKeyEvent", KeyEvent.class);
            declaredMethod.invoke(mediaSessionManager, keyEvent);
            declaredMethod.invoke(mediaSessionManager, keyEvent2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int addFavorite(final CountDownLatch countDownLatch) {
        try {
            RLog.i("NEU_Audio AudioSdk", "start addFavorite ==");
            CustomRadioDomain.RadioResult = -1;
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, -1, 64, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.8
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    RLog.i("NEU_Audio AudioSdk", "addFavorite mode==" + i + ",action==" + i2 + WuwInfoBean.ReturnKey + result.getString(MediaControl.PARAM_SUCCESS));
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        RLog.i("NEU_Audio AudioSdk", "addFavorite success");
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        RLog.i("NEU_Audio AudioSdk", "addFavorite fail==" + string);
                        if (MediaControl.REASON_ALREADY_COLLECTED.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Audio AudioSdk", "addFavorite  REASON_ALREADY_COLLECTED==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                            RLog.i("NEU_Audio AudioSdk", "addFavorite  fail==" + string);
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", "start addFavorite error ==" + e.toString());
            return -1;
        }
    }

    public int removeFavorite(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Audio AudioSdk", "start removeFavorite ==");
        try {
            CustomRadioDomain.RadioResult = -1;
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, -1, 65, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.9
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    RLog.i("NEU_Audio AudioSdk", "removeFavorite mode==" + i + ",action==" + i2 + WuwInfoBean.ReturnKey + result.getString(MediaControl.PARAM_SUCCESS));
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        RLog.i("NEU_Audio AudioSdk", "removeFavorite success");
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        RLog.i("NEU_Audio AudioSdk", "removeFavorite fail==" + string);
                        if (MediaControl.REASON_NOT_COLLECTED.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Audio AudioSdk", "removeFavorite  REASON_NOT_COLLECTED==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                            RLog.i("NEU_Audio AudioSdk", "removeFavorite  fail==" + string);
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", "start removeFavorite error ==" + e.toString());
            return -1;
        }
    }

    public int setPlayMode(int i, final CountDownLatch countDownLatch) {
        RLog.i("NEU_Audio AudioSdk", "start setPlayMode  playMode==" + i);
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (AudioHelper.getInstance(this.mContext).IfMusicShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            param.setParam("pattern", i);
            MediaControl.controlMedia(this.mContext, 1, 61, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.10
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    RLog.i("NEU_Audio AudioSdk", "setPlayMode mode==" + i2 + ",action==" + i3 + WuwInfoBean.ReturnKey + result.getString(MediaControl.PARAM_SUCCESS));
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Audio AudioSdk", "setPlayMode success");
                        CustomAudioControlDomain.AudioResult = 0;
                    } else {
                        RLog.i("NEU_Audio AudioSdk", "setPlayMode fail==" + result.getString(MediaControl.PARAM_REASON));
                        CustomAudioControlDomain.AudioResult = -1;
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", "start setPlayMode  error ==" + e.toString());
            return -1;
        }
    }

    public void playFavoriteMusic() {
        RLog.i("NEU_Audio AudioSdk", "start playFavoriteMusic");
        MediaControl.Param param = new MediaControl.Param();
        if (AudioHelper.getInstance(this.mContext).IfMusicShow().booleanValue()) {
            VoiceAssistantManager.get().setShowAfterExit(false);
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
        } else {
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
        }
        MediaControl.controlMedia(this.mContext, 1, 25, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.11
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                RLog.i("NEU_Audio AudioSdk", "playFavoriteMusic mode==" + i + ",action==" + i2 + WuwInfoBean.ReturnKey + result.getString(MediaControl.PARAM_SUCCESS));
                if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                    RLog.i("NEU_Audio AudioSdk", "playFavoriteMusic success");
                } else {
                    RLog.i("NEU_Audio AudioSdk", "playFavoriteMusic fail==" + result.getString(MediaControl.PARAM_REASON));
                }
            }
        });
    }

    public int openMusicList(final CountDownLatch countDownLatch) {
        RLog.d("NEU_Audio AudioSdk", "start openMusicList ");
        int mediaMode = RadioSdk.getInstance(this.mContext).getMediaMode();
        RLog.d("NEU_Audio AudioSdk", "start openMusicList mode : " + mediaMode);
        MediaControl.Param param = new MediaControl.Param();
        param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_ALL_LIST);
        try {
            MediaControl.controlMedia(this.mContext, mediaMode, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.12
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.d("NEU_Audio AudioSdk", "openMusicList success");
                        AudioSdk.this.mIntOpenMusicListRet = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                            AudioSdk.this.mIntOpenMusicListRet = 2;
                        } else if (MediaControl.REASON_UNSUPPORTED_OPERATION.equals(string)) {
                            AudioSdk.this.mIntOpenMusicListRet = 1;
                        } else {
                            AudioSdk.this.mIntOpenMusicListRet = -1;
                        }
                        RLog.d("NEU_Audio AudioSdk", "openMusicList fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.d("NEU_Audio AudioSdk", "openMusicList error==" + e.toString());
            return -1;
        }
    }

    public int closeMusicList(final CountDownLatch countDownLatch) {
        RLog.d("NEU_Audio AudioSdk", "start closeMusicList ");
        int mediaMode = RadioSdk.getInstance(this.mContext).getMediaMode();
        RLog.d("NEU_Audio AudioSdk", "start closeMusicList mode : " + mediaMode);
        MediaControl.Param param = new MediaControl.Param();
        param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_ALL_LIST);
        param.setParam(MediaControl.PARAM_NAVIGATION_IS_CLOSE, MediaControl.TRUE);
        try {
            MediaControl.controlMedia(this.mContext, mediaMode, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.13
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.d("NEU_Audio AudioSdk", "closeMusicList success");
                        AudioSdk.this.mIntCloseMusicListRet = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                            AudioSdk.this.mIntCloseMusicListRet = 2;
                        } else if (MediaControl.REASON_UNSUPPORTED_OPERATION.equals(string)) {
                            AudioSdk.this.mIntCloseMusicListRet = 1;
                        } else {
                            AudioSdk.this.mIntCloseMusicListRet = -1;
                        }
                        RLog.d("NEU_Audio AudioSdk", "closeMusicList fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.d("NEU_Audio AudioSdk", "closeMusicList error==" + e.toString());
            return -1;
        }
    }

    public int ChangeSource(final CountDownLatch countDownLatch, int i, boolean z) {
        RLog.i("NEU_Audio AudioSdk", "start ChangeSource showUI==" + z);
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (z) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            param.setParam("source", i);
            MediaControl.controlMedia(this.mContext, 1, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.14
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_Audio AudioSdk", "ChangeSource success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_Audio AudioSdk", "ChangeSource duplicated_action==");
                        } else if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                            CustomRadioDomain.RadioResult = 2;
                            RLog.i("NEU_Audio AudioSdk", "ChangeSource not_found==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_Audio AudioSdk", "ChangeSouce fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", "ChangeSouce error==" + e.toString());
            return -1;
        }
    }

    public void showVolumeBar() {
    }

    public int getLocalMusicType() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            getCallBackMusicType(countDownLatch);
            countDownLatch.await(4L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = this.localMusicType;
        RLog.i("NEU_Audio AudioSdk", "getLocalMusicType ==" + i);
        return i;
    }

    public void getCallBackMusicType(final CountDownLatch countDownLatch) {
        RLog.i("NEU_Audio AudioSdk", "getCallBackMusicType  start localMusicType = " + this.localMusicType);
        MediaControl.getInstance(BydVoiceApplication.getContext()).controlMedia(-1, 70, null, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.15
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                String string = result.getResult().getString(MediaControl.PARAM_SUCCESS);
                RLog.i("NEU_Audio AudioSdk", "getCallBackMusicType get result =" + string);
                if (string.equals(MediaControl.TRUE)) {
                    AudioSdk.this.localMusicType = result.getResult().getInt(MediaControl.PARAM_MEDIA_TYPE);
                    RLog.i("NEU_Audio AudioSdk", "getCallBackMusicType mode = " + result.getResult().getInt(MediaControl.PARAM_MEDIA_MODE));
                    RLog.i("NEU_Audio AudioSdk", "getCallBackMusicType type =" + AudioSdk.this.localMusicType);
                    RLog.i("NEU_Audio AudioSdk", "getCallBackMusicType play state =" + result.getResult().getInt(MediaControl.PARAM_MEDIA_PLAY_STATE));
                } else {
                    RLog.i("NEU_Audio AudioSdk", "ACTION_GET_MEDIA_STATUS fail ret =" + result.getResult().getString(MediaControl.PARAM_REASON));
                    AudioSdk.this.localMusicType = -1;
                }
                countDownLatch.countDown();
            }
        });
    }

    public int openMusicList(final CountDownLatch countDownLatch, int i) {
        RLog.i("NEU_Audio AudioSdk", "start openMusicList ");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_ALL_LIST);
            param.setParam("source", i);
            VoiceAssistantManager.get().setShowAfterExit(false);
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            MediaControl.controlMedia(this.mContext, 1, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.16
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        RLog.i("NEU_Audio AudioSdk", "openMusicList success");
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        CustomRadioDomain.RadioResult = -1;
                        RLog.i("NEU_Audio AudioSdk", "openMusicList fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", " openMusicList error ==" + e.toString());
            return -1;
        }
    }

    public int openFavoriteMusicList(final CountDownLatch countDownLatch, int i) {
        RLog.i("NEU_Audio AudioSdk", "start openFavoriteMusicList ");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_FAVORITES);
            param.setParam("source", i);
            VoiceAssistantManager.get().setShowAfterExit(false);
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            MediaControl.controlMedia(this.mContext, 1, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.AudioSdk.17
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        RLog.i("NEU_Audio AudioSdk", "openFavoriteMusicList success");
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        CustomRadioDomain.RadioResult = -1;
                        RLog.i("NEU_Audio AudioSdk", "openFavoriteMusicList fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioSdk", " openFavoriteMusicList error ==" + e.toString());
            return -1;
        }
    }
}
