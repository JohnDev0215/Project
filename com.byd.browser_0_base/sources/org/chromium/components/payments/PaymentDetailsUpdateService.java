package org.chromium.components.payments;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import defpackage.a8;
import defpackage.jw0;

/* JADX INFO: loaded from: classes3.dex */
public class PaymentDetailsUpdateService extends Service {
    public final a e = new a();

    public class a extends jw0 {
        public static final /* synthetic */ int e = 0;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        if (a8.N("AndroidAppPaymentUpdateEvents")) {
            return this.e;
        }
        return null;
    }
}
