package ipc.fdbus;

import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class FdbusClient {
    private FdbusClientListener mFdbusListener;
    private long mNativeHandle;

    private native String fdb_bus_name(long j);

    private native boolean fdb_connect(long j, String str);

    private native long fdb_create(String str);

    private native void fdb_destroy(long j);

    private native boolean fdb_disconnect(long j);

    private native String fdb_endpoint_name(long j);

    private native boolean fdb_get_event_async(long j, int i, String str, Object obj, int i2, int i3);

    private native FdbusMessage fdb_get_event_sync(long j, int i, String str, int i2, int i3);

    private native boolean fdb_invoke_async(long j, int i, byte[] bArr, String str, Object obj, int i2, int i3);

    private native FdbusMessage fdb_invoke_sync(long j, int i, byte[] bArr, String str, int i2, int i3);

    private native boolean fdb_log_enabled(long j, int i);

    private native boolean fdb_publish(long j, int i, String str, byte[] bArr, String str2, boolean z, int i2);

    private native boolean fdb_send(long j, int i, byte[] bArr, String str, int i2);

    private native boolean fdb_subscribe(long j, ArrayList<SubscribeItem> arrayList, int i);

    private native boolean fdb_unsubscribe(long j, ArrayList<SubscribeItem> arrayList, int i);

    private void initialize(String name, FdbusClientListener listener) {
        this.mNativeHandle = fdb_create(name);
        this.mFdbusListener = listener;
    }

    public FdbusClient(String name, FdbusClientListener listener) {
        initialize(name, listener);
    }

    FdbusClient(long handle) {
        this.mNativeHandle = handle;
        this.mFdbusListener = null;
    }

    public FdbusClient(FdbusClientListener listener) {
        initialize(null, listener);
    }

    public FdbusClient(String name) {
        initialize(name, null);
    }

    public FdbusClient() {
        initialize(null, null);
    }

    public void setListener(FdbusClientListener listener) {
        this.mFdbusListener = listener;
    }

    public void destroy() {
        long handle = this.mNativeHandle;
        this.mNativeHandle = 0L;
        if (handle != 0) {
            fdb_destroy(handle);
        }
    }

    public boolean connect(String url) {
        return fdb_connect(this.mNativeHandle, url);
    }

    public boolean connect() {
        return connect("svc://" + busName());
    }

    public boolean disconnect() {
        return fdb_disconnect(this.mNativeHandle);
    }

    public boolean invokeAsync(int msg_code, Object msg, Object user_data, int timeout, int qos) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, logEnabled(1));
        if (builder == null) {
            return false;
        }
        return fdb_invoke_async(this.mNativeHandle, msg_code, builder.toBuffer(), builder.toString(), user_data, timeout, qos);
    }

    public boolean invokeAsync(int msg_code, Object msg, Object user_data) {
        return invokeAsync(msg_code, msg, user_data, 0, 5);
    }

    public FdbusMessage invokeSync(int msg_code, Object msg, int timeout, int qos) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, logEnabled(1));
        if (builder == null) {
            return null;
        }
        return fdb_invoke_sync(this.mNativeHandle, msg_code, builder.toBuffer(), builder.toString(), timeout, qos);
    }

    public FdbusMessage invokeSync(int msg_code, Object msg) {
        return invokeSync(msg_code, msg, 0, 5);
    }

    public boolean send(int msg_code, Object msg, int qos) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, logEnabled(1));
        if (builder == null) {
            return false;
        }
        return fdb_send(this.mNativeHandle, msg_code, builder.toBuffer(), builder.toString(), qos);
    }

    public boolean send(int msg_code, Object msg) {
        return send(msg_code, msg, 5);
    }

    public boolean subscribe(ArrayList<SubscribeItem> sub_list, int qos) {
        return fdb_subscribe(this.mNativeHandle, sub_list, qos);
    }

    public boolean subscribe(ArrayList<SubscribeItem> sub_list) {
        return subscribe(sub_list, 5);
    }

    public boolean unsubscribe(ArrayList<SubscribeItem> sub_list, int qos) {
        return fdb_unsubscribe(this.mNativeHandle, sub_list, qos);
    }

    public boolean unsubscribe(ArrayList<SubscribeItem> sub_list) {
        return unsubscribe(sub_list, 5);
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

    public boolean publish(int event, String topic, Object msg, boolean always_update, int qos) {
        FdbusMsgBuilder builder = Fdbus.encodeMessage(msg, logEnabled(1));
        if (builder == null) {
            return false;
        }
        return fdb_publish(this.mNativeHandle, event, topic, builder.toBuffer(), builder.toString(), always_update, qos);
    }

    public boolean publish(int event, String topic, Object msg) {
        return publish(event, topic, msg, false, 5);
    }

    public boolean getAsync(int event, String topic, Object user_data, int timeout, int qos) {
        return fdb_get_event_async(this.mNativeHandle, event, topic, user_data, timeout, qos);
    }

    public boolean getAsync(int event, String topic, Object user_data) {
        return getAsync(event, topic, user_data, 0, 5);
    }

    public FdbusMessage getSync(int event, String topic, int timeout, int qos) {
        return fdb_get_event_sync(this.mNativeHandle, event, topic, timeout, qos);
    }

    public FdbusMessage getSync(int event, String topic) {
        return getSync(event, topic, 0, 5);
    }

    private void callbackOnline(int sid, int qos) {
        FdbusClientListener fdbusClientListener = this.mFdbusListener;
        if (fdbusClientListener != null) {
            try {
                fdbusClientListener.onOnline(sid, qos);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void callbackOffline(int sid, int qos) {
        FdbusClientListener fdbusClientListener = this.mFdbusListener;
        if (fdbusClientListener != null) {
            try {
                fdbusClientListener.onOffline(sid, qos);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void callbackReply(int sid, int msg_code, byte[] payload, int status, Object user_data) {
        Log.d("fdbus", "callbackReply:sid=" + sid + "msg_code:" + msg_code);
        if (this.mFdbusListener != null) {
            Log.d("fdbus", "callbackReply:mFdbusListener!=null");
            FdbusMessage msg = new FdbusMessage(sid, msg_code, payload, user_data, status);
            try {
                Log.d("fdbus", "callbackReply:mFdbusListener start");
                this.mFdbusListener.onReply(msg);
                Log.d("fdbus", "callbackReply:mFdbusListener end");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void callbackGetEvent(int sid, int msg_code, String topic, byte[] payload, int status, Object user_data) {
        if (this.mFdbusListener != null) {
            FdbusMessage msg = new FdbusMessage(sid, msg_code, topic, payload, user_data, status);
            try {
                this.mFdbusListener.onGetEvent(msg);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void callbackBroadcast(int sid, int msg_code, String filter, byte[] payload) {
        if (this.mFdbusListener != null) {
            FdbusMessage msg = new FdbusMessage(sid, msg_code, payload);
            msg.topic(filter);
            try {
                this.mFdbusListener.onBroadcast(msg);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
