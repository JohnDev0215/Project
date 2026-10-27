package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum CommuteStatus {
    TYPE_NORMAL(0),
    TYPE_HOME(1),
    TYPE_COMPANY(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    CommuteStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static CommuteStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return TYPE_NORMAL;
        }
        if (iIntValue == 1) {
            return TYPE_HOME;
        }
        if (iIntValue == 2) {
            return TYPE_COMPANY;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + CommuteStatus.class.getName());
    }

    public static CommuteStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "TYPE_COMPANY":
                return TYPE_COMPANY;
            case "TYPE_HOME":
                return TYPE_HOME;
            case "TYPE_NORMAL":
                return TYPE_NORMAL;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + CommuteStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
