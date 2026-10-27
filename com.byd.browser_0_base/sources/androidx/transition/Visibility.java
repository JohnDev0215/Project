package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;
import defpackage.df3;
import defpackage.fh3;
import defpackage.sv2;
import defpackage.wn3;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class Visibility extends Transition {
    public static final String[] D = {"android:visibility:visibility", "android:visibility:parent"};
    public int C;

    public static class b {
        public boolean a;
        public boolean b;
        public int c;
        public int d;
        public ViewGroup e;
        public ViewGroup f;
    }

    public Visibility() {
        this.C = 3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static androidx.transition.Visibility.b S(defpackage.df3 r8, defpackage.df3 r9) {
        /*
            androidx.transition.Visibility$b r0 = new androidx.transition.Visibility$b
            r0.<init>()
            r1 = 0
            r0.a = r1
            r0.b = r1
            java.lang.String r2 = "android:visibility:parent"
            r3 = 0
            r4 = -1
            java.lang.String r5 = "android:visibility:visibility"
            if (r8 == 0) goto L2f
            java.util.HashMap r6 = r8.a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L2f
            java.lang.Object r7 = r6.get(r5)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r0.c = r7
            java.lang.Object r6 = r6.get(r2)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r0.e = r6
            goto L33
        L2f:
            r0.c = r4
            r0.e = r3
        L33:
            if (r9 == 0) goto L52
            java.util.HashMap r6 = r9.a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L52
            java.lang.Object r3 = r6.get(r5)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r0.d = r3
            java.lang.Object r2 = r6.get(r2)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r0.f = r2
            goto L56
        L52:
            r0.d = r4
            r0.f = r3
        L56:
            r2 = 1
            if (r8 == 0) goto L8a
            if (r9 == 0) goto L8a
            int r8 = r0.c
            int r9 = r0.d
            if (r8 != r9) goto L68
            android.view.ViewGroup r3 = r0.e
            android.view.ViewGroup r4 = r0.f
            if (r3 != r4) goto L68
            return r0
        L68:
            if (r8 == r9) goto L78
            if (r8 != 0) goto L71
            r0.b = r1
            r0.a = r2
            goto L9f
        L71:
            if (r9 != 0) goto L9f
            r0.b = r2
            r0.a = r2
            goto L9f
        L78:
            android.view.ViewGroup r8 = r0.f
            if (r8 != 0) goto L81
            r0.b = r1
            r0.a = r2
            goto L9f
        L81:
            android.view.ViewGroup r8 = r0.e
            if (r8 != 0) goto L9f
            r0.b = r2
            r0.a = r2
            goto L9f
        L8a:
            if (r8 != 0) goto L95
            int r8 = r0.d
            if (r8 != 0) goto L95
            r0.b = r2
            r0.a = r2
            goto L9f
        L95:
            if (r9 != 0) goto L9f
            int r8 = r0.c
            if (r8 != 0) goto L9f
            r0.b = r1
            r0.a = r2
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.S(df3, df3):androidx.transition.Visibility$b");
    }

    public final void Q(df3 df3Var) {
        View view = df3Var.b;
        int visibility = view.getVisibility();
        HashMap map = df3Var.a;
        map.put("android:visibility:visibility", Integer.valueOf(visibility));
        map.put("android:visibility:parent", view.getParent());
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        map.put("android:visibility:screenLocation", iArr);
    }

    public Animator T(ViewGroup viewGroup, View view, df3 df3Var, df3 df3Var2) {
        return null;
    }

    public Animator U(ViewGroup viewGroup, View view, df3 df3Var) {
        return null;
    }

    @Override // androidx.transition.Transition
    public void i(df3 df3Var) {
        Q(df3Var);
    }

    @Override // androidx.transition.Transition
    public void l(df3 df3Var) {
        Q(df3Var);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003e, code lost:
    
        if (S(s(r5, false), x(r5, false)).a != false) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01d1  */
    @Override // androidx.transition.Transition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.animation.Animator p(android.view.ViewGroup r20, defpackage.df3 r21, defpackage.df3 r22) {
        /*
            Method dump skipped, instruction units count: 610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Visibility.p(android.view.ViewGroup, df3, df3):android.animation.Animator");
    }

    @Override // androidx.transition.Transition
    public final String[] v() {
        return D;
    }

    @Override // androidx.transition.Transition
    public final boolean y(df3 df3Var, df3 df3Var2) {
        if (df3Var == null && df3Var2 == null) {
            return false;
        }
        if (df3Var != null && df3Var2 != null && df3Var2.a.containsKey("android:visibility:visibility") != df3Var.a.containsKey("android:visibility:visibility")) {
            return false;
        }
        b bVarS = S(df3Var, df3Var2);
        if (bVarS.a) {
            return bVarS.c == 0 || bVarS.d == 0;
        }
        return false;
    }

    @SuppressLint({"RestrictedApi"})
    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = 3;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sv2.c);
        int iD = fh3.d(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (iD != 0) {
            if ((iD & (-4)) == 0) {
                this.C = iD;
                return;
            }
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
    }

    public static class a extends AnimatorListenerAdapter implements Transition.d {
        public final View e;
        public final int f;
        public final ViewGroup g;
        public boolean i;
        public boolean j = false;
        public final boolean h = true;

        public a(View view, int i) {
            this.e = view;
            this.f = i;
            this.g = (ViewGroup) view.getParent();
            f(true);
        }

        @Override // androidx.transition.Transition.d
        public final void a() {
            f(false);
        }

        @Override // androidx.transition.Transition.d
        public final void d(Transition transition) {
            if (!this.j) {
                wn3.b(this.e, this.f);
                ViewGroup viewGroup = this.g;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            f(false);
            transition.C(this);
        }

        @Override // androidx.transition.Transition.d
        public final void e() {
            f(true);
        }

        public final void f(boolean z) {
            ViewGroup viewGroup;
            if (!this.h || this.i == z || (viewGroup = this.g) == null) {
                return;
            }
            this.i = z;
            viewGroup.suppressLayout(z);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.j = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (!this.j) {
                wn3.b(this.e, this.f);
                ViewGroup viewGroup = this.g;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            f(false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public final void onAnimationPause(Animator animator) {
            if (this.j) {
                return;
            }
            wn3.b(this.e, this.f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public final void onAnimationResume(Animator animator) {
            if (this.j) {
                return;
            }
            wn3.b(this.e, 0);
        }

        @Override // androidx.transition.Transition.d
        public final void c() {
        }

        @Override // androidx.transition.Transition.d
        public final void b(Transition transition) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }
}
