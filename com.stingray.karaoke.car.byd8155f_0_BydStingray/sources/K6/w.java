package k6;

import com.stingray.karaoke.car.utils.ToastType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f18302a;

    static {
        int[] iArr = new int[ToastType.values().length];
        try {
            iArr[ToastType.ADD_TO_QUEUE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[ToastType.ADD_TO_FAVORITE.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[ToastType.REMOVE_FROM_FAVORITE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[ToastType.DRIVING_DISABLED_WARNING.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[ToastType.DRIVING_DISABLED_ERROR.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[ToastType.DRIVING_DISABLED_WITHOUT_PASSENGER.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr[ToastType.SCORING_ON_HOLD.ordinal()] = 7;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr[ToastType.DEFAULT.ordinal()] = 8;
        } catch (NoSuchFieldError unused8) {
        }
        f18302a = iArr;
    }
}
