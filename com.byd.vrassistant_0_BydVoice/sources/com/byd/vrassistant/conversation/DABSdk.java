package com.byd.vrassistant.conversation;

import android.app.Instrumentation;
import android.content.Context;
import com.byd.common.bean.WuwInfoBean;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.customizedimpl.CustomRadioDomain;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public class DABSdk {
    private final String TAG;
    MediaControl.Callback callback;
    private Context mContext;

    private DABSdk() {
        this.TAG = "NEU_DAB DABSdk";
        this.mContext = BydVoiceApplication.getContext();
        this.callback = new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.1
            @Override // com.byd.mediacenter.api.media.MediaControl.Callback
            public void onControlResult(int i, int i2, MediaControl.Result result) {
                RLog.i("NEU_DAB DABSdk", "MediaControl Callback mode==" + i + ",action==" + i2 + WuwInfoBean.ReturnKey + result.getString(MediaControl.PARAM_SUCCESS));
            }
        };
    }

    private static class DABSdkHolder {
        private static final DABSdk dab = new DABSdk();

        private DABSdkHolder() {
        }
    }

    public static DABSdk getInstance(Context context) {
        return DABSdkHolder.dab;
    }

    public int openDAB(final CountDownLatch countDownLatch, boolean z) {
        RLog.i("NEU_DAB DABSdk", "start openDAB");
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (z) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 13, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.2
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_DAB DABSdk", "openDAB success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        RLog.i("NEU_DAB DABSdk", "openDAB fail==" + result.getString(MediaControl.PARAM_REASON));
                        CustomRadioDomain.RadioResult = -1;
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "openDAB ERROR e ==" + e.toString());
            return -1;
        }
    }

    public int muteDAB(final CountDownLatch countDownLatch) {
        RLog.i("NEU_DAB DABSdk", "start muteDAB");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            MediaControl.controlMedia(this.mContext, 13, 20, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.3
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_DAB DABSdk", "muteDAB success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        CustomRadioDomain.RadioResult = -1;
                        RLog.i("NEU_DAB DABSdk", "muteDAB fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "muteDAB error e==" + e.toString());
            return -1;
        }
    }

    public int playFavoriteFreDAB(final CountDownLatch countDownLatch) {
        RLog.i("NEU_DAB DABSdk", "start playFavoriteFreDAB ");
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfDABShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 13, 25, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.4
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_DAB DABSdk", "playFavoriteFreDAB success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_DAB DABSdk", "playFavoriteFreDAB duplicated_action==");
                        } else if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                            CustomRadioDomain.RadioResult = 2;
                            RLog.i("NEU_DAB DABSdk", "playFavoriteFreDAB not_found==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_DAB DABSdk", "playFavoriteFreDAB fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "start playFavoriteFreDAB error==" + e.toString());
            return -1;
        }
    }

    public int ControlfavoriteDAB(int i, final CountDownLatch countDownLatch) {
        RLog.i("NEU_DAB DABSdk", "start ControlfavoriteDAB maction==" + i);
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfDABShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 13, i, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.5
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        RLog.i("NEU_DAB DABSdk", "ControlfavoriteDAB ACTION_ADD_REMOVE_FAVORITE success");
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_DAB DABSdk", "ControlfavoriteDAB ACTION_ADD_REMOVE_FAVORITE duplicated_action==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                            RLog.i("NEU_DAB DABSdk", "ControlfavoriteDAB ACTION_ADD_REMOVE_FAVORITE fail==" + string);
                        }
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "ControlfavoriteDAB error e==" + e.toString());
            return -1;
        }
    }

    public void playDAB() {
        RLog.i("NEU_DAB DABSdk", "start playDAB ==");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.DABSdk.6
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(126);
            }
        }).start();
    }

    public void pauseDAB() {
        RLog.i("NEU_DAB DABSdk", "start pauseDAB ==");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.DABSdk.7
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(127);
            }
        }).start();
    }

    public int playPreFreDAB(final CountDownLatch countDownLatch) {
        RLog.i("NEU_DAB DABSdk", "start playPreFreDAB ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfDABShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 13, 21, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.8
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_DAB DABSdk", "playPreFreDAB success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        RLog.i("NEU_DAB DABSdk", "playPreFreDAB fail==" + result.getString(MediaControl.PARAM_REASON));
                        CustomRadioDomain.RadioResult = -1;
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "playPreFreDAB error==" + e.toString());
            return -1;
        }
    }

    public int playNextFreDAB(final CountDownLatch countDownLatch) {
        RLog.i("NEU_DAB DABSdk", "start playNextFreDAB ==");
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfDABShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 13, 22, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.9
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_DAB DABSdk", "playNextFreDAB success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        RLog.i("NEU_DAB DABSdk", "playNextFreDAB fail==" + result.getString(MediaControl.PARAM_REASON));
                        CustomRadioDomain.RadioResult = -1;
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "playNextFre error==" + e.toString());
            return -1;
        }
    }

    public int openStationListDAB(final CountDownLatch countDownLatch) {
        RLog.i("NEU_DAB DABSdk", "start openStationListDAB ");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_ALL_LIST);
            VoiceAssistantManager.get().setShowAfterExit(false);
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            MediaControl.controlMedia(this.mContext, 13, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.10
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        CustomRadioDomain.RadioResult = 0;
                        RLog.i("NEU_DAB DABSdk", "openStationListDAB success");
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        CustomRadioDomain.RadioResult = -1;
                        RLog.i("NEU_DAB DABSdk", "openStationListDAB fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", " openStationListDAB error ==" + e.toString());
            return -1;
        }
    }

    public int openFavoriteStationListDAB(final CountDownLatch countDownLatch) {
        int i;
        RLog.i("NEU_DAB DABSdk", "start openFavoriteStationListDAB ");
        try {
            MediaControl.Param param = new MediaControl.Param();
            param.setParam(MediaControl.PARAM_NAVIGATION_DEST, MediaControl.NAVIGATION_DEST_FAVORITES);
            i = 0;
            VoiceAssistantManager.get().setShowAfterExit(false);
            param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            MediaControl.controlMedia(this.mContext, 13, 2, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.11
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i2, int i3, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_DAB DABSdk", "openFavoriteStationListDAB success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_DAB DABSdk", "openFavoriteStationListDAB duplicated_action==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_DAB DABSdk", "openFavoriteStationListDAB fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "openFavoriteStationListDAB error==" + e.toString());
            i = -1;
        }
        return i;
    }

    public int playByCategoryDAB(final CountDownLatch countDownLatch, String str) {
        RLog.i("NEU_DAB DABSdk", "start playByCategoryDAB ");
        CustomRadioDomain.RadioResult = -1;
        try {
            MediaControl.Param param = new MediaControl.Param();
            if (RadioHelper.getInstance(this.mContext).IfDABShow().booleanValue()) {
                VoiceAssistantManager.get().setShowAfterExit(false);
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.TRUE);
            } else {
                param.setParam(MediaControl.PARAM_WITH_UI, MediaControl.FALSE);
            }
            MediaControl.controlMedia(this.mContext, 13, 19, param, new MediaControl.Callback() { // from class: com.byd.vrassistant.conversation.DABSdk.12
                @Override // com.byd.mediacenter.api.media.MediaControl.Callback
                public void onControlResult(int i, int i2, MediaControl.Result result) {
                    if (MediaControl.TRUE.equals(result.getString(MediaControl.PARAM_SUCCESS))) {
                        RLog.i("NEU_DAB DABSdk", "playByCategoryDAB success");
                        CustomRadioDomain.RadioResult = 0;
                    } else {
                        String string = result.getString(MediaControl.PARAM_REASON);
                        if (MediaControl.REASON_DUPLICATED_ACTION.equals(string)) {
                            CustomRadioDomain.RadioResult = 1;
                            RLog.i("NEU_DAB DABSdk", "playByCategoryDAB duplicated_action==");
                        } else if (MediaControl.REASON_NOT_FOUND.equals(string)) {
                            CustomRadioDomain.RadioResult = 2;
                            RLog.i("NEU_DAB DABSdk", "playByCategoryDAB not_found==");
                        } else {
                            CustomRadioDomain.RadioResult = -1;
                        }
                        RLog.i("NEU_DAB DABSdk", "playByCategoryDAB fail==" + string);
                    }
                    countDownLatch.countDown();
                }
            });
            return 0;
        } catch (Exception e) {
            RLog.i("NEU_DAB DABSdk", "start playFavoriteFreDAB error==" + e.toString());
            return -1;
        }
    }

    public void closeStationListDAB() {
        RLog.i("NEU_DAB DABSdk", "start closeStationListDAB ");
        new Thread(new Runnable() { // from class: com.byd.vrassistant.conversation.DABSdk.13
            @Override // java.lang.Runnable
            public void run() {
                new Instrumentation().sendKeyDownUpSync(3);
            }
        }).start();
    }
}
