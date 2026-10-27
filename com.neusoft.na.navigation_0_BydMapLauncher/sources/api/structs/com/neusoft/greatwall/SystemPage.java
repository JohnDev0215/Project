package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SystemPage {
    SYS_SETTING_PAGE(0),
    SYS_UNKNOWN_PAGE(99);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SystemPage(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SystemPage getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SYS_SETTING_PAGE;
        }
        if (iIntValue == 99) {
            return SYS_UNKNOWN_PAGE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SystemPage.class.getName());
    }

    public static SystemPage valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("SYS_SETTING_PAGE")) {
            return SYS_SETTING_PAGE;
        }
        if (str.equals("SYS_UNKNOWN_PAGE")) {
            return SYS_UNKNOWN_PAGE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SystemPage.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
