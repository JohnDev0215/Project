package Q5;

import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;

/* JADX INFO: renamed from: Q5.g1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC0571g1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6815a;

    static {
        int[] iArr = new int[LoadingInterface$LoadingState.values().length];
        try {
            iArr[LoadingInterface$LoadingState.Loading.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[LoadingInterface$LoadingState.Error.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f6815a = iArr;
    }
}
