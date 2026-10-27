package com.byd.filemanager;

import android.app.Application;
import android.os.Process;
import android.util.Log;
import com.byd.affinity.ThreadAffinity;
import io.reactivex.exceptions.UndeliverableException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class FileApplication extends Application {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static FileApplication f3514b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f3515c = System.currentTimeMillis();

    class a implements d.a.m.e<Throwable> {
        a() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            com.byd.filemanager.k.b.d("Undeliverable exception", th);
            if (th instanceof UndeliverableException) {
                th = th.getCause();
            }
            if ((th instanceof IOException) || (th instanceof InterruptedException)) {
                return;
            }
            if ((th instanceof NullPointerException) || (th instanceof IllegalArgumentException)) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
            } else if (th instanceof IllegalStateException) {
                Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
            }
        }
    }

    public static FileApplication a() {
        return f3514b;
    }

    private void b() {
        d.a.o.a.r(new a());
    }

    @Override // android.app.Application
    public void onCreate() {
        Log.i("FileApplication", new ThreadAffinity().bindPidToCore(Process.myPid(), 3) + ",onCreate version : 1.18.2-202505230927");
        super.onCreate();
        f3514b = this;
        f.h().r();
        b();
        Log.i("FileApplication", "onCreate cast=" + (System.currentTimeMillis() - a().f3515c));
    }
}
