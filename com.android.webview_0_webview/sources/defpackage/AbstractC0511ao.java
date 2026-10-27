package defpackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.text.TextUtils;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: ao, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0511ao {
    public static final Pattern d = Pattern.compile("^file:///android_(asset|res)/.*");
    public final C1839yo a;
    public int b;
    public String c;

    public AbstractC0511ao() {
        Looper looperMyLooper = Looper.myLooper();
        this.b = 0;
        this.c = "";
        Trace.beginSection("AwContentsClient.constructorOneArg");
        try {
            this.a = new C1839yo(looperMyLooper, this);
            Trace.endSection();
        } catch (Throwable th) {
            try {
                Trace.endSection();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public static Uri[] f(int i, Intent intent) {
        if (i == 0) {
            return null;
        }
        Uri data = (intent == null || i != -1) ? null : intent.getData();
        if (data != null) {
            return new Uri[]{data};
        }
        return null;
    }

    public abstract void a(String str);

    public abstract void b(String str);

    public abstract void c(String str);

    public abstract void d(int i);

    public abstract void e(String str);

    public final void g(String str, boolean z) {
        if (z || !TextUtils.equals(this.c, str)) {
            this.c = str;
            Handler handler = this.a.d;
            handler.sendMessage(handler.obtainMessage(10, str));
        }
    }
}
