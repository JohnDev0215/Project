package org.chromium.chrome.browser.preferences;

import J.N;
import android.util.ArrayMap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class PrefChangeRegistrar {
    public final ArrayMap a = new ArrayMap();
    public long b = N.MiTdj3xP(this);

    public interface a {
        void f();
    }

    public final void a(String str, a aVar) {
        this.a.put(str, aVar);
        N.Mrf8X6ah(this.b, this, str);
    }

    public final void b() {
        long j = this.b;
        if (j != 0) {
            N.Mn0XciAY(j, this);
        }
        this.b = 0L;
    }

    @CalledByNative
    public final void onPreferenceChange(String str) {
        ((a) this.a.get(str)).f();
    }
}
