package org.chromium.device.power_save_blocker;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PowerSaveBlocker {
    public static WeakHashMap b = new WeakHashMap();
    public WeakReference a;

    private void applyBlock(View view) {
        this.a = new WeakReference(view);
        Integer num = (Integer) b.get(view);
        if (num == null) {
            b.put(view, 1);
        } else {
            b.put(view, Integer.valueOf(num.intValue() + 1));
        }
        if (num == null || num.intValue() == 0) {
            view.setKeepScreenOn(true);
        }
    }

    public static PowerSaveBlocker create() {
        return new PowerSaveBlocker();
    }

    private void removeBlock() {
        WeakReference weakReference = this.a;
        if (weakReference == null) {
            return;
        }
        View view = (View) weakReference.get();
        this.a = null;
        if (view == null) {
            return;
        }
        Integer num = (Integer) b.get(view);
        b.put(view, Integer.valueOf(num.intValue() - 1));
        if (num.intValue() == 1) {
            view.setKeepScreenOn(false);
        }
    }
}
