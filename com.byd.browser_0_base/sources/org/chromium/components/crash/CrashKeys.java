package org.chromium.components.crash;

import J.N;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class CrashKeys {
    public static final String[] c = {"loaded_dynamic_module", "active_dynamic_module", "application_status", "installed_modules", "emulated_modules", "dynamic_module_dex_name", "partner_customization_config", "first_run"};
    public final AtomicReferenceArray<String> a = new AtomicReferenceArray<>(8);
    public boolean b;

    public static class a {
        public static final CrashKeys a = new CrashKeys();
    }

    @CalledByNative
    public static CrashKeys getInstance() {
        return a.a;
    }

    @CalledByNative
    public void flushToNative() {
        Object obj = ThreadUtils.a;
        int i = 0;
        while (true) {
            AtomicReferenceArray<String> atomicReferenceArray = this.a;
            if (i >= atomicReferenceArray.length()) {
                this.b = true;
                return;
            } else {
                N.Mk$gnuuz(this, i, atomicReferenceArray.getAndSet(i, null));
                i++;
            }
        }
    }

    @CalledByNative
    public void set(int i, String str) {
        Object obj = ThreadUtils.a;
        if (this.b) {
            N.Mk$gnuuz(this, i, str);
        } else {
            this.a.set(i, str);
        }
    }
}
