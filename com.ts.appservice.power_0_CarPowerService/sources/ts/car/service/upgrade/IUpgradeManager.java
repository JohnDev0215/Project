package ts.car.service.upgrade;

import android.os.RemoteException;
import java.util.List;
import ts.car.service.IPlatformService;
import ts.car.service.upgrade.data.UpgradeRequest;

/* JADX INFO: loaded from: classes.dex */
public interface IUpgradeManager extends IPlatformService {
    String getCurrentVersion() throws RemoteException;

    List<String> getModuleList(String str) throws RemoteException;

    String getTargetVersion(String str) throws RemoteException;

    List<String> getUpgradeList() throws RemoteException;

    int getUpgradeProgress() throws RemoteException;

    boolean isPackageValid(String str) throws RemoteException;

    boolean isUpgrading() throws RemoteException;

    boolean registerUpgradeListener(IUpgradeListener iUpgradeListener) throws RemoteException;

    boolean unregisterUpgradeListener(IUpgradeListener iUpgradeListener) throws RemoteException;

    boolean upgrade(UpgradeRequest upgradeRequest) throws RemoteException;
}
