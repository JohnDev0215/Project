package com.ts.lib.cluster.adapter;

/* JADX INFO: loaded from: classes.dex */
public class ClusterConstants {
    public static final short DISTANCE_END_MARK_BYTE_1 = 41;
    public static final short DISTANCE_MAX_LENGTH = 64;
    public static final short NAME_END_MARK_BYTE_1 = 61;
    public static final short NAME_END_MARK_BYTE_2 = 62;
    public static final short NAME_END_MARK_BYTE_3 = 63;
    public static final short NUM_0 = 0;
    public static final short NUM_1 = 1;
    public static final short NUM_16 = 16;
    public static final short NUM_2 = 2;
    public static final short NUM_20 = 20;
    public static final short NUM_21 = 21;
    public static final short NUM_24 = 24;
    public static final short NUM_3 = 3;
    public static final short NUM_4 = 4;
    public static final short NUM_40 = 40;
    public static final short NUM_5 = 5;
    public static final short NUM_6 = 6;
    public static final short NUM_7 = 7;
    public static final short NUM_8 = 8;
    public static final short NUM_9 = 9;
    public static final Integer[] PROPIDS = {Integer.valueOf(TypesHalProperties.IVI_ID_CLUSTER_BITMAP), Integer.valueOf(TypesHalProperties.IVI_ID_CLUSTER_MEDIA), Integer.valueOf(TypesHalProperties.IVI_ID_CLUSTER_FULL_MAP_REQ), Integer.valueOf(TypesHalProperties.IVI_ID_CLUSTER_FULL_MAP_RESP)};

    public enum BitMapType {
        UNKNOWN,
        Meida,
        Radio,
        Phone
    }

    public enum IviState {
        NO_MEDIA,
        RADIO_OPEN,
        MUSIC_OPEN,
        OTHER_OPEN,
        MEDIA_INTERRUPT,
        NO_PHONE,
        CALL_CAME,
        PHONE_INFO_INTERRUPT,
        NO_NAVI_INFO,
        NAVI_INFO_INPUT,
        NAVI_INFO_INTERRUPT
    }

    public class TypesHalProperties {
        private static final int ID_CLUSTER_BITMAP = 2560;
        public static final int ID_CLUSTER_FULL_MAP_REQ = 2562;
        private static final int ID_CLUSTER_FULL_MAP_RESP = 2563;
        private static final int ID_CLUSTER_MEDIA = 2561;
        public static final int IVI_ID_CLUSTER_BITMAP = 554699264;
        public static final int IVI_ID_CLUSTER_FULL_MAP_REQ = 560990722;
        public static final int IVI_ID_CLUSTER_FULL_MAP_RESP = 560990723;
        public static final int IVI_ID_CLUSTER_MEDIA = 560990721;

        public TypesHalProperties() {
        }
    }

    public class FormatOfBitMapData {
        public FormatOfBitMapData() {
        }
    }

    public class FormatOfTextData {
        public static final byte END_MARK = -1;
        public static final short FORMAT_OF_TEXT_DATA_MAX_BYTES_LENGTH = 64;
        public static final short MAX_TIME_BYTES_LENGTH = 2;

        public FormatOfTextData() {
        }

        public class FmData {
            public static final byte FM_DATA = 1;
            public static final byte FREQUENCY_SATE = 16;

            public FmData() {
            }

            public class RadioStatus {
                public static final byte REACH = 1;
                public static final byte UNREACH = 0;

                public RadioStatus() {
                }
            }

            public class HaveCover {
                public static final byte EXIST_COVER = 1;
                public static final byte INEXIST_COVER = 0;

                public HaveCover() {
                }
            }
        }

        public class AmData {
            public static final byte AM_DATA = 2;
            public static final byte FREQUENCY_SATE = 16;

            public AmData() {
            }

            public class RadioStatus {
                public static final byte REACH = 1;
                public static final byte UNREACH = 0;

                public RadioStatus() {
                }
            }

            public class HaveCover {
                public static final byte EXIST_COVER = 1;
                public static final byte INEXIST_COVER = 0;

                public HaveCover() {
                }
            }
        }

        public class MediaData {
            public static final byte ARTIST_NAME = 32;
            public static final byte MEDIA_DATA = 3;
            public static final byte MEDIA_DATA_INFO = 48;
            public static final short MEDIA_INFO_MAX_NAME_BYTES_LENGTH = 60;
            public static final byte MEDIA_NAME = 16;

            public MediaData() {
            }

            public class MediaPlayStatus {
                public static final byte INVALID = 0;
                public static final byte LOADING = 3;
                public static final byte NO_SOURCE = 4;
                public static final byte PLAY = 1;
                public static final byte SUSPENDED = 2;

                public MediaPlayStatus() {
                }
            }

            public class HaveCover {
                public static final byte EXIST_COVER = 16;
                public static final byte INEXIST_COVER = 0;

                public HaveCover() {
                }
            }

            public class MediaSource {
                public static final byte BLUETOOTH = 2;
                public static final byte CARLIFE = 4;
                public static final byte CARPLAY = 3;
                public static final byte OTHERS = 0;
                public static final byte USB = 1;

                public MediaSource() {
                }
            }
        }

        public class PhoneData {
            public static final byte CONTACE_NAME = 48;
            public static final byte PHONE_DATA = 4;
            public static final byte PHONE_INFO = 16;
            public static final byte PHONE_NUMBER = 32;

            public PhoneData() {
            }

            public class PhoneSource {
                public static final byte BTPHONE = 0;
                public static final byte CARLIFE = 2;
                public static final byte CARPLAY = 1;

                public PhoneSource() {
                }
            }

            public class HavePicture {
                public static final byte HAVE_PICTURE = 1;
                public static final byte NON_PICTURE = 0;

                public HavePicture() {
                }
            }

            public class PhoneStatus {
                public static final byte CALLING = 1;
                public static final byte HANG_UP = 4;
                public static final byte INCOMING = 2;
                public static final byte NO_STATE = 0;
                public static final byte ON_A_CALL = 3;

                public PhoneStatus() {
                }
            }
        }

        public class NaviData {
            public static final byte DISTANCE_INFO = 64;
            public static final short DISTANCE_INFO_MAX_BYTES_LENGTH = 20;
            public static final byte NAVI_ALARM = 16;
            public static final byte NAVI_DATA = 5;
            public static final byte NAVI_INFO = 32;
            public static final byte NEXT_INTERSECTION_NAME = 48;
            public static final short ROAD_DIRECTION_1 = 13;
            public static final short ROAD_DIRECTION_2 = 14;
            public static final short ROAD_DIRECTION_3 = 15;
            public static final short ROAD_DIRECTION_4 = 16;
            public static final short ROAD_DIRECTION_5 = 17;
            public static final short ROAD_DIRECTION_6 = 18;
            public static final short ROAD_DIRECTION_7 = 19;
            public static final short ROAD_DIRECTION_8 = 20;

            public NaviData() {
            }

            public class GuidanceViewDisplay {
                public static final byte DISPLAY = 1;
                public static final byte NOT_DISPLAY = 0;

                public GuidanceViewDisplay() {
                }
            }

            public class NextDistanceUnit {
                public static final byte UNIT_FT = 80;
                public static final byte UNIT_KM = 16;
                public static final byte UNIT_M = 48;
                public static final byte UNIT_MILES = 32;
                public static final byte UNIT_YARDS = 64;

                public NextDistanceUnit() {
                }
            }

            public class DistanceToDestUnit {
                public static final byte UNIT_FT = 5;
                public static final byte UNIT_KM = 1;
                public static final byte UNIT_M = 3;
                public static final byte UNIT_MILES = 2;
                public static final byte UNIT_YARDS = 4;

                public DistanceToDestUnit() {
                }
            }
        }

        public class NaviAndMediaStatus {
            public static final byte NAVI_AND_MEDIA_STATUS = 6;

            public NaviAndMediaStatus() {
            }

            public class MultimediaState {
                public static final byte MEDIA_INTERRUPT = 16;
                public static final byte MUSIC_OPEN = 2;
                public static final byte NO_MEDIA = 0;
                public static final byte OTHER_OPEN = 3;
                public static final byte RADIO_OPEN = 1;

                public MultimediaState() {
                }
            }

            public class PhoneState {
                public static final byte CALL_CAME = 1;
                public static final byte NO_PHONE = 0;
                public static final byte PHONE_INFORMATION_INTERRUPT = 16;

                public PhoneState() {
                }
            }

            public class NaviState {
                public static final byte NAVIGATION_INFORMATION_INPUT = 1;
                public static final byte NAVIGATION_INFORMATION_INTERRUPT = 16;
                public static final byte NO_NAVIGATION_INFORMATION = 0;

                public NaviState() {
                }
            }
        }
    }

    public class ReqFullMapData {
        public ReqFullMapData() {
        }

        public class Sender {
            public static final byte CLUSTER = 0;
            public static final byte IVI = 1;

            public Sender() {
            }
        }

        public class CurrentMode {
            public static final byte FULL = 1;
            public static final byte ORG = 0;

            public CurrentMode() {
            }
        }

        public class DesiredMode {
            public static final byte FULL = 1;
            public static final byte ORG = 0;

            public DesiredMode() {
            }
        }

        public class App {
            public static final byte GUIDANCE_VIEW = 1;
            public static final byte MAP = 0;

            public App() {
            }
        }
    }

    public class RespFullMapData {
        public static final short RESP_FULL_MAP_DATA_MAX_BYTES_LENGTH = 5;

        public RespFullMapData() {
        }

        public class Sender {
            public static final byte CLUSTER = 0;
            public static final byte IVI = 1;

            public Sender() {
            }
        }

        public class CurrentMode {
            public static final byte FULL = 1;
            public static final byte ORG = 0;

            public CurrentMode() {
            }
        }

        public class DesiredMode {
            public static final byte FULL = 1;
            public static final byte ORG = 0;

            public DesiredMode() {
            }
        }

        public class App {
            public static final byte GUIDANCE_VIEW = 1;
            public static final byte MAP = 0;

            public App() {
            }
        }

        public class Ack {
            public static final byte FAILURE = 1;
            public static final byte SUCCESS = 0;

            public Ack() {
            }
        }
    }
}
