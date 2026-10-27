package ipc.fdbus;

import ipc.fdbus.FdbusAppListener;

/* JADX INFO: loaded from: classes.dex */
public class Fdbus {
    public static final int FDB_LL_DEBUG = 1;
    public static final int FDB_LL_ERROR = 4;
    public static final int FDB_LL_FATAL = 5;
    public static final int FDB_LL_INFO = 2;
    public static final int FDB_LL_MAX = 7;
    public static final int FDB_LL_SILENT = 6;
    public static final int FDB_LL_VERBOSE = 0;
    public static final int FDB_LL_WARNING = 3;
    public static final int FDB_MT_BROADCAST = 4;
    public static final int FDB_MT_GET_EVENT = 8;
    public static final int FDB_MT_MAX = 10;
    public static final int FDB_MT_PUBLISH = 10;
    public static final int FDB_MT_REPLY = 2;
    public static final int FDB_MT_REQUEST = 1;
    public static final int FDB_MT_RETURN_EVENT = 9;
    public static final int FDB_MT_SIDEBAND_REPLY = 6;
    public static final int FDB_MT_SIDEBAND_REQUEST = 5;
    public static final int FDB_MT_STATUS = 7;
    public static final int FDB_MT_SUBSCRIBE_REQ = 3;
    public static final int FDB_MT_UNKNOWN = 0;
    public static final int FDB_QOS_BEST_EFFORTS = 2;
    public static final int FDB_QOS_INVALID = 7;
    public static final int FDB_QOS_LOCAL = 0;
    public static final int FDB_QOS_RELIABLE = 1;
    public static final int FDB_QOS_SECURE_BEST_EFFORTS = 4;
    public static final int FDB_QOS_SECURE_RELIABLE = 3;
    public static final int FDB_QOS_TRY_SECURE_BEST_EFFORTS = 6;
    public static final int FDB_QOS_TRY_SECURE_RELIABLE = 5;
    public static final int FDB_ST_ALREADY_EXIST = -22;
    public static final int FDB_ST_AUTHENTICATION_FAIL = -30;
    public static final int FDB_ST_AUTO_REPLY_OK = -11;
    public static final int FDB_ST_BAD_PARAMETER = -24;
    public static final int FDB_ST_DEAD_LOCK = -19;
    public static final int FDB_ST_INTERNAL_FAIL = -26;
    public static final int FDB_ST_INVALID_ID = -17;
    public static final int FDB_ST_MSG_DECODE_FAIL = -23;
    public static final int FDB_ST_NON_EXIST = -21;
    public static final int FDB_ST_NOT_AVAILABLE = -25;
    public static final int FDB_ST_NOT_IMPLEMENTED = -28;
    public static final int FDB_ST_OBJECT_NOT_FOUND = -29;
    public static final int FDB_ST_OK = 0;
    public static final int FDB_ST_OUT_OF_MEMORY = -27;
    public static final int FDB_ST_PEER_VANISH = -18;
    public static final int FDB_ST_SUBSCRIBE_FAIL = -13;
    public static final int FDB_ST_SUBSCRIBE_OK = -12;
    public static final int FDB_ST_TIMEOUT = -16;
    public static final int FDB_ST_UNABLE_TO_SEND = -20;
    public static final int FDB_ST_UNKNOWN = -128;
    public static final int FDB_ST_UNSUBSCRIBE_FAIL = -15;
    public static final int FDB_ST_UNSUBSCRIBE_OK = -14;

    private native void fdb_init(Class cls, Class cls2, Class cls3, Class cls4, Class cls5, Class cls6);

    private static native void fdb_log_trace(String str, int i, String str2);

    public Fdbus() {
        System.loadLibrary("fdbus-jni");
        fdb_init(FdbusServer.class, FdbusClient.class, SubscribeItem.class, FdbusMessage.class, FdbusAppListener.Connection.class, FdbusAppListener.Action.class);
    }

    public static void LOG_D(String tag, String data) {
        fdb_log_trace(tag, 1, data);
    }

    public static void LOG_I(String tag, String data) {
        fdb_log_trace(tag, 2, data);
    }

    public static void LOG_W(String tag, String data) {
        fdb_log_trace(tag, 3, data);
    }

    public static void LOG_E(String tag, String data) {
        fdb_log_trace(tag, 4, data);
    }

    public static void LOG_F(String tag, String data) {
        fdb_log_trace(tag, 5, data);
    }

    public static FdbusMsgBuilder encodeMessage(Object msg, boolean enable_log) {
        if (msg == null || (msg instanceof byte[])) {
            return new FdbusMsgBuilder((byte[]) msg, null);
        }
        if (msg instanceof FdbusMsgBuilder) {
            FdbusMsgBuilder builder = (FdbusMsgBuilder) msg;
            if (!builder.build(enable_log)) {
                LOG_E("fdbus-jni", "Fail to build message!\n");
                return null;
            }
            return builder;
        }
        LOG_E("fdbus-jni", "Unsupported message type!\n");
        return null;
    }
}
