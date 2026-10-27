package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FunctionTypes {
    GPS_FUNC(0);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    FunctionTypes(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static FunctionTypes getByValue(Integer num) {
        if (num.intValue() == 0) {
            return GPS_FUNC;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FunctionTypes.class.getName());
    }

    public static FunctionTypes valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("GPS_FUNC")) {
            return GPS_FUNC;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FunctionTypes.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
