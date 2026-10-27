package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RouteViewStatus {
    ROUTE_VIEW_IN(0),
    ROUTE_VIEW_OUT(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RouteViewStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RouteViewStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ROUTE_VIEW_IN;
        }
        if (iIntValue == 1) {
            return ROUTE_VIEW_OUT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RouteViewStatus.class.getName());
    }

    public static RouteViewStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("ROUTE_VIEW_OUT")) {
            return ROUTE_VIEW_OUT;
        }
        if (str.equals("ROUTE_VIEW_IN")) {
            return ROUTE_VIEW_IN;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RouteViewStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
