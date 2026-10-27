package com.neusoft.na.sens;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import com.neusoft.na.sens.IOneCoreSenService;

/* JADX INFO: loaded from: classes2.dex */
public class OneCoreSenService extends Service {
    private IOneCoreSenService.Stub oneCoreSenstance = null;
    private OneCoreSenRegister register = null;

    @Override // android.app.Service
    public void onCreate() {
        this.oneCoreSenstance = new OneCoreSenServiceImp();
        OneCoreSenRegister oneCoreSenRegister = OneCoreSenRegister.getInstance();
        this.register = oneCoreSenRegister;
        oneCoreSenRegister.registerFiter(this, false);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Process.killProcess(Process.myPid());
        this.register.unregisterFiter();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.oneCoreSenstance;
    }
}
