package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ViewModeType {
    HEAD_UP_2D(0),
    NORTH_UP_2D(1),
    HEAD_UP_3D(2),
    MODE_CYCLE_SWITCHIN(3);

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
            return HEAD_UP_2D;
        }
        if (iIntValue == 1) {
            return NORTH_UP_2D;
        }
        if (iIntValue == 2) {
            return HEAD_UP_3D;
        }
        if (iIntValue == 3) {
            return MODE_CYCLE_SWITCHIN;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ViewModeType.class.getName());
    }

    public static ViewModeType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "HEAD_UP_2D":
                return HEAD_UP_2D;
            case "HEAD_UP_3D":
                return HEAD_UP_3D;
            case "NORTH_UP_2D":
                return NORTH_UP_2D;
            case "MODE_CYCLE_SWITCHIN":
                return MODE_CYCLE_SWITCHIN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ViewModeType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
