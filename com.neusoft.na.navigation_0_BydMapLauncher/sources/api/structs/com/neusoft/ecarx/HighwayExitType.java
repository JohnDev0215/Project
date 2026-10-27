package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum HighwayExitType {
    HIGHWAY_EXIT(0),
    NEAREST_EXIT(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    HighwayExitType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static HighwayExitType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return HIGHWAY_EXIT;
        }
        if (iIntValue == 1) {
            return NEAREST_EXIT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + HighwayExitType.class.getName());
    }

    public static HighwayExitType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("NEAREST_EXIT")) {
            return NEAREST_EXIT;
        }
        if (str.equals("HIGHWAY_EXIT")) {
            return HIGHWAY_EXIT;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + HighwayExitType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
