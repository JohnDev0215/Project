package y0;

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
    public static final Property<k, PointF> N = new C0076c(PointF.class, "topLeft");
    public static final Property<k, PointF> O = new d(PointF.class, "bottomRight");
    public static final Property<View, PointF> P = new e(PointF.class, "bottomRight");
    public static final Property<View, PointF> Q = new f(PointF.class, "topLeft");
    public static final Property<View, PointF> R = new g(PointF.class, "position");
    public static y0.j S = new y0.j();
    public int[] T = new int[2];
    public boolean U = false;
    public boolean V = false;

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f5854a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ BitmapDrawable f5855b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f5856c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ float f5857d;

        public a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f3) {
            this.f5854a = viewGroup;
            this.f5855b = bitmapDrawable;
            this.f5856c = view;
            this.f5857d = f3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b0.b(this.f5854a).b(this.f5855b);
            b0.g(this.f5856c, this.f5857d);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class b extends Property<Drawable, PointF> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f5859a;

        public b(Class cls, String str) {
            super(cls, str);
            this.f5859a = new Rect();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f5859a);
            Rect rect = this.f5859a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f5859a);
            this.f5859a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f5859a);
        }
    }

    /* JADX INFO: renamed from: y0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class C0076c extends Property<k, PointF> {
        public C0076c(Class cls, String str) {
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
        public final /* synthetic */ k f5860a;
        private k mViewBounds;

        public h(k kVar) {
            this.f5860a = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class i extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5862a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f5863b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Rect f5864c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f5865d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f5866e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f5867f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ int f5868g;

        public i(View view, Rect rect, int i3, int i4, int i5, int i6) {
            this.f5863b = view;
            this.f5864c = rect;
            this.f5865d = i3;
            this.f5866e = i4;
            this.f5867f = i5;
            this.f5868g = i6;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5862a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f5862a) {
                return;
            }
            l0.t.p0(this.f5863b, this.f5864c);
            b0.f(this.f5863b, this.f5865d, this.f5866e, this.f5867f, this.f5868g);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class j extends m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f5870a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f5871b;

        public j(ViewGroup viewGroup) {
            this.f5871b = viewGroup;
        }

        @Override // y0.m, y0.l.f
        public void a(l lVar) {
            w.c(this.f5871b, false);
        }

        @Override // y0.m, y0.l.f
        public void b(l lVar) {
            w.c(this.f5871b, true);
        }

        @Override // y0.m, y0.l.f
        public void d(l lVar) {
            w.c(this.f5871b, false);
            this.f5870a = true;
        }

        @Override // y0.l.f
        public void e(l lVar) {
            if (!this.f5870a) {
                w.c(this.f5871b, false);
            }
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5873a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5874b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5875c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5876d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f5877e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5878f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5879g;

        public k(View view) {
            this.f5877e = view;
        }

        public void a(PointF pointF) {
            this.f5875c = Math.round(pointF.x);
            this.f5876d = Math.round(pointF.y);
            int i3 = this.f5879g + 1;
            this.f5879g = i3;
            if (this.f5878f == i3) {
                b();
            }
        }

        public final void b() {
            b0.f(this.f5877e, this.f5873a, this.f5874b, this.f5875c, this.f5876d);
            this.f5878f = 0;
            this.f5879g = 0;
        }

        public void c(PointF pointF) {
            this.f5873a = Math.round(pointF.x);
            this.f5874b = Math.round(pointF.y);
            int i3 = this.f5878f + 1;
            this.f5878f = i3;
            if (i3 == this.f5879g) {
                b();
            }
        }
    }

    @Override // y0.l
    public String[] D() {
        return L;
    }

    public final void c0(r rVar) {
        View view = rVar.f5981b;
        if (!l0.t.P(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        rVar.f5980a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        rVar.f5980a.put("android:changeBounds:parent", rVar.f5981b.getParent());
        if (this.V) {
            rVar.f5981b.getLocationInWindow(this.T);
            rVar.f5980a.put("android:changeBounds:windowX", Integer.valueOf(this.T[0]));
            rVar.f5980a.put("android:changeBounds:windowY", Integer.valueOf(this.T[1]));
        }
        if (this.U) {
            rVar.f5980a.put("android:changeBounds:clip", l0.t.s(view));
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
        } else if (view2 == rVarT.f5981b) {
            return true;
        }
        return false;
    }

    @Override // y0.l
    public void g(r rVar) {
        c0(rVar);
    }

    @Override // y0.l
    public void j(r rVar) {
        c0(rVar);
    }

    @Override // y0.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        int i3;
        View view;
        int i4;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorC;
        if (rVar == null || rVar2 == null) {
            return null;
        }
        Map<String, Object> map = rVar.f5980a;
        Map<String, Object> map2 = rVar2.f5980a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = rVar2.f5981b;
        if (!d0(viewGroup2, viewGroup3)) {
            int iIntValue = ((Integer) rVar.f5980a.get("android:changeBounds:windowX")).intValue();
            int iIntValue2 = ((Integer) rVar.f5980a.get("android:changeBounds:windowY")).intValue();
            int iIntValue3 = ((Integer) rVar2.f5980a.get("android:changeBounds:windowX")).intValue();
            int iIntValue4 = ((Integer) rVar2.f5980a.get("android:changeBounds:windowY")).intValue();
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
            y0.g gVarV = v();
            int[] iArr = this.T;
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, y0.i.a(M, gVarV.a(iIntValue - iArr[0], iIntValue2 - iArr[1], iIntValue3 - iArr[0], iIntValue4 - iArr[1])));
            objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fC));
            return objectAnimatorOfPropertyValuesHolder;
        }
        Rect rect2 = (Rect) rVar.f5980a.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) rVar2.f5980a.get("android:changeBounds:bounds");
        int i5 = rect2.left;
        int i6 = rect3.left;
        int i7 = rect2.top;
        int i8 = rect3.top;
        int i9 = rect2.right;
        int i10 = rect3.right;
        int i11 = rect2.bottom;
        int i12 = rect3.bottom;
        int i13 = i9 - i5;
        int i14 = i11 - i7;
        int i15 = i10 - i6;
        int i16 = i12 - i8;
        Rect rect4 = (Rect) rVar.f5980a.get("android:changeBounds:clip");
        Rect rect5 = (Rect) rVar2.f5980a.get("android:changeBounds:clip");
        if ((i13 == 0 || i14 == 0) && (i15 == 0 || i16 == 0)) {
            i3 = 0;
        } else {
            i3 = (i5 == i6 && i7 == i8) ? 0 : 1;
            if (i9 != i10 || i11 != i12) {
                i3++;
            }
        }
        if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
            i3++;
        }
        if (i3 <= 0) {
            return null;
        }
        if (this.U) {
            view = view2;
            b0.f(view, i5, i7, Math.max(i13, i15) + i5, Math.max(i14, i16) + i7);
            ObjectAnimator objectAnimatorA = (i5 == i6 && i7 == i8) ? null : y0.f.a(view, R, v().a(i5, i7, i6, i8));
            if (rect4 == null) {
                i4 = 0;
                rect = new Rect(0, 0, i13, i14);
            } else {
                i4 = 0;
                rect = rect4;
            }
            Rect rect6 = rect5 == null ? new Rect(i4, i4, i15, i16) : rect5;
            if (rect.equals(rect6)) {
                objectAnimator = null;
            } else {
                l0.t.p0(view, rect);
                y0.j jVar = S;
                Object[] objArr = new Object[2];
                objArr[i4] = rect;
                objArr[1] = rect6;
                ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", jVar, objArr);
                objectAnimatorOfObject.addListener(new i(view, rect5, i6, i8, i10, i12));
                objectAnimator = objectAnimatorOfObject;
            }
            animatorC = q.c(objectAnimatorA, objectAnimator);
        } else {
            view = view2;
            b0.f(view, i5, i7, i9, i11);
            if (i3 != 2) {
                animatorC = (i5 == i6 && i7 == i8) ? y0.f.a(view, P, v().a(i9, i11, i10, i12)) : y0.f.a(view, Q, v().a(i5, i7, i6, i8));
            } else if (i13 == i15 && i14 == i16) {
                animatorC = y0.f.a(view, R, v().a(i5, i7, i6, i8));
            } else {
                k kVar = new k(view);
                ObjectAnimator objectAnimatorA2 = y0.f.a(kVar, N, v().a(i5, i7, i6, i8));
                ObjectAnimator objectAnimatorA3 = y0.f.a(kVar, O, v().a(i9, i11, i10, i12));
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
