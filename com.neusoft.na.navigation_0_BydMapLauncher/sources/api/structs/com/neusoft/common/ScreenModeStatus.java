package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ScreenModeStatus {
    SPLIT(0),
    FULL(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ScreenModeStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ScreenModeStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SPLIT;
        }
        if (iIntValue == 1) {
            return FULL;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ScreenModeStatus.class.getName());
    }

    public static ScreenModeStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("FULL")) {
            return FULL;
        }
        if (str.equals("SPLIT")) {
            return SPLIT;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ScreenModeStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
