package com.byd.dilink.cdrservice;

/* JADX INFO: loaded from: classes.dex */
public class CDRServiceConstants {

    public static class Command {
        public static final int REMOTE_TRIGGER_LOCK = 2;
        public static final int REMOTE_TRIGGER_RECORD = 0;
        public static final int REMOTE_TRIGGER_SWITCH_DVR = 5;
        public static final int REMOTE_TRIGGER_SWITCH_RECORDER_AUDIO = 6;
        public static final int REMOTE_TRIGGER_TAKE_PICTURE = 1;
    }

    public static class CommandResponse {
        public static final int REMOTE_PROCESS_CDR_EXTERNAL_SUCCESS = 3;
        public static final int REMOTE_PROCESS_CDR_FAIL_ACC_OFF = 23;
        public static final int REMOTE_PROCESS_CDR_FAIL_CAMERA_NOT_EXIST = 21;
        public static final int REMOTE_PROCESS_CDR_FAIL_CAMERA_SIGNAL_ERROR = 22;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_CAPACITY_ERROR = 13;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_EXTERNAL_SCANNING = 18;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_FORMATTING = 15;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_FORMAT_ERROR = 12;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_FORMAT_FAIL = 16;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_INTERNAL_SCANNING = 17;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_IO_ERROR = 19;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_MOUNTED = 20;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_TIMEOUT = 10;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_TOO_FULL = 14;
        public static final int REMOTE_PROCESS_CDR_FAIL_CHECK_SDCARD_UNMOUNTED = 11;
        public static final int REMOTE_PROCESS_CDR_FAIL_OTHER = 26;
        public static final int REMOTE_PROCESS_CDR_FAIL_RECORDER_SWITCH_OFF = 25;
        public static final int REMOTE_PROCESS_CDR_FAIL_STOPRECORD = 24;
        public static final int REMOTE_PROCESS_CDR_INTERNAL_SUCCESS = 2;
        public static final int REMOTE_PROCESS_CDR_SUCCESS = 1;
    }

    public static class Value {
        public static final int REMOTE_SET_INVALID = 255;
        public static final int REMOTE_SET_ON = 1;
        public static final int REMOTE_SET_OPEN = 2;
    }
}
