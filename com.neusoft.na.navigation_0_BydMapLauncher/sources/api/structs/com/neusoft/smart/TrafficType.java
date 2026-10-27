package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum TrafficType {
    TRAFFIC_ON(0),
    TRAFFIC_OFF(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    TrafficType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static TrafficType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return TRAFFIC_ON;
        }
        if (iIntValue == 1) {
            return TRAFFIC_OFF;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + TrafficType.class.getName());
    }

    public static TrafficType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("TRAFFIC_ON")) {
            return TRAFFIC_ON;
        }
        if (str.equals("TRAFFIC_OFF")) {
            return TRAFFIC_OFF;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + TrafficType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
