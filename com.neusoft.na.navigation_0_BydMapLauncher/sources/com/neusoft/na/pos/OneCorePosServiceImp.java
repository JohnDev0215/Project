package com.neusoft.na.pos;

import android.os.RemoteException;
import android.util.Log;
import com.neusoft.na.pos.IOneCorePosService;

/* JADX INFO: loaded from: classes2.dex */
public class OneCorePosServiceImp extends IOneCorePosService.Stub {
    private Positioning mPositioning = new Positioning();

    @Override // com.neusoft.na.pos.IOneCorePosService
    public void startPos(String str) throws RemoteException {
        Log.d("OneCorePosServiceImp", " changjiang OneCorePosJni.startPos");
        this.mPositioning.start(str);
    }

    @Override // com.neusoft.na.pos.IOneCorePosService
    public void stopPos() throws RemoteException {
        Log.d("OneCorePosServiceImp", " changjiang OneCorePosJni.stopPos");
        this.mPositioning.stop();
    }
}
