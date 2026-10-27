package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum VRCommandOnOff {
    ENABLE(0),
    DISABLE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    VRCommandOnOff(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static VRCommandOnOff getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ENABLE;
        }
        if (iIntValue == 1) {
            return DISABLE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + VRCommandOnOff.class.getName());
    }

    public static VRCommandOnOff valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("DISABLE")) {
            return DISABLE;
        }
        if (str.equals("ENABLE")) {
            return ENABLE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + VRCommandOnOff.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
