package a.r;

import a.r.i;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class b0 extends i {
    public static final String[] J = {"android:visibility:visibility", "android:visibility:parent"};
    public int I = 3;

    public static class a extends AnimatorListenerAdapter implements i.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final View f1301a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f1302b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ViewGroup f1303c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final boolean f1304d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f1305e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f1306f = false;

        public a(View view, int i, boolean z) {
            this.f1301a = view;
            this.f1302b = i;
            this.f1303c = (ViewGroup) view.getParent();
            this.f1304d = z;
            g(true);
        }

        @Override // a.r.i.d
        public void a(i iVar) {
        }

        @Override // a.r.i.d
        public void b(i iVar) {
        }

        @Override // a.r.i.d
        public void c(i iVar) {
            f();
            iVar.w(this);
        }

        @Override // a.r.i.d
        public void d(i iVar) {
            g(false);
        }

        @Override // a.r.i.d
        public void e(i iVar) {
            g(true);
        }

        public final void f() {
            if (!this.f1306f) {
                t.g(this.f1301a, this.f1302b);
                ViewGroup viewGroup = this.f1303c;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            g(false);
        }

        public final void g(boolean z) {
            ViewGroup viewGroup;
            if (!this.f1304d || this.f1305e == z || (viewGroup = this.f1303c) == null) {
                return;
            }
            this.f1305e = z;
            viewGroup.suppressLayout(z);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1306f = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            f();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animator) {
            if (this.f1306f) {
                return;
            }
            t.g(this.f1301a, this.f1302b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animator) {
            if (this.f1306f) {
                return;
            }
            t.g(this.f1301a, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1307a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1308b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1309c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1310d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public ViewGroup f1311e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public ViewGroup f1312f;
    }

    public final void I(p pVar) {
        pVar.f1348a.put("android:visibility:visibility", Integer.valueOf(pVar.f1349b.getVisibility()));
        pVar.f1348a.put("android:visibility:parent", pVar.f1349b.getParent());
        int[] iArr = new int[2];
        pVar.f1349b.getLocationOnScreen(iArr);
        pVar.f1348a.put("android:visibility:screenLocation", iArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0079, code lost:
    
        if (r8 == 0) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0083, code lost:
    
        if (r6.f1311e == null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0095, code lost:
    
        if (r6.f1309c == 0) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final a.r.b0.b J(a.r.p r7, a.r.p r8) {
        /*
            r6 = this;
            a.r.b0$b r6 = new a.r.b0$b
            r6.<init>()
            r0 = 0
            r6.f1307a = r0
            r6.f1308b = r0
            java.lang.String r1 = "android:visibility:parent"
            r2 = 0
            r3 = -1
            java.lang.String r4 = "android:visibility:visibility"
            if (r7 == 0) goto L33
            java.util.Map<java.lang.String, java.lang.Object> r5 = r7.f1348a
            boolean r5 = r5.containsKey(r4)
            if (r5 == 0) goto L33
            java.util.Map<java.lang.String, java.lang.Object> r5 = r7.f1348a
            java.lang.Object r5 = r5.get(r4)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r6.f1309c = r5
            java.util.Map<java.lang.String, java.lang.Object> r5 = r7.f1348a
            java.lang.Object r5 = r5.get(r1)
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            r6.f1311e = r5
            goto L37
        L33:
            r6.f1309c = r3
            r6.f1311e = r2
        L37:
            if (r8 == 0) goto L5a
            java.util.Map<java.lang.String, java.lang.Object> r5 = r8.f1348a
            boolean r5 = r5.containsKey(r4)
            if (r5 == 0) goto L5a
            java.util.Map<java.lang.String, java.lang.Object> r2 = r8.f1348a
            java.lang.Object r2 = r2.get(r4)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r6.f1310d = r2
            java.util.Map<java.lang.String, java.lang.Object> r2 = r8.f1348a
            java.lang.Object r1 = r2.get(r1)
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            r6.f1312f = r1
            goto L5e
        L5a:
            r6.f1310d = r3
            r6.f1312f = r2
        L5e:
            r1 = 1
            if (r7 == 0) goto L86
            if (r8 == 0) goto L86
            int r7 = r6.f1309c
            int r8 = r6.f1310d
            if (r7 != r8) goto L70
            android.view.ViewGroup r7 = r6.f1311e
            android.view.ViewGroup r8 = r6.f1312f
            if (r7 != r8) goto L70
            return r6
        L70:
            int r7 = r6.f1309c
            int r8 = r6.f1310d
            if (r7 == r8) goto L7c
            if (r7 != 0) goto L79
            goto L97
        L79:
            if (r8 != 0) goto L9a
            goto L8c
        L7c:
            android.view.ViewGroup r7 = r6.f1312f
            if (r7 != 0) goto L81
            goto L97
        L81:
            android.view.ViewGroup r7 = r6.f1311e
            if (r7 != 0) goto L9a
            goto L8c
        L86:
            if (r7 != 0) goto L91
            int r7 = r6.f1310d
            if (r7 != 0) goto L91
        L8c:
            r6.f1308b = r1
        L8e:
            r6.f1307a = r1
            goto L9a
        L91:
            if (r8 != 0) goto L9a
            int r7 = r6.f1309c
            if (r7 != 0) goto L9a
        L97:
            r6.f1308b = r0
            goto L8e
        L9a:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: a.r.b0.J(a.r.p, a.r.p):a.r.b0$b");
    }

    public abstract Animator K(ViewGroup viewGroup, View view, p pVar, p pVar2);

    @Override // a.r.i
    public void e(p pVar) {
        I(pVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
    
        if (r0.J(r0.o(r1, false), r0.r(r1, false)).f1307a != false) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01fa  */
    @Override // a.r.i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.animation.Animator l(android.view.ViewGroup r21, a.r.p r22, a.r.p r23) {
        /*
            Method dump skipped, instruction units count: 644
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.r.b0.l(android.view.ViewGroup, a.r.p, a.r.p):android.animation.Animator");
    }

    @Override // a.r.i
    public String[] q() {
        return J;
    }

    @Override // a.r.i
    public boolean s(p pVar, p pVar2) {
        if (pVar == null && pVar2 == null) {
            return false;
        }
        if (pVar != null && pVar2 != null && pVar2.f1348a.containsKey("android:visibility:visibility") != pVar.f1348a.containsKey("android:visibility:visibility")) {
            return false;
        }
        b bVarJ = J(pVar, pVar2);
        if (bVarJ.f1307a) {
            return bVarJ.f1309c == 0 || bVarJ.f1310d == 0;
        }
        return false;
    }
}
