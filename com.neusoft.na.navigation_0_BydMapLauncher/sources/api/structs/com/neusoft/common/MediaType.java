package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MediaType {
    E_FILESYSTEM(0),
    E_USB(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MediaType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MediaType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return E_FILESYSTEM;
        }
        if (iIntValue == 1) {
            return E_USB;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MediaType.class.getName());
    }

    public static MediaType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("E_USB")) {
            return E_USB;
        }
        if (str.equals("E_FILESYSTEM")) {
            return E_FILESYSTEM;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MediaType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
