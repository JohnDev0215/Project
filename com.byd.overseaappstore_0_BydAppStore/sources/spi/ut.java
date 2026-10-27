package spi;

import androidx.constraintlayout.core.state.Interpolator;
import androidx.constraintlayout.core.state.Transition;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ut implements Interpolator {
    public static final /* synthetic */ ut b = new ut(0);
    public static final /* synthetic */ ut c = new ut(1);
    public static final /* synthetic */ ut d = new ut(2);
    public final /* synthetic */ int a;

    public /* synthetic */ ut(int i) {
        this.a = i;
    }

    @Override // androidx.constraintlayout.core.state.Interpolator
    public final float getInterpolation(float f) {
        switch (this.a) {
            case 0:
                return Transition.lambda$getInterpolator$1(f);
            case 1:
                return Transition.lambda$getInterpolator$4(f);
            default:
                return Transition.lambda$getInterpolator$7(f);
        }
    }
}
