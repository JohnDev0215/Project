package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ShowModeStatus {
    QUIT(0),
    SHOW_MODE(1),
    DEMO_MODE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ShowModeStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ShowModeStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return QUIT;
        }
        if (iIntValue == 1) {
            return SHOW_MODE;
        }
        if (iIntValue == 2) {
            return DEMO_MODE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ShowModeStatus.class.getName());
    }

    public static ShowModeStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DEMO_MODE":
                return DEMO_MODE;
            case "QUIT":
                return QUIT;
            case "SHOW_MODE":
                return SHOW_MODE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ShowModeStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
