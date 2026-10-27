package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ModuleName {
    DBU(0),
    ONECORE(1),
    TELEMATICS(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ModuleName(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ModuleName getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return DBU;
        }
        if (iIntValue == 1) {
            return ONECORE;
        }
        if (iIntValue == 2) {
            return TELEMATICS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ModuleName.class.getName());
    }

    public static ModuleName valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "TELEMATICS":
                return TELEMATICS;
            case "ONECORE":
                return ONECORE;
            case "DBU":
                return DBU;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ModuleName.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
