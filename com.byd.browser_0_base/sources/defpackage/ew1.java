package defpackage;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import java.util.function.BooleanSupplier;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;

/* JADX INFO: compiled from: PartialCustomTabHandleStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class ew1 extends GestureDetector.SimpleOnGestureListener implements CustomTabToolbar.c {
    public final GestureDetector a;
    public float b;
    public float c;
    public boolean d;
    public final VelocityTracker e = VelocityTracker.obtain();
    public Runnable f;
    public final BooleanSupplier g;
    public final nw2<Integer> h;
    public final a i;

    /* JADX INFO: compiled from: PartialCustomTabHandleStrategy.java */
    public interface a {
    }

    public ew1(Activity activity, BooleanSupplier booleanSupplier, nw2 nw2Var, a aVar) {
        this.g = booleanSupplier;
        this.h = nw2Var;
        this.i = aVar;
        this.a = new GestureDetector(activity, this, ThreadUtils.c());
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return true;
    }
}
