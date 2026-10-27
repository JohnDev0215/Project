package api.structs.com.neusoft.smart;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MuteStatus {
    MUTE_ON(40),
    MUTE_OFF(41);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MuteStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MuteStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 40) {
            return MUTE_ON;
        }
        if (iIntValue == 41) {
            return MUTE_OFF;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MuteStatus.class.getName());
    }

    public static MuteStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("MUTE_OFF")) {
            return MUTE_OFF;
        }
        if (str.equals("MUTE_ON")) {
            return MUTE_ON;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MuteStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
