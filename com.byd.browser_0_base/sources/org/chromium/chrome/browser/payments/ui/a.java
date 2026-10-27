package org.chromium.chrome.browser.payments.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.ColorDrawable;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import defpackage.h21;
import defpackage.hk;
import defpackage.o4;
import defpackage.tf0;
import defpackage.x5;
import defpackage.yq;
import defpackage.z7;
import java.util.ArrayList;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.app.ChromeActivity;

/* JADX INFO: compiled from: DimmingDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public final o4 a;
    public final FrameLayout b;
    public final int c;
    public c d;
    public boolean e;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.payments.ui.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DimmingDialog.java */
    public class C0106a extends AnimatorListenerAdapter {
        public final boolean e = true;

        public C0106a() {
            ArrayList arrayList = new ArrayList();
            FrameLayout frameLayout = a.this.b;
            View childAt = frameLayout.getChildAt(0);
            if (childAt != null) {
                arrayList.add(ObjectAnimator.ofFloat(childAt, (Property<View, Float>) View.ALPHA, childAt.getAlpha(), 0.0f));
                arrayList.add(ObjectAnimator.ofFloat(childAt, (Property<View, Float>) View.TRANSLATION_Y, 0.0f, a.this.c));
            }
            arrayList.add(ObjectAnimator.ofInt(frameLayout.getBackground(), x5.a, 127, 0));
            if (arrayList.isEmpty()) {
                return;
            }
            a.this.e = true;
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(195L);
            animatorSet.setInterpolator(h21.d);
            animatorSet.playTogether(arrayList);
            animatorSet.addListener(this);
            animatorSet.start();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            a aVar = a.this;
            aVar.e = false;
            FrameLayout frameLayout = aVar.b;
            frameLayout.removeView(frameLayout.getChildAt(0));
            if (this.e) {
                o4 o4Var = aVar.a;
                if (o4Var.isShowing()) {
                    o4Var.dismiss();
                }
                aVar.a();
            }
        }
    }

    /* JADX INFO: compiled from: DimmingDialog.java */
    public class b extends AnimatorListenerAdapter implements View.OnLayoutChangeListener {
        public b() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            a aVar = a.this;
            aVar.b.getChildAt(0).removeOnLayoutChangeListener(this);
            FrameLayout frameLayout = aVar.b;
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(frameLayout.getBackground(), x5.a, 0, 255);
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(frameLayout, (Property<FrameLayout, Float>) View.ALPHA, 0.0f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorOfInt, objectAnimatorOfFloat);
            animatorSet.setDuration(225L);
            animatorSet.setInterpolator(h21.g);
            animatorSet.start();
        }
    }

    /* JADX INFO: compiled from: DimmingDialog.java */
    public interface c {
    }

    public a(ChromeActivity chromeActivity, c cVar) {
        this.d = cVar;
        FrameLayout frameLayout = new FrameLayout(chromeActivity);
        this.b = frameLayout;
        frameLayout.setBackgroundColor(chromeActivity.getResources().getColor(R$color.modal_dialog_scrim_color));
        o4 o4Var = new o4(chromeActivity, R$style.DimmingDialog);
        this.a = o4Var;
        o4Var.setOnDismissListener(new hk(1, this));
        o4Var.addContentView(frameLayout, new ViewGroup.LayoutParams(-1, -1));
        Window window = o4Var.getWindow();
        window.setGravity(17);
        window.setLayout(-1, -1);
        window.setBackgroundDrawable(new ColorDrawable(0));
        View rootView = window.getDecorView().getRootView();
        boolean z = !z7.h0(window.getStatusBarColor());
        int systemUiVisibility = rootView.getSystemUiVisibility();
        rootView.setSystemUiVisibility(z ? systemUiVisibility | 8192 : systemUiVisibility & (-8193));
        this.c = chromeActivity.getResources().getDimensionPixelSize(R$dimen.payments_ui_translation);
    }

    public final void a() {
        c cVar = this.d;
        if (cVar == null) {
            return;
        }
        e eVar = (e) cVar;
        eVar.L = true;
        tf0 tf0Var = eVar.l;
        if (tf0Var.isShowing()) {
            tf0Var.dismiss();
        }
        if (!eVar.H) {
            yq yqVar = (yq) ((j) eVar.g).o;
            yqVar.e.a(0);
            yqVar.b("User closed the Payment Request UI.");
        }
        this.d = null;
    }
}
