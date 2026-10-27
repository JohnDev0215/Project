package defpackage;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

/* JADX INFO: compiled from: MemoryPressureUma.java */
/* JADX INFO: loaded from: classes.dex */
public final class we1 implements ComponentCallbacks2 {
    public final String e;

    public we1(String str) {
        this.e = "Android.MemoryPressureNotification.".concat(str);
    }

    public final void a(int i) {
        oj2.U(i, 9, this.e);
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
        a(8);
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        if (i == 5) {
            a(7);
            return;
        }
        if (i == 10) {
            a(6);
            return;
        }
        if (i == 15) {
            a(5);
            return;
        }
        if (i == 20) {
            a(4);
            return;
        }
        if (i == 40) {
            a(3);
            return;
        }
        if (i == 60) {
            a(2);
        } else if (i != 80) {
            a(0);
        } else {
            a(1);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }
}
