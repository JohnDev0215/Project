package android.hardware;

/* JADX INFO: loaded from: classes.dex */
public interface IBYDAutoEvent {
    byte[] getBufferData();

    Object getData();

    int getDeviceType();

    double getDoubleValue();

    int getEventType();

    int getValue();

    void setData(Object obj);
}
