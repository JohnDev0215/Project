package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum GPSAuthorizedStatus {
    E_UNKNOWN(0),
    E_GPS_AUTHORIZED(1),
    E_GPS_UNAUTHORIZED(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    GPSAuthorizedStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static GPSAuthorizedStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_UNKNOWN;
        }
        if (iIntValue == 1) {
            return E_GPS_AUTHORIZED;
        }
        if (iIntValue == 2) {
            return E_GPS_UNAUTHORIZED;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + GPSAuthorizedStatus.class.getName());
    }

    public static GPSAuthorizedStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "E_UNKNOWN":
                return E_UNKNOWN;
            case "E_GPS_AUTHORIZED":
                return E_GPS_AUTHORIZED;
            case "E_GPS_UNAUTHORIZED":
                return E_GPS_UNAUTHORIZED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + GPSAuthorizedStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
