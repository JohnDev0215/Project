package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ScreenDirectionStatus {
    HORIZONTAL(0),
    VERTICAL(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ScreenDirectionStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ScreenDirectionStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return HORIZONTAL;
        }
        if (iIntValue == 1) {
            return VERTICAL;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ScreenDirectionStatus.class.getName());
    }

    public static ScreenDirectionStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("VERTICAL")) {
            return VERTICAL;
        }
        if (str.equals("HORIZONTAL")) {
            return HORIZONTAL;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ScreenDirectionStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
