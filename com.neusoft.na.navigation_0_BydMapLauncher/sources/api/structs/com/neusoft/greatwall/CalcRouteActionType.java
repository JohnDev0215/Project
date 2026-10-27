package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum CalcRouteActionType {
    CALC_PLANNING_ROUTE(0),
    CALC_PLANNING_ROUTE_AND_START_GUIDANCE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    CalcRouteActionType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static CalcRouteActionType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return CALC_PLANNING_ROUTE;
        }
        if (iIntValue == 1) {
            return CALC_PLANNING_ROUTE_AND_START_GUIDANCE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + CalcRouteActionType.class.getName());
    }

    public static CalcRouteActionType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("CALC_PLANNING_ROUTE_AND_START_GUIDANCE")) {
            return CALC_PLANNING_ROUTE_AND_START_GUIDANCE;
        }
        if (str.equals("CALC_PLANNING_ROUTE")) {
            return CALC_PLANNING_ROUTE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + CalcRouteActionType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
