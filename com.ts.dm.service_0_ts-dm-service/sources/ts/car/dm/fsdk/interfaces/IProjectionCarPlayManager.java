package ts.car.dm.fsdk.interfaces;

import com.ts.car.core.annotation.FoundationInterface;

/* JADX INFO: loaded from: classes.dex */
@FoundationInterface
public interface IProjectionCarPlayManager extends IProjectionManager {
    boolean cancelWirelessCarPlay(String str);

    boolean checkWirelessCarPlayAvailability(String str, IDeviceCapabilityCheckCallback iDeviceCapabilityCheckCallback);
}
