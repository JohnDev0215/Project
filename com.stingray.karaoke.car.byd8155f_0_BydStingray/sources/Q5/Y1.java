package Q5;

import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.kca.model.KaraokeItemType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class Y1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f6660b;
    public static final /* synthetic */ int[] c;

    static {
        int[] iArr = new int[LayoutMode.values().length];
        try {
            iArr[LayoutMode.WIDE_ULTRA.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[LayoutMode.NARROW_COMPACT.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[LayoutMode.SQUARE_COMPACT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        f6659a = iArr;
        int[] iArr2 = new int[KaraokeItemType.values().length];
        try {
            iArr2[KaraokeItemType.PLAY.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[KaraokeItemType.GROUP.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
        f6660b = iArr2;
        int[] iArr3 = new int[LoadingInterface$LoadingState.values().length];
        try {
            iArr3[LoadingInterface$LoadingState.Loading.ordinal()] = 1;
        } catch (NoSuchFieldError unused6) {
        }
        try {
            iArr3[LoadingInterface$LoadingState.Error.ordinal()] = 2;
        } catch (NoSuchFieldError unused7) {
        }
        try {
            iArr3[LoadingInterface$LoadingState.Done.ordinal()] = 3;
        } catch (NoSuchFieldError unused8) {
        }
        c = iArr3;
    }
}
