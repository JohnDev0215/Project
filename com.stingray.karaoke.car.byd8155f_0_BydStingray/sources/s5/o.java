package S5;

import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f8021a;

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
        f8021a = iArr;
    }
}
