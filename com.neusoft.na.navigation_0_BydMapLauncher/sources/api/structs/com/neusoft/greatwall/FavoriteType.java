package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum FavoriteType {
    HOME(0),
    COMPANY(1),
    FAVORITE(2);

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
            return HOME;
        }
        if (iIntValue == 1) {
            return COMPANY;
        }
        if (iIntValue == 2) {
            return FAVORITE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + FavoriteType.class.getName());
    }

    public static FavoriteType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "HOME":
                return HOME;
            case "COMPANY":
                return COMPANY;
            case "FAVORITE":
                return FAVORITE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + FavoriteType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
