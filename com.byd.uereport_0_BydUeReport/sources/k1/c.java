package k1;

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
    public static final String[] N = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property<Drawable, PointF> O = new b(PointF.class, "boundsOrigin");
    public static final Property<k, PointF> P = new C0070c(PointF.class, "topLeft");
    public static final Property<k, PointF> Q = new d(PointF.class, "bottomRight");
    public static final Property<View, PointF> R = new e(PointF.class, "bottomRight");
    public static final Property<View, PointF> S = new f(PointF.class, "topLeft");
    public static final Property<View, PointF> T = new g(PointF.class, "position");
    public static k1.j U = new k1.j();
    public int[] V = new int[2];
    public boolean W = false;
    public boolean X = false;

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f5698d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ BitmapDrawable f5699e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ View f5700f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ float f5701g;

        public a(ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f8) {
            this.f5698d = viewGroup;
            this.f5699e = bitmapDrawable;
            this.f5700f = view;
            this.f5701g = f8;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            b0.b(this.f5698d).b(this.f5699e);
            b0.g(this.f5700f, this.f5701g);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class b extends Property<Drawable, PointF> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f5703a;

        public b(Class cls, String str) {
            super(cls, str);
            this.f5703a = new Rect();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f5703a);
            Rect rect = this.f5703a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(Drawable drawable, PointF pointF) {
            drawable.copyBounds(this.f5703a);
            this.f5703a.offsetTo(Math.round(pointF.x), Math.round(pointF.y));
            drawable.setBounds(this.f5703a);
        }
    }

    /* JADX INFO: renamed from: k1.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class C0070c extends Property<k, PointF> {
        public C0070c(Class cls, String str) {
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ k f5704d;
        private k mViewBounds;

        public h(k kVar) {
            this.f5704d = kVar;
            this.mViewBounds = kVar;
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class i extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5706d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ View f5707e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Rect f5708f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ int f5709g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ int f5710h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public final /* synthetic */ int f5711i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public final /* synthetic */ int f5712j;

        public i(View view, Rect rect, int i8, int i9, int i10, int i11) {
            this.f5707e = view;
            this.f5708f = rect;
            this.f5709g = i8;
            this.f5710h = i9;
            this.f5711i = i10;
            this.f5712j = i11;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f5706d = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f5706d) {
                return;
            }
            o0.t.r0(this.f5707e, this.f5708f);
            b0.f(this.f5707e, this.f5709g, this.f5710h, this.f5711i, this.f5712j);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public class j extends m {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5714d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f5715e;

        public j(ViewGroup viewGroup) {
            this.f5715e = viewGroup;
        }

        @Override // k1.m, k1.l.f
        public void b(l lVar) {
            w.c(this.f5715e, false);
            this.f5714d = true;
        }

        @Override // k1.m, k1.l.f
        public void c(l lVar) {
            w.c(this.f5715e, false);
        }

        @Override // k1.m, k1.l.f
        public void d(l lVar) {
            w.c(this.f5715e, true);
        }

        @Override // k1.l.f
        public void e(l lVar) {
            if (!this.f5714d) {
                w.c(this.f5715e, false);
            }
            lVar.P(this);
        }
    }

    /* JADX INFO: compiled from: ChangeBounds.java */
    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5717a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5718b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5719c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5720d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f5721e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5722f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5723g;

        public k(View view) {
            this.f5721e = view;
        }

        public void a(PointF pointF) {
            this.f5719c = Math.round(pointF.x);
            this.f5720d = Math.round(pointF.y);
            int i8 = this.f5723g + 1;
            this.f5723g = i8;
            if (this.f5722f == i8) {
                b();
            }
        }

        public final void b() {
            b0.f(this.f5721e, this.f5717a, this.f5718b, this.f5719c, this.f5720d);
            this.f5722f = 0;
            this.f5723g = 0;
        }

        public void c(PointF pointF) {
            this.f5717a = Math.round(pointF.x);
            this.f5718b = Math.round(pointF.y);
            int i8 = this.f5722f + 1;
            this.f5722f = i8;
            if (i8 == this.f5723g) {
                b();
            }
        }
    }

    @Override // k1.l
    public String[] D() {
        return N;
    }

    public final void c0(r rVar) {
        View view = rVar.f5823b;
        if (!o0.t.Q(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        rVar.f5822a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        rVar.f5822a.put("android:changeBounds:parent", rVar.f5823b.getParent());
        if (this.X) {
            rVar.f5823b.getLocationInWindow(this.V);
            rVar.f5822a.put("android:changeBounds:windowX", Integer.valueOf(this.V[0]));
            rVar.f5822a.put("android:changeBounds:windowY", Integer.valueOf(this.V[1]));
        }
        if (this.W) {
            rVar.f5822a.put("android:changeBounds:clip", o0.t.t(view));
        }
    }

    public final boolean d0(View view, View view2) {
        if (!this.X) {
            return true;
        }
        r rVarT = t(view, true);
        if (rVarT == null) {
            if (view == view2) {
                return true;
            }
        } else if (view2 == rVarT.f5823b) {
            return true;
        }
        return false;
    }

    @Override // k1.l
    public void g(r rVar) {
        c0(rVar);
    }

    @Override // k1.l
    public void j(r rVar) {
        c0(rVar);
    }

    @Override // k1.l
    public Animator n(ViewGroup viewGroup, r rVar, r rVar2) {
        int i8;
        View view;
        int i9;
        Rect rect;
        ObjectAnimator objectAnimator;
        Animator animatorC;
        if (rVar == null || rVar2 == null) {
            return null;
        }
        Map<String, Object> map = rVar.f5822a;
        Map<String, Object> map2 = rVar2.f5822a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = rVar2.f5823b;
        if (!d0(viewGroup2, viewGroup3)) {
            int iIntValue = ((Integer) rVar.f5822a.get("android:changeBounds:windowX")).intValue();
            int iIntValue2 = ((Integer) rVar.f5822a.get("android:changeBounds:windowY")).intValue();
            int iIntValue3 = ((Integer) rVar2.f5822a.get("android:changeBounds:windowX")).intValue();
            int iIntValue4 = ((Integer) rVar2.f5822a.get("android:changeBounds:windowY")).intValue();
            if (iIntValue == iIntValue3 && iIntValue2 == iIntValue4) {
                return null;
            }
            viewGroup.getLocationInWindow(this.V);
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(view2.getWidth(), view2.getHeight(), Bitmap.Config.ARGB_8888);
            view2.draw(new Canvas(bitmapCreateBitmap));
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            float fC = b0.c(view2);
            b0.g(view2, 0.0f);
            b0.b(viewGroup).a(bitmapDrawable);
            k1.g gVarV = v();
            int[] iArr = this.V;
            ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(bitmapDrawable, k1.i.a(O, gVarV.a(iIntValue - iArr[0], iIntValue2 - iArr[1], iIntValue3 - iArr[0], iIntValue4 - iArr[1])));
            objectAnimatorOfPropertyValuesHolder.addListener(new a(viewGroup, bitmapDrawable, view2, fC));
            return objectAnimatorOfPropertyValuesHolder;
        }
        Rect rect2 = (Rect) rVar.f5822a.get("android:changeBounds:bounds");
        Rect rect3 = (Rect) rVar2.f5822a.get("android:changeBounds:bounds");
        int i10 = rect2.left;
        int i11 = rect3.left;
        int i12 = rect2.top;
        int i13 = rect3.top;
        int i14 = rect2.right;
        int i15 = rect3.right;
        int i16 = rect2.bottom;
        int i17 = rect3.bottom;
        int i18 = i14 - i10;
        int i19 = i16 - i12;
        int i20 = i15 - i11;
        int i21 = i17 - i13;
        Rect rect4 = (Rect) rVar.f5822a.get("android:changeBounds:clip");
        Rect rect5 = (Rect) rVar2.f5822a.get("android:changeBounds:clip");
        if ((i18 == 0 || i19 == 0) && (i20 == 0 || i21 == 0)) {
            i8 = 0;
        } else {
            i8 = (i10 == i11 && i12 == i13) ? 0 : 1;
            if (i14 != i15 || i16 != i17) {
                i8++;
            }
        }
        if ((rect4 != null && !rect4.equals(rect5)) || (rect4 == null && rect5 != null)) {
            i8++;
        }
        if (i8 <= 0) {
            return null;
        }
        if (this.W) {
            view = view2;
            b0.f(view, i10, i12, Math.max(i18, i20) + i10, Math.max(i19, i21) + i12);
            ObjectAnimator objectAnimatorA = (i10 == i11 && i12 == i13) ? null : k1.f.a(view, T, v().a(i10, i12, i11, i13));
            if (rect4 == null) {
                i9 = 0;
                rect = new Rect(0, 0, i18, i19);
            } else {
                i9 = 0;
                rect = rect4;
            }
            Rect rect6 = rect5 == null ? new Rect(i9, i9, i20, i21) : rect5;
            if (rect.equals(rect6)) {
                objectAnimator = null;
            } else {
                o0.t.r0(view, rect);
                k1.j jVar = U;
                Object[] objArr = new Object[2];
                objArr[i9] = rect;
                objArr[1] = rect6;
                ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(view, "clipBounds", jVar, objArr);
                objectAnimatorOfObject.addListener(new i(view, rect5, i11, i13, i15, i17));
                objectAnimator = objectAnimatorOfObject;
            }
            animatorC = q.c(objectAnimatorA, objectAnimator);
        } else {
            view = view2;
            b0.f(view, i10, i12, i14, i16);
            if (i8 != 2) {
                animatorC = (i10 == i11 && i12 == i13) ? k1.f.a(view, R, v().a(i14, i16, i15, i17)) : k1.f.a(view, S, v().a(i10, i12, i11, i13));
            } else if (i18 == i20 && i19 == i21) {
                animatorC = k1.f.a(view, T, v().a(i10, i12, i11, i13));
            } else {
                k kVar = new k(view);
                ObjectAnimator objectAnimatorA2 = k1.f.a(kVar, P, v().a(i10, i12, i11, i13));
                ObjectAnimator objectAnimatorA3 = k1.f.a(kVar, Q, v().a(i14, i16, i15, i17));
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
