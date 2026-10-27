package api.structs.com.neusoft.onecore.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LinkDirection {
    POSITIVE(0),
    NEGATIVE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LinkDirection(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LinkDirection getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return POSITIVE;
        }
        if (iIntValue == 1) {
            return NEGATIVE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LinkDirection.class.getName());
    }

    public static LinkDirection valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("POSITIVE")) {
            return POSITIVE;
        }
        if (str.equals("NEGATIVE")) {
            return NEGATIVE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LinkDirection.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
