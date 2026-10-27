package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: compiled from: CustomTabToolbarAnimationDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class q40 implements View.OnLayoutChangeListener {
    public final /* synthetic */ float e;
    public final /* synthetic */ int[] f;
    public final /* synthetic */ r40 g;

    /* JADX INFO: compiled from: CustomTabToolbarAnimationDelegate.java */
    public class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: q40$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: CustomTabToolbarAnimationDelegate.java */
        public class C0164a extends AnimatorListenerAdapter {
            public C0164a() {
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                q40.this.g.h = false;
            }
        }

        public a() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            q40.this.g.d.animate().alpha(1.0f).setInterpolator(pc.e).setDuration(150L).setListener(new C0164a()).start();
        }
    }

    public q40(r40 r40Var, float f, int[] iArr) {
        this.g = r40Var;
        this.e = f;
        this.f = iArr;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        r40 r40Var = this.g;
        r40Var.c.removeOnLayoutChangeListener(this);
        r40Var.c.getLocationInWindow(new int[2]);
        TextView textView = r40Var.c;
        float f = this.e;
        textView.setScaleX(f);
        r40Var.c.setScaleY(f);
        TextView textView2 = r40Var.c;
        int[] iArr = this.f;
        textView2.setTranslationX(iArr[0] - r2[0]);
        r40Var.c.setTranslationY(iArr[1] - r2[1]);
        r40Var.h = true;
        r40Var.c.animate().scaleX(1.0f).scaleY(1.0f).translationX(0.0f).translationY(0.0f).setDuration(200L).setInterpolator(pc.c).setListener(new a()).start();
    }
}
