package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RegionalType {
    DOMESTIC(0),
    EUROPE(1),
    GENERAL(2),
    NORTH_AMERICA(3),
    RUSSIA(4),
    INDIA(5),
    AUSTRALIA(6),
    GENERAL_LEFT_DRIVE_HAND(7),
    GENERAL_RIGHT_DRIVE_HAND(8),
    NON_SUPPORT(9);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RegionalType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RegionalType getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return DOMESTIC;
            case 1:
                return EUROPE;
            case 2:
                return GENERAL;
            case 3:
                return NORTH_AMERICA;
            case 4:
                return RUSSIA;
            case 5:
                return INDIA;
            case 6:
                return AUSTRALIA;
            case 7:
                return GENERAL_LEFT_DRIVE_HAND;
            case 8:
                return GENERAL_RIGHT_DRIVE_HAND;
            case 9:
                return NON_SUPPORT;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RegionalType.class.getName());
        }
    }

    public static RegionalType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "RUSSIA":
                return RUSSIA;
            case "GENERAL_RIGHT_DRIVE_HAND":
                return GENERAL_RIGHT_DRIVE_HAND;
            case "AUSTRALIA":
                return AUSTRALIA;
            case "INDIA":
                return INDIA;
            case "NORTH_AMERICA":
                return NORTH_AMERICA;
            case "GENERAL":
                return GENERAL;
            case "NON_SUPPORT":
                return NON_SUPPORT;
            case "GENERAL_LEFT_DRIVE_HAND":
                return GENERAL_LEFT_DRIVE_HAND;
            case "DOMESTIC":
                return DOMESTIC;
            case "EUROPE":
                return EUROPE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RegionalType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
