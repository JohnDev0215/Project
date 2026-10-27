package com.google.android.material.internal;

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
class n implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected a f4171a;

    /* JADX INFO: compiled from: ViewOverlayApi14.java */
    @SuppressLint({"ViewConstructor", "PrivateApi"})
    static class a extends ViewGroup {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static Method f4172b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ViewGroup f4173c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        View f4174d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        ArrayList<Drawable> f4175e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        n f4176f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private boolean f4177g;

        static {
            try {
                Class cls = Integer.TYPE;
                f4172b = ViewGroup.class.getDeclaredMethod("invalidateChildInParentFast", cls, cls, Rect.class);
            } catch (NoSuchMethodException unused) {
            }
        }

        a(Context context, ViewGroup viewGroup, View view, n nVar) {
            super(context);
            this.f4175e = null;
            this.f4173c = viewGroup;
            this.f4174d = view;
            setRight(viewGroup.getWidth());
            setBottom(viewGroup.getHeight());
            viewGroup.addView(this);
            this.f4176f = nVar;
        }

        private void b() {
            if (this.f4177g) {
                throw new IllegalStateException("This overlay was disposed already. Please use a new one via ViewGroupUtils.getOverlay()");
            }
        }

        private void c() {
            if (getChildCount() == 0) {
                ArrayList<Drawable> arrayList = this.f4175e;
                if (arrayList == null || arrayList.size() == 0) {
                    this.f4177g = true;
                    this.f4173c.removeView(this);
                }
            }
        }

        private void d(int[] iArr) {
            int[] iArr2 = new int[2];
            int[] iArr3 = new int[2];
            this.f4173c.getLocationOnScreen(iArr2);
            this.f4174d.getLocationOnScreen(iArr3);
            iArr[0] = iArr3[0] - iArr2[0];
            iArr[1] = iArr3[1] - iArr2[1];
        }

        public void a(Drawable drawable) {
            b();
            if (this.f4175e == null) {
                this.f4175e = new ArrayList<>();
            }
            if (this.f4175e.contains(drawable)) {
                return;
            }
            this.f4175e.add(drawable);
            invalidate(drawable.getBounds());
            drawable.setCallback(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void dispatchDraw(Canvas canvas) {
            this.f4173c.getLocationOnScreen(new int[2]);
            this.f4174d.getLocationOnScreen(new int[2]);
            canvas.translate(r0[0] - r1[0], r0[1] - r1[1]);
            canvas.clipRect(new Rect(0, 0, this.f4174d.getWidth(), this.f4174d.getHeight()));
            super.dispatchDraw(canvas);
            ArrayList<Drawable> arrayList = this.f4175e;
            int size = arrayList == null ? 0 : arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.f4175e.get(i2).draw(canvas);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public void e(Drawable drawable) {
            ArrayList<Drawable> arrayList = this.f4175e;
            if (arrayList != null) {
                arrayList.remove(drawable);
                invalidate(drawable.getBounds());
                drawable.setCallback(null);
                c();
            }
        }

        @Override // android.view.ViewGroup, android.view.ViewParent
        public ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
            if (this.f4173c == null) {
                return null;
            }
            rect.offset(iArr[0], iArr[1]);
            if (this.f4173c == null) {
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
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        }

        @Override // android.view.View
        protected boolean verifyDrawable(Drawable drawable) {
            ArrayList<Drawable> arrayList;
            return super.verifyDrawable(drawable) || ((arrayList = this.f4175e) != null && arrayList.contains(drawable));
        }
    }

    n(Context context, ViewGroup viewGroup, View view) {
        this.f4171a = new a(context, viewGroup, view, this);
    }

    static n c(View view) {
        ViewGroup viewGroupD = q.d(view);
        if (viewGroupD == null) {
            return null;
        }
        int childCount = viewGroupD.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroupD.getChildAt(i2);
            if (childAt instanceof a) {
                return ((a) childAt).f4176f;
            }
        }
        return new m(viewGroupD.getContext(), viewGroupD, view);
    }

    @Override // com.google.android.material.internal.p
    public void a(Drawable drawable) {
        this.f4171a.a(drawable);
    }

    @Override // com.google.android.material.internal.p
    public void b(Drawable drawable) {
        this.f4171a.e(drawable);
    }
}
