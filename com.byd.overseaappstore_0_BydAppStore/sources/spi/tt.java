package spi;

import androidx.constraintlayout.core.state.Interpolator;
import androidx.constraintlayout.core.state.Transition;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class tt implements Interpolator {
    public static final /* synthetic */ tt b = new tt(0);
    public static final /* synthetic */ tt c = new tt(1);
    public final /* synthetic */ int a;

    public /* synthetic */ tt(int i) {
        this.a = i;
    }

    @Override // androidx.constraintlayout.core.state.Interpolator
    public final float getInterpolation(float f) {
        switch (this.a) {
            case 0:
                return Transition.lambda$getInterpolator$3(f);
            default:
                return Transition.lambda$getInterpolator$6(f);
        }
    }
}
