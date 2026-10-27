package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum CommuteType {
    COMMUTE_GO_HOME(0),
    COMMUTE_GO_COMPANY(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    CommuteType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static CommuteType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return COMMUTE_GO_HOME;
        }
        if (iIntValue == 1) {
            return COMMUTE_GO_COMPANY;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + CommuteType.class.getName());
    }

    public static CommuteType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("COMMUTE_GO_HOME")) {
            return COMMUTE_GO_HOME;
        }
        if (str.equals("COMMUTE_GO_COMPANY")) {
            return COMMUTE_GO_COMPANY;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + CommuteType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
