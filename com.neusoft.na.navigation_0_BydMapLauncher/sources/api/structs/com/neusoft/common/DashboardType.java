package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DashboardType {
    DBT_DEFAULT(0),
    DBT_SIMPLE_NAVIGATION(1),
    DBT_SMALL_SCREEN_NAVIGATION(2),
    DBT_FULL_SCREEN_NAVIGATION(3),
    DBT_AR_NAVIGATION(4),
    DBT_OTHERS(10);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DashboardType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DashboardType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DBT_DEFAULT;
        }
        if (iIntValue == 1) {
            return DBT_SIMPLE_NAVIGATION;
        }
        if (iIntValue == 2) {
            return DBT_SMALL_SCREEN_NAVIGATION;
        }
        if (iIntValue == 3) {
            return DBT_FULL_SCREEN_NAVIGATION;
        }
        if (iIntValue == 4) {
            return DBT_AR_NAVIGATION;
        }
        if (iIntValue == 10) {
            return DBT_OTHERS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DashboardType.class.getName());
    }

    public static DashboardType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "DBT_AR_NAVIGATION":
                return DBT_AR_NAVIGATION;
            case "DBT_FULL_SCREEN_NAVIGATION":
                return DBT_FULL_SCREEN_NAVIGATION;
            case "DBT_DEFAULT":
                return DBT_DEFAULT;
            case "DBT_SIMPLE_NAVIGATION":
                return DBT_SIMPLE_NAVIGATION;
            case "DBT_SMALL_SCREEN_NAVIGATION":
                return DBT_SMALL_SCREEN_NAVIGATION;
            case "DBT_OTHERS":
                return DBT_OTHERS;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DashboardType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
