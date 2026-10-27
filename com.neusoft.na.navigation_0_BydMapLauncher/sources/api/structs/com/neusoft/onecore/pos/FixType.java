package api.structs.com.neusoft.onecore.pos;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FixType {
    FIX_NO(0),
    FIX_TIME(1),
    FIX_2D(2),
    FIX_3D(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    FixType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static FixType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return FIX_NO;
        }
        if (iIntValue == 1) {
            return FIX_TIME;
        }
        if (iIntValue == 2) {
            return FIX_2D;
        }
        if (iIntValue == 3) {
            return FIX_3D;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FixType.class.getName());
    }

    public static FixType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "FIX_TIME":
                return FIX_TIME;
            case "FIX_2D":
                return FIX_2D;
            case "FIX_3D":
                return FIX_3D;
            case "FIX_NO":
                return FIX_NO;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FixType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
