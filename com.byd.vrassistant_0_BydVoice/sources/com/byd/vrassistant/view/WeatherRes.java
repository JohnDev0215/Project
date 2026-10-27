package com.byd.vrassistant.view;

import android.content.Context;
import com.byd.vrassistant.ui.R;
import com.google.common.base.Ascii;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WeatherRes {
    List<Integer> jxList = Arrays.asList(Integer.valueOf(R.string.weather_Mon), Integer.valueOf(R.string.weather_Tue), Integer.valueOf(R.string.weather_Wed), Integer.valueOf(R.string.weather_Thu), Integer.valueOf(R.string.weather_Fri), Integer.valueOf(R.string.weather_Sat), Integer.valueOf(R.string.weather_Sun));

    public String getJXweek(Context context, int i) {
        return context.getString(this.jxList.get(i - 1).intValue());
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public int get120ResId(String str) {
        byte b;
        str.hashCode();
        switch (str.hashCode()) {
            case -1263611923:
                b = !str.equals("WEATHER_ICON_ID_ID1") ? (byte) -1 : (byte) 0;
                break;
            case -1263611922:
                b = !str.equals("WEATHER_ICON_ID_ID2") ? (byte) -1 : (byte) 1;
                break;
            case -1263611921:
                b = !str.equals("WEATHER_ICON_ID_ID3") ? (byte) -1 : (byte) 2;
                break;
            case -1263611920:
                b = !str.equals("WEATHER_ICON_ID_ID4") ? (byte) -1 : (byte) 3;
                break;
            case -1263611919:
                b = !str.equals("WEATHER_ICON_ID_ID5") ? (byte) -1 : (byte) 4;
                break;
            case -1263611918:
                b = !str.equals("WEATHER_ICON_ID_ID6") ? (byte) -1 : (byte) 5;
                break;
            case -1263611917:
                b = !str.equals("WEATHER_ICON_ID_ID7") ? (byte) -1 : (byte) 6;
                break;
            case -1263611916:
                b = !str.equals("WEATHER_ICON_ID_ID8") ? (byte) -1 : (byte) 7;
                break;
            case -1263611915:
                b = !str.equals("WEATHER_ICON_ID_ID9") ? (byte) -1 : (byte) 8;
                break;
            case -517263901:
                b = !str.equals("WEATHER_ICON_ID_ID10") ? (byte) -1 : (byte) 9;
                break;
            case -517263899:
                b = !str.equals("WEATHER_ICON_ID_ID12") ? (byte) -1 : (byte) 10;
                break;
            case -517263898:
                b = !str.equals("WEATHER_ICON_ID_ID13") ? (byte) -1 : (byte) 11;
                break;
            case -517263897:
                b = !str.equals("WEATHER_ICON_ID_ID14") ? (byte) -1 : (byte) 12;
                break;
            case -517263896:
                b = !str.equals("WEATHER_ICON_ID_ID15") ? (byte) -1 : Ascii.CR;
                break;
            case -517263895:
                b = !str.equals("WEATHER_ICON_ID_ID16") ? (byte) -1 : Ascii.SO;
                break;
            case -517263894:
                b = !str.equals("WEATHER_ICON_ID_ID17") ? (byte) -1 : Ascii.SI;
                break;
            case -517263893:
                b = !str.equals("WEATHER_ICON_ID_ID18") ? (byte) -1 : (byte) 16;
                break;
            case -517263892:
                b = !str.equals("WEATHER_ICON_ID_ID19") ? (byte) -1 : (byte) 17;
                break;
            case -517263870:
                b = !str.equals("WEATHER_ICON_ID_ID20") ? (byte) -1 : Ascii.DC2;
                break;
            case -517263864:
                b = !str.equals("WEATHER_ICON_ID_ID26") ? (byte) -1 : (byte) 19;
                break;
            case -517263863:
                b = !str.equals("WEATHER_ICON_ID_ID27") ? (byte) -1 : Ascii.DC4;
                break;
            case -517263862:
                b = !str.equals("WEATHER_ICON_ID_ID28") ? (byte) -1 : Ascii.NAK;
                break;
            case -517263776:
                b = !str.equals("WEATHER_ICON_ID_ID51") ? (byte) -1 : Ascii.SYN;
                break;
            case -517263746:
                b = !str.equals("WEATHER_ICON_ID_ID60") ? (byte) -1 : Ascii.ETB;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 3:
                return R.mipmap.w_cloudy120;
            case 1:
            case 2:
                return R.mipmap.w_sunny120;
            case 4:
            case 21:
                return R.mipmap.w_haze120;
            case 5:
                return R.mipmap.w_overcast120;
            case 6:
            case 22:
                return R.mipmap.w_foggy120;
            case 7:
                return R.mipmap.w_dust120;
            case 8:
                return R.mipmap.w_ice_rain120;
            case 9:
            case 10:
            case 11:
                return R.mipmap.w_sleet120;
            case 12:
                return R.mipmap.w_heavy_to_severe_storm120;
            case 13:
            case 17:
                return R.mipmap.w_shower120;
            case 14:
                return R.mipmap.w_thunderstorm_hail120;
            case 15:
                return R.mipmap.w_thundershower120;
            case 16:
                return R.mipmap.w_light_snow120;
            case 18:
                return R.mipmap.w_windy120;
            case 19:
                return R.mipmap.w_sandstorm120;
            case 20:
                return R.mipmap.w_duststorm120;
            case 23:
                return R.mipmap.w_thunderstorm120;
            default:
                return -1;
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public int get72ResId(String str) {
        byte b;
        str.hashCode();
        switch (str.hashCode()) {
            case -1263611923:
                b = !str.equals("WEATHER_ICON_ID_ID1") ? (byte) -1 : (byte) 0;
                break;
            case -1263611922:
                b = !str.equals("WEATHER_ICON_ID_ID2") ? (byte) -1 : (byte) 1;
                break;
            case -1263611921:
                b = !str.equals("WEATHER_ICON_ID_ID3") ? (byte) -1 : (byte) 2;
                break;
            case -1263611920:
                b = !str.equals("WEATHER_ICON_ID_ID4") ? (byte) -1 : (byte) 3;
                break;
            case -1263611919:
                b = !str.equals("WEATHER_ICON_ID_ID5") ? (byte) -1 : (byte) 4;
                break;
            case -1263611918:
                b = !str.equals("WEATHER_ICON_ID_ID6") ? (byte) -1 : (byte) 5;
                break;
            case -1263611917:
                b = !str.equals("WEATHER_ICON_ID_ID7") ? (byte) -1 : (byte) 6;
                break;
            case -1263611916:
                b = !str.equals("WEATHER_ICON_ID_ID8") ? (byte) -1 : (byte) 7;
                break;
            case -1263611915:
                b = !str.equals("WEATHER_ICON_ID_ID9") ? (byte) -1 : (byte) 8;
                break;
            case -517263901:
                b = !str.equals("WEATHER_ICON_ID_ID10") ? (byte) -1 : (byte) 9;
                break;
            case -517263899:
                b = !str.equals("WEATHER_ICON_ID_ID12") ? (byte) -1 : (byte) 10;
                break;
            case -517263898:
                b = !str.equals("WEATHER_ICON_ID_ID13") ? (byte) -1 : (byte) 11;
                break;
            case -517263897:
                b = !str.equals("WEATHER_ICON_ID_ID14") ? (byte) -1 : (byte) 12;
                break;
            case -517263896:
                b = !str.equals("WEATHER_ICON_ID_ID15") ? (byte) -1 : Ascii.CR;
                break;
            case -517263895:
                b = !str.equals("WEATHER_ICON_ID_ID16") ? (byte) -1 : Ascii.SO;
                break;
            case -517263894:
                b = !str.equals("WEATHER_ICON_ID_ID17") ? (byte) -1 : Ascii.SI;
                break;
            case -517263893:
                b = !str.equals("WEATHER_ICON_ID_ID18") ? (byte) -1 : (byte) 16;
                break;
            case -517263892:
                b = !str.equals("WEATHER_ICON_ID_ID19") ? (byte) -1 : (byte) 17;
                break;
            case -517263870:
                b = !str.equals("WEATHER_ICON_ID_ID20") ? (byte) -1 : Ascii.DC2;
                break;
            case -517263864:
                b = !str.equals("WEATHER_ICON_ID_ID26") ? (byte) -1 : (byte) 19;
                break;
            case -517263863:
                b = !str.equals("WEATHER_ICON_ID_ID27") ? (byte) -1 : Ascii.DC4;
                break;
            case -517263862:
                b = !str.equals("WEATHER_ICON_ID_ID28") ? (byte) -1 : Ascii.NAK;
                break;
            case -517263776:
                b = !str.equals("WEATHER_ICON_ID_ID51") ? (byte) -1 : Ascii.SYN;
                break;
            case -517263746:
                b = !str.equals("WEATHER_ICON_ID_ID60") ? (byte) -1 : Ascii.ETB;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 3:
                return R.mipmap.w_cloudy72;
            case 1:
            case 2:
                return R.mipmap.w_sunny72;
            case 4:
            case 21:
                return R.mipmap.w_haze72;
            case 5:
                return R.mipmap.w_overcast72;
            case 6:
            case 22:
                return R.mipmap.w_foggy72;
            case 7:
                return R.mipmap.w_dust72;
            case 8:
                return R.mipmap.w_ice_rain72;
            case 9:
            case 10:
            case 11:
                return R.mipmap.w_sleet72;
            case 12:
                return R.mipmap.w_heavy_to_severe_storm72;
            case 13:
            case 17:
                return R.mipmap.w_shower72;
            case 14:
                return R.mipmap.w_thunderstorm_hail72;
            case 15:
                return R.mipmap.w_thundershower72;
            case 16:
                return R.mipmap.w_light_snow72;
            case 18:
                return R.mipmap.w_windy72;
            case 19:
                return R.mipmap.w_sandstorm72;
            case 20:
                return R.mipmap.w_duststorm72;
            case 23:
                return R.mipmap.w_thunderstorm72;
            default:
                return -1;
        }
    }
}
