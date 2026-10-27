package androidx.transition;

import android.graphics.Rect;
import androidx.transition.Transition;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends Transition.c {
    public final /* synthetic */ Rect a;

    public c(Rect rect) {
        this.a = rect;
    }

    @Override // androidx.transition.Transition.c
    public final Rect a() {
        return this.a;
    }
}
