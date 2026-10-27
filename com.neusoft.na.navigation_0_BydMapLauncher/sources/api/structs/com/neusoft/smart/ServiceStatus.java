package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ServiceStatus {
    SERVICE_CONNECTED(0),
    SERVICE_DISCONNECTED(1),
    SERVICE_DIED(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ServiceStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ServiceStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SERVICE_CONNECTED;
        }
        if (iIntValue == 1) {
            return SERVICE_DISCONNECTED;
        }
        if (iIntValue == 2) {
            return SERVICE_DIED;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ServiceStatus.class.getName());
    }

    public static ServiceStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "SERVICE_DIED":
                return SERVICE_DIED;
            case "SERVICE_CONNECTED":
                return SERVICE_CONNECTED;
            case "SERVICE_DISCONNECTED":
                return SERVICE_DISCONNECTED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ServiceStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
