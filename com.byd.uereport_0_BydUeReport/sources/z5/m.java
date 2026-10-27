package z5;

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
/* JADX INFO: loaded from: classes2.dex */
public class m implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f9602a;

    /* JADX INFO: compiled from: ViewOverlayApi14.java */
    @SuppressLint({"ViewConstructor", "PrivateApi"})
    public static class a extends ViewGroup {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static Method f9603d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f9604e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public View f9605f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public ArrayList<Drawable> f9606g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public m f9607h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f9608i;

        static {
            try {
                Class cls = Integer.TYPE;
                f9603d = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", cls, cls, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        public a(Context context, ViewGroup viewGroup, View view, m mVar) {
            super(context);
            this.f9606g = null;
            this.f9604e = viewGroup;
            this.f9605f = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.f9607h = mVar;
        }

        public void a(Drawable drawable) {
            b();
            if (this.f9606g == null) {
                this.f9606g = new ArrayList<>();
            }
            if (this.f9606g.contains(drawable)) {
                return;
            }
            this.f9606g.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        public final void b() {
            if (this.f9608i) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        public final void c() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.f9606g;
                if (arrayList == null || arrayList.size() == 0) {
                    this.f9608i = true;
                    this.f9604e.removeView(this);
                }
            }
        }

        public final void d(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f9604e.getLocationOnScreen(iArr2);
            this.f9605f.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas canvas) {
            this.f9604e.getLocationOnScreen(new int[2]);
            this.f9605f.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.f9605f.getWidth(), this.f9605f.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.f9606g;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i8 = 0; i8 < size; i8++) {
                this.f9606g.get(i8).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public void e(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.f9606g;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
                c();
            }
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.f9604e == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (this.f9604e == null) {
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
        public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.f9606g) != null && arrayList.contains(drawable));
        }
    }

    public m(Context context, ViewGroup viewGroup, View view) {
        this.f9602a = new a(context, viewGroup, view, this);
    }

    public static m c(View view) {
        ViewGroup viewGroupD = p.d(view);
        if (viewGroupD == null) {
            return null;
        }
        int childCount = viewGroupD.getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = viewGroupD.getChildAt(i8);
            if (childAt instanceof a) {
                return ((a) childAt).f9607h;
            }
        }
        return new l(viewGroupD.getContext(), viewGroupD, view);
    }

    @Override // z5.o
    public void a(Drawable drawable) {
        this.f9602a.a(drawable);
    }

    @Override // z5.o
    public void b(Drawable drawable) {
        this.f9602a.e(drawable);
    }
}
