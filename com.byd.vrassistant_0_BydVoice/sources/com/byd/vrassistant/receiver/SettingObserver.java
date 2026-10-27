package com.byd.vrassistant.receiver;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import com.byd.vrassistant.eventbus.SystemStatusEvent;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.utils.SharedPreferencesUtil;
import com.google.common.base.Ascii;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class SettingObserver extends ContentObserver {
    public static String TAG = "NEU_SettingObserver";

    public SettingObserver(Handler handler) {
        super(handler);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.database.ContentObserver
    public void onChange(boolean z, Uri uri) {
        super.onChange(z, uri);
        String string = uri.toString();
        string.hashCode();
        byte b = -1;
        switch (string.hashCode()) {
            case -1543851541:
                if (string.equals("content://settings/system/KEY_MAP_NAV")) {
                    b = 0;
                }
                break;
            case -944111797:
                if (string.equals("content://settings/system/KEY_REGISTER_SET_REQUEST")) {
                    b = 1;
                }
                break;
            case -848303883:
                if (string.equals("content://settings/system/KEY_WAKEUP_WORD")) {
                    b = 2;
                }
                break;
            case -814788637:
                if (string.equals("content://settings/system/KEY_VOICE_SKILLS_COMMANDS")) {
                    b = 3;
                }
                break;
            case -632924464:
                if (string.equals("content://settings/system/KEY_MUSIC_PLAYER")) {
                    b = 4;
                }
                break;
            case -551081086:
                if (string.equals("content://settings/system/KEY_CURRENT_VOICE_VOLUME")) {
                    b = 5;
                }
                break;
            case -514734025:
                if (string.equals("content://settings/system/KEY_USER_IMPROVEMENT")) {
                    b = 6;
                }
                break;
            case -242108397:
                if (string.equals("content://settings/system/KEY_HOME_COMMANDS")) {
                    b = 7;
                }
                break;
            case 197761310:
                if (string.equals("content://settings/system/KEY_PRIVACY_POLICY")) {
                    b = 8;
                }
                break;
            case 395197533:
                if (string.equals("content://settings/system/KEY_AUDIO_WAKEUP")) {
                    b = 9;
                }
                break;
            case 532621131:
                if (string.equals("content://settings/system/KEY_CONTINUE_DIALOG")) {
                    b = 10;
                }
                break;
            case 623510966:
                if (string.equals("content://settings/system/KEY_USER_IMPROVEMENT_DELETE_DATA")) {
                    b = 11;
                }
                break;
            case 886338418:
                if (string.equals("content://settings/system/KEY_REGISTER_LOCK")) {
                    b = 12;
                }
                break;
            case 1177857508:
                if (string.equals("content://settings/system/KEY_TEL_CONTROL")) {
                    b = Ascii.CR;
                }
                break;
            case 1308037197:
                if (string.equals("content://settings/system/KEY_VOLUME_CONTROL")) {
                    b = Ascii.SO;
                }
                break;
            case 1858184781:
                if (string.equals("content://settings/system/KEY_LANGUAGE")) {
                    b = Ascii.SI;
                }
                break;
            case 1999476693:
                if (string.equals("content://settings/system/KEY_AC_CONTROL")) {
                    b = 16;
                }
                break;
        }
        switch (b) {
            case 0:
                SystemSettingManager.get().setFreeWakeupWordsValue("KEY_MAP_NAV");
                VoiceAssistantManager.get().processNavShortCutCommand();
                RLog.e(TAG, "Setting set KEY_MAP_NAV =");
                break;
            case 1:
                VoiceAssistantManager.get().requestVoiceZone();
                RLog.e(TAG, "Setting " + uri);
                break;
            case 2:
                String string2 = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_WAKEUP_WORD");
                ArrayList arrayList = new ArrayList();
                arrayList.add(string2);
                VoiceAssistantManager.get().setVoiceAssistantWUW(arrayList);
                RLog.e(TAG, "Setting set KEY_WAKEUP_WORD =" + string2);
                break;
            case 3:
                RLog.e(TAG, "Setting set KEY_VOICE_SKILLS_COMMANDS =" + Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_VOICE_SKILLS_COMMANDS"));
                break;
            case 4:
                SystemSettingManager.get().setFreeWakeupWordsValue("KEY_MUSIC_PLAYER");
                VoiceAssistantManager.get().processMusicShortCutCommand();
                RLog.e(TAG, "Setting set KEY_MUSIC_PLAYER");
                break;
            case 5:
                int i = Settings.System.getInt(BydVoiceApplication.getContext().getContentResolver(), "KEY_CURRENT_VOICE_VOLUME", 1);
                SystemSettingManager.get().TtsVolumeTo(i);
                RLog.e(TAG, "Setting set KEY_CURRENT_VOICE_VOLUME =" + i);
                break;
            case 6:
                VoiceAssistantManager.get().setUserDataRetentionEnabled();
                break;
            case 7:
                SystemSettingManager.get().setFreeWakeupWordsValue("KEY_HOME_COMMANDS");
                VoiceAssistantManager.get().processHomeShortCutCommand();
                RLog.e(TAG, "Setting set KEY_HOME_COMMANDS");
                break;
            case 8:
                String string3 = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_PRIVACY_POLICY");
                new Handler(BydVoiceApplication.getContext().getMainLooper());
                if (string3.equals("false")) {
                    VoiceAssistantManager.get().sendCancelPTTEvent();
                }
                RLog.e(TAG, "Setting set KEY_PRIVACY_POLICY =" + string3);
                break;
            case 9:
                String string4 = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_AUDIO_WAKEUP");
                if (string4.equals("true")) {
                    SharedPreferencesUtil.putVoiceSetting(BydVoiceApplication.getContext(), "KEY_AUDIO_WAKEUP", "true");
                    VoiceAssistantManager.get().openVoiceAssistant();
                } else {
                    SharedPreferencesUtil.putVoiceSetting(BydVoiceApplication.getContext(), "KEY_AUDIO_WAKEUP", "false");
                    VoiceAssistantManager.get().closeVoiceAssistant();
                }
                RLog.e(TAG, "Setting set KEY_AUDIO_WAKEUP =" + string4);
                break;
            case 10:
                VoiceAssistantManager.get().setDLDHEnabled();
                break;
            case 11:
                RLog.e(TAG, "Setting set KEY_USER_IMPROVEMENT_DELETE_DATA =" + Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_USER_IMPROVEMENT_DELETE_DATA"));
                VoiceAssistantManager.get().deleteUserCloudSessionData();
                break;
            case 12:
                VoiceAssistantManager.get().processVoiceAcvtiveZone();
                RLog.e(TAG, "Setting set KEY_REGISTER_LOCK ");
                break;
            case 13:
                SystemSettingManager.get().setFreeWakeupWordsValue(Constant.KEY_TEL_CONTROL);
                VoiceAssistantManager.get().processTelShortCutCommand();
                RLog.e(TAG, "Setting set KEY_TEL_CONTROL =");
                break;
            case 14:
                SystemSettingManager.get().setFreeWakeupWordsValue("KEY_VOLUME_CONTROL");
                VoiceAssistantManager.get().processVolumeShortCutCommand();
                RLog.e(TAG, "Setting set KEY_VOLUME_CONTROL =");
                break;
            case 15:
                String string5 = Settings.System.getString(BydVoiceApplication.getContext().getContentResolver(), "KEY_LANGUAGE");
                RLog.e(TAG, "Setting set KEY_LANGUAGE =" + string5);
                EventBus.getDefault().post(new SystemStatusEvent(string5, 9));
                break;
            case 16:
                SystemSettingManager.get().setFreeWakeupWordsValue("KEY_AC_CONTROL");
                VoiceAssistantManager.get().processACShortCutCommand();
                RLog.e(TAG, "Setting set KEY_AC_CONTROL");
                break;
        }
    }
}
