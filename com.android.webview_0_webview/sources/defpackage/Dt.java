package defpackage;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Dt implements ComponentCallbacks2 {
    public static Dt t;
    public final String s;

    public Dt(String str) {
        this.s = Z7.c("Android.MemoryPressureNotification.", str);
    }

    public static void a() {
        ThreadUtils.b();
        t = new Dt("ChildService");
        Is.a.registerComponentCallbacks(t);
    }

    public final void b(int i) {
        It.d(this.s, i, 9);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        b(8);
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        if (i == 5) {
            b(7);
            return;
        }
        if (i == 10) {
            b(6);
            return;
        }
        if (i == 15) {
            b(5);
            return;
        }
        if (i == 20) {
            b(4);
            return;
        }
        if (i == 40) {
            b(3);
            return;
        }
        if (i == 60) {
            b(2);
        } else if (i != 80) {
            b(0);
        } else {
            b(1);
        }
    }
}
