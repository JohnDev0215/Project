package defpackage;

import J.N;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.b;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tracing.settings.TracingSettings;
import org.chromium.components.browser_ui.notifications.NotificationManagerProxyImpl;
import org.chromium.content.browser.TracingControllerAndroidImpl;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: TracingController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class te3 {
    public static te3 f;
    public TracingControllerAndroidImpl a;
    public final org.chromium.base.b<b> b = new org.chromium.base.b<>();
    public int c = 0;
    public HashSet d;
    public File e;

    /* JADX INFO: compiled from: TracingController.java */
    public class a extends i9<File> {
        public a() {
        }

        @Override // defpackage.i9
        public final File b() {
            File file = new File(xz.a.getCacheDir() + "/traces");
            file.mkdir();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                File file2 = new File(file, "chrome-trace-" + simpleDateFormat.format(new Date()) + ".pftrace.gz");
                file2.createNewFile();
                return file2;
            } catch (IOException e) {
                fa1.b("TracingController", "Couldn't create chrome-trace temp file: %s", e.getMessage());
                return null;
            }
        }

        @Override // defpackage.i9
        public final void l(File file) {
            File file2 = file;
            te3 te3Var = te3.this;
            if (file2 == null) {
                te3Var.getClass();
                Toast.b(0, xz.a, "Error occurred while recording Chrome trace, see log for details.").e();
                te3Var.b(1);
                return;
            }
            te3Var.e = file2;
            if (!te3Var.a.a(te3Var.e.getPath(), false, TextUtils.join(",", TracingSettings.N0()), TracingSettings.O0(), true, true)) {
                Log.e("cr_TracingController", "Native error while trying to start tracing");
                Toast.b(0, xz.a, "Error occurred while recording Chrome trace, see log for details.").e();
                te3Var.b(1);
                return;
            }
            te3Var.b(3);
            if (te3Var.c != 3) {
                return;
            }
            TracingControllerAndroidImpl tracingControllerAndroidImpl = te3Var.a;
            x10 x10Var = new x10(11, te3Var);
            if (tracingControllerAndroidImpl.i == 0) {
                tracingControllerAndroidImpl.i = N.MWlLnA$6(tracingControllerAndroidImpl);
            }
            N.MkLMghix(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, x10Var);
        }
    }

    /* JADX INFO: compiled from: TracingController.java */
    public interface b {
        void p();
    }

    /* JADX INFO: compiled from: TracingController.java */
    public static class c implements Runnable {
        public final File e;

        public c(File file) {
            this.e = file;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.e.delete();
        }
    }

    public te3() {
        PostTask.c(d73.g, new qe3(19));
    }

    public static te3 a() {
        if (f == null) {
            te3 te3Var = new te3();
            f = te3Var;
            TracingControllerAndroidImpl tracingControllerAndroidImpl = new TracingControllerAndroidImpl(xz.a);
            te3Var.a = tracingControllerAndroidImpl;
            jb1 jb1Var = new jb1(19, te3Var);
            if (tracingControllerAndroidImpl.i == 0) {
                tracingControllerAndroidImpl.i = N.MWlLnA$6(tracingControllerAndroidImpl);
            }
            N.MdRNuqnW(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, jb1Var);
        }
        return f;
    }

    public final void b(int i) {
        this.c = i;
        if (i == 1) {
            new NotificationManagerProxyImpl(xz.a).d(100, "tracing_status");
            ue3.a = null;
            File file = this.e;
            if (file != null) {
                PostTask.c(d73.g, new c(file));
                this.e = null;
            }
            TracingControllerAndroidImpl tracingControllerAndroidImpl = this.a;
            long j = tracingControllerAndroidImpl.i;
            if (j != 0) {
                N.MLYQdwUF(j, tracingControllerAndroidImpl);
                tracingControllerAndroidImpl.i = 0L;
            }
            this.a = null;
        }
        Iterator<b> it = this.b.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).p();
            }
        }
    }
}
