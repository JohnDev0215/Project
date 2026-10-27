package com.neusoft.na.pos;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import com.neusoft.na.pos.IOneCorePosService;

/* JADX INFO: loaded from: classes2.dex */
public class OneCorePosService extends Service {
    private IOneCorePosService.Stub oneCorePosstance = new OneCorePosServiceImp();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.oneCorePosstance;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Process.killProcess(Process.myPid());
    }
}
