package api.structs.com.neusoft.onecore.pos;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LineType {
    Invalid(0),
    Unknown(1),
    Solid(2),
    DoubleSolid(3),
    Dashed(4),
    WideDashed(5);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LineType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LineType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return Invalid;
        }
        if (iIntValue == 1) {
            return Unknown;
        }
        if (iIntValue == 2) {
            return Solid;
        }
        if (iIntValue == 3) {
            return DoubleSolid;
        }
        if (iIntValue == 4) {
            return Dashed;
        }
        if (iIntValue == 5) {
            return WideDashed;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LineType.class.getName());
    }

    public static LineType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "WideDashed":
                return WideDashed;
            case "Invalid":
                return Invalid;
            case "DoubleSolid":
                return DoubleSolid;
            case "Solid":
                return Solid;
            case "Unknown":
                return Unknown;
            case "Dashed":
                return Dashed;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LineType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
