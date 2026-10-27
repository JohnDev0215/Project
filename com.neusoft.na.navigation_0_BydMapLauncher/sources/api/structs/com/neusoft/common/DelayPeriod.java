package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DelayPeriod {
    LONG_DELAY(0),
    SHORT_DELAY(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DelayPeriod(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DelayPeriod getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return LONG_DELAY;
        }
        if (iIntValue == 1) {
            return SHORT_DELAY;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DelayPeriod.class.getName());
    }

    public static DelayPeriod valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("SHORT_DELAY")) {
            return SHORT_DELAY;
        }
        if (str.equals("LONG_DELAY")) {
            return LONG_DELAY;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DelayPeriod.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
