package com.ts.lib.car.hardware.light;

import com.ts.lib.car.CarNotConnectedException;

/* JADX INFO: loaded from: classes3.dex */
public interface CarVendorLightManager {
    public static final int ID_AFS_SWITCH = 557860610;
    public static final int ID_AFS_SWITCH_SATE = 557860626;
    public static final int ID_ALL_WEATHER_LIGHT_SWITCH_STATE_R = 557857169;
    public static final int ID_ALL_WEATHER_LIGHT_SWITCH_STATE_S = 557860691;
    public static final int ID_AMBIENT_COLORS_SUPPORT = 557856188;
    public static final int ID_AMBIENT_FEED_BACK = 557860631;
    public static final int ID_AMBIENT_FEED_BACKR = 557860635;
    public static final int ID_AMBIENT_MULTI_COLOR_MODE_R = 557860617;
    public static final int ID_AMBIENT_MULTI_COLOR_MODE_S = 557860618;
    public static final int ID_AMBIENT_MULTI_COLOR_STATE_R = 557860615;
    public static final int ID_AMBIENT_MULTI_COLOR_STATE_S = 557860616;
    public static final int ID_AMBIENT_MUSIC_MODE_R = 557860613;
    public static final int ID_AMBIENT_MUSIC_MODE_S = 557860614;
    public static final int ID_AMBIENT_MUSIC_MODE_STATE_R = 557860611;
    public static final int ID_AMBIENT_MUSIC_MODE_STATE_S = 557860612;
    public static final int ID_AMBIENT_STATE_R = 557860697;
    public static final int ID_AMBIENT_STATE_S = 557860696;
    public static final int ID_AMBIENT_SWITCH_CONFIG_R = 557860698;
    public static final int ID_ATMOSPERE_LAMP_POPUP_PROMPT_R = 557860706;
    public static final int ID_ATMOSPERE_LAMP_UI_DISPLAY_R = 557860709;
    public static final int ID_ATMOSPERE_LIGHT_NIGHT_WEAKEN_MODE_CONFIG_R = 557860703;
    public static final int ID_ATMOSPERE_LIGHT_NIGHT_WEAKEN_MODE_R = 557860704;
    public static final int ID_ATMOSPERE_LIGHT_NIGHT_WEAKEN_MODE_S = 557860705;
    public static final int ID_ATMOSPHERE_LAMP_BODY_DISPLAY_R = 557860702;
    public static final int ID_BACKLIGHT_LEVEL_R = 557860648;
    public static final int ID_CARGO_LIGHT_SWITCH_CONFIG_R = 557860666;
    public static final int ID_CARGO_LIGHT_SWITCH_STATE_R = 557860665;
    public static final int ID_CARGO_LIGHT_SWITCH_STATE_S = 557860667;
    public static final int ID_DAY_LIGHT_SUPPORT = 557856041;
    public static final int ID_DAY_TIME_LIGHT_STATE_R = 557860619;
    public static final int ID_DAY_TIME_LIGHT_STATE_S = 557860620;
    public static final int ID_DAY_TIME_RUNNING_LIGHT_STATE_R = 557860643;
    public static final int ID_DOOR_LIGHT_STATE_S = 557860622;
    public static final int ID_FRONT_CENTER_LIGHT_STATUS_R = 557860671;
    public static final int ID_FRONT_FOG_LAMP_STATUS_R = 557860670;
    public static final int ID_FRONT_FOG_LIGHT_CONFIG_R = 557860689;
    public static final int ID_FRONT_FOG_LIGHT_SWITCH_STATE_R = 557860688;
    public static final int ID_FRONT_FOG_LIGHT_SWITCH_STATE_S = 557860692;
    public static final int ID_HEADLIGHT_CONTROL_MODE_R = 557857171;
    public static final int ID_HEADLIGHT_CONTROL_MODE_S = 557857170;
    public static final int ID_HIGHLIGHT_POSITION_S = 557860676;
    public static final int ID_HIGHTLIGHT_POSITION_CONFIG_R = 557860678;
    public static final int ID_HIGHTLIGHT_POSITION_R = 557860677;
    public static final int ID_IAL_AREA_DIFFERENT_WAYS = 557857722;
    public static final int ID_IAL_BRIGHTNESS_BACK_R = 557857718;
    public static final int ID_IAL_BRIGHTNESS_FRONT_R = 557857719;
    public static final int ID_IAL_BRIGHTNESS_S = 557857720;
    public static final int ID_IAL_BRIGHTNESS_STARRY_SKY_R = 557857717;
    public static final int ID_IAL_BRIGHTNESS_STAR_RING_R = 557857716;
    public static final int ID_IAL_COLOR_BACK_R = 557857713;
    public static final int ID_IAL_COLOR_FRONT_R = 557857712;
    public static final int ID_IAL_COLOR_S = 557857714;
    public static final int ID_IAL_COLOR_STARRY_SKY_R = 557857711;
    public static final int ID_IAL_COLOR_STAR_RING_R = 557857710;
    public static final int ID_INTELLIGENT_POSTION_FEEDBACK_R = 557860679;
    public static final int ID_KTV_MODE_BRIGHTNESS_S = 557844261;
    public static final int ID_KTV_MODE_COLOR_S = 557860646;
    public static final int ID_LAST_SETED_AREA = 557857721;
    public static final int ID_LEFT_CORNER_LIGHT_STATUS_R = 557860668;
    public static final int ID_LEFT_DAYTIME_LIGHT_STATE_R = 557860675;
    public static final int ID_LEFT_DAYTIME_RUN_LAMP_STATE_R = 557860682;
    public static final int ID_LEFT_FRONT_POSITION_S_W_L_STATUS_R = 557860680;
    public static final int ID_LEFT_FRONT_READING_LAMP_STATUS_R = 557860657;
    public static final int ID_LEFT_FRONT_READING_LAMP_SWITCH_STATE_S = 557860649;
    public static final int ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_R = 557860661;
    public static final int ID_LEFT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S = 557860653;
    public static final int ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_R = 557860659;
    public static final int ID_LEFT_MIDDLE_ROW_SIDE_R_LIGHT_SWITCH_STATE_S = 557860651;
    public static final int ID_LEFT_REAR_R_LIGHT_SWITCH_R = 557860663;
    public static final int ID_LEFT_REAR_R_LIGHT_SWITCH_STATE_S = 557860655;
    public static final int ID_LEFT_REMOTE_LIGHT_STATE_R = 557860693;
    public static final int ID_LIGHT_ADAS_INDICATOR_STATUS_R = 557860700;
    public static final int ID_LIGHT_AUTO_STATUS_ONE = 557860608;
    public static final int ID_LIGHT_AUTO_STATUS_TWO = 557860609;
    public static final int ID_LIGHT_FRONT_GLOW_LOGO_WORK_STATE_R = 557860701;
    public static final int ID_LIGHT_HIGH_BEAMR = 557860638;
    public static final int ID_LIGHT_LOW_BEAM_STATUSR = 557860637;
    public static final int ID_LIGHT_REAR_FOGR = 557860642;
    public static final int ID_LIGHT_REAR_GLOW_LOGO_WORK_STATE_R = 557860699;
    public static final int ID_LIGHT_REAR_POSITIONR = 557860644;
    public static final int ID_LIGHT_SIDE_STATUSR = 557879809;
    public static final int ID_MIDDLE_STERN_LAMP_STATE_R = 557860695;
    public static final int ID_ON_SCENE_MODE_S = 557860647;
    public static final int ID_POSITION_LIGHT_DISPLAY_FEED_CALLBACK_R = 557860672;
    public static final int ID_POSITION_LIGHT_DISPLAY_S = 557860673;
    public static final int ID_REAR_FOG_LIGHT_SWITCH_STATE_R = 557857172;
    public static final int ID_REAR_FOG_LIGHT_SWITCH_STATE_S = 557857173;
    public static final int ID_RESET_DELAY_LIGHT_PARAMS = 557860627;
    public static final int ID_REVERSING_LIGHT_STATE = 557860629;
    public static final int ID_REVERSING_LIGHT_STATER = 557860633;
    public static final int ID_RIGHT_CORNER_LIGHT_STATUS_R = 557860669;
    public static final int ID_RIGHT_DAYTIME_LIGHT_STATE_R = 557860674;
    public static final int ID_RIGHT_DAYTIME_RUN_LAMP_STATE_R = 557860683;
    public static final int ID_RIGHT_FRONT_POSITION_S_W_L_STATUS_R = 557860681;
    public static final int ID_RIGHT_FRONT_READING_LAMP_STATUS_R = 557860658;
    public static final int ID_RIGHT_FRONT_READING_LAMP_SWITCH_STATE_S = 557860650;
    public static final int ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_R = 557860662;
    public static final int ID_RIGHT_MIDDLE_ROW_R_LIGHT_ROTARY_STATE_S = 557860654;
    public static final int ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_R = 557860660;
    public static final int ID_RIGHT_MIDDLE_ROW_R_LIGHT_SWITCH_STATE_S = 557860652;
    public static final int ID_RIGHT_REAR_R_LIGHT_SWITCH_R = 557860664;
    public static final int ID_RIGHT_REAR_R_LIGHT_SWITCH_STATE_S = 557860656;
    public static final int ID_RIGHT_REMOTE_LIGHT_STATE_R = 557860694;
    public static final int ID_STOP_LIGHT_STATE = 557870867;
    public static final int ID_THEME_COLOR_FEATURE_FLAG_R = 557860712;
    public static final int ID_THEME_COLOR_FEED_BACK_R = 557860710;
    public static final int ID_THEME_COLOR_SWITCH_STATE_R = 557860707;
    public static final int ID_THEME_LINKED_AMBIENCE_LIGHT_COLOR_S = 557860708;
    public static final int ID_THEME_LINKED_ATMOSPHERE_LAMP_SWITCH_S = 557860711;
    public static final int ID_TURN_LIGHT_FLASH_STATE_R = 557860624;
    public static final int ID_TURN_LIGHT_FLASH_STATE_S = 557860625;
    public static final int ID_TURN_LIGHT_STATE = 557860630;
    public static final int ID_TURN_LIGHT_STATER = 557860634;
    public static final String PERSIST_DEBUG_LOGGER = "persist.car.vendor.log.light";
    public static final String TAG = "CAR.VENDOR.LIGHT";

    public interface OnCarLightEventListener {
        void onLightErrorEvent(int i, int i2);

        void onLightEventChange(LightEventChangeValue lightEventChangeValue);
    }

    void addCarLightEventListener(OnCarLightEventListener onCarLightEventListener) throws CarNotConnectedException;

    int getAFSSwitch() throws CarNotConnectedException;

    int getAllWeatherLightSwitchStateR() throws CarNotConnectedException;

    int getAmbientColorsSupport() throws CarNotConnectedException;

    int getAmbientFeedBack() throws CarNotConnectedException;

    int getAmbientFeedBackR() throws CarNotConnectedException;

    int getAmbientMulticolorModeR() throws CarNotConnectedException;

    int getAmbientMulticolorStateR() throws CarNotConnectedException;

    int getAmbientMusicModeR() throws CarNotConnectedException;

    int getAmbientMusicModeStateR() throws CarNotConnectedException;

    int getAmbientStateR() throws CarNotConnectedException;

    int getAmbientSwitchConfigR() throws CarNotConnectedException;

    int getAtmospereLampPopupPromptR() throws CarNotConnectedException;

    int getAtmospereLampUiDisplayR() throws CarNotConnectedException;

    int getAtmospereLightNightWeakenModeConfigR() throws CarNotConnectedException;

    int getAtmospereLightNightWeakenModeR() throws CarNotConnectedException;

    int getAtmosphereLampBodyDisplayR() throws CarNotConnectedException;

    int getBacklightLevelR() throws CarNotConnectedException;

    int getCargoLightSwitchConfigR() throws CarNotConnectedException;

    int getCargoLightSwitchStateR() throws CarNotConnectedException;

    int getDayLightSupport() throws CarNotConnectedException;

    int getDayTimeLightStateR() throws CarNotConnectedException;

    int getDayTimeRunningLightStateR() throws CarNotConnectedException;

    int getFrontCenterLightStatusR() throws CarNotConnectedException;

    int getFrontFogLampStatusR() throws CarNotConnectedException;

    int getFrontFogLightConfigR() throws CarNotConnectedException;

    int getFrontFogLightSwitchStateR() throws CarNotConnectedException;

    int getHeadlightControlModeR() throws CarNotConnectedException;

    int getHightlightPositionConfigR() throws CarNotConnectedException;

    int getHightlightPositionR() throws CarNotConnectedException;

    int getIalBrightnessBackR() throws CarNotConnectedException;

    int getIalBrightnessFrontR() throws CarNotConnectedException;

    int getIalBrightnessStarRingR() throws CarNotConnectedException;

    int getIalBrightnessStarrySkyR() throws CarNotConnectedException;

    int getIalColorBackR() throws CarNotConnectedException;

    int getIalColorFrontR() throws CarNotConnectedException;

    int getIalColorStarRingR() throws CarNotConnectedException;

    int getIalColorStarrySkyR() throws CarNotConnectedException;

    int getIntelligentPostionFeedbackR() throws CarNotConnectedException;

    int getLastSetedArea() throws CarNotConnectedException;

    int getLeftCornerLightStatusR() throws CarNotConnectedException;

    int getLeftDaytimeLightStateR() throws CarNotConnectedException;

    int getLeftDaytimeRunLampStateR() throws CarNotConnectedException;

    int getLeftFrontPositionSwlStatusR() throws CarNotConnectedException;

    int getLeftFrontReadingLampStatusR() throws CarNotConnectedException;

    int getLeftMiddleRowRLightRotaryStateR() throws CarNotConnectedException;

    int getLeftMiddleRowSideRLightSwitchR() throws CarNotConnectedException;

    int getLeftRearRLightSwitchR() throws CarNotConnectedException;

    int getLeftRemoteLightStateR() throws CarNotConnectedException;

    int getLightAdasIndicatorStatusR() throws CarNotConnectedException;

    int getLightAutoStatusOne() throws CarNotConnectedException;

    int getLightAutoStatusTwo() throws CarNotConnectedException;

    int getLightFrontGlowLogoWorkStateR() throws CarNotConnectedException;

    int getLightHighBeamR() throws CarNotConnectedException;

    int getLightLowBeamStatusR() throws CarNotConnectedException;

    int getLightRearFog() throws CarNotConnectedException;

    int getLightRearGlowLogoWorkStateR() throws CarNotConnectedException;

    int getLightRearPosition() throws CarNotConnectedException;

    int getLightSideStatusR() throws CarNotConnectedException;

    int getMiddleSternLampStateR() throws CarNotConnectedException;

    int getPositionLightDisplayFeedCallbackR() throws CarNotConnectedException;

    int getRearFogLightSwitchStateR() throws CarNotConnectedException;

    int getReversingLightState() throws CarNotConnectedException;

    int getReversingLightStateR() throws CarNotConnectedException;

    int getRightCornerLightStatusR() throws CarNotConnectedException;

    int getRightDaytimeLightStateR() throws CarNotConnectedException;

    int getRightDaytimeRunLampStateR() throws CarNotConnectedException;

    int getRightFrontPositionSWLStatusR() throws CarNotConnectedException;

    int getRightFrontReadingLampStatusR() throws CarNotConnectedException;

    int getRightMiddleRowRLightRotaryR() throws CarNotConnectedException;

    int getRightMiddleRowRLightSwitchR() throws CarNotConnectedException;

    int getRightRearRLightSwitchR() throws CarNotConnectedException;

    int getRightRemoteLightStateR() throws CarNotConnectedException;

    int getStopLightStateR() throws CarNotConnectedException;

    int getThemeColorFeatureFlagR() throws CarNotConnectedException;

    int getThemeColorFeedBackR() throws CarNotConnectedException;

    int getThemeColorSwitchStateR() throws CarNotConnectedException;

    int getTurnLightFlashStateR() throws CarNotConnectedException;

    int getTurnLightState() throws CarNotConnectedException;

    int getTurnLightStateR() throws CarNotConnectedException;

    void removeCarLightEventListener(OnCarLightEventListener onCarLightEventListener) throws CarNotConnectedException;

    void setAFSSwitchSate(int i) throws CarNotConnectedException;

    void setAllWeatherLightSwitchStateS(int i) throws CarNotConnectedException;

    void setAmbientMulticolorModeS(int i) throws CarNotConnectedException;

    void setAmbientMulticolorStateS(int i) throws CarNotConnectedException;

    void setAmbientMusicModeS(int i) throws CarNotConnectedException;

    void setAmbientMusicModeStateS(int i) throws CarNotConnectedException;

    void setAmbientStateS(int i) throws CarNotConnectedException;

    void setAtmospereLightNightWeakenModeS(int i) throws CarNotConnectedException;

    void setCargoLightSwitchStateS(int i) throws CarNotConnectedException;

    void setDayTimeLightStateS(int i) throws CarNotConnectedException;

    void setDoorLightStateS(int i) throws CarNotConnectedException;

    void setFrontFogLightSwitchStateS(int i) throws CarNotConnectedException;

    void setHeadlightControlModeS(int i) throws CarNotConnectedException;

    void setHighlightPositionS(int i) throws CarNotConnectedException;

    void setIalAreaDifferentWays(int i) throws CarNotConnectedException;

    void setIalBrightnessS(int i) throws CarNotConnectedException;

    void setIalColorS(int i) throws CarNotConnectedException;

    void setKtvModeBrightnessS(int i) throws CarNotConnectedException;

    void setKtvModeColorS(int i) throws CarNotConnectedException;

    void setLeftFrontReadingLampSwitchStateS(int i) throws CarNotConnectedException;

    void setLeftMiddleRowRLightRotaryStateS(int i) throws CarNotConnectedException;

    void setLeftMiddleRowSideRLightSwitchStateS(int i) throws CarNotConnectedException;

    void setLeftRearRLightSwitchStateS(int i) throws CarNotConnectedException;

    void setOnSceneModeS(int i) throws CarNotConnectedException;

    void setPositionLightDisplayS(int i) throws CarNotConnectedException;

    void setRearFogLightSwitchStateS(int i) throws CarNotConnectedException;

    void setResetDelayLightParamS(int i) throws CarNotConnectedException;

    void setRightFrontReadingLampSwitchStateS(int i) throws CarNotConnectedException;

    void setRightMiddleRowRLightRotaryStateS(int i) throws CarNotConnectedException;

    void setRightMiddleRowRLightSwitchStateS(int i) throws CarNotConnectedException;

    void setRightRearRLightSwitchStateS(int i) throws CarNotConnectedException;

    void setThemeLinkedAmbienceLightColorS(int i) throws CarNotConnectedException;

    void setThemeLinkedAtmosphereLampSwitchS(int i) throws CarNotConnectedException;

    void setTurnLightFlashStateS(int i) throws CarNotConnectedException;
}
