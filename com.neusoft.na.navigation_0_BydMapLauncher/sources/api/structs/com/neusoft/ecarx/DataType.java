package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DataType {
    TYPE_POI(0),
    TYPE_NON_POI(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DataType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DataType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return TYPE_POI;
        }
        if (iIntValue == 1) {
            return TYPE_NON_POI;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DataType.class.getName());
    }

    public static DataType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("TYPE_NON_POI")) {
            return TYPE_NON_POI;
        }
        if (str.equals("TYPE_POI")) {
            return TYPE_POI;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DataType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
