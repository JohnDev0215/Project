package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum CameraType {
    CAMERA_UNKNOWN(-1),
    SPEED_CAMERA(0),
    MONITOR_CAMERA(1),
    TAKE_PHOTOS_FOR_RUNNING_THE_RED_LIGHT_CAMERA(2),
    TAKE_PHOTOS_FOR_VIOLATIONS_CAMERA(3),
    SPECIAL_FOP_PUBLIC_TRANSPORTATION_CAMERA(4),
    EMERGENCY_CAMERA(5),
    TAKE_CONTINUOUS_PHOTOS_CAMERA(6),
    TAKE_PHOTOS_OF_THE_MOTORWAY_CAMERA(7),
    CAMERA_OTHER(8);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    CameraType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static CameraType getByValue(Integer num) {
        switch (num.intValue()) {
            case -1:
                return CAMERA_UNKNOWN;
            case 0:
                return SPEED_CAMERA;
            case 1:
                return MONITOR_CAMERA;
            case 2:
                return TAKE_PHOTOS_FOR_RUNNING_THE_RED_LIGHT_CAMERA;
            case 3:
                return TAKE_PHOTOS_FOR_VIOLATIONS_CAMERA;
            case 4:
                return SPECIAL_FOP_PUBLIC_TRANSPORTATION_CAMERA;
            case 5:
                return EMERGENCY_CAMERA;
            case 6:
                return TAKE_CONTINUOUS_PHOTOS_CAMERA;
            case 7:
                return TAKE_PHOTOS_OF_THE_MOTORWAY_CAMERA;
            case 8:
                return CAMERA_OTHER;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + CameraType.class.getName());
        }
    }

    public static CameraType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SPEED_CAMERA":
                return SPEED_CAMERA;
            case "CAMERA_OTHER":
                return CAMERA_OTHER;
            case "MONITOR_CAMERA":
                return MONITOR_CAMERA;
            case "CAMERA_UNKNOWN":
                return CAMERA_UNKNOWN;
            case "EMERGENCY_CAMERA":
                return EMERGENCY_CAMERA;
            case "TAKE_PHOTOS_OF_THE_MOTORWAY_CAMERA":
                return TAKE_PHOTOS_OF_THE_MOTORWAY_CAMERA;
            case "TAKE_PHOTOS_FOR_RUNNING_THE_RED_LIGHT_CAMERA":
                return TAKE_PHOTOS_FOR_RUNNING_THE_RED_LIGHT_CAMERA;
            case "TAKE_CONTINUOUS_PHOTOS_CAMERA":
                return TAKE_CONTINUOUS_PHOTOS_CAMERA;
            case "SPECIAL_FOP_PUBLIC_TRANSPORTATION_CAMERA":
                return SPECIAL_FOP_PUBLIC_TRANSPORTATION_CAMERA;
            case "TAKE_PHOTOS_FOR_VIOLATIONS_CAMERA":
                return TAKE_PHOTOS_FOR_VIOLATIONS_CAMERA;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + CameraType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
