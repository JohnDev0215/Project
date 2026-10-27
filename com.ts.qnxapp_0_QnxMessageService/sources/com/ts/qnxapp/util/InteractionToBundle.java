package com.ts.qnxapp.util;

import android.os.Bundle;
import com.autolink.interaction.InteractionProto;
import com.ts.qnxapp.fdbusClientForAndroid.FdbusProtoBuilder;
import ipc.fdbus.FdbusMessage;
import ipc.fdbus.FdbusMsgBuilder;

/* JADX INFO: loaded from: classes.dex */
public class InteractionToBundle {
    private static final String ADAS_DEBUG_MODE = "adasDebugMode";
    private static final String ADAS_SELF_LEARN_DISPLAY = "adasSelfLearn";
    private static final String ADAS_STATUS_NUMBER = "adasStatusNumber";
    private static final String ALBUM_PATH = "MediaCoverUri";
    private static final String AM_PM_DISPLAY = "AmPmDisplay";
    private static final String ARRIVAL_DEST_TIME = "arriveToDestTime";
    private static final String ARRIVAL_DEST_TIME_DAY = "arriveToDestTimeDay";
    private static final String ARTIST_NAME = "MediaSinger";
    private static final String CALL_STATUS = "CallState";
    private static final String CALL_TIME = "CallActiveTime";
    private static final String CHIME_SOUND_TYPE = "chimeSound";
    private static final String CLOCK_FORMAT = "ClockFormat";
    private static final String CLUSTER_THEME_NUMBER = "clusterThemeNumber";
    private static final String CONTACT_NAME = "CallName";
    private static final String CONTACT_NUMBER = "CallNum";
    private static final String CPAA_CONNECT_STATE = "cpaaConnectState";
    private static final String CURRENT_PROGRESS = "MediaCurTime";
    private static final String CURRENT_VISIBLE = "CurrentVisible";
    private static final String DIST_TO_DEST = "distToDest";
    private static final String DIST_TO_DEST_DIS_DATA = "distToDestDisData";
    private static final String DIST_TO_DEST_DIS_UNIT = "distToDestDisUnit";
    private static final String DIST_TO_DEST_TIME = "distToDestTime";
    private static final String DIST_TO_DEST_UNIT = "distToDestUnit";
    private static final String ELEVATION_UNIT = "ElevationUnit";
    private static final String ELEVATION_VALUE = "ElevationValue";
    private static final String ICON_DISPLAY = "IconDisplay";
    private static final String INS_DISPLAY_NUMBER = "insDisPlayNumber";
    private static final String LANGUAGE_SET = "LanguageSet";
    private static final String MAP_TYPE = "mapType";
    private static final String MAX_PROGRESS = "MediaTotalTime";
    private static final String MEDIA_TEXT = "MediaName";
    private static final String MSG_TYPE = "msgType";
    private static final String MULTIPLE_TEXT = "multipleText";
    private static final String MULTIPLE_TEXT_ONE = "multipleText1";
    private static final String MULTIPLE_TEXT_TWO = "multipleText2";
    private static final String MUSIC_NAME_ROLL = "MusicNameRoll";
    private static final String NAVI_INSTALL_STATE = "naviInstallState";
    private static final String NAVI_MODE = "mode";
    private static final String NAVI_POP_STATE = "state";
    private static final String NAVI_STATE = "naviState";
    private static final String NEXI_DIST_UNIT = "nextDistUnit";
    private static final String NEXI_NEXT_DIST_UNIT = "nextNextDistUnit";
    private static final String NEXI_NEXT_ROAD_DIST = "nextNextRoadDist";
    private static final String NEXI_NEXT_TBT_ICON = "nextNextTbtIcon";
    private static final String NEXI_ROAD_DIST = "nextRoadDist";
    private static final String NEXI_ROAD_NAME = "nextRoadName";
    private static final String NEXI_TBT_ICON = "nextTbtIcon";
    private static final String NEXT_NEXT_ROAD_DIST_DATA = "nextNextDistData";
    private static final String NEXT_NEXT_ROAD_DIST_UNIT = "nextNextDistAndUnit";
    private static final String NEXT_ROAD_DIST_DATA = "nextRoadDistData";
    private static final String NEXT_ROAD_DIST_UNIT = "nextRoadDistUnit";
    private static final String OSD_CLUSTER_MODE = "osdClusterMode";
    private static final String OVERSEAS_MAP_SUPPLIER = "overseasMapSupplier";
    private static final String PHONE_NUMBER_VISIBLE = "PhoneNumberVisible";
    private static final String PHONE_TIME_VISIBLE = "PhoneTimeVisible";
    private static final String PHONE_TYPE = "PhoneType";
    private static final String PLAY_INTERFACE = "PlayInterface";
    private static final String PLAY_SOURCE = "MusicSource";
    private static final String PLAY_STATUS = "MediaState";
    private static final String QUERY_NUMBER = "queryNumber";
    private static final String RESET_OPERATION_VALUE = "ResetOperation";
    public static final String SCREEN_ORIENTATION_MODE = "screenOrientation";
    private static final String SETTING_THEME = "SettingTheme";
    private static final String SLOPE_UNIT = "SlopeUnit";
    private static final String SLOPE_VALUE = "SlopeValue";
    private static final String SOUND_TYPE = "SoundType";
    private static final String SUB_TYPE = "subtype";
    private static final String TAG = "InteractionToBundle";
    private static final String THEME_SET_NUMBER = "themeSetNumber";
    private static final String TIME_HOUR = "TimeHour";
    private static final String TIME_MINUTE = "TimeMinute";
    private static final String TIME_SECOND = "TimeSecond";
    private static final String TO_DEST_DAYS = "toDestDays";
    private static final String TO_DEST_HOURS = "toDestHours";
    private static final String TO_DEST_MINUTES = "toDestMinutes";
    private static final String TO_DEST_TIME = "toDestTime";
    private static final String UI_STATE = "uiState";
    private static final String VEHICLE_ACC_TIMER = "VehicleAccTimer";
    private static final String VEHICLE_ADAS_STAT = "VehicleAdasStat";
    private static final String VEHICLE_ID = "VehicleID";
    private static final String VEHICLE_INS_DISPLAY = "VehicleINSDisplay";
    private static final String VEHICLE_LIGHT_GEAR = "VehicleLightGear";
    private static final String VEHICLE_LIGHT_GEAR_STAT = "VehicleLightGearStat";
    private static final String VEHICLE_MILE_INFO = "VehicleMileInfo";
    private static final String VEHICLE_MILE_UNIT = "VehicleMileUnit";
    private static final String VEHICLE_MILE_VALUE = "VehicleMileValue";
    private static final String VEHICLE_SPEED_LIMIT = "VehicleSpeedLimit";
    private static final String VEHICLE_THEME_TYPE = "VehicleThemeType";
    private static final String VEHICLE_TYPE = "VehicleType";
    private static final String VERSATILE_THEME_MODE = "versatileTheme";
    private static volatile InteractionToBundle sInstance;

    public static InteractionToBundle getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        synchronized (InteractionToBundle.class) {
            if (sInstance == null) {
                sInstance = new InteractionToBundle();
            }
        }
        return sInstance;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public Bundle messageInteractionToBundle(FdbusMessage msg, int flag) {
        Bundle bundle = new Bundle();
        if (flag == 0) {
            switch (msg.code()) {
                case 0:
                    try {
                        bundle.putInt(MSG_TYPE, 9);
                        bundle.putInt(SUB_TYPE, msg.code());
                        byte[] dayNightBytes = msg.byteArray();
                        int themeSetNumber = dayNightBytes[0];
                        bundle.putInt(THEME_SET_NUMBER, themeSetNumber);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        bundle.putInt(MSG_TYPE, 3);
                        bundle.putInt(SUB_TYPE, msg.code());
                        byte[] hotKeyNaviBytes = msg.byteArray();
                        int naviMode = hotKeyNaviBytes[0];
                        bundle.putInt(NAVI_MODE, naviMode);
                    } catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        bundle.putInt(MSG_TYPE, 9);
                        bundle.putInt(SUB_TYPE, msg.code());
                        byte[] hotKeyInsDisBytes = msg.byteArray();
                        int insDispNumber = hotKeyInsDisBytes[0];
                        bundle.putInt(INS_DISPLAY_NUMBER, insDispNumber);
                    } catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        bundle.putInt(MSG_TYPE, 9);
                        bundle.putInt(SUB_TYPE, msg.code());
                        byte[] hotKeyAdasBytes = msg.byteArray();
                        int adasStatNumber = hotKeyAdasBytes[0];
                        bundle.putInt(ADAS_STATUS_NUMBER, adasStatNumber);
                    } catch (Exception ex4) {
                        ex4.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        bundle.putInt(MSG_TYPE, 9);
                        bundle.putInt(SUB_TYPE, msg.code());
                        byte[] hotKeyThemeBytes = msg.byteArray();
                        int themeTypeNumber = hotKeyThemeBytes[0];
                        bundle.putInt(CLUSTER_THEME_NUMBER, themeTypeNumber);
                    } catch (Exception ex5) {
                        ex5.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        bundle.putInt(MSG_TYPE, 3);
                        bundle.putInt(SUB_TYPE, msg.code());
                        byte[] uiModeBytes = msg.byteArray();
                        int uiStateNumber = uiModeBytes[0];
                        bundle.putInt(UI_STATE, uiStateNumber);
                    } catch (Exception ex6) {
                        ex6.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        bundle.putInt(MSG_TYPE, 9);
                        bundle.putInt(SUB_TYPE, msg.code());
                        byte[] chimeSoundBytes = msg.byteArray();
                        int chimeSoundNumber = chimeSoundBytes[0];
                        bundle.putInt(CHIME_SOUND_TYPE, chimeSoundNumber);
                    } catch (Exception ex7) {
                        ex7.printStackTrace();
                    }
                    break;
            }
        }
        return bundle;
    }

    public Object getInteractionMessage(int subType, String content, Bundle bundle) {
        String multipleText2;
        String multipleText1;
        String multipleText;
        String toDestTime;
        String toDestTimeDay;
        String distToDestTime;
        String distToDestUnit;
        String distToDest;
        String nextRoadDistUnit;
        String nextRoadDistData;
        String nextRoadDistUnit2;
        String nextRoadDistData2;
        String nextRoadName;
        String nextRoadName2;
        String contactNumber;
        String contactName;
        String artistName;
        String artistPath;
        String musicName;
        FdbusMsgBuilder msg = null;
        switch (subType) {
            case 0:
                try {
                    InteractionProto.ThemeSetting.Builder settingThemeBuilder = InteractionProto.ThemeSetting.newBuilder();
                    if (bundle.containsKey(SETTING_THEME)) {
                        InteractionProto.ThemeSet settingTheme = InteractionProto.ThemeSet.valueOf(bundle.getInt(SETTING_THEME));
                        settingThemeBuilder.setNCurrTheme(settingTheme);
                    }
                    InteractionProto.ThemeSetting req = settingThemeBuilder.build();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return null;
                }
                break;
            case 1:
                try {
                    InteractionProto.Clock.Builder clockBuilder = InteractionProto.Clock.newBuilder();
                    if (bundle.containsKey(CLOCK_FORMAT)) {
                        InteractionProto.ClockFormat clockFormat = InteractionProto.ClockFormat.valueOf(bundle.getInt(CLOCK_FORMAT));
                        clockBuilder.setTimeFormat(clockFormat);
                    }
                    if (bundle.containsKey(AM_PM_DISPLAY)) {
                        InteractionProto.AmPmDisplay amPmDisplay = InteractionProto.AmPmDisplay.valueOf(bundle.getInt(AM_PM_DISPLAY));
                        clockBuilder.setAmpmDisplay(amPmDisplay);
                    }
                    if (bundle.containsKey(TIME_HOUR)) {
                        clockBuilder.setTimeHour(bundle.getInt(TIME_HOUR));
                    }
                    if (bundle.containsKey(TIME_MINUTE)) {
                        clockBuilder.setTimeMinute(bundle.getInt(TIME_MINUTE));
                    }
                    if (bundle.containsKey(TIME_SECOND)) {
                        clockBuilder.setTimeSecond(bundle.getInt(TIME_SECOND));
                    }
                    InteractionProto.Clock clockReq = clockBuilder.build();
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    return null;
                }
                break;
            case 2:
                try {
                    InteractionProto.NaviSetting.Builder naviSettingBuilder = InteractionProto.NaviSetting.newBuilder();
                    if (bundle.containsKey(MAP_TYPE)) {
                        InteractionProto.MapType mapType = InteractionProto.MapType.valueOf(bundle.getInt(MAP_TYPE));
                        naviSettingBuilder.setNMapType(mapType);
                    }
                    if (bundle.containsKey(NAVI_STATE)) {
                        InteractionProto.MediaNavigationalstate mediaNavigationalstate = InteractionProto.MediaNavigationalstate.valueOf(bundle.getInt(NAVI_STATE));
                        naviSettingBuilder.setNCurrMedNaviStat(mediaNavigationalstate);
                    }
                    InteractionProto.NaviSetting naviSettingReq = naviSettingBuilder.build();
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                    return null;
                }
                break;
            case 3:
                try {
                    InteractionProto.HotKeySetting.Builder hotKeySettingBuilder = InteractionProto.HotKeySetting.newBuilder();
                    if (bundle.containsKey(VEHICLE_TYPE)) {
                        InteractionProto.VehicleType vehicleType = InteractionProto.VehicleType.valueOf(bundle.getInt(VEHICLE_TYPE));
                        hotKeySettingBuilder.setNVehicleType(vehicleType);
                    }
                    if (bundle.containsKey(VEHICLE_ID)) {
                        InteractionProto.VehicleID vehicleId = InteractionProto.VehicleID.valueOf(bundle.getInt(VEHICLE_ID));
                        hotKeySettingBuilder.setNVehicleID(vehicleId);
                    }
                    if (bundle.containsKey(VEHICLE_THEME_TYPE)) {
                        InteractionProto.VehicleThemeType vehicleThemeType = InteractionProto.VehicleThemeType.valueOf(bundle.getInt(VEHICLE_THEME_TYPE));
                        hotKeySettingBuilder.setNCurrThemeType(vehicleThemeType);
                    }
                    if (bundle.containsKey(VEHICLE_LIGHT_GEAR_STAT)) {
                        InteractionProto.VehicleLightGearStat vehicleLightGearStat = InteractionProto.VehicleLightGearStat.valueOf(bundle.getInt(VEHICLE_LIGHT_GEAR_STAT));
                        hotKeySettingBuilder.setNCurrLightStat(vehicleLightGearStat);
                    }
                    if (bundle.containsKey(VEHICLE_LIGHT_GEAR)) {
                        InteractionProto.VehicleLightGear vehicleLightGear = InteractionProto.VehicleLightGear.valueOf(bundle.getInt(VEHICLE_LIGHT_GEAR));
                        hotKeySettingBuilder.setNCurrLight(vehicleLightGear);
                    }
                    if (bundle.containsKey(NAVI_MODE)) {
                        try {
                            if (checkCurrNaviType(bundle.getInt(NAVI_MODE))) {
                                InteractionProto.VehicleNaviType vehicleNaviType = InteractionProto.VehicleNaviType.valueOf(bundle.getInt(NAVI_MODE));
                                hotKeySettingBuilder.setNCurrNaviType(vehicleNaviType);
                            }
                        } catch (Exception e) {
                            e = e;
                            Exception ex4 = e;
                            ex4.printStackTrace();
                            return null;
                        }
                    }
                    if (bundle.containsKey(VEHICLE_ADAS_STAT)) {
                        InteractionProto.VehicleAdasStat vehicleAdasStat = InteractionProto.VehicleAdasStat.valueOf(bundle.getInt(VEHICLE_ADAS_STAT));
                        hotKeySettingBuilder.setNCurrAdasStat(vehicleAdasStat);
                    }
                    if (bundle.containsKey(VEHICLE_INS_DISPLAY)) {
                        InteractionProto.VehicleINSDisp vehicleInsDisplay = InteractionProto.VehicleINSDisp.valueOf(bundle.getInt(VEHICLE_INS_DISPLAY));
                        hotKeySettingBuilder.setNCurrINSDisplay(vehicleInsDisplay);
                    }
                    if (bundle.containsKey(VEHICLE_MILE_INFO)) {
                        InteractionProto.VehicleMileInfo vehicleMileInfo = InteractionProto.VehicleMileInfo.valueOf(bundle.getInt(VEHICLE_MILE_INFO));
                        hotKeySettingBuilder.setNCurrMileInfo(vehicleMileInfo);
                    }
                    if (bundle.containsKey(VEHICLE_MILE_UNIT)) {
                        InteractionProto.VehicleMileUnit vehicleMileUnit = InteractionProto.VehicleMileUnit.valueOf(bundle.getInt(VEHICLE_MILE_UNIT));
                        hotKeySettingBuilder.setNCurrMileUnit(vehicleMileUnit);
                    }
                    if (bundle.containsKey(VEHICLE_MILE_VALUE)) {
                        InteractionProto.VehicleMileValue vehicleMileValue = InteractionProto.VehicleMileValue.valueOf(bundle.getInt(VEHICLE_MILE_VALUE));
                        hotKeySettingBuilder.setNCurrMileValue(vehicleMileValue);
                    }
                    if (bundle.containsKey(VEHICLE_ACC_TIMER)) {
                        InteractionProto.VehicleAccTimer vehicleAccTimer = InteractionProto.VehicleAccTimer.valueOf(bundle.getInt(VEHICLE_ACC_TIMER));
                        hotKeySettingBuilder.setNCurrAccTimer(vehicleAccTimer);
                    }
                    if (bundle.containsKey(VEHICLE_SPEED_LIMIT)) {
                        InteractionProto.VehicleSpeedLimit vehicleSpeedLimit = InteractionProto.VehicleSpeedLimit.valueOf(bundle.getInt(VEHICLE_SPEED_LIMIT));
                        hotKeySettingBuilder.setNCurrSpeedLimit(vehicleSpeedLimit);
                    }
                    InteractionProto.HotKeySetting hotKeySettingReq = hotKeySettingBuilder.build();
                } catch (Exception e2) {
                    e = e2;
                }
                break;
            case 4:
                try {
                    InteractionProto.Music.Builder musicBuilder = InteractionProto.Music.newBuilder();
                    if (bundle.containsKey(ICON_DISPLAY)) {
                        InteractionProto.IconDisply iconDisplay = InteractionProto.IconDisply.valueOf(bundle.getInt(ICON_DISPLAY));
                        musicBuilder.setMusicDisplayIcon(iconDisplay);
                    }
                    if (bundle.containsKey(MEDIA_TEXT) && (musicName = bundle.getString(MEDIA_TEXT)) != null) {
                        musicBuilder.setMediaText(musicName);
                    }
                    if (bundle.containsKey(PLAY_STATUS)) {
                        InteractionProto.PlayStatus playStatus = InteractionProto.PlayStatus.valueOf(bundle.getInt(PLAY_STATUS));
                        musicBuilder.setMStatus(playStatus);
                    }
                    if (bundle.containsKey(CURRENT_VISIBLE)) {
                        InteractionProto.Visible currentVisible = InteractionProto.Visible.valueOf(bundle.getInt(CURRENT_VISIBLE));
                        musicBuilder.setMVisible(currentVisible);
                    }
                    if (bundle.containsKey(MUSIC_NAME_ROLL)) {
                        InteractionProto.MusicNameRoll musicNameRoll = InteractionProto.MusicNameRoll.valueOf(bundle.getInt(MUSIC_NAME_ROLL));
                        musicBuilder.setRoll(musicNameRoll);
                    }
                    if (bundle.containsKey(PLAY_SOURCE)) {
                        musicBuilder.setMPlaySource(bundle.getInt(PLAY_SOURCE));
                    }
                    if (bundle.containsKey(PLAY_INTERFACE)) {
                        InteractionProto.PlayInterface playInterface = InteractionProto.PlayInterface.valueOf(bundle.getInt(PLAY_INTERFACE));
                        musicBuilder.setMPlayInterface(playInterface);
                    }
                    if (bundle.containsKey(ALBUM_PATH) && (artistPath = bundle.getString(ALBUM_PATH)) != null) {
                        musicBuilder.setAlbumPath(artistPath);
                    }
                    if (bundle.containsKey(ARTIST_NAME) && (artistName = bundle.getString(ARTIST_NAME)) != null) {
                        musicBuilder.setArtistName(artistName);
                    }
                    InteractionProto.Music musicReq = musicBuilder.build();
                } catch (Exception ex5) {
                    ex5.printStackTrace();
                    return null;
                }
                break;
            case 5:
                try {
                    InteractionProto.MediaProgress.Builder mediaProgressBuilder = InteractionProto.MediaProgress.newBuilder();
                    if (bundle.containsKey(CURRENT_PROGRESS)) {
                        mediaProgressBuilder.setCurrentProgress(bundle.getInt(CURRENT_PROGRESS));
                    }
                    if (bundle.containsKey(MAX_PROGRESS)) {
                        mediaProgressBuilder.setMaxProgress(bundle.getInt(MAX_PROGRESS));
                    }
                    InteractionProto.MediaProgress mediaProgressReq = mediaProgressBuilder.build();
                } catch (Exception ex6) {
                    ex6.printStackTrace();
                    return null;
                }
                break;
            case 6:
                try {
                    InteractionProto.BluetoothPhone.Builder bluetoothPhoneBuilder = InteractionProto.BluetoothPhone.newBuilder();
                    if (bundle.containsKey(ICON_DISPLAY)) {
                        InteractionProto.IconDisply iconDisplay2 = InteractionProto.IconDisply.valueOf(bundle.getInt(ICON_DISPLAY));
                        bluetoothPhoneBuilder.setPhoneDisplayIcon(iconDisplay2);
                    }
                    if (bundle.containsKey(CONTACT_NAME) && (contactName = bundle.getString(CONTACT_NAME)) != null) {
                        bluetoothPhoneBuilder.setContactName(contactName);
                    }
                    if (bundle.containsKey(CONTACT_NUMBER) && (contactNumber = bundle.getString(CONTACT_NUMBER)) != null) {
                        bluetoothPhoneBuilder.setContactNumber(contactNumber);
                    }
                    if (bundle.containsKey(CALL_TIME)) {
                        int callTimeMin = bundle.getInt(CALL_TIME);
                        bluetoothPhoneBuilder.setCallTime(callTimeMin);
                    }
                    if (bundle.containsKey(PHONE_NUMBER_VISIBLE)) {
                        InteractionProto.PhoneNumberVisible phoneNumberVisible = InteractionProto.PhoneNumberVisible.valueOf(bundle.getInt(PHONE_NUMBER_VISIBLE));
                        bluetoothPhoneBuilder.setPVisible(phoneNumberVisible);
                    }
                    if (bundle.containsKey(PHONE_TIME_VISIBLE)) {
                        InteractionProto.PhoneTimeVisible phoneTimeVisible = InteractionProto.PhoneTimeVisible.valueOf(bundle.getInt(PHONE_TIME_VISIBLE));
                        bluetoothPhoneBuilder.setTimeVisible(phoneTimeVisible);
                    }
                    if (bundle.containsKey(PHONE_TYPE)) {
                        bluetoothPhoneBuilder.setType(bundle.getInt(PHONE_TYPE));
                    }
                    if (bundle.containsKey(CALL_STATUS)) {
                        InteractionProto.CallStatus callStatus = InteractionProto.CallStatus.valueOf(bundle.getInt(CALL_STATUS));
                        bluetoothPhoneBuilder.setStatus(callStatus);
                    }
                    InteractionProto.BluetoothPhone bluetoothPhoneReq = bluetoothPhoneBuilder.build();
                } catch (Exception ex7) {
                    ex7.printStackTrace();
                    return null;
                }
                break;
            case 7:
                try {
                    InteractionProto.Navigation.Builder navigationBuilder = InteractionProto.Navigation.newBuilder();
                    if (bundle.containsKey(MAP_TYPE)) {
                        InteractionProto.MapType mapType2 = InteractionProto.MapType.valueOf(bundle.getInt(MAP_TYPE));
                        navigationBuilder.setMapType(mapType2);
                    }
                    if (bundle.containsKey(NEXI_TBT_ICON)) {
                        navigationBuilder.setNextNavigationInformationIcon(bundle.getInt(NEXI_TBT_ICON));
                    }
                    if (bundle.containsKey(NEXI_ROAD_NAME) && (nextRoadName2 = bundle.getString(NEXI_ROAD_NAME)) != null) {
                        navigationBuilder.setDestinationInfo(nextRoadName2);
                    }
                    if (bundle.containsKey(NEXI_ROAD_DIST)) {
                        float nextRoadDist = bundle.getFloat(NEXI_ROAD_DIST);
                        navigationBuilder.setTurnTipDigit(nextRoadDist);
                    }
                    if (bundle.containsKey(NEXI_DIST_UNIT)) {
                        InteractionProto.TopTipUnit nextDistUnit = InteractionProto.TopTipUnit.valueOf(bundle.getInt(NEXI_DIST_UNIT));
                        navigationBuilder.setTopTipUnit(nextDistUnit);
                    }
                    if (bundle.containsKey(NEXI_NEXT_TBT_ICON)) {
                        navigationBuilder.setTurnNavigationInformationIcon(bundle.getInt(NEXI_NEXT_TBT_ICON));
                    }
                    if (bundle.containsKey(NEXI_NEXT_ROAD_DIST)) {
                        float nextRoadDist2 = bundle.getFloat(NEXI_NEXT_ROAD_DIST);
                        navigationBuilder.setNextNextRoadDist(nextRoadDist2);
                    }
                    if (bundle.containsKey(NEXI_NEXT_DIST_UNIT)) {
                        InteractionProto.TopTipUnit nextNextDistUnit = InteractionProto.TopTipUnit.valueOf(bundle.getInt(NEXI_NEXT_DIST_UNIT));
                        navigationBuilder.setNextNextRoadUnit(nextNextDistUnit);
                    }
                    if (bundle.containsKey(DIST_TO_DEST)) {
                        navigationBuilder.setRemainingMilageValue(bundle.getFloat(DIST_TO_DEST));
                    }
                    if (bundle.containsKey(DIST_TO_DEST_UNIT)) {
                        navigationBuilder.setRemainingMilage(InteractionProto.TopTipUnit.valueOf(bundle.getInt(DIST_TO_DEST_UNIT)));
                    }
                    if (bundle.containsKey(TO_DEST_TIME)) {
                        navigationBuilder.setRemainingSecondValue(bundle.getInt(TO_DEST_TIME));
                    }
                    if (bundle.containsKey(TO_DEST_DAYS)) {
                        int toDestDays = bundle.getInt(TO_DEST_DAYS);
                        navigationBuilder.setArriveTimeDay(toDestDays);
                    }
                    if (bundle.containsKey(TO_DEST_HOURS)) {
                        int toDestHours = bundle.getInt(TO_DEST_HOURS);
                        navigationBuilder.setArriveTimeHour(toDestHours);
                    }
                    if (bundle.containsKey(TO_DEST_MINUTES)) {
                        int toDestMinutes = bundle.getInt(TO_DEST_MINUTES);
                        navigationBuilder.setArriveTimeMinute(toDestMinutes);
                    }
                    InteractionProto.Navigation navigationReq = navigationBuilder.build();
                } catch (Exception ex8) {
                    ex8.printStackTrace();
                    return null;
                }
                break;
            case 8:
                try {
                    InteractionProto.Language.Builder languageBuilder = InteractionProto.Language.newBuilder();
                    languageBuilder.setNCurrLang(bundle.getInt(LANGUAGE_SET));
                    InteractionProto.Language languageReq = languageBuilder.build();
                } catch (Exception ex9) {
                    ex9.printStackTrace();
                    return null;
                }
                break;
            case 9:
                try {
                    InteractionProto.ResetOperation.Builder resetOperationBuilder = InteractionProto.ResetOperation.newBuilder();
                    if (bundle.containsKey(RESET_OPERATION_VALUE)) {
                        int resetOperation = bundle.getInt(RESET_OPERATION_VALUE);
                        resetOperationBuilder.setNResetOper(resetOperation);
                        InteractionProto.ResetOperation resetOperationReq = resetOperationBuilder.build();
                        msg = new FdbusProtoBuilder(resetOperationReq);
                    }
                } catch (Exception ex10) {
                    ex10.printStackTrace();
                    return null;
                }
                break;
            case 10:
                try {
                    InteractionProto.ChimeSetting.Builder chimeSettingBuilder = InteractionProto.ChimeSetting.newBuilder();
                    if (bundle.containsKey(SOUND_TYPE)) {
                        InteractionProto.SoundType soundType = InteractionProto.SoundType.valueOf(bundle.getInt(SOUND_TYPE));
                        chimeSettingBuilder.setNCurrSoundType(soundType);
                        InteractionProto.ChimeSetting chimeSettingReq = chimeSettingBuilder.build();
                        msg = new FdbusProtoBuilder(chimeSettingReq);
                    }
                } catch (Exception ex11) {
                    ex11.printStackTrace();
                    return null;
                }
                break;
            case 11:
                try {
                    InteractionProto.Elevation.Builder elevationBuilder = InteractionProto.Elevation.newBuilder();
                    if (bundle.containsKey(ELEVATION_VALUE)) {
                        int elevationValue = bundle.getInt(ELEVATION_VALUE);
                        elevationBuilder.setElevationValue(elevationValue);
                    }
                    if (bundle.containsKey(ELEVATION_UNIT)) {
                        InteractionProto.ElevationUnit elevationUnit = InteractionProto.ElevationUnit.valueOf(bundle.getInt(ELEVATION_UNIT));
                        elevationBuilder.setElevationUnit(elevationUnit);
                    }
                    InteractionProto.Elevation elevationReq = elevationBuilder.build();
                } catch (Exception ex12) {
                    ex12.printStackTrace();
                    return null;
                }
                break;
            case 12:
                try {
                    InteractionProto.Slope.Builder slopeBuilder = InteractionProto.Slope.newBuilder();
                    if (bundle.containsKey(SLOPE_VALUE)) {
                        int slopeValue = bundle.getInt(SLOPE_VALUE);
                        slopeBuilder.setSlopeValue(slopeValue);
                    }
                    if (bundle.containsKey(SLOPE_UNIT)) {
                        InteractionProto.SlopeUnit slopeUnit = InteractionProto.SlopeUnit.valueOf(bundle.getInt(SLOPE_UNIT));
                        slopeBuilder.setSlopeUnit(slopeUnit);
                    }
                    InteractionProto.Slope slopeReq = slopeBuilder.build();
                } catch (Exception ex13) {
                    ex13.printStackTrace();
                    return null;
                }
                break;
            case 13:
                try {
                    InteractionProto.Query.Builder queryBuilder = InteractionProto.Query.newBuilder();
                    if (bundle.containsKey(QUERY_NUMBER)) {
                        InteractionProto.QueryType queryType = InteractionProto.QueryType.valueOf(bundle.getInt(QUERY_NUMBER));
                        queryBuilder.setQueryValue(queryType);
                    }
                    InteractionProto.Query queryReq = queryBuilder.build();
                } catch (Exception ex14) {
                    ex14.printStackTrace();
                    return null;
                }
                break;
            case 14:
                try {
                    InteractionProto.MapFocus.Builder mapFocusBuilder = InteractionProto.MapFocus.newBuilder();
                    if (bundle.containsKey(MAP_TYPE)) {
                        InteractionProto.MapType mapType3 = InteractionProto.MapType.valueOf(bundle.getInt(MAP_TYPE));
                        mapFocusBuilder.setNCurrMapType(mapType3);
                    }
                    InteractionProto.MapFocus mapFocusReq = mapFocusBuilder.build();
                } catch (Exception ex15) {
                    ex15.printStackTrace();
                    return null;
                }
                break;
            case 15:
                try {
                    InteractionProto.MapState.Builder mapStateBuilder = InteractionProto.MapState.newBuilder();
                    if (bundle.containsKey(MAP_TYPE)) {
                        InteractionProto.MapType mapType4 = InteractionProto.MapType.valueOf(bundle.getInt(MAP_TYPE));
                        mapStateBuilder.setMCurrMapType(mapType4);
                    }
                    if (bundle.containsKey(NAVI_POP_STATE)) {
                        InteractionProto.NavigationPopInfo navigationPopInfo = InteractionProto.NavigationPopInfo.valueOf(bundle.getInt(NAVI_POP_STATE));
                        mapStateBuilder.setNavigationPopInfo(navigationPopInfo);
                    }
                    InteractionProto.MapState mapStateReq = mapStateBuilder.build();
                } catch (Exception ex16) {
                    ex16.printStackTrace();
                    return null;
                }
                break;
            case 16:
                try {
                    InteractionProto.InstrumentTest.Builder instrumentTestBuilder = InteractionProto.InstrumentTest.newBuilder();
                    if (bundle.containsKey(OSD_CLUSTER_MODE)) {
                        InteractionProto.OSDVisible osdVisible = InteractionProto.OSDVisible.valueOf(bundle.getInt(OSD_CLUSTER_MODE));
                        instrumentTestBuilder.setOsdVisible(osdVisible);
                    }
                    if (bundle.containsKey(ADAS_SELF_LEARN_DISPLAY)) {
                        InteractionProto.AdasAutoLearnVisible adasAutoLearnVisible = InteractionProto.AdasAutoLearnVisible.valueOf(bundle.getInt(ADAS_SELF_LEARN_DISPLAY));
                        instrumentTestBuilder.setAdasAutoLearnVisible(adasAutoLearnVisible);
                    }
                    if (bundle.containsKey(ADAS_DEBUG_MODE)) {
                        InteractionProto.AdasDebugMode adasDebugMode = InteractionProto.AdasDebugMode.valueOf(bundle.getInt(ADAS_DEBUG_MODE));
                        instrumentTestBuilder.setAdasDebugMode(adasDebugMode);
                    }
                    InteractionProto.InstrumentTest instrumentTestReq = instrumentTestBuilder.build();
                } catch (Exception ex17) {
                    ex17.printStackTrace();
                    return null;
                }
                break;
            case 17:
                try {
                    InteractionProto.ScreenOrientationSetting.Builder screenOrientationSetting = InteractionProto.ScreenOrientationSetting.newBuilder();
                    if (bundle.containsKey(SCREEN_ORIENTATION_MODE)) {
                        InteractionProto.ScreenOrientationValue screenOrientationValue = InteractionProto.ScreenOrientationValue.valueOf(bundle.getInt(SCREEN_ORIENTATION_MODE));
                        screenOrientationSetting.setMCurrScreenOrientation(screenOrientationValue);
                    }
                    InteractionProto.ScreenOrientationSetting screenOrientationReq = screenOrientationSetting.build();
                } catch (Exception ex18) {
                    ex18.printStackTrace();
                    return null;
                }
                break;
            case 18:
                try {
                    InteractionProto.VersatileThemeSetting.Builder versatileThemeSetting = InteractionProto.VersatileThemeSetting.newBuilder();
                    if (bundle.containsKey(VERSATILE_THEME_MODE)) {
                        versatileThemeSetting.setMCurrVersatileTheme(bundle.getInt(VERSATILE_THEME_MODE));
                    }
                    InteractionProto.VersatileThemeSetting versatileThemeReq = versatileThemeSetting.build();
                } catch (Exception ex19) {
                    ex19.printStackTrace();
                    return null;
                }
                break;
            case 19:
                try {
                    InteractionProto.OverseasMapSupplier.Builder overseasMapSupplier = InteractionProto.OverseasMapSupplier.newBuilder();
                    if (bundle.containsKey(OVERSEAS_MAP_SUPPLIER)) {
                        overseasMapSupplier.setMOverseasMapSupplier(bundle.getInt(OVERSEAS_MAP_SUPPLIER));
                    }
                    InteractionProto.OverseasMapSupplier overseasMapSupplierReq = overseasMapSupplier.build();
                } catch (Exception ex20) {
                    ex20.printStackTrace();
                    return null;
                }
                break;
            case 20:
                try {
                    InteractionProto.NaviTbtInfo.Builder naviTbtInfoBuilder = InteractionProto.NaviTbtInfo.newBuilder();
                    if (bundle.containsKey(MAP_TYPE)) {
                        InteractionProto.MapType mapType5 = InteractionProto.MapType.valueOf(bundle.getInt(MAP_TYPE));
                        naviTbtInfoBuilder.setTbtMapType(mapType5);
                    }
                    if (bundle.containsKey(NEXI_TBT_ICON)) {
                        naviTbtInfoBuilder.setTbtNextNavigationInformationIcon(bundle.getInt(NEXI_TBT_ICON));
                    }
                    if (bundle.containsKey(NEXI_ROAD_NAME) && (nextRoadName = bundle.getString(NEXI_ROAD_NAME)) != null) {
                        naviTbtInfoBuilder.setTbtDestinationInfo(nextRoadName);
                    }
                    if (bundle.containsKey(NEXT_ROAD_DIST_DATA) && (nextRoadDistData2 = bundle.getString(NEXT_ROAD_DIST_DATA)) != null) {
                        naviTbtInfoBuilder.setTbtTurnTipDigit(nextRoadDistData2);
                    }
                    if (bundle.containsKey(NEXT_ROAD_DIST_UNIT) && (nextRoadDistUnit2 = bundle.getString(NEXT_ROAD_DIST_UNIT)) != null) {
                        naviTbtInfoBuilder.setTbtTurnTipUnit(nextRoadDistUnit2);
                    }
                    if (bundle.containsKey(NEXI_NEXT_TBT_ICON)) {
                        naviTbtInfoBuilder.setTbtTurnNavigationInformationIcon(bundle.getInt(NEXI_NEXT_TBT_ICON));
                    }
                    if (bundle.containsKey(NEXT_NEXT_ROAD_DIST_DATA) && (nextRoadDistData = bundle.getString(NEXT_NEXT_ROAD_DIST_DATA)) != null) {
                        naviTbtInfoBuilder.setTbtNextNextRoadDist(nextRoadDistData);
                    }
                    if (bundle.containsKey(NEXT_NEXT_ROAD_DIST_UNIT) && (nextRoadDistUnit = bundle.getString(NEXT_NEXT_ROAD_DIST_UNIT)) != null) {
                        naviTbtInfoBuilder.setTbtNextNextRoadDistUnit(nextRoadDistUnit);
                    }
                    if (bundle.containsKey(DIST_TO_DEST_DIS_DATA) && (distToDest = bundle.getString(DIST_TO_DEST_DIS_DATA)) != null) {
                        naviTbtInfoBuilder.setTbtRemainingMilageValue(distToDest);
                    }
                    if (bundle.containsKey(DIST_TO_DEST_DIS_UNIT) && (distToDestUnit = bundle.getString(DIST_TO_DEST_DIS_UNIT)) != null) {
                        naviTbtInfoBuilder.setTbtRemainingMilageUnit(distToDestUnit);
                    }
                    if (bundle.containsKey(DIST_TO_DEST_TIME) && (distToDestTime = bundle.getString(DIST_TO_DEST_TIME)) != null) {
                        naviTbtInfoBuilder.setTbtRemainingSecondValue(distToDestTime);
                    }
                    if (bundle.containsKey(ARRIVAL_DEST_TIME_DAY) && (toDestTimeDay = bundle.getString(ARRIVAL_DEST_TIME_DAY)) != null) {
                        naviTbtInfoBuilder.setTbtArriveTimeDay(toDestTimeDay);
                    }
                    if (bundle.containsKey(ARRIVAL_DEST_TIME) && (toDestTime = bundle.getString(ARRIVAL_DEST_TIME)) != null) {
                        naviTbtInfoBuilder.setTbtArriveTime(toDestTime);
                    }
                    if (bundle.containsKey(MULTIPLE_TEXT) && (multipleText = bundle.getString(MULTIPLE_TEXT)) != null) {
                        naviTbtInfoBuilder.setTbtMultipleText(multipleText);
                    }
                    if (bundle.containsKey(MULTIPLE_TEXT_ONE) && (multipleText1 = bundle.getString(MULTIPLE_TEXT_ONE)) != null) {
                        naviTbtInfoBuilder.setTbtMultipleTextOne(multipleText1);
                    }
                    if (bundle.containsKey(MULTIPLE_TEXT_TWO) && (multipleText2 = bundle.getString(MULTIPLE_TEXT_TWO)) != null) {
                        naviTbtInfoBuilder.setTbtMultipleTextTwo(multipleText2);
                    }
                    InteractionProto.NaviTbtInfo navigationReq2 = naviTbtInfoBuilder.build();
                } catch (Exception ex21) {
                    ex21.printStackTrace();
                    return null;
                }
                break;
            case 21:
                try {
                    InteractionProto.NaviInstallState.Builder naviInstallStateBuilder = InteractionProto.NaviInstallState.newBuilder();
                    if (bundle.containsKey(NAVI_INSTALL_STATE)) {
                        naviInstallStateBuilder.setMNaviInstallState(bundle.getInt(NAVI_INSTALL_STATE));
                    }
                    InteractionProto.NaviInstallState naviInstallStateReq = naviInstallStateBuilder.build();
                } catch (Exception ex22) {
                    ex22.printStackTrace();
                    return null;
                }
                break;
            case 22:
                try {
                    InteractionProto.CpaaConnectState.Builder cpaaConnectStateBuilder = InteractionProto.CpaaConnectState.newBuilder();
                    if (bundle.containsKey(CPAA_CONNECT_STATE)) {
                        cpaaConnectStateBuilder.setMCpaaConnectState(bundle.getInt(CPAA_CONNECT_STATE));
                    }
                    InteractionProto.CpaaConnectState cpaaConnectStateReq = cpaaConnectStateBuilder.build();
                } catch (Exception ex23) {
                    ex23.printStackTrace();
                    return null;
                }
                break;
        }
        return null;
    }

    private boolean checkCurrNaviType(int value) {
        if (value == 1 || value == 2 || value == 3 || value == 4) {
            LogUtil.logD(TAG, "checkCurrNaviType value true");
            return true;
        }
        LogUtil.logD(TAG, "checkCurrNaviType value false");
        return false;
    }
}
