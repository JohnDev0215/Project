package F1;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class r extends B2.O {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f2345n;

    /* JADX WARN: Type inference failed for: r3v0, types: [F1.q] */
    @Override // B2.O
    public final void b0() {
        View view = this.f2345n;
        WindowInsetsController windowInsetsController = view != null ? view.getWindowInsetsController() : null;
        if (windowInsetsController == null) {
            super.b0();
            return;
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ?? r32 = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: F1.q
            @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
            public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController2, int i7) {
                atomicBoolean.set((i7 & 8) != 0);
            }
        };
        windowInsetsController.addOnControllableInsetsChangedListener(r32);
        if (!atomicBoolean.get() && view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        windowInsetsController.removeOnControllableInsetsChangedListener(r32);
        windowInsetsController.hide(WindowInsets.Type.ime());
    }

    @Override // B2.O
    public final void i0() {
        View view = this.f2345n;
        if (view != null && Build.VERSION.SDK_INT < 33) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).isActive();
        }
        WindowInsetsController windowInsetsController = view != null ? view.getWindowInsetsController() : null;
        if (windowInsetsController != null) {
            windowInsetsController.show(WindowInsets.Type.ime());
        }
        super.i0();
    }
}
