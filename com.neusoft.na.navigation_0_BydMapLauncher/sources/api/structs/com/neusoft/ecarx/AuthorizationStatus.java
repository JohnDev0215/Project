package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum AuthorizationStatus {
    UNKNOWN_STATUS(0),
    LOGIN_STATUS(1),
    LOGOUT_STATUS(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    AuthorizationStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static AuthorizationStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN_STATUS;
        }
        if (iIntValue == 1) {
            return LOGIN_STATUS;
        }
        if (iIntValue == 2) {
            return LOGOUT_STATUS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + AuthorizationStatus.class.getName());
    }

    public static AuthorizationStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "UNKNOWN_STATUS":
                return UNKNOWN_STATUS;
            case "LOGIN_STATUS":
                return LOGIN_STATUS;
            case "LOGOUT_STATUS":
                return LOGOUT_STATUS;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + AuthorizationStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
