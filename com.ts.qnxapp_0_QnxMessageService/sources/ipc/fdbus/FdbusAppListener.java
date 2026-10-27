package ipc.fdbus;

/* JADX INFO: loaded from: classes.dex */
public interface FdbusAppListener {

    public interface Action {
        void handleMessage(FdbusMessage fdbusMessage);
    }

    public interface Connection {
        void onConnectionStatus(int i, boolean z, boolean z2, int i2);
    }
}
