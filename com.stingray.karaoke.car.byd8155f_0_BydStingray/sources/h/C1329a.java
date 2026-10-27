package h;

import android.window.BackEvent;

/* JADX INFO: renamed from: h.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1329a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C1329a f15091a = new C1329a();

    public final BackEvent a(float f10, float f11, float f12, int i7) {
        return new BackEvent(f10, f11, f12, i7);
    }

    public final float b(BackEvent backEvent) {
        A7.m.f(backEvent, "backEvent");
        return backEvent.getProgress();
    }

    public final int c(BackEvent backEvent) {
        A7.m.f(backEvent, "backEvent");
        return backEvent.getSwipeEdge();
    }

    public final float d(BackEvent backEvent) {
        A7.m.f(backEvent, "backEvent");
        return backEvent.getTouchX();
    }

    public final float e(BackEvent backEvent) {
        A7.m.f(backEvent, "backEvent");
        return backEvent.getTouchY();
    }
}
