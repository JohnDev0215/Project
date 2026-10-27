package defpackage;

import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import defpackage.ow1;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: PartialCustomTabVersionCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class pw1 extends WindowInsetsAnimation.Callback {
    public final /* synthetic */ Callback a;
    public final /* synthetic */ ow1.b b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pw1(ow1.b bVar, ld ldVar) {
        super(0);
        this.b = bVar;
        this.a = ldVar;
    }

    @Override // android.view.WindowInsetsAnimation.Callback
    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
        this.a.onResult(Boolean.valueOf(this.b.a.getWindowManager().getCurrentWindowMetrics().getWindowInsets().isVisible(WindowInsets.Type.ime())));
    }

    @Override // android.view.WindowInsetsAnimation.Callback
    public final WindowInsets onProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
        return windowInsets;
    }
}
