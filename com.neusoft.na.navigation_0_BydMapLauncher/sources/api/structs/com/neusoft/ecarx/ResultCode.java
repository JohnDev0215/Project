package api.structs.com.neusoft.ecarx;

import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum ResultCode {
    FAILURE_CURRENT_MAP_NOT_SUPPORT(-1),
    FAILURE_NO_PERMISSION(1000),
    FAILURE_MAP_NOT_INSTALLED(1001),
    FAILURE_REQUEST_TIMEOUT(1002),
    FAILURE_NO_HISTORIACL_DESTINATION_DATA(1003),
    SUCCESS(10000),
    FAILURE_ILLEGAL_PARAMETER(Integer.valueOf(TestVehicleHealthManagerImplCallback.SET_REAR_WIPER_CHECK_STATE)),
    FAILURE_MISSING_OFFLINE_MAP_DATA(Integer.valueOf(TestVehicleHealthManagerImplCallback.RESET_TIRE_PRESSURE)),
    FAILURE_MAP_SOFTWARE_NOT_AUTHORIZED(Integer.valueOf(TestVehicleHealthManagerImplCallback.RESET_OIL_LIFE_TIME)),
    FAILURE_ROUTE_NOT_PLANNED(Integer.valueOf(TestVehicleHealthManagerImplCallback.SET_OIL_RECORD_LIST)),
    FAILURE_SET_NO_HOME_LOCATION(10005),
    FAILURE_SET_NO_COMPANY_LOCATION(10006),
    FAILURE_CURRENTLY_ZOOMIN_TO_MAX(10007),
    FAILURE_CURRENTLY_ZOOMOUT_TO_MIN(10008),
    FAILURE_POOR_NEUWORK(10009),
    FAILURE_UNSUPPORTED_VIEW(10010),
    FAILURE_REPEAT_APPROACH_POINT(10011),
    FAILURE_APPROACH_POINT_FULL(10012),
    FAILURE_START_POINT_WRONG(10013),
    FAILURE_END_POINT_WRONG(10014),
    FAILURE_WAYPOINT_WRONG(10015),
    FAILURE_ROUTE_PLANNING_FAILED(10016),
    FAILURE_NAVIGATION_NOT_START(10018),
    FAILURE_NO_NETWORK_NO_DATA(10019),
    FAILURE_UNKNOWN_ERROR(10020),
    FAILURE_CITY_NOT_SUPPORT(10021),
    FAILURE_Road_NOT_SUPPORT(10022),
    FAILURE_NO_RESULT_FOUND(10023),
    FAILURE_NO_ACCESS(10024),
    FAILURE_ENGINE_INITIALIZATION_FAILED(10025),
    FAILURE_SDK_INITIALIZATION_ERROR(10026),
    FAILURE_AUTO_NOT_START(10027),
    FAILURE_FEATUR_NOT_SUPPORT(10028),
    FAILURE_NOT_SUPPORT_IN_BACKGROUND(10029),
    FAILURE_CALLBACK_EXCEPTION(10030),
    FAILURE_EVENT_LISTENER_IS_EMPTY(10031),
    FAILURE_REQUEST_FAILURE(10032),
    FAILURE_EXECUTE_ACTION_CANCEL(10034),
    SUCCESS_SET_HOME(10035),
    SUCCESS_SET_COMPANY(10036),
    SUCCESS_SET_COMMON_COLLECTION_POINT(10037),
    FAILURE_EXECUTION_FAILED_CURRENTLY_MUTED(10038),
    FAILURE_EXECUTION_FAILED_VOLUME_IS_NOW_RESTORED(10039),
    FAILURE_NO_PARKING_DATA(10043),
    FAILURE_EXCEEDING_QUANTITY_LIMIT(20001);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    ResultCode(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static ResultCode getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == -1) {
            return FAILURE_CURRENT_MAP_NOT_SUPPORT;
        }
        if (iIntValue == 10043) {
            return FAILURE_NO_PARKING_DATA;
        }
        if (iIntValue != 20001) {
            switch (iIntValue) {
                case 1000:
                    return FAILURE_NO_PERMISSION;
                case 1001:
                    return FAILURE_MAP_NOT_INSTALLED;
                case 1002:
                    return FAILURE_REQUEST_TIMEOUT;
                case 1003:
                    return FAILURE_NO_HISTORIACL_DESTINATION_DATA;
                default:
                    switch (iIntValue) {
                        case 10000:
                            return SUCCESS;
                        case TestVehicleHealthManagerImplCallback.SET_REAR_WIPER_CHECK_STATE /* 10001 */:
                            return FAILURE_ILLEGAL_PARAMETER;
                        case TestVehicleHealthManagerImplCallback.RESET_TIRE_PRESSURE /* 10002 */:
                            return FAILURE_MISSING_OFFLINE_MAP_DATA;
                        case TestVehicleHealthManagerImplCallback.RESET_OIL_LIFE_TIME /* 10003 */:
                            return FAILURE_MAP_SOFTWARE_NOT_AUTHORIZED;
                        case TestVehicleHealthManagerImplCallback.SET_OIL_RECORD_LIST /* 10004 */:
                            return FAILURE_ROUTE_NOT_PLANNED;
                        case 10005:
                            return FAILURE_SET_NO_HOME_LOCATION;
                        case 10006:
                            return FAILURE_SET_NO_COMPANY_LOCATION;
                        case 10007:
                            return FAILURE_CURRENTLY_ZOOMIN_TO_MAX;
                        case 10008:
                            return FAILURE_CURRENTLY_ZOOMOUT_TO_MIN;
                        case 10009:
                            return FAILURE_POOR_NEUWORK;
                        case 10010:
                            return FAILURE_UNSUPPORTED_VIEW;
                        case 10011:
                            return FAILURE_REPEAT_APPROACH_POINT;
                        case 10012:
                            return FAILURE_APPROACH_POINT_FULL;
                        case 10013:
                            return FAILURE_START_POINT_WRONG;
                        case 10014:
                            return FAILURE_END_POINT_WRONG;
                        case 10015:
                            return FAILURE_WAYPOINT_WRONG;
                        case 10016:
                            return FAILURE_ROUTE_PLANNING_FAILED;
                        default:
                            switch (iIntValue) {
                                case 10018:
                                    return FAILURE_NAVIGATION_NOT_START;
                                case 10019:
                                    return FAILURE_NO_NETWORK_NO_DATA;
                                case 10020:
                                    return FAILURE_UNKNOWN_ERROR;
                                case 10021:
                                    return FAILURE_CITY_NOT_SUPPORT;
                                case 10022:
                                    return FAILURE_Road_NOT_SUPPORT;
                                case 10023:
                                    return FAILURE_NO_RESULT_FOUND;
                                case 10024:
                                    return FAILURE_NO_ACCESS;
                                case 10025:
                                    return FAILURE_ENGINE_INITIALIZATION_FAILED;
                                case 10026:
                                    return FAILURE_SDK_INITIALIZATION_ERROR;
                                case 10027:
                                    return FAILURE_AUTO_NOT_START;
                                case 10028:
                                    return FAILURE_FEATUR_NOT_SUPPORT;
                                case 10029:
                                    return FAILURE_NOT_SUPPORT_IN_BACKGROUND;
                                case 10030:
                                    return FAILURE_CALLBACK_EXCEPTION;
                                case 10031:
                                    return FAILURE_EVENT_LISTENER_IS_EMPTY;
                                case 10032:
                                    return FAILURE_REQUEST_FAILURE;
                                default:
                                    switch (iIntValue) {
                                        case 10034:
                                            return FAILURE_EXECUTE_ACTION_CANCEL;
                                        case 10035:
                                            return SUCCESS_SET_HOME;
                                        case 10036:
                                            return SUCCESS_SET_COMPANY;
                                        case 10037:
                                            return SUCCESS_SET_COMMON_COLLECTION_POINT;
                                        case 10038:
                                            return FAILURE_EXECUTION_FAILED_CURRENTLY_MUTED;
                                        case 10039:
                                            return FAILURE_EXECUTION_FAILED_VOLUME_IS_NOW_RESTORED;
                                        default:
                                            throw new IllegalArgumentException("invalid value '" + num + "' for enum " + ResultCode.class.getName());
                                    }
                            }
                    }
            }
        }
        return FAILURE_EXCEEDING_QUANTITY_LIMIT;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static ResultCode valueOfSerializedName(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2096269568:
                if (str.equals("FAILURE_NAVIGATION_NOT_START")) {
                    b = 0;
                }
                break;
            case -2086648023:
                if (str.equals("FAILURE_SET_NO_HOME_LOCATION")) {
                    b = 1;
                }
                break;
            case -2061245587:
                if (str.equals("FAILURE_ROUTE_NOT_PLANNED")) {
                    b = 2;
                }
                break;
            case -1862694325:
                if (str.equals("FAILURE_ILLEGAL_PARAMETER")) {
                    b = 3;
                }
                break;
            case -1814552932:
                if (str.equals("FAILURE_REQUEST_TIMEOUT")) {
                    b = 4;
                }
                break;
            case -1787014717:
                if (str.equals("FAILURE_SDK_INITIALIZATION_ERROR")) {
                    b = 5;
                }
                break;
            case -1745752536:
                if (str.equals("FAILURE_FEATUR_NOT_SUPPORT")) {
                    b = 6;
                }
                break;
            case -1736748136:
                if (str.equals("SUCCESS_SET_HOME")) {
                    b = 7;
                }
                break;
            case -1699771483:
                if (str.equals("FAILURE_END_POINT_WRONG")) {
                    b = 8;
                }
                break;
            case -1610682506:
                if (str.equals("FAILURE_MAP_NOT_INSTALLED")) {
                    b = 9;
                }
                break;
            case -1609770760:
                if (str.equals("FAILURE_NO_PERMISSION")) {
                    b = 10;
                }
                break;
            case -1587215675:
                if (str.equals("FAILURE_REQUEST_FAILURE")) {
                    b = 11;
                }
                break;
            case -1191001917:
                if (str.equals("FAILURE_SET_NO_COMPANY_LOCATION")) {
                    b = 12;
                }
                break;
            case -1172415765:
                if (str.equals("FAILURE_NO_HISTORIACL_DESTINATION_DATA")) {
                    b = 13;
                }
                break;
            case -1149187101:
                if (str.equals("SUCCESS")) {
                    b = 14;
                }
                break;
            case -1086522498:
                if (str.equals("FAILURE_UNKNOWN_ERROR")) {
                    b = 15;
                }
                break;
            case -1028403955:
                if (str.equals("FAILURE_NO_ACCESS")) {
                    b = 16;
                }
                break;
            case -961221580:
                if (str.equals("FAILURE_ROUTE_PLANNING_FAILED")) {
                    b = 17;
                }
                break;
            case -942437500:
                if (str.equals("FAILURE_CITY_NOT_SUPPORT")) {
                    b = 18;
                }
                break;
            case -643548231:
                if (str.equals("FAILURE_CURRENTLY_ZOOMIN_TO_MAX")) {
                    b = 19;
                }
                break;
            case -539254588:
                if (str.equals("FAILURE_UNSUPPORTED_VIEW")) {
                    b = 20;
                }
                break;
            case -501346130:
                if (str.equals("FAILURE_NO_NETWORK_NO_DATA")) {
                    b = 21;
                }
                break;
            case -394779340:
                if (str.equals("FAILURE_ENGINE_INITIALIZATION_FAILED")) {
                    b = 22;
                }
                break;
            case -350391065:
                if (str.equals("FAILURE_MAP_SOFTWARE_NOT_AUTHORIZED")) {
                    b = 23;
                }
                break;
            case -316863947:
                if (str.equals("FAILURE_EXECUTION_FAILED_VOLUME_IS_NOW_RESTORED")) {
                    b = 24;
                }
                break;
            case -152799831:
                if (str.equals("FAILURE_NO_RESULT_FOUND")) {
                    b = 25;
                }
                break;
            case -111040864:
                if (str.equals("FAILURE_CURRENTLY_ZOOMOUT_TO_MIN")) {
                    b = 26;
                }
                break;
            case -6341849:
                if (str.equals("FAILURE_POOR_NEUWORK")) {
                    b = 27;
                }
                break;
            case 7172311:
                if (str.equals("FAILURE_NOT_SUPPORT_IN_BACKGROUND")) {
                    b = 28;
                }
                break;
            case 190718189:
                if (str.equals("FAILURE_EXCEEDING_QUANTITY_LIMIT")) {
                    b = 29;
                }
                break;
            case 225789354:
                if (str.equals("FAILURE_CALLBACK_EXCEPTION")) {
                    b = 30;
                }
                break;
            case 232117721:
                if (str.equals("FAILURE_Road_NOT_SUPPORT")) {
                    b = 31;
                }
                break;
            case 425814743:
                if (str.equals("FAILURE_MISSING_OFFLINE_MAP_DATA")) {
                    b = 32;
                }
                break;
            case 530101708:
                if (str.equals("FAILURE_EXECUTION_FAILED_CURRENTLY_MUTED")) {
                    b = 33;
                }
                break;
            case 655859752:
                if (str.equals("FAILURE_REPEAT_APPROACH_POINT")) {
                    b = 34;
                }
                break;
            case 723075744:
                if (str.equals("FAILURE_APPROACH_POINT_FULL")) {
                    b = 35;
                }
                break;
            case 783741988:
                if (str.equals("FAILURE_EXECUTE_ACTION_CANCEL")) {
                    b = 36;
                }
                break;
            case 804846404:
                if (str.equals("FAILURE_WAYPOINT_WRONG")) {
                    b = 37;
                }
                break;
            case 850492645:
                if (str.equals("FAILURE_CURRENT_MAP_NOT_SUPPORT")) {
                    b = 38;
                }
                break;
            case 945184986:
                if (str.equals("FAILURE_NO_PARKING_DATA")) {
                    b = WPCModel.cSignalId0x27;
                }
                break;
            case 1063255195:
                if (str.equals("FAILURE_AUTO_NOT_START")) {
                    b = 40;
                }
                break;
            case 1217796588:
                if (str.equals("FAILURE_START_POINT_WRONG")) {
                    b = 41;
                }
                break;
            case 1806280970:
                if (str.equals("SUCCESS_SET_COMMON_COLLECTION_POINT")) {
                    b = 42;
                }
                break;
            case 1865136900:
                if (str.equals("SUCCESS_SET_COMPANY")) {
                    b = 43;
                }
                break;
            case 2100074153:
                if (str.equals("FAILURE_EVENT_LISTENER_IS_EMPTY")) {
                    b = 44;
                }
                break;
        }
        switch (b) {
            case 0:
                return FAILURE_NAVIGATION_NOT_START;
            case 1:
                return FAILURE_SET_NO_HOME_LOCATION;
            case 2:
                return FAILURE_ROUTE_NOT_PLANNED;
            case 3:
                return FAILURE_ILLEGAL_PARAMETER;
            case 4:
                return FAILURE_REQUEST_TIMEOUT;
            case 5:
                return FAILURE_SDK_INITIALIZATION_ERROR;
            case 6:
                return FAILURE_FEATUR_NOT_SUPPORT;
            case 7:
                return SUCCESS_SET_HOME;
            case 8:
                return FAILURE_END_POINT_WRONG;
            case 9:
                return FAILURE_MAP_NOT_INSTALLED;
            case 10:
                return FAILURE_NO_PERMISSION;
            case 11:
                return FAILURE_REQUEST_FAILURE;
            case 12:
                return FAILURE_SET_NO_COMPANY_LOCATION;
            case 13:
                return FAILURE_NO_HISTORIACL_DESTINATION_DATA;
            case 14:
                return SUCCESS;
            case 15:
                return FAILURE_UNKNOWN_ERROR;
            case 16:
                return FAILURE_NO_ACCESS;
            case 17:
                return FAILURE_ROUTE_PLANNING_FAILED;
            case 18:
                return FAILURE_CITY_NOT_SUPPORT;
            case 19:
                return FAILURE_CURRENTLY_ZOOMIN_TO_MAX;
            case 20:
                return FAILURE_UNSUPPORTED_VIEW;
            case 21:
                return FAILURE_NO_NETWORK_NO_DATA;
            case 22:
                return FAILURE_ENGINE_INITIALIZATION_FAILED;
            case 23:
                return FAILURE_MAP_SOFTWARE_NOT_AUTHORIZED;
            case 24:
                return FAILURE_EXECUTION_FAILED_VOLUME_IS_NOW_RESTORED;
            case 25:
                return FAILURE_NO_RESULT_FOUND;
            case 26:
                return FAILURE_CURRENTLY_ZOOMOUT_TO_MIN;
            case 27:
                return FAILURE_POOR_NEUWORK;
            case 28:
                return FAILURE_NOT_SUPPORT_IN_BACKGROUND;
            case 29:
                return FAILURE_EXCEEDING_QUANTITY_LIMIT;
            case 30:
                return FAILURE_CALLBACK_EXCEPTION;
            case 31:
                return FAILURE_Road_NOT_SUPPORT;
            case 32:
                return FAILURE_MISSING_OFFLINE_MAP_DATA;
            case 33:
                return FAILURE_EXECUTION_FAILED_CURRENTLY_MUTED;
            case 34:
                return FAILURE_REPEAT_APPROACH_POINT;
            case 35:
                return FAILURE_APPROACH_POINT_FULL;
            case 36:
                return FAILURE_EXECUTE_ACTION_CANCEL;
            case 37:
                return FAILURE_WAYPOINT_WRONG;
            case 38:
                return FAILURE_CURRENT_MAP_NOT_SUPPORT;
            case 39:
                return FAILURE_NO_PARKING_DATA;
            case 40:
                return FAILURE_AUTO_NOT_START;
            case 41:
                return FAILURE_START_POINT_WRONG;
            case 42:
                return SUCCESS_SET_COMMON_COLLECTION_POINT;
            case 43:
                return SUCCESS_SET_COMPANY;
            case 44:
                return FAILURE_EVENT_LISTENER_IS_EMPTY;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + ResultCode.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
