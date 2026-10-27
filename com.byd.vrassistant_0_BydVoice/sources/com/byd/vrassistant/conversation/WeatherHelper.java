package com.byd.vrassistant.conversation;

import android.content.Context;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.ui.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class WeatherHelper {
    private static Context mContext = ContextHolder.getApplicationContext();
    private final String TAG;
    private int TimeOut;

    private WeatherHelper() {
        this.TAG = "NEU_Audio WeatherHelper";
        this.TimeOut = 4;
    }

    private static class WeatherHelperHolder {
        private static final WeatherHelper audioHelper = new WeatherHelper();

        private WeatherHelperHolder() {
        }
    }

    public static WeatherHelper getInstance(Context context) {
        mContext = context;
        return WeatherHelperHolder.audioHelper;
    }

    public String getOneDayWeekSource(String str) {
        String str2 = "";
        try {
            str2 = new SimpleDateFormat("MM/dd EEEE", Locale.ENGLISH).format(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(str));
            return str2.substring(6);
        } catch (ParseException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public int getOneDayWeekInt(String str) {
        str.hashCode();
        switch (str) {
            case "Saturday":
                return 6;
            case "Monday":
                return 1;
            case "Sunday":
                return 7;
            case "Wednesday":
                return 3;
            case "Tuesday":
                return 2;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            default:
                return -1;
        }
    }

    public String getCurrendDayWeek(String str) {
        str.hashCode();
        switch (str) {
            case "Saturday":
                return mContext.getResources().getString(R.string.weather_Saturday);
            case "Monday":
                return mContext.getResources().getString(R.string.weather_Monday);
            case "Sunday":
                return mContext.getResources().getString(R.string.weather_Sunday);
            case "Wednesday":
                return mContext.getResources().getString(R.string.weather_Wednesday);
            case "Tuesday":
                return mContext.getResources().getString(R.string.weather_Tuesday);
            case "Thursday":
                return mContext.getResources().getString(R.string.weather_Thursday);
            case "Friday":
                return mContext.getResources().getString(R.string.weather_Friday);
            default:
                return "";
        }
    }

    public String getJXweek(int i) {
        switch (i) {
            case 1:
                return mContext.getResources().getString(R.string.weather_Mon);
            case 2:
                return mContext.getResources().getString(R.string.weather_Tue);
            case 3:
                return mContext.getResources().getString(R.string.weather_Wed);
            case 4:
                return mContext.getResources().getString(R.string.weather_Thu);
            case 5:
                return mContext.getResources().getString(R.string.weather_Fri);
            case 6:
                return mContext.getResources().getString(R.string.weather_Sat);
            case 7:
                return mContext.getResources().getString(R.string.weather_Sun);
            default:
                return "";
        }
    }
}
