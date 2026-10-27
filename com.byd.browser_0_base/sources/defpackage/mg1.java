package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import defpackage.dx2;
import org.chromium.components.messages.R$dimen;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.i;

/* JADX INFO: compiled from: MessageBannerMediator.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mg1 implements dx2.a {
    public static final OvershootInterpolator s = h21.h;
    public static final LinearInterpolator t = h21.f;
    public static final ik0 u = h21.d;
    public final PropertyModel e;
    public final nw2<Integer> f;
    public final float g;
    public final float h;
    public final p11 i;
    public final Runnable j;
    public final cx2 k;
    public final int l;
    public final int m;
    public Animator n;
    public int o = 0;
    public int p;
    public float q;
    public boolean r;

    /* JADX INFO: compiled from: MessageBannerMediator.java */
    public class a extends vl {
        public final /* synthetic */ boolean f;
        public final /* synthetic */ int g;
        public final /* synthetic */ Runnable h;

        public a(boolean z, int i, Runnable runnable) {
            this.f = z;
            this.g = i;
            this.h = runnable;
        }

        @Override // defpackage.vl
        public final void b() {
            mg1 mg1Var = mg1.this;
            boolean z = this.f;
            if (z) {
                mg1Var.e.n(ng1.B, this.g);
                mg1Var.e.m(ng1.A, 0.0f);
            }
            mg1Var.o = z ? 2 : 0;
            this.h.run();
            mg1Var.n = null;
        }

        @Override // defpackage.vl
        public final void c() {
            mg1.this.o = 1;
        }
    }

    public mg1(PropertyModel propertyModel, nw2 nw2Var, Resources resources, es3 es3Var, cx2 cx2Var) {
        this.e = propertyModel;
        this.f = nw2Var;
        this.g = resources.getDimensionPixelSize(R$dimen.message_vertical_hide_threshold);
        this.h = resources.getDimensionPixelSize(R$dimen.message_horizontal_hide_threshold);
        this.i = new p11(18, resources);
        this.j = es3Var;
        this.k = cx2Var;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.message_shadow_top_margin);
        this.m = dimensionPixelSize;
        this.l = resources.getDimensionPixelSize(R$dimen.message_peeking_layer_height) + dimensionPixelSize;
    }

    public static boolean a(int i) {
        return i == 3 || i == 4;
    }

    public final AnimatorSet b(boolean z, boolean z2, float f, boolean z3, int i, Runnable runnable) {
        long j = z2 ? 600L : 300L;
        float f2 = z2 ? 1.0f : 0.0f;
        PropertyModel.o oVar = ng1.C;
        PropertyModel propertyModel = this.e;
        ObjectAnimator objectAnimatorA = i.a(propertyModel, oVar, f2);
        TimeInterpolator timeInterpolator = u;
        objectAnimatorA.setInterpolator(z2 ? t : timeInterpolator);
        objectAnimatorA.setDuration(j);
        ObjectAnimator objectAnimatorA2 = i.a(propertyModel, z ? ng1.A : ng1.z, (f + i) - this.m);
        if (z2) {
            timeInterpolator = s;
        }
        objectAnimatorA2.setInterpolator(timeInterpolator);
        objectAnimatorA2.setDuration(j);
        if (!z3) {
            (z2 ? objectAnimatorA2 : objectAnimatorA).setStartDelay(100L);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorA, objectAnimatorA2);
        animatorSet.addListener(new a(z2, i, runnable));
        this.n = animatorSet;
        return animatorSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0078  */
    @Override // dx2.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(android.view.MotionEvent r9, float r10, float r11, float r12, float r13) {
        /*
            r8 = this;
            r9 = 1
            r8.r = r9
            int r10 = r8.p
            boolean r10 = a(r10)
            if (r10 == 0) goto Lc
            r12 = r13
        Lc:
            org.chromium.ui.modelutil.PropertyModel r11 = r8.e
            r13 = 0
            r0 = 0
            if (r10 == 0) goto L2d
            org.chromium.ui.modelutil.PropertyModel$o r10 = defpackage.ng1.A
            float r10 = r11.g(r10)
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 >= 0) goto L2b
            nw2<java.lang.Integer> r10 = r8.f
            java.lang.Object r10 = r10.get()
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            int r10 = -r10
            float r10 = (float) r10
            goto L57
        L2b:
            r10 = r0
            goto L57
        L2d:
            org.chromium.ui.modelutil.PropertyModel$o r10 = defpackage.ng1.z
            float r10 = r11.g(r10)
            float r11 = java.lang.Math.abs(r10)
            float r1 = r8.h
            int r11 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r11 >= 0) goto L3f
            r4 = r0
            goto L58
        L3f:
            p11 r11 = r8.i
            java.lang.Object r11 = r11.get()
            java.lang.Float r11 = (java.lang.Float) r11
            float r11 = r11.floatValue()
            int r10 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r10 >= 0) goto L51
            r10 = r9
            goto L52
        L51:
            r10 = r13
        L52:
            if (r10 == 0) goto L56
            float r10 = -r11
            goto L57
        L56:
            r10 = r11
        L57:
            r4 = r10
        L58:
            int r10 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r10 != 0) goto L5e
            r3 = r9
            goto L5f
        L5e:
            r3 = r13
        L5f:
            int r10 = r8.p
            boolean r2 = a(r10)
            int r10 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r10 == 0) goto L6b
            r5 = r9
            goto L6c
        L6b:
            r5 = r13
        L6c:
            int r6 = r8.m
            if (r3 == 0) goto L78
            wz r9 = new wz
            r10 = 25
            r9.<init>(r10)
            goto L7a
        L78:
            java.lang.Runnable r9 = r8.j
        L7a:
            r7 = r9
            r1 = r8
            android.animation.AnimatorSet r9 = r1.b(r2, r3, r4, r5, r6, r7)
            cx2 r8 = r8.k
            hg1 r8 = (defpackage.hg1) r8
            r8.a(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mg1.f(android.view.MotionEvent, float, float, float, float):void");
    }

    @Override // dx2.a
    public final void h(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        float fAbs;
        float fFloatValue;
        boolean zA = a(this.p);
        nw2<Integer> nw2Var = this.f;
        p11 p11Var = this.i;
        PropertyModel propertyModel = this.e;
        if (zA) {
            propertyModel.m(ng1.A, a8.o(this.q + f2, -nw2Var.get().intValue(), 0.0f));
        } else {
            propertyModel.m(ng1.z, a8.o(this.q + f, -((Float) p11Var.get()).floatValue(), ((Float) p11Var.get()).floatValue()));
        }
        PropertyModel.o oVar = ng1.C;
        if (a(this.p)) {
            fAbs = Math.abs(propertyModel.g(ng1.A));
            fFloatValue = nw2Var.get().intValue();
        } else {
            fAbs = Math.abs(propertyModel.g(ng1.z));
            fFloatValue = ((Float) p11Var.get()).floatValue();
        }
        propertyModel.m(oVar, 1.0f - (fAbs / fFloatValue));
    }

    @Override // dx2.a
    public final void k() {
        float fFloatValue;
        float f;
        if (this.r) {
            return;
        }
        Animator animator = this.n;
        if (animator != null) {
            animator.cancel();
        }
        this.n = null;
        PropertyModel.o oVar = ng1.A;
        PropertyModel propertyModel = this.e;
        boolean z = propertyModel.g(oVar) == 0.0f && propertyModel.g(ng1.z) == 0.0f;
        cx2 cx2Var = this.k;
        if (z) {
            this.o = 2;
            ((hg1) cx2Var).a(null);
            return;
        }
        boolean zA = a(this.p);
        if (zA) {
            f = propertyModel.g(oVar) > (-this.g) ? 0.0f : -this.f.get().intValue();
        } else {
            float fG = propertyModel.g(ng1.z);
            if (Math.abs(fG) < this.h) {
                fFloatValue = 0.0f;
            } else {
                fFloatValue = ((Float) this.i.get()).floatValue();
                if (fG < 0.0f) {
                    fFloatValue = -fFloatValue;
                }
            }
            f = fFloatValue;
        }
        boolean z2 = f == 0.0f;
        ((hg1) cx2Var).a(b(zA, z2, f, false, this.m, z2 ? new qe3(22) : this.j));
    }

    @Override // dx2.a
    public final boolean q(int i) {
        return i != 0 && this.o == 2 && (((hg1) this.k).e.isStarted() ^ true);
    }

    @Override // dx2.a
    public final void v(int i) {
        this.o = 3;
        this.p = i;
        this.q = this.e.g(a(i) ? ng1.A : ng1.z);
        this.r = false;
        ((jq) ((hg1) this.k).d).b();
    }
}
