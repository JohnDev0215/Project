package com.ts.car.timesync;

import com.ts.car.input.ConfigParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DataLoader {
    private static final String CONFIG_FILE_NAME = "system/etc/data/timesync.json";
    private static final String DM_DEFAULT_ENCODING = "utf-8";
    private static final String TAG = "DataLoader";
    private static final String TAG_ENABLE = "enable";
    private static final String TAG_HAS_RTC = "has_rtc";
    private static final String TAG_INTERNAL_SOURCE = "is_internal_source";
    private static final String TAG_MCU_TIME_RESYNC_ENABLE = "mcu_time_resync_enable";
    private static final String TAG_NAME = "name";
    private static final String TAG_PRIORITY = "priority";
    private static final String TAG_SET_ANDROID_TIME_ENABLE = "android_time_set_enable";
    private static final String TAG_SET_ANDROID_TIME_ZONE_ENABLE = "android_time_zone_set_enable";
    private static final String TAG_SKEW_THRESHOLD = "skew_threshold";
    private static final String TAG_TIME_SOURCE = "time_source";
    private static volatile DataLoader sInstance;
    private String mConfigFileName = CONFIG_FILE_NAME;

    public static DataLoader getInstance() {
        if (sInstance == null) {
            synchronized (DataLoader.class) {
                if (sInstance == null) {
                    sInstance = new DataLoader();
                }
            }
        }
        return sInstance;
    }

    public void setConfigFileName(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            this.mConfigFileName = fileName;
            LogUtils.debug(TAG, "mConfigFileName=" + this.mConfigFileName);
        }
    }

    public TimeSyncData loadDataFromJson() {
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder;
        TimeSyncData timeSyncData = new TimeSyncData();
        try {
            inputStream = new FileInputStream(this.mConfigFileName);
            inputStreamReader = new InputStreamReader(inputStream, DM_DEFAULT_ENCODING);
            bufferedReader = new BufferedReader(inputStreamReader);
            stringBuilder = new StringBuilder();
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        } catch (JSONException ex3) {
            ex3.printStackTrace();
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            stringBuilder.append(line);
            inputStream = inputStream;
            LogUtils.debug(TAG, "timeSyncData=" + timeSyncData.toString());
            return timeSyncData;
        }
        bufferedReader.close();
        inputStreamReader.close();
        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        if (jsonObject.has(TAG_SKEW_THRESHOLD)) {
            timeSyncData.setSkewThreshold(jsonObject.getInt(TAG_SKEW_THRESHOLD));
        }
        if (jsonObject.has(TAG_HAS_RTC)) {
            timeSyncData.setHasRtc(jsonObject.getBoolean(TAG_HAS_RTC));
        }
        if (jsonObject.has(TAG_MCU_TIME_RESYNC_ENABLE)) {
            timeSyncData.setEnableReSync(jsonObject.getBoolean(TAG_MCU_TIME_RESYNC_ENABLE));
        }
        if (jsonObject.has(TAG_SET_ANDROID_TIME_ENABLE)) {
            timeSyncData.setEnableSetKernelTime(jsonObject.getBoolean(TAG_SET_ANDROID_TIME_ENABLE));
        }
        if (jsonObject.has(TAG_SET_ANDROID_TIME_ZONE_ENABLE)) {
            timeSyncData.setEnableSetKernelTimeZone(jsonObject.getBoolean(TAG_SET_ANDROID_TIME_ZONE_ENABLE));
        }
        if (jsonObject.has(TAG_TIME_SOURCE)) {
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_TIME_SOURCE);
            ArrayList<ClockSourceInfo> sourceList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                ClockSourceInfo sourceInfo = new ClockSourceInfo();
                sourceInfo.setName(object.has(TAG_NAME) ? object.getString(TAG_NAME) : ConfigParser.EMPTY);
                boolean z = false;
                sourceInfo.setIsEnable(object.has(TAG_ENABLE) ? object.getBoolean(TAG_ENABLE) : false);
                sourceInfo.setPriority(object.has(TAG_PRIORITY) ? object.getInt(TAG_PRIORITY) : 0);
                if (object.has(TAG_INTERNAL_SOURCE)) {
                    z = object.getBoolean(TAG_INTERNAL_SOURCE);
                }
                sourceInfo.setIsInternalSource(z);
                sourceList.add(sourceInfo);
            }
            timeSyncData.setClockSourceList(sourceList);
        }
        LogUtils.debug(TAG, "timeSyncData=" + timeSyncData.toString());
        return timeSyncData;
    }
}
