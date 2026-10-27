package ts.car.dm.fsdk.interfaces;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IProjectionManager {

    public interface Result {
        public static final int ERROR = -1;
        public static final int FAILED = 0;
        public static final int SUCCESS = 1;
    }

    int deleteProjection(String str);

    ProjectionDevice getProjectionDevice(String str);

    List<ProjectionDevice> getUsbDeviceList();
}
