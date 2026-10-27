package a.p.e;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class o extends RecyclerView.l implements RecyclerView.o {
    public e A;
    public Rect C;
    public long D;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f1234d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f1235e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f1236f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public d m;
    public int o;
    public int q;
    public RecyclerView r;
    public VelocityTracker t;
    public List<RecyclerView.b0> u;
    public List<Integer> v;
    public a.h.k.d z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<View> f1231a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f1232b = new float[2];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RecyclerView.b0 f1233c = null;
    public int l = -1;
    public int n = 0;
    public List<f> p = new ArrayList();
    public final Runnable s = new a();
    public RecyclerView.h w = null;
    public View x = null;
    public int y = -1;
    public final RecyclerView.q B = new b();

    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0083  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00cc  */
        /* JADX WARN: Removed duplicated region for block: B:6:0x000d  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 306
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: a.p.e.o.a.run():void");
        }
    }

    public class b implements RecyclerView.q {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public void b(boolean z) {
            if (z) {
                o.this.r(null, 0);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public boolean c(RecyclerView recyclerView, MotionEvent motionEvent) {
            int iFindPointerIndex;
            o.this.z.f812a.a(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            f fVar = null;
            if (actionMasked == 0) {
                o.this.l = motionEvent.getPointerId(0);
                o.this.f1234d = motionEvent.getX();
                o.this.f1235e = motionEvent.getY();
                o oVar = o.this;
                VelocityTracker velocityTracker = oVar.t;
                if (velocityTracker != null) {
                    velocityTracker.recycle();
                }
                oVar.t = VelocityTracker.obtain();
                o oVar2 = o.this;
                if (oVar2.f1233c == null) {
                    if (!oVar2.p.isEmpty()) {
                        View viewM = oVar2.m(motionEvent);
                        int size = oVar2.p.size() - 1;
                        while (true) {
                            if (size < 0) {
                                break;
                            }
                            f fVar2 = oVar2.p.get(size);
                            if (fVar2.f1248e.f1629a == viewM) {
                                fVar = fVar2;
                                break;
                            }
                            size--;
                        }
                    }
                    if (fVar != null) {
                        o oVar3 = o.this;
                        oVar3.f1234d -= fVar.i;
                        oVar3.f1235e -= fVar.j;
                        oVar3.l(fVar.f1248e, true);
                        if (o.this.f1231a.remove(fVar.f1248e.f1629a)) {
                            o.this.m.a(fVar.f1248e);
                        }
                        o.this.r(fVar.f1248e, fVar.f1249f);
                        o oVar4 = o.this;
                        oVar4.s(motionEvent, oVar4.o, 0);
                    }
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                o oVar5 = o.this;
                oVar5.l = -1;
                oVar5.r(null, 0);
            } else {
                int i = o.this.l;
                if (i != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i)) >= 0) {
                    o.this.j(actionMasked, motionEvent, iFindPointerIndex);
                }
            }
            VelocityTracker velocityTracker2 = o.this.t;
            if (velocityTracker2 != null) {
                velocityTracker2.addMovement(motionEvent);
            }
            return o.this.f1233c != null;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public void d(RecyclerView recyclerView, MotionEvent motionEvent) {
            o.this.z.f812a.a(motionEvent);
            VelocityTracker velocityTracker = o.this.t;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (o.this.l == -1) {
                return;
            }
            int actionMasked = motionEvent.getActionMasked();
            int iFindPointerIndex = motionEvent.findPointerIndex(o.this.l);
            if (iFindPointerIndex >= 0) {
                o.this.j(actionMasked, motionEvent, iFindPointerIndex);
            }
            o oVar = o.this;
            RecyclerView.b0 b0Var = oVar.f1233c;
            if (b0Var == null) {
                return;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    if (iFindPointerIndex >= 0) {
                        oVar.s(motionEvent, oVar.o, iFindPointerIndex);
                        o.this.p(b0Var);
                        o oVar2 = o.this;
                        oVar2.r.removeCallbacks(oVar2.s);
                        o.this.s.run();
                        o.this.r.invalidate();
                        return;
                    }
                    return;
                }
                if (actionMasked != 3) {
                    if (actionMasked != 6) {
                        return;
                    }
                    int actionIndex = motionEvent.getActionIndex();
                    if (motionEvent.getPointerId(actionIndex) == o.this.l) {
                        o.this.l = motionEvent.getPointerId(actionIndex == 0 ? 1 : 0);
                        o oVar3 = o.this;
                        oVar3.s(motionEvent, oVar3.o, actionIndex);
                        return;
                    }
                    return;
                }
                VelocityTracker velocityTracker2 = oVar.t;
                if (velocityTracker2 != null) {
                    velocityTracker2.clear();
                }
            }
            o.this.r(null, 0);
            o.this.l = -1;
        }
    }

    public class c extends f {
        public final /* synthetic */ int n;
        public final /* synthetic */ RecyclerView.b0 o;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(RecyclerView.b0 b0Var, int i, int i2, float f2, float f3, float f4, float f5, int i3, RecyclerView.b0 b0Var2) {
            super(b0Var, i, i2, f2, f3, f4, f5);
            this.n = i3;
            this.o = b0Var2;
        }

        @Override // a.p.e.o.f, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.l) {
                this.f1248e.t(true);
            }
            this.l = true;
            if (this.k) {
                return;
            }
            if (this.n <= 0) {
                o oVar = o.this;
                d dVar = oVar.m;
                RecyclerView recyclerView = oVar.r;
                dVar.a(this.o);
            } else {
                o.this.f1231a.add(this.o.f1629a);
                this.h = true;
                int i = this.n;
                if (i > 0) {
                    o oVar2 = o.this;
                    oVar2.r.post(new p(oVar2, this, i));
                }
            }
            o oVar3 = o.this;
            View view = oVar3.x;
            View view2 = this.o.f1629a;
            if (view == view2) {
                oVar3.q(view2);
            }
        }
    }

    public static abstract class d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final Interpolator f1239b = new a();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final Interpolator f1240c = new b();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1241a = -1;

        public static class a implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                return f2 * f2 * f2 * f2 * f2;
            }
        }

        public static class b implements Interpolator {
            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        }

        public static int c(int i, int i2) {
            int i3;
            int i4 = i & 789516;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 << 2;
            } else {
                int i6 = i4 << 1;
                i5 |= (-789517) & i6;
                i3 = (i6 & 789516) << 2;
            }
            return i5 | i3;
        }

        public void a(RecyclerView.b0 b0Var) {
            View view = b0Var.f1629a;
            Object tag = view.getTag(a.p.c.item_touch_helper_previous_elevation);
            if (tag instanceof Float) {
                a.h.k.q.Z(view, ((Float) tag).floatValue());
            }
            view.setTag(a.p.c.item_touch_helper_previous_elevation, null);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        public int b(int i, int i2) {
            int i3;
            int i4 = i & 3158064;
            if (i4 == 0) {
                return i;
            }
            int i5 = i & (~i4);
            if (i2 == 0) {
                i3 = i4 >> 2;
            } else {
                int i6 = i4 >> 1;
                i5 |= (-3158065) & i6;
                i3 = (3158064 & i6) >> 2;
            }
            return i3 | i5;
        }

        public final int d(RecyclerView recyclerView, RecyclerView.b0 b0Var) {
            g gVar = (g) this;
            int i = gVar.f1252e;
            int i2 = gVar.f1251d;
            return b((i2 << 8) | ((i2 | i) << 0) | (i << 16), a.h.k.q.p(recyclerView));
        }

        public int e(RecyclerView recyclerView, int i, int i2, long j) {
            if (this.f1241a == -1) {
                this.f1241a = recyclerView.getResources().getDimensionPixelSize(a.p.b.item_touch_helper_max_drag_scroll_per_frame);
            }
            int interpolation = (int) (((a) f1239b).getInterpolation(j <= 2000 ? j / 2000.0f : 1.0f) * ((int) (((b) f1240c).getInterpolation(Math.min(1.0f, (Math.abs(i2) * 1.0f) / i)) * ((int) Math.signum(i2)) * this.f1241a)));
            return interpolation == 0 ? i2 > 0 ? 1 : -1 : interpolation;
        }

        public void f(Canvas canvas, RecyclerView recyclerView, RecyclerView.b0 b0Var, float f2, float f3, int i, boolean z) {
            View view = b0Var.f1629a;
            if (z && view.getTag(a.p.c.item_touch_helper_previous_elevation) == null) {
                Float fValueOf = Float.valueOf(a.h.k.q.l(view));
                int childCount = recyclerView.getChildCount();
                float f4 = 0.0f;
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = recyclerView.getChildAt(i2);
                    if (childAt != view) {
                        float elevation = childAt.getElevation();
                        if (elevation > f4) {
                            f4 = elevation;
                        }
                    }
                }
                view.setElevation(f4 + 1.0f);
                view.setTag(a.p.c.item_touch_helper_previous_elevation, fValueOf);
            }
            view.setTranslationX(f2);
            view.setTranslationY(f3);
        }
    }

    public class e extends GestureDetector.SimpleOnGestureListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1242a = true;

        public e() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public void onLongPress(MotionEvent motionEvent) {
            View viewM;
            RecyclerView.b0 b0VarJ;
            if (!this.f1242a || (viewM = o.this.m(motionEvent)) == null || (b0VarJ = o.this.r.J(viewM)) == null) {
                return;
            }
            o oVar = o.this;
            if ((oVar.m.d(oVar.r, b0VarJ) & 16711680) != 0) {
                int pointerId = motionEvent.getPointerId(0);
                int i = o.this.l;
                if (pointerId == i) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(i);
                    float x = motionEvent.getX(iFindPointerIndex);
                    float y = motionEvent.getY(iFindPointerIndex);
                    o oVar2 = o.this;
                    oVar2.f1234d = x;
                    oVar2.f1235e = y;
                    oVar2.i = 0.0f;
                    oVar2.h = 0.0f;
                    if (oVar2.m == null) {
                        throw null;
                    }
                    oVar2.r(b0VarJ, 2);
                }
            }
        }
    }

    public static class f implements Animator.AnimatorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final float f1244a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final float f1245b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final float f1246c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final float f1247d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final RecyclerView.b0 f1248e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int f1249f;
        public final ValueAnimator g;
        public boolean h;
        public float i;
        public float j;
        public boolean k = false;
        public boolean l = false;
        public float m;

        public class a implements ValueAnimator.AnimatorUpdateListener {
            public a() {
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                f.this.m = valueAnimator.getAnimatedFraction();
            }
        }

        public f(RecyclerView.b0 b0Var, int i, int i2, float f2, float f3, float f4, float f5) {
            this.f1249f = i2;
            this.f1248e = b0Var;
            this.f1244a = f2;
            this.f1245b = f3;
            this.f1246c = f4;
            this.f1247d = f5;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.g = valueAnimatorOfFloat;
            valueAnimatorOfFloat.addUpdateListener(new a());
            this.g.setTarget(b0Var.f1629a);
            this.g.addListener(this);
            this.m = 0.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.m = 1.0f;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (!this.l) {
                this.f1248e.t(true);
            }
            this.l = true;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public static abstract class g extends d {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1251d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1252e;

        public g(int i, int i2) {
            this.f1251d = i2;
            this.f1252e = i;
        }
    }

    public o(d dVar) {
        this.m = dVar;
    }

    public static boolean o(View view, float f2, float f3, float f4, float f5) {
        return f2 >= f4 && f2 <= f4 + ((float) view.getWidth()) && f3 >= f5 && f3 <= f5 + ((float) view.getHeight());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void a(View view) {
        q(view);
        RecyclerView.b0 b0VarJ = this.r.J(view);
        if (b0VarJ == null) {
            return;
        }
        RecyclerView.b0 b0Var = this.f1233c;
        if (b0Var != null && b0VarJ == b0Var) {
            r(null, 0);
            return;
        }
        l(b0VarJ, false);
        if (this.f1231a.remove(b0VarJ.f1629a)) {
            this.m.a(b0VarJ);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.o
    public void e(View view) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void f(Rect rect, View view, RecyclerView recyclerView, RecyclerView.y yVar) {
        rect.setEmpty();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        float f2;
        float f3;
        this.y = -1;
        if (this.f1233c != null) {
            n(this.f1232b);
            float[] fArr = this.f1232b;
            float f4 = fArr[0];
            f3 = fArr[1];
            f2 = f4;
        } else {
            f2 = 0.0f;
            f3 = 0.0f;
        }
        d dVar = this.m;
        RecyclerView.b0 b0Var = this.f1233c;
        List<f> list = this.p;
        int i = this.n;
        if (dVar == null) {
            throw null;
        }
        int size = list.size();
        int i2 = 0;
        while (i2 < size) {
            f fVar = list.get(i2);
            float f5 = fVar.f1244a;
            float f6 = fVar.f1246c;
            fVar.i = f5 == f6 ? fVar.f1248e.f1629a.getTranslationX() : b.a.a.a.a.a(f6, f5, fVar.m, f5);
            float f7 = fVar.f1245b;
            float f8 = fVar.f1247d;
            fVar.j = f7 == f8 ? fVar.f1248e.f1629a.getTranslationY() : b.a.a.a.a.a(f8, f7, fVar.m, f7);
            int iSave = canvas.save();
            dVar.f(canvas, recyclerView, fVar.f1248e, fVar.i, fVar.j, fVar.f1249f, false);
            canvas.restoreToCount(iSave);
            i2++;
            list = list;
        }
        if (b0Var != null) {
            int iSave2 = canvas.save();
            dVar.f(canvas, recyclerView, b0Var, f2, f3, i, true);
            canvas.restoreToCount(iSave2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public void h(Canvas canvas, RecyclerView recyclerView, RecyclerView.y yVar) {
        boolean z = false;
        if (this.f1233c != null) {
            n(this.f1232b);
            float[] fArr = this.f1232b;
            float f2 = fArr[0];
            float f3 = fArr[1];
        }
        d dVar = this.m;
        RecyclerView.b0 b0Var = this.f1233c;
        List<f> list = this.p;
        if (dVar == null) {
            throw null;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            f fVar = list.get(i);
            int iSave = canvas.save();
            View view = fVar.f1248e.f1629a;
            canvas.restoreToCount(iSave);
        }
        if (b0Var != null) {
            canvas.restoreToCount(canvas.save());
        }
        for (int i2 = size - 1; i2 >= 0; i2--) {
            f fVar2 = list.get(i2);
            if (fVar2.l && !fVar2.h) {
                list.remove(i2);
            } else if (!fVar2.l) {
                z = true;
            }
        }
        if (z) {
            recyclerView.invalidate();
        }
    }

    public final int i(RecyclerView.b0 b0Var, int i) {
        if ((i & 12) == 0) {
            return 0;
        }
        int i2 = this.h > 0.0f ? 8 : 4;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            d dVar = this.m;
            float f2 = this.g;
            if (dVar == null) {
                throw null;
            }
            velocityTracker.computeCurrentVelocity(1000, f2);
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i3 = xVelocity <= 0.0f ? 4 : 8;
            float fAbs = Math.abs(xVelocity);
            if ((i3 & i) != 0 && i2 == i3) {
                d dVar2 = this.m;
                float f3 = this.f1236f;
                if (dVar2 == null) {
                    throw null;
                }
                if (fAbs >= f3 && fAbs > Math.abs(yVelocity)) {
                    return i3;
                }
            }
        }
        float width = this.r.getWidth();
        if (((b.c.a.f.q) this.m) == null) {
            throw null;
        }
        float f4 = width * 0.3f;
        if ((i & i2) == 0 || Math.abs(this.h) <= f4) {
            return 0;
        }
        return i2;
    }

    public void j(int i, MotionEvent motionEvent, int i2) {
        int iD;
        View viewM;
        if (this.f1233c == null && i == 2 && this.n != 2) {
            RecyclerView.b0 b0VarJ = null;
            if (this.m == null) {
                throw null;
            }
            if (this.r.getScrollState() == 1) {
                return;
            }
            RecyclerView.m layoutManager = this.r.getLayoutManager();
            int i3 = this.l;
            if (i3 != -1) {
                int iFindPointerIndex = motionEvent.findPointerIndex(i3);
                float x = motionEvent.getX(iFindPointerIndex) - this.f1234d;
                float y = motionEvent.getY(iFindPointerIndex) - this.f1235e;
                float fAbs = Math.abs(x);
                float fAbs2 = Math.abs(y);
                float f2 = this.q;
                if ((fAbs >= f2 || fAbs2 >= f2) && ((fAbs <= fAbs2 || !layoutManager.f()) && ((fAbs2 <= fAbs || !layoutManager.g()) && (viewM = m(motionEvent)) != null))) {
                    b0VarJ = this.r.J(viewM);
                }
            }
            if (b0VarJ == null || (iD = (this.m.d(this.r, b0VarJ) & 65280) >> 8) == 0) {
                return;
            }
            float x2 = motionEvent.getX(i2);
            float y2 = motionEvent.getY(i2);
            float f3 = x2 - this.f1234d;
            float f4 = y2 - this.f1235e;
            float fAbs3 = Math.abs(f3);
            float fAbs4 = Math.abs(f4);
            float f5 = this.q;
            if (fAbs3 >= f5 || fAbs4 >= f5) {
                if (fAbs3 > fAbs4) {
                    if (f3 < 0.0f && (iD & 4) == 0) {
                        return;
                    }
                    if (f3 > 0.0f && (iD & 8) == 0) {
                        return;
                    }
                } else {
                    if (f4 < 0.0f && (iD & 1) == 0) {
                        return;
                    }
                    if (f4 > 0.0f && (iD & 2) == 0) {
                        return;
                    }
                }
                this.i = 0.0f;
                this.h = 0.0f;
                this.l = motionEvent.getPointerId(0);
                r(b0VarJ, 1);
            }
        }
    }

    public final int k(RecyclerView.b0 b0Var, int i) {
        if ((i & 3) == 0) {
            return 0;
        }
        int i2 = this.i > 0.0f ? 2 : 1;
        VelocityTracker velocityTracker = this.t;
        if (velocityTracker != null && this.l > -1) {
            d dVar = this.m;
            float f2 = this.g;
            if (dVar == null) {
                throw null;
            }
            velocityTracker.computeCurrentVelocity(1000, f2);
            float xVelocity = this.t.getXVelocity(this.l);
            float yVelocity = this.t.getYVelocity(this.l);
            int i3 = yVelocity <= 0.0f ? 1 : 2;
            float fAbs = Math.abs(yVelocity);
            if ((i3 & i) != 0 && i3 == i2) {
                d dVar2 = this.m;
                float f3 = this.f1236f;
                if (dVar2 == null) {
                    throw null;
                }
                if (fAbs >= f3 && fAbs > Math.abs(xVelocity)) {
                    return i3;
                }
            }
        }
        float height = this.r.getHeight();
        if (((b.c.a.f.q) this.m) == null) {
            throw null;
        }
        float f4 = height * 0.3f;
        if ((i & i2) == 0 || Math.abs(this.i) <= f4) {
            return 0;
        }
        return i2;
    }

    public void l(RecyclerView.b0 b0Var, boolean z) {
        for (int size = this.p.size() - 1; size >= 0; size--) {
            f fVar = this.p.get(size);
            if (fVar.f1248e == b0Var) {
                fVar.k |= z;
                if (!fVar.l) {
                    fVar.g.cancel();
                }
                this.p.remove(size);
                return;
            }
        }
    }

    public View m(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.b0 b0Var = this.f1233c;
        if (b0Var != null) {
            View view = b0Var.f1629a;
            if (o(view, x, y, this.j + this.h, this.k + this.i)) {
                return view;
            }
        }
        for (int size = this.p.size() - 1; size >= 0; size--) {
            f fVar = this.p.get(size);
            View view2 = fVar.f1248e.f1629a;
            if (o(view2, x, y, fVar.i, fVar.j)) {
                return view2;
            }
        }
        RecyclerView recyclerView = this.r;
        int iE = recyclerView.f1619e.e();
        while (true) {
            iE--;
            if (iE < 0) {
                return null;
            }
            View viewD = recyclerView.f1619e.d(iE);
            float translationX = viewD.getTranslationX();
            float translationY = viewD.getTranslationY();
            if (x >= viewD.getLeft() + translationX && x <= viewD.getRight() + translationX && y >= viewD.getTop() + translationY && y <= viewD.getBottom() + translationY) {
                return viewD;
            }
        }
    }

    public final void n(float[] fArr) {
        if ((this.o & 12) != 0) {
            fArr[0] = (this.j + this.h) - this.f1233c.f1629a.getLeft();
        } else {
            fArr[0] = this.f1233c.f1629a.getTranslationX();
        }
        if ((this.o & 3) != 0) {
            fArr[1] = (this.k + this.i) - this.f1233c.f1629a.getTop();
        } else {
            fArr[1] = this.f1233c.f1629a.getTranslationY();
        }
    }

    public void p(RecyclerView.b0 b0Var) {
        List<RecyclerView.b0> list;
        int bottom;
        int iAbs;
        int top;
        int iAbs2;
        int left;
        int iAbs3;
        int right;
        int iAbs4;
        int i;
        int i2;
        if (!this.r.isLayoutRequested() && this.n == 2) {
            if (this.m == null) {
                throw null;
            }
            int i3 = (int) (this.j + this.h);
            int i4 = (int) (this.k + this.i);
            if (Math.abs(i4 - b0Var.f1629a.getTop()) >= b0Var.f1629a.getHeight() * 0.5f || Math.abs(i3 - b0Var.f1629a.getLeft()) >= b0Var.f1629a.getWidth() * 0.5f) {
                List<RecyclerView.b0> list2 = this.u;
                if (list2 == null) {
                    this.u = new ArrayList();
                    this.v = new ArrayList();
                } else {
                    list2.clear();
                    this.v.clear();
                }
                if (this.m == null) {
                    throw null;
                }
                int iRound = Math.round(this.j + this.h) - 0;
                int iRound2 = Math.round(this.k + this.i) - 0;
                int width = b0Var.f1629a.getWidth() + iRound + 0;
                int height = b0Var.f1629a.getHeight() + iRound2 + 0;
                int i5 = (iRound + width) / 2;
                int i6 = (iRound2 + height) / 2;
                RecyclerView.m layoutManager = this.r.getLayoutManager();
                int iZ = layoutManager.z();
                int i7 = 0;
                while (i7 < iZ) {
                    View viewY = layoutManager.y(i7);
                    if (viewY != b0Var.f1629a && viewY.getBottom() >= iRound2 && viewY.getTop() <= height && viewY.getRight() >= iRound && viewY.getLeft() <= width) {
                        RecyclerView.b0 b0VarJ = this.r.J(viewY);
                        if (this.m == null) {
                            throw null;
                        }
                        int iAbs5 = Math.abs(i5 - ((viewY.getRight() + viewY.getLeft()) / 2));
                        int iAbs6 = Math.abs(i6 - ((viewY.getBottom() + viewY.getTop()) / 2));
                        int i8 = (iAbs6 * iAbs6) + (iAbs5 * iAbs5);
                        int size = this.u.size();
                        i = iRound;
                        i2 = iRound2;
                        int i9 = 0;
                        int i10 = 0;
                        while (i9 < size) {
                            int i11 = size;
                            if (i8 <= this.v.get(i9).intValue()) {
                                break;
                            }
                            i10++;
                            i9++;
                            size = i11;
                        }
                        this.u.add(i10, b0VarJ);
                        this.v.add(i10, Integer.valueOf(i8));
                    } else {
                        i = iRound;
                        i2 = iRound2;
                    }
                    i7++;
                    iRound = i;
                    iRound2 = i2;
                }
                List<RecyclerView.b0> list3 = this.u;
                if (list3.size() == 0) {
                    return;
                }
                if (this.m == null) {
                    throw null;
                }
                int width2 = b0Var.f1629a.getWidth() + i3;
                int height2 = b0Var.f1629a.getHeight() + i4;
                int left2 = i3 - b0Var.f1629a.getLeft();
                int top2 = i4 - b0Var.f1629a.getTop();
                int size2 = list3.size();
                int i12 = -1;
                RecyclerView.b0 b0Var2 = null;
                int i13 = 0;
                while (i13 < size2) {
                    RecyclerView.b0 b0Var3 = list3.get(i13);
                    if (left2 <= 0 || (right = b0Var3.f1629a.getRight() - width2) >= 0) {
                        list = list3;
                    } else {
                        list = list3;
                        if (b0Var3.f1629a.getRight() > b0Var.f1629a.getRight() && (iAbs4 = Math.abs(right)) > i12) {
                            i12 = iAbs4;
                            b0Var2 = b0Var3;
                        }
                    }
                    if (left2 < 0 && (left = b0Var3.f1629a.getLeft() - i3) > 0 && b0Var3.f1629a.getLeft() < b0Var.f1629a.getLeft() && (iAbs3 = Math.abs(left)) > i12) {
                        i12 = iAbs3;
                        b0Var2 = b0Var3;
                    }
                    if (top2 < 0 && (top = b0Var3.f1629a.getTop() - i4) > 0 && b0Var3.f1629a.getTop() < b0Var.f1629a.getTop() && (iAbs2 = Math.abs(top)) > i12) {
                        i12 = iAbs2;
                        b0Var2 = b0Var3;
                    }
                    if (top2 > 0 && (bottom = b0Var3.f1629a.getBottom() - height2) < 0 && b0Var3.f1629a.getBottom() > b0Var.f1629a.getBottom() && (iAbs = Math.abs(bottom)) > i12) {
                        i12 = iAbs;
                        b0Var2 = b0Var3;
                    }
                    i13++;
                    list3 = list;
                }
                if (b0Var2 == null) {
                    this.u.clear();
                    this.v.clear();
                } else {
                    b0Var2.e();
                    b0Var.e();
                    if (((b.c.a.f.q) this.m) == null) {
                        throw null;
                    }
                }
            }
        }
    }

    public void q(View view) {
        if (view == this.x) {
            this.x = null;
            if (this.w != null) {
                this.r.setChildDrawingOrderCallback(null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a7 A[PHI: r3
  0x00a7: PHI (r3v14 int) = (r3v13 int), (r3v16 int) binds: [B:42:0x00a5, B:31:0x008c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01b0  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x01cd  */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Throwable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void r(androidx.recyclerview.widget.RecyclerView.b0 r22, int r23) {
        /*
            Method dump skipped, instruction units count: 462
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.p.e.o.r(androidx.recyclerview.widget.RecyclerView$b0, int):void");
    }

    public void s(MotionEvent motionEvent, int i, int i2) {
        float x = motionEvent.getX(i2);
        float y = motionEvent.getY(i2);
        float f2 = x - this.f1234d;
        this.h = f2;
        this.i = y - this.f1235e;
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
}
