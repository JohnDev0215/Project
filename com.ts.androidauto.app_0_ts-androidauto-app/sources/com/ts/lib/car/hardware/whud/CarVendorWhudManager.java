package com.ts.lib.car.hardware.whud;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes2.dex */
public interface CarVendorWhudManager {
    public static final int ID_ACC_CRUISING_LAMPR = 557872393;
    public static final int ID_ACC_CRUISING_LAMP_COLORR = 557872394;
    public static final int ID_ACC_CRUISING_SPEED_VALUER = 557872391;
    public static final int ID_AHEAD_VEHICLE_SPACER = 557872386;
    public static final int ID_ARRIVAL_DAY_ESTIMATEDS = 557872416;
    public static final int ID_ARRIVAL_HOUR_ESTIMATEDS = 557872417;
    public static final int ID_ARRIVAL_MINUTE_ESTIMATEDS = 557872418;
    public static final int ID_ARRIVAL_SECOND_ESTIMATEDS = 557872419;
    public static final int ID_CALL_NUMBER_LEN_S = 557872425;
    public static final int ID_CALL_NUMBER_S = 561018154;
    public static final int ID_CALL_STATES = 557872402;
    public static final int ID_CALL_TIME_HOURS = 557872398;
    public static final int ID_CALL_TIME_MINUTES = 557872399;
    public static final int ID_CALL_TIME_SECONDS = 557872400;
    public static final int ID_CURRENT_DAY_UNIT1_S = 561018186;
    public static final int ID_CURRENT_DAY_UNIT2_S = 561018187;
    public static final int ID_CURRENT_DAY_UNIT3_S = 561018188;
    public static final int ID_CURRENT_DAY_UNIT4_S = 561018189;
    public static final int ID_CURRENT_DAY_UNIT5_S = 561018190;
    public static final int ID_CURRENT_DAY_UNIT6_S = 561018191;
    public static final int ID_CURRENT_DAY_UNIT7_S = 561018192;
    public static final int ID_CURRENT_DAY_UNIT8_S = 561018193;
    public static final int ID_CURRENT_DAY_UNIT_LEN_S = 558986568;
    public static final int ID_CURRENT_HOUR_UNIT1_S = 561018196;
    public static final int ID_CURRENT_HOUR_UNIT2_S = 561018197;
    public static final int ID_CURRENT_HOUR_UNIT3_S = 561018198;
    public static final int ID_CURRENT_HOUR_UNIT4_S = 561018199;
    public static final int ID_CURRENT_HOUR_UNIT5_S = 561018200;
    public static final int ID_CURRENT_HOUR_UNIT6_S = 561018201;
    public static final int ID_CURRENT_HOUR_UNIT7_S = 561018202;
    public static final int ID_CURRENT_HOUR_UNIT8_S = 561018203;
    public static final int ID_CURRENT_HOUR_UNIT_LEN_S = 558986578;
    public static final int ID_CURRENT_MIN_UNIT1_S = 561018206;
    public static final int ID_CURRENT_MIN_UNIT2_S = 561018207;
    public static final int ID_CURRENT_MIN_UNIT3_S = 561018208;
    public static final int ID_CURRENT_MIN_UNIT4_S = 561018209;
    public static final int ID_CURRENT_MIN_UNIT5_S = 561018210;
    public static final int ID_CURRENT_MIN_UNIT6_S = 561018211;
    public static final int ID_CURRENT_MIN_UNIT7_S = 561018212;
    public static final int ID_CURRENT_MIN_UNIT8_S = 561018213;
    public static final int ID_CURRENT_MIN_UNIT_LEN_S = 558986588;
    public static final int ID_DISTANCE_AHEADS = 557872404;
    public static final int ID_DISTANCE_AHEAD_UNITS = 557872405;
    public static final int ID_DISTANCE_FRONT_TARGETS = 557872414;
    public static final int ID_DISTANCE_FRONT_TARGET_UNITS = 557872415;
    public static final int ID_DRIVE_REMAINING_DAYSS = 557872409;
    public static final int ID_DRIVE_REMAINING_HOURS = 557872406;
    public static final int ID_DRIVE_REMAINING_HOUR_FORMATS = 557872412;
    public static final int ID_DRIVE_REMAINING_MILEAGES = 558920986;
    public static final int ID_DRIVE_REMAINING_MINUTES = 557872407;
    public static final int ID_DRIVE_REMAINING_SECONDS = 557872408;
    public static final int ID_DRIVE_REMAINING_UNITS = 557872411;
    public static final int ID_EASY_NAV_GUIDE_INFO_S = 558986545;
    public static final int ID_LEFT_LINER = 557872389;
    public static final int ID_MEDIA_SOURCES = 557872401;
    public static final int ID_MUSIC_CURRENT_NAME_LEN_S = 557872423;
    public static final int ID_MUSIC_CURRENT_NAME_S = 561018152;
    public static final int ID_MUSIC_PLAY_PROGRESSS = 557872397;
    public static final int ID_MUSIC_PLAY_STATES = 557872395;
    public static final int ID_NAME_NEXT_ROADS = 557872420;
    public static final int ID_NAME_NEXT_ROAD_UNITCODE = 561018150;
    public static final int ID_NAVI_STATE_S = 557872432;
    public static final int ID_NAVI_TYPES = 557872421;
    public static final int ID_NAV_GUIDE_INFOS = 557872403;
    public static final int ID_NAV_GUIDE_INFO_ADVANCEDS = 557872413;
    public static final int ID_NEW_NAME_NEXT_LEN_AND_ROAD_S = 558986546;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA1 = 561018163;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA2 = 561018164;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA3 = 561018165;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA4 = 561018166;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA5 = 561018167;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA6 = 561018168;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA7 = 561018169;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA8 = 561018176;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SA9 = 561018177;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SAA = 561018178;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SAB = 561018179;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SAC = 561018180;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SAD = 561018181;
    public static final int ID_NEW_NAME_NEXT_ROAD_NAME_SAE = 561018182;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA1_S = 561018226;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA2_S = 561018227;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA3_S = 561018228;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA4_S = 561018229;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA5_S = 561018230;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA6_S = 561018231;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA7_S = 561018232;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA8_S = 561018233;
    public static final int ID_NEXT_ROAD_DISTANCE_UNIT_DATA_LEN_S = 558986608;
    public static final int ID_OVERSEAS_MAP_SUPPLIER_S = 557872455;
    public static final int ID_RADIO_PLAY_STATES = 557872396;
    public static final int ID_RIGHT_LINER = 557872390;
    public static final int ID_SCREEN_DISPLAY_STATE_S = 557872456;
    public static final int ID_SCREEN_DISPLAY_STATE_WHUD_S = 557872506;
    public static final int ID_SHOW_ADAS_WORKR = 557872384;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA1_S = 561018216;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA2_S = 561018217;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA3_S = 561018218;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA4_S = 561018219;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA5_S = 561018220;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA6_S = 561018221;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA7_S = 561018222;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA8_S = 561018223;
    public static final int ID_TARGET_DISTANCE_UNIT_DATA_LEN_S = 558986598;
    public static final int ID_TEXT_HINTR = 557872387;
    public static final int ID_TEXT_MESSAGE_HINTR = 557875227;
    public static final int ID_TRAFFIC_SIGN_RECOGNITION_VALUER = 557872392;
    public static final int ID_VEHICLE_BODY_POSITIONR = 557872388;
    public static final int ID_VEHICLE_SPACER = 557872385;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.whud";
    public static final String TAG = "CAR.VENDOR.WHUD";

    public interface OnCarWhudEventListener {
        void onWhudErrorEvent(int i, int i2);

        void onWhudEventChange(WhudEventChangeValue whudEventChangeValue);
    }

    void addCarWhudEventListener(OnCarWhudEventListener onCarWhudEventListener) throws CarNotConnectedException;

    int getAccCruisingLampColorR() throws CarNotConnectedException;

    int getAccCruisingLampR() throws CarNotConnectedException;

    int getAccCruisingSpeedValueR() throws CarNotConnectedException;

    int getAheadVehicleSpaceR() throws CarNotConnectedException;

    int getLeftLineR() throws CarNotConnectedException;

    int getRightLineR() throws CarNotConnectedException;

    int getShowAdasWorkR() throws CarNotConnectedException;

    int getTextHintR() throws CarNotConnectedException;

    int getTextMessageHintR() throws CarNotConnectedException;

    int getTrafficSignRecognitionValueR() throws CarNotConnectedException;

    int getVehicleBodyPositionR() throws CarNotConnectedException;

    int getVehicleSpaceR() throws CarNotConnectedException;

    void removeCarWhudEventListener(OnCarWhudEventListener onCarWhudEventListener) throws CarNotConnectedException;

    void setArrivalDayEstimatedS(int i) throws CarNotConnectedException;

    void setArrivalHourEstimatedS(int i) throws CarNotConnectedException;

    void setArrivalMinuteEstimatedS(int i) throws CarNotConnectedException;

    void setArrivalSecondEstimatedS(int i) throws CarNotConnectedException;

    void setCallNumberLenS(int i) throws CarNotConnectedException;

    void setCallNumberS(byte[] bArr) throws CarNotConnectedException;

    void setCallStateS(int i) throws CarNotConnectedException;

    void setCallTimeHourS(int i) throws CarNotConnectedException;

    void setCallTimeMinuteS(int i) throws CarNotConnectedException;

    void setCallTimeSecondS(int i) throws CarNotConnectedException;

    void setCurrentDayUnit1S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnit2S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnit3S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnit4S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnit5S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnit6S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnit7S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnit8S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentDayUnitLenS(long[] jArr) throws CarNotConnectedException;

    void setCurrentHourUnit1S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnit2S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnit3S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnit4S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnit5S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnit6S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnit7S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnit8S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentHourUnitLenS(long[] jArr) throws CarNotConnectedException;

    void setCurrentMinUnit1S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnit2S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnit3S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnit4S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnit5S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnit6S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnit7S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnit8S(byte[] bArr) throws CarNotConnectedException;

    void setCurrentMinUnitLenS(long[] jArr) throws CarNotConnectedException;

    void setDistanceAheadS(int i) throws CarNotConnectedException;

    void setDistanceAheadUnitS(int i) throws CarNotConnectedException;

    void setDistanceFrontTargetS(int i) throws CarNotConnectedException;

    void setDistanceFrontTargetUnitS(int i) throws CarNotConnectedException;

    void setDriveRemainingDaysS(int i) throws CarNotConnectedException;

    void setDriveRemainingHourFormatS(int i) throws CarNotConnectedException;

    void setDriveRemainingHourS(int i) throws CarNotConnectedException;

    void setDriveRemainingMileageS(long j) throws CarNotConnectedException;

    void setDriveRemainingMinuteS(int i) throws CarNotConnectedException;

    void setDriveRemainingSecondS(int i) throws CarNotConnectedException;

    void setDriveRemainingUnitS(int i) throws CarNotConnectedException;

    void setEasyNavGuideInfoS(long[] jArr) throws CarNotConnectedException;

    void setMediaSourceS(int i) throws CarNotConnectedException;

    void setMusicCurrentNameLenS(int i) throws CarNotConnectedException;

    void setMusicCurrentNameS(byte[] bArr) throws CarNotConnectedException;

    void setMusicPlayProgressS(int i) throws CarNotConnectedException;

    void setMusicPlayStateS(int i) throws CarNotConnectedException;

    void setNameNextRoadS(int i) throws CarNotConnectedException;

    void setNameNextRoadUnitcode(byte[] bArr) throws CarNotConnectedException;

    void setNavGuideInfoAdvancedS(int i) throws CarNotConnectedException;

    void setNavGuideInfoS(int i) throws CarNotConnectedException;

    void setNaviStateS(int i) throws CarNotConnectedException;

    void setNaviTypeS(int i) throws CarNotConnectedException;

    void setNewNameNextLenAndRoadS(long[] jArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa1(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa2(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa3(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa4(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa5(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa6(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa7(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa8(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSa9(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSaA(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSaB(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSaC(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSaD(byte[] bArr) throws CarNotConnectedException;

    void setNewNameNextRoadNameSaE(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData1S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData2S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData3S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData4S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData5S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData6S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData7S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitData8S(byte[] bArr) throws CarNotConnectedException;

    void setNextRoadDistanceUnitDataLenS(long[] jArr) throws CarNotConnectedException;

    void setOverseasMapSupplierS(int i) throws CarNotConnectedException;

    void setRadioPlayStateS(int i) throws CarNotConnectedException;

    void setScreenDisplayStateS(int i) throws CarNotConnectedException;

    void setTargetDistanceUnitData1S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitData2S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitData3S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitData4S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitData5S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitData6S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitData7S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitData8S(byte[] bArr) throws CarNotConnectedException;

    void setTargetDistanceUnitDataLenS(long[] jArr) throws CarNotConnectedException;
}
