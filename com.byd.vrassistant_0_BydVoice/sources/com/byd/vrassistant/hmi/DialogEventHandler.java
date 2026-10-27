package com.byd.vrassistant.hmi;

import android.app.Instrumentation;
import android.content.Intent;
import android.util.Log;
import cerence.ark.assistant.api.notification.DialogEvent;
import cerence.ark.assistant.controller.domain.Domains;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import cerence.ark.assistant.library.JsonKey;
import com.byd.vrassistant.BYDVMUtils;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.NeuVoiceService;
import com.byd.vrassistant.bean.PresentationBean;
import com.byd.vrassistant.bean.VRAudioTrackNullBean;
import com.byd.vrassistant.conversation.RadioSdk;
import com.byd.vrassistant.eventbus.NavigationEvent;
import com.byd.vrassistant.hint.GSON;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SharedPreferencesUtil;
import com.byd.vrassistant.utils.SystemPropertiesUtils;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DialogEventHandler {
    private static final String TAG = "NEU_DialogEventHandler";
    boolean isShortCutWakeup;

    private DialogEventHandler() {
    }

    private static class Holder {
        private static final DialogEventHandler INSTANCE = new DialogEventHandler();

        private Holder() {
        }
    }

    public static DialogEventHandler get() {
        return Holder.INSTANCE;
    }

    public void handle(DialogEvent dialogEvent) throws Exception {
        if (dialogEvent.getEvent() == DialogEvent.Event.CONVERSATION_BEGIN) {
            PresentationBean presentationBean = (PresentationBean) GSON.fromJson(dialogEvent.getExtra(), PresentationBean.class);
            RLog.i(TAG, "BydVoiceApp Version[S.8.12.4sop],GitHash = ");
            RLog.i(Constant.TAG_FORTEST, "wake up successful. area=" + presentationBean.getZone() + ",reason=" + presentationBean.getReason());
            if (presentationBean.getReason().equals("WUW")) {
                VoiceAssistantManager.get().setVirtualKeyPTTEvent(false);
            }
            this.isShortCutWakeup = presentationBean.getReason().trim().equals("SHORTCUT");
            SharedPreferencesUtil.putCurrentArea(ContextHolder.getApplicationContext(), presentationBean.getZone());
            NeuVoiceService.topAppPkg = ForegroundAppUtil.getTopActivityPackage();
            boolean powerState = BYDVMUtils.getInstance().getBYDVMViewModel().getPowerState();
            RLog.e(TAG, "power8155State : " + powerState);
            if (powerState) {
                Intent intent = new Intent();
                intent.setAction(Constant.ACTION_EXIT_CAR_POWER);
                ContextHolder.getApplicationContext().sendBroadcast(intent);
                RLog.e(TAG, "send : ACTION_EXIT_CAR_POWER");
            } else {
                RLog.e(TAG, "no==send : ACTION_EXIT_CAR_POWER");
            }
            if (!this.isShortCutWakeup && SystemSettingManager.get().getHUForbidStatus() == 0) {
                SystemSettingManager.get().setTrackVolumeDuck(true);
                SystemSettingManager.get().setNaviMuteState(true);
                RLog.e(TAG, "current focus package : " + RadioSdk.getInstance(ContextHolder.getApplicationContext()).getPacketAudio());
            }
            if (SystemPropertiesUtils.getBoolean("sys.isinscrsav", false)) {
                ContextHolder.getApplicationContext().sendBroadcast(new Intent("CLOSE_SCREEN_SAVER"));
            }
            EventBus.getDefault().post(new NavigationEvent(false, 1));
            RLog.i(TAG, "onConversationBegin: apk version = S.8.12.4,GitHash = ");
            VoiceAssistantManager.get().setDialogEvent(dialogEvent.getEvent());
            if (!this.isShortCutWakeup) {
                VoiceAssistantManager.get().setFanSppedReduced();
            }
            BYDVMUtils.getInstance().getBYDVMViewModel().setVRState(0);
            VoiceAssistantManager.get().sendWakeUpBroadcast();
            EventBus.getDefault().post(new VRAudioTrackNullBean(true));
            if (SystemSettingManager.get().getPowerScreenStatus() != 1) {
                SystemSettingManager.get().turnBackLightOn();
                try {
                    new Instrumentation().sendKeyDownUpSync(4);
                } catch (Exception e) {
                    Log.e(TAG, "sendKeyDownUpSync(KeyEvent.KEYCODE_BACK)===err", e);
                }
            }
            if (SystemSettingManager.get().getAudioScreenStatus()) {
                SystemSettingManager.get().sendCloseAudioScreenBroad();
                return;
            }
            return;
        }
        if (dialogEvent.getEvent() == DialogEvent.Event.CONVERSATION_END) {
            VoiceAssistantManager.get().setDialogEvent(dialogEvent.getEvent());
            BYDVMUtils.getInstance().getBYDVMViewModel().setVRState(1);
            VoiceAssistantManager.get().setFanSppedRaise();
            EventBus.getDefault().post(new VRAudioTrackNullBean(false));
            SystemSettingManager.get().setTrackVolumeDuck(false);
            VoiceAssistantManager.get().setHardKeyPTTEvent(false);
            SystemSettingManager.get().setNaviMuteState(false);
            this.isShortCutWakeup = false;
            VoiceAssistantManager.get().sendReleaseBroadcast();
            VoiceAssistantManager.get().setVirtualKeyPTTEvent(false);
            return;
        }
        if (dialogEvent.getEvent() == DialogEvent.Event.PROMPT_BEGIN) {
            if (this.isShortCutWakeup) {
                SystemSettingManager.get().setTrackVolumeDuck(true);
                RLog.e(TAG, "short cut word prompt begin");
                return;
            }
            return;
        }
        if (DialogEvent.Event.SESSION_BEGIN.equals(dialogEvent.getEvent())) {
            try {
                String strOptString = new JSONObject(dialogEvent.getExtra()).optString(JsonKey.DOMAIN_NAME);
                RLog.i(TAG, "SESSION_BEGIN=>" + strOptString);
                if (strOptString.equalsIgnoreCase(Domains.UDE) || strOptString.equalsIgnoreCase("navi")) {
                    VoiceAssistantManager.get().setShowAfterExit(false);
                    return;
                }
                return;
            } catch (JSONException e2) {
                Log.e(TAG, "handle: SESSION_BEGIN err===", e2);
                return;
            }
        }
        if (DialogEvent.Event.SESSION_END.equals(dialogEvent.getEvent())) {
            try {
                RLog.i(TAG, "SESSION_END=>" + new JSONObject(dialogEvent.getExtra()).optString(JsonKey.DOMAIN_NAME));
            } catch (JSONException e3) {
                Log.e(TAG, "handle: SESSION_END err===", e3);
            }
            processAvm();
        }
    }

    private void processAvm() {
        RLog.i(TAG, "processAvm=>" + VoiceAssistantManager.get().isAvmForground());
        if (SharedPreferencesUtil.getCurrentArea(BydVoiceApplication.getContext()).equalsIgnoreCase(SystemSettingManager.get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT) || !VoiceAssistantManager.get().isAvmForground()) {
            return;
        }
        VoiceAssistantManager.get().sendCancelPTTEvent();
    }
}
