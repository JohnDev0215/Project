package i2;

import android.annotation.SuppressLint;
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
public class m implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f3371a;

    /* JADX INFO: compiled from: ViewOverlayApi14.java */
    @SuppressLint({"ViewConstructor", "PrivateApi"})
    public static class a extends ViewGroup {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Method f3372b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ViewGroup f3373c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f3374d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ArrayList<Drawable> f3375e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public m f3376f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f3377g;

        static {
            try {
                Class cls = Integer.TYPE;
                f3372b = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", cls, cls, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        public a(Context context, ViewGroup viewGroup, View view, m mVar) {
            super(context);
            this.f3375e = null;
            this.f3373c = viewGroup;
            this.f3374d = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.f3376f = mVar;
        }

        public void a(Drawable drawable) {
            b();
            if (this.f3375e == null) {
                this.f3375e = new ArrayList<>();
            }
            if (this.f3375e.contains(drawable)) {
                return;
            }
            this.f3375e.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public final void b() {
            if (this.f3377g) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        public final void c() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.f3375e;
                if (arrayList == null || arrayList.size() == 0) {
                    this.f3377g = true;
                    this.f3373c.removeView(this);
                }
            }
        }

        public final void d(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f3373c.getLocationOnScreen(iArr2);
            this.f3374d.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            this.f3373c.getLocationOnScreen(new int[2]);
            this.f3374d.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.f3374d.getWidth(), this.f3374d.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.f3375e;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i3 = 0; i3 < size; i3++) {
                this.f3375e.get(i3).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public void e(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.f3375e;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
                c();
            }
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.f3373c == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (this.f3373c == null) {
                invalidate(rect);
                return null;
            }
            iArr[0] = 0;
            iArr[1] = 0;
            int[] iArr2 = new int[2];
            d(iArr2);
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
            return super.verifyDrawable(drawable) || ((arrayList = this.f3375e) != null && arrayList.contains(drawable));
        }
    }

    public m(Context context, ViewGroup viewGroup, View view) {
        this.f3371a = new a(context, viewGroup, view, this);
    }

    public static m c(View view) {
        ViewGroup viewGroupD = p.d(view);
        if (viewGroupD == null) {
            return null;
        }
        int childCount = viewGroupD.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = viewGroupD.getChildAt(i3);
            if (childAt instanceof a) {
                return ((a) childAt).f3376f;
            }
        }
        return new l(viewGroupD.getContext(), viewGroupD, view);
    }

    @Override // i2.o
    public void a(Drawable drawable) {
        this.f3371a.a(drawable);
    }

    @Override // i2.o
    public void b(Drawable drawable) {
        this.f3371a.e(drawable);
    }
}
