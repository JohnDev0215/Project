package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FuelType {
    DIESEL(0),
    GASOLINE(1),
    PETROL_NATURAL_GAS(2),
    HEV(3),
    PHEV(4),
    EV(5),
    EV_NATURAL_GAS(6),
    NATURAL_GAS(7),
    FUEL_CELL_PACKAGE(8),
    HEV_FUEL_CELL_PACKAGE(9),
    PHEV_FUEL_CELL_PACKAGE(10),
    NON_SUPPORT(11);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    FuelType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static FuelType getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return DIESEL;
            case 1:
                return GASOLINE;
            case 2:
                return PETROL_NATURAL_GAS;
            case 3:
                return HEV;
            case 4:
                return PHEV;
            case 5:
                return EV;
            case 6:
                return EV_NATURAL_GAS;
            case 7:
                return NATURAL_GAS;
            case 8:
                return FUEL_CELL_PACKAGE;
            case 9:
                return HEV_FUEL_CELL_PACKAGE;
            case 10:
                return PHEV_FUEL_CELL_PACKAGE;
            case 11:
                return NON_SUPPORT;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FuelType.class.getName());
        }
    }

    public static FuelType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "EV_NATURAL_GAS":
                return EV_NATURAL_GAS;
            case "GASOLINE":
                return GASOLINE;
            case "NATURAL_GAS":
                return NATURAL_GAS;
            case "FUEL_CELL_PACKAGE":
                return FUEL_CELL_PACKAGE;
            case "HEV_FUEL_CELL_PACKAGE":
                return HEV_FUEL_CELL_PACKAGE;
            case "PHEV_FUEL_CELL_PACKAGE":
                return PHEV_FUEL_CELL_PACKAGE;
            case "EV":
                return EV;
            case "HEV":
                return HEV;
            case "PHEV":
                return PHEV;
            case "NON_SUPPORT":
                return NON_SUPPORT;
            case "PETROL_NATURAL_GAS":
                return PETROL_NATURAL_GAS;
            case "DIESEL":
                return DIESEL;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FuelType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
