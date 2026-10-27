package defpackage;

import android.animation.Animator;
import android.widget.ImageView;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;

/* JADX INFO: compiled from: PickerVideoPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j12 implements Animator.AnimatorListener {
    public final /* synthetic */ PickerVideoPlayer e;

    public j12(PickerVideoPlayer pickerVideoPlayer) {
        this.e = pickerVideoPlayer;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.e;
        ImageView imageView = pickerVideoPlayer.o;
        pickerVideoPlayer.getClass();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.e;
        pickerVideoPlayer.o.setClickable(true);
        ImageView imageView = pickerVideoPlayer.o;
        pickerVideoPlayer.getClass();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        PickerVideoPlayer pickerVideoPlayer = this.e;
        ImageView imageView = pickerVideoPlayer.o;
        pickerVideoPlayer.getClass();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }
}
