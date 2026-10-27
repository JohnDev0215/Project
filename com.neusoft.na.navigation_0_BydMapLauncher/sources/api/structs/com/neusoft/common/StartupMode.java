package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum StartupMode {
    ASM_DEFAULT(0),
    ASM_VOICE_RECOGNITION(1),
    ASM_APP_ICON(2),
    ASM_OTHERS(10);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    StartupMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static StartupMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ASM_DEFAULT;
        }
        if (iIntValue == 1) {
            return ASM_VOICE_RECOGNITION;
        }
        if (iIntValue == 2) {
            return ASM_APP_ICON;
        }
        if (iIntValue == 10) {
            return ASM_OTHERS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + StartupMode.class.getName());
    }

    public static StartupMode valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "ASM_OTHERS":
                return ASM_OTHERS;
            case "ASM_DEFAULT":
                return ASM_DEFAULT;
            case "ASM_APP_ICON":
                return ASM_APP_ICON;
            case "ASM_VOICE_RECOGNITION":
                return ASM_VOICE_RECOGNITION;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + StartupMode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
