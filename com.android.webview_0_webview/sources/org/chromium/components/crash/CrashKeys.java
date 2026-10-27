package org.chromium.components.crash;

import J.N;
import defpackage.AbstractC0966ix;
import defpackage.AbstractC1022jx;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CrashKeys {
    public static final String[] c = {"loaded_dynamic_module", "active_dynamic_module", "application_status", "installed_modules", "emulated_modules", "dynamic_module_dex_name"};
    public final AtomicReferenceArray a = new AtomicReferenceArray(c.length);
    public boolean b;

    public CrashKeys(AbstractC0966ix abstractC0966ix) {
    }

    public static CrashKeys getInstance() {
        return AbstractC1022jx.a;
    }

    public void flushToNative() {
        ThreadUtils.b();
        for (int i = 0; i < this.a.length(); i++) {
            N.Mk$gnuuz(this, i, (String) this.a.getAndSet(i, null));
        }
        this.b = true;
    }

    public void set(int i, String str) {
        ThreadUtils.b();
        if (this.b) {
            N.Mk$gnuuz(this, i, str);
        } else {
            this.a.set(i, str);
        }
    }
}
