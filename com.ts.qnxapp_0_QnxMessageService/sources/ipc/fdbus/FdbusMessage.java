package ipc.fdbus;

/* JADX INFO: loaded from: classes.dex */
public class FdbusMessage {
    private int mMsgCode;
    private long mNativeHandle;
    private byte[] mPayload;
    private int mSid;
    private int mStatus;
    private String mTopic;
    private Object mUserData;

    private native boolean fdb_broadcast(long j, int i, String str, byte[] bArr, String str2);

    private native void fdb_destroy(long j);

    private native boolean fdb_log_enabled(long j);

    private native boolean fdb_reply(long j, byte[] bArr, String str);

    public int returnValue() {
        return this.mStatus;
    }

    private void initialize(long handle, int sid, int msg_code, String topic, byte[] payload, Object user_data, int status) {
        this.mNativeHandle = handle;
        this.mSid = sid;
        this.mMsgCode = msg_code;
        this.mPayload = payload;
        this.mUserData = user_data;
        this.mTopic = topic;
        this.mStatus = status;
    }

    public FdbusMessage(int sid, int msg_code, byte[] payload, Object user_data, int status) {
        initialize(0L, sid, msg_code, null, payload, user_data, status);
    }

    public FdbusMessage(int sid, int msg_code, String topic, byte[] payload, Object user_data, int status) {
        initialize(0L, sid, msg_code, topic, payload, user_data, status);
    }

    public FdbusMessage(int sid, int msg_code, byte[] payload, Object user_data) {
        initialize(0L, sid, msg_code, null, payload, user_data, 0);
    }

    public FdbusMessage(int sid, int msg_code, byte[] payload, int status) {
        initialize(0L, sid, msg_code, null, payload, null, status);
    }

    public FdbusMessage(int sid, int msg_code, String topic, byte[] payload, int status) {
        initialize(0L, sid, msg_code, topic, payload, null, status);
    }

    public FdbusMessage(int sid, int msg_code, byte[] payload) {
        initialize(0L, sid, msg_code, null, payload, null, 0);
    }

    public FdbusMessage(int sid, int msg_code, byte[] payload, long handle) {
        initialize(handle, sid, msg_code, null, payload, null, 0);
    }

    public byte[] byteArray() {
        return this.mPayload;
    }

    public int code() {
        return this.mMsgCode;
    }

    public int sid() {
        return this.mSid;
    }

    public Object userData() {
        return this.mUserData;
    }

    public String topic() {
        return this.mTopic;
    }

    public void topic(String tpc) {
        this.mTopic = tpc;
    }

    public boolean reply(Object msg) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, logEnabled());
        if (builder == null) {
            return false;
        }
        boolean ret = fdb_reply(this.mNativeHandle, builder.toBuffer(), builder.toString());
        this.mNativeHandle = 0L;
        return ret;
    }

    public boolean broadcast(int msg_code, String topic, Object msg) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, logEnabled());
        if (builder == null) {
            return false;
        }
        boolean ret = fdb_broadcast(this.mNativeHandle, msg_code, topic, builder.toBuffer(), builder.toString());
        return ret;
    }

    public boolean broadcast(int msg_code, Object msg) {
        return broadcast(msg_code, null, msg);
    }

    public boolean logEnabled() {
        return fdb_log_enabled(this.mNativeHandle);
    }

    public void destroy() {
        long handle = this.mNativeHandle;
        this.mNativeHandle = 0L;
        if (handle != 0) {
            fdb_destroy(handle);
        }
    }
}
