package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: LGEmailActionModeWorkaroundImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class w41 extends AnimatorListenerAdapter {
    public final /* synthetic */ PopupWindow e;
    public final /* synthetic */ ViewGroup f;

    /* JADX INFO: compiled from: LGEmailActionModeWorkaroundImpl.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            w41 w41Var = w41.this;
            w41Var.e.dismiss();
            w41Var.f.removeAllViews();
        }
    }

    public w41(PopupWindow popupWindow, ViewGroup viewGroup) {
        this.e = popupWindow;
        this.f = viewGroup;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        PostTask.c(rh3.a, new a());
    }
}
