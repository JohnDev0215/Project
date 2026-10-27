package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RC extends AnimatorListenerAdapter {
    public final /* synthetic */ PopupWindow a;
    public final /* synthetic */ ViewGroup b;

    public RC(PopupWindow popupWindow, ViewGroup viewGroup) {
        this.a = popupWindow;
        this.b = viewGroup;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        PostTask.a(AbstractC0538bE.a, new QC(this), 0L);
    }
}
