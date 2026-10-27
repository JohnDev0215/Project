package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SimulationType {
    DEMO_START(0),
    DEMO_STOP(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SimulationType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SimulationType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DEMO_START;
        }
        if (iIntValue == 1) {
            return DEMO_STOP;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SimulationType.class.getName());
    }

    public static SimulationType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("DEMO_STOP")) {
            return DEMO_STOP;
        }
        if (str.equals("DEMO_START")) {
            return DEMO_START;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SimulationType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
