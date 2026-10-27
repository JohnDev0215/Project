package org.chromium.content.browser;

import J.N;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Environment;
import android.util.Pair;
import defpackage.AbstractC0279Pk;
import defpackage.AbstractC0566bn;
import defpackage.C0683dt;
import defpackage.C1602uP;
import defpackage.Hz;
import defpackage.Ts;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TracingControllerAndroidImpl {
    public final Context a;
    public final TracingIntentFilter c;
    public boolean d;
    public String f;
    public boolean g;
    public long h;
    public boolean e = true;
    public final Hz b = new Hz(this);

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class TracingIntentFilter extends IntentFilter {
        public TracingIntentFilter(Context context) {
            addAction(context.getPackageName() + ".GPU_PROFILER_START");
            addAction(context.getPackageName() + ".GPU_PROFILER_STOP");
            addAction(context.getPackageName() + ".GPU_PROFILER_LIST_CATEGORIES");
        }
    }

    public TracingControllerAndroidImpl(Context context) {
        this.a = context;
        this.c = new TracingIntentFilter(context);
    }

    public static String generateTracingFilePath() {
        C0683dt c0683dtA = C0683dt.A();
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                c0683dtA.close();
                return null;
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "chrome-profile-results-" + simpleDateFormat.format(new Date())).getPath();
            c0683dtA.close();
            return path;
        } catch (Throwable th) {
            try {
                c0683dtA.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public final void a(String str) {
        Ts.a("TracingController", str, new Object[0]);
        if (this.e) {
            C1602uP.a(this.a, str, 0).a.show();
        }
    }

    public boolean b(String str, boolean z, String str2, String str3, boolean z2) {
        this.e = z;
        if (str == null && (str = generateTracingFilePath()) == null) {
            a(this.a.getString(AbstractC0566bn.k2));
            return false;
        }
        if (this.d) {
            Ts.a("TracingController", "Received startTracing, but we're already tracing", new Object[0]);
            return false;
        }
        if (this.h == 0) {
            this.h = N.MWlLnA$6(this);
        }
        if (!N.MZYMIGWv(this.h, this, str2, str3)) {
            a(this.a.getString(AbstractC0566bn.j2));
            return false;
        }
        Ts.d("TracingController", String.format("Profiler started: %s", str2), new Object[0]);
        String str4 = this.a.getString(AbstractC0566bn.l2) + ": " + str2;
        if (this.e) {
            C1602uP.a(this.a, str4, 0).a.show();
        }
        this.f = str;
        this.g = z2;
        this.d = true;
        return true;
    }

    public void onKnownCategoriesReceived(String[] strArr, Object obj) {
        if (obj != null) {
            ((Callback) obj).a(strArr);
        }
    }

    public void onTraceBufferUsageReceived(float f, long j, Object obj) {
        ((Callback) obj).a(new Pair(Float.valueOf(f), Long.valueOf(j)));
    }

    public void onTracingStopped(Object obj) {
        if (!this.d) {
            Ts.a("TracingController", "Received onTracingStopped, but we aren't tracing", new Object[0]);
            return;
        }
        Ts.d("TracingController", String.format("Profiler finished. Results are in %s.", this.f), new Object[0]);
        String string = this.a.getString(AbstractC0566bn.m2, this.f);
        if (this.e) {
            C1602uP.a(this.a, string, 0).a.show();
        }
        this.d = false;
        this.f = null;
        this.g = false;
        if (obj != null) {
            ((Callback) obj).a(null);
        }
    }
}
