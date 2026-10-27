package com.neusoft.na.sens;

import android.os.RemoteException;
import com.neusoft.na.sens.IOneCoreSenService;
import com.neusoft.na.sens.jni.OneCoreSenJni;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreSenServiceImp extends IOneCoreSenService.Stub {
    @Override // com.neusoft.na.sens.IOneCoreSenService
    public void startSens(String str) throws RemoteException {
        OneCoreSenJni.getInstance().startSens(str);
    }

    @Override // com.neusoft.na.sens.IOneCoreSenService
    public void stopSens() throws RemoteException {
        OneCoreSenJni.getInstance().stopSens();
    }
}
