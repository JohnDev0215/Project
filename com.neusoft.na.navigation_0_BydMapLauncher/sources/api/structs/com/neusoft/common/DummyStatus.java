package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DummyStatus {
    PLACEHOLDER_STATUS(0);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DummyStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DummyStatus getByValue(Integer num) {
        if (num.intValue() == 0) {
            return PLACEHOLDER_STATUS;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DummyStatus.class.getName());
    }

    public static DummyStatus valueOfSerializedName(String str) {
        str.hashCode();
        if (str.equals("PLACEHOLDER_STATUS")) {
            return PLACEHOLDER_STATUS;
        }
        throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DummyStatus.class.getName());
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
