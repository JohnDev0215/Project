package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FavoriteType {
    GENERAL_FAVORITES(0),
    HOME(1),
    COMPANY(2),
    ALL_FAVORITES(3);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    FavoriteType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static FavoriteType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return GENERAL_FAVORITES;
        }
        if (iIntValue == 1) {
            return HOME;
        }
        if (iIntValue == 2) {
            return COMPANY;
        }
        if (iIntValue == 3) {
            return ALL_FAVORITES;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FavoriteType.class.getName());
    }

    public static FavoriteType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "GENERAL_FAVORITES":
                return GENERAL_FAVORITES;
            case "ALL_FAVORITES":
                return ALL_FAVORITES;
            case "HOME":
                return HOME;
            case "COMPANY":
                return COMPANY;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FavoriteType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
