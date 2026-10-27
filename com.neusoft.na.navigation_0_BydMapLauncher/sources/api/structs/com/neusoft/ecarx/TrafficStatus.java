package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum TrafficStatus {
    TRAFFIC_STATUS_ON(23),
    TRAFFIC_STATUS_OFF(24);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    TrafficStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static TrafficStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 23) {
            return TRAFFIC_STATUS_ON;
        }
        if (iIntValue == 24) {
            return TRAFFIC_STATUS_OFF;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + TrafficStatus.class.getName());
    }

    public static TrafficStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("TRAFFIC_STATUS_OFF")) {
            return TRAFFIC_STATUS_OFF;
        }
        if (str.equals("TRAFFIC_STATUS_ON")) {
            return TRAFFIC_STATUS_ON;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + TrafficStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
