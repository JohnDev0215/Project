package org.chromium.device.power_save_blocker;

import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class PowerSaveBlocker {
    public static final WeakHashMap<View, Integer> b = new WeakHashMap<>();
    public WeakReference<View> a;

    @CalledByNative
    public static PowerSaveBlocker create() {
        return new PowerSaveBlocker();
    }

    @CalledByNative
    public final void applyBlock(View view) {
        this.a = new WeakReference<>(view);
        WeakHashMap<View, Integer> weakHashMap = b;
        Integer num = weakHashMap.get(view);
        if (num == null) {
            weakHashMap.put(view, 1);
        } else {
            weakHashMap.put(view, Integer.valueOf(num.intValue() + 1));
        }
        if (num == null || num.intValue() == 0) {
            view.setKeepScreenOn(true);
        }
    }

    @CalledByNative
    public final void removeBlock() {
        WeakReference<View> weakReference = this.a;
        if (weakReference == null) {
            return;
        }
        View view = weakReference.get();
        this.a = null;
        if (view == null) {
            return;
        }
        WeakHashMap<View, Integer> weakHashMap = b;
        Integer num = weakHashMap.get(view);
        weakHashMap.put(view, Integer.valueOf(num.intValue() - 1));
        if (num.intValue() == 1) {
            view.setKeepScreenOn(false);
        }
    }
}
