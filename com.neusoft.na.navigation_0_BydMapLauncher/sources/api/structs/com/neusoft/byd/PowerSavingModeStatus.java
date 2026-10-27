package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum PowerSavingModeStatus {
    UNKNOWN(0),
    POWER_SAVING_MODE(1),
    RELEASE_POWER_SAVING_MODE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    PowerSavingModeStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static PowerSavingModeStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return UNKNOWN;
        }
        if (iIntValue == 1) {
            return POWER_SAVING_MODE;
        }
        if (iIntValue == 2) {
            return RELEASE_POWER_SAVING_MODE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + PowerSavingModeStatus.class.getName());
    }

    public static PowerSavingModeStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "POWER_SAVING_MODE":
                return POWER_SAVING_MODE;
            case "RELEASE_POWER_SAVING_MODE":
                return RELEASE_POWER_SAVING_MODE;
            case "UNKNOWN":
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + PowerSavingModeStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
