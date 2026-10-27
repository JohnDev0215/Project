package r;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class e0 implements View.OnTouchListener {
    public final /* synthetic */ j0 l;

    public e0(j0 j0Var) {
        this.l = j0Var;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        C2264w c2264w;
        int action = motionEvent.getAction();
        int x8 = (int) motionEvent.getX();
        int y10 = (int) motionEvent.getY();
        j0 j0Var = this.l;
        if (action == 0 && (c2264w = j0Var.f21505G) != null && c2264w.isShowing() && x8 >= 0 && x8 < j0Var.f21505G.getWidth() && y10 >= 0 && y10 < j0Var.f21505G.getHeight()) {
            j0Var.f21501C.postDelayed(j0Var.f21518y, 250L);
            return false;
        }
        if (action != 1) {
            return false;
        }
        j0Var.f21501C.removeCallbacks(j0Var.f21518y);
        return false;
    }
}
