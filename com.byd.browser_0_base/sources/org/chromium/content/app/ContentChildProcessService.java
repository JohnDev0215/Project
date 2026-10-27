package org.chromium.content.app;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.support.v4.media.session.PlaybackStateCompat;
import defpackage.ao;
import defpackage.fa1;
import defpackage.uv;
import defpackage.xz;
import defpackage.y70;
import defpackage.zn;
import org.chromium.base.library_loader.a;

/* JADX INFO: loaded from: classes3.dex */
public class ContentChildProcessService extends Service {
    public zn e;

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        zn znVar = this.e;
        if (!znVar.m) {
            znVar.b.stopSelf();
            znVar.f = intent.getBooleanExtra("org.chromium.base.process_launcher.extra.bind_to_caller", false);
            znVar.m = true;
            ((ContentChildProcessServiceDelegate) znVar.a).getClass();
            a aVar = a.l;
            a.C0070a c0070a = aVar.g;
            Bundle extras = intent.getExtras();
            c0070a.getClass();
            c0070a.a = extras.getLong("org.chromium.base.android.linker.base_load_address", 0L);
            aVar.h(intent.getExtras().getInt("org.chromium.content.common.child_service_params.library_process_type", 2));
            String stringExtra = intent.getStringExtra("org.chromium.base.process_launcher.extra.browser_package_name");
            if (stringExtra == null) {
                stringExtra = znVar.c.getApplicationInfo().packageName;
            }
            new Handler(Looper.getMainLooper()).post(new y70(znVar, 4, stringExtra));
        }
        return znVar.o;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        zn znVar = new zn(new ContentChildProcessServiceDelegate(), this, getApplicationContext());
        this.e = znVar;
        fa1.h("ChildProcessService", "Creating new ChildProcessService pid=%d %s", Integer.valueOf(Process.myPid()), znVar.b);
        if (zn.p) {
            throw new uv("Illegal child process reuse.");
        }
        zn.p = true;
        xz.a = znVar.c;
        znVar.a.getClass();
        Thread thread = new Thread(null, new ao(znVar), "ChildProcessMain", Process.is64Bit() ? 8388608L : PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED);
        znVar.i = thread;
        thread.start();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        zn znVar = this.e;
        znVar.getClass();
        fa1.h("ChildProcessService", "Destroying ChildProcessService pid=%d %s", Integer.valueOf(Process.myPid()), znVar.b.toString());
        System.exit(0);
        this.e = null;
    }
}
