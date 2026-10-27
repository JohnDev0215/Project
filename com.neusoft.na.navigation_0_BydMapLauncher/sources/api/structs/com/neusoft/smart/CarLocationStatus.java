package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum CarLocationStatus {
    DEFAULT(0),
    CAR_LOCATION_ON(1),
    CAR_LOCATION_OFF(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    CarLocationStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static CarLocationStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DEFAULT;
        }
        if (iIntValue == 1) {
            return CAR_LOCATION_ON;
        }
        if (iIntValue == 2) {
            return CAR_LOCATION_OFF;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + CarLocationStatus.class.getName());
    }

    public static CarLocationStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DEFAULT":
                return DEFAULT;
            case "CAR_LOCATION_ON":
                return CAR_LOCATION_ON;
            case "CAR_LOCATION_OFF":
                return CAR_LOCATION_OFF;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + CarLocationStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
