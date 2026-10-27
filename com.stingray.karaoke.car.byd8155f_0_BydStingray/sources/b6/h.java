package b6;

import com.stingray.kca.model.KaraokeItemType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f12244a;

    static {
        int[] iArr = new int[KaraokeItemType.values().length];
        try {
            iArr[KaraokeItemType.WEBLINK.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[KaraokeItemType.DEEPLINK.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f12244a = iArr;
    }
}
