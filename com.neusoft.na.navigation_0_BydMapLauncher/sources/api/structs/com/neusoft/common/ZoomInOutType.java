package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ZoomInOutType {
    ZOOM_DEFAULT(0),
    ZOOM_OUT(1),
    ZOOM_OUT_TO_MIN(2),
    ZOOM_IN(3),
    ZOOM_IN_TO_MAX(4);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ZoomInOutType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ZoomInOutType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ZOOM_DEFAULT;
        }
        if (iIntValue == 1) {
            return ZOOM_OUT;
        }
        if (iIntValue == 2) {
            return ZOOM_OUT_TO_MIN;
        }
        if (iIntValue == 3) {
            return ZOOM_IN;
        }
        if (iIntValue == 4) {
            return ZOOM_IN_TO_MAX;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ZoomInOutType.class.getName());
    }

    public static ZoomInOutType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "ZOOM_DEFAULT":
                return ZOOM_DEFAULT;
            case "ZOOM_IN_TO_MAX":
                return ZOOM_IN_TO_MAX;
            case "ZOOM_IN":
                return ZOOM_IN;
            case "ZOOM_OUT_TO_MIN":
                return ZOOM_OUT_TO_MIN;
            case "ZOOM_OUT":
                return ZOOM_OUT;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ZoomInOutType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
