package n6;

import com.stingray.karaoke.scoring.NoteResult;

/* JADX INFO: renamed from: n6.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class AbstractC1980m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f20114a;

    static {
        int[] iArr = new int[NoteResult.values().length];
        try {
            iArr[NoteResult.HIT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[NoteResult.MISS.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[NoteResult.UNKNOWN.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        f20114a = iArr;
    }
}
