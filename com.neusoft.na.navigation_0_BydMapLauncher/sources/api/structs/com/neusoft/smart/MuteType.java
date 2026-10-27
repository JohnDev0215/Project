package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MuteType {
    MUTE_PERMANENT(1),
    MUTE_TEMPORARY(2),
    MUTE_PERMANENT_CANCEL(3),
    MUTE_TEMPORARY_CANCEL(4);

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
        if (iIntValue == 1) {
            return MUTE_PERMANENT;
        }
        if (iIntValue == 2) {
            return MUTE_TEMPORARY;
        }
        if (iIntValue == 3) {
            return MUTE_PERMANENT_CANCEL;
        }
        if (iIntValue == 4) {
            return MUTE_TEMPORARY_CANCEL;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MuteType.class.getName());
    }

    public static MuteType valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "MUTE_TEMPORARY":
                return MUTE_TEMPORARY;
            case "MUTE_TEMPORARY_CANCEL":
                return MUTE_TEMPORARY_CANCEL;
            case "MUTE_PERMANENT":
                return MUTE_PERMANENT;
            case "MUTE_PERMANENT_CANCEL":
                return MUTE_PERMANENT_CANCEL;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MuteType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
