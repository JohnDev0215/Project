package com.autolink.interaction;

import com.autolink.ota.OtaProto;
import com.bosch.cm.housekeeping.pb.susdif.ComBoschCmHousekeepingSusdif;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.SingleFieldBuilder;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionProto {
    private static Descriptors.FileDescriptor descriptor;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_BluetoothPhone_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_BluetoothPhone_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_ChimeSettingReq_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_ChimeSettingReq_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_ChimeSetting_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_ChimeSetting_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_Clock_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_Clock_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_CpaaConnectState_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_CpaaConnectState_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_Elevation_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_Elevation_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_HotKeySetting_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_HotKeySetting_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_InsUiChangeReq_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_InsUiChangeReq_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_InstrumentTest_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_InstrumentTest_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_InteractionData_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_InteractionData_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_Language_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_Language_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_MapFocus_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_MapFocus_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_MapState_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_MapState_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_MediaProgress_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_MediaProgress_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_Music_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_Music_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_NaviInstallState_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_NaviInstallState_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_NaviSetting_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_NaviSetting_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_NaviTbtInfo_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_NaviTbtInfo_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_Navigation_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_Navigation_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_OverseasMapSupplier_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_OverseasMapSupplier_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_Query_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_Query_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_ResetOperation_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_ResetOperation_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_ScreenOrientationSetting_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_ScreenOrientationSetting_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_Slope_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_Slope_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_ThemeSetting_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_ThemeSetting_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_UpdateHotKeyAdasReq_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_UpdateHotKeyAdasReq_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_UpdateHotKeyNaviReq_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_UpdateHotKeyNaviReq_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_UpdateHotKeyThemeReq_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_UpdateHotKeyThemeReq_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_UpdateSettingThemeReq_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_UpdateSettingThemeReq_fieldAccessorTable;
    private static final Descriptors.Descriptor internal_static_autolink_interaction_VersatileThemeSetting_descriptor;
    private static GeneratedMessage.FieldAccessorTable internal_static_autolink_interaction_VersatileThemeSetting_fieldAccessorTable;

    public interface BluetoothPhoneOrBuilder extends MessageOrBuilder {
        int getCallTime();

        String getContactName();

        ByteString getContactNameBytes();

        String getContactNumber();

        ByteString getContactNumberBytes();

        PhoneNumberVisible getPVisible();

        IconDisply getPhoneDisplayIcon();

        CallStatus getStatus();

        PhoneTimeVisible getTimeVisible();

        int getType();

        boolean hasCallTime();

        boolean hasContactName();

        boolean hasContactNumber();

        boolean hasPVisible();

        boolean hasPhoneDisplayIcon();

        boolean hasStatus();

        boolean hasTimeVisible();

        boolean hasType();
    }

    public interface ChimeSettingOrBuilder extends MessageOrBuilder {
        SoundType getNCurrSoundType();

        boolean hasNCurrSoundType();
    }

    public interface ChimeSettingReqOrBuilder extends MessageOrBuilder {
        SoundType getMCurrSoundType();

        boolean hasMCurrSoundType();
    }

    public interface ClockOrBuilder extends MessageOrBuilder {
        AmPmDisplay getAmpmDisplay();

        ClockFormat getTimeFormat();

        int getTimeHour();

        int getTimeMinute();

        int getTimeSecond();

        boolean hasAmpmDisplay();

        boolean hasTimeFormat();

        boolean hasTimeHour();

        boolean hasTimeMinute();

        boolean hasTimeSecond();
    }

    public interface CpaaConnectStateOrBuilder extends MessageOrBuilder {
        int getMCpaaConnectState();

        boolean hasMCpaaConnectState();
    }

    public interface ElevationOrBuilder extends MessageOrBuilder {
        ElevationUnit getElevationUnit();

        int getElevationValue();

        boolean hasElevationUnit();

        boolean hasElevationValue();
    }

    public interface HotKeySettingOrBuilder extends MessageOrBuilder {
        VehicleAccTimer getNCurrAccTimer();

        VehicleAdasStat getNCurrAdasStat();

        VehicleINSDisp getNCurrINSDisplay();

        VehicleLightGear getNCurrLight();

        VehicleLightGearStat getNCurrLightStat();

        VehicleMileInfo getNCurrMileInfo();

        VehicleMileUnit getNCurrMileUnit();

        VehicleMileValue getNCurrMileValue();

        VehicleNaviType getNCurrNaviType();

        VehicleSpeedLimit getNCurrSpeedLimit();

        VehicleThemeType getNCurrThemeType();

        VehicleID getNVehicleID();

        VehicleType getNVehicleType();

        boolean hasNCurrAccTimer();

        boolean hasNCurrAdasStat();

        boolean hasNCurrINSDisplay();

        boolean hasNCurrLight();

        boolean hasNCurrLightStat();

        boolean hasNCurrMileInfo();

        boolean hasNCurrMileUnit();

        boolean hasNCurrMileValue();

        boolean hasNCurrNaviType();

        boolean hasNCurrSpeedLimit();

        boolean hasNCurrThemeType();

        boolean hasNVehicleID();

        boolean hasNVehicleType();
    }

    public interface InsUiChangeReqOrBuilder extends MessageOrBuilder {
        InsUiState getNCurrInsUiReq();

        boolean hasNCurrInsUiReq();
    }

    public interface InstrumentTestOrBuilder extends MessageOrBuilder {
        AdasAutoLearnVisible getAdasAutoLearnVisible();

        AdasDebugMode getAdasDebugMode();

        OSDVisible getOsdVisible();

        boolean hasAdasAutoLearnVisible();

        boolean hasAdasDebugMode();

        boolean hasOsdVisible();
    }

    public interface InteractionDataOrBuilder extends MessageOrBuilder {
        BluetoothPhone getBluetoothPhoneValue();

        BluetoothPhoneOrBuilder getBluetoothPhoneValueOrBuilder();

        Clock getClockValue();

        ClockOrBuilder getClockValueOrBuilder();

        Music getMusicValue();

        MusicOrBuilder getMusicValueOrBuilder();

        Navigation getNavigationValue();

        NavigationOrBuilder getNavigationValueOrBuilder();

        boolean hasBluetoothPhoneValue();

        boolean hasClockValue();

        boolean hasMusicValue();

        boolean hasNavigationValue();
    }

    public interface LanguageOrBuilder extends MessageOrBuilder {
        int getNCurrLang();

        boolean hasNCurrLang();
    }

    public interface MapFocusOrBuilder extends MessageOrBuilder {
        MapType getNCurrMapType();

        boolean hasNCurrMapType();
    }

    public interface MapStateOrBuilder extends MessageOrBuilder {
        MapType getMCurrMapType();

        NavigationPopInfo getNavigationPopInfo();

        boolean hasMCurrMapType();

        boolean hasNavigationPopInfo();
    }

    public interface MediaProgressOrBuilder extends MessageOrBuilder {
        int getCurrentProgress();

        int getMaxProgress();

        boolean hasCurrentProgress();

        boolean hasMaxProgress();
    }

    public interface MusicOrBuilder extends MessageOrBuilder {
        String getAlbumPath();

        ByteString getAlbumPathBytes();

        String getArtistName();

        ByteString getArtistNameBytes();

        PlayInterface getMPlayInterface();

        int getMPlaySource();

        PlayStatus getMStatus();

        Visible getMVisible();

        String getMediaText();

        ByteString getMediaTextBytes();

        IconDisply getMusicDisplayIcon();

        MusicNameRoll getRoll();

        boolean hasAlbumPath();

        boolean hasArtistName();

        boolean hasMPlayInterface();

        boolean hasMPlaySource();

        boolean hasMStatus();

        boolean hasMVisible();

        boolean hasMediaText();

        boolean hasMusicDisplayIcon();

        boolean hasRoll();
    }

    public interface NaviInstallStateOrBuilder extends MessageOrBuilder {
        int getMNaviInstallState();

        boolean hasMNaviInstallState();
    }

    public interface NaviSettingOrBuilder extends MessageOrBuilder {
        MediaNavigationalstate getNCurrMedNaviStat();

        MapType getNMapType();

        boolean hasNCurrMedNaviStat();

        boolean hasNMapType();
    }

    public interface NaviTbtInfoOrBuilder extends MessageOrBuilder {
        String getTbtArriveTime();

        ByteString getTbtArriveTimeBytes();

        String getTbtArriveTimeDay();

        ByteString getTbtArriveTimeDayBytes();

        String getTbtDestinationInfo();

        ByteString getTbtDestinationInfoBytes();

        MapType getTbtMapType();

        String getTbtMultipleText();

        ByteString getTbtMultipleTextBytes();

        String getTbtMultipleTextOne();

        ByteString getTbtMultipleTextOneBytes();

        String getTbtMultipleTextTwo();

        ByteString getTbtMultipleTextTwoBytes();

        int getTbtNextNavigationInformationIcon();

        String getTbtNextNextRoadDist();

        ByteString getTbtNextNextRoadDistBytes();

        String getTbtNextNextRoadDistUnit();

        ByteString getTbtNextNextRoadDistUnitBytes();

        String getTbtRemainingMilageUnit();

        ByteString getTbtRemainingMilageUnitBytes();

        String getTbtRemainingMilageValue();

        ByteString getTbtRemainingMilageValueBytes();

        String getTbtRemainingSecondValue();

        ByteString getTbtRemainingSecondValueBytes();

        int getTbtTurnNavigationInformationIcon();

        String getTbtTurnTipDigit();

        ByteString getTbtTurnTipDigitBytes();

        String getTbtTurnTipUnit();

        ByteString getTbtTurnTipUnitBytes();

        boolean hasTbtArriveTime();

        boolean hasTbtArriveTimeDay();

        boolean hasTbtDestinationInfo();

        boolean hasTbtMapType();

        boolean hasTbtMultipleText();

        boolean hasTbtMultipleTextOne();

        boolean hasTbtMultipleTextTwo();

        boolean hasTbtNextNavigationInformationIcon();

        boolean hasTbtNextNextRoadDist();

        boolean hasTbtNextNextRoadDistUnit();

        boolean hasTbtRemainingMilageUnit();

        boolean hasTbtRemainingMilageValue();

        boolean hasTbtRemainingSecondValue();

        boolean hasTbtTurnNavigationInformationIcon();

        boolean hasTbtTurnTipDigit();

        boolean hasTbtTurnTipUnit();
    }

    public interface NavigationOrBuilder extends MessageOrBuilder {
        int getArriveTimeDay();

        int getArriveTimeHour();

        int getArriveTimeMinute();

        String getDestinationInfo();

        ByteString getDestinationInfoBytes();

        MapType getMapType();

        int getNextNavigationInformationIcon();

        float getNextNextRoadDist();

        TopTipUnit getNextNextRoadUnit();

        TopTipUnit getRemainingMilage();

        float getRemainingMilageValue();

        int getRemainingSecondValue();

        TopTipUnit getTopTipUnit();

        int getTurnNavigationInformationIcon();

        float getTurnTipDigit();

        boolean hasArriveTimeDay();

        boolean hasArriveTimeHour();

        boolean hasArriveTimeMinute();

        boolean hasDestinationInfo();

        boolean hasMapType();

        boolean hasNextNavigationInformationIcon();

        boolean hasNextNextRoadDist();

        boolean hasNextNextRoadUnit();

        boolean hasRemainingMilage();

        boolean hasRemainingMilageValue();

        boolean hasRemainingSecondValue();

        boolean hasTopTipUnit();

        boolean hasTurnNavigationInformationIcon();

        boolean hasTurnTipDigit();
    }

    public interface OverseasMapSupplierOrBuilder extends MessageOrBuilder {
        int getMOverseasMapSupplier();

        boolean hasMOverseasMapSupplier();
    }

    public interface QueryOrBuilder extends MessageOrBuilder {
        QueryType getQueryValue();

        boolean hasQueryValue();
    }

    public interface ResetOperationOrBuilder extends MessageOrBuilder {
        int getNResetOper();

        boolean hasNResetOper();
    }

    public interface ScreenOrientationSettingOrBuilder extends MessageOrBuilder {
        ScreenOrientationValue getMCurrScreenOrientation();

        boolean hasMCurrScreenOrientation();
    }

    public interface SlopeOrBuilder extends MessageOrBuilder {
        SlopeUnit getSlopeUnit();

        int getSlopeValue();

        boolean hasSlopeUnit();

        boolean hasSlopeValue();
    }

    public interface ThemeSettingOrBuilder extends MessageOrBuilder {
        ThemeSet getNCurrTheme();

        boolean hasNCurrTheme();
    }

    public interface UpdateHotKeyAdasReqOrBuilder extends MessageOrBuilder {
        VehicleAdasStat getNCurrAdasStatReq();

        boolean hasNCurrAdasStatReq();
    }

    public interface UpdateHotKeyInsDisplayReqOrBuilder extends MessageOrBuilder {
        VehicleINSDisp getNCurrAdasStatReq();

        boolean hasNCurrAdasStatReq();
    }

    public interface UpdateHotKeyNaviReqOrBuilder extends MessageOrBuilder {
        VehicleNaviType getNCurrNaviType();

        boolean hasNCurrNaviType();
    }

    public interface UpdateHotKeyThemeReqOrBuilder extends MessageOrBuilder {
        VehicleThemeType getNCurrHotKeyThemeTypeReq();

        boolean hasNCurrHotKeyThemeTypeReq();
    }

    public interface UpdateSettingThemeReqOrBuilder extends MessageOrBuilder {
        ThemeSet getNCurrThemeInfoReq();

        boolean hasNCurrThemeInfoReq();
    }

    public interface VersatileThemeSettingOrBuilder extends MessageOrBuilder {
        int getMCurrVersatileTheme();

        boolean hasMCurrVersatileTheme();
    }

    private InteractionProto() {
    }

    public static void registerAllExtensions(ExtensionRegistry registry) {
    }

    public enum EventId implements ProtocolMessageEnum {
        SETTING_THEME(0, 0),
        SETTING_CLOCK(1, 1),
        SETTING_NAVI(2, 2),
        SETTING_HOTKEY(3, 3),
        MEDIA_MUSIC(4, 4),
        MEDIA_PROGRESS(5, 5),
        MEDIA_PHONE(6, 6),
        MEDIA_NAVIGATION(7, 7),
        SETTING_LANGUAGE(8, 8),
        SETTING_RESET(9, 9),
        CHIME_SOUND(10, 10),
        FIXEDISPLAY_ELEVATION(11, 11),
        FIXEDISPLAY_SLOPE(12, 12),
        STARTUP_QUERY(13, 13),
        MAP_TYEP_FOCUS(14, 14),
        MAP_STATE(15, 15),
        INSTRUMENT_TEST(16, 16),
        SETTING_SCREEN_ORIENTATION(17, 17),
        SETTING_VERSATILE_THEME(18, 18),
        OVERSEAS_MAP_SUPPLIER(19, 19),
        NAVIGATION_TBT_INFO(20, 20),
        NAVIGATION_INSTALL_STATE(21, 21),
        CPAA_CONNECT_STATE(22, 22);

        public static final int CHIME_SOUND_VALUE = 10;
        public static final int CPAA_CONNECT_STATE_VALUE = 22;
        public static final int FIXEDISPLAY_ELEVATION_VALUE = 11;
        public static final int FIXEDISPLAY_SLOPE_VALUE = 12;
        public static final int INSTRUMENT_TEST_VALUE = 16;
        public static final int MAP_STATE_VALUE = 15;
        public static final int MAP_TYEP_FOCUS_VALUE = 14;
        public static final int MEDIA_MUSIC_VALUE = 4;
        public static final int MEDIA_NAVIGATION_VALUE = 7;
        public static final int MEDIA_PHONE_VALUE = 6;
        public static final int MEDIA_PROGRESS_VALUE = 5;
        public static final int NAVIGATION_INSTALL_STATE_VALUE = 21;
        public static final int NAVIGATION_TBT_INFO_VALUE = 20;
        public static final int OVERSEAS_MAP_SUPPLIER_VALUE = 19;
        public static final int SETTING_CLOCK_VALUE = 1;
        public static final int SETTING_HOTKEY_VALUE = 3;
        public static final int SETTING_LANGUAGE_VALUE = 8;
        public static final int SETTING_NAVI_VALUE = 2;
        public static final int SETTING_RESET_VALUE = 9;
        public static final int SETTING_SCREEN_ORIENTATION_VALUE = 17;
        public static final int SETTING_THEME_VALUE = 0;
        public static final int SETTING_VERSATILE_THEME_VALUE = 18;
        public static final int STARTUP_QUERY_VALUE = 13;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<EventId> internalValueMap = new Internal.EnumLiteMap<EventId>() { // from class: com.autolink.interaction.InteractionProto.EventId.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public EventId findValueByNumber(int number) {
                return EventId.valueOf(number);
            }
        };
        private static final EventId[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static EventId valueOf(int value) {
            switch (value) {
                case 0:
                    return SETTING_THEME;
                case 1:
                    return SETTING_CLOCK;
                case 2:
                    return SETTING_NAVI;
                case 3:
                    return SETTING_HOTKEY;
                case 4:
                    return MEDIA_MUSIC;
                case 5:
                    return MEDIA_PROGRESS;
                case 6:
                    return MEDIA_PHONE;
                case 7:
                    return MEDIA_NAVIGATION;
                case 8:
                    return SETTING_LANGUAGE;
                case 9:
                    return SETTING_RESET;
                case 10:
                    return CHIME_SOUND;
                case 11:
                    return FIXEDISPLAY_ELEVATION;
                case 12:
                    return FIXEDISPLAY_SLOPE;
                case 13:
                    return STARTUP_QUERY;
                case 14:
                    return MAP_TYEP_FOCUS;
                case 15:
                    return MAP_STATE;
                case 16:
                    return INSTRUMENT_TEST;
                case 17:
                    return SETTING_SCREEN_ORIENTATION;
                case 18:
                    return SETTING_VERSATILE_THEME;
                case 19:
                    return OVERSEAS_MAP_SUPPLIER;
                case 20:
                    return NAVIGATION_TBT_INFO;
                case 21:
                    return NAVIGATION_INSTALL_STATE;
                case 22:
                    return CPAA_CONNECT_STATE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<EventId> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(0);
        }

        public static EventId valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        EventId(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum MsgType implements ProtocolMessageEnum {
        SETTING_THEME_CMD(0, 0),
        SETTING_HOTKEYNAVI_CMD(1, 1),
        SETTING_HOTKEYINSDISPLAY_CMD(2, 2),
        SETTING_HOTKEYADAS_CMD(3, 3),
        SETTING_HOTKEYTHEME_CMD(4, 4),
        INS_UI_CHANGE_CMD(5, 5),
        CHIME_SOUND_CMD(6, 6),
        MSG_TYPE_MAX(7, 7);

        public static final int CHIME_SOUND_CMD_VALUE = 6;
        public static final int INS_UI_CHANGE_CMD_VALUE = 5;
        public static final int MSG_TYPE_MAX_VALUE = 7;
        public static final int SETTING_HOTKEYADAS_CMD_VALUE = 3;
        public static final int SETTING_HOTKEYINSDISPLAY_CMD_VALUE = 2;
        public static final int SETTING_HOTKEYNAVI_CMD_VALUE = 1;
        public static final int SETTING_HOTKEYTHEME_CMD_VALUE = 4;
        public static final int SETTING_THEME_CMD_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<MsgType> internalValueMap = new Internal.EnumLiteMap<MsgType>() { // from class: com.autolink.interaction.InteractionProto.MsgType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MsgType findValueByNumber(int number) {
                return MsgType.valueOf(number);
            }
        };
        private static final MsgType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static MsgType valueOf(int value) {
            switch (value) {
                case 0:
                    return SETTING_THEME_CMD;
                case 1:
                    return SETTING_HOTKEYNAVI_CMD;
                case 2:
                    return SETTING_HOTKEYINSDISPLAY_CMD;
                case 3:
                    return SETTING_HOTKEYADAS_CMD;
                case 4:
                    return SETTING_HOTKEYTHEME_CMD;
                case 5:
                    return INS_UI_CHANGE_CMD;
                case 6:
                    return CHIME_SOUND_CMD;
                case 7:
                    return MSG_TYPE_MAX;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<MsgType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(1);
        }

        public static MsgType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        MsgType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NavigationIfomation implements ProtocolMessageEnum {
        INVALID(0, 0),
        TURN_LEFT(1, 1),
        TURN_RIGHT(2, 2),
        LEFT_FRONT(3, 3),
        RIGHT_FRONT(4, 4),
        LEFT_REAR(5, 5),
        RIGHT_REAR(6, 6),
        LEFT_TURNAROUND(7, 7),
        STRAIGHT_AHEAD(8, 8),
        REST_POINT(9, 10),
        CONTACT(10, 11),
        DESTINATION(11, 12),
        ENTER_THE_TUNNEL(12, 13),
        RIGHT_TURNAROUND(13, 14),
        PROGRADE_MOTION(14, 15),
        ROUNDABOUT_EXIT_1(15, 16),
        ROUNDABOUT_EXIT_2(16, 17),
        ROUNDABOUT_EXIT_3(17, 18),
        ROUNDABOUT_EXIT_4(18, 19),
        ROUNDABOUT_EXIT_5(19, 20),
        ROUNDABOUT_EXIT_6(20, 21),
        ROUNDABOUT_EXIT_7(21, 22),
        ROUNDABOUT_EXIT_8(22, 23),
        ROUNDABOUT_EXIT_9(23, 44),
        ROUNDABOUT_EXIT_10(24, 45),
        ROUNDABOUT_EXIT_11(25, 46),
        ROUNDABOUT_EXIT_12(26, 47),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_1(27, 48),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_1(28, 49),
        DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1(29, 50),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_2(30, 51),
        DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2(31, 52),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_2(32, 53),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_3(33, 54),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_4(34, 55),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_3(35, 56),
        DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_4(36, 57),
        FERRY(37, 58),
        ROUNDABOUT_EXIT_13(38, 59),
        ROUNDABOUT_EXIT_14(39, 60),
        ROUNDABOUT_EXIT_15(40, 61),
        ROUNDABOUT_EXIT_16(41, 62),
        ROUNDABOUT_EXIT_17(42, 63),
        ROUNDABOUT_EXIT_18(43, 64),
        DOUBLE_CROSS_SMALL_ANGLE_RIGHT_HAND_STRAIGHT_AHEAD(44, 65),
        DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD(45, 66),
        THREE_CROSS_SMALL_ANGLE_TURN_LEFT_1(46, 67),
        THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1(47, 68),
        THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_1(48, 69),
        THREE_CROSS_SMALL_ANGLE_TURN_LEFT_2(49, 70),
        THREE_CROSS_SMALL_ANGLE_TURN_LEFT_3(50, 71),
        THREE_CROSS_SMALL_ANGLE_TURN_LEFT_4(51, 72),
        THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_2(52, 73),
        THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3(53, 74),
        THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_4(54, 75),
        THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2(55, 76),
        THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_5(56, 77),
        THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_6(57, 78),
        THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_3(58, 79),
        THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5(59, 80),
        THREE_CROSS_SMALL_ANGLE_TURN_LEFT_6(60, 81),
        THREE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD(61, 82),
        THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_4(62, 83),
        THREE_CROSS_SMALL_ANGLE_RIGHT_RIGHT_STRAIGHT_AHEAD(63, 84),
        PASSING_POINTS_1(64, 85),
        PASSING_POINTS_2(65, 86),
        PASSING_POINTS_3(66, 87),
        PASSING_POINTS_4(67, 88),
        PASSING_POINTS_5(68, 89);

        public static final int CONTACT_VALUE = 11;
        public static final int DESTINATION_VALUE = 12;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_RIGHT_HAND_STRAIGHT_AHEAD_VALUE = 65;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD_VALUE = 66;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1_VALUE = 50;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2_VALUE = 52;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_1_VALUE = 48;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_2_VALUE = 51;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_3_VALUE = 54;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_4_VALUE = 55;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_1_VALUE = 49;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_2_VALUE = 53;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE = 56;
        public static final int DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_4_VALUE = 57;
        public static final int ENTER_THE_TUNNEL_VALUE = 13;
        public static final int FERRY_VALUE = 58;
        public static final int INVALID_VALUE = 0;
        public static final int LEFT_FRONT_VALUE = 3;
        public static final int LEFT_REAR_VALUE = 5;
        public static final int LEFT_TURNAROUND_VALUE = 7;
        public static final int PASSING_POINTS_1_VALUE = 85;
        public static final int PASSING_POINTS_2_VALUE = 86;
        public static final int PASSING_POINTS_3_VALUE = 87;
        public static final int PASSING_POINTS_4_VALUE = 88;
        public static final int PASSING_POINTS_5_VALUE = 89;
        public static final int PROGRADE_MOTION_VALUE = 15;
        public static final int REST_POINT_VALUE = 10;
        public static final int RIGHT_FRONT_VALUE = 4;
        public static final int RIGHT_REAR_VALUE = 6;
        public static final int RIGHT_TURNAROUND_VALUE = 14;
        public static final int ROUNDABOUT_EXIT_10_VALUE = 45;
        public static final int ROUNDABOUT_EXIT_11_VALUE = 46;
        public static final int ROUNDABOUT_EXIT_12_VALUE = 47;
        public static final int ROUNDABOUT_EXIT_13_VALUE = 59;
        public static final int ROUNDABOUT_EXIT_14_VALUE = 60;
        public static final int ROUNDABOUT_EXIT_15_VALUE = 61;
        public static final int ROUNDABOUT_EXIT_16_VALUE = 62;
        public static final int ROUNDABOUT_EXIT_17_VALUE = 63;
        public static final int ROUNDABOUT_EXIT_18_VALUE = 64;
        public static final int ROUNDABOUT_EXIT_1_VALUE = 16;
        public static final int ROUNDABOUT_EXIT_2_VALUE = 17;
        public static final int ROUNDABOUT_EXIT_3_VALUE = 18;
        public static final int ROUNDABOUT_EXIT_4_VALUE = 19;
        public static final int ROUNDABOUT_EXIT_5_VALUE = 20;
        public static final int ROUNDABOUT_EXIT_6_VALUE = 21;
        public static final int ROUNDABOUT_EXIT_7_VALUE = 22;
        public static final int ROUNDABOUT_EXIT_8_VALUE = 23;
        public static final int ROUNDABOUT_EXIT_9_VALUE = 44;
        public static final int STRAIGHT_AHEAD_VALUE = 8;
        public static final int THREE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD_VALUE = 82;
        public static final int THREE_CROSS_SMALL_ANGLE_RIGHT_RIGHT_STRAIGHT_AHEAD_VALUE = 84;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1_VALUE = 68;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2_VALUE = 76;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_3_VALUE = 79;
        public static final int THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_4_VALUE = 83;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_1_VALUE = 67;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_2_VALUE = 70;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_3_VALUE = 71;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_4_VALUE = 72;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5_VALUE = 80;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_LEFT_6_VALUE = 81;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_1_VALUE = 69;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_2_VALUE = 73;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE = 74;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_4_VALUE = 75;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_5_VALUE = 77;
        public static final int THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_6_VALUE = 78;
        public static final int TURN_LEFT_VALUE = 1;
        public static final int TURN_RIGHT_VALUE = 2;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NavigationIfomation> internalValueMap = new Internal.EnumLiteMap<NavigationIfomation>() { // from class: com.autolink.interaction.InteractionProto.NavigationIfomation.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NavigationIfomation findValueByNumber(int number) {
                return NavigationIfomation.valueOf(number);
            }
        };
        private static final NavigationIfomation[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NavigationIfomation valueOf(int value) {
            switch (value) {
                case 0:
                    return INVALID;
                case 1:
                    return TURN_LEFT;
                case 2:
                    return TURN_RIGHT;
                case 3:
                    return LEFT_FRONT;
                case 4:
                    return RIGHT_FRONT;
                case 5:
                    return LEFT_REAR;
                case 6:
                    return RIGHT_REAR;
                case 7:
                    return LEFT_TURNAROUND;
                case 8:
                    return STRAIGHT_AHEAD;
                default:
                    switch (value) {
                        case 10:
                            return REST_POINT;
                        case 11:
                            return CONTACT;
                        case 12:
                            return DESTINATION;
                        case 13:
                            return ENTER_THE_TUNNEL;
                        case 14:
                            return RIGHT_TURNAROUND;
                        case 15:
                            return PROGRADE_MOTION;
                        case 16:
                            return ROUNDABOUT_EXIT_1;
                        case 17:
                            return ROUNDABOUT_EXIT_2;
                        case 18:
                            return ROUNDABOUT_EXIT_3;
                        case 19:
                            return ROUNDABOUT_EXIT_4;
                        case 20:
                            return ROUNDABOUT_EXIT_5;
                        case 21:
                            return ROUNDABOUT_EXIT_6;
                        case 22:
                            return ROUNDABOUT_EXIT_7;
                        case 23:
                            return ROUNDABOUT_EXIT_8;
                        default:
                            switch (value) {
                                case 44:
                                    return ROUNDABOUT_EXIT_9;
                                case 45:
                                    return ROUNDABOUT_EXIT_10;
                                case ROUNDABOUT_EXIT_11_VALUE:
                                    return ROUNDABOUT_EXIT_11;
                                case ROUNDABOUT_EXIT_12_VALUE:
                                    return ROUNDABOUT_EXIT_12;
                                case 48:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_1;
                                case 49:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_1;
                                case 50:
                                    return DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1;
                                case 51:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_2;
                                case 52:
                                    return DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2;
                                case 53:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_2;
                                case 54:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_3;
                                case DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_4_VALUE:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_4;
                                case DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_3;
                                case DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_4_VALUE:
                                    return DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_4;
                                case FERRY_VALUE:
                                    return FERRY;
                                case ROUNDABOUT_EXIT_13_VALUE:
                                    return ROUNDABOUT_EXIT_13;
                                case ROUNDABOUT_EXIT_14_VALUE:
                                    return ROUNDABOUT_EXIT_14;
                                case ROUNDABOUT_EXIT_15_VALUE:
                                    return ROUNDABOUT_EXIT_15;
                                case ROUNDABOUT_EXIT_16_VALUE:
                                    return ROUNDABOUT_EXIT_16;
                                case ROUNDABOUT_EXIT_17_VALUE:
                                    return ROUNDABOUT_EXIT_17;
                                case 64:
                                    return ROUNDABOUT_EXIT_18;
                                case DOUBLE_CROSS_SMALL_ANGLE_RIGHT_HAND_STRAIGHT_AHEAD_VALUE:
                                    return DOUBLE_CROSS_SMALL_ANGLE_RIGHT_HAND_STRAIGHT_AHEAD;
                                case DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD_VALUE:
                                    return DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_1_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_LEFT_1;
                                case THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_1_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_1;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_2_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_LEFT_2;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_3_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_LEFT_3;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_4_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_LEFT_4;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_2_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_2;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_4_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_4;
                                case THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_5_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_5;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_6_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_6;
                                case THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_3_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_3;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_6_VALUE:
                                    return THREE_CROSS_SMALL_ANGLE_TURN_LEFT_6;
                                case 82:
                                    return THREE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD;
                                case 83:
                                    return THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_4;
                                case 84:
                                    return THREE_CROSS_SMALL_ANGLE_RIGHT_RIGHT_STRAIGHT_AHEAD;
                                case 85:
                                    return PASSING_POINTS_1;
                                case 86:
                                    return PASSING_POINTS_2;
                                case 87:
                                    return PASSING_POINTS_3;
                                case 88:
                                    return PASSING_POINTS_4;
                                case 89:
                                    return PASSING_POINTS_5;
                                default:
                                    return null;
                            }
                    }
            }
        }

        public static Internal.EnumLiteMap<NavigationIfomation> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(2);
        }

        public static NavigationIfomation valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NavigationIfomation(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum CallStatus implements ProtocolMessageEnum {
        IDLE(0, 0),
        INCOMING(1, 1),
        DIALING(2, 2),
        CALLING(3, 3),
        ENDING(4, 4);

        public static final int CALLING_VALUE = 3;
        public static final int DIALING_VALUE = 2;
        public static final int ENDING_VALUE = 4;
        public static final int IDLE_VALUE = 0;
        public static final int INCOMING_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<CallStatus> internalValueMap = new Internal.EnumLiteMap<CallStatus>() { // from class: com.autolink.interaction.InteractionProto.CallStatus.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public CallStatus findValueByNumber(int number) {
                return CallStatus.valueOf(number);
            }
        };
        private static final CallStatus[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static CallStatus valueOf(int value) {
            if (value == 0) {
                return IDLE;
            }
            if (value == 1) {
                return INCOMING;
            }
            if (value == 2) {
                return DIALING;
            }
            if (value == 3) {
                return CALLING;
            }
            if (value == 4) {
                return ENDING;
            }
            return null;
        }

        public static Internal.EnumLiteMap<CallStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(3);
        }

        public static CallStatus valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        CallStatus(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum PlayStatus implements ProtocolMessageEnum {
        DEFAULT(0, 0),
        PLAY(1, 1),
        STOP(2, 2),
        LOADING(3, 3);

        public static final int DEFAULT_VALUE = 0;
        public static final int LOADING_VALUE = 3;
        public static final int PLAY_VALUE = 1;
        public static final int STOP_VALUE = 2;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<PlayStatus> internalValueMap = new Internal.EnumLiteMap<PlayStatus>() { // from class: com.autolink.interaction.InteractionProto.PlayStatus.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PlayStatus findValueByNumber(int number) {
                return PlayStatus.valueOf(number);
            }
        };
        private static final PlayStatus[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static PlayStatus valueOf(int value) {
            if (value == 0) {
                return DEFAULT;
            }
            if (value == 1) {
                return PLAY;
            }
            if (value == 2) {
                return STOP;
            }
            if (value == 3) {
                return LOADING;
            }
            return null;
        }

        public static Internal.EnumLiteMap<PlayStatus> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(4);
        }

        public static PlayStatus valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        PlayStatus(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum MusicNameRoll implements ProtocolMessageEnum {
        ROLL(0, 1),
        PAUSE(1, 2),
        EXIT(2, 3);

        public static final int EXIT_VALUE = 3;
        public static final int PAUSE_VALUE = 2;
        public static final int ROLL_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<MusicNameRoll> internalValueMap = new Internal.EnumLiteMap<MusicNameRoll>() { // from class: com.autolink.interaction.InteractionProto.MusicNameRoll.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MusicNameRoll findValueByNumber(int number) {
                return MusicNameRoll.valueOf(number);
            }
        };
        private static final MusicNameRoll[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static MusicNameRoll valueOf(int value) {
            if (value == 1) {
                return ROLL;
            }
            if (value == 2) {
                return PAUSE;
            }
            if (value == 3) {
                return EXIT;
            }
            return null;
        }

        public static Internal.EnumLiteMap<MusicNameRoll> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(5);
        }

        public static MusicNameRoll valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        MusicNameRoll(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum ClockFormat implements ProtocolMessageEnum {
        FORMAT12(0, 0),
        FORMAT24(1, 1);

        public static final int FORMAT12_VALUE = 0;
        public static final int FORMAT24_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<ClockFormat> internalValueMap = new Internal.EnumLiteMap<ClockFormat>() { // from class: com.autolink.interaction.InteractionProto.ClockFormat.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ClockFormat findValueByNumber(int number) {
                return ClockFormat.valueOf(number);
            }
        };
        private static final ClockFormat[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static ClockFormat valueOf(int value) {
            if (value == 0) {
                return FORMAT12;
            }
            if (value == 1) {
                return FORMAT24;
            }
            return null;
        }

        public static Internal.EnumLiteMap<ClockFormat> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(6);
        }

        public static ClockFormat valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        ClockFormat(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum AmPmDisplay implements ProtocolMessageEnum {
        DISPLAY(0, 0),
        NODISPLAY(1, 1);

        public static final int DISPLAY_VALUE = 0;
        public static final int NODISPLAY_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<AmPmDisplay> internalValueMap = new Internal.EnumLiteMap<AmPmDisplay>() { // from class: com.autolink.interaction.InteractionProto.AmPmDisplay.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AmPmDisplay findValueByNumber(int number) {
                return AmPmDisplay.valueOf(number);
            }
        };
        private static final AmPmDisplay[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static AmPmDisplay valueOf(int value) {
            if (value == 0) {
                return DISPLAY;
            }
            if (value == 1) {
                return NODISPLAY;
            }
            return null;
        }

        public static Internal.EnumLiteMap<AmPmDisplay> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(7);
        }

        public static AmPmDisplay valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        AmPmDisplay(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum LanguageSet implements ProtocolMessageEnum {
        Chinese(0, 0),
        English(1, 1);

        public static final int Chinese_VALUE = 0;
        public static final int English_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<LanguageSet> internalValueMap = new Internal.EnumLiteMap<LanguageSet>() { // from class: com.autolink.interaction.InteractionProto.LanguageSet.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public LanguageSet findValueByNumber(int number) {
                return LanguageSet.valueOf(number);
            }
        };
        private static final LanguageSet[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static LanguageSet valueOf(int value) {
            if (value == 0) {
                return Chinese;
            }
            if (value == 1) {
                return English;
            }
            return null;
        }

        public static Internal.EnumLiteMap<LanguageSet> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(8);
        }

        public static LanguageSet valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        LanguageSet(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum ThemeSet implements ProtocolMessageEnum {
        InvaildColor(0, 0),
        DeepColor(1, 1),
        LightColor(2, 2),
        ReservedColor(3, 3);

        public static final int DeepColor_VALUE = 1;
        public static final int InvaildColor_VALUE = 0;
        public static final int LightColor_VALUE = 2;
        public static final int ReservedColor_VALUE = 3;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<ThemeSet> internalValueMap = new Internal.EnumLiteMap<ThemeSet>() { // from class: com.autolink.interaction.InteractionProto.ThemeSet.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ThemeSet findValueByNumber(int number) {
                return ThemeSet.valueOf(number);
            }
        };
        private static final ThemeSet[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static ThemeSet valueOf(int value) {
            if (value == 0) {
                return InvaildColor;
            }
            if (value == 1) {
                return DeepColor;
            }
            if (value == 2) {
                return LightColor;
            }
            if (value == 3) {
                return ReservedColor;
            }
            return null;
        }

        public static Internal.EnumLiteMap<ThemeSet> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(9);
        }

        public static ThemeSet valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        ThemeSet(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum HardKeyResult implements ProtocolMessageEnum {
        HARDKEY_FAIL(0, 0),
        HARDKEY_SUCCESS(1, 1);

        public static final int HARDKEY_FAIL_VALUE = 0;
        public static final int HARDKEY_SUCCESS_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<HardKeyResult> internalValueMap = new Internal.EnumLiteMap<HardKeyResult>() { // from class: com.autolink.interaction.InteractionProto.HardKeyResult.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public HardKeyResult findValueByNumber(int number) {
                return HardKeyResult.valueOf(number);
            }
        };
        private static final HardKeyResult[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static HardKeyResult valueOf(int value) {
            if (value == 0) {
                return HARDKEY_FAIL;
            }
            if (value == 1) {
                return HARDKEY_SUCCESS;
            }
            return null;
        }

        public static Internal.EnumLiteMap<HardKeyResult> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(10);
        }

        public static HardKeyResult valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        HardKeyResult(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum MediaNavigationalstate implements ProtocolMessageEnum {
        NAVITYPESTATE_INVALID(0, 0),
        NAVITYPESTATE_OPENN(1, 1),
        NAVITYPESTATE_CLOSE(2, 2);

        public static final int NAVITYPESTATE_CLOSE_VALUE = 2;
        public static final int NAVITYPESTATE_INVALID_VALUE = 0;
        public static final int NAVITYPESTATE_OPENN_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<MediaNavigationalstate> internalValueMap = new Internal.EnumLiteMap<MediaNavigationalstate>() { // from class: com.autolink.interaction.InteractionProto.MediaNavigationalstate.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MediaNavigationalstate findValueByNumber(int number) {
                return MediaNavigationalstate.valueOf(number);
            }
        };
        private static final MediaNavigationalstate[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static MediaNavigationalstate valueOf(int value) {
            if (value == 0) {
                return NAVITYPESTATE_INVALID;
            }
            if (value == 1) {
                return NAVITYPESTATE_OPENN;
            }
            if (value == 2) {
                return NAVITYPESTATE_CLOSE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<MediaNavigationalstate> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(11);
        }

        public static MediaNavigationalstate valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        MediaNavigationalstate(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleType implements ProtocolMessageEnum {
        VEHICLETYPE_EV(0, 1),
        VEHICLETYPE_HEV(1, 2),
        VEHICLETYPE_CONV(2, 3);

        public static final int VEHICLETYPE_CONV_VALUE = 3;
        public static final int VEHICLETYPE_EV_VALUE = 1;
        public static final int VEHICLETYPE_HEV_VALUE = 2;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleType> internalValueMap = new Internal.EnumLiteMap<VehicleType>() { // from class: com.autolink.interaction.InteractionProto.VehicleType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleType findValueByNumber(int number) {
                return VehicleType.valueOf(number);
            }
        };
        private static final VehicleType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleType valueOf(int value) {
            if (value == 1) {
                return VEHICLETYPE_EV;
            }
            if (value == 2) {
                return VEHICLETYPE_HEV;
            }
            if (value == 3) {
                return VEHICLETYPE_CONV;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(12);
        }

        public static VehicleType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleID implements ProtocolMessageEnum {
        VEHICLEID_CLASSICMODEL(0, 1),
        VEHICLEID_DYNASTYMODEL(1, 2),
        VEHICLEID_DENZAMODEL(2, 3),
        VEHICLEID_SEAMODEL(3, 4);

        public static final int VEHICLEID_CLASSICMODEL_VALUE = 1;
        public static final int VEHICLEID_DENZAMODEL_VALUE = 3;
        public static final int VEHICLEID_DYNASTYMODEL_VALUE = 2;
        public static final int VEHICLEID_SEAMODEL_VALUE = 4;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleID> internalValueMap = new Internal.EnumLiteMap<VehicleID>() { // from class: com.autolink.interaction.InteractionProto.VehicleID.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleID findValueByNumber(int number) {
                return VehicleID.valueOf(number);
            }
        };
        private static final VehicleID[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleID valueOf(int value) {
            if (value == 1) {
                return VEHICLEID_CLASSICMODEL;
            }
            if (value == 2) {
                return VEHICLEID_DYNASTYMODEL;
            }
            if (value == 3) {
                return VEHICLEID_DENZAMODEL;
            }
            if (value == 4) {
                return VEHICLEID_SEAMODEL;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleID> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(13);
        }

        public static VehicleID valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleID(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleThemeType implements ProtocolMessageEnum {
        VEHICLEID_INVALID(0, 0),
        VEHICLEID_CLASSICTYPE(1, 1),
        VEHICLEID_TECHNOTYPE(2, 2),
        VEHICLEID_FASHION(3, 3),
        VEHICLEID_SIMPLE(4, 4),
        VEHICLEID_RESERVED(5, 5);

        public static final int VEHICLEID_CLASSICTYPE_VALUE = 1;
        public static final int VEHICLEID_FASHION_VALUE = 3;
        public static final int VEHICLEID_INVALID_VALUE = 0;
        public static final int VEHICLEID_RESERVED_VALUE = 5;
        public static final int VEHICLEID_SIMPLE_VALUE = 4;
        public static final int VEHICLEID_TECHNOTYPE_VALUE = 2;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleThemeType> internalValueMap = new Internal.EnumLiteMap<VehicleThemeType>() { // from class: com.autolink.interaction.InteractionProto.VehicleThemeType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleThemeType findValueByNumber(int number) {
                return VehicleThemeType.valueOf(number);
            }
        };
        private static final VehicleThemeType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleThemeType valueOf(int value) {
            if (value == 0) {
                return VEHICLEID_INVALID;
            }
            if (value == 1) {
                return VEHICLEID_CLASSICTYPE;
            }
            if (value == 2) {
                return VEHICLEID_TECHNOTYPE;
            }
            if (value == 3) {
                return VEHICLEID_FASHION;
            }
            if (value == 4) {
                return VEHICLEID_SIMPLE;
            }
            if (value == 5) {
                return VEHICLEID_RESERVED;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleThemeType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(14);
        }

        public static VehicleThemeType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleThemeType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleLightGearStat implements ProtocolMessageEnum {
        LIGHTSTATUS_NORMAL(0, 1),
        LIGHTSTATUS_NORX(1, 2),
        LIGHTSTATUS_TIMER(2, 3);

        public static final int LIGHTSTATUS_NORMAL_VALUE = 1;
        public static final int LIGHTSTATUS_NORX_VALUE = 2;
        public static final int LIGHTSTATUS_TIMER_VALUE = 3;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleLightGearStat> internalValueMap = new Internal.EnumLiteMap<VehicleLightGearStat>() { // from class: com.autolink.interaction.InteractionProto.VehicleLightGearStat.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleLightGearStat findValueByNumber(int number) {
                return VehicleLightGearStat.valueOf(number);
            }
        };
        private static final VehicleLightGearStat[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleLightGearStat valueOf(int value) {
            if (value == 1) {
                return LIGHTSTATUS_NORMAL;
            }
            if (value == 2) {
                return LIGHTSTATUS_NORX;
            }
            if (value == 3) {
                return LIGHTSTATUS_TIMER;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleLightGearStat> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(15);
        }

        public static VehicleLightGearStat valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleLightGearStat(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleLightGear implements ProtocolMessageEnum {
        LIGHTGEAR_1(0, 1),
        LIGHTGEAR_2(1, 2),
        LIGHTGEAR_3(2, 3),
        LIGHTGEAR_4(3, 4),
        LIGHTGEAR_5(4, 5),
        LIGHTGEAR_6(5, 6),
        LIGHTGEAR_7(6, 7),
        LIGHTGEAR_8(7, 8),
        LIGHTGEAR_9(8, 9),
        LIGHTGEAR_10(9, 10),
        LIGHTGEAR_11(10, 11),
        LIGHTGEAR_12(11, 12),
        LIGHTGEAR_13(12, 13),
        LIGHTGEAR_14(13, 14),
        LIGHTGEAR_15(14, 15),
        LIGHTGEAR_16(15, 16);

        public static final int LIGHTGEAR_10_VALUE = 10;
        public static final int LIGHTGEAR_11_VALUE = 11;
        public static final int LIGHTGEAR_12_VALUE = 12;
        public static final int LIGHTGEAR_13_VALUE = 13;
        public static final int LIGHTGEAR_14_VALUE = 14;
        public static final int LIGHTGEAR_15_VALUE = 15;
        public static final int LIGHTGEAR_16_VALUE = 16;
        public static final int LIGHTGEAR_1_VALUE = 1;
        public static final int LIGHTGEAR_2_VALUE = 2;
        public static final int LIGHTGEAR_3_VALUE = 3;
        public static final int LIGHTGEAR_4_VALUE = 4;
        public static final int LIGHTGEAR_5_VALUE = 5;
        public static final int LIGHTGEAR_6_VALUE = 6;
        public static final int LIGHTGEAR_7_VALUE = 7;
        public static final int LIGHTGEAR_8_VALUE = 8;
        public static final int LIGHTGEAR_9_VALUE = 9;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleLightGear> internalValueMap = new Internal.EnumLiteMap<VehicleLightGear>() { // from class: com.autolink.interaction.InteractionProto.VehicleLightGear.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleLightGear findValueByNumber(int number) {
                return VehicleLightGear.valueOf(number);
            }
        };
        private static final VehicleLightGear[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleLightGear valueOf(int value) {
            switch (value) {
                case 1:
                    return LIGHTGEAR_1;
                case 2:
                    return LIGHTGEAR_2;
                case 3:
                    return LIGHTGEAR_3;
                case 4:
                    return LIGHTGEAR_4;
                case 5:
                    return LIGHTGEAR_5;
                case 6:
                    return LIGHTGEAR_6;
                case 7:
                    return LIGHTGEAR_7;
                case 8:
                    return LIGHTGEAR_8;
                case 9:
                    return LIGHTGEAR_9;
                case 10:
                    return LIGHTGEAR_10;
                case 11:
                    return LIGHTGEAR_11;
                case 12:
                    return LIGHTGEAR_12;
                case 13:
                    return LIGHTGEAR_13;
                case 14:
                    return LIGHTGEAR_14;
                case 15:
                    return LIGHTGEAR_15;
                case 16:
                    return LIGHTGEAR_16;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<VehicleLightGear> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(16);
        }

        public static VehicleLightGear valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleLightGear(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleNaviType implements ProtocolMessageEnum {
        VEHNAVITYPE_CLOSED(0, 1),
        VEHNAVITYPE_SIMPLE(1, 2),
        VEHNAVITYPE_SMALL(2, 3),
        VEHNAVITYPE_FULL(3, 4);

        public static final int VEHNAVITYPE_CLOSED_VALUE = 1;
        public static final int VEHNAVITYPE_FULL_VALUE = 4;
        public static final int VEHNAVITYPE_SIMPLE_VALUE = 2;
        public static final int VEHNAVITYPE_SMALL_VALUE = 3;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleNaviType> internalValueMap = new Internal.EnumLiteMap<VehicleNaviType>() { // from class: com.autolink.interaction.InteractionProto.VehicleNaviType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleNaviType findValueByNumber(int number) {
                return VehicleNaviType.valueOf(number);
            }
        };
        private static final VehicleNaviType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleNaviType valueOf(int value) {
            if (value == 1) {
                return VEHNAVITYPE_CLOSED;
            }
            if (value == 2) {
                return VEHNAVITYPE_SIMPLE;
            }
            if (value == 3) {
                return VEHNAVITYPE_SMALL;
            }
            if (value == 4) {
                return VEHNAVITYPE_FULL;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleNaviType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(17);
        }

        public static VehicleNaviType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleNaviType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleAdasStat implements ProtocolMessageEnum {
        VEHADASSTAT_INVALID(0, 0),
        VEHNAVISTAT_VALID(1, 1);

        public static final int VEHADASSTAT_INVALID_VALUE = 0;
        public static final int VEHNAVISTAT_VALID_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleAdasStat> internalValueMap = new Internal.EnumLiteMap<VehicleAdasStat>() { // from class: com.autolink.interaction.InteractionProto.VehicleAdasStat.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleAdasStat findValueByNumber(int number) {
                return VehicleAdasStat.valueOf(number);
            }
        };
        private static final VehicleAdasStat[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleAdasStat valueOf(int value) {
            if (value == 0) {
                return VEHADASSTAT_INVALID;
            }
            if (value == 1) {
                return VEHNAVISTAT_VALID;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleAdasStat> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(18);
        }

        public static VehicleAdasStat valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleAdasStat(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleINSDisp implements ProtocolMessageEnum {
        DRIVINGINFO_CLOSE(0, 0),
        DRIVINGINFO_50KMAVE(1, 1),
        DRIVINGINFO_CUMENE(2, 2),
        DRIVINGINFO_TIREPREE(3, 3),
        DRIVINGINFO_INSTANTFUEL(4, 6),
        DRIVINGINFO_RANGE(5, 7),
        DRIVINGINFO_FUELCONSUMP(6, 8),
        DRIVINGINFO_SINGLESTROKE(7, 9),
        DRIVINGINFO_AVERSPEED(8, 10),
        DRIVINGINFO_EXTERCHARG(9, 11),
        DRIVINGINFO_ENERGYFLOW(10, 12);

        public static final int DRIVINGINFO_50KMAVE_VALUE = 1;
        public static final int DRIVINGINFO_AVERSPEED_VALUE = 10;
        public static final int DRIVINGINFO_CLOSE_VALUE = 0;
        public static final int DRIVINGINFO_CUMENE_VALUE = 2;
        public static final int DRIVINGINFO_ENERGYFLOW_VALUE = 12;
        public static final int DRIVINGINFO_EXTERCHARG_VALUE = 11;
        public static final int DRIVINGINFO_FUELCONSUMP_VALUE = 8;
        public static final int DRIVINGINFO_INSTANTFUEL_VALUE = 6;
        public static final int DRIVINGINFO_RANGE_VALUE = 7;
        public static final int DRIVINGINFO_SINGLESTROKE_VALUE = 9;
        public static final int DRIVINGINFO_TIREPREE_VALUE = 3;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleINSDisp> internalValueMap = new Internal.EnumLiteMap<VehicleINSDisp>() { // from class: com.autolink.interaction.InteractionProto.VehicleINSDisp.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleINSDisp findValueByNumber(int number) {
                return VehicleINSDisp.valueOf(number);
            }
        };
        private static final VehicleINSDisp[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleINSDisp valueOf(int value) {
            switch (value) {
                case 0:
                    return DRIVINGINFO_CLOSE;
                case 1:
                    return DRIVINGINFO_50KMAVE;
                case 2:
                    return DRIVINGINFO_CUMENE;
                case 3:
                    return DRIVINGINFO_TIREPREE;
                case 4:
                case 5:
                default:
                    return null;
                case 6:
                    return DRIVINGINFO_INSTANTFUEL;
                case 7:
                    return DRIVINGINFO_RANGE;
                case 8:
                    return DRIVINGINFO_FUELCONSUMP;
                case 9:
                    return DRIVINGINFO_SINGLESTROKE;
                case 10:
                    return DRIVINGINFO_AVERSPEED;
                case 11:
                    return DRIVINGINFO_EXTERCHARG;
                case 12:
                    return DRIVINGINFO_ENERGYFLOW;
            }
        }

        public static Internal.EnumLiteMap<VehicleINSDisp> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(19);
        }

        public static VehicleINSDisp valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleINSDisp(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleDrivedInfo implements ProtocolMessageEnum {
        DRIVEDINFO_ENERGY(0, 0),
        DRIVEDINFO_ELEC(1, 1),
        DRIVEDINFO_OIL(2, 2);

        public static final int DRIVEDINFO_ELEC_VALUE = 1;
        public static final int DRIVEDINFO_ENERGY_VALUE = 0;
        public static final int DRIVEDINFO_OIL_VALUE = 2;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleDrivedInfo> internalValueMap = new Internal.EnumLiteMap<VehicleDrivedInfo>() { // from class: com.autolink.interaction.InteractionProto.VehicleDrivedInfo.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleDrivedInfo findValueByNumber(int number) {
                return VehicleDrivedInfo.valueOf(number);
            }
        };
        private static final VehicleDrivedInfo[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleDrivedInfo valueOf(int value) {
            if (value == 0) {
                return DRIVEDINFO_ENERGY;
            }
            if (value == 1) {
                return DRIVEDINFO_ELEC;
            }
            if (value == 2) {
                return DRIVEDINFO_OIL;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleDrivedInfo> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(20);
        }

        public static VehicleDrivedInfo valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleDrivedInfo(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleMileInfo implements ProtocolMessageEnum {
        TOTALMILEAGE(0, 1),
        MILEAGEINFO1(1, 2),
        MILEAGEINFO2(2, 3),
        MILEAGEHEV(3, 4),
        MILEAGEEV(4, 5),
        MILEAGEINVALID(5, 6);

        public static final int MILEAGEEV_VALUE = 5;
        public static final int MILEAGEHEV_VALUE = 4;
        public static final int MILEAGEINFO1_VALUE = 2;
        public static final int MILEAGEINFO2_VALUE = 3;
        public static final int MILEAGEINVALID_VALUE = 6;
        public static final int TOTALMILEAGE_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleMileInfo> internalValueMap = new Internal.EnumLiteMap<VehicleMileInfo>() { // from class: com.autolink.interaction.InteractionProto.VehicleMileInfo.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleMileInfo findValueByNumber(int number) {
                return VehicleMileInfo.valueOf(number);
            }
        };
        private static final VehicleMileInfo[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleMileInfo valueOf(int value) {
            switch (value) {
                case 1:
                    return TOTALMILEAGE;
                case 2:
                    return MILEAGEINFO1;
                case 3:
                    return MILEAGEINFO2;
                case 4:
                    return MILEAGEHEV;
                case 5:
                    return MILEAGEEV;
                case 6:
                    return MILEAGEINVALID;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<VehicleMileInfo> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(21);
        }

        public static VehicleMileInfo valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleMileInfo(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleMileUnit implements ProtocolMessageEnum {
        MILEUNIT_INVALID(0, 0),
        MILEUNIT_KM(1, 1),
        MILEUNIT_MILE(2, 2),
        MILEUNIT_RESERVED(3, 3);

        public static final int MILEUNIT_INVALID_VALUE = 0;
        public static final int MILEUNIT_KM_VALUE = 1;
        public static final int MILEUNIT_MILE_VALUE = 2;
        public static final int MILEUNIT_RESERVED_VALUE = 3;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleMileUnit> internalValueMap = new Internal.EnumLiteMap<VehicleMileUnit>() { // from class: com.autolink.interaction.InteractionProto.VehicleMileUnit.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleMileUnit findValueByNumber(int number) {
                return VehicleMileUnit.valueOf(number);
            }
        };
        private static final VehicleMileUnit[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleMileUnit valueOf(int value) {
            if (value == 0) {
                return MILEUNIT_INVALID;
            }
            if (value == 1) {
                return MILEUNIT_KM;
            }
            if (value == 2) {
                return MILEUNIT_MILE;
            }
            if (value == 3) {
                return MILEUNIT_RESERVED;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleMileUnit> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(22);
        }

        public static VehicleMileUnit valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleMileUnit(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleMileValue implements ProtocolMessageEnum {
        TOTALMILEVALUE(0, 0),
        TOTALMILEINVALUE(1, 1),
        MILE1VALUE(2, 2),
        MILE1INVALUE(3, 3),
        MILE2VALUE(4, 4),
        MILE2INVALUE(5, 5),
        HEVMILEVALUE(6, 6),
        HEVMILEINVALUE(7, 7),
        EVMILEVALUE(8, 8),
        EVMILEINVALUE(9, 9);

        public static final int EVMILEINVALUE_VALUE = 9;
        public static final int EVMILEVALUE_VALUE = 8;
        public static final int HEVMILEINVALUE_VALUE = 7;
        public static final int HEVMILEVALUE_VALUE = 6;
        public static final int MILE1INVALUE_VALUE = 3;
        public static final int MILE1VALUE_VALUE = 2;
        public static final int MILE2INVALUE_VALUE = 5;
        public static final int MILE2VALUE_VALUE = 4;
        public static final int TOTALMILEINVALUE_VALUE = 1;
        public static final int TOTALMILEVALUE_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleMileValue> internalValueMap = new Internal.EnumLiteMap<VehicleMileValue>() { // from class: com.autolink.interaction.InteractionProto.VehicleMileValue.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleMileValue findValueByNumber(int number) {
                return VehicleMileValue.valueOf(number);
            }
        };
        private static final VehicleMileValue[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleMileValue valueOf(int value) {
            switch (value) {
                case 0:
                    return TOTALMILEVALUE;
                case 1:
                    return TOTALMILEINVALUE;
                case 2:
                    return MILE1VALUE;
                case 3:
                    return MILE1INVALUE;
                case 4:
                    return MILE2VALUE;
                case 5:
                    return MILE2INVALUE;
                case 6:
                    return HEVMILEVALUE;
                case 7:
                    return HEVMILEINVALUE;
                case 8:
                    return EVMILEVALUE;
                case 9:
                    return EVMILEINVALUE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<VehicleMileValue> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(23);
        }

        public static VehicleMileValue valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleMileValue(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleAccTimer implements ProtocolMessageEnum {
        ACCTIMER_INVALID(0, 0),
        ACCTIMER_START(1, 1),
        ACCTIMER_CLOSE(2, 2);

        public static final int ACCTIMER_CLOSE_VALUE = 2;
        public static final int ACCTIMER_INVALID_VALUE = 0;
        public static final int ACCTIMER_START_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleAccTimer> internalValueMap = new Internal.EnumLiteMap<VehicleAccTimer>() { // from class: com.autolink.interaction.InteractionProto.VehicleAccTimer.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleAccTimer findValueByNumber(int number) {
                return VehicleAccTimer.valueOf(number);
            }
        };
        private static final VehicleAccTimer[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleAccTimer valueOf(int value) {
            if (value == 0) {
                return ACCTIMER_INVALID;
            }
            if (value == 1) {
                return ACCTIMER_START;
            }
            if (value == 2) {
                return ACCTIMER_CLOSE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleAccTimer> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(24);
        }

        public static VehicleAccTimer valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleAccTimer(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum VehicleSpeedLimit implements ProtocolMessageEnum {
        SPEEDLIMIT_INVALID(0, 0),
        SPEEDLIMIT_VALID(1, 1);

        public static final int SPEEDLIMIT_INVALID_VALUE = 0;
        public static final int SPEEDLIMIT_VALID_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<VehicleSpeedLimit> internalValueMap = new Internal.EnumLiteMap<VehicleSpeedLimit>() { // from class: com.autolink.interaction.InteractionProto.VehicleSpeedLimit.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public VehicleSpeedLimit findValueByNumber(int number) {
                return VehicleSpeedLimit.valueOf(number);
            }
        };
        private static final VehicleSpeedLimit[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static VehicleSpeedLimit valueOf(int value) {
            if (value == 0) {
                return SPEEDLIMIT_INVALID;
            }
            if (value == 1) {
                return SPEEDLIMIT_VALID;
            }
            return null;
        }

        public static Internal.EnumLiteMap<VehicleSpeedLimit> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(25);
        }

        public static VehicleSpeedLimit valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        VehicleSpeedLimit(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum ElevationUnit implements ProtocolMessageEnum {
        ELEVATION_UNIT_METER(0, 1),
        ELEVATION_UNIT_KM(1, 2);

        public static final int ELEVATION_UNIT_KM_VALUE = 2;
        public static final int ELEVATION_UNIT_METER_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<ElevationUnit> internalValueMap = new Internal.EnumLiteMap<ElevationUnit>() { // from class: com.autolink.interaction.InteractionProto.ElevationUnit.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ElevationUnit findValueByNumber(int number) {
                return ElevationUnit.valueOf(number);
            }
        };
        private static final ElevationUnit[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static ElevationUnit valueOf(int value) {
            if (value == 1) {
                return ELEVATION_UNIT_METER;
            }
            if (value == 2) {
                return ELEVATION_UNIT_KM;
            }
            return null;
        }

        public static Internal.EnumLiteMap<ElevationUnit> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(26);
        }

        public static ElevationUnit valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        ElevationUnit(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum SlopeUnit implements ProtocolMessageEnum {
        SLOPE_UNIT_DEGREE(0, 1),
        SLOPE_UNIT_PERCENTAGE(1, 2);

        public static final int SLOPE_UNIT_DEGREE_VALUE = 1;
        public static final int SLOPE_UNIT_PERCENTAGE_VALUE = 2;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<SlopeUnit> internalValueMap = new Internal.EnumLiteMap<SlopeUnit>() { // from class: com.autolink.interaction.InteractionProto.SlopeUnit.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SlopeUnit findValueByNumber(int number) {
                return SlopeUnit.valueOf(number);
            }
        };
        private static final SlopeUnit[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static SlopeUnit valueOf(int value) {
            if (value == 1) {
                return SLOPE_UNIT_DEGREE;
            }
            if (value == 2) {
                return SLOPE_UNIT_PERCENTAGE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<SlopeUnit> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(27);
        }

        public static SlopeUnit valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        SlopeUnit(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum SoundType implements ProtocolMessageEnum {
        STANDARD_SOUND(0, 1),
        BRAND_SOUND(1, 2);

        public static final int BRAND_SOUND_VALUE = 2;
        public static final int STANDARD_SOUND_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<SoundType> internalValueMap = new Internal.EnumLiteMap<SoundType>() { // from class: com.autolink.interaction.InteractionProto.SoundType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public SoundType findValueByNumber(int number) {
                return SoundType.valueOf(number);
            }
        };
        private static final SoundType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static SoundType valueOf(int value) {
            if (value == 1) {
                return STANDARD_SOUND;
            }
            if (value == 2) {
                return BRAND_SOUND;
            }
            return null;
        }

        public static Internal.EnumLiteMap<SoundType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(28);
        }

        public static SoundType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        SoundType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum IconDisply implements ProtocolMessageEnum {
        ICON_DISPLY_OFF(0, 0),
        ICON_DISPLY_ON(1, 1);

        public static final int ICON_DISPLY_OFF_VALUE = 0;
        public static final int ICON_DISPLY_ON_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<IconDisply> internalValueMap = new Internal.EnumLiteMap<IconDisply>() { // from class: com.autolink.interaction.InteractionProto.IconDisply.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public IconDisply findValueByNumber(int number) {
                return IconDisply.valueOf(number);
            }
        };
        private static final IconDisply[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static IconDisply valueOf(int value) {
            if (value == 0) {
                return ICON_DISPLY_OFF;
            }
            if (value == 1) {
                return ICON_DISPLY_ON;
            }
            return null;
        }

        public static Internal.EnumLiteMap<IconDisply> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(29);
        }

        public static IconDisply valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        IconDisply(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum Visible implements ProtocolMessageEnum {
        INVISIBLE(0, 0),
        VISIBLE(1, 1);

        public static final int INVISIBLE_VALUE = 0;
        public static final int VISIBLE_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<Visible> internalValueMap = new Internal.EnumLiteMap<Visible>() { // from class: com.autolink.interaction.InteractionProto.Visible.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public Visible findValueByNumber(int number) {
                return Visible.valueOf(number);
            }
        };
        private static final Visible[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static Visible valueOf(int value) {
            if (value == 0) {
                return INVISIBLE;
            }
            if (value == 1) {
                return VISIBLE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<Visible> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(30);
        }

        public static Visible valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        Visible(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum PlaySource implements ProtocolMessageEnum {
        SOURCE_DEFAULT(0, 0),
        SOURCE_LOCAL_MUSIC(1, 1),
        SOURCE_USB1(2, 2),
        SOURCE_USB2(3, 3),
        SOURCE_SD(4, 4),
        SOURCE_AUX(5, 5),
        SOURCE_BT(6, 6),
        SOURCE_FM(7, 7),
        SOURCE_VIDEO(8, 8),
        SOURCE_KOOWO(9, 9),
        SOURCE_KOALA(10, 10),
        SOURCE_TRIPARTITE(11, 11),
        SOURCE_HIMALAYA(12, 12),
        SOURCE_KOOGOO(13, 13),
        SOURCE_CLOUD_LISTENING(14, 14),
        SOURCE_CAR_KARAOKE(15, 15),
        SOURCE_SPOTIFY(16, 16),
        SOURCE_DAB(17, 17),
        SOURCE_AM(18, 18),
        SOURCE_CAR_PLAY(19, 30),
        SOURCE_ANDROID_AUTO(20, 31);

        public static final int SOURCE_AM_VALUE = 18;
        public static final int SOURCE_ANDROID_AUTO_VALUE = 31;
        public static final int SOURCE_AUX_VALUE = 5;
        public static final int SOURCE_BT_VALUE = 6;
        public static final int SOURCE_CAR_KARAOKE_VALUE = 15;
        public static final int SOURCE_CAR_PLAY_VALUE = 30;
        public static final int SOURCE_CLOUD_LISTENING_VALUE = 14;
        public static final int SOURCE_DAB_VALUE = 17;
        public static final int SOURCE_DEFAULT_VALUE = 0;
        public static final int SOURCE_FM_VALUE = 7;
        public static final int SOURCE_HIMALAYA_VALUE = 12;
        public static final int SOURCE_KOALA_VALUE = 10;
        public static final int SOURCE_KOOGOO_VALUE = 13;
        public static final int SOURCE_KOOWO_VALUE = 9;
        public static final int SOURCE_LOCAL_MUSIC_VALUE = 1;
        public static final int SOURCE_SD_VALUE = 4;
        public static final int SOURCE_SPOTIFY_VALUE = 16;
        public static final int SOURCE_TRIPARTITE_VALUE = 11;
        public static final int SOURCE_USB1_VALUE = 2;
        public static final int SOURCE_USB2_VALUE = 3;
        public static final int SOURCE_VIDEO_VALUE = 8;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<PlaySource> internalValueMap = new Internal.EnumLiteMap<PlaySource>() { // from class: com.autolink.interaction.InteractionProto.PlaySource.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PlaySource findValueByNumber(int number) {
                return PlaySource.valueOf(number);
            }
        };
        private static final PlaySource[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static PlaySource valueOf(int value) {
            if (value == 30) {
                return SOURCE_CAR_PLAY;
            }
            if (value != 31) {
                switch (value) {
                    case 0:
                        return SOURCE_DEFAULT;
                    case 1:
                        return SOURCE_LOCAL_MUSIC;
                    case 2:
                        return SOURCE_USB1;
                    case 3:
                        return SOURCE_USB2;
                    case 4:
                        return SOURCE_SD;
                    case 5:
                        return SOURCE_AUX;
                    case 6:
                        return SOURCE_BT;
                    case 7:
                        return SOURCE_FM;
                    case 8:
                        return SOURCE_VIDEO;
                    case 9:
                        return SOURCE_KOOWO;
                    case 10:
                        return SOURCE_KOALA;
                    case 11:
                        return SOURCE_TRIPARTITE;
                    case 12:
                        return SOURCE_HIMALAYA;
                    case 13:
                        return SOURCE_KOOGOO;
                    case 14:
                        return SOURCE_CLOUD_LISTENING;
                    case 15:
                        return SOURCE_CAR_KARAOKE;
                    case 16:
                        return SOURCE_SPOTIFY;
                    case 17:
                        return SOURCE_DAB;
                    case 18:
                        return SOURCE_AM;
                    default:
                        return null;
                }
            }
            return SOURCE_ANDROID_AUTO;
        }

        public static Internal.EnumLiteMap<PlaySource> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(31);
        }

        public static PlaySource valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        PlaySource(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum PlayInterface implements ProtocolMessageEnum {
        FRONT_DESK_PLAY(0, 1),
        BACKGROUND_PLAY(1, 2);

        public static final int BACKGROUND_PLAY_VALUE = 2;
        public static final int FRONT_DESK_PLAY_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<PlayInterface> internalValueMap = new Internal.EnumLiteMap<PlayInterface>() { // from class: com.autolink.interaction.InteractionProto.PlayInterface.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PlayInterface findValueByNumber(int number) {
                return PlayInterface.valueOf(number);
            }
        };
        private static final PlayInterface[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static PlayInterface valueOf(int value) {
            if (value == 1) {
                return FRONT_DESK_PLAY;
            }
            if (value == 2) {
                return BACKGROUND_PLAY;
            }
            return null;
        }

        public static Internal.EnumLiteMap<PlayInterface> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(32);
        }

        public static PlayInterface valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        PlayInterface(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum PhoneNumberVisible implements ProtocolMessageEnum {
        PHONE_INVISIBLE(0, 0),
        PHONE_VISIBLE(1, 1);

        public static final int PHONE_INVISIBLE_VALUE = 0;
        public static final int PHONE_VISIBLE_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<PhoneNumberVisible> internalValueMap = new Internal.EnumLiteMap<PhoneNumberVisible>() { // from class: com.autolink.interaction.InteractionProto.PhoneNumberVisible.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PhoneNumberVisible findValueByNumber(int number) {
                return PhoneNumberVisible.valueOf(number);
            }
        };
        private static final PhoneNumberVisible[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static PhoneNumberVisible valueOf(int value) {
            if (value == 0) {
                return PHONE_INVISIBLE;
            }
            if (value == 1) {
                return PHONE_VISIBLE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<PhoneNumberVisible> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(33);
        }

        public static PhoneNumberVisible valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        PhoneNumberVisible(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum PhoneTimeVisible implements ProtocolMessageEnum {
        CALL_TIME_INVISIBLE(0, 0),
        CALL_TIME_VISIBLE(1, 1);

        public static final int CALL_TIME_INVISIBLE_VALUE = 0;
        public static final int CALL_TIME_VISIBLE_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<PhoneTimeVisible> internalValueMap = new Internal.EnumLiteMap<PhoneTimeVisible>() { // from class: com.autolink.interaction.InteractionProto.PhoneTimeVisible.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PhoneTimeVisible findValueByNumber(int number) {
                return PhoneTimeVisible.valueOf(number);
            }
        };
        private static final PhoneTimeVisible[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static PhoneTimeVisible valueOf(int value) {
            if (value == 0) {
                return CALL_TIME_INVISIBLE;
            }
            if (value == 1) {
                return CALL_TIME_VISIBLE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<PhoneTimeVisible> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(34);
        }

        public static PhoneTimeVisible valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        PhoneTimeVisible(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum PhoneType implements ProtocolMessageEnum {
        NORMAL(0, 0),
        NAVIGATION_FULL_SCREEN(1, 1);

        public static final int NAVIGATION_FULL_SCREEN_VALUE = 1;
        public static final int NORMAL_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<PhoneType> internalValueMap = new Internal.EnumLiteMap<PhoneType>() { // from class: com.autolink.interaction.InteractionProto.PhoneType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public PhoneType findValueByNumber(int number) {
                return PhoneType.valueOf(number);
            }
        };
        private static final PhoneType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static PhoneType valueOf(int value) {
            if (value == 0) {
                return NORMAL;
            }
            if (value == 1) {
                return NAVIGATION_FULL_SCREEN;
            }
            return null;
        }

        public static Internal.EnumLiteMap<PhoneType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(35);
        }

        public static PhoneType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        PhoneType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum MapType implements ProtocolMessageEnum {
        MAP_TYPE_INVALID(0, 0),
        LOCAL_MAP(1, 1),
        CARPLAY_MAP(2, 2),
        ANDROIDAUTO_MAP(3, 3);

        public static final int ANDROIDAUTO_MAP_VALUE = 3;
        public static final int CARPLAY_MAP_VALUE = 2;
        public static final int LOCAL_MAP_VALUE = 1;
        public static final int MAP_TYPE_INVALID_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<MapType> internalValueMap = new Internal.EnumLiteMap<MapType>() { // from class: com.autolink.interaction.InteractionProto.MapType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public MapType findValueByNumber(int number) {
                return MapType.valueOf(number);
            }
        };
        private static final MapType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static MapType valueOf(int value) {
            if (value == 0) {
                return MAP_TYPE_INVALID;
            }
            if (value == 1) {
                return LOCAL_MAP;
            }
            if (value == 2) {
                return CARPLAY_MAP;
            }
            if (value == 3) {
                return ANDROIDAUTO_MAP;
            }
            return null;
        }

        public static Internal.EnumLiteMap<MapType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(36);
        }

        public static MapType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        MapType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum InsUiState implements ProtocolMessageEnum {
        FULL_MAP_MODE_PENETRATE(0, 1),
        FULL_MAP_MODE_NOT_PENETRATE(1, 2),
        SAMLL_MAP_MODE_PENETRATE(2, 3),
        SAMLL_MAP_MODE_NOT_PENETRATE(3, 4),
        SIMPLE_MAP_MODE_PENETRATE(4, 5),
        SIMPLE_MAP_MODE_NOT_PENETRATE(5, 6),
        CLOSE_MAP_MODE(6, 7);

        public static final int CLOSE_MAP_MODE_VALUE = 7;
        public static final int FULL_MAP_MODE_NOT_PENETRATE_VALUE = 2;
        public static final int FULL_MAP_MODE_PENETRATE_VALUE = 1;
        public static final int SAMLL_MAP_MODE_NOT_PENETRATE_VALUE = 4;
        public static final int SAMLL_MAP_MODE_PENETRATE_VALUE = 3;
        public static final int SIMPLE_MAP_MODE_NOT_PENETRATE_VALUE = 6;
        public static final int SIMPLE_MAP_MODE_PENETRATE_VALUE = 5;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<InsUiState> internalValueMap = new Internal.EnumLiteMap<InsUiState>() { // from class: com.autolink.interaction.InteractionProto.InsUiState.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public InsUiState findValueByNumber(int number) {
                return InsUiState.valueOf(number);
            }
        };
        private static final InsUiState[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static InsUiState valueOf(int value) {
            switch (value) {
                case 1:
                    return FULL_MAP_MODE_PENETRATE;
                case 2:
                    return FULL_MAP_MODE_NOT_PENETRATE;
                case 3:
                    return SAMLL_MAP_MODE_PENETRATE;
                case 4:
                    return SAMLL_MAP_MODE_NOT_PENETRATE;
                case 5:
                    return SIMPLE_MAP_MODE_PENETRATE;
                case 6:
                    return SIMPLE_MAP_MODE_NOT_PENETRATE;
                case 7:
                    return CLOSE_MAP_MODE;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<InsUiState> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(37);
        }

        public static InsUiState valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        InsUiState(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum TopTipUnit implements ProtocolMessageEnum {
        UNIT_INVALID(0, 0),
        UNIT_KM(1, 1),
        UNIT_MILES(2, 2),
        UNIT_M(3, 3),
        UNIT_YARDS(4, 4),
        UNIT_FT(5, 5);

        public static final int UNIT_FT_VALUE = 5;
        public static final int UNIT_INVALID_VALUE = 0;
        public static final int UNIT_KM_VALUE = 1;
        public static final int UNIT_MILES_VALUE = 2;
        public static final int UNIT_M_VALUE = 3;
        public static final int UNIT_YARDS_VALUE = 4;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<TopTipUnit> internalValueMap = new Internal.EnumLiteMap<TopTipUnit>() { // from class: com.autolink.interaction.InteractionProto.TopTipUnit.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public TopTipUnit findValueByNumber(int number) {
                return TopTipUnit.valueOf(number);
            }
        };
        private static final TopTipUnit[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static TopTipUnit valueOf(int value) {
            if (value == 0) {
                return UNIT_INVALID;
            }
            if (value == 1) {
                return UNIT_KM;
            }
            if (value == 2) {
                return UNIT_MILES;
            }
            if (value == 3) {
                return UNIT_M;
            }
            if (value == 4) {
                return UNIT_YARDS;
            }
            if (value == 5) {
                return UNIT_FT;
            }
            return null;
        }

        public static Internal.EnumLiteMap<TopTipUnit> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(38);
        }

        public static TopTipUnit valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        TopTipUnit(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum NavigationPopInfo implements ProtocolMessageEnum {
        CLOSE_NAVI(0, 1),
        CLOSE_SAMLL_NAVI(1, 2),
        CLOSE_FULL_NAVI(2, 3),
        CLOSE_SIMPLE_NAVI(3, 4),
        OPEN_NAVI_SMALL(4, 5),
        OPEN_NAVI_FULL(5, 6),
        OPEN_NAVI_SIMPLE(6, 7),
        MAP_NOT_ATTIVE(7, 8),
        MAP_NOT_AGREED(8, 9),
        MAP_NOT_SUPPORT(9, 10);

        public static final int CLOSE_FULL_NAVI_VALUE = 3;
        public static final int CLOSE_NAVI_VALUE = 1;
        public static final int CLOSE_SAMLL_NAVI_VALUE = 2;
        public static final int CLOSE_SIMPLE_NAVI_VALUE = 4;
        public static final int MAP_NOT_AGREED_VALUE = 9;
        public static final int MAP_NOT_ATTIVE_VALUE = 8;
        public static final int MAP_NOT_SUPPORT_VALUE = 10;
        public static final int OPEN_NAVI_FULL_VALUE = 6;
        public static final int OPEN_NAVI_SIMPLE_VALUE = 7;
        public static final int OPEN_NAVI_SMALL_VALUE = 5;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<NavigationPopInfo> internalValueMap = new Internal.EnumLiteMap<NavigationPopInfo>() { // from class: com.autolink.interaction.InteractionProto.NavigationPopInfo.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public NavigationPopInfo findValueByNumber(int number) {
                return NavigationPopInfo.valueOf(number);
            }
        };
        private static final NavigationPopInfo[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static NavigationPopInfo valueOf(int value) {
            switch (value) {
                case 1:
                    return CLOSE_NAVI;
                case 2:
                    return CLOSE_SAMLL_NAVI;
                case 3:
                    return CLOSE_FULL_NAVI;
                case 4:
                    return CLOSE_SIMPLE_NAVI;
                case 5:
                    return OPEN_NAVI_SMALL;
                case 6:
                    return OPEN_NAVI_FULL;
                case 7:
                    return OPEN_NAVI_SIMPLE;
                case 8:
                    return MAP_NOT_ATTIVE;
                case 9:
                    return MAP_NOT_AGREED;
                case 10:
                    return MAP_NOT_SUPPORT;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<NavigationPopInfo> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(39);
        }

        public static NavigationPopInfo valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        NavigationPopInfo(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum QueryType implements ProtocolMessageEnum {
        SETTING_THEME_CMD_QUERY(0, 0),
        SETTING_HOTKEYNAVI_CMD_QUERY(1, 1),
        SETTING_HOTKEYINSDISPLAY_CMD_QUERY(2, 2),
        SETTING_HOTKEYADAS_CMD_QUERY(3, 3),
        SETTING_HOTKEYTHEME_CMD_QUERY(4, 4),
        CHIME_SOUND_CMD_QUER(5, 5);

        public static final int CHIME_SOUND_CMD_QUER_VALUE = 5;
        public static final int SETTING_HOTKEYADAS_CMD_QUERY_VALUE = 3;
        public static final int SETTING_HOTKEYINSDISPLAY_CMD_QUERY_VALUE = 2;
        public static final int SETTING_HOTKEYNAVI_CMD_QUERY_VALUE = 1;
        public static final int SETTING_HOTKEYTHEME_CMD_QUERY_VALUE = 4;
        public static final int SETTING_THEME_CMD_QUERY_VALUE = 0;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<QueryType> internalValueMap = new Internal.EnumLiteMap<QueryType>() { // from class: com.autolink.interaction.InteractionProto.QueryType.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public QueryType findValueByNumber(int number) {
                return QueryType.valueOf(number);
            }
        };
        private static final QueryType[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static QueryType valueOf(int value) {
            if (value == 0) {
                return SETTING_THEME_CMD_QUERY;
            }
            if (value == 1) {
                return SETTING_HOTKEYNAVI_CMD_QUERY;
            }
            if (value == 2) {
                return SETTING_HOTKEYINSDISPLAY_CMD_QUERY;
            }
            if (value == 3) {
                return SETTING_HOTKEYADAS_CMD_QUERY;
            }
            if (value == 4) {
                return SETTING_HOTKEYTHEME_CMD_QUERY;
            }
            if (value == 5) {
                return CHIME_SOUND_CMD_QUER;
            }
            return null;
        }

        public static Internal.EnumLiteMap<QueryType> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(40);
        }

        public static QueryType valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        QueryType(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum OSDVisible implements ProtocolMessageEnum {
        OSD_INVISIBLE(0, 0),
        OSD_VISIBLE(1, 1);

        public static final int OSD_INVISIBLE_VALUE = 0;
        public static final int OSD_VISIBLE_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<OSDVisible> internalValueMap = new Internal.EnumLiteMap<OSDVisible>() { // from class: com.autolink.interaction.InteractionProto.OSDVisible.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public OSDVisible findValueByNumber(int number) {
                return OSDVisible.valueOf(number);
            }
        };
        private static final OSDVisible[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static OSDVisible valueOf(int value) {
            if (value == 0) {
                return OSD_INVISIBLE;
            }
            if (value == 1) {
                return OSD_VISIBLE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<OSDVisible> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(41);
        }

        public static OSDVisible valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        OSDVisible(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum AdasAutoLearnVisible implements ProtocolMessageEnum {
        ADAS_AUTO_LEARN_INVISIBLE(0, 0),
        ADAS_AUTO_LEARN_VISIBLE(1, 1);

        public static final int ADAS_AUTO_LEARN_INVISIBLE_VALUE = 0;
        public static final int ADAS_AUTO_LEARN_VISIBLE_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<AdasAutoLearnVisible> internalValueMap = new Internal.EnumLiteMap<AdasAutoLearnVisible>() { // from class: com.autolink.interaction.InteractionProto.AdasAutoLearnVisible.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AdasAutoLearnVisible findValueByNumber(int number) {
                return AdasAutoLearnVisible.valueOf(number);
            }
        };
        private static final AdasAutoLearnVisible[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static AdasAutoLearnVisible valueOf(int value) {
            if (value == 0) {
                return ADAS_AUTO_LEARN_INVISIBLE;
            }
            if (value == 1) {
                return ADAS_AUTO_LEARN_VISIBLE;
            }
            return null;
        }

        public static Internal.EnumLiteMap<AdasAutoLearnVisible> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(42);
        }

        public static AdasAutoLearnVisible valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        AdasAutoLearnVisible(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum AdasDebugMode implements ProtocolMessageEnum {
        ADAS_DEBUG_MODE_CLOSE(0, 0),
        ADAS_DEBUG_MODE_OPEN(1, 1);

        public static final int ADAS_DEBUG_MODE_CLOSE_VALUE = 0;
        public static final int ADAS_DEBUG_MODE_OPEN_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<AdasDebugMode> internalValueMap = new Internal.EnumLiteMap<AdasDebugMode>() { // from class: com.autolink.interaction.InteractionProto.AdasDebugMode.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public AdasDebugMode findValueByNumber(int number) {
                return AdasDebugMode.valueOf(number);
            }
        };
        private static final AdasDebugMode[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static AdasDebugMode valueOf(int value) {
            if (value == 0) {
                return ADAS_DEBUG_MODE_CLOSE;
            }
            if (value == 1) {
                return ADAS_DEBUG_MODE_OPEN;
            }
            return null;
        }

        public static Internal.EnumLiteMap<AdasDebugMode> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(43);
        }

        public static AdasDebugMode valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        AdasDebugMode(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public enum ScreenOrientationValue implements ProtocolMessageEnum {
        LANDSCAPE_SCREEN(0, 0),
        VERTICAL_SCREEN(1, 1);

        public static final int LANDSCAPE_SCREEN_VALUE = 0;
        public static final int VERTICAL_SCREEN_VALUE = 1;
        private final int index;
        private final int value;
        private static Internal.EnumLiteMap<ScreenOrientationValue> internalValueMap = new Internal.EnumLiteMap<ScreenOrientationValue>() { // from class: com.autolink.interaction.InteractionProto.ScreenOrientationValue.1
            @Override // com.google.protobuf.Internal.EnumLiteMap
            public ScreenOrientationValue findValueByNumber(int number) {
                return ScreenOrientationValue.valueOf(number);
            }
        };
        private static final ScreenOrientationValue[] VALUES = values();

        @Override // com.google.protobuf.ProtocolMessageEnum, com.google.protobuf.Internal.EnumLite
        public final int getNumber() {
            return this.value;
        }

        public static ScreenOrientationValue valueOf(int value) {
            if (value == 0) {
                return LANDSCAPE_SCREEN;
            }
            if (value == 1) {
                return VERTICAL_SCREEN;
            }
            return null;
        }

        public static Internal.EnumLiteMap<ScreenOrientationValue> internalGetValueMap() {
            return internalValueMap;
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            return getDescriptor().getValues().get(this.index);
        }

        @Override // com.google.protobuf.ProtocolMessageEnum
        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return InteractionProto.getDescriptor().getEnumTypes().get(44);
        }

        public static ScreenOrientationValue valueOf(Descriptors.EnumValueDescriptor desc) {
            if (desc.getType() != getDescriptor()) {
                throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
            }
            return VALUES[desc.getIndex()];
        }

        ScreenOrientationValue(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static final class Query extends GeneratedMessage implements QueryOrBuilder {
        public static Parser<Query> PARSER = new AbstractParser<Query>() { // from class: com.autolink.interaction.InteractionProto.Query.1
            @Override // com.google.protobuf.Parser
            public Query parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Query(input, extensionRegistry);
            }
        };
        public static final int QUERY_VALUE_FIELD_NUMBER = 1;
        private static final Query defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private QueryType queryValue_;
        private final UnknownFieldSet unknownFields;

        private Query(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Query(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Query getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Query getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Query(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            QueryType value = QueryType.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.queryValue_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_Query_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_Query_fieldAccessorTable.ensureFieldAccessorsInitialized(Query.class, Builder.class);
        }

        static {
            Query query = new Query(true);
            defaultInstance = query;
            query.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Query> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.QueryOrBuilder
        public boolean hasQueryValue() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.QueryOrBuilder
        public QueryType getQueryValue() {
            return this.queryValue_;
        }

        private void initFields() {
            this.queryValue_ = QueryType.SETTING_THEME_CMD_QUERY;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.queryValue_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.queryValue_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Query parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Query parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Query parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Query parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Query parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Query parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Query parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Query parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Query parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Query parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Query prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements QueryOrBuilder {
            private int bitField0_;
            private QueryType queryValue_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_Query_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_Query_fieldAccessorTable.ensureFieldAccessorsInitialized(Query.class, Builder.class);
            }

            private Builder() {
                this.queryValue_ = QueryType.SETTING_THEME_CMD_QUERY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.queryValue_ = QueryType.SETTING_THEME_CMD_QUERY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Query.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.queryValue_ = QueryType.SETTING_THEME_CMD_QUERY;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_Query_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Query getDefaultInstanceForType() {
                return Query.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Query build() {
                Query result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Query buildPartial() {
                Query result = new Query(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.queryValue_ = this.queryValue_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Query) {
                    return mergeFrom((Query) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Query other) {
                if (other == Query.getDefaultInstance()) {
                    return this;
                }
                if (other.hasQueryValue()) {
                    setQueryValue(other.getQueryValue());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Query parsedMessage = null;
                try {
                    try {
                        parsedMessage = Query.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.QueryOrBuilder
            public boolean hasQueryValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.QueryOrBuilder
            public QueryType getQueryValue() {
                return this.queryValue_;
            }

            public Builder setQueryValue(QueryType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.queryValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearQueryValue() {
                this.bitField0_ &= -2;
                this.queryValue_ = QueryType.SETTING_THEME_CMD_QUERY;
                onChanged();
                return this;
            }
        }
    }

    public static final class Elevation extends GeneratedMessage implements ElevationOrBuilder {
        public static final int ELEVATION_UNIT_FIELD_NUMBER = 2;
        public static final int ELEVATION_VALUE_FIELD_NUMBER = 1;
        public static Parser<Elevation> PARSER = new AbstractParser<Elevation>() { // from class: com.autolink.interaction.InteractionProto.Elevation.1
            @Override // com.google.protobuf.Parser
            public Elevation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Elevation(input, extensionRegistry);
            }
        };
        private static final Elevation defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ElevationUnit elevationUnit_;
        private int elevationValue_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private Elevation(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Elevation(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Elevation getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Elevation getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Elevation(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.elevationValue_ = input.readInt32();
                            } else if (tag != 16) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue = input.readEnum();
                                ElevationUnit value = ElevationUnit.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.elevationUnit_ = value;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue);
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_Elevation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_Elevation_fieldAccessorTable.ensureFieldAccessorsInitialized(Elevation.class, Builder.class);
        }

        static {
            Elevation elevation = new Elevation(true);
            defaultInstance = elevation;
            elevation.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Elevation> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
        public boolean hasElevationValue() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
        public int getElevationValue() {
            return this.elevationValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
        public boolean hasElevationUnit() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
        public ElevationUnit getElevationUnit() {
            return this.elevationUnit_;
        }

        private void initFields() {
            this.elevationValue_ = 0;
            this.elevationUnit_ = ElevationUnit.ELEVATION_UNIT_METER;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.elevationValue_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.elevationUnit_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.elevationValue_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.elevationUnit_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Elevation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Elevation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Elevation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Elevation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Elevation parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Elevation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Elevation parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Elevation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Elevation parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Elevation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Elevation prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ElevationOrBuilder {
            private int bitField0_;
            private ElevationUnit elevationUnit_;
            private int elevationValue_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_Elevation_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_Elevation_fieldAccessorTable.ensureFieldAccessorsInitialized(Elevation.class, Builder.class);
            }

            private Builder() {
                this.elevationUnit_ = ElevationUnit.ELEVATION_UNIT_METER;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.elevationUnit_ = ElevationUnit.ELEVATION_UNIT_METER;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Elevation.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.elevationValue_ = 0;
                this.bitField0_ &= -2;
                this.elevationUnit_ = ElevationUnit.ELEVATION_UNIT_METER;
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_Elevation_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Elevation getDefaultInstanceForType() {
                return Elevation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Elevation build() {
                Elevation result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Elevation buildPartial() {
                Elevation result = new Elevation(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.elevationValue_ = this.elevationValue_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.elevationUnit_ = this.elevationUnit_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Elevation) {
                    return mergeFrom((Elevation) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Elevation other) {
                if (other == Elevation.getDefaultInstance()) {
                    return this;
                }
                if (other.hasElevationValue()) {
                    setElevationValue(other.getElevationValue());
                }
                if (other.hasElevationUnit()) {
                    setElevationUnit(other.getElevationUnit());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Elevation parsedMessage = null;
                try {
                    try {
                        parsedMessage = Elevation.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
            public boolean hasElevationValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
            public int getElevationValue() {
                return this.elevationValue_;
            }

            public Builder setElevationValue(int value) {
                this.bitField0_ |= 1;
                this.elevationValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearElevationValue() {
                this.bitField0_ &= -2;
                this.elevationValue_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
            public boolean hasElevationUnit() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.ElevationOrBuilder
            public ElevationUnit getElevationUnit() {
                return this.elevationUnit_;
            }

            public Builder setElevationUnit(ElevationUnit value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.elevationUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearElevationUnit() {
                this.bitField0_ &= -3;
                this.elevationUnit_ = ElevationUnit.ELEVATION_UNIT_METER;
                onChanged();
                return this;
            }
        }
    }

    public static final class Slope extends GeneratedMessage implements SlopeOrBuilder {
        public static Parser<Slope> PARSER = new AbstractParser<Slope>() { // from class: com.autolink.interaction.InteractionProto.Slope.1
            @Override // com.google.protobuf.Parser
            public Slope parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Slope(input, extensionRegistry);
            }
        };
        public static final int SLOPE_UNIT_FIELD_NUMBER = 2;
        public static final int SLOPE_VALUE_FIELD_NUMBER = 1;
        private static final Slope defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private SlopeUnit slopeUnit_;
        private int slopeValue_;
        private final UnknownFieldSet unknownFields;

        private Slope(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Slope(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Slope getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Slope getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Slope(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                this.bitField0_ |= 1;
                                this.slopeValue_ = input.readInt32();
                            } else if (tag != 16) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue = input.readEnum();
                                SlopeUnit value = SlopeUnit.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.slopeUnit_ = value;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue);
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_Slope_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_Slope_fieldAccessorTable.ensureFieldAccessorsInitialized(Slope.class, Builder.class);
        }

        static {
            Slope slope = new Slope(true);
            defaultInstance = slope;
            slope.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Slope> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
        public boolean hasSlopeValue() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
        public int getSlopeValue() {
            return this.slopeValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
        public boolean hasSlopeUnit() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
        public SlopeUnit getSlopeUnit() {
            return this.slopeUnit_;
        }

        private void initFields() {
            this.slopeValue_ = 0;
            this.slopeUnit_ = SlopeUnit.SLOPE_UNIT_DEGREE;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.slopeValue_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.slopeUnit_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.slopeValue_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.slopeUnit_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Slope parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Slope parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Slope parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Slope parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Slope parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Slope parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Slope parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Slope parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Slope parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Slope parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Slope prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements SlopeOrBuilder {
            private int bitField0_;
            private SlopeUnit slopeUnit_;
            private int slopeValue_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_Slope_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_Slope_fieldAccessorTable.ensureFieldAccessorsInitialized(Slope.class, Builder.class);
            }

            private Builder() {
                this.slopeUnit_ = SlopeUnit.SLOPE_UNIT_DEGREE;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.slopeUnit_ = SlopeUnit.SLOPE_UNIT_DEGREE;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Slope.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.slopeValue_ = 0;
                this.bitField0_ &= -2;
                this.slopeUnit_ = SlopeUnit.SLOPE_UNIT_DEGREE;
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_Slope_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Slope getDefaultInstanceForType() {
                return Slope.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Slope build() {
                Slope result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Slope buildPartial() {
                Slope result = new Slope(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.slopeValue_ = this.slopeValue_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.slopeUnit_ = this.slopeUnit_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Slope) {
                    return mergeFrom((Slope) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Slope other) {
                if (other == Slope.getDefaultInstance()) {
                    return this;
                }
                if (other.hasSlopeValue()) {
                    setSlopeValue(other.getSlopeValue());
                }
                if (other.hasSlopeUnit()) {
                    setSlopeUnit(other.getSlopeUnit());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Slope parsedMessage = null;
                try {
                    try {
                        parsedMessage = Slope.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
            public boolean hasSlopeValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
            public int getSlopeValue() {
                return this.slopeValue_;
            }

            public Builder setSlopeValue(int value) {
                this.bitField0_ |= 1;
                this.slopeValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearSlopeValue() {
                this.bitField0_ &= -2;
                this.slopeValue_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
            public boolean hasSlopeUnit() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.SlopeOrBuilder
            public SlopeUnit getSlopeUnit() {
                return this.slopeUnit_;
            }

            public Builder setSlopeUnit(SlopeUnit value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.slopeUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearSlopeUnit() {
                this.bitField0_ &= -3;
                this.slopeUnit_ = SlopeUnit.SLOPE_UNIT_DEGREE;
                onChanged();
                return this;
            }
        }
    }

    public static final class BluetoothPhone extends GeneratedMessage implements BluetoothPhoneOrBuilder {
        public static final int CALL_TIME_FIELD_NUMBER = 4;
        public static final int CONTACT_NAME_FIELD_NUMBER = 2;
        public static final int CONTACT_NUMBER_FIELD_NUMBER = 3;
        public static Parser<BluetoothPhone> PARSER = new AbstractParser<BluetoothPhone>() { // from class: com.autolink.interaction.InteractionProto.BluetoothPhone.1
            @Override // com.google.protobuf.Parser
            public BluetoothPhone parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new BluetoothPhone(input, extensionRegistry);
            }
        };
        public static final int PHONE_DISPLAY_ICON_FIELD_NUMBER = 1;
        public static final int P_VISIBLE_FIELD_NUMBER = 5;
        public static final int STATUS_FIELD_NUMBER = 8;
        public static final int TIME_VISIBLE_FIELD_NUMBER = 6;
        public static final int TYPE_FIELD_NUMBER = 7;
        private static final BluetoothPhone defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int callTime_;
        private Object contactName_;
        private Object contactNumber_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private PhoneNumberVisible pVisible_;
        private IconDisply phoneDisplayIcon_;
        private CallStatus status_;
        private PhoneTimeVisible timeVisible_;
        private int type_;
        private final UnknownFieldSet unknownFields;

        private BluetoothPhone(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private BluetoothPhone(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static BluetoothPhone getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public BluetoothPhone getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private BluetoothPhone(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            IconDisply value = IconDisply.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.phoneDisplayIcon_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        } else if (tag == 18) {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.contactName_ = bs;
                        } else if (tag == 26) {
                            ByteString bs2 = input.readBytes();
                            this.bitField0_ |= 4;
                            this.contactNumber_ = bs2;
                        } else if (tag == 32) {
                            this.bitField0_ = 8 | this.bitField0_;
                            this.callTime_ = input.readInt32();
                        } else if (tag == 40) {
                            int rawValue2 = input.readEnum();
                            PhoneNumberVisible value2 = PhoneNumberVisible.valueOf(rawValue2);
                            if (value2 == null) {
                                unknownFields.mergeVarintField(5, rawValue2);
                            } else {
                                this.bitField0_ |= 16;
                                this.pVisible_ = value2;
                            }
                        } else if (tag == 48) {
                            int rawValue3 = input.readEnum();
                            PhoneTimeVisible value3 = PhoneTimeVisible.valueOf(rawValue3);
                            if (value3 == null) {
                                unknownFields.mergeVarintField(6, rawValue3);
                            } else {
                                this.bitField0_ = 32 | this.bitField0_;
                                this.timeVisible_ = value3;
                            }
                        } else if (tag == 56) {
                            this.bitField0_ |= 64;
                            this.type_ = input.readInt32();
                        } else if (tag != 64) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue4 = input.readEnum();
                            CallStatus value4 = CallStatus.valueOf(rawValue4);
                            if (value4 == null) {
                                unknownFields.mergeVarintField(8, rawValue4);
                            } else {
                                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                                this.status_ = value4;
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_BluetoothPhone_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_BluetoothPhone_fieldAccessorTable.ensureFieldAccessorsInitialized(BluetoothPhone.class, Builder.class);
        }

        static {
            BluetoothPhone bluetoothPhone = new BluetoothPhone(true);
            defaultInstance = bluetoothPhone;
            bluetoothPhone.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<BluetoothPhone> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasPhoneDisplayIcon() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public IconDisply getPhoneDisplayIcon() {
            return this.phoneDisplayIcon_;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasContactName() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public String getContactName() {
            Object ref = this.contactName_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.contactName_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public ByteString getContactNameBytes() {
            Object ref = this.contactName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.contactName_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasContactNumber() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public String getContactNumber() {
            Object ref = this.contactNumber_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.contactNumber_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public ByteString getContactNumberBytes() {
            Object ref = this.contactNumber_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.contactNumber_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasCallTime() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public int getCallTime() {
            return this.callTime_;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasPVisible() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public PhoneNumberVisible getPVisible() {
            return this.pVisible_;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasTimeVisible() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public PhoneTimeVisible getTimeVisible() {
            return this.timeVisible_;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasType() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public int getType() {
            return this.type_;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public boolean hasStatus() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
        public CallStatus getStatus() {
            return this.status_;
        }

        private void initFields() {
            this.phoneDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
            this.contactName_ = "";
            this.contactNumber_ = "";
            this.callTime_ = 0;
            this.pVisible_ = PhoneNumberVisible.PHONE_INVISIBLE;
            this.timeVisible_ = PhoneTimeVisible.CALL_TIME_INVISIBLE;
            this.type_ = 0;
            this.status_ = CallStatus.IDLE;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.phoneDisplayIcon_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, getContactNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, getContactNumberBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.callTime_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.pVisible_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeEnum(6, this.timeVisible_.getNumber());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeInt32(7, this.type_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeEnum(8, this.status_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.phoneDisplayIcon_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, getContactNameBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, getContactNumberBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.callTime_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.pVisible_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeEnumSize(6, this.timeVisible_.getNumber());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeInt32Size(7, this.type_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeEnumSize(8, this.status_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static BluetoothPhone parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BluetoothPhone parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BluetoothPhone parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static BluetoothPhone parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static BluetoothPhone parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static BluetoothPhone parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static BluetoothPhone parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static BluetoothPhone parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static BluetoothPhone parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static BluetoothPhone parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(BluetoothPhone prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements BluetoothPhoneOrBuilder {
            private int bitField0_;
            private int callTime_;
            private Object contactName_;
            private Object contactNumber_;
            private PhoneNumberVisible pVisible_;
            private IconDisply phoneDisplayIcon_;
            private CallStatus status_;
            private PhoneTimeVisible timeVisible_;
            private int type_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_BluetoothPhone_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_BluetoothPhone_fieldAccessorTable.ensureFieldAccessorsInitialized(BluetoothPhone.class, Builder.class);
            }

            private Builder() {
                this.phoneDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                this.contactName_ = "";
                this.contactNumber_ = "";
                this.pVisible_ = PhoneNumberVisible.PHONE_INVISIBLE;
                this.timeVisible_ = PhoneTimeVisible.CALL_TIME_INVISIBLE;
                this.status_ = CallStatus.IDLE;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.phoneDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                this.contactName_ = "";
                this.contactNumber_ = "";
                this.pVisible_ = PhoneNumberVisible.PHONE_INVISIBLE;
                this.timeVisible_ = PhoneTimeVisible.CALL_TIME_INVISIBLE;
                this.status_ = CallStatus.IDLE;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = BluetoothPhone.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.phoneDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.contactName_ = "";
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.contactNumber_ = "";
                int i3 = i2 & (-5);
                this.bitField0_ = i3;
                this.callTime_ = 0;
                this.bitField0_ = i3 & (-9);
                this.pVisible_ = PhoneNumberVisible.PHONE_INVISIBLE;
                this.bitField0_ &= -17;
                this.timeVisible_ = PhoneTimeVisible.CALL_TIME_INVISIBLE;
                int i4 = this.bitField0_ & (-33);
                this.bitField0_ = i4;
                this.type_ = 0;
                this.bitField0_ = i4 & (-65);
                this.status_ = CallStatus.IDLE;
                this.bitField0_ &= -129;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_BluetoothPhone_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public BluetoothPhone getDefaultInstanceForType() {
                return BluetoothPhone.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BluetoothPhone build() {
                BluetoothPhone result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public BluetoothPhone buildPartial() {
                BluetoothPhone result = new BluetoothPhone(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.phoneDisplayIcon_ = this.phoneDisplayIcon_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.contactName_ = this.contactName_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.contactNumber_ = this.contactNumber_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.callTime_ = this.callTime_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.pVisible_ = this.pVisible_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.timeVisible_ = this.timeVisible_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.type_ = this.type_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.status_ = this.status_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof BluetoothPhone) {
                    return mergeFrom((BluetoothPhone) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(BluetoothPhone other) {
                if (other == BluetoothPhone.getDefaultInstance()) {
                    return this;
                }
                if (other.hasPhoneDisplayIcon()) {
                    setPhoneDisplayIcon(other.getPhoneDisplayIcon());
                }
                if (other.hasContactName()) {
                    this.bitField0_ |= 2;
                    this.contactName_ = other.contactName_;
                    onChanged();
                }
                if (other.hasContactNumber()) {
                    this.bitField0_ |= 4;
                    this.contactNumber_ = other.contactNumber_;
                    onChanged();
                }
                if (other.hasCallTime()) {
                    setCallTime(other.getCallTime());
                }
                if (other.hasPVisible()) {
                    setPVisible(other.getPVisible());
                }
                if (other.hasTimeVisible()) {
                    setTimeVisible(other.getTimeVisible());
                }
                if (other.hasType()) {
                    setType(other.getType());
                }
                if (other.hasStatus()) {
                    setStatus(other.getStatus());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                BluetoothPhone parsedMessage = null;
                try {
                    try {
                        parsedMessage = BluetoothPhone.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasPhoneDisplayIcon() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public IconDisply getPhoneDisplayIcon() {
                return this.phoneDisplayIcon_;
            }

            public Builder setPhoneDisplayIcon(IconDisply value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.phoneDisplayIcon_ = value;
                onChanged();
                return this;
            }

            public Builder clearPhoneDisplayIcon() {
                this.bitField0_ &= -2;
                this.phoneDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasContactName() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public String getContactName() {
                Object ref = this.contactName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.contactName_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public ByteString getContactNameBytes() {
                Object ref = this.contactName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.contactName_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setContactName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.contactName_ = value;
                onChanged();
                return this;
            }

            public Builder clearContactName() {
                this.bitField0_ &= -3;
                this.contactName_ = BluetoothPhone.getDefaultInstance().getContactName();
                onChanged();
                return this;
            }

            public Builder setContactNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.contactName_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasContactNumber() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public String getContactNumber() {
                Object ref = this.contactNumber_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.contactNumber_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public ByteString getContactNumberBytes() {
                Object ref = this.contactNumber_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.contactNumber_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setContactNumber(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.contactNumber_ = value;
                onChanged();
                return this;
            }

            public Builder clearContactNumber() {
                this.bitField0_ &= -5;
                this.contactNumber_ = BluetoothPhone.getDefaultInstance().getContactNumber();
                onChanged();
                return this;
            }

            public Builder setContactNumberBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.contactNumber_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasCallTime() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public int getCallTime() {
                return this.callTime_;
            }

            public Builder setCallTime(int value) {
                this.bitField0_ |= 8;
                this.callTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearCallTime() {
                this.bitField0_ &= -9;
                this.callTime_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasPVisible() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public PhoneNumberVisible getPVisible() {
                return this.pVisible_;
            }

            public Builder setPVisible(PhoneNumberVisible value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.pVisible_ = value;
                onChanged();
                return this;
            }

            public Builder clearPVisible() {
                this.bitField0_ &= -17;
                this.pVisible_ = PhoneNumberVisible.PHONE_INVISIBLE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasTimeVisible() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public PhoneTimeVisible getTimeVisible() {
                return this.timeVisible_;
            }

            public Builder setTimeVisible(PhoneTimeVisible value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.timeVisible_ = value;
                onChanged();
                return this;
            }

            public Builder clearTimeVisible() {
                this.bitField0_ &= -33;
                this.timeVisible_ = PhoneTimeVisible.CALL_TIME_INVISIBLE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasType() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public int getType() {
                return this.type_;
            }

            public Builder setType(int value) {
                this.bitField0_ |= 64;
                this.type_ = value;
                onChanged();
                return this;
            }

            public Builder clearType() {
                this.bitField0_ &= -65;
                this.type_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public boolean hasStatus() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // com.autolink.interaction.InteractionProto.BluetoothPhoneOrBuilder
            public CallStatus getStatus() {
                return this.status_;
            }

            public Builder setStatus(CallStatus value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.status_ = value;
                onChanged();
                return this;
            }

            public Builder clearStatus() {
                this.bitField0_ &= -129;
                this.status_ = CallStatus.IDLE;
                onChanged();
                return this;
            }
        }
    }

    public static final class Music extends GeneratedMessage implements MusicOrBuilder {
        public static final int ALBUM_PATH_FIELD_NUMBER = 8;
        public static final int ARTIST_NAME_FIELD_NUMBER = 9;
        public static final int MEDIA_TEXT_FIELD_NUMBER = 2;
        public static final int MUSIC_DISPLAY_ICON_FIELD_NUMBER = 1;
        public static final int M_PLAY_INTERFACE_FIELD_NUMBER = 7;
        public static final int M_PLAY_SOURCE_FIELD_NUMBER = 6;
        public static final int M_STATUS_FIELD_NUMBER = 3;
        public static final int M_VISIBLE_FIELD_NUMBER = 4;
        public static Parser<Music> PARSER = new AbstractParser<Music>() { // from class: com.autolink.interaction.InteractionProto.Music.1
            @Override // com.google.protobuf.Parser
            public Music parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Music(input, extensionRegistry);
            }
        };
        public static final int ROLL_FIELD_NUMBER = 5;
        private static final Music defaultInstance;
        private static final long serialVersionUID = 0;
        private Object albumPath_;
        private Object artistName_;
        private int bitField0_;
        private PlayInterface mPlayInterface_;
        private int mPlaySource_;
        private PlayStatus mStatus_;
        private Visible mVisible_;
        private Object mediaText_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private IconDisply musicDisplayIcon_;
        private MusicNameRoll roll_;
        private final UnknownFieldSet unknownFields;

        private Music(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Music(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Music getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Music getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Music(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            IconDisply value = IconDisply.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.musicDisplayIcon_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        } else if (tag == 18) {
                            ByteString bs = input.readBytes();
                            this.bitField0_ |= 2;
                            this.mediaText_ = bs;
                        } else if (tag == 24) {
                            int rawValue2 = input.readEnum();
                            PlayStatus value2 = PlayStatus.valueOf(rawValue2);
                            if (value2 == null) {
                                unknownFields.mergeVarintField(3, rawValue2);
                            } else {
                                this.bitField0_ = 4 | this.bitField0_;
                                this.mStatus_ = value2;
                            }
                        } else if (tag == 32) {
                            int rawValue3 = input.readEnum();
                            Visible value3 = Visible.valueOf(rawValue3);
                            if (value3 == null) {
                                unknownFields.mergeVarintField(4, rawValue3);
                            } else {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.mVisible_ = value3;
                            }
                        } else if (tag == 40) {
                            int rawValue4 = input.readEnum();
                            MusicNameRoll value4 = MusicNameRoll.valueOf(rawValue4);
                            if (value4 == null) {
                                unknownFields.mergeVarintField(5, rawValue4);
                            } else {
                                this.bitField0_ |= 16;
                                this.roll_ = value4;
                            }
                        } else if (tag == 48) {
                            this.bitField0_ |= 32;
                            this.mPlaySource_ = input.readInt32();
                        } else if (tag == 56) {
                            int rawValue5 = input.readEnum();
                            PlayInterface value5 = PlayInterface.valueOf(rawValue5);
                            if (value5 == null) {
                                unknownFields.mergeVarintField(7, rawValue5);
                            } else {
                                this.bitField0_ |= 64;
                                this.mPlayInterface_ = value5;
                            }
                        } else if (tag == 66) {
                            ByteString bs2 = input.readBytes();
                            this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                            this.albumPath_ = bs2;
                        } else if (tag != 74) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            ByteString bs3 = input.readBytes();
                            this.bitField0_ |= 256;
                            this.artistName_ = bs3;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_Music_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_Music_fieldAccessorTable.ensureFieldAccessorsInitialized(Music.class, Builder.class);
        }

        static {
            Music music = new Music(true);
            defaultInstance = music;
            music.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Music> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasMusicDisplayIcon() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public IconDisply getMusicDisplayIcon() {
            return this.musicDisplayIcon_;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasMediaText() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public String getMediaText() {
            Object ref = this.mediaText_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.mediaText_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public ByteString getMediaTextBytes() {
            Object ref = this.mediaText_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.mediaText_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasMStatus() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public PlayStatus getMStatus() {
            return this.mStatus_;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasMVisible() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public Visible getMVisible() {
            return this.mVisible_;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasRoll() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public MusicNameRoll getRoll() {
            return this.roll_;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasMPlaySource() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public int getMPlaySource() {
            return this.mPlaySource_;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasMPlayInterface() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public PlayInterface getMPlayInterface() {
            return this.mPlayInterface_;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasAlbumPath() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public String getAlbumPath() {
            Object ref = this.albumPath_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.albumPath_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public ByteString getAlbumPathBytes() {
            Object ref = this.albumPath_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.albumPath_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public boolean hasArtistName() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public String getArtistName() {
            Object ref = this.artistName_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.artistName_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
        public ByteString getArtistNameBytes() {
            Object ref = this.artistName_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.artistName_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        private void initFields() {
            this.musicDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
            this.mediaText_ = "";
            this.mStatus_ = PlayStatus.DEFAULT;
            this.mVisible_ = Visible.INVISIBLE;
            this.roll_ = MusicNameRoll.ROLL;
            this.mPlaySource_ = 0;
            this.mPlayInterface_ = PlayInterface.FRONT_DESK_PLAY;
            this.albumPath_ = "";
            this.artistName_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.musicDisplayIcon_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeBytes(2, getMediaTextBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.mStatus_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.mVisible_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.roll_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.mPlaySource_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeEnum(7, this.mPlayInterface_.getNumber());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeBytes(8, getAlbumPathBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeBytes(9, getArtistNameBytes());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.musicDisplayIcon_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeBytesSize(2, getMediaTextBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.mStatus_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.mVisible_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.roll_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.mPlaySource_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeEnumSize(7, this.mPlayInterface_.getNumber());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeBytesSize(8, getAlbumPathBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeBytesSize(9, getArtistNameBytes());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Music parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Music parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Music parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Music parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Music parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Music parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Music parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Music parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Music parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Music parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Music prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements MusicOrBuilder {
            private Object albumPath_;
            private Object artistName_;
            private int bitField0_;
            private PlayInterface mPlayInterface_;
            private int mPlaySource_;
            private PlayStatus mStatus_;
            private Visible mVisible_;
            private Object mediaText_;
            private IconDisply musicDisplayIcon_;
            private MusicNameRoll roll_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_Music_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_Music_fieldAccessorTable.ensureFieldAccessorsInitialized(Music.class, Builder.class);
            }

            private Builder() {
                this.musicDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                this.mediaText_ = "";
                this.mStatus_ = PlayStatus.DEFAULT;
                this.mVisible_ = Visible.INVISIBLE;
                this.roll_ = MusicNameRoll.ROLL;
                this.mPlayInterface_ = PlayInterface.FRONT_DESK_PLAY;
                this.albumPath_ = "";
                this.artistName_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.musicDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                this.mediaText_ = "";
                this.mStatus_ = PlayStatus.DEFAULT;
                this.mVisible_ = Visible.INVISIBLE;
                this.roll_ = MusicNameRoll.ROLL;
                this.mPlayInterface_ = PlayInterface.FRONT_DESK_PLAY;
                this.albumPath_ = "";
                this.artistName_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Music.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.musicDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.mediaText_ = "";
                this.bitField0_ = i & (-3);
                this.mStatus_ = PlayStatus.DEFAULT;
                this.bitField0_ &= -5;
                this.mVisible_ = Visible.INVISIBLE;
                this.bitField0_ &= -9;
                this.roll_ = MusicNameRoll.ROLL;
                int i2 = this.bitField0_ & (-17);
                this.bitField0_ = i2;
                this.mPlaySource_ = 0;
                this.bitField0_ = i2 & (-33);
                this.mPlayInterface_ = PlayInterface.FRONT_DESK_PLAY;
                int i3 = this.bitField0_ & (-65);
                this.bitField0_ = i3;
                this.albumPath_ = "";
                int i4 = i3 & (-129);
                this.bitField0_ = i4;
                this.artistName_ = "";
                this.bitField0_ = i4 & (-257);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_Music_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Music getDefaultInstanceForType() {
                return Music.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Music build() {
                Music result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Music buildPartial() {
                Music result = new Music(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.musicDisplayIcon_ = this.musicDisplayIcon_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.mediaText_ = this.mediaText_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.mStatus_ = this.mStatus_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.mVisible_ = this.mVisible_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.roll_ = this.roll_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.mPlaySource_ = this.mPlaySource_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.mPlayInterface_ = this.mPlayInterface_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.albumPath_ = this.albumPath_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 256;
                }
                result.artistName_ = this.artistName_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Music) {
                    return mergeFrom((Music) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Music other) {
                if (other == Music.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMusicDisplayIcon()) {
                    setMusicDisplayIcon(other.getMusicDisplayIcon());
                }
                if (other.hasMediaText()) {
                    this.bitField0_ |= 2;
                    this.mediaText_ = other.mediaText_;
                    onChanged();
                }
                if (other.hasMStatus()) {
                    setMStatus(other.getMStatus());
                }
                if (other.hasMVisible()) {
                    setMVisible(other.getMVisible());
                }
                if (other.hasRoll()) {
                    setRoll(other.getRoll());
                }
                if (other.hasMPlaySource()) {
                    setMPlaySource(other.getMPlaySource());
                }
                if (other.hasMPlayInterface()) {
                    setMPlayInterface(other.getMPlayInterface());
                }
                if (other.hasAlbumPath()) {
                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                    this.albumPath_ = other.albumPath_;
                    onChanged();
                }
                if (other.hasArtistName()) {
                    this.bitField0_ |= 256;
                    this.artistName_ = other.artistName_;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Music parsedMessage = null;
                try {
                    try {
                        parsedMessage = Music.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasMusicDisplayIcon() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public IconDisply getMusicDisplayIcon() {
                return this.musicDisplayIcon_;
            }

            public Builder setMusicDisplayIcon(IconDisply value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.musicDisplayIcon_ = value;
                onChanged();
                return this;
            }

            public Builder clearMusicDisplayIcon() {
                this.bitField0_ &= -2;
                this.musicDisplayIcon_ = IconDisply.ICON_DISPLY_OFF;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasMediaText() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public String getMediaText() {
                Object ref = this.mediaText_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.mediaText_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public ByteString getMediaTextBytes() {
                Object ref = this.mediaText_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.mediaText_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setMediaText(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.mediaText_ = value;
                onChanged();
                return this;
            }

            public Builder clearMediaText() {
                this.bitField0_ &= -3;
                this.mediaText_ = Music.getDefaultInstance().getMediaText();
                onChanged();
                return this;
            }

            public Builder setMediaTextBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.mediaText_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasMStatus() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public PlayStatus getMStatus() {
                return this.mStatus_;
            }

            public Builder setMStatus(PlayStatus value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.mStatus_ = value;
                onChanged();
                return this;
            }

            public Builder clearMStatus() {
                this.bitField0_ &= -5;
                this.mStatus_ = PlayStatus.DEFAULT;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasMVisible() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public Visible getMVisible() {
                return this.mVisible_;
            }

            public Builder setMVisible(Visible value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.mVisible_ = value;
                onChanged();
                return this;
            }

            public Builder clearMVisible() {
                this.bitField0_ &= -9;
                this.mVisible_ = Visible.INVISIBLE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasRoll() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public MusicNameRoll getRoll() {
                return this.roll_;
            }

            public Builder setRoll(MusicNameRoll value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.roll_ = value;
                onChanged();
                return this;
            }

            public Builder clearRoll() {
                this.bitField0_ &= -17;
                this.roll_ = MusicNameRoll.ROLL;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasMPlaySource() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public int getMPlaySource() {
                return this.mPlaySource_;
            }

            public Builder setMPlaySource(int value) {
                this.bitField0_ |= 32;
                this.mPlaySource_ = value;
                onChanged();
                return this;
            }

            public Builder clearMPlaySource() {
                this.bitField0_ &= -33;
                this.mPlaySource_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasMPlayInterface() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public PlayInterface getMPlayInterface() {
                return this.mPlayInterface_;
            }

            public Builder setMPlayInterface(PlayInterface value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.mPlayInterface_ = value;
                onChanged();
                return this;
            }

            public Builder clearMPlayInterface() {
                this.bitField0_ &= -65;
                this.mPlayInterface_ = PlayInterface.FRONT_DESK_PLAY;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasAlbumPath() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public String getAlbumPath() {
                Object ref = this.albumPath_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.albumPath_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public ByteString getAlbumPathBytes() {
                Object ref = this.albumPath_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.albumPath_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setAlbumPath(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.albumPath_ = value;
                onChanged();
                return this;
            }

            public Builder clearAlbumPath() {
                this.bitField0_ &= -129;
                this.albumPath_ = Music.getDefaultInstance().getAlbumPath();
                onChanged();
                return this;
            }

            public Builder setAlbumPathBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.albumPath_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public boolean hasArtistName() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public String getArtistName() {
                Object ref = this.artistName_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.artistName_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.MusicOrBuilder
            public ByteString getArtistNameBytes() {
                Object ref = this.artistName_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.artistName_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setArtistName(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.artistName_ = value;
                onChanged();
                return this;
            }

            public Builder clearArtistName() {
                this.bitField0_ &= -257;
                this.artistName_ = Music.getDefaultInstance().getArtistName();
                onChanged();
                return this;
            }

            public Builder setArtistNameBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.artistName_ = value;
                onChanged();
                return this;
            }
        }
    }

    public static final class MediaProgress extends GeneratedMessage implements MediaProgressOrBuilder {
        public static final int CURRENT_PROGRESS_FIELD_NUMBER = 1;
        public static final int MAX_PROGRESS_FIELD_NUMBER = 2;
        public static Parser<MediaProgress> PARSER = new AbstractParser<MediaProgress>() { // from class: com.autolink.interaction.InteractionProto.MediaProgress.1
            @Override // com.google.protobuf.Parser
            public MediaProgress parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new MediaProgress(input, extensionRegistry);
            }
        };
        private static final MediaProgress defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int currentProgress_;
        private int maxProgress_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private MediaProgress(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private MediaProgress(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MediaProgress getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MediaProgress getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MediaProgress(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            this.bitField0_ |= 1;
                            this.currentProgress_ = input.readInt32();
                        } else if (tag != 16) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 2;
                            this.maxProgress_ = input.readInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_MediaProgress_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_MediaProgress_fieldAccessorTable.ensureFieldAccessorsInitialized(MediaProgress.class, Builder.class);
        }

        static {
            MediaProgress mediaProgress = new MediaProgress(true);
            defaultInstance = mediaProgress;
            mediaProgress.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MediaProgress> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
        public boolean hasCurrentProgress() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
        public int getCurrentProgress() {
            return this.currentProgress_;
        }

        @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
        public boolean hasMaxProgress() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
        public int getMaxProgress() {
            return this.maxProgress_;
        }

        private void initFields() {
            this.currentProgress_ = 0;
            this.maxProgress_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.currentProgress_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.maxProgress_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.currentProgress_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.maxProgress_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MediaProgress parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MediaProgress parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MediaProgress parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MediaProgress parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MediaProgress parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MediaProgress parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static MediaProgress parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static MediaProgress parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static MediaProgress parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MediaProgress parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(MediaProgress prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements MediaProgressOrBuilder {
            private int bitField0_;
            private int currentProgress_;
            private int maxProgress_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_MediaProgress_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_MediaProgress_fieldAccessorTable.ensureFieldAccessorsInitialized(MediaProgress.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MediaProgress.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.currentProgress_ = 0;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.maxProgress_ = 0;
                this.bitField0_ = i & (-3);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_MediaProgress_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MediaProgress getDefaultInstanceForType() {
                return MediaProgress.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MediaProgress build() {
                MediaProgress result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MediaProgress buildPartial() {
                MediaProgress result = new MediaProgress(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.currentProgress_ = this.currentProgress_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.maxProgress_ = this.maxProgress_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof MediaProgress) {
                    return mergeFrom((MediaProgress) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MediaProgress other) {
                if (other == MediaProgress.getDefaultInstance()) {
                    return this;
                }
                if (other.hasCurrentProgress()) {
                    setCurrentProgress(other.getCurrentProgress());
                }
                if (other.hasMaxProgress()) {
                    setMaxProgress(other.getMaxProgress());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                MediaProgress parsedMessage = null;
                try {
                    try {
                        parsedMessage = MediaProgress.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
            public boolean hasCurrentProgress() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
            public int getCurrentProgress() {
                return this.currentProgress_;
            }

            public Builder setCurrentProgress(int value) {
                this.bitField0_ |= 1;
                this.currentProgress_ = value;
                onChanged();
                return this;
            }

            public Builder clearCurrentProgress() {
                this.bitField0_ &= -2;
                this.currentProgress_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
            public boolean hasMaxProgress() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.MediaProgressOrBuilder
            public int getMaxProgress() {
                return this.maxProgress_;
            }

            public Builder setMaxProgress(int value) {
                this.bitField0_ |= 2;
                this.maxProgress_ = value;
                onChanged();
                return this;
            }

            public Builder clearMaxProgress() {
                this.bitField0_ &= -3;
                this.maxProgress_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class Clock extends GeneratedMessage implements ClockOrBuilder {
        public static final int AMPM_DISPLAY_FIELD_NUMBER = 2;
        public static Parser<Clock> PARSER = new AbstractParser<Clock>() { // from class: com.autolink.interaction.InteractionProto.Clock.1
            @Override // com.google.protobuf.Parser
            public Clock parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Clock(input, extensionRegistry);
            }
        };
        public static final int TIME_FORMAT_FIELD_NUMBER = 1;
        public static final int TIME_HOUR_FIELD_NUMBER = 3;
        public static final int TIME_MINUTE_FIELD_NUMBER = 4;
        public static final int TIME_SECOND_FIELD_NUMBER = 5;
        private static final Clock defaultInstance;
        private static final long serialVersionUID = 0;
        private AmPmDisplay ampmDisplay_;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private ClockFormat timeFormat_;
        private int timeHour_;
        private int timeMinute_;
        private int timeSecond_;
        private final UnknownFieldSet unknownFields;

        private Clock(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Clock(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Clock getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Clock getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Clock(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                ClockFormat value = ClockFormat.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.timeFormat_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag == 16) {
                                int rawValue2 = input.readEnum();
                                AmPmDisplay value2 = AmPmDisplay.valueOf(rawValue2);
                                if (value2 != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.ampmDisplay_ = value2;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue2);
                                }
                            } else if (tag == 24) {
                                this.bitField0_ |= 4;
                                this.timeHour_ = input.readInt32();
                            } else if (tag == 32) {
                                this.bitField0_ = 8 | this.bitField0_;
                                this.timeMinute_ = input.readInt32();
                            } else if (tag != 40) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                this.bitField0_ |= 16;
                                this.timeSecond_ = input.readInt32();
                            }
                        } catch (InvalidProtocolBufferException e) {
                            throw e.setUnfinishedMessage(this);
                        }
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_Clock_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_Clock_fieldAccessorTable.ensureFieldAccessorsInitialized(Clock.class, Builder.class);
        }

        static {
            Clock clock = new Clock(true);
            defaultInstance = clock;
            clock.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Clock> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public boolean hasTimeFormat() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public ClockFormat getTimeFormat() {
            return this.timeFormat_;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public boolean hasAmpmDisplay() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public AmPmDisplay getAmpmDisplay() {
            return this.ampmDisplay_;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public boolean hasTimeHour() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public int getTimeHour() {
            return this.timeHour_;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public boolean hasTimeMinute() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public int getTimeMinute() {
            return this.timeMinute_;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public boolean hasTimeSecond() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
        public int getTimeSecond() {
            return this.timeSecond_;
        }

        private void initFields() {
            this.timeFormat_ = ClockFormat.FORMAT12;
            this.ampmDisplay_ = AmPmDisplay.DISPLAY;
            this.timeHour_ = 0;
            this.timeMinute_ = 0;
            this.timeSecond_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.timeFormat_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.ampmDisplay_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeInt32(3, this.timeHour_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeInt32(4, this.timeMinute_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeInt32(5, this.timeSecond_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.timeFormat_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.ampmDisplay_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeInt32Size(3, this.timeHour_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeInt32Size(4, this.timeMinute_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeInt32Size(5, this.timeSecond_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Clock parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Clock parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Clock parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Clock parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Clock parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Clock parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Clock parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Clock parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Clock parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Clock parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Clock prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ClockOrBuilder {
            private AmPmDisplay ampmDisplay_;
            private int bitField0_;
            private ClockFormat timeFormat_;
            private int timeHour_;
            private int timeMinute_;
            private int timeSecond_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_Clock_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_Clock_fieldAccessorTable.ensureFieldAccessorsInitialized(Clock.class, Builder.class);
            }

            private Builder() {
                this.timeFormat_ = ClockFormat.FORMAT12;
                this.ampmDisplay_ = AmPmDisplay.DISPLAY;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.timeFormat_ = ClockFormat.FORMAT12;
                this.ampmDisplay_ = AmPmDisplay.DISPLAY;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Clock.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.timeFormat_ = ClockFormat.FORMAT12;
                this.bitField0_ &= -2;
                this.ampmDisplay_ = AmPmDisplay.DISPLAY;
                int i = this.bitField0_ & (-3);
                this.bitField0_ = i;
                this.timeHour_ = 0;
                int i2 = i & (-5);
                this.bitField0_ = i2;
                this.timeMinute_ = 0;
                int i3 = i2 & (-9);
                this.bitField0_ = i3;
                this.timeSecond_ = 0;
                this.bitField0_ = i3 & (-17);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_Clock_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Clock getDefaultInstanceForType() {
                return Clock.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Clock build() {
                Clock result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Clock buildPartial() {
                Clock result = new Clock(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.timeFormat_ = this.timeFormat_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.ampmDisplay_ = this.ampmDisplay_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.timeHour_ = this.timeHour_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.timeMinute_ = this.timeMinute_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.timeSecond_ = this.timeSecond_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Clock) {
                    return mergeFrom((Clock) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Clock other) {
                if (other == Clock.getDefaultInstance()) {
                    return this;
                }
                if (other.hasTimeFormat()) {
                    setTimeFormat(other.getTimeFormat());
                }
                if (other.hasAmpmDisplay()) {
                    setAmpmDisplay(other.getAmpmDisplay());
                }
                if (other.hasTimeHour()) {
                    setTimeHour(other.getTimeHour());
                }
                if (other.hasTimeMinute()) {
                    setTimeMinute(other.getTimeMinute());
                }
                if (other.hasTimeSecond()) {
                    setTimeSecond(other.getTimeSecond());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Clock parsedMessage = null;
                try {
                    try {
                        parsedMessage = Clock.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public boolean hasTimeFormat() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public ClockFormat getTimeFormat() {
                return this.timeFormat_;
            }

            public Builder setTimeFormat(ClockFormat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.timeFormat_ = value;
                onChanged();
                return this;
            }

            public Builder clearTimeFormat() {
                this.bitField0_ &= -2;
                this.timeFormat_ = ClockFormat.FORMAT12;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public boolean hasAmpmDisplay() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public AmPmDisplay getAmpmDisplay() {
                return this.ampmDisplay_;
            }

            public Builder setAmpmDisplay(AmPmDisplay value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.ampmDisplay_ = value;
                onChanged();
                return this;
            }

            public Builder clearAmpmDisplay() {
                this.bitField0_ &= -3;
                this.ampmDisplay_ = AmPmDisplay.DISPLAY;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public boolean hasTimeHour() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public int getTimeHour() {
                return this.timeHour_;
            }

            public Builder setTimeHour(int value) {
                this.bitField0_ |= 4;
                this.timeHour_ = value;
                onChanged();
                return this;
            }

            public Builder clearTimeHour() {
                this.bitField0_ &= -5;
                this.timeHour_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public boolean hasTimeMinute() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public int getTimeMinute() {
                return this.timeMinute_;
            }

            public Builder setTimeMinute(int value) {
                this.bitField0_ |= 8;
                this.timeMinute_ = value;
                onChanged();
                return this;
            }

            public Builder clearTimeMinute() {
                this.bitField0_ &= -9;
                this.timeMinute_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public boolean hasTimeSecond() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.autolink.interaction.InteractionProto.ClockOrBuilder
            public int getTimeSecond() {
                return this.timeSecond_;
            }

            public Builder setTimeSecond(int value) {
                this.bitField0_ |= 16;
                this.timeSecond_ = value;
                onChanged();
                return this;
            }

            public Builder clearTimeSecond() {
                this.bitField0_ &= -17;
                this.timeSecond_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class Navigation extends GeneratedMessage implements NavigationOrBuilder {
        public static final int ARRIVE_TIME_DAY_FIELD_NUMBER = 12;
        public static final int ARRIVE_TIME_HOUR_FIELD_NUMBER = 13;
        public static final int ARRIVE_TIME_MINUTE_FIELD_NUMBER = 14;
        public static final int DESTINATION_INFO_FIELD_NUMBER = 3;
        public static final int MAP_TYPE_FIELD_NUMBER = 1;
        public static final int NEXT_NAVIGATION_INFORMATION_ICON_FIELD_NUMBER = 2;
        public static final int NEXT_NEXT_ROAD_DIST_FIELD_NUMBER = 7;
        public static final int NEXT_NEXT_ROAD_UNIT_FIELD_NUMBER = 8;
        public static Parser<Navigation> PARSER = new AbstractParser<Navigation>() { // from class: com.autolink.interaction.InteractionProto.Navigation.1
            @Override // com.google.protobuf.Parser
            public Navigation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Navigation(input, extensionRegistry);
            }
        };
        public static final int REMAINING_MILAGE_FIELD_NUMBER = 10;
        public static final int REMAINING_MILAGE_VALUE_FIELD_NUMBER = 9;
        public static final int REMAINING_SECOND_VALUE_FIELD_NUMBER = 11;
        public static final int TOP_TIP_UNIT_FIELD_NUMBER = 5;
        public static final int TURN_NAVIGATION_INFORMATION_ICON_FIELD_NUMBER = 6;
        public static final int TURN_TIP_DIGIT_FIELD_NUMBER = 4;
        private static final Navigation defaultInstance;
        private static final long serialVersionUID = 0;
        private int arriveTimeDay_;
        private int arriveTimeHour_;
        private int arriveTimeMinute_;
        private int bitField0_;
        private Object destinationInfo_;
        private MapType mapType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int nextNavigationInformationIcon_;
        private float nextNextRoadDist_;
        private TopTipUnit nextNextRoadUnit_;
        private float remainingMilageValue_;
        private TopTipUnit remainingMilage_;
        private int remainingSecondValue_;
        private TopTipUnit topTipUnit_;
        private int turnNavigationInformationIcon_;
        private float turnTipDigit_;
        private final UnknownFieldSet unknownFields;

        private Navigation(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Navigation(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Navigation getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Navigation getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Navigation(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    int rawValue = input.readEnum();
                                    MapType value = MapType.valueOf(rawValue);
                                    if (value != null) {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.mapType_ = value;
                                    } else {
                                        unknownFields.mergeVarintField(1, rawValue);
                                    }
                                    break;
                                case 16:
                                    this.bitField0_ |= 2;
                                    this.nextNavigationInformationIcon_ = input.readInt32();
                                    break;
                                case 26:
                                    ByteString bs = input.readBytes();
                                    this.bitField0_ |= 4;
                                    this.destinationInfo_ = bs;
                                    break;
                                case 37:
                                    this.bitField0_ = 8 | this.bitField0_;
                                    this.turnTipDigit_ = input.readFloat();
                                    break;
                                case 40:
                                    int rawValue2 = input.readEnum();
                                    TopTipUnit value2 = TopTipUnit.valueOf(rawValue2);
                                    if (value2 == null) {
                                        unknownFields.mergeVarintField(5, rawValue2);
                                    } else {
                                        this.bitField0_ |= 16;
                                        this.topTipUnit_ = value2;
                                    }
                                    break;
                                case 48:
                                    this.bitField0_ |= 32;
                                    this.turnNavigationInformationIcon_ = input.readInt32();
                                    break;
                                case ROUNDABOUT_EXIT_15_VALUE:
                                    this.bitField0_ |= 64;
                                    this.nextNextRoadDist_ = input.readFloat();
                                    break;
                                case 64:
                                    int rawValue3 = input.readEnum();
                                    TopTipUnit value3 = TopTipUnit.valueOf(rawValue3);
                                    if (value3 == null) {
                                        unknownFields.mergeVarintField(8, rawValue3);
                                    } else {
                                        this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                                        this.nextNextRoadUnit_ = value3;
                                    }
                                    break;
                                case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_5_VALUE:
                                    this.bitField0_ |= 256;
                                    this.remainingMilageValue_ = input.readFloat();
                                    break;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5_VALUE:
                                    int rawValue4 = input.readEnum();
                                    TopTipUnit value4 = TopTipUnit.valueOf(rawValue4);
                                    if (value4 == null) {
                                        unknownFields.mergeVarintField(10, rawValue4);
                                    } else {
                                        this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                                        this.remainingMilage_ = value4;
                                    }
                                    break;
                                case 88:
                                    this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                                    this.remainingSecondValue_ = input.readInt32();
                                    break;
                                case SUSD_IVI_STATE_OF_HEALTH_VALUE:
                                    this.bitField0_ |= 2048;
                                    this.arriveTimeDay_ = input.readInt32();
                                    break;
                                case SUSD_DRIVER_DOOR_OPENED_VALUE:
                                    this.bitField0_ |= 4096;
                                    this.arriveTimeHour_ = input.readInt32();
                                    break;
                                case 112:
                                    this.bitField0_ |= 8192;
                                    this.arriveTimeMinute_ = input.readInt32();
                                    break;
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                                    break;
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_Navigation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_Navigation_fieldAccessorTable.ensureFieldAccessorsInitialized(Navigation.class, Builder.class);
        }

        static {
            Navigation navigation = new Navigation(true);
            defaultInstance = navigation;
            navigation.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Navigation> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasMapType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public MapType getMapType() {
            return this.mapType_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasNextNavigationInformationIcon() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public int getNextNavigationInformationIcon() {
            return this.nextNavigationInformationIcon_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasDestinationInfo() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public String getDestinationInfo() {
            Object ref = this.destinationInfo_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.destinationInfo_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public ByteString getDestinationInfoBytes() {
            Object ref = this.destinationInfo_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.destinationInfo_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasTurnTipDigit() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public float getTurnTipDigit() {
            return this.turnTipDigit_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasTopTipUnit() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public TopTipUnit getTopTipUnit() {
            return this.topTipUnit_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasTurnNavigationInformationIcon() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public int getTurnNavigationInformationIcon() {
            return this.turnNavigationInformationIcon_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasNextNextRoadDist() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public float getNextNextRoadDist() {
            return this.nextNextRoadDist_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasNextNextRoadUnit() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public TopTipUnit getNextNextRoadUnit() {
            return this.nextNextRoadUnit_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasRemainingMilageValue() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public float getRemainingMilageValue() {
            return this.remainingMilageValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasRemainingMilage() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public TopTipUnit getRemainingMilage() {
            return this.remainingMilage_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasRemainingSecondValue() {
            return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public int getRemainingSecondValue() {
            return this.remainingSecondValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasArriveTimeDay() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public int getArriveTimeDay() {
            return this.arriveTimeDay_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasArriveTimeHour() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public int getArriveTimeHour() {
            return this.arriveTimeHour_;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public boolean hasArriveTimeMinute() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
        public int getArriveTimeMinute() {
            return this.arriveTimeMinute_;
        }

        private void initFields() {
            this.mapType_ = MapType.MAP_TYPE_INVALID;
            this.nextNavigationInformationIcon_ = 0;
            this.destinationInfo_ = "";
            this.turnTipDigit_ = 0.0f;
            this.topTipUnit_ = TopTipUnit.UNIT_INVALID;
            this.turnNavigationInformationIcon_ = 0;
            this.nextNextRoadDist_ = 0.0f;
            this.nextNextRoadUnit_ = TopTipUnit.UNIT_INVALID;
            this.remainingMilageValue_ = 0.0f;
            this.remainingMilage_ = TopTipUnit.UNIT_INVALID;
            this.remainingSecondValue_ = 0;
            this.arriveTimeDay_ = 0;
            this.arriveTimeHour_ = 0;
            this.arriveTimeMinute_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.mapType_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.nextNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, getDestinationInfoBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeFloat(4, this.turnTipDigit_);
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.topTipUnit_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.turnNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeFloat(7, this.nextNextRoadDist_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeEnum(8, this.nextNextRoadUnit_.getNumber());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeFloat(9, this.remainingMilageValue_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                output.writeEnum(10, this.remainingMilage_.getNumber());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                output.writeInt32(11, this.remainingSecondValue_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeInt32(12, this.arriveTimeDay_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeInt32(13, this.arriveTimeHour_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeInt32(14, this.arriveTimeMinute_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.mapType_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.nextNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, getDestinationInfoBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeFloatSize(4, this.turnTipDigit_);
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.topTipUnit_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.turnNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeFloatSize(7, this.nextNextRoadDist_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeEnumSize(8, this.nextNextRoadUnit_.getNumber());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeFloatSize(9, this.remainingMilageValue_);
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                size2 += CodedOutputStream.computeEnumSize(10, this.remainingMilage_.getNumber());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                size2 += CodedOutputStream.computeInt32Size(11, this.remainingSecondValue_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeInt32Size(12, this.arriveTimeDay_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeInt32Size(13, this.arriveTimeHour_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeInt32Size(14, this.arriveTimeMinute_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Navigation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Navigation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Navigation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Navigation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Navigation parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Navigation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Navigation parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Navigation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Navigation parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Navigation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Navigation prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements NavigationOrBuilder {
            private int arriveTimeDay_;
            private int arriveTimeHour_;
            private int arriveTimeMinute_;
            private int bitField0_;
            private Object destinationInfo_;
            private MapType mapType_;
            private int nextNavigationInformationIcon_;
            private float nextNextRoadDist_;
            private TopTipUnit nextNextRoadUnit_;
            private float remainingMilageValue_;
            private TopTipUnit remainingMilage_;
            private int remainingSecondValue_;
            private TopTipUnit topTipUnit_;
            private int turnNavigationInformationIcon_;
            private float turnTipDigit_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_Navigation_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_Navigation_fieldAccessorTable.ensureFieldAccessorsInitialized(Navigation.class, Builder.class);
            }

            private Builder() {
                this.mapType_ = MapType.MAP_TYPE_INVALID;
                this.destinationInfo_ = "";
                this.topTipUnit_ = TopTipUnit.UNIT_INVALID;
                this.nextNextRoadUnit_ = TopTipUnit.UNIT_INVALID;
                this.remainingMilage_ = TopTipUnit.UNIT_INVALID;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.mapType_ = MapType.MAP_TYPE_INVALID;
                this.destinationInfo_ = "";
                this.topTipUnit_ = TopTipUnit.UNIT_INVALID;
                this.nextNextRoadUnit_ = TopTipUnit.UNIT_INVALID;
                this.remainingMilage_ = TopTipUnit.UNIT_INVALID;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Navigation.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mapType_ = MapType.MAP_TYPE_INVALID;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.nextNavigationInformationIcon_ = 0;
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.destinationInfo_ = "";
                int i3 = i2 & (-5);
                this.bitField0_ = i3;
                this.turnTipDigit_ = 0.0f;
                this.bitField0_ = i3 & (-9);
                this.topTipUnit_ = TopTipUnit.UNIT_INVALID;
                int i4 = this.bitField0_ & (-17);
                this.bitField0_ = i4;
                this.turnNavigationInformationIcon_ = 0;
                int i5 = i4 & (-33);
                this.bitField0_ = i5;
                this.nextNextRoadDist_ = 0.0f;
                this.bitField0_ = i5 & (-65);
                this.nextNextRoadUnit_ = TopTipUnit.UNIT_INVALID;
                int i6 = this.bitField0_ & (-129);
                this.bitField0_ = i6;
                this.remainingMilageValue_ = 0.0f;
                this.bitField0_ = i6 & (-257);
                this.remainingMilage_ = TopTipUnit.UNIT_INVALID;
                int i7 = this.bitField0_ & (-513);
                this.bitField0_ = i7;
                this.remainingSecondValue_ = 0;
                int i8 = i7 & (-1025);
                this.bitField0_ = i8;
                this.arriveTimeDay_ = 0;
                int i9 = i8 & (-2049);
                this.bitField0_ = i9;
                this.arriveTimeHour_ = 0;
                int i10 = i9 & (-4097);
                this.bitField0_ = i10;
                this.arriveTimeMinute_ = 0;
                this.bitField0_ = i10 & (-8193);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_Navigation_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Navigation getDefaultInstanceForType() {
                return Navigation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Navigation build() {
                Navigation result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Navigation buildPartial() {
                Navigation result = new Navigation(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mapType_ = this.mapType_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.nextNavigationInformationIcon_ = this.nextNavigationInformationIcon_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.destinationInfo_ = this.destinationInfo_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.turnTipDigit_ = this.turnTipDigit_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.topTipUnit_ = this.topTipUnit_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.turnNavigationInformationIcon_ = this.turnNavigationInformationIcon_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.nextNextRoadDist_ = this.nextNextRoadDist_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.nextNextRoadUnit_ = this.nextNextRoadUnit_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 256;
                }
                result.remainingMilageValue_ = this.remainingMilageValue_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                }
                result.remainingMilage_ = this.remainingMilage_;
                if ((from_bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                    to_bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                }
                result.remainingSecondValue_ = this.remainingSecondValue_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 2048;
                }
                result.arriveTimeDay_ = this.arriveTimeDay_;
                if ((from_bitField0_ & 4096) == 4096) {
                    to_bitField0_ |= 4096;
                }
                result.arriveTimeHour_ = this.arriveTimeHour_;
                if ((from_bitField0_ & 8192) == 8192) {
                    to_bitField0_ |= 8192;
                }
                result.arriveTimeMinute_ = this.arriveTimeMinute_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Navigation) {
                    return mergeFrom((Navigation) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Navigation other) {
                if (other == Navigation.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMapType()) {
                    setMapType(other.getMapType());
                }
                if (other.hasNextNavigationInformationIcon()) {
                    setNextNavigationInformationIcon(other.getNextNavigationInformationIcon());
                }
                if (other.hasDestinationInfo()) {
                    this.bitField0_ |= 4;
                    this.destinationInfo_ = other.destinationInfo_;
                    onChanged();
                }
                if (other.hasTurnTipDigit()) {
                    setTurnTipDigit(other.getTurnTipDigit());
                }
                if (other.hasTopTipUnit()) {
                    setTopTipUnit(other.getTopTipUnit());
                }
                if (other.hasTurnNavigationInformationIcon()) {
                    setTurnNavigationInformationIcon(other.getTurnNavigationInformationIcon());
                }
                if (other.hasNextNextRoadDist()) {
                    setNextNextRoadDist(other.getNextNextRoadDist());
                }
                if (other.hasNextNextRoadUnit()) {
                    setNextNextRoadUnit(other.getNextNextRoadUnit());
                }
                if (other.hasRemainingMilageValue()) {
                    setRemainingMilageValue(other.getRemainingMilageValue());
                }
                if (other.hasRemainingMilage()) {
                    setRemainingMilage(other.getRemainingMilage());
                }
                if (other.hasRemainingSecondValue()) {
                    setRemainingSecondValue(other.getRemainingSecondValue());
                }
                if (other.hasArriveTimeDay()) {
                    setArriveTimeDay(other.getArriveTimeDay());
                }
                if (other.hasArriveTimeHour()) {
                    setArriveTimeHour(other.getArriveTimeHour());
                }
                if (other.hasArriveTimeMinute()) {
                    setArriveTimeMinute(other.getArriveTimeMinute());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Navigation parsedMessage = null;
                try {
                    try {
                        parsedMessage = Navigation.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasMapType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public MapType getMapType() {
                return this.mapType_;
            }

            public Builder setMapType(MapType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.mapType_ = value;
                onChanged();
                return this;
            }

            public Builder clearMapType() {
                this.bitField0_ &= -2;
                this.mapType_ = MapType.MAP_TYPE_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasNextNavigationInformationIcon() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public int getNextNavigationInformationIcon() {
                return this.nextNavigationInformationIcon_;
            }

            public Builder setNextNavigationInformationIcon(int value) {
                this.bitField0_ |= 2;
                this.nextNavigationInformationIcon_ = value;
                onChanged();
                return this;
            }

            public Builder clearNextNavigationInformationIcon() {
                this.bitField0_ &= -3;
                this.nextNavigationInformationIcon_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasDestinationInfo() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public String getDestinationInfo() {
                Object ref = this.destinationInfo_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.destinationInfo_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public ByteString getDestinationInfoBytes() {
                Object ref = this.destinationInfo_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.destinationInfo_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setDestinationInfo(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.destinationInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearDestinationInfo() {
                this.bitField0_ &= -5;
                this.destinationInfo_ = Navigation.getDefaultInstance().getDestinationInfo();
                onChanged();
                return this;
            }

            public Builder setDestinationInfoBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.destinationInfo_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasTurnTipDigit() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public float getTurnTipDigit() {
                return this.turnTipDigit_;
            }

            public Builder setTurnTipDigit(float value) {
                this.bitField0_ |= 8;
                this.turnTipDigit_ = value;
                onChanged();
                return this;
            }

            public Builder clearTurnTipDigit() {
                this.bitField0_ &= -9;
                this.turnTipDigit_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasTopTipUnit() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public TopTipUnit getTopTipUnit() {
                return this.topTipUnit_;
            }

            public Builder setTopTipUnit(TopTipUnit value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.topTipUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearTopTipUnit() {
                this.bitField0_ &= -17;
                this.topTipUnit_ = TopTipUnit.UNIT_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasTurnNavigationInformationIcon() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public int getTurnNavigationInformationIcon() {
                return this.turnNavigationInformationIcon_;
            }

            public Builder setTurnNavigationInformationIcon(int value) {
                this.bitField0_ |= 32;
                this.turnNavigationInformationIcon_ = value;
                onChanged();
                return this;
            }

            public Builder clearTurnNavigationInformationIcon() {
                this.bitField0_ &= -33;
                this.turnNavigationInformationIcon_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasNextNextRoadDist() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public float getNextNextRoadDist() {
                return this.nextNextRoadDist_;
            }

            public Builder setNextNextRoadDist(float value) {
                this.bitField0_ |= 64;
                this.nextNextRoadDist_ = value;
                onChanged();
                return this;
            }

            public Builder clearNextNextRoadDist() {
                this.bitField0_ &= -65;
                this.nextNextRoadDist_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasNextNextRoadUnit() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public TopTipUnit getNextNextRoadUnit() {
                return this.nextNextRoadUnit_;
            }

            public Builder setNextNextRoadUnit(TopTipUnit value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.nextNextRoadUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearNextNextRoadUnit() {
                this.bitField0_ &= -129;
                this.nextNextRoadUnit_ = TopTipUnit.UNIT_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasRemainingMilageValue() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public float getRemainingMilageValue() {
                return this.remainingMilageValue_;
            }

            public Builder setRemainingMilageValue(float value) {
                this.bitField0_ |= 256;
                this.remainingMilageValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearRemainingMilageValue() {
                this.bitField0_ &= -257;
                this.remainingMilageValue_ = 0.0f;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasRemainingMilage() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public TopTipUnit getRemainingMilage() {
                return this.remainingMilage_;
            }

            public Builder setRemainingMilage(TopTipUnit value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.remainingMilage_ = value;
                onChanged();
                return this;
            }

            public Builder clearRemainingMilage() {
                this.bitField0_ &= -513;
                this.remainingMilage_ = TopTipUnit.UNIT_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasRemainingSecondValue() {
                return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public int getRemainingSecondValue() {
                return this.remainingSecondValue_;
            }

            public Builder setRemainingSecondValue(int value) {
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.remainingSecondValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearRemainingSecondValue() {
                this.bitField0_ &= -1025;
                this.remainingSecondValue_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasArriveTimeDay() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public int getArriveTimeDay() {
                return this.arriveTimeDay_;
            }

            public Builder setArriveTimeDay(int value) {
                this.bitField0_ |= 2048;
                this.arriveTimeDay_ = value;
                onChanged();
                return this;
            }

            public Builder clearArriveTimeDay() {
                this.bitField0_ &= -2049;
                this.arriveTimeDay_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasArriveTimeHour() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public int getArriveTimeHour() {
                return this.arriveTimeHour_;
            }

            public Builder setArriveTimeHour(int value) {
                this.bitField0_ |= 4096;
                this.arriveTimeHour_ = value;
                onChanged();
                return this;
            }

            public Builder clearArriveTimeHour() {
                this.bitField0_ &= -4097;
                this.arriveTimeHour_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public boolean hasArriveTimeMinute() {
                return (this.bitField0_ & 8192) == 8192;
            }

            @Override // com.autolink.interaction.InteractionProto.NavigationOrBuilder
            public int getArriveTimeMinute() {
                return this.arriveTimeMinute_;
            }

            public Builder setArriveTimeMinute(int value) {
                this.bitField0_ |= 8192;
                this.arriveTimeMinute_ = value;
                onChanged();
                return this;
            }

            public Builder clearArriveTimeMinute() {
                this.bitField0_ &= -8193;
                this.arriveTimeMinute_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class InteractionData extends GeneratedMessage implements InteractionDataOrBuilder {
        public static final int BLUETOOTH_PHONE_VALUE_FIELD_NUMBER = 1;
        public static final int CLOCK_VALUE_FIELD_NUMBER = 3;
        public static final int MUSIC_VALUE_FIELD_NUMBER = 2;
        public static final int NAVIGATION_VALUE_FIELD_NUMBER = 4;
        public static Parser<InteractionData> PARSER = new AbstractParser<InteractionData>() { // from class: com.autolink.interaction.InteractionProto.InteractionData.1
            @Override // com.google.protobuf.Parser
            public InteractionData parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new InteractionData(input, extensionRegistry);
            }
        };
        private static final InteractionData defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private BluetoothPhone bluetoothPhoneValue_;
        private Clock clockValue_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Music musicValue_;
        private Navigation navigationValue_;
        private final UnknownFieldSet unknownFields;

        private InteractionData(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private InteractionData(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static InteractionData getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public InteractionData getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private InteractionData(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 10) {
                            BluetoothPhone.Builder subBuilder = (this.bitField0_ & 1) == 1 ? this.bluetoothPhoneValue_.toBuilder() : null;
                            BluetoothPhone bluetoothPhone = (BluetoothPhone) input.readMessage(BluetoothPhone.PARSER, extensionRegistry);
                            this.bluetoothPhoneValue_ = bluetoothPhone;
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(bluetoothPhone);
                                this.bluetoothPhoneValue_ = subBuilder.buildPartial();
                            }
                            this.bitField0_ |= 1;
                        } else if (tag == 18) {
                            Music.Builder subBuilder2 = (this.bitField0_ & 2) == 2 ? this.musicValue_.toBuilder() : null;
                            Music music = (Music) input.readMessage(Music.PARSER, extensionRegistry);
                            this.musicValue_ = music;
                            if (subBuilder2 != null) {
                                subBuilder2.mergeFrom(music);
                                this.musicValue_ = subBuilder2.buildPartial();
                            }
                            this.bitField0_ |= 2;
                        } else if (tag == 26) {
                            Clock.Builder subBuilder3 = (this.bitField0_ & 4) == 4 ? this.clockValue_.toBuilder() : null;
                            Clock clock = (Clock) input.readMessage(Clock.PARSER, extensionRegistry);
                            this.clockValue_ = clock;
                            if (subBuilder3 != null) {
                                subBuilder3.mergeFrom(clock);
                                this.clockValue_ = subBuilder3.buildPartial();
                            }
                            this.bitField0_ |= 4;
                        } else if (tag != 34) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            Navigation.Builder subBuilder4 = (this.bitField0_ & 8) == 8 ? this.navigationValue_.toBuilder() : null;
                            Navigation navigation = (Navigation) input.readMessage(Navigation.PARSER, extensionRegistry);
                            this.navigationValue_ = navigation;
                            if (subBuilder4 != null) {
                                subBuilder4.mergeFrom(navigation);
                                this.navigationValue_ = subBuilder4.buildPartial();
                            }
                            this.bitField0_ |= 8;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_InteractionData_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_InteractionData_fieldAccessorTable.ensureFieldAccessorsInitialized(InteractionData.class, Builder.class);
        }

        static {
            InteractionData interactionData = new InteractionData(true);
            defaultInstance = interactionData;
            interactionData.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<InteractionData> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public boolean hasBluetoothPhoneValue() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public BluetoothPhone getBluetoothPhoneValue() {
            return this.bluetoothPhoneValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public BluetoothPhoneOrBuilder getBluetoothPhoneValueOrBuilder() {
            return this.bluetoothPhoneValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public boolean hasMusicValue() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public Music getMusicValue() {
            return this.musicValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public MusicOrBuilder getMusicValueOrBuilder() {
            return this.musicValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public boolean hasClockValue() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public Clock getClockValue() {
            return this.clockValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public ClockOrBuilder getClockValueOrBuilder() {
            return this.clockValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public boolean hasNavigationValue() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public Navigation getNavigationValue() {
            return this.navigationValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
        public NavigationOrBuilder getNavigationValueOrBuilder() {
            return this.navigationValue_;
        }

        private void initFields() {
            this.bluetoothPhoneValue_ = BluetoothPhone.getDefaultInstance();
            this.musicValue_ = Music.getDefaultInstance();
            this.clockValue_ = Clock.getDefaultInstance();
            this.navigationValue_ = Navigation.getDefaultInstance();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeMessage(1, this.bluetoothPhoneValue_);
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeMessage(2, this.musicValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeMessage(3, this.clockValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeMessage(4, this.navigationValue_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeMessageSize(1, this.bluetoothPhoneValue_) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeMessageSize(2, this.musicValue_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeMessageSize(3, this.clockValue_);
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeMessageSize(4, this.navigationValue_);
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static InteractionData parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static InteractionData parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static InteractionData parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static InteractionData parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static InteractionData parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static InteractionData parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static InteractionData parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static InteractionData parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static InteractionData parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static InteractionData parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(InteractionData prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements InteractionDataOrBuilder {
            private int bitField0_;
            private SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> bluetoothPhoneValueBuilder_;
            private BluetoothPhone bluetoothPhoneValue_;
            private SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> clockValueBuilder_;
            private Clock clockValue_;
            private SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> musicValueBuilder_;
            private Music musicValue_;
            private SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> navigationValueBuilder_;
            private Navigation navigationValue_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_InteractionData_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_InteractionData_fieldAccessorTable.ensureFieldAccessorsInitialized(InteractionData.class, Builder.class);
            }

            private Builder() {
                this.bluetoothPhoneValue_ = BluetoothPhone.getDefaultInstance();
                this.musicValue_ = Music.getDefaultInstance();
                this.clockValue_ = Clock.getDefaultInstance();
                this.navigationValue_ = Navigation.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.bluetoothPhoneValue_ = BluetoothPhone.getDefaultInstance();
                this.musicValue_ = Music.getDefaultInstance();
                this.clockValue_ = Clock.getDefaultInstance();
                this.navigationValue_ = Navigation.getDefaultInstance();
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                if (InteractionData.alwaysUseFieldBuilders) {
                    getBluetoothPhoneValueFieldBuilder();
                    getMusicValueFieldBuilder();
                    getClockValueFieldBuilder();
                    getNavigationValueFieldBuilder();
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.bluetoothPhoneValue_ = BluetoothPhone.getDefaultInstance();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -2;
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder2 = this.musicValueBuilder_;
                if (singleFieldBuilder2 == null) {
                    this.musicValue_ = Music.getDefaultInstance();
                } else {
                    singleFieldBuilder2.clear();
                }
                this.bitField0_ &= -3;
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder3 = this.clockValueBuilder_;
                if (singleFieldBuilder3 == null) {
                    this.clockValue_ = Clock.getDefaultInstance();
                } else {
                    singleFieldBuilder3.clear();
                }
                this.bitField0_ &= -5;
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder4 = this.navigationValueBuilder_;
                if (singleFieldBuilder4 == null) {
                    this.navigationValue_ = Navigation.getDefaultInstance();
                } else {
                    singleFieldBuilder4.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_InteractionData_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public InteractionData getDefaultInstanceForType() {
                return InteractionData.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InteractionData build() {
                InteractionData result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InteractionData buildPartial() {
                InteractionData result = new InteractionData(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder == null) {
                    result.bluetoothPhoneValue_ = this.bluetoothPhoneValue_;
                } else {
                    result.bluetoothPhoneValue_ = (BluetoothPhone) singleFieldBuilder.build();
                }
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder2 = this.musicValueBuilder_;
                if (singleFieldBuilder2 == null) {
                    result.musicValue_ = this.musicValue_;
                } else {
                    result.musicValue_ = (Music) singleFieldBuilder2.build();
                }
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder3 = this.clockValueBuilder_;
                if (singleFieldBuilder3 == null) {
                    result.clockValue_ = this.clockValue_;
                } else {
                    result.clockValue_ = (Clock) singleFieldBuilder3.build();
                }
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder4 = this.navigationValueBuilder_;
                if (singleFieldBuilder4 == null) {
                    result.navigationValue_ = this.navigationValue_;
                } else {
                    result.navigationValue_ = (Navigation) singleFieldBuilder4.build();
                }
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof InteractionData) {
                    return mergeFrom((InteractionData) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(InteractionData other) {
                if (other == InteractionData.getDefaultInstance()) {
                    return this;
                }
                if (other.hasBluetoothPhoneValue()) {
                    mergeBluetoothPhoneValue(other.getBluetoothPhoneValue());
                }
                if (other.hasMusicValue()) {
                    mergeMusicValue(other.getMusicValue());
                }
                if (other.hasClockValue()) {
                    mergeClockValue(other.getClockValue());
                }
                if (other.hasNavigationValue()) {
                    mergeNavigationValue(other.getNavigationValue());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                InteractionData parsedMessage = null;
                try {
                    try {
                        parsedMessage = InteractionData.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public boolean hasBluetoothPhoneValue() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public BluetoothPhone getBluetoothPhoneValue() {
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder == null) {
                    return this.bluetoothPhoneValue_;
                }
                return (BluetoothPhone) singleFieldBuilder.getMessage();
            }

            public Builder setBluetoothPhoneValue(BluetoothPhone value) {
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    this.bluetoothPhoneValue_ = value;
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setBluetoothPhoneValue(BluetoothPhone.Builder builderForValue) {
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.bluetoothPhoneValue_ = builderForValue.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder mergeBluetoothPhoneValue(BluetoothPhone value) {
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 1) == 1 && this.bluetoothPhoneValue_ != BluetoothPhone.getDefaultInstance()) {
                        this.bluetoothPhoneValue_ = BluetoothPhone.newBuilder(this.bluetoothPhoneValue_).mergeFrom(value).buildPartial();
                    } else {
                        this.bluetoothPhoneValue_ = value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(value);
                }
                this.bitField0_ |= 1;
                return this;
            }

            public Builder clearBluetoothPhoneValue() {
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.bluetoothPhoneValue_ = BluetoothPhone.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -2;
                return this;
            }

            public BluetoothPhone.Builder getBluetoothPhoneValueBuilder() {
                this.bitField0_ |= 1;
                onChanged();
                return (BluetoothPhone.Builder) getBluetoothPhoneValueFieldBuilder().getBuilder();
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public BluetoothPhoneOrBuilder getBluetoothPhoneValueOrBuilder() {
                SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> singleFieldBuilder = this.bluetoothPhoneValueBuilder_;
                if (singleFieldBuilder != null) {
                    return (BluetoothPhoneOrBuilder) singleFieldBuilder.getMessageOrBuilder();
                }
                return this.bluetoothPhoneValue_;
            }

            private SingleFieldBuilder<BluetoothPhone, BluetoothPhone.Builder, BluetoothPhoneOrBuilder> getBluetoothPhoneValueFieldBuilder() {
                if (this.bluetoothPhoneValueBuilder_ == null) {
                    this.bluetoothPhoneValueBuilder_ = new SingleFieldBuilder<>(getBluetoothPhoneValue(), getParentForChildren(), isClean());
                    this.bluetoothPhoneValue_ = null;
                }
                return this.bluetoothPhoneValueBuilder_;
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public boolean hasMusicValue() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public Music getMusicValue() {
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder = this.musicValueBuilder_;
                if (singleFieldBuilder == null) {
                    return this.musicValue_;
                }
                return (Music) singleFieldBuilder.getMessage();
            }

            public Builder setMusicValue(Music value) {
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder = this.musicValueBuilder_;
                if (singleFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    this.musicValue_ = value;
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setMusicValue(Music.Builder builderForValue) {
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder = this.musicValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.musicValue_ = builderForValue.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder mergeMusicValue(Music value) {
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder = this.musicValueBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 2) == 2 && this.musicValue_ != Music.getDefaultInstance()) {
                        this.musicValue_ = Music.newBuilder(this.musicValue_).mergeFrom(value).buildPartial();
                    } else {
                        this.musicValue_ = value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(value);
                }
                this.bitField0_ |= 2;
                return this;
            }

            public Builder clearMusicValue() {
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder = this.musicValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.musicValue_ = Music.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -3;
                return this;
            }

            public Music.Builder getMusicValueBuilder() {
                this.bitField0_ |= 2;
                onChanged();
                return (Music.Builder) getMusicValueFieldBuilder().getBuilder();
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public MusicOrBuilder getMusicValueOrBuilder() {
                SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> singleFieldBuilder = this.musicValueBuilder_;
                if (singleFieldBuilder != null) {
                    return (MusicOrBuilder) singleFieldBuilder.getMessageOrBuilder();
                }
                return this.musicValue_;
            }

            private SingleFieldBuilder<Music, Music.Builder, MusicOrBuilder> getMusicValueFieldBuilder() {
                if (this.musicValueBuilder_ == null) {
                    this.musicValueBuilder_ = new SingleFieldBuilder<>(getMusicValue(), getParentForChildren(), isClean());
                    this.musicValue_ = null;
                }
                return this.musicValueBuilder_;
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public boolean hasClockValue() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public Clock getClockValue() {
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder = this.clockValueBuilder_;
                if (singleFieldBuilder == null) {
                    return this.clockValue_;
                }
                return (Clock) singleFieldBuilder.getMessage();
            }

            public Builder setClockValue(Clock value) {
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder = this.clockValueBuilder_;
                if (singleFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    this.clockValue_ = value;
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder setClockValue(Clock.Builder builderForValue) {
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder = this.clockValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.clockValue_ = builderForValue.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder mergeClockValue(Clock value) {
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder = this.clockValueBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 4) == 4 && this.clockValue_ != Clock.getDefaultInstance()) {
                        this.clockValue_ = Clock.newBuilder(this.clockValue_).mergeFrom(value).buildPartial();
                    } else {
                        this.clockValue_ = value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(value);
                }
                this.bitField0_ |= 4;
                return this;
            }

            public Builder clearClockValue() {
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder = this.clockValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.clockValue_ = Clock.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -5;
                return this;
            }

            public Clock.Builder getClockValueBuilder() {
                this.bitField0_ |= 4;
                onChanged();
                return (Clock.Builder) getClockValueFieldBuilder().getBuilder();
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public ClockOrBuilder getClockValueOrBuilder() {
                SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> singleFieldBuilder = this.clockValueBuilder_;
                if (singleFieldBuilder != null) {
                    return (ClockOrBuilder) singleFieldBuilder.getMessageOrBuilder();
                }
                return this.clockValue_;
            }

            private SingleFieldBuilder<Clock, Clock.Builder, ClockOrBuilder> getClockValueFieldBuilder() {
                if (this.clockValueBuilder_ == null) {
                    this.clockValueBuilder_ = new SingleFieldBuilder<>(getClockValue(), getParentForChildren(), isClean());
                    this.clockValue_ = null;
                }
                return this.clockValueBuilder_;
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public boolean hasNavigationValue() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public Navigation getNavigationValue() {
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder = this.navigationValueBuilder_;
                if (singleFieldBuilder == null) {
                    return this.navigationValue_;
                }
                return (Navigation) singleFieldBuilder.getMessage();
            }

            public Builder setNavigationValue(Navigation value) {
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder = this.navigationValueBuilder_;
                if (singleFieldBuilder == null) {
                    if (value == null) {
                        throw null;
                    }
                    this.navigationValue_ = value;
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder setNavigationValue(Navigation.Builder builderForValue) {
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder = this.navigationValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.navigationValue_ = builderForValue.build();
                    onChanged();
                } else {
                    singleFieldBuilder.setMessage(builderForValue.build());
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder mergeNavigationValue(Navigation value) {
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder = this.navigationValueBuilder_;
                if (singleFieldBuilder == null) {
                    if ((this.bitField0_ & 8) == 8 && this.navigationValue_ != Navigation.getDefaultInstance()) {
                        this.navigationValue_ = Navigation.newBuilder(this.navigationValue_).mergeFrom(value).buildPartial();
                    } else {
                        this.navigationValue_ = value;
                    }
                    onChanged();
                } else {
                    singleFieldBuilder.mergeFrom(value);
                }
                this.bitField0_ |= 8;
                return this;
            }

            public Builder clearNavigationValue() {
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder = this.navigationValueBuilder_;
                if (singleFieldBuilder == null) {
                    this.navigationValue_ = Navigation.getDefaultInstance();
                    onChanged();
                } else {
                    singleFieldBuilder.clear();
                }
                this.bitField0_ &= -9;
                return this;
            }

            public Navigation.Builder getNavigationValueBuilder() {
                this.bitField0_ |= 8;
                onChanged();
                return (Navigation.Builder) getNavigationValueFieldBuilder().getBuilder();
            }

            @Override // com.autolink.interaction.InteractionProto.InteractionDataOrBuilder
            public NavigationOrBuilder getNavigationValueOrBuilder() {
                SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> singleFieldBuilder = this.navigationValueBuilder_;
                if (singleFieldBuilder != null) {
                    return (NavigationOrBuilder) singleFieldBuilder.getMessageOrBuilder();
                }
                return this.navigationValue_;
            }

            private SingleFieldBuilder<Navigation, Navigation.Builder, NavigationOrBuilder> getNavigationValueFieldBuilder() {
                if (this.navigationValueBuilder_ == null) {
                    this.navigationValueBuilder_ = new SingleFieldBuilder<>(getNavigationValue(), getParentForChildren(), isClean());
                    this.navigationValue_ = null;
                }
                return this.navigationValueBuilder_;
            }
        }
    }

    public static final class Language extends GeneratedMessage implements LanguageOrBuilder {
        public static final int NCURRLANG_FIELD_NUMBER = 1;
        public static Parser<Language> PARSER = new AbstractParser<Language>() { // from class: com.autolink.interaction.InteractionProto.Language.1
            @Override // com.google.protobuf.Parser
            public Language parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new Language(input, extensionRegistry);
            }
        };
        private static final Language defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int nCurrLang_;
        private final UnknownFieldSet unknownFields;

        private Language(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private Language(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static Language getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public Language getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private Language(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.nCurrLang_ = input.readInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_Language_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_Language_fieldAccessorTable.ensureFieldAccessorsInitialized(Language.class, Builder.class);
        }

        static {
            Language language = new Language(true);
            defaultInstance = language;
            language.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<Language> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.LanguageOrBuilder
        public boolean hasNCurrLang() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.LanguageOrBuilder
        public int getNCurrLang() {
            return this.nCurrLang_;
        }

        private void initFields() {
            this.nCurrLang_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            if (!hasNCurrLang()) {
                this.memoizedIsInitialized = (byte) 0;
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.nCurrLang_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.nCurrLang_) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static Language parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Language parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Language parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static Language parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static Language parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Language parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Language parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static Language parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static Language parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static Language parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(Language prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements LanguageOrBuilder {
            private int bitField0_;
            private int nCurrLang_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_Language_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_Language_fieldAccessorTable.ensureFieldAccessorsInitialized(Language.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = Language.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrLang_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_Language_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public Language getDefaultInstanceForType() {
                return Language.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Language build() {
                Language result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Language buildPartial() {
                Language result = new Language(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrLang_ = this.nCurrLang_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof Language) {
                    return mergeFrom((Language) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(Language other) {
                if (other == Language.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrLang()) {
                    setNCurrLang(other.getNCurrLang());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                if (!hasNCurrLang()) {
                    return false;
                }
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                Language parsedMessage = null;
                try {
                    try {
                        parsedMessage = Language.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.LanguageOrBuilder
            public boolean hasNCurrLang() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.LanguageOrBuilder
            public int getNCurrLang() {
                return this.nCurrLang_;
            }

            public Builder setNCurrLang(int value) {
                this.bitField0_ |= 1;
                this.nCurrLang_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrLang() {
                this.bitField0_ &= -2;
                this.nCurrLang_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class ResetOperation extends GeneratedMessage implements ResetOperationOrBuilder {
        public static final int NRESETOPER_FIELD_NUMBER = 1;
        public static Parser<ResetOperation> PARSER = new AbstractParser<ResetOperation>() { // from class: com.autolink.interaction.InteractionProto.ResetOperation.1
            @Override // com.google.protobuf.Parser
            public ResetOperation parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ResetOperation(input, extensionRegistry);
            }
        };
        private static final ResetOperation defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private int nResetOper_;
        private final UnknownFieldSet unknownFields;

        private ResetOperation(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ResetOperation(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ResetOperation getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ResetOperation getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ResetOperation(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.nResetOper_ = input.readInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_ResetOperation_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_ResetOperation_fieldAccessorTable.ensureFieldAccessorsInitialized(ResetOperation.class, Builder.class);
        }

        static {
            ResetOperation resetOperation = new ResetOperation(true);
            defaultInstance = resetOperation;
            resetOperation.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ResetOperation> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.ResetOperationOrBuilder
        public boolean hasNResetOper() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.ResetOperationOrBuilder
        public int getNResetOper() {
            return this.nResetOper_;
        }

        private void initFields() {
            this.nResetOper_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.nResetOper_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.nResetOper_) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ResetOperation parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ResetOperation parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ResetOperation parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ResetOperation parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ResetOperation parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ResetOperation parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ResetOperation parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ResetOperation parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ResetOperation parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ResetOperation parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ResetOperation prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ResetOperationOrBuilder {
            private int bitField0_;
            private int nResetOper_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_ResetOperation_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_ResetOperation_fieldAccessorTable.ensureFieldAccessorsInitialized(ResetOperation.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ResetOperation.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nResetOper_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_ResetOperation_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ResetOperation getDefaultInstanceForType() {
                return ResetOperation.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ResetOperation build() {
                ResetOperation result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ResetOperation buildPartial() {
                ResetOperation result = new ResetOperation(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nResetOper_ = this.nResetOper_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ResetOperation) {
                    return mergeFrom((ResetOperation) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ResetOperation other) {
                if (other == ResetOperation.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNResetOper()) {
                    setNResetOper(other.getNResetOper());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ResetOperation parsedMessage = null;
                try {
                    try {
                        parsedMessage = ResetOperation.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.ResetOperationOrBuilder
            public boolean hasNResetOper() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.ResetOperationOrBuilder
            public int getNResetOper() {
                return this.nResetOper_;
            }

            public Builder setNResetOper(int value) {
                this.bitField0_ |= 1;
                this.nResetOper_ = value;
                onChanged();
                return this;
            }

            public Builder clearNResetOper() {
                this.bitField0_ &= -2;
                this.nResetOper_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class ThemeSetting extends GeneratedMessage implements ThemeSettingOrBuilder {
        public static final int NCURRTHEME_FIELD_NUMBER = 1;
        public static Parser<ThemeSetting> PARSER = new AbstractParser<ThemeSetting>() { // from class: com.autolink.interaction.InteractionProto.ThemeSetting.1
            @Override // com.google.protobuf.Parser
            public ThemeSetting parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ThemeSetting(input, extensionRegistry);
            }
        };
        private static final ThemeSetting defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private ThemeSet nCurrTheme_;
        private final UnknownFieldSet unknownFields;

        private ThemeSetting(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ThemeSetting(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ThemeSetting getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ThemeSetting getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ThemeSetting(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            ThemeSet value = ThemeSet.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrTheme_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_ThemeSetting_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_ThemeSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(ThemeSetting.class, Builder.class);
        }

        static {
            ThemeSetting themeSetting = new ThemeSetting(true);
            defaultInstance = themeSetting;
            themeSetting.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ThemeSetting> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.ThemeSettingOrBuilder
        public boolean hasNCurrTheme() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.ThemeSettingOrBuilder
        public ThemeSet getNCurrTheme() {
            return this.nCurrTheme_;
        }

        private void initFields() {
            this.nCurrTheme_ = ThemeSet.InvaildColor;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrTheme_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrTheme_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ThemeSetting parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ThemeSetting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ThemeSetting parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ThemeSetting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ThemeSetting parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ThemeSetting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ThemeSetting parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ThemeSetting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ThemeSetting parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ThemeSetting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ThemeSetting prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ThemeSettingOrBuilder {
            private int bitField0_;
            private ThemeSet nCurrTheme_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_ThemeSetting_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_ThemeSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(ThemeSetting.class, Builder.class);
            }

            private Builder() {
                this.nCurrTheme_ = ThemeSet.InvaildColor;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrTheme_ = ThemeSet.InvaildColor;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ThemeSetting.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrTheme_ = ThemeSet.InvaildColor;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_ThemeSetting_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ThemeSetting getDefaultInstanceForType() {
                return ThemeSetting.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ThemeSetting build() {
                ThemeSetting result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ThemeSetting buildPartial() {
                ThemeSetting result = new ThemeSetting(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrTheme_ = this.nCurrTheme_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ThemeSetting) {
                    return mergeFrom((ThemeSetting) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ThemeSetting other) {
                if (other == ThemeSetting.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrTheme()) {
                    setNCurrTheme(other.getNCurrTheme());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ThemeSetting parsedMessage = null;
                try {
                    try {
                        parsedMessage = ThemeSetting.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.ThemeSettingOrBuilder
            public boolean hasNCurrTheme() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.ThemeSettingOrBuilder
            public ThemeSet getNCurrTheme() {
                return this.nCurrTheme_;
            }

            public Builder setNCurrTheme(ThemeSet value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrTheme_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrTheme() {
                this.bitField0_ &= -2;
                this.nCurrTheme_ = ThemeSet.InvaildColor;
                onChanged();
                return this;
            }
        }
    }

    public static final class ChimeSetting extends GeneratedMessage implements ChimeSettingOrBuilder {
        public static final int NCURRSOUNDTYPE_FIELD_NUMBER = 1;
        public static Parser<ChimeSetting> PARSER = new AbstractParser<ChimeSetting>() { // from class: com.autolink.interaction.InteractionProto.ChimeSetting.1
            @Override // com.google.protobuf.Parser
            public ChimeSetting parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ChimeSetting(input, extensionRegistry);
            }
        };
        private static final ChimeSetting defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private SoundType nCurrSoundType_;
        private final UnknownFieldSet unknownFields;

        private ChimeSetting(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ChimeSetting(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ChimeSetting getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ChimeSetting getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ChimeSetting(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            SoundType value = SoundType.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrSoundType_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_ChimeSetting_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_ChimeSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(ChimeSetting.class, Builder.class);
        }

        static {
            ChimeSetting chimeSetting = new ChimeSetting(true);
            defaultInstance = chimeSetting;
            chimeSetting.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ChimeSetting> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.ChimeSettingOrBuilder
        public boolean hasNCurrSoundType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.ChimeSettingOrBuilder
        public SoundType getNCurrSoundType() {
            return this.nCurrSoundType_;
        }

        private void initFields() {
            this.nCurrSoundType_ = SoundType.STANDARD_SOUND;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrSoundType_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrSoundType_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ChimeSetting parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ChimeSetting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ChimeSetting parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ChimeSetting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ChimeSetting parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ChimeSetting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ChimeSetting parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ChimeSetting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ChimeSetting parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ChimeSetting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ChimeSetting prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ChimeSettingOrBuilder {
            private int bitField0_;
            private SoundType nCurrSoundType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_ChimeSetting_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_ChimeSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(ChimeSetting.class, Builder.class);
            }

            private Builder() {
                this.nCurrSoundType_ = SoundType.STANDARD_SOUND;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrSoundType_ = SoundType.STANDARD_SOUND;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ChimeSetting.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrSoundType_ = SoundType.STANDARD_SOUND;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_ChimeSetting_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ChimeSetting getDefaultInstanceForType() {
                return ChimeSetting.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChimeSetting build() {
                ChimeSetting result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChimeSetting buildPartial() {
                ChimeSetting result = new ChimeSetting(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrSoundType_ = this.nCurrSoundType_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ChimeSetting) {
                    return mergeFrom((ChimeSetting) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ChimeSetting other) {
                if (other == ChimeSetting.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrSoundType()) {
                    setNCurrSoundType(other.getNCurrSoundType());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ChimeSetting parsedMessage = null;
                try {
                    try {
                        parsedMessage = ChimeSetting.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.ChimeSettingOrBuilder
            public boolean hasNCurrSoundType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.ChimeSettingOrBuilder
            public SoundType getNCurrSoundType() {
                return this.nCurrSoundType_;
            }

            public Builder setNCurrSoundType(SoundType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrSoundType_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrSoundType() {
                this.bitField0_ &= -2;
                this.nCurrSoundType_ = SoundType.STANDARD_SOUND;
                onChanged();
                return this;
            }
        }
    }

    public static final class NaviSetting extends GeneratedMessage implements NaviSettingOrBuilder {
        public static final int NCURRMEDNAVISTAT_FIELD_NUMBER = 2;
        public static final int NMAPTYPE_FIELD_NUMBER = 1;
        public static Parser<NaviSetting> PARSER = new AbstractParser<NaviSetting>() { // from class: com.autolink.interaction.InteractionProto.NaviSetting.1
            @Override // com.google.protobuf.Parser
            public NaviSetting parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new NaviSetting(input, extensionRegistry);
            }
        };
        private static final NaviSetting defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private MediaNavigationalstate nCurrMedNaviStat_;
        private MapType nMapType_;
        private final UnknownFieldSet unknownFields;

        private NaviSetting(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private NaviSetting(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static NaviSetting getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NaviSetting getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NaviSetting(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                MapType value = MapType.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.nMapType_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag != 16) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue2 = input.readEnum();
                                MediaNavigationalstate value2 = MediaNavigationalstate.valueOf(rawValue2);
                                if (value2 != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.nCurrMedNaviStat_ = value2;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue2);
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_NaviSetting_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_NaviSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(NaviSetting.class, Builder.class);
        }

        static {
            NaviSetting naviSetting = new NaviSetting(true);
            defaultInstance = naviSetting;
            naviSetting.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NaviSetting> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
        public boolean hasNMapType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
        public MapType getNMapType() {
            return this.nMapType_;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
        public boolean hasNCurrMedNaviStat() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
        public MediaNavigationalstate getNCurrMedNaviStat() {
            return this.nCurrMedNaviStat_;
        }

        private void initFields() {
            this.nMapType_ = MapType.MAP_TYPE_INVALID;
            this.nCurrMedNaviStat_ = MediaNavigationalstate.NAVITYPESTATE_INVALID;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nMapType_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.nCurrMedNaviStat_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nMapType_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.nCurrMedNaviStat_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static NaviSetting parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NaviSetting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NaviSetting parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NaviSetting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NaviSetting parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NaviSetting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static NaviSetting parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static NaviSetting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static NaviSetting parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NaviSetting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(NaviSetting prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements NaviSettingOrBuilder {
            private int bitField0_;
            private MediaNavigationalstate nCurrMedNaviStat_;
            private MapType nMapType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_NaviSetting_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_NaviSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(NaviSetting.class, Builder.class);
            }

            private Builder() {
                this.nMapType_ = MapType.MAP_TYPE_INVALID;
                this.nCurrMedNaviStat_ = MediaNavigationalstate.NAVITYPESTATE_INVALID;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nMapType_ = MapType.MAP_TYPE_INVALID;
                this.nCurrMedNaviStat_ = MediaNavigationalstate.NAVITYPESTATE_INVALID;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = NaviSetting.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nMapType_ = MapType.MAP_TYPE_INVALID;
                this.bitField0_ &= -2;
                this.nCurrMedNaviStat_ = MediaNavigationalstate.NAVITYPESTATE_INVALID;
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_NaviSetting_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NaviSetting getDefaultInstanceForType() {
                return NaviSetting.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NaviSetting build() {
                NaviSetting result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NaviSetting buildPartial() {
                NaviSetting result = new NaviSetting(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nMapType_ = this.nMapType_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.nCurrMedNaviStat_ = this.nCurrMedNaviStat_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof NaviSetting) {
                    return mergeFrom((NaviSetting) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(NaviSetting other) {
                if (other == NaviSetting.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNMapType()) {
                    setNMapType(other.getNMapType());
                }
                if (other.hasNCurrMedNaviStat()) {
                    setNCurrMedNaviStat(other.getNCurrMedNaviStat());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                NaviSetting parsedMessage = null;
                try {
                    try {
                        parsedMessage = NaviSetting.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
            public boolean hasNMapType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
            public MapType getNMapType() {
                return this.nMapType_;
            }

            public Builder setNMapType(MapType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nMapType_ = value;
                onChanged();
                return this;
            }

            public Builder clearNMapType() {
                this.bitField0_ &= -2;
                this.nMapType_ = MapType.MAP_TYPE_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
            public boolean hasNCurrMedNaviStat() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviSettingOrBuilder
            public MediaNavigationalstate getNCurrMedNaviStat() {
                return this.nCurrMedNaviStat_;
            }

            public Builder setNCurrMedNaviStat(MediaNavigationalstate value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.nCurrMedNaviStat_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrMedNaviStat() {
                this.bitField0_ &= -3;
                this.nCurrMedNaviStat_ = MediaNavigationalstate.NAVITYPESTATE_INVALID;
                onChanged();
                return this;
            }
        }
    }

    public static final class HotKeySetting extends GeneratedMessage implements HotKeySettingOrBuilder {
        public static final int NCURRACCTIMER_FIELD_NUMBER = 12;
        public static final int NCURRADASSTAT_FIELD_NUMBER = 7;
        public static final int NCURRINSDISPLAY_FIELD_NUMBER = 8;
        public static final int NCURRLIGHTSTAT_FIELD_NUMBER = 4;
        public static final int NCURRLIGHT_FIELD_NUMBER = 5;
        public static final int NCURRMILEINFO_FIELD_NUMBER = 9;
        public static final int NCURRMILEUNIT_FIELD_NUMBER = 10;
        public static final int NCURRMILEVALUE_FIELD_NUMBER = 11;
        public static final int NCURRNAVITYPE_FIELD_NUMBER = 6;
        public static final int NCURRSPEEDLIMIT_FIELD_NUMBER = 13;
        public static final int NCURRTHEMETYPE_FIELD_NUMBER = 3;
        public static final int NVEHICLEID_FIELD_NUMBER = 2;
        public static final int NVEHICLETYPE_FIELD_NUMBER = 1;
        public static Parser<HotKeySetting> PARSER = new AbstractParser<HotKeySetting>() { // from class: com.autolink.interaction.InteractionProto.HotKeySetting.1
            @Override // com.google.protobuf.Parser
            public HotKeySetting parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new HotKeySetting(input, extensionRegistry);
            }
        };
        private static final HotKeySetting defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private VehicleAccTimer nCurrAccTimer_;
        private VehicleAdasStat nCurrAdasStat_;
        private VehicleINSDisp nCurrINSDisplay_;
        private VehicleLightGearStat nCurrLightStat_;
        private VehicleLightGear nCurrLight_;
        private VehicleMileInfo nCurrMileInfo_;
        private VehicleMileUnit nCurrMileUnit_;
        private VehicleMileValue nCurrMileValue_;
        private VehicleNaviType nCurrNaviType_;
        private VehicleSpeedLimit nCurrSpeedLimit_;
        private VehicleThemeType nCurrThemeType_;
        private VehicleID nVehicleID_;
        private VehicleType nVehicleType_;
        private final UnknownFieldSet unknownFields;

        private HotKeySetting(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private HotKeySetting(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static HotKeySetting getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public HotKeySetting getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private HotKeySetting(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            switch (tag) {
                                case 0:
                                    done = true;
                                    break;
                                case 8:
                                    int rawValue = input.readEnum();
                                    VehicleType value = VehicleType.valueOf(rawValue);
                                    if (value != null) {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.nVehicleType_ = value;
                                    } else {
                                        unknownFields.mergeVarintField(1, rawValue);
                                    }
                                    break;
                                case 16:
                                    int rawValue2 = input.readEnum();
                                    VehicleID value2 = VehicleID.valueOf(rawValue2);
                                    if (value2 != null) {
                                        this.bitField0_ = 2 | this.bitField0_;
                                        this.nVehicleID_ = value2;
                                    } else {
                                        unknownFields.mergeVarintField(2, rawValue2);
                                    }
                                    break;
                                case 24:
                                    int rawValue3 = input.readEnum();
                                    VehicleThemeType value3 = VehicleThemeType.valueOf(rawValue3);
                                    if (value3 == null) {
                                        unknownFields.mergeVarintField(3, rawValue3);
                                    } else {
                                        this.bitField0_ = 4 | this.bitField0_;
                                        this.nCurrThemeType_ = value3;
                                    }
                                    break;
                                case SUSD_DISPLAY3_MASK_VALUE:
                                    int rawValue4 = input.readEnum();
                                    VehicleLightGearStat value4 = VehicleLightGearStat.valueOf(rawValue4);
                                    if (value4 != null) {
                                        this.bitField0_ = 8 | this.bitField0_;
                                        this.nCurrLightStat_ = value4;
                                    } else {
                                        unknownFields.mergeVarintField(4, rawValue4);
                                    }
                                    break;
                                case 40:
                                    int rawValue5 = input.readEnum();
                                    VehicleLightGear value5 = VehicleLightGear.valueOf(rawValue5);
                                    if (value5 == null) {
                                        unknownFields.mergeVarintField(5, rawValue5);
                                    } else {
                                        this.bitField0_ |= 16;
                                        this.nCurrLight_ = value5;
                                    }
                                    break;
                                case 48:
                                    int rawValue6 = input.readEnum();
                                    VehicleNaviType value6 = VehicleNaviType.valueOf(rawValue6);
                                    if (value6 == null) {
                                        unknownFields.mergeVarintField(6, rawValue6);
                                    } else {
                                        this.bitField0_ |= 32;
                                        this.nCurrNaviType_ = value6;
                                    }
                                    break;
                                case DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE:
                                    int rawValue7 = input.readEnum();
                                    VehicleAdasStat value7 = VehicleAdasStat.valueOf(rawValue7);
                                    if (value7 == null) {
                                        unknownFields.mergeVarintField(7, rawValue7);
                                    } else {
                                        this.bitField0_ |= 64;
                                        this.nCurrAdasStat_ = value7;
                                    }
                                    break;
                                case 64:
                                    int rawValue8 = input.readEnum();
                                    VehicleINSDisp value8 = VehicleINSDisp.valueOf(rawValue8);
                                    if (value8 == null) {
                                        unknownFields.mergeVarintField(8, rawValue8);
                                    } else {
                                        this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                                        this.nCurrINSDisplay_ = value8;
                                    }
                                    break;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_4_VALUE:
                                    int rawValue9 = input.readEnum();
                                    VehicleMileInfo value9 = VehicleMileInfo.valueOf(rawValue9);
                                    if (value9 == null) {
                                        unknownFields.mergeVarintField(9, rawValue9);
                                    } else {
                                        this.bitField0_ |= 256;
                                        this.nCurrMileInfo_ = value9;
                                    }
                                    break;
                                case THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5_VALUE:
                                    int rawValue10 = input.readEnum();
                                    VehicleMileUnit value10 = VehicleMileUnit.valueOf(rawValue10);
                                    if (value10 == null) {
                                        unknownFields.mergeVarintField(10, rawValue10);
                                    } else {
                                        this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                                        this.nCurrMileUnit_ = value10;
                                    }
                                    break;
                                case 88:
                                    int rawValue11 = input.readEnum();
                                    VehicleMileValue value11 = VehicleMileValue.valueOf(rawValue11);
                                    if (value11 == null) {
                                        unknownFields.mergeVarintField(11, rawValue11);
                                    } else {
                                        this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                                        this.nCurrMileValue_ = value11;
                                    }
                                    break;
                                case SUSD_IVI_STATE_OF_HEALTH_VALUE:
                                    int rawValue12 = input.readEnum();
                                    VehicleAccTimer value12 = VehicleAccTimer.valueOf(rawValue12);
                                    if (value12 == null) {
                                        unknownFields.mergeVarintField(12, rawValue12);
                                    } else {
                                        this.bitField0_ |= 2048;
                                        this.nCurrAccTimer_ = value12;
                                    }
                                    break;
                                case SUSD_DRIVER_DOOR_OPENED_VALUE:
                                    int rawValue13 = input.readEnum();
                                    VehicleSpeedLimit value13 = VehicleSpeedLimit.valueOf(rawValue13);
                                    if (value13 == null) {
                                        unknownFields.mergeVarintField(13, rawValue13);
                                    } else {
                                        this.bitField0_ |= 4096;
                                        this.nCurrSpeedLimit_ = value13;
                                    }
                                    break;
                                default:
                                    if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                        done = true;
                                    }
                                    break;
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_HotKeySetting_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_HotKeySetting_fieldAccessorTable.ensureFieldAccessorsInitialized(HotKeySetting.class, Builder.class);
        }

        static {
            HotKeySetting hotKeySetting = new HotKeySetting(true);
            defaultInstance = hotKeySetting;
            hotKeySetting.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<HotKeySetting> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNVehicleType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleType getNVehicleType() {
            return this.nVehicleType_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNVehicleID() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleID getNVehicleID() {
            return this.nVehicleID_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrThemeType() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleThemeType getNCurrThemeType() {
            return this.nCurrThemeType_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrLightStat() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleLightGearStat getNCurrLightStat() {
            return this.nCurrLightStat_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrLight() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleLightGear getNCurrLight() {
            return this.nCurrLight_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrNaviType() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleNaviType getNCurrNaviType() {
            return this.nCurrNaviType_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrAdasStat() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleAdasStat getNCurrAdasStat() {
            return this.nCurrAdasStat_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrINSDisplay() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleINSDisp getNCurrINSDisplay() {
            return this.nCurrINSDisplay_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrMileInfo() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleMileInfo getNCurrMileInfo() {
            return this.nCurrMileInfo_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrMileUnit() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleMileUnit getNCurrMileUnit() {
            return this.nCurrMileUnit_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrMileValue() {
            return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleMileValue getNCurrMileValue() {
            return this.nCurrMileValue_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrAccTimer() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleAccTimer getNCurrAccTimer() {
            return this.nCurrAccTimer_;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public boolean hasNCurrSpeedLimit() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
        public VehicleSpeedLimit getNCurrSpeedLimit() {
            return this.nCurrSpeedLimit_;
        }

        private void initFields() {
            this.nVehicleType_ = VehicleType.VEHICLETYPE_EV;
            this.nVehicleID_ = VehicleID.VEHICLEID_CLASSICMODEL;
            this.nCurrThemeType_ = VehicleThemeType.VEHICLEID_INVALID;
            this.nCurrLightStat_ = VehicleLightGearStat.LIGHTSTATUS_NORMAL;
            this.nCurrLight_ = VehicleLightGear.LIGHTGEAR_1;
            this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
            this.nCurrAdasStat_ = VehicleAdasStat.VEHADASSTAT_INVALID;
            this.nCurrINSDisplay_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
            this.nCurrMileInfo_ = VehicleMileInfo.TOTALMILEAGE;
            this.nCurrMileUnit_ = VehicleMileUnit.MILEUNIT_INVALID;
            this.nCurrMileValue_ = VehicleMileValue.TOTALMILEVALUE;
            this.nCurrAccTimer_ = VehicleAccTimer.ACCTIMER_INVALID;
            this.nCurrSpeedLimit_ = VehicleSpeedLimit.SPEEDLIMIT_INVALID;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nVehicleType_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.nVehicleID_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.nCurrThemeType_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeEnum(4, this.nCurrLightStat_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeEnum(5, this.nCurrLight_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeEnum(6, this.nCurrNaviType_.getNumber());
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeEnum(7, this.nCurrAdasStat_.getNumber());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeEnum(8, this.nCurrINSDisplay_.getNumber());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeEnum(9, this.nCurrMileInfo_.getNumber());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                output.writeEnum(10, this.nCurrMileUnit_.getNumber());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                output.writeEnum(11, this.nCurrMileValue_.getNumber());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeEnum(12, this.nCurrAccTimer_.getNumber());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeEnum(13, this.nCurrSpeedLimit_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nVehicleType_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.nVehicleID_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.nCurrThemeType_.getNumber());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeEnumSize(4, this.nCurrLightStat_.getNumber());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeEnumSize(5, this.nCurrLight_.getNumber());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeEnumSize(6, this.nCurrNaviType_.getNumber());
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeEnumSize(7, this.nCurrAdasStat_.getNumber());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeEnumSize(8, this.nCurrINSDisplay_.getNumber());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeEnumSize(9, this.nCurrMileInfo_.getNumber());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                size2 += CodedOutputStream.computeEnumSize(10, this.nCurrMileUnit_.getNumber());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                size2 += CodedOutputStream.computeEnumSize(11, this.nCurrMileValue_.getNumber());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeEnumSize(12, this.nCurrAccTimer_.getNumber());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeEnumSize(13, this.nCurrSpeedLimit_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static HotKeySetting parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static HotKeySetting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static HotKeySetting parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static HotKeySetting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static HotKeySetting parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static HotKeySetting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static HotKeySetting parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static HotKeySetting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static HotKeySetting parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static HotKeySetting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(HotKeySetting prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements HotKeySettingOrBuilder {
            private int bitField0_;
            private VehicleAccTimer nCurrAccTimer_;
            private VehicleAdasStat nCurrAdasStat_;
            private VehicleINSDisp nCurrINSDisplay_;
            private VehicleLightGearStat nCurrLightStat_;
            private VehicleLightGear nCurrLight_;
            private VehicleMileInfo nCurrMileInfo_;
            private VehicleMileUnit nCurrMileUnit_;
            private VehicleMileValue nCurrMileValue_;
            private VehicleNaviType nCurrNaviType_;
            private VehicleSpeedLimit nCurrSpeedLimit_;
            private VehicleThemeType nCurrThemeType_;
            private VehicleID nVehicleID_;
            private VehicleType nVehicleType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_HotKeySetting_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_HotKeySetting_fieldAccessorTable.ensureFieldAccessorsInitialized(HotKeySetting.class, Builder.class);
            }

            private Builder() {
                this.nVehicleType_ = VehicleType.VEHICLETYPE_EV;
                this.nVehicleID_ = VehicleID.VEHICLEID_CLASSICMODEL;
                this.nCurrThemeType_ = VehicleThemeType.VEHICLEID_INVALID;
                this.nCurrLightStat_ = VehicleLightGearStat.LIGHTSTATUS_NORMAL;
                this.nCurrLight_ = VehicleLightGear.LIGHTGEAR_1;
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                this.nCurrAdasStat_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                this.nCurrINSDisplay_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                this.nCurrMileInfo_ = VehicleMileInfo.TOTALMILEAGE;
                this.nCurrMileUnit_ = VehicleMileUnit.MILEUNIT_INVALID;
                this.nCurrMileValue_ = VehicleMileValue.TOTALMILEVALUE;
                this.nCurrAccTimer_ = VehicleAccTimer.ACCTIMER_INVALID;
                this.nCurrSpeedLimit_ = VehicleSpeedLimit.SPEEDLIMIT_INVALID;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nVehicleType_ = VehicleType.VEHICLETYPE_EV;
                this.nVehicleID_ = VehicleID.VEHICLEID_CLASSICMODEL;
                this.nCurrThemeType_ = VehicleThemeType.VEHICLEID_INVALID;
                this.nCurrLightStat_ = VehicleLightGearStat.LIGHTSTATUS_NORMAL;
                this.nCurrLight_ = VehicleLightGear.LIGHTGEAR_1;
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                this.nCurrAdasStat_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                this.nCurrINSDisplay_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                this.nCurrMileInfo_ = VehicleMileInfo.TOTALMILEAGE;
                this.nCurrMileUnit_ = VehicleMileUnit.MILEUNIT_INVALID;
                this.nCurrMileValue_ = VehicleMileValue.TOTALMILEVALUE;
                this.nCurrAccTimer_ = VehicleAccTimer.ACCTIMER_INVALID;
                this.nCurrSpeedLimit_ = VehicleSpeedLimit.SPEEDLIMIT_INVALID;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = HotKeySetting.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nVehicleType_ = VehicleType.VEHICLETYPE_EV;
                this.bitField0_ &= -2;
                this.nVehicleID_ = VehicleID.VEHICLEID_CLASSICMODEL;
                this.bitField0_ &= -3;
                this.nCurrThemeType_ = VehicleThemeType.VEHICLEID_INVALID;
                this.bitField0_ &= -5;
                this.nCurrLightStat_ = VehicleLightGearStat.LIGHTSTATUS_NORMAL;
                this.bitField0_ &= -9;
                this.nCurrLight_ = VehicleLightGear.LIGHTGEAR_1;
                this.bitField0_ &= -17;
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                this.bitField0_ &= -33;
                this.nCurrAdasStat_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                this.bitField0_ &= -65;
                this.nCurrINSDisplay_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                this.bitField0_ &= -129;
                this.nCurrMileInfo_ = VehicleMileInfo.TOTALMILEAGE;
                this.bitField0_ &= -257;
                this.nCurrMileUnit_ = VehicleMileUnit.MILEUNIT_INVALID;
                this.bitField0_ &= -513;
                this.nCurrMileValue_ = VehicleMileValue.TOTALMILEVALUE;
                this.bitField0_ &= -1025;
                this.nCurrAccTimer_ = VehicleAccTimer.ACCTIMER_INVALID;
                this.bitField0_ &= -2049;
                this.nCurrSpeedLimit_ = VehicleSpeedLimit.SPEEDLIMIT_INVALID;
                this.bitField0_ &= -4097;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_HotKeySetting_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public HotKeySetting getDefaultInstanceForType() {
                return HotKeySetting.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public HotKeySetting build() {
                HotKeySetting result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public HotKeySetting buildPartial() {
                HotKeySetting result = new HotKeySetting(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nVehicleType_ = this.nVehicleType_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.nVehicleID_ = this.nVehicleID_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.nCurrThemeType_ = this.nCurrThemeType_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.nCurrLightStat_ = this.nCurrLightStat_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.nCurrLight_ = this.nCurrLight_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.nCurrNaviType_ = this.nCurrNaviType_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.nCurrAdasStat_ = this.nCurrAdasStat_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.nCurrINSDisplay_ = this.nCurrINSDisplay_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 256;
                }
                result.nCurrMileInfo_ = this.nCurrMileInfo_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                }
                result.nCurrMileUnit_ = this.nCurrMileUnit_;
                if ((from_bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                    to_bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                }
                result.nCurrMileValue_ = this.nCurrMileValue_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 2048;
                }
                result.nCurrAccTimer_ = this.nCurrAccTimer_;
                if ((from_bitField0_ & 4096) == 4096) {
                    to_bitField0_ |= 4096;
                }
                result.nCurrSpeedLimit_ = this.nCurrSpeedLimit_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof HotKeySetting) {
                    return mergeFrom((HotKeySetting) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(HotKeySetting other) {
                if (other == HotKeySetting.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNVehicleType()) {
                    setNVehicleType(other.getNVehicleType());
                }
                if (other.hasNVehicleID()) {
                    setNVehicleID(other.getNVehicleID());
                }
                if (other.hasNCurrThemeType()) {
                    setNCurrThemeType(other.getNCurrThemeType());
                }
                if (other.hasNCurrLightStat()) {
                    setNCurrLightStat(other.getNCurrLightStat());
                }
                if (other.hasNCurrLight()) {
                    setNCurrLight(other.getNCurrLight());
                }
                if (other.hasNCurrNaviType()) {
                    setNCurrNaviType(other.getNCurrNaviType());
                }
                if (other.hasNCurrAdasStat()) {
                    setNCurrAdasStat(other.getNCurrAdasStat());
                }
                if (other.hasNCurrINSDisplay()) {
                    setNCurrINSDisplay(other.getNCurrINSDisplay());
                }
                if (other.hasNCurrMileInfo()) {
                    setNCurrMileInfo(other.getNCurrMileInfo());
                }
                if (other.hasNCurrMileUnit()) {
                    setNCurrMileUnit(other.getNCurrMileUnit());
                }
                if (other.hasNCurrMileValue()) {
                    setNCurrMileValue(other.getNCurrMileValue());
                }
                if (other.hasNCurrAccTimer()) {
                    setNCurrAccTimer(other.getNCurrAccTimer());
                }
                if (other.hasNCurrSpeedLimit()) {
                    setNCurrSpeedLimit(other.getNCurrSpeedLimit());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                HotKeySetting parsedMessage = null;
                try {
                    try {
                        parsedMessage = HotKeySetting.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNVehicleType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleType getNVehicleType() {
                return this.nVehicleType_;
            }

            public Builder setNVehicleType(VehicleType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nVehicleType_ = value;
                onChanged();
                return this;
            }

            public Builder clearNVehicleType() {
                this.bitField0_ &= -2;
                this.nVehicleType_ = VehicleType.VEHICLETYPE_EV;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNVehicleID() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleID getNVehicleID() {
                return this.nVehicleID_;
            }

            public Builder setNVehicleID(VehicleID value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.nVehicleID_ = value;
                onChanged();
                return this;
            }

            public Builder clearNVehicleID() {
                this.bitField0_ &= -3;
                this.nVehicleID_ = VehicleID.VEHICLEID_CLASSICMODEL;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrThemeType() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleThemeType getNCurrThemeType() {
                return this.nCurrThemeType_;
            }

            public Builder setNCurrThemeType(VehicleThemeType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.nCurrThemeType_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrThemeType() {
                this.bitField0_ &= -5;
                this.nCurrThemeType_ = VehicleThemeType.VEHICLEID_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrLightStat() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleLightGearStat getNCurrLightStat() {
                return this.nCurrLightStat_;
            }

            public Builder setNCurrLightStat(VehicleLightGearStat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.nCurrLightStat_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrLightStat() {
                this.bitField0_ &= -9;
                this.nCurrLightStat_ = VehicleLightGearStat.LIGHTSTATUS_NORMAL;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrLight() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleLightGear getNCurrLight() {
                return this.nCurrLight_;
            }

            public Builder setNCurrLight(VehicleLightGear value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.nCurrLight_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrLight() {
                this.bitField0_ &= -17;
                this.nCurrLight_ = VehicleLightGear.LIGHTGEAR_1;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrNaviType() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleNaviType getNCurrNaviType() {
                return this.nCurrNaviType_;
            }

            public Builder setNCurrNaviType(VehicleNaviType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32;
                this.nCurrNaviType_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrNaviType() {
                this.bitField0_ &= -33;
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrAdasStat() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleAdasStat getNCurrAdasStat() {
                return this.nCurrAdasStat_;
            }

            public Builder setNCurrAdasStat(VehicleAdasStat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.nCurrAdasStat_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrAdasStat() {
                this.bitField0_ &= -65;
                this.nCurrAdasStat_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrINSDisplay() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleINSDisp getNCurrINSDisplay() {
                return this.nCurrINSDisplay_;
            }

            public Builder setNCurrINSDisplay(VehicleINSDisp value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.nCurrINSDisplay_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrINSDisplay() {
                this.bitField0_ &= -129;
                this.nCurrINSDisplay_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrMileInfo() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleMileInfo getNCurrMileInfo() {
                return this.nCurrMileInfo_;
            }

            public Builder setNCurrMileInfo(VehicleMileInfo value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.nCurrMileInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrMileInfo() {
                this.bitField0_ &= -257;
                this.nCurrMileInfo_ = VehicleMileInfo.TOTALMILEAGE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrMileUnit() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleMileUnit getNCurrMileUnit() {
                return this.nCurrMileUnit_;
            }

            public Builder setNCurrMileUnit(VehicleMileUnit value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.nCurrMileUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrMileUnit() {
                this.bitField0_ &= -513;
                this.nCurrMileUnit_ = VehicleMileUnit.MILEUNIT_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrMileValue() {
                return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleMileValue getNCurrMileValue() {
                return this.nCurrMileValue_;
            }

            public Builder setNCurrMileValue(VehicleMileValue value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.nCurrMileValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrMileValue() {
                this.bitField0_ &= -1025;
                this.nCurrMileValue_ = VehicleMileValue.TOTALMILEVALUE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrAccTimer() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleAccTimer getNCurrAccTimer() {
                return this.nCurrAccTimer_;
            }

            public Builder setNCurrAccTimer(VehicleAccTimer value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2048;
                this.nCurrAccTimer_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrAccTimer() {
                this.bitField0_ &= -2049;
                this.nCurrAccTimer_ = VehicleAccTimer.ACCTIMER_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public boolean hasNCurrSpeedLimit() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // com.autolink.interaction.InteractionProto.HotKeySettingOrBuilder
            public VehicleSpeedLimit getNCurrSpeedLimit() {
                return this.nCurrSpeedLimit_;
            }

            public Builder setNCurrSpeedLimit(VehicleSpeedLimit value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4096;
                this.nCurrSpeedLimit_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrSpeedLimit() {
                this.bitField0_ &= -4097;
                this.nCurrSpeedLimit_ = VehicleSpeedLimit.SPEEDLIMIT_INVALID;
                onChanged();
                return this;
            }
        }
    }

    public static final class UpdateSettingThemeReq extends GeneratedMessage implements UpdateSettingThemeReqOrBuilder {
        public static final int NCURRTHEMEINFOREQ_FIELD_NUMBER = 1;
        public static Parser<UpdateSettingThemeReq> PARSER = new AbstractParser<UpdateSettingThemeReq>() { // from class: com.autolink.interaction.InteractionProto.UpdateSettingThemeReq.1
            @Override // com.google.protobuf.Parser
            public UpdateSettingThemeReq parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new UpdateSettingThemeReq(input, extensionRegistry);
            }
        };
        private static final UpdateSettingThemeReq defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private ThemeSet nCurrThemeInfoReq_;
        private final UnknownFieldSet unknownFields;

        private UpdateSettingThemeReq(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UpdateSettingThemeReq(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UpdateSettingThemeReq getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateSettingThemeReq getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateSettingThemeReq(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            ThemeSet value = ThemeSet.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrThemeInfoReq_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_UpdateSettingThemeReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_UpdateSettingThemeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateSettingThemeReq.class, Builder.class);
        }

        static {
            UpdateSettingThemeReq updateSettingThemeReq = new UpdateSettingThemeReq(true);
            defaultInstance = updateSettingThemeReq;
            updateSettingThemeReq.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateSettingThemeReq> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateSettingThemeReqOrBuilder
        public boolean hasNCurrThemeInfoReq() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateSettingThemeReqOrBuilder
        public ThemeSet getNCurrThemeInfoReq() {
            return this.nCurrThemeInfoReq_;
        }

        private void initFields() {
            this.nCurrThemeInfoReq_ = ThemeSet.InvaildColor;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrThemeInfoReq_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrThemeInfoReq_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateSettingThemeReq parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateSettingThemeReq parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateSettingThemeReq parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateSettingThemeReq parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateSettingThemeReq parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateSettingThemeReq parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static UpdateSettingThemeReq parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static UpdateSettingThemeReq parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static UpdateSettingThemeReq parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateSettingThemeReq parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdateSettingThemeReq prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements UpdateSettingThemeReqOrBuilder {
            private int bitField0_;
            private ThemeSet nCurrThemeInfoReq_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_UpdateSettingThemeReq_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_UpdateSettingThemeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateSettingThemeReq.class, Builder.class);
            }

            private Builder() {
                this.nCurrThemeInfoReq_ = ThemeSet.InvaildColor;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrThemeInfoReq_ = ThemeSet.InvaildColor;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateSettingThemeReq.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrThemeInfoReq_ = ThemeSet.InvaildColor;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_UpdateSettingThemeReq_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateSettingThemeReq getDefaultInstanceForType() {
                return UpdateSettingThemeReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateSettingThemeReq build() {
                UpdateSettingThemeReq result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateSettingThemeReq buildPartial() {
                UpdateSettingThemeReq result = new UpdateSettingThemeReq(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrThemeInfoReq_ = this.nCurrThemeInfoReq_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof UpdateSettingThemeReq) {
                    return mergeFrom((UpdateSettingThemeReq) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UpdateSettingThemeReq other) {
                if (other == UpdateSettingThemeReq.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrThemeInfoReq()) {
                    setNCurrThemeInfoReq(other.getNCurrThemeInfoReq());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                UpdateSettingThemeReq parsedMessage = null;
                try {
                    try {
                        parsedMessage = UpdateSettingThemeReq.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateSettingThemeReqOrBuilder
            public boolean hasNCurrThemeInfoReq() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateSettingThemeReqOrBuilder
            public ThemeSet getNCurrThemeInfoReq() {
                return this.nCurrThemeInfoReq_;
            }

            public Builder setNCurrThemeInfoReq(ThemeSet value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrThemeInfoReq_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrThemeInfoReq() {
                this.bitField0_ &= -2;
                this.nCurrThemeInfoReq_ = ThemeSet.InvaildColor;
                onChanged();
                return this;
            }
        }
    }

    public static final class UpdateHotKeyNaviReq extends GeneratedMessage implements UpdateHotKeyNaviReqOrBuilder {
        public static final int NCURRNAVITYPE_FIELD_NUMBER = 1;
        public static Parser<UpdateHotKeyNaviReq> PARSER = new AbstractParser<UpdateHotKeyNaviReq>() { // from class: com.autolink.interaction.InteractionProto.UpdateHotKeyNaviReq.1
            @Override // com.google.protobuf.Parser
            public UpdateHotKeyNaviReq parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new UpdateHotKeyNaviReq(input, extensionRegistry);
            }
        };
        private static final UpdateHotKeyNaviReq defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private VehicleNaviType nCurrNaviType_;
        private final UnknownFieldSet unknownFields;

        private UpdateHotKeyNaviReq(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UpdateHotKeyNaviReq(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UpdateHotKeyNaviReq getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateHotKeyNaviReq getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateHotKeyNaviReq(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            VehicleNaviType value = VehicleNaviType.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrNaviType_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyNaviReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyNaviReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyNaviReq.class, Builder.class);
        }

        static {
            UpdateHotKeyNaviReq updateHotKeyNaviReq = new UpdateHotKeyNaviReq(true);
            defaultInstance = updateHotKeyNaviReq;
            updateHotKeyNaviReq.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateHotKeyNaviReq> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyNaviReqOrBuilder
        public boolean hasNCurrNaviType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyNaviReqOrBuilder
        public VehicleNaviType getNCurrNaviType() {
            return this.nCurrNaviType_;
        }

        private void initFields() {
            this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrNaviType_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrNaviType_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateHotKeyNaviReq parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyNaviReq parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyNaviReq parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyNaviReq parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyNaviReq parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyNaviReq parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyNaviReq parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static UpdateHotKeyNaviReq parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyNaviReq parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyNaviReq parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdateHotKeyNaviReq prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements UpdateHotKeyNaviReqOrBuilder {
            private int bitField0_;
            private VehicleNaviType nCurrNaviType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyNaviReq_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyNaviReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyNaviReq.class, Builder.class);
            }

            private Builder() {
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateHotKeyNaviReq.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyNaviReq_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateHotKeyNaviReq getDefaultInstanceForType() {
                return UpdateHotKeyNaviReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyNaviReq build() {
                UpdateHotKeyNaviReq result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyNaviReq buildPartial() {
                UpdateHotKeyNaviReq result = new UpdateHotKeyNaviReq(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrNaviType_ = this.nCurrNaviType_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof UpdateHotKeyNaviReq) {
                    return mergeFrom((UpdateHotKeyNaviReq) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UpdateHotKeyNaviReq other) {
                if (other == UpdateHotKeyNaviReq.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrNaviType()) {
                    setNCurrNaviType(other.getNCurrNaviType());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                UpdateHotKeyNaviReq parsedMessage = null;
                try {
                    try {
                        parsedMessage = UpdateHotKeyNaviReq.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyNaviReqOrBuilder
            public boolean hasNCurrNaviType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyNaviReqOrBuilder
            public VehicleNaviType getNCurrNaviType() {
                return this.nCurrNaviType_;
            }

            public Builder setNCurrNaviType(VehicleNaviType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrNaviType_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrNaviType() {
                this.bitField0_ &= -2;
                this.nCurrNaviType_ = VehicleNaviType.VEHNAVITYPE_CLOSED;
                onChanged();
                return this;
            }
        }
    }

    public static final class UpdateHotKeyInsDisplayReq extends GeneratedMessage implements UpdateHotKeyInsDisplayReqOrBuilder {
        public static final int NCURRADASSTATREQ_FIELD_NUMBER = 1;
        public static Parser<UpdateHotKeyInsDisplayReq> PARSER = new AbstractParser<UpdateHotKeyInsDisplayReq>() { // from class: com.autolink.interaction.InteractionProto.UpdateHotKeyInsDisplayReq.1
            @Override // com.google.protobuf.Parser
            public UpdateHotKeyInsDisplayReq parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new UpdateHotKeyInsDisplayReq(input, extensionRegistry);
            }
        };
        private static final UpdateHotKeyInsDisplayReq defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private VehicleINSDisp nCurrAdasStatReq_;
        private final UnknownFieldSet unknownFields;

        private UpdateHotKeyInsDisplayReq(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UpdateHotKeyInsDisplayReq(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UpdateHotKeyInsDisplayReq getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateHotKeyInsDisplayReq getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateHotKeyInsDisplayReq(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            VehicleINSDisp value = VehicleINSDisp.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrAdasStatReq_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyInsDisplayReq.class, Builder.class);
        }

        static {
            UpdateHotKeyInsDisplayReq updateHotKeyInsDisplayReq = new UpdateHotKeyInsDisplayReq(true);
            defaultInstance = updateHotKeyInsDisplayReq;
            updateHotKeyInsDisplayReq.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateHotKeyInsDisplayReq> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyInsDisplayReqOrBuilder
        public boolean hasNCurrAdasStatReq() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyInsDisplayReqOrBuilder
        public VehicleINSDisp getNCurrAdasStatReq() {
            return this.nCurrAdasStatReq_;
        }

        private void initFields() {
            this.nCurrAdasStatReq_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrAdasStatReq_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrAdasStatReq_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyInsDisplayReq parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static UpdateHotKeyInsDisplayReq parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyInsDisplayReq parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdateHotKeyInsDisplayReq prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements UpdateHotKeyInsDisplayReqOrBuilder {
            private int bitField0_;
            private VehicleINSDisp nCurrAdasStatReq_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyInsDisplayReq.class, Builder.class);
            }

            private Builder() {
                this.nCurrAdasStatReq_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrAdasStatReq_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateHotKeyInsDisplayReq.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrAdasStatReq_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateHotKeyInsDisplayReq getDefaultInstanceForType() {
                return UpdateHotKeyInsDisplayReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyInsDisplayReq build() {
                UpdateHotKeyInsDisplayReq result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyInsDisplayReq buildPartial() {
                UpdateHotKeyInsDisplayReq result = new UpdateHotKeyInsDisplayReq(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrAdasStatReq_ = this.nCurrAdasStatReq_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof UpdateHotKeyInsDisplayReq) {
                    return mergeFrom((UpdateHotKeyInsDisplayReq) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UpdateHotKeyInsDisplayReq other) {
                if (other == UpdateHotKeyInsDisplayReq.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrAdasStatReq()) {
                    setNCurrAdasStatReq(other.getNCurrAdasStatReq());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                UpdateHotKeyInsDisplayReq parsedMessage = null;
                try {
                    try {
                        parsedMessage = UpdateHotKeyInsDisplayReq.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyInsDisplayReqOrBuilder
            public boolean hasNCurrAdasStatReq() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyInsDisplayReqOrBuilder
            public VehicleINSDisp getNCurrAdasStatReq() {
                return this.nCurrAdasStatReq_;
            }

            public Builder setNCurrAdasStatReq(VehicleINSDisp value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrAdasStatReq_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrAdasStatReq() {
                this.bitField0_ &= -2;
                this.nCurrAdasStatReq_ = VehicleINSDisp.DRIVINGINFO_CLOSE;
                onChanged();
                return this;
            }
        }
    }

    public static final class UpdateHotKeyAdasReq extends GeneratedMessage implements UpdateHotKeyAdasReqOrBuilder {
        public static final int NCURRADASSTATREQ_FIELD_NUMBER = 1;
        public static Parser<UpdateHotKeyAdasReq> PARSER = new AbstractParser<UpdateHotKeyAdasReq>() { // from class: com.autolink.interaction.InteractionProto.UpdateHotKeyAdasReq.1
            @Override // com.google.protobuf.Parser
            public UpdateHotKeyAdasReq parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new UpdateHotKeyAdasReq(input, extensionRegistry);
            }
        };
        private static final UpdateHotKeyAdasReq defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private VehicleAdasStat nCurrAdasStatReq_;
        private final UnknownFieldSet unknownFields;

        private UpdateHotKeyAdasReq(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UpdateHotKeyAdasReq(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UpdateHotKeyAdasReq getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateHotKeyAdasReq getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateHotKeyAdasReq(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            VehicleAdasStat value = VehicleAdasStat.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrAdasStatReq_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyAdasReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyAdasReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyAdasReq.class, Builder.class);
        }

        static {
            UpdateHotKeyAdasReq updateHotKeyAdasReq = new UpdateHotKeyAdasReq(true);
            defaultInstance = updateHotKeyAdasReq;
            updateHotKeyAdasReq.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateHotKeyAdasReq> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyAdasReqOrBuilder
        public boolean hasNCurrAdasStatReq() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyAdasReqOrBuilder
        public VehicleAdasStat getNCurrAdasStatReq() {
            return this.nCurrAdasStatReq_;
        }

        private void initFields() {
            this.nCurrAdasStatReq_ = VehicleAdasStat.VEHADASSTAT_INVALID;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrAdasStatReq_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrAdasStatReq_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateHotKeyAdasReq parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyAdasReq parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyAdasReq parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyAdasReq parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyAdasReq parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyAdasReq parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyAdasReq parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static UpdateHotKeyAdasReq parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyAdasReq parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyAdasReq parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdateHotKeyAdasReq prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements UpdateHotKeyAdasReqOrBuilder {
            private int bitField0_;
            private VehicleAdasStat nCurrAdasStatReq_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyAdasReq_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyAdasReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyAdasReq.class, Builder.class);
            }

            private Builder() {
                this.nCurrAdasStatReq_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrAdasStatReq_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateHotKeyAdasReq.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrAdasStatReq_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyAdasReq_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateHotKeyAdasReq getDefaultInstanceForType() {
                return UpdateHotKeyAdasReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyAdasReq build() {
                UpdateHotKeyAdasReq result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyAdasReq buildPartial() {
                UpdateHotKeyAdasReq result = new UpdateHotKeyAdasReq(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrAdasStatReq_ = this.nCurrAdasStatReq_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof UpdateHotKeyAdasReq) {
                    return mergeFrom((UpdateHotKeyAdasReq) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UpdateHotKeyAdasReq other) {
                if (other == UpdateHotKeyAdasReq.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrAdasStatReq()) {
                    setNCurrAdasStatReq(other.getNCurrAdasStatReq());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                UpdateHotKeyAdasReq parsedMessage = null;
                try {
                    try {
                        parsedMessage = UpdateHotKeyAdasReq.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyAdasReqOrBuilder
            public boolean hasNCurrAdasStatReq() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyAdasReqOrBuilder
            public VehicleAdasStat getNCurrAdasStatReq() {
                return this.nCurrAdasStatReq_;
            }

            public Builder setNCurrAdasStatReq(VehicleAdasStat value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrAdasStatReq_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrAdasStatReq() {
                this.bitField0_ &= -2;
                this.nCurrAdasStatReq_ = VehicleAdasStat.VEHADASSTAT_INVALID;
                onChanged();
                return this;
            }
        }
    }

    public static final class UpdateHotKeyThemeReq extends GeneratedMessage implements UpdateHotKeyThemeReqOrBuilder {
        public static final int NCURRHOTKEYTHEMETYPEREQ_FIELD_NUMBER = 1;
        public static Parser<UpdateHotKeyThemeReq> PARSER = new AbstractParser<UpdateHotKeyThemeReq>() { // from class: com.autolink.interaction.InteractionProto.UpdateHotKeyThemeReq.1
            @Override // com.google.protobuf.Parser
            public UpdateHotKeyThemeReq parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new UpdateHotKeyThemeReq(input, extensionRegistry);
            }
        };
        private static final UpdateHotKeyThemeReq defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private VehicleThemeType nCurrHotKeyThemeTypeReq_;
        private final UnknownFieldSet unknownFields;

        private UpdateHotKeyThemeReq(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private UpdateHotKeyThemeReq(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static UpdateHotKeyThemeReq getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public UpdateHotKeyThemeReq getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private UpdateHotKeyThemeReq(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            VehicleThemeType value = VehicleThemeType.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrHotKeyThemeTypeReq_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyThemeReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyThemeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyThemeReq.class, Builder.class);
        }

        static {
            UpdateHotKeyThemeReq updateHotKeyThemeReq = new UpdateHotKeyThemeReq(true);
            defaultInstance = updateHotKeyThemeReq;
            updateHotKeyThemeReq.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<UpdateHotKeyThemeReq> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyThemeReqOrBuilder
        public boolean hasNCurrHotKeyThemeTypeReq() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyThemeReqOrBuilder
        public VehicleThemeType getNCurrHotKeyThemeTypeReq() {
            return this.nCurrHotKeyThemeTypeReq_;
        }

        private void initFields() {
            this.nCurrHotKeyThemeTypeReq_ = VehicleThemeType.VEHICLEID_INVALID;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrHotKeyThemeTypeReq_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrHotKeyThemeTypeReq_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static UpdateHotKeyThemeReq parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyThemeReq parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyThemeReq parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static UpdateHotKeyThemeReq parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static UpdateHotKeyThemeReq parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyThemeReq parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyThemeReq parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static UpdateHotKeyThemeReq parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static UpdateHotKeyThemeReq parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static UpdateHotKeyThemeReq parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(UpdateHotKeyThemeReq prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements UpdateHotKeyThemeReqOrBuilder {
            private int bitField0_;
            private VehicleThemeType nCurrHotKeyThemeTypeReq_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyThemeReq_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyThemeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(UpdateHotKeyThemeReq.class, Builder.class);
            }

            private Builder() {
                this.nCurrHotKeyThemeTypeReq_ = VehicleThemeType.VEHICLEID_INVALID;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrHotKeyThemeTypeReq_ = VehicleThemeType.VEHICLEID_INVALID;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = UpdateHotKeyThemeReq.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrHotKeyThemeTypeReq_ = VehicleThemeType.VEHICLEID_INVALID;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_UpdateHotKeyThemeReq_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public UpdateHotKeyThemeReq getDefaultInstanceForType() {
                return UpdateHotKeyThemeReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyThemeReq build() {
                UpdateHotKeyThemeReq result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public UpdateHotKeyThemeReq buildPartial() {
                UpdateHotKeyThemeReq result = new UpdateHotKeyThemeReq(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrHotKeyThemeTypeReq_ = this.nCurrHotKeyThemeTypeReq_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof UpdateHotKeyThemeReq) {
                    return mergeFrom((UpdateHotKeyThemeReq) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(UpdateHotKeyThemeReq other) {
                if (other == UpdateHotKeyThemeReq.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrHotKeyThemeTypeReq()) {
                    setNCurrHotKeyThemeTypeReq(other.getNCurrHotKeyThemeTypeReq());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                UpdateHotKeyThemeReq parsedMessage = null;
                try {
                    try {
                        parsedMessage = UpdateHotKeyThemeReq.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyThemeReqOrBuilder
            public boolean hasNCurrHotKeyThemeTypeReq() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.UpdateHotKeyThemeReqOrBuilder
            public VehicleThemeType getNCurrHotKeyThemeTypeReq() {
                return this.nCurrHotKeyThemeTypeReq_;
            }

            public Builder setNCurrHotKeyThemeTypeReq(VehicleThemeType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrHotKeyThemeTypeReq_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrHotKeyThemeTypeReq() {
                this.bitField0_ &= -2;
                this.nCurrHotKeyThemeTypeReq_ = VehicleThemeType.VEHICLEID_INVALID;
                onChanged();
                return this;
            }
        }
    }

    public static final class InsUiChangeReq extends GeneratedMessage implements InsUiChangeReqOrBuilder {
        public static final int NCURRINSUIREQ_FIELD_NUMBER = 1;
        public static Parser<InsUiChangeReq> PARSER = new AbstractParser<InsUiChangeReq>() { // from class: com.autolink.interaction.InteractionProto.InsUiChangeReq.1
            @Override // com.google.protobuf.Parser
            public InsUiChangeReq parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new InsUiChangeReq(input, extensionRegistry);
            }
        };
        private static final InsUiChangeReq defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private InsUiState nCurrInsUiReq_;
        private final UnknownFieldSet unknownFields;

        private InsUiChangeReq(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private InsUiChangeReq(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static InsUiChangeReq getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public InsUiChangeReq getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private InsUiChangeReq(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            InsUiState value = InsUiState.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrInsUiReq_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_InsUiChangeReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_InsUiChangeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(InsUiChangeReq.class, Builder.class);
        }

        static {
            InsUiChangeReq insUiChangeReq = new InsUiChangeReq(true);
            defaultInstance = insUiChangeReq;
            insUiChangeReq.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<InsUiChangeReq> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.InsUiChangeReqOrBuilder
        public boolean hasNCurrInsUiReq() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.InsUiChangeReqOrBuilder
        public InsUiState getNCurrInsUiReq() {
            return this.nCurrInsUiReq_;
        }

        private void initFields() {
            this.nCurrInsUiReq_ = InsUiState.FULL_MAP_MODE_PENETRATE;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrInsUiReq_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrInsUiReq_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static InsUiChangeReq parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static InsUiChangeReq parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static InsUiChangeReq parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static InsUiChangeReq parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static InsUiChangeReq parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static InsUiChangeReq parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static InsUiChangeReq parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static InsUiChangeReq parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static InsUiChangeReq parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static InsUiChangeReq parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(InsUiChangeReq prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements InsUiChangeReqOrBuilder {
            private int bitField0_;
            private InsUiState nCurrInsUiReq_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_InsUiChangeReq_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_InsUiChangeReq_fieldAccessorTable.ensureFieldAccessorsInitialized(InsUiChangeReq.class, Builder.class);
            }

            private Builder() {
                this.nCurrInsUiReq_ = InsUiState.FULL_MAP_MODE_PENETRATE;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrInsUiReq_ = InsUiState.FULL_MAP_MODE_PENETRATE;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = InsUiChangeReq.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrInsUiReq_ = InsUiState.FULL_MAP_MODE_PENETRATE;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_InsUiChangeReq_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public InsUiChangeReq getDefaultInstanceForType() {
                return InsUiChangeReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InsUiChangeReq build() {
                InsUiChangeReq result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InsUiChangeReq buildPartial() {
                InsUiChangeReq result = new InsUiChangeReq(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrInsUiReq_ = this.nCurrInsUiReq_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof InsUiChangeReq) {
                    return mergeFrom((InsUiChangeReq) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(InsUiChangeReq other) {
                if (other == InsUiChangeReq.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrInsUiReq()) {
                    setNCurrInsUiReq(other.getNCurrInsUiReq());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                InsUiChangeReq parsedMessage = null;
                try {
                    try {
                        parsedMessage = InsUiChangeReq.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.InsUiChangeReqOrBuilder
            public boolean hasNCurrInsUiReq() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.InsUiChangeReqOrBuilder
            public InsUiState getNCurrInsUiReq() {
                return this.nCurrInsUiReq_;
            }

            public Builder setNCurrInsUiReq(InsUiState value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrInsUiReq_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrInsUiReq() {
                this.bitField0_ &= -2;
                this.nCurrInsUiReq_ = InsUiState.FULL_MAP_MODE_PENETRATE;
                onChanged();
                return this;
            }
        }
    }

    public static final class MapFocus extends GeneratedMessage implements MapFocusOrBuilder {
        public static final int NCURRMAPTYPE_FIELD_NUMBER = 1;
        public static Parser<MapFocus> PARSER = new AbstractParser<MapFocus>() { // from class: com.autolink.interaction.InteractionProto.MapFocus.1
            @Override // com.google.protobuf.Parser
            public MapFocus parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new MapFocus(input, extensionRegistry);
            }
        };
        private static final MapFocus defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private MapType nCurrMapType_;
        private final UnknownFieldSet unknownFields;

        private MapFocus(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private MapFocus(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MapFocus getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MapFocus getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MapFocus(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            MapType value = MapType.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.nCurrMapType_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_MapFocus_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_MapFocus_fieldAccessorTable.ensureFieldAccessorsInitialized(MapFocus.class, Builder.class);
        }

        static {
            MapFocus mapFocus = new MapFocus(true);
            defaultInstance = mapFocus;
            mapFocus.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MapFocus> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.MapFocusOrBuilder
        public boolean hasNCurrMapType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.MapFocusOrBuilder
        public MapType getNCurrMapType() {
            return this.nCurrMapType_;
        }

        private void initFields() {
            this.nCurrMapType_ = MapType.MAP_TYPE_INVALID;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.nCurrMapType_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.nCurrMapType_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MapFocus parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MapFocus parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MapFocus parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MapFocus parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MapFocus parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MapFocus parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static MapFocus parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static MapFocus parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static MapFocus parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MapFocus parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(MapFocus prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements MapFocusOrBuilder {
            private int bitField0_;
            private MapType nCurrMapType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_MapFocus_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_MapFocus_fieldAccessorTable.ensureFieldAccessorsInitialized(MapFocus.class, Builder.class);
            }

            private Builder() {
                this.nCurrMapType_ = MapType.MAP_TYPE_INVALID;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.nCurrMapType_ = MapType.MAP_TYPE_INVALID;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MapFocus.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.nCurrMapType_ = MapType.MAP_TYPE_INVALID;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_MapFocus_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MapFocus getDefaultInstanceForType() {
                return MapFocus.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapFocus build() {
                MapFocus result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapFocus buildPartial() {
                MapFocus result = new MapFocus(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.nCurrMapType_ = this.nCurrMapType_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof MapFocus) {
                    return mergeFrom((MapFocus) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MapFocus other) {
                if (other == MapFocus.getDefaultInstance()) {
                    return this;
                }
                if (other.hasNCurrMapType()) {
                    setNCurrMapType(other.getNCurrMapType());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                MapFocus parsedMessage = null;
                try {
                    try {
                        parsedMessage = MapFocus.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.MapFocusOrBuilder
            public boolean hasNCurrMapType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.MapFocusOrBuilder
            public MapType getNCurrMapType() {
                return this.nCurrMapType_;
            }

            public Builder setNCurrMapType(MapType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.nCurrMapType_ = value;
                onChanged();
                return this;
            }

            public Builder clearNCurrMapType() {
                this.bitField0_ &= -2;
                this.nCurrMapType_ = MapType.MAP_TYPE_INVALID;
                onChanged();
                return this;
            }
        }
    }

    public static final class MapState extends GeneratedMessage implements MapStateOrBuilder {
        public static final int MCURRMAPTYPE_FIELD_NUMBER = 1;
        public static final int NAVIGATION_POP_INFO_FIELD_NUMBER = 2;
        public static Parser<MapState> PARSER = new AbstractParser<MapState>() { // from class: com.autolink.interaction.InteractionProto.MapState.1
            @Override // com.google.protobuf.Parser
            public MapState parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new MapState(input, extensionRegistry);
            }
        };
        private static final MapState defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private MapType mCurrMapType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private NavigationPopInfo navigationPopInfo_;
        private final UnknownFieldSet unknownFields;

        private MapState(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private MapState(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static MapState getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public MapState getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private MapState(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        try {
                            int tag = input.readTag();
                            if (tag == 0) {
                                done = true;
                            } else if (tag == 8) {
                                int rawValue = input.readEnum();
                                MapType value = MapType.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.mCurrMapType_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                            } else if (tag != 16) {
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                            } else {
                                int rawValue2 = input.readEnum();
                                NavigationPopInfo value2 = NavigationPopInfo.valueOf(rawValue2);
                                if (value2 != null) {
                                    this.bitField0_ = 2 | this.bitField0_;
                                    this.navigationPopInfo_ = value2;
                                } else {
                                    unknownFields.mergeVarintField(2, rawValue2);
                                }
                            }
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e.getMessage()).setUnfinishedMessage(this);
                        }
                    } catch (InvalidProtocolBufferException e2) {
                        throw e2.setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_MapState_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_MapState_fieldAccessorTable.ensureFieldAccessorsInitialized(MapState.class, Builder.class);
        }

        static {
            MapState mapState = new MapState(true);
            defaultInstance = mapState;
            mapState.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<MapState> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
        public boolean hasMCurrMapType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
        public MapType getMCurrMapType() {
            return this.mCurrMapType_;
        }

        @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
        public boolean hasNavigationPopInfo() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
        public NavigationPopInfo getNavigationPopInfo() {
            return this.navigationPopInfo_;
        }

        private void initFields() {
            this.mCurrMapType_ = MapType.MAP_TYPE_INVALID;
            this.navigationPopInfo_ = NavigationPopInfo.CLOSE_NAVI;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.mCurrMapType_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.navigationPopInfo_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.mCurrMapType_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.navigationPopInfo_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static MapState parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MapState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MapState parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static MapState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static MapState parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MapState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static MapState parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static MapState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static MapState parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static MapState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(MapState prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements MapStateOrBuilder {
            private int bitField0_;
            private MapType mCurrMapType_;
            private NavigationPopInfo navigationPopInfo_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_MapState_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_MapState_fieldAccessorTable.ensureFieldAccessorsInitialized(MapState.class, Builder.class);
            }

            private Builder() {
                this.mCurrMapType_ = MapType.MAP_TYPE_INVALID;
                this.navigationPopInfo_ = NavigationPopInfo.CLOSE_NAVI;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.mCurrMapType_ = MapType.MAP_TYPE_INVALID;
                this.navigationPopInfo_ = NavigationPopInfo.CLOSE_NAVI;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = MapState.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mCurrMapType_ = MapType.MAP_TYPE_INVALID;
                this.bitField0_ &= -2;
                this.navigationPopInfo_ = NavigationPopInfo.CLOSE_NAVI;
                this.bitField0_ &= -3;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_MapState_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public MapState getDefaultInstanceForType() {
                return MapState.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapState build() {
                MapState result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public MapState buildPartial() {
                MapState result = new MapState(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mCurrMapType_ = this.mCurrMapType_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.navigationPopInfo_ = this.navigationPopInfo_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof MapState) {
                    return mergeFrom((MapState) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(MapState other) {
                if (other == MapState.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMCurrMapType()) {
                    setMCurrMapType(other.getMCurrMapType());
                }
                if (other.hasNavigationPopInfo()) {
                    setNavigationPopInfo(other.getNavigationPopInfo());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                MapState parsedMessage = null;
                try {
                    try {
                        parsedMessage = MapState.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
            public boolean hasMCurrMapType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
            public MapType getMCurrMapType() {
                return this.mCurrMapType_;
            }

            public Builder setMCurrMapType(MapType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.mCurrMapType_ = value;
                onChanged();
                return this;
            }

            public Builder clearMCurrMapType() {
                this.bitField0_ &= -2;
                this.mCurrMapType_ = MapType.MAP_TYPE_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
            public boolean hasNavigationPopInfo() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.MapStateOrBuilder
            public NavigationPopInfo getNavigationPopInfo() {
                return this.navigationPopInfo_;
            }

            public Builder setNavigationPopInfo(NavigationPopInfo value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.navigationPopInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearNavigationPopInfo() {
                this.bitField0_ &= -3;
                this.navigationPopInfo_ = NavigationPopInfo.CLOSE_NAVI;
                onChanged();
                return this;
            }
        }
    }

    public static final class InstrumentTest extends GeneratedMessage implements InstrumentTestOrBuilder {
        public static final int ADAS_AUTO_LEARN_VISIBLE_FIELD_NUMBER = 2;
        public static final int ADAS_DEBUG_MODE_FIELD_NUMBER = 3;
        public static final int OSD_VISIBLE_FIELD_NUMBER = 1;
        public static Parser<InstrumentTest> PARSER = new AbstractParser<InstrumentTest>() { // from class: com.autolink.interaction.InteractionProto.InstrumentTest.1
            @Override // com.google.protobuf.Parser
            public InstrumentTest parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new InstrumentTest(input, extensionRegistry);
            }
        };
        private static final InstrumentTest defaultInstance;
        private static final long serialVersionUID = 0;
        private AdasAutoLearnVisible adasAutoLearnVisible_;
        private AdasDebugMode adasDebugMode_;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private OSDVisible osdVisible_;
        private final UnknownFieldSet unknownFields;

        private InstrumentTest(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private InstrumentTest(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static InstrumentTest getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public InstrumentTest getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private InstrumentTest(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag == 8) {
                            int rawValue = input.readEnum();
                            OSDVisible value = OSDVisible.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.osdVisible_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        } else if (tag == 16) {
                            int rawValue2 = input.readEnum();
                            AdasAutoLearnVisible value2 = AdasAutoLearnVisible.valueOf(rawValue2);
                            if (value2 != null) {
                                this.bitField0_ = 2 | this.bitField0_;
                                this.adasAutoLearnVisible_ = value2;
                            } else {
                                unknownFields.mergeVarintField(2, rawValue2);
                            }
                        } else if (tag != 24) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue3 = input.readEnum();
                            AdasDebugMode value3 = AdasDebugMode.valueOf(rawValue3);
                            if (value3 == null) {
                                unknownFields.mergeVarintField(3, rawValue3);
                            } else {
                                this.bitField0_ |= 4;
                                this.adasDebugMode_ = value3;
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_InstrumentTest_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_InstrumentTest_fieldAccessorTable.ensureFieldAccessorsInitialized(InstrumentTest.class, Builder.class);
        }

        static {
            InstrumentTest instrumentTest = new InstrumentTest(true);
            defaultInstance = instrumentTest;
            instrumentTest.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<InstrumentTest> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
        public boolean hasOsdVisible() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
        public OSDVisible getOsdVisible() {
            return this.osdVisible_;
        }

        @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
        public boolean hasAdasAutoLearnVisible() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
        public AdasAutoLearnVisible getAdasAutoLearnVisible() {
            return this.adasAutoLearnVisible_;
        }

        @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
        public boolean hasAdasDebugMode() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
        public AdasDebugMode getAdasDebugMode() {
            return this.adasDebugMode_;
        }

        private void initFields() {
            this.osdVisible_ = OSDVisible.OSD_INVISIBLE;
            this.adasAutoLearnVisible_ = AdasAutoLearnVisible.ADAS_AUTO_LEARN_INVISIBLE;
            this.adasDebugMode_ = AdasDebugMode.ADAS_DEBUG_MODE_CLOSE;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.osdVisible_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeEnum(2, this.adasAutoLearnVisible_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeEnum(3, this.adasDebugMode_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.osdVisible_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeEnumSize(2, this.adasAutoLearnVisible_.getNumber());
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeEnumSize(3, this.adasDebugMode_.getNumber());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static InstrumentTest parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static InstrumentTest parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static InstrumentTest parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static InstrumentTest parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static InstrumentTest parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static InstrumentTest parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static InstrumentTest parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static InstrumentTest parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static InstrumentTest parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static InstrumentTest parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(InstrumentTest prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements InstrumentTestOrBuilder {
            private AdasAutoLearnVisible adasAutoLearnVisible_;
            private AdasDebugMode adasDebugMode_;
            private int bitField0_;
            private OSDVisible osdVisible_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_InstrumentTest_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_InstrumentTest_fieldAccessorTable.ensureFieldAccessorsInitialized(InstrumentTest.class, Builder.class);
            }

            private Builder() {
                this.osdVisible_ = OSDVisible.OSD_INVISIBLE;
                this.adasAutoLearnVisible_ = AdasAutoLearnVisible.ADAS_AUTO_LEARN_INVISIBLE;
                this.adasDebugMode_ = AdasDebugMode.ADAS_DEBUG_MODE_CLOSE;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.osdVisible_ = OSDVisible.OSD_INVISIBLE;
                this.adasAutoLearnVisible_ = AdasAutoLearnVisible.ADAS_AUTO_LEARN_INVISIBLE;
                this.adasDebugMode_ = AdasDebugMode.ADAS_DEBUG_MODE_CLOSE;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = InstrumentTest.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.osdVisible_ = OSDVisible.OSD_INVISIBLE;
                this.bitField0_ &= -2;
                this.adasAutoLearnVisible_ = AdasAutoLearnVisible.ADAS_AUTO_LEARN_INVISIBLE;
                this.bitField0_ &= -3;
                this.adasDebugMode_ = AdasDebugMode.ADAS_DEBUG_MODE_CLOSE;
                this.bitField0_ &= -5;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_InstrumentTest_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public InstrumentTest getDefaultInstanceForType() {
                return InstrumentTest.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InstrumentTest build() {
                InstrumentTest result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public InstrumentTest buildPartial() {
                InstrumentTest result = new InstrumentTest(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.osdVisible_ = this.osdVisible_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.adasAutoLearnVisible_ = this.adasAutoLearnVisible_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.adasDebugMode_ = this.adasDebugMode_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof InstrumentTest) {
                    return mergeFrom((InstrumentTest) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(InstrumentTest other) {
                if (other == InstrumentTest.getDefaultInstance()) {
                    return this;
                }
                if (other.hasOsdVisible()) {
                    setOsdVisible(other.getOsdVisible());
                }
                if (other.hasAdasAutoLearnVisible()) {
                    setAdasAutoLearnVisible(other.getAdasAutoLearnVisible());
                }
                if (other.hasAdasDebugMode()) {
                    setAdasDebugMode(other.getAdasDebugMode());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                InstrumentTest parsedMessage = null;
                try {
                    try {
                        parsedMessage = InstrumentTest.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
            public boolean hasOsdVisible() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
            public OSDVisible getOsdVisible() {
                return this.osdVisible_;
            }

            public Builder setOsdVisible(OSDVisible value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.osdVisible_ = value;
                onChanged();
                return this;
            }

            public Builder clearOsdVisible() {
                this.bitField0_ &= -2;
                this.osdVisible_ = OSDVisible.OSD_INVISIBLE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
            public boolean hasAdasAutoLearnVisible() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
            public AdasAutoLearnVisible getAdasAutoLearnVisible() {
                return this.adasAutoLearnVisible_;
            }

            public Builder setAdasAutoLearnVisible(AdasAutoLearnVisible value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2;
                this.adasAutoLearnVisible_ = value;
                onChanged();
                return this;
            }

            public Builder clearAdasAutoLearnVisible() {
                this.bitField0_ &= -3;
                this.adasAutoLearnVisible_ = AdasAutoLearnVisible.ADAS_AUTO_LEARN_INVISIBLE;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
            public boolean hasAdasDebugMode() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.InstrumentTestOrBuilder
            public AdasDebugMode getAdasDebugMode() {
                return this.adasDebugMode_;
            }

            public Builder setAdasDebugMode(AdasDebugMode value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.adasDebugMode_ = value;
                onChanged();
                return this;
            }

            public Builder clearAdasDebugMode() {
                this.bitField0_ &= -5;
                this.adasDebugMode_ = AdasDebugMode.ADAS_DEBUG_MODE_CLOSE;
                onChanged();
                return this;
            }
        }
    }

    public static final class ChimeSettingReq extends GeneratedMessage implements ChimeSettingReqOrBuilder {
        public static final int MCURRSOUNDTYPE_FIELD_NUMBER = 1;
        public static Parser<ChimeSettingReq> PARSER = new AbstractParser<ChimeSettingReq>() { // from class: com.autolink.interaction.InteractionProto.ChimeSettingReq.1
            @Override // com.google.protobuf.Parser
            public ChimeSettingReq parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ChimeSettingReq(input, extensionRegistry);
            }
        };
        private static final ChimeSettingReq defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private SoundType mCurrSoundType_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private ChimeSettingReq(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ChimeSettingReq(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ChimeSettingReq getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ChimeSettingReq getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ChimeSettingReq(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            SoundType value = SoundType.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.mCurrSoundType_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_ChimeSettingReq_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_ChimeSettingReq_fieldAccessorTable.ensureFieldAccessorsInitialized(ChimeSettingReq.class, Builder.class);
        }

        static {
            ChimeSettingReq chimeSettingReq = new ChimeSettingReq(true);
            defaultInstance = chimeSettingReq;
            chimeSettingReq.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ChimeSettingReq> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.ChimeSettingReqOrBuilder
        public boolean hasMCurrSoundType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.ChimeSettingReqOrBuilder
        public SoundType getMCurrSoundType() {
            return this.mCurrSoundType_;
        }

        private void initFields() {
            this.mCurrSoundType_ = SoundType.STANDARD_SOUND;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.mCurrSoundType_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.mCurrSoundType_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ChimeSettingReq parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ChimeSettingReq parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ChimeSettingReq parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ChimeSettingReq parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ChimeSettingReq parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ChimeSettingReq parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ChimeSettingReq parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ChimeSettingReq parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ChimeSettingReq parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ChimeSettingReq parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ChimeSettingReq prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ChimeSettingReqOrBuilder {
            private int bitField0_;
            private SoundType mCurrSoundType_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_ChimeSettingReq_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_ChimeSettingReq_fieldAccessorTable.ensureFieldAccessorsInitialized(ChimeSettingReq.class, Builder.class);
            }

            private Builder() {
                this.mCurrSoundType_ = SoundType.STANDARD_SOUND;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.mCurrSoundType_ = SoundType.STANDARD_SOUND;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ChimeSettingReq.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mCurrSoundType_ = SoundType.STANDARD_SOUND;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_ChimeSettingReq_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ChimeSettingReq getDefaultInstanceForType() {
                return ChimeSettingReq.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChimeSettingReq build() {
                ChimeSettingReq result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ChimeSettingReq buildPartial() {
                ChimeSettingReq result = new ChimeSettingReq(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mCurrSoundType_ = this.mCurrSoundType_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ChimeSettingReq) {
                    return mergeFrom((ChimeSettingReq) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ChimeSettingReq other) {
                if (other == ChimeSettingReq.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMCurrSoundType()) {
                    setMCurrSoundType(other.getMCurrSoundType());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ChimeSettingReq parsedMessage = null;
                try {
                    try {
                        parsedMessage = ChimeSettingReq.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.ChimeSettingReqOrBuilder
            public boolean hasMCurrSoundType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.ChimeSettingReqOrBuilder
            public SoundType getMCurrSoundType() {
                return this.mCurrSoundType_;
            }

            public Builder setMCurrSoundType(SoundType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.mCurrSoundType_ = value;
                onChanged();
                return this;
            }

            public Builder clearMCurrSoundType() {
                this.bitField0_ &= -2;
                this.mCurrSoundType_ = SoundType.STANDARD_SOUND;
                onChanged();
                return this;
            }
        }
    }

    public static final class ScreenOrientationSetting extends GeneratedMessage implements ScreenOrientationSettingOrBuilder {
        public static final int MCURRSCREENORIENTATION_FIELD_NUMBER = 1;
        public static Parser<ScreenOrientationSetting> PARSER = new AbstractParser<ScreenOrientationSetting>() { // from class: com.autolink.interaction.InteractionProto.ScreenOrientationSetting.1
            @Override // com.google.protobuf.Parser
            public ScreenOrientationSetting parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new ScreenOrientationSetting(input, extensionRegistry);
            }
        };
        private static final ScreenOrientationSetting defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private ScreenOrientationValue mCurrScreenOrientation_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private ScreenOrientationSetting(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private ScreenOrientationSetting(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static ScreenOrientationSetting getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public ScreenOrientationSetting getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private ScreenOrientationSetting(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            int rawValue = input.readEnum();
                            ScreenOrientationValue value = ScreenOrientationValue.valueOf(rawValue);
                            if (value != null) {
                                this.bitField0_ = 1 | this.bitField0_;
                                this.mCurrScreenOrientation_ = value;
                            } else {
                                unknownFields.mergeVarintField(1, rawValue);
                            }
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_ScreenOrientationSetting_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_ScreenOrientationSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(ScreenOrientationSetting.class, Builder.class);
        }

        static {
            ScreenOrientationSetting screenOrientationSetting = new ScreenOrientationSetting(true);
            defaultInstance = screenOrientationSetting;
            screenOrientationSetting.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<ScreenOrientationSetting> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.ScreenOrientationSettingOrBuilder
        public boolean hasMCurrScreenOrientation() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.ScreenOrientationSettingOrBuilder
        public ScreenOrientationValue getMCurrScreenOrientation() {
            return this.mCurrScreenOrientation_;
        }

        private void initFields() {
            this.mCurrScreenOrientation_ = ScreenOrientationValue.LANDSCAPE_SCREEN;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.mCurrScreenOrientation_.getNumber());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.mCurrScreenOrientation_.getNumber()) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static ScreenOrientationSetting parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ScreenOrientationSetting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ScreenOrientationSetting parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static ScreenOrientationSetting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static ScreenOrientationSetting parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ScreenOrientationSetting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static ScreenOrientationSetting parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static ScreenOrientationSetting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static ScreenOrientationSetting parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static ScreenOrientationSetting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(ScreenOrientationSetting prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements ScreenOrientationSettingOrBuilder {
            private int bitField0_;
            private ScreenOrientationValue mCurrScreenOrientation_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_ScreenOrientationSetting_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_ScreenOrientationSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(ScreenOrientationSetting.class, Builder.class);
            }

            private Builder() {
                this.mCurrScreenOrientation_ = ScreenOrientationValue.LANDSCAPE_SCREEN;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.mCurrScreenOrientation_ = ScreenOrientationValue.LANDSCAPE_SCREEN;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = ScreenOrientationSetting.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mCurrScreenOrientation_ = ScreenOrientationValue.LANDSCAPE_SCREEN;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_ScreenOrientationSetting_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public ScreenOrientationSetting getDefaultInstanceForType() {
                return ScreenOrientationSetting.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ScreenOrientationSetting build() {
                ScreenOrientationSetting result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public ScreenOrientationSetting buildPartial() {
                ScreenOrientationSetting result = new ScreenOrientationSetting(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mCurrScreenOrientation_ = this.mCurrScreenOrientation_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof ScreenOrientationSetting) {
                    return mergeFrom((ScreenOrientationSetting) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(ScreenOrientationSetting other) {
                if (other == ScreenOrientationSetting.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMCurrScreenOrientation()) {
                    setMCurrScreenOrientation(other.getMCurrScreenOrientation());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                ScreenOrientationSetting parsedMessage = null;
                try {
                    try {
                        parsedMessage = ScreenOrientationSetting.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.ScreenOrientationSettingOrBuilder
            public boolean hasMCurrScreenOrientation() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.ScreenOrientationSettingOrBuilder
            public ScreenOrientationValue getMCurrScreenOrientation() {
                return this.mCurrScreenOrientation_;
            }

            public Builder setMCurrScreenOrientation(ScreenOrientationValue value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.mCurrScreenOrientation_ = value;
                onChanged();
                return this;
            }

            public Builder clearMCurrScreenOrientation() {
                this.bitField0_ &= -2;
                this.mCurrScreenOrientation_ = ScreenOrientationValue.LANDSCAPE_SCREEN;
                onChanged();
                return this;
            }
        }
    }

    public static final class VersatileThemeSetting extends GeneratedMessage implements VersatileThemeSettingOrBuilder {
        public static final int MCURRVERSATILETHEME_FIELD_NUMBER = 1;
        public static Parser<VersatileThemeSetting> PARSER = new AbstractParser<VersatileThemeSetting>() { // from class: com.autolink.interaction.InteractionProto.VersatileThemeSetting.1
            @Override // com.google.protobuf.Parser
            public VersatileThemeSetting parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new VersatileThemeSetting(input, extensionRegistry);
            }
        };
        private static final VersatileThemeSetting defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int mCurrVersatileTheme_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private VersatileThemeSetting(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private VersatileThemeSetting(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static VersatileThemeSetting getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public VersatileThemeSetting getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private VersatileThemeSetting(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.mCurrVersatileTheme_ = input.readInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_VersatileThemeSetting_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_VersatileThemeSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(VersatileThemeSetting.class, Builder.class);
        }

        static {
            VersatileThemeSetting versatileThemeSetting = new VersatileThemeSetting(true);
            defaultInstance = versatileThemeSetting;
            versatileThemeSetting.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<VersatileThemeSetting> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.VersatileThemeSettingOrBuilder
        public boolean hasMCurrVersatileTheme() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.VersatileThemeSettingOrBuilder
        public int getMCurrVersatileTheme() {
            return this.mCurrVersatileTheme_;
        }

        private void initFields() {
            this.mCurrVersatileTheme_ = 100000010;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.mCurrVersatileTheme_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.mCurrVersatileTheme_) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static VersatileThemeSetting parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static VersatileThemeSetting parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static VersatileThemeSetting parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static VersatileThemeSetting parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static VersatileThemeSetting parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static VersatileThemeSetting parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static VersatileThemeSetting parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static VersatileThemeSetting parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static VersatileThemeSetting parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static VersatileThemeSetting parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(VersatileThemeSetting prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements VersatileThemeSettingOrBuilder {
            private int bitField0_;
            private int mCurrVersatileTheme_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_VersatileThemeSetting_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_VersatileThemeSetting_fieldAccessorTable.ensureFieldAccessorsInitialized(VersatileThemeSetting.class, Builder.class);
            }

            private Builder() {
                this.mCurrVersatileTheme_ = 100000010;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.mCurrVersatileTheme_ = 100000010;
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = VersatileThemeSetting.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mCurrVersatileTheme_ = 100000010;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_VersatileThemeSetting_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public VersatileThemeSetting getDefaultInstanceForType() {
                return VersatileThemeSetting.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public VersatileThemeSetting build() {
                VersatileThemeSetting result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public VersatileThemeSetting buildPartial() {
                VersatileThemeSetting result = new VersatileThemeSetting(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mCurrVersatileTheme_ = this.mCurrVersatileTheme_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof VersatileThemeSetting) {
                    return mergeFrom((VersatileThemeSetting) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(VersatileThemeSetting other) {
                if (other == VersatileThemeSetting.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMCurrVersatileTheme()) {
                    setMCurrVersatileTheme(other.getMCurrVersatileTheme());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                VersatileThemeSetting parsedMessage = null;
                try {
                    try {
                        parsedMessage = VersatileThemeSetting.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.VersatileThemeSettingOrBuilder
            public boolean hasMCurrVersatileTheme() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.VersatileThemeSettingOrBuilder
            public int getMCurrVersatileTheme() {
                return this.mCurrVersatileTheme_;
            }

            public Builder setMCurrVersatileTheme(int value) {
                this.bitField0_ |= 1;
                this.mCurrVersatileTheme_ = value;
                onChanged();
                return this;
            }

            public Builder clearMCurrVersatileTheme() {
                this.bitField0_ &= -2;
                this.mCurrVersatileTheme_ = 100000010;
                onChanged();
                return this;
            }
        }
    }

    public static final class OverseasMapSupplier extends GeneratedMessage implements OverseasMapSupplierOrBuilder {
        public static final int MOVERSEASMAPSUPPLIER_FIELD_NUMBER = 1;
        public static Parser<OverseasMapSupplier> PARSER = new AbstractParser<OverseasMapSupplier>() { // from class: com.autolink.interaction.InteractionProto.OverseasMapSupplier.1
            @Override // com.google.protobuf.Parser
            public OverseasMapSupplier parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new OverseasMapSupplier(input, extensionRegistry);
            }
        };
        private static final OverseasMapSupplier defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int mOverseasMapSupplier_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private OverseasMapSupplier(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private OverseasMapSupplier(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static OverseasMapSupplier getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public OverseasMapSupplier getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private OverseasMapSupplier(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.mOverseasMapSupplier_ = input.readInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_OverseasMapSupplier_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_OverseasMapSupplier_fieldAccessorTable.ensureFieldAccessorsInitialized(OverseasMapSupplier.class, Builder.class);
        }

        static {
            OverseasMapSupplier overseasMapSupplier = new OverseasMapSupplier(true);
            defaultInstance = overseasMapSupplier;
            overseasMapSupplier.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<OverseasMapSupplier> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.OverseasMapSupplierOrBuilder
        public boolean hasMOverseasMapSupplier() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.OverseasMapSupplierOrBuilder
        public int getMOverseasMapSupplier() {
            return this.mOverseasMapSupplier_;
        }

        private void initFields() {
            this.mOverseasMapSupplier_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.mOverseasMapSupplier_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.mOverseasMapSupplier_) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static OverseasMapSupplier parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static OverseasMapSupplier parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static OverseasMapSupplier parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static OverseasMapSupplier parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static OverseasMapSupplier parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static OverseasMapSupplier parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static OverseasMapSupplier parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static OverseasMapSupplier parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static OverseasMapSupplier parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static OverseasMapSupplier parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(OverseasMapSupplier prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements OverseasMapSupplierOrBuilder {
            private int bitField0_;
            private int mOverseasMapSupplier_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_OverseasMapSupplier_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_OverseasMapSupplier_fieldAccessorTable.ensureFieldAccessorsInitialized(OverseasMapSupplier.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = OverseasMapSupplier.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mOverseasMapSupplier_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_OverseasMapSupplier_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public OverseasMapSupplier getDefaultInstanceForType() {
                return OverseasMapSupplier.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public OverseasMapSupplier build() {
                OverseasMapSupplier result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public OverseasMapSupplier buildPartial() {
                OverseasMapSupplier result = new OverseasMapSupplier(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mOverseasMapSupplier_ = this.mOverseasMapSupplier_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof OverseasMapSupplier) {
                    return mergeFrom((OverseasMapSupplier) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(OverseasMapSupplier other) {
                if (other == OverseasMapSupplier.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMOverseasMapSupplier()) {
                    setMOverseasMapSupplier(other.getMOverseasMapSupplier());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                OverseasMapSupplier parsedMessage = null;
                try {
                    try {
                        parsedMessage = OverseasMapSupplier.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.OverseasMapSupplierOrBuilder
            public boolean hasMOverseasMapSupplier() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.OverseasMapSupplierOrBuilder
            public int getMOverseasMapSupplier() {
                return this.mOverseasMapSupplier_;
            }

            public Builder setMOverseasMapSupplier(int value) {
                this.bitField0_ |= 1;
                this.mOverseasMapSupplier_ = value;
                onChanged();
                return this;
            }

            public Builder clearMOverseasMapSupplier() {
                this.bitField0_ &= -2;
                this.mOverseasMapSupplier_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class NaviTbtInfo extends GeneratedMessage implements NaviTbtInfoOrBuilder {
        public static Parser<NaviTbtInfo> PARSER = new AbstractParser<NaviTbtInfo>() { // from class: com.autolink.interaction.InteractionProto.NaviTbtInfo.1
            @Override // com.google.protobuf.Parser
            public NaviTbtInfo parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new NaviTbtInfo(input, extensionRegistry);
            }
        };
        public static final int TBT_ARRIVE_TIME_DAY_FIELD_NUMBER = 12;
        public static final int TBT_ARRIVE_TIME_FIELD_NUMBER = 13;
        public static final int TBT_DESTINATION_INFO_FIELD_NUMBER = 3;
        public static final int TBT_MAP_TYPE_FIELD_NUMBER = 1;
        public static final int TBT_MULTIPLE_TEXT_FIELD_NUMBER = 14;
        public static final int TBT_MULTIPLE_TEXT_ONE_FIELD_NUMBER = 15;
        public static final int TBT_MULTIPLE_TEXT_TWO_FIELD_NUMBER = 16;
        public static final int TBT_NEXT_NAVIGATION_INFORMATION_ICON_FIELD_NUMBER = 2;
        public static final int TBT_NEXT_NEXT_ROAD_DIST_FIELD_NUMBER = 7;
        public static final int TBT_NEXT_NEXT_ROAD_DIST_UNIT_FIELD_NUMBER = 8;
        public static final int TBT_REMAINING_MILAGE_UNIT_FIELD_NUMBER = 10;
        public static final int TBT_REMAINING_MILAGE_VALUE_FIELD_NUMBER = 9;
        public static final int TBT_REMAINING_SECOND_VALUE_FIELD_NUMBER = 11;
        public static final int TBT_TURN_NAVIGATION_INFORMATION_ICON_FIELD_NUMBER = 6;
        public static final int TBT_TURN_TIP_DIGIT_FIELD_NUMBER = 4;
        public static final int TBT_TURN_TIP_UNIT_FIELD_NUMBER = 5;
        private static final NaviTbtInfo defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private Object tbtArriveTimeDay_;
        private Object tbtArriveTime_;
        private Object tbtDestinationInfo_;
        private MapType tbtMapType_;
        private Object tbtMultipleTextOne_;
        private Object tbtMultipleTextTwo_;
        private Object tbtMultipleText_;
        private int tbtNextNavigationInformationIcon_;
        private Object tbtNextNextRoadDistUnit_;
        private Object tbtNextNextRoadDist_;
        private Object tbtRemainingMilageUnit_;
        private Object tbtRemainingMilageValue_;
        private Object tbtRemainingSecondValue_;
        private int tbtTurnNavigationInformationIcon_;
        private Object tbtTurnTipDigit_;
        private Object tbtTurnTipUnit_;
        private final UnknownFieldSet unknownFields;

        private NaviTbtInfo(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private NaviTbtInfo(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static NaviTbtInfo getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NaviTbtInfo getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NaviTbtInfo(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        switch (tag) {
                            case 0:
                                done = true;
                                break;
                            case 8:
                                int rawValue = input.readEnum();
                                MapType value = MapType.valueOf(rawValue);
                                if (value != null) {
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.tbtMapType_ = value;
                                } else {
                                    unknownFields.mergeVarintField(1, rawValue);
                                }
                                break;
                            case 16:
                                this.bitField0_ |= 2;
                                this.tbtNextNavigationInformationIcon_ = input.readInt32();
                                break;
                            case 26:
                                ByteString bs = input.readBytes();
                                this.bitField0_ |= 4;
                                this.tbtDestinationInfo_ = bs;
                                break;
                            case DescriptorProtos.MethodOptions.IDEMPOTENCY_LEVEL_FIELD_NUMBER /* 34 */:
                                ByteString bs2 = input.readBytes();
                                this.bitField0_ |= 8;
                                this.tbtTurnTipDigit_ = bs2;
                                break;
                            case DescriptorProtos.FileOptions.PHP_GENERIC_SERVICES_FIELD_NUMBER /* 42 */:
                                ByteString bs3 = input.readBytes();
                                this.bitField0_ |= 16;
                                this.tbtTurnTipUnit_ = bs3;
                                break;
                            case 48:
                                this.bitField0_ |= 32;
                                this.tbtTurnNavigationInformationIcon_ = input.readInt32();
                                break;
                            case FERRY_VALUE:
                                ByteString bs4 = input.readBytes();
                                this.bitField0_ |= 64;
                                this.tbtNextNextRoadDist_ = bs4;
                                break;
                            case DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD_VALUE:
                                ByteString bs5 = input.readBytes();
                                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                                this.tbtNextNextRoadDistUnit_ = bs5;
                                break;
                            case THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3_VALUE:
                                ByteString bs6 = input.readBytes();
                                this.bitField0_ |= 256;
                                this.tbtRemainingMilageValue_ = bs6;
                                break;
                            case 82:
                                ByteString bs7 = input.readBytes();
                                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                                this.tbtRemainingMilageUnit_ = bs7;
                                break;
                            case 90:
                                ByteString bs8 = input.readBytes();
                                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                                this.tbtRemainingSecondValue_ = bs8;
                                break;
                            case SUSD_SHUTDOWN_PREPARE_COMPLETED_VALUE:
                                ByteString bs9 = input.readBytes();
                                this.bitField0_ |= 2048;
                                this.tbtArriveTimeDay_ = bs9;
                                break;
                            case SUSD_TELL_TALE_INFO_VALUE:
                                ByteString bs10 = input.readBytes();
                                this.bitField0_ |= 4096;
                                this.tbtArriveTime_ = bs10;
                                break;
                            case 114:
                                ByteString bs11 = input.readBytes();
                                this.bitField0_ |= 8192;
                                this.tbtMultipleText_ = bs11;
                                break;
                            case 122:
                                ByteString bs12 = input.readBytes();
                                this.bitField0_ |= 16384;
                                this.tbtMultipleTextOne_ = bs12;
                                break;
                            case SUSD_POWER_IVI_UI_POPUP_VALUE:
                                ByteString bs13 = input.readBytes();
                                this.bitField0_ |= 32768;
                                this.tbtMultipleTextTwo_ = bs13;
                                break;
                            default:
                                if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                    done = true;
                                }
                                break;
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_NaviTbtInfo_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_NaviTbtInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(NaviTbtInfo.class, Builder.class);
        }

        static {
            NaviTbtInfo naviTbtInfo = new NaviTbtInfo(true);
            defaultInstance = naviTbtInfo;
            naviTbtInfo.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NaviTbtInfo> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtMapType() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public MapType getTbtMapType() {
            return this.tbtMapType_;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtNextNavigationInformationIcon() {
            return (this.bitField0_ & 2) == 2;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public int getTbtNextNavigationInformationIcon() {
            return this.tbtNextNavigationInformationIcon_;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtDestinationInfo() {
            return (this.bitField0_ & 4) == 4;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtDestinationInfo() {
            Object ref = this.tbtDestinationInfo_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtDestinationInfo_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtDestinationInfoBytes() {
            Object ref = this.tbtDestinationInfo_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtDestinationInfo_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtTurnTipDigit() {
            return (this.bitField0_ & 8) == 8;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtTurnTipDigit() {
            Object ref = this.tbtTurnTipDigit_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtTurnTipDigit_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtTurnTipDigitBytes() {
            Object ref = this.tbtTurnTipDigit_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtTurnTipDigit_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtTurnTipUnit() {
            return (this.bitField0_ & 16) == 16;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtTurnTipUnit() {
            Object ref = this.tbtTurnTipUnit_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtTurnTipUnit_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtTurnTipUnitBytes() {
            Object ref = this.tbtTurnTipUnit_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtTurnTipUnit_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtTurnNavigationInformationIcon() {
            return (this.bitField0_ & 32) == 32;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public int getTbtTurnNavigationInformationIcon() {
            return this.tbtTurnNavigationInformationIcon_;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtNextNextRoadDist() {
            return (this.bitField0_ & 64) == 64;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtNextNextRoadDist() {
            Object ref = this.tbtNextNextRoadDist_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtNextNextRoadDist_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtNextNextRoadDistBytes() {
            Object ref = this.tbtNextNextRoadDist_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtNextNextRoadDist_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtNextNextRoadDistUnit() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtNextNextRoadDistUnit() {
            Object ref = this.tbtNextNextRoadDistUnit_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtNextNextRoadDistUnit_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtNextNextRoadDistUnitBytes() {
            Object ref = this.tbtNextNextRoadDistUnit_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtNextNextRoadDistUnit_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtRemainingMilageValue() {
            return (this.bitField0_ & 256) == 256;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtRemainingMilageValue() {
            Object ref = this.tbtRemainingMilageValue_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtRemainingMilageValue_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtRemainingMilageValueBytes() {
            Object ref = this.tbtRemainingMilageValue_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtRemainingMilageValue_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtRemainingMilageUnit() {
            return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtRemainingMilageUnit() {
            Object ref = this.tbtRemainingMilageUnit_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtRemainingMilageUnit_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtRemainingMilageUnitBytes() {
            Object ref = this.tbtRemainingMilageUnit_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtRemainingMilageUnit_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtRemainingSecondValue() {
            return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtRemainingSecondValue() {
            Object ref = this.tbtRemainingSecondValue_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtRemainingSecondValue_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtRemainingSecondValueBytes() {
            Object ref = this.tbtRemainingSecondValue_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtRemainingSecondValue_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtArriveTimeDay() {
            return (this.bitField0_ & 2048) == 2048;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtArriveTimeDay() {
            Object ref = this.tbtArriveTimeDay_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtArriveTimeDay_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtArriveTimeDayBytes() {
            Object ref = this.tbtArriveTimeDay_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtArriveTimeDay_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtArriveTime() {
            return (this.bitField0_ & 4096) == 4096;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtArriveTime() {
            Object ref = this.tbtArriveTime_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtArriveTime_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtArriveTimeBytes() {
            Object ref = this.tbtArriveTime_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtArriveTime_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtMultipleText() {
            return (this.bitField0_ & 8192) == 8192;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtMultipleText() {
            Object ref = this.tbtMultipleText_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtMultipleText_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtMultipleTextBytes() {
            Object ref = this.tbtMultipleText_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtMultipleText_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtMultipleTextOne() {
            return (this.bitField0_ & 16384) == 16384;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtMultipleTextOne() {
            Object ref = this.tbtMultipleTextOne_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtMultipleTextOne_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtMultipleTextOneBytes() {
            Object ref = this.tbtMultipleTextOne_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtMultipleTextOne_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public boolean hasTbtMultipleTextTwo() {
            return (this.bitField0_ & 32768) == 32768;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public String getTbtMultipleTextTwo() {
            Object ref = this.tbtMultipleTextTwo_;
            if (ref instanceof String) {
                return (String) ref;
            }
            ByteString bs = (ByteString) ref;
            String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                this.tbtMultipleTextTwo_ = s;
            }
            return s;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
        public ByteString getTbtMultipleTextTwoBytes() {
            Object ref = this.tbtMultipleTextTwo_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.tbtMultipleTextTwo_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        private void initFields() {
            this.tbtMapType_ = MapType.MAP_TYPE_INVALID;
            this.tbtNextNavigationInformationIcon_ = 0;
            this.tbtDestinationInfo_ = "";
            this.tbtTurnTipDigit_ = "";
            this.tbtTurnTipUnit_ = "";
            this.tbtTurnNavigationInformationIcon_ = 0;
            this.tbtNextNextRoadDist_ = "";
            this.tbtNextNextRoadDistUnit_ = "";
            this.tbtRemainingMilageValue_ = "";
            this.tbtRemainingMilageUnit_ = "";
            this.tbtRemainingSecondValue_ = "";
            this.tbtArriveTimeDay_ = "";
            this.tbtArriveTime_ = "";
            this.tbtMultipleText_ = "";
            this.tbtMultipleTextOne_ = "";
            this.tbtMultipleTextTwo_ = "";
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeEnum(1, this.tbtMapType_.getNumber());
            }
            if ((this.bitField0_ & 2) == 2) {
                output.writeInt32(2, this.tbtNextNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 4) == 4) {
                output.writeBytes(3, getTbtDestinationInfoBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                output.writeBytes(4, getTbtTurnTipDigitBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                output.writeBytes(5, getTbtTurnTipUnitBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                output.writeInt32(6, this.tbtTurnNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 64) == 64) {
                output.writeBytes(7, getTbtNextNextRoadDistBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                output.writeBytes(8, getTbtNextNextRoadDistUnitBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                output.writeBytes(9, getTbtRemainingMilageValueBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                output.writeBytes(10, getTbtRemainingMilageUnitBytes());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                output.writeBytes(11, getTbtRemainingSecondValueBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                output.writeBytes(12, getTbtArriveTimeDayBytes());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                output.writeBytes(13, getTbtArriveTimeBytes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                output.writeBytes(14, getTbtMultipleTextBytes());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                output.writeBytes(15, getTbtMultipleTextOneBytes());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                output.writeBytes(16, getTbtMultipleTextTwoBytes());
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = (this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeEnumSize(1, this.tbtMapType_.getNumber()) : 0;
            if ((this.bitField0_ & 2) == 2) {
                size2 += CodedOutputStream.computeInt32Size(2, this.tbtNextNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 4) == 4) {
                size2 += CodedOutputStream.computeBytesSize(3, getTbtDestinationInfoBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                size2 += CodedOutputStream.computeBytesSize(4, getTbtTurnTipDigitBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                size2 += CodedOutputStream.computeBytesSize(5, getTbtTurnTipUnitBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                size2 += CodedOutputStream.computeInt32Size(6, this.tbtTurnNavigationInformationIcon_);
            }
            if ((this.bitField0_ & 64) == 64) {
                size2 += CodedOutputStream.computeBytesSize(7, getTbtNextNextRoadDistBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                size2 += CodedOutputStream.computeBytesSize(8, getTbtNextNextRoadDistUnitBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                size2 += CodedOutputStream.computeBytesSize(9, getTbtRemainingMilageValueBytes());
            }
            if ((this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                size2 += CodedOutputStream.computeBytesSize(10, getTbtRemainingMilageUnitBytes());
            }
            if ((this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                size2 += CodedOutputStream.computeBytesSize(11, getTbtRemainingSecondValueBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                size2 += CodedOutputStream.computeBytesSize(12, getTbtArriveTimeDayBytes());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                size2 += CodedOutputStream.computeBytesSize(13, getTbtArriveTimeBytes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size2 += CodedOutputStream.computeBytesSize(14, getTbtMultipleTextBytes());
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size2 += CodedOutputStream.computeBytesSize(15, getTbtMultipleTextOneBytes());
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size2 += CodedOutputStream.computeBytesSize(16, getTbtMultipleTextTwoBytes());
            }
            int size3 = size2 + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size3;
            return size3;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static NaviTbtInfo parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NaviTbtInfo parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NaviTbtInfo parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NaviTbtInfo parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NaviTbtInfo parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NaviTbtInfo parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static NaviTbtInfo parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static NaviTbtInfo parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static NaviTbtInfo parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NaviTbtInfo parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(NaviTbtInfo prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements NaviTbtInfoOrBuilder {
            private int bitField0_;
            private Object tbtArriveTimeDay_;
            private Object tbtArriveTime_;
            private Object tbtDestinationInfo_;
            private MapType tbtMapType_;
            private Object tbtMultipleTextOne_;
            private Object tbtMultipleTextTwo_;
            private Object tbtMultipleText_;
            private int tbtNextNavigationInformationIcon_;
            private Object tbtNextNextRoadDistUnit_;
            private Object tbtNextNextRoadDist_;
            private Object tbtRemainingMilageUnit_;
            private Object tbtRemainingMilageValue_;
            private Object tbtRemainingSecondValue_;
            private int tbtTurnNavigationInformationIcon_;
            private Object tbtTurnTipDigit_;
            private Object tbtTurnTipUnit_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_NaviTbtInfo_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_NaviTbtInfo_fieldAccessorTable.ensureFieldAccessorsInitialized(NaviTbtInfo.class, Builder.class);
            }

            private Builder() {
                this.tbtMapType_ = MapType.MAP_TYPE_INVALID;
                this.tbtDestinationInfo_ = "";
                this.tbtTurnTipDigit_ = "";
                this.tbtTurnTipUnit_ = "";
                this.tbtNextNextRoadDist_ = "";
                this.tbtNextNextRoadDistUnit_ = "";
                this.tbtRemainingMilageValue_ = "";
                this.tbtRemainingMilageUnit_ = "";
                this.tbtRemainingSecondValue_ = "";
                this.tbtArriveTimeDay_ = "";
                this.tbtArriveTime_ = "";
                this.tbtMultipleText_ = "";
                this.tbtMultipleTextOne_ = "";
                this.tbtMultipleTextTwo_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                this.tbtMapType_ = MapType.MAP_TYPE_INVALID;
                this.tbtDestinationInfo_ = "";
                this.tbtTurnTipDigit_ = "";
                this.tbtTurnTipUnit_ = "";
                this.tbtNextNextRoadDist_ = "";
                this.tbtNextNextRoadDistUnit_ = "";
                this.tbtRemainingMilageValue_ = "";
                this.tbtRemainingMilageUnit_ = "";
                this.tbtRemainingSecondValue_ = "";
                this.tbtArriveTimeDay_ = "";
                this.tbtArriveTime_ = "";
                this.tbtMultipleText_ = "";
                this.tbtMultipleTextOne_ = "";
                this.tbtMultipleTextTwo_ = "";
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = NaviTbtInfo.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.tbtMapType_ = MapType.MAP_TYPE_INVALID;
                int i = this.bitField0_ & (-2);
                this.bitField0_ = i;
                this.tbtNextNavigationInformationIcon_ = 0;
                int i2 = i & (-3);
                this.bitField0_ = i2;
                this.tbtDestinationInfo_ = "";
                int i3 = i2 & (-5);
                this.bitField0_ = i3;
                this.tbtTurnTipDigit_ = "";
                int i4 = i3 & (-9);
                this.bitField0_ = i4;
                this.tbtTurnTipUnit_ = "";
                int i5 = i4 & (-17);
                this.bitField0_ = i5;
                this.tbtTurnNavigationInformationIcon_ = 0;
                int i6 = i5 & (-33);
                this.bitField0_ = i6;
                this.tbtNextNextRoadDist_ = "";
                int i7 = i6 & (-65);
                this.bitField0_ = i7;
                this.tbtNextNextRoadDistUnit_ = "";
                int i8 = i7 & (-129);
                this.bitField0_ = i8;
                this.tbtRemainingMilageValue_ = "";
                int i9 = i8 & (-257);
                this.bitField0_ = i9;
                this.tbtRemainingMilageUnit_ = "";
                int i10 = i9 & (-513);
                this.bitField0_ = i10;
                this.tbtRemainingSecondValue_ = "";
                int i11 = i10 & (-1025);
                this.bitField0_ = i11;
                this.tbtArriveTimeDay_ = "";
                int i12 = i11 & (-2049);
                this.bitField0_ = i12;
                this.tbtArriveTime_ = "";
                int i13 = i12 & (-4097);
                this.bitField0_ = i13;
                this.tbtMultipleText_ = "";
                int i14 = i13 & (-8193);
                this.bitField0_ = i14;
                this.tbtMultipleTextOne_ = "";
                int i15 = i14 & (-16385);
                this.bitField0_ = i15;
                this.tbtMultipleTextTwo_ = "";
                this.bitField0_ = i15 & (-32769);
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_NaviTbtInfo_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NaviTbtInfo getDefaultInstanceForType() {
                return NaviTbtInfo.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NaviTbtInfo build() {
                NaviTbtInfo result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NaviTbtInfo buildPartial() {
                NaviTbtInfo result = new NaviTbtInfo(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.tbtMapType_ = this.tbtMapType_;
                if ((from_bitField0_ & 2) == 2) {
                    to_bitField0_ |= 2;
                }
                result.tbtNextNavigationInformationIcon_ = this.tbtNextNavigationInformationIcon_;
                if ((from_bitField0_ & 4) == 4) {
                    to_bitField0_ |= 4;
                }
                result.tbtDestinationInfo_ = this.tbtDestinationInfo_;
                if ((from_bitField0_ & 8) == 8) {
                    to_bitField0_ |= 8;
                }
                result.tbtTurnTipDigit_ = this.tbtTurnTipDigit_;
                if ((from_bitField0_ & 16) == 16) {
                    to_bitField0_ |= 16;
                }
                result.tbtTurnTipUnit_ = this.tbtTurnTipUnit_;
                if ((from_bitField0_ & 32) == 32) {
                    to_bitField0_ |= 32;
                }
                result.tbtTurnNavigationInformationIcon_ = this.tbtTurnNavigationInformationIcon_;
                if ((from_bitField0_ & 64) == 64) {
                    to_bitField0_ |= 64;
                }
                result.tbtNextNextRoadDist_ = this.tbtNextNextRoadDist_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                }
                result.tbtNextNextRoadDistUnit_ = this.tbtNextNextRoadDistUnit_;
                if ((from_bitField0_ & 256) == 256) {
                    to_bitField0_ |= 256;
                }
                result.tbtRemainingMilageValue_ = this.tbtRemainingMilageValue_;
                if ((from_bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512) {
                    to_bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                }
                result.tbtRemainingMilageUnit_ = this.tbtRemainingMilageUnit_;
                if ((from_bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024) {
                    to_bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                }
                result.tbtRemainingSecondValue_ = this.tbtRemainingSecondValue_;
                if ((from_bitField0_ & 2048) == 2048) {
                    to_bitField0_ |= 2048;
                }
                result.tbtArriveTimeDay_ = this.tbtArriveTimeDay_;
                if ((from_bitField0_ & 4096) == 4096) {
                    to_bitField0_ |= 4096;
                }
                result.tbtArriveTime_ = this.tbtArriveTime_;
                if ((from_bitField0_ & 8192) == 8192) {
                    to_bitField0_ |= 8192;
                }
                result.tbtMultipleText_ = this.tbtMultipleText_;
                if ((from_bitField0_ & 16384) == 16384) {
                    to_bitField0_ |= 16384;
                }
                result.tbtMultipleTextOne_ = this.tbtMultipleTextOne_;
                if ((from_bitField0_ & 32768) == 32768) {
                    to_bitField0_ |= 32768;
                }
                result.tbtMultipleTextTwo_ = this.tbtMultipleTextTwo_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof NaviTbtInfo) {
                    return mergeFrom((NaviTbtInfo) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(NaviTbtInfo other) {
                if (other == NaviTbtInfo.getDefaultInstance()) {
                    return this;
                }
                if (other.hasTbtMapType()) {
                    setTbtMapType(other.getTbtMapType());
                }
                if (other.hasTbtNextNavigationInformationIcon()) {
                    setTbtNextNavigationInformationIcon(other.getTbtNextNavigationInformationIcon());
                }
                if (other.hasTbtDestinationInfo()) {
                    this.bitField0_ |= 4;
                    this.tbtDestinationInfo_ = other.tbtDestinationInfo_;
                    onChanged();
                }
                if (other.hasTbtTurnTipDigit()) {
                    this.bitField0_ |= 8;
                    this.tbtTurnTipDigit_ = other.tbtTurnTipDigit_;
                    onChanged();
                }
                if (other.hasTbtTurnTipUnit()) {
                    this.bitField0_ |= 16;
                    this.tbtTurnTipUnit_ = other.tbtTurnTipUnit_;
                    onChanged();
                }
                if (other.hasTbtTurnNavigationInformationIcon()) {
                    setTbtTurnNavigationInformationIcon(other.getTbtTurnNavigationInformationIcon());
                }
                if (other.hasTbtNextNextRoadDist()) {
                    this.bitField0_ |= 64;
                    this.tbtNextNextRoadDist_ = other.tbtNextNextRoadDist_;
                    onChanged();
                }
                if (other.hasTbtNextNextRoadDistUnit()) {
                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                    this.tbtNextNextRoadDistUnit_ = other.tbtNextNextRoadDistUnit_;
                    onChanged();
                }
                if (other.hasTbtRemainingMilageValue()) {
                    this.bitField0_ |= 256;
                    this.tbtRemainingMilageValue_ = other.tbtRemainingMilageValue_;
                    onChanged();
                }
                if (other.hasTbtRemainingMilageUnit()) {
                    this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                    this.tbtRemainingMilageUnit_ = other.tbtRemainingMilageUnit_;
                    onChanged();
                }
                if (other.hasTbtRemainingSecondValue()) {
                    this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                    this.tbtRemainingSecondValue_ = other.tbtRemainingSecondValue_;
                    onChanged();
                }
                if (other.hasTbtArriveTimeDay()) {
                    this.bitField0_ |= 2048;
                    this.tbtArriveTimeDay_ = other.tbtArriveTimeDay_;
                    onChanged();
                }
                if (other.hasTbtArriveTime()) {
                    this.bitField0_ |= 4096;
                    this.tbtArriveTime_ = other.tbtArriveTime_;
                    onChanged();
                }
                if (other.hasTbtMultipleText()) {
                    this.bitField0_ |= 8192;
                    this.tbtMultipleText_ = other.tbtMultipleText_;
                    onChanged();
                }
                if (other.hasTbtMultipleTextOne()) {
                    this.bitField0_ |= 16384;
                    this.tbtMultipleTextOne_ = other.tbtMultipleTextOne_;
                    onChanged();
                }
                if (other.hasTbtMultipleTextTwo()) {
                    this.bitField0_ |= 32768;
                    this.tbtMultipleTextTwo_ = other.tbtMultipleTextTwo_;
                    onChanged();
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                NaviTbtInfo parsedMessage = null;
                try {
                    try {
                        parsedMessage = NaviTbtInfo.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtMapType() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public MapType getTbtMapType() {
                return this.tbtMapType_;
            }

            public Builder setTbtMapType(MapType value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 1;
                this.tbtMapType_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtMapType() {
                this.bitField0_ &= -2;
                this.tbtMapType_ = MapType.MAP_TYPE_INVALID;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtNextNavigationInformationIcon() {
                return (this.bitField0_ & 2) == 2;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public int getTbtNextNavigationInformationIcon() {
                return this.tbtNextNavigationInformationIcon_;
            }

            public Builder setTbtNextNavigationInformationIcon(int value) {
                this.bitField0_ |= 2;
                this.tbtNextNavigationInformationIcon_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtNextNavigationInformationIcon() {
                this.bitField0_ &= -3;
                this.tbtNextNavigationInformationIcon_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtDestinationInfo() {
                return (this.bitField0_ & 4) == 4;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtDestinationInfo() {
                Object ref = this.tbtDestinationInfo_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtDestinationInfo_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtDestinationInfoBytes() {
                Object ref = this.tbtDestinationInfo_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtDestinationInfo_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtDestinationInfo(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.tbtDestinationInfo_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtDestinationInfo() {
                this.bitField0_ &= -5;
                this.tbtDestinationInfo_ = NaviTbtInfo.getDefaultInstance().getTbtDestinationInfo();
                onChanged();
                return this;
            }

            public Builder setTbtDestinationInfoBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4;
                this.tbtDestinationInfo_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtTurnTipDigit() {
                return (this.bitField0_ & 8) == 8;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtTurnTipDigit() {
                Object ref = this.tbtTurnTipDigit_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtTurnTipDigit_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtTurnTipDigitBytes() {
                Object ref = this.tbtTurnTipDigit_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtTurnTipDigit_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtTurnTipDigit(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.tbtTurnTipDigit_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtTurnTipDigit() {
                this.bitField0_ &= -9;
                this.tbtTurnTipDigit_ = NaviTbtInfo.getDefaultInstance().getTbtTurnTipDigit();
                onChanged();
                return this;
            }

            public Builder setTbtTurnTipDigitBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8;
                this.tbtTurnTipDigit_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtTurnTipUnit() {
                return (this.bitField0_ & 16) == 16;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtTurnTipUnit() {
                Object ref = this.tbtTurnTipUnit_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtTurnTipUnit_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtTurnTipUnitBytes() {
                Object ref = this.tbtTurnTipUnit_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtTurnTipUnit_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtTurnTipUnit(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.tbtTurnTipUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtTurnTipUnit() {
                this.bitField0_ &= -17;
                this.tbtTurnTipUnit_ = NaviTbtInfo.getDefaultInstance().getTbtTurnTipUnit();
                onChanged();
                return this;
            }

            public Builder setTbtTurnTipUnitBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16;
                this.tbtTurnTipUnit_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtTurnNavigationInformationIcon() {
                return (this.bitField0_ & 32) == 32;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public int getTbtTurnNavigationInformationIcon() {
                return this.tbtTurnNavigationInformationIcon_;
            }

            public Builder setTbtTurnNavigationInformationIcon(int value) {
                this.bitField0_ |= 32;
                this.tbtTurnNavigationInformationIcon_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtTurnNavigationInformationIcon() {
                this.bitField0_ &= -33;
                this.tbtTurnNavigationInformationIcon_ = 0;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtNextNextRoadDist() {
                return (this.bitField0_ & 64) == 64;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtNextNextRoadDist() {
                Object ref = this.tbtNextNextRoadDist_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtNextNextRoadDist_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtNextNextRoadDistBytes() {
                Object ref = this.tbtNextNextRoadDist_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtNextNextRoadDist_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtNextNextRoadDist(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.tbtNextNextRoadDist_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtNextNextRoadDist() {
                this.bitField0_ &= -65;
                this.tbtNextNextRoadDist_ = NaviTbtInfo.getDefaultInstance().getTbtNextNextRoadDist();
                onChanged();
                return this;
            }

            public Builder setTbtNextNextRoadDistBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 64;
                this.tbtNextNextRoadDist_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtNextNextRoadDistUnit() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE) == 128;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtNextNextRoadDistUnit() {
                Object ref = this.tbtNextNextRoadDistUnit_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtNextNextRoadDistUnit_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtNextNextRoadDistUnitBytes() {
                Object ref = this.tbtNextNextRoadDistUnit_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtNextNextRoadDistUnit_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtNextNextRoadDistUnit(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.tbtNextNextRoadDistUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtNextNextRoadDistUnit() {
                this.bitField0_ &= -129;
                this.tbtNextNextRoadDistUnit_ = NaviTbtInfo.getDefaultInstance().getTbtNextNextRoadDistUnit();
                onChanged();
                return this;
            }

            public Builder setTbtNextNextRoadDistUnitBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDEventId.SUSD_POWER_SUSPEND_PREPARE_VALUE;
                this.tbtNextNextRoadDistUnit_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtRemainingMilageValue() {
                return (this.bitField0_ & 256) == 256;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtRemainingMilageValue() {
                Object ref = this.tbtRemainingMilageValue_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtRemainingMilageValue_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtRemainingMilageValueBytes() {
                Object ref = this.tbtRemainingMilageValue_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtRemainingMilageValue_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtRemainingMilageValue(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.tbtRemainingMilageValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtRemainingMilageValue() {
                this.bitField0_ &= -257;
                this.tbtRemainingMilageValue_ = NaviTbtInfo.getDefaultInstance().getTbtRemainingMilageValue();
                onChanged();
                return this;
            }

            public Builder setTbtRemainingMilageValueBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 256;
                this.tbtRemainingMilageValue_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtRemainingMilageUnit() {
                return (this.bitField0_ & ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE) == 512;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtRemainingMilageUnit() {
                Object ref = this.tbtRemainingMilageUnit_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtRemainingMilageUnit_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtRemainingMilageUnitBytes() {
                Object ref = this.tbtRemainingMilageUnit_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtRemainingMilageUnit_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtRemainingMilageUnit(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.tbtRemainingMilageUnit_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtRemainingMilageUnit() {
                this.bitField0_ &= -513;
                this.tbtRemainingMilageUnit_ = NaviTbtInfo.getDefaultInstance().getTbtRemainingMilageUnit();
                onChanged();
                return this;
            }

            public Builder setTbtRemainingMilageUnitBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= ComBoschCmHousekeepingSusdif.SUSDMsgId.SUSD_SET_CAMERA_POWER_REQ_VALUE;
                this.tbtRemainingMilageUnit_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtRemainingSecondValue() {
                return (this.bitField0_ & OtaProto.ErrorCode.ERASE_ROUTINE_VALUE) == 1024;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtRemainingSecondValue() {
                Object ref = this.tbtRemainingSecondValue_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtRemainingSecondValue_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtRemainingSecondValueBytes() {
                Object ref = this.tbtRemainingSecondValue_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtRemainingSecondValue_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtRemainingSecondValue(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.tbtRemainingSecondValue_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtRemainingSecondValue() {
                this.bitField0_ &= -1025;
                this.tbtRemainingSecondValue_ = NaviTbtInfo.getDefaultInstance().getTbtRemainingSecondValue();
                onChanged();
                return this;
            }

            public Builder setTbtRemainingSecondValueBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= OtaProto.ErrorCode.ERASE_ROUTINE_VALUE;
                this.tbtRemainingSecondValue_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtArriveTimeDay() {
                return (this.bitField0_ & 2048) == 2048;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtArriveTimeDay() {
                Object ref = this.tbtArriveTimeDay_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtArriveTimeDay_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtArriveTimeDayBytes() {
                Object ref = this.tbtArriveTimeDay_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtArriveTimeDay_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtArriveTimeDay(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2048;
                this.tbtArriveTimeDay_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtArriveTimeDay() {
                this.bitField0_ &= -2049;
                this.tbtArriveTimeDay_ = NaviTbtInfo.getDefaultInstance().getTbtArriveTimeDay();
                onChanged();
                return this;
            }

            public Builder setTbtArriveTimeDayBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 2048;
                this.tbtArriveTimeDay_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtArriveTime() {
                return (this.bitField0_ & 4096) == 4096;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtArriveTime() {
                Object ref = this.tbtArriveTime_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtArriveTime_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtArriveTimeBytes() {
                Object ref = this.tbtArriveTime_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtArriveTime_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtArriveTime(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4096;
                this.tbtArriveTime_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtArriveTime() {
                this.bitField0_ &= -4097;
                this.tbtArriveTime_ = NaviTbtInfo.getDefaultInstance().getTbtArriveTime();
                onChanged();
                return this;
            }

            public Builder setTbtArriveTimeBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 4096;
                this.tbtArriveTime_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtMultipleText() {
                return (this.bitField0_ & 8192) == 8192;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtMultipleText() {
                Object ref = this.tbtMultipleText_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtMultipleText_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtMultipleTextBytes() {
                Object ref = this.tbtMultipleText_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtMultipleText_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtMultipleText(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8192;
                this.tbtMultipleText_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtMultipleText() {
                this.bitField0_ &= -8193;
                this.tbtMultipleText_ = NaviTbtInfo.getDefaultInstance().getTbtMultipleText();
                onChanged();
                return this;
            }

            public Builder setTbtMultipleTextBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 8192;
                this.tbtMultipleText_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtMultipleTextOne() {
                return (this.bitField0_ & 16384) == 16384;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtMultipleTextOne() {
                Object ref = this.tbtMultipleTextOne_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtMultipleTextOne_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtMultipleTextOneBytes() {
                Object ref = this.tbtMultipleTextOne_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtMultipleTextOne_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtMultipleTextOne(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16384;
                this.tbtMultipleTextOne_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtMultipleTextOne() {
                this.bitField0_ &= -16385;
                this.tbtMultipleTextOne_ = NaviTbtInfo.getDefaultInstance().getTbtMultipleTextOne();
                onChanged();
                return this;
            }

            public Builder setTbtMultipleTextOneBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 16384;
                this.tbtMultipleTextOne_ = value;
                onChanged();
                return this;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public boolean hasTbtMultipleTextTwo() {
                return (this.bitField0_ & 32768) == 32768;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public String getTbtMultipleTextTwo() {
                Object ref = this.tbtMultipleTextTwo_;
                if (!(ref instanceof String)) {
                    ByteString bs = (ByteString) ref;
                    String s = bs.toStringUtf8();
                    if (bs.isValidUtf8()) {
                        this.tbtMultipleTextTwo_ = s;
                    }
                    return s;
                }
                return (String) ref;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviTbtInfoOrBuilder
            public ByteString getTbtMultipleTextTwoBytes() {
                Object ref = this.tbtMultipleTextTwo_;
                if (ref instanceof String) {
                    ByteString b = ByteString.copyFromUtf8((String) ref);
                    this.tbtMultipleTextTwo_ = b;
                    return b;
                }
                return (ByteString) ref;
            }

            public Builder setTbtMultipleTextTwo(String value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32768;
                this.tbtMultipleTextTwo_ = value;
                onChanged();
                return this;
            }

            public Builder clearTbtMultipleTextTwo() {
                this.bitField0_ &= -32769;
                this.tbtMultipleTextTwo_ = NaviTbtInfo.getDefaultInstance().getTbtMultipleTextTwo();
                onChanged();
                return this;
            }

            public Builder setTbtMultipleTextTwoBytes(ByteString value) {
                if (value == null) {
                    throw null;
                }
                this.bitField0_ |= 32768;
                this.tbtMultipleTextTwo_ = value;
                onChanged();
                return this;
            }
        }
    }

    public static final class NaviInstallState extends GeneratedMessage implements NaviInstallStateOrBuilder {
        public static final int MNAVIINSTALLSTATE_FIELD_NUMBER = 1;
        public static Parser<NaviInstallState> PARSER = new AbstractParser<NaviInstallState>() { // from class: com.autolink.interaction.InteractionProto.NaviInstallState.1
            @Override // com.google.protobuf.Parser
            public NaviInstallState parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new NaviInstallState(input, extensionRegistry);
            }
        };
        private static final NaviInstallState defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int mNaviInstallState_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private NaviInstallState(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private NaviInstallState(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static NaviInstallState getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public NaviInstallState getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private NaviInstallState(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.mNaviInstallState_ = input.readInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_NaviInstallState_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_NaviInstallState_fieldAccessorTable.ensureFieldAccessorsInitialized(NaviInstallState.class, Builder.class);
        }

        static {
            NaviInstallState naviInstallState = new NaviInstallState(true);
            defaultInstance = naviInstallState;
            naviInstallState.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<NaviInstallState> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviInstallStateOrBuilder
        public boolean hasMNaviInstallState() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.NaviInstallStateOrBuilder
        public int getMNaviInstallState() {
            return this.mNaviInstallState_;
        }

        private void initFields() {
            this.mNaviInstallState_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.mNaviInstallState_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.mNaviInstallState_) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static NaviInstallState parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NaviInstallState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NaviInstallState parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static NaviInstallState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static NaviInstallState parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NaviInstallState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static NaviInstallState parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static NaviInstallState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static NaviInstallState parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static NaviInstallState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(NaviInstallState prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements NaviInstallStateOrBuilder {
            private int bitField0_;
            private int mNaviInstallState_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_NaviInstallState_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_NaviInstallState_fieldAccessorTable.ensureFieldAccessorsInitialized(NaviInstallState.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = NaviInstallState.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mNaviInstallState_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_NaviInstallState_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public NaviInstallState getDefaultInstanceForType() {
                return NaviInstallState.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NaviInstallState build() {
                NaviInstallState result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public NaviInstallState buildPartial() {
                NaviInstallState result = new NaviInstallState(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mNaviInstallState_ = this.mNaviInstallState_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof NaviInstallState) {
                    return mergeFrom((NaviInstallState) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(NaviInstallState other) {
                if (other == NaviInstallState.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMNaviInstallState()) {
                    setMNaviInstallState(other.getMNaviInstallState());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                NaviInstallState parsedMessage = null;
                try {
                    try {
                        parsedMessage = NaviInstallState.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.NaviInstallStateOrBuilder
            public boolean hasMNaviInstallState() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.NaviInstallStateOrBuilder
            public int getMNaviInstallState() {
                return this.mNaviInstallState_;
            }

            public Builder setMNaviInstallState(int value) {
                this.bitField0_ |= 1;
                this.mNaviInstallState_ = value;
                onChanged();
                return this;
            }

            public Builder clearMNaviInstallState() {
                this.bitField0_ &= -2;
                this.mNaviInstallState_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static final class CpaaConnectState extends GeneratedMessage implements CpaaConnectStateOrBuilder {
        public static final int MCPAACONNECTSTATE_FIELD_NUMBER = 1;
        public static Parser<CpaaConnectState> PARSER = new AbstractParser<CpaaConnectState>() { // from class: com.autolink.interaction.InteractionProto.CpaaConnectState.1
            @Override // com.google.protobuf.Parser
            public CpaaConnectState parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
                return new CpaaConnectState(input, extensionRegistry);
            }
        };
        private static final CpaaConnectState defaultInstance;
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private int mCpaaConnectState_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final UnknownFieldSet unknownFields;

        private CpaaConnectState(GeneratedMessage.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = builder.getUnknownFields();
        }

        private CpaaConnectState(boolean noInit) {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            this.unknownFields = UnknownFieldSet.getDefaultInstance();
        }

        public static CpaaConnectState getDefaultInstance() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
        public CpaaConnectState getDefaultInstanceForType() {
            return defaultInstance;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageOrBuilder
        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        private CpaaConnectState(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            this.memoizedIsInitialized = (byte) -1;
            this.memoizedSerializedSize = -1;
            initFields();
            UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
            boolean done = false;
            while (!done) {
                try {
                    try {
                        int tag = input.readTag();
                        if (tag == 0) {
                            done = true;
                        } else if (tag != 8) {
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                        } else {
                            this.bitField0_ |= 1;
                            this.mCpaaConnectState_ = input.readInt32();
                        }
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = unknownFields.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return InteractionProto.internal_static_autolink_interaction_CpaaConnectState_descriptor;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
            return InteractionProto.internal_static_autolink_interaction_CpaaConnectState_fieldAccessorTable.ensureFieldAccessorsInitialized(CpaaConnectState.class, Builder.class);
        }

        static {
            CpaaConnectState cpaaConnectState = new CpaaConnectState(true);
            defaultInstance = cpaaConnectState;
            cpaaConnectState.initFields();
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Parser<CpaaConnectState> getParserForType() {
            return PARSER;
        }

        @Override // com.autolink.interaction.InteractionProto.CpaaConnectStateOrBuilder
        public boolean hasMCpaaConnectState() {
            return (this.bitField0_ & 1) == 1;
        }

        @Override // com.autolink.interaction.InteractionProto.CpaaConnectStateOrBuilder
        public int getMCpaaConnectState() {
            return this.mCpaaConnectState_;
        }

        private void initFields() {
            this.mCpaaConnectState_ = 0;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLiteOrBuilder
        public final boolean isInitialized() {
            byte isInitialized = this.memoizedIsInitialized;
            if (isInitialized == 1) {
                return true;
            }
            if (isInitialized == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public void writeTo(CodedOutputStream output) throws IOException {
            getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                output.writeInt32(1, this.mCpaaConnectState_);
            }
            getUnknownFields().writeTo(output);
        }

        @Override // com.google.protobuf.GeneratedMessage, com.google.protobuf.AbstractMessage, com.google.protobuf.MessageLite
        public int getSerializedSize() {
            int size = this.memoizedSerializedSize;
            if (size != -1) {
                return size;
            }
            int size2 = ((this.bitField0_ & 1) == 1 ? 0 + CodedOutputStream.computeInt32Size(1, this.mCpaaConnectState_) : 0) + getUnknownFields().getSerializedSize();
            this.memoizedSerializedSize = size2;
            return size2;
        }

        @Override // com.google.protobuf.GeneratedMessage
        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public static CpaaConnectState parseFrom(ByteString data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CpaaConnectState parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CpaaConnectState parseFrom(byte[] data) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }

        public static CpaaConnectState parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }

        public static CpaaConnectState parseFrom(InputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CpaaConnectState parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static CpaaConnectState parseDelimitedFrom(InputStream input) throws IOException {
            return PARSER.parseDelimitedFrom(input);
        }

        public static CpaaConnectState parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseDelimitedFrom(input, extensionRegistry);
        }

        public static CpaaConnectState parseFrom(CodedInputStream input) throws IOException {
            return PARSER.parseFrom(input);
        }

        public static CpaaConnectState parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            return PARSER.parseFrom(input, extensionRegistry);
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder newBuilderForType() {
            return newBuilder();
        }

        public static Builder newBuilder(CpaaConnectState prototype) {
            return newBuilder().mergeFrom(prototype);
        }

        @Override // com.google.protobuf.MessageLite, com.google.protobuf.Message
        public Builder toBuilder() {
            return newBuilder(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.protobuf.GeneratedMessage
        public Builder newBuilderForType(GeneratedMessage.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }

        public static final class Builder extends GeneratedMessage.Builder<Builder> implements CpaaConnectStateOrBuilder {
            private int bitField0_;
            private int mCpaaConnectState_;

            public static final Descriptors.Descriptor getDescriptor() {
                return InteractionProto.internal_static_autolink_interaction_CpaaConnectState_descriptor;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder
            protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
                return InteractionProto.internal_static_autolink_interaction_CpaaConnectState_fieldAccessorTable.ensureFieldAccessorsInitialized(CpaaConnectState.class, Builder.class);
            }

            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessage.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = CpaaConnectState.alwaysUseFieldBuilders;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public static Builder create() {
                return new Builder();
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder clear() {
                super.clear();
                this.mCpaaConnectState_ = 0;
                this.bitField0_ &= -2;
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder
            /* JADX INFO: renamed from: clone */
            public Builder mo0clone() {
                return create().mergeFrom(buildPartial());
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.Message.Builder, com.google.protobuf.MessageOrBuilder
            public Descriptors.Descriptor getDescriptorForType() {
                return InteractionProto.internal_static_autolink_interaction_CpaaConnectState_descriptor;
            }

            @Override // com.google.protobuf.MessageLiteOrBuilder, com.google.protobuf.MessageOrBuilder
            public CpaaConnectState getDefaultInstanceForType() {
                return CpaaConnectState.getDefaultInstance();
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CpaaConnectState build() {
                CpaaConnectState result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException((Message) result);
                }
                return result;
            }

            @Override // com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public CpaaConnectState buildPartial() {
                CpaaConnectState result = new CpaaConnectState(this);
                int from_bitField0_ = this.bitField0_;
                int to_bitField0_ = 0;
                if ((from_bitField0_ & 1) == 1) {
                    to_bitField0_ = 0 | 1;
                }
                result.mCpaaConnectState_ = this.mCpaaConnectState_;
                result.bitField0_ = to_bitField0_;
                onBuilt();
                return result;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(Message other) {
                if (other instanceof CpaaConnectState) {
                    return mergeFrom((CpaaConnectState) other);
                }
                super.mergeFrom(other);
                return this;
            }

            public Builder mergeFrom(CpaaConnectState other) {
                if (other == CpaaConnectState.getDefaultInstance()) {
                    return this;
                }
                if (other.hasMCpaaConnectState()) {
                    setMCpaaConnectState(other.getMCpaaConnectState());
                }
                mergeUnknownFields(other.getUnknownFields());
                return this;
            }

            @Override // com.google.protobuf.GeneratedMessage.Builder, com.google.protobuf.MessageLiteOrBuilder
            public final boolean isInitialized() {
                return true;
            }

            @Override // com.google.protobuf.AbstractMessage.Builder, com.google.protobuf.AbstractMessageLite.Builder, com.google.protobuf.MessageLite.Builder, com.google.protobuf.Message.Builder
            public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
                CpaaConnectState parsedMessage = null;
                try {
                    try {
                        parsedMessage = CpaaConnectState.PARSER.parsePartialFrom(input, extensionRegistry);
                        return this;
                    } catch (InvalidProtocolBufferException e) {
                        throw e;
                    }
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
            }

            @Override // com.autolink.interaction.InteractionProto.CpaaConnectStateOrBuilder
            public boolean hasMCpaaConnectState() {
                return (this.bitField0_ & 1) == 1;
            }

            @Override // com.autolink.interaction.InteractionProto.CpaaConnectStateOrBuilder
            public int getMCpaaConnectState() {
                return this.mCpaaConnectState_;
            }

            public Builder setMCpaaConnectState(int value) {
                this.bitField0_ |= 1;
                this.mCpaaConnectState_ = value;
                onChanged();
                return this;
            }

            public Builder clearMCpaaConnectState() {
                this.bitField0_ &= -2;
                this.mCpaaConnectState_ = 0;
                onChanged();
                return this;
            }
        }
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        String[] descriptorData = {"\n\u001ecom.autolink.interaction.proto\u0012\u0014autolink.interaction\"=\n\u0005Query\u00124\n\u000bquery_value\u0018\u0001 \u0001(\u000e2\u001f.autolink.interaction.QueryType\"a\n\tElevation\u0012\u0017\n\u000felevation_value\u0018\u0001 \u0001(\u0005\u0012;\n\u000eelevation_unit\u0018\u0002 \u0001(\u000e2#.autolink.interaction.ElevationUnit\"Q\n\u0005Slope\u0012\u0013\n\u000bslope_value\u0018\u0001 \u0001(\u0005\u00123\n\nslope_unit\u0018\u0002 \u0001(\u000e2\u001f.autolink.interaction.SlopeUnit\"Ê\u0002\n\u000eBluetoothPhone\u0012<\n\u0012phone_display_icon\u0018\u0001 \u0001(\u000e2 .autolink.interaction.IconDisply\u0012\u0014\n\fcontact_name\u0018\u0002 \u0001", "(\t\u0012\u0016\n\u000econtact_number\u0018\u0003 \u0001(\t\u0012\u0011\n\tcall_time\u0018\u0004 \u0001(\u0005\u0012;\n\tP_visible\u0018\u0005 \u0001(\u000e2(.autolink.interaction.PhoneNumberVisible\u0012<\n\ftime_visible\u0018\u0006 \u0001(\u000e2&.autolink.interaction.PhoneTimeVisible\u0012\f\n\u0004type\u0018\u0007 \u0001(\u0005\u00120\n\u0006status\u0018\b \u0001(\u000e2 .autolink.interaction.CallStatus\"ñ\u0002\n\u0005Music\u0012<\n\u0012music_display_icon\u0018\u0001 \u0001(\u000e2 .autolink.interaction.IconDisply\u0012\u0012\n\nmedia_text\u0018\u0002 \u0001(\t\u00122\n\bM_status\u0018\u0003 \u0001(\u000e2 .autolink.interaction.PlayStatus\u00120\n\tM_visible\u0018\u0004 \u0001(\u000e2\u001d.au", "tolink.interaction.Visible\u00121\n\u0004roll\u0018\u0005 \u0001(\u000e2#.autolink.interaction.MusicNameRoll\u0012\u0015\n\rM_play_source\u0018\u0006 \u0001(\u0005\u0012=\n\u0010M_play_interface\u0018\u0007 \u0001(\u000e2#.autolink.interaction.PlayInterface\u0012\u0012\n\nalbum_path\u0018\b \u0001(\t\u0012\u0013\n\u000bartist_name\u0018\t \u0001(\t\"?\n\rMediaProgress\u0012\u0018\n\u0010current_progress\u0018\u0001 \u0001(\u0005\u0012\u0014\n\fmax_progress\u0018\u0002 \u0001(\u0005\"µ\u0001\n\u0005Clock\u00126\n\u000btime_format\u0018\u0001 \u0001(\u000e2!.autolink.interaction.ClockFormat\u00127\n\fampm_display\u0018\u0002 \u0001(\u000e2!.autolink.interaction.AmPmDisplay\u0012\u0011\n\ttime", "_hour\u0018\u0003 \u0001(\u0005\u0012\u0013\n\u000btime_minute\u0018\u0004 \u0001(\u0005\u0012\u0013\n\u000btime_second\u0018\u0005 \u0001(\u0005\"¢\u0004\n\nNavigation\u0012/\n\bmap_type\u0018\u0001 \u0001(\u000e2\u001d.autolink.interaction.MapType\u0012(\n next_navigation_information_icon\u0018\u0002 \u0001(\u0005\u0012\u0018\n\u0010destination_info\u0018\u0003 \u0001(\t\u0012\u0016\n\u000eturn_tip_digit\u0018\u0004 \u0001(\u0002\u00126\n\ftop_tip_unit\u0018\u0005 \u0001(\u000e2 .autolink.interaction.TopTipUnit\u0012(\n turn_navigation_information_icon\u0018\u0006 \u0001(\u0005\u0012\u001b\n\u0013next_next_road_dist\u0018\u0007 \u0001(\u0002\u0012=\n\u0013next_next_road_unit\u0018\b \u0001(\u000e2 .autolink.interaction.TopTipUnit\u0012", "\u001e\n\u0016remaining_milage_value\u0018\t \u0001(\u0002\u0012:\n\u0010remaining_milage\u0018\n \u0001(\u000e2 .autolink.interaction.TopTipUnit\u0012\u001e\n\u0016remaining_second_value\u0018\u000b \u0001(\u0005\u0012\u0017\n\u000farrive_time_day\u0018\f \u0001(\u0005\u0012\u0018\n\u0010arrive_time_hour\u0018\r \u0001(\u0005\u0012\u001a\n\u0012arrive_time_minute\u0018\u000e \u0001(\u0005\"ö\u0001\n\u000fInteractionData\u0012C\n\u0015bluetooth_phone_value\u0018\u0001 \u0001(\u000b2$.autolink.interaction.BluetoothPhone\u00120\n\u000bmusic_value\u0018\u0002 \u0001(\u000b2\u001b.autolink.interaction.Music\u00120\n\u000bclock_value\u0018\u0003 \u0001(\u000b2\u001b.autolink.interaction.Clock\u0012:\n\u0010Navig", "ation_value\u0018\u0004 \u0001(\u000b2 .autolink.interaction.Navigation\"\u001d\n\bLanguage\u0012\u0011\n\tnCurrLang\u0018\u0001 \u0002(\u0005\"$\n\u000eResetOperation\u0012\u0012\n\nnResetOper\u0018\u0001 \u0001(\u0005\"B\n\fThemeSetting\u00122\n\nnCurrTheme\u0018\u0001 \u0001(\u000e2\u001e.autolink.interaction.ThemeSet\"G\n\fChimeSetting\u00127\n\u000enCurrSoundType\u0018\u0001 \u0001(\u000e2\u001f.autolink.interaction.SoundType\"\u0086\u0001\n\u000bNaviSetting\u0012/\n\bnMapType\u0018\u0001 \u0001(\u000e2\u001d.autolink.interaction.MapType\u0012F\n\u0010nCurrMedNaviStat\u0018\u0002 \u0001(\u000e2,.autolink.interaction.MediaNavigationalstate\"´", "\u0006\n\rHotKeySetting\u00127\n\fnVehicleType\u0018\u0001 \u0001(\u000e2!.autolink.interaction.VehicleType\u00123\n\nnVehicleID\u0018\u0002 \u0001(\u000e2\u001f.autolink.interaction.VehicleID\u0012>\n\u000enCurrThemeType\u0018\u0003 \u0001(\u000e2&.autolink.interaction.VehicleThemeType\u0012B\n\u000enCurrLightStat\u0018\u0004 \u0001(\u000e2*.autolink.interaction.VehicleLightGearStat\u0012:\n\nnCurrLight\u0018\u0005 \u0001(\u000e2&.autolink.interaction.VehicleLightGear\u0012<\n\rnCurrNaviType\u0018\u0006 \u0001(\u000e2%.autolink.interaction.VehicleNaviType\u0012<\n\rnCurrAdasStat\u0018\u0007 ", "\u0001(\u000e2%.autolink.interaction.VehicleAdasStat\u0012=\n\u000fnCurrINSDisplay\u0018\b \u0001(\u000e2$.autolink.interaction.VehicleINSDisp\u0012<\n\rnCurrMileInfo\u0018\t \u0001(\u000e2%.autolink.interaction.VehicleMileInfo\u0012<\n\rnCurrMileUnit\u0018\n \u0001(\u000e2%.autolink.interaction.VehicleMileUnit\u0012>\n\u000enCurrMileValue\u0018\u000b \u0001(\u000e2&.autolink.interaction.VehicleMileValue\u0012<\n\rnCurrAccTimer\u0018\f \u0001(\u000e2%.autolink.interaction.VehicleAccTimer\u0012@\n\u000fnCurrSpeedLimit\u0018\r \u0001(\u000e2'.autolink.interact", "ion.VehicleSpeedLimit\"R\n\u0015UpdateSettingThemeReq\u00129\n\u0011nCurrThemeInfoReq\u0018\u0001 \u0001(\u000e2\u001e.autolink.interaction.ThemeSet\"S\n\u0013UpdateHotKeyNaviReq\u0012<\n\rnCurrNaviType\u0018\u0001 \u0001(\u000e2%.autolink.interaction.VehicleNaviType\"[\n\u0019UpdateHotKeyInsDisplayReq\u0012>\n\u0010nCurrAdasStatReq\u0018\u0001 \u0001(\u000e2$.autolink.interaction.VehicleINSDisp\"V\n\u0013UpdateHotKeyAdasReq\u0012?\n\u0010nCurrAdasStatReq\u0018\u0001 \u0001(\u000e2%.autolink.interaction.VehicleAdasStat\"_\n\u0014UpdateHotKeyThemeReq\u0012G\n\u0017n", "CurrHotKeyThemeTypeReq\u0018\u0001 \u0001(\u000e2&.autolink.interaction.VehicleThemeType\"I\n\u000eInsUiChangeReq\u00127\n\rnCurrInsUiReq\u0018\u0001 \u0001(\u000e2 .autolink.interaction.InsUiState\"?\n\bMapFocus\u00123\n\fnCurrMapType\u0018\u0001 \u0001(\u000e2\u001d.autolink.interaction.MapType\"\u0085\u0001\n\bMapState\u00123\n\fmCurrMapType\u0018\u0001 \u0001(\u000e2\u001d.autolink.interaction.MapType\u0012D\n\u0013navigation_pop_info\u0018\u0002 \u0001(\u000e2'.autolink.interaction.NavigationPopInfo\"Ò\u0001\n\u000eInstrumentTest\u00125\n\u000bosd_visible\u0018\u0001 \u0001(\u000e2 .autolink.inte", "raction.OSDVisible\u0012K\n\u0017adas_auto_learn_visible\u0018\u0002 \u0001(\u000e2*.autolink.interaction.AdasAutoLearnVisible\u0012<\n\u000fadas_debug_mode\u0018\u0003 \u0001(\u000e2#.autolink.interaction.AdasDebugMode\"J\n\u000fChimeSettingReq\u00127\n\u000emCurrSoundType\u0018\u0001 \u0001(\u000e2\u001f.autolink.interaction.SoundType\"h\n\u0018ScreenOrientationSetting\u0012L\n\u0016mCurrScreenOrientation\u0018\u0001 \u0001(\u000e2,.autolink.interaction.ScreenOrientationValue\"?\n\u0015VersatileThemeSetting\u0012&\n\u0013mCurrVersatileTheme\u0018\u0001 \u0001(\u0005:\t10000", "0010\"3\n\u0013OverseasMapSupplier\u0012\u001c\n\u0014mOverseasMapSupplier\u0018\u0001 \u0001(\u0005\"´\u0004\n\u000bNaviTbtInfo\u00123\n\ftbt_map_type\u0018\u0001 \u0001(\u000e2\u001d.autolink.interaction.MapType\u0012,\n$tbt_next_navigation_information_icon\u0018\u0002 \u0001(\u0005\u0012\u001c\n\u0014tbt_destination_info\u0018\u0003 \u0001(\t\u0012\u001a\n\u0012tbt_turn_tip_digit\u0018\u0004 \u0001(\t\u0012\u0019\n\u0011tbt_turn_tip_unit\u0018\u0005 \u0001(\t\u0012,\n$tbt_turn_navigation_information_icon\u0018\u0006 \u0001(\u0005\u0012\u001f\n\u0017tbt_next_next_road_dist\u0018\u0007 \u0001(\t\u0012$\n\u001ctbt_next_next_road_dist_unit\u0018\b \u0001(\t\u0012\"\n\u001atbt_remaining_milage_v", "alue\u0018\t \u0001(\t\u0012!\n\u0019tbt_remaining_milage_unit\u0018\n \u0001(\t\u0012\"\n\u001atbt_remaining_second_value\u0018\u000b \u0001(\t\u0012\u001b\n\u0013tbt_arrive_time_day\u0018\f \u0001(\t\u0012\u0017\n\u000ftbt_arrive_time\u0018\r \u0001(\t\u0012\u0019\n\u0011tbt_multiple_text\u0018\u000e \u0001(\t\u0012\u001d\n\u0015tbt_multiple_text_one\u0018\u000f \u0001(\t\u0012\u001d\n\u0015tbt_multiple_text_two\u0018\u0010 \u0001(\t\"-\n\u0010NaviInstallState\u0012\u0019\n\u0011mNaviInstallState\u0018\u0001 \u0001(\u0005\"-\n\u0010CpaaConnectState\u0012\u0019\n\u0011mCpaaConnectState\u0018\u0001 \u0001(\u0005*ÿ\u0003\n\u0007EventId\u0012\u0011\n\rSETTING_THEME\u0010\u0000\u0012\u0011\n\rSETTING_CLOCK\u0010\u0001\u0012\u0010\n\fSETTING_NAVI\u0010\u0002\u0012\u0012\n\u000eSETTING_HO", "TKEY\u0010\u0003\u0012\u000f\n\u000bMEDIA_MUSIC\u0010\u0004\u0012\u0012\n\u000eMEDIA_PROGRESS\u0010\u0005\u0012\u000f\n\u000bMEDIA_PHONE\u0010\u0006\u0012\u0014\n\u0010MEDIA_NAVIGATION\u0010\u0007\u0012\u0014\n\u0010SETTING_LANGUAGE\u0010\b\u0012\u0011\n\rSETTING_RESET\u0010\t\u0012\u000f\n\u000bCHIME_SOUND\u0010\n\u0012\u0019\n\u0015FIXEDISPLAY_ELEVATION\u0010\u000b\u0012\u0015\n\u0011FIXEDISPLAY_SLOPE\u0010\f\u0012\u0011\n\rSTARTUP_QUERY\u0010\r\u0012\u0012\n\u000eMAP_TYEP_FOCUS\u0010\u000e\u0012\r\n\tMAP_STATE\u0010\u000f\u0012\u0013\n\u000fINSTRUMENT_TEST\u0010\u0010\u0012\u001e\n\u001aSETTING_SCREEN_ORIENTATION\u0010\u0011\u0012\u001b\n\u0017SETTING_VERSATILE_THEME\u0010\u0012\u0012\u0019\n\u0015OVERSEAS_MAP_SUPPLIER\u0010\u0013\u0012\u0017\n\u0013NAVIGATION_TBT_INFO\u0010\u0014\u0012\u001c\n\u0018NAVIGATION_INSTALL", "_STATE\u0010\u0015\u0012\u0016\n\u0012CPAA_CONNECT_STATE\u0010\u0016*Õ\u0001\n\u0007MsgType\u0012\u0015\n\u0011SETTING_THEME_CMD\u0010\u0000\u0012\u001a\n\u0016SETTING_HOTKEYNAVI_CMD\u0010\u0001\u0012 \n\u001cSETTING_HOTKEYINSDISPLAY_CMD\u0010\u0002\u0012\u001a\n\u0016SETTING_HOTKEYADAS_CMD\u0010\u0003\u0012\u001b\n\u0017SETTING_HOTKEYTHEME_CMD\u0010\u0004\u0012\u0015\n\u0011INS_UI_CHANGE_CMD\u0010\u0005\u0012\u0013\n\u000fCHIME_SOUND_CMD\u0010\u0006\u0012\u0010\n\fMSG_TYPE_MAX\u0010\u0007*ô\u0010\n\u0013NavigationIfomation\u0012\u000b\n\u0007INVALID\u0010\u0000\u0012\r\n\tTURN_LEFT\u0010\u0001\u0012\u000e\n\nTURN_RIGHT\u0010\u0002\u0012\u000e\n\nLEFT_FRONT\u0010\u0003\u0012\u000f\n\u000bRIGHT_FRONT\u0010\u0004\u0012\r\n\tLEFT_REAR\u0010\u0005\u0012\u000e\n\nRIGHT_REAR\u0010\u0006\u0012\u0013\n\u000fLEFT_TURNAROUND\u0010", "\u0007\u0012\u0012\n\u000eSTRAIGHT_AHEAD\u0010\b\u0012\u000e\n\nREST_POINT\u0010\n\u0012\u000b\n\u0007CONTACT\u0010\u000b\u0012\u000f\n\u000bDESTINATION\u0010\f\u0012\u0014\n\u0010ENTER_THE_TUNNEL\u0010\r\u0012\u0014\n\u0010RIGHT_TURNAROUND\u0010\u000e\u0012\u0013\n\u000fPROGRADE_MOTION\u0010\u000f\u0012\u0015\n\u0011ROUNDABOUT_EXIT_1\u0010\u0010\u0012\u0015\n\u0011ROUNDABOUT_EXIT_2\u0010\u0011\u0012\u0015\n\u0011ROUNDABOUT_EXIT_3\u0010\u0012\u0012\u0015\n\u0011ROUNDABOUT_EXIT_4\u0010\u0013\u0012\u0015\n\u0011ROUNDABOUT_EXIT_5\u0010\u0014\u0012\u0015\n\u0011ROUNDABOUT_EXIT_6\u0010\u0015\u0012\u0015\n\u0011ROUNDABOUT_EXIT_7\u0010\u0016\u0012\u0015\n\u0011ROUNDABOUT_EXIT_8\u0010\u0017\u0012\u0015\n\u0011ROUNDABOUT_EXIT_9\u0010,\u0012\u0016\n\u0012ROUNDABOUT_EXIT_10\u0010-\u0012\u0016\n\u0012ROUNDABOUT_EXIT_11\u0010.\u0012\u0016\n\u0012ROUNDABOU", "T_EXIT_12\u0010/\u0012(\n$DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_1\u00100\u0012)\n%DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_1\u00101\u0012-\n)DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1\u00102\u0012(\n$DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_2\u00103\u0012-\n)DOUBLE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2\u00104\u0012)\n%DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_2\u00105\u0012(\n$DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_3\u00106\u0012(\n$DOUBLE_CROSS_SMALL_ANGLE_TURN_LEFT_4\u00107\u0012)\n%DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_3", "\u00108\u0012)\n%DOUBLE_CROSS_SMALL_ANGLE_TURN_RIGHT_4\u00109\u0012\t\n\u0005FERRY\u0010:\u0012\u0016\n\u0012ROUNDABOUT_EXIT_13\u0010;\u0012\u0016\n\u0012ROUNDABOUT_EXIT_14\u0010<\u0012\u0016\n\u0012ROUNDABOUT_EXIT_15\u0010=\u0012\u0016\n\u0012ROUNDABOUT_EXIT_16\u0010>\u0012\u0016\n\u0012ROUNDABOUT_EXIT_17\u0010?\u0012\u0016\n\u0012ROUNDABOUT_EXIT_18\u0010@\u00126\n2DOUBLE_CROSS_SMALL_ANGLE_RIGHT_HAND_STRAIGHT_AHEAD\u0010A\u00126\n2DOUBLE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD\u0010B\u0012'\n#THREE_CROSS_SMALL_ANGLE_TURN_LEFT_1\u0010C\u0012,\n(THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_1\u0010D\u0012", "(\n$THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_1\u0010E\u0012'\n#THREE_CROSS_SMALL_ANGLE_TURN_LEFT_2\u0010F\u0012'\n#THREE_CROSS_SMALL_ANGLE_TURN_LEFT_3\u0010G\u0012'\n#THREE_CROSS_SMALL_ANGLE_TURN_LEFT_4\u0010H\u0012(\n$THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_2\u0010I\u0012(\n$THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_3\u0010J\u0012(\n$THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_4\u0010K\u0012,\n(THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_2\u0010L\u0012(\n$THREE_CROSS_SMALL_ANGLE_TURN_RIGHT_5\u0010M\u0012(\n$THREE_CROSS_SMALL_", "ANGLE_TURN_RIGHT_6\u0010N\u0012,\n(THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_3\u0010O\u0012'\n#THREE_CROSS_SMALL_ANGLE_TURN_LEFT_5\u0010P\u0012'\n#THREE_CROSS_SMALL_ANGLE_TURN_LEFT_6\u0010Q\u00125\n1THREE_CROSS_SMALL_ANGLE_RIGHT_LEFT_STRAIGHT_AHEAD\u0010R\u0012,\n(THREE_CROSS_SMALL_ANGLE_STRAIGHT_AHEAD_4\u0010S\u00126\n2THREE_CROSS_SMALL_ANGLE_RIGHT_RIGHT_STRAIGHT_AHEAD\u0010T\u0012\u0014\n\u0010PASSING_POINTS_1\u0010U\u0012\u0014\n\u0010PASSING_POINTS_2\u0010V\u0012\u0014\n\u0010PASSING_POINTS_3\u0010W\u0012\u0014\n\u0010PASSING_POINTS_4\u0010X\u0012\u0014\n\u0010PAS", "SING_POINTS_5\u0010Y*J\n\nCallStatus\u0012\b\n\u0004IDLE\u0010\u0000\u0012\f\n\bINCOMING\u0010\u0001\u0012\u000b\n\u0007DIALING\u0010\u0002\u0012\u000b\n\u0007CALLING\u0010\u0003\u0012\n\n\u0006ENDING\u0010\u0004*:\n\nPlayStatus\u0012\u000b\n\u0007DEFAULT\u0010\u0000\u0012\b\n\u0004PLAY\u0010\u0001\u0012\b\n\u0004STOP\u0010\u0002\u0012\u000b\n\u0007LOADING\u0010\u0003*.\n\rMusicNameRoll\u0012\b\n\u0004ROLL\u0010\u0001\u0012\t\n\u0005PAUSE\u0010\u0002\u0012\b\n\u0004EXIT\u0010\u0003*)\n\u000bClockFormat\u0012\f\n\bFORMAT12\u0010\u0000\u0012\f\n\bFORMAT24\u0010\u0001*)\n\u000bAmPmDisplay\u0012\u000b\n\u0007DISPLAY\u0010\u0000\u0012\r\n\tNODISPLAY\u0010\u0001*'\n\u000bLanguageSet\u0012\u000b\n\u0007Chinese\u0010\u0000\u0012\u000b\n\u0007English\u0010\u0001*N\n\bThemeSet\u0012\u0010\n\fInvaildColor\u0010\u0000\u0012\r\n\tDeepColor\u0010\u0001\u0012\u000e\n\nLightColor\u0010\u0002\u0012\u0011\n\rReservedC", "olor\u0010\u0003*6\n\rHardKeyResult\u0012\u0010\n\fHARDKEY_FAIL\u0010\u0000\u0012\u0013\n\u000fHARDKEY_SUCCESS\u0010\u0001*e\n\u0016MediaNavigationalstate\u0012\u0019\n\u0015NAVITYPESTATE_INVALID\u0010\u0000\u0012\u0017\n\u0013NAVITYPESTATE_OPENN\u0010\u0001\u0012\u0017\n\u0013NAVITYPESTATE_CLOSE\u0010\u0002*L\n\u000bVehicleType\u0012\u0012\n\u000eVEHICLETYPE_EV\u0010\u0001\u0012\u0013\n\u000fVEHICLETYPE_HEV\u0010\u0002\u0012\u0014\n\u0010VEHICLETYPE_CONV\u0010\u0003*u\n\tVehicleID\u0012\u001a\n\u0016VEHICLEID_CLASSICMODEL\u0010\u0001\u0012\u001a\n\u0016VEHICLEID_DYNASTYMODEL\u0010\u0002\u0012\u0018\n\u0014VEHICLEID_DENZAMODEL\u0010\u0003\u0012\u0016\n\u0012VEHICLEID_SEAMODEL\u0010\u0004*£\u0001\n\u0010VehicleThemeType\u0012\u0015\n\u0011VEHICLEID_INV", "ALID\u0010\u0000\u0012\u0019\n\u0015VEHICLEID_CLASSICTYPE\u0010\u0001\u0012\u0018\n\u0014VEHICLEID_TECHNOTYPE\u0010\u0002\u0012\u0015\n\u0011VEHICLEID_FASHION\u0010\u0003\u0012\u0014\n\u0010VEHICLEID_SIMPLE\u0010\u0004\u0012\u0016\n\u0012VEHICLEID_RESERVED\u0010\u0005*[\n\u0014VehicleLightGearStat\u0012\u0016\n\u0012LIGHTSTATUS_NORMAL\u0010\u0001\u0012\u0014\n\u0010LIGHTSTATUS_NORX\u0010\u0002\u0012\u0015\n\u0011LIGHTSTATUS_TIMER\u0010\u0003*©\u0002\n\u0010VehicleLightGear\u0012\u000f\n\u000bLIGHTGEAR_1\u0010\u0001\u0012\u000f\n\u000bLIGHTGEAR_2\u0010\u0002\u0012\u000f\n\u000bLIGHTGEAR_3\u0010\u0003\u0012\u000f\n\u000bLIGHTGEAR_4\u0010\u0004\u0012\u000f\n\u000bLIGHTGEAR_5\u0010\u0005\u0012\u000f\n\u000bLIGHTGEAR_6\u0010\u0006\u0012\u000f\n\u000bLIGHTGEAR_7\u0010\u0007\u0012\u000f\n\u000bLIGHTGEAR_8\u0010\b\u0012\u000f\n\u000bLIGHTGEAR_9\u0010\t\u0012\u0010\n\fL", "IGHTGEAR_10\u0010\n\u0012\u0010\n\fLIGHTGEAR_11\u0010\u000b\u0012\u0010\n\fLIGHTGEAR_12\u0010\f\u0012\u0010\n\fLIGHTGEAR_13\u0010\r\u0012\u0010\n\fLIGHTGEAR_14\u0010\u000e\u0012\u0010\n\fLIGHTGEAR_15\u0010\u000f\u0012\u0010\n\fLIGHTGEAR_16\u0010\u0010*n\n\u000fVehicleNaviType\u0012\u0016\n\u0012VEHNAVITYPE_CLOSED\u0010\u0001\u0012\u0016\n\u0012VEHNAVITYPE_SIMPLE\u0010\u0002\u0012\u0015\n\u0011VEHNAVITYPE_SMALL\u0010\u0003\u0012\u0014\n\u0010VEHNAVITYPE_FULL\u0010\u0004*A\n\u000fVehicleAdasStat\u0012\u0017\n\u0013VEHADASSTAT_INVALID\u0010\u0000\u0012\u0015\n\u0011VEHNAVISTAT_VALID\u0010\u0001*´\u0002\n\u000eVehicleINSDisp\u0012\u0015\n\u0011DRIVINGINFO_CLOSE\u0010\u0000\u0012\u0017\n\u0013DRIVINGINFO_50KMAVE\u0010\u0001\u0012\u0016\n\u0012DRIVINGINFO_CUMENE\u0010\u0002\u0012\u0018\n\u0014DRIVI", "NGINFO_TIREPREE\u0010\u0003\u0012\u001b\n\u0017DRIVINGINFO_INSTANTFUEL\u0010\u0006\u0012\u0015\n\u0011DRIVINGINFO_RANGE\u0010\u0007\u0012\u001b\n\u0017DRIVINGINFO_FUELCONSUMP\u0010\b\u0012\u001c\n\u0018DRIVINGINFO_SINGLESTROKE\u0010\t\u0012\u0019\n\u0015DRIVINGINFO_AVERSPEED\u0010\n\u0012\u001a\n\u0016DRIVINGINFO_EXTERCHARG\u0010\u000b\u0012\u001a\n\u0016DRIVINGINFO_ENERGYFLOW\u0010\f*S\n\u0011VehicleDrivedInfo\u0012\u0015\n\u0011DRIVEDINFO_ENERGY\u0010\u0000\u0012\u0013\n\u000fDRIVEDINFO_ELEC\u0010\u0001\u0012\u0012\n\u000eDRIVEDINFO_OIL\u0010\u0002*z\n\u000fVehicleMileInfo\u0012\u0010\n\fTOTALMILEAGE\u0010\u0001\u0012\u0010\n\fMILEAGEINFO1\u0010\u0002\u0012\u0010\n\fMILEAGEINFO2\u0010\u0003\u0012\u000e\n\nMILEAGEHEV\u0010\u0004\u0012\r\n\tMILEAGEEV\u0010\u0005", "\u0012\u0012\n\u000eMILEAGEINVALID\u0010\u0006*b\n\u000fVehicleMileUnit\u0012\u0014\n\u0010MILEUNIT_INVALID\u0010\u0000\u0012\u000f\n\u000bMILEUNIT_KM\u0010\u0001\u0012\u0011\n\rMILEUNIT_MILE\u0010\u0002\u0012\u0015\n\u0011MILEUNIT_RESERVED\u0010\u0003*Ê\u0001\n\u0010VehicleMileValue\u0012\u0012\n\u000eTOTALMILEVALUE\u0010\u0000\u0012\u0014\n\u0010TOTALMILEINVALUE\u0010\u0001\u0012\u000e\n\nMILE1VALUE\u0010\u0002\u0012\u0010\n\fMILE1INVALUE\u0010\u0003\u0012\u000e\n\nMILE2VALUE\u0010\u0004\u0012\u0010\n\fMILE2INVALUE\u0010\u0005\u0012\u0010\n\fHEVMILEVALUE\u0010\u0006\u0012\u0012\n\u000eHEVMILEINVALUE\u0010\u0007\u0012\u000f\n\u000bEVMILEVALUE\u0010\b\u0012\u0011\n\rEVMILEINVALUE\u0010\t*O\n\u000fVehicleAccTimer\u0012\u0014\n\u0010ACCTIMER_INVALID\u0010\u0000\u0012\u0012\n\u000eACCTIMER_START\u0010\u0001\u0012\u0012\n\u000eACCTIMER_C", "LOSE\u0010\u0002*A\n\u0011VehicleSpeedLimit\u0012\u0016\n\u0012SPEEDLIMIT_INVALID\u0010\u0000\u0012\u0014\n\u0010SPEEDLIMIT_VALID\u0010\u0001*@\n\rElevationUnit\u0012\u0018\n\u0014ELEVATION_UNIT_METER\u0010\u0001\u0012\u0015\n\u0011ELEVATION_UNIT_KM\u0010\u0002*=\n\tSlopeUnit\u0012\u0015\n\u0011SLOPE_UNIT_DEGREE\u0010\u0001\u0012\u0019\n\u0015SLOPE_UNIT_PERCENTAGE\u0010\u0002*0\n\tSoundType\u0012\u0012\n\u000eSTANDARD_SOUND\u0010\u0001\u0012\u000f\n\u000bBRAND_SOUND\u0010\u0002*5\n\nIconDisply\u0012\u0013\n\u000fICON_DISPLY_OFF\u0010\u0000\u0012\u0012\n\u000eICON_DISPLY_ON\u0010\u0001*%\n\u0007Visible\u0012\r\n\tINVISIBLE\u0010\u0000\u0012\u000b\n\u0007VISIBLE\u0010\u0001*¡\u0003\n\nPlaySource\u0012\u0012\n\u000eSOURCE_DEFAULT\u0010\u0000\u0012\u0016\n\u0012SOURCE_LOCAL_MU", "SIC\u0010\u0001\u0012\u000f\n\u000bSOURCE_USB1\u0010\u0002\u0012\u000f\n\u000bSOURCE_USB2\u0010\u0003\u0012\r\n\tSOURCE_SD\u0010\u0004\u0012\u000e\n\nSOURCE_AUX\u0010\u0005\u0012\r\n\tSOURCE_BT\u0010\u0006\u0012\r\n\tSOURCE_FM\u0010\u0007\u0012\u0010\n\fSOURCE_VIDEO\u0010\b\u0012\u0010\n\fSOURCE_KOOWO\u0010\t\u0012\u0010\n\fSOURCE_KOALA\u0010\n\u0012\u0015\n\u0011SOURCE_TRIPARTITE\u0010\u000b\u0012\u0013\n\u000fSOURCE_HIMALAYA\u0010\f\u0012\u0011\n\rSOURCE_KOOGOO\u0010\r\u0012\u001a\n\u0016SOURCE_CLOUD_LISTENING\u0010\u000e\u0012\u0016\n\u0012SOURCE_CAR_KARAOKE\u0010\u000f\u0012\u0012\n\u000eSOURCE_SPOTIFY\u0010\u0010\u0012\u000e\n\nSOURCE_DAB\u0010\u0011\u0012\r\n\tSOURCE_AM\u0010\u0012\u0012\u0013\n\u000fSOURCE_CAR_PLAY\u0010\u001e\u0012\u0017\n\u0013SOURCE_ANDROID_AUTO\u0010\u001f*9\n\rPlayInterface\u0012\u0013\n\u000fFRONT_DESK_PL", "AY\u0010\u0001\u0012\u0013\n\u000fBACKGROUND_PLAY\u0010\u0002*<\n\u0012PhoneNumberVisible\u0012\u0013\n\u000fPHONE_INVISIBLE\u0010\u0000\u0012\u0011\n\rPHONE_VISIBLE\u0010\u0001*B\n\u0010PhoneTimeVisible\u0012\u0017\n\u0013CALL_TIME_INVISIBLE\u0010\u0000\u0012\u0015\n\u0011CALL_TIME_VISIBLE\u0010\u0001*3\n\tPhoneType\u0012\n\n\u0006NORMAL\u0010\u0000\u0012\u001a\n\u0016NAVIGATION_FULL_SCREEN\u0010\u0001*T\n\u0007MapType\u0012\u0014\n\u0010MAP_TYPE_INVALID\u0010\u0000\u0012\r\n\tLOCAL_MAP\u0010\u0001\u0012\u000f\n\u000bCARPLAY_MAP\u0010\u0002\u0012\u0013\n\u000fANDROIDAUTO_MAP\u0010\u0003*à\u0001\n\nInsUiState\u0012\u001b\n\u0017FULL_MAP_MODE_PENETRATE\u0010\u0001\u0012\u001f\n\u001bFULL_MAP_MODE_NOT_PENETRATE\u0010\u0002\u0012\u001c\n\u0018SAMLL_MAP_MODE_PENETRATE\u0010", "\u0003\u0012 \n\u001cSAMLL_MAP_MODE_NOT_PENETRATE\u0010\u0004\u0012\u001d\n\u0019SIMPLE_MAP_MODE_PENETRATE\u0010\u0005\u0012!\n\u001dSIMPLE_MAP_MODE_NOT_PENETRATE\u0010\u0006\u0012\u0012\n\u000eCLOSE_MAP_MODE\u0010\u0007*d\n\nTopTipUnit\u0012\u0010\n\fUNIT_INVALID\u0010\u0000\u0012\u000b\n\u0007UNIT_KM\u0010\u0001\u0012\u000e\n\nUNIT_MILES\u0010\u0002\u0012\n\n\u0006UNIT_M\u0010\u0003\u0012\u000e\n\nUNIT_YARDS\u0010\u0004\u0012\u000b\n\u0007UNIT_FT\u0010\u0005*á\u0001\n\u0011NavigationPopInfo\u0012\u000e\n\nCLOSE_NAVI\u0010\u0001\u0012\u0014\n\u0010CLOSE_SAMLL_NAVI\u0010\u0002\u0012\u0013\n\u000fCLOSE_FULL_NAVI\u0010\u0003\u0012\u0015\n\u0011CLOSE_SIMPLE_NAVI\u0010\u0004\u0012\u0013\n\u000fOPEN_NAVI_SMALL\u0010\u0005\u0012\u0012\n\u000eOPEN_NAVI_FULL\u0010\u0006\u0012\u0014\n\u0010OPEN_NAVI_SIMPLE\u0010\u0007\u0012\u0012\n\u000eMAP_NO", "T_ATTIVE\u0010\b\u0012\u0012\n\u000eMAP_NOT_AGREED\u0010\t\u0012\u0013\n\u000fMAP_NOT_SUPPORT\u0010\n*Ñ\u0001\n\tQueryType\u0012\u001b\n\u0017SETTING_THEME_CMD_QUERY\u0010\u0000\u0012 \n\u001cSETTING_HOTKEYNAVI_CMD_QUERY\u0010\u0001\u0012&\n\"SETTING_HOTKEYINSDISPLAY_CMD_QUERY\u0010\u0002\u0012 \n\u001cSETTING_HOTKEYADAS_CMD_QUERY\u0010\u0003\u0012!\n\u001dSETTING_HOTKEYTHEME_CMD_QUERY\u0010\u0004\u0012\u0018\n\u0014CHIME_SOUND_CMD_QUER\u0010\u0005*0\n\nOSDVisible\u0012\u0011\n\rOSD_INVISIBLE\u0010\u0000\u0012\u000f\n\u000bOSD_VISIBLE\u0010\u0001*R\n\u0014AdasAutoLearnVisible\u0012\u001d\n\u0019ADAS_AUTO_LEARN_INVISIBLE\u0010\u0000\u0012\u001b\n\u0017ADAS_AUTO_LEARN_VISIBLE\u0010\u0001*D\n", "\rAdasDebugMode\u0012\u0019\n\u0015ADAS_DEBUG_MODE_CLOSE\u0010\u0000\u0012\u0018\n\u0014ADAS_DEBUG_MODE_OPEN\u0010\u0001*C\n\u0016ScreenOrientationValue\u0012\u0014\n\u0010LANDSCAPE_SCREEN\u0010\u0000\u0012\u0013\n\u000fVERTICAL_SCREEN\u0010\u0001B,\n\u0018com.autolink.interactionB\u0010InteractionProto"};
        Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: com.autolink.interaction.InteractionProto.1
            @Override // com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor root) {
                Descriptors.FileDescriptor unused = InteractionProto.descriptor = root;
                return null;
            }
        };
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0], assigner);
        Descriptors.Descriptor descriptor2 = getDescriptor().getMessageTypes().get(0);
        internal_static_autolink_interaction_Query_descriptor = descriptor2;
        internal_static_autolink_interaction_Query_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor2, new String[]{"QueryValue"});
        Descriptors.Descriptor descriptor3 = getDescriptor().getMessageTypes().get(1);
        internal_static_autolink_interaction_Elevation_descriptor = descriptor3;
        internal_static_autolink_interaction_Elevation_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor3, new String[]{"ElevationValue", "ElevationUnit"});
        Descriptors.Descriptor descriptor4 = getDescriptor().getMessageTypes().get(2);
        internal_static_autolink_interaction_Slope_descriptor = descriptor4;
        internal_static_autolink_interaction_Slope_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor4, new String[]{"SlopeValue", "SlopeUnit"});
        Descriptors.Descriptor descriptor5 = getDescriptor().getMessageTypes().get(3);
        internal_static_autolink_interaction_BluetoothPhone_descriptor = descriptor5;
        internal_static_autolink_interaction_BluetoothPhone_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor5, new String[]{"PhoneDisplayIcon", "ContactName", "ContactNumber", "CallTime", "PVisible", "TimeVisible", "Type", "Status"});
        Descriptors.Descriptor descriptor6 = getDescriptor().getMessageTypes().get(4);
        internal_static_autolink_interaction_Music_descriptor = descriptor6;
        internal_static_autolink_interaction_Music_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor6, new String[]{"MusicDisplayIcon", "MediaText", "MStatus", "MVisible", "Roll", "MPlaySource", "MPlayInterface", "AlbumPath", "ArtistName"});
        Descriptors.Descriptor descriptor7 = getDescriptor().getMessageTypes().get(5);
        internal_static_autolink_interaction_MediaProgress_descriptor = descriptor7;
        internal_static_autolink_interaction_MediaProgress_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor7, new String[]{"CurrentProgress", "MaxProgress"});
        Descriptors.Descriptor descriptor8 = getDescriptor().getMessageTypes().get(6);
        internal_static_autolink_interaction_Clock_descriptor = descriptor8;
        internal_static_autolink_interaction_Clock_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor8, new String[]{"TimeFormat", "AmpmDisplay", "TimeHour", "TimeMinute", "TimeSecond"});
        Descriptors.Descriptor descriptor9 = getDescriptor().getMessageTypes().get(7);
        internal_static_autolink_interaction_Navigation_descriptor = descriptor9;
        internal_static_autolink_interaction_Navigation_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor9, new String[]{"MapType", "NextNavigationInformationIcon", "DestinationInfo", "TurnTipDigit", "TopTipUnit", "TurnNavigationInformationIcon", "NextNextRoadDist", "NextNextRoadUnit", "RemainingMilageValue", "RemainingMilage", "RemainingSecondValue", "ArriveTimeDay", "ArriveTimeHour", "ArriveTimeMinute"});
        Descriptors.Descriptor descriptor10 = getDescriptor().getMessageTypes().get(8);
        internal_static_autolink_interaction_InteractionData_descriptor = descriptor10;
        internal_static_autolink_interaction_InteractionData_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor10, new String[]{"BluetoothPhoneValue", "MusicValue", "ClockValue", "NavigationValue"});
        Descriptors.Descriptor descriptor11 = getDescriptor().getMessageTypes().get(9);
        internal_static_autolink_interaction_Language_descriptor = descriptor11;
        internal_static_autolink_interaction_Language_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor11, new String[]{"NCurrLang"});
        Descriptors.Descriptor descriptor12 = getDescriptor().getMessageTypes().get(10);
        internal_static_autolink_interaction_ResetOperation_descriptor = descriptor12;
        internal_static_autolink_interaction_ResetOperation_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor12, new String[]{"NResetOper"});
        Descriptors.Descriptor descriptor13 = getDescriptor().getMessageTypes().get(11);
        internal_static_autolink_interaction_ThemeSetting_descriptor = descriptor13;
        internal_static_autolink_interaction_ThemeSetting_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor13, new String[]{"NCurrTheme"});
        Descriptors.Descriptor descriptor14 = getDescriptor().getMessageTypes().get(12);
        internal_static_autolink_interaction_ChimeSetting_descriptor = descriptor14;
        internal_static_autolink_interaction_ChimeSetting_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor14, new String[]{"NCurrSoundType"});
        Descriptors.Descriptor descriptor15 = getDescriptor().getMessageTypes().get(13);
        internal_static_autolink_interaction_NaviSetting_descriptor = descriptor15;
        internal_static_autolink_interaction_NaviSetting_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor15, new String[]{"NMapType", "NCurrMedNaviStat"});
        Descriptors.Descriptor descriptor16 = getDescriptor().getMessageTypes().get(14);
        internal_static_autolink_interaction_HotKeySetting_descriptor = descriptor16;
        internal_static_autolink_interaction_HotKeySetting_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor16, new String[]{"NVehicleType", "NVehicleID", "NCurrThemeType", "NCurrLightStat", "NCurrLight", "NCurrNaviType", "NCurrAdasStat", "NCurrINSDisplay", "NCurrMileInfo", "NCurrMileUnit", "NCurrMileValue", "NCurrAccTimer", "NCurrSpeedLimit"});
        Descriptors.Descriptor descriptor17 = getDescriptor().getMessageTypes().get(15);
        internal_static_autolink_interaction_UpdateSettingThemeReq_descriptor = descriptor17;
        internal_static_autolink_interaction_UpdateSettingThemeReq_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor17, new String[]{"NCurrThemeInfoReq"});
        Descriptors.Descriptor descriptor18 = getDescriptor().getMessageTypes().get(16);
        internal_static_autolink_interaction_UpdateHotKeyNaviReq_descriptor = descriptor18;
        internal_static_autolink_interaction_UpdateHotKeyNaviReq_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor18, new String[]{"NCurrNaviType"});
        Descriptors.Descriptor descriptor19 = getDescriptor().getMessageTypes().get(17);
        internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_descriptor = descriptor19;
        internal_static_autolink_interaction_UpdateHotKeyInsDisplayReq_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor19, new String[]{"NCurrAdasStatReq"});
        Descriptors.Descriptor descriptor20 = getDescriptor().getMessageTypes().get(18);
        internal_static_autolink_interaction_UpdateHotKeyAdasReq_descriptor = descriptor20;
        internal_static_autolink_interaction_UpdateHotKeyAdasReq_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor20, new String[]{"NCurrAdasStatReq"});
        Descriptors.Descriptor descriptor21 = getDescriptor().getMessageTypes().get(19);
        internal_static_autolink_interaction_UpdateHotKeyThemeReq_descriptor = descriptor21;
        internal_static_autolink_interaction_UpdateHotKeyThemeReq_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor21, new String[]{"NCurrHotKeyThemeTypeReq"});
        Descriptors.Descriptor descriptor22 = getDescriptor().getMessageTypes().get(20);
        internal_static_autolink_interaction_InsUiChangeReq_descriptor = descriptor22;
        internal_static_autolink_interaction_InsUiChangeReq_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor22, new String[]{"NCurrInsUiReq"});
        Descriptors.Descriptor descriptor23 = getDescriptor().getMessageTypes().get(21);
        internal_static_autolink_interaction_MapFocus_descriptor = descriptor23;
        internal_static_autolink_interaction_MapFocus_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor23, new String[]{"NCurrMapType"});
        Descriptors.Descriptor descriptor24 = getDescriptor().getMessageTypes().get(22);
        internal_static_autolink_interaction_MapState_descriptor = descriptor24;
        internal_static_autolink_interaction_MapState_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor24, new String[]{"MCurrMapType", "NavigationPopInfo"});
        Descriptors.Descriptor descriptor25 = getDescriptor().getMessageTypes().get(23);
        internal_static_autolink_interaction_InstrumentTest_descriptor = descriptor25;
        internal_static_autolink_interaction_InstrumentTest_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor25, new String[]{"OsdVisible", "AdasAutoLearnVisible", "AdasDebugMode"});
        Descriptors.Descriptor descriptor26 = getDescriptor().getMessageTypes().get(24);
        internal_static_autolink_interaction_ChimeSettingReq_descriptor = descriptor26;
        internal_static_autolink_interaction_ChimeSettingReq_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor26, new String[]{"MCurrSoundType"});
        Descriptors.Descriptor descriptor27 = getDescriptor().getMessageTypes().get(25);
        internal_static_autolink_interaction_ScreenOrientationSetting_descriptor = descriptor27;
        internal_static_autolink_interaction_ScreenOrientationSetting_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor27, new String[]{"MCurrScreenOrientation"});
        Descriptors.Descriptor descriptor28 = getDescriptor().getMessageTypes().get(26);
        internal_static_autolink_interaction_VersatileThemeSetting_descriptor = descriptor28;
        internal_static_autolink_interaction_VersatileThemeSetting_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor28, new String[]{"MCurrVersatileTheme"});
        Descriptors.Descriptor descriptor29 = getDescriptor().getMessageTypes().get(27);
        internal_static_autolink_interaction_OverseasMapSupplier_descriptor = descriptor29;
        internal_static_autolink_interaction_OverseasMapSupplier_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor29, new String[]{"MOverseasMapSupplier"});
        Descriptors.Descriptor descriptor30 = getDescriptor().getMessageTypes().get(28);
        internal_static_autolink_interaction_NaviTbtInfo_descriptor = descriptor30;
        internal_static_autolink_interaction_NaviTbtInfo_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor30, new String[]{"TbtMapType", "TbtNextNavigationInformationIcon", "TbtDestinationInfo", "TbtTurnTipDigit", "TbtTurnTipUnit", "TbtTurnNavigationInformationIcon", "TbtNextNextRoadDist", "TbtNextNextRoadDistUnit", "TbtRemainingMilageValue", "TbtRemainingMilageUnit", "TbtRemainingSecondValue", "TbtArriveTimeDay", "TbtArriveTime", "TbtMultipleText", "TbtMultipleTextOne", "TbtMultipleTextTwo"});
        Descriptors.Descriptor descriptor31 = getDescriptor().getMessageTypes().get(29);
        internal_static_autolink_interaction_NaviInstallState_descriptor = descriptor31;
        internal_static_autolink_interaction_NaviInstallState_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor31, new String[]{"MNaviInstallState"});
        Descriptors.Descriptor descriptor32 = getDescriptor().getMessageTypes().get(30);
        internal_static_autolink_interaction_CpaaConnectState_descriptor = descriptor32;
        internal_static_autolink_interaction_CpaaConnectState_fieldAccessorTable = new GeneratedMessage.FieldAccessorTable(descriptor32, new String[]{"MCpaaConnectState"});
    }
}
