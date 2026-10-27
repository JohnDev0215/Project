package b.q;

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
    public static final String[] L = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property<Drawable, PointF> M = new b(PointF.class, "boundsOrigin");
    public static final Property<k, PointF> N = new C0039c(PointF.class, "topLeft");
    public static final Property<k, PointF> O = new d(PointF.class, "bottomRight");
    public static final Property<View, PointF> P = new e(PointF.class, "bottomRight");
    public static final Property<View, PointF> Q = new f(PointF.class, "topLeft");
    public static final Property<View, PointF> R = new g(PointF.class, "position");
    public static b.q.j S = new b.q.j();
    public int[] T = new int[2];
    public boolean U = false;
    public boolean V = false;

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1431a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ BitmapDrawable f1432b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f1433c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ float f1434d;

        public a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f) {
            this.f1431a = viewGroup;
            this.f1432b = bitmapDrawable;
            this.f1433c = view;
            this.f1434d = f;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b0.b(this.f1431a).b(this.f1432b);
            b0.g(this.f1433c, this.f1434d);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class b extends Property<Drawable, PointF> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f1435a;

        public b(Class cls, String str) {
            super(cls, str);
            this.f1435a = new Rect();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f1435a);
            Rect rect = this.f1435a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f1435a);
            this.f1435a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f1435a);
        }
    }

    /* JADX INFO: renamed from: b.q.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class C0039c extends Property<k, PointF> {
        public C0039c(Class cls, String str) {
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
    public static class d extends Property<k, PointF> {
        public d(Class cls, String str) {
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
    public static class e extends Property<View, PointF> {
        public e(Class cls, String str) {
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
            b0.f(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class f extends Property<View, PointF> {
        public f(Class cls, String str) {
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
            b0.f(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class g extends Property<View, PointF> {
        public g(Class cls, String str) {
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
            b0.f(view, iRound, iRound2, view.getWidth() + iRound, view.getHeight() + iRound2);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ k f1436a;
        private k mViewBounds;

        public h(k kVar) {
            this.f1436a = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class i extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1438a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1439b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Rect f1440c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f1441d;
        public final /* synthetic */ int e;
        public final /* synthetic */ int f;
        public final /* synthetic */ int g;

        public i(View view, Rect rect, int i, int i2, int i3, int i4) {
            this.f1439b = view;
            this.f1440c = rect;
            this.f1441d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1438a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1438a) {
                return;
            }
            b.g.j.t.p0(this.f1439b, this.f1440c);
            b0.f(this.f1439b, this.f1441d, this.e, this.f, this.g);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class j extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1442a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1443b;

        public j(ViewGroup viewGroup) {
            this.f1443b = viewGroup;
        }

        @Override // b.q.m, b.q.l.f
        public void a(l lVar) {
            w.c(this.f1443b, false);
        }

        @Override // b.q.m, b.q.l.f
        public void b(l lVar) {
            w.c(this.f1443b, true);
        }

        @Override // b.q.m, b.q.l.f
        public void d(l lVar) {
            w.c(this.f1443b, false);
            this.f1442a = true;
        }

        @Override // b.q.l.f
        public void e(l lVar) {
            if (!this.f1442a) {
                w.c(this.f1443b, false);
            }
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1445a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1446b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1447c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1448d;
        public View e;
        public int f;
        public int g;

        public k(View view) {
            this.e = view;
        }

        public void a(PointF pointF) {
            this.f1447c = Math.round(pointF.x);
            this.f1448d = Math.round(pointF.y);
            int i = this.g + 1;
            this.g = i;
            if (this.f == i) {
                b();
            }
        }

        public final void b() {
            b0.f(this.e, this.f1445a, this.f1446b, this.f1447c, this.f1448d);
            this.f = 0;
            this.g = 0;
        }

        public void c(PointF pointF) {
            this.f1445a = Math.round(pointF.x);
            this.f1446b = Math.round(pointF.y);
            int i = this.f + 1;
            this.f = i;
            if (i == this.g) {
                b();
            }
        }
    }

    @Override // b.q.l
    public String[] D() {
        return L;
    }

    public final void c0(r rVar) {
        View view = rVar.f1513b;
        if (!b.g.j.t.P(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        rVar.f1512a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        rVar.f1512a.put("android:changeBounds:parent", rVar.f1513b.getParent());
        if (this.V) {
            rVar.f1513b.getLocationInWindow(this.T);
            rVar.f1512a.put("android:changeBounds:windowX", Integer.valueOf(this.T[0]));
            rVar.f1512a.put("android:changeBounds:windowY", Integer.valueOf(this.T[1]));
        }
        if (this.U) {
            rVar.f1512a.put("android:changeBounds:clip", b.g.j.t.s(view));
        }
    }

    public final boolean d0(View view, View view2) {
        if (!this.V) {
            return true;
        }
        r rVarT = t(view, true);
        if (rVarT == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == rVarT.f1513b) {
            return true;
        }
        return false;
    }

    @Override // b.q.l
    public void g(r rVar) {
        c0(rVar);
    }

    @Override // b.q.l
    public void j(r rVar) {
        c0(rVar);
    }

    @Override // b.q.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        int i2;
        View view;
        int i3;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorC;
        if (rVar == null || rVar2 == null) {
            return null;
        }
        Map<String, Object> map = rVar.f1512a;
        Map<String, Object> map2 = rVar2.f1512a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = rVar2.f1513b;
        if (!d0(viewGroup2, viewGroup3)) {
            int iIntValue = ((Integer) rVar.f1512a.get("android:changeBounds:windowX")).intValue();
            int iIntValue2 = ((Integer) rVar.f1512a.get("android:changeBounds:windowY")).intValue();
            int iIntValue3 = ((Integer) rVar2.f1512a.get("android:changeBounds:windowX")).intValue();
            int iIntValue4 = ((Integer) rVar2.f1512a.get("android:changeBounds:windowY")).intValue();
            if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.T);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(bitmapCreateBitmap));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            float fC = b0.c(view2);
            b0.g(view2, 0.0f);
            b0.b(viewGroup).a(bitmapDrawable);
            b.q.g gVarV = v();
            int[] iArr = this.T;
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, b.q.i.a(M, gVarV.a(iIntValue - iArr[0], iIntValue2 - iArr[1], iIntValue3 - iArr[0], iIntValue4 - iArr[1])));
            objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fC));
            return objectAnimatorOfPropertyValuesHolder;
        }
        Rect rect2 = (Rect) rVar.f1512a.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) rVar2.f1512a.get("android:changeBounds:bounds");
        int i4 = rect2.left;
        int i5 = rect3.left;
        int i6 = rect2.top;
        int i7 = rect3.top;
        int i8 = rect2.right;
        int i9 = rect3.right;
        int i10 = rect2.bottom;
        int i11 = rect3.bottom;
        int i12 = i8 - i4;
        int i13 = i10 - i6;
        int i14 = i9 - i5;
        int i15 = i11 - i7;
        Rect rect4 = (Rect) rVar.f1512a.get("android:changeBounds:clip");
        Rect rect5 = (Rect) rVar2.f1512a.get("android:changeBounds:clip");
        if ((i12 == 0 || i13 == 0) && (i14 == 0 || i15 == 0)) {
            i2 = 0;
        } else {
            i2 = (i4 == i5 && i6 == i7) ? 0 : 1;
            if (i8 != i9 || i10 != i11) {
                i2++;
            }
        }
        if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
            i2++;
        }
        if (i2 <= 0) {
            return null;
        }
        if (this.U) {
            view = view2;
            b0.f(view, i4, i6, Math.max(i12, i14) + i4, Math.max(i13, i15) + i6);
            ObjectAnimator objectAnimatorA = (i4 == i5 && i6 == i7) ? null : b.q.f.a(view, R, v().a(i4, i6, i5, i7));
            if (rect4 == null) {
                i3 = 0;
                rect = new Rect(0, 0, i12, i13);
            } else {
                i3 = 0;
                rect = rect4;
            }
            Rect rect6 = rect5 == null ? new Rect(i3, i3, i14, i15) : rect5;
            if (rect.equals(rect6)) {
                objectAnimator = null;
            } else {
                b.g.j.t.p0(view, rect);
                b.q.j jVar = S;
                Object[] objArr = new Object[2];
                objArr[i3] = rect;
                objArr[1] = rect6;
                ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", jVar, objArr);
                objectAnimatorOfObject.addListener(new i(view, rect5, i5, i7, i9, i11));
                objectAnimator = objectAnimatorOfObject;
            }
            animatorC = q.c(objectAnimatorA, objectAnimator);
        } else {
            view = view2;
            b0.f(view, i4, i6, i8, i10);
            if (i2 != 2) {
                animatorC = (i4 == i5 && i6 == i7) ? b.q.f.a(view, P, v().a(i8, i10, i9, i11)) : b.q.f.a(view, Q, v().a(i4, i6, i5, i7));
            } else if (i12 == i14 && i13 == i15) {
                animatorC = b.q.f.a(view, R, v().a(i4, i6, i5, i7));
            } else {
                k kVar = new k(view);
                ObjectAnimator objectAnimatorA2 = b.q.f.a(kVar, N, v().a(i4, i6, i5, i7));
                ObjectAnimator objectAnimatorA3 = b.q.f.a(kVar, O, v().a(i8, i10, i9, i11));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorA2, objectAnimatorA3);
                animatorSet.addListener(new h(kVar));
                animatorC = animatorSet;
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            w.c(viewGroup4, true);
            a(new j(viewGroup4));
        }
        return animatorC;
    }
}
