package api.structs.com.neusoft.ecarx;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum MapStatus {
    APP_START_FINISH(8),
    APP_FINISH(9),
    APP_FOREGROUND(10),
    APP_BACKGROUND(11);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    MapStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static MapStatus getByValue(Integer num) {
        switch (num.intValue()) {
            case 8:
                return APP_START_FINISH;
            case 9:
                return APP_FINISH;
            case 10:
                return APP_FOREGROUND;
            case 11:
                return APP_BACKGROUND;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + MapStatus.class.getName());
        }
    }

    public static MapStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "APP_START_FINISH":
                return APP_START_FINISH;
            case "APP_FINISH":
                return APP_FINISH;
            case "APP_FOREGROUND":
                return APP_FOREGROUND;
            case "APP_BACKGROUND":
                return APP_BACKGROUND;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + MapStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
