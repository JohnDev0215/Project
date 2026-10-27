package defpackage;

import android.graphics.Rect;
import android.transition.Transition;

/* JADX INFO: compiled from: FragmentTransitionCompat21.java */
/* JADX INFO: loaded from: classes.dex */
public final class wp0 extends Transition.EpicenterCallback {
    public final /* synthetic */ Rect a;

    public wp0(Rect rect) {
        this.a = rect;
    }

    @Override // android.transition.Transition.EpicenterCallback
    public final Rect onGetEpicenter(Transition transition) {
        return this.a;
    }
}
