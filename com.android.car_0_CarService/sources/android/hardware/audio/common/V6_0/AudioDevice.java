package android.hardware.audio.common.V6_0;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class AudioDevice {
    public static final int BIT_DEFAULT = 1073741824;
    public static final int BIT_IN = Integer.MIN_VALUE;
    public static final int IN_AMBIENT = -2147483646;
    public static final int IN_ANLG_DOCK_HEADSET = -2147483136;
    public static final int IN_AUX_DIGITAL = -2147483616;
    public static final int IN_BACK_MIC = -2147483520;
    public static final int IN_BLUETOOTH_A2DP = -2147352576;
    public static final int IN_BLUETOOTH_BLE = -2080374784;
    public static final int IN_BLUETOOTH_SCO_HEADSET = -2147483640;
    public static final int IN_BUILTIN_MIC = -2147483644;
    public static final int IN_BUS = -2146435072;
    public static final int IN_COMMUNICATION = -2147483647;
    public static final int IN_DEFAULT = -1073741824;
    public static final int IN_DGTL_DOCK_HEADSET = -2147482624;
    public static final int IN_ECHO_REFERENCE = -1879048192;
    public static final int IN_FM_TUNER = -2147475456;
    public static final int IN_HDMI = -2147483616;
    public static final int IN_IP = -2146959360;
    public static final int IN_LINE = -2147450880;
    public static final int IN_LOOPBACK = -2147221504;
    public static final int IN_PROXY = -2130706432;
    public static final int IN_REMOTE_SUBMIX = -2147483392;
    public static final int IN_SPDIF = -2147418112;
    public static final int IN_TELEPHONY_RX = -2147483584;
    public static final int IN_TV_TUNER = -2147467264;
    public static final int IN_USB_ACCESSORY = -2147481600;
    public static final int IN_USB_DEVICE = -2147479552;
    public static final int IN_USB_HEADSET = -2113929216;
    public static final int IN_VOICE_CALL = -2147483584;
    public static final int IN_WIRED_HEADSET = -2147483632;
    public static final int NONE = 0;
    public static final int OUT_ANLG_DOCK_HEADSET = 2048;
    public static final int OUT_AUX_DIGITAL = 1024;
    public static final int OUT_AUX_LINE = 2097152;
    public static final int OUT_BLUETOOTH_A2DP = 128;
    public static final int OUT_BLUETOOTH_A2DP_HEADPHONES = 256;
    public static final int OUT_BLUETOOTH_A2DP_SPEAKER = 512;
    public static final int OUT_BLUETOOTH_SCO = 16;
    public static final int OUT_BLUETOOTH_SCO_CARKIT = 64;
    public static final int OUT_BLUETOOTH_SCO_HEADSET = 32;
    public static final int OUT_BUS = 16777216;
    public static final int OUT_DEFAULT = 1073741824;
    public static final int OUT_DGTL_DOCK_HEADSET = 4096;
    public static final int OUT_EARPIECE = 1;
    public static final int OUT_ECHO_CANCELLER = 268435456;
    public static final int OUT_FM = 1048576;
    public static final int OUT_HDMI = 1024;
    public static final int OUT_HDMI_ARC = 262144;
    public static final int OUT_HEARING_AID = 134217728;
    public static final int OUT_IP = 8388608;
    public static final int OUT_LINE = 131072;
    public static final int OUT_PROXY = 33554432;
    public static final int OUT_REMOTE_SUBMIX = 32768;
    public static final int OUT_SPDIF = 524288;
    public static final int OUT_SPEAKER = 2;
    public static final int OUT_SPEAKER_SAFE = 4194304;
    public static final int OUT_TELEPHONY_TX = 65536;
    public static final int OUT_USB_ACCESSORY = 8192;
    public static final int OUT_USB_DEVICE = 16384;
    public static final int OUT_USB_HEADSET = 67108864;
    public static final int OUT_WIRED_HEADPHONE = 8;
    public static final int OUT_WIRED_HEADSET = 4;
    public static final HashMap<Integer, String> hashmap;

    static {
        HashMap<Integer, String> map = new HashMap<>();
        hashmap = map;
        map.put(0, "NONE");
        hashmap.put(Integer.MIN_VALUE, "BIT_IN");
        hashmap.put(1073741824, "BIT_DEFAULT");
        hashmap.put(1, "OUT_EARPIECE");
        hashmap.put(2, "OUT_SPEAKER");
        hashmap.put(4, "OUT_WIRED_HEADSET");
        hashmap.put(8, "OUT_WIRED_HEADPHONE");
        hashmap.put(16, "OUT_BLUETOOTH_SCO");
        hashmap.put(32, "OUT_BLUETOOTH_SCO_HEADSET");
        hashmap.put(64, "OUT_BLUETOOTH_SCO_CARKIT");
        hashmap.put(128, "OUT_BLUETOOTH_A2DP");
        hashmap.put(256, "OUT_BLUETOOTH_A2DP_HEADPHONES");
        hashmap.put(512, "OUT_BLUETOOTH_A2DP_SPEAKER");
        hashmap.put(1024, "OUT_AUX_DIGITAL");
        hashmap.put(1024, "OUT_HDMI");
        hashmap.put(2048, "OUT_ANLG_DOCK_HEADSET");
        hashmap.put(4096, "OUT_DGTL_DOCK_HEADSET");
        hashmap.put(8192, "OUT_USB_ACCESSORY");
        hashmap.put(16384, "OUT_USB_DEVICE");
        hashmap.put(32768, "OUT_REMOTE_SUBMIX");
        hashmap.put(65536, "OUT_TELEPHONY_TX");
        hashmap.put(131072, "OUT_LINE");
        hashmap.put(262144, "OUT_HDMI_ARC");
        hashmap.put(524288, "OUT_SPDIF");
        hashmap.put(1048576, "OUT_FM");
        hashmap.put(2097152, "OUT_AUX_LINE");
        hashmap.put(4194304, "OUT_SPEAKER_SAFE");
        hashmap.put(8388608, "OUT_IP");
        hashmap.put(16777216, "OUT_BUS");
        hashmap.put(33554432, "OUT_PROXY");
        hashmap.put(67108864, "OUT_USB_HEADSET");
        hashmap.put(134217728, "OUT_HEARING_AID");
        hashmap.put(268435456, "OUT_ECHO_CANCELLER");
        hashmap.put(1073741824, "OUT_DEFAULT");
        hashmap.put(-2147483647, "IN_COMMUNICATION");
        hashmap.put(Integer.valueOf(IN_AMBIENT), "IN_AMBIENT");
        hashmap.put(Integer.valueOf(IN_BUILTIN_MIC), "IN_BUILTIN_MIC");
        hashmap.put(Integer.valueOf(IN_BLUETOOTH_SCO_HEADSET), "IN_BLUETOOTH_SCO_HEADSET");
        hashmap.put(Integer.valueOf(IN_WIRED_HEADSET), "IN_WIRED_HEADSET");
        hashmap.put(-2147483616, "IN_AUX_DIGITAL");
        hashmap.put(-2147483616, "IN_HDMI");
        hashmap.put(-2147483584, "IN_VOICE_CALL");
        hashmap.put(-2147483584, "IN_TELEPHONY_RX");
        hashmap.put(Integer.valueOf(IN_BACK_MIC), "IN_BACK_MIC");
        hashmap.put(Integer.valueOf(IN_REMOTE_SUBMIX), "IN_REMOTE_SUBMIX");
        hashmap.put(Integer.valueOf(IN_ANLG_DOCK_HEADSET), "IN_ANLG_DOCK_HEADSET");
        hashmap.put(Integer.valueOf(IN_DGTL_DOCK_HEADSET), "IN_DGTL_DOCK_HEADSET");
        hashmap.put(Integer.valueOf(IN_USB_ACCESSORY), "IN_USB_ACCESSORY");
        hashmap.put(Integer.valueOf(IN_USB_DEVICE), "IN_USB_DEVICE");
        hashmap.put(Integer.valueOf(IN_FM_TUNER), "IN_FM_TUNER");
        hashmap.put(Integer.valueOf(IN_TV_TUNER), "IN_TV_TUNER");
        hashmap.put(Integer.valueOf(IN_LINE), "IN_LINE");
        hashmap.put(Integer.valueOf(IN_SPDIF), "IN_SPDIF");
        hashmap.put(Integer.valueOf(IN_BLUETOOTH_A2DP), "IN_BLUETOOTH_A2DP");
        hashmap.put(Integer.valueOf(IN_LOOPBACK), "IN_LOOPBACK");
        hashmap.put(Integer.valueOf(IN_IP), "IN_IP");
        hashmap.put(Integer.valueOf(IN_BUS), "IN_BUS");
        hashmap.put(Integer.valueOf(IN_PROXY), "IN_PROXY");
        hashmap.put(Integer.valueOf(IN_USB_HEADSET), "IN_USB_HEADSET");
        hashmap.put(Integer.valueOf(IN_BLUETOOTH_BLE), "IN_BLUETOOTH_BLE");
        hashmap.put(Integer.valueOf(IN_ECHO_REFERENCE), "IN_ECHO_REFERENCE");
        hashmap.put(-1073741824, "IN_DEFAULT");
    }

    public static final String toString(int o) {
        if (hashmap.containsKey(Integer.valueOf(o))) {
            return hashmap.get(Integer.valueOf(o));
        }
        return "0x" + Integer.toHexString(o);
    }

    public static final String dumpBitfield(int o) {
        ArrayList<String> list = new ArrayList<>();
        int flipped = 0;
        if (hashmap.containsKey(Integer.valueOf(o))) {
            list.add(hashmap.get(Integer.valueOf(o)));
            flipped = 0 | o;
        }
        if (o != flipped) {
            list.add("0x" + Integer.toHexString((~flipped) & o));
        }
        return String.join(" | ", list);
    }
}
