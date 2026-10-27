package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RouteOverviewMode {
    ROUTE_OVERVIEW_OFF(0),
    ROUTE_OVERVIEW_ON(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RouteOverviewMode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RouteOverviewMode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ROUTE_OVERVIEW_OFF;
        }
        if (iIntValue == 1) {
            return ROUTE_OVERVIEW_ON;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RouteOverviewMode.class.getName());
    }

    public static RouteOverviewMode valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("ROUTE_OVERVIEW_OFF")) {
            return ROUTE_OVERVIEW_OFF;
        }
        if (str.equals("ROUTE_OVERVIEW_ON")) {
            return ROUTE_OVERVIEW_ON;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RouteOverviewMode.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
