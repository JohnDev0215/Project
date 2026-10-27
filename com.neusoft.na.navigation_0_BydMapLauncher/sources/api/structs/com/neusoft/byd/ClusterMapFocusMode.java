package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ClusterMapFocusMode {
    DEFAULT_MODE(0),
    LOCAL_MAP_MODE(1),
    CAR_PLAY_MODE(2),
    ANDROID_AUTO_MODE(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ClusterMapFocusMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ClusterMapFocusMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DEFAULT_MODE;
        }
        if (iIntValue == 1) {
            return LOCAL_MAP_MODE;
        }
        if (iIntValue == 2) {
            return CAR_PLAY_MODE;
        }
        if (iIntValue == 3) {
            return ANDROID_AUTO_MODE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ClusterMapFocusMode.class.getName());
    }

    public static ClusterMapFocusMode valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "CAR_PLAY_MODE":
                return CAR_PLAY_MODE;
            case "LOCAL_MAP_MODE":
                return LOCAL_MAP_MODE;
            case "DEFAULT_MODE":
                return DEFAULT_MODE;
            case "ANDROID_AUTO_MODE":
                return ANDROID_AUTO_MODE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ClusterMapFocusMode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
