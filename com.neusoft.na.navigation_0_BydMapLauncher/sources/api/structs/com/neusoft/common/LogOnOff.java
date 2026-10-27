package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum LogOnOff {
    ENABLE_TRACE_LOG(0),
    DISABLE_TRACE_LOG(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    LogOnOff(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static LogOnOff getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ENABLE_TRACE_LOG;
        }
        if (iIntValue == 1) {
            return DISABLE_TRACE_LOG;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + LogOnOff.class.getName());
    }

    public static LogOnOff valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("DISABLE_TRACE_LOG")) {
            return DISABLE_TRACE_LOG;
        }
        if (str.equals("ENABLE_TRACE_LOG")) {
            return ENABLE_TRACE_LOG;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + LogOnOff.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
