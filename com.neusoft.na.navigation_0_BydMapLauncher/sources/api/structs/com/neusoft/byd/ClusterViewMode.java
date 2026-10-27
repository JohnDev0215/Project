package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ClusterViewMode {
    DEFAULT_MODE(0),
    SMALL_SCREEN_MODE(1),
    LARGE_SCREEN_MODE(2),
    TURN_BY_TURN_MODE(3),
    CLOSE_SCREEN_MODE(4);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ClusterViewMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ClusterViewMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DEFAULT_MODE;
        }
        if (iIntValue == 1) {
            return SMALL_SCREEN_MODE;
        }
        if (iIntValue == 2) {
            return LARGE_SCREEN_MODE;
        }
        if (iIntValue == 3) {
            return TURN_BY_TURN_MODE;
        }
        if (iIntValue == 4) {
            return CLOSE_SCREEN_MODE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ClusterViewMode.class.getName());
    }

    public static ClusterViewMode valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "TURN_BY_TURN_MODE":
                return TURN_BY_TURN_MODE;
            case "CLOSE_SCREEN_MODE":
                return CLOSE_SCREEN_MODE;
            case "SMALL_SCREEN_MODE":
                return SMALL_SCREEN_MODE;
            case "LARGE_SCREEN_MODE":
                return LARGE_SCREEN_MODE;
            case "DEFAULT_MODE":
                return DEFAULT_MODE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ClusterViewMode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
