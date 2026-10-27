package defpackage;

import android.animation.Animator;
import android.view.View;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* JADX INFO: compiled from: PickerVideoPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i12 implements Animator.AnimatorListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ PickerVideoPlayer f;

    public i12(PickerVideoPlayer pickerVideoPlayer, int i) {
        this.f = pickerVideoPlayer;
        this.e = i;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.f;
        View view = pickerVideoPlayer.m;
        pickerVideoPlayer.getClass();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        boolean z = PickerVideoPlayer.E;
        PickerVideoPlayer pickerVideoPlayer = this.f;
        pickerVideoPlayer.p.setClickable(true);
        pickerVideoPlayer.r.setClickable(true);
        pickerVideoPlayer.c(this.e);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.f;
        View view = pickerVideoPlayer.m;
        pickerVideoPlayer.getClass();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }
}
