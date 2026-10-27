package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum AddressType {
    HOME(0),
    COMPANY(1),
    ADDRESS(2),
    UNKNOWN(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    AddressType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static AddressType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return HOME;
        }
        if (iIntValue == 1) {
            return COMPANY;
        }
        if (iIntValue == 2) {
            return ADDRESS;
        }
        if (iIntValue == 3) {
            return UNKNOWN;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + AddressType.class.getName());
    }

    public static AddressType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "ADDRESS":
                return ADDRESS;
            case "HOME":
                return HOME;
            case "UNKNOWN":
                return UNKNOWN;
            case "COMPANY":
                return COMPANY;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + AddressType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
