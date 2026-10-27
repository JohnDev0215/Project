package com.ts.lib.cluster.adapter.someip;

/* JADX INFO: loaded from: classes.dex */
class SomeIpConstants {
    SomeIpConstants() {
    }

    static class MsgType {
        public static final int MUSIC = 4097;
        public static final int NAVI = 4100;
        public static final int NAVI_REQ = 8196;
        public static final int PHONE = 4099;
        public static final int RADIO = 4098;

        MsgType() {
        }
    }

    static class SubTypeMusic {
        public static final int MUSIC_ID3 = 0;
        public static final int MUSIC_LIST = 5;
        public static final int MUSIC_LOAD_STATUS = 8;
        public static final int MUSIC_LYRICS = 7;
        public static final int MUSIC_PLAY_ACTION = 2;
        public static final int MUSIC_PLAY_FAVORITE = 4;
        public static final int MUSIC_PLAY_MODE = 3;
        public static final int MUSIC_PLAY_STATUS = 1;
        public static final int MUSIC_PLAY_TIME = 6;

        SubTypeMusic() {
        }
    }

    static class SubTypeRadio {
        public static final int CHANNEL_STATE = 1;
        public static final int FAST_TUNER_MSG = 0;
        public static final int RADIO_LIST = 2;
        public static final int RADIO_PLAY_STATE = 5;
        public static final int RADIO_SINGLE_STATE = 4;

        SubTypeRadio() {
        }
    }

    static class SubTypePhone {
        public static final int CALL_DURATION = 3;
        public static final int CALL_LOGS = 0;
        public static final int CALL_LOG_SYNC_STATE = 1;
        public static final int CALL_STATE = 2;

        SubTypePhone() {
        }
    }

    static class SubTypeNavi {
        public static final int COMPASS = 6;
        public static final int DISPLAY_AREA_AND_RESULT = 5;
        public static final int ELECTRONIC_EYE_INFO = 4;
        public static final int GPS_SIGNAL = 7;
        public static final int IS_SHOW_ON_CLUSTER = 2;
        public static final int LANE_INFO = 0;
        public static final int ROAD_INFO = 1;
        public static final int TOTAL_NAVI_INFOR = 3;

        SubTypeNavi() {
        }
    }

    static class SubTypeNaviReq {
        public static final int REQUEST_NAVI_AREA_DISPLAY = 1;
        public static final int REQUEST_PERSPECTIVE = 0;

        SubTypeNaviReq() {
        }
    }

    static class Key {
        public static final String ANGLE = "Angle";
        public static final String ARRIVAL_TIME = "ArrivalTime";
        public static final String AST_STATE = "AstState";
        public static final String BAND = "Band";
        public static final String CALL_DURATION = "CallDuration";
        public static final String CALL_STATE = "CallState";
        public static final String CAMERA_TYPE = "CameraType";
        public static final String CONTACT_PICTURE = "ContactPicture";
        public static final String CONTENT = "content";
        public static final String CURRENT_FREQ = "CurrentFreq";
        public static final String CURRENT_PALYING_TIME = "CurrentPlayingTime";
        public static final String DISPLAY_CLUSTER = "DisplayCluster";
        public static final String DISTANCE_UNIT = "DistanceUint";
        public static final String HAS_FOCUS = "HasFocus";
        public static final String INDEX = "Index";
        public static final String INTERSECTION_ZOOM_STATUS = "IntersectionZoomStatus";
        public static final String IS_CHANDE_ALBUM_PIC = "IsChangeAlbumPic";
        public static final String LANE_ICON_ID = "LaneIconId";
        public static final String LANE_ID = "LaneId";
        public static final String LANE_INFO = "LaneInfo";
        public static final String LYRICS = "Lyrics";
        public static final String MEDIA_VOLUME = "MediaVolume";
        public static final String MSG_TYPE = "msgtype";
        public static final String MUSIC_LOAD_STATE = "MusicLoadingStatus";
        public static final String MUSIC_PLAY_STATUS = "MusicPlayStatus";
        public static final String NAME = "Name";
        public static final String NAVI_DISPLAY_AREA = "NaviDisplayArea";
        public static final String NAVI_DISPLAY_AREA_RESULT = "NaviDisplayAreaResult";
        public static final String NAVI_FRONT_DESK_STATUS = "NaviFrontDeskStatus";
        public static final String NAVI_GPS_SIGNAL = "GPSWeakSignal";
        public static final String NAVI_STATUS = "NaviStatus";
        public static final String NEXT_NAVI_ACTION_PROGBAR = "NextNaviActionProgbar";
        public static final String NUMBER = "Number";
        public static final String ORIGIN = "Origin";
        public static final String PERSPECTIVE = "Perspective";
        public static final String PERSPECTIVE_RESULT = "PerspectiveResult";
        public static final String PHONE_TYPE = "PhoneType";
        public static final String RADIO_LOAD_STATE = "RadioSignalStatus";
        public static final String RADIO_PLAY_STATUS = "RadioPlayStatus";
        public static final String REQUEST_DISPLAY_NAVI_AREA = "RequestDisplayNaviArea";
        public static final String REQUEST_NAVI_AREA_DISPLAY = "RequestNaviAreaDisplay";
        public static final String ROAD_ICON = "RoadIcon";
        public static final String ROAD_INFO = "RoadInfo";
        public static final String ROAD_TYPE = "RoadType";
        public static final String ROSD_NAME = "RoadName";
        public static final String SEG_REMAIN_DIS = "SegRemainDis";
        public static final String SONG_AGE = "SongAges";
        public static final String SONG_ALBUM = "SongAlbum";
        public static final String SONG_ALBUM_PICTURE = "SongAlbumPicture";
        public static final String SONG_ARTIST = "SongArtist";
        public static final String SONG_INDEX = "SongIndex";
        public static final String SONG_NAME = "SongName";
        public static final String SONG_SCHOOLS = "SongSchools";
        public static final String SPEEDING_INFO = "SpeedingInfo";
        public static final String STATION_NAME = "StationName";
        public static final String SUB_TYPE = "subtype";
        public static final String TIME_LEFT = "TimeLeft";
        public static final String TOTAL_DISTANCE = "TotalDistance";
        public static final String TOTAL_PLAY_TIME = "TotalPlayTime";
        public static final String WARNING_MESSAGE = "WarningMessage";

        Key() {
        }
    }

    static class StateOrigin {
        public static final int BLUETOOTH = 6;
        public static final int CARLIFE = 14;
        public static final int CARPLAY = 13;
        public static final int INTERN_MUSIC_STORAGE = 1;
        public static final int IPOD_1 = 4;
        public static final int IPOD_2 = 5;
        public static final int NEWS = 10;
        public static final int NO_SOURCE = 0;
        public static final int ONLINE_MUSIC = 7;
        public static final int ONLINE_RADIO = 8;
        public static final int ONLINE_VOICE = 9;
        public static final int SCREEN = 15;
        public static final int USB_1 = 2;
        public static final int USB_1_VIDEO = 11;
        public static final int USB_2 = 3;
        public static final int USB_2_VIDEO = 12;

        StateOrigin() {
        }
    }

    static class StateMediaState {
        public static final String MEDIA_STATE_PAUSE = "Pause";
        public static final String MEDIA_STATE_START = "Play";

        StateMediaState() {
        }
    }

    static class StateRadioBand {
        public static final int RADIO_BAND_AM = 0;
        public static final int RADIO_BAND_FM = 1;

        StateRadioBand() {
        }
    }

    static class StateAstState {
        public static final String RADIO_AM_SEARCHING = "AmSearching";
        public static final String RADIO_FM_SEARCHING = "FmSearching";
        public static final String RADIO_STATE_NORMAL = "Normal";

        StateAstState() {
        }
    }

    static class StatePhoneType {
        public static final int BCALL = 3;
        public static final int BTCALL = 1;
        public static final int ECALL = 2;
        public static final int NOT_ACTIVE = 0;

        StatePhoneType() {
        }
    }

    static class StatePhoneState {
        public static final int CALLEND = 4;
        public static final int CALLING = 3;
        public static final int DIALING = 2;
        public static final int INCOMING = 1;
        public static final int MISSCALL = 5;
        public static final int NORMAL = 0;

        StatePhoneState() {
        }
    }

    static class StateDistanceUnit {
        public static final int KILONETER = 2;
        public static final int MILE = 3;
        public static final int NETER = 1;
        public static final int NOT_USED = 0;

        StateDistanceUnit() {
        }
    }

    static class NaviDisplayArea {
        public static final int CLOSED = 0;
        public static final int THEME_ONE_CENTER = 1;
        public static final int THEME_ONE_LEFT = 2;
        public static final int THEME_THREE = 4;
        public static final int THEME_TWO = 3;

        NaviDisplayArea() {
        }
    }
}
