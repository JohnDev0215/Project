package v2;

import android.R;
import android.app.Dialog;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* JADX INFO: compiled from: InsetDialogOnTouchListener.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements View.OnTouchListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Dialog f9672e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f9673g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final int f9674h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final int f9675i;

    public a(Dialog dialog, Rect rect) {
        this.f9672e = dialog;
        this.f9673g = rect.left;
        this.f9674h = rect.top;
        this.f9675i = ViewConfiguration.get(dialog.getContext()).getScaledWindowTouchSlop();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        View viewFindViewById = view.findViewById(R.id.content);
        int left = this.f9673g + viewFindViewById.getLeft();
        int width = viewFindViewById.getWidth() + left;
        if (new RectF(left, this.f9674h + viewFindViewById.getTop(), width, viewFindViewById.getHeight() + r3).contains(motionEvent.getX(), motionEvent.getY())) {
            return false;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        if (motionEvent.getAction() == 1) {
            motionEventObtain.setAction(4);
        }
        view.performClick();
        return this.f9672e.onTouchEvent(motionEventObtain);
    }
}
