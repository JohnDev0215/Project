package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum DetailPageStatus {
    CLOSE(0),
    LOADING(1),
    COMPLETE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    DetailPageStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static DetailPageStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return CLOSE;
        }
        if (iIntValue == 1) {
            return LOADING;
        }
        if (iIntValue == 2) {
            return COMPLETE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + DetailPageStatus.class.getName());
    }

    public static DetailPageStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "CLOSE":
                return CLOSE;
            case "COMPLETE":
                return COMPLETE;
            case "LOADING":
                return LOADING;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + DetailPageStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
