package com.ts.lib.cluster.common.data;

/* JADX INFO: loaded from: classes2.dex */
public class Constants {

    public static class BtCallState {
        public static final int CALL_STATE_ACTIVE = 0;
        public static final int CALL_STATE_ALERTING = 3;
        public static final int CALL_STATE_DIALING = 2;
        public static final int CALL_STATE_HELD = 1;
        public static final int CALL_STATE_HELD_BY_RESPONSE_AND_HOLD = 6;
        public static final int CALL_STATE_INCOMING = 4;
        public static final int CALL_STATE_TERMINATED = 7;
        public static final int CALL_STATE_WAITING = 5;
    }

    public static class BtConnectState {
        public static final int BT_CONNECTED = 2;
        public static final int BT_CONNECTING = 1;
        public static final int BT_DISCONNECTED = 0;
        public static final int BT_DISCONNECTING = 3;
    }

    public static class CallType {
        public static final int BTCALL = 3;
        public static final int CPCALL = 2;
        public static final int EBCALL = 1;
    }

    public static class CarplayCallState {
        public static final int CALL_STATE_ACTIVE = 4;
        public static final int CALL_STATE_CONNECTING = 3;
        public static final int CALL_STATE_DISCONNECTED = 0;
        public static final int CALL_STATE_DISCONNECTING = 6;
        public static final int CALL_STATE_HELD = 5;
        public static final int CALL_STATE_RINGING = 2;
        public static final int CALL_STATE_SENDING = 1;
        public static final int CALL_STATE_UNKNOW = -1;
    }

    public static class EbCallState {
        public static final int EBCALL_ANSWER_CALLS = 3;
        public static final int EBCALL_CALL_OVER = 6;
        public static final int EBCALL_CONNECTING = 2;
        public static final int EBCALL_HANG_UP = 5;
        public static final int EBCALL_INCOMING = 1;
        public static final int EBCALL_NOT_ACTIVE = 0;
        public static final int EBCALL_ON_THE_PHONE = 4;
    }

    public static class EbCallType {
        public static final int CALL_TYPE_BCALL = 1;
        public static final int CALL_TYPE_ECALL = 0;
    }

    public static class InternetRadioType {
        public static final int INTERNET_RADIO_TYPE_LIVE = 0;
        public static final int INTERNET_RADIO_TYPE_REPLAY = 1;
    }

    public static class Key {
        public static final String BT_CONNECT_STATE = "BtConnectState";
        public static final String CALL_ACTIVIE_TIME = "CallActiveTime";
        public static final String CALL_INDEX = "CallIndex";
        public static final String CALL_NAME = "CallName";
        public static final String CALL_NUM = "CallNum";
        public static final String CALL_PHOTO = "CallPhoto";
        public static final String CALL_PHOTO_URL = "CallPhotoUrl";
        public static final String CALL_STATE = "CallState";
        public static final String CALL_TYPE = "PhoneType";
        public static final String CLUSTER_AVAILABLE_AREAS = "ClusterAvailableAreas";
        public static final String CLUSTER_DRIVE_MODE_TYPE = "ClusterDriveModeType";
        public static final String CLUSTER_OPENED_AREAS = "ClusterOpenedAreas";
        public static final String CLUSTER_THEME_TYPE = "ClusterThemeType";
        public static final String DATA_TYPE = "DataType";
        public static final String EBCALL_TYPE = "EbcallType";
        public static final String INTERNET_RADIO_TYPE = "InternetRadioType";
        public static final String MEDIA_COVER = "MediaCover";
        public static final String MEDIA_COVER_URI = "MediaCoverUri";
        public static final String MEDIA_CUR_TIME = "MediaCurTime";
        public static final String MEDIA_LOAD_STATUS = "IsLoading";
        public static final String MEDIA_LYRICS = "MediaLyrics";
        public static final String MEDIA_NAME = "MediaName";
        public static final String MEDIA_SINGER = "MediaSinger";
        public static final String MEDIA_STATE = "MediaState";
        public static final String MEDIA_TOTAL_TIME = "MediaTotalTime";
        public static final String MUSIC_SOURCE = "MusicSource";
        public static final String NAVI_ARRIVAL_TIME = "NaviArivalTime";
        public static final String NAVI_CROSSING_STATUS = "NaviCrossingStatus";
        public static final String NAVI_DISTANCE_TO_DEST = "NaviDistanceToDest";
        public static final String NAVI_DISTANCE_TO_DEST_CP = "NaviDistanceToDestCp";
        public static final String NAVI_DISTANCE_TO_DEST_UNIT = "NaviDistanceToDestUnit";
        public static final String NAVI_GPS_SIGNAL = "NaviGpsSignal";
        public static final String NAVI_ICON_ELECTRONIC_TYPE = "NaviIconElectronicType";
        public static final String NAVI_ICON_LANE_INFO = "NaviIconLaneInfo";
        public static final String NAVI_ICON_LANE_SWITCH = "NaviIconLaneSwitch";
        public static final String NAVI_ICON_ROAD_SAFETY_TYPE = "NaviIconRoadSafetyType";
        public static final String NAVI_ICON_TBT = "NaviIconTbt";
        public static final String NAVI_NEXT_DISTANCE_UNIT = "NaviNextDistanceUnit";
        public static final String NAVI_NEXT_ROAD_DISTANCE = "NaviNextRoadDistance";
        public static final String NAVI_NEXT_ROAD_DISTANCE_CP = "NaviNextRoadDistanceCp";
        public static final String NAVI_NEXT_ROAD_NAME = "NaviNextRoadName";
        public static final String NAVI_NEXT_ROAD_PRECENT = "NaviNextRoadPrecent";
        public static final String NAVI_ROAD_TYPE = "NaviRoadType";
        public static final String NAVI_SOURCE = "NaviSource";
        public static final String NAVI_SPEED_LIMIT = "NaviSpeedLimit";
        public static final String NAVI_STATE = "NaviState";
        public static final String NAVI_TBT_SWITCH = "NaviTbtSwitch";
        public static final String NAVI_TO_DEST_TIME = "NaviToDestTime";
        public static final String RADIO_TYPE = "RadioType";
    }

    public static class MediaState {
        public static final int MEDIA_STATE_ABNORMAL = 3;
        public static final int MEDIA_STATE_LOADING = 2;
        public static final int MEDIA_STATE_PAUSE = 0;
        public static final int MEDIA_STATE_START = 1;
        public static final int MEDIA_STATE_UNKNOW = -1;
    }

    public static class MusicSource {
        public static final int BLUETOOTH = 1;
        public static final int CARLIFE = 5;
        public static final int CARPLAY = 4;
        public static final int NONE = 0;
        public static final int ONLINE = 3;
        public static final int ONLINE_RADIO = 7;
        public static final int RADIO = 6;
        public static final int USB = 2;
    }

    public static class NaviDistanceUnit {
        public static final int UNIT_FT = 4;
        public static final int UNIT_KM = 0;
        public static final int UNIT_M = 2;
        public static final int UNIT_MILES = 1;
        public static final int UNIT_YARDS = 3;
    }

    public static class NaviLaneInforSwitch {
        public static final int NAVI_LANE_CLOSE = 1;
        public static final int NAVI_LANE_OPEN = 2;
        public static final int NAVI_LANE_UNKNOW = 0;
    }

    public static class NaviSource {
        public static final int NAVI_TYPE_AMAP = 1;
        public static final int NAVI_TYPE_CARPLAY = 3;
        public static final int NAVI_TYPE_NONE = 0;
        public static final int NAVI_TYPE_TENCENT = 2;
        public static final int NAVI_TYPE_UNKNOW = -1;
    }

    public static class NaviState {
        public static final int NAVIGATING = 3;
        public static final int NAVIGATION_OVER = 4;
        public static final int NAVIGATION_PLANING = 1;
        public static final int NAVIGATION_START = 2;
        public static final int NOT_NAVIGATED = 0;
    }

    public static class NaviTbtSwitch {
        public static final int NAVI_TBT_CLOSE = 0;
        public static final int NAVI_TBT_OPEN = 1;
    }

    public static class NaviType {
        public static final int CARPLAY_NAVI = 2;
        public static final int LOCAL_NAVI = 1;
    }

    public static class RadioClassify {
        public static final int LOCAL_RADIO = 1;
        public static final int ONLINE_RADIO = 2;
    }

    public static class RadioType {
        public static final int RADIO_TYPE_AM = 0;
        public static final int RADIO_TYPE_FM = 1;
    }

    public static class Command {
        public static final int ALL_VALID_CMDS = 127;
        public static final int BLUETOOTH = 4;
        public static final int CALL = 8;
        public static final int INVALID = 0;
        public static final int MUSIC = 1;
        public static final int NAVI = 16;
        public static final int POST_CLUSTER = 32;
        public static final int RADIO = 2;
        public static final int REQUEST_CLUSTER = 64;
        private static int[] cmdList = {1, 2, 4, 8, 16, 32, 64};

        public static boolean isAvailableCmd(int command) {
            if (command == 1 || command == 2 || command == 4 || command == 8 || command == 16 || command == 32 || command == 64) {
                return true;
            }
            return false;
        }

        public static int[] getCmdList() {
            return (int[]) cmdList.clone();
        }
    }

    public static class DataType {
        public static final int BT_CALL_ACTIVE_TIME = 23;
        public static final int BT_CALL_INFO = 11;
        public static final int BT_CALL_STATE = 12;
        public static final int BT_CONNECT_STATE = 10;
        public static final int CARPLAY_CALL_ACTIVE_TIME = 25;
        public static final int CARPLAY_CALL_INFO = 18;
        public static final int CARPLAY_CALL_STATE = 19;
        public static final int CHANGE_CLUSTER_AREAS = 22;
        public static final int CLUSTER_STATUS = 20;
        public static final int EB_CALL_ACTIVE_TIME = 24;
        public static final int EB_CALL_INFO = 13;
        public static final int EB_CALL_STATE = 14;
        public static final int INTERNET_RADIO_INFO = 7;
        public static final int INTERNET_RADIO_PLAYING_TIME = 8;
        public static final int INTERNET_RADIO_STATE = 9;
        public static final int INVALID_INFO = 0;
        public static final int MUSIC_INFO = 1;
        public static final int MUSIC_LYRICS = 26;
        public static final int MUSIC_PLAYING_TIME = 2;
        public static final int MUSIC_RADIO_LOAD_STATUS = 27;
        public static final int MUSIC_SOURCE = 4;
        public static final int MUSIC_STATE = 3;
        public static final int NAVI_CARPLAY_INFO = 17;
        public static final int NAVI_GPS_SIGNAL = 28;
        public static final int NAVI_INFO = 16;
        public static final int NAVI_SWITCH = 15;
        public static final int RADIO_INFO = 5;
        public static final int RADIO_STATE = 6;
        public static final int REQ_CLUSTER = 21;

        public static boolean isAvailableType(int value) {
            return value > 0 && value <= 22;
        }
    }
}
