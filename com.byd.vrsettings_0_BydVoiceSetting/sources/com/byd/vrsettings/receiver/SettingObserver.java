package com.byd.vrsettings.receiver;

import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import com.byd.vrsettings.BydVoiceSettingApplication;
import com.byd.vrsettings.eventbus.PrivacyPolicyEvent;
import com.byd.vrsettings.eventbus.VoiceZoneUIEvent;
import com.byd.vrsettings.service.OtaDataManager;
import com.byd.vrsettings.utils.Constant;
import com.byd.vrsettings.utils.RLog;
import com.chad.library.BuildConfig;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class SettingObserver extends ContentObserver {
    public static String TAG = "NEU_Wens_SettingObserver";

    public SettingObserver(Handler handler) {
        super(handler);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        RLog.i(TAG, "ota onChange: uri:" + uri.toString());
        String string = uri.toString();
        string.hashCode();
        switch (string) {
            case "content://settings/system/system_locales":
                RLog.i(TAG, "content://settings/system/system_locales = ");
                RLog.i(TAG, "8155 deal with the system_locales");
                BydVoiceSettingApplication.getContext().getResources();
                RLog.e(TAG, "system language:" + Resources.getSystem().getConfiguration().getLocales().get(0).getLanguage());
                OtaDataManager.sendUpdateAppLanguage(3500L);
                break;
            case "content://settings/system/KEY_MAP_NAV":
                String string2 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_MAP_NAV);
                RLog.i(TAG, "Setting set KEY_MAP_NAV =" + string2);
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_MAP_NAV, string2));
                break;
            case "content://settings/system/KEY_LANGUAG_DPS":
                String string3 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_LANGUAG_DPS);
                RLog.i(TAG, "Setting set KEY_LANGUAG_DPS：" + string3);
                if (!TextUtils.isEmpty(string3)) {
                    OtaDataManager.dealLanguageDPSResult(string3);
                    break;
                }
                break;
            case "content://settings/system/KEY_MUSIC_PLAYER":
                String string4 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_MUSIC_PLAYER);
                RLog.i(TAG, "Setting set KEY_MUSIC_PLAYER =" + string4);
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_MUSIC_PLAYER, string4));
                break;
            case "content://settings/system/KEY_HOME_COMMANDS":
                String string5 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_HOME_COMMANDS);
                RLog.i(TAG, "Setting set KEY_HOME_COMMANDS =" + string5);
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_HOME_COMMANDS, string5));
                break;
            case "content://settings/system/KEY_LANGUAG_DPSETINGS":
                String string6 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAG_DPSETINGS");
                RLog.i(TAG, "Setting set KEY_LANGUAG_DPSETINGS：" + string6);
                if (!TextUtils.isEmpty(string6)) {
                    OtaDataManager.dealSwitchResult(string6);
                    break;
                }
                break;
            case "content://settings/system/KEY_LANGUAG_NAVIDPS":
                String string7 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAG_NAVIDPS");
                RLog.i(TAG, "KEY_LANGUAG_NAVIDPS = " + string7);
                if (!TextUtils.isEmpty(string7)) {
                    EventBus.getDefault().post(new PrivacyPolicyEvent("KEY_LANGUAG_NAVIDPS", string7));
                    break;
                }
                break;
            case "content://settings/system/KEY_PRIVACY_POLICY":
                RLog.i(TAG, "onChange: KEY_PRIVACY_POLICY");
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_PRIVACY_POLICY, Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_PRIVACY_POLICY)));
                break;
            case "content://settings/system/KEY_AUDIO_WAKEUP":
                String string8 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_AUDIO_WAKEUP);
                RLog.i(TAG, "Setting set KEY_AUDIO_WAKEUP =" + string8);
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_AUDIO_WAKEUP, string8));
                break;
            case "content://settings/system/VR_START_SUCCESS":
                String string9 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.VR_START_SUCCESS);
                RLog.i(TAG, "onChange vr_start_success: " + string9);
                if (!TextUtils.isEmpty(string9)) {
                    EventBus.getDefault().post(new PrivacyPolicyEvent(com.byd.vrassistant.hint.Constant.VR_START_SUCCESS, string9));
                    Settings.System.putString(BydVoiceSettingApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.VR_START_SUCCESS, BuildConfig.FLAVOR);
                    break;
                }
                break;
            case "content://settings/system/FREE_WORDS_SWITCH_STATUS":
                String string10 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS);
                RLog.i(TAG, "onChange get free words switch status: FREE_WORDS_SWITCH_STATUS");
                if (!TextUtils.isEmpty(string10)) {
                    EventBus.getDefault().post(new PrivacyPolicyEvent(com.byd.vrassistant.hint.Constant.FREE_WORDS_SWITCH_STATUS, string10));
                    break;
                }
                break;
            case "content://settings/system/KEY_REGISTER_LOCK":
                RLog.i(TAG, "Setting set KEY_REGISTER_LOCK ");
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_REGISTER_LOCK, Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_LOCK)));
                break;
            case "content://settings/system/KEY_LANGUAG_INSTALLING":
                int i = Settings.System.getInt(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_LANGUAG_INSTALLING", 0);
                RLog.i(TAG, "KEY_LANGUAG_INSTALLING = " + i);
                if (i == 1) {
                    EventBus.getDefault().post(new PrivacyPolicyEvent("KEY_LANGUAG_INSTALLING", String.valueOf(i)));
                    break;
                }
                break;
            case "content://settings/system/KEY_VOICE_DIALOGEVENT":
                String string11 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "KEY_VOICE_DIALOGEVENT");
                RLog.i(TAG, "KEY_VOICE_DIALOGEVENT：" + string11);
                if (Constant.CONVERSATION_END.equals(string11)) {
                    EventBus.getDefault().post(new PrivacyPolicyEvent("KEY_VOICE_DIALOGEVENT", string11));
                    break;
                }
                break;
            case "content://settings/system/KEY_TEL_CONTROL":
                String string12 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_TEL_CONTROL);
                RLog.i(TAG, "Setting set KEY_TEL_CONTROL =" + string12);
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_TEL_CONTROL, string12));
                break;
            case "content://settings/system/KEY_VOLUME_CONTROL":
                String string13 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_VOLUME_CONTROL);
                RLog.i(TAG, "Setting set KEY_VOLUME_CONTROL =" + string13);
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_VOLUME_CONTROL, string13));
                break;
            case "content://settings/system/KEY_REGISTER_SET":
                String string14 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_RESULT);
                if (TextUtils.isEmpty(string14)) {
                    RLog.e(TAG, "KEY_REGISTER_SET_RESULT is null");
                    break;
                } else {
                    String string15 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_REGISTER_SET_REQUEST);
                    VoiceZoneUIEvent voiceZoneUIEvent = new VoiceZoneUIEvent();
                    if (string15.equals(string14)) {
                        RLog.e(TAG, "change succ");
                        voiceZoneUIEvent.setSucc(true);
                    } else {
                        RLog.e(TAG, "change error");
                        voiceZoneUIEvent.setSucc(false);
                    }
                    EventBus.getDefault().post(voiceZoneUIEvent);
                    break;
                }
                break;
            case "content://settings/system/SP_KEY_CUR_LANGUAGE":
                String string16 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), "SP_KEY_CUR_LANGUAGE");
                RLog.i(TAG, "VRLanguage = " + string16);
                EventBus.getDefault().post(new PrivacyPolicyEvent("SP_KEY_CUR_LANGUAGE", string16));
                break;
            case "content://settings/system/KEY_AC_CONTROL":
                String string17 = Settings.System.getString(BydVoiceSettingApplication.getContext().getContentResolver(), Constant.KEY_AC_CONTROL);
                RLog.i(TAG, "Setting set KEY_AC_CONTROL =" + string17);
                EventBus.getDefault().post(new PrivacyPolicyEvent(Constant.KEY_AC_CONTROL, string17));
                break;
        }
    }
}
