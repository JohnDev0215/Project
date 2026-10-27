package defpackage;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.base.process_launcher.FileDescriptorInfo;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Bu {
    public static boolean p;
    public final Cu a;
    public final Service b;
    public final Context c;
    public boolean f;
    public int g;
    public String h;
    public Thread i;
    public String[] j;
    public FileDescriptorInfo[] k;
    public boolean l;
    public boolean m;
    public Ju n;
    public final Object d = new Object();
    public final Object e = new Object();
    public final Fu o = new BinderC1900zu(this);

    public Bu(Cu cu, Service service, Context context) {
        this.a = cu;
        this.b = service;
        this.c = context;
    }

    public IBinder a(Intent intent) {
        if (this.m) {
            return this.o;
        }
        this.b.stopSelf();
        this.f = intent.getBooleanExtra("org.chromium.base.process_launcher.extra.bind_to_caller", false);
        this.m = true;
        ContentChildProcessServiceDelegate contentChildProcessServiceDelegate = (ContentChildProcessServiceDelegate) this.a;
        Objects.requireNonNull(contentChildProcessServiceDelegate);
        Bundle extras = intent.getExtras();
        contentChildProcessServiceDelegate.a = (extras.containsKey("org.chromium.content.common.linker_params.base_load_address") && extras.containsKey("org.chromium.content.common.linker_params.wait_for_shared_relro") && extras.containsKey("org.chromium.content.common.linker_params.test_runner_class_name") && extras.containsKey("org.chromium.content.common.linker_params.linker_implementation")) ? new C1794xy(extras) : null;
        C1569tt.l.m(intent.getExtras().getInt("org.chromium.content.common.child_service_params.library_process_type", 2));
        new Handler(Looper.getMainLooper()).post(new Runnable(this) { // from class: xu
            public final Bu s;

            {
                this.s = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                Bu bu = this.s;
                Cu cu = bu.a;
                Context context = bu.c;
                Objects.requireNonNull((ContentChildProcessServiceDelegate) cu);
                C1569tt c1569tt = C1569tt.l;
                synchronized (c1569tt.c) {
                    if (!c1569tt.g) {
                        c1569tt.e = false;
                        c1569tt.f = false;
                        c1569tt.g = true;
                    }
                    if (c1569tt.e) {
                        return;
                    }
                    context.getApplicationInfo();
                    TraceEvent traceEventM = TraceEvent.M("LibraryLoader.preloadAlreadyLocked");
                    if (traceEventM != null) {
                        traceEventM.close();
                    }
                }
            }
        });
        return this.o;
    }

    public void b() {
        Ts.d("ChildProcessService", "Creating new ChildProcessService pid=%d", Integer.valueOf(Process.myPid()));
        if (p) {
            throw new RuntimeException("Illegal child process reuse.");
        }
        p = true;
        Is.a = this.c;
        Objects.requireNonNull((ContentChildProcessServiceDelegate) this.a);
        Thread thread = new Thread(new Au(this), "ChildProcessMain");
        this.i = thread;
        thread.start();
    }

    public void c() {
        Ts.d("ChildProcessService", "Destroying ChildProcessService pid=%d", Integer.valueOf(Process.myPid()));
        System.exit(0);
    }
}
