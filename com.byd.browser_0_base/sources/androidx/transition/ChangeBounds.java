package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import defpackage.df3;
import defpackage.fh3;
import defpackage.in3;
import defpackage.om3;
import defpackage.sv2;
import defpackage.wn3;
import defpackage.x92;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ChangeBounds extends Transition {
    public static final String[] D = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    public static final b E;
    public static final c F;
    public static final d G;
    public static final e H;
    public static final f I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final x92 f22J;
    public boolean C;

    public class a extends Property<Drawable, PointF> {
        public final Rect a;

        public a() {
            super(PointF.class, "boundsOrigin");
            this.a = new Rect();
        }

        @Override // android.util.Property
        public final PointF get(Drawable drawable) {
            drawable.copyBounds(this.a);
            return new PointF(r1.left, r1.top);
        }

        @Override // android.util.Property
        public final void set(Drawable drawable, PointF pointF) {
            Drawable drawable2 = drawable;
            PointF pointF2 = pointF;
            Rect rect = this.a;
            drawable2.copyBounds(rect);
            rect.offsetTo(Math.round(pointF2.x), Math.round(pointF2.y));
            drawable2.setBounds(rect);
        }
    }

    public class b extends Property<j, PointF> {
        public b() {
            super(PointF.class, "topLeft");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ PointF get(j jVar) {
            return null;
        }

        @Override // android.util.Property
        public final void set(j jVar, PointF pointF) {
            j jVar2 = jVar;
            PointF pointF2 = pointF;
            jVar2.getClass();
            jVar2.a = Math.round(pointF2.x);
            int iRound = Math.round(pointF2.y);
            jVar2.b = iRound;
            int i = jVar2.f + 1;
            jVar2.f = i;
            if (i == jVar2.g) {
                wn3.a(jVar2.e, jVar2.a, iRound, jVar2.c, jVar2.d);
                jVar2.f = 0;
                jVar2.g = 0;
            }
        }
    }

    public class c extends Property<j, PointF> {
        public c() {
            super(PointF.class, "bottomRight");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ PointF get(j jVar) {
            return null;
        }

        @Override // android.util.Property
        public final void set(j jVar, PointF pointF) {
            j jVar2 = jVar;
            PointF pointF2 = pointF;
            jVar2.getClass();
            jVar2.c = Math.round(pointF2.x);
            int iRound = Math.round(pointF2.y);
            jVar2.d = iRound;
            int i = jVar2.g + 1;
            jVar2.g = i;
            if (jVar2.f == i) {
                wn3.a(jVar2.e, jVar2.a, jVar2.b, jVar2.c, iRound);
                jVar2.f = 0;
                jVar2.g = 0;
            }
        }
    }

    public class d extends Property<View, PointF> {
        public d() {
            super(PointF.class, "bottomRight");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public final void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            wn3.a(view2, view2.getLeft(), view2.getTop(), Math.round(pointF2.x), Math.round(pointF2.y));
        }
    }

    public class e extends Property<View, PointF> {
        public e() {
            super(PointF.class, "topLeft");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public final void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            wn3.a(view2, Math.round(pointF2.x), Math.round(pointF2.y), view2.getRight(), view2.getBottom());
        }
    }

    public class f extends Property<View, PointF> {
        public f() {
            super(PointF.class, "position");
        }

        @Override // android.util.Property
        public final /* bridge */ /* synthetic */ PointF get(View view) {
            return null;
        }

        @Override // android.util.Property
        public final void set(View view, PointF pointF) {
            View view2 = view;
            PointF pointF2 = pointF;
            int iRound = Math.round(pointF2.x);
            int iRound2 = Math.round(pointF2.y);
            wn3.a(view2, iRound, iRound2, view2.getWidth() + iRound, view2.getHeight() + iRound2);
        }
    }

    public class g extends AnimatorListenerAdapter {
        private j mViewBounds;

        public g(j jVar) {
            this.mViewBounds = jVar;
        }
    }

    public class h extends AnimatorListenerAdapter {
        public boolean e;
        public final /* synthetic */ View f;
        public final /* synthetic */ Rect g;
        public final /* synthetic */ int h;
        public final /* synthetic */ int i;
        public final /* synthetic */ int j;
        public final /* synthetic */ int k;

        public h(View view, Rect rect, int i, int i2, int i3, int i4) {
            this.f = view;
            this.g = rect;
            this.h = i;
            this.i = i2;
            this.j = i3;
            this.k = i4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.e = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.e) {
                return;
            }
            WeakHashMap<View, in3> weakHashMap = om3.a;
            View view = this.f;
            om3.f.b(view, this.g);
            wn3.a(view, this.h, this.i, this.j, this.k);
        }
    }

    public class i extends androidx.transition.g {
        public boolean e = false;
        public final /* synthetic */ ViewGroup f;

        public i(ViewGroup viewGroup) {
            this.f = viewGroup;
        }

        @Override // androidx.transition.g, androidx.transition.Transition.d
        public final void a() {
            this.f.suppressLayout(false);
        }

        @Override // androidx.transition.g, androidx.transition.Transition.d
        public final void c() {
            this.f.suppressLayout(false);
            this.e = true;
        }

        @Override // androidx.transition.Transition.d
        public final void d(Transition transition) {
            if (!this.e) {
                this.f.suppressLayout(false);
            }
            transition.C(this);
        }

        @Override // androidx.transition.g, androidx.transition.Transition.d
        public final void e() {
            this.f.suppressLayout(true);
        }
    }

    public static class j {
        public int a;
        public int b;
        public int c;
        public int d;
        public final View e;
        public int f;
        public int g;

        public j(View view) {
            this.e = view;
        }
    }

    static {
        new a();
        E = new b();
        F = new c();
        G = new d();
        H = new e();
        I = new f();
        f22J = new x92();
    }

    public ChangeBounds() {
        this.C = false;
    }

    public final void Q(df3 df3Var) {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        View view = df3Var.b;
        if (!om3.g.c(view) && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        HashMap map = df3Var.a;
        map.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        map.put("android:changeBounds:parent", view.getParent());
        if (this.C) {
            map.put("android:changeBounds:clip", om3.f.a(view));
        }
    }

    @Override // androidx.transition.Transition
    public final void i(df3 df3Var) {
        Q(df3Var);
    }

    @Override // androidx.transition.Transition
    public final void l(df3 df3Var) {
        Q(df3Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01ad  */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.Animator p(android.view.ViewGroup r21, defpackage.df3 r22, defpackage.df3 r23) {
        /*
            Method dump skipped, instruction units count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.ChangeBounds.p(android.view.ViewGroup, df3, df3):android.animation.Animator");
    }

    @Override // androidx.transition.Transition
    public final String[] v() {
        return D;
    }

    @SuppressLint({"RestrictedApi"})
    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sv2.b);
        boolean z = fh3.g((XmlResourceParser) attributeSet, "resizeClip") ? typedArrayObtainStyledAttributes.getBoolean(0, false) : false;
        typedArrayObtainStyledAttributes.recycle();
        this.C = z;
    }
}
