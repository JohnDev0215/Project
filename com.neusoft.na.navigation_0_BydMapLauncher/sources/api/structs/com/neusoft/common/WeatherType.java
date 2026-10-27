package api.structs.com.neusoft.common;

import com.ecarx.xui.adaptapi.hvac.specific.Define;
import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum WeatherType {
    WT_SUNNY(0),
    WT_CLOUDY(1),
    WT_FEW_CLOUDS(2),
    WT_PARTLY_CLOUDY(3),
    WT_OVERCAST(4),
    WT_WINDY(5),
    WT_CALM(6),
    WT_LIGHT_BREEZE(7),
    WT_MODERATE_BREEZE(8),
    WT_FRESH_BREEZE(9),
    WT_STRONG_BREEZE(10),
    WT_HIGH_WIND(11),
    WT_GALE(12),
    WT_STRONG_GALE(13),
    WT_STORM(14),
    WT_VIOLENT_STORM(15),
    WT_HURRICANE(16),
    WT_TORNADO(17),
    WT_TROPICAL_STORM(18),
    WT_SHOWER_RAIN(19),
    WT_HEAVY_SHOWER_RAIN(20),
    WT_THUNDERSHOWER(21),
    WT_HEAVY_THUNDERSTORM(22),
    WT_THUNDERSHOWER_WITH_HAIL(23),
    WT_LIGHT_RAIN(24),
    WT_MODERATE_RAIN(25),
    WT_HEAVY_RAIN(26),
    WT_EXTREME_RAIN(27),
    WT_DRIZZLE_RAIN(28),
    WT_STORM_RAIN(29),
    WT_HEAVY_STORM(30),
    WT_SEVERE_STORM(31),
    WT_FREEZING_RAIN(32),
    WT_LIGHT_SNOW(33),
    WT_MODERATE_SNOW(34),
    WT_HEAVY_SNOW(35),
    WT_SNOWSTORM(36),
    WT_SLEET(37),
    WT_RAIN_AND_SNOW(38),
    WT_SHOWER_SNOW(39),
    WT_SNOW_FLURRY(40),
    WT_MIST(41),
    WT_FOGGY(42),
    WT_HAZE(43),
    WT_SAND(44),
    WT_DUST(45),
    WT_DUST_STORM(46),
    WT_SANDSTORM(47),
    WT_HOT(48),
    WT_COLD(49),
    WT_ICE_PARTICLES(50),
    WT_ICE_NEEDLE(51),
    WT_HAIL(52),
    WT_THUNDERSTORMS(53),
    WT_THUNDER_LIGHTNING(54),
    WT_UNKNOWN(127);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    WeatherType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static WeatherType getByValue(Integer num) {
        int iIntValue = num.intValue();
        if (iIntValue != 127) {
            switch (iIntValue) {
                case 0:
                    return WT_SUNNY;
                case 1:
                    return WT_CLOUDY;
                case 2:
                    return WT_FEW_CLOUDS;
                case 3:
                    return WT_PARTLY_CLOUDY;
                case 4:
                    return WT_OVERCAST;
                case 5:
                    return WT_WINDY;
                case 6:
                    return WT_CALM;
                case 7:
                    return WT_LIGHT_BREEZE;
                case 8:
                    return WT_MODERATE_BREEZE;
                case 9:
                    return WT_FRESH_BREEZE;
                case 10:
                    return WT_STRONG_BREEZE;
                case 11:
                    return WT_HIGH_WIND;
                case 12:
                    return WT_GALE;
                case 13:
                    return WT_STRONG_GALE;
                case 14:
                    return WT_STORM;
                case 15:
                    return WT_VIOLENT_STORM;
                case 16:
                    return WT_HURRICANE;
                case 17:
                    return WT_TORNADO;
                case 18:
                    return WT_TROPICAL_STORM;
                case 19:
                    return WT_SHOWER_RAIN;
                case 20:
                    return WT_HEAVY_SHOWER_RAIN;
                case 21:
                    return WT_THUNDERSHOWER;
                case 22:
                    return WT_HEAVY_THUNDERSTORM;
                case 23:
                    return WT_THUNDERSHOWER_WITH_HAIL;
                case 24:
                    return WT_LIGHT_RAIN;
                case 25:
                    return WT_MODERATE_RAIN;
                case 26:
                    return WT_HEAVY_RAIN;
                case 27:
                    return WT_EXTREME_RAIN;
                case 28:
                    return WT_DRIZZLE_RAIN;
                case 29:
                    return WT_STORM_RAIN;
                case 30:
                    return WT_HEAVY_STORM;
                case 31:
                    return WT_SEVERE_STORM;
                case 32:
                    return WT_FREEZING_RAIN;
                case 33:
                    return WT_LIGHT_SNOW;
                case 34:
                    return WT_MODERATE_SNOW;
                case 35:
                    return WT_HEAVY_SNOW;
                case 36:
                    return WT_SNOWSTORM;
                case 37:
                    return WT_SLEET;
                case 38:
                    return WT_RAIN_AND_SNOW;
                case 39:
                    return WT_SHOWER_SNOW;
                case 40:
                    return WT_SNOW_FLURRY;
                case 41:
                    return WT_MIST;
                case 42:
                    return WT_FOGGY;
                case 43:
                    return WT_HAZE;
                case 44:
                    return WT_SAND;
                case 45:
                    return WT_DUST;
                case 46:
                    return WT_DUST_STORM;
                case 47:
                    return WT_SANDSTORM;
                case 48:
                    return WT_HOT;
                case 49:
                    return WT_COLD;
                case 50:
                    return WT_ICE_PARTICLES;
                case 51:
                    return WT_ICE_NEEDLE;
                case 52:
                    return WT_HAIL;
                case 53:
                    return WT_THUNDERSTORMS;
                case 54:
                    return WT_THUNDER_LIGHTNING;
                default:
                    throw new IllegalArgumentException("invalid value '" + num + "' for enum " + WeatherType.class.getName());
            }
        }
        return WT_UNKNOWN;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static WeatherType valueOfSerializedName(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -2047286431:
                if (str.equals("WT_PARTLY_CLOUDY")) {
                    b = 0;
                }
                break;
            case -2028021338:
                if (str.equals("WT_LIGHT_BREEZE")) {
                    b = 1;
                }
                break;
            case -1896914303:
                if (str.equals("WT_CALM")) {
                    b = 2;
                }
                break;
            case -1896900858:
                if (str.equals("WT_COLD")) {
                    b = 3;
                }
                break;
            case -1896865068:
                if (str.equals("WT_DUST")) {
                    b = 4;
                }
                break;
            case -1896795147:
                if (str.equals("WT_GALE")) {
                    b = 5;
                }
                break;
            case -1896765442:
                if (str.equals("WT_HAIL")) {
                    b = 6;
                }
                break;
            case -1896764922:
                if (str.equals("WT_HAZE")) {
                    b = 7;
                }
                break;
            case -1896608481:
                if (str.equals("WT_MIST")) {
                    b = 8;
                }
                break;
            case -1896437594:
                if (str.equals("WT_SAND")) {
                    b = 9;
                }
                break;
            case -1858253690:
                if (str.equals("WT_CLOUDY")) {
                    b = 10;
                }
                break;
            case -1756247691:
                if (str.equals("WT_MODERATE_BREEZE")) {
                    b = 11;
                }
                break;
            case -1723753525:
                if (str.equals("WT_HOT")) {
                    b = 12;
                }
                break;
            case -1640327773:
                if (str.equals("WT_HIGH_WIND")) {
                    b = 13;
                }
                break;
            case -1538451554:
                if (str.equals("WT_DUST_STORM")) {
                    b = 14;
                }
                break;
            case -1122458649:
                if (str.equals("WT_SHOWER_RAIN")) {
                    b = 15;
                }
                break;
            case -1122416170:
                if (str.equals("WT_SHOWER_SNOW")) {
                    b = 16;
                }
                break;
            case -1062271164:
                if (str.equals("WT_SNOW_FLURRY")) {
                    b = 17;
                }
                break;
            case -995006908:
                if (str.equals("WT_SNOWSTORM")) {
                    b = 18;
                }
                break;
            case -763463793:
                if (str.equals("WT_HURRICANE")) {
                    b = 19;
                }
                break;
            case -403884065:
                if (str.equals("WT_LIGHT_RAIN")) {
                    b = 20;
                }
                break;
            case -403841586:
                if (str.equals("WT_LIGHT_SNOW")) {
                    b = 21;
                }
                break;
            case -157152817:
                if (str.equals("WT_HEAVY_SHOWER_RAIN")) {
                    b = 22;
                }
                break;
            case -141513503:
                if (str.equals("WT_STRONG_BREEZE")) {
                    b = 23;
                }
                break;
            case -100830851:
                if (str.equals("WT_FREEZING_RAIN")) {
                    b = 24;
                }
                break;
            case -36322777:
                if (str.equals("WT_FEW_CLOUDS")) {
                    b = 25;
                }
                break;
            case -11198112:
                if (str.equals("WT_SEVERE_STORM")) {
                    b = 26;
                }
                break;
            case 6869975:
                if (str.equals("WT_ICE_PARTICLES")) {
                    b = 27;
                }
                break;
            case 359476533:
                if (str.equals("WT_OVERCAST")) {
                    b = 28;
                }
                break;
            case 519817171:
                if (str.equals("WT_TORNADO")) {
                    b = 29;
                }
                break;
            case 645868620:
                if (str.equals("WT_THUNDERSHOWER")) {
                    b = 30;
                }
                break;
            case 656946316:
                if (str.equals("WT_THUNDERSTORMS")) {
                    b = 31;
                }
                break;
            case 686804963:
                if (str.equals("WT_SANDSTORM")) {
                    b = 32;
                }
                break;
            case 745000814:
                if (str.equals("WT_MODERATE_RAIN")) {
                    b = 33;
                }
                break;
            case 745043293:
                if (str.equals("WT_MODERATE_SNOW")) {
                    b = 34;
                }
                break;
            case 751766158:
                if (str.equals("WT_FRESH_BREEZE")) {
                    b = 35;
                }
                break;
            case 776800463:
                if (str.equals("WT_HEAVY_STORM")) {
                    b = 36;
                }
                break;
            case 816605556:
                if (str.equals("WT_RAIN_AND_SNOW")) {
                    b = 37;
                }
                break;
            case 955637740:
                if (str.equals("WT_STORM_RAIN")) {
                    b = 38;
                }
                break;
            case 989280191:
                if (str.equals("WT_HEAVY_THUNDERSTORM")) {
                    b = WPCModel.cSignalId0x27;
                }
                break;
            case 1133388494:
                if (str.equals("WT_HEAVY_RAIN")) {
                    b = 40;
                }
                break;
            case 1133430973:
                if (str.equals("WT_HEAVY_SNOW")) {
                    b = 41;
                }
                break;
            case 1265612813:
                if (str.equals("WT_THUNDER_LIGHTNING")) {
                    b = 42;
                }
                break;
            case 1328381486:
                if (str.equals("WT_FOGGY")) {
                    b = 43;
                }
                break;
            case 1340295897:
                if (str.equals("WT_SLEET")) {
                    b = 44;
                }
                break;
            case 1340544231:
                if (str.equals("WT_STORM")) {
                    b = Define.cSubCodeSetADS;
                }
                break;
            case 1340572949:
                if (str.equals("WT_SUNNY")) {
                    b = Define.cSubCodeSetFraganceSwitch;
                }
                break;
            case 1343909231:
                if (str.equals("WT_WINDY")) {
                    b = Define.cSubCodeSetFraganceType;
                }
                break;
            case 1372241096:
                if (str.equals("WT_UNKNOWN")) {
                    b = Define.cSubCodeSetFraganceLevel;
                }
                break;
            case 1541049218:
                if (str.equals("WT_THUNDERSHOWER_WITH_HAIL")) {
                    b = Define.cSubCodeSetAcComfortLevel;
                }
                break;
            case 1599772835:
                if (str.equals("WT_VIOLENT_STORM")) {
                    b = 50;
                }
                break;
            case 1648459408:
                if (str.equals("WT_TROPICAL_STORM")) {
                    b = Define.cSubCodeSetRightWindSeat;
                }
                break;
            case 1661657697:
                if (str.equals("WT_DRIZZLE_RAIN")) {
                    b = 52;
                }
                break;
            case 1955244425:
                if (str.equals("WT_EXTREME_RAIN")) {
                    b = 53;
                }
                break;
            case 1991372677:
                if (str.equals("WT_ICE_NEEDLE")) {
                    b = Define.cSubCodeSetAcSync;
                }
                break;
            case 2006687257:
                if (str.equals("WT_STRONG_GALE")) {
                    b = Define.cSubCodeSetPowerOn;
                }
                break;
        }
        switch (b) {
            case 0:
                return WT_PARTLY_CLOUDY;
            case 1:
                return WT_LIGHT_BREEZE;
            case 2:
                return WT_CALM;
            case 3:
                return WT_COLD;
            case 4:
                return WT_DUST;
            case 5:
                return WT_GALE;
            case 6:
                return WT_HAIL;
            case 7:
                return WT_HAZE;
            case 8:
                return WT_MIST;
            case 9:
                return WT_SAND;
            case 10:
                return WT_CLOUDY;
            case 11:
                return WT_MODERATE_BREEZE;
            case 12:
                return WT_HOT;
            case 13:
                return WT_HIGH_WIND;
            case 14:
                return WT_DUST_STORM;
            case 15:
                return WT_SHOWER_RAIN;
            case 16:
                return WT_SHOWER_SNOW;
            case 17:
                return WT_SNOW_FLURRY;
            case 18:
                return WT_SNOWSTORM;
            case 19:
                return WT_HURRICANE;
            case 20:
                return WT_LIGHT_RAIN;
            case 21:
                return WT_LIGHT_SNOW;
            case 22:
                return WT_HEAVY_SHOWER_RAIN;
            case 23:
                return WT_STRONG_BREEZE;
            case 24:
                return WT_FREEZING_RAIN;
            case 25:
                return WT_FEW_CLOUDS;
            case 26:
                return WT_SEVERE_STORM;
            case 27:
                return WT_ICE_PARTICLES;
            case 28:
                return WT_OVERCAST;
            case 29:
                return WT_TORNADO;
            case 30:
                return WT_THUNDERSHOWER;
            case 31:
                return WT_THUNDERSTORMS;
            case 32:
                return WT_SANDSTORM;
            case 33:
                return WT_MODERATE_RAIN;
            case 34:
                return WT_MODERATE_SNOW;
            case 35:
                return WT_FRESH_BREEZE;
            case 36:
                return WT_HEAVY_STORM;
            case 37:
                return WT_RAIN_AND_SNOW;
            case 38:
                return WT_STORM_RAIN;
            case 39:
                return WT_HEAVY_THUNDERSTORM;
            case 40:
                return WT_HEAVY_RAIN;
            case 41:
                return WT_HEAVY_SNOW;
            case 42:
                return WT_THUNDER_LIGHTNING;
            case 43:
                return WT_FOGGY;
            case 44:
                return WT_SLEET;
            case 45:
                return WT_STORM;
            case 46:
                return WT_SUNNY;
            case 47:
                return WT_WINDY;
            case 48:
                return WT_UNKNOWN;
            case 49:
                return WT_THUNDERSHOWER_WITH_HAIL;
            case 50:
                return WT_VIOLENT_STORM;
            case 51:
                return WT_TROPICAL_STORM;
            case 52:
                return WT_DRIZZLE_RAIN;
            case 53:
                return WT_EXTREME_RAIN;
            case 54:
                return WT_ICE_NEEDLE;
            case 55:
                return WT_STRONG_GALE;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + WeatherType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
