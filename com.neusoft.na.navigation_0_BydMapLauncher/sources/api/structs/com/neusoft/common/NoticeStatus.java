package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NoticeStatus {
    STATUS_START(0),
    STATUS_RUNNING(1),
    STATUS_FINISH(2);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NoticeStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NoticeStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return STATUS_START;
        }
        if (iIntValue == 1) {
            return STATUS_RUNNING;
        }
        if (iIntValue == 2) {
            return STATUS_FINISH;
        }
        throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NoticeStatus.class.getName());
    }

    public static NoticeStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "STATUS_RUNNING":
                return STATUS_RUNNING;
            case "STATUS_FINISH":
                return STATUS_FINISH;
            case "STATUS_START":
                return STATUS_START;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NoticeStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
