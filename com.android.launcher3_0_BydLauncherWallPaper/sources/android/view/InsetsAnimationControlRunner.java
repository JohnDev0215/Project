package android.view;

/* JADX INFO: loaded from: classes3.dex */
public interface InsetsAnimationControlRunner {
    void cancel();

    WindowInsetsAnimation getAnimation();

    int getAnimationType();

    int getTypes();

    default boolean controlsInternalType(int i) {
        return InsetsState.toInternalType(getTypes()).contains(Integer.valueOf(i));
    }
}
