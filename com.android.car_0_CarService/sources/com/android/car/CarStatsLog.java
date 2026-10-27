package com.android.car;

import android.util.StatsEvent;
import android.util.StatsLog;

/* JADX INFO: loaded from: classes3.dex */
public class CarStatsLog {
    public static final byte ANNOTATION_ID_EXCLUSIVE_STATE = 4;
    public static final byte ANNOTATION_ID_IS_UID = 1;
    public static final byte ANNOTATION_ID_PRIMARY_FIELD = 3;
    public static final byte ANNOTATION_ID_PRIMARY_FIELD_FIRST_UID = 5;
    public static final byte ANNOTATION_ID_STATE_NESTED = 8;
    public static final byte ANNOTATION_ID_TRIGGER_STATE_RESET = 7;
    public static final byte ANNOTATION_ID_TRUNCATE_TIMESTAMP = 2;
    public static final int CAR_POWER_STATE_CHANGED = 203;
    public static final int CAR_POWER_STATE_CHANGED__STATE__ON = 1;
    public static final int CAR_POWER_STATE_CHANGED__STATE__SHUTDOWN_PREPARE = 2;
    public static final int CAR_POWER_STATE_CHANGED__STATE__SIMULATE_SLEEP = 5;
    public static final int CAR_POWER_STATE_CHANGED__STATE__SUSPEND = 4;
    public static final int CAR_POWER_STATE_CHANGED__STATE__WAIT_FOR_FINISH = 3;
    public static final int CAR_POWER_STATE_CHANGED__STATE__WAIT_FOR_VHAL = 0;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_REQUEST_REPORTED = 286;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_REQUEST_REPORTED__REQUEST_TYPE__COLD_BOOT = 3;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_REQUEST_REPORTED__REQUEST_TYPE__FIRST_BOOT = 1;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_REQUEST_REPORTED__REQUEST_TYPE__FIRST_BOOT_AFTER_OTA = 2;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_REQUEST_REPORTED__REQUEST_TYPE__RESUME = 4;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_REQUEST_REPORTED__REQUEST_TYPE__UNKNOWN = 0;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED = 287;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__CALLBACK_STATUS__CONCURRENT_OPERATION = 6;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__CALLBACK_STATUS__HAL_RESPONSE_TIMEOUT = 4;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__CALLBACK_STATUS__HAL_SET_TIMEOUT = 3;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__CALLBACK_STATUS__INVALID = 1;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__CALLBACK_STATUS__OK = 2;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__CALLBACK_STATUS__UNKNOWN = 0;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__CALLBACK_STATUS__WRONG_HAL_RESPONSE = 5;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__RESPONSE_ACTION__CREATE = 3;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__RESPONSE_ACTION__DEFAULT = 1;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__RESPONSE_ACTION__SWITCH = 2;
    public static final int CAR_USER_HAL_INITIAL_USER_INFO_RESPONSE_REPORTED__RESPONSE_ACTION__UNSPECIFIED = 0;
    public static final int CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED = 283;
    public static final int CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED__REQUEST_TYPE__CREATE_REQUEST = 4;
    public static final int CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED__REQUEST_TYPE__REMOVE_REQUEST = 5;
    public static final int CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED__REQUEST_TYPE__SWITCH_REQUEST_ANDROID = 1;
    public static final int CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED__REQUEST_TYPE__SWITCH_REQUEST_LEGACY = 3;
    public static final int CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED__REQUEST_TYPE__SWITCH_REQUEST_OEM = 2;
    public static final int CAR_USER_HAL_MODIFY_USER_REQUEST_REPORTED__REQUEST_TYPE__UNKNOWN = 0;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED = 284;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__CALLBACK_STATUS__CONCURRENT_OPERATION = 6;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__CALLBACK_STATUS__HAL_RESPONSE_TIMEOUT = 4;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__CALLBACK_STATUS__HAL_SET_TIMEOUT = 3;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__CALLBACK_STATUS__INVALID = 1;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__CALLBACK_STATUS__OK = 2;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__CALLBACK_STATUS__UNKNOWN = 0;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__CALLBACK_STATUS__WRONG_HAL_RESPONSE = 5;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__REQUEST_STATUS__FAILURE = 2;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__REQUEST_STATUS__SUCCESS = 1;
    public static final int CAR_USER_HAL_MODIFY_USER_RESPONSE_REPORTED__REQUEST_STATUS__UNSPECIFIED = 0;
    public static final int CAR_USER_HAL_POST_SWITCH_RESPONSE_REPORTED = 285;
    public static final int CAR_USER_HAL_POST_SWITCH_RESPONSE_REPORTED__SWITCH_STATUS__FAILURE = 2;
    public static final int CAR_USER_HAL_POST_SWITCH_RESPONSE_REPORTED__SWITCH_STATUS__SUCCESS = 1;
    public static final int CAR_USER_HAL_POST_SWITCH_RESPONSE_REPORTED__SWITCH_STATUS__UNKNOWN = 0;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED = 289;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED__CALLBACK_STATUS__CONCURRENT_OPERATION = 6;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED__CALLBACK_STATUS__HAL_RESPONSE_TIMEOUT = 4;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED__CALLBACK_STATUS__HAL_SET_TIMEOUT = 3;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED__CALLBACK_STATUS__INVALID = 1;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED__CALLBACK_STATUS__OK = 2;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED__CALLBACK_STATUS__UNKNOWN = 0;
    public static final int CAR_USER_HAL_SET_USER_ASSOCIATION_RESPONSE_REPORTED__CALLBACK_STATUS__WRONG_HAL_RESPONSE = 5;
    public static final int CAR_USER_HAL_USER_ASSOCIATION_REQUEST_REPORTED = 288;
    public static final int CAR_USER_HAL_USER_ASSOCIATION_REQUEST_REPORTED__REQUEST_TYPE__GET = 2;
    public static final int CAR_USER_HAL_USER_ASSOCIATION_REQUEST_REPORTED__REQUEST_TYPE__SET = 1;
    public static final int CAR_USER_HAL_USER_ASSOCIATION_REQUEST_REPORTED__REQUEST_TYPE__UNKNOWN = 0;
    public static final int GARAGE_MODE_INFO = 204;
    public static final int VMS_CLIENT_CONNECTION_STATE_CHANGED = 230;
    public static final int VMS_CLIENT_CONNECTION_STATE_CHANGED__STATE__CONNECTED = 2;
    public static final int VMS_CLIENT_CONNECTION_STATE_CHANGED__STATE__CONNECTING = 1;
    public static final int VMS_CLIENT_CONNECTION_STATE_CHANGED__STATE__CONNECTION_ERROR = 5;
    public static final int VMS_CLIENT_CONNECTION_STATE_CHANGED__STATE__DISCONNECTED = 3;
    public static final int VMS_CLIENT_CONNECTION_STATE_CHANGED__STATE__TERMINATED = 4;
    public static final int VMS_CLIENT_CONNECTION_STATE_CHANGED__STATE__UNKNOWN = 0;
    public static final int VMS_CLIENT_STATS = 10065;

    public static void write(int code) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, boolean arg1) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeBoolean(arg1);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, int arg1) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeInt(arg1);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, int arg1, int arg2) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeInt(arg1);
        if (230 == code) {
            builder.addBooleanAnnotation((byte) 1, true);
        }
        builder.writeInt(arg2);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, int arg1, int arg2, int arg3) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeInt(arg1);
        builder.writeInt(arg2);
        builder.writeInt(arg3);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeInt(arg1);
        builder.writeInt(arg2);
        builder.writeInt(arg3);
        builder.writeInt(arg4);
        builder.writeInt(arg5);
        builder.writeInt(arg6);
        builder.writeInt(arg7);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, int arg1, int arg2, int arg3, int arg4, int arg5, String arg6) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeInt(arg1);
        builder.writeInt(arg2);
        builder.writeInt(arg3);
        builder.writeInt(arg4);
        builder.writeInt(arg5);
        builder.writeString(arg6);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, int arg1, int arg2, int arg3, int arg4, int arg5, String arg6, String arg7) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeInt(arg1);
        builder.writeInt(arg2);
        builder.writeInt(arg3);
        builder.writeInt(arg4);
        builder.writeInt(arg5);
        builder.writeString(arg6);
        builder.writeString(arg7);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }

    public static void write(int code, int arg1, int arg2, int arg3, String arg4, String arg5) {
        StatsEvent.Builder builder = StatsEvent.newBuilder();
        builder.setAtomId(code);
        builder.writeInt(arg1);
        builder.writeInt(arg2);
        builder.writeInt(arg3);
        builder.writeString(arg4);
        builder.writeString(arg5);
        builder.usePooledBuffer();
        StatsLog.write(builder.build());
    }
}
