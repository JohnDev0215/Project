package api.structs.com.neusoft.common;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NavAPPRunningStatus {
    NAV_MAINAPP_STARTING(0),
    NAV_MAINAPP_OPERABLE(1),
    NAV_MAINAPP_EXIT(2),
    NAV_SLAVEAPP_STARTING(3),
    NAV_SLAVEAPP_OPERABLE(4),
    NAV_SLAVEAPP_EXIT(5),
    NAV_CLUSTER_STARTING(6),
    NAV_CLUSTER_OPERABLE(7),
    NAV_CLUSTER_EXIT(8);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NavAPPRunningStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NavAPPRunningStatus getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return NAV_MAINAPP_STARTING;
            case 1:
                return NAV_MAINAPP_OPERABLE;
            case 2:
                return NAV_MAINAPP_EXIT;
            case 3:
                return NAV_SLAVEAPP_STARTING;
            case 4:
                return NAV_SLAVEAPP_OPERABLE;
            case 5:
                return NAV_SLAVEAPP_EXIT;
            case 6:
                return NAV_CLUSTER_STARTING;
            case 7:
                return NAV_CLUSTER_OPERABLE;
            case 8:
                return NAV_CLUSTER_EXIT;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NavAPPRunningStatus.class.getName());
        }
    }

    public static NavAPPRunningStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "NAV_SLAVEAPP_OPERABLE":
                return NAV_SLAVEAPP_OPERABLE;
            case "NAV_SLAVEAPP_EXIT":
                return NAV_SLAVEAPP_EXIT;
            case "NAV_CLUSTER_OPERABLE":
                return NAV_CLUSTER_OPERABLE;
            case "NAV_MAINAPP_STARTING":
                return NAV_MAINAPP_STARTING;
            case "NAV_SLAVEAPP_STARTING":
                return NAV_SLAVEAPP_STARTING;
            case "NAV_CLUSTER_STARTING":
                return NAV_CLUSTER_STARTING;
            case "NAV_CLUSTER_EXIT":
                return NAV_CLUSTER_EXIT;
            case "NAV_MAINAPP_EXIT":
                return NAV_MAINAPP_EXIT;
            case "NAV_MAINAPP_OPERABLE":
                return NAV_MAINAPP_OPERABLE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NavAPPRunningStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
