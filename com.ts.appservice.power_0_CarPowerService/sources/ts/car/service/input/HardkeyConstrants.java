package ts.car.service.input;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class HardkeyConstrants {
    public static final HashMap<String, Integer> KEYCODE_MAP;
    public static final int SCENE_ACCELERATION_TIMER = 501;
    public static final int SCENE_ADAPTER_SERVICE = 502;
    public static final int SCENE_ADAPTER_SERVICE_LONG = 503;
    public static final int SCENE_ALARM_MESSAGE_BG = 504;
    public static final int SCENE_ANDROID_AUTO = 505;
    public static final int SCENE_BT_HMI_BG = 506;
    public static final int SCENE_BT_PHONE = 507;
    public static final int SCENE_BT_PHONE_RETURN = 508;
    public static final int SCENE_CARPLAY = 509;
    public static final int SCENE_CARPLAY_PHONE = 510;
    public static final int SCENE_CARPLAY_PHONE_RETURN = 511;
    public static final int SCENE_CLUSTER_MENU = 512;
    public static final int SCENE_DISABLE_ALL_KEYS = 513;
    public static final HashMap<String, Integer> SCENE_MAP;
    public static final int SCENE_R_GEAR = 514;
    public static final int SCENE_UNKNOWN = 499;
    public static final int SCENE_VR_ADAPTER = 515;

    static {
        HashMap<String, Integer> map = new HashMap<>();
        KEYCODE_MAP = map;
        map.put("KEYCODE_AVM", 1);
        KEYCODE_MAP.put("KEYCODE_SCREEN_ROTATION", 2);
        KEYCODE_MAP.put("KEYCODE_LEFT", 8);
        KEYCODE_MAP.put("KEYCODE_RIGHT", 10);
        KEYCODE_MAP.put("KEYCODE_WHEEL_UP", 17);
        KEYCODE_MAP.put("KEYCODE_WHEEL_DOWN", 18);
        KEYCODE_MAP.put("KEYCODE_WHEEL", 9);
        KEYCODE_MAP.put("KEYCODE_PHONE", 12);
        KEYCODE_MAP.put("KEYCODE_VR", 11);
        KEYCODE_MAP.put("KEYCODE_CLUSTER_RETURN", 13);
        KEYCODE_MAP.put("KEYCODE_MODE", 7);
        KEYCODE_MAP.put("KEYCODE_POWER", 501);
        KEYCODE_MAP.put("KEYCODE_POWER_WHEEL_UP", 502);
        KEYCODE_MAP.put("KEYCODE_POWER_WHEEL_DOWN", 503);
        KEYCODE_MAP.put("KEYCODE_POWER_RESET", 504);
        KEYCODE_MAP.put("KEYCODE_EXHIBITION", 19);
        KEYCODE_MAP.put("KEYCODE_RIGHT_CUSTOM", 20);
        HashMap<String, Integer> map2 = new HashMap<>();
        SCENE_MAP = map2;
        map2.put("SCENE_ACCELERATION_TIMER", 501);
        SCENE_MAP.put("SCENE_ADAPTER_SERVICE", 502);
        SCENE_MAP.put("SCENE_ADAPTER_SERVICE_LONG", 503);
        SCENE_MAP.put("SCENE_ALARM_MESSAGE_BG", 504);
        SCENE_MAP.put("SCENE_ANDROID_AUTO", Integer.valueOf(SCENE_ANDROID_AUTO));
        SCENE_MAP.put("SCENE_BT_HMI_BG", Integer.valueOf(SCENE_BT_HMI_BG));
        SCENE_MAP.put("SCENE_BT_PHONE", 507);
        SCENE_MAP.put("SCENE_BT_PHONE_RETURN", Integer.valueOf(SCENE_BT_PHONE_RETURN));
        SCENE_MAP.put("SCENE_CARPLAY", Integer.valueOf(SCENE_CARPLAY));
        SCENE_MAP.put("SCENE_CARPLAY_PHONE", Integer.valueOf(SCENE_CARPLAY_PHONE));
        SCENE_MAP.put("SCENE_CARPLAY_PHONE_RETURN", Integer.valueOf(SCENE_CARPLAY_PHONE_RETURN));
        SCENE_MAP.put("SCENE_CLUSTER_MENU", 512);
        SCENE_MAP.put("SCENE_DISABLE_ALL_KEYS", Integer.valueOf(SCENE_DISABLE_ALL_KEYS));
        SCENE_MAP.put("SCENE_R_GEAR", Integer.valueOf(SCENE_R_GEAR));
        SCENE_MAP.put("SCENE_VR_ADAPTER", Integer.valueOf(SCENE_VR_ADAPTER));
    }
}
