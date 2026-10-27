package ipc.fdbus;

/* JADX INFO: loaded from: classes.dex */
public interface FdbusClientListener {
    void onBroadcast(FdbusMessage fdbusMessage);

    void onGetEvent(FdbusMessage fdbusMessage);

    void onOffline(int i, int i2);

    void onOnline(int i, int i2);

    void onReply(FdbusMessage fdbusMessage);
}
