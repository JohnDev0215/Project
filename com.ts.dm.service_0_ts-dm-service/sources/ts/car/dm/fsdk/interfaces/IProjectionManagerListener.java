package ts.car.dm.fsdk.interfaces;

/* JADX INFO: loaded from: classes.dex */
public interface IProjectionManagerListener {
    void onDeviceDetachedOverUsb(ProjectionDevice projectionDevice);

    void onDeviceMerged(String str, String str2, ProjectionDevice projectionDevice);

    void onDeviceProjectionRequested(int i, ProjectionDevice projectionDevice);

    void onDeviceStatusChanged(ProjectionDevice projectionDevice);

    void onServiceConnected();

    void onServiceDisconnected();

    void oniPhoneAttachedOverUsb(boolean z, ProjectionDevice projectionDevice);
}
