package com.android.services.telephony.sip.components;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.android.server.sip.SipService;

/* JADX INFO: loaded from: classes.dex */
public class TelephonySipService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || !"android.net.sip.action.START_SIP".equals(intent.getAction())) {
            return 1;
        }
        SipService.start(this);
        return 1;
    }
}
