package F0;

import android.view.MotionEvent;

/* JADX INFO: loaded from: classes.dex */
public final class G0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final G0 f2023a = new G0();

    public final boolean a(MotionEvent motionEvent, int i7) {
        float rawX = motionEvent.getRawX(i7);
        if (!Float.isInfinite(rawX) && !Float.isNaN(rawX)) {
            float rawY = motionEvent.getRawY(i7);
            if (!Float.isInfinite(rawY) && !Float.isNaN(rawY)) {
                return true;
            }
        }
        return false;
    }
}
