package org.chromium.components.browser_ui.photo_picker;

import J.N;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: loaded from: classes2.dex */
public class TestImageDecoderService extends Service {
    public final g e = new g();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.e;
    }

    @Override // android.app.Service
    public final void onCreate() {
        org.chromium.base.library_loader.a.l.a();
        g gVar = this.e;
        gVar.getClass();
        N.Mw4AD5hY();
        gVar.f = true;
    }
}
