package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ExternalStorageStatus {
    USB_MOUNTED(0),
    USB_UNMOUNTED(1),
    USB_MOUNTED_WITHOUT_DB_FILE(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ExternalStorageStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ExternalStorageStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return USB_MOUNTED;
        }
        if (iIntValue == 1) {
            return USB_UNMOUNTED;
        }
        if (iIntValue == 2) {
            return USB_MOUNTED_WITHOUT_DB_FILE;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ExternalStorageStatus.class.getName());
    }

    public static ExternalStorageStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "USB_MOUNTED_WITHOUT_DB_FILE":
                return USB_MOUNTED_WITHOUT_DB_FILE;
            case "USB_MOUNTED":
                return USB_MOUNTED;
            case "USB_UNMOUNTED":
                return USB_UNMOUNTED;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ExternalStorageStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
