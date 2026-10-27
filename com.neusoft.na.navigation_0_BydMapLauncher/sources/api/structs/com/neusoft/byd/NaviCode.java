package api.structs.com.neusoft.byd;

import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NaviCode {
    OK(0),
    ERROR(1),
    UNSUPPORTED(2),
    ALREADY_EXPECTED(3),
    ALREADY_MAX(4),
    ALREADY_MIN(5),
    OUT_OF_RANGE(6),
    NO_ROUTE_GUIDANCE(7),
    FAVORITE_LIST_FULL(8),
    TIMEOUT(9),
    NO_UNFINISHEDROUTE(10),
    RESUME_NEEDROUTE(11),
    RESUME_NOTROUTE(12),
    QUERYNEXTVIAPOINT_NONE(13);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NaviCode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NaviCode getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return OK;
            case 1:
                return ERROR;
            case 2:
                return UNSUPPORTED;
            case 3:
                return ALREADY_EXPECTED;
            case 4:
                return ALREADY_MAX;
            case 5:
                return ALREADY_MIN;
            case 6:
                return OUT_OF_RANGE;
            case 7:
                return NO_ROUTE_GUIDANCE;
            case 8:
                return FAVORITE_LIST_FULL;
            case 9:
                return TIMEOUT;
            case 10:
                return NO_UNFINISHEDROUTE;
            case 11:
                return RESUME_NEEDROUTE;
            case 12:
                return RESUME_NOTROUTE;
            case 13:
                return QUERYNEXTVIAPOINT_NONE;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NaviCode.class.getName());
        }
    }

    public static NaviCode valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "UNSUPPORTED":
                return UNSUPPORTED;
            case "NO_UNFINISHEDROUTE":
                return NO_UNFINISHEDROUTE;
            case "NO_ROUTE_GUIDANCE":
                return NO_ROUTE_GUIDANCE;
            case "ALREADY_MAX":
                return ALREADY_MAX;
            case "ALREADY_MIN":
                return ALREADY_MIN;
            case "RESUME_NOTROUTE":
                return RESUME_NOTROUTE;
            case "ALREADY_EXPECTED":
                return ALREADY_EXPECTED;
            case "FAVORITE_LIST_FULL":
                return FAVORITE_LIST_FULL;
            case "TIMEOUT":
                return TIMEOUT;
            case "RESUME_NEEDROUTE":
                return RESUME_NEEDROUTE;
            case "OK":
                return OK;
            case "ERROR":
                return ERROR;
            case "OUT_OF_RANGE":
                return OUT_OF_RANGE;
            case "QUERYNEXTVIAPOINT_NONE":
                return QUERYNEXTVIAPOINT_NONE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NaviCode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
