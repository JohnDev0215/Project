package m3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class w extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19153a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ s f19154b;
    public final /* synthetic */ x c;

    public /* synthetic */ w(x xVar, s sVar, int i7) {
        this.f19153a = i7;
        this.c = xVar;
        this.f19154b = sVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f19153a) {
            case 0:
                x xVar = this.c;
                xVar.i(1);
                if (xVar.B) {
                    this.f19154b.post(xVar.f19171s);
                    xVar.B = false;
                }
                break;
            case 1:
                x xVar2 = this.c;
                xVar2.i(2);
                if (xVar2.B) {
                    this.f19154b.post(xVar2.f19171s);
                    xVar2.B = false;
                }
                break;
            default:
                x xVar3 = this.c;
                xVar3.i(2);
                if (xVar3.B) {
                    this.f19154b.post(xVar3.f19171s);
                    xVar3.B = false;
                }
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f19153a) {
            case 0:
                this.c.i(3);
                break;
            case 1:
                this.c.i(3);
                break;
            default:
                this.c.i(3);
                break;
        }
    }
}
