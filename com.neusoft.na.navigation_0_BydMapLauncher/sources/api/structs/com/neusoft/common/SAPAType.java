package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SAPAType {
    HIGHWAY_SAPA(0),
    OTHER_SAPA(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SAPAType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SAPAType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return HIGHWAY_SAPA;
        }
        if (iIntValue == 1) {
            return OTHER_SAPA;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SAPAType.class.getName());
    }

    public static SAPAType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("OTHER_SAPA")) {
            return OTHER_SAPA;
        }
        if (str.equals("HIGHWAY_SAPA")) {
            return HIGHWAY_SAPA;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SAPAType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
