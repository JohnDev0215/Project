package api.structs.com.neusoft.greatwall;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum CalculationResult {
    CALCULATED_SUCCESSFUL(0),
    CALCULATED_FAILED(1);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    CalculationResult(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static CalculationResult getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return CALCULATED_SUCCESSFUL;
        }
        if (iIntValue == 1) {
            return CALCULATED_FAILED;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + CalculationResult.class.getName());
    }

    public static CalculationResult valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("CALCULATED_SUCCESSFUL")) {
            return CALCULATED_SUCCESSFUL;
        }
        if (str.equals("CALCULATED_FAILED")) {
            return CALCULATED_FAILED;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + CalculationResult.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
