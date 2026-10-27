package defpackage;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.transition.R$id;
import defpackage.om3;
import defpackage.wn3;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: GhostViewPort.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
public final class ws0 extends ViewGroup implements us0 {
    public static final /* synthetic */ int k = 0;
    public ViewGroup e;
    public View f;
    public final View g;
    public int h;
    public Matrix i;
    public final a j;

    /* JADX INFO: compiled from: GhostViewPort.java */
    public class a implements ViewTreeObserver.OnPreDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public final boolean onPreDraw() {
            View view;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            ws0 ws0Var = ws0.this;
            om3.d.j(ws0Var);
            ViewGroup viewGroup = ws0Var.e;
            if (viewGroup == null || (view = ws0Var.f) == null) {
                return true;
            }
            viewGroup.endViewTransition(view);
            om3.d.j(ws0Var.e);
            ws0Var.e = null;
            ws0Var.f = null;
            return true;
        }
    }

    public ws0(View view) {
        super(view.getContext());
        this.j = new a();
        this.g = view;
        setWillNotDraw(false);
        setClipChildren(false);
        setLayerType(2, null);
    }

    public static void a(View view, ViewGroup viewGroup) {
        int left = viewGroup.getLeft();
        int top = viewGroup.getTop();
        int width = view.getWidth() + viewGroup.getLeft();
        int height = view.getHeight() + viewGroup.getTop();
        wn3.a aVar = wn3.a;
        viewGroup.setLeftTopRightBottom(left, top, width, height);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int i = R$id.ghost_view;
        View view = this.g;
        view.setTag(i, this);
        view.getViewTreeObserver().addOnPreDrawListener(this.j);
        wn3.a aVar = wn3.a;
        view.setTransitionVisibility(4);
        if (view.getParent() != null) {
            ((View) view.getParent()).invalidate();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        View view = this.g;
        view.getViewTreeObserver().removeOnPreDrawListener(this.j);
        wn3.a aVar = wn3.a;
        view.setTransitionVisibility(0);
        view.setTag(R$id.ghost_view, null);
        if (view.getParent() != null) {
            ((View) view.getParent()).invalidate();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        canvas.enableZ();
        canvas.setMatrix(this.i);
        wn3.a aVar = wn3.a;
        View view = this.g;
        view.setTransitionVisibility(0);
        view.invalidate();
        view.setTransitionVisibility(4);
        drawChild(canvas, view, getDrawingTime());
        canvas.disableZ();
    }

    @Override // android.view.View, defpackage.us0
    public final void setVisibility(int i) {
        super.setVisibility(i);
        int i2 = R$id.ghost_view;
        View view = this.g;
        if (((ws0) view.getTag(i2)) == this) {
            int i3 = i == 0 ? 4 : 0;
            wn3.a aVar = wn3.a;
            view.setTransitionVisibility(i3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }
}
