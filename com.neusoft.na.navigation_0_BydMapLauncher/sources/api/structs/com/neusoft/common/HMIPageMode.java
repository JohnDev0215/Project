package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum HMIPageMode {
    LOCATION(0),
    SETTINGS(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    HMIPageMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static HMIPageMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return LOCATION;
        }
        if (iIntValue == 1) {
            return SETTINGS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + HMIPageMode.class.getName());
    }

    public static HMIPageMode valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("SETTINGS")) {
            return SETTINGS;
        }
        if (str.equals("LOCATION")) {
            return LOCATION;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + HMIPageMode.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
