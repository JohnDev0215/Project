package defpackage;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.crash.ChromePureJavaExceptionReporter;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.components.crash.a;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: BrandingController.java */
/* JADX INFO: loaded from: classes.dex */
public final class bj {
    public static final k11 l;
    public static final k11 m;
    public static final sh n;
    public static final sh o;
    public final ll a;
    public final rs1<Integer> b;
    public final aj c;
    public final Context d;
    public final String e;
    public final boolean f;
    public sb3 g;
    public Toast h;
    public long i;
    public boolean j;
    public final a k;

    static {
        int millis = (int) TimeUnit.HOURS.toMillis(1L);
        l = new k11(500, "CCTBrandTransparency", "max_blank_toolbar_timeout");
        m = new k11(millis, "CCTBrandTransparency", "branding_cadence");
        n = new sh("CCTBrandTransparency", "use_temporary_storage", false);
        o = new sh("CCTBrandTransparency", "animate_toolbar_transition", true);
    }

    public bj(Context context, String str, String str2, ChromePureJavaExceptionReporter chromePureJavaExceptionReporter) {
        ll llVar = new ll();
        this.a = llVar;
        rs1<Integer> rs1Var = new rs1<>();
        this.b = rs1Var;
        this.d = context;
        this.e = str2;
        this.k = chromePureJavaExceptionReporter;
        this.f = o.c();
        rs1Var.h(llVar.b(new so(21, this)));
        if (pl2.b == null) {
            pl2.b = new pl2();
        }
        aj ajVar = new aj(str, pl2.b, new wb(19, rs1Var), m.c());
        this.c = ajVar;
        ajVar.e(d73.i);
    }

    public final void a() {
        if (this.g != null) {
            rs1<Integer> rs1Var = this.b;
            if (rs1Var.get() == null) {
                return;
            }
            long jElapsedRealtime = 1800 - (SystemClock.elapsedRealtime() - this.i);
            int iIntValue = rs1Var.get().intValue();
            ll llVar = this.a;
            if (iIntValue == 0) {
                ((CustomTabToolbar.b) this.g).z();
            } else if (iIntValue == 1) {
                CustomTabToolbar.b bVar = (CustomTabToolbar.b) this.g;
                bVar.s = true;
                if (!bVar.r) {
                    bVar.r = true;
                    bVar.v = Integer.valueOf(bVar.e);
                }
                bVar.x(false);
                bVar.t(false);
                bVar.o.f = true;
                bVar.y();
                PostTask.b(rh3.a, llVar.c(new aw(22, this)), jElapsedRealtime);
            } else if (iIntValue == 2) {
                ((CustomTabToolbar.b) this.g).z();
                if (this.j) {
                    Log.e("cr_CctBrand", "Toast should not get accessed after destroyed.");
                    a aVar = this.k;
                    if (aVar != null) {
                        aVar.a(new Throwable("Toast should not get accessed after destroyed."));
                    }
                } else {
                    Context context = this.d;
                    String string = context.getResources().getString(R$string.twa_running_in_chrome_template, this.e);
                    TextView textView = (TextView) LayoutInflater.from(context).inflate(R$layout.custom_tabs_toast_branding_layout, (ViewGroup) null, false);
                    textView.setText(string);
                    Toast toast = new Toast(context.getApplicationContext(), textView);
                    this.h = toast;
                    toast.a.setDuration((int) jElapsedRealtime);
                    this.h.e();
                }
            }
            PostTask.c(d73.f, llVar.c(new wz(8)));
        }
    }
}
