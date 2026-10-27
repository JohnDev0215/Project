package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum TunnelEntranceExitStatus {
    ENTRANCE_STATUS(0),
    EXIT_STATUS(1),
    UNKNOWN_STATUS(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    TunnelEntranceExitStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static TunnelEntranceExitStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ENTRANCE_STATUS;
        }
        if (iIntValue == 1) {
            return EXIT_STATUS;
        }
        if (iIntValue == 2) {
            return UNKNOWN_STATUS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + TunnelEntranceExitStatus.class.getName());
    }

    public static TunnelEntranceExitStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "EXIT_STATUS":
                return EXIT_STATUS;
            case "UNKNOWN_STATUS":
                return UNKNOWN_STATUS;
            case "ENTRANCE_STATUS":
                return ENTRANCE_STATUS;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + TunnelEntranceExitStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
