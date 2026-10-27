package com.ts.lib.cluster.adapter.someip;

import android.os.Bundle;
import android.util.Log;
import com.ts.lib.cluster.adapter.someip.SomeIpConstants;
import com.ts.lib.cluster.utils.LogUtil;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SomeIpPackageUtils {
    private static final String B_CALL_NAME = "B-CALL";
    private static final int DEFAULT_INT = 0;
    private static final int DEFAULT_MEDIA_SOURCE = 0;
    private static final int DEFAULT_MEDIA_VOLUME = 0;
    private static final String E_CALL_NAME = "E-CALL";
    private static final String FALSE = "false";
    private static final int HAS_FOCUS = 1;
    private static final int NUM_SIXTY = 60;
    private static final int NUM_TEN = 10;
    private static final int ONE_THOUSAND_HZ = 1000;
    private static final int ONE_THOUSAND_MS = 1000;
    private static final int PHONE_STATE_DEFAULT = -1;
    private static final int RADIO_STATE_PAUSE = 0;
    private static final int RADIO_STATE_PLAY = 1;
    private static final String TAG = "SomeIpPackageUtils";
    private static final String TRUE = "true";
    private static volatile SomeIpPackageUtils sInstance = null;

    public static synchronized SomeIpPackageUtils getInstance() {
        if (sInstance == null) {
            sInstance = new SomeIpPackageUtils();
        }
        return sInstance;
    }

    private SomeIpPackageUtils() {
    }

    public String packageMusicInfo(Bundle bundle) {
        int musicSource = bundle.getInt("MusicSource", 0);
        String mediaName = bundle.getString("MediaName", "");
        String mediaSinger = bundle.getString("MediaSinger", "");
        String mediaCoverUri = bundle.getString("MediaCoverUri", "");
        String isChangeAlbumPic = bundle.getString(SomeIpConstants.Key.IS_CHANDE_ALBUM_PIC, TRUE);
        LogUtil.logD(TAG, "packageMusicInfo   musicSource " + musicSource + " mediaName " + mediaName + " mediaSinger " + mediaSinger + " mediaCoverUri " + mediaCoverUri);
        int origin = DataConvertUtils.getInstance().musicSourceConvert(musicSource);
        JSONObject musicInfo = new JSONObject();
        try {
            musicInfo.put(SomeIpConstants.Key.ORIGIN, origin);
            musicInfo.put(SomeIpConstants.Key.SONG_INDEX, 0);
            musicInfo.put(SomeIpConstants.Key.SONG_NAME, mediaName);
            musicInfo.put(SomeIpConstants.Key.SONG_ARTIST, mediaSinger);
            musicInfo.put(SomeIpConstants.Key.SONG_ALBUM, "");
            musicInfo.put(SomeIpConstants.Key.SONG_SCHOOLS, "");
            musicInfo.put(SomeIpConstants.Key.SONG_AGE, "");
            musicInfo.put(SomeIpConstants.Key.IS_CHANDE_ALBUM_PIC, isChangeAlbumPic);
            musicInfo.put(SomeIpConstants.Key.SONG_ALBUM_PICTURE, mediaCoverUri);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return musicInfo.toString();
    }

    public String packageMusicState(Bundle bundle) {
        int mediaState = bundle.getInt("MediaState", -1);
        LogUtil.logD(TAG, "packageMusicState  mediaState " + mediaState);
        String state = DataConvertUtils.getInstance().musicStateConvert(mediaState);
        JSONObject musicState = new JSONObject();
        try {
            musicState.put(SomeIpConstants.Key.MUSIC_PLAY_STATUS, state);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return musicState.toString();
    }

    public String packageMusicTime(Bundle bundle) {
        long totalTime = bundle.getLong("MediaTotalTime", 0L);
        long curTime = bundle.getLong("MediaCurTime", 0L);
        LogUtil.logD(TAG, "packageMusicTime  mediaTotalTIme " + totalTime + " mediaCurTime " + curTime);
        int musicTotalTime = 0;
        int musicCurTime = 0;
        if (totalTime != 0) {
            musicTotalTime = (int) (totalTime / 1000);
        }
        if (curTime != 0) {
            musicCurTime = (int) (curTime / 1000);
        }
        JSONObject musicPlayingTime = new JSONObject();
        try {
            musicPlayingTime.put(SomeIpConstants.Key.CURRENT_PALYING_TIME, musicCurTime);
            musicPlayingTime.put(SomeIpConstants.Key.TOTAL_PLAY_TIME, musicTotalTime);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return musicPlayingTime.toString();
    }

    public String packageMusicLyrics(Bundle bundle) {
        String lyrics = bundle.getString("MediaLyrics", "");
        LogUtil.logD(TAG, "packageMusicLyrics:" + lyrics);
        JSONObject musicLyrics = new JSONObject();
        try {
            musicLyrics.put(SomeIpConstants.Key.LYRICS, lyrics);
        } catch (Exception exc) {
            LogUtil.logE(TAG, Log.getStackTraceString(exc));
        }
        return musicLyrics.toString();
    }

    public String packageMusicLoadState(Bundle bundle) {
        int musicLoadState = bundle.getInt("IsLoading");
        LogUtil.logD(TAG, "packageMusicLoadStatus:" + musicLoadState);
        JSONObject musicLoadStatus = new JSONObject();
        try {
            musicLoadStatus.put(SomeIpConstants.Key.MUSIC_LOAD_STATE, musicLoadState);
        } catch (Exception exc) {
            LogUtil.logE(TAG, Log.getStackTraceString(exc));
        }
        return musicLoadStatus.toString();
    }

    public String packageRadioInfo(Bundle bundle) {
        int radioType = bundle.getInt("RadioType");
        String mediaName = bundle.getString("MediaName");
        LogUtil.logD(TAG, "packageRadioInfo  radioType " + radioType + " mediaName " + mediaName);
        int radioFreq = 0;
        if (radioType == 0) {
            radioFreq = Integer.parseInt(mediaName);
        } else if (radioType == 1) {
            radioFreq = (int) (Float.parseFloat(mediaName) * 1000.0f);
        } else {
            LogUtil.logD(TAG, "radio type is invalid.");
        }
        JSONObject radioInfo = new JSONObject();
        try {
            radioInfo.put(SomeIpConstants.Key.CURRENT_FREQ, radioFreq);
            radioInfo.put(SomeIpConstants.Key.STATION_NAME, "");
            radioInfo.put(SomeIpConstants.Key.HAS_FOCUS, 1);
            radioInfo.put(SomeIpConstants.Key.MEDIA_VOLUME, 0);
            radioInfo.put(SomeIpConstants.Key.BAND, radioType);
            radioInfo.put(SomeIpConstants.Key.INDEX, 0);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return radioInfo.toString();
    }

    public String packageRadioState(Bundle bundle) {
        int radioType = bundle.getInt("RadioType");
        int mediaState = bundle.getInt("MediaState");
        LogUtil.logD(TAG, "packageRadioState  radioType " + radioType + " mediaState " + mediaState);
        String radioState = DataConvertUtils.getInstance().radioStateConvert(radioType, mediaState);
        JSONObject radioInfo = new JSONObject();
        try {
            radioInfo.put(SomeIpConstants.Key.AST_STATE, radioState);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return radioInfo.toString();
    }

    public String packageRadioLoadState(Bundle bundle) {
        int radioLoadState = bundle.getInt("IsLoading");
        LogUtil.logD(TAG, "packageRadioLoadState " + radioLoadState);
        JSONObject radioLoadStateInfo = new JSONObject();
        try {
            radioLoadStateInfo.put(SomeIpConstants.Key.RADIO_LOAD_STATE, radioLoadState);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return radioLoadStateInfo.toString();
    }

    public String packageRadioRealPlayState(Bundle bundle) {
        int mediaState = bundle.getInt("MediaState");
        LogUtil.logD(TAG, "packageRadioRealPlayState " + mediaState);
        JSONObject radioInfo = new JSONObject();
        try {
            if (mediaState == 1) {
                radioInfo.put(SomeIpConstants.Key.RADIO_PLAY_STATUS, SomeIpConstants.StateMediaState.MEDIA_STATE_START);
                LogUtil.logD(TAG, "RADIO_PLAY_STATUS:" + radioInfo);
            } else if (mediaState == 0) {
                radioInfo.put(SomeIpConstants.Key.RADIO_PLAY_STATUS, SomeIpConstants.StateMediaState.MEDIA_STATE_PAUSE);
                LogUtil.logD(TAG, "RADIO_PLAY_STATUS:" + radioInfo);
            } else {
                LogUtil.logD(TAG, "RADIO_PLAY_STATUS:Get wrong data,please check!");
            }
        } catch (Exception exc) {
            LogUtil.logE(TAG, Log.getStackTraceString(exc));
        }
        return radioInfo.toString();
    }

    public String packageBtCallInfo(Bundle bundle) {
        int callIndex = bundle.getInt("CallIndex", 0);
        String callName = bundle.getString("CallName", "");
        String callNum = bundle.getString("CallNum", "");
        String callPhotoUrl = bundle.getString("CallPhotoUrl", "");
        int callState = bundle.getInt(SomeIpConstants.Key.CALL_STATE, -1);
        LogUtil.logD(TAG, "packageBtCallInfo  callIndex " + callIndex + " callName " + callName + " callNum " + callNum + " callPhotoUrl " + callPhotoUrl + " CallState " + callState);
        int phoneType = DataConvertUtils.getInstance().phoneTypeConvert(3);
        int phoneState = DataConvertUtils.getInstance().btCallStateConvert(callState);
        JSONObject callInfo = new JSONObject();
        try {
            callInfo.put(SomeIpConstants.Key.PHONE_TYPE, phoneType);
            callInfo.put(SomeIpConstants.Key.NAME, callName);
            callInfo.put(SomeIpConstants.Key.NUMBER, callNum);
            callInfo.put(SomeIpConstants.Key.CALL_STATE, phoneState);
            callInfo.put(SomeIpConstants.Key.CONTACT_PICTURE, callPhotoUrl);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return callInfo.toString();
    }

    public String packageCallTime(Bundle bundle) {
        long callActiveTime = bundle.getLong("CallActiveTime", 0L);
        LogUtil.logD(TAG, "packageCallTime callActiveTime： " + callActiveTime);
        int phoneActiveTime = 0;
        if (callActiveTime != 0) {
            phoneActiveTime = (int) (callActiveTime / 1000);
        }
        JSONObject callTime = new JSONObject();
        try {
            callTime.put(SomeIpConstants.Key.CALL_DURATION, phoneActiveTime);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return callTime.toString();
    }

    public String packageEbCallInfo(Bundle bundle) {
        int callType = bundle.getInt("EbcallType");
        int callState = bundle.getInt(SomeIpConstants.Key.CALL_STATE, -1);
        String callName = "";
        if (callType == 0) {
            callName = E_CALL_NAME;
        } else if (callType == 1) {
            callName = B_CALL_NAME;
        } else {
            LogUtil.logW(TAG, "unexpected callType in switch: " + callType);
        }
        LogUtil.logD(TAG, "packageEbCallInfo  callType " + callType + " CallState " + callState + " callName " + callName);
        int phoneType = DataConvertUtils.getInstance().phoneTypeConvert(callType);
        int phoneState = DataConvertUtils.getInstance().ebCallStateConvert(callState);
        JSONObject callInfo = new JSONObject();
        try {
            callInfo.put(SomeIpConstants.Key.PHONE_TYPE, phoneType);
            callInfo.put(SomeIpConstants.Key.NAME, callName);
            callInfo.put(SomeIpConstants.Key.NUMBER, "");
            callInfo.put(SomeIpConstants.Key.CALL_STATE, phoneState);
            callInfo.put(SomeIpConstants.Key.CONTACT_PICTURE, "");
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return callInfo.toString();
    }

    public String packageCpCallInfo(Bundle bundle) {
        String callName = bundle.getString("CallName", "");
        String callNum = bundle.getString("CallNum", "");
        int callState = bundle.getInt(SomeIpConstants.Key.CALL_STATE, -1);
        LogUtil.logD(TAG, "packageCpCallInfo  callName " + callName + " callNum " + callNum + " CallState " + callState);
        int phoneState = DataConvertUtils.getInstance().ebCallStateConvert(callState);
        JSONObject callInfo = new JSONObject();
        try {
            callInfo.put(SomeIpConstants.Key.PHONE_TYPE, 0);
            callInfo.put(SomeIpConstants.Key.NAME, callName);
            callInfo.put(SomeIpConstants.Key.NUMBER, callNum);
            callInfo.put(SomeIpConstants.Key.CALL_STATE, phoneState);
            callInfo.put(SomeIpConstants.Key.CONTACT_PICTURE, "");
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return callInfo.toString();
    }

    public String packageNaviLaneInfo(Bundle bundle) {
        int naviIconLaneSwitch = bundle.getInt("NaviIconLaneSwitch", 0);
        JSONObject jSONObject = new JSONObject();
        JSONArray naviLaneInfo = new JSONArray();
        if (bundle.getIntArray("NaviIconLaneInfo") != null) {
            int[] naviIconLaneInfo = bundle.getIntArray("NaviIconLaneInfo");
            LogUtil.logD(TAG, "packageNaviLaneInfo:  naviIconLaneSwitch: " + naviIconLaneSwitch);
            int laneInfo = naviIconLaneInfo.length;
            LogUtil.logD(TAG, "laneInfo：" + laneInfo);
            for (int i = 0; i < laneInfo; i++) {
                try {
                    JSONObject eachLaneInfo = new JSONObject();
                    eachLaneInfo.put(SomeIpConstants.Key.LANE_INFO, i + 1);
                    eachLaneInfo.put(SomeIpConstants.Key.LANE_ID, i);
                    eachLaneInfo.put(SomeIpConstants.Key.LANE_ICON_ID, naviIconLaneInfo[i]);
                    naviLaneInfo.put(eachLaneInfo);
                } catch (JSONException exc) {
                    LogUtil.logI(TAG, Log.getStackTraceString(exc));
                }
            }
            try {
                jSONObject.put(SomeIpConstants.Key.ROAD_INFO, naviLaneInfo);
            } catch (Exception exc2) {
                LogUtil.logI(TAG, Log.getStackTraceString(exc2));
            }
            return jSONObject.toString();
        }
        JSONObject eachLaneInfo2 = new JSONObject();
        try {
            eachLaneInfo2.put(SomeIpConstants.Key.LANE_INFO, 0);
            eachLaneInfo2.put(SomeIpConstants.Key.LANE_ID, 0);
            eachLaneInfo2.put(SomeIpConstants.Key.LANE_ICON_ID, 0);
            naviLaneInfo.put(eachLaneInfo2);
            jSONObject.put(SomeIpConstants.Key.ROAD_INFO, naviLaneInfo);
        } catch (JSONException exc3) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc3));
        }
        LogUtil.logD(TAG, "Don't hava lane info，naviIconLaneInfo's value is null");
        return jSONObject.toString();
    }

    public String packageNaviRoadInfo(Bundle bundle) {
        int naviSource = bundle.getInt("NaviSource", 0);
        int naviIconTbt = bundle.getInt("NaviIconTbt", 0);
        int naviNextRoadDistance = bundle.getInt("NaviNextRoadDistance", 0);
        int roadType = bundle.getInt("NaviRoadType", 0);
        String naviNextRoadName = bundle.getString("NaviNextRoadName");
        int crossStatus = bundle.getInt("NaviCrossingStatus", 0);
        int naviActionProgbar = bundle.getInt("NaviNextRoadPrecent", 0);
        LogUtil.logD(TAG, "packageNaviRoadInfo:  naviSource: " + naviSource + " naviIconTbt: " + naviIconTbt + " naviNextRoadDistance: " + naviNextRoadDistance + " roadType: " + roadType + " naviNextRoadName: " + naviNextRoadName + " crossStatus:" + crossStatus + " naviActionProgbar:" + naviActionProgbar);
        JSONObject naviInfo = new JSONObject();
        try {
            naviInfo.put(SomeIpConstants.Key.SEG_REMAIN_DIS, naviNextRoadDistance);
            naviInfo.put(SomeIpConstants.Key.ROAD_TYPE, roadType);
            naviInfo.put(SomeIpConstants.Key.ROAD_ICON, naviIconTbt);
            naviInfo.put(SomeIpConstants.Key.ROSD_NAME, naviNextRoadName);
            naviInfo.put(SomeIpConstants.Key.NEXT_NAVI_ACTION_PROGBAR, naviActionProgbar);
            naviInfo.put(SomeIpConstants.Key.INTERSECTION_ZOOM_STATUS, crossStatus);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return naviInfo.toString();
    }

    public String packageNaviIsShowOnCluster(Bundle bundle) {
        String ifNeedThemeValue;
        int naviTbtSwitch = bundle.getInt("NaviState", 0);
        if (naviTbtSwitch == 2) {
            ifNeedThemeValue = FALSE;
        } else {
            ifNeedThemeValue = TRUE;
        }
        JSONObject naviInfo = new JSONObject();
        try {
            naviInfo.put(SomeIpConstants.Key.NAVI_FRONT_DESK_STATUS, TRUE);
            naviInfo.put(SomeIpConstants.Key.DISPLAY_CLUSTER, TRUE);
            naviInfo.put(SomeIpConstants.Key.PERSPECTIVE, 0);
            naviInfo.put(SomeIpConstants.Key.PERSPECTIVE_RESULT, TRUE);
            naviInfo.put(SomeIpConstants.Key.REQUEST_DISPLAY_NAVI_AREA, ifNeedThemeValue);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return naviInfo.toString();
    }

    public String packageNaviGpsSignalInfo(Bundle bundle) {
        int naviGps = bundle.getInt("NaviGpsSignal");
        LogUtil.logD(TAG, "GPS signal is " + naviGps);
        JSONObject naviGpsSignalInfo = new JSONObject();
        try {
            naviGpsSignalInfo.put(SomeIpConstants.Key.NAVI_GPS_SIGNAL, naviGps);
        } catch (JSONException exception) {
            LogUtil.logI(TAG, Log.getStackTraceString(exception));
        }
        return naviGpsSignalInfo.toString();
    }

    public String packageNaviTotalInfo(Bundle bundle) {
        int distanceUnitOrigin = bundle.getInt("NaviDistanceToDestUnit", 2);
        int distanceUnit = DataConvertUtils.getInstance().distanceUnitConvert(distanceUnitOrigin);
        int naviToDestTime = bundle.getInt("NaviToDestTime", 0) / NUM_SIXTY;
        int naviArrivalTime = bundle.getInt("NaviArivalTime", 0);
        String arrivalTime = changeTimeFormat(naviArrivalTime);
        LogUtil.logD(TAG, "arrivalTime:" + arrivalTime);
        JSONObject naviInfo = new JSONObject();
        int naviSource = bundle.getInt("NaviSource", 0);
        if (naviSource == 1) {
            int naviDistanceToDest = bundle.getInt("NaviDistanceToDest", 0);
            LogUtil.logD(TAG, "packageNaviTotalInfo:  naviDistanceToDest: " + naviDistanceToDest + " naviToDestTime: " + naviToDestTime + " naviArrivalTime " + naviArrivalTime);
            try {
                naviInfo.put(SomeIpConstants.Key.TOTAL_DISTANCE, naviDistanceToDest);
                naviInfo.put(SomeIpConstants.Key.DISTANCE_UNIT, distanceUnit);
                naviInfo.put(SomeIpConstants.Key.TIME_LEFT, naviToDestTime);
                naviInfo.put(SomeIpConstants.Key.ARRIVAL_TIME, arrivalTime);
            } catch (JSONException exc) {
                LogUtil.logI(TAG, Log.getStackTraceString(exc));
            }
            String naviTotalInfo = naviInfo.toString();
            return naviTotalInfo;
        }
        if (naviSource == 3) {
            String naviDistanceToDestOfCarplay = bundle.getString("NaviDistanceToDestCp", "");
            LogUtil.logD(TAG, "packageNaviTotalInfo:  naviDistanceToDest: " + naviDistanceToDestOfCarplay + " naviToDestTime: " + naviToDestTime + " naviArrivalTime " + naviArrivalTime);
            try {
                naviInfo.put(SomeIpConstants.Key.TOTAL_DISTANCE, naviDistanceToDestOfCarplay);
                naviInfo.put(SomeIpConstants.Key.DISTANCE_UNIT, distanceUnit);
                naviInfo.put(SomeIpConstants.Key.TIME_LEFT, naviToDestTime);
                naviInfo.put(SomeIpConstants.Key.ARRIVAL_TIME, arrivalTime);
            } catch (JSONException exc2) {
                LogUtil.logI(TAG, Log.getStackTraceString(exc2));
            }
            String naviTotalInfo2 = naviInfo.toString();
            return naviTotalInfo2;
        }
        LogUtil.logD(TAG, "unknow naviSource,please check");
        return "";
    }

    public String packageNaviElectronicEyeInfo(Bundle bundle) {
        int naviStatus = bundle.getInt("NaviState", 0);
        int naviSpeedlimit = bundle.getInt("NaviSpeedLimit", 0);
        int naviIconElectronicType = bundle.getInt("NaviIconElectronicType", 0);
        int naviIconRoadSafetyType = bundle.getInt("NaviIconRoadSafetyType", 0);
        LogUtil.logD(TAG, "packageNaviTotalInfo:  naviStatus: " + naviStatus + " naviSpeedlimit: " + naviSpeedlimit + " naviIconElectronicType: " + naviIconElectronicType + " naviIconRoadSafetyType: " + naviIconRoadSafetyType);
        int naviSpeedValue = naviSpeedlimit / 10;
        JSONObject naviInfo = new JSONObject();
        try {
            naviInfo.put(SomeIpConstants.Key.NAVI_STATUS, naviStatus);
            naviInfo.put(SomeIpConstants.Key.SPEEDING_INFO, naviSpeedValue);
            naviInfo.put(SomeIpConstants.Key.CAMERA_TYPE, naviIconElectronicType);
            naviInfo.put(SomeIpConstants.Key.WARNING_MESSAGE, naviIconRoadSafetyType);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return naviInfo.toString();
    }

    public String packageDisplayAreaAndResult(Bundle bundle) {
        int displayArea = bundle.getInt(SomeIpConstants.Key.NAVI_DISPLAY_AREA);
        int result = bundle.getInt("ClusterThemeType");
        String displayResult = result == 0 ? TRUE : FALSE;
        LogUtil.logD(TAG, "packageDisplayAreaAndResult:  displayArea: " + displayArea + " result：" + result + " displayResult: " + displayResult);
        JSONObject naviInfo = new JSONObject();
        try {
            naviInfo.put(SomeIpConstants.Key.NAVI_DISPLAY_AREA, displayArea);
            naviInfo.put(SomeIpConstants.Key.NAVI_DISPLAY_AREA_RESULT, displayResult);
        } catch (Exception exc) {
            LogUtil.logI(TAG, Log.getStackTraceString(exc));
        }
        return naviInfo.toString();
    }

    private String changeTimeFormat(int naviArrivalTime) {
        String arrivalTime;
        Calendar calendar = Calendar.getInstance();
        calendar.add(13, naviArrivalTime);
        int etaHour = calendar.get(11);
        int etaMinute = calendar.get(12);
        if ((etaHour >= 0) & (etaHour < 10)) {
            arrivalTime = "0" + etaHour + ":";
        } else {
            arrivalTime = "" + etaHour + ":";
        }
        if ((etaMinute < 10) & (etaMinute >= 0)) {
            return arrivalTime + "0" + etaMinute;
        }
        return arrivalTime + etaMinute;
    }
}
