package e1;

import android.annotation.SuppressLint;
import android.view.View;

/* JADX INFO: compiled from: ViewUtilsApi19.java */
/* JADX INFO: loaded from: classes.dex */
class z extends e0 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f6173c = true;

    z() {
    }

    @Override // e1.e0
    public void a(View view) {
    }

    @Override // e1.e0
    @SuppressLint({"NewApi"})
    public float b(View view) {
        if (f6173c) {
            try {
                return view.getTransitionAlpha();
            } catch (NoSuchMethodError unused) {
                f6173c = false;
            }
        }
        return view.getAlpha();
    }

    @Override // e1.e0
    public void c(View view) {
    }

    @Override // e1.e0
    @SuppressLint({"NewApi"})
    public void e(View view, float f7) {
        if (f6173c) {
            try {
                view.setTransitionAlpha(f7);
                return;
            } catch (NoSuchMethodError unused) {
                f6173c = false;
            }
        }
        view.setAlpha(f7);
    }
}
