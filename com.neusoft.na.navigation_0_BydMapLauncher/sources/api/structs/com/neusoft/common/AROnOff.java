package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum AROnOff {
    ENABLE_AR(0),
    DISABLE_AR(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    AROnOff(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static AROnOff getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ENABLE_AR;
        }
        if (iIntValue == 1) {
            return DISABLE_AR;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + AROnOff.class.getName());
    }

    public static AROnOff valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("ENABLE_AR")) {
            return ENABLE_AR;
        }
        if (str.equals("DISABLE_AR")) {
            return DISABLE_AR;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + AROnOff.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
