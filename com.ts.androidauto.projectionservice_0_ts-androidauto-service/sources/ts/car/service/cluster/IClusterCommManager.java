package ts.car.service.cluster;

import android.os.Bundle;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IClusterCommManager extends IPlatformService {
    boolean registerCallback(int i, IClusterCommCallback iClusterCommCallback);

    boolean sendCommand(int i, Bundle bundle);

    boolean unregisterCallback(int i, IClusterCommCallback iClusterCommCallback);
}
