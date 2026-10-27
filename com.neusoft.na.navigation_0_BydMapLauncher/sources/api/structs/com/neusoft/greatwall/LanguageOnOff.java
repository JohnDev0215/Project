package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LanguageOnOff {
    ENABLE_NAV_LANGUAGE_SETTING(0),
    DISABLE_NAV_LANGUAGE_SETTING(1),
    USE_CHINESE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LanguageOnOff(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LanguageOnOff getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ENABLE_NAV_LANGUAGE_SETTING;
        }
        if (iIntValue == 1) {
            return DISABLE_NAV_LANGUAGE_SETTING;
        }
        if (iIntValue == 2) {
            return USE_CHINESE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LanguageOnOff.class.getName());
    }

    public static LanguageOnOff valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DISABLE_NAV_LANGUAGE_SETTING":
                return DISABLE_NAV_LANGUAGE_SETTING;
            case "ENABLE_NAV_LANGUAGE_SETTING":
                return ENABLE_NAV_LANGUAGE_SETTING;
            case "USE_CHINESE":
                return USE_CHINESE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LanguageOnOff.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
