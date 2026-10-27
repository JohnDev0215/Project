package com.byd.music.utils.mediahelper;

import android.app.ActivityManager;
import android.bluetooth.BluetoothHidDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.gnss.visibility_control.V1_0.IGnssVisibilityControlCallback;
import android.media.AudioSystem;
import android.provider.Settings;
import android.view.KeyEvent;
import androidx.exifinterface.media.ExifInterface;
import com.android.internal.midi.MidiConstants;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.ThirdSDK.Spotify.SpotifyManager;
import com.byd.music.ThirdSDK.ThirdManager;
import com.byd.music.ThirdSDK.amazon.AmazonManager;
import com.byd.music.activity.main.MediaMainActivity;
import com.byd.music.api.ApiMethod;
import com.byd.music.common.AudioFocusManager;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.CurrentMediaInfoUtils;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class MediaButtonReceiver extends BroadcastReceiver {
    public static final String ACTION_ENTER_OTA_MODEL = "com.byd.ota.broadcast.ENTER_OTA_MODEL";
    public static final String ACTION_EXIT_OTA_MODEL = "com.byd.ota.broadcast.EXIT_OTA_MODEL";
    public static final String ACTION_REQUEST_FOR_MEDIA_INFO = "com.byd.ACTION_REQUEST_FOR_MEDIA_INFO";
    public static final String ACTION_SWITCH_MEDIA_MODE = "com.byd.ACTION_SWITCH_MEDIA_MODE";
    private final String TAG = "MediaButtonReceiver";

    private void sendAutoVoiceResult(String str, String[] strArr) {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        byte b;
        action = intent.getAction();
        LogUtil.e("MediaButtonReceiver", "MediaButtonReceiver --> action == " + action);
        switch (action) {
            case "android.intent.action.ACTION_SHUTDOWN":
                if (OnLineMusic.musicEngine != 102) {
                    if (CurrentMediaInfoUtils.getInstance().getPlayingState()) {
                        CurrentMediaInfoUtils.getInstance().expectPlayingStatusShouldNotBeOverride = true;
                    }
                    if (OnLineMusic.musicEngine == 103) {
                        Utils.getApp().sendBroadcast(new Intent() { // from class: com.byd.music.utils.mediahelper.MediaButtonReceiver.1
                            {
                                setAction(SpotifyManager.StopPlayButPlayMemoryEnable);
                                setPackage(SpotifyManager.SpotifyPKG);
                            }
                        });
                    } else {
                        CurrentMediaInfoUtils.getInstance().saveCurrentProgress();
                        ApiMethod.playOrStop(false);
                        MusicPlayHelper.getInstance().getMusicPlayer().releaseMediaPlayer();
                    }
                    RadioServiceWrapper.getInstance().setRadioSTR(true);
                    break;
                }
                break;
            case "com.byd.ota.broadcast.ENTER_OTA_MODEL":
                ApiMethod.playOrStop(false);
                AudioFocusManager.getInstance().releaseAudioFocus();
                break;
            case "android.intent.action.BOOT_COMPLETED":
                LogUtil.e("MediaButtonReceiver", "MediaButtonReceiver --> ACTION_BOOT_COMPLETED");
                if (Settings.System.getInt(Utils.getApp().getContentResolver(), "byd_memory_playback_state", 1) > 0) {
                    OnLineMusic.isMemoryPlayOpen = true;
                    LogUtil.d("MediaButtonReceiver", "byd_memory_playback_state = true");
                    if (!CurrentMediaInfoUtils.getInstance().getPlayingState() && CurrentMediaInfoUtils.getInstance().savePlaying.getPlayState()) {
                        ApiMethod.playOrStop(true);
                        break;
                    }
                } else {
                    LogUtil.d("byd_memory_playback_state = false");
                    break;
                }
                break;
            case "android.intent.action.LOCALE_CHANGED":
            case "com.byd.ACTION_REQUEST_FOR_MEDIA_INFO":
                if (OnLineMusic.musicEngine == 9 && RadioServiceWrapper.isDabProgram(RadioServiceWrapper.getInstance().getCurrentProgram().get())) {
                    RadioServiceWrapper.getInstance().updateCurrentRadio(RadioServiceWrapper.getInstance().getCurrentProgram().get());
                }
                CurrentMediaInfoUtils.getInstance().updateForceAndNotify();
                break;
            case "android.intent.action.MEDIA_BUTTON":
                KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                if (keyEvent != null) {
                    LogUtil.e("MediaButtonReceiver", "MediaButtonReceiver keyEvent = " + keyEvent.toString() + ", keyEvent.getKeyCode = " + keyEvent.getKeyCode());
                    if (!AudioFocusManager.getInstance().isHoldAudioFocus()) {
                        LogUtil.e("MediaButtonReceiver", "MediaButtonReceiver return without AudioFocus");
                    } else {
                        int keyCode = keyEvent.getKeyCode();
                        if (keyCode != 126) {
                            if (keyCode != 127) {
                                switch (keyCode) {
                                    case 85:
                                        ApiMethod.playOrStop(!CurrentMediaInfoUtils.getInstance().getPlayingState());
                                        break;
                                    case 87:
                                        ApiMethod.playNext();
                                        break;
                                    case 88:
                                        ApiMethod.playPre();
                                        break;
                                }
                            }
                            ApiMethod.playOrStop(false);
                        } else {
                            ApiMethod.playOrStop(true);
                        }
                    }
                    break;
                }
                break;
            case "com.byd.ACTION_SWITCH_MEDIA_MODE":
                if (!MusicWidgetProvider.isSelected) {
                    Utils.getApp().startActivity(new Intent(Utils.getApp(), (Class<?>) MediaMainActivity.class).setFlags(268435456));
                }
                intent.getIntExtra(ACTION_SWITCH_MEDIA_MODE, -1);
                break;
            case "android.intent.action.PACKAGE_ADDED":
                String dataString = intent.getDataString();
                LogUtil.e("MediaButtonReceiver", "MediaButtonReceiver INSTALL " + dataString);
                if (dataString.contains("com.fce.onlinemedia")) {
                    ThirdManager.getInstance().updateFceGroup();
                    break;
                } else {
                    if (dataString.contains(SpotifyManager.SpotifyPKG)) {
                        ThirdManager.getInstance().setSpotifyEnable(true);
                    } else if (dataString.contains(AmazonManager.AmazonPKG)) {
                        ThirdManager.getInstance().setAmazonEnable(true);
                    }
                    break;
                }
                break;
            case "android.intent.action.PACKAGE_REMOVED":
                String dataString2 = intent.getDataString();
                LogUtil.e("MediaButtonReceiver", "MediaButtonReceiver UNINSTALL " + dataString2);
                if (dataString2.contains("com.fce.onlinemedia")) {
                    ThirdManager.getInstance().updateFceGroup();
                    if (!ThirdManager.isSupportType(OnLineMusic.musicEngine)) {
                        OnLineMusic.setDefaultEngine();
                    }
                    break;
                } else {
                    if (dataString2.contains(SpotifyManager.SpotifyPKG)) {
                        ThirdManager.getInstance().setSpotifyEnable(false);
                        if (!ThirdManager.isSupportType(OnLineMusic.musicEngine)) {
                            OnLineMusic.setDefaultEngine();
                        }
                    } else if (dataString2.contains(AmazonManager.AmazonPKG)) {
                        ThirdManager.getInstance().setAmazonEnable(false);
                        if (!ThirdManager.isSupportType(OnLineMusic.musicEngine)) {
                            OnLineMusic.setDefaultEngine();
                        }
                    }
                    break;
                }
                break;
            case "com.byd.action.AUTOVOICE":
                String[] strArr = {AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, "执行成功"};
                switch (action.hashCode()) {
                    case -2050460947:
                        b = !action.equals("com.byd.action.AUTOVOICE_SPEED_ADJUST") ? (byte) -1 : (byte) 12;
                        break;
                    case -1714844001:
                        b = !action.equals("com.byd.action.AUTOVOICE_FORWARD") ? (byte) -1 : (byte) 8;
                        break;
                    case -1582980144:
                        b = !action.equals("com.byd.action.AUTOVOICE_OPEN_LYRIC") ? (byte) -1 : (byte) 4;
                        break;
                    case -1470705983:
                        b = !action.equals("com.byd.action.AUTOVOICE_PLAY_FAVORITE") ? (byte) -1 : (byte) 19;
                        break;
                    case -1093677651:
                        b = !action.equals("com.byd.action.AUTOVOICE_PLAY_DOWNLOAD") ? (byte) -1 : (byte) 18;
                        break;
                    case -921356181:
                        b = !action.equals("com.byd.action.AUTOVOICE_CLOSE_LIST") ? (byte) -1 : (byte) 17;
                        break;
                    case -676050994:
                        b = !action.equals("com.byd.action.AUTOVOICE_DOWNLOAD") ? (byte) -1 : IGnssVisibilityControlCallback.NfwRequestor.OTHER_CHIPSET_VENDOR;
                        break;
                    case -230035481:
                        b = !action.equals("com.byd.action.AUTOVOICE_UNDOWNLOAD") ? (byte) -1 : BluetoothHidDevice.ERROR_RSP_UNKNOWN;
                        break;
                    case -136509055:
                        b = !action.equals("com.byd.action.AUTOVOICE_REWIND") ? (byte) -1 : (byte) 9;
                        break;
                    case -108526994:
                        b = !action.equals("com.byd.action.AUTOVOICE_SEARCH") ? (byte) -1 : (byte) 21;
                        break;
                    case -88253180:
                        b = !action.equals("com.byd.action.AUTOVOICE_COLLECT") ? (byte) -1 : (byte) 0;
                        break;
                    case -42929720:
                        b = !action.equals("com.byd.action.AUTOVOICE_UNBOOK") ? (byte) -1 : (byte) 3;
                        break;
                    case -31894225:
                        b = !action.equals("com.byd.action.AUTOVOICE_BOOK") ? (byte) -1 : (byte) 2;
                        break;
                    case -31441771:
                        b = !action.equals("com.byd.action.AUTOVOICE_QUIT") ? (byte) -1 : MidiConstants.STATUS_CHANNEL_MASK;
                        break;
                    case 193755670:
                        b = !action.equals("com.byd.action.AUTOVOICE_RECOMMEND") ? (byte) -1 : IGnssVisibilityControlCallback.NfwRequestor.AUTOMOBILE_CLIENT;
                        break;
                    case 318832733:
                        b = !action.equals("com.byd.action.AUTOVOICE_UNFULL_SCREEN") ? (byte) -1 : (byte) 11;
                        break;
                    case 780204781:
                        b = !action.equals("com.byd.action.AUTOVOICE_OPEN_LIST") ? (byte) -1 : (byte) 16;
                        break;
                    case 1503204882:
                        b = !action.equals("com.byd.action.AUTOVOICE_CLOSE_LYRIC") ? (byte) -1 : (byte) 5;
                        break;
                    case 1651351880:
                        b = !action.equals("com.byd.action.AUTOVOICE_PLAY_MODE") ? (byte) -1 : (byte) 6;
                        break;
                    case 1699498774:
                        b = !action.equals("com.byd.action.AUTOVOICE_FULL_SCREEN") ? (byte) -1 : (byte) 10;
                        break;
                    case 1865797067:
                        b = !action.equals("com.byd.action.AUTOVOICE_UNCOLLECT") ? (byte) -1 : (byte) 1;
                        break;
                    case 1994869319:
                        b = !action.equals("com.byd.action.AUTOVOICE_SEARCH _PLUS") ? (byte) -1 : (byte) 22;
                        break;
                    case 2002148390:
                        b = !action.equals("com.byd.action.AUTOVOICE_JUMP_TO") ? (byte) -1 : (byte) 7;
                        break;
                    case 2065145112:
                        b = !action.equals("com.byd.action.AUTOVOICE_PLAY_LATELY") ? (byte) -1 : (byte) 23;
                        break;
                    default:
                        b = -1;
                        break;
                }
                switch (b) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 10:
                    case 11:
                    case 13:
                    case 14:
                    case 15:
                    case 18:
                    case 19:
                    case 20:
                    case 23:
                        break;
                    case 4:
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 5:
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 6:
                        intent.getStringExtra("EXTRA_PLAY_MODE");
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 7:
                        Integer.parseInt(intent.getStringExtra("EXTRA_SET_TIME"));
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 8:
                        Integer.parseInt(intent.hasExtra("EXTRA_SET_TIME") ? intent.getStringExtra("EXTRA_SET_TIME") : "15");
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 9:
                        Integer.parseInt(intent.hasExtra("EXTRA_SET_TIME") ? intent.getStringExtra("EXTRA_SET_TIME") : "15");
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 12:
                        Float.parseFloat(intent.getStringExtra("EXTRA_SET_TIME"));
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 16:
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 17:
                        sendAutoVoiceResult(action, strArr);
                        break;
                    case 21:
                    case 22:
                        StringBuilder sb = new StringBuilder();
                        if (intent.hasExtra("EXTRA_KEYWORDS_SEARCH")) {
                            sb.append(intent.getStringExtra("EXTRA_KEYWORDS_SEARCH"));
                        }
                        if (intent.hasExtra("EXTRA_PROGRAM_TAGS")) {
                            sb.append(intent.getStringExtra("EXTRA_PROGRAM_TAGS"));
                        }
                        if (intent.hasExtra("EXTRA_ARTIST_SEARCH")) {
                            sb.append(intent.getStringExtra("EXTRA_ARTIST_SEARCH"));
                        }
                        if (intent.hasExtra("EXTRA_EPISODE_TAGS")) {
                            sb.append(intent.getStringExtra("EXTRA_EPISODE_TAGS"));
                        }
                        if (intent.hasExtra("EXTRA_MEDIASOURCE_SEARCH")) {
                            sb.append(intent.getStringExtra("EXTRA_MEDIASOURCE_SEARCH"));
                        }
                        if (intent.hasExtra("EXTRA_SOURCE_SEARCH")) {
                            sb.append(intent.getStringExtra("EXTRA_SOURCE_SEARCH"));
                        }
                        if (intent.hasExtra("EXTRA_TYPE_SEARCH")) {
                            sb.append(intent.getStringExtra("EXTRA_TYPE_SEARCH"));
                        }
                        sendAutoVoiceResult(action, strArr);
                        break;
                    default:
                        strArr[0] = ExifInterface.GPS_MEASUREMENT_3D;
                        strArr[1] = "当前场景不支持该功能";
                        sendAutoVoiceResult(action, strArr);
                        break;
                }
                break;
        }
    }

    public static boolean isActivityTop(Class cls, Context context) {
        return ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity.getClassName().equals(cls.getName());
    }
}
