package ts.car.service.bt.phone;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothPhoneBookListener {
    default void onCalllogUpdated(String address, List<CallLogItem> callLogItems) {
    }

    default void onSyncJobFinished(String address, int handle, int result) {
    }

    default void onSyncJobProgressChanged(String address, int handle, int current, int phoneBookSize) {
    }
}
