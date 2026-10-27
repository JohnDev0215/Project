package org.chromium.components.dom_distiller.core;

import J.N;
import android.widget.RadioButton;
import defpackage.z7;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.dom_distiller.DistilledPagePrefsView;

/* JADX INFO: loaded from: classes2.dex */
public final class DistilledPagePrefs {
    public final long a;
    public final HashMap b = new HashMap();

    public static class DistilledPagePrefsObserverWrapper {
        public final a a;
        public final long b = N.MxAdC41V(this);

        public DistilledPagePrefsObserverWrapper(a aVar) {
            this.a = aVar;
        }

        @CalledByNative
        public final void onChangeFontFamily(int i) {
            z7.n0(i);
            DistilledPagePrefsView distilledPagePrefsView = (DistilledPagePrefsView) this.a;
            distilledPagePrefsView.getClass();
            z7.n0(i);
            distilledPagePrefsView.j.setSelection(i);
        }

        @CalledByNative
        public final void onChangeFontScaling(float f) {
            ((DistilledPagePrefsView) this.a).b(f);
        }

        @CalledByNative
        public final void onChangeTheme(int i) {
            z7.o0(i);
            DistilledPagePrefsView distilledPagePrefsView = (DistilledPagePrefsView) this.a;
            distilledPagePrefsView.getClass();
            z7.o0(i);
            ((RadioButton) distilledPagePrefsView.f.get(Integer.valueOf(i))).setChecked(true);
        }
    }

    public interface a {
    }

    public DistilledPagePrefs(long j) {
        this.a = N.MazIrxOm(this, j);
    }
}
