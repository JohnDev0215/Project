package api.structs.com.neusoft.types.common.weathertypes;

import com.ecarx.xui.adaptapi.wpc.specific.WPCModel;
import com.neusoft.de.nts.OneCoreTest.utils.EProjectInterfaceType;

/* JADX INFO: loaded from: classes2.dex */
public enum WeatherType {
    WT_UNKNOWN(0),
    WT_SUNNY(1),
    WT_CLOUDY(2),
    WT_OVERCAST(3),
    WT_SHOWER(4),
    WT_THUNDERSHOWER(5),
    WT_THUNDERSHOWER_WITH_HAIL(6),
    WT_SLEET(7),
    WT_LIGHT_RAIN(8),
    WT_MODERATE_RAIN(9),
    WT_HEAVY_RAIN(10),
    WT_STORM(11),
    WT_HEAVY_STORM(12),
    WT_SEVERE_STORM(13),
    WT_ICE_RAIN(14),
    WT_LIGHT_TO_MODERATE_RAIN(15),
    WT_MODERATE_TO_HEAVY_RAIN(16),
    WT_HEAVY_RAIN_TO_STORM(17),
    WT_STORM_TO_HEAVY_STORM(18),
    WT_HEAVY_TO_SEVERE_STORM(19),
    WT_SNOW_FLURRY(20),
    WT_LIGHT_SNOW(21),
    WT_MODERATE_SNOW(22),
    WT_HEAVY_SNOW(23),
    WT_SNOWSTORM(24),
    WT_LIGHT_TO_MODERATE_SNOW(25),
    WT_MODERATE_TO_HEAVY_SNOW(26),
    WT_HEAVY_SNOW_TO_SNOWSTORM(27),
    WT_FOGGY(28),
    WT_DENSE_FOG(29),
    WT_HEAVY_DENSE_FOG(30),
    WT_HEAVY_FOG(31),
    WT_EXTRA_HEAVY_DENSE_FOG(32),
    WT_DUST(33),
    WT_SAND(34),
    WT_SANDSTORM(35),
    WT_HAZE(36),
    WT_MODERATE_HAZE(37),
    WT_SEVERE_HAZE(38),
    WT_HAZARDOUS_HAZE(39);

    private static final EProjectInterfaceType projectType = EProjectInterfaceType.OneCore;
    private final Integer value;

    WeatherType(Integer num) {
        this.value = num;
    }

    public Integer getValue() {
        return this.value;
    }

    public static WeatherType getByValue(Integer num) {
        switch (num.intValue()) {
            case 0:
                return WT_UNKNOWN;
            case 1:
                return WT_SUNNY;
            case 2:
                return WT_CLOUDY;
            case 3:
                return WT_OVERCAST;
            case 4:
                return WT_SHOWER;
            case 5:
                return WT_THUNDERSHOWER;
            case 6:
                return WT_THUNDERSHOWER_WITH_HAIL;
            case 7:
                return WT_SLEET;
            case 8:
                return WT_LIGHT_RAIN;
            case 9:
                return WT_MODERATE_RAIN;
            case 10:
                return WT_HEAVY_RAIN;
            case 11:
                return WT_STORM;
            case 12:
                return WT_HEAVY_STORM;
            case 13:
                return WT_SEVERE_STORM;
            case 14:
                return WT_ICE_RAIN;
            case 15:
                return WT_LIGHT_TO_MODERATE_RAIN;
            case 16:
                return WT_MODERATE_TO_HEAVY_RAIN;
            case 17:
                return WT_HEAVY_RAIN_TO_STORM;
            case 18:
                return WT_STORM_TO_HEAVY_STORM;
            case 19:
                return WT_HEAVY_TO_SEVERE_STORM;
            case 20:
                return WT_SNOW_FLURRY;
            case 21:
                return WT_LIGHT_SNOW;
            case 22:
                return WT_MODERATE_SNOW;
            case 23:
                return WT_HEAVY_SNOW;
            case 24:
                return WT_SNOWSTORM;
            case 25:
                return WT_LIGHT_TO_MODERATE_SNOW;
            case 26:
                return WT_MODERATE_TO_HEAVY_SNOW;
            case 27:
                return WT_HEAVY_SNOW_TO_SNOWSTORM;
            case 28:
                return WT_FOGGY;
            case 29:
                return WT_DENSE_FOG;
            case 30:
                return WT_HEAVY_DENSE_FOG;
            case 31:
                return WT_HEAVY_FOG;
            case 32:
                return WT_EXTRA_HEAVY_DENSE_FOG;
            case 33:
                return WT_DUST;
            case 34:
                return WT_SAND;
            case 35:
                return WT_SANDSTORM;
            case 36:
                return WT_HAZE;
            case 37:
                return WT_MODERATE_HAZE;
            case 38:
                return WT_SEVERE_HAZE;
            case 39:
                return WT_HAZARDOUS_HAZE;
            default:
                throw new IllegalArgumentException("invalid value '" + num + "' for enum " + WeatherType.class.getName());
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static WeatherType valueOfSerializedName(String str) {
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1896865068:
                if (str.equals("WT_DUST")) {
                    b = 0;
                }
                break;
            case -1896764922:
                if (str.equals("WT_HAZE")) {
                    b = 1;
                }
                break;
            case -1896437594:
                if (str.equals("WT_SAND")) {
                    b = 2;
                }
                break;
            case -1858253690:
                if (str.equals("WT_CLOUDY")) {
                    b = 3;
                }
                break;
            case -1403879412:
                if (str.equals("WT_SHOWER")) {
                    b = 4;
                }
                break;
            case -1177699318:
                if (str.equals("WT_ICE_RAIN")) {
                    b = 5;
                }
                break;
            case -1062271164:
                if (str.equals("WT_SNOW_FLURRY")) {
                    b = 6;
                }
                break;
            case -1053264732:
                if (str.equals("WT_HEAVY_SNOW_TO_SNOWSTORM")) {
                    b = 7;
                }
                break;
            case -1035728796:
                if (str.equals("WT_HEAVY_DENSE_FOG")) {
                    b = 8;
                }
                break;
            case -995006908:
                if (str.equals("WT_SNOWSTORM")) {
                    b = 9;
                }
                break;
            case -656186844:
                if (str.equals("WT_HEAVY_FOG")) {
                    b = 10;
                }
                break;
            case -403884065:
                if (str.equals("WT_LIGHT_RAIN")) {
                    b = 11;
                }
                break;
            case -403841586:
                if (str.equals("WT_LIGHT_SNOW")) {
                    b = 12;
                }
                break;
            case -138084777:
                if (str.equals("WT_LIGHT_TO_MODERATE_RAIN")) {
                    b = 13;
                }
                break;
            case -138042298:
                if (str.equals("WT_LIGHT_TO_MODERATE_SNOW")) {
                    b = 14;
                }
                break;
            case -11198112:
                if (str.equals("WT_SEVERE_STORM")) {
                    b = 15;
                }
                break;
            case 359476533:
                if (str.equals("WT_OVERCAST")) {
                    b = 16;
                }
                break;
            case 645868620:
                if (str.equals("WT_THUNDERSHOWER")) {
                    b = 17;
                }
                break;
            case 686804963:
                if (str.equals("WT_SANDSTORM")) {
                    b = 18;
                }
                break;
            case 744703422:
                if (str.equals("WT_MODERATE_HAZE")) {
                    b = 19;
                }
                break;
            case 745000814:
                if (str.equals("WT_MODERATE_RAIN")) {
                    b = 20;
                }
                break;
            case 745043293:
                if (str.equals("WT_MODERATE_SNOW")) {
                    b = 21;
                }
                break;
            case 767465925:
                if (str.equals("WT_STORM_TO_HEAVY_STORM")) {
                    b = 22;
                }
                break;
            case 776800463:
                if (str.equals("WT_HEAVY_STORM")) {
                    b = 23;
                }
                break;
            case 826951804:
                if (str.equals("WT_DENSE_FOG")) {
                    b = 24;
                }
                break;
            case 835681130:
                if (str.equals("WT_HAZARDOUS_HAZE")) {
                    b = 25;
                }
                break;
            case 969124461:
                if (str.equals("WT_SEVERE_HAZE")) {
                    b = 26;
                }
                break;
            case 1133388494:
                if (str.equals("WT_HEAVY_RAIN")) {
                    b = 27;
                }
                break;
            case 1133430973:
                if (str.equals("WT_HEAVY_SNOW")) {
                    b = 28;
                }
                break;
            case 1164049878:
                if (str.equals("WT_MODERATE_TO_HEAVY_RAIN")) {
                    b = 29;
                }
                break;
            case 1164092357:
                if (str.equals("WT_MODERATE_TO_HEAVY_SNOW")) {
                    b = 30;
                }
                break;
            case 1165671510:
                if (str.equals("WT_HEAVY_RAIN_TO_STORM")) {
                    b = 31;
                }
                break;
            case 1328381486:
                if (str.equals("WT_FOGGY")) {
                    b = 32;
                }
                break;
            case 1340295897:
                if (str.equals("WT_SLEET")) {
                    b = 33;
                }
                break;
            case 1340544231:
                if (str.equals("WT_STORM")) {
                    b = 34;
                }
                break;
            case 1340572949:
                if (str.equals("WT_SUNNY")) {
                    b = 35;
                }
                break;
            case 1372241096:
                if (str.equals("WT_UNKNOWN")) {
                    b = 36;
                }
                break;
            case 1476471701:
                if (str.equals("WT_EXTRA_HEAVY_DENSE_FOG")) {
                    b = 37;
                }
                break;
            case 1541049218:
                if (str.equals("WT_THUNDERSHOWER_WITH_HAIL")) {
                    b = 38;
                }
                break;
            case 1811163912:
                if (str.equals("WT_HEAVY_TO_SEVERE_STORM")) {
                    b = WPCModel.cSignalId0x27;
                }
                break;
        }
        switch (b) {
            case 0:
                return WT_DUST;
            case 1:
                return WT_HAZE;
            case 2:
                return WT_SAND;
            case 3:
                return WT_CLOUDY;
            case 4:
                return WT_SHOWER;
            case 5:
                return WT_ICE_RAIN;
            case 6:
                return WT_SNOW_FLURRY;
            case 7:
                return WT_HEAVY_SNOW_TO_SNOWSTORM;
            case 8:
                return WT_HEAVY_DENSE_FOG;
            case 9:
                return WT_SNOWSTORM;
            case 10:
                return WT_HEAVY_FOG;
            case 11:
                return WT_LIGHT_RAIN;
            case 12:
                return WT_LIGHT_SNOW;
            case 13:
                return WT_LIGHT_TO_MODERATE_RAIN;
            case 14:
                return WT_LIGHT_TO_MODERATE_SNOW;
            case 15:
                return WT_SEVERE_STORM;
            case 16:
                return WT_OVERCAST;
            case 17:
                return WT_THUNDERSHOWER;
            case 18:
                return WT_SANDSTORM;
            case 19:
                return WT_MODERATE_HAZE;
            case 20:
                return WT_MODERATE_RAIN;
            case 21:
                return WT_MODERATE_SNOW;
            case 22:
                return WT_STORM_TO_HEAVY_STORM;
            case 23:
                return WT_HEAVY_STORM;
            case 24:
                return WT_DENSE_FOG;
            case 25:
                return WT_HAZARDOUS_HAZE;
            case 26:
                return WT_SEVERE_HAZE;
            case 27:
                return WT_HEAVY_RAIN;
            case 28:
                return WT_HEAVY_SNOW;
            case 29:
                return WT_MODERATE_TO_HEAVY_RAIN;
            case 30:
                return WT_MODERATE_TO_HEAVY_SNOW;
            case 31:
                return WT_HEAVY_RAIN_TO_STORM;
            case 32:
                return WT_FOGGY;
            case 33:
                return WT_SLEET;
            case 34:
                return WT_STORM;
            case 35:
                return WT_SUNNY;
            case 36:
                return WT_UNKNOWN;
            case 37:
                return WT_EXTRA_HEAVY_DENSE_FOG;
            case 38:
                return WT_THUNDERSHOWER_WITH_HAIL;
            case 39:
                return WT_HEAVY_TO_SEVERE_STORM;
            default:
                throw new IllegalArgumentException("invalid serializedName '" + str + "' for enum " + WeatherType.class.getName());
        }
    }

    @Override // java.lang.Enum
    public String toString() {
        return projectType.getGson().toJson(this);
    }
}
