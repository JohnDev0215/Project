package api.structs.com.neusoft.onecore.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DatabaseOrigin {
    DatabaseOriginLocal(1),
    DatabaseOriginRemote(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DatabaseOrigin(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DatabaseOrigin getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 1) {
            return DatabaseOriginLocal;
        }
        if (iIntValue == 2) {
            return DatabaseOriginRemote;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DatabaseOrigin.class.getName());
    }

    public static DatabaseOrigin valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("DatabaseOriginLocal")) {
            return DatabaseOriginLocal;
        }
        if (str.equals("DatabaseOriginRemote")) {
            return DatabaseOriginRemote;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DatabaseOrigin.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
