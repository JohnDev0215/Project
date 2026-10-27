package defpackage;

import android.view.animation.Animation;
import org.chromium.components.browser_ui.widget.image_tiles.b;

/* JADX INFO: compiled from: TileListView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ga3 implements Animation.AnimationListener {
    public final /* synthetic */ b a;

    public ga3(b bVar) {
        this.a = bVar;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        this.a.b.setLayoutAnimation(null);
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
    }
}
