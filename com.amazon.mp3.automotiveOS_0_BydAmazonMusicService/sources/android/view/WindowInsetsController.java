package android.view;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.os.CancellationSignal;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ interface WindowInsetsController {

    public /* synthetic */ interface OnControllableInsetsChangedListener {
        static {
            throw new NoClassDefFoundError();
        }
    }

    static {
        throw new NoClassDefFoundError();
    }

    /* synthetic */ void addOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener);

    /* synthetic */ void controlWindowInsetsAnimation(int i, long j, @Nullable Interpolator interpolator, @Nullable CancellationSignal cancellationSignal, @NonNull WindowInsetsAnimationControlListener windowInsetsAnimationControlListener);

    /* synthetic */ int getSystemBarsAppearance();

    /* synthetic */ int getSystemBarsBehavior();

    /* synthetic */ void hide(int i);

    /* synthetic */ void removeOnControllableInsetsChangedListener(@NonNull OnControllableInsetsChangedListener onControllableInsetsChangedListener);

    /* synthetic */ void setSystemBarsAppearance(int i, int i2);

    /* synthetic */ void setSystemBarsBehavior(int i);

    /* synthetic */ void show(int i);
}
