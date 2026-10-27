package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MuteTypeComm {
    Navi_Com_Silent(1),
    Navi_Com_NonSilent(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MuteTypeComm(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MuteTypeComm getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 1) {
            return Navi_Com_Silent;
        }
        if (iIntValue == 2) {
            return Navi_Com_NonSilent;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MuteTypeComm.class.getName());
    }

    public static MuteTypeComm valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("Navi_Com_Silent")) {
            return Navi_Com_Silent;
        }
        if (str.equals("Navi_Com_NonSilent")) {
            return Navi_Com_NonSilent;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MuteTypeComm.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
