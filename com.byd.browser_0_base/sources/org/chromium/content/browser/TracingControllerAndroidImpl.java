package org.chromium.content.browser;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import defpackage.fa1;
import defpackage.ku2;
import defpackage.xz;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.R$string;
import org.chromium.ui.widget.Toast;

/* JADX INFO: loaded from: classes3.dex */
public class TracingControllerAndroidImpl {
    public final Context a;
    public final TracingIntentFilter c;
    public boolean d;
    public String f;
    public boolean g;
    public boolean h;
    public long i;
    public boolean e = true;
    public final a b = new a();

    public static class TracingIntentFilter extends IntentFilter {
        public TracingIntentFilter(Context context) {
            addAction(context.getPackageName() + ".GPU_PROFILER_START");
            addAction(context.getPackageName() + ".GPU_PROFILER_STOP");
            addAction(context.getPackageName() + ".GPU_PROFILER_LIST_CATEGORIES");
        }
    }

    public class a extends BroadcastReceiver {
        public a() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            boolean zEndsWith = intent.getAction().endsWith("GPU_PROFILER_START");
            TracingControllerAndroidImpl tracingControllerAndroidImpl = TracingControllerAndroidImpl.this;
            if (zEndsWith) {
                String stringExtra = intent.getStringExtra("categories");
                String strMy9pNx9O = TextUtils.isEmpty(stringExtra) ? N.My9pNx9O(tracingControllerAndroidImpl) : stringExtra.replaceFirst("_DEFAULT_CHROME_CATEGORIES", N.My9pNx9O(tracingControllerAndroidImpl));
                String str = intent.getStringExtra("continuous") == null ? "record-until-full" : "record-continuously";
                String stringExtra2 = intent.getStringExtra("file");
                if (stringExtra2 != null) {
                    TracingControllerAndroidImpl.this.a(stringExtra2, true, strMy9pNx9O, str, false, false);
                    return;
                } else {
                    TracingControllerAndroidImpl.this.a(null, true, strMy9pNx9O, str, false, false);
                    return;
                }
            }
            if (intent.getAction().endsWith("GPU_PROFILER_STOP")) {
                if (tracingControllerAndroidImpl.d) {
                    N.M$HKWu8q(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, tracingControllerAndroidImpl.f, tracingControllerAndroidImpl.g, tracingControllerAndroidImpl.h, null);
                }
            } else {
                if (!intent.getAction().endsWith("GPU_PROFILER_LIST_CATEGORIES")) {
                    fa1.b("TracingController", "Unexpected intent: %s", intent);
                    return;
                }
                if (tracingControllerAndroidImpl.i == 0) {
                    tracingControllerAndroidImpl.i = N.MWlLnA$6(tracingControllerAndroidImpl);
                }
                if (N.MdRNuqnW(tracingControllerAndroidImpl.i, tracingControllerAndroidImpl, null)) {
                    return;
                }
                Log.e("cr_TracingController", "Unable to fetch tracing category list.");
            }
        }
    }

    public TracingControllerAndroidImpl(Context context) {
        this.a = context;
        this.c = new TracingIntentFilter(context);
    }

    /* JADX WARN: Finally extract failed */
    @CalledByNative
    public static String generateTracingFilePath(String str) {
        ku2 ku2VarC = ku2.C();
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                ku2VarC.close();
                return null;
            }
            if (str.isEmpty()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HHmmss", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                str = "chrome-profile-results-" + simpleDateFormat.format(new Date());
            }
            String path = new File(xz.a.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), str).getPath();
            ku2VarC.close();
            return path;
        } catch (Throwable th) {
            try {
                ku2VarC.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final boolean a(String str, boolean z, String str2, String str3, boolean z2, boolean z3) {
        String str4;
        this.e = z;
        Context context = this.a;
        if (str == null) {
            String strGenerateTracingFilePath = generateTracingFilePath("");
            if (strGenerateTracingFilePath == null) {
                String string = context.getString(R$string.profiler_no_storage_toast);
                Log.e("cr_TracingController", string);
                if (this.e) {
                    Toast.b(0, context, string).e();
                }
                return false;
            }
            str4 = strGenerateTracingFilePath;
        } else {
            str4 = str;
        }
        if (this.d) {
            Log.e("cr_TracingController", "Received startTracing, but we're already tracing");
            return false;
        }
        if (this.i == 0) {
            this.i = N.MWlLnA$6(this);
        }
        if (!N.MZYMIGWv(this.i, this, str2, str3, z3)) {
            String string2 = context.getString(R$string.profiler_error_toast);
            Log.e("cr_TracingController", string2);
            if (this.e) {
                Toast.b(0, context, string2).e();
            }
            return false;
        }
        Log.i("cr_TracingController", String.format("Profiler started: %s", str2));
        String str5 = context.getString(R$string.profiler_started_toast) + ": " + str2;
        if (this.e) {
            Toast.b(0, context, str5).e();
        }
        this.f = str4;
        this.g = z2;
        this.h = z3;
        this.d = true;
        return true;
    }

    @CalledByNative
    public void onKnownCategoriesReceived(String[] strArr, Object obj) {
        if (obj != null) {
            ((Callback) obj).onResult(strArr);
        }
    }

    @CalledByNative
    public void onTraceBufferUsageReceived(float f, long j, Object obj) {
        ((Callback) obj).onResult(new Pair(Float.valueOf(f), Long.valueOf(j)));
    }

    @CalledByNative
    public void onTracingStopped(Object obj) {
        if (!this.d) {
            Log.e("cr_TracingController", "Received onTracingStopped, but we aren't tracing");
            return;
        }
        Log.i("cr_TracingController", String.format("Profiler finished. Results are in %s.", this.f));
        int i = R$string.profiler_stopped_toast;
        Object[] objArr = {this.f};
        Context context = this.a;
        String string = context.getString(i, objArr);
        if (this.e) {
            Toast.b(0, context, string).e();
        }
        this.d = false;
        this.f = null;
        this.g = false;
        if (obj != null) {
            ((Callback) obj).onResult(null);
        }
    }
}
