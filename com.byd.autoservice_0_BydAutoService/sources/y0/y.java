package y0;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: compiled from: ViewOverlayApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class y implements a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f5994a;

    /* JADX INFO: compiled from: ViewOverlayApi14.java */
    public static class a extends ViewGroup {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Method f5995b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ViewGroup f5996c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f5997d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ArrayList<Drawable> f5998e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public y f5999f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f6000g;

        static {
            try {
                Class cls = Integer.TYPE;
                f5995b = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", cls, cls, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        public a(Context context, ViewGroup viewGroup, View view, y yVar) {
            super(context);
            this.f5998e = null;
            this.f5996c = viewGroup;
            this.f5997d = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.f5999f = yVar;
        }

        public void a(Drawable drawable) {
            c();
            if (this.f5998e == null) {
                this.f5998e = new ArrayList<>();
            }
            if (this.f5998e.contains(drawable)) {
                return;
            }
            this.f5998e.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public void b(View view) {
            c();
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != this.f5996c && viewGroup.getParent() != null && l0.t.O(viewGroup)) {
                    int[] iArr = new int[2];
                    int[] iArr2 = new int[2];
                    viewGroup.getLocationOnScreen(iArr);
                    this.f5996c.getLocationOnScreen(iArr2);
                    l0.t.U(view, iArr[0] - iArr2[0]);
                    l0.t.V(view, iArr[1] - iArr2[1]);
                }
                viewGroup.removeView(view);
                if (view.getParent() != null) {
                    viewGroup.removeView(view);
                }
            }
            super.addView(view);
        }

        public final void c() {
            if (this.f6000g) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        public final void d() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.f5998e;
                if (arrayList == null || arrayList.size() == 0) {
                    this.f6000g = true;
                    this.f5996c.removeView(this);
                }
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            this.f5996c.getLocationOnScreen(new int[2]);
            this.f5997d.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.f5997d.getWidth(), this.f5997d.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.f5998e;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f5998e.get(i3).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public final void e(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f5996c.getLocationOnScreen(iArr2);
            this.f5997d.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        public void f(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.f5998e;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
                d();
            }
        }

        public void g(View view) {
            super.removeView(view);
            d();
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.f5996c == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (!(this.f5996c instanceof ViewGroup)) {
                invalidate(rect);
                return null;
            }
            iArr[0] = 0;
            iArr[1] = 0;
            int[] iArr2 = new int[2];
            e(iArr2);
            rect.offset(iArr2[0], iArr2[1]);
            return super.invalidateChildInParent(iArr, rect);
        }

        @Override // android.view.View, android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            invalidate(drawable.getBounds());
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.f5998e) != null && arrayList.contains(drawable));
        }
    }

    public y(Context context, ViewGroup viewGroup, View view) {
        this.f5994a = new a(context, viewGroup, view, this);
    }

    public static y e(View view) {
        ViewGroup viewGroupF = f(view);
        if (viewGroupF == null) {
            return null;
        }
        int childCount = viewGroupF.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = viewGroupF.getChildAt(i3);
            if (childAt instanceof a) {
                return ((a) childAt).f5999f;
            }
        }
        return new t(viewGroupF.getContext(), viewGroupF, view);
    }

    public static ViewGroup f(View view) {
        while (view != null) {
            if (view.getId() == 16908290 && (view instanceof ViewGroup)) {
                return (ViewGroup) view;
            }
            if (view.getParent() instanceof ViewGroup) {
                view = (ViewGroup) view.getParent();
            }
        }
        return null;
    }

    @Override // y0.a0
    public void a(Drawable drawable) {
        this.f5994a.a(drawable);
    }

    @Override // y0.a0
    public void b(Drawable drawable) {
        this.f5994a.f(drawable);
    }
}
