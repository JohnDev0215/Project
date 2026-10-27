package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ViewModeStatus {
    VIEW_MODE_3D_FRONT_UP(25),
    VIEW_MODE_2D_NORTHWARD(26),
    VIEW_MODE_2D_FRONT_UP(27);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ViewModeStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ViewModeStatus getByValue(Integer num) {
        switch (num.intValue()) {
            case 25:
                return VIEW_MODE_3D_FRONT_UP;
            case 26:
                return VIEW_MODE_2D_NORTHWARD;
            case 27:
                return VIEW_MODE_2D_FRONT_UP;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ViewModeStatus.class.getName());
        }
    }

    public static ViewModeStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "VIEW_MODE_3D_FRONT_UP":
                return VIEW_MODE_3D_FRONT_UP;
            case "VIEW_MODE_2D_NORTHWARD":
                return VIEW_MODE_2D_NORTHWARD;
            case "VIEW_MODE_2D_FRONT_UP":
                return VIEW_MODE_2D_FRONT_UP;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ViewModeStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
