package com.android.permissioncontroller;

import android.os.Build;
import android.os.SystemClock;
import android.util.StatsEvent;
import android.util.StatsLog;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes8.dex */
public class PermissionControllerStatsLog {
    public static final byte ANNOTATION_ID_EXCLUSIVE_STATE = 4;
    public static final byte ANNOTATION_ID_IS_UID = 1;
    public static final byte ANNOTATION_ID_PRIMARY_FIELD = 3;
    public static final byte ANNOTATION_ID_PRIMARY_FIELD_FIRST_UID = 5;
    public static final byte ANNOTATION_ID_STATE_NESTED = 8;
    public static final byte ANNOTATION_ID_TRIGGER_STATE_RESET = 7;
    public static final byte ANNOTATION_ID_TRUNCATE_TIMESTAMP = 2;
    public static final int APP_PERMISSIONS_FRAGMENT_VIEWED = 217;
    public static final int APP_PERMISSIONS_FRAGMENT_VIEWED__CATEGORY__ALLOWED = 1;
    public static final int APP_PERMISSIONS_FRAGMENT_VIEWED__CATEGORY__ALLOWED_FOREGROUND = 2;
    public static final int APP_PERMISSIONS_FRAGMENT_VIEWED__CATEGORY__DENIED = 3;
    public static final int APP_PERMISSIONS_FRAGMENT_VIEWED__CATEGORY__UNDEFINED = 0;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED = 215;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED__BUTTON_PRESSED__ALLOW = 1;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED__BUTTON_PRESSED__ALLOW_ALWAYS = 4;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED__BUTTON_PRESSED__ALLOW_FOREGROUND = 5;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED__BUTTON_PRESSED__ASK_EVERY_TIME = 3;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED__BUTTON_PRESSED__DENY = 2;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED__BUTTON_PRESSED__DENY_FOREGROUND = 6;
    public static final int APP_PERMISSION_FRAGMENT_ACTION_REPORTED__BUTTON_PRESSED__UNDEFINED = 0;
    public static final int APP_PERMISSION_FRAGMENT_VIEWED = 216;
    public static final int APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION = 273;
    public static final int APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION__ACTION__OPENED_FOR_AUTO_REVOKE = 1;
    public static final int APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION__ACTION__OPENED_FROM_INTENT = 2;
    public static final int APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION__ACTION__SWITCH_DISABLED = 4;
    public static final int APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION__ACTION__SWITCH_ENABLED = 3;
    public static final int APP_PERMISSION_GROUPS_FRAGMENT_AUTO_REVOKE_ACTION__ACTION__UNDEFINED = 0;
    public static final int AUTO_REVOKED_APP_INTERACTION = 272;
    public static final int AUTO_REVOKED_APP_INTERACTION__ACTION__APP_INFO = 3;
    public static final int AUTO_REVOKED_APP_INTERACTION__ACTION__OPEN = 2;
    public static final int AUTO_REVOKED_APP_INTERACTION__ACTION__OPEN_IN_SETTINGS = 6;
    public static final int AUTO_REVOKED_APP_INTERACTION__ACTION__PERMISSIONS = 4;
    public static final int AUTO_REVOKED_APP_INTERACTION__ACTION__REMOVE = 1;
    public static final int AUTO_REVOKED_APP_INTERACTION__ACTION__REMOVE_IN_SETTINGS = 5;
    public static final int AUTO_REVOKED_APP_INTERACTION__ACTION__UNDEFINED = 0;
    public static final int AUTO_REVOKE_FRAGMENT_APP_VIEWED = 271;
    public static final int AUTO_REVOKE_FRAGMENT_APP_VIEWED__AGE__NEWER_BUCKET = 1;
    public static final int AUTO_REVOKE_FRAGMENT_APP_VIEWED__AGE__OLDER_BUCKET = 2;
    public static final int AUTO_REVOKE_FRAGMENT_APP_VIEWED__AGE__UNDEFINED = 0;
    public static final int AUTO_REVOKE_NOTIFICATION_CLICKED = 270;
    public static final int GRANT_PERMISSIONS_ACTIVITY_BUTTON_ACTIONS = 213;
    public static final int LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION = 214;
    public static final int LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION__RESULT__NOTIFICATION_CLICKED = 3;
    public static final int LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION__RESULT__NOTIFICATION_DECLINED = 2;
    public static final int LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION__RESULT__NOTIFICATION_PRESENTED = 1;
    public static final int LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION__RESULT__UNDEFINED = 0;
    public static final int PERMISSION_APPS_FRAGMENT_VIEWED = 218;
    public static final int PERMISSION_APPS_FRAGMENT_VIEWED__CATEGORY__ALLOWED = 1;
    public static final int PERMISSION_APPS_FRAGMENT_VIEWED__CATEGORY__ALLOWED_FOREGROUND = 2;
    public static final int PERMISSION_APPS_FRAGMENT_VIEWED__CATEGORY__DENIED = 3;
    public static final int PERMISSION_APPS_FRAGMENT_VIEWED__CATEGORY__UNDEFINED = 0;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED = 170;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__AUTO_DENIED = 8;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__AUTO_GRANTED = 5;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__AUTO_ONE_TIME_PERMISSION_REVOKED = 15;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__AUTO_UNUSED_APP_PERMISSION_REVOKED = 16;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__IGNORED = 1;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__IGNORED_POLICY_FIXED = 3;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__IGNORED_RESTRICTED_PERMISSION = 9;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__IGNORED_USER_FIXED = 2;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__UNDEFINED = 0;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_DENIED = 6;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_DENIED_IN_SETTINGS = 13;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_DENIED_WITH_PREJUDICE = 7;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_DENIED_WITH_PREJUDICE_IN_SETTINGS = 14;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_GRANTED = 4;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_GRANTED_IN_SETTINGS = 12;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_GRANTED_ONE_TIME = 10;
    public static final int PERMISSION_GRANT_REQUEST_RESULT_REPORTED__RESULT__USER_IGNORED = 11;
    public static final int PRIVACY_INDICATORS_INTERACTED = 180;
    public static final int PRIVACY_INDICATORS_INTERACTED__TYPE__CHIP_CLICKED = 2;
    public static final int PRIVACY_INDICATORS_INTERACTED__TYPE__CHIP_VIEWED = 1;
    public static final int PRIVACY_INDICATORS_INTERACTED__TYPE__DIALOG_DISMISS = 4;
    public static final int PRIVACY_INDICATORS_INTERACTED__TYPE__DIALOG_LINE_ITEM = 5;
    public static final int PRIVACY_INDICATORS_INTERACTED__TYPE__UNKNOWN = 0;
    public static final int REVIEW_PERMISSIONS_FRAGMENT_RESULT_REPORTED = 211;
    public static final int ROLE_REQUEST_RESULT_REPORTED = 190;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__IGNORED = 1;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__IGNORED_ALREADY_GRANTED = 2;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__IGNORED_NOT_QUALIFIED = 3;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__IGNORED_USER_ALWAYS_DENIED = 4;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__UNDEFINED = 0;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__USER_DENIED = 6;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__USER_DENIED_GRANTED_ANOTHER = 7;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__USER_DENIED_WITH_ALWAYS = 8;
    public static final int ROLE_REQUEST_RESULT_REPORTED__RESULT__USER_GRANTED = 5;
    public static final int RUNTIME_PERMISSIONS_UPGRADE_RESULT = 212;

    public static void write(int code, int arg1) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeInt(arg1);
            if (180 == code) {
                builder.addBooleanAnnotation((byte) 4, true);
            }
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1);
    }

    public static void write(int code, int arg1, String arg2, String arg3, int arg4, int arg5, String arg6, int arg7, String arg8, int arg9) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeInt(arg1);
            builder.writeString(arg2);
            builder.writeString(arg3);
            builder.writeInt(arg4);
            builder.writeInt(arg5);
            builder.writeString(arg6);
            builder.writeInt(arg7);
            builder.writeString(arg8);
            builder.writeInt(arg9);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
    }

    public static void write(int code, long arg1) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1);
    }

    public static void write(int code, long arg1, int arg2, String arg3, int arg4) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.writeInt(arg2);
            if (272 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            if (273 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg3);
            builder.writeInt(arg4);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4);
    }

    public static void write(int code, long arg1, int arg2, String arg3, String arg4) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.writeInt(arg2);
            if (216 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg3);
            builder.writeString(arg4);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4);
    }

    public static void write(int code, long arg1, int arg2, String arg3, String arg4, boolean arg5) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.writeInt(arg2);
            if (211 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg3);
            builder.writeString(arg4);
            builder.writeBoolean(arg5);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4, arg5);
    }

    public static void write(int code, long arg1, int arg2, String arg3, String arg4, boolean arg5, int arg6) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.writeInt(arg2);
            if (170 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg3);
            builder.writeString(arg4);
            builder.writeBoolean(arg5);
            builder.writeInt(arg6);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    public static void write(int code, long arg1, int arg2, String arg3, String arg4, int arg5) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.writeInt(arg2);
            if (271 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg3);
            builder.writeString(arg4);
            builder.writeInt(arg5);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4, arg5);
    }

    public static void write(int code, long arg1, long arg2, int arg3, String arg4, String arg5, boolean arg6, int arg7, int arg8) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.writeLong(arg2);
            builder.writeInt(arg3);
            if (215 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg4);
            builder.writeString(arg5);
            builder.writeBoolean(arg6);
            builder.writeInt(arg7);
            builder.writeInt(arg8);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);
    }

    public static void write(int code, long arg1, long arg2, String arg3, int arg4, String arg5, int arg6) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeLong(arg1);
            builder.writeLong(arg2);
            builder.writeString(arg3);
            builder.writeInt(arg4);
            if (217 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            if (218 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg5);
            builder.writeInt(arg6);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    public static void write(int code, String arg1, int arg2, String arg3) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeString(arg1);
            builder.writeInt(arg2);
            if (212 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg3);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3);
    }

    public static void write(int code, String arg1, int arg2, String arg3, int arg4, int arg5, long arg6) {
        if (Build.VERSION.SDK_INT > 29) {
            StatsEvent.Builder builder = StatsEvent.newBuilder();
            builder.setAtomId(code);
            builder.writeString(arg1);
            builder.writeInt(arg2);
            if (213 == code) {
                builder.addBooleanAnnotation((byte) 1, true);
            }
            builder.writeString(arg3);
            builder.writeInt(arg4);
            builder.writeInt(arg5);
            builder.writeLong(arg6);
            builder.usePooledBuffer();
            StatsLog.write(builder.build());
            return;
        }
        QLogger.write(code, arg1, arg2, arg3, arg4, arg5, arg6);
    }

    private static class QLogger {
        private static final byte FLOAT_TYPE = 4;
        private static final int FLOAT_TYPE_SIZE = 5;
        private static final byte INT_TYPE = 0;
        private static final int INT_TYPE_SIZE = 5;
        private static final byte LIST_TYPE = 3;
        private static final int LIST_TYPE_OVERHEAD = 2;
        private static final int LOGGER_ENTRY_MAX_PAYLOAD = 4068;
        private static final byte LONG_TYPE = 1;
        private static final int LONG_TYPE_SIZE = 9;
        private static final int MAX_EVENT_PAYLOAD = 4064;
        private static final byte STRING_TYPE = 2;
        private static final int STRING_TYPE_OVERHEAD = 5;

        private QLogger() {
        }

        public static void write(int code, int arg1) {
            int needed = 16 + 5;
            if (needed > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed];
            buff[0] = 3;
            buff[0 + 1] = 3;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 0;
            copyInt(buff, pos3 + 1, arg1);
            StatsLog.writeRaw(buff, pos3 + 5);
        }

        public static void write(int code, int arg1, String arg2, String arg3, int arg4, int arg5, String arg6, int arg7, String arg8, int arg9) {
            String arg22;
            String arg32;
            String arg62;
            String arg82;
            int needed = 16 + 5;
            if (arg2 != null) {
                arg22 = arg2;
            } else {
                arg22 = "";
            }
            byte[] arg2Bytes = arg22.getBytes(StandardCharsets.UTF_8);
            int needed2 = needed + arg2Bytes.length + 5;
            if (arg3 != null) {
                arg32 = arg3;
            } else {
                arg32 = "";
            }
            byte[] arg3Bytes = arg32.getBytes(StandardCharsets.UTF_8);
            int needed3 = needed2 + arg3Bytes.length + 5 + 5 + 5;
            if (arg6 != null) {
                arg62 = arg6;
            } else {
                arg62 = "";
            }
            byte[] arg6Bytes = arg62.getBytes(StandardCharsets.UTF_8);
            int needed4 = needed3 + arg6Bytes.length + 5 + 5;
            if (arg8 != null) {
                arg82 = arg8;
            } else {
                arg82 = "";
            }
            byte[] arg8Bytes = arg82.getBytes(StandardCharsets.UTF_8);
            int needed5 = needed4 + arg8Bytes.length + 5 + 5;
            if (needed5 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed5];
            buff[0] = 3;
            buff[0 + 1] = 11;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 0;
            copyInt(buff, pos3 + 1, arg1);
            int pos4 = pos3 + 5;
            buff[pos4] = 2;
            int needed6 = arg2Bytes.length;
            copyInt(buff, pos4 + 1, needed6);
            System.arraycopy(arg2Bytes, 0, buff, pos4 + 5, arg2Bytes.length);
            int pos5 = pos4 + arg2Bytes.length + 5;
            buff[pos5] = 2;
            copyInt(buff, pos5 + 1, arg3Bytes.length);
            System.arraycopy(arg3Bytes, 0, buff, pos5 + 5, arg3Bytes.length);
            int pos6 = pos5 + arg3Bytes.length + 5;
            buff[pos6] = 0;
            copyInt(buff, pos6 + 1, arg4);
            int pos7 = pos6 + 5;
            buff[pos7] = 0;
            copyInt(buff, pos7 + 1, arg5);
            int pos8 = pos7 + 5;
            buff[pos8] = 2;
            copyInt(buff, pos8 + 1, arg6Bytes.length);
            System.arraycopy(arg6Bytes, 0, buff, pos8 + 5, arg6Bytes.length);
            int pos9 = pos8 + arg6Bytes.length + 5;
            buff[pos9] = 0;
            copyInt(buff, pos9 + 1, arg7);
            int pos10 = pos9 + 5;
            buff[pos10] = 2;
            copyInt(buff, pos10 + 1, arg8Bytes.length);
            System.arraycopy(arg8Bytes, 0, buff, pos10 + 5, arg8Bytes.length);
            int pos11 = pos10 + arg8Bytes.length + 5;
            buff[pos11] = 0;
            copyInt(buff, pos11 + 1, arg9);
            StatsLog.writeRaw(buff, pos11 + 5);
        }

        public static void write(int code, long arg1) {
            int needed = 16 + 9;
            if (needed > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed];
            buff[0] = 3;
            buff[0 + 1] = 3;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 1;
            copyLong(buff, pos3 + 1, arg1);
            StatsLog.writeRaw(buff, pos3 + 9);
        }

        public static void write(int code, long arg1, int arg2, String arg3, int arg4) {
            int needed = 16 + 9;
            int needed2 = needed + 5;
            if (arg3 == null) {
                arg3 = "";
            }
            byte[] arg3Bytes = arg3.getBytes(StandardCharsets.UTF_8);
            int needed3 = needed2 + arg3Bytes.length + 5 + 5;
            if (needed3 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed3];
            buff[0] = 3;
            buff[0 + 1] = 6;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 1;
            copyLong(buff, pos3 + 1, arg1);
            int pos4 = pos3 + 9;
            buff[pos4] = 0;
            copyInt(buff, pos4 + 1, arg2);
            int pos5 = pos4 + 5;
            buff[pos5] = 2;
            copyInt(buff, pos5 + 1, arg3Bytes.length);
            System.arraycopy(arg3Bytes, 0, buff, pos5 + 5, arg3Bytes.length);
            int pos6 = pos5 + arg3Bytes.length + 5;
            buff[pos6] = 0;
            copyInt(buff, pos6 + 1, arg4);
            StatsLog.writeRaw(buff, pos6 + 5);
        }

        public static void write(int code, long arg1, int arg2, String arg3, String arg4) {
            String arg32;
            String arg42;
            int needed = 16 + 9;
            int needed2 = needed + 5;
            if (arg3 != null) {
                arg32 = arg3;
            } else {
                arg32 = "";
            }
            byte[] arg3Bytes = arg32.getBytes(StandardCharsets.UTF_8);
            int needed3 = needed2 + arg3Bytes.length + 5;
            if (arg4 != null) {
                arg42 = arg4;
            } else {
                arg42 = "";
            }
            byte[] arg4Bytes = arg42.getBytes(StandardCharsets.UTF_8);
            int needed4 = needed3 + arg4Bytes.length + 5;
            if (needed4 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed4];
            buff[0] = 3;
            buff[0 + 1] = 6;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 1;
            copyLong(buff, pos3 + 1, arg1);
            int pos4 = pos3 + 9;
            buff[pos4] = 0;
            copyInt(buff, pos4 + 1, arg2);
            int pos5 = pos4 + 5;
            buff[pos5] = 2;
            copyInt(buff, pos5 + 1, arg3Bytes.length);
            System.arraycopy(arg3Bytes, 0, buff, pos5 + 5, arg3Bytes.length);
            int pos6 = pos5 + arg3Bytes.length + 5;
            buff[pos6] = 2;
            copyInt(buff, pos6 + 1, arg4Bytes.length);
            System.arraycopy(arg4Bytes, 0, buff, pos6 + 5, arg4Bytes.length);
            StatsLog.writeRaw(buff, pos6 + arg4Bytes.length + 5);
        }

        public static void write(int i, long j, int i2, String str, String str2, boolean z) {
            String str3;
            String str4;
            int i3 = 16 + 9 + 5;
            if (str != null) {
                str3 = str;
            } else {
                str3 = "";
            }
            byte[] bytes = str3.getBytes(StandardCharsets.UTF_8);
            int length = i3 + bytes.length + 5;
            if (str2 != null) {
                str4 = str2;
            } else {
                str4 = "";
            }
            byte[] bytes2 = str4.getBytes(StandardCharsets.UTF_8);
            int length2 = length + bytes2.length + 5 + 5;
            if (length2 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] bArr = new byte[length2];
            bArr[0] = 3;
            bArr[0 + 1] = 7;
            int i4 = 0 + 2;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            bArr[i4] = 1;
            copyLong(bArr, i4 + 1, jElapsedRealtimeNanos);
            int i5 = i4 + 9;
            bArr[i5] = 0;
            copyInt(bArr, i5 + 1, i);
            int i6 = i5 + 5;
            bArr[i6] = 1;
            copyLong(bArr, i6 + 1, j);
            int i7 = i6 + 9;
            bArr[i7] = 0;
            copyInt(bArr, i7 + 1, i2);
            int i8 = i7 + 5;
            bArr[i8] = 2;
            copyInt(bArr, i8 + 1, bytes.length);
            System.arraycopy(bytes, 0, bArr, i8 + 5, bytes.length);
            int length3 = i8 + bytes.length + 5;
            bArr[length3] = 2;
            copyInt(bArr, length3 + 1, bytes2.length);
            System.arraycopy(bytes2, 0, bArr, length3 + 5, bytes2.length);
            int length4 = length3 + bytes2.length + 5;
            bArr[length4] = 0;
            copyInt(bArr, length4 + 1, z ? 1 : 0);
            StatsLog.writeRaw(bArr, length4 + 5);
        }

        public static void write(int i, long j, int i2, String str, String str2, boolean z, int i3) {
            String str3;
            String str4;
            int i4 = 16 + 9 + 5;
            if (str != null) {
                str3 = str;
            } else {
                str3 = "";
            }
            byte[] bytes = str3.getBytes(StandardCharsets.UTF_8);
            int length = i4 + bytes.length + 5;
            if (str2 != null) {
                str4 = str2;
            } else {
                str4 = "";
            }
            byte[] bytes2 = str4.getBytes(StandardCharsets.UTF_8);
            int length2 = length + bytes2.length + 5 + 5 + 5;
            if (length2 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] bArr = new byte[length2];
            bArr[0] = 3;
            bArr[0 + 1] = 8;
            int i5 = 0 + 2;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            bArr[i5] = 1;
            copyLong(bArr, i5 + 1, jElapsedRealtimeNanos);
            int i6 = i5 + 9;
            bArr[i6] = 0;
            copyInt(bArr, i6 + 1, i);
            int i7 = i6 + 5;
            bArr[i7] = 1;
            copyLong(bArr, i7 + 1, j);
            int i8 = i7 + 9;
            bArr[i8] = 0;
            copyInt(bArr, i8 + 1, i2);
            int i9 = i8 + 5;
            bArr[i9] = 2;
            copyInt(bArr, i9 + 1, bytes.length);
            System.arraycopy(bytes, 0, bArr, i9 + 5, bytes.length);
            int length3 = i9 + bytes.length + 5;
            bArr[length3] = 2;
            copyInt(bArr, length3 + 1, bytes2.length);
            System.arraycopy(bytes2, 0, bArr, length3 + 5, bytes2.length);
            int length4 = length3 + bytes2.length + 5;
            bArr[length4] = 0;
            copyInt(bArr, length4 + 1, z ? 1 : 0);
            int i10 = length4 + 5;
            bArr[i10] = 0;
            copyInt(bArr, i10 + 1, i3);
            StatsLog.writeRaw(bArr, i10 + 5);
        }

        public static void write(int code, long arg1, int arg2, String arg3, String arg4, int arg5) {
            String arg32;
            String arg42;
            int needed = 16 + 9;
            int needed2 = needed + 5;
            if (arg3 != null) {
                arg32 = arg3;
            } else {
                arg32 = "";
            }
            byte[] arg3Bytes = arg32.getBytes(StandardCharsets.UTF_8);
            int needed3 = needed2 + arg3Bytes.length + 5;
            if (arg4 != null) {
                arg42 = arg4;
            } else {
                arg42 = "";
            }
            byte[] arg4Bytes = arg42.getBytes(StandardCharsets.UTF_8);
            int needed4 = needed3 + arg4Bytes.length + 5 + 5;
            if (needed4 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed4];
            buff[0] = 3;
            buff[0 + 1] = 7;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 1;
            copyLong(buff, pos3 + 1, arg1);
            int pos4 = pos3 + 9;
            buff[pos4] = 0;
            copyInt(buff, pos4 + 1, arg2);
            int pos5 = pos4 + 5;
            buff[pos5] = 2;
            copyInt(buff, pos5 + 1, arg3Bytes.length);
            System.arraycopy(arg3Bytes, 0, buff, pos5 + 5, arg3Bytes.length);
            int pos6 = pos5 + arg3Bytes.length + 5;
            buff[pos6] = 2;
            copyInt(buff, pos6 + 1, arg4Bytes.length);
            System.arraycopy(arg4Bytes, 0, buff, pos6 + 5, arg4Bytes.length);
            int pos7 = pos6 + arg4Bytes.length + 5;
            buff[pos7] = 0;
            copyInt(buff, pos7 + 1, arg5);
            StatsLog.writeRaw(buff, pos7 + 5);
        }

        public static void write(int i, long j, long j2, int i2, String str, String str2, boolean z, int i3, int i4) {
            String str3;
            String str4;
            int i5 = 16 + 9 + 9 + 5;
            if (str != null) {
                str3 = str;
            } else {
                str3 = "";
            }
            byte[] bytes = str3.getBytes(StandardCharsets.UTF_8);
            int length = i5 + bytes.length + 5;
            if (str2 != null) {
                str4 = str2;
            } else {
                str4 = "";
            }
            byte[] bytes2 = str4.getBytes(StandardCharsets.UTF_8);
            int length2 = length + bytes2.length + 5 + 5 + 5 + 5;
            if (length2 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] bArr = new byte[length2];
            bArr[0] = 3;
            bArr[0 + 1] = 10;
            int i6 = 0 + 2;
            long jElapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
            bArr[i6] = 1;
            copyLong(bArr, i6 + 1, jElapsedRealtimeNanos);
            int i7 = i6 + 9;
            bArr[i7] = 0;
            copyInt(bArr, i7 + 1, i);
            int i8 = i7 + 5;
            bArr[i8] = 1;
            copyLong(bArr, i8 + 1, j);
            int i9 = i8 + 9;
            bArr[i9] = 1;
            copyLong(bArr, i9 + 1, j2);
            int i10 = i9 + 9;
            bArr[i10] = 0;
            copyInt(bArr, i10 + 1, i2);
            int i11 = i10 + 5;
            bArr[i11] = 2;
            copyInt(bArr, i11 + 1, bytes.length);
            System.arraycopy(bytes, 0, bArr, i11 + 5, bytes.length);
            int length3 = i11 + bytes.length + 5;
            bArr[length3] = 2;
            copyInt(bArr, length3 + 1, bytes2.length);
            System.arraycopy(bytes2, 0, bArr, length3 + 5, bytes2.length);
            int length4 = length3 + bytes2.length + 5;
            bArr[length4] = 0;
            copyInt(bArr, length4 + 1, z ? 1 : 0);
            int i12 = length4 + 5;
            bArr[i12] = 0;
            copyInt(bArr, i12 + 1, i3);
            int i13 = i12 + 5;
            bArr[i13] = 0;
            copyInt(bArr, i13 + 1, i4);
            StatsLog.writeRaw(bArr, i13 + 5);
        }

        public static void write(int code, long arg1, long arg2, String arg3, int arg4, String arg5, int arg6) {
            String arg32;
            String arg52;
            int needed = 16 + 9;
            int needed2 = needed + 9;
            if (arg3 != null) {
                arg32 = arg3;
            } else {
                arg32 = "";
            }
            byte[] arg3Bytes = arg32.getBytes(StandardCharsets.UTF_8);
            int needed3 = needed2 + arg3Bytes.length + 5 + 5;
            if (arg5 != null) {
                arg52 = arg5;
            } else {
                arg52 = "";
            }
            byte[] arg5Bytes = arg52.getBytes(StandardCharsets.UTF_8);
            int needed4 = needed3 + arg5Bytes.length + 5 + 5;
            if (needed4 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed4];
            buff[0] = 3;
            buff[0 + 1] = 8;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 1;
            copyLong(buff, pos3 + 1, arg1);
            int pos4 = pos3 + 9;
            buff[pos4] = 1;
            copyLong(buff, pos4 + 1, arg2);
            int pos5 = pos4 + 9;
            buff[pos5] = 2;
            copyInt(buff, pos5 + 1, arg3Bytes.length);
            System.arraycopy(arg3Bytes, 0, buff, pos5 + 5, arg3Bytes.length);
            int pos6 = pos5 + arg3Bytes.length + 5;
            buff[pos6] = 0;
            copyInt(buff, pos6 + 1, arg4);
            int pos7 = pos6 + 5;
            buff[pos7] = 2;
            copyInt(buff, pos7 + 1, arg5Bytes.length);
            System.arraycopy(arg5Bytes, 0, buff, pos7 + 5, arg5Bytes.length);
            int pos8 = pos7 + arg5Bytes.length + 5;
            buff[pos8] = 0;
            copyInt(buff, pos8 + 1, arg6);
            StatsLog.writeRaw(buff, pos8 + 5);
        }

        public static void write(int code, String arg1, int arg2, String arg3) {
            if (arg1 == null) {
                arg1 = "";
            }
            byte[] arg1Bytes = arg1.getBytes(StandardCharsets.UTF_8);
            int needed = 16 + arg1Bytes.length + 5;
            int needed2 = needed + 5;
            if (arg3 == null) {
                arg3 = "";
            }
            byte[] arg3Bytes = arg3.getBytes(StandardCharsets.UTF_8);
            int needed3 = needed2 + arg3Bytes.length + 5;
            if (needed3 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed3];
            buff[0] = 3;
            buff[0 + 1] = 5;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 2;
            copyInt(buff, pos3 + 1, arg1Bytes.length);
            System.arraycopy(arg1Bytes, 0, buff, pos3 + 5, arg1Bytes.length);
            int pos4 = pos3 + arg1Bytes.length + 5;
            buff[pos4] = 0;
            copyInt(buff, pos4 + 1, arg2);
            int pos5 = pos4 + 5;
            buff[pos5] = 2;
            copyInt(buff, pos5 + 1, arg3Bytes.length);
            System.arraycopy(arg3Bytes, 0, buff, pos5 + 5, arg3Bytes.length);
            StatsLog.writeRaw(buff, pos5 + arg3Bytes.length + 5);
        }

        public static void write(int code, String arg1, int arg2, String arg3, int arg4, int arg5, long arg6) {
            String arg12;
            String arg32;
            if (arg1 != null) {
                arg12 = arg1;
            } else {
                arg12 = "";
            }
            byte[] arg1Bytes = arg12.getBytes(StandardCharsets.UTF_8);
            int needed = 16 + arg1Bytes.length + 5;
            int needed2 = needed + 5;
            if (arg3 != null) {
                arg32 = arg3;
            } else {
                arg32 = "";
            }
            byte[] arg3Bytes = arg32.getBytes(StandardCharsets.UTF_8);
            int needed3 = needed2 + arg3Bytes.length + 5 + 5 + 5 + 9;
            if (needed3 > MAX_EVENT_PAYLOAD) {
                return;
            }
            byte[] buff = new byte[needed3];
            buff[0] = 3;
            buff[0 + 1] = 8;
            int pos = 0 + 2;
            long elapsedRealtime = SystemClock.elapsedRealtimeNanos();
            buff[pos] = 1;
            copyLong(buff, pos + 1, elapsedRealtime);
            int pos2 = pos + 9;
            buff[pos2] = 0;
            copyInt(buff, pos2 + 1, code);
            int pos3 = pos2 + 5;
            buff[pos3] = 2;
            copyInt(buff, pos3 + 1, arg1Bytes.length);
            System.arraycopy(arg1Bytes, 0, buff, pos3 + 5, arg1Bytes.length);
            int pos4 = pos3 + arg1Bytes.length + 5;
            buff[pos4] = 0;
            copyInt(buff, pos4 + 1, arg2);
            int pos5 = pos4 + 5;
            buff[pos5] = 2;
            copyInt(buff, pos5 + 1, arg3Bytes.length);
            System.arraycopy(arg3Bytes, 0, buff, pos5 + 5, arg3Bytes.length);
            int pos6 = pos5 + arg3Bytes.length + 5;
            buff[pos6] = 0;
            copyInt(buff, pos6 + 1, arg4);
            int pos7 = pos6 + 5;
            buff[pos7] = 0;
            copyInt(buff, pos7 + 1, arg5);
            int pos8 = pos7 + 5;
            buff[pos8] = 1;
            copyLong(buff, pos8 + 1, arg6);
            StatsLog.writeRaw(buff, pos8 + 9);
        }

        private static void copyInt(byte[] buff, int pos, int val) {
            buff[pos] = (byte) val;
            buff[pos + 1] = (byte) (val >> 8);
            buff[pos + 2] = (byte) (val >> 16);
            buff[pos + 3] = (byte) (val >> 24);
        }

        private static void copyLong(byte[] buff, int pos, long val) {
            buff[pos] = (byte) val;
            buff[pos + 1] = (byte) (val >> 8);
            buff[pos + 2] = (byte) (val >> 16);
            buff[pos + 3] = (byte) (val >> 24);
            buff[pos + 4] = (byte) (val >> 32);
            buff[pos + 5] = (byte) (val >> 40);
            buff[pos + 6] = (byte) (val >> 48);
            buff[pos + 7] = (byte) (val >> 56);
        }
    }
}
