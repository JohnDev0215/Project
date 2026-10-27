package ts.car.service.input;

import android.os.RemoteException;
import android.view.KeyEvent;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IHardKeyAdminManager extends IPlatformService {
    void performKeyEvent(KeyEvent keyEvent, int i) throws RemoteException;

    void performKeyLongPress(KeyEvent keyEvent, int i, int i2) throws RemoteException;
}
