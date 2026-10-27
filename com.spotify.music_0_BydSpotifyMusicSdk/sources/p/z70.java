package p;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class z70 implements Animation.AnimationListener {
    public final /* synthetic */ bf2 a;
    public final /* synthetic */ androidx.fragment.app.a b;
    public final /* synthetic */ View c;
    public final /* synthetic */ w70 d;

    public z70(View view, w70 w70Var, androidx.fragment.app.a aVar, bf2 bf2Var) {
        this.a = bf2Var;
        this.b = aVar;
        this.c = view;
        this.d = w70Var;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        v03.j(animation, "animation");
        androidx.fragment.app.a aVar = this.b;
        aVar.a.post(new kc0(aVar, this.c, this.d, 3));
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(this.a);
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
        v03.j(animation, "animation");
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        v03.j(animation, "animation");
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(this.a);
        }
    }
}
