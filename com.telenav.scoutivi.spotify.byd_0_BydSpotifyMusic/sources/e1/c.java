package e1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

/* JADX INFO: compiled from: ChangeBounds.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends l {
    private static final String[] R = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private static final Property<Drawable, PointF> S = new b(PointF.class, "boundsOrigin");
    private static final Property<k, PointF> T = new C0068c(PointF.class, "topLeft");
    private static final Property<k, PointF> U = new d(PointF.class, "bottomRight");
    private static final Property<View, PointF> V = new e(PointF.class, "bottomRight");
    private static final Property<View, PointF> W = new f(PointF.class, "topLeft");
    private static final Property<View, PointF> X = new g(PointF.class, "position");
    private static e1.j Y = new e1.j();
    private int[] O = new int[2];
    private boolean P = false;
    private boolean Q = false;

    /* JADX INFO: compiled from: ChangeBounds.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f6047a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ BitmapDrawable f6048b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ View f6049c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ float f6050d;

        a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f7) {
            this.f6047a = viewGroup;
            this.f6048b = bitmapDrawable;
            this.f6049c = view;
            this.f6050d = f7;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            y.b(this.f6047a).d(this.f6048b);
            y.g(this.f6049c, this.f6050d);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    static class b extends Property<Drawable, PointF> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Rect f6052a;

        b(Class cls, String str) {
            super(cls, str);
            this.f6052a = new Rect();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f6052a);
            Rect rect = this.f6052a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f6052a);
            this.f6052a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f6052a);
        }
    }

    /* JADX INFO: renamed from: e1.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChangeBounds.java */
    static class C0068c extends Property<k, PointF> {
        C0068c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.c(pointF);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    static class d extends Property<k, PointF> {
        d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(k kVar, PointF pointF) {
            kVar.a(pointF);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    static class e extends Property<View, PointF> {
        e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            y.f(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    static class f extends Property<View, PointF> {
        f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            y.f(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    static class g extends Property<View, PointF> {
        g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, PointF pointF) {
            int iRound = Math.round(pointF.x);
            int iRound2 = Math.round(pointF.y);
            y.f(view, iRound, iRound2, view.getWidth() + iRound, view.getHeight() + iRound2);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ k f6053a;
        private k mViewBounds;

        h(k kVar) {
            this.f6053a = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    class i extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f6055a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f6056b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Rect f6057c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f6058d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f6059e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ int f6060f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ int f6061g;

        i(View view, Rect rect, int i7, int i8, int i9, int i10) {
            this.f6056b = view;
            this.f6057c = rect;
            this.f6058d = i7;
            this.f6059e = i8;
            this.f6060f = i9;
            this.f6061g = i10;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f6055a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f6055a) {
                return;
            }
            i0.u.q0(this.f6056b, this.f6057c);
            y.f(this.f6056b, this.f6058d, this.f6059e, this.f6060f, this.f6061g);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    class j extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f6063a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ViewGroup f6064b;

        j(ViewGroup viewGroup) {
            this.f6064b = viewGroup;
        }

        @Override // e1.m, e1.l.f
        public void b(l lVar) {
            v.c(this.f6064b, false);
        }

        @Override // e1.m, e1.l.f
        public void c(l lVar) {
            v.c(this.f6064b, true);
        }

        @Override // e1.m, e1.l.f
        public void d(l lVar) {
            v.c(this.f6064b, false);
            this.f6063a = true;
        }

        @Override // e1.l.f
        public void e(l lVar) {
            if (!this.f6063a) {
                v.c(this.f6064b, false);
            }
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    private static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f6066a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f6067b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f6068c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f6069d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private View f6070e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f6071f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private int f6072g;

        k(View view) {
            this.f6070e = view;
        }

        private void b() {
            y.f(this.f6070e, this.f6066a, this.f6067b, this.f6068c, this.f6069d);
            this.f6071f = 0;
            this.f6072g = 0;
        }

        void a(PointF pointF) {
            this.f6068c = Math.round(pointF.x);
            this.f6069d = Math.round(pointF.y);
            int i7 = this.f6072g + 1;
            this.f6072g = i7;
            if (this.f6071f == i7) {
                b();
            }
        }

        void c(PointF pointF) {
            this.f6066a = Math.round(pointF.x);
            this.f6067b = Math.round(pointF.y);
            int i7 = this.f6071f + 1;
            this.f6071f = i7;
            if (i7 == this.f6072g) {
                b();
            }
        }
    }

    private void c0(r rVar) {
        View view = rVar.f6161b;
        if (!i0.u.P(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        rVar.f6160a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        rVar.f6160a.put("android:changeBounds:parent", rVar.f6161b.getParent());
        if (this.Q) {
            rVar.f6161b.getLocationInWindow(this.O);
            rVar.f6160a.put("android:changeBounds:windowX", Integer.valueOf(this.O[0]));
            rVar.f6160a.put("android:changeBounds:windowY", Integer.valueOf(this.O[1]));
        }
        if (this.P) {
            rVar.f6160a.put("android:changeBounds:clip", i0.u.r(view));
        }
    }

    private boolean d0(View view, View view2) {
        if (!this.Q) {
            return true;
        }
        r rVarT = t(view, true);
        if (rVarT == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == rVarT.f6161b) {
            return true;
        }
        return false;
    }

    @Override // e1.l
    public String[] D() {
        return R;
    }

    @Override // e1.l
    public void f(r rVar) {
        c0(rVar);
    }

    @Override // e1.l
    public void i(r rVar) {
        c0(rVar);
    }

    @Override // e1.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        int i7;
        View view;
        int i8;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorC;
        if (rVar == null || rVar2 == null) {
            return null;
        }
        Map<String, Object> map = rVar.f6160a;
        Map<String, Object> map2 = rVar2.f6160a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = rVar2.f6161b;
        if (!d0(viewGroup2, viewGroup3)) {
            int iIntValue = ((Integer) rVar.f6160a.get("android:changeBounds:windowX")).intValue();
            int iIntValue2 = ((Integer) rVar.f6160a.get("android:changeBounds:windowY")).intValue();
            int iIntValue3 = ((Integer) rVar2.f6160a.get("android:changeBounds:windowX")).intValue();
            int iIntValue4 = ((Integer) rVar2.f6160a.get("android:changeBounds:windowY")).intValue();
            if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.O);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(bitmapCreateBitmap));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            float fC = y.c(view2);
            y.g(view2, 0.0f);
            y.b(viewGroup).b(bitmapDrawable);
            e1.g gVarV = v();
            int[] iArr = this.O;
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, e1.h.a(S, gVarV.a(iIntValue - iArr[0], iIntValue2 - iArr[1], iIntValue3 - iArr[0], iIntValue4 - iArr[1])));
            objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fC));
            return objectAnimatorOfPropertyValuesHolder;
        }
        Rect rect2 = (Rect) rVar.f6160a.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) rVar2.f6160a.get("android:changeBounds:bounds");
        int i9 = rect2.left;
        int i10 = rect3.left;
        int i11 = rect2.top;
        int i12 = rect3.top;
        int i13 = rect2.right;
        int i14 = rect3.right;
        int i15 = rect2.bottom;
        int i16 = rect3.bottom;
        int i17 = i13 - i9;
        int i18 = i15 - i11;
        int i19 = i14 - i10;
        int i20 = i16 - i12;
        Rect rect4 = (Rect) rVar.f6160a.get("android:changeBounds:clip");
        Rect rect5 = (Rect) rVar2.f6160a.get("android:changeBounds:clip");
        if ((i17 == 0 || i18 == 0) && (i19 == 0 || i20 == 0)) {
            i7 = 0;
        } else {
            i7 = (i9 == i10 && i11 == i12) ? 0 : 1;
            if (i13 != i14 || i15 != i16) {
                i7++;
            }
        }
        if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
            i7++;
        }
        if (i7 <= 0) {
            return null;
        }
        if (this.P) {
            view = view2;
            y.f(view, i9, i11, Math.max(i17, i19) + i9, Math.max(i18, i20) + i11);
            ObjectAnimator objectAnimatorA = (i9 == i10 && i11 == i12) ? null : e1.f.a(view, X, v().a(i9, i11, i10, i12));
            if (rect4 == null) {
                i8 = 0;
                rect = new Rect(0, 0, i17, i18);
            } else {
                i8 = 0;
                rect = rect4;
            }
            Rect rect6 = rect5 == null ? new Rect(i8, i8, i19, i20) : rect5;
            if (rect.equals(rect6)) {
                objectAnimator = null;
            } else {
                i0.u.q0(view, rect);
                e1.j jVar = Y;
                Object[] objArr = new Object[2];
                objArr[i8] = rect;
                objArr[1] = rect6;
                ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", jVar, objArr);
                objectAnimatorOfObject.addListener(new i(view, rect5, i10, i12, i14, i16));
                objectAnimator = objectAnimatorOfObject;
            }
            animatorC = q.c(objectAnimatorA, objectAnimator);
        } else {
            view = view2;
            y.f(view, i9, i11, i13, i15);
            if (i7 != 2) {
                animatorC = (i9 == i10 && i11 == i12) ? e1.f.a(view, V, v().a(i13, i15, i14, i16)) : e1.f.a(view, W, v().a(i9, i11, i10, i12));
            } else if (i17 == i19 && i18 == i20) {
                animatorC = e1.f.a(view, X, v().a(i9, i11, i10, i12));
            } else {
                k kVar = new k(view);
                ObjectAnimator objectAnimatorA2 = e1.f.a(kVar, T, v().a(i9, i11, i10, i12));
                ObjectAnimator objectAnimatorA3 = e1.f.a(kVar, U, v().a(i13, i15, i14, i16));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorA2, objectAnimatorA3);
                animatorSet.addListener(new h(kVar));
                animatorC = animatorSet;
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            v.c(viewGroup4, true);
            a(new j(viewGroup4));
        }
        return animatorC;
    }
}
