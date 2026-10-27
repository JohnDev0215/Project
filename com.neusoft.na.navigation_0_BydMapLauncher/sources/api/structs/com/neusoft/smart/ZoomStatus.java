package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ZoomStatus {
    ZOOM_STATUS_MID(20),
    ZOOM_STATUS_MAX(21),
    ZOOM_STATUS_MIN(22);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ZoomStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ZoomStatus getByValue(Integer num) {
        switch (num.intValue()) {
            case 20:
                return ZOOM_STATUS_MID;
            case 21:
                return ZOOM_STATUS_MAX;
            case 22:
                return ZOOM_STATUS_MIN;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ZoomStatus.class.getName());
        }
    }

    public static ZoomStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "ZOOM_STATUS_MAX":
                return ZOOM_STATUS_MAX;
            case "ZOOM_STATUS_MID":
                return ZOOM_STATUS_MID;
            case "ZOOM_STATUS_MIN":
                return ZOOM_STATUS_MIN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ZoomStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
