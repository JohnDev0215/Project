package org.chromium.components.infobars;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.components.infobars.InfoBarContainerLayout;

/* JADX INFO: compiled from: InfoBarContainerLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a extends AnimatorListenerAdapter {
    public final /* synthetic */ InfoBarContainerLayout.g e;

    public a(InfoBarContainerLayout.g gVar) {
        this.e = gVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        InfoBarContainerLayout.g gVar = this.e;
        gVar.d();
        InfoBarContainerLayout infoBarContainerLayout = InfoBarContainerLayout.this;
        infoBarContainerLayout.j = null;
        infoBarContainerLayout.i.a(gVar.c());
        InfoBarContainerLayout.this.l();
    }
}
