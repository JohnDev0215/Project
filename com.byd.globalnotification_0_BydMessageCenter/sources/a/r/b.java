package a.r;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class b extends a.r.i {
    public static final String[] L = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final Property<Drawable, PointF> M = new C0027b(PointF.class, "boundsOrigin");
    public static final Property<k, PointF> N = new c(PointF.class, "topLeft");
    public static final Property<k, PointF> O = new d(PointF.class, "bottomRight");
    public static final Property<View, PointF> P = new e(PointF.class, "bottomRight");
    public static final Property<View, PointF> Q = new f(PointF.class, "topLeft");
    public static final Property<View, PointF> R = new g(PointF.class, "position");
    public static a.r.g S = new a.r.g();
    public int[] I = new int[2];
    public boolean J = false;
    public boolean K = false;

    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1281a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ BitmapDrawable f1282b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ View f1283c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ float f1284d;

        public a(b bVar, ViewGroup viewGroup, BitmapDrawable bitmapDrawable, View view, float f2) {
            this.f1281a = viewGroup;
            this.f1282b = bitmapDrawable;
            this.f1283c = view;
            this.f1284d = f2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            s sVarA = t.a(this.f1281a);
            ((r) sVarA).f1355a.remove(this.f1282b);
            this.f1283c.setTransitionAlpha(this.f1284d);
        }
    }

    /* JADX INFO: renamed from: a.r.b$b, reason: collision with other inner class name */
    public static class C0027b extends Property<Drawable, PointF> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Rect f1285a;

        public C0027b(Class cls, String str) {
            super(cls, str);
            this.f1285a = new Rect();
        }

        @Override // android.util.Property
        public PointF get(Drawable drawable) {
            drawable.copyBounds(this.f1285a);
            Rect rect = this.f1285a;
            return new PointF(rect.left, rect.top);
        }

        @Override // android.util.Property
        public void set(Drawable drawable, PointF pointF) {
            Drawable drawable2 = drawable;
            PointF pointF2 = pointF;
            drawable2.copyBounds(this.f1285a);
            this.f1285a.offsetTo(Math.round(pointF2.x), Math.round(pointF2.y));
            drawable2.setBounds(this.f1285a);
        }
    }

    public static class c extends Property<k, PointF> {
        public c(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        public void set(k kVar, PointF pointF) {
            k kVar2 = kVar;
            PointF pointF2 = pointF;
            if (kVar2 == null) {
                throw null;
            }
            kVar2.f1295a = Math.round(pointF2.x);
            int iRound = Math.round(pointF2.y);
            kVar2.f1296b = iRound;
            int i = kVar2.f1300f + 1;
            kVar2.f1300f = i;
            if (i == kVar2.g) {
                t.e(kVar2.f1299e, kVar2.f1295a, iRound, kVar2.f1297c, kVar2.f1298d);
                kVar2.f1300f = 0;
                kVar2.g = 0;
            }
        }
    }

    public static class d extends Property<k, PointF> {
        public d(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public PointF get(k kVar) {
            return null;
        }

        @Override // android.util.Property
        public void set(k kVar, PointF pointF) {
            k kVar2 = kVar;
            PointF pointF2 = pointF;
            if (kVar2 == null) {
                throw null;
            }
            kVar2.f1297c = Math.round(pointF2.x);
            int iRound = Math.round(pointF2.y);
            kVar2.f1298d = iRound;
            int i = kVar2.g + 1;
            kVar2.g = i;
            if (kVar2.f1300f == i) {
                t.e(kVar2.f1299e, kVar2.f1295a, kVar2.f1296b, kVar2.f1297c, iRound);
                kVar2.f1300f = 0;
                kVar2.g = 0;
            }
        }
    }

    public static class e extends Property<View, PointF> {
        public e(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            t.e(view2, view2.getLeft(), view2.getTop(), Math.round(pointF2.x), Math.round(pointF2.y));
        }
    }

    public static class f extends Property<View, PointF> {
        public f(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            t.e(view2, Math.round(pointF2.x), Math.round(pointF2.y), view2.getRight(), view2.getBottom());
        }
    }

    public static class g extends Property<View, PointF> {
        public g(Class cls, String str) {
            super(cls, str);
        }

        @Override // android.util.Property
        public PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            int iRound = Math.round(pointF2.x);
            int iRound2 = Math.round(pointF2.y);
            t.e(view2, iRound, iRound2, view2.getWidth() + iRound, view2.getHeight() + iRound2);
        }
    }

    public class h extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ k f1286a;
        public k mViewBounds;

        public h(b bVar, k kVar) {
            this.f1286a = kVar;
            this.mViewBounds = this.f1286a;
        }
    }

    public class i extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1287a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ View f1288b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Rect f1289c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ int f1290d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ int f1291e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ int f1292f;
        public final /* synthetic */ int g;

        public i(b bVar, View view, Rect rect, int i, int i2, int i3, int i4) {
            this.f1288b = view;
            this.f1289c = rect;
            this.f1290d = i;
            this.f1291e = i2;
            this.f1292f = i3;
            this.g = i4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1287a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f1287a) {
                return;
            }
            a.h.k.q.Y(this.f1288b, this.f1289c);
            t.e(this.f1288b, this.f1290d, this.f1291e, this.f1292f, this.g);
        }
    }

    public class j extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1293a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ViewGroup f1294b;

        public j(b bVar, ViewGroup viewGroup) {
            this.f1294b = viewGroup;
        }

        @Override // a.r.l, a.r.i.d
        public void b(a.r.i iVar) {
            this.f1294b.suppressLayout(false);
            this.f1293a = true;
        }

        @Override // a.r.i.d
        public void c(a.r.i iVar) {
            if (!this.f1293a) {
                this.f1294b.suppressLayout(false);
            }
            iVar.w(this);
        }

        @Override // a.r.l, a.r.i.d
        public void d(a.r.i iVar) {
            this.f1294b.suppressLayout(false);
        }

        @Override // a.r.l, a.r.i.d
        public void e(a.r.i iVar) {
            this.f1294b.suppressLayout(true);
        }
    }

    public static class k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1295a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1296b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1297c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1298d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f1299e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1300f;
        public int g;

        public k(View view) {
            this.f1299e = view;
        }
    }

    public final void I(p pVar) {
        View view = pVar.f1349b;
        if (!a.h.k.q.C(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        pVar.f1348a.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        pVar.f1348a.put("android:changeBounds:parent", pVar.f1349b.getParent());
        if (this.K) {
            pVar.f1349b.getLocationInWindow(this.I);
            pVar.f1348a.put("android:changeBounds:windowX", Integer.valueOf(this.I[0]));
            pVar.f1348a.put("android:changeBounds:windowY", Integer.valueOf(this.I[1]));
        }
        if (this.J) {
            pVar.f1348a.put("android:changeBounds:clip", view.getClipBounds());
        }
    }

    @Override // a.r.i
    public void e(p pVar) {
        I(pVar);
    }

    @Override // a.r.i
    public void h(p pVar) {
        I(pVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x01b8  */
    @Override // a.r.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.animation.Animator l(android.view.ViewGroup r19, a.r.p r20, a.r.p r21) {
        /*
            Method dump skipped, instruction units count: 628
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.r.b.l(android.view.ViewGroup, a.r.p, a.r.p):android.animation.Animator");
    }

    @Override // a.r.i
    public String[] q() {
        return L;
    }
}
