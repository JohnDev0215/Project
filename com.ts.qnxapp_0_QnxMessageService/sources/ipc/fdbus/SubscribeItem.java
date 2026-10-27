package ipc.fdbus;

import ipc.fdbus.FdbusAppListener;

/* JADX INFO: loaded from: classes.dex */
public class SubscribeItem {
    private FdbusAppListener.Action mCallback;
    private int mCode;
    private String mTopic;
    private FdbusAppListener xxx;

    public SubscribeItem(int code, String topic, FdbusAppListener.Action callback) {
        this.mCode = code;
        this.mTopic = topic;
        this.mCallback = callback;
    }

    public SubscribeItem(int code, String topic) {
        this.mCode = code;
        this.mTopic = topic;
        this.mCallback = null;
    }

    public static SubscribeItem newEvent(int code, String topic) {
        return new SubscribeItem(code, topic);
    }

    public static SubscribeItem newEvent(int code) {
        return new SubscribeItem(code, null);
    }

    public static SubscribeItem newAction(int code, String topic, FdbusAppListener.Action callback) {
        return new SubscribeItem(code, topic, callback);
    }

    public static SubscribeItem newAction(int code, FdbusAppListener.Action callback) {
        return new SubscribeItem(code, null, callback);
    }

    public static SubscribeItem newGroup(int group, String topic) {
        return new SubscribeItem(((group & 255) << 24) | 16777215, topic);
    }

    public static SubscribeItem newGroup(int group) {
        return new SubscribeItem(((group & 255) << 24) | 16777215, null);
    }

    public int code() {
        return this.mCode;
    }

    public String topic() {
        return this.mTopic;
    }
}
