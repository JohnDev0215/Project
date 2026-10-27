package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SpecialPoiType {
    GO_HOME(0),
    GO_COMPANY(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SpecialPoiType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SpecialPoiType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return GO_HOME;
        }
        if (iIntValue == 1) {
            return GO_COMPANY;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SpecialPoiType.class.getName());
    }

    public static SpecialPoiType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("GO_HOME")) {
            return GO_HOME;
        }
        if (str.equals("GO_COMPANY")) {
            return GO_COMPANY;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SpecialPoiType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
