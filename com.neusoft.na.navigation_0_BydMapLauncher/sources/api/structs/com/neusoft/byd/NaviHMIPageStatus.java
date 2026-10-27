package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NaviHMIPageStatus {
    NOT_FULL_SCREEN_PAGE(0),
    FULL_SCREEN_PAGE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NaviHMIPageStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NaviHMIPageStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return NOT_FULL_SCREEN_PAGE;
        }
        if (iIntValue == 1) {
            return FULL_SCREEN_PAGE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NaviHMIPageStatus.class.getName());
    }

    public static NaviHMIPageStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("FULL_SCREEN_PAGE")) {
            return FULL_SCREEN_PAGE;
        }
        if (str.equals("NOT_FULL_SCREEN_PAGE")) {
            return NOT_FULL_SCREEN_PAGE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NaviHMIPageStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
