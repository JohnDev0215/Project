package p;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class hb1 implements View.OnTouchListener {
    public final /* synthetic */ ib1 c;

    public hb1(ib1 ib1Var) {
        this.c = ib1Var;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        d9 d9Var;
        int action = motionEvent.getAction();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        ib1 ib1Var = this.c;
        if (action == 0 && (d9Var = ib1Var.G) != null && d9Var.isShowing() && x >= 0 && x < ib1Var.G.getWidth() && y >= 0 && y < ib1Var.G.getHeight()) {
            ib1Var.C.postDelayed(ib1Var.y, 250L);
            return false;
        }
        if (action != 1) {
            return false;
        }
        ib1Var.C.removeCallbacks(ib1Var.y);
        return false;
    }
}
