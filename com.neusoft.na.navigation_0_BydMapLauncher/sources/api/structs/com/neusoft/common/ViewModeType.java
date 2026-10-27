package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ViewModeType {
    HEAD_UP(0),
    NORTH_UP(1),
    PERSPECTIVE_VIEW(2),
    CYCLE_ALTERNATE_MODE(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ViewModeType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ViewModeType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return HEAD_UP;
        }
        if (iIntValue == 1) {
            return NORTH_UP;
        }
        if (iIntValue == 2) {
            return PERSPECTIVE_VIEW;
        }
        if (iIntValue == 3) {
            return CYCLE_ALTERNATE_MODE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ViewModeType.class.getName());
    }

    public static ViewModeType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "NORTH_UP":
                return NORTH_UP;
            case "CYCLE_ALTERNATE_MODE":
                return CYCLE_ALTERNATE_MODE;
            case "HEAD_UP":
                return HEAD_UP;
            case "PERSPECTIVE_VIEW":
                return PERSPECTIVE_VIEW;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ViewModeType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
