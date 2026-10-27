package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum AuthorizedStatus {
    DEFAULT(0),
    AUTHORIZED(1),
    UNAUTHORIZED(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    AuthorizedStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static AuthorizedStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DEFAULT;
        }
        if (iIntValue == 1) {
            return AUTHORIZED;
        }
        if (iIntValue == 2) {
            return UNAUTHORIZED;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + AuthorizedStatus.class.getName());
    }

    public static AuthorizedStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DEFAULT":
                return DEFAULT;
            case "UNAUTHORIZED":
                return UNAUTHORIZED;
            case "AUTHORIZED":
                return AUTHORIZED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + AuthorizedStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
