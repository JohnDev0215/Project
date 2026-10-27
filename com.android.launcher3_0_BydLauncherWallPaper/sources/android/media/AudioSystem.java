package android.media;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.audiofx.AudioEffect;
import android.media.audiopolicy.AudioMix;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.android.internal.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class AudioSystem {
    public static final int AUDIO_FORMAT_AAC = 67108864;
    public static final int AUDIO_FORMAT_APTX = 536870912;
    public static final int AUDIO_FORMAT_APTX_HD = 553648128;
    public static final int AUDIO_FORMAT_DEFAULT = 0;
    public static final int AUDIO_FORMAT_INVALID = -1;
    public static final int AUDIO_FORMAT_LDAC = 587202560;
    public static final int AUDIO_FORMAT_SBC = 520093696;
    public static final int AUDIO_HW_SYNC_INVALID = 0;
    public static final int AUDIO_SESSION_ALLOCATE = 0;
    public static final int AUDIO_STATUS_ERROR = 1;
    public static final int AUDIO_STATUS_OK = 0;
    public static final int AUDIO_STATUS_SERVER_DIED = 100;
    public static final int BAD_VALUE = -2;
    public static final int DEAD_OBJECT = -6;
    private static final boolean DEBUG_VOLUME = false;
    public static final int DEFAULT_MUTE_STREAMS_AFFECTED = 111;
    public static int[] DEFAULT_STREAM_VOLUME = null;
    public static final Set<Integer> DEVICE_ALL_HDMI_SYSTEM_AUDIO_AND_SPEAKER_SET;
    public static final int DEVICE_BIT_DEFAULT = 1073741824;
    public static final int DEVICE_BIT_IN = Integer.MIN_VALUE;
    public static final Set<Integer> DEVICE_IN_ALL_SCO_SET;
    public static final Set<Integer> DEVICE_IN_ALL_SET;
    public static final Set<Integer> DEVICE_IN_ALL_USB_SET;
    public static final int DEVICE_IN_AMBIENT = -2147483646;
    public static final String DEVICE_IN_AMBIENT_NAME = "ambient";
    public static final int DEVICE_IN_ANLG_DOCK_HEADSET = -2147483136;
    public static final String DEVICE_IN_ANLG_DOCK_HEADSET_NAME = "analog_dock";
    public static final int DEVICE_IN_AUX_DIGITAL = -2147483616;
    public static final String DEVICE_IN_AUX_DIGITAL_NAME = "aux_digital";
    public static final int DEVICE_IN_BACK_MIC = -2147483520;
    public static final String DEVICE_IN_BACK_MIC_NAME = "back_mic";
    public static final int DEVICE_IN_BLUETOOTH_A2DP = -2147352576;
    public static final String DEVICE_IN_BLUETOOTH_A2DP_NAME = "bt_a2dp";
    public static final int DEVICE_IN_BLUETOOTH_BLE = -2080374784;
    public static final String DEVICE_IN_BLUETOOTH_BLE_NAME = "bt_ble";
    public static final int DEVICE_IN_BLUETOOTH_SCO_HEADSET = -2147483640;
    public static final String DEVICE_IN_BLUETOOTH_SCO_HEADSET_NAME = "bt_sco_hs";
    public static final int DEVICE_IN_BUILTIN_MIC = -2147483644;
    public static final String DEVICE_IN_BUILTIN_MIC_NAME = "mic";
    public static final int DEVICE_IN_BUS = -2146435072;
    public static final String DEVICE_IN_BUS_NAME = "bus";
    public static final int DEVICE_IN_COMMUNICATION = -2147483647;
    public static final String DEVICE_IN_COMMUNICATION_NAME = "communication";
    public static final int DEVICE_IN_DEFAULT = -1073741824;
    public static final int DEVICE_IN_DGTL_DOCK_HEADSET = -2147482624;
    public static final String DEVICE_IN_DGTL_DOCK_HEADSET_NAME = "digital_dock";
    public static final int DEVICE_IN_ECHO_REFERENCE = -1879048192;
    public static final String DEVICE_IN_ECHO_REFERENCE_NAME = "echo_reference";
    public static final int DEVICE_IN_FM_TUNER = -2147475456;
    public static final String DEVICE_IN_FM_TUNER_NAME = "fm_tuner";
    public static final int DEVICE_IN_HDMI = -2147483616;
    public static final int DEVICE_IN_HDMI_ARC = -2013265920;
    public static final String DEVICE_IN_HDMI_ARC_NAME = "hdmi_arc";
    public static final int DEVICE_IN_IP = -2146959360;
    public static final String DEVICE_IN_IP_NAME = "ip";
    public static final int DEVICE_IN_LINE = -2147450880;
    public static final String DEVICE_IN_LINE_NAME = "line";
    public static final int DEVICE_IN_LOOPBACK = -2147221504;
    public static final String DEVICE_IN_LOOPBACK_NAME = "loopback";
    public static final int DEVICE_IN_PROXY = -2130706432;
    public static final String DEVICE_IN_PROXY_NAME = "proxy";
    public static final int DEVICE_IN_REMOTE_SUBMIX = -2147483392;
    public static final String DEVICE_IN_REMOTE_SUBMIX_NAME = "remote_submix";
    public static final int DEVICE_IN_SPDIF = -2147418112;
    public static final String DEVICE_IN_SPDIF_NAME = "spdif";
    public static final int DEVICE_IN_TELEPHONY_RX = -2147483584;
    public static final String DEVICE_IN_TELEPHONY_RX_NAME = "telephony_rx";
    public static final int DEVICE_IN_TV_TUNER = -2147467264;
    public static final String DEVICE_IN_TV_TUNER_NAME = "tv_tuner";
    public static final int DEVICE_IN_USB_ACCESSORY = -2147481600;
    public static final String DEVICE_IN_USB_ACCESSORY_NAME = "usb_accessory";
    public static final int DEVICE_IN_USB_DEVICE = -2147479552;
    public static final String DEVICE_IN_USB_DEVICE_NAME = "usb_device";
    public static final int DEVICE_IN_USB_HEADSET = -2113929216;
    public static final String DEVICE_IN_USB_HEADSET_NAME = "usb_headset";
    public static final int DEVICE_IN_VOICE_CALL = -2147483584;
    public static final int DEVICE_IN_WIRED_HEADSET = -2147483632;
    public static final String DEVICE_IN_WIRED_HEADSET_NAME = "headset";
    public static final int DEVICE_NONE = 0;
    public static final Set<Integer> DEVICE_OUT_ALL_A2DP_SET;
    public static final Set<Integer> DEVICE_OUT_ALL_HDMI_SYSTEM_AUDIO_SET;
    public static final Set<Integer> DEVICE_OUT_ALL_SCO_SET;
    public static final Set<Integer> DEVICE_OUT_ALL_SET;
    public static final int DEVICE_OUT_ALL_USB = 67133440;
    public static final Set<Integer> DEVICE_OUT_ALL_USB_SET;
    public static final int DEVICE_OUT_ANLG_DOCK_HEADSET = 2048;
    public static final String DEVICE_OUT_ANLG_DOCK_HEADSET_NAME = "analog_dock";
    public static final int DEVICE_OUT_AUX_DIGITAL = 1024;
    public static final String DEVICE_OUT_AUX_DIGITAL_NAME = "aux_digital";
    public static final int DEVICE_OUT_AUX_LINE = 2097152;
    public static final String DEVICE_OUT_AUX_LINE_NAME = "aux_line";
    public static final int DEVICE_OUT_BLUETOOTH_A2DP = 128;
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    public static final String DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES_NAME = "bt_a2dp_hp";
    public static final String DEVICE_OUT_BLUETOOTH_A2DP_NAME = "bt_a2dp";
    public static final int DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    public static final String DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER_NAME = "bt_a2dp_spk";
    public static final int DEVICE_OUT_BLUETOOTH_SCO = 16;
    public static final int DEVICE_OUT_BLUETOOTH_SCO_CARKIT = 64;
    public static final String DEVICE_OUT_BLUETOOTH_SCO_CARKIT_NAME = "bt_sco_carkit";
    public static final int DEVICE_OUT_BLUETOOTH_SCO_HEADSET = 32;
    public static final String DEVICE_OUT_BLUETOOTH_SCO_HEADSET_NAME = "bt_sco_hs";
    public static final String DEVICE_OUT_BLUETOOTH_SCO_NAME = "bt_sco";
    public static final int DEVICE_OUT_BUS = 16777216;
    public static final String DEVICE_OUT_BUS_NAME = "bus";
    public static final int DEVICE_OUT_DEFAULT = 1073741824;
    public static final int DEVICE_OUT_DGTL_DOCK_HEADSET = 4096;
    public static final String DEVICE_OUT_DGTL_DOCK_HEADSET_NAME = "digital_dock";
    public static final int DEVICE_OUT_EARPIECE = 1;
    public static final String DEVICE_OUT_EARPIECE_NAME = "earpiece";
    public static final int DEVICE_OUT_FM = 1048576;
    public static final String DEVICE_OUT_FM_NAME = "fm_transmitter";
    public static final int DEVICE_OUT_HDMI = 1024;
    public static final int DEVICE_OUT_HDMI_ARC = 262144;
    public static final String DEVICE_OUT_HDMI_ARC_NAME = "hmdi_arc";
    public static final String DEVICE_OUT_HDMI_NAME = "hdmi";
    public static final int DEVICE_OUT_HEARING_AID = 134217728;
    public static final String DEVICE_OUT_HEARING_AID_NAME = "hearing_aid_out";
    public static final int DEVICE_OUT_IP = 8388608;
    public static final String DEVICE_OUT_IP_NAME = "ip";
    public static final int DEVICE_OUT_LINE = 131072;
    public static final String DEVICE_OUT_LINE_NAME = "line";
    public static final int DEVICE_OUT_PROXY = 33554432;
    public static final String DEVICE_OUT_PROXY_NAME = "proxy";
    public static final int DEVICE_OUT_REMOTE_SUBMIX = 32768;
    public static final String DEVICE_OUT_REMOTE_SUBMIX_NAME = "remote_submix";
    public static final int DEVICE_OUT_SPDIF = 524288;
    public static final String DEVICE_OUT_SPDIF_NAME = "spdif";
    public static final int DEVICE_OUT_SPEAKER = 2;
    public static final String DEVICE_OUT_SPEAKER_NAME = "speaker";
    public static final int DEVICE_OUT_SPEAKER_SAFE = 4194304;
    public static final String DEVICE_OUT_SPEAKER_SAFE_NAME = "speaker_safe";
    public static final int DEVICE_OUT_TELEPHONY_TX = 65536;
    public static final String DEVICE_OUT_TELEPHONY_TX_NAME = "telephony_tx";
    public static final int DEVICE_OUT_USB_ACCESSORY = 8192;
    public static final String DEVICE_OUT_USB_ACCESSORY_NAME = "usb_accessory";
    public static final int DEVICE_OUT_USB_DEVICE = 16384;
    public static final String DEVICE_OUT_USB_DEVICE_NAME = "usb_device";
    public static final int DEVICE_OUT_USB_HEADSET = 67108864;
    public static final String DEVICE_OUT_USB_HEADSET_NAME = "usb_headset";
    public static final int DEVICE_OUT_WIRED_HEADPHONE = 8;
    public static final String DEVICE_OUT_WIRED_HEADPHONE_NAME = "headphone";
    public static final int DEVICE_OUT_WIRED_HEADSET = 4;
    public static final String DEVICE_OUT_WIRED_HEADSET_NAME = "headset";
    public static final int DEVICE_STATE_AVAILABLE = 1;
    public static final int DEVICE_STATE_UNAVAILABLE = 0;
    private static final int DYNAMIC_POLICY_EVENT_MIX_STATE_UPDATE = 0;
    public static final int ERROR = -1;
    public static final int FORCE_ANALOG_DOCK = 8;
    public static final int FORCE_BT_A2DP = 4;
    public static final int FORCE_BT_CAR_DOCK = 6;
    public static final int FORCE_BT_DESK_DOCK = 7;
    public static final int FORCE_BT_SCO = 3;
    public static final int FORCE_DEFAULT = 0;
    public static final int FORCE_DIGITAL_DOCK = 9;
    public static final int FORCE_ENCODED_SURROUND_ALWAYS = 14;
    public static final int FORCE_ENCODED_SURROUND_MANUAL = 15;
    public static final int FORCE_ENCODED_SURROUND_NEVER = 13;
    public static final int FORCE_HDMI_SYSTEM_AUDIO_ENFORCED = 12;
    public static final int FORCE_HEADPHONES = 2;
    public static final int FORCE_NONE = 0;
    public static final int FORCE_NO_BT_A2DP = 10;
    public static final int FORCE_SPEAKER = 1;
    public static final int FORCE_SYSTEM_ENFORCED = 11;
    public static final int FORCE_WIRED_ACCESSORY = 5;
    public static final int FOR_COMMUNICATION = 0;
    public static final int FOR_DOCK = 3;
    public static final int FOR_ENCODED_SURROUND = 6;
    public static final int FOR_HDMI_SYSTEM_AUDIO = 5;
    public static final int FOR_MEDIA = 1;
    public static final int FOR_RECORD = 2;
    public static final int FOR_SYSTEM = 4;
    public static final int FOR_VIBRATE_RINGING = 7;
    public static final int INVALID_OPERATION = -3;
    public static final String IN_VOICE_COMM_FOCUS_ID = "AudioFocus_For_Phone_Ring_And_Calls";
    public static final String LEGACY_REMOTE_SUBMIX_ADDRESS = "0";
    private static final int MAX_DEVICE_ROUTING = 4;
    public static final int MODE_CALL_SCREENING = 4;
    public static final int MODE_CURRENT = -1;
    public static final int MODE_INVALID = -2;
    public static final int MODE_IN_CALL = 2;
    public static final int MODE_IN_COMMUNICATION = 3;
    public static final int MODE_NORMAL = 0;
    public static final int MODE_RINGTONE = 1;
    static final int NATIVE_EVENT_ROUTING_CHANGE = 1000;
    public static final int NO_INIT = -5;
    private static final int NUM_DEVICE_STATES = 1;
    public static final int NUM_FORCE_CONFIG = 16;
    private static final int NUM_FORCE_USE = 8;
    public static final int NUM_MODES = 5;
    public static final int NUM_STREAMS = 5;
    private static final int NUM_STREAM_TYPES = 12;
    public static final int PERMISSION_DENIED = -4;
    public static final int PHONE_STATE_INCALL = 2;
    public static final int PHONE_STATE_OFFCALL = 0;
    public static final int PHONE_STATE_RINGING = 1;
    public static final int PLATFORM_DEFAULT = 0;
    public static final int PLATFORM_TELEVISION = 2;
    public static final int PLATFORM_VOICE = 1;
    public static final int PLAY_SOUND_DELAY = 300;

    @Deprecated
    public static final int ROUTE_ALL = -1;

    @Deprecated
    public static final int ROUTE_BLUETOOTH = 4;

    @Deprecated
    public static final int ROUTE_BLUETOOTH_A2DP = 16;

    @Deprecated
    public static final int ROUTE_BLUETOOTH_SCO = 4;

    @Deprecated
    public static final int ROUTE_EARPIECE = 1;

    @Deprecated
    public static final int ROUTE_HEADSET = 8;

    @Deprecated
    public static final int ROUTE_SPEAKER = 2;
    public static final int STREAM_ACCESSIBILITY = 10;
    public static final int STREAM_ALARM = 4;
    public static final int STREAM_ASSISTANT = 11;
    public static final int STREAM_BLUETOOTH_SCO = 6;
    public static final int STREAM_DEFAULT = -1;
    public static final int STREAM_DTMF = 8;
    public static final int STREAM_MUSIC = 3;
    public static final int STREAM_NOTIFICATION = 5;
    public static final int STREAM_RING = 2;
    public static final int STREAM_SYSTEM = 1;
    public static final int STREAM_SYSTEM_ENFORCED = 7;
    public static final int STREAM_TTS = 9;
    public static final int STREAM_VOICE_CALL = 0;
    public static final int SUCCESS = 0;
    public static final int SYNC_EVENT_NONE = 0;
    public static final int SYNC_EVENT_PRESENTATION_COMPLETE = 1;
    private static final String TAG = "AudioSystem";
    public static final int WOULD_BLOCK = -7;
    private static DynamicPolicyCallback sDynPolicyCallback;
    private static ErrorCallback sErrorCallback;
    private static AudioRecordingCallback sRecordingCallback;
    public static final int OUT_CHANNEL_COUNT_MAX = native_get_FCC_8();
    public static final String[] STREAM_NAMES = {"STREAM_VOICE_CALL", "STREAM_SYSTEM", "STREAM_RING", "STREAM_MUSIC", "STREAM_ALARM", "STREAM_NOTIFICATION", "STREAM_BLUETOOTH_SCO", "STREAM_SYSTEM_ENFORCED", "STREAM_DTMF", "STREAM_TTS", "STREAM_ACCESSIBILITY", "STREAM_ASSISTANT"};

    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioFormatNativeEnumForBtCodec {
    }

    public interface AudioRecordingCallback {
        void onRecordingConfigurationChanged(int i, int i2, int i3, int i4, int i5, int i6, boolean z, int[] iArr, AudioEffect.Descriptor[] descriptorArr, AudioEffect.Descriptor[] descriptorArr2, int i7, String str);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AudioSystemError {
    }

    public interface DynamicPolicyCallback {
        void onDynamicPolicyMixStateUpdate(String str, int i);
    }

    public interface ErrorCallback {
        void onError(int i);
    }

    public static native int checkAudioFlinger();

    public static native int createAudioPatch(AudioPatch[] audioPatchArr, AudioPortConfig[] audioPortConfigArr, AudioPortConfig[] audioPortConfigArr2);

    public static native int getAudioHwSyncForSession(int i);

    public static native int getDeviceConnectionState(int i, String str);

    private static native int getDevicesForAttributes(AudioAttributes audioAttributes, AudioDeviceAttributes[] audioDeviceAttributesArr);

    public static native int getDevicesForStream(int i);

    public static native int getForceUse(int i);

    public static native int getHwOffloadEncodingFormatsSupportedForA2DP(ArrayList<Integer> arrayList);

    public static native float getMasterBalance();

    public static native boolean getMasterMono();

    public static native boolean getMasterMute();

    public static native float getMasterVolume();

    public static native int getMaxVolumeIndexForAttributes(AudioAttributes audioAttributes);

    public static native int getMicrophones(ArrayList<MicrophoneInfo> arrayList);

    public static native int getMinVolumeIndexForAttributes(AudioAttributes audioAttributes);

    public static final int getNumStreamTypes() {
        return 12;
    }

    public static native int getOutputLatency(int i);

    public static native String getParameters(String str);

    public static native int getPreferredDeviceForStrategy(int i, AudioDeviceAttributes[] audioDeviceAttributesArr);

    public static native int getPrimaryOutputFrameCount();

    public static native int getPrimaryOutputSamplingRate();

    public static native float getStreamVolumeDB(int i, int i2, int i3);

    public static native int getStreamVolumeIndex(int i, int i2);

    public static native int getSurroundFormats(Map<Integer, Boolean> map, boolean z);

    public static int getValueForVibrateSetting(int i, int i2, int i3) {
        int i4 = i2 * 2;
        return (i & (~(3 << i4))) | ((i3 & 3) << i4);
    }

    public static native int getVolumeIndexForAttributes(AudioAttributes audioAttributes, int i);

    public static native int handleDeviceConfigChange(int i, String str, String str2, int i2);

    public static native int initStreamVolume(int i, int i2, int i3);

    public static native boolean isCallScreeningModeSupported();

    public static native boolean isHapticPlaybackSupported();

    public static native boolean isMicrophoneMuted();

    public static native boolean isSourceActive(int i);

    public static native boolean isStreamActive(int i, int i2);

    public static native boolean isStreamActiveRemotely(int i, int i2);

    public static native int listAudioPatches(ArrayList<AudioPatch> arrayList, int[] iArr);

    public static native int listAudioPorts(ArrayList<AudioPort> arrayList, int[] iArr);

    public static native int muteMicrophone(boolean z);

    private static native int native_get_FCC_8();

    private static native boolean native_is_offload_supported(int i, int i2, int i3, int i4, int i5);

    private static final native void native_register_dynamic_policy_callback();

    private static final native void native_register_recording_callback();

    public static native int newAudioPlayerId();

    public static native int newAudioRecorderId();

    public static native int newAudioSessionId();

    public static native int registerPolicyMixes(ArrayList<AudioMix> arrayList, boolean z);

    public static native int releaseAudioPatch(AudioPatch audioPatch);

    public static native int removePreferredDeviceForStrategy(int i);

    public static native int removeUidDeviceAffinities(int i);

    public static native int removeUserIdDeviceAffinities(int i);

    public static native int setA11yServicesUids(int[] iArr);

    public static native int setAllowedCapturePolicy(int i, int i2);

    public static native int setAssistantUid(int i);

    public static native int setAudioHalPids(int[] iArr);

    public static native int setAudioPortConfig(AudioPortConfig audioPortConfig);

    public static native int setCurrentImeUid(int i);

    public static native int setDeviceConnectionState(int i, int i2, String str, String str2, int i3);

    public static native int setForceUse(int i, int i2);

    public static native int setLowRamDevice(boolean z, long j);

    public static native int setMasterBalance(float f);

    public static native int setMasterMono(boolean z);

    public static native int setMasterMute(boolean z);

    public static native int setMasterVolume(float f);

    public static native int setParameters(String str);

    public static native int setPhoneState(int i, int i2);

    private static native int setPreferredDeviceForStrategy(int i, int i2, String str);

    public static native int setRttEnabled(boolean z);

    private static native int setStreamVolumeIndex(int i, int i2, int i3);

    public static native int setSupportedSystemUsages(int[] iArr);

    public static native int setSurroundFormatEnabled(int i, boolean z);

    public static native int setUidDeviceAffinities(int i, int[] iArr, String[] strArr);

    public static native int setUserIdDeviceAffinities(int i, int[] iArr, String[] strArr);

    public static native int setVolumeIndexForAttributes(AudioAttributes audioAttributes, int i, int i2);

    public static native int startAudioSource(AudioPortConfig audioPortConfig, AudioAttributes audioAttributes);

    public static native int stopAudioSource(int i);

    public static native int systemReady();

    private AudioSystem() {
        throw new UnsupportedOperationException("Trying to instantiate AudioSystem");
    }

    static {
        HashSet hashSet = new HashSet();
        DEVICE_OUT_ALL_SET = hashSet;
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(4);
        hashSet.add(8);
        hashSet.add(16);
        hashSet.add(32);
        hashSet.add(64);
        hashSet.add(128);
        hashSet.add(256);
        hashSet.add(512);
        hashSet.add(1024);
        hashSet.add(2048);
        hashSet.add(4096);
        hashSet.add(8192);
        hashSet.add(16384);
        hashSet.add(32768);
        hashSet.add(65536);
        hashSet.add(131072);
        hashSet.add(262144);
        hashSet.add(524288);
        hashSet.add(1048576);
        hashSet.add(2097152);
        hashSet.add(4194304);
        hashSet.add(8388608);
        hashSet.add(16777216);
        hashSet.add(33554432);
        hashSet.add(67108864);
        hashSet.add(134217728);
        hashSet.add(1073741824);
        HashSet hashSet2 = new HashSet();
        DEVICE_OUT_ALL_A2DP_SET = hashSet2;
        hashSet2.add(128);
        hashSet2.add(256);
        hashSet2.add(512);
        HashSet hashSet3 = new HashSet();
        DEVICE_OUT_ALL_SCO_SET = hashSet3;
        hashSet3.add(16);
        hashSet3.add(32);
        hashSet3.add(64);
        HashSet hashSet4 = new HashSet();
        DEVICE_OUT_ALL_USB_SET = hashSet4;
        hashSet4.add(8192);
        hashSet4.add(16384);
        hashSet4.add(67108864);
        HashSet hashSet5 = new HashSet();
        DEVICE_OUT_ALL_HDMI_SYSTEM_AUDIO_SET = hashSet5;
        hashSet5.add(2097152);
        hashSet5.add(262144);
        hashSet5.add(524288);
        HashSet hashSet6 = new HashSet();
        DEVICE_ALL_HDMI_SYSTEM_AUDIO_AND_SPEAKER_SET = hashSet6;
        hashSet6.addAll(hashSet5);
        hashSet6.add(2);
        HashSet hashSet7 = new HashSet();
        DEVICE_IN_ALL_SET = hashSet7;
        hashSet7.add(Integer.valueOf(DEVICE_IN_COMMUNICATION));
        hashSet7.add(Integer.valueOf(DEVICE_IN_AMBIENT));
        hashSet7.add(-2147483644);
        hashSet7.add(-2147483640);
        hashSet7.add(-2147483632);
        hashSet7.add(-2147483616);
        hashSet7.add(-2147483584);
        hashSet7.add(-2147483520);
        hashSet7.add(Integer.valueOf(DEVICE_IN_REMOTE_SUBMIX));
        hashSet7.add(-2147483136);
        hashSet7.add(-2147482624);
        hashSet7.add(-2147481600);
        hashSet7.add(-2147479552);
        hashSet7.add(-2147475456);
        hashSet7.add(-2147467264);
        hashSet7.add(-2147450880);
        hashSet7.add(-2147418112);
        hashSet7.add(Integer.valueOf(DEVICE_IN_BLUETOOTH_A2DP));
        hashSet7.add(-2147221504);
        hashSet7.add(Integer.valueOf(DEVICE_IN_IP));
        hashSet7.add(Integer.valueOf(DEVICE_IN_BUS));
        hashSet7.add(Integer.valueOf(DEVICE_IN_PROXY));
        hashSet7.add(Integer.valueOf(DEVICE_IN_USB_HEADSET));
        hashSet7.add(Integer.valueOf(DEVICE_IN_BLUETOOTH_BLE));
        hashSet7.add(-2013265920);
        hashSet7.add(-1879048192);
        hashSet7.add(-1073741824);
        HashSet hashSet8 = new HashSet();
        DEVICE_IN_ALL_SCO_SET = hashSet8;
        hashSet8.add(-2147483640);
        HashSet hashSet9 = new HashSet();
        DEVICE_IN_ALL_USB_SET = hashSet9;
        hashSet9.add(-2147481600);
        hashSet9.add(-2147479552);
        hashSet9.add(Integer.valueOf(DEVICE_IN_USB_HEADSET));
        DEFAULT_STREAM_VOLUME = new int[]{4, 7, 5, 5, 6, 5, 7, 7, 5, 5, 5, 5};
    }

    public static String modeToString(int i) {
        switch (i) {
            case -2:
                return "MODE_INVALID";
            case -1:
                return "MODE_CURRENT";
            case 0:
                return "MODE_NORMAL";
            case 1:
                return "MODE_RINGTONE";
            case 2:
                return "MODE_IN_CALL";
            case 3:
                return "MODE_IN_COMMUNICATION";
            case 4:
                return "MODE_CALL_SCREENING";
            default:
                return "unknown mode (" + i + ")";
        }
    }

    public static int audioFormatToBluetoothSourceCodec(int i) {
        if (i == 67108864) {
            return 1;
        }
        if (i == 520093696) {
            return 0;
        }
        if (i == 536870912) {
            return 2;
        }
        if (i == 553648128) {
            return 3;
        }
        if (i == 587202560) {
            return 4;
        }
        Log.e(TAG, "Unknown audio format 0x" + Integer.toHexString(i) + " for conversion to BT codec");
        return 1000000;
    }

    public static int bluetoothCodecToAudioFormat(int i) {
        if (i == 0) {
            return 520093696;
        }
        if (i == 1) {
            return 67108864;
        }
        if (i == 2) {
            return 536870912;
        }
        if (i == 3) {
            return 553648128;
        }
        if (i == 4) {
            return 587202560;
        }
        Log.e(TAG, "Unknown BT codec 0x" + Integer.toHexString(i) + " for conversion to audio format");
        return 0;
    }

    public static String audioFormatToString(int i) {
        if (i == 167772160) {
            return "AUDIO_FORMAT_E_AC3";
        }
        if (i == 167772161) {
            return "AUDIO_FORMAT_E_AC3_JOC";
        }
        switch (i) {
            case -1:
                return "AUDIO_FORMAT_INVALID";
            case 0:
                return "AUDIO_FORMAT_DEFAULT";
            case 1:
                return "AUDIO_FORMAT_PCM_16_BIT";
            case 2:
                return "AUDIO_FORMAT_PCM_8_BIT";
            case 3:
                return "AUDIO_FORMAT_PCM_32_BIT";
            case 4:
                return "AUDIO_FORMAT_PCM_8_24_BIT";
            case 5:
                return "AUDIO_FORMAT_PCM_FLOAT";
            case 6:
                return "AUDIO_FORMAT_PCM_24_BIT_PACKED";
            default:
                switch (i) {
                    case 16777216:
                        return "AUDIO_FORMAT_MP3";
                    case 33554432:
                        return "AUDIO_FORMAT_AMR_NB";
                    case 50331648:
                        return "AUDIO_FORMAT_AMR_WB";
                    case 67108868:
                        return "AUDIO_FORMAT_AAC_SSR";
                    case 67108872:
                        return "AUDIO_FORMAT_AAC_LTP";
                    case 67108880:
                        return "AUDIO_FORMAT_AAC_HE_V1";
                    case 67108896:
                        return "AUDIO_FORMAT_AAC_SCALABLE";
                    case 67108928:
                        return "AUDIO_FORMAT_AAC_ERLC";
                    case 67108992:
                        return "AUDIO_FORMAT_AAC_LD";
                    case 67109120:
                        return "AUDIO_FORMAT_AAC_HE_V2";
                    case 67109376:
                        return "AUDIO_FORMAT_AAC_ELD";
                    case 67109632:
                        return "AUDIO_FORMAT_AAC_XHE";
                    case android.media.audio.common.AudioFormat.HE_AAC_V1 /* 83886080 */:
                        return "AUDIO_FORMAT_HE_AAC_V1";
                    case 100663296:
                        return "AUDIO_FORMAT_HE_AAC_V2";
                    case android.media.audio.common.AudioFormat.VORBIS /* 117440512 */:
                        return "AUDIO_FORMAT_VORBIS";
                    case 134217728:
                        return "AUDIO_FORMAT_OPUS";
                    case android.media.audio.common.AudioFormat.AC3 /* 150994944 */:
                        return "AUDIO_FORMAT_AC3";
                    case android.media.audio.common.AudioFormat.DTS /* 184549376 */:
                        return "AUDIO_FORMAT_DTS";
                    case android.media.audio.common.AudioFormat.DTS_HD /* 201326592 */:
                        return "AUDIO_FORMAT_DTS_HD";
                    case android.media.audio.common.AudioFormat.IEC61937 /* 218103808 */:
                        return "AUDIO_FORMAT_IEC61937";
                    case android.media.audio.common.AudioFormat.DOLBY_TRUEHD /* 234881024 */:
                        return "AUDIO_FORMAT_DOLBY_TRUEHD";
                    case 268435456:
                        return "AUDIO_FORMAT_EVRC";
                    case 285212672:
                        return "AUDIO_FORMAT_EVRCB";
                    case 301989888:
                        return "AUDIO_FORMAT_EVRCWB";
                    case 318767104:
                        return "AUDIO_FORMAT_EVRCNW";
                    case android.media.audio.common.AudioFormat.AAC_ADIF /* 335544320 */:
                        return "AUDIO_FORMAT_AAC_ADIF";
                    case 352321536:
                        return "AUDIO_FORMAT_WMA";
                    case 369098752:
                        return "AUDIO_FORMAT_WMA_PRO";
                    case 385875968:
                        return "AUDIO_FORMAT_AMR_WB_PLUS";
                    case 402653184:
                        return "AUDIO_FORMAT_MP2";
                    case 419430400:
                        return "AUDIO_FORMAT_QCELP";
                    case android.media.audio.common.AudioFormat.DSD /* 436207616 */:
                        return "AUDIO_FORMAT_DSD";
                    case android.media.audio.common.AudioFormat.FLAC /* 452984832 */:
                        return "AUDIO_FORMAT_FLAC";
                    case android.media.audio.common.AudioFormat.ALAC /* 469762048 */:
                        return "AUDIO_FORMAT_ALAC";
                    case android.media.audio.common.AudioFormat.APE /* 486539264 */:
                        return "AUDIO_FORMAT_APE";
                    case 503316484:
                        return "AUDIO_FORMAT_AAC_ADTS_SSR";
                    case 503316488:
                        return "AUDIO_FORMAT_AAC_ADTS_LTP";
                    case 503316496:
                        return "AUDIO_FORMAT_AAC_ADTS_HE_V1";
                    case 503316512:
                        return "AUDIO_FORMAT_AAC_ADTS_SCALABLE";
                    case 503316544:
                        return "AUDIO_FORMAT_AAC_ADTS_ERLC";
                    case 503316608:
                        return "AUDIO_FORMAT_AAC_ADTS_LD";
                    case 503316736:
                        return "AUDIO_FORMAT_AAC_ADTS_HE_V2";
                    case 503316992:
                        return "AUDIO_FORMAT_AAC_ADTS_ELD";
                    case 503317248:
                        return "AUDIO_FORMAT_AAC_ADTS_XHE";
                    case 520093696:
                        return "AUDIO_FORMAT_SBC";
                    case 536870912:
                        return "AUDIO_FORMAT_APTX";
                    case 553648128:
                        return "AUDIO_FORMAT_APTX_HD";
                    case android.media.audio.common.AudioFormat.AC4 /* 570425344 */:
                        return "AUDIO_FORMAT_AC4";
                    case 587202560:
                        return "AUDIO_FORMAT_LDAC";
                    case android.media.audio.common.AudioFormat.AAC_LATM /* 620756992 */:
                        return "AUDIO_FORMAT_AAC_LATM";
                    case 620756994:
                        return "AUDIO_FORMAT_AAC_LATM_LC";
                    case 620757008:
                        return "AUDIO_FORMAT_AAC_LATM_HE_V1";
                    case 620757248:
                        return "AUDIO_FORMAT_AAC_LATM_HE_V2";
                    case android.media.audio.common.AudioFormat.CELT /* 637534208 */:
                        return "AUDIO_FORMAT_CELT";
                    case android.media.audio.common.AudioFormat.APTX_ADAPTIVE /* 654311424 */:
                        return "AUDIO_FORMAT_APTX_ADAPTIVE";
                    case android.media.audio.common.AudioFormat.LHDC /* 671088640 */:
                        return "AUDIO_FORMAT_LHDC";
                    case android.media.audio.common.AudioFormat.LHDC_LL /* 687865856 */:
                        return "AUDIO_FORMAT_LHDC_LL";
                    case android.media.audio.common.AudioFormat.APTX_TWSP /* 704643072 */:
                        return "AUDIO_FORMAT_APTX_TWSP";
                    default:
                        switch (i) {
                            case 67108864:
                                return "AUDIO_FORMAT_AAC";
                            case 67108865:
                                return "AUDIO_FORMAT_AAC_MAIN";
                            case 67108866:
                                return "AUDIO_FORMAT_AAC_LC";
                            default:
                                switch (i) {
                                    case android.media.audio.common.AudioFormat.AAC_ADTS /* 503316480 */:
                                        return "AUDIO_FORMAT_AAC_ADTS";
                                    case 503316481:
                                        return "AUDIO_FORMAT_AAC_ADTS_MAIN";
                                    case 503316482:
                                        return "AUDIO_FORMAT_AAC_ADTS_LC";
                                    default:
                                        switch (i) {
                                            case android.media.audio.common.AudioFormat.MAT /* 603979776 */:
                                                return "AUDIO_FORMAT_MAT";
                                            case 603979777:
                                                return "AUDIO_FORMAT_MAT_1_0";
                                            case 603979778:
                                                return "AUDIO_FORMAT_MAT_2_0";
                                            case 603979779:
                                                return "AUDIO_FORMAT_MAT_2_1";
                                            default:
                                                return "AUDIO_FORMAT_(" + i + ")";
                                        }
                                }
                        }
                }
        }
    }

    public static void setErrorCallback(ErrorCallback errorCallback) {
        synchronized (AudioSystem.class) {
            sErrorCallback = errorCallback;
            if (errorCallback != null) {
                errorCallback.onError(checkAudioFlinger());
            }
        }
    }

    private static void errorCallbackFromNative(int i) {
        ErrorCallback errorCallback;
        synchronized (AudioSystem.class) {
            errorCallback = sErrorCallback;
            if (errorCallback == null) {
                errorCallback = null;
            }
        }
        if (errorCallback != null) {
            errorCallback.onError(i);
        }
    }

    public static void setDynamicPolicyCallback(DynamicPolicyCallback dynamicPolicyCallback) {
        synchronized (AudioSystem.class) {
            sDynPolicyCallback = dynamicPolicyCallback;
            native_register_dynamic_policy_callback();
        }
    }

    private static void dynamicPolicyCallbackFromNative(int i, String str, int i2) {
        DynamicPolicyCallback dynamicPolicyCallback;
        synchronized (AudioSystem.class) {
            dynamicPolicyCallback = sDynPolicyCallback;
            if (dynamicPolicyCallback == null) {
                dynamicPolicyCallback = null;
            }
        }
        if (dynamicPolicyCallback != null) {
            if (i == 0) {
                dynamicPolicyCallback.onDynamicPolicyMixStateUpdate(str, i2);
            } else {
                Log.e(TAG, "dynamicPolicyCallbackFromNative: unknown event " + i);
            }
        }
    }

    public static void setRecordingCallback(AudioRecordingCallback audioRecordingCallback) {
        synchronized (AudioSystem.class) {
            sRecordingCallback = audioRecordingCallback;
            native_register_recording_callback();
        }
    }

    private static void recordingCallbackFromNative(int i, int i2, int i3, int i4, int i5, int i6, boolean z, int[] iArr, AudioEffect.Descriptor[] descriptorArr, AudioEffect.Descriptor[] descriptorArr2, int i7) {
        AudioRecordingCallback audioRecordingCallback;
        synchronized (AudioSystem.class) {
            audioRecordingCallback = sRecordingCallback;
        }
        boolean z2 = false;
        if (descriptorArr.length != 0) {
            String str = descriptorArr[0].name;
        }
        if (descriptorArr2.length != 0) {
            String str2 = descriptorArr2[0].name;
        }
        if (audioRecordingCallback != null) {
            ArrayList arrayList = new ArrayList();
            if (AudioManager.listAudioPatches(arrayList) == 0) {
                int i8 = iArr[6];
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (((AudioPatch) it.next()).id() == i8) {
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    AudioManager.resetAudioPortGeneration();
                }
            }
            audioRecordingCallback.onRecordingConfigurationChanged(i, i2, i3, i4, i5, i6, z, iArr, descriptorArr, descriptorArr2, i7, "");
        }
    }

    public static String audioSystemErrorToString(int i) {
        switch (i) {
            case -7:
                return "WOULD_BLOCK";
            case -6:
                return "DEAD_OBJECT";
            case -5:
                return "NO_INIT";
            case -4:
                return "PERMISSION_DENIED";
            case -3:
                return "INVALID_OPERATION";
            case -2:
                return "BAD_VALUE";
            case -1:
                return "ERROR";
            case 0:
                return "SUCCESS";
            default:
                return "unknown error:" + i;
        }
    }

    public static String deviceStateToString(int i) {
        return i != 0 ? i != 1 ? "unknown state (" + i + ")" : "DEVICE_STATE_AVAILABLE" : "DEVICE_STATE_UNAVAILABLE";
    }

    public static String getOutputDeviceName(int i) {
        if (i == 1) {
            return DEVICE_OUT_EARPIECE_NAME;
        }
        if (i == 2) {
            return DEVICE_OUT_SPEAKER_NAME;
        }
        switch (i) {
            case 4:
                return "headset";
            case 8:
                return DEVICE_OUT_WIRED_HEADPHONE_NAME;
            case 16:
                return DEVICE_OUT_BLUETOOTH_SCO_NAME;
            case 32:
                return "bt_sco_hs";
            case 64:
                return DEVICE_OUT_BLUETOOTH_SCO_CARKIT_NAME;
            case 128:
                return "bt_a2dp";
            case 256:
                return DEVICE_OUT_BLUETOOTH_A2DP_HEADPHONES_NAME;
            case 512:
                return DEVICE_OUT_BLUETOOTH_A2DP_SPEAKER_NAME;
            case 1024:
                return DEVICE_OUT_HDMI_NAME;
            case 2048:
                return "analog_dock";
            case 4096:
                return "digital_dock";
            case 8192:
                return "usb_accessory";
            case 16384:
                return "usb_device";
            case 32768:
                return "remote_submix";
            case 65536:
                return DEVICE_OUT_TELEPHONY_TX_NAME;
            case 131072:
                return "line";
            case 262144:
                return DEVICE_OUT_HDMI_ARC_NAME;
            case 524288:
                return "spdif";
            case 1048576:
                return DEVICE_OUT_FM_NAME;
            case 2097152:
                return DEVICE_OUT_AUX_LINE_NAME;
            case 4194304:
                return DEVICE_OUT_SPEAKER_SAFE_NAME;
            case 8388608:
                return "ip";
            case 16777216:
                return "bus";
            case 33554432:
                return "proxy";
            case 67108864:
                return "usb_headset";
            case 134217728:
                return DEVICE_OUT_HEARING_AID_NAME;
            default:
                return Integer.toString(i);
        }
    }

    public static String getInputDeviceName(int i) {
        switch (i) {
            case DEVICE_IN_COMMUNICATION /* -2147483647 */:
                return DEVICE_IN_COMMUNICATION_NAME;
            case DEVICE_IN_AMBIENT /* -2147483646 */:
                return DEVICE_IN_AMBIENT_NAME;
            case -2147483644:
                return DEVICE_IN_BUILTIN_MIC_NAME;
            case -2147483640:
                return "bt_sco_hs";
            case -2147483632:
                return "headset";
            case -2147483616:
                return "aux_digital";
            case -2147483584:
                return DEVICE_IN_TELEPHONY_RX_NAME;
            case -2147483520:
                return DEVICE_IN_BACK_MIC_NAME;
            case DEVICE_IN_REMOTE_SUBMIX /* -2147483392 */:
                return "remote_submix";
            case -2147483136:
                return "analog_dock";
            case -2147482624:
                return "digital_dock";
            case -2147481600:
                return "usb_accessory";
            case -2147479552:
                return "usb_device";
            case -2147475456:
                return DEVICE_IN_FM_TUNER_NAME;
            case -2147467264:
                return DEVICE_IN_TV_TUNER_NAME;
            case -2147450880:
                return "line";
            case -2147418112:
                return "spdif";
            case DEVICE_IN_BLUETOOTH_A2DP /* -2147352576 */:
                return "bt_a2dp";
            case -2147221504:
                return DEVICE_IN_LOOPBACK_NAME;
            case DEVICE_IN_IP /* -2146959360 */:
                return "ip";
            case DEVICE_IN_BUS /* -2146435072 */:
                return "bus";
            case DEVICE_IN_PROXY /* -2130706432 */:
                return "proxy";
            case DEVICE_IN_USB_HEADSET /* -2113929216 */:
                return "usb_headset";
            case DEVICE_IN_BLUETOOTH_BLE /* -2080374784 */:
                return DEVICE_IN_BLUETOOTH_BLE_NAME;
            case -2013265920:
                return DEVICE_IN_HDMI_ARC_NAME;
            case -1879048192:
                return DEVICE_IN_ECHO_REFERENCE_NAME;
            default:
                return Integer.toString(i);
        }
    }

    public static String getDeviceName(int i) {
        if ((Integer.MIN_VALUE & i) != 0) {
            return getInputDeviceName(i);
        }
        return getOutputDeviceName(i);
    }

    public static String forceUseConfigToString(int i) {
        switch (i) {
            case 0:
                return "FORCE_NONE";
            case 1:
                return "FORCE_SPEAKER";
            case 2:
                return "FORCE_HEADPHONES";
            case 3:
                return "FORCE_BT_SCO";
            case 4:
                return "FORCE_BT_A2DP";
            case 5:
                return "FORCE_WIRED_ACCESSORY";
            case 6:
                return "FORCE_BT_CAR_DOCK";
            case 7:
                return "FORCE_BT_DESK_DOCK";
            case 8:
                return "FORCE_ANALOG_DOCK";
            case 9:
                return "FORCE_DIGITAL_DOCK";
            case 10:
                return "FORCE_NO_BT_A2DP";
            case 11:
                return "FORCE_SYSTEM_ENFORCED";
            case 12:
                return "FORCE_HDMI_SYSTEM_AUDIO_ENFORCED";
            case 13:
                return "FORCE_ENCODED_SURROUND_NEVER";
            case 14:
                return "FORCE_ENCODED_SURROUND_ALWAYS";
            case 15:
                return "FORCE_ENCODED_SURROUND_MANUAL";
            default:
                return "unknown config (" + i + ")";
        }
    }

    public static String forceUseUsageToString(int i) {
        switch (i) {
            case 0:
                return "FOR_COMMUNICATION";
            case 1:
                return "FOR_MEDIA";
            case 2:
                return "FOR_RECORD";
            case 3:
                return "FOR_DOCK";
            case 4:
                return "FOR_SYSTEM";
            case 5:
                return "FOR_HDMI_SYSTEM_AUDIO";
            case 6:
                return "FOR_ENCODED_SURROUND";
            case 7:
                return "FOR_VIBRATE_RINGING";
            default:
                return "unknown usage (" + i + ")";
        }
    }

    public static int setStreamVolumeIndexAS(int i, int i2, int i3) {
        return setStreamVolumeIndex(i, i2, i3);
    }

    public static int setPhoneState(int i) {
        Log.w(TAG, "Do not use this method! Use AudioManager.setMode() instead.");
        return 0;
    }

    public static ArrayList<AudioDeviceAttributes> getDevicesForAttributes(AudioAttributes audioAttributes) {
        Objects.requireNonNull(audioAttributes);
        AudioDeviceAttributes[] audioDeviceAttributesArr = new AudioDeviceAttributes[4];
        int devicesForAttributes = getDevicesForAttributes(audioAttributes, audioDeviceAttributesArr);
        ArrayList<AudioDeviceAttributes> arrayList = new ArrayList<>();
        if (devicesForAttributes != 0) {
            Log.e(TAG, "error " + devicesForAttributes + " in getDevicesForAttributes for " + audioAttributes);
            return arrayList;
        }
        for (int i = 0; i < 4; i++) {
            AudioDeviceAttributes audioDeviceAttributes = audioDeviceAttributesArr[i];
            if (audioDeviceAttributes != null) {
                arrayList.add(audioDeviceAttributes);
            }
        }
        return arrayList;
    }

    static boolean isOffloadSupported(AudioFormat audioFormat, AudioAttributes audioAttributes) {
        return native_is_offload_supported(audioFormat.getEncoding(), audioFormat.getSampleRate(), audioFormat.getChannelMask(), audioFormat.getChannelIndexMask(), audioAttributes.getVolumeControlStream());
    }

    public static int setPreferredDeviceForStrategy(int i, AudioDeviceAttributes audioDeviceAttributes) {
        return setPreferredDeviceForStrategy(i, AudioDeviceInfo.convertDeviceTypeToInternalDevice(audioDeviceAttributes.getType()), audioDeviceAttributes.getAddress());
    }

    public static int getDefaultStreamVolume(int i) {
        return DEFAULT_STREAM_VOLUME[i];
    }

    public static String streamToString(int i) {
        if (i >= 0) {
            String[] strArr = STREAM_NAMES;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return i == Integer.MIN_VALUE ? "USE_DEFAULT_STREAM_TYPE" : "UNKNOWN_STREAM_" + i;
    }

    public static int getPlatformType(Context context) {
        if (((TelephonyManager) context.getSystemService("phone")).isVoiceCapable()) {
            return 1;
        }
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK) ? 2 : 0;
    }

    public static boolean isSingleVolume(Context context) {
        return getPlatformType(context) == 2 || context.getResources().getBoolean(R.bool.config_single_volume);
    }

    public static Set<Integer> generateAudioDeviceTypesSet(int i) {
        HashSet hashSet = new HashSet();
        Iterator<Integer> it = ((Integer.MIN_VALUE & i) == 0 ? DEVICE_OUT_ALL_SET : DEVICE_IN_ALL_SET).iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if ((i & iIntValue) == iIntValue) {
                hashSet.add(Integer.valueOf(iIntValue));
            }
        }
        return hashSet;
    }

    public static Set<Integer> intersectionAudioDeviceTypes(Set<Integer> set, Set<Integer> set2) {
        HashSet hashSet = new HashSet(set);
        hashSet.retainAll(set2);
        return hashSet;
    }

    public static boolean isSingleAudioDeviceType(Set<Integer> set, int i) {
        return set.size() == 1 && set.contains(Integer.valueOf(i));
    }
}
