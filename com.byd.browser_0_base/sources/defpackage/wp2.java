package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.LinkedList;
import org.chromium.base.ApplicationStatus;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SnackbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wp2 implements View.OnClickListener, ApplicationStatus.c, zi3 {
    public final Activity e;
    public yp2 f;
    public final Handler g;
    public boolean i;
    public final ViewGroup j;
    public ViewGroup k;
    public final WindowAndroid l;
    public final aq1<Boolean> n;
    public final up2 h = new up2();
    public final a m = new a();

    /* JADX INFO: compiled from: SnackbarManager.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            wp2 wp2Var = wp2.this;
            up2 up2Var = wp2Var.h;
            if (!(up2Var.a().l == 2)) {
                up2Var.b(false);
                while (true) {
                    tp2 tp2VarA = up2Var.a();
                    if (tp2VarA != null) {
                        if (!(tp2VarA.l == 0)) {
                            break;
                        } else {
                            up2Var.b(false);
                        }
                    } else {
                        break;
                    }
                }
            }
            wp2Var.e();
        }
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public interface c {
        wp2 q0();
    }

    public wp2(Activity activity, ViewGroup viewGroup, g3 g3Var) {
        aq1<Boolean> aq1Var = new aq1<>();
        this.n = aq1Var;
        this.e = activity;
        this.g = new Handler();
        this.j = viewGroup;
        this.l = g3Var;
        ApplicationStatus.f(this, activity);
        if (ApplicationStatus.c(activity) == 2 || ApplicationStatus.c(activity) == 3) {
            this.i = true;
        }
        yp2 yp2Var = this.f;
        aq1Var.r(Boolean.valueOf(yp2Var != null && yp2Var.b.isShown()));
    }

    public final void a(b bVar) {
        up2 up2Var = this.h;
        if (up2.c(up2Var.a, bVar) || up2.c(up2Var.b, bVar)) {
            e();
        }
    }

    public final void b(b bVar, Object obj) {
        up2 up2Var = this.h;
        if (up2.d(up2Var.a, bVar, obj) || up2.d(up2Var.b, bVar, obj)) {
            e();
        }
    }

    public final void c(tp2 tp2Var) {
        if (this.i) {
            oj2.a0(tp2Var.m, "Snackbar.Shown");
            up2 up2Var = this.h;
            up2Var.getClass();
            int i = tp2Var.l;
            boolean z = i == 0;
            LinkedList linkedList = up2Var.a;
            if (z) {
                if (up2Var.a() != null) {
                    if (!(up2Var.a().l == 0)) {
                        up2Var.b(false);
                    }
                }
                linkedList.addFirst(tp2Var);
            } else {
                if (i == 2) {
                    up2Var.b.addFirst(tp2Var);
                } else {
                    linkedList.addLast(tp2Var);
                }
            }
            e();
            this.f.a();
        }
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 2) {
            this.i = true;
            return;
        }
        if (i != 5) {
            return;
        }
        while (true) {
            up2 up2Var = this.h;
            if (up2Var.a.isEmpty() && up2Var.b.isEmpty()) {
                e();
                this.i = false;
                return;
            }
            up2Var.b(false);
        }
    }

    public final void e() {
        boolean zD;
        if (this.i) {
            tp2 tp2VarA = this.h.a();
            Handler handler = this.g;
            boolean z = false;
            a aVar = this.m;
            if (tp2VarA == null) {
                handler.removeCallbacks(aVar);
                yp2 yp2Var = this.f;
                if (yp2Var != null) {
                    yp2Var.e.setOnClickListener(null);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(yp2Var.g);
                    animatorSet.addListener(new aq2(yp2Var));
                    Property property = View.TRANSLATION_Y;
                    ViewGroup viewGroup = yp2Var.b;
                    ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroup, (Property<ViewGroup, Float>) property, viewGroup.getHeight() + ((FrameLayout.LayoutParams) viewGroup.getLayoutParams()).bottomMargin);
                    objectAnimatorOfFloat.setInterpolator(h21.a);
                    ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(viewGroup, (Property<ViewGroup, Float>) View.ALPHA, 0.0f);
                    objectAnimatorOfFloat2.setInterpolator(pc.d);
                    animatorSet.playTogether(objectAnimatorOfFloat2, objectAnimatorOfFloat);
                    WindowAndroid windowAndroid = yp2Var.a;
                    if (windowAndroid != null) {
                        windowAndroid.v(animatorSet);
                    } else {
                        animatorSet.start();
                    }
                    this.f = null;
                }
            } else {
                yp2 yp2Var2 = this.f;
                if (yp2Var2 == null) {
                    yp2 yp2Var3 = new yp2(this.e, this, tp2VarA, this.j, this.l);
                    this.f = yp2Var3;
                    ViewGroup viewGroup2 = yp2Var3.j;
                    ViewGroup viewGroup3 = yp2Var3.b;
                    viewGroup2.addView(viewGroup3);
                    yp2Var3.l.addOnLayoutChangeListener(yp2Var3.p);
                    viewGroup3.addOnLayoutChangeListener(new zp2(yp2Var3));
                    ViewGroup viewGroup4 = this.k;
                    if (viewGroup4 != null) {
                        this.f.b(viewGroup4);
                    }
                    zD = true;
                } else {
                    zD = yp2Var2.d(tp2VarA, true);
                }
                if (zD) {
                    handler.removeCallbacks(aVar);
                    if (!(tp2VarA.l == 2)) {
                        int recommendedTimeoutMillis = tp2VarA.j;
                        if (recommendedTimeoutMillis == 0) {
                            recommendedTimeoutMillis = 3000;
                        }
                        if (qo.f().c()) {
                            qo.f().getClass();
                            recommendedTimeoutMillis = C0188h0.b().getRecommendedTimeoutMillis(recommendedTimeoutMillis, 7);
                        }
                        handler.postDelayed(aVar, recommendedTimeoutMillis);
                    }
                    this.f.a();
                }
            }
            aq1<Boolean> aq1Var = this.n;
            yp2 yp2Var4 = this.f;
            if (yp2Var4 != null && yp2Var4.b.isShown()) {
                z = true;
            }
            aq1Var.r(Boolean.valueOf(z));
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        yp2 yp2Var = this.f;
        if (!TextUtils.isEmpty(yp2Var.k.f)) {
            yp2Var.b.announceForAccessibility(yp2Var.k.f);
        }
        this.h.b(true);
        e();
    }

    /* JADX INFO: compiled from: SnackbarManager.java */
    public interface b {
        default void d(Object obj) {
        }

        default void j(Object obj) {
        }
    }
}
