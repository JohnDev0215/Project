package b.b.g.q.d;

import android.animation.Animator;
import android.view.View;
import com.airbnb.lottie.LottieAnimationView;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: LottieViewAnimation.java */
/* JADX INFO: loaded from: classes2.dex */
public class n implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public LottieAnimationView f1484c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1482a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1483b = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ArrayList<View> f1485d = new ArrayList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<View> f1486e = new ArrayList<>();

    public void a() {
        ArrayList<View> arrayList = this.f1485d;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.f1484c = null;
    }

    public void b(View... viewArr) {
        if (viewArr != null) {
            ArrayList<View> arrayList = this.f1486e;
            if (arrayList != null) {
                arrayList.clear();
            }
            for (View view : viewArr) {
                if (view != null && !this.f1486e.contains(view)) {
                    this.f1486e.add(view);
                }
            }
        }
    }

    public void c(LottieAnimationView lottieAnimationView) {
        this.f1484c = lottieAnimationView;
    }

    public void d(boolean z, boolean z2) {
        this.f1482a = z;
        this.f1483b = z2;
    }

    public void e(View... viewArr) {
        if (viewArr != null) {
            ArrayList<View> arrayList = this.f1485d;
            if (arrayList != null) {
                arrayList.clear();
            }
            for (View view : viewArr) {
                if (view != null && !this.f1485d.contains(view)) {
                    this.f1485d.add(view);
                }
            }
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator, boolean z) {
        Iterator<View> it = this.f1485d.iterator();
        while (it.hasNext()) {
            it.next().setVisibility(this.f1483b ? 0 : 8);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator, boolean z) {
        Iterator<View> it = this.f1486e.iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            View next = it.next();
            if (!this.f1483b) {
                i = 8;
            }
            next.setVisibility(i);
        }
        Iterator<View> it2 = this.f1485d.iterator();
        while (it2.hasNext()) {
            it2.next().setVisibility(this.f1482a ? 0 : 8);
        }
    }
}
