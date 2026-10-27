package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.io.File;
import java.util.Map;
import java.util.Objects;
import org.chromium.android_webview.AwBrowserProcess;

/* JADX INFO: renamed from: ln, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ServiceConnectionC1124ln implements ServiceConnection {
    public final /* synthetic */ File[] a;
    public final /* synthetic */ Map b;
    public final /* synthetic */ Context c;

    public ServiceConnectionC1124ln(File[] fileArr, Map map, Context context) {
        this.a = fileArr;
        this.b = map;
        this.c = context;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, final IBinder iBinder) {
        InterfaceC1020jv interfaceC1020jv = AwBrowserProcess.a;
        final File[] fileArr = this.a;
        final Map map = this.b;
        final Context context = this.c;
        interfaceC1020jv.a(new Runnable(this, fileArr, map, iBinder, context) { // from class: kn
            public final ServiceConnectionC1124ln s;
            public final File[] t;
            public final Map u;
            public final IBinder v;
            public final Context w;

            {
                this.s = this;
                this.t = fileArr;
                this.u = map;
                this.v = iBinder;
                this.w = context;
            }

            @Override // java.lang.Runnable
            public void run() {
                ServiceConnectionC1124ln serviceConnectionC1124ln = this.s;
                File[] fileArr2 = this.t;
                Map map2 = this.u;
                IBinder iBinder2 = this.v;
                Context context2 = this.w;
                Objects.requireNonNull(serviceConnectionC1124ln);
                AwBrowserProcess.a(fileArr2, map2, AbstractBinderC0303Qq.c(iBinder2));
                context2.unbindService(serviceConnectionC1124ln);
            }
        });
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
    }
}
