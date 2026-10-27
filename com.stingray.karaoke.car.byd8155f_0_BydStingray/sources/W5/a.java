package W5;

import com.stingray.kca.model.KaraokeItemChildType;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f9811a;

    static {
        int[] iArr = new int[KaraokeItemChildType.values().length];
        try {
            iArr[KaraokeItemChildType.SONG.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[KaraokeItemChildType.GROUP.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[KaraokeItemChildType.ARTIST.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        f9811a = iArr;
    }
}
