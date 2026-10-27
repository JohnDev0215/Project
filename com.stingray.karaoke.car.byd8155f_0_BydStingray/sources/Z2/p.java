package z2;

import android.hardware.display.DisplayManager;

/* JADX INFO: loaded from: classes.dex */
public final class p implements DisplayManager.DisplayListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayManager f24350a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ r f24351b;

    public p(r rVar, DisplayManager displayManager) {
        this.f24351b = rVar;
        this.f24350a = displayManager;
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayAdded(int i7) {
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayChanged(int i7) {
        if (i7 == 0) {
            r.a(this.f24351b, this.f24350a.getDisplay(0));
        }
    }

    @Override // android.hardware.display.DisplayManager.DisplayListener
    public final void onDisplayRemoved(int i7) {
    }
}
