package com.android.car.media.byd;

import android.content.res.Configuration;
import androidx.annotation.NonNull;
import com.android.car.media.BaseMediaApplication;
import com.android.car.media.netObserver.NetStateChangeReceiver;
import spi.r7;
import spi.t6;

/* JADX INFO: loaded from: classes.dex */
public class MediaApplication extends BaseMediaApplication {
    public t6 c;

    @Override // com.android.car.media.BaseMediaApplication
    public final void a() {
        r7.a.a.a(this);
    }

    @Override // com.android.car.media.BaseMediaApplication, android.app.Application, android.content.ComponentCallbacks
    public final void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*  JADX ERROR: JadxRuntimeException in pass: ModVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't change immutable type java.lang.Object to com.android.car.media.byd.MediaApplication for r5v2 'this'  java.lang.Object
        	at jadx.core.dex.instructions.args.SSAVar.setType(SSAVar.java:114)
        	at jadx.core.dex.instructions.args.RegisterArg.setType(RegisterArg.java:52)
        	at jadx.core.dex.visitors.ModVisitor.removeCheckCast(ModVisitor.java:417)
        	at jadx.core.dex.visitors.ModVisitor.replaceStep(ModVisitor.java:152)
        	at jadx.core.dex.visitors.ModVisitor.visit(ModVisitor.java:96)
        */
    @Override // com.android.car.media.BaseMediaApplication, android.app.Application
    @android.annotation.SuppressLint({"UnspecifiedRegisterReceiverFlag"})
    public final void onCreate() {
        /*
            Method dump skipped, instruction units count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.media.byd.MediaApplication.onCreate():void");
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<android.content.Context>] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.util.ArrayList, java.util.List<android.content.Context>] */
    @Override // android.app.Application
    public final void onTerminate() {
        NetStateChangeReceiver.d(this.c);
        NetStateChangeReceiver netStateChangeReceiver = NetStateChangeReceiver.a.a;
        ?? r1 = netStateChangeReceiver.b;
        if (r1 != 0 && r1.contains(this)) {
            try {
                unregisterReceiver(netStateChangeReceiver);
            } catch (Exception e) {
                e.printStackTrace();
            }
            NetStateChangeReceiver.a.a.b.remove(this);
        }
        super.onTerminate();
    }
}
