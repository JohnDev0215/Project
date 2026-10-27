package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum GuidedStatus {
    GUIDED_ACTIVE(0),
    GUIDED_INACTIVE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    GuidedStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static GuidedStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return GUIDED_ACTIVE;
        }
        if (iIntValue == 1) {
            return GUIDED_INACTIVE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + GuidedStatus.class.getName());
    }

    public static GuidedStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("GUIDED_ACTIVE")) {
            return GUIDED_ACTIVE;
        }
        if (str.equals("GUIDED_INACTIVE")) {
            return GUIDED_INACTIVE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + GuidedStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
