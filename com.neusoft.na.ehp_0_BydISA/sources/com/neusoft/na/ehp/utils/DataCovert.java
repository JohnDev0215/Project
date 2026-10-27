package com.neusoft.na.ehp.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class DataCovert {
    public static String dbVersion = "-";

    public static String covertAccident_Ahead_status_S(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Reserved" : "0" : "1" : "-";
    }

    public static String covertConstruction_Zones_status_S(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Reserved" : "0" : "1" : "-";
    }

    public static String covertCountryCode_16(int i) {
        switch (i) {
            case 0:
                return "NotUsed";
            case 14:
                return "Austria";
            case 21:
                return "Belgium";
            case 33:
                return "Bulgaria";
            case 54:
                return "Croatia";
            case 56:
                return "Cyprus";
            case 57:
                return "Czechia";
            case 58:
                return "Denmark";
            case 68:
                return "Estonia";
            case 73:
                return "Finland";
            case 74:
                return "France";
            case 82:
                return "Germany";
            case 85:
                return "Greece";
            case 98:
                return "Hungary";
            case 99:
                return "Iceland";
            case 104:
                return "Ireland";
            case 105:
                return "Israel";
            case 106:
                return "Italy";
            case 118:
                return "Latvia";
            case 123:
                return "Liechtenstein";
            case 124:
                return "Lithuania";
            case 125:
                return "Luxembourg";
            case 133:
                return "Malta";
            case 142:
                return "Monaco";
            case 151:
                return "Netherlands";
            case 161:
                return "Norway";
            case 172:
                return "Poland";
            case 173:
                return "Portugal";
            case 177:
                return "Romania";
            case 191:
                return "Slovakia";
            case 192:
                return "Slovenia";
            case 197:
                return "Spain";
            case 205:
                return "Sweden";
            case 206:
                return "Switzerland";
            case 217:
                return "Türkiye";
            case 230:
                return "Vatican";
            case 233:
                return "UK";
            default:
                return "--";
        }
    }

    public static String covertMap_SpeedLimit_Unit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Res" : "MPH" : "Km/h" : "Invalid";
    }

    public static String covertMap_Status(int i) {
        switch (i) {
            case 0:
                return "Invalid";
            case 1:
                return "Active";
            case 2:
                return "InitializationNotComplete";
            case 3:
                return "MapDataMissing";
            case 4:
                return "VehicleOffRoad";
            case 5:
                return "OtherFault";
            case 6:
            case 7:
                return "Reserved";
            default:
                return "--";
        }
    }

    public static String covertNextRamp(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Ramp connected at highSpeed and service area" : "Ramp connecting expressway and non-expressway" : "Ramp connecting expressway and expressway" : "Invalid";
    }

    public static String covertRoadtype_S(int i) {
        switch (i) {
            case 0:
                return "Invalid";
            case 1:
                return "Highways";
            case 2:
                return "Major arteria and secondary roads";
            case 3:
                return "Residential roads and extra-urban roads with low traffic volume";
            case 4:
                return "Unknown";
            case 5:
                return "Expressways";
            case 6:
                return "Roundabout";
            case 7:
                return "Reserved";
            default:
                return "--";
        }
    }

    public static String covertSpeedlimit_Rain(int i) {
        switch (i) {
            case 0:
                return "Without";
            case 1:
                return "SPL5";
            case 2:
                return "SPL10";
            case 3:
                return "SPL15";
            case 4:
                return "SPL20";
            case 5:
                return "SPL25";
            case 6:
                return "SPL30";
            case 7:
                return "SPL35";
            case 8:
                return "SPL40";
            case 9:
                return "SPL45";
            case 10:
                return "SPL50";
            case 11:
                return "SPL55";
            case 12:
                return "SPL60";
            case 13:
                return "SPL65";
            case 14:
                return "SPL70";
            case 15:
                return "SPL75";
            case 16:
                return "SPL80";
            case 17:
                return "SPL85";
            case 18:
                return "SPL90";
            case 19:
                return "SPL95";
            case 20:
                return "SPL100";
            case 21:
                return "SPL105";
            case 22:
                return "SPL110";
            case 23:
                return "SPL115";
            case 24:
                return "SPL120";
            case 25:
                return "SPL125";
            case 26:
                return "SPL130";
            case 27:
                return "SPL131p";
            case 28:
            case 29:
            case 30:
                return "Reserved";
            case 31:
                return "Invalid";
            default:
                return "--";
        }
    }

    public static String covertSpeedlimit_S(int i) {
        switch (i) {
            case 0:
                return "UnknownSPL";
            case 1:
                return "SPL5";
            case 2:
                return "SPL10";
            case 3:
                return "SPL15";
            case 4:
                return "SPL20";
            case 5:
                return "SPL25";
            case 6:
                return "SPL30";
            case 7:
                return "SPL35";
            case 8:
                return "SPL40";
            case 9:
                return "SPL45";
            case 10:
                return "SPL50";
            case 11:
                return "SPL55";
            case 12:
                return "SPL60";
            case 13:
                return "SPL65";
            case 14:
                return "SPL70";
            case 15:
                return "SPL75";
            case 16:
                return "SPL80";
            case 17:
                return "SPL85";
            case 18:
                return "SPL90";
            case 19:
                return "SPL95";
            case 20:
                return "SPL100";
            case 21:
                return "SPL105";
            case 22:
                return "SPL110";
            case 23:
                return "SPL115";
            case 24:
                return "SPL120";
            case 25:
                return "SPL125";
            case 26:
                return "SPL130";
            case 27:
                return "SPL131p";
            case 28:
            case 29:
                return "Reserved";
            case 30:
                return "Unlimited";
            case 31:
                return "Invalid";
            default:
                return "--";
        }
    }

    public static String covertSpeedlimit_Snow(int i) {
        switch (i) {
            case 0:
                return "Without";
            case 1:
                return "SPL5";
            case 2:
                return "SPL10";
            case 3:
                return "SPL15";
            case 4:
                return "SPL20";
            case 5:
                return "SPL25";
            case 6:
                return "SPL30";
            case 7:
                return "SPL35";
            case 8:
                return "SPL40";
            case 9:
                return "SPL45";
            case 10:
                return "SPL50";
            case 11:
                return "SPL55";
            case 12:
                return "SPL60";
            case 13:
                return "SPL65";
            case 14:
                return "SPL70";
            case 15:
                return "SPL75";
            case 16:
                return "SPL80";
            case 17:
                return "SPL85";
            case 18:
                return "SPL90";
            case 19:
                return "SPL95";
            case 20:
                return "SPL100";
            case 21:
                return "SPL105";
            case 22:
                return "SPL110";
            case 23:
                return "SPL115";
            case 24:
                return "SPL120";
            case 25:
                return "SPL125";
            case 26:
                return "SPL130";
            case 27:
                return "SPL131p";
            case 28:
            case 29:
            case 30:
                return "Reserved";
            case 31:
                return "Invalid";
            default:
                return "--";
        }
    }

    public static String covertSpeedlimit_Type(int i) {
        switch (i) {
            case 0:
                return "Invalid";
            case 1:
                return "Implicit";
            case 2:
                return "Explicit";
            case 3:
                return "RoadFeatures";
            case 4:
                return "LocalHarzards";
            case 5:
            case 6:
            case 7:
                return "Reserved";
            default:
                return "--";
        }
    }

    public static String covertTimeConditionSignExist(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Reserved" : "False" : "True" : "Invalid";
    }

    public static String covertTimeConditionSignUsedSpeedlimit_S(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Reserved" : "False" : "True" : "Invalid";
    }

    public static String covertTraffic_Jams_status_S(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Reserved" : "0" : "1" : "-";
    }

    public static String covertTraffic_sign_type_S(int i) {
        switch (i) {
            case 0:
                return "Invalid";
            case 1:
                return "RoundaboutSign";
            case 2:
                return "JunctionSign";
            case 3:
                return "TrafficLightSign";
            case 4:
                return "StopSign";
            case 5:
                return "YieldSign";
            case 6:
                return "Unknown";
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return "Reserved";
            default:
                return "--";
        }
    }

    public static String covertWrong_Way_Driver_status_S(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "--" : "Reserved" : "False" : "True" : "Invalid";
    }

    public static String plusAddl10(int i) {
        switch (i) {
            case 0:
                return "Unknown";
            case 1:
                return "Free";
            case 2:
                return "Heavy";
            case 3:
                return "Slow";
            case 4:
                return "Queuing";
            case 5:
                return "Stationary";
            case 6:
                return "No_Traffic";
            default:
                return i == 65535 ? "Invalid" : "--";
        }
    }

    public static String plusAddl11(int i) {
        switch (i) {
            case 0:
                return "Unknown";
            case 1:
                return "Free";
            case 2:
                return "Heavy";
            case 3:
                return "Slow";
            case 4:
                return "Queuing";
            case 5:
                return "Stationary";
            case 6:
                return "No_Traffic";
            default:
                return i == 65535 ? "Invalid" : "--";
        }
    }

    public static String plusAddl13(int i) {
        return i != 0 ? i != 1 ? i == 65535 ? "Invalid" : "--" : "Construction" : "None";
    }

    public static String plusAddl14(int i) {
        return i != 0 ? i != 1 ? i == 65535 ? "Invalid" : "--" : "Construction" : "None";
    }

    public static String plusAddl16(int i) {
        return i != 0 ? i != 1 ? i == 65535 ? "Invalid" : "--" : "Accident" : "None";
    }

    public static String plusAddl17(int i) {
        return i != 0 ? i != 1 ? i == 65535 ? "Invalid" : "--" : "Accident" : "None";
    }

    public static String covertRampOffset(int i) {
        return (i < 0 || i > 8190) ? "Invalid" : i + "m";
    }

    public static String covertCountryCode_10(int i) {
        switch (i) {
            case 0:
                return "NotUsed";
            case 14:
                return "Austria";
            case 21:
                return "Belgium";
            case 33:
                return "Bulgaria";
            case 54:
                return "Croatia";
            case 56:
                return "Cyprus";
            case 57:
                return "Czechia";
            case 58:
                return "Denmark";
            case 68:
                return "Estonia";
            case 73:
                return "Finland";
            case 74:
                return "France";
            case 82:
                return "Germany";
            case 85:
                return "Greece";
            case 98:
                return "Hungary";
            case 99:
                return "Iceland";
            case 104:
                return "Ireland";
            case 105:
                return "Israel";
            case 106:
                return "Italy";
            case 118:
                return "Latvia";
            case 123:
                return "Liechtenstein";
            case 124:
                return "Lithuania";
            case 125:
                return "Luxembourg";
            case 133:
                return "Malta";
            case 142:
                return "Monaco";
            case 151:
                return "Netherlands";
            case 161:
                return "Norway";
            case 172:
                return "Poland";
            case 173:
                return "Portugal";
            case 177:
                return "Romania";
            case 191:
                return "Slovakia";
            case 192:
                return "Slovenia";
            case 197:
                return "Spain";
            case 205:
                return "Sweden";
            case 206:
                return "Switzerland";
            case 217:
                return "Türkiye";
            case 224:
                return "UK";
            case 230:
                return "Vatican";
            case 255:
                return "Invalid";
            default:
                return ExtensionRequestData.EMPTY_VALUE + i;
        }
    }

    public static String plusAddl0(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String plusAddl1(int i) {
        return i == 65535 ? "Invalid" : i + "s";
    }

    public static String plusAddl2(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String plusAddl3(int i) {
        return i == 65535 ? "Invalid" : i + "s";
    }

    public static String plusAddl4(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String plusAddl5(int i) {
        return i == 65535 ? "Invalid" : i + "s";
    }

    public static String plusAddl6(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String plusAddl7(int i) {
        return i == 65535 ? "Invalid" : i + "s";
    }

    public static String plusAddl8(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String plusAddl9(int i) {
        return i == 65535 ? "Invalid" : i + "s";
    }

    public static String plusAddl12(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String plusAddl15(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String plusAddl18(int i) {
        return i == 65535 ? "Invalid" : i + "m";
    }

    public static String currentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public static int[] mergeArray(int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int length2 = iArr2.length;
        int[] iArrCopyOf = Arrays.copyOf(iArr, length + length2);
        System.arraycopy(iArr2, 0, iArrCopyOf, length, length2);
        return iArrCopyOf;
    }
}
