package Q5;

import com.stingray.kca.model.KaraokeItemType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class h3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f6858a;

    static {
        int[] iArr = new int[KaraokeItemType.values().length];
        try {
            iArr[KaraokeItemType.PLAY.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[KaraokeItemType.GROUP.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f6858a = iArr;
    }
}
