package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum PrivacyMode {
    DEFAULT_MODE(-1),
    PRIVACY_MODE(0),
    BASIC_MODE(1),
    ADVANCE_MODE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    PrivacyMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static PrivacyMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return DEFAULT_MODE;
        }
        if (iIntValue == 0) {
            return PRIVACY_MODE;
        }
        if (iIntValue == 1) {
            return BASIC_MODE;
        }
        if (iIntValue == 2) {
            return ADVANCE_MODE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + PrivacyMode.class.getName());
    }

    public static PrivacyMode valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "BASIC_MODE":
                return BASIC_MODE;
            case "ADVANCE_MODE":
                return ADVANCE_MODE;
            case "DEFAULT_MODE":
                return DEFAULT_MODE;
            case "PRIVACY_MODE":
                return PRIVACY_MODE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + PrivacyMode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
