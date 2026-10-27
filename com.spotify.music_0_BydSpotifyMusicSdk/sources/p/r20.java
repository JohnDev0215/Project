package p;

import com.spotify.player.model.command.options.PrefetchLevel;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class r20 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[PrefetchLevel.values().length];
        try {
            iArr[0] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[1] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        a = iArr;
    }
}
