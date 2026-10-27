package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ExitMode {
    AEM_DEFAULT(0),
    AEM_KILL_PROC(1),
    AEM_POWER_OFF(2),
    AEM_MANUALLY(3),
    AEM_CRASH(4),
    AEM_OTHERS(10);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ExitMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ExitMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return AEM_DEFAULT;
        }
        if (iIntValue == 1) {
            return AEM_KILL_PROC;
        }
        if (iIntValue == 2) {
            return AEM_POWER_OFF;
        }
        if (iIntValue == 3) {
            return AEM_MANUALLY;
        }
        if (iIntValue == 4) {
            return AEM_CRASH;
        }
        if (iIntValue == 10) {
            return AEM_OTHERS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ExitMode.class.getName());
    }

    public static ExitMode valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "AEM_MANUALLY":
                return AEM_MANUALLY;
            case "AEM_POWER_OFF":
                return AEM_POWER_OFF;
            case "AEM_CRASH":
                return AEM_CRASH;
            case "AEM_DEFAULT":
                return AEM_DEFAULT;
            case "AEM_KILL_PROC":
                return AEM_KILL_PROC;
            case "AEM_OTHERS":
                return AEM_OTHERS;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ExitMode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
