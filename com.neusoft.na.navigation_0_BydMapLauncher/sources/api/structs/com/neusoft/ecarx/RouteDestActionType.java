package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RouteDestActionType {
    ROUTE_DEST_DEL(-1),
    ROUTE_DEST_ADD(0);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RouteDestActionType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RouteDestActionType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return ROUTE_DEST_DEL;
        }
        if (iIntValue == 0) {
            return ROUTE_DEST_ADD;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RouteDestActionType.class.getName());
    }

    public static RouteDestActionType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("ROUTE_DEST_ADD")) {
            return ROUTE_DEST_ADD;
        }
        if (str.equals("ROUTE_DEST_DEL")) {
            return ROUTE_DEST_DEL;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RouteDestActionType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
