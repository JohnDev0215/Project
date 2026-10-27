package a.l.d;

import android.animation.Animator;
import android.view.animation.Animation;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Animation f995a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Animator f996b;

    public i(Animator animator) {
        this.f995a = null;
        this.f996b = animator;
    }

    public i(Animation animation) {
        this.f995a = animation;
        this.f996b = null;
        if (animation == null) {
            throw new IllegalStateException("Animation cannot be null");
        }
    }
}
