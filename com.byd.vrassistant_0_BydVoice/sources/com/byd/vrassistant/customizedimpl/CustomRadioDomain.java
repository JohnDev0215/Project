package com.byd.vrassistant.customizedimpl;

import android.media.AudioManager;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.radio.AbsRadioDomain;
import cerence.ark.assistant.controller.domain.radio.param.ControlDabParam;
import cerence.ark.assistant.controller.domain.radio.param.ControlRadioParam;
import cerence.ark.assistant.controller.domain.radio.param.FavoriteControlDabParam;
import cerence.ark.assistant.controller.domain.radio.param.FavoriteControlParam;
import cerence.ark.assistant.controller.domain.radio.param.IsFrequencyOutOfRangeParam;
import cerence.ark.assistant.controller.domain.radio.param.PlayRadioByCategoryParam;
import cerence.ark.assistant.controller.domain.radio.param.PlayRadioParam;
import cerence.ark.assistant.controller.domain.radio.param.ScanControlParam;
import cerence.ark.assistant.controller.domain.radio.result.FavoriteControlDabResult;
import cerence.ark.assistant.controller.domain.radio.result.FavoriteControlResult;
import cerence.ark.assistant.controller.domain.radio.result.GetDabSupplyVersionResult;
import cerence.ark.assistant.controller.domain.radio.result.IsFrequencyOutOfRangeResult;
import cerence.ark.assistant.controller.domain.radio.result.IsRadioStationListSupportedResult;
import cerence.ark.assistant.controller.enumeration.DabSupplyVersionEnum;
import cerence.ark.assistant.controller.enumeration.RadioBandEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.DABHelper;
import com.byd.vrassistant.conversation.DABSdk;
import com.byd.vrassistant.conversation.RadioHelper;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class CustomRadioDomain extends AbsRadioDomain {
    public static int RadioMode = -1;
    public static int RadioResult = -1;
    public static String RadioTitle = "";
    private static final String TAG = "NEU_Radio CustomRadioDomain Ver=S.8.12.4";
    public AudioManager audioManager;

    public CustomRadioDomain(Dhi dhi) {
        super(dhi);
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void playRadio(PlayRadioParam playRadioParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】");
        RLog.i(TAG, "playRadio start playRadioParam getFreq ==" + playRadioParam.getRadioInfo().getFreq());
        RLog.i(TAG, "playRadio start playRadioParam getName  ==" + playRadioParam.getRadioInfo().getName());
        RLog.i(TAG, "playRadio start playRadioParam getBand  ==" + playRadioParam.getRadioInfo().getBand());
        int mediaPlayType = RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaPlayType();
        RLog.i(TAG, "playRadio start playRadioParam getCurrentBand FM:1 AM:0 currentband ==" + mediaPlayType);
        if (playRadioParam.getRadioInfo().getFreq() > 0.0d) {
            if (RadioBandEnum.AM.equals(playRadioParam.getRadioInfo().getBand())) {
                int freq = (int) playRadioParam.getRadioInfo().getFreq();
                int i = mediaPlayType == 0 ? -1 : 0;
                RLog.i(TAG, "playRadio start playRadioParam AM mhz ==" + freq);
                RadioHelper.getInstance(BydVoiceApplication.getContext()).PlayFrequencyHelper(freq, i, iResponseSender);
                return;
            }
            if (RadioBandEnum.FM.equals(playRadioParam.getRadioInfo().getBand())) {
                int freq2 = (int) (playRadioParam.getRadioInfo().getFreq() * 1000.0d);
                int i2 = mediaPlayType == 1 ? -1 : 1;
                RLog.i(TAG, "playRadio start playRadioParam FM mhz ==" + freq2);
                RadioHelper.getInstance(BydVoiceApplication.getContext()).PlayFrequencyHelper(freq2, i2, iResponseSender);
                return;
            }
            if (RadioHelper.getInstance(BydVoiceApplication.getContext()).CheckFreModeHelper(playRadioParam.getRadioInfo().getFreq()) == 0) {
                int i3 = RadioMode;
                if (i3 == 11) {
                    int freq3 = (int) (playRadioParam.getRadioInfo().getFreq() * 1000.0d);
                    RLog.i(TAG, "playRadio start playRadioParam Current FM mhz ==" + freq3);
                    RadioHelper.getInstance(BydVoiceApplication.getContext()).PlayFrequencyHelper(freq3, 1, iResponseSender);
                    return;
                } else if (i3 == 12) {
                    int freq4 = (int) playRadioParam.getRadioInfo().getFreq();
                    RLog.i(TAG, "playRadio start playRadioParam Current AM mhz ==" + freq4);
                    RadioHelper.getInstance(BydVoiceApplication.getContext()).PlayFrequencyHelper(freq4, 0, iResponseSender);
                    return;
                } else {
                    RLog.i(TAG, "playRadio start FREQUENCY_UNKNOWN_BAND UNSPECIFIED band");
                    iResponseSender.send(new Response(ResultCodeEnum.FREQUENCY_UNKNOWN_BAND));
                    RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【FREQUENCY_UNKNOWN_BAND】");
                    return;
                }
            }
            RLog.i(TAG, "playRadio start FREQUENCY_INVALID UNSPECIFIED band");
            iResponseSender.send(new Response(ResultCodeEnum.FREQUENCY_INVALID));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【FREQUENCY_INVALID】");
            return;
        }
        if (!"UNSPECIFIED".equals(playRadioParam.getRadioInfo().getBand())) {
            if (RadioBandEnum.AM.equals(playRadioParam.getRadioInfo().getBand())) {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).ChangeBandHelper(0, iResponseSender);
                return;
            } else {
                if (RadioBandEnum.FM.equals(playRadioParam.getRadioInfo().getBand())) {
                    RadioHelper.getInstance(BydVoiceApplication.getContext()).ChangeBandHelper(1, iResponseSender);
                    return;
                }
                return;
            }
        }
        RLog.i(TAG, "playRadio start OUT_OF_RANGE ");
        iResponseSender.send(new Response("OUT_OF_RANGE"));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadio】,result【OUT_OF_RANGE】");
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void controlRadio(ControlRadioParam controlRadioParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】");
        RLog.i(TAG, "controlRadio start controlRadioParam.getAction()==" + controlRadioParam.getAction());
        int mediaMode = RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode();
        String packetAudio = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio();
        int playStatus = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus();
        RLog.i(TAG, "controlRadio mode==" + mediaMode + ",packageName==" + packetAudio + ",playState=" + playStatus);
        if ("ON".equals(controlRadioParam.getAction())) {
            if (!LocalMediaManager.LOCALMEDIA_PACKAGE_NAME.equals(packetAudio)) {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).openRadioHelper(iResponseSender, RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioShow().booleanValue());
                return;
            }
            if (mediaMode != 0) {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).openRadioHelper(iResponseSender, RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioShow().booleanValue());
                return;
            } else if (playStatus == 0) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ALREADY_EXPECTED】");
                return;
            } else {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).openRadioHelper(iResponseSender, RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioShow().booleanValue());
                return;
            }
        }
        if (!LocalMediaManager.LOCALMEDIA_PACKAGE_NAME.equals(packetAudio)) {
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ALREADY_EXPECTED】");
        } else if (mediaMode != 0) {
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ALREADY_EXPECTED】");
        } else if (playStatus == 0) {
            RadioHelper.getInstance(BydVoiceApplication.getContext()).muteRadioHelper(iResponseSender);
        } else {
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlRadio】,result【ALREADY_EXPECTED】");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void controlDab(ControlDabParam controlDabParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlDab】");
        int playStatus = RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus();
        RLog.i(TAG, "controlDab playState=" + playStatus);
        if (controlDabParam.getAction().equals("ON")) {
            if (!RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
                int iSelectDabVersion = RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion();
                if (iSelectDabVersion == 2) {
                    DABHelper.getInstance(BydVoiceApplication.getContext()).openDABHelper(iResponseSender, RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDABShow().booleanValue());
                } else if (iSelectDabVersion == 1) {
                    DABHelper.getInstance(BydVoiceApplication.getContext()).openDAB_ONE_Helper(iResponseSender);
                } else {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                }
            } else if (playStatus == 0) {
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
            } else {
                int iSelectDabVersion2 = RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion();
                if (iSelectDabVersion2 == 2) {
                    DABHelper.getInstance(BydVoiceApplication.getContext()).openDABHelper(iResponseSender, RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDABShow().booleanValue());
                } else if (iSelectDabVersion2 == 1) {
                    DABHelper.getInstance(BydVoiceApplication.getContext()).openDAB_ONE_Helper(iResponseSender);
                } else {
                    iResponseSender.send(new Response("UNSUPPORTED"));
                }
            }
        } else if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue() && playStatus == 0) {
            int iSelectDabVersion3 = RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion();
            if (iSelectDabVersion3 == 2) {
                DABHelper.getInstance(BydVoiceApplication.getContext()).muteDABHelper(iResponseSender);
            } else if (iSelectDabVersion3 == 1) {
                DABSdk.getInstance(BydVoiceApplication.getContext()).pauseDAB();
                iResponseSender.send(new Response("OK"));
            } else {
                iResponseSender.send(new Response("UNSUPPORTED"));
            }
        } else {
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlDab】,result【ALREADY_EXPECTED】");
        }
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【controlDab】,result【ALREADY_EXPECTED】");
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void favoriteControl(FavoriteControlParam favoriteControlParam, IResponseSender<FavoriteControlResult> iResponseSender) {
        RLog.i(TAG, "favoriteControl start favoriteControlParam==" + favoriteControlParam.getControlType());
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】");
        RLog.i(TAG, "favoriteControl mode==" + RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() + ",packageName==" + RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio() + ",playState=" + RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus());
        FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
        favoriteControlResult.setRadioTitle("");
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfRadioWorking().booleanValue()) {
            if ("ADD".equals(favoriteControlParam.getControlType())) {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).ControlfavoriteHelper(64, iResponseSender);
                return;
            } else {
                RadioHelper.getInstance(BydVoiceApplication.getContext()).ControlfavoriteHelper(65, iResponseSender);
                return;
            }
        }
        iResponseSender.send(new Response(ResultCodeEnum.NOT_SUPPORTED_BY_VOICE, favoriteControlResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControl】,result【NOT_SUPPORTED_BY_VOICE】");
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void favoriteControlDab(FavoriteControlDabParam favoriteControlDabParam, IResponseSender<FavoriteControlDabResult> iResponseSender) {
        RLog.d(TAG, "favoriteControl start favoriteControlDab==" + favoriteControlDabParam.getControlType());
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControlDab】");
        RLog.d(TAG, "favoriteControl mode==" + RadioSdk.getInstance(BydVoiceApplication.getContext()).getMediaMode() + ",packageName==" + RadioSdk.getInstance(BydVoiceApplication.getContext()).getPacketAudio() + ",playState=" + RadioSdk.getInstance(BydVoiceApplication.getContext()).getPlayStatus());
        FavoriteControlDabResult favoriteControlDabResult = new FavoriteControlDabResult();
        favoriteControlDabResult.setDabTitle("");
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).IfDabWorking().booleanValue()) {
            if (RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion() == 1) {
                iResponseSender.send(new Response("UNSUPPORTED", favoriteControlDabResult));
                RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControlDab】,result【UNSUPPORTED】");
                return;
            } else if ("ADD".equals(favoriteControlDabParam.getControlType())) {
                DABHelper.getInstance(BydVoiceApplication.getContext()).ControlDABfavoriteHelper(64, iResponseSender);
                return;
            } else {
                DABHelper.getInstance(BydVoiceApplication.getContext()).ControlDABfavoriteHelper(65, iResponseSender);
                return;
            }
        }
        iResponseSender.send(new Response(ResultCodeEnum.NOT_SUPPORTED_BY_VOICE, favoriteControlDabResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【favoriteControlDab】,result【NOT_SUPPORTED_BY_VOICE】");
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void scanControl(ScanControlParam scanControlParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【scanControl】");
        RLog.i(TAG, "scanControl start scanControlParam==" + scanControlParam.getControlType());
        RLog.i(Constant.TAG_FORTEST, "request domain scanControl 8155");
        iResponseSender.send(new Response("UNSUPPORTED"));
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void isRadioStationListSupported(IResponseSender<IsRadioStationListSupportedResult> iResponseSender) {
        RLog.i(TAG, "isRadioStationListSupported start");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【isRadioStationListSupported】");
        IsRadioStationListSupportedResult isRadioStationListSupportedResult = new IsRadioStationListSupportedResult();
        isRadioStationListSupportedResult.setIsSupported(true);
        iResponseSender.send(new Response("OK", isRadioStationListSupportedResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【isRadioStationListSupported】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void isFrequencyOutOfRange(IsFrequencyOutOfRangeParam isFrequencyOutOfRangeParam, IResponseSender<IsFrequencyOutOfRangeResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【isFrequencyOutOfRange】");
        RLog.i(TAG, "isFrequencyOutOfRange start");
        IsFrequencyOutOfRangeResult isFrequencyOutOfRangeResult = new IsFrequencyOutOfRangeResult();
        isFrequencyOutOfRangeResult.setIsOutOfRange(false);
        iResponseSender.send(new Response("OK", isFrequencyOutOfRangeResult));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【isFrequencyOutOfRange】,result【OK】");
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void playRadioByCategory(PlayRadioByCategoryParam playRadioByCategoryParam, IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】");
        RLog.i(TAG, "playRadioByCategory start");
        iResponseSender.send(new Response("UNSUPPORTED"));
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playRadioByCategory】,result【UNSUPPORTED】");
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void playFavRadio(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】");
        RLog.i(TAG, "playFavRadio start");
        RadioHelper.getInstance(BydVoiceApplication.getContext()).playFavRadioHelper(iResponseSender);
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void playFavDab(IResponseSender<BaseResult> iResponseSender) {
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【playFavDab】");
        if (RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion() != 2) {
            iResponseSender.send(new Response("UNSUPPORTED"));
            RLog.i(Constant.TAG_FORTEST, "request domain【CustomRadioDomain】,api【playFavRadio】,result【UNSUPPORTED】");
        } else {
            DABHelper.getInstance(BydVoiceApplication.getContext()).playFavDABHelper(iResponseSender);
        }
    }

    @Override // cerence.ark.assistant.controller.domain.radio.AbsRadioDomain
    protected void getDabSupplyVersion(IResponseSender<GetDabSupplyVersionResult> iResponseSender) {
        GetDabSupplyVersionResult getDabSupplyVersionResult = new GetDabSupplyVersionResult();
        int iSelectDabVersion = RadioHelper.getInstance(BydVoiceApplication.getContext()).selectDabVersion();
        if (iSelectDabVersion == 2) {
            getDabSupplyVersionResult.setDabSupplyVersion(DabSupplyVersionEnum.DAB_SUPPLY_TWO);
        } else if (iSelectDabVersion == 1) {
            getDabSupplyVersionResult.setDabSupplyVersion(DabSupplyVersionEnum.DAB_SUPPLY_ONE);
        } else {
            getDabSupplyVersionResult.setDabSupplyVersion(DabSupplyVersionEnum.DAB_SUPPLY_NONE);
        }
        iResponseSender.send(new Response("OK", getDabSupplyVersionResult));
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        RLog.i(TAG, "activate start");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【activate】");
    }

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
        RLog.i(TAG, "deactivate start");
        RLog.i(Constant.TAG_FORTEST, "request domain【CustomAudioControlDomain】,api【deactivate】");
    }
}
