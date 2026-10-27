package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MuteType {
    MUTE_ENABLE(0),
    MUTE_DISABLE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MuteType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MuteType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return MUTE_ENABLE;
        }
        if (iIntValue == 1) {
            return MUTE_DISABLE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MuteType.class.getName());
    }

    public static MuteType valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("MUTE_ENABLE")) {
            return MUTE_ENABLE;
        }
        if (str.equals("MUTE_DISABLE")) {
            return MUTE_DISABLE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MuteType.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
