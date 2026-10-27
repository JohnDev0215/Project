package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Interpolator;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.R$id;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.C0173a;
import defpackage.in3;
import defpackage.is0;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ItemTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class l extends RecyclerView.l implements RecyclerView.n {
    public Rect A;
    public long B;
    public float d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public final d m;
    public int o;
    public int q;
    public RecyclerView r;
    public VelocityTracker t;
    public ArrayList u;
    public ArrayList v;
    public is0 x;
    public e y;
    public final ArrayList a = new ArrayList();
    public final float[] b = new float[2];
    public RecyclerView.a0 c = null;
    public int l = -1;
    public int n = 0;
    public final ArrayList p = new ArrayList();
    public final a s = new a();
    public View w = null;
    public final b z = new b();

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00c1  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                Method dump skipped, instruction units count: 295
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.a.run():void");
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class b implements RecyclerView.p {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public final void a(MotionEvent motionEvent) {
            l lVar = l.this;
            lVar.x.a.a.onTouchEvent(motionEvent);
            VelocityTracker velocityTracker = lVar.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (lVar.l == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int iFindPointerIndex = motionEvent.findPointerIndex(lVar.l);
            if (iFindPointerIndex >= 0) {
                lVar.l(actionMasked, iFindPointerIndex, motionEvent);
            }
            RecyclerView.a0 a0Var = lVar.c;
            if (a0Var == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (iFindPointerIndex >= 0) {
                        lVar.v(lVar.o, iFindPointerIndex, motionEvent);
                        lVar.r(a0Var);
                        RecyclerView recyclerView = lVar.r;
                        a aVar = lVar.s;
                        recyclerView.removeCallbacks(aVar);
                        aVar.run();
                        lVar.r.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == lVar.l) {
                        lVar.l = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        lVar.v(lVar.o, actionIndex, motionEvent);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = lVar.t;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            lVar.t(null, 0);
            lVar.l = -1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public final boolean b(MotionEvent motionEvent) {
            int iFindPointerIndex;
            l lVar = l.this;
            lVar.x.a.a.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            f fVar = null;
            if (actionMasked == 0) {
                lVar.l = motionEvent.getPointerId(0);
                lVar.d = motionEvent.getX();
                lVar.e = motionEvent.getY();
                VelocityTracker velocityTracker = lVar.t;
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                }
                lVar.t = VelocityTracker.obtain();
                if (lVar.c == null) {
                    ArrayList arrayList = lVar.p;
                    if (!arrayList.isEmpty()) {
                        View viewO = lVar.o(motionEvent);
                        int size = arrayList.size() - 1;
                        while (true) {
                            if (size < 0) {
                                break;
                            }
                            f fVar2 = (f) arrayList.get(size);
                            if (fVar2.i.e == viewO) {
                                fVar = fVar2;
                                break;
                            }
                            size--;
                        }
                    }
                    if (fVar != null) {
                        lVar.d -= fVar.m;
                        lVar.e -= fVar.n;
                        RecyclerView.a0 a0Var = fVar.i;
                        lVar.n(a0Var, true);
                        if (lVar.a.remove(a0Var.e)) {
                            lVar.m.b(lVar.r, a0Var);
                        }
                        lVar.t(a0Var, fVar.j);
                        lVar.v(lVar.o, 0, motionEvent);
                    }
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                lVar.l = -1;
                lVar.t(null, 0);
            } else {
                int i = lVar.l;
                if (i != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i)) >= 0) {
                    lVar.l(actionMasked, iFindPointerIndex, motionEvent);
                }
            }
            VelocityTracker velocityTracker2 = lVar.t;
            if (velocityTracker2 != null) {
                velocityTracker2.addMovement(motionEvent);
            }
            return lVar.c != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.p
        public final void e(boolean z) {
            if (z) {
                l.this.t(null, 0);
            }
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class c extends f {
        public final /* synthetic */ int r;
        public final /* synthetic */ RecyclerView.a0 s;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(RecyclerView.a0 a0Var, int i, int i2, float f, float f2, float f3, float f4, int i3, RecyclerView.a0 a0Var2) {
            super(a0Var, i2, f, f2, f3, f4);
            this.r = i3;
            this.s = a0Var2;
        }

        @Override // androidx.recyclerview.widget.l.f, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            if (this.o) {
                return;
            }
            int i = this.r;
            RecyclerView.a0 a0Var = this.s;
            l lVar = l.this;
            if (i <= 0) {
                lVar.m.b(lVar.r, a0Var);
            } else {
                lVar.a.add(a0Var.e);
                this.l = true;
                if (i > 0) {
                    lVar.r.post(new m(lVar, this, i));
                }
            }
            View view = lVar.w;
            View view2 = a0Var.e;
            if (view == view2) {
                lVar.s(view2);
            }
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public static abstract class d {
        public static final b b;
        public int a = -1;

        /* JADX INFO: compiled from: ItemTouchHelper.java */
        public class a implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public final float getInterpolation(float f) {
                return f * f * f * f * f;
            }
        }

        /* JADX INFO: compiled from: ItemTouchHelper.java */
        public class b implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public final float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        }

        static {
            new a();
            b = new b();
        }

        public abstract boolean a(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2);

        public void b(RecyclerView recyclerView, RecyclerView.a0 a0Var) {
            View view = a0Var.e;
            int i = R$id.item_touch_helper_previous_elevation;
            Object tag = view.getTag(i);
            if (tag instanceof Float) {
                float fFloatValue = ((Float) tag).floatValue();
                WeakHashMap<View, in3> weakHashMap = om3.a;
                om3.i.l(view, fFloatValue);
            }
            view.setTag(i, null);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        public abstract int c(RecyclerView recyclerView, RecyclerView.a0 a0Var);

        public float d() {
            return 0.5f;
        }

        public final int e(RecyclerView recyclerView, int i, int i2, long j) {
            if (this.a == -1) {
                this.a = recyclerView.getResources().getDimensionPixelSize(R$dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            int interpolation = (int) (b.getInterpolation(Math.min(1.0f, (Math.abs(i2) * 1.0f) / i)) * ((int) Math.signum(i2)) * this.a);
            float f = j <= 2000 ? j / 2000.0f : 1.0f;
            int i3 = (int) (f * f * f * f * f * interpolation);
            return i3 == 0 ? i2 > 0 ? 1 : -1 : i3;
        }

        public boolean f() {
            return true;
        }

        public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var, float f, float f2, int i, boolean z) {
            View view = a0Var.e;
            if (z && view.getTag(R$id.item_touch_helper_previous_elevation) == null) {
                WeakHashMap<View, in3> weakHashMap = om3.a;
                Float fValueOf = Float.valueOf(om3.i.e(view));
                int childCount = recyclerView.getChildCount();
                float f3 = 0.0f;
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = recyclerView.getChildAt(i2);
                    if (childAt != view) {
                        WeakHashMap<View, in3> weakHashMap2 = om3.a;
                        float fE = om3.i.e(childAt);
                        if (fE > f3) {
                            f3 = fE;
                        }
                    }
                }
                om3.i.l(view, f3 + 1.0f);
                view.setTag(R$id.item_touch_helper_previous_elevation, fValueOf);
            }
            view.setTranslationX(f);
            view.setTranslationY(f2);
        }

        public abstract boolean h(RecyclerView.a0 a0Var, RecyclerView.a0 a0Var2);

        public abstract void i(RecyclerView.a0 a0Var, int i);

        public abstract void j(RecyclerView.a0 a0Var);
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public class e extends GestureDetector.SimpleOnGestureListener {
        public boolean a = true;

        public e() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public final void onLongPress(MotionEvent motionEvent) {
            l lVar;
            View viewO;
            RecyclerView.a0 a0VarJ;
            int i;
            if (!this.a || (viewO = (lVar = l.this).o(motionEvent)) == null || (a0VarJ = lVar.r.J(viewO)) == null) {
                return;
            }
            RecyclerView recyclerView = lVar.r;
            d dVar = lVar.m;
            int iC = dVar.c(recyclerView, a0VarJ);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            int iC2 = om3.e.c(recyclerView);
            int i2 = iC & 3158064;
            if (i2 != 0) {
                int i3 = iC & (~i2);
                if (iC2 == 0) {
                    i = i2 >> 2;
                } else {
                    int i4 = i2 >> 1;
                    i3 |= (-3158065) & i4;
                    i = (i4 & 3158064) >> 2;
                }
                iC = i3 | i;
            }
            if ((16711680 & iC) != 0) {
                int pointerId = motionEvent.getPointerId(0);
                int i5 = lVar.l;
                if (pointerId == i5) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(i5);
                    float x = motionEvent.getX(iFindPointerIndex);
                    float y = motionEvent.getY(iFindPointerIndex);
                    lVar.d = x;
                    lVar.e = y;
                    lVar.i = 0.0f;
                    lVar.h = 0.0f;
                    if (dVar.f()) {
                        lVar.t(a0VarJ, 2);
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public static abstract class g extends d {
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public interface h {
        void c(View view, View view2);
    }

    public l(d dVar) {
        this.m = dVar;
    }

    public static boolean q(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public final void c(View view) {
        s(view);
        RecyclerView.a0 a0VarJ = this.r.J(view);
        if (a0VarJ == null) {
            return;
        }
        RecyclerView.a0 a0Var = this.c;
        if (a0Var != null && a0VarJ == a0Var) {
            t(null, 0);
            return;
        }
        n(a0VarJ, false);
        if (this.a.remove(a0VarJ.e)) {
            this.m.b(this.r, a0VarJ);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    @SuppressLint({"UnknownNullness"})
    public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
        rect.setEmpty();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    @SuppressLint({"UnknownNullness"})
    public final void h(Canvas canvas, RecyclerView recyclerView) {
        float f2;
        float f3;
        if (this.c != null) {
            float[] fArr = this.b;
            p(fArr);
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        RecyclerView.a0 a0Var = this.c;
        ArrayList arrayList = this.p;
        int i = this.n;
        d dVar = this.m;
        dVar.getClass();
        int size = arrayList.size();
        int i2 = 0;
        while (i2 < size) {
            f fVar = (f) arrayList.get(i2);
            float f5 = fVar.e;
            float f6 = fVar.g;
            RecyclerView.a0 a0Var2 = fVar.i;
            if (f5 == f6) {
                fVar.m = a0Var2.e.getTranslationX();
            } else {
                fVar.m = C0173a.f(f6, f5, fVar.q, f5);
            }
            float f7 = fVar.f;
            float f8 = fVar.h;
            if (f7 == f8) {
                fVar.n = a0Var2.e.getTranslationY();
            } else {
                fVar.n = C0173a.f(f8, f7, fVar.q, f7);
            }
            int iSave = canvas.save();
            dVar.g(canvas, recyclerView, fVar.i, fVar.m, fVar.n, fVar.j, false);
            canvas.restoreToCount(iSave);
            i2++;
            arrayList = arrayList;
        }
        if (a0Var != null) {
            int iSave2 = canvas.save();
            dVar.g(canvas, recyclerView, a0Var, f2, f3, i, true);
            canvas.restoreToCount(iSave2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void i(Canvas canvas, RecyclerView recyclerView) {
        boolean z = false;
        if (this.c != null) {
            float[] fArr = this.b;
            p(fArr);
            float f2 = fArr[0];
            float f3 = fArr[1];
        }
        RecyclerView.a0 a0Var = this.c;
        ArrayList arrayList = this.p;
        this.m.getClass();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            f fVar = (f) arrayList.get(i);
            int iSave = canvas.save();
            View view = fVar.i.e;
            canvas.restoreToCount(iSave);
        }
        if (a0Var != null) {
            canvas.restoreToCount(canvas.save());
        }
        for (int i2 = size - 1; i2 >= 0; i2--) {
            f fVar2 = (f) arrayList.get(i2);
            boolean z2 = fVar2.p;
            if (z2 && !fVar2.l) {
                arrayList.remove(i2);
            } else if (!z2) {
                z = true;
            }
        }
        if (z) {
            recyclerView.invalidate();
        }
    }

    public final void j(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.r;
        if (recyclerView2 == recyclerView) {
            return;
        }
        b bVar = this.z;
        if (recyclerView2 != null) {
            recyclerView2.c0(this);
            this.r.removeOnItemTouchListener(bVar);
            this.r.removeOnChildAttachStateChangeListener(this);
            ArrayList arrayList = this.p;
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                f fVar = (f) arrayList.get(0);
                fVar.k.cancel();
                this.m.b(this.r, fVar.i);
            }
            arrayList.clear();
            this.w = null;
            VelocityTracker velocityTracker = this.t;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.t = null;
            }
            e eVar = this.y;
            if (eVar != null) {
                eVar.a = false;
                this.y = null;
            }
            if (this.x != null) {
                this.x = null;
            }
        }
        this.r = recyclerView;
        if (recyclerView != null) {
            Resources resources = recyclerView.getResources();
            this.f = resources.getDimension(R$dimen.item_touch_helper_swipe_escape_velocity);
            this.g = resources.getDimension(R$dimen.item_touch_helper_swipe_escape_max_velocity);
            this.q = ViewConfiguration.get(this.r.getContext()).getScaledTouchSlop();
            this.r.g(this);
            this.r.addOnItemTouchListener(bVar);
            this.r.addOnChildAttachStateChangeListener(this);
            this.y = new e();
            this.x = new is0(this.r.getContext(), this.y);
        }
    }

    public final int k(RecyclerView.a0 a0Var, int i) {
        if ((i & 12) == 0) {
            return 0;
        }
        int i2 = this.h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.t;
        d dVar = this.m;
        if (velocityTracker != null && this.l > -1) {
            float f2 = this.g;
            dVar.getClass();
            velocityTracker.computeCurrentVelocity(1000, f2);
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i3 = xVelocity <= 0.0f ? 4 : 8;
            float fAbs = Math.abs(xVelocity);
            if ((i3 & i) != 0 && i2 == i3 && fAbs >= this.f && fAbs > Math.abs(yVelocity)) {
                return i3;
            }
        }
        float fD = dVar.d() * this.r.getWidth();
        if ((i & i2) == 0 || Math.abs(this.h) <= fD) {
            return 0;
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(int r9, int r10, android.view.MotionEvent r11) {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.l(int, int, android.view.MotionEvent):void");
    }

    public final int m(RecyclerView.a0 a0Var, int i) {
        if ((i & 3) == 0) {
            return 0;
        }
        int i2 = this.i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.t;
        d dVar = this.m;
        if (velocityTracker != null && this.l > -1) {
            float f2 = this.g;
            dVar.getClass();
            velocityTracker.computeCurrentVelocity(1000, f2);
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i3 = yVelocity <= 0.0f ? 1 : 2;
            float fAbs = Math.abs(yVelocity);
            if ((i3 & i) != 0 && i3 == i2 && fAbs >= this.f && fAbs > Math.abs(xVelocity)) {
                return i3;
            }
        }
        float fD = dVar.d() * this.r.getHeight();
        if ((i & i2) == 0 || Math.abs(this.i) <= fD) {
            return 0;
        }
        return i2;
    }

    public final void n(RecyclerView.a0 a0Var, boolean z) {
        f fVar;
        ArrayList arrayList = this.p;
        int size = arrayList.size();
        do {
            size--;
            if (size < 0) {
                return;
            } else {
                fVar = (f) arrayList.get(size);
            }
        } while (fVar.i != a0Var);
        fVar.o |= z;
        if (!fVar.p) {
            fVar.k.cancel();
        }
        arrayList.remove(size);
    }

    public final View o(MotionEvent motionEvent) {
        f fVar;
        View view;
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.a0 a0Var = this.c;
        if (a0Var != null) {
            float f2 = this.j + this.h;
            float f3 = this.k + this.i;
            View view2 = a0Var.e;
            if (q(view2, x, y, f2, f3)) {
                return view2;
            }
        }
        ArrayList arrayList = this.p;
        int size = arrayList.size();
        do {
            size--;
            if (size >= 0) {
                fVar = (f) arrayList.get(size);
                view = fVar.i.e;
            } else {
                RecyclerView recyclerView = this.r;
                int iE = recyclerView.j.e();
                while (true) {
                    iE--;
                    if (iE < 0) {
                        return null;
                    }
                    View viewD = recyclerView.j.d(iE);
                    float translationX = viewD.getTranslationX();
                    float translationY = viewD.getTranslationY();
                    if (x >= viewD.getLeft() + translationX && x <= viewD.getRight() + translationX && y >= viewD.getTop() + translationY && y <= viewD.getBottom() + translationY) {
                        return viewD;
                    }
                }
            }
        } while (!q(view, x, y, fVar.m, fVar.n));
        return view;
    }

    public final void p(float[] fArr) {
        if ((this.o & 12) != 0) {
            fArr[0] = (this.j + this.h) - this.c.e.getLeft();
        } else {
            fArr[0] = this.c.e.getTranslationX();
        }
        if ((this.o & 3) != 0) {
            fArr[1] = (this.k + this.i) - this.c.e.getTop();
        } else {
            fArr[1] = this.c.e.getTranslationY();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void r(RecyclerView.a0 a0Var) {
        ArrayList arrayList;
        int i;
        int i2;
        int bottom;
        int iAbs;
        int top;
        int iAbs2;
        int left;
        int iAbs3;
        int iAbs4;
        RecyclerView.m mVar;
        int i3;
        int i4;
        int i5;
        if (!this.r.isLayoutRequested() && this.n == 2) {
            d dVar = this.m;
            dVar.getClass();
            int i6 = (int) (this.j + this.h);
            int i7 = (int) (this.k + this.i);
            float fAbs = Math.abs(i7 - a0Var.e.getTop());
            View view = a0Var.e;
            if (fAbs >= view.getHeight() * 0.5f || Math.abs(i6 - view.getLeft()) >= view.getWidth() * 0.5f) {
                ArrayList arrayList2 = this.u;
                if (arrayList2 == null) {
                    this.u = new ArrayList();
                    this.v = new ArrayList();
                } else {
                    arrayList2.clear();
                    this.v.clear();
                }
                int i8 = 0;
                int iRound = Math.round(this.j + this.h) - 0;
                int iRound2 = Math.round(this.k + this.i) - 0;
                int width = view.getWidth() + iRound + 0;
                int height = view.getHeight() + iRound2 + 0;
                int i9 = (iRound + width) / 2;
                int i10 = (iRound2 + height) / 2;
                RecyclerView.m layoutManager = this.r.getLayoutManager();
                int iA = layoutManager.A();
                while (i8 < iA) {
                    View viewZ = layoutManager.z(i8);
                    if (viewZ == view) {
                        i3 = iRound;
                        i4 = iRound2;
                        i5 = width;
                        mVar = layoutManager;
                    } else {
                        mVar = layoutManager;
                        if (viewZ.getBottom() < iRound2 || viewZ.getTop() > height || viewZ.getRight() < iRound || viewZ.getLeft() > width) {
                            i3 = iRound;
                        } else {
                            RecyclerView.a0 a0VarJ = this.r.J(viewZ);
                            i3 = iRound;
                            if (dVar.a(this.c, a0VarJ)) {
                                int iAbs5 = Math.abs(i9 - ((viewZ.getRight() + viewZ.getLeft()) / 2));
                                int iAbs6 = Math.abs(i10 - ((viewZ.getBottom() + viewZ.getTop()) / 2));
                                int i11 = (iAbs6 * iAbs6) + (iAbs5 * iAbs5);
                                int size = this.u.size();
                                i4 = iRound2;
                                i5 = width;
                                int i12 = 0;
                                int i13 = 0;
                                while (i12 < size) {
                                    int i14 = size;
                                    if (i11 <= ((Integer) this.v.get(i12)).intValue()) {
                                        break;
                                    }
                                    i13++;
                                    i12++;
                                    size = i14;
                                }
                                this.u.add(i13, a0VarJ);
                                this.v.add(i13, Integer.valueOf(i11));
                            }
                        }
                        i4 = iRound2;
                        i5 = width;
                    }
                    i8++;
                    layoutManager = mVar;
                    iRound = i3;
                    iRound2 = i4;
                    width = i5;
                }
                ArrayList arrayList3 = this.u;
                if (arrayList3.size() == 0) {
                    return;
                }
                int width2 = view.getWidth() + i6;
                int height2 = view.getHeight() + i7;
                int left2 = i6 - view.getLeft();
                int top2 = i7 - view.getTop();
                int size2 = arrayList3.size();
                int i15 = -1;
                RecyclerView.a0 a0Var2 = null;
                int i16 = 0;
                while (i16 < size2) {
                    RecyclerView.a0 a0Var3 = (RecyclerView.a0) arrayList3.get(i16);
                    if (left2 > 0) {
                        arrayList = arrayList3;
                        int right = a0Var3.e.getRight() - width2;
                        i = width2;
                        if (right < 0) {
                            i2 = size2;
                            if (a0Var3.e.getRight() > view.getRight() && (iAbs4 = Math.abs(right)) > i15) {
                                i15 = iAbs4;
                                a0Var2 = a0Var3;
                            }
                        }
                        if (left2 < 0 && (left = a0Var3.e.getLeft() - i6) > 0 && a0Var3.e.getLeft() < view.getLeft() && (iAbs3 = Math.abs(left)) > i15) {
                            i15 = iAbs3;
                            a0Var2 = a0Var3;
                        }
                        if (top2 < 0 && (top = a0Var3.e.getTop() - i7) > 0 && a0Var3.e.getTop() < view.getTop() && (iAbs2 = Math.abs(top)) > i15) {
                            i15 = iAbs2;
                            a0Var2 = a0Var3;
                        }
                        if (top2 <= 0 && (bottom = a0Var3.e.getBottom() - height2) < 0 && a0Var3.e.getBottom() > view.getBottom() && (iAbs = Math.abs(bottom)) > i15) {
                            i15 = iAbs;
                            a0Var2 = a0Var3;
                        }
                        i16++;
                        arrayList3 = arrayList;
                        width2 = i;
                        size2 = i2;
                    } else {
                        arrayList = arrayList3;
                        i = width2;
                    }
                    i2 = size2;
                    if (left2 < 0) {
                        i15 = iAbs3;
                        a0Var2 = a0Var3;
                    }
                    if (top2 < 0) {
                        i15 = iAbs2;
                        a0Var2 = a0Var3;
                    }
                    if (top2 <= 0) {
                    }
                    i16++;
                    arrayList3 = arrayList;
                    width2 = i;
                    size2 = i2;
                }
                if (a0Var2 == null) {
                    this.u.clear();
                    this.v.clear();
                    return;
                }
                int iE = a0Var2.e();
                a0Var.e();
                if (dVar.h(a0Var, a0Var2)) {
                    RecyclerView recyclerView = this.r;
                    RecyclerView.m layoutManager2 = recyclerView.getLayoutManager();
                    boolean z = layoutManager2 instanceof h;
                    View view2 = a0Var2.e;
                    if (z) {
                        ((h) layoutManager2).c(view, view2);
                        return;
                    }
                    if (layoutManager2.h()) {
                        if (view2.getLeft() - RecyclerView.m.F(view2) <= recyclerView.getPaddingLeft()) {
                            recyclerView.g0(iE);
                        }
                        if (RecyclerView.m.M(view2) + view2.getRight() >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                            recyclerView.g0(iE);
                        }
                    }
                    if (layoutManager2.i()) {
                        if (view2.getTop() - RecyclerView.m.O(view2) <= recyclerView.getPaddingTop()) {
                            recyclerView.g0(iE);
                        }
                        if (RecyclerView.m.y(view2) + view2.getBottom() >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                            recyclerView.g0(iE);
                        }
                    }
                }
            }
        }
    }

    public final void s(View view) {
        if (view == this.w) {
            this.w = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b3 A[PHI: r1 r2
  0x00b3: PHI (r1v36 int) = (r1v33 int), (r1v37 int) binds: [B:61:0x00e4, B:43:0x00b1] A[DONT_GENERATE, DONT_INLINE]
  0x00b3: PHI (r2v20 int) = (r2v15 int), (r2v23 int) binds: [B:61:0x00e4, B:43:0x00b1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b6 A[PHI: r1 r2
  0x00b6: PHI (r1v34 int) = (r1v33 int), (r1v37 int) binds: [B:61:0x00e4, B:43:0x00b1] A[DONT_GENERATE, DONT_INLINE]
  0x00b6: PHI (r2v16 int) = (r2v15 int), (r2v23 int) binds: [B:61:0x00e4, B:43:0x00b1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t(androidx.recyclerview.widget.RecyclerView.a0 r25, int r26) {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.t(androidx.recyclerview.widget.RecyclerView$a0, int):void");
    }

    public final void u(RecyclerView.a0 a0Var) {
        int i;
        RecyclerView recyclerView = this.r;
        int iC = this.m.c(recyclerView, a0Var);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iC2 = om3.e.c(recyclerView);
        int i2 = iC & 3158064;
        if (i2 != 0) {
            int i3 = iC & (~i2);
            if (iC2 == 0) {
                i = i2 >> 2;
            } else {
                int i4 = i2 >> 1;
                i3 |= (-3158065) & i4;
                i = (i4 & 3158064) >> 2;
            }
            iC = i3 | i;
        }
        if (!((16711680 & iC) != 0)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            return;
        }
        if (a0Var.e.getParent() != this.r) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.t = VelocityTracker.obtain();
        this.i = 0.0f;
        this.h = 0.0f;
        t(a0Var, 2);
    }

    public final void v(int i, int i2, MotionEvent motionEvent) {
        float x = motionEvent.getX(i2);
        float y = motionEvent.getY(i2);
        float f2 = x - this.d;
        this.h = f2;
        this.i = y - this.e;
        if ((i & 4) == 0) {
            this.h = Math.max(0.0f, f2);
        }
        if ((i & 8) == 0) {
            this.h = Math.min(0.0f, this.h);
        }
        if ((i & 1) == 0) {
            this.i = Math.max(0.0f, this.i);
        }
        if ((i & 2) == 0) {
            this.i = Math.min(0.0f, this.i);
        }
    }

    /* JADX INFO: compiled from: ItemTouchHelper.java */
    public static class f implements Animator.AnimatorListener {
        public final float e;
        public final float f;
        public final float g;
        public final float h;
        public final RecyclerView.a0 i;
        public final int j;
        public final ValueAnimator k;
        public boolean l;
        public float m;
        public float n;
        public boolean o = false;
        public boolean p = false;
        public float q;

        public f(RecyclerView.a0 a0Var, int i, float f, float f2, float f3, float f4) {
            this.j = i;
            this.i = a0Var;
            this.e = f;
            this.f = f2;
            this.g = f3;
            this.h = f4;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.k = valueAnimatorOfFloat;
            valueAnimatorOfFloat.addUpdateListener(new n(this));
            valueAnimatorOfFloat.setTarget(a0Var.e);
            valueAnimatorOfFloat.addListener(this);
            this.q = 0.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.q = 1.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.p) {
                this.i.r(true);
            }
            this.p = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public final void d(View view) {
    }
}
