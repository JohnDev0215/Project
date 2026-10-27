package api.structs.com.neusoft.ecarx;

import cerence.ark.assistant.controller.enumeration.FunctionAvailabilityEnum;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum RestoreGuidanceInfo {
    AVAILABLE(0),
    NOT_AVAILABLE(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    RestoreGuidanceInfo(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static RestoreGuidanceInfo getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return AVAILABLE;
        }
        if (iIntValue == 1) {
            return NOT_AVAILABLE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + RestoreGuidanceInfo.class.getName());
    }

    public static RestoreGuidanceInfo valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("NOT_AVAILABLE")) {
            return NOT_AVAILABLE;
        }
        if (str.equals(FunctionAvailabilityEnum.AVAILABLE)) {
            return AVAILABLE;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + RestoreGuidanceInfo.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
