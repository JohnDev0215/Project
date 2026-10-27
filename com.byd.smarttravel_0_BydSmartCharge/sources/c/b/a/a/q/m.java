package c.b.a.a.q;

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
    public a f1807a;

    /* JADX INFO: compiled from: ViewOverlayApi14.java */
    @SuppressLint({"ViewConstructor", "PrivateApi"})
    public static class a extends ViewGroup {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Method f1808b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ViewGroup f1809c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f1810d;
        public ArrayList<Drawable> e;
        public m f;
        public boolean g;

        static {
            try {
                Class cls = Integer.TYPE;
                f1808b = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", cls, cls, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        public a(Context context, ViewGroup viewGroup, View view, m mVar) {
            super(context);
            this.e = null;
            this.f1809c = viewGroup;
            this.f1810d = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.f = mVar;
        }

        public void a(Drawable drawable) {
            b();
            if (this.e == null) {
                this.e = new ArrayList<>();
            }
            if (this.e.contains(drawable)) {
                return;
            }
            this.e.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public final void b() {
            if (this.g) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        public final void c() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.e;
                if (arrayList == null || arrayList.size() == 0) {
                    this.g = true;
                    this.f1809c.removeView(this);
                }
            }
        }

        public final void d(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f1809c.getLocationOnScreen(iArr2);
            this.f1810d.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            this.f1809c.getLocationOnScreen(new int[2]);
            this.f1810d.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.f1810d.getWidth(), this.f1810d.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.e;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i = 0; i < size; i++) {
                this.e.get(i).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public void e(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.e;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
                c();
            }
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.f1809c == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (this.f1809c == null) {
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
        public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.e) != null && arrayList.contains(drawable));
        }
    }

    public m(Context context, ViewGroup viewGroup, View view) {
        this.f1807a = new a(context, viewGroup, view, this);
    }

    public static m c(View view) {
        ViewGroup viewGroupD = p.d(view);
        if (viewGroupD == null) {
            return null;
        }
        int childCount = viewGroupD.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroupD.getChildAt(i);
            if (childAt instanceof a) {
                return ((a) childAt).f;
            }
        }
        return new l(viewGroupD.getContext(), viewGroupD, view);
    }

    @Override // c.b.a.a.q.o
    public void a(Drawable drawable) {
        this.f1807a.a(drawable);
    }

    @Override // c.b.a.a.q.o
    public void b(Drawable drawable) {
        this.f1807a.e(drawable);
    }
}
