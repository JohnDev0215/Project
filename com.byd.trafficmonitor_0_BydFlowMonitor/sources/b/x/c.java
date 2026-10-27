package b.x;

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
public class c extends m {
    public static final String[] L = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property<Drawable, PointF> M = new b(PointF.class, "boundsOrigin");
    public static final Property<k, PointF> N = new C0065c(PointF.class, "topLeft");
    public static final Property<k, PointF> O = new d(PointF.class, "bottomRight");
    public static final Property<View, PointF> P = new e(PointF.class, "bottomRight");
    public static final Property<View, PointF> Q = new f(PointF.class, "topLeft");
    public static final Property<View, PointF> R = new g(PointF.class, "position");
    public static b.x.k S = new b.x.k();
    public int[] T = new int[2];
    public boolean U = false;
    public boolean V = false;

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f3203a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ BitmapDrawable f3204b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f3205c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ float f3206d;

        public a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f2) {
            this.f3203a = viewGroup;
            this.f3204b = bitmapDrawable;
            this.f3205c = view;
            this.f3206d = f2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            c0.b(this.f3203a).b(this.f3204b);
            c0.g(this.f3205c, this.f3206d);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class b extends Property<Drawable, PointF> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f3208a;

        public b(Class cls, String str) {
            super(cls, str);
            this.f3208a = new Rect();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f3208a);
            Rect rect = this.f3208a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f3208a);
            this.f3208a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f3208a);
        }
    }

    /* JADX INFO: renamed from: b.x.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class C0065c extends Property<k, PointF> {
        public C0065c(Class cls, String str) {
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
            c0.f(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
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
            c0.f(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
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
            c0.f(view, iRound, iRound2, view.getWidth() + iRound, view.getHeight() + iRound2);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ k f3209a;
        private k mViewBounds;

        public h(k kVar) {
            this.f3209a = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class i extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f3212b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Rect f3213c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f3214d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f3215e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f3216f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ int f3217g;

        public i(View view, Rect rect, int i2, int i3, int i4, int i5) {
            this.f3212b = view;
            this.f3213c = rect;
            this.f3214d = i2;
            this.f3215e = i3;
            this.f3216f = i4;
            this.f3217g = i5;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f3211a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f3211a) {
                return;
            }
            b.h.m.t.q0(this.f3212b, this.f3213c);
            c0.f(this.f3212b, this.f3214d, this.f3215e, this.f3216f, this.f3217g);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class j extends n {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3219a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f3220b;

        public j(ViewGroup viewGroup) {
            this.f3220b = viewGroup;
        }

        @Override // b.x.n, b.x.m.f
        public void a(m mVar) {
            x.c(this.f3220b, false);
        }

        @Override // b.x.n, b.x.m.f
        public void b(m mVar) {
            x.c(this.f3220b, true);
        }

        @Override // b.x.n, b.x.m.f
        public void d(m mVar) {
            x.c(this.f3220b, false);
            this.f3219a = true;
        }

        @Override // b.x.m.f
        public void e(m mVar) {
            if (!this.f3219a) {
                x.c(this.f3220b, false);
            }
            mVar.Q(this);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3222a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3223b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3224c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3225d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f3226e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3227f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f3228g;

        public k(View view) {
            this.f3226e = view;
        }

        public void a(PointF pointF) {
            this.f3224c = Math.round(pointF.x);
            this.f3225d = Math.round(pointF.y);
            int i2 = this.f3228g + 1;
            this.f3228g = i2;
            if (this.f3227f == i2) {
                b();
            }
        }

        public final void b() {
            c0.f(this.f3226e, this.f3222a, this.f3223b, this.f3224c, this.f3225d);
            this.f3227f = 0;
            this.f3228g = 0;
        }

        public void c(PointF pointF) {
            this.f3222a = Math.round(pointF.x);
            this.f3223b = Math.round(pointF.y);
            int i2 = this.f3227f + 1;
            this.f3227f = i2;
            if (i2 == this.f3228g) {
                b();
            }
        }
    }

    @Override // b.x.m
    public String[] E() {
        return L;
    }

    public final void d0(s sVar) {
        View view = sVar.f3316b;
        if (!b.h.m.t.P(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        sVar.f3315a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        sVar.f3315a.put("android:changeBounds:parent", sVar.f3316b.getParent());
        if (this.V) {
            sVar.f3316b.getLocationInWindow(this.T);
            sVar.f3315a.put("android:changeBounds:windowX", Integer.valueOf(this.T[0]));
            sVar.f3315a.put("android:changeBounds:windowY", Integer.valueOf(this.T[1]));
        }
        if (this.U) {
            sVar.f3315a.put("android:changeBounds:clip", b.h.m.t.s(view));
        }
    }

    public final boolean e0(View view, View view2) {
        if (!this.V) {
            return true;
        }
        s sVarS = s(view, true);
        if (sVarS == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == sVarS.f3316b) {
            return true;
        }
        return false;
    }

    @Override // b.x.m
    public void f(s sVar) {
        d0(sVar);
    }

    @Override // b.x.m
    public void i(s sVar) {
        d0(sVar);
    }

    @Override // b.x.m
    public Animator m(ViewGroup viewGroup, s sVar, s sVar2) {
        int i2;
        View view;
        int i3;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorC;
        if (sVar == null || sVar2 == null) {
            return null;
        }
        Map<String, Object> map = sVar.f3315a;
        Map<String, Object> map2 = sVar2.f3315a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = sVar2.f3316b;
        if (!e0(viewGroup2, viewGroup3)) {
            int iIntValue = ((Integer) sVar.f3315a.get("android:changeBounds:windowX")).intValue();
            int iIntValue2 = ((Integer) sVar.f3315a.get("android:changeBounds:windowY")).intValue();
            int iIntValue3 = ((Integer) sVar2.f3315a.get("android:changeBounds:windowX")).intValue();
            int iIntValue4 = ((Integer) sVar2.f3315a.get("android:changeBounds:windowY")).intValue();
            if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.T);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(bitmapCreateBitmap));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            float fC = c0.c(view2);
            c0.g(view2, 0.0f);
            c0.b(viewGroup).a(bitmapDrawable);
            b.x.g gVarU = u();
            int[] iArr = this.T;
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, b.x.i.a(M, gVarU.a(iIntValue - iArr[0], iIntValue2 - iArr[1], iIntValue3 - iArr[0], iIntValue4 - iArr[1])));
            objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fC));
            return objectAnimatorOfPropertyValuesHolder;
        }
        Rect rect2 = (Rect) sVar.f3315a.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) sVar2.f3315a.get("android:changeBounds:bounds");
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
        Rect rect4 = (Rect) sVar.f3315a.get("android:changeBounds:clip");
        Rect rect5 = (Rect) sVar2.f3315a.get("android:changeBounds:clip");
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
            c0.f(view, i4, i6, Math.max(i12, i14) + i4, Math.max(i13, i15) + i6);
            ObjectAnimator objectAnimatorA = (i4 == i5 && i6 == i7) ? null : b.x.f.a(view, R, u().a(i4, i6, i5, i7));
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
                b.h.m.t.q0(view, rect);
                b.x.k kVar = S;
                Object[] objArr = new Object[2];
                objArr[i3] = rect;
                objArr[1] = rect6;
                ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", kVar, objArr);
                objectAnimatorOfObject.addListener(new i(view, rect5, i5, i7, i9, i11));
                objectAnimator = objectAnimatorOfObject;
            }
            animatorC = r.c(objectAnimatorA, objectAnimator);
        } else {
            view = view2;
            c0.f(view, i4, i6, i8, i10);
            if (i2 != 2) {
                animatorC = (i4 == i5 && i6 == i7) ? b.x.f.a(view, P, u().a(i8, i10, i9, i11)) : b.x.f.a(view, Q, u().a(i4, i6, i5, i7));
            } else if (i12 == i14 && i13 == i15) {
                animatorC = b.x.f.a(view, R, u().a(i4, i6, i5, i7));
            } else {
                k kVar2 = new k(view);
                ObjectAnimator objectAnimatorA2 = b.x.f.a(kVar2, N, u().a(i4, i6, i5, i7));
                ObjectAnimator objectAnimatorA3 = b.x.f.a(kVar2, O, u().a(i8, i10, i9, i11));
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorA2, objectAnimatorA3);
                animatorSet.addListener(new h(kVar2));
                animatorC = animatorSet;
            }
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            x.c(viewGroup4, true);
            a(new j(viewGroup4));
        }
        return animatorC;
    }
}
