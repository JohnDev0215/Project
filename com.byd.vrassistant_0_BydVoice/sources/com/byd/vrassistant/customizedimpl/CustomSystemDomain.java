package com.byd.vrassistant.customizedimpl;

import android.content.pm.PackageManager;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.provider.Settings;
import android.util.Log;
import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.api.Dhi;
import cerence.ark.assistant.api.IResponseSender;
import cerence.ark.assistant.api.Response;
import cerence.ark.assistant.controller.domain.system.AbsSystemDomain;
import cerence.ark.assistant.controller.domain.system.param.CheckShortcutAvailableParam;
import cerence.ark.assistant.controller.domain.system.param.GetEnabledAudioZonesParam;
import cerence.ark.assistant.controller.domain.system.param.GetExternalVoiceAssistantStatusParam;
import cerence.ark.assistant.controller.domain.system.param.HideScreenParam;
import cerence.ark.assistant.controller.domain.system.result.CheckShortcutAvailableResult;
import cerence.ark.assistant.controller.domain.system.result.GetEnabledAudioZonesResult;
import cerence.ark.assistant.controller.domain.system.result.GetExternalVoiceAssistantStatusResult;
import cerence.ark.assistant.controller.domain.system.result.GetNetworkStatusResult;
import cerence.ark.assistant.controller.domain.system.result.QueryHUForbidStatusResult;
import cerence.ark.assistant.controller.domain.vehiclecontrol.result.CheckCPAAInForegroundResult;
import cerence.ark.assistant.controller.enumeration.AsrModeEnum;
import cerence.ark.assistant.controller.enumeration.CPAAStateEnum;
import cerence.ark.assistant.controller.enumeration.HUForbidStateEnum;
import cerence.ark.assistant.controller.enumeration.MediaProviderEnum;
import cerence.ark.assistant.controller.enumeration.ResultCodeEnum;
import cerence.ark.assistant.controller.enumeration.ScreenNameEnum;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import cerence.ark.assistant.controller.enumeration.checkSortCutCmdEnum;
import cerence.ark.assistant.controller.enumeration.extVrTypeEnum;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.conversation.AudioHelper;
import com.byd.vrassistant.conversation.HvacCheckHelper;
import com.byd.vrassistant.conversation.NaviHelper;
import com.byd.vrassistant.conversation.PhoneHelper;
import com.byd.vrassistant.conversation.VehicleBasicHelper;
import com.byd.vrassistant.conversation.VehicleSettingsHelper;
import com.byd.vrassistant.conversation.ZoomCarHelper;
import com.byd.vrassistant.eventbus.FinalOperation;
import com.byd.vrassistant.manager.DataManager;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.manager.SpotifyManager;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.receiver.AudioMicReceiver;
import com.byd.vrassistant.utils.DataUtils;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.InternetConnectionJudge;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import com.byd.vrassistant.utils.VoiceToastStrChangeUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class CustomSystemDomain extends AbsSystemDomain {
    public static String TAG = "NEU_CustomSystemDomain";
    public static boolean isPhoneShortCut = false;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    private boolean hasFocus;
    boolean isCpaaForeground;
    private AudioFocusRequest mFocusRequest;
    private PackageManager packageManager;
    TestLogCat testLogCat;

    @Override // cerence.ark.assistant.api.Activated
    public void deactivate() {
    }

    public static class TestLogCat {
        String domain;
        String method;

        public TestLogCat(String str) {
            this.domain = str;
        }

        public void setMethod(String str) {
            this.method = str;
            RLog.i(Constant.TAG_FORTEST, "request domain [" + this.domain + "] , api [" + str + "]");
        }

        public void setResponse(String str) {
            RLog.i(Constant.TAG_FORTEST, "response  domain [" + this.domain + "] , api [" + this.method + "]  ,result [" + str + "]");
        }
    }

    public CustomSystemDomain(Dhi dhi) {
        super(dhi);
        this.mFocusRequest = null;
        this.hasFocus = false;
        this.testLogCat = new TestLogCat("system");
        this.isCpaaForeground = false;
        this.audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() { // from class: com.byd.vrassistant.customizedimpl.CustomSystemDomain.1
            public String toString() {
                return "----VR Audio---";
            }

            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                RLog.i(CustomSystemDomain.TAG, "----onAudioFocusChange----" + i);
                if (i == -1 || i == -2) {
                    RLog.i(CustomSystemDomain.TAG, "----AUDIOFOCUS_LOSS");
                    CustomSystemDomain.this.hasFocus = false;
                    SystemSettingManager.get().checkVoiceAssistantAudioFocusChange(0);
                } else if (i == 1 || i == 2) {
                    RLog.i(CustomSystemDomain.TAG, "----AUDIOFOCUS_GAIN");
                    CustomSystemDomain.this.hasFocus = true;
                    SystemSettingManager.get().checkVoiceAssistantAudioFocusChange(1);
                }
            }
        };
    }

    private AudioFocusRequest requestAudioFocus(int i) {
        RLog.i(TAG, "---requestAudioFocus---usage: " + i);
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(2).setAudioAttributes(new AudioAttributes.Builder().setUsage(i).build()).setOnAudioFocusChangeListener(this.audioFocusChangeListener).build();
        int iRequestAudioFocus = SystemSettingManager.get().audioManager.requestAudioFocus(audioFocusRequestBuild);
        RLog.i(TAG, "---requestAudioFocus---result: " + iRequestAudioFocus);
        if (iRequestAudioFocus == 0) {
            RLog.i(TAG, "requestAudioFocus FAILED");
            this.hasFocus = false;
        } else if (iRequestAudioFocus == 1) {
            RLog.i(TAG, "requestAudioFocus GRANTED");
            this.hasFocus = true;
        } else if (iRequestAudioFocus == 2) {
            RLog.i(TAG, "requestAudioFocus DELAYED");
            this.hasFocus = false;
        }
        return audioFocusRequestBuild;
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void getNetworkStatus(IResponseSender<GetNetworkStatusResult> iResponseSender) {
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        GetNetworkStatusResult getNetworkStatusResult = new GetNetworkStatusResult();
        String str = InternetConnectionJudge.getInstance().isThereInternetConnection() ? "CONNECTED" : "DISCONNECTED";
        getNetworkStatusResult.setStatus(str);
        RLog.i(TAG, "----getNetworkStatus : ".concat(str));
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK", getNetworkStatusResult));
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void requestAudioFocus(IResponseSender<BaseResult> iResponseSender) {
        RLog.e(TAG, "----requestAudioFocus :  VehicleType=" + SystemPropertiesUtils.getVehicleType());
        RLog.e(TAG, "This is platform =8155");
        CheckCPAAInForegroundResult checkCPAAInForegroundResultCheckCPAAInForeground = SystemSettingManager.get().checkCPAAInForeground(ForegroundAppUtil.getTopActivityPackage());
        boolean isEnableDLDH = DataManager.get().getIsEnableDLDH();
        if (checkCPAAInForegroundResultCheckCPAAInForeground.getState() == CPAAStateEnum.AA_FOREGROUND || (checkCPAAInForegroundResultCheckCPAAInForeground.getState() == CPAAStateEnum.CP_FOREGROUND && isEnableDLDH)) {
            RLog.e(TAG, "cpaa foreground ... setSnoozingDuration 0 isDLDH:" + isEnableDLDH);
            this.isCpaaForeground = true;
        } else {
            this.isCpaaForeground = false;
        }
        bindZoomAidlService();
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        boolean z = SystemSettingManager.get().isCPConnect() && (SystemSettingManager.get().isCarplayCallSending() || SystemSettingManager.get().isCarplayCallOthers() || SystemSettingManager.get().isCarplayCallRinging());
        RLog.e(TAG, "isHardKeyPTTEvent " + VoiceAssistantManager.get().isHardKeyPTTEvent());
        RLog.e(TAG, "isVirtualKeyPTTEvent " + VoiceAssistantManager.get().isVirtualKeyPTTEvent());
        RLog.e(TAG, "isCarplayRelease " + AudioMicReceiver.isCarplayRelease);
        RLog.e(TAG, "carplayCallResult " + z);
        RLog.e(TAG, "carplayCallSendingState " + SystemSettingManager.carplayCallSendingState);
        RLog.e(TAG, "isCPConnect " + SystemSettingManager.get().isCPConnect());
        if (!AudioMicReceiver.isCarplayRelease || z) {
            if (z && (VoiceAssistantManager.get().isHardKeyPTTEvent() || VoiceAssistantManager.get().isVirtualKeyPTTEvent() || (SystemSettingManager.carplayCallSendingState != 3 && SystemSettingManager.carplayCallSendingState != 4))) {
                EventBus.getDefault().post(new FinalOperation(VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_OCCUPANCY), true));
            }
            this.testLogCat.setResponse(ResultCodeEnum.ERROR);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
            VoiceAssistantManager.get().setVirtualKeyPTTEvent(false);
            VoiceAssistantManager.get().setHardKeyPTTEvent(false);
            return;
        }
        SystemSettingManager.get().getDiLinkUi(BydVoiceApplication.getContext());
        LocalMediaManager.get().saveCurrentAudioPackage();
        LocalMediaManager.get().saveCurrentPlayingState();
        SpotifyManager.get().saveCurrentPlayingState();
        LocalMediaManager.get().setSavedStreamType(0);
        this.mFocusRequest = requestAudioFocus(16);
        RLog.i(TAG, "request AudioFocusResult==hasFocus:" + this.hasFocus);
        String currentAudioPackage = LocalMediaManager.get().getCurrentAudioPackage();
        if (!this.hasFocus && !Arrays.asList(Constant.AUDIOFOCUS_FAIL_VR_MAY_USE).contains(currentAudioPackage)) {
            this.testLogCat.setResponse(ResultCodeEnum.ERROR);
            iResponseSender.send(new Response(ResultCodeEnum.ERROR));
        } else {
            this.testLogCat.setResponse("OK");
            iResponseSender.send(new Response("OK"));
        }
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void releaseAudioFocus(IResponseSender<BaseResult> iResponseSender) {
        RLog.e(TAG, "----releaseAudioFocus : VehicleType=" + SystemPropertiesUtils.getVehicleType());
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        RLog.e(TAG, "releaseAudioFocus,isCpaaForeground :" + this.isCpaaForeground);
        if (this.isCpaaForeground) {
            VoiceAssistantManager.get().sendCancelPTTEvent();
        }
        if (this.mFocusRequest != null) {
            int iAbandonAudioFocusRequest = SystemSettingManager.get().audioManager.abandonAudioFocusRequest(this.mFocusRequest);
            RLog.i(TAG, "abandonAudioFocusRequest========" + iAbandonAudioFocusRequest);
            if (iAbandonAudioFocusRequest == 1) {
                RLog.i(TAG, "release AudioFocusResult==AUDIOFOCUS_REQUEST_GRANTED");
            } else if (iAbandonAudioFocusRequest == 0) {
                RLog.i(TAG, "release AudioFocusResult==AUDIOFOCUS_REQUEST_FAILED");
            }
            this.mFocusRequest = null;
        }
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK"));
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void checkShortcutAvailable(CheckShortcutAvailableParam checkShortcutAvailableParam, IResponseSender<CheckShortcutAvailableResult> iResponseSender) {
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        try {
            RLog.e(TAG, "checkShortcutAvailable:" + checkShortcutAvailableParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CheckShortcutAvailableResult checkShortcutAvailableResult = new CheckShortcutAvailableResult();
        if (Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_PRIVACY_POLICY").equals("false")) {
            checkShortcutAvailableResult.setIsAvailable(false);
            iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
            this.testLogCat.setResponse("OK");
            return;
        }
        if (checkShortcutAvailableParam.getShortcutCmd().equals(checkSortCutCmdEnum.RETURN_TO_HOMEPAGE)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("com.android.launcher3");
            if (!BydVoiceApplication.isApplicationForeground(arrayList)) {
                RLog.e(TAG, "is not at top home page");
                SystemSettingManager.get().setTrackVolumeDuck(true);
                checkShortcutAvailableResult.setIsAvailable(true);
                iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
                this.testLogCat.setResponse("OK");
                return;
            }
            RLog.e(TAG, "is already home page");
            checkShortcutAvailableResult.setIsAvailable(false);
            iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
            this.testLogCat.setResponse("OK");
            return;
        }
        if (checkShortcutAvailableParam.getShortcutCmd().equals(checkSortCutCmdEnum.MUSIC_CMD)) {
            RLog.i(TAG, "isShortCutWakeup MUSIC_CMD");
            if (LocalMediaManager.get().getCurrentMediaProvider().equals(MediaProviderEnum.NONE)) {
                if (LocalMediaManager.get().getRecentMediaProvider().equals(MediaProviderEnum.SPOTIFY)) {
                    RLog.i(TAG, "isShortCutWakeup MUSIC_CMD true");
                    checkShortcutAvailableResult.setIsAvailable(true);
                } else {
                    RLog.i(TAG, "isShortCutWakeup MUSIC_CMD false");
                    checkShortcutAvailableResult.setIsAvailable(false);
                }
                iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
            } else {
                RLog.i(TAG, "isShortCutWakeup MUSIC_CMD true");
                checkShortcutAvailableResult.setIsAvailable(true);
                SystemSettingManager.get().setTrackVolumeDuck(true);
                iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
            }
        } else if (checkShortcutAvailableParam.getShortcutCmd().equals(checkSortCutCmdEnum.TURN_OFF_AC)) {
            boolean zCheckAcOff = HvacCheckHelper.getInstance().checkAcOff();
            if (zCheckAcOff) {
                SystemSettingManager.get().setTrackVolumeDuck(true);
            }
            checkShortcutAvailableResult.setIsAvailable(zCheckAcOff);
            iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
        } else if (checkShortcutAvailableParam.getShortcutCmd().equals(checkSortCutCmdEnum.TURN_ON_AC)) {
            boolean zCheckAcOn = HvacCheckHelper.getInstance().checkAcOn();
            if (zCheckAcOn) {
                SystemSettingManager.get().setTrackVolumeDuck(true);
            }
            checkShortcutAvailableResult.setIsAvailable(zCheckAcOn);
            iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
        } else if (checkShortcutAvailableParam.getShortcutCmd().equals(checkSortCutCmdEnum.PHONE_CMD)) {
            boolean zCheckCommandPhone = PhoneHelper.getInstance().checkCommandPhone();
            if (zCheckCommandPhone) {
                SystemSettingManager.get().setTrackVolumeDuck(true);
                isPhoneShortCut = true;
            }
            RLog.i(TAG, "checkCommandPhone  isavailable :" + zCheckCommandPhone);
            checkShortcutAvailableResult.setIsAvailable(zCheckCommandPhone);
            iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
        } else if (checkShortcutAvailableParam.getShortcutCmd().equals(checkSortCutCmdEnum.MAP_CMD)) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Constant.NEU_NAVIPACKAGE);
            arrayList2.add(Constant.ZAMP_NAVIPACKAGE);
            arrayList2.add(Constant.KOR_NAVIPACKAGE);
            if (BydVoiceApplication.isApplicationForeground(arrayList2)) {
                boolean z = NaviHelper.getInstance().getMapAppStatus() == 1;
                Log.e(TAG, "checkCommandNavigation : " + z);
                checkShortcutAvailableResult.setIsAvailable(z);
            } else {
                Log.e(TAG, "checkCommandNavigation2 : false");
                checkShortcutAvailableResult.setIsAvailable(false);
            }
            iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
        } else {
            SystemSettingManager.get().setTrackVolumeDuck(true);
            checkShortcutAvailableResult.setIsAvailable(true);
            iResponseSender.send(new Response("OK", checkShortcutAvailableResult));
        }
        this.testLogCat.setResponse("OK");
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void cancel(IResponseSender<BaseResult> iResponseSender) {
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        iResponseSender.send(new Response("OK"));
        this.testLogCat.setResponse("OK");
        int pOIDetailStatus = NaviHelper.getInstance().getPOIDetailStatus();
        if (pOIDetailStatus == 1 || pOIDetailStatus == 2) {
            NaviHelper.getInstance().reqClosePOIDetail();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0570  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0588  */
    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void showScreen(cerence.ark.assistant.controller.domain.system.param.ShowScreenParam r12, cerence.ark.assistant.api.IResponseSender<cerence.ark.assistant.api.BaseResult> r13) {
        /*
            Method dump skipped, instruction units count: 1772
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrassistant.customizedimpl.CustomSystemDomain.showScreen(cerence.ark.assistant.controller.domain.system.param.ShowScreenParam, cerence.ark.assistant.api.IResponseSender):void");
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void hideScreen(HideScreenParam hideScreenParam, IResponseSender<BaseResult> iResponseSender) {
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        try {
            RLog.e(TAG, "hideScreen:" + hideScreenParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (ScreenNameEnum.MAP_SETTING.equals(hideScreenParam.getScreenName())) {
            if (-4 == NaviHelper.getInstance().getMapAppStatus()) {
                iResponseSender.send(new Response(ResultCodeEnum.NAVI_AUTH_INAVLID));
                return;
            }
            int mapSettingStatus = NaviHelper.getInstance().getMapSettingStatus();
            if (mapSettingStatus != 1) {
                if (mapSettingStatus == 0) {
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    this.testLogCat.setResponse("ALREADY_EXPECTED");
                    RLog.i(TAG, "reqSwitchMapSetting 0: ALREADY_EXPECTED");
                    return;
                } else {
                    iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                    this.testLogCat.setResponse(ResultCodeEnum.ERROR);
                    RLog.d(TAG, "reqSwitchMapSetting 0: ERROR2");
                    return;
                }
            }
            int iReqSwitchMapSetting = NaviHelper.getInstance().reqSwitchMapSetting(0);
            if (iReqSwitchMapSetting == 1) {
                iResponseSender.send(new Response("OK"));
                this.testLogCat.setResponse("OK");
                RLog.i(TAG, "reqSwitchMapSetting 0: OK");
                return;
            } else if (-2 == iReqSwitchMapSetting) {
                iResponseSender.send(new Response("UNSUPPORTED"));
                RLog.i(TAG, "reqSwitchMapSetting UNSUPPORTED");
                this.testLogCat.setResponse("UNSUPPORTED");
                return;
            } else {
                iResponseSender.send(new Response(ResultCodeEnum.ERROR));
                this.testLogCat.setResponse(ResultCodeEnum.ERROR);
                RLog.i(TAG, "reqSwitchMapSetting 0: ERROR1");
                return;
            }
        }
        if (ScreenNameEnum.VOICE_LANG_SETTING.equals(hideScreenParam.getScreenName())) {
            VoiceAssistantManager.get().sendExitSplitScreen();
            if (BydVoiceApplication.isActivityTopAndRun("com.byd.vrsettings.activity.OtaUpgradeActivity")) {
                SystemSettingManager.get().returnToHomePage();
                iResponseSender.send(new Response("OK"));
                this.testLogCat.setResponse("OK");
                return;
            } else {
                RLog.d(TAG, "is already home page");
                iResponseSender.send(new Response("ALREADY_EXPECTED"));
                this.testLogCat.setResponse("ALREADY_EXPECTED");
                return;
            }
        }
        if (ScreenNameEnum.FRAGRANCE_SETTING.equals(hideScreenParam.getScreenName())) {
            if (VehicleBasicHelper.getInstance().checkFragrance()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add("com.android.launcher3");
                arrayList.add("com.android.systemui");
                VoiceAssistantManager.get().sendExitSplitScreen();
                if (!BydVoiceApplication.isApplicationForeground(arrayList)) {
                    SystemSettingManager.get().returnToHomePage();
                    iResponseSender.send(new Response("OK"));
                    this.testLogCat.setResponse("OK");
                    return;
                } else {
                    RLog.e(TAG, "is already home page");
                    iResponseSender.send(new Response("ALREADY_EXPECTED"));
                    this.testLogCat.setResponse("ALREADY_EXPECTED");
                    return;
                }
            }
            iResponseSender.send(new Response("UNSUPPORTED"));
            this.testLogCat.setResponse("UNSUPPORTED");
            return;
        }
        if (ScreenNameEnum.SOC_SETTING.equals(hideScreenParam.getScreenName())) {
            iResponseSender.send(new Response("UNSUPPORTED"));
            this.testLogCat.setResponse("UNSUPPORTED");
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("com.android.launcher3");
        arrayList2.add("com.android.systemui");
        VoiceAssistantManager.get().sendExitSplitScreen();
        if (!BydVoiceApplication.isApplicationForeground(arrayList2)) {
            SystemSettingManager.get().returnToHomePage();
            iResponseSender.send(new Response("OK"));
            this.testLogCat.setResponse("OK");
        } else {
            RLog.e(TAG, "is already home page");
            iResponseSender.send(new Response("ALREADY_EXPECTED"));
            this.testLogCat.setResponse("ALREADY_EXPECTED");
        }
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void queryHUForbidStatus(IResponseSender<QueryHUForbidStatusResult> iResponseSender) {
        String toastString;
        this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
        QueryHUForbidStatusResult queryHUForbidStatusResult = new QueryHUForbidStatusResult();
        queryHUForbidStatusResult.setStatus(HUForbidStateEnum.READY);
        int hUForbidStatus = SystemSettingManager.get().getHUForbidStatus();
        RLog.e(TAG, "queryHUForbidStatus:" + hUForbidStatus);
        toastString = "";
        switch (hUForbidStatus) {
            case 0:
                AudioHelper.getInstance(BydVoiceApplication.getContext()).getMuteShowToast();
                break;
            case 1:
                toastString = VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.REVERSE);
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.CARREVERSE);
                break;
            case 2:
                if (isPhoneShortCut) {
                    isPhoneShortCut = false;
                    iResponseSender.send(new Response("OK", queryHUForbidStatusResult));
                    return;
                } else if (VoiceAssistantManager.get().isHardKeyPTTEvent() || VoiceAssistantManager.get().isVirtualKeyPTTEvent() || (SystemSettingManager.carplayCallSendingState != 3 && SystemSettingManager.carplayCallSendingState != 4)) {
                    toastString = VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_OCCUPANCY);
                    queryHUForbidStatusResult.setStatus(HUForbidStateEnum.MICOCCUPIED);
                }
                break;
            case 3:
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.BOOTING);
                toastString = VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.BOOT_NOT_FINISHED);
                break;
            case 4:
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.REGISTING);
                toastString = VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.REGISTRATION_IS_NOT_COMPLETE);
                break;
            case 5:
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.LOCKED);
                EventBus.getDefault().post(new FinalOperation("PRIVACY", false));
                break;
            case 6:
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.LOCKED);
                break;
            case 7:
                toastString = VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_OCCUPANCY);
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.MICOCCUPIED);
                break;
            case 8:
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.CARREVERSE);
                break;
            case 9:
                Log.e(TAG, ":queryHUForbidStatus zoom car is forbid");
                toastString = (VoiceAssistantManager.get().isHardKeyPTTEvent() || VoiceAssistantManager.get().isVirtualKeyPTTEvent()) ? VoiceToastStrChangeUtil.getToastString(VoiceToastStrChangeUtil.ToastString.AUDIO_OCCUPANCY) : "";
                queryHUForbidStatusResult.setStatus(HUForbidStateEnum.MICOCCUPIED);
                break;
        }
        if (!toastString.isEmpty()) {
            EventBus.getDefault().post(new FinalOperation(toastString, true));
        }
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK", queryHUForbidStatusResult));
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void getEnabledAudioZones(GetEnabledAudioZonesParam getEnabledAudioZonesParam, IResponseSender<GetEnabledAudioZonesResult> iResponseSender) {
        try {
            this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
            RLog.e(TAG, "getEnabledAudioZones:" + getEnabledAudioZonesParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        String mode = getEnabledAudioZonesParam.getMode();
        String activeZone = getEnabledAudioZonesParam.getActiveZone();
        GetEnabledAudioZonesResult getEnabledAudioZonesResult = new GetEnabledAudioZonesResult();
        if (mode.equals("WUW")) {
            RLog.e(TAG, "getEnabledAudioZones mode :" + mode);
            RLog.e(TAG, "getVoiceLockMask :" + VoiceAssistantManager.get().getVoiceLockMask());
            arrayList.addAll(DataManager.getEnableAudioZones(VoiceAssistantManager.get().getVoiceLockMask()));
            getEnabledAudioZonesResult.setEnabledZones(arrayList);
            arrayList.forEach(new Consumer() { // from class: com.byd.vrassistant.customizedimpl.CustomSystemDomain$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RLog.e(CustomSystemDomain.TAG, "active zone : " + ((String) obj));
                }
            });
        } else if (mode.equals(AsrModeEnum.CMD)) {
            RLog.e(TAG, "getEnabledAudioZones mode :" + mode);
            boolean zIsVoiceActiveZonesLocked = VoiceAssistantManager.get().isVoiceActiveZonesLocked();
            if (!zIsVoiceActiveZonesLocked) {
                RLog.e(TAG, "isVoiceActiveZonesLocked:" + zIsVoiceActiveZonesLocked);
                arrayList.addAll(DataManager.getEnableAudioZones(VoiceAssistantManager.get().getVoiceLockMask()));
            } else {
                RLog.e(TAG, "isVoiceActiveZonesLocked:" + zIsVoiceActiveZonesLocked);
                if (activeZone.isEmpty()) {
                    arrayList.addAll(DataManager.getEnableAudioZones(VoiceAssistantManager.get().getVoiceLockMask()));
                } else {
                    RLog.e(TAG, "add active zone:" + activeZone);
                    if (VoiceAssistantManager.get().isVirtualKeyPTTEvent()) {
                        arrayList.clear();
                        arrayList.addAll(DataManager.getEnableAudioZones(VoiceAssistantManager.get().getVoiceLockMask() & 3));
                        RLog.e(TAG, "virtual active :" + activeZone);
                    } else {
                        RLog.e(TAG, "normal active :" + activeZone);
                        arrayList.add(activeZone);
                    }
                }
            }
            if (VoiceAssistantManager.get().isAvmForground()) {
                RLog.e(TAG, "isAvmForground start only dirver ");
                arrayList.clear();
                arrayList.add(SystemSettingManager.get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT);
            }
            RLog.e(TAG, "getVoiceLockMask :" + VoiceAssistantManager.get().getVoiceLockMask());
            arrayList.forEach(new Consumer() { // from class: com.byd.vrassistant.customizedimpl.CustomSystemDomain$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    RLog.e(CustomSystemDomain.TAG, "active zone : " + ((String) obj));
                }
            });
            getEnabledAudioZonesResult.setEnabledZones(arrayList);
        }
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK", getEnabledAudioZonesResult));
    }

    @Override // cerence.ark.assistant.controller.domain.system.AbsSystemDomain
    protected void getExternalVoiceAssistantStatus(GetExternalVoiceAssistantStatusParam getExternalVoiceAssistantStatusParam, IResponseSender<GetExternalVoiceAssistantStatusResult> iResponseSender) {
        try {
            this.testLogCat.setMethod(DataUtils.getCurrentMethodName());
            Log.e(TAG, "getExternalVoiceAssistantStatus:" + getExternalVoiceAssistantStatusParam.toJsonString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int hUForbidStatus = SystemSettingManager.get().getHUForbidStatus();
        GetExternalVoiceAssistantStatusResult getExternalVoiceAssistantStatusResult = new GetExternalVoiceAssistantStatusResult();
        if (hUForbidStatus != 0) {
            Log.e(TAG, "getExternalVoiceAssistantStatus forbidStatus:" + hUForbidStatus);
            getExternalVoiceAssistantStatusResult.setStatus("UNSUPPORTED");
            this.testLogCat.setResponse("OK");
            iResponseSender.send(new Response("OK", getExternalVoiceAssistantStatusResult));
            return;
        }
        String externalVrType = getExternalVoiceAssistantStatusParam.getExternalVrType();
        if (externalVrType.equalsIgnoreCase(extVrTypeEnum.SIRI)) {
            if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), VehicleSettingsHelper.PACKAGE_CAR_PLAY)) {
                boolean carplayConnectStatus = SystemSettingManager.get().getCarplayConnectStatus();
                Log.e(TAG, "carplayConnected:" + carplayConnectStatus);
                getExternalVoiceAssistantStatusResult.setStatus(carplayConnectStatus ? "CONNECTED" : "DISCONNECTED");
            } else {
                Log.e(TAG, "carplay app not installed ");
                getExternalVoiceAssistantStatusResult.setStatus("UNSUPPORTED");
            }
        } else if (externalVrType.equalsIgnoreCase(extVrTypeEnum.GOOGLE)) {
            if (ForegroundAppUtil.isAppInstalled(BydVoiceApplication.getContext(), VehicleSettingsHelper.PACKAGE_ANDROID_AUTO)) {
                boolean androidAutoConnectStatus = SystemSettingManager.get().getAndroidAutoConnectStatus();
                Log.e(TAG, "androidAutoConnected:" + androidAutoConnectStatus);
                getExternalVoiceAssistantStatusResult.setStatus(androidAutoConnectStatus ? "CONNECTED" : "DISCONNECTED");
            } else {
                Log.e(TAG, "androidAuto app not installed ");
                getExternalVoiceAssistantStatusResult.setStatus("UNSUPPORTED");
            }
        }
        this.testLogCat.setResponse("OK");
        iResponseSender.send(new Response("OK", getExternalVoiceAssistantStatusResult));
    }

    @Override // cerence.ark.assistant.api.Activated
    public void activate() {
        this.packageManager = BydVoiceApplication.getContext().getPackageManager();
    }

    private void bindZoomAidlService() {
        ZoomCarHelper.getInstance().bindAidlServer();
    }
}
