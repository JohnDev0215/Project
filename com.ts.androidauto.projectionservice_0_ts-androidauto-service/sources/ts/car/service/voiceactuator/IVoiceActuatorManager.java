package ts.car.service.voiceactuator;

import android.os.RemoteException;
import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IVoiceActuatorManager extends IPlatformService {
    int doVoice(VoiceData voiceData) throws RemoteException;

    boolean registerListener(IVoiceActuatorResultCallback iVoiceActuatorResultCallback) throws RemoteException;

    void release();

    boolean unregisterListener(IVoiceActuatorResultCallback iVoiceActuatorResultCallback) throws RemoteException;
}
