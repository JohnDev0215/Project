package ts.car.service.input;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IHardKeyManager extends IPlatformService {
    boolean addKeyEventListener(IKeyEventListener iKeyEventListener, int i) throws RemoteException;

    boolean addLongPressEventListener(IKeyLongPressEventListener iKeyLongPressEventListener, int i) throws RemoteException;

    boolean removeKeyEventListener(IKeyEventListener iKeyEventListener, int i) throws RemoteException;

    boolean removeLongPressEventListener(IKeyLongPressEventListener iKeyLongPressEventListener, int i) throws RemoteException;
}
