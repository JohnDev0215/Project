package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FunctionType {
    GAS_STATION_SERVICE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    FunctionType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static FunctionType getByValue(Integer num) {
        if (num.intValue() == 1) {
            return GAS_STATION_SERVICE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FunctionType.class.getName());
    }

    public static FunctionType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("GAS_STATION_SERVICE")) {
            return GAS_STATION_SERVICE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FunctionType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
