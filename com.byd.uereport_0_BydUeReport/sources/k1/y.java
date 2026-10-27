package k1;

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
    public a f5836a;

    /* JADX INFO: compiled from: ViewOverlayApi14.java */
    public static class a extends ViewGroup {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static Method f5837d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f5838e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f5839f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ArrayList<Drawable> f5840g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public y f5841h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f5842i;

        static {
            try {
                Class cls = Integer.TYPE;
                f5837d = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", cls, cls, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        public a(Context context, ViewGroup viewGroup, View view, y yVar) {
            super(context);
            this.f5840g = null;
            this.f5838e = viewGroup;
            this.f5839f = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.f5841h = yVar;
        }

        public void a(Drawable drawable) {
            c();
            if (this.f5840g == null) {
                this.f5840g = new ArrayList<>();
            }
            if (this.f5840g.contains(drawable)) {
                return;
            }
            this.f5840g.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public void b(View view) {
            c();
            if (view.getParent() instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != this.f5838e && viewGroup.getParent() != null && o0.t.P(viewGroup)) {
                    int[] iArr = new int[2];
                    int[] iArr2 = new int[2];
                    viewGroup.getLocationOnScreen(iArr);
                    this.f5838e.getLocationOnScreen(iArr2);
                    o0.t.V(view, iArr[0] - iArr2[0]);
                    o0.t.W(view, iArr[1] - iArr2[1]);
                }
                viewGroup.removeView(view);
                if (view.getParent() != null) {
                    viewGroup.removeView(view);
                }
            }
            super.addView(view);
        }

        public final void c() {
            if (this.f5842i) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        public final void d() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.f5840g;
                if (arrayList == null || arrayList.size() == 0) {
                    this.f5842i = true;
                    this.f5838e.removeView(this);
                }
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            this.f5838e.getLocationOnScreen(new int[2]);
            this.f5839f.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.f5839f.getWidth(), this.f5839f.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.f5840g;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                this.f5840g.get(i8).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public final void e(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f5838e.getLocationOnScreen(iArr2);
            this.f5839f.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        public void f(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.f5840g;
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
            if (this.f5838e == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (!(this.f5838e instanceof ViewGroup)) {
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
        public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.f5840g) != null && arrayList.contains(drawable));
        }
    }

    public y(Context context, ViewGroup viewGroup, View view) {
        this.f5836a = new a(context, viewGroup, view, this);
    }

    public static y e(View view) {
        ViewGroup viewGroupF = f(view);
        if (viewGroupF == null) {
            return null;
        }
        int childCount = viewGroupF.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = viewGroupF.getChildAt(i8);
            if (childAt instanceof a) {
                return ((a) childAt).f5841h;
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

    @Override // k1.a0
    public void a(Drawable drawable) {
        this.f5836a.a(drawable);
    }

    @Override // k1.a0
    public void b(Drawable drawable) {
        this.f5836a.f(drawable);
    }
}
