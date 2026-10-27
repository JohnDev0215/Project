package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NaviFullViewType {
    FULLVIEW_IN(0),
    FULLVIEW_OUT(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NaviFullViewType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NaviFullViewType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return FULLVIEW_IN;
        }
        if (iIntValue == 1) {
            return FULLVIEW_OUT;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NaviFullViewType.class.getName());
    }

    public static NaviFullViewType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("FULLVIEW_OUT")) {
            return FULLVIEW_OUT;
        }
        if (str.equals("FULLVIEW_IN")) {
            return FULLVIEW_IN;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NaviFullViewType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
