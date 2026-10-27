package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FavoriteStatus {
    CLOSE(0),
    OPEN(1),
    UNKNOWN(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    FavoriteStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static FavoriteStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return CLOSE;
        }
        if (iIntValue == 1) {
            return OPEN;
        }
        if (iIntValue == 2) {
            return UNKNOWN;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FavoriteStatus.class.getName());
    }

    public static FavoriteStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "OPEN":
                return OPEN;
            case "CLOSE":
                return CLOSE;
            case "UNKNOWN":
                return UNKNOWN;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FavoriteStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
