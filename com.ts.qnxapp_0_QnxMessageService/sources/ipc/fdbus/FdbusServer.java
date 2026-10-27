package ipc.fdbus;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class FdbusServer {
    private FdbusServerListener mFdbusListener;
    private long mNativeHandle;

    private native boolean fdb_bind(long j, String str);

    private native boolean fdb_broadcast(long j, int i, String str, byte[] bArr, String str2, int i2);

    private native String fdb_bus_name(long j);

    private native long fdb_create(String str);

    private native void fdb_destroy(long j);

    private native void fdb_enable_event_cache(long j, boolean z);

    private native String fdb_endpoint_name(long j);

    private native void fdb_init_event_cache(long j, int i, String str, byte[] bArr, boolean z);

    private native boolean fdb_log_enabled(long j, int i);

    private native boolean fdb_unbind(long j);

    private void initialize(String name, FdbusServerListener listener) {
        this.mNativeHandle = fdb_create(name);
        this.mFdbusListener = listener;
    }

    public FdbusServer(String name, FdbusServerListener listener) {
        initialize(name, listener);
    }

    FdbusServer(long handle) {
        this.mNativeHandle = handle;
        this.mFdbusListener = null;
    }

    public FdbusServer(FdbusServerListener listener) {
        initialize(null, listener);
    }

    public FdbusServer(String name) {
        initialize(name, null);
    }

    public FdbusServer() {
        initialize(null, null);
    }

    public void setListener(FdbusServerListener listener) {
        this.mFdbusListener = listener;
    }

    public void destroy() {
        long handle = this.mNativeHandle;
        this.mNativeHandle = 0L;
        if (handle != 0) {
            fdb_destroy(handle);
        }
    }

    public boolean bind(String url) {
        return fdb_bind(this.mNativeHandle, url);
    }

    public boolean bind() {
        return bind("svc://" + busName());
    }

    public boolean unbind() {
        return fdb_unbind(this.mNativeHandle);
    }

    public boolean broadcast(int msg_code, String topic, Object msg, int qos) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, logEnabled(4));
        if (builder == null) {
            return false;
        }
        return fdb_broadcast(this.mNativeHandle, msg_code, topic, builder.toBuffer(), builder.toString(), qos);
    }

    public boolean broadcast(int msg_code, String topic, Object msg) {
        return broadcast(msg_code, topic, msg, 5);
    }

    public boolean broadcast(int msg_code, Object msg, int qos) {
        return broadcast(msg_code, null, msg, qos);
    }

    public boolean broadcast(int msg_code, Object msg) {
        return broadcast(msg_code, null, msg, 5);
    }

    public boolean initEventCache(int event, String topic, Object msg, boolean always_update) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, false);
        if (builder == null) {
            return false;
        }
        fdb_init_event_cache(this.mNativeHandle, event, topic, builder.toBuffer(), always_update);
        return true;
    }

    public String endpointName() {
        return fdb_endpoint_name(this.mNativeHandle);
    }

    public String busName() {
        return fdb_bus_name(this.mNativeHandle);
    }

    public boolean logEnabled(int msg_type) {
        return fdb_log_enabled(this.mNativeHandle, msg_type);
    }

    public void enableEventCache(boolean enable) {
        fdb_enable_event_cache(this.mNativeHandle, enable);
    }

    private void callbackOnline(int sid, boolean is_first, int qos) {
        FdbusServerListener fdbusServerListener = this.mFdbusListener;
        if (fdbusServerListener != null) {
            try {
                fdbusServerListener.onOnline(sid, is_first, qos);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void callbackOffline(int sid, boolean is_last, int qos) {
        FdbusServerListener fdbusServerListener = this.mFdbusListener;
        if (fdbusServerListener != null) {
            try {
                fdbusServerListener.onOffline(sid, is_last, qos);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void callbackInvoke(int sid, int msg_code, byte[] payload, long msg_handle) {
        if (this.mFdbusListener != null) {
            FdbusMessage msg = new FdbusMessage(sid, msg_code, payload, msg_handle);
            try {
                this.mFdbusListener.onInvoke(msg);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void callbackSubscribe(int sid, long msg_handle, ArrayList<SubscribeItem> sub_list) {
        if (this.mFdbusListener != null) {
            FdbusMessage msg = new FdbusMessage(sid, 0, (byte[]) null, msg_handle);
            try {
                this.mFdbusListener.onSubscribe(msg, sub_list);
            } catch (Exception e) {
                System.out.println(e);
            }
            msg.destroy();
        }
    }
}
