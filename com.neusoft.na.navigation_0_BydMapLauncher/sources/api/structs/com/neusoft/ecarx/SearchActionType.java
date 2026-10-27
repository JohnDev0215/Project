package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum SearchActionType {
    SEARCH_IN_MAP(0),
    SEARCH_ONLY_IN_THROUGH_INFORMATION(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    SearchActionType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static SearchActionType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return SEARCH_IN_MAP;
        }
        if (iIntValue == 1) {
            return SEARCH_ONLY_IN_THROUGH_INFORMATION;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + SearchActionType.class.getName());
    }

    public static SearchActionType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("SEARCH_ONLY_IN_THROUGH_INFORMATION")) {
            return SEARCH_ONLY_IN_THROUGH_INFORMATION;
        }
        if (str.equals("SEARCH_IN_MAP")) {
            return SEARCH_IN_MAP;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + SearchActionType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
