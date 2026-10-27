package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NaviLayoutDirection {
    UNDEFINED(-1),
    LTR(0),
    RTL(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NaviLayoutDirection(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NaviLayoutDirection getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return UNDEFINED;
        }
        if (iIntValue == 0) {
            return LTR;
        }
        if (iIntValue == 1) {
            return RTL;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NaviLayoutDirection.class.getName());
    }

    public static NaviLayoutDirection valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "LTR":
                return LTR;
            case "RTL":
                return RTL;
            case "UNDEFINED":
                return UNDEFINED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NaviLayoutDirection.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
