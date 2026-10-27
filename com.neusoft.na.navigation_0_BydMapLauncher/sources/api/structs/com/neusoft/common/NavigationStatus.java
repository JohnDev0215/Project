package api.structs.com.neusoft.common;

import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum NavigationStatus {
    NAVI_STAT_OVERVIEW_OUT(1),
    NAVI_STAT_OVERVIEW_IN(2),
    NAVI_STAT_APP_START_FINISH(8),
    NAVI_STAT_APP_FINISH(9),
    NAVI_STAT_APP_FOREGROUND(10),
    NAVI_STAT_APP_BACKGROUND(11),
    NAVI_STAT_GUIDE_START(16),
    NAVI_STAT_GUIDE_STOP(17),
    NAVI_STAT_ZOOM_IN(18),
    NAVI_STAT_ZOOM_OUT(19),
    NAVI_STAT_ZOOM_STATUS_MAX(21),
    NAVI_STAT_ZOOM_STATUS_MIN(22),
    NAVI_STAT_TRAFFIC_STATUS_ON(23),
    NAVI_STAT_TRAFFIC_STATUS_OFF(24),
    NAVI_STAT_VIEW_MODE_3D_FRONT_UP(25),
    NAVI_STAT_VIEW_MODE_2D_NORTHWARD(26),
    NAVI_STAT_VIEW_MODE_2D_FRONT_UP(27),
    NAVI_STAT_DAYNIGHT_STATUS_DAY(30),
    NAVI_STAT_DAYNIGHT_STATUS_NIGHT(32),
    NAVI_STAT_SETTING_OPEN(44),
    NAVI_STAT_SETTING_CLOSE(45),
    NAVI_STAT_FAVORITE_OPEN(46),
    NAVI_STAT_FAVORITE_CLOSE(47),
    NAVI_STAT_OPEN_HISTORYDESTINATION(49),
    NAVI_STAT_OPEN_ROUTEOPTION(50),
    NAVI_STAT_POIDETAIL_ROUTING(55),
    NAVI_STAT_POIDETAIL_CLOSE(57),
    NAVI_STAT_CLOSE_HISTORYDESTINATION(100),
    NAVI_STAT_MUTE_ON(101),
    NAVI_STAT_MUTE_OFF(102),
    UNKNOWN(Integer.valueOf(BYDAutoStatisticDevice.MILEAGE_MAX));

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    NavigationStatus(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static NavigationStatus getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue == 1) {
            return NAVI_STAT_OVERVIEW_OUT;
        }
        if (iIntValue == 2) {
            return NAVI_STAT_OVERVIEW_IN;
        }
        if (iIntValue == 30) {
            return NAVI_STAT_DAYNIGHT_STATUS_DAY;
        }
        if (iIntValue == 32) {
            return NAVI_STAT_DAYNIGHT_STATUS_NIGHT;
        }
        if (iIntValue == 55) {
            return NAVI_STAT_POIDETAIL_ROUTING;
        }
        if (iIntValue == 57) {
            return NAVI_STAT_POIDETAIL_CLOSE;
        }
        if (iIntValue == 99999) {
            return UNKNOWN;
        }
        if (iIntValue == 49) {
            return NAVI_STAT_OPEN_HISTORYDESTINATION;
        }
        if (iIntValue != 50) {
            switch (iIntValue) {
                case 8:
                    return NAVI_STAT_APP_START_FINISH;
                case 9:
                    return NAVI_STAT_APP_FINISH;
                case 10:
                    return NAVI_STAT_APP_FOREGROUND;
                case 11:
                    return NAVI_STAT_APP_BACKGROUND;
                default:
                    switch (iIntValue) {
                        case 16:
                            return NAVI_STAT_GUIDE_START;
                        case 17:
                            return NAVI_STAT_GUIDE_STOP;
                        case 18:
                            return NAVI_STAT_ZOOM_IN;
                        case 19:
                            return NAVI_STAT_ZOOM_OUT;
                        default:
                            switch (iIntValue) {
                                case 21:
                                    return NAVI_STAT_ZOOM_STATUS_MAX;
                                case 22:
                                    return NAVI_STAT_ZOOM_STATUS_MIN;
                                case 23:
                                    return NAVI_STAT_TRAFFIC_STATUS_ON;
                                case 24:
                                    return NAVI_STAT_TRAFFIC_STATUS_OFF;
                                case 25:
                                    return NAVI_STAT_VIEW_MODE_3D_FRONT_UP;
                                case 26:
                                    return NAVI_STAT_VIEW_MODE_2D_NORTHWARD;
                                case 27:
                                    return NAVI_STAT_VIEW_MODE_2D_FRONT_UP;
                                default:
                                    switch (iIntValue) {
                                        case 44:
                                            return NAVI_STAT_SETTING_OPEN;
                                        case 45:
                                            return NAVI_STAT_SETTING_CLOSE;
                                        case 46:
                                            return NAVI_STAT_FAVORITE_OPEN;
                                        case 47:
                                            return NAVI_STAT_FAVORITE_CLOSE;
                                        default:
                                            switch (iIntValue) {
                                                case 100:
                                                    return NAVI_STAT_CLOSE_HISTORYDESTINATION;
                                                case 101:
                                                    return NAVI_STAT_MUTE_ON;
                                                case 102:
                                                    return NAVI_STAT_MUTE_OFF;
                                                default:
                                                    throw new IllegalArgumentException("invalid value '" + num + "' for enum " + NavigationStatus.class.getName());
                                            }
                                    }
                            }
                    }
            }
        }
        return NAVI_STAT_OPEN_ROUTEOPTION;
    }

    public static NavigationStatus valueOfSerializedName(String str) {
        str.hashCode();
        switch (str) {
            case "NAVI_STAT_APP_FOREGROUND":
                return NAVI_STAT_APP_FOREGROUND;
            case "NAVI_STAT_ZOOM_STATUS_MAX":
                return NAVI_STAT_ZOOM_STATUS_MAX;
            case "NAVI_STAT_ZOOM_STATUS_MIN":
                return NAVI_STAT_ZOOM_STATUS_MIN;
            case "NAVI_STAT_VIEW_MODE_2D_FRONT_UP":
                return NAVI_STAT_VIEW_MODE_2D_FRONT_UP;
            case "NAVI_STAT_POIDETAIL_CLOSE":
                return NAVI_STAT_POIDETAIL_CLOSE;
            case "NAVI_STAT_FAVORITE_OPEN":
                return NAVI_STAT_FAVORITE_OPEN;
            case "NAVI_STAT_ZOOM_IN":
                return NAVI_STAT_ZOOM_IN;
            case "NAVI_STAT_APP_BACKGROUND":
                return NAVI_STAT_APP_BACKGROUND;
            case "NAVI_STAT_FAVORITE_CLOSE":
                return NAVI_STAT_FAVORITE_CLOSE;
            case "NAVI_STAT_OPEN_HISTORYDESTINATION":
                return NAVI_STAT_OPEN_HISTORYDESTINATION;
            case "NAVI_STAT_VIEW_MODE_2D_NORTHWARD":
                return NAVI_STAT_VIEW_MODE_2D_NORTHWARD;
            case "NAVI_STAT_SETTING_CLOSE":
                return NAVI_STAT_SETTING_CLOSE;
            case "NAVI_STAT_MUTE_OFF":
                return NAVI_STAT_MUTE_OFF;
            case "NAVI_STAT_ZOOM_OUT":
                return NAVI_STAT_ZOOM_OUT;
            case "NAVI_STAT_SETTING_OPEN":
                return NAVI_STAT_SETTING_OPEN;
            case "NAVI_STAT_TRAFFIC_STATUS_OFF":
                return NAVI_STAT_TRAFFIC_STATUS_OFF;
            case "NAVI_STAT_OPEN_ROUTEOPTION":
                return NAVI_STAT_OPEN_ROUTEOPTION;
            case "NAVI_STAT_DAYNIGHT_STATUS_NIGHT":
                return NAVI_STAT_DAYNIGHT_STATUS_NIGHT;
            case "NAVI_STAT_GUIDE_START":
                return NAVI_STAT_GUIDE_START;
            case "NAVI_STAT_MUTE_ON":
                return NAVI_STAT_MUTE_ON;
            case "NAVI_STAT_APP_FINISH":
                return NAVI_STAT_APP_FINISH;
            case "UNKNOWN":
                return UNKNOWN;
            case "NAVI_STAT_APP_START_FINISH":
                return NAVI_STAT_APP_START_FINISH;
            case "NAVI_STAT_VIEW_MODE_3D_FRONT_UP":
                return NAVI_STAT_VIEW_MODE_3D_FRONT_UP;
            case "NAVI_STAT_TRAFFIC_STATUS_ON":
                return NAVI_STAT_TRAFFIC_STATUS_ON;
            case "NAVI_STAT_GUIDE_STOP":
                return NAVI_STAT_GUIDE_STOP;
            case "NAVI_STAT_OVERVIEW_IN":
                return NAVI_STAT_OVERVIEW_IN;
            case "NAVI_STAT_CLOSE_HISTORYDESTINATION":
                return NAVI_STAT_CLOSE_HISTORYDESTINATION;
            case "NAVI_STAT_OVERVIEW_OUT":
                return NAVI_STAT_OVERVIEW_OUT;
            case "NAVI_STAT_DAYNIGHT_STATUS_DAY":
                return NAVI_STAT_DAYNIGHT_STATUS_DAY;
            case "NAVI_STAT_POIDETAIL_ROUTING":
                return NAVI_STAT_POIDETAIL_ROUTING;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + NavigationStatus.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
