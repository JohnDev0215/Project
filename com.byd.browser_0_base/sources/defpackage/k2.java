package defpackage;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.IntProperty;
import androidx.appcompat.app.ActionBar;
import org.chromium.chrome.R$dimen;

/* JADX INFO: compiled from: ActionModeController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class k2 {
    public static final a f = new a();
    public ObjectAnimator a;
    public boolean b;
    public float c;
    public final Context d;
    public final e e;

    /* JADX INFO: compiled from: ActionModeController.java */
    public class a extends IntProperty<e> {
        public a() {
            super("controlTopMargin");
        }

        @Override // android.util.Property
        public final Integer get(Object obj) {
            return Integer.valueOf(((e) obj).a());
        }

        @Override // android.util.IntProperty
        public final void setValue(e eVar, int i) {
            eVar.b(i);
        }
    }

    /* JADX INFO: compiled from: ActionModeController.java */
    public class b extends AnimatorListenerAdapter {
        public b() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            k2.this.a = null;
        }
    }

    /* JADX INFO: compiled from: ActionModeController.java */
    public class c implements ValueAnimator.AnimatorUpdateListener {
        public c() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            k2 k2Var = k2.this;
            if (k2Var.e.d() != null) {
                valueAnimator.setIntValues((int) Math.max(0.0f, k2Var.a() - k2Var.c));
            }
        }
    }

    /* JADX INFO: compiled from: ActionModeController.java */
    public class d extends AnimatorListenerAdapter {
        public d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            k2 k2Var = k2.this;
            k2Var.a = null;
            k2Var.e.c(false);
        }
    }

    /* JADX INFO: compiled from: ActionModeController.java */
    public interface e {
        int a();

        void b(int i);

        void c(boolean z);

        ActionBar d();
    }

    public k2(Context context, qn3 qn3Var, rb3 rb3Var) {
        this.e = qn3Var;
        this.d = context;
        rb3Var.a = this;
        this.c = context.getResources().getDimension(R$dimen.tab_strip_height);
    }

    public final int a() {
        ActionBar actionBarD = this.e.d();
        if (actionBarD != null) {
            return actionBarD.k();
        }
        TypedArray typedArrayObtainStyledAttributes = this.d.obtainStyledAttributes(new int[]{R.attr.actionBarSize});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public final void b() {
        if (this.b) {
            ObjectAnimator objectAnimator = this.a;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            ObjectAnimator duration = ObjectAnimator.ofInt(this.e, f, 0).setDuration(200L);
            this.a = duration;
            duration.addListener(new d());
            this.a.start();
            this.b = false;
        }
    }

    public final void c() {
        ObjectAnimator objectAnimator = this.a;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        int[] iArr = {(int) Math.max(0.0f, a() - this.c)};
        e eVar = this.e;
        ObjectAnimator duration = ObjectAnimator.ofInt(eVar, f, iArr).setDuration(200L);
        this.a = duration;
        duration.addListener(new b());
        this.a.addUpdateListener(new c());
        eVar.c(true);
        this.a.start();
        this.b = true;
    }
}
