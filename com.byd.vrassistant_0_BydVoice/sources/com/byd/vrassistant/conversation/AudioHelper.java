package com.byd.vrassistant.conversation;

import android.car.media.CarEnhancedAudioManager;
import android.content.Context;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.audiocontrol.param.SetPlayModeParam;
import cerence.ark.assistant.controller.domain.audiocontrol.result.FavoriteControlResult;
import cerence.ark.assistant.controller.domain.audiocontrol.result.SetPlayModeResult;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.PlayModeEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.customizedimpl.CustomAudioControlDomain;
import com.byd.vrassistant.customizedimpl.CustomRadioDomain;
import com.byd.vrassistant.eventbus.FinalOperation;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.VoiceToastStrChangeUtil;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class AudioHelper {
    public int BTPhone_GroupID;
    public int Media_GroupID;
    public int Navi_GroupID;
    private final String TAG;
    private int TimeOut;
    public int VOICE_GroupID;
    private int groupId;
    private CarEnhancedAudioManager mCarEnhAudioManager;
    private Context mContext;

    private AudioHelper() {
        this.TAG = "NEU_Audio AudioHelper";
        BydVoiceApplication context = BydVoiceApplication.getContext();
        this.mContext = context;
        this.TimeOut = 4;
        this.Media_GroupID = BYDAutoAudioDevice.getInstance(context).getVolumeGroupIdForUsage(1);
        this.Navi_GroupID = BYDAutoAudioDevice.getInstance(this.mContext).getVolumeGroupIdForUsage(12);
        this.VOICE_GroupID = BYDAutoAudioDevice.getInstance(this.mContext).getVolumeGroupIdForUsage(16);
        this.BTPhone_GroupID = BYDAutoAudioDevice.getInstance(this.mContext).getVolumeGroupIdForUsage(2);
    }

    private static class AudioHelperHolder {
        private static final AudioHelper audioHelper = new AudioHelper();

        private AudioHelperHolder() {
        }
    }

    public static AudioHelper getInstance(Context context) {
        return AudioHelperHolder.audioHelper;
    }

    public void setPlayModeHelper(Boolean bool, int i, IResponseSender<SetPlayModeResult> iResponseSender, SetPlayModeResult setPlayModeResult) {
        RLog.i("NEU_Audio AudioHelper", "setPlayModeHelper start");
        int playStatus = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus();
        RLog.i("NEU_Audio AudioHelper", "setPlayModeHelper playState==" + playStatus + "needPlay==" + bool);
        try {
            CustomAudioControlDomain.AudioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (AudioSdk.getInstance(this.mContext).setPlayMode(i, countDownLatch) == -1) {
                RLog.i("NEU_Audio AudioHelper", "setPlayModeHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Audio AudioHelper", "setPlayModeHelper AudioResult==" + CustomAudioControlDomain.AudioResult);
            if (CustomAudioControlDomain.AudioResult == 0) {
                if (playStatus != 0 && bool.booleanValue()) {
                    AudioSdk.getInstance(BydVoiceApplication.getContext()).playMusic(1);
                }
                iResponseSender.send(new Response("OK", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【OK】");
                return;
            }
            if (CustomAudioControlDomain.AudioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ERROR】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioHelper", "setPlayModeHelper off e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void ControlfavoriteAudioHelper(int i, IResponseSender<FavoriteControlResult> iResponseSender) {
        int iRemoveFavorite;
        RLog.i("NEU_Audio AudioHelper", "ControlfavoriteAudioHelper start");
        FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
        favoriteControlResult.setMediaProvider(MediaProviderEnum.LOCAL);
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (64 == i) {
                iRemoveFavorite = AudioSdk.getInstance(this.mContext).addFavorite(countDownLatch);
            } else {
                iRemoveFavorite = AudioSdk.getInstance(this.mContext).removeFavorite(countDownLatch);
            }
            if (iRemoveFavorite == -1) {
                RLog.i("NEU_Audio AudioHelper", "ControlfavoriteAudioHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Audio AudioHelper", "ControlfavoriteHelper audio RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                RLog.i("NEU_Audio AudioHelper", "ControlfavoriteHelper audio 111==" + CustomRadioDomain.RadioResult);
                iResponseSender.send(new Response("OK", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                RLog.i("NEU_Audio AudioHelper", "ControlfavoriteHelper audio 222==" + CustomRadioDomain.RadioResult);
                iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                RLog.i("NEU_Audio AudioHelper", "ControlfavoriteHelper audio 333==" + CustomRadioDomain.RadioResult);
                iResponseSender.send(new Response("ALREADY_EXPECTED", favoriteControlResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioHelper", "ControlfavoriteHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR, favoriteControlResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【favoriteControl】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public int ChangeSourceAudioHelper(int i, Boolean bool) {
        CountDownLatch countDownLatch;
        RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper start showUI==" + bool);
        try {
            CustomRadioDomain.RadioResult = -1;
            countDownLatch = new CountDownLatch(1);
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper  e ==" + e.toString());
            CustomRadioDomain.RadioResult = -1;
            e.printStackTrace();
        }
        if (AudioSdk.getInstance(this.mContext).ChangeSource(countDownLatch, i, bool.booleanValue()) == -1) {
            RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper 接口失败 返回-1");
            return -1;
        }
        countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
        RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper audio ==" + CustomRadioDomain.RadioResult);
        if (CustomRadioDomain.RadioResult == 0) {
            RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper audio 111==" + CustomRadioDomain.RadioResult);
        } else if (CustomRadioDomain.RadioResult == -1) {
            RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper audio 222==" + CustomRadioDomain.RadioResult);
        } else if (CustomRadioDomain.RadioResult == 1) {
            RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper audio 333==" + CustomRadioDomain.RadioResult);
        } else if (CustomRadioDomain.RadioResult == 2) {
            RLog.i("NEU_Audio AudioHelper", "ChangeSourceAudioHelper audio 444==" + CustomRadioDomain.RadioResult);
        }
        return CustomRadioDomain.RadioResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x01c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Map<java.lang.Integer, java.lang.Boolean> IfSourceHasSong() {
        /*
            Method dump skipped, instruction units count: 510
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.AudioHelper.IfSourceHasSong():java.util.Map");
    }

    public int openWhiceSourceHasSong(boolean z) {
        int madieRet;
        Map<Integer, Boolean> mapIfSourceHasSong = IfSourceHasSong();
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong SOURCE_LOCAL==" + mapIfSourceHasSong.get(0));
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong SOURCE_USB==" + mapIfSourceHasSong.get(1));
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong SOURCE_SD==" + mapIfSourceHasSong.get(2));
        if (mapIfSourceHasSong.get(0).booleanValue()) {
            madieRet = ChangeSourceAudioHelper(0, Boolean.valueOf(z));
        } else if (mapIfSourceHasSong.get(1).booleanValue()) {
            madieRet = ChangeSourceAudioHelper(1, Boolean.valueOf(z));
        } else if (mapIfSourceHasSong.get(2).booleanValue()) {
            madieRet = ChangeSourceAudioHelper(2, Boolean.valueOf(z));
        } else {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            LocalMediaManager.get().openLocalMediaWithUI(countDownLatch, z);
            try {
                countDownLatch.await(4L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            madieRet = LocalMediaManager.get().getMadieRet();
        }
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong result==" + madieRet);
        return madieRet;
    }

    public int openCurrentOrWhichHasSong() {
        int madieRet;
        Map<Integer, Boolean> mapIfSourceHasSong = IfSourceHasSong();
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong SOURCE_LOCAL==" + mapIfSourceHasSong.get(0));
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong SOURCE_USB==" + mapIfSourceHasSong.get(1));
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong SOURCE_SD==" + mapIfSourceHasSong.get(2));
        if (IfMusicWorking().booleanValue()) {
            if (AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType() == 0) {
                if (mapIfSourceHasSong.get(0).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(0, true);
                } else if (mapIfSourceHasSong.get(1).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(1, true);
                } else if (mapIfSourceHasSong.get(2).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(2, true);
                } else {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    LocalMediaManager.get().openLocalMedia(countDownLatch);
                    try {
                        countDownLatch.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    madieRet = LocalMediaManager.get().getMadieRet();
                }
            } else if (AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType() == 2) {
                if (mapIfSourceHasSong.get(2).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(2, true);
                } else if (mapIfSourceHasSong.get(0).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(0, true);
                } else if (mapIfSourceHasSong.get(1).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(1, true);
                } else {
                    CountDownLatch countDownLatch2 = new CountDownLatch(1);
                    LocalMediaManager.get().openLocalMedia(countDownLatch2);
                    try {
                        countDownLatch2.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    madieRet = LocalMediaManager.get().getMadieRet();
                }
            } else if (AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType() == 1) {
                if (mapIfSourceHasSong.get(1).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(1, true);
                } else if (mapIfSourceHasSong.get(0).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(0, true);
                } else if (mapIfSourceHasSong.get(2).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(2, true);
                } else {
                    CountDownLatch countDownLatch3 = new CountDownLatch(1);
                    LocalMediaManager.get().openLocalMedia(countDownLatch3);
                    try {
                        countDownLatch3.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                    madieRet = LocalMediaManager.get().getMadieRet();
                }
            } else {
                CountDownLatch countDownLatch4 = new CountDownLatch(1);
                LocalMediaManager.get().openLocalMedia(countDownLatch4);
                try {
                    countDownLatch4.await(4L, TimeUnit.SECONDS);
                } catch (InterruptedException e4) {
                    e4.printStackTrace();
                }
                madieRet = LocalMediaManager.get().getMadieRet();
            }
        } else {
            CountDownLatch countDownLatch5 = new CountDownLatch(1);
            LocalMediaManager.get().openLocalMedia(countDownLatch5);
            try {
                countDownLatch5.await(4L, TimeUnit.SECONDS);
            } catch (InterruptedException e5) {
                e5.printStackTrace();
            }
            madieRet = LocalMediaManager.get().getMadieRet();
        }
        RLog.i("NEU_Audio AudioHelper", "openWhiceSourceHasSong result==" + madieRet);
        return madieRet;
    }

    public int ResumeopenCurrentOrWhichHasSong(boolean z) {
        int madieRet;
        Map<Integer, Boolean> mapIfSourceHasSong = IfSourceHasSong();
        RLog.i("NEU_Audio AudioHelper", "ResumeopenCurrentOrWhichHasSong SOURCE_LOCAL==" + mapIfSourceHasSong.get(0));
        RLog.i("NEU_Audio AudioHelper", "ResumeopenCurrentOrWhichHasSong SOURCE_USB==" + mapIfSourceHasSong.get(1));
        RLog.i("NEU_Audio AudioHelper", "ResumeopenCurrentOrWhichHasSong SOURCE_SD==" + mapIfSourceHasSong.get(2));
        if (IfMusicWorking().booleanValue()) {
            if (AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType() == 0) {
                if (mapIfSourceHasSong.get(0).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(0, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(1).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(1, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(2).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(2, Boolean.valueOf(z));
                } else {
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    LocalMediaManager.get().openLocalMediaWithUI(countDownLatch, z);
                    try {
                        countDownLatch.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    madieRet = LocalMediaManager.get().getMadieRet();
                }
            } else if (AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType() == 2) {
                if (mapIfSourceHasSong.get(2).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(2, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(0).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(0, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(1).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(1, Boolean.valueOf(z));
                } else {
                    CountDownLatch countDownLatch2 = new CountDownLatch(1);
                    LocalMediaManager.get().openLocalMediaWithUI(countDownLatch2, z);
                    try {
                        countDownLatch2.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    madieRet = LocalMediaManager.get().getMadieRet();
                }
            } else if (AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType() == 1) {
                if (mapIfSourceHasSong.get(1).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(1, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(0).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(0, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(2).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(2, Boolean.valueOf(z));
                } else {
                    CountDownLatch countDownLatch3 = new CountDownLatch(1);
                    LocalMediaManager.get().openLocalMediaWithUI(countDownLatch3, z);
                    try {
                        countDownLatch3.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e3) {
                        e3.printStackTrace();
                    }
                    madieRet = LocalMediaManager.get().getMadieRet();
                }
            } else if (AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType() == 3) {
                if (mapIfSourceHasSong.get(0).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(0, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(1).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(1, Boolean.valueOf(z));
                } else if (mapIfSourceHasSong.get(2).booleanValue()) {
                    madieRet = ChangeSourceAudioHelper(2, Boolean.valueOf(z));
                } else {
                    CountDownLatch countDownLatch4 = new CountDownLatch(1);
                    LocalMediaManager.get().openLocalMediaWithUI(countDownLatch4, z);
                    try {
                        countDownLatch4.await(4L, TimeUnit.SECONDS);
                    } catch (InterruptedException e4) {
                        e4.printStackTrace();
                    }
                    madieRet = LocalMediaManager.get().getMadieRet();
                }
            } else {
                CountDownLatch countDownLatch5 = new CountDownLatch(1);
                LocalMediaManager.get().openLocalMediaWithUI(countDownLatch5, z);
                try {
                    countDownLatch5.await(4L, TimeUnit.SECONDS);
                } catch (InterruptedException e5) {
                    e5.printStackTrace();
                }
                madieRet = LocalMediaManager.get().getMadieRet();
            }
        } else {
            CountDownLatch countDownLatch6 = new CountDownLatch(1);
            LocalMediaManager.get().openLocalMediaWithUI(countDownLatch6, z);
            try {
                countDownLatch6.await(4L, TimeUnit.SECONDS);
            } catch (InterruptedException e6) {
                e6.printStackTrace();
            }
            madieRet = LocalMediaManager.get().getMadieRet();
        }
        RLog.i("NEU_Audio AudioHelper", "ResumeopenCurrentOrWhichHasSong result==" + madieRet);
        return madieRet;
    }

    public boolean IfLocalHasSong() {
        Map<Integer, Boolean> mapIfSourceHasSong = IfSourceHasSong();
        if (mapIfSourceHasSong.get(0).booleanValue() || mapIfSourceHasSong.get(1).booleanValue() || mapIfSourceHasSong.get(2).booleanValue()) {
            RLog.i("NEU_Audio AudioHelper", "IfLocalHasSong there is song in local");
            return true;
        }
        RLog.i("NEU_Audio AudioHelper", "IfLocalHasSong there is no song in local");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x01e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int next_or_pre_SongForLocalHsaNoMedia(int r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 537
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.AudioHelper.next_or_pre_SongForLocalHsaNoMedia(int, boolean):int");
    }

    public Boolean IfMusicWorking() {
        RLog.i("NEU_Audio AudioHelper", "IfMusicWorking start");
        if ((LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.LOCAL) || LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.SDCARD) || LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.USB)) && RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 1) {
            RLog.i("NEU_Audio AudioHelper", "IfMusicWorking   working");
            return true;
        }
        RLog.i("NEU_Audio AudioHelper", "IfMusicWorking not  working");
        return false;
    }

    public Boolean IfBTMusicWorking() {
        RLog.i("NEU_Audio AudioHelper", "IfBTMusicWorking start");
        if (LocalMediaManager.get().getCurrentMediaProvider().equals("BLUETOOTH")) {
            RLog.i("NEU_Audio AudioHelper", "IfBTMusicWorking   working");
            return true;
        }
        RLog.i("NEU_Audio AudioHelper", "IfBTMusicWorking not  working");
        return false;
    }

    public Boolean IfKaraokeWorking() {
        Log.d("NEU_Audio AudioHelper", "IfKaraokeWorking start");
        if (RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() == 4) {
            Log.d("NEU_Audio AudioHelper", "IfKaraokeWorking true");
            return true;
        }
        Log.d("NEU_Audio AudioHelper", "IfKaraokeWorking false");
        return false;
    }

    public Boolean IfMusicShow() {
        RLog.i("NEU_Audio AudioHelper", "IfMusicShow start");
        if (IfMusicWorking().booleanValue()) {
            return false;
        }
        return true;
    }

    public Boolean IfBTMusicShow() {
        RLog.i("NEU_Audio AudioHelper", "IfBTMusicShow start");
        if (IfBTMusicWorking().booleanValue()) {
            return false;
        }
        return true;
    }

    public Boolean IfLocalMediaWorking() {
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue() || getInstance(BydVoiceApplication.getContext()).IfMusicWorking().booleanValue() || getInstance(BydVoiceApplication.getContext()).IfBTMusicWorking().booleanValue()) {
            RLog.i("NEU_Audio AudioHelper", "IfControlMediaWorking true");
            return true;
        }
        RLog.i("NEU_Audio AudioHelper", "IfControlMediaWorking false");
        return false;
    }

    public void setMuteAll(Boolean bool) {
        RLog.i("NEU_Audio AudioHelper", "MuteAll flag==" + bool);
        BYDAutoAudioDevice.getInstance(this.mContext).setAllMute(bool.booleanValue() ? 1 : 0);
    }

    public boolean getMuteState() {
        boolean z = BYDAutoAudioDevice.getInstance(this.mContext).getAllMute() == 1;
        RLog.i("NEU_Audio AudioHelper", "getMuteState state==" + z);
        return z;
    }

    public void setPlayModeReturnMediaNotFound(SetPlayModeParam setPlayModeParam, Map<Integer, Boolean> map, IResponseSender<SetPlayModeResult> iResponseSender, SetPlayModeResult setPlayModeResult) {
        int localMusicType = AudioSdk.getInstance(BydVoiceApplication.getContext()).getLocalMusicType();
        RLog.i("NEU_Audio AudioHelper", "setPlayMode mediaType==" + localMusicType);
        if (localMusicType == 0 && !map.get(0).booleanValue()) {
            RLog.i("NEU_Audio AudioHelper", "setPlayMode: MEDIA_NOT_FOUND no media");
            setPlayModeResult.setMediaProvider(MediaProviderEnum.LOCAL);
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【MEDIA_NOT_FOUND】");
            return;
        }
        if (localMusicType == 2 && !map.get(2).booleanValue()) {
            RLog.i("NEU_Audio AudioHelper", "setPlayMode: MEDIA_NOT_FOUND no media");
            setPlayModeResult.setMediaProvider(MediaProviderEnum.SDCARD);
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【MEDIA_NOT_FOUND】");
            return;
        }
        if (localMusicType == 1 && !map.get(1).booleanValue()) {
            RLog.i("NEU_Audio AudioHelper", "setPlayMode: MEDIA_NOT_FOUND no media");
            setPlayModeResult.setMediaProvider(MediaProviderEnum.USB);
            iResponseSender.send(new Response(ResultCodeEnum.MEDIA_NOT_FOUND, setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【MEDIA_NOT_FOUND】");
            return;
        }
        if (localMusicType == 0) {
            setPlayModeResult.setMediaProvider(MediaProviderEnum.LOCAL);
        } else if (localMusicType == 2) {
            setPlayModeResult.setMediaProvider(MediaProviderEnum.SDCARD);
        } else if (localMusicType == 1) {
            setPlayModeResult.setMediaProvider(MediaProviderEnum.USB);
        } else {
            setPlayModeResult.setMediaProvider(MediaProviderEnum.NONE);
        }
        int mediaPlayMode = RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaPlayMode();
        Boolean boolValueOf = Boolean.valueOf(setPlayModeParam.getNeedPlay());
        RLog.i("NEU_Audio AudioHelper", "setPlayMode: setPlayModeParam ==" + setPlayModeParam.getPlayMode() + ",needPlay==" + boolValueOf);
        if ("SHUFFLE".equals(setPlayModeParam.getPlayMode())) {
            if (mediaPlayMode == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                return;
            } else {
                getInstance(BydVoiceApplication.getContext()).setPlayModeHelper(boolValueOf, 1, iResponseSender, setPlayModeResult);
                return;
            }
        }
        if (PlayModeEnum.REPEAT_ONE.equals(setPlayModeParam.getPlayMode())) {
            if (mediaPlayMode == 0) {
                iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
                return;
            } else {
                getInstance(BydVoiceApplication.getContext()).setPlayModeHelper(boolValueOf, 2, iResponseSender, setPlayModeResult);
                return;
            }
        }
        if (mediaPlayMode == 5) {
            iResponseSender.send(new Response("ALREADY_EXPECTED", setPlayModeResult));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【setPlayMode】,result【ALREADY_EXPECTED】");
        } else {
            getInstance(BydVoiceApplication.getContext()).setPlayModeHelper(boolValueOf, 0, iResponseSender, setPlayModeResult);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void domaincontrolVolume(cerence.ark.assistant.controller.domain.audiocontrol.param.ControlVolumeParam r17, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r18) {
        /*
            Method dump skipped, instruction units count: 735
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.conversation.AudioHelper.domaincontrolVolume(cerence.ark.assistant.controller.domain.audiocontrol.param.ControlVolumeParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    public int getCurrentVolume(int i) {
        int volume;
        if (i == 1) {
            volume = BYDAutoAudioDevice.getInstance(this.mContext).getVolume();
        } else if (i == 2) {
            volume = BYDAutoAudioDevice.getInstance(this.mContext).getPhoneVolume();
        } else if (i == 12) {
            volume = BYDAutoAudioDevice.getInstance(this.mContext).getNaviVolume();
        } else {
            volume = i != 16 ? 0 : BYDAutoAudioDevice.getInstance(this.mContext).getVoiceAssistVolume();
        }
        RLog.i("NEU_Audio AudioHelper", "getCurrentVolume value=" + volume + ",stream_Type==" + i);
        return volume;
    }

    public int getMinVolume(int i) {
        int groupMinVolume = BYDAutoAudioDevice.getInstance(this.mContext).getGroupMinVolume(BYDAutoAudioDevice.getInstance(this.mContext).getVolumeGroupIdForUsage(i));
        RLog.i("NEU_Audio AudioHelper", "getMinVolume value=" + groupMinVolume + ",type==" + i);
        return groupMinVolume;
    }

    public int getMaxVolume(int i) {
        int groupMaxVolume = BYDAutoAudioDevice.getInstance(this.mContext).getGroupMaxVolume(BYDAutoAudioDevice.getInstance(this.mContext).getVolumeGroupIdForUsage(i));
        RLog.i("NEU_Audio AudioHelper", "getMaxVolume value=" + groupMaxVolume + ",type==" + i);
        return groupMaxVolume;
    }

    public void setVolume(int i, int i2) {
        RLog.i("NEU_Audio AudioHelper", "setVolume value=" + i + ",type==" + i2);
        if (i2 == 1) {
            BYDAutoAudioDevice.getInstance(this.mContext).setVolume(i);
            return;
        }
        if (i2 == 2) {
            BYDAutoAudioDevice.getInstance(this.mContext).setPhoneVolume(i);
        } else if (i2 == 12) {
            BYDAutoAudioDevice.getInstance(this.mContext).setNaviVolume(i);
        } else {
            if (i2 != 16) {
                return;
            }
            BYDAutoAudioDevice.getInstance(this.mContext).setVoiceAssistVolume(i);
        }
    }

    public void getMuteShowToast() {
        boolean muteState = getMuteState();
        Log.d("NEU_Audio AudioHelper", "getMuteShowToast state==" + muteState);
        if (muteState) {
            EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_MUTE), true));
        }
    }

    public void OpenMusicListHelper(IResponseSender<BaseResult> iResponseSender, int i) {
        RLog.i("NEU_Audio AudioHelper", "OpenMusicListHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (AudioSdk.getInstance(this.mContext).openMusicList(countDownLatch, i) == -1) {
                RLog.i("NEU_Audio AudioHelper", "OpenMusicListHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【OpenMusicList】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Audio AudioHelper", "OpenMusicListHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【OpenMusicList】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【OpenMusicList】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【OpenMusicList】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioHelper", "OpenMusicListHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【OpenMusicList】,result【ERROR】");
            e.printStackTrace();
        }
    }

    public void OpenFavoriteMusicListHelper(IResponseSender<BaseResult> iResponseSender, int i) {
        RLog.i("NEU_Audio AudioHelper", "OpenFavoriteMusicListHelper start");
        try {
            CustomRadioDomain.RadioResult = -1;
            CountDownLatch countDownLatch = new CountDownLatch(1);
            if (AudioSdk.getInstance(this.mContext).openFavoriteMusicList(countDownLatch, i) == -1) {
                RLog.i("NEU_Audio AudioHelper", "OpenFavoriteMusicListHelper 接口失败 返回-1");
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlFavoritelist】,result【ERROR】");
                return;
            }
            countDownLatch.await(this.TimeOut, TimeUnit.SECONDS);
            RLog.i("NEU_Audio AudioHelper", "OpenFavoriteMusicListHelper RadioResult==" + CustomRadioDomain.RadioResult);
            if (CustomRadioDomain.RadioResult == 0) {
                iResponseSender.send(new Response("OK"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlFavoritelist】,result【OK】");
            } else if (CustomRadioDomain.RadioResult == -1) {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlFavoritelist】,result【ERROR】");
            } else if (CustomRadioDomain.RadioResult == 1) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlFavoritelist】,result【ALREADY_EXPECTED】");
            }
        } catch (Exception e) {
            RLog.i("NEU_Audio AudioHelper", "OpenFavoriteMusicListHelper  e ==" + e.toString());
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【controlFavoritelist】,result【ERROR】");
            e.printStackTrace();
        }
    }
}
