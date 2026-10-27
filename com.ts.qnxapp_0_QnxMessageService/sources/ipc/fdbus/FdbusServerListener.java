package ipc.fdbus;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface FdbusServerListener {
    void onInvoke(FdbusMessage fdbusMessage);

    void onOffline(int i, boolean z, int i2);

    void onOnline(int i, boolean z, int i2);

    void onSubscribe(FdbusMessage fdbusMessage, ArrayList<SubscribeItem> arrayList);
}
