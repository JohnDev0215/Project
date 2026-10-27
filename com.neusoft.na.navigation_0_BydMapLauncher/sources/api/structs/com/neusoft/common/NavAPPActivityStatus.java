package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NavAPPActivityStatus {
    NAV_APP_FOREGROUND(0),
    NAV_APP_BACKGROUND(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NavAPPActivityStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NavAPPActivityStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return NAV_APP_FOREGROUND;
        }
        if (iIntValue == 1) {
            return NAV_APP_BACKGROUND;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NavAPPActivityStatus.class.getName());
    }

    public static NavAPPActivityStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("NAV_APP_FOREGROUND")) {
            return NAV_APP_FOREGROUND;
        }
        if (str.equals("NAV_APP_BACKGROUND")) {
            return NAV_APP_BACKGROUND;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NavAPPActivityStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
