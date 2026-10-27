package com.byd.cleardata;

import android.app.Application;
import android.content.Intent;
import c.a.k.e;
import c.a.l.d;
import com.byd.cleardata.service.FileScanService;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class FileApplication extends Application {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static FileApplication f2563b;

    class a implements d<Throwable> {
        a() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            com.byd.cleardata.i.c.d("Undeliverable exception", th);
            if (th instanceof e) {
                th = th.getCause();
            }
            if ((th instanceof IOException) || (th instanceof InterruptedException)) {
                return;
            }
            if ((th instanceof NullPointerException) || (th instanceof IllegalArgumentException) || (th instanceof IllegalStateException)) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
            }
        }
    }

    public static FileApplication a() {
        return f2563b;
    }

    private void b() {
        c.a.n.a.q(new a());
    }

    @Override // android.app.Application
    public void onCreate() {
        com.byd.cleardata.i.c.b("onCreate application");
        super.onCreate();
        f2563b = this;
        b.h().q();
        startService(new Intent(this, (Class<?>) FileScanService.class));
        b();
    }
}
