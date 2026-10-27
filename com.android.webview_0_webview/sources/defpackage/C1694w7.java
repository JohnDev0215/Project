package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX INFO: renamed from: w7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1694w7 extends AnimatorListenerAdapter {
    public final /* synthetic */ InterfaceC1145m7 a;
    public final /* synthetic */ View b;

    public C1694w7(AbstractC1859z7 abstractC1859z7, InterfaceC1145m7 interfaceC1145m7, View view) {
        this.a = interfaceC1145m7;
        this.b = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.a.a(this.b);
    }
}
