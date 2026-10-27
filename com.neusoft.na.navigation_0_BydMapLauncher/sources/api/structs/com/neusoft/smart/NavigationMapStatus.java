package api.structs.com.neusoft.smart;

import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import cerence.ark.assistant.controller.enumeration.ZoomMapTypeEnum;
import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NavigationMapStatus {
    ROUTE_VIEW_IN(0),
    ROUTE_VIEW_OUT(1),
    APP_START(7),
    APP_START_FINISH(8),
    APP_FINISH(9),
    APP_FOREGROUND(10),
    APP_BACKGROUND(11),
    ROUTE_START(13),
    ROUTE_SUCCESS(14),
    ROUTE_FAILED(15),
    GUIDE_START(16),
    GUIDE_STOP(17),
    ARRIVE_DESTINATION(18),
    ZOOM_OUT(19),
    ZOOM_IN(20),
    ZOOM_MAX(21),
    ZOOM_MIN(22),
    TMC_ON(23),
    TMC_OFF(24),
    CAR_UP_3D(25),
    NORTH_UP_2D(26),
    CAR_UP_2D(27),
    CRUISE_START(28),
    CRUISE_STOP(29),
    SIMULATE_GUIDE_START(33),
    SIMULATE_GUIDE_PAUSE(34),
    SIMULATE_GUIDE_STOP(35),
    PERMANENT_MUTE_ON(40),
    MUTE_OFF(41),
    TEMPORARY_MUTE_ON(42),
    GUIDANCE_VIEW_ENABLE(43),
    GUIDANCE_VIEW_DISABLE(44),
    START_SEARCH_RESTAURANT(45),
    START_SEARCH_PARKING(46),
    START_SEARCH_CHARGING_STATION(47),
    MAP_2_DIM_STARTED(116),
    MAP_2_DIM_STOPPED(117),
    AR_GUIDE_ON(120),
    AR_GUIDE_OFF(121),
    FULL_VIEW_ON(122),
    FULL_VIEW_OFF(123),
    WIDGET_FRIST_FRAME_DARWN(124),
    REROUTING(9001),
    ENTER_TUNNEL(9002),
    LEAVE_TUNNEL(9003),
    GUIDE_VEHICLE_YAW(9004),
    PSD_APP_FOREGROUND(200),
    PSD_APP_BACKGROUND(201),
    UNKNOWN(Integer.valueOf(BYDAutoStatisticDevice.MILEAGE_MAX));

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NavigationMapStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NavigationMapStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 0) {
            return ROUTE_VIEW_IN;
        }
        if (iIntValue == 1) {
            return ROUTE_VIEW_OUT;
        }
        if (iIntValue == 116) {
            return MAP_2_DIM_STARTED;
        }
        if (iIntValue == 117) {
            return MAP_2_DIM_STOPPED;
        }
        if (iIntValue == 200) {
            return PSD_APP_FOREGROUND;
        }
        if (iIntValue == 201) {
            return PSD_APP_BACKGROUND;
        }
        if (iIntValue != 99999) {
            switch (iIntValue) {
                case 7:
                    return APP_START;
                case 8:
                    return APP_START_FINISH;
                case 9:
                    return APP_FINISH;
                case 10:
                    return APP_FOREGROUND;
                case 11:
                    return APP_BACKGROUND;
                default:
                    switch (iIntValue) {
                        case 13:
                            return ROUTE_START;
                        case 14:
                            return ROUTE_SUCCESS;
                        case 15:
                            return ROUTE_FAILED;
                        case 16:
                            return GUIDE_START;
                        case 17:
                            return GUIDE_STOP;
                        case 18:
                            return ARRIVE_DESTINATION;
                        case 19:
                            return ZOOM_OUT;
                        case 20:
                            return ZOOM_IN;
                        case 21:
                            return ZOOM_MAX;
                        case 22:
                            return ZOOM_MIN;
                        case 23:
                            return TMC_ON;
                        case 24:
                            return TMC_OFF;
                        case 25:
                            return CAR_UP_3D;
                        case 26:
                            return NORTH_UP_2D;
                        case 27:
                            return CAR_UP_2D;
                        case 28:
                            return CRUISE_START;
                        case 29:
                            return CRUISE_STOP;
                        default:
                            switch (iIntValue) {
                                case 33:
                                    return SIMULATE_GUIDE_START;
                                case 34:
                                    return SIMULATE_GUIDE_PAUSE;
                                case 35:
                                    return SIMULATE_GUIDE_STOP;
                                default:
                                    switch (iIntValue) {
                                        case 40:
                                            return PERMANENT_MUTE_ON;
                                        case 41:
                                            return MUTE_OFF;
                                        case 42:
                                            return TEMPORARY_MUTE_ON;
                                        case 43:
                                            return GUIDANCE_VIEW_ENABLE;
                                        case 44:
                                            return GUIDANCE_VIEW_DISABLE;
                                        case 45:
                                            return START_SEARCH_RESTAURANT;
                                        case 46:
                                            return START_SEARCH_PARKING;
                                        case 47:
                                            return START_SEARCH_CHARGING_STATION;
                                        default:
                                            switch (iIntValue) {
                                                case 120:
                                                    return AR_GUIDE_ON;
                                                case 121:
                                                    return AR_GUIDE_OFF;
                                                case 122:
                                                    return FULL_VIEW_ON;
                                                case 123:
                                                    return FULL_VIEW_OFF;
                                                case 124:
                                                    return WIDGET_FRIST_FRAME_DARWN;
                                                default:
                                                    switch (iIntValue) {
                                                        case 9001:
                                                            return REROUTING;
                                                        case 9002:
                                                            return ENTER_TUNNEL;
                                                        case 9003:
                                                            return LEAVE_TUNNEL;
                                                        case 9004:
                                                            return GUIDE_VEHICLE_YAW;
                                                        default:
                                                            throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NavigationMapStatus.class.getName());
                                                    }
                                            }
                                    }
                            }
                    }
            }
        }
        return UNKNOWN;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static NavigationMapStatus valueOfSerializedName(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2085832530:
                if (str.equals("APP_START_FINISH")) {
                    b = 0;
                }
                break;
            case -1816917676:
                if (str.equals("TMC_ON")) {
                    b = 1;
                }
                break;
            case -1807584839:
                if (str.equals("GUIDE_VEHICLE_YAW")) {
                    b = 2;
                }
                break;
            case -1766869577:
                if (str.equals("TEMPORARY_MUTE_ON")) {
                    b = 3;
                }
                break;
            case -1672154163:
                if (str.equals("ROUTE_SUCCESS")) {
                    b = 4;
                }
                break;
            case -1541127040:
                if (str.equals("SIMULATE_GUIDE_STOP")) {
                    b = 5;
                }
                break;
            case -1405746313:
                if (str.equals("START_SEARCH_RESTAURANT")) {
                    b = 6;
                }
                break;
            case -1394885120:
                if (str.equals("START_SEARCH_CHARGING_STATION")) {
                    b = 7;
                }
                break;
            case -1349880744:
                if (str.equals("CRUISE_START")) {
                    b = 8;
                }
                break;
            case -1347317782:
                if (str.equals("ROUTE_VIEW_OUT")) {
                    b = 9;
                }
                break;
            case -1290470100:
                if (str.equals("CRUISE_STOP")) {
                    b = 10;
                }
                break;
            case -1043345227:
                if (str.equals("GUIDANCE_VIEW_DISABLE")) {
                    b = 11;
                }
                break;
            case -960207317:
                if (str.equals("CAR_UP_2D")) {
                    b = 12;
                }
                break;
            case -960207286:
                if (str.equals("CAR_UP_3D")) {
                    b = 13;
                }
                break;
            case -812214650:
                if (str.equals("ARRIVE_DESTINATION")) {
                    b = 14;
                }
                break;
            case -606647652:
                if (str.equals("NORTH_UP_2D")) {
                    b = 15;
                }
                break;
            case -533628648:
                if (str.equals("SIMULATE_GUIDE_PAUSE")) {
                    b = 16;
                }
                break;
            case -530311292:
                if (str.equals("SIMULATE_GUIDE_START")) {
                    b = 17;
                }
                break;
            case -489873286:
                if (str.equals("TMC_OFF")) {
                    b = 18;
                }
                break;
            case -488775916:
                if (str.equals("PERMANENT_MUTE_ON")) {
                    b = 19;
                }
                break;
            case -418398199:
                if (str.equals("FULL_VIEW_ON")) {
                    b = 20;
                }
                break;
            case -318189634:
                if (str.equals("WIDGET_FRIST_FRAME_DARWN")) {
                    b = 21;
                }
                break;
            case -277681007:
                if (str.equals("APP_FINISH")) {
                    b = 22;
                }
                break;
            case -212681104:
                if (str.equals("AR_GUIDE_ON")) {
                    b = 23;
                }
                break;
            case -200255174:
                if (str.equals("MAP_2_DIM_STARTED")) {
                    b = 24;
                }
                break;
            case -187389306:
                if (str.equals("MAP_2_DIM_STOPPED")) {
                    b = 25;
                }
                break;
            case -173304929:
                if (str.equals("PSD_APP_FOREGROUND")) {
                    b = 26;
                }
                break;
            case -139491850:
                if (str.equals("GUIDANCE_VIEW_ENABLE")) {
                    b = 27;
                }
                break;
            case -85442459:
                if (str.equals("FULL_VIEW_OFF")) {
                    b = 28;
                }
                break;
            case -83913965:
                if (str.equals("REROUTING")) {
                    b = 29;
                }
                break;
            case 87977374:
                if (str.equals("START_SEARCH_PARKING")) {
                    b = 30;
                }
                break;
            case 181728207:
                if (str.equals("ENTER_TUNNEL")) {
                    b = 31;
                }
                break;
            case 433141802:
                if (str.equals("UNKNOWN")) {
                    b = 32;
                }
                break;
            case 500601409:
                if (str.equals("APP_FOREGROUND")) {
                    b = 33;
                }
                break;
            case 580181893:
                if (str.equals("GUIDE_STOP")) {
                    b = 34;
                }
                break;
            case 608001297:
                if (str.equals(ZoomMapTypeEnum.ZOOM_IN)) {
                    b = 35;
                }
                break;
            case 649274601:
                if (str.equals("ROUTE_VIEW_IN")) {
                    b = 36;
                }
                break;
            case 805011338:
                if (str.equals("PSD_APP_BACKGROUND")) {
                    b = 37;
                }
                break;
            case 805756191:
                if (str.equals("GUIDE_START")) {
                    b = 38;
                }
                break;
            case 1218165523:
                if (str.equals("ROUTE_FAILED")) {
                    b = WPCModel.cSignalId0x27;
                }
                break;
            case 1250289796:
                if (str.equals("APP_START")) {
                    b = 40;
                }
                break;
            case 1298785964:
                if (str.equals("ROUTE_START")) {
                    b = 41;
                }
                break;
            case 1478917676:
                if (str.equals("APP_BACKGROUND")) {
                    b = 42;
                }
                break;
            case 1652291113:
                if (str.equals("MUTE_OFF")) {
                    b = 43;
                }
                break;
            case 1668174552:
                if (str.equals("ZOOM_MAX")) {
                    b = 44;
                }
                break;
            case 1668174790:
                if (str.equals("ZOOM_MIN")) {
                    b = Define.cSubCodeSetADS;
                }
                break;
            case 1668177090:
                if (str.equals(ZoomMapTypeEnum.ZOOM_OUT)) {
                    b = Define.cSubCodeSetFraganceSwitch;
                }
                break;
            case 1968104208:
                if (str.equals("LEAVE_TUNNEL")) {
                    b = Define.cSubCodeSetFraganceType;
                }
                break;
            case 1996820190:
                if (str.equals("AR_GUIDE_OFF")) {
                    b = Define.cSubCodeSetFraganceLevel;
                }
                break;
        }
        switch (b) {
            case 0:
                return APP_START_FINISH;
            case 1:
                return TMC_ON;
            case 2:
                return GUIDE_VEHICLE_YAW;
            case 3:
                return TEMPORARY_MUTE_ON;
            case 4:
                return ROUTE_SUCCESS;
            case 5:
                return SIMULATE_GUIDE_STOP;
            case 6:
                return START_SEARCH_RESTAURANT;
            case 7:
                return START_SEARCH_CHARGING_STATION;
            case 8:
                return CRUISE_START;
            case 9:
                return ROUTE_VIEW_OUT;
            case 10:
                return CRUISE_STOP;
            case 11:
                return GUIDANCE_VIEW_DISABLE;
            case 12:
                return CAR_UP_2D;
            case 13:
                return CAR_UP_3D;
            case 14:
                return ARRIVE_DESTINATION;
            case 15:
                return NORTH_UP_2D;
            case 16:
                return SIMULATE_GUIDE_PAUSE;
            case 17:
                return SIMULATE_GUIDE_START;
            case 18:
                return TMC_OFF;
            case 19:
                return PERMANENT_MUTE_ON;
            case 20:
                return FULL_VIEW_ON;
            case 21:
                return WIDGET_FRIST_FRAME_DARWN;
            case 22:
                return APP_FINISH;
            case 23:
                return AR_GUIDE_ON;
            case 24:
                return MAP_2_DIM_STARTED;
            case 25:
                return MAP_2_DIM_STOPPED;
            case 26:
                return PSD_APP_FOREGROUND;
            case 27:
                return GUIDANCE_VIEW_ENABLE;
            case 28:
                return FULL_VIEW_OFF;
            case 29:
                return REROUTING;
            case 30:
                return START_SEARCH_PARKING;
            case 31:
                return ENTER_TUNNEL;
            case 32:
                return UNKNOWN;
            case 33:
                return APP_FOREGROUND;
            case 34:
                return GUIDE_STOP;
            case 35:
                return ZOOM_IN;
            case 36:
                return ROUTE_VIEW_IN;
            case 37:
                return PSD_APP_BACKGROUND;
            case 38:
                return GUIDE_START;
            case 39:
                return ROUTE_FAILED;
            case 40:
                return APP_START;
            case 41:
                return ROUTE_START;
            case 42:
                return APP_BACKGROUND;
            case 43:
                return MUTE_OFF;
            case 44:
                return ZOOM_MAX;
            case 45:
                return ZOOM_MIN;
            case 46:
                return ZOOM_OUT;
            case 47:
                return LEAVE_TUNNEL;
            case 48:
                return AR_GUIDE_OFF;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NavigationMapStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
