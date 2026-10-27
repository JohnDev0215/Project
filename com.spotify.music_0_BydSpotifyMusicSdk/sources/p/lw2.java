package p;

import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public abstract class lw2 {
    public static void a(Window.Callback callback, boolean z) {
        callback.onPointerCaptureChanged(z);
    }
}
