package defpackage;

import android.annotation.SuppressLint;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: TabIdManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class t03 {
    public static final Object c = new Object();

    @SuppressLint({"StaticFieldLeak"})
    public static t03 d;
    public final AtomicInteger a;
    public final SharedPreferencesManager b;

    public t03() {
        AtomicInteger atomicInteger = new AtomicInteger();
        this.a = atomicInteger;
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        this.b = sharedPreferencesManager;
        atomicInteger.set(sharedPreferencesManager.d(0, "org.chromium.chrome.browser.tab.TabIdManager.NEXT_ID"));
    }

    public static t03 a() {
        synchronized (c) {
            if (d == null) {
                d = new t03();
            }
        }
        return d;
    }
}
